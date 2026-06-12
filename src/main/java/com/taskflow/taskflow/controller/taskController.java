package com.taskflow.taskflow.controller;
import jakarta.servlet.http.HttpSession;
import com.taskflow.taskflow.model.Task;
import com.taskflow.taskflow.model.User;
import com.taskflow.taskflow.service.TaskService;
import com.taskflow.taskflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class taskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    // PAGINA PRINCIPAL - lista tarefas
    @GetMapping("/")
    public String index(Model model) {
        List<Task> tarefas = taskService.listarTodasPorUrgencia();
        model.addAttribute("tarefas", tarefas);
        model.addAttribute("totalTarefas", tarefas.size());
        model.addAttribute("pendentes", taskService.contarPendentes());
        model.addAttribute("concluidas", taskService.contarConcluidas());
        model.addAttribute("muitoUrgentes", taskService.contarPorUrgencia("Muito Urgente"));
        return "index";
    }

    // PAGINA DE ADICIONAR TAREFA
    @GetMapping("/adicionar")
    public String adicionarForm(Model model) {
        model.addAttribute("task", new Task());
        return "adicionar";
    }

    // SALVAR TAREFA
    @PostMapping("/salvar")
    public String salvarTarefa(@RequestParam String titulo,
                                @RequestParam String descricao,
                                @RequestParam int horarioInicio,
                                @RequestParam int horarioFim,
                                @RequestParam String ordemUrgencia,
                                @RequestParam String cor) {
        User user = userService.listarTodos().get(0);
        taskService.criarTarefa(titulo, descricao, horarioInicio,
                                horarioFim, ordemUrgencia, cor, user);
        return "redirect:/";
    }

    // CONCLUIR TAREFA
    @PostMapping("/concluir/{id}")
    public String concluirTarefa(@PathVariable Long id) {
        taskService.concluirTarefa(id);
        return "redirect:/";
    }

    // DELETAR TAREFA
    @PostMapping("/deletar/{id}")
    public String deletarTarefa(@PathVariable Long id) {
        taskService.deletarTarefa(id);
        return "redirect:/";
    }

    // PAGINA DE EDITAR TAREFA
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Task task = taskService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Tarefa nao encontrada!"));
        model.addAttribute("task", task);
        return "editar";
    }

    // ATUALIZAR TAREFA
    @PostMapping("/atualizar/{id}")
    public String atualizarTarefa(@PathVariable Long id,
                                   @RequestParam String titulo,
                                   @RequestParam String descricao,
                                   @RequestParam int horarioInicio,
                                   @RequestParam int horarioFim,
                                   @RequestParam String ordemUrgencia,
                                   @RequestParam String cor) {
        taskService.atualizarTarefa(id, titulo, descricao,
                                    horarioInicio, horarioFim,
                                    ordemUrgencia, cor);
        return "redirect:/";
    }

    // PAGINA DE ESTATISTICAS
    @GetMapping("/estatisticas")
    public String estatisticas(Model model) {
        model.addAttribute("total", taskService.listarTodasPorUrgencia().size());
        model.addAttribute("pendentes", taskService.contarPendentes());
        model.addAttribute("concluidas", taskService.contarConcluidas());
        model.addAttribute("muitoUrgentes", taskService.contarPorUrgencia("Muito Urgente"));
        model.addAttribute("urgentes", taskService.contarPorUrgencia("Urgente"));
        model.addAttribute("normais", taskService.contarPorUrgencia("Normal"));
        model.addAttribute("poucoUrgentes", taskService.contarPorUrgencia("Pouco Urgente"));
        model.addAttribute("nadaUrgentes", taskService.contarPorUrgencia("Nada Urgente"));
        return "estatisticas";
    }

    // LOGIN
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // PROCESSAR LOGIN
    @PostMapping("/login")
    public String processarLogin(@RequestParam String email,
                                  @RequestParam String senha,
                                  HttpSession session,
                                  Model model) {
        return userService.login(email, senha)
                .map(user -> {
                    session.setAttribute("usuarioLogado", user);
                    return "redirect:/";
                })
                .orElseGet(() -> {
                    model.addAttribute("erro", "Email ou senha incorretos!");
                    return "login";
                });
    }
    // CADASTRO
    @GetMapping("/cadastro")
    public String cadastroForm() {
        return "cadastro";
    }

    // PROCESSAR CADASTRO
    @PostMapping("/cadastro")
    public String processarCadastro(@RequestParam String nome,
                                     @RequestParam String email,
                                     @RequestParam String cpf,
                                     @RequestParam int idade,
                                     @RequestParam String senha,
                                     Model model) {
        try {
            userService.cadastrarUsuario(nome, email, cpf, idade, senha);
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            return "cadastro"; }
    } }
        