package com.taskflow.taskflow.repository;

import com.taskflow.taskflow.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface taskRepository extends JpaRepository<Task, Long> {

    // BUSCA TODAS AS TAREFAS ORDENADAS POR URGENCIA
    @Query("SELECT t FROM Task t ORDER BY " +
           "CASE t.ordemUrgencia " +
           "WHEN 'Muito Urgente' THEN 1 " +
           "WHEN 'Urgente' THEN 2 " +
           "WHEN 'Normal' THEN 3 " +
           "WHEN 'Pouco Urgente' THEN 4 " +
           "WHEN 'Nada Urgente' THEN 5 " +
           "ELSE 3 END")
    List<Task> findAllOrderByUrgencia();

    // BUSCA TAREFAS POR STATUS
    List<Task> findByStatusConcluida(boolean statusConcluida);

    // BUSCA TAREFAS POR URGENCIA
    List<Task> findByOrdemUrgencia(String ordemUrgencia);

    // BUSCA TAREFAS POR USER
    List<Task> findByUserId(Long userId);

    // BUSCA TAREFAS PENDENTES POR USER ORDENADAS POR URGENCIA
    @Query("SELECT t FROM Task t WHERE t.user.id = :userId AND t.statusConcluida = false " +
           "ORDER BY CASE t.ordemUrgencia " +
           "WHEN 'Muito Urgente' THEN 1 " +
           "WHEN 'Urgente' THEN 2 " +
           "WHEN 'Normal' THEN 3 " +
           "WHEN 'Pouco Urgente' THEN 4 " +
           "WHEN 'Nada Urgente' THEN 5 END")
    List<Task> findPendentesByUserIdOrderByUrgencia(Long userId);

    // BUSCA POR TITULO
    List<Task> findByTituloContainingIgnoreCase(String titulo);

    // CONTA TAREFAS PENDENTES
    long countByStatusConcluida(boolean statusConcluida);

    // CONTA POR URGENCIA
    long countByOrdemUrgencia(String ordemUrgencia);
}