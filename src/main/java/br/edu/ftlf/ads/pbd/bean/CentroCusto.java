package br.edu.ftlf.ads.pbd.bean;

import static javax.persistence.FetchType.LAZY;

import java.util.EnumSet;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ftlf.ads.pbd.bean.Enums.CentroCustoTipo;
import br.edu.ftlf.ads.pbd.bean.audit.Audit;
import br.edu.ftlf.ads.pbd.bean.audit.AuditorListener;
import br.edu.ftlf.ads.pbd.bean.audit.CentroCustoAudit;
import br.edu.ftlf.ads.pbd.bean.validator.NotEmptyEnumSet;

/**
 * The persistent class for the tbcentrocustos database table.
 * 
 */
@Entity
@Table(name = "TbCentroCustos")
@EntityListeners(AuditorListener.class)
@Audit(CentroCustoAudit.class)
public class CentroCusto extends BeanImpl<Short> {
	private static final long serialVersionUID = 1L;	
	
	@NotNull
	private boolean ativo;
	
	@NotEmpty
	@Length(min=3, max=30)
	private String nome;

	@NotEmptyEnumSet
	@Type(type = "br.edu.ftlf.ads.pbd.bean.usertype.CentroCustoTipoUserType")
	private EnumSet<CentroCustoTipo> tipo = EnumSet.noneOf(CentroCustoTipo.class);

	@OneToMany(mappedBy = "centroCusto", fetch = LAZY)
	private List<Despesa> despesas;

	@OneToMany(mappedBy = "centroCusto", fetch = LAZY)
	private List<Movimento> movimentos;

	@OneToMany(mappedBy = "centroCusto", fetch = LAZY)
	private List<Receita> receitas;

	public CentroCusto() {
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		firePropertyChange("ativo", this.ativo, this.ativo = ativo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		firePropertyChange("nome", this.nome, this.nome = nome);
	}

	public EnumSet<CentroCustoTipo> getTipo() {
		return tipo;
	}

	public void setTipo(EnumSet<CentroCustoTipo> tipo) {
		firePropertyChange("tipos", this.tipo, this.tipo = tipo);
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}

	public List<Movimento> getMovimentos() {
		return movimentos;
	}

	public void setMovimentos(List<Movimento> movimentos) {
		this.movimentos = movimentos;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

}
