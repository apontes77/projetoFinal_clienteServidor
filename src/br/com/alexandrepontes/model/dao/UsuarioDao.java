package br.com.alexandrepontes.model.dao;


import java.util.List;

import br.com.alexandrepontes.model.entities.Usuario;
/**
 * Opera��es elementares de conex�o com a base de dados para a entidade Usu�rio.
 * @author alepq
 *
 */
public interface UsuarioDao {
		boolean inserir(Usuario u) ;
		boolean atualizar (Usuario u);
		boolean validarUsuario (String nomeusuario, String senha);
		List<Usuario> obterUsuarios();
		Usuario obterUsuario(String nomeusuario, String senha);
		
}