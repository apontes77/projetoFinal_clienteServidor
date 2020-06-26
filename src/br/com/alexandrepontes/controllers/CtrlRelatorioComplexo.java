package br.com.alexandrepontes.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;

import br.com.alexandrepontes.model.dao.DaoFactory.DaoFactory;
import br.com.alexandrepontes.model.entities.Disciplina;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Executa a escolha de parâmetros para geração de relatório complexo.
 * @author alepq
 *
 */

public class CtrlRelatorioComplexo extends SelectorComposer<Component>{

	
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


	@Listen("onClick=#buscarTurmas")
	public  void preencheComboBoxTurmas() throws 
	SQLException, InterruptedException, IOException, ClassNotFoundException{
			this.buscarTurmas.getItems().clear();
			Integer ind = buscarDisciplinas.getSelectedItem().getIndex();
			Comboitem cbi = new Comboitem();			
			cbi.setLabel(DaoFactory.criaTurmaDao().encontrarPorId(ind+1).getnometurma());
			cbi.setValue(DaoFactory.criaTurmaDao().encontrarPorId(ind+1).getIdturma());
			this.buscarTurmas.appendChild(cbi);
		}
	
	
	public void geraRelatorio() {
		String disc = this.buscarDisciplinas.getText();
		String turma = this.buscarTurmas.getText();
		
		JRDataSource datasource = new JREmptyDataSource();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Disciplina", disc);
		parametros.put("Turma", turma);
		
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport("C:/Users/alepq/eclipse-workspace/final_project/relatorios/Relatorio_Complexo.jasper"
					, parametros, datasource);
			
			JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/Users/alepq/eclipse-workspace/final_project/relatorios/");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}