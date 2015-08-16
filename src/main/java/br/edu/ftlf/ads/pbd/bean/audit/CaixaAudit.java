package br.edu.ftlf.ads.pbd.bean.audit;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.ftlf.ads.pbd.bean.Caixa;
import br.edu.ftlf.ads.pbd.bean.Enums.CaixaSituacao;
import br.edu.ftlf.ads.pbd.bean.Usuario;

/**
 * The persistent class for the tbcaixas database table.
 */
@Entity
@Table(name="TbAudCaixas")
public class CaixaAudit extends Auditable<Caixa> {

	private static final long serialVersionUID = 1L;

	public CaixaAudit() {}
	
	public Integer getId() {
		return getBean().getId();
	}
	
	public void setId(Integer id) {
		getBean().setId(id);
	}
	
	public Date getData() {
		return this.getBean().getData();
	}

	public void setData(Date data) {
		this.getBean().setData(data);
	}

	public CaixaSituacao getSituacao() {
		return this.getBean().getSituacao();
	}

	public void setSituacao(CaixaSituacao situacao) {
		this.getBean().setSituacao(situacao);
	}

	public Usuario getUsuario() {
		return this.getBean().getUsuario();
	}

	public void setUsuario(Usuario usuario) {
		this.getBean().setUsuario(usuario);
	}
	
}