package br.edu.ftlf.ads.pbd.bean.audit;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.ftlf.ads.pbd.bean.CentroCusto;
import br.edu.ftlf.ads.pbd.bean.Despesa;
import br.edu.ftlf.ads.pbd.bean.Enums.DespesaSituacao;
import br.edu.ftlf.ads.pbd.bean.Fornecedor;
import br.edu.ftlf.ads.pbd.bean.Movimento;

@Entity
@Table(name="TbAudDespesas")
public class DespesaAudit extends Auditable<Despesa> {

	private static final long serialVersionUID = 1L;

	public DespesaAudit() {
	}
	
	public Integer getId() {
		return getBean().getId();
	}
	
	public void setId(Integer id) {
		getBean().setId(id);
	}
	
	public Date getData() {
		return getBean().getData();
	}

	public void setData(Date data) {
		getBean().setData(data);
	}

	public DespesaSituacao getSituacao() {
		return getBean().getSituacao();
	}

	public void setSituacao(DespesaSituacao situacao) {
		getBean().setSituacao(situacao);
	}

	public BigDecimal getValor() {
		return getBean().getValor();
	}

	public void setValor(BigDecimal valor) {
		getBean().setValor(valor);
	}

	public CentroCusto getCentroCusto() {
		return getBean().getCentroCusto();
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		getBean().setCentroCusto(centroCusto);
	}
	
	public Fornecedor getFornecedor() {
		return getBean().getFornecedor();
	}

	public void setFornecedor(Fornecedor tbfornecedore) {
		getBean().setFornecedor(tbfornecedore);
	}
	
	public Movimento getMovimento() {
		return getBean().getMovimento();
	}

	public void setMovimento(Movimento tbmovimento) {
		getBean().setMovimento(tbmovimento);
	}
	
}