package tarefas.dao;

import java.util.List;

import tarefas.model.Tarefa;

public interface TarefaDao {
	Tarefa buscaPorId(Long id);
    List<Tarefa> lista();
	void adiciona(Tarefa t);
	void altera(Tarefa t);
	void remove(Tarefa t);
	void finaliza(Long id);
}
