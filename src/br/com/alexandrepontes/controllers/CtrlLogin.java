package br.com.alexandrepontes.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;

import br.com.alexandrepontes.model.dao.DaoFactory.DaoFactory;
import br.com.alexandrepontes.model.entities.Usuario;


/**
 * Manipula os eventos associados ao processo de autenticação no sistema.
 * @author alepq
 *
 */
public class CtrlLogin extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire
	Button btnlogin;
	@Wire
	Textbox txtuser;
	@Wire
	Textbox txtkeyword;
	
	
	CtrlInserirAluno ctrl = new CtrlInserirAluno();
	@Listen("onClick = #btnlogin")
	public void onClickbtnlogin() throws Exception {
			Usuario u = new Usuario();
			try {				
				if(DaoFactory.criaUsuarioDao().validarUsuario
						(this.txtuser.getText(), this.txtkeyword.getText())){
					Utilidades.mensagem("Dados Corretos! Redirecionando...");
					u = DaoFactory.criaUsuarioDao().obterUsuario(
							this.txtuser.getText(), this.txtkeyword.getText());
					if(u.gettipousuario().equals("padrao")) {
						Sessions.getCurrent().setAttribute("padrao", u.gettipousuario());
						Executions.sendRedirect("/inserir_aluno.zul");
						
						ctrl.tabAtualizarAluno.setVisible(false);
						ctrl.tabPesquisaAlunos.setVisible(true);
					}
					else {
						Executions.sendRedirect("/menu_principal.zul");
						
						ctrl.tabAtualizarAluno.setVisible(true);
						ctrl.tabPesquisaAlunos.setVisible(true);
					}
				}
				else {
					Utilidades.mensagem("Dados incorretos");
				}
			}
			catch (NullPointerException e){
				Utilidades.mensagem("Login ou senha estão incorretos.");
			}
		}	
	
}
