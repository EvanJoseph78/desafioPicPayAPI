package com.picpaysimplificado.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpaysimplificado.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Jpa cria automaticamente uma query no banco de dados baseado na assinatura
    // do método, seguindo um certo padrão de escrita
    Optional<User> findUserByDocument(String document);
    Optional<User> findUserById(Long id);
}
