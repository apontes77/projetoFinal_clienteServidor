package br.com.alexandrepontes.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Menuitem;

/**
 * Manipula os eventos associados ao redirecionamento entre páginas da aplicação.
 * @author alepq
 *
 */

public class CtrlMenuPrincipal extends SelectorComposer<Component>{
	

	private static final long serialVersionUID = 1L;
	@Wire
	Menuitem mItemNovoUsuario;
	@Wire
	Menuitem mItemNovoAluno;
	@Wire
	Menuitem menuDisciplinas;
	@Wire
	Menuitem menuTurmas;
	@Wire
	Menuitem mRelSimples;
	@Wire
	Menuitem mRelComplexo;
	
			@Listen("onClick=#mItemNovoUsuario")
			public void onClickItemNovoUsuario() {
				Executions.sendRedirect("/cadastro_usuario.zul");
			}
			
			@Listen("onClick=#mItemNovoAluno")
			public void onClickItemNovoAluno() {
				Executions.sendRedirect("/inserir_aluno.zul");
			}
			
			@Listen("onClick=#menuDisciplinas")
			public void onClickMenuDisciplinas() {
				Executions.sendRedirect("/disciplinas.zul");
			}
			
			@Listen("onClick=#menuTurmas")
			public void onClickMenuTurmas() {
				Executions.sendRedirect("/turmas.zul");
			}
			
			@Listen("onClick=#mRelSimples")
			public void onClickRelSimples() {
				Executions.sendRedirect("/relatorio_simples.zul");
			}
			
			@Listen("onClick=#mRelComplexo")
			public void onClickRelComplexo() {
				Executions.sendRedirect("/relatorio_complexo.zul");
			}
	
	
}
