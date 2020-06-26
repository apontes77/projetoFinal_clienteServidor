package br.com.alexandrepontes.model.dao.implementacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alexandrepontes.model.connection.Conexao;
import br.com.alexandrepontes.model.connection.DbException;
import br.com.alexandrepontes.model.dao.AlunoDao;
import br.com.alexandrepontes.model.entities.Aluno;
/**
 * Classe que implementa a interface AlunoDao para conectar-se com a 
 * base de dados relacional.
 * @author alepq
 *
 */
public class AlunoDaoJDBC implements AlunoDao {

	private Connection conn;

	public AlunoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	public AlunoDaoJDBC() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Obtém como resultado a listagem completa de alunos cadastrados.
	 * 
	 * @return
	 */
	public List<Aluno> obterAlunos(Integer i) {

		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Aluno> alunos = new ArrayList<Aluno>();
		Aluno a;

		try {
			conn = Conexao.getConnection();
			psmt = conn.prepareStatement("select idaluno , nomealuno, nomedisc, nometurma \r\n"
					+ "from disciplina as d\r\n" + "inner join turma as t\r\n"
					+ "on d.iddisc = t.fk_disciplina_iddisc\r\n" + "inner join aluno as a\r\n"
					+ "inner join registro as r\r\n" + "on a.idaluno = r.idregistro\r\n" + "where t.idturma = ?");

			psmt.setInt(1, i);
			rs = psmt.executeQuery();

			while (rs.next()) {
				a = new Aluno();
				a.setIdaluno(rs.getInt("idaluno"));
				a.setnomealuno(rs.getString("nomealuno"));
				alunos.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			Conexao.closeResultSet(rs);
			Conexao.closeStatement(psmt);
			Conexao.closeConnection();
		}
		return alunos;
	}

	/**
	 * Realiza consulta de alunos cadastrados via número de identificação (id)
	 * previamente inserido como argumento deste método.
	 */

	public Integer encontrarId(Aluno a) {
		PreparedStatement st = null;
		ResultSet rs = null;
		Aluno aluno = null;

		try {
			conn = Conexao.getConnection();
			st = conn.prepareStatement("SELECT * FROM aluno WHERE nomealuno = ?");
			st.setString(1, a.getnomealuno());
			rs = st.executeQuery();
			if (rs.next()) {
				 aluno = new Aluno();
				 aluno.setIdaluno(rs.getInt("idaluno"));
				 aluno.setMatricula(rs.getInt("matricula"));
				 aluno.setnomealuno(rs.getString("nomealuno"));
			}
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Conexao.closeStatement(st);
			Conexao.closeResultSet(rs);
		}
		return aluno.getIdaluno();
	}
	
	public Aluno retornaId(Aluno a) { 
		PreparedStatement st = null;
		ResultSet rs = null;
		Aluno aluno;
		try {
			conn = Conexao.getConnection();
			st = conn.prepareStatement("SELECT * FROM aluno WHERE nomealuno = ?");
			st.setString(1, a.getnomealuno());
			rs = st.executeQuery();
			if (rs.next()) {
				aluno = new Aluno();
				aluno.setIdaluno(rs.getInt("idaluno"));
				aluno.setnomealuno(rs.getString("nomealuno"));
				aluno.setMatricula(rs.getInt("matricula"));
				return a;
			}
		}

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Conexao.closeStatement(st);
			Conexao.closeResultSet(rs);
		}
		
		
		return a;
	}

	/**
	 * Insere na base de dados um objeto do tipo Aluno contendo somente o nome do
	 * aluno, uma vez que o atributo identificador é auto incrementável na base de
	 * dados, o que exime o usuário de inseri-lo.
	 * @throws SQLException 
	 */
	@Override
	public Aluno inserir(Aluno a) throws SQLException {		
		conn = Conexao.getConnection();
		PreparedStatement pstm;
		String select = "select * from aluno where matricula = ?";
		PreparedStatement pstm1 = null;
		pstm1 = conn.prepareStatement(select);
		pstm1.setInt(1, a.getMatricula());
		ResultSet rs1 = pstm1.executeQuery();
		if(rs1.next()) {	
			return a;
		}
		else {
			String insert = "insert into aluno (nomealuno, matricula) values (?,?)";
			try {
				pstm = conn.prepareStatement(insert);
				pstm.setString(1, a.getnomealuno());
				pstm.setInt(2, a.getMatricula());
				int x = pstm.executeUpdate();
				
				if(x > 0) {
					Aluno aluno = new Aluno();
					aluno.setIdaluno(rs1.getInt("idaluno"));
					aluno.setMatricula(rs1.getInt("matricula"));
					aluno.setnomealuno(rs1.getString("nomealuno"));
					
					return aluno;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}	
		}	
		return a;
	}

	/**
	 * Atualiza um registro existente na base de dados, passando como argumento para
	 * o método o nome e identificador do aluno.
	 */
	@Override
	public Aluno atualizar(Aluno a) {
		String update = "update aluno set nomealuno=? where matricula =?";
		PreparedStatement pstm1 = null;
		try {
			conn = Conexao.getConnection();
			pstm1 = conn.prepareStatement(update);
			pstm1.setString(1, a.getnomealuno());
			pstm1.setInt(2, a.getMatricula());
			int x  = pstm1.executeUpdate();
			if(x > 0) {	
				return a;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return a;
	}
	/**
	 * Possibilita a exclusão de um registro de aluno por meio da inserção de seu
	 * identificador.
	 */
	@Override
	public boolean excluirPorId(Integer id) {

		PreparedStatement st = null;
		boolean excluiu = false;
		try {
			conn = Conexao.getConnection();
			st = conn.prepareStatement("DELETE FROM aluno WHERE idaluno = ?");
			st.setInt(1, id);
			st.executeUpdate();
			
			if(st.execute() == true) {
				excluiu = true;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Conexao.closeStatement(st);
		}
		
		return excluiu;
	}

	@Override
	public boolean excluirPorNome(String nomealuno) {
		boolean excluiu = false;
		PreparedStatement st = null;
		try {
			conn = Conexao.getConnection();
			st = conn.prepareStatement("DELETE FROM aluno WHERE nomealuno = ?");
			st.setString(1, nomealuno);
			st.executeUpdate();
			excluiu = true;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Conexao.closeStatement(st);
		}

		return excluiu;
	}

	@Override
	public Aluno encontrarPorId(Aluno a) {
		// TODO Auto-generated method stub
		return null;
	}

}