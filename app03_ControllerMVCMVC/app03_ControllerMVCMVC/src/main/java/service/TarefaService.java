package service;

import model.Tarefa;
import model.Usuario;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TarefaService {

    // Map usuário -> lista de tarefas
    private final Map<String, List<Tarefa>> tarefasPorUsuario = new HashMap<>();

    public List<Tarefa> listarTarefasDoUsuario(Usuario usuario) {
        return tarefasPorUsuario.getOrDefault(usuario.getLogin(), new ArrayList<>());
    }

    public void adicionarTarefa(Usuario usuario, Tarefa tarefa) {
        List<Tarefa> tarefas = tarefasPorUsuario.computeIfAbsent(usuario.getLogin(), k -> new ArrayList<>());
        tarefas.add(tarefa);
    }

    public void concluirTarefa(Usuario usuario, int indice, String dataConclusao) {
        List<Tarefa> tarefas = tarefasPorUsuario.get(usuario.getLogin());
        if (tarefas != null && indice >= 0 && indice < tarefas.size()) {
            Tarefa tarefa = tarefas.get(indice);
            tarefa.setConcluida(true);
            tarefa.setDataConclusao(dataConclusao);
        }
    }
}
