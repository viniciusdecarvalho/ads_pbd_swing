package br.edu.ftlf.ads.pbd.bean.audit;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.ftlf.ads.pbd.bean.Banco;
import br.edu.ftlf.ads.pbd.bean.FormasPagamento;
import br.edu.ftlf.ads.pbd.bean.Movimento;
import br.edu.ftlf.ads.pbd.bean.Pagamento;
import br.edu.ftlf.ads.pbd.bean.Usuario;

@Entity
@Table(name="TbAudPagamentos")
public class PagamentoAudit extends Auditable<Pagamento> {

	private static final long serialVersionUID = 1L;

	public PagamentoAudit() {
	}
	
	public Integer getId() {
		return getBean().getId();
	}
	
	public void setId(Integer id) {
		getBean().setId(id);
	}
	
	public String getNumDocumento() {
		return getBean().getNumDocumento();
	}

	public void setNumDocumento(String numDocumento) {
		getBean().setNumDocumento(numDocumento);
	}

	public BigDecimal getValor() {
		return getBean().getValor();
	}

	public void setValor(BigDecimal valor) {
		getBean().setValor(valor);
	}

	public Banco getBanco() {
		return getBean().getBanco();
	}

	public void setBanco(Banco tbbanco) {
		getBean().setBanco(tbbanco);
	}
	
	public FormasPagamento getFormasPagamento() {
		return getBean().getFormasPagamento();
	}

	public void setFormasPagamento(FormasPagamento formasPagamento) {
		getBean().setFormasPagamento(formasPagamento);
	}
	
	public Movimento getMovimento() {
		return getBean().getMovimento();
	}

	public void setMovimento(Movimento tbmovimento) {
		getBean().setMovimento(tbmovimento);
	}
	
	public Usuario getUsuario() {
		return getBean().getUsuario();
	}

	public void setUsuario(Usuario tbusuario) {
		getBean().setUsuario(tbusuario);
	}
	
}