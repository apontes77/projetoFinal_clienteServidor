package br.com.alexandrepontes.model.connection;
/**
 * Classe respons�vel por executar um m�todo de lan�amento de exce��es.
 * @author alepq
 *
 */
public class DbException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Lan�a uma exce��o personalizada, de modo que as estruturas de TRY-CATCH
	 * n�o sejam desnecessariamente utilizadas.
	 * @param msg
	 */
	public DbException(String msg) {
		super(msg);
	}
	
}
