package br.edu.ftlf.ads.pbd.bean.audit;

import java.util.EnumSet;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import br.edu.ftlf.ads.pbd.bean.CentroCusto;
import br.edu.ftlf.ads.pbd.bean.Enums.CentroCustoTipo;

@Entity
@Table(name = "TbAudCentroCustos")
public class CentroCustoAudit extends Auditable<CentroCusto> {
	
	private static final long serialVersionUID = 1L;

	public CentroCustoAudit() {
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

	@Type(type = "br.edu.ftlf.ads.pbd.bean.usertype.CentroCustoTipoUserType")
	public EnumSet<CentroCustoTipo> getTipo() {
		return getBean().getTipo();
	}

	public void setTipo(EnumSet<CentroCustoTipo> tipo) {
		getBean().setTipo(tipo);
	}

}
