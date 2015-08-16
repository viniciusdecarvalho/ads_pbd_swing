package br.edu.ftlf.ads.pbd.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ftlf.ads.pbd.bean.Enums.CaixaSituacao;
import br.edu.ftlf.ads.pbd.bean.audit.Audit;
import br.edu.ftlf.ads.pbd.bean.audit.AuditorListener;
import br.edu.ftlf.ads.pbd.bean.audit.CaixaAudit;

/**
 * The persistent class for the tbcaixas database table.
 */
@Entity
@Table(name="TbCaixas")
@EntityListeners(AuditorListener.class)
@Audit(CaixaAudit.class)
public class Caixa extends BeanImpl<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

    @Temporal( TemporalType.DATE)
	private Date data;

    @Enumerated(EnumType.STRING)
	private CaixaSituacao situacao;

	@OneToMany(mappedBy="caixa")
	private List<Movimento> movimentos;

    @ManyToOne
	@JoinColumn(name="usuarioId")
	private Usuario usuario;

    public Caixa() {
    }

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public CaixaSituacao getSituacao() {
		return this.situacao;
	}

	public void setSituacao(CaixaSituacao situacao) {
		this.situacao = situacao;
	}

	public List<Movimento> getTbmovimentos() {
		return this.movimentos;
	}

	public void setTbmovimentos(List<Movimento> tbmovimentos) {
		this.movimentos = tbmovimentos;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario tbusuario) {
		this.usuario = tbusuario;
	}
	
}