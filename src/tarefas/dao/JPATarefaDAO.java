package tarefas.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tarefas.model.Tarefa;

@Repository
public class JPATarefaDAO implements TarefaDao {

	@PersistenceContext
	EntityManager manager;

	@Override
	public Tarefa buscaPorId(Long id) {
		return manager.find(Tarefa.class, id);
	}

	@Override
	public List<Tarefa> lista() {
		return manager.createQuery("SELECT t FROM Tarefa t").getResultList();
	}

	@Override
	public void adiciona(Tarefa t) {
		manager.persist(t);
	}

	@Override
	public void altera(Tarefa t) {
		manager.merge(t);
	}

	@Override
	public void remove(Tarefa t) {
		manager.remove(buscaPorId(t.getId()));
	}

	@Override
	public void finaliza(Long id) {
		Tarefa tarefa = buscaPorId(id);
		tarefa.setFinalizado(true);
		tarefa.setDataFinalizacao(Calendar.getInstance());
	}
}
