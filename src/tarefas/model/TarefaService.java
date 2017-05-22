package tarefas.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tarefas.exception.LogicaDeNegocioException;

@Component
public class TarefaService {
	
	
	private TarefaDAO dao;
	
	@Autowired	
	public TarefaService(TarefaDAO dao) {
		this.dao = dao;
	}
	
	public void addTarefa(Tarefa tarefa) {
		try {
			dao.addTarefa(tarefa);
		} catch (SQLException e) {
			throw new LogicaDeNegocioException("erro ao adicionar tarefa: " + e);
		}
	}

	public void removeTarefaById(Long id) {
		try {
			dao.removeTarefaById(id);
		} catch (SQLException e) {
			throw new LogicaDeNegocioException("erro ao remover tarefa pelo id: " + e);
		}
	}

	public List<Tarefa> getTarefas() {
		try {
			return dao.getTarefas();
		} catch (SQLException e) {
			throw new LogicaDeNegocioException("erro ao recuperar tarefas: +" + e);
		}
	}

	public Tarefa getTarefaById(Long id) {
		try {
			return dao.getTarefaById(id);
		} catch (SQLException e) {
			throw new LogicaDeNegocioException("erro ao recuperar tarefa pelo id: " + e);
		}
	}

	public List<Tarefa> findByData(Date data) {
		try {
			return dao.findTarefaByData(data);
		} catch (SQLException e) {
			throw new LogicaDeNegocioException("erro ao recuperar tarefas pela data: " + e);
		}
	}
	
}