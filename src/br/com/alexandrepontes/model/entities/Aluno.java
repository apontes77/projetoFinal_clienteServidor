package br.com.alexandrepontes.model.entities;


import java.io.Serializable;
/**
 * A entidade Aluno, que consta no banco de dados, é representada
 * pela classe Aluno, aqui implementada.
 * @author alepq
 *
 */
public class Aluno implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomealuno;
	private Integer idaluno;
	private Integer matricula;

	public Aluno() {

	}

	public Aluno(String nomealuno, Integer idaluno) {

		this.nomealuno = nomealuno;
		this.idaluno = idaluno;
	}

	public String getnomealuno() {
		return nomealuno;
	}

	public void setnomealuno(String nomealuno) {
		this.nomealuno = nomealuno;
	}

	public Integer getIdaluno() {
		return idaluno;
	}

	public void setIdaluno(Integer idaluno) {
		this.idaluno = idaluno;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	@Override
	public int hashCode() {
		final Integer prime = 31;
		Integer result = 1;
		result = prime * result + idaluno;
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
		Aluno other = (Aluno) obj;
		if (idaluno != other.idaluno)
			return false;
		return true;
	}

}
