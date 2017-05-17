package tarefas.model;

public class TestaConexao {
	public static void main(String[] args) {
		System.out.println("Conectado com sucesso: " + ConnectionFactory.getConnection());
	}
}
