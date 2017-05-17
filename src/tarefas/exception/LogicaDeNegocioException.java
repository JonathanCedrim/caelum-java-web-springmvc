package tarefas.exception;

public class LogicaDeNegocioException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LogicaDeNegocioException(String msg) {
		super(msg);
	}

}
