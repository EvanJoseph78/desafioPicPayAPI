package com.picpaysimplificado.domain.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaysimplificado.domain.dtos.UserDTO;
import com.picpaysimplificado.domain.repositories.UserRepository;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() != UserType.COMUM) {
            throw new Exception("Usuário comerciante não está autorizado a realizar transações");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente!");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }

    public User createUser(UserDTO user) {
        User newUser = new User(user);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return this.repository.findAll();
    }
}
