package br.edu.ftlf.ads.pbd.bean.audit;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.ftlf.ads.pbd.bean.Banco;

@Entity
@Table(name="TbAudBancos")
public class BancoAudit extends Auditable<Banco> {

	private static final long serialVersionUID = 1L;

	public BancoAudit() {
	}
	
	public Short getId() {
		return getBean().getId();
	}
	
	public void setId(Short id) {
		getBean().setId(id);
	}
	
	public Boolean getAtivo() {
		return getBean().getAtivo();
	}

	public void setAtivo(Boolean ativo) {
		getBean().setAtivo(ativo);
	}

	public String getNome() {
		return getBean().getNome();
	}

	public void setNome(String nome) {
		getBean().setNome(nome);
	}

}
