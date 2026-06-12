package com.taskflow.taskflow.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private int idade;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tarefas = new ArrayList<>();

    public User() {}

    public User(String nome, String email, String cpf, int idade, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.idade = idade;
        this.senha = senha;
        this.dataCadastro = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCpf() { return cpf; }
    public int getIdade() { return idade; }
    public String getSenha() { return senha; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public List<Task> getTarefas() { return tarefas; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setTarefas(List<Task> tarefas) { this.tarefas = tarefas; }

    @Override
    public String toString() {
        return "User{id=" + id + ", nome='" + nome + "', email='" + email + "'}";
    }
}