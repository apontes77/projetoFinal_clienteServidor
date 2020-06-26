package br.com.alexandrepontes.model.entities;

import java.io.Serializable;
/**
 * Esta classe representa a entidade Registro, que é utilizada para
 * armazenar corretamente as notas dos alunos em turmas distintas,
 * além de buscar atender a 2ª forma normal em termos de normalização
 * do modelo de base de dados seguido.
 * @author alepq
 *
 */
public class Registro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer idregistro;
	private double n1;
	private double n2;
	private Integer fk_aluno_idaluno;
	private Integer fk_turma_idturma;
	
	
	public Registro() {
		
	}


	public Registro(Integer idregistro, double n1, double n2, Integer fk_aluno_idaluno, Integer fk_turma_idturma) {
		super();
		this.idregistro = idregistro;
		this.n1 = n1;
		this.n2 = n2;
		this.fk_aluno_idaluno = fk_aluno_idaluno;
		this.fk_turma_idturma = fk_turma_idturma;
	}


	public Integer getIdregistro() {
		return idregistro;
	}


	public void setIdregistro(Integer idregistro) {
		this.idregistro = idregistro;
	}


	public double getN1() {
		return n1;
	}


	public void setN1(double n1) {
		this.n1 = n1;
	}


	public double getN2() {
		return n2;
	}


	public void setN2(double n2) {
		this.n2 = n2;
	}


	public Integer getFk_aluno_idaluno() {
		return fk_aluno_idaluno;
	}


	public void setFk_aluno_idaluno(Integer fk_aluno_idaluno) {
		this.fk_aluno_idaluno = fk_aluno_idaluno;
	}


	public Integer getFk_turma_idturma() {
		return fk_turma_idturma;
	}


	public void setFk_turma_idturma(Integer fk_turma_idturma) {
		this.fk_turma_idturma = fk_turma_idturma;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idregistro == null) ? 0 : idregistro.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registro other = (Registro) obj;
		if (idregistro == null) {
			if (other.idregistro != null)
				return false;
		} else if (!idregistro.equals(other.idregistro))
			return false;
		return true;
	}
	
	
	
	
	
	
}