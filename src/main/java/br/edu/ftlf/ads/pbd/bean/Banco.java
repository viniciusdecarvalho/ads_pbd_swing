package br.edu.ftlf.ads.pbd.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ftlf.ads.pbd.bean.audit.Audit;
import br.edu.ftlf.ads.pbd.bean.audit.AuditorListener;
import br.edu.ftlf.ads.pbd.bean.audit.BancoAudit;

/**
 * The persistent class for the tbbancos database table.
 * 
 */
@Entity
@Table(name = "TbBancos")
@EntityListeners(AuditorListener.class)
@Audit(BancoAudit.class)
public class Banco extends BeanImpl<Short> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotNull
	private boolean ativo;
	
	@NotEmpty
	@Length(min=3, max=30)
	private String nome;
	
	@OneToMany(mappedBy = "banco")
	private List<Pagamento> pagamentos;

	public Banco() {
	}
	
	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Pagamento> getPagamentos() {
		return this.pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

}