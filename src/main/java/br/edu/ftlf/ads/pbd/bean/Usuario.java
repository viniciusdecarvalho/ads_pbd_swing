package br.edu.ftlf.ads.pbd.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import br.edu.ftlf.ads.pbd.bean.audit.Audit;
import br.edu.ftlf.ads.pbd.bean.audit.AuditorListener;
import br.edu.ftlf.ads.pbd.bean.audit.UsuarioAudit;


/**
 * The persistent class for the tbusuarios database table.
 * 
 */
@Entity
@Table(name = "TbUsuarios")
@EntityListeners(AuditorListener.class)
@Audit(UsuarioAudit.class)
public class Usuario extends BeanImpl<Integer>
		implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Boolean ativo;

	private String cpf;

	private String login;

	private String nome;

	private String senha;

	public Usuario() {
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}