package tarefas.usuarios;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tarefas.exception.LogicaDeNegocioException;
import tarefas.login.Usuario;

@Component
public class UserDAO {

	private Connection conn;

	@Autowired
	public UserDAO(DataSource dataSource) {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new LogicaDeNegocioException("erro ao conectar com a base de dados: " + e);
		}
	}

	public boolean searchUser(Usuario usuario) {
		String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";

		return true;
	/*	try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();;
			
			
	
			}
		} catch (SQLException e) {
			throw new LogicaDeNegocioException("erro ao comparar login: " + e);
		}
*/
	}

}
