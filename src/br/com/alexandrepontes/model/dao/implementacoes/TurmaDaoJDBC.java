package br.com.alexandrepontes.model.dao.implementacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.alexandrepontes.model.connection.Conexao;
import br.com.alexandrepontes.model.connection.DbException;
import br.com.alexandrepontes.model.dao.TurmaDao;
import br.com.alexandrepontes.model.entities.Turma;


/**
 * Classe que implementar a interface TurmaDao.
 * @author alepq
 *
 */
public class TurmaDaoJDBC implements TurmaDao {

	private Connection conn;

	public TurmaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	public TurmaDaoJDBC() {
	}

	@Override
	public Turma inserir(Turma t) throws SQLException {
		
		conn = Conexao.getConnection();
		PreparedStatement pstm;
		String select = "select * from turma where nometurma = ?";
		PreparedStatement pstm1 = null;
		pstm1 = conn.prepareStatement(select);
		pstm1.setString(1, t.getnometurma());
		ResultSet rs1 = pstm1.executeQuery();
		if(rs1.next()) {	
			return t;
		}
		String insert = "insert into turma (nometurma, fk_disciplina_iddisc) values (?,?)";
		try {
			pstm = conn.prepareStatement(insert);
			pstm.setString(1, t.getnometurma());
			pstm.setInt(2, t.getfk_disciplina_iddisc());
			pstm.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return t;
	}

	public Turma atualizar (Turma t) {
		String update = "update turma set nometurma=? where idturma =?";
		PreparedStatement pstm1 = null;
		try {
			conn = Conexao.getConnection();
			pstm1 = conn.prepareStatement(update);
			pstm1.setString(1, t.getnometurma());
			pstm1.setInt(2,t.getIdturma());
			int x = pstm1.executeUpdate();
			if(x > 0) {	
				return t;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return t;
	}

	@Override
	public void excluirPorId(Integer id) {
		PreparedStatement st = null;
		try {
			conn = Conexao.getConnection();
			st = conn.prepareStatement("DELETE FROM turma WHERE idturma = ?");
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Conexao.closeStatement(st);
		}

	}

	@Override
	public Turma encontrarPorId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		Turma t = null;
		try {
			conn = Conexao.getConnection();
			st = conn.prepareStatement("SELECT * FROM turma WHERE idturma = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				 t = new Turma();
				t.setIdturma(rs.getInt("idturma"));
				t.setnometurma(rs.getString("nometurma"));
				t.setfk_disciplina_iddisc(rs.getInt("fk_disciplina_iddisc"));

				return t;
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Conexao.closeStatement(st);
			Conexao.closeResultSet(rs);
		}
		return t;
	}
	
	public Integer encontrarPorId(Turma t) {
		PreparedStatement st = null;
		ResultSet rs = null;
		Turma turma = null;

		try {
			conn = Conexao.getConnection();
			st = conn.prepareStatement("SELECT * FROM turma WHERE nometurma = ?");
			st.setString(1, t.getnometurma());
			rs = st.executeQuery();
			if (rs.next()) {
				 turma = new Turma();
				turma.setIdturma(rs.getInt("idturma"));
				turma.setnometurma(rs.getString("nometurma"));
				turma.setfk_disciplina_iddisc(rs.getInt("fk_disciplina_iddisc"));
				
			}
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Conexao.closeStatement(st);
			Conexao.closeResultSet(rs);
		}
		return turma.getIdturma();
	}

	@Override
	public List<Turma> obterTurmas() {

		Statement st = null;
		ResultSet rs = null;
		List<Turma> turmas = new ArrayList<>();
		Turma t;

		try {
			conn = Conexao.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select * from turma");

			while (rs.next()) {
				t = new Turma();
				t.setIdturma(rs.getInt("idturma"));
				t.setnometurma(rs.getString("nometurma"));
				t.setfk_disciplina_iddisc(rs.getInt("fk_disciplina_iddisc"));
				turmas.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			Conexao.closeResultSet(rs);
			Conexao.closeStatement(st);
			Conexao.closeConnection();
		}
		return turmas;

	}

}