package com.taskflow.taskflow.repository;

import com.taskflow.taskflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // BUSCA USER POR EMAIL
    Optional<User> findByEmail(String email);

    // BUSCA USER POR CPF
    Optional<User> findByCpf(String cpf);

    // VERIFICA SE EMAIL JA EXISTE
    boolean existsByEmail(String email);

    // VERIFICA SE CPF JA EXISTE
    boolean existsByCpf(String cpf);

    // LOGIN - busca por email e senha
    Optional<User> findByEmailAndSenha(String email, String senha);
}