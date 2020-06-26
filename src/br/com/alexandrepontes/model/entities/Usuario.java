package br.com.alexandrepontes.model.entities;

import java.io.Serializable;
/**
 * Esta classe representa a entidade Usuario, 
 * útil para armazenar as credenciais de usuários no sistema 
 * e assim autenticá-los.
 * @author alepq
 *
 */
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
		private Integer idusuario;
		private String nomeusuario;
		private String senha;
		private String tipousuario;
		
		public Usuario(){
			
		}

		public Usuario(Integer idusuario, String nomeusuario, String senha, String tipousuario) {
			super();
			this.idusuario = idusuario;
			this.nomeusuario = nomeusuario;
			this.senha = senha;
			this.tipousuario = tipousuario;
		}

		public Integer getIdusuario() {
			return idusuario;
		}

		public void setIdusuario(Integer idusuario) {
			this.idusuario = idusuario;
		}

		public String getnomeusuario() {
			return nomeusuario;
		}

		public void setnomeusuario(String nomeusuario) {
			this.nomeusuario = nomeusuario;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String gettipousuario() {
			return tipousuario;
		}

		public void settipousuario(String tipousuario) {
			this.tipousuario = tipousuario;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((idusuario == null) ? 0 : idusuario.hashCode());
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
			Usuario other = (Usuario) obj;
			if (idusuario == null) {
				if (other.idusuario != null)
					return false;
			} else if (!idusuario.equals(other.idusuario))
				return false;
			return true;
		}
		
		
	
}