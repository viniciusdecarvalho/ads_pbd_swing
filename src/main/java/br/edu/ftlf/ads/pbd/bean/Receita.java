package br.edu.ftlf.ads.pbd.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ftlf.ads.pbd.bean.Enums.ReceitaSituacao;
import br.edu.ftlf.ads.pbd.bean.audit.Audit;
import br.edu.ftlf.ads.pbd.bean.audit.AuditorListener;
import br.edu.ftlf.ads.pbd.bean.audit.ReceitaAudit;

/**
 * The persistent class for the tbreceitas database table.
 * 
 */
@Entity
@Table(name="tbreceitas")
@EntityListeners(AuditorListener.class)
@Audit(ReceitaAudit.class)
public class Receita extends BeanImpl<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

    @Temporal( TemporalType.DATE)
	private Date data;

	private ReceitaSituacao situacao;

	private BigDecimal valor;

	//bi-directional many-to-one association to CentroCusto
    @ManyToOne
	@JoinColumn(name="centroCustoId")
	private CentroCusto centroCusto;

	//bi-directional many-to-one association to Movimento
    @ManyToOne
	@JoinColumn(name="movimentoId")
	private Movimento movimento;

	//bi-directional many-to-one association to Cliente
    @ManyToOne
	@JoinColumn(name="clienteId")
	private Cliente cliente;

    public Receita() {
    }

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public ReceitaSituacao getSituacao() {
		return this.situacao;
	}

	public void setSituacao(ReceitaSituacao situacao) {
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

	public void setCentroCusto(CentroCusto tbcentrocusto) {
		this.centroCusto = tbcentrocusto;
	}
	
	public Movimento getMovimento() {
		return this.movimento;
	}

	public void setMovimento(Movimento tbmovimento) {
		this.movimento = tbmovimento;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente tbcliente) {
		this.cliente = tbcliente;
	}
	
}