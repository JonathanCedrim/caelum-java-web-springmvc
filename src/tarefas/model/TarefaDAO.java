package tarefas.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.Statement;

import tarefas.exception.LogicaDeNegocioException;

public class TarefaDAO {

	private Connection conn;
	private PreparedStatement stmt = null;

	public TarefaDAO(Connection conn) {
		this.conn = conn;
	}

	public void addTarefa(Tarefa tarefa) throws SQLException {
		String sql = "insert into tarefas " + "(descricao, finalizado, dataFinalizacao)" + "values(?, ?, ?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));

			stmt.execute();
		} catch (SQLException e) {
			throw new LogicaDeNegocioException("erro ao inserir tarefa: " + e);
		} finally {
			closeConnection();
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
		} finally {
			closeConnection();
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
		} finally {
			closeConnection();
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
		} finally {
			closeConnection();
		}
	}

	public void removeById(Long id) throws SQLException {
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
		} finally {
			closeConnection();
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
		} finally {
			closeConnection();
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
