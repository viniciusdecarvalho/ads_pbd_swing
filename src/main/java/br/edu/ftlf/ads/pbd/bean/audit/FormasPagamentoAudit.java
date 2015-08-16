package br.edu.ftlf.ads.pbd.bean.audit;

import java.util.EnumSet;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import br.edu.ftlf.ads.pbd.bean.Enums.FormasPagamentoTipo;
import br.edu.ftlf.ads.pbd.bean.FormasPagamento;


@Entity
@Table(name = "TbAudFormasPagamentos")
public class FormasPagamentoAudit extends Auditable<FormasPagamento> {

	private static final long serialVersionUID = 1L;

	public FormasPagamentoAudit() {
	}
	
	public Short getId() {
		return getBean().getId();
	}
	
	public void setId(Short id) {
		getBean().setId(id);
	}
	
	public Boolean getAtivo() {
		return getBean().getAtivo();
	}

	public void setAtivo(Boolean ativo) {
		getBean().setAtivo(ativo);
	}

	public String getNome() {
		return getBean().getNome();
	}

	public void setNome(String nome) {
		getBean().setNome(nome);
	}

	@Type(type = "br.edu.ftlf.ads.pbd.bean.usertype.FormasPagamentoTipoUserType")
	public EnumSet<FormasPagamentoTipo> getTipo() {
		return getBean().getTipo();
	}

	public void setTipo(EnumSet<FormasPagamentoTipo> tipo) {
		getBean().setTipo(tipo);		
	}

}
