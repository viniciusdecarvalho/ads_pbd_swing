package br.edu.ftlf.ads.pbd.bean.audit;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.ftlf.ads.pbd.bean.Caixa;
import br.edu.ftlf.ads.pbd.bean.CentroCusto;
import br.edu.ftlf.ads.pbd.bean.Enums.MovimentoSituacao;
import br.edu.ftlf.ads.pbd.bean.Movimento;
import br.edu.ftlf.ads.pbd.bean.Usuario;

@Entity
@Table(name = "TbAudMovimentos")
public class MovimentoAudit extends Auditable<Movimento> {

	private static final long serialVersionUID = 1L;

	public MovimentoAudit() {
	}
	
	public Integer getId() {
		return getBean().getId();
	}
	
	public void setId(Integer id) {
		getBean().setId(id);
	}
	
	public MovimentoSituacao getSituacao() {
		return getBean().getSituacao();
	}

	public void setSituacao(MovimentoSituacao situacao) {
		getBean().setSituacao(situacao);
	}

	public BigDecimal getValor() {
		return getBean().getValor();
	}

	public void setValor(BigDecimal valor) {
		getBean().setValor(valor);
	}

	public Caixa getCaixa() {
		return getBean().getCaixa();
	}

	public void setCaixa(Caixa tbcaixa) {
		getBean().setCaixa(tbcaixa);
	}

	public CentroCusto getCentroCusto() {
		return getBean().getCentroCusto();
	}

	public void setCentroCusto(CentroCusto tbcentrocusto) {
		getBean().setCentroCusto(tbcentrocusto);
	}

	public Usuario getUsuario() {
		return getBean().getUsuario();
	}

	public void setUsuario(Usuario tbusuario) {
		getBean().setUsuario(tbusuario);
	}

}