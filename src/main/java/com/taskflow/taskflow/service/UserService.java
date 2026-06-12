package com.taskflow.taskflow.service;

import com.taskflow.taskflow.model.User;
import com.taskflow.taskflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // CADASTRAR USUARIO
    public User cadastrarUsuario(String nome, String email, String cpf, int idade, String senha) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email ja cadastrado!");
        }
        if (userRepository.existsByCpf(cpf)) {
            throw new RuntimeException("CPF ja cadastrado!");
        }
        User user = new User(nome, email, cpf, idade, senha);
        return userRepository.save(user);
    }

    // LOGIN
    public Optional<User> login(String email, String senha) {
        return userRepository.findByEmailAndSenha(email, senha);
    }

    // BUSCAR POR ID
    public Optional<User> buscarPorId(Long id) {
        return userRepository.findById(id);
    }

    // BUSCAR POR EMAIL
    public Optional<User> buscarPorEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // LISTAR TODOS
    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    // ATUALIZAR USUARIO
    public User atualizarUsuario(Long id, String nome, String email, int idade) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado!"));
        user.setNome(nome);
        user.setEmail(email);
        user.setIdade(idade);
        return userRepository.save(user);
    }

    // DELETAR USUARIO
    public void deletarUsuario(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario nao encontrado!");
        }
        userRepository.deleteById(id);
    }

    // VERIFICAR SE EMAIL EXISTE
    public boolean emailExiste(String email) {
        return userRepository.existsByEmail(email);
    }
}