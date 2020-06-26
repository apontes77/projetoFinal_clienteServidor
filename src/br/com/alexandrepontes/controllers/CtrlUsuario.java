package br.com.alexandrepontes.controllers;

import java.sql.SQLException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import br.com.alexandrepontes.model.dao.DaoFactory.DaoFactory;
import br.com.alexandrepontes.model.entities.Usuario;


/**
 * 
 * Manipula o processo de manutenção de usuários na base de dados.
 * @author alepq
 *
 */
public class CtrlUsuario extends SelectorComposer<Component>{

	
	private static final long serialVersionUID = 1L;
	@Wire
	Textbox txtNomeUsuario;
	@Wire
	Textbox txtTipoUser;
	@Wire
	Textbox txtSenha;
	@Wire
	Button btnIncluirUsuario;
	
	@Listen("onClick=#btnIncluirUsuario")
	public void onClickbtnIncluirUsuario() throws SQLException {
		Usuario u = new Usuario();
		u.setnomeusuario(this.txtNomeUsuario.getValue());
		u.settipousuario(this.txtTipoUser.getValue());
		u.setSenha(this.txtSenha.getValue());
		
		if(DaoFactory.criaUsuarioDao().inserir(u)) {
			Messagebox.show("Usuário inserido com sucesso!");
		}
		else {
			Messagebox.show("Erro na inserção deste usuário. Tente novamente.");
		}
	}

}
