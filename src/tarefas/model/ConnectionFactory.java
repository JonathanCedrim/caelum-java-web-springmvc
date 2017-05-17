package tarefas.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import tarefas.exception.LogicaDeNegocioException;

public class ConnectionFactory {
	private static String user = "caelum";
	private static String password = "123";
	private static String url = "jdbc:mysql://localhost/caelumJavaWeb";

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			throw new LogicaDeNegocioException("erro ao abrir conexao com a base de dados : " + e);
		}
	}
}