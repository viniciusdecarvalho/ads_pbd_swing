package br.edu.ftlf.ads.pbd.bean.audit;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.ftlf.ads.pbd.bean.Fornecedor;

@Entity
@Table(name="TbAudFornecedores")
public class FornecedorAudit extends Auditable<Fornecedor> implements Serializable {

	private static final long serialVersionUID = 2101489714469269982L;

	public FornecedorAudit() {
	}
	
	public Boolean getAtivo() {
		return getBean().getAtivo();
	}

	public void setAtivo(Boolean ativo) {
		getBean().setAtivo(ativo);
	}

	public String getCpfCnpj() {
		return getBean().getCpfCnpj();
	}

	public void setCpfCnpj(String cpfCnpj) {
		getBean().setCpfCnpj(cpfCnpj);
	}

	public String getRazaoSocial() {
		return getBean().getRazaoSocial();
	}

	public void setRazaoSocial(String razaoSocial) {
		getBean().setRazaoSocial(razaoSocial);
	}
	
}