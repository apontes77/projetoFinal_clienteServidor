package br.com.alexandrepontes.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;

import br.com.alexandrepontes.model.dao.DaoFactory.DaoFactory;
import br.com.alexandrepontes.model.entities.Disciplina;

/**
 * Manipula o evento de geração de relatório simples.
 * @author alepq
 *
 */
public class CtrlRelatorioSimples extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	@Wire
	Combobox buscarDisciplinas;
	@Wire
	Combobox buscarTurmas;
	

	@Listen("onClick=#buscarDisciplinas")
	public void preencheComboBoxDisciplinas()
			throws SQLException, InterruptedException, IOException, ClassNotFoundException {
		this.buscarDisciplinas.getItems().clear();
		List<Disciplina> disciplinas = new ArrayList<>();

		disciplinas = DaoFactory.criaDisciplinaDao().obterDisciplinas();

		for (int i = 0; i < disciplinas.size(); i++) {
			Comboitem cbi = new Comboitem();
			cbi.setLabel(disciplinas.get(i).getnomedisc());
			cbi.setValue(disciplinas.get(i).getIddisc());
			this.buscarDisciplinas.appendChild(cbi);
		}
	}

	@Listen("onClick = #buscarTurmas")
	public void preencheComboBoxTurmas()
			throws SQLException, InterruptedException, IOException, ClassNotFoundException {
		this.buscarTurmas.getItems().clear();
		Integer ind = buscarDisciplinas.getSelectedItem().getIndex();
		Comboitem cbi = new Comboitem();
		cbi.setLabel(DaoFactory.criaTurmaDao().encontrarPorId(ind + 1).getnometurma());
		cbi.setValue(DaoFactory.criaTurmaDao().encontrarPorId(ind + 1).getIdturma());
		this.buscarTurmas.appendChild(cbi);
	}
}