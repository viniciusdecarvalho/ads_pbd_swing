package br.edu.ftlf.ads.pbd.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.ftlf.ads.pbd.bean.audit.Audit;
import br.edu.ftlf.ads.pbd.bean.audit.AuditorListener;
import br.edu.ftlf.ads.pbd.bean.audit.PagamentoAudit;



/**
 * The persistent class for the tbpagamentos database table.
 * 
 */
@Entity
@Table(name="TbPagamentos")
@EntityListeners(AuditorListener.class)
@Audit(PagamentoAudit.class)
public class Pagamento extends BeanImpl<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String numDocumento;

	private BigDecimal valor;

    @ManyToOne
	@JoinColumn(name="bancoId")
	private Banco banco;

    @ManyToOne
	@JoinColumn(name="formaPagamentoId")
	private FormasPagamento formasPagamento;

    @ManyToOne
	@JoinColumn(name="movimentoId")
	private Movimento movimento;

    @ManyToOne
	@JoinColumn(name="usuarioId")
	private Usuario usuario;

    public Pagamento() {
    }

	public String getNumDocumento() {
		return this.numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco tbbanco) {
		this.banco = tbbanco;
	}
	
	public FormasPagamento getFormasPagamento() {
		return this.formasPagamento;
	}

	public void setFormasPagamento(FormasPagamento formasPagamento) {
		this.formasPagamento = formasPagamento;
	}
	
	public Movimento getMovimento() {
		return this.movimento;
	}

	public void setMovimento(Movimento tbmovimento) {
		this.movimento = tbmovimento;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario tbusuario) {
		this.usuario = tbusuario;
	}
	
}