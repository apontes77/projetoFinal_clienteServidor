package br.com.alexandrepontes.model.entities;

import java.io.Serializable;

/**
 * Esta classe é auxiliar a fim de realizar consultas 
 * em diversas tabelas. Tais consultas podem ser inviáveis de fazer devido à 
 * complexidade (e possível inviabilidade) das queries SQL a escrever. 
 * @author alepq
 *
 */

public class BuscaGeral implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private Integer idaluno;
	private Integer matricula;
	private String nomealuno;
	private String disciplina;
	private Integer idturma;
	private String nometurma;
	private Double n1;
	private Double n2;
	
	public BuscaGeral() {
		
	}

	public BuscaGeral(Integer idaluno, Integer matricula, String nomealuno, String nometurma, Double n1, Double n2, String disciplina, Integer idturma) {
		this.idaluno = idaluno;
		this.matricula = matricula;
		this.nomealuno = nomealuno;
		this.nometurma = nometurma;
		this.n1 = n1;
		this.n2 = n2;
		this.disciplina = disciplina;
		this.idturma = idturma;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getNomealuno() {
		return nomealuno;
	}

	public void setNomealuno(String nomealuno) {
		this.nomealuno = nomealuno;
	}

	public String getNometurma() {
		return nometurma;
	}

	public void setNometurma(String nometurma) {
		this.nometurma = nometurma;
	}

	public Double getN1() {
		return n1;
	}

	public void setN1(Double n1) {
		this.n1 = n1;
	}

	public Double getN2() {
		return n2;
	}

	public void setN2(Double n2) {
		this.n2 = n2;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public Integer getIdturma() {
		return idturma;
	}

	public void setIdturma(Integer idturma) {
		this.idturma = idturma;
	}

	public Integer getIdaluno() {
		return idaluno;
	}

	public void setIdaluno(Integer idaluno) {
		this.idaluno = idaluno;
	}
	
	

	
	
	
}
