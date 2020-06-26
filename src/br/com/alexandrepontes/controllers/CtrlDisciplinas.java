package br.com.alexandrepontes.controllers;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import br.com.alexandrepontes.model.dao.DaoFactory.DaoFactory;
import br.com.alexandrepontes.model.dao.implementacoes.DisciplinaDaoJDBC;
import br.com.alexandrepontes.model.entities.Disciplina;

/**
 * Manipula os eventos associados à tela de manipulação de dados de disciplinas.
 * @author alepq
 *
 */

public class CtrlDisciplinas extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	
	@Wire
	Textbox txtDisciplina;
	@Wire
	Button btnIncluirDisciplina;
	@Wire
	Listbox listboxDisciplina;
	@Wire
	Listheader listId;
	@Wire
	Listheader listDisciplina;
	@Wire
	Textbox txtBuscaDisciplina;
	@Wire
	Button btnBuscaDisciplina;
	
	@Listen("onClick=#btnIncluirDisciplina")
	public void onClickBtnIncluirDisciplina() throws InterruptedException, WrongValueException, SQLException {
		String nomedisc = this.txtDisciplina.getText();
		Disciplina d = new Disciplina();
		d.setnomedisc(nomedisc);
			DaoFactory.criaDisciplinaDao().encontrarPorNome(txtDisciplina.getText());				
					try {
						DaoFactory.criaDisciplinaDao().inserir(d);
						Messagebox.show("Disciplina Inserida com Sucesso!");				
					} catch (SQLException e) {							
						Messagebox.show(e.getMessage());
					}
				}	
	
	@Listen("onClick=#btnLimparCampos")
	public void onClickLimparDados() {
		this.txtDisciplina.setText(" ");
	}
	
	@Listen("onClick = #btnBuscaDisciplina")
	public void onClickbtnBuscaDisciplina() {
		this.listboxDisciplina.getItems().clear();
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		String nomedisc = this.txtBuscaDisciplina.getValue().toString().toLowerCase();
		
		try {
			DisciplinaDaoJDBC d = new DisciplinaDaoJDBC();
			disciplinas = d.encontrarListadeDisciplinasNome(nomedisc);
			for (int i = 0; i < disciplinas.size(); i++) {
				Listitem li = new Listitem();
				Listcell lc01 = new Listcell();
				lc01.setLabel(Integer.toString(disciplinas.get(i).getIddisc()));
				
				Listcell lc02 = new Listcell();
				lc02.setLabel(disciplinas.get(i).getnomedisc());
						
				li.appendChild(lc01);
				li.appendChild(lc02);
				
				this.listboxDisciplina.appendChild(li);			
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
