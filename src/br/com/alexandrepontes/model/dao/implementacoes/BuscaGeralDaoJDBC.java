package br.com.alexandrepontes.model.dao.implementacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alexandrepontes.model.connection.Conexao;
import br.com.alexandrepontes.model.dao.BuscaGeralDao;
import br.com.alexandrepontes.model.entities.BuscaGeral;

/**
 * Classe que implementa a interface BuscaGeralDao, a fim de conectar-se com a
 * base de dados relacional.
 * 
 * @author alepq
 *
 */
public class BuscaGeralDaoJDBC implements BuscaGeralDao {

	private Connection conn;

	public BuscaGeralDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	public BuscaGeralDaoJDBC() {

	}

	@Override
	public List<BuscaGeral> cadastroPorMatriculaDeAluno(Integer matricula) {

		List<BuscaGeral> cadastros = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		BuscaGeral buscas;
		String sql = "select idaluno, matricula, nomealuno, nomedisc, nometurma, n1, n2 \r\n"
				+ " from disciplina as d\r\n" + " inner join turma as t\r\n"
				+ "	on d.iddisc = t.fk_disciplina_iddisc\r\n" + " inner join registro as r\r\n"
				+ "	inner join aluno as a\r\n" + "	on a.idaluno = r.fk_aluno_idaluno\r\n"
				+ " on t.idturma = r.fk_turma_idturma" + " where a.matricula = " + matricula;
		try {
			psmt = Conexao.getConnection().prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				buscas = new BuscaGeral();
				buscas.setIdaluno(rs.getInt("idaluno"));
				buscas.setMatricula(rs.getInt("matricula"));
				buscas.setNomealuno(rs.getString("nomealuno"));
				buscas.setDisciplina(rs.getString("nomedisc"));
				buscas.setNometurma(rs.getString("nometurma"));
				buscas.setN1(rs.getDouble("n1"));
				buscas.setN2(rs.getDouble("n2"));

				cadastros.add(buscas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexao.closeResultSet(rs);
			Conexao.closeStatement(psmt);
			Conexao.closeConnection();
		}
		return cadastros;
	}

	public BuscaGeral cadastroUnicoMatriculaDeAluno(Integer idturma, Integer matricula) {

		PreparedStatement psmt = null;
		ResultSet rs = null;
		BuscaGeral buscas = null;

		try {
			conn = Conexao.getConnection();
			psmt = conn.prepareStatement("select idaluno, matricula, nomealuno, nomedisc, nometurma, n1, n2\r\n"
					+ " from disciplina as d\r\n" + " inner join turma as t\r\n"
					+ " on d.iddisc = t.fk_disciplina_iddisc\r\n" + " inner join aluno as a\r\n"
					+ " inner join registro as r\r\n" + " on a.idaluno = r.fk_aluno_idaluno\r\n"
					+ " where t.idturma = ? and a.matricula = ?");

			psmt.setInt(1, idturma);
			psmt.setInt(2, matricula);
			rs = psmt.executeQuery();

			while (rs.next()) {
				buscas = new BuscaGeral();
				buscas.setIdaluno(rs.getInt("idaluno"));
				buscas.setMatricula(rs.getInt("matricula"));
				buscas.setNomealuno(rs.getString("nomealuno"));
				buscas.setDisciplina(rs.getString("nomedisc"));
				buscas.setNometurma(rs.getString("nometurma"));
				buscas.setN1(rs.getDouble("n1"));
				buscas.setN2(rs.getDouble("n2"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexao.closeResultSet(rs);
			Conexao.closeStatement(psmt);
			Conexao.closeConnection();
		}
		return buscas;
	}

	@Override
	public List<BuscaGeral> cadastroPorNomeDeAluno(String nomealuno) {

		List<BuscaGeral> cadastros = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		BuscaGeral buscas;
		String sql = "select idaluno, matricula, nomealuno, nomedisc, nometurma, n1, n2 \r\n"
				+ " from disciplina as d\r\n" + " inner join turma as t\r\n"
				+ "	on d.iddisc = t.fk_disciplina_iddisc\r\n" + " inner join registro as r\r\n"
				+ "	inner join aluno as a\r\n" + "	on a.idaluno = r.fk_aluno_idaluno\r\n"
				+ " on t.idturma = r.fk_turma_idturma" + " where a.nomealuno like '%" + nomealuno + "%'";
		try {
			psmt = Conexao.getConnection().prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				buscas = new BuscaGeral();
				buscas.setIdaluno(rs.getInt("idaluno"));
				buscas.setMatricula(rs.getInt("matricula"));
				buscas.setNomealuno(rs.getString("nomealuno"));
				buscas.setDisciplina(rs.getString("nomedisc"));
				buscas.setNometurma(rs.getString("nometurma"));
				buscas.setN1(rs.getDouble("n1"));
				buscas.setN2(rs.getDouble("n2"));

				cadastros.add(buscas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexao.closeResultSet(rs);
			Conexao.closeStatement(psmt);
			Conexao.closeConnection();
		}
		return cadastros;
	}

	public List<BuscaGeral> todosOsCadastros() {

		List<BuscaGeral> cadastros = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		BuscaGeral buscas;
		String sql = "select idaluno, matricula, nomealuno, nomedisc, nometurma, n1, n2 \r\n"
				+ " from disciplina as d\r\n" + " inner join turma as t\r\n"
				+ "	on d.iddisc = t.fk_disciplina_iddisc\r\n" + " inner join registro as r\r\n"
				+ "	inner join aluno as a\r\n" + "	on a.idaluno = r.fk_aluno_idaluno\r\n"
				+ " on t.idturma = r.fk_turma_idturma";
		try {
			psmt = Conexao.getConnection().prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				buscas = new BuscaGeral();
				buscas.setIdaluno(rs.getInt("idaluno"));
				buscas.setMatricula(rs.getInt("matricula"));
				buscas.setNomealuno(rs.getString("nomealuno"));
				buscas.setDisciplina(rs.getString("nomedisc"));
				buscas.setNometurma(rs.getString("nometurma"));
				buscas.setN1(rs.getDouble("n1"));
				buscas.setN2(rs.getDouble("n2"));

				cadastros.add(buscas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexao.closeResultSet(rs);
			Conexao.closeStatement(psmt);
			Conexao.closeConnection();
		}
		return cadastros;
	}

	@Override
	public List<BuscaGeral> obterTurmasComDisciplinas(String nometurma) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		BuscaGeral buscas;
		List<BuscaGeral> turmasdisc = new ArrayList<>();

		String sql = "select nomedisc, nometurma\r\n" + " from disciplina as d\r\n" + " inner join turma as t\r\n"
				+ " on d.iddisc = t.fk_disciplina_iddisc\r\n" + " WHERE t.nometurma like '%" + nometurma + "%'";

		try {
			psmt = Conexao.getConnection().prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				buscas = new BuscaGeral();
				buscas.setDisciplina(rs.getString("nomedisc"));
				buscas.setNometurma(rs.getString("nometurma"));
				turmasdisc.add(buscas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			Conexao.closeResultSet(rs);
			Conexao.closeStatement(psmt);
			Conexao.closeConnection();
		}
		return turmasdisc;

	}

	public BuscaGeral obtemAlunoSelecionado(String nomedisc, String nometurma, Integer matricula) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		BuscaGeral bg = null;

		String sql = "select idaluno, matricula, nomealuno, nomedisc, idturma, nometurma, n1, n2\r\n"
				+ " from disciplina as d\r\n" + " inner join turma as t\r\n"
				+ " on d.iddisc = t.fk_disciplina_iddisc\r\n" + " inner join registro as r\r\n"
				+ " inner join aluno as a\r\n" + " on a.idaluno = r.fk_aluno_idaluno\r\n"
				+ " on t.idturma = r.fk_turma_idturma\r\n" + " where d.nomedisc like '%" + nomedisc + "%'"
				+ " and t.nometurma like '%" + nometurma + "%' " + " and a.matricula = " + matricula;
		try {
			psmt = Conexao.getConnection().prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				bg = new BuscaGeral();
				bg.setIdaluno(rs.getInt("idaluno"));
				bg.setMatricula(rs.getInt("matricula"));
				bg.setNomealuno(rs.getString("nomealuno"));
				bg.setDisciplina(rs.getString("nomedisc"));
				bg.setIdturma(rs.getInt("idturma"));
				bg.setNometurma(rs.getString("nometurma"));
				bg.setN1(rs.getDouble("n1"));
				bg.setN2(rs.getDouble("n2"));
			}
		} catch (Exception e) {
			bg = null;
		}

		return bg;
	}

}
