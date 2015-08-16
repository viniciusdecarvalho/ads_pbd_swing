package br.edu.ftlf.ads.pbd.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.edu.ftlf.ads.pbd.bean.Enums.FornecedorTipo;
import br.edu.ftlf.ads.pbd.bean.audit.Audit;
import br.edu.ftlf.ads.pbd.bean.audit.AuditorListener;
import br.edu.ftlf.ads.pbd.bean.audit.FornecedorAudit;

/**
 * The persistent class for the tbfornecedores database table.
 * 
 */
@Entity
@Table(name="TbFornecedores")
@EntityListeners(AuditorListener.class)
@Audit(FornecedorAudit.class)
public class Fornecedor extends BeanImpl<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean ativo;

	private String cpfCnpj;

	private String razaoSocial;

	@OneToMany(mappedBy="fornecedor")
	private List<Despesa> despesas;

    public Fornecedor() {
    }

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getCpfCnpj() {
		return this.cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	@Transient
	public FornecedorTipo getTipo() {
		return cpfCnpj == null ? FornecedorTipo.FISICA :
				(cpfCnpj.length() > 11 ? FornecedorTipo.JURIDICA : FornecedorTipo.FISICA);
	}

	public List<Despesa> getDespesas() {
		return this.despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}
	
}