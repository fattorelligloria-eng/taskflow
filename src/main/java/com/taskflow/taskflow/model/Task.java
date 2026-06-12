package com.taskflow.taskflow.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private int horarioInicio;

    @Column(nullable = false)
    private int horarioFim;

    @Column(nullable = false)
    private String ordemUrgencia;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = false)
    private boolean statusConcluida = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column
    private LocalDateTime dataConclusao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // CONSTRUTOR VAZIO
    public Task() {}

    // CONSTRUTOR COMPLETO
    public Task(String titulo, String descricao, int horarioInicio,
                int horarioFim, String ordemUrgencia, String cor, User user) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.ordemUrgencia = ordemUrgencia;
        this.cor = cor;
        this.statusConcluida = false;
        this.dataCriacao = LocalDateTime.now();
        this.user = user;
    }

    // METODO PARA CONCLUIR TAREFA
    public void concluir() {
        this.statusConcluida = true;
        this.dataConclusao = LocalDateTime.now();
    }

    // GETTERS
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public int getHorarioInicio() { return horarioInicio; }
    public int getHorarioFim() { return horarioFim; }
    public String getOrdemUrgencia() { return ordemUrgencia; }
    public String getCor() { return cor; }
    public boolean isStatusConcluida() { return statusConcluida; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public LocalDateTime getDataConclusao() { return dataConclusao; }
    public User getUser() { return user; }

    // SETTERS
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setHorarioInicio(int horarioInicio) { this.horarioInicio = horarioInicio; }
    public void setHorarioFim(int horarioFim) { this.horarioFim = horarioFim; }
    public void setOrdemUrgencia(String ordemUrgencia) { this.ordemUrgencia = ordemUrgencia; }
    public void setCor(String cor) { this.cor = cor; }
    public void setStatusConcluida(boolean statusConcluida) { this.statusConcluida = statusConcluida; }
    public void setUser(User user) { this.user = user; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", ordemUrgencia='" + ordemUrgencia + '\'' +
                ", statusConcluida=" + statusConcluida +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}