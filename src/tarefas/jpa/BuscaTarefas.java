package tarefas.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import tarefas.model.Tarefa;

public class BuscaTarefas {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefas");
		EntityManager manager = factory.createEntityManager();

		Query query = manager.createQuery("select t from Tarefa as t where t.finalizado = :paramFinalizado");
		query.setParameter("paramFinalizado", true);

		List<Tarefa> lista = query.getResultList();

		for (Tarefa tarefa : lista) {
			System.out.println(tarefa.getDescricao());
			System.out.println(tarefa.getDataFinalizacao());
			System.out.println(tarefa.isFinalizado());
		}
		
		manager.close();
	}
}
