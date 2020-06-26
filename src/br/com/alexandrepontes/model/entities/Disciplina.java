package br.com.alexandrepontes.model.entities;

import java.io.Serializable;

/**
 * Esta classe representa a entidade Disciplina, presente na
 * base de dados utilizada.
 * @author alepq
 *
 */

public class Disciplina implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer iddisc;
	private String nomedisc;
	
	public Disciplina() {
	}
	
	public Disciplina(Integer iddisc, String nomedisc) {
		this.iddisc = iddisc;
		this.nomedisc = nomedisc;
	}

	public Integer getIddisc() {
		return iddisc;
	}

	public void setIddisc(Integer iddisc) {
		this.iddisc = iddisc;
	}

	public String getnomedisc() {
		return nomedisc;
	}

	public void setnomedisc(String nomedisc) {
		this.nomedisc = nomedisc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iddisc == null) ? 0 : iddisc.hashCode());
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
		Disciplina other = (Disciplina) obj;
		if (iddisc == null) {
			if (other.iddisc != null)
				return false;
		} else if (!iddisc.equals(other.iddisc))
			return false;
		return true;
	}

	
	
	
	
	
	
}