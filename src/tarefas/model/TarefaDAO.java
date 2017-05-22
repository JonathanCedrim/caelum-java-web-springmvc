package tarefas.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.Statement;

import tarefas.exception.LogicaDeNegocioException;

@Component
public class TarefaDAO {

	private Connection conn;
	private PreparedStatement stmt;
	
	@Autowired
	public TarefaDAO(DataSource dataSource) {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new LogicaDeNegocioException("Erro ao conectar com o baco de dados: " + e);
		}
	}

	public void addTarefa(Tarefa tarefa) throws SQLException {
		String sql = "insert into tarefas " + "(descricao, finalizado, dataFinalizacao)" + "values(?, ?, ?)";

		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));

			stmt.execute();
		} catch (SQLException e) {
			throw new LogicaDeNegocioException("erro ao inserir tarefa: " + e);
		}
	}

	public List<Tarefa> getTarefas() throws SQLException {

		try {
			String sql = "select * from tarefas";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			List<Tarefa> tarefas = new LinkedList<>();

			while (rs.next()) {
				Tarefa tarefa = createTarefa(rs);
				tarefas.add(tarefa);
			}
			rs.close();
			return tarefas;
		} catch (SQLException e) {
			throw new LogicaDeNegocioException("erro ao recuperar tarefas: " + e);
		} 
	}

	public Tarefa getTarefaById(Long id) throws SQLException {
		try {
			String sql = "select * from tarefas where id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Tarefa tarefa = createTarefa(rs);
				rs.close();
				return tarefa;
			}
		} catch (SQLException e) {
			throw new LogicaDeNegocioException("erro ao recuperar tarefa pelo id: " + e);
		}
		return null;
	}

	public List<Tarefa> findTarefaByData(Date data) throws SQLException {
		String sql = "select * from tarefas where data like ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + data + "%");

			ResultSet rs = stmt.executeQuery();
			List<Tarefa> tarefas = new LinkedList<>();

			while (rs.next()) {
				Tarefa tarefa = createTarefa(rs);
				tarefas.add(tarefa);

			}
			rs.close();
			return tarefas;
		} catch (SQLException e) {
			throw new LogicaDeNegocioException("Erro ao buscar por tarefas: " + e);
		}
	}

	public void removeTarefaById(Long id) throws SQLException {
		try {
			String sql = "delete from tarefas where id=?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setLong(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new LogicaDeNegocioException("erro ao remover contato: " + e);
		} finally {
			closeConnection();
		}
	}

	public void removeByData(Date data) throws SQLException {
		try {
			String sql = "delete from tarefas where data=?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setDate(1, data);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new LogicaDeNegocioException("erro ao remover tarefa: " + e);
		}
	}

	public void save(Tarefa tarefa) throws SQLException {
		try {
			if (tarefa.getId() == null) {
				String sql = "insert into tarefas (descricao, finalizado,  dataFinalizacao) " + "values (?, ?, ?)";
				stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn
						.prepareStatement("update tarefas set descricao=?, finalizado=?, dataFinalizacao=? where id=?");
			}
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));

			if (tarefa.getId() != null) {
				stmt.setLong(4, tarefa.getId());
			}
			stmt.execute();
		} catch (SQLException e) {
			throw new LogicaDeNegocioException("Erro ao salvar tarefa: " + e);
		}
	}

	private Tarefa createTarefa(ResultSet rs) throws SQLException {
		Tarefa tarefa = new Tarefa();

		Calendar c = Calendar.getInstance();

		tarefa.setId(rs.getLong("id"));
		tarefa.setDescricao(rs.getString("descricao"));
		tarefa.setFinalizado(rs.getBoolean("finalizado"));
		c.setTime(new Date(rs.getDate("dataFinalizacao").getTime()));

		tarefa.setDataFinalizacao(c);

		return tarefa;
	}

	private void closeConnection() throws SQLException {
		if (this.stmt != null) {
			stmt.close();
		}
		if (this.conn != null) {
			conn.close();
		}
	}
}
