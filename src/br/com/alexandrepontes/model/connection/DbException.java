package br.com.alexandrepontes.model.connection;
/**
 * Classe responsável por executar um método de lançamento de exceções.
 * @author alepq
 *
 */
public class DbException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Lança uma exceção personalizada, de modo que as estruturas de TRY-CATCH
	 * não sejam desnecessariamente utilizadas.
	 * @param msg
	 */
	public DbException(String msg) {
		super(msg);
	}
	
}
