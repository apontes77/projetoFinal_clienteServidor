package br.com.alexandrepontes.model.dao.implementacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alexandrepontes.model.connection.Conexao;
import br.com.alexandrepontes.model.connection.DbException;
import br.com.alexandrepontes.model.dao.RegistroDao;
import br.com.alexandrepontes.model.entities.Registro;


/**
 * Classe que implementa a interface RegistroDao
 * @author alepq
 *
 */
public class RegistroDaoJDBC implements RegistroDao{
	
	private Connection conn;
	
	public RegistroDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	public RegistroDaoJDBC() {
		// TODO Auto-generated constructor stub
	}
		
	@Override
public Registro inserir(Registro r) throws SQLException {
		
		
		PreparedStatement pstm = null;

		String insert = "insert into registro (n1,n2,fk_aluno_idaluno, fk_turma_idturma) values (?,?,?,?)";
		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement(insert);
			pstm.setDouble(1, r.getN1());
			pstm.setDouble(2, r.getN2());
			pstm.setInt(3, r.getFk_aluno_idaluno());
			pstm.setInt(4, r.getFk_turma_idturma());
			pstm.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Registro atualizar(Registro r) throws SQLException {
		PreparedStatement psmt = null;		
		ResultSet rs = null;
		Registro r1 = null;
		Connection conn = Conexao.getConnection();
		try {
			psmt = conn.prepareStatement("UPDATE registro SET n1 = ?, n2 = ?"
					+ " WHERE fk_aluno_idaluno = ? AND fk_turma_idturma = ? ");
			psmt.setDouble(1, r.getN1());
			psmt.setDouble(2, r.getN2());
			psmt.setInt(3, r.getFk_aluno_idaluno());
			psmt.setInt(4, r.getFk_turma_idturma());
			int x = psmt.executeUpdate();
			rs = psmt.executeQuery();
			
			if(x > 0) {
				
				r1 = new Registro();
				r1.setN1(rs.getDouble("n1"));
				r1.setN2(rs.getDouble("n2"));
				r1.setFk_aluno_idaluno(rs.getInt("fk_aluno_idaluno"));
				r1.setFk_turma_idturma(rs.getInt("fk_turma_idturma"));			
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Conexao.closeStatement(psmt);
		}
			return r1;
	}
		
	

	@Override
	public List<Registro> encontrarPorId(Integer idturma) {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Registro> registros = new ArrayList<Registro>();
		
		try {
			Connection conn = Conexao.getConnection();
			st = conn.prepareStatement("select nomealuno, nomedisc, nometurma, n1, n2 \r\n" + 
					"from disciplina as d\r\n" + 
					"inner join turma as t\r\n" + 
					"on d.iddisc = t.fk_disciplina_iddisc\r\n" + 
					"inner join aluno as a\r\n" + 
					"inner join registro as r\r\n" + 
					"on a.idaluno = r.idregistro\r\n" + 
					"where t.idturma = ?");
			
			st.setInt(1, idturma);
			rs = st.executeQuery();
			while (rs.next()) {
				Registro registro = new Registro();
				registro.setN1(rs.getFloat("n1"));
				registro.setN1(rs.getFloat("n2"));
				registros.add(registro);	
			}

		}

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Conexao.closeStatement(st);
			Conexao.closeResultSet(rs);
		}
		return null;
	}

}
