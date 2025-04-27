package controller;

import model.Tarefa;
import model.Usuario;
import service.TarefaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public String listarTarefas(Model modelo, HttpSession sessao) {
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        modelo.addAttribute("tarefas", tarefaService.listarTarefasDoUsuario(usuario));
        return "tarefas"; // vai buscar o "tarefas.html"
    }

    @PostMapping("/adicionar")
    public String adicionarTarefa(@RequestParam("descricao") String descricao, HttpSession sessao) {
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(descricao);
        tarefa.setConcluida(false);
        tarefa.setDataCriacao(LocalDate.now().toString());
        tarefa.setDataConclusao("");

        tarefaService.adicionarTarefa(usuario, tarefa);
        return "redirect:/tarefas";
    }

    @PostMapping("/concluir/{indice}")
    public String concluirTarefa(@PathVariable int indice, HttpSession sessao) {
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        String dataConclusao = LocalDate.now().toString();
        tarefaService.concluirTarefa(usuario, indice, dataConclusao);
        return "redirect:/tarefas";
    }
}
