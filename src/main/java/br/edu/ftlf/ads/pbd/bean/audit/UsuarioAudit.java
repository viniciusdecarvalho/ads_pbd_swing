package br.edu.ftlf.ads.pbd.bean.audit;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.ftlf.ads.pbd.bean.Usuario;


/**
 * The persistent class for the tbusuarios database table.
 * 
 */
@Entity
@Table(name = "TbAudUsuarios")
public class UsuarioAudit extends Auditable<Usuario> {

	private static final long serialVersionUID = 1L;

	public UsuarioAudit() {
	}
	
	public Integer getId() {
		return getBean().getId();
	}
	
	public void setId(Integer id) {
		getBean().setId(id);
	}
	
	public Boolean getAtivo() {
		return getBean().getAtivo();
	}

	public void setAtivo(Boolean ativo) {
		getBean().setAtivo(ativo);
	}

	public String getCpf() {
		return getBean().getCpf();
	}

	public void setCpf(String cpf) {
		getBean().setCpf(cpf);
	}

	public String getLogin() {
		return getBean().getLogin();
	}

	public void setLogin(String login) {
		getBean().setLogin(login);
	}

	public String getNome() {
		return getBean().getNome();
	}

	public void setNome(String nome) {
		getBean().setNome(nome);
	}

	public String getSenha() {
		return getBean().getSenha();
	}

	public void setSenha(String senha) {
		getBean().setSenha(senha);
	}

}