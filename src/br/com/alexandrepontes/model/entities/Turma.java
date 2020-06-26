package br.com.alexandrepontes.model.entities;

import java.io.Serializable;
/**
 * Esta classe representa a entidade Turma.
 * @author alepq
 *
 */
public class Turma implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idturma;
	private Integer fk_disciplina_iddisc;
	private String nometurma;
	
	public Turma() {
		
	}

	public Turma(Integer idturma, Integer fk_disciplina_iddisc, String nometurma) {
		
		this.idturma = idturma;
		this.fk_disciplina_iddisc = fk_disciplina_iddisc;
		this.nometurma = nometurma;
	}

	public Integer getIdturma() {
		return idturma;
	}

	public void setIdturma(Integer idturma) {
		this.idturma = idturma;
	}

	public Integer getfk_disciplina_iddisc() {
		return fk_disciplina_iddisc;
	}

	public void setfk_disciplina_iddisc(Integer fk_disciplina_iddisc) {
		this.fk_disciplina_iddisc = fk_disciplina_iddisc;
	}

	public String getnometurma() {
		return nometurma;
	}

	public void setnometurma(String nometurma) {
		this.nometurma = nometurma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idturma == null) ? 0 : idturma.hashCode());
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
		Turma other = (Turma) obj;
		if (idturma == null) {
			if (other.idturma != null)
				return false;
		} else if (!idturma.equals(other.idturma))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
}