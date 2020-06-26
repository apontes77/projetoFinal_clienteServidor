package br.com.alexandrepontes.model.dao.DaoFactory;

import java.sql.SQLException;

import br.com.alexandrepontes.model.connection.Conexao;
import br.com.alexandrepontes.model.dao.AlunoDao;
import br.com.alexandrepontes.model.dao.BuscaGeralDao;
import br.com.alexandrepontes.model.dao.DisciplinaDao;
import br.com.alexandrepontes.model.dao.RegistroDao;
import br.com.alexandrepontes.model.dao.TurmaDao;
import br.com.alexandrepontes.model.dao.UsuarioDao;
import br.com.alexandrepontes.model.dao.implementacoes.AlunoDaoJDBC;
import br.com.alexandrepontes.model.dao.implementacoes.BuscaGeralDaoJDBC;
import br.com.alexandrepontes.model.dao.implementacoes.DisciplinaDaoJDBC;
import br.com.alexandrepontes.model.dao.implementacoes.RegistroDaoJDBC;
import br.com.alexandrepontes.model.dao.implementacoes.TurmaDaoJDBC;
import br.com.alexandrepontes.model.dao.implementacoes.UsuarioDaoJDBC;

/**
 * Implementa o padr�o de projeto "Factory", que consiste no uso de m�todos espec�ficos
 * para instancia��o de determinados objetos de maneira otimizada. Aqui, utiliza-se este padr�o
 * para instanciar objetos de classes que implementam opera��es em Banco de Dados.
 * @author alexandre
 *
 */

public class DaoFactory {
	
	public static AlunoDao criaAlunoDao() throws SQLException {
		return new AlunoDaoJDBC(Conexao.getConnection());
	}
	
	public static DisciplinaDao criaDisciplinaDao() throws SQLException {
		return new DisciplinaDaoJDBC(Conexao.getConnection());
	}
	
	public static RegistroDao criaRegistroDao() throws SQLException {
		return new RegistroDaoJDBC(Conexao.getConnection());
	}
	
	
	public static TurmaDao criaTurmaDao() throws SQLException{
		return new TurmaDaoJDBC(Conexao.getConnection());
	}
	
	public static UsuarioDao criaUsuarioDao() throws SQLException{
		return new UsuarioDaoJDBC(Conexao.getConnection());
	}
	
	public static BuscaGeralDao criaBuscaGeralDao() throws SQLException{
		return new BuscaGeralDaoJDBC(Conexao.getConnection());
	}
	
	

}
