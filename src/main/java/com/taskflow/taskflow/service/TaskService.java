package com.taskflow.taskflow.service;

import com.taskflow.taskflow.model.Task;
import com.taskflow.taskflow.model.User;
import com.taskflow.taskflow.repository.taskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private taskRepository taskRepository;

    // CRIAR TAREFA
    public Task criarTarefa(String titulo, String descricao, int horarioInicio,
                             int horarioFim, String ordemUrgencia, String cor, User user) {
        Task task = new Task(titulo, descricao, horarioInicio, horarioFim, ordemUrgencia, cor, user);
        return taskRepository.save(task);
    }

    // LISTAR TODAS ORDENADAS POR URGENCIA
    public List<Task> listarTodasPorUrgencia() {
        return taskRepository.findAllOrderByUrgencia();
    }

    // LISTAR PENDENTES POR USER
    public List<Task> listarPendentesPorUser(Long userId) {
        return taskRepository.findPendentesByUserIdOrderByUrgencia(userId);
    }

    // LISTAR POR URGENCIA
    public List<Task> listarPorUrgencia(String urgencia) {
        return taskRepository.findByOrdemUrgencia(urgencia);
    }

    // BUSCAR POR ID
    public Optional<Task> buscarPorId(Long id) {
        return taskRepository.findById(id);
    }

    // BUSCAR POR TITULO
    public List<Task> buscarPorTitulo(String titulo) {
        return taskRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // CONCLUIR TAREFA
    public Task concluirTarefa(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa nao encontrada!"));
        task.concluir();
        return taskRepository.save(task);
    }

    // ATUALIZAR TAREFA
    public Task atualizarTarefa(Long id, String titulo, String descricao,
                                 int horarioInicio, int horarioFim,
                                 String ordemUrgencia, String cor) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa nao encontrada!"));
        task.setTitulo(titulo);
        task.setDescricao(descricao);
        task.setHorarioInicio(horarioInicio);
        task.setHorarioFim(horarioFim);
        task.setOrdemUrgencia(ordemUrgencia);
        task.setCor(cor);
        return taskRepository.save(task);
    }

    // DELETAR TAREFA
    public void deletarTarefa(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Tarefa nao encontrada!");
        }
        taskRepository.deleteById(id);
    }

    // ESTATISTICAS
    public long contarPendentes() {
        return taskRepository.countByStatusConcluida(false);
    }

    public long contarConcluidas() {
        return taskRepository.countByStatusConcluida(true);
    }

    public long contarPorUrgencia(String urgencia) {
        return taskRepository.countByOrdemUrgencia(urgencia);
    }
}