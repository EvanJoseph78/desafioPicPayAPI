package com.picpaysimplificado.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.domain.dtos.TransactionDTO;
import com.picpaysimplificado.domain.repositories.TransactionRepository;
import com.picpaysimplificado.domain.services.UserService;
import com.picpaysimplificado.domain.user.User;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User reciever = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAutorized = this.authorizeTransaction(sender, transaction.value());

        if (!isAutorized) {
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setQuantidadeTransacao(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(reciever);
        newTransaction.setHoraTransacao(LocalDateTime.now());
        
        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        reciever.setBalance(reciever.getBalance().add(transaction.value()));
        
        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(reciever);

    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> authorizationResponse = restTemplate
                .getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else
            return false;

    }
}
