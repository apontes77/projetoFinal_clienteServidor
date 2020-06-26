package br.com.alexandrepontes.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Respons�vel por instanciar um objeto da classe Connection, ou seja, conectar-se com uma
 * base de dados relacional.
 * @author alepq
 *
 */
public class Conexao {

	private static Connection conn = null;
	
	public Conexao() {
		
	}
		/**
	 * Utiliza os dados do arquivo de conex�o para concretizar 
	 * o acesso a dados do SGBD escolhido. Portanto, uma conex�o com alguma 
	 * base de dados resume-se a instanciar um objeto da classe Connection.
	 * @return
	 */
	
	public static Connection getConnection() throws SQLException{
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		String url = "jdbc:mysql://localhost:3306/projetofinal?allowPublicKeyRetrieval=true&useSSL=false&useTimezone=true&serverTimezone=UTC";
		Connection conn = DriverManager.getConnection(url, "root", "1270apc16");
		return conn;
	}
	
	
	/**
	 * Termina uma conex�o anteriormente instanciada e realizada com algum Banco de Dados.
	 */

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	/**
	 * Trata de finalizar o uso de m�todos da classe Statement de forma manual,
	 * a fim de otimizar a utiliza��o de recursos de mem�ria.
	 * @param st
	 */
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	/**
	 * Trata de finalizar o uso de m�todos da classe ResultSet de forma manual,
	 * a fim de otimizar a utiliza��o de recursos de mem�ria.
	 * @param rs
	 */
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	
}
