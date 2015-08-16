package br.edu.ftlf.ads.pbd.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ftlf.ads.pbd.bean.Enums.DespesaSituacao;
import br.edu.ftlf.ads.pbd.bean.audit.Audit;
import br.edu.ftlf.ads.pbd.bean.audit.AuditorListener;
import br.edu.ftlf.ads.pbd.bean.audit.DespesaAudit;



/**
 * The persistent class for the tbdespesas database table.
 * 
 */
@Entity
@Table(name="TbAudDespesas")
@EntityListeners(AuditorListener.class)
@Audit(DespesaAudit.class)
public class Despesa extends BeanImpl<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

    @Temporal( TemporalType.DATE)
	private Date data;

    @Enumerated(EnumType.STRING)
	private DespesaSituacao situacao;

	private BigDecimal valor;

	//bi-directional many-to-one association to CentroCusto
    @ManyToOne
	@JoinColumn(name="centroCustoId")
	private CentroCusto centroCusto;

	//bi-directional many-to-one association to Fornecedor
    @ManyToOne
	@JoinColumn(name="fornecedorId")
	private Fornecedor fornecedor;

	//bi-directional many-to-one association to Movimento
    @ManyToOne
	@JoinColumn(name="movimentoId")
	private Movimento movimento;

    public Despesa() {
    }

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public DespesaSituacao getSituacao() {
		return this.situacao;
	}

	public void setSituacao(DespesaSituacao situacao) {
		this.situacao = situacao;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public CentroCusto getCentroCusto() {
		return this.centroCusto;
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}
	
	public Fornecedor getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(Fornecedor tbfornecedore) {
		this.fornecedor = tbfornecedore;
	}
	
	public Movimento getMovimento() {
		return this.movimento;
	}

	public void setMovimento(Movimento tbmovimento) {
		this.movimento = tbmovimento;
	}
	
}