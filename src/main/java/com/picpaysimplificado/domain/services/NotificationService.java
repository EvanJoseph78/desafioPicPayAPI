package com.picpaysimplificado.domain.services;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.domain.dtos.NotificationRequestDTO;
import com.picpaysimplificado.domain.user.User;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationRequestDTO notificationRequest = new NotificationRequestDTO(email, message);
        // ResponseEntity<String> notificationResponse = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify",
        //         notificationRequest, String.class);

        // if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
        //     System.out.println("Erro no serviço de notificação");
        //     throw new Exception("Serviço de notificação fora do ar");
        // }

        System.out.println("Notificação enviada para o usuário");
    }
}
