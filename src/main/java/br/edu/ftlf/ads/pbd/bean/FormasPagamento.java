package br.edu.ftlf.ads.pbd.bean;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ftlf.ads.pbd.bean.Enums.FormasPagamentoTipo;
import br.edu.ftlf.ads.pbd.bean.audit.Audit;
import br.edu.ftlf.ads.pbd.bean.audit.AuditorListener;
import br.edu.ftlf.ads.pbd.bean.audit.FormasPagamentoAudit;
import br.edu.ftlf.ads.pbd.bean.validator.NotEmptyEnumSet;


/**
 * The persistent class for the tbformaspagamentos database table.
 * 
 */
@Entity
@Table(name = "TbFormasPagamentos")
@EntityListeners(AuditorListener.class)
@Audit(FormasPagamentoAudit.class)
public class FormasPagamento extends BeanImpl<Short> implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean ativo;

	@NotEmpty
	@Length(min=3, max=30)
	private String nome;

	@NotEmptyEnumSet
	@Type(type = "br.edu.ftlf.ads.pbd.bean.usertype.FormasPagamentoTipoUserType")
	private EnumSet<FormasPagamentoTipo> tipo = EnumSet.noneOf(FormasPagamentoTipo.class);

	@OneToMany(mappedBy = "formasPagamento")
	private List<Pagamento> pagamentos;

	public FormasPagamento() {
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

	public EnumSet<FormasPagamentoTipo> getTipo() {
		return this.tipo;
	}

	public void setTipo(EnumSet<FormasPagamentoTipo> tipo) {
		this.tipo = tipo;
	}

	public List<Pagamento> getPagamentos() {
		return this.pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

}
