package br.edu.ftlf.ads.pbd.bean.audit;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.ftlf.ads.pbd.bean.CentroCusto;
import br.edu.ftlf.ads.pbd.bean.Cliente;
import br.edu.ftlf.ads.pbd.bean.Enums.ReceitaSituacao;
import br.edu.ftlf.ads.pbd.bean.Movimento;
import br.edu.ftlf.ads.pbd.bean.Receita;



/**
 * The persistent class for the tbreceitas database table.
 * 
 */
@Entity
@Table(name="TbAudReceitas")
public class ReceitaAudit extends Auditable<Receita> {

	private static final long serialVersionUID = 1L;

	public ReceitaAudit() {
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

	public ReceitaSituacao getSituacao() {
		return getBean().getSituacao();
	}

	public void setSituacao(ReceitaSituacao situacao) {
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

	public void setCentroCusto(CentroCusto tbcentrocusto) {
		getBean().setCentroCusto(tbcentrocusto);
	}
	
	public Movimento getMovimento() {
		return getBean().getMovimento();
	}

	public void setMovimento(Movimento tbmovimento) {
		getBean().setMovimento(tbmovimento);
	}
	
	public Cliente getCliente() {
		return getBean().getCliente();
	}

	public void setCliente(Cliente tbcliente) {
		getBean().setCliente(tbcliente);
	}
	
}