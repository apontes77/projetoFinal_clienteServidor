package br.com.alexandrepontes.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import br.com.alexandrepontes.model.dao.implementacoes.AlunoDaoJDBC;
import br.com.alexandrepontes.model.dao.implementacoes.BuscaGeralDaoJDBC;
import br.com.alexandrepontes.model.dao.implementacoes.DisciplinaDaoJDBC;
import br.com.alexandrepontes.model.dao.implementacoes.RegistroDaoJDBC;
import br.com.alexandrepontes.model.dao.implementacoes.TurmaDaoJDBC;
import br.com.alexandrepontes.model.entities.Aluno;
import br.com.alexandrepontes.model.entities.BuscaGeral;
import br.com.alexandrepontes.model.entities.Disciplina;
import br.com.alexandrepontes.model.entities.Registro;
import br.com.alexandrepontes.model.entities.Turma;

/**
 * Manipula os eventos associados à tela de busca e cadastro de alunos.
 * 
 * @author alepq
 *
 */
public class CtrlInserirAluno extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	// ------------------------------------------ // ---------------
	@Wire
	Tab tabAtualizarAluno;
	@Wire
	Tab tabPesquisaAlunos;
	@Wire
	Combobox tipoDePesquisa;
	@Wire
	Textbox txtBuscaCadastro;
	@Wire
	Button btnBuscaCadastro;
	@Wire
	Listbox listboxAlunos;
	// --------------------------------------------------------------------
	@Wire
	Intbox intId;
	@Wire
	Textbox txtMatricula;
	@Wire
	Textbox txtNomeDeAluno;
	@Wire
	Textbox txtDisciplinaDeAluno;
	@Wire
	Textbox txtTurmaDeAluno;
	@Wire
	Textbox decimalN1Inserir;
	@Wire
	Textbox decimalN2Inserir;
	@Wire
	Button btnIncluirAluno;
	@Wire
	Button btnLimparAluno;
	@Wire
	Button btnAlterarAluno;

	// ------------------------------------------ // ---------------

	@Listen("onClick=#tipoDePesquisa")
	public void onClicktipoPesquisa() throws InterruptedException {

		if (this.tipoDePesquisa.getValue().equals("Nome")) {
			onClickbtnBuscaCadastroNome();
		} else {
			if (this.tipoDePesquisa.getValue().equals("Matrícula"))
			onClickbtnBuscaCadastroMatricula();
		}
	}

	@Listen("onClick = #btnBuscaCadastro")
	public void onClickbtnBuscaCadastroNome() throws InterruptedException {
		this.listboxAlunos.getItems().clear();
		List<BuscaGeral> cadastros = new ArrayList<>();
		BuscaGeralDaoJDBC bg;
		String nome = this.txtBuscaCadastro.getValue();
		if (this.txtBuscaCadastro.getText().isEmpty()
				|| this.txtBuscaCadastro.getText().length() == 0
				|| this.txtBuscaCadastro.getText().equals("")
				|| this.txtBuscaCadastro.getText() == null) {

				bg = new BuscaGeralDaoJDBC();
				cadastros = bg.todosOsCadastros();
			
		} else {
			bg = new BuscaGeralDaoJDBC();
			cadastros = bg.cadastroPorNomeDeAluno(nome);		
		}
		
		if (cadastros != null) {
			for (int i = 0; i < cadastros.size(); i++) {
				Listitem li = new Listitem();

				Listcell lc01 = new Listcell();
				lc01.setLabel(Integer.toString(cadastros.get(i).getIdaluno()));
				Listcell lc02 = new Listcell();
				lc02.setLabel(Integer.toString(cadastros.get(i).getMatricula()));
				Listcell lc03 = new Listcell();
				lc03.setLabel(cadastros.get(i).getNomealuno());
				Listcell lc04 = new Listcell();
				lc04.setLabel(cadastros.get(i).getDisciplina());
				Listcell lc05 = new Listcell();
				lc05.setLabel(cadastros.get(i).getNometurma());
				Listcell lc06 = new Listcell();
				lc06.setLabel(Double.toString(cadastros.get(i).getN1()));
				Listcell lc07 = new Listcell();
				lc07.setLabel(Double.toString(cadastros.get(i).getN2()));
				li.appendChild(lc01);
				li.appendChild(lc02);
				li.appendChild(lc03);
				li.appendChild(lc04);
				li.appendChild(lc05);
				li.appendChild(lc06);
				li.appendChild(lc07);

				this.listboxAlunos.appendChild(li);
			}
		}else {
			Utilidades.mensagem("Erro no carregamento dos dados!");
		}
	}

	@Listen("onClick = #btnBuscaCadastro")
	public void onClickbtnBuscaCadastroMatricula() throws InterruptedException {
		this.listboxAlunos.getItems().clear();
		String matricula = this.txtBuscaCadastro.getText();
		Integer mat = Integer.valueOf(matricula);
		BuscaGeralDaoJDBC bg; 
		List<BuscaGeral> cadastros = new ArrayList<>();
		
			if (this.txtBuscaCadastro.getText().isEmpty()
					|| this.txtBuscaCadastro.getText().length() == 0
					|| this.txtBuscaCadastro.getText().equals("")
					|| this.txtBuscaCadastro.getText() == null) {

					bg = new BuscaGeralDaoJDBC();
					cadastros = bg.todosOsCadastros();
				
			} else {
				bg = new BuscaGeralDaoJDBC();
				cadastros = bg.cadastroPorMatriculaDeAluno(mat);		
			}
			
			if (cadastros != null) {
				for (int i = 0; i < cadastros.size(); i++) {
					Listitem li = new Listitem();
					
					Listcell lc01 = new Listcell();
					lc01.setLabel(Integer.toString(cadastros.get(i).getIdaluno()));
					Listcell lc02 = new Listcell();
					lc02.setLabel(Integer.toString(cadastros.get(i).getMatricula()));
					Listcell lc03 = new Listcell();
					lc03.setLabel(cadastros.get(i).getNomealuno());
					Listcell lc04 = new Listcell();
					lc04.setLabel(cadastros.get(i).getDisciplina());
					Listcell lc05 = new Listcell();
					lc05.setLabel(cadastros.get(i).getNometurma());
					Listcell lc06 = new Listcell();
					lc06.setLabel(Double.toString(cadastros.get(i).getN1()));
					Listcell lc07 = new Listcell();
					lc07.setLabel(Double.toString(cadastros.get(i).getN2()));
					li.appendChild(lc01);
					li.appendChild(lc02);
					li.appendChild(lc03);
					li.appendChild(lc04);
					li.appendChild(lc05);
					li.appendChild(lc06);
					li.appendChild(lc07);

					this.listboxAlunos.appendChild(li);
					
				}
				
			}else {
				Utilidades.mensagem("Erro no carregamento dos dados!");
			}
			
		}
	

	@Listen("onClick=#btnAlterarAluno")
	public void onClickbtnAlterarAluno() throws InterruptedException {
		Integer id;
		Integer matricula;
		String nomealuno;
		String nomedisc;
		String nometurma;
		double n1;
		double n2;

		Aluno aluno = new Aluno();
		Disciplina disciplina = new Disciplina();
		Turma turma = new Turma();
		Registro registro = new Registro();
		
		AlunoDaoJDBC a = new AlunoDaoJDBC();
		DisciplinaDaoJDBC d = new DisciplinaDaoJDBC();
		TurmaDaoJDBC t = new TurmaDaoJDBC();
		RegistroDaoJDBC r = new RegistroDaoJDBC();
		
		id = Integer.valueOf(this.intId.getText());
		matricula = Integer.parseInt(this.txtMatricula.getText());
		nomealuno = this.txtNomeDeAluno.getText();
		nomedisc = this.txtDisciplinaDeAluno.getText();
		nometurma = this.txtTurmaDeAluno.getText();
		n1 = Double.valueOf(this.decimalN1Inserir.getText());
		n2 = Double.valueOf(this.decimalN2Inserir.getText());
		try {
			aluno.setnomealuno(nomealuno);
			aluno.setMatricula(matricula);
			aluno.setIdaluno(id);
			a.atualizar(aluno);
			
			
			disciplina.setnomedisc(nomedisc);
			d.atualizar(disciplina);
			
			
			turma.setnometurma(nometurma);
			turma.setfk_disciplina_iddisc(disciplina.getIddisc());
			t.atualizar(turma); 
			
			
			registro.setN1(n1);
			registro.setN2(n2);
			registro.setFk_aluno_idaluno(id);
			registro.setFk_turma_idturma((t.encontrarPorId(turma)));
			
			r.atualizar(registro);
			Utilidades.mensagem("Dados atualizados com sucesso!");
			
		}catch(SQLException e) {
			Utilidades.mensagem("Dados atualizados com sucesso!");
			e.printStackTrace();
		}
		
	}

	@Listen("onClick=#btnIncluirAluno")
	public void onClickbtnIncluirAluno() throws InterruptedException, SQLException {
	
		Integer matricula;
		String nomealuno;
		String nomedisc;
		String nometurma;
		double n1;
		double n2;
		Aluno novoaluno = new Aluno();
		Turma novaturma = new Turma();
		Aluno aluno = new Aluno();
		Disciplina disciplina = new Disciplina();
		Turma turma = new Turma();
		Registro registro = new Registro();
		
		AlunoDaoJDBC a = new AlunoDaoJDBC();
		DisciplinaDaoJDBC d = new DisciplinaDaoJDBC();
		TurmaDaoJDBC t = new TurmaDaoJDBC();
		RegistroDaoJDBC r = new RegistroDaoJDBC();
		
		
		matricula = Integer.valueOf(this.txtMatricula.getValue());
		nomealuno = this.txtNomeDeAluno.getValue();
		nomedisc = this.txtDisciplinaDeAluno.getValue();
		nometurma = this.txtTurmaDeAluno.getValue();
		n1 = Double.valueOf(this.decimalN1Inserir.getText());
		n2 = Double.valueOf(this.decimalN2Inserir.getText());
		try {
			aluno.setnomealuno(nomealuno);
			aluno.setMatricula(matricula);
			a.inserir(aluno);
			int y = a.encontrarId(aluno);
			aluno.setIdaluno(y);
			novoaluno = a.inserir(aluno);
			
			disciplina.setnomedisc(nomedisc);
			d.inserir(disciplina);
			
			turma.setnometurma(nometurma);
			turma.setfk_disciplina_iddisc(disciplina.getIddisc());
			int x = t.encontrarPorId(turma);
			turma.setIdturma(x);
			novaturma = t.inserir(turma);
			
			registro.setN1(n1);
			registro.setN2(n2);
			registro.setFk_aluno_idaluno(novoaluno.getIdaluno());
			registro.setFk_turma_idturma(novaturma.getIdturma());
			
			Registro reg = r.inserir(registro);
			if(reg!=null) {
				Utilidades.mensagem("Dados inseridos com sucesso!");
			}	
		}catch(Exception e) {
			e.printStackTrace();
			Utilidades.mensagem("Dados incorretos para inserção. Tente Novamente.");
		}	
	}

	@Listen("onSelect=#listboxAlunos")
	public void obtemCadastroSelecionado() {
		int indice = this.listboxAlunos.getSelectedIndex();
		BuscaGeralDaoJDBC bgDao = new BuscaGeralDaoJDBC();
		BuscaGeral bg = new BuscaGeral();
		String nometurma = null;
		String nomedisc = null;
		Integer matricula = 0;
		if (indice >= 0) {

			Listcell lc01 = new Listcell();
			Listcell lc02 = new Listcell();
			Listcell lc03 = new Listcell();
					
			lc01 = (Listcell) this.listboxAlunos.getSelectedItem().getChildren().get(1);
			lc02 = (Listcell) this.listboxAlunos.getSelectedItem().getChildren().get(3);
			lc03 = (Listcell) this.listboxAlunos.getSelectedItem().getChildren().get(4);
			
			matricula = Integer.parseInt(lc01.getLabel().toString());
			nomedisc = lc02.getLabel().toString();
			nometurma = lc03.getLabel().toString();
			
			bg = bgDao.obtemAlunoSelecionado(nomedisc, nometurma, matricula);

			if (bg != null) {
				this.intId.setText(Integer.toString(bg.getIdaluno()));
				this.txtMatricula.setText(Integer.toString(bg.getMatricula()));
				this.txtNomeDeAluno.setText(bg.getNomealuno());
				this.txtDisciplinaDeAluno.setText(bg.getDisciplina());
				this.txtTurmaDeAluno.setText(bg.getNometurma());
				this.decimalN1Inserir.setText(Double.toString(bg.getN1()));
				this.decimalN2Inserir.setText(Double.toString(bg.getN2()));
				this.listboxAlunos.getItems().clear();
				this.txtBuscaCadastro.setText("");
				this.tabAtualizarAluno.setSelected(true);
			}
		}
	}

	@Listen("onClick=#btnLimparAluno")
	public void onClickbtnLimparAluno() {
		this.intId.setText(" ");
		this.txtTurmaDeAluno.setText(" ");
		this.txtNomeDeAluno.setText(" ");
		this.txtMatricula.setText(" ");
		this.txtDisciplinaDeAluno.setText(" ");
		this.decimalN1Inserir.setText(Double.toString(0));
		this.decimalN2Inserir.setText(Double.toString(0));

	}

}
