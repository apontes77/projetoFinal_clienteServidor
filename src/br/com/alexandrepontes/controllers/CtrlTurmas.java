package br.com.alexandrepontes.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import br.com.alexandrepontes.model.dao.DaoFactory.DaoFactory;
import br.com.alexandrepontes.model.dao.implementacoes.BuscaGeralDaoJDBC;
import br.com.alexandrepontes.model.dao.implementacoes.DisciplinaDaoJDBC;
import br.com.alexandrepontes.model.entities.BuscaGeral;
import br.com.alexandrepontes.model.entities.Disciplina;
import br.com.alexandrepontes.model.entities.Turma;

/**
 * Manipula os eventos associados a tela de busca e cadastro de turmas no sistema.
 * @author alepq
 *
 */

public class CtrlTurmas extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	@Wire
	Combobox buscarDisciplinas;
	@Wire
	Textbox txtTurma;
	@Wire
	Button btnIncluirTurma;
	@Wire
	Button btnExcluirTurma;
	@Wire
	Listbox listboxTurmas;
	@Wire
	Textbox txtBuscaTurma;
	@Wire
	Button btnBuscaTurma;

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

	@Listen("onClick=#btnIncluirTurma")
	public void onClickBtnIncluirTurma() throws InterruptedException {
		Turma turma = new Turma();
		String disciplinaSelecionada = this.buscarDisciplinas.getText();
		DisciplinaDaoJDBC dc = new DisciplinaDaoJDBC();
		Integer idDisciplina = dc.encontrarDisciplinaPorNome(disciplinaSelecionada);

		turma.setnometurma(txtTurma.getText().toString());
		turma.setfk_disciplina_iddisc(idDisciplina);

		try {
			DaoFactory.criaTurmaDao().inserir(turma);
			Messagebox.show("Turma Inserida com Sucesso!");
		} catch (SQLException e) {
			Utilidades.mensagem(e.getMessage());
		}
	}

	@Listen("onClick=#btnlimparCampos")
	public void onClickBtnExcluirTurma() {

		this.txtTurma.setText(" ");
	}

	@Listen("onClick = #btnBuscaTurma")
	public void onClickbtnBuscaTurma() {
		this.listboxTurmas.getItems().clear();
		String nometurma = this.txtBuscaTurma.getValue().toString().toLowerCase();
		List<BuscaGeral> turmascomdisciplinas = new ArrayList<>();

		try {
			BuscaGeralDaoJDBC bg = new BuscaGeralDaoJDBC();
			turmascomdisciplinas = bg.obterTurmasComDisciplinas(nometurma);
			for (int i = 0; i < turmascomdisciplinas.size(); i++) {
				Listitem li = new Listitem();
				Listcell lc01 = new Listcell();
				lc01.setLabel(turmascomdisciplinas.get(i).getDisciplina());
				
				Listcell lc02 = new Listcell();
				lc02.setLabel(turmascomdisciplinas.get(i).getNometurma());

				li.appendChild(lc01);
				li.appendChild(lc02);
				
				this.listboxTurmas.appendChild(li);
			}
		}

		catch (Exception e) {
			Messagebox.show(e.getMessage());
		}

	}
}
