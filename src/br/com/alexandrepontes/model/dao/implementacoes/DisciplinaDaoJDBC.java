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
import br.com.alexandrepontes.model.dao.DisciplinaDao;
import br.com.alexandrepontes.model.entities.Disciplina;
/**
 * Classe que implementa a interface DisciplinaDao.
 * @author alepq
 *
 */
public class DisciplinaDaoJDBC implements DisciplinaDao {

	private Connection conn;

	public DisciplinaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	public DisciplinaDaoJDBC() {
		
	}

	@Override
	public Disciplina inserir(Disciplina d) throws SQLException {
		
		conn = Conexao.getConnection();
		PreparedStatement pstm;
		String select = "select * from disciplina where nomedisc = ?";
		PreparedStatement pstm1 = null;
		pstm1 = conn.prepareStatement(select);
		pstm1.setString(1, d.getnomedisc());
		ResultSet rs1 = pstm1.executeQuery();
		if(rs1.next()) {	
			return d;
		}
		String insert = "insert into disciplina (nomedisc) values (?)";
		try {
			pstm = conn.prepareStatement(insert);
			pstm.setString(1, d.getnomedisc());
			pstm.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return d;
	}

	@Override
	public Disciplina atualizar(Disciplina d) {
		String update = "update disciplina set nomedisciplina=? where iddisc =?";
		PreparedStatement pstm1 = null;
		try {
			conn = Conexao.getConnection();
			pstm1 = conn.prepareStatement(update);
			pstm1.setString(1, d.getnomedisc());
			pstm1.setInt(2,d.getIddisc());
			int x = pstm1.executeUpdate();
			if(x > 0) {	
				return d;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return d;
	}
	

	@Override
	public Disciplina encontrarPorId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		Disciplina d = null;
		try {
			conn = Conexao.getConnection();
			st = conn.prepareStatement("SELECT * FROM disciplina WHERE iddisc = ?");
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				d = new Disciplina();
				d.setIddisc(rs.getInt("iddisc"));
				d.setnomedisc(rs.getString("nomedisc"));
			}
			return d;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Conexao.closeConnection();
			Conexao.closeStatement(st);
			Conexao.closeResultSet(rs);
		}

	}

	@Override
	public List<Disciplina> obterDisciplinas() {
		Statement st = null;
		ResultSet rs = null;
		List<Disciplina> disciplinas = new ArrayList<>();
		Disciplina d;

		try {
			conn = Conexao.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select * from disciplina");

			while (rs.next()) {
				d = new Disciplina();
				d.setIddisc(rs.getInt("iddisc"));
				d.setnomedisc(rs.getString("nomedisc"));
				disciplinas.add(d);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			Conexao.closeResultSet(rs);
			Conexao.closeStatement(st);
			Conexao.closeConnection();
		}
		return disciplinas;

	}

	public boolean encontrarPorNome(String nomedisc) {
		PreparedStatement st = null;
		ResultSet rs = null;
		boolean existe = false;

		try {
			conn = Conexao.getConnection();
			st = conn.prepareStatement("SELECT * FROM disciplina WHERE nomedisc = ?");
			st.setString(1, nomedisc);
			rs = st.executeQuery();

			if (rs.next()) {
				existe = true;
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Conexao.closeConnection();
			Conexao.closeStatement(st);
			Conexao.closeResultSet(rs);
		}
		
		return existe;
	}
	
	
	public int encontrarDisciplinaPorNome(String disciplinaSelecionada) {
	PreparedStatement st = null;
	ResultSet rs = null;
	int id = 0;
	
	try {
		st = Conexao.getConnection().prepareStatement("SELECT * FROM disciplina WHERE nomedisc = ?");
		st.setString(1, disciplinaSelecionada);
		rs = st.executeQuery();

		if (rs.next()) {
			Disciplina d = new Disciplina();
			d.setIddisc(rs.getInt("iddisc"));
			d.setnomedisc(rs.getString("nomedisc"));
			id = d.getIddisc();
		}
		
	} catch (SQLException e) {
		throw new DbException(e.getMessage());
	} finally {
		Conexao.closeConnection();
		Conexao.closeStatement(st);
		Conexao.closeResultSet(rs);
	}
	
	return id;
}
	
	public List<Disciplina> encontrarListadeDisciplinasNome(String nomedisc) {
		PreparedStatement st = null;
		ResultSet rs = null;
		Disciplina d;
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		String sql = "SELECT * FROM disciplina WHERE nomedisc like '%" + nomedisc + "%'";

		try {
			st = Conexao.getConnection().prepareStatement(sql);
			rs = st.executeQuery();

			while (rs.next()) {
				 d = new Disciplina();
				d.setIddisc(rs.getInt("iddisc"));
				d.setnomedisc(rs.getString("nomedisc"));
				disciplinas.add(d);
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Conexao.closeConnection();
			Conexao.closeStatement(st);
			Conexao.closeResultSet(rs);
		}
		
		return disciplinas;
	}

	public void excluirPorNome(String nomedisc) {

		PreparedStatement st = null;
		try {
			conn = Conexao.getConnection();
			st = conn.prepareStatement("DELETE FROM disciplina WHERE nomedisc = ?");
			st.setString(1, nomedisc);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Conexao.closeStatement(st);
		}
	}
}