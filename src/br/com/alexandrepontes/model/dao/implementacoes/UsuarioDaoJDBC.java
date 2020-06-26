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
import br.com.alexandrepontes.model.dao.UsuarioDao;
import br.com.alexandrepontes.model.entities.Usuario;

/**
 * Classe que implementa a interface UsuarioDao.
 * @author alepq
 *
 */
public class UsuarioDaoJDBC implements UsuarioDao {

	private Connection conn;

	public UsuarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean inserir(Usuario u)   {
		PreparedStatement st = null;
		boolean retorno = false;
				
		try {
			st = conn.prepareStatement("INSERT INTO usuario (nomeusuario, senha, tipousuario) VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, u.getnomeusuario());
			st.setString(2, u.getSenha());
			st.setString(3, u.gettipousuario());

			int linhasAfetadas = st.executeUpdate();
			if (linhasAfetadas > 0) {
				Conexao.closeStatement(st);
				retorno = true;
			} else {
				throw new DbException("Nenhuma linha foi adicionada!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Conexao.closeStatement(st);
		}
		return retorno;

	}

	@Override
	public boolean atualizar(Usuario u) {
		PreparedStatement st = null;
		boolean retorno = false;
		try {
			st = conn.prepareStatement(
					"UPDATE usuario SET nomeusuario = ?, senha= ?, tipousuario = ? WHERE idusuario = ?");
			st.setString(1, u.getnomeusuario());
			st.setString(2, u.getSenha());
			st.setString(3, u.gettipousuario());
			st.setInt(4, u.getIdusuario());
			st.executeUpdate();
			retorno = true;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Conexao.closeStatement(st);
		}
		return retorno;
	}

	@Override
	public List<Usuario> obterUsuarios() {
		Statement st = null;
		ResultSet rs = null;
		List<Usuario> usuarios = new ArrayList<>();
		Usuario u;
		try {
			conn = Conexao.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select * from usuario");

			while (rs.next()) {
				u = new Usuario();
				u.setnomeusuario(rs.getString("nomeusuario"));
				u.settipousuario(rs.getString("tipousuario"));

				usuarios.add(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			Conexao.closeResultSet(rs);
			Conexao.closeStatement(st);
			Conexao.closeConnection();
		}
		return usuarios;

	}

	@Override
	public boolean validarUsuario(String nomeusuario, String senha) {

		PreparedStatement ps = null;
		boolean autenticado = false;
		ResultSet rs;
		try {
			ps = conn.prepareStatement("SELECT nomeusuario, senha, tipousuario FROM usuario WHERE nomeusuario = ? AND senha = ?");
			ps.setString(1, nomeusuario);
			ps.setString(2, senha);
			rs = ps.executeQuery();

			if (rs.next()) {
				autenticado = true;
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return autenticado;
	}
	@Override
	public Usuario obterUsuario(String nomeusuario, String senha) {

		PreparedStatement ps = null;
		
		ResultSet rs;
		Usuario u = new Usuario();

		try {
			ps = conn.prepareStatement("SELECT nomeusuario, senha, tipousuario FROM usuario WHERE nomeusuario = ? AND senha = ?");
			ps.setString(1, nomeusuario);
			ps.setString(2, senha);
			rs = ps.executeQuery();

			if (rs.next()) {
				u.setnomeusuario(rs.getString("nomeusuario"));
				u.setSenha(rs.getString("senha"));
				u.settipousuario(rs.getString("tipousuario"));
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return u;
	}
	
	

}