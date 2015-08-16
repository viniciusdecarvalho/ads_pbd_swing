package br.edu.ftlf.ads.pbd.bean.audit;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import br.edu.ftlf.ads.pbd.bean.Bean;

@MappedSuperclass
public abstract class Auditable<T extends Bean<? extends Serializable>>
	implements Serializable {

	private static final long serialVersionUID = -7572766837531181517L;
	
	private T bean;	
	private InstanteAuditoria instante;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="instanteId")
	public InstanteAuditoria getInstante() {
		return instante;
	}
	
	public void setInstante(InstanteAuditoria auditoria) {
		this.instante = auditoria;
	}

	@Transient
	public T getBean() {
		return bean;
	}
	
	public void setBean(T bean) {
		this.bean = bean;
	}
	
	public Timestamp getUltimaAtualizacao() {
		return getBean().getUltimaAtualizacao();
	}
	
	public void setUltimaAtualizacao(Timestamp ultimaAtualizacao) {
		this.getBean().setUltimaAtualizacao(ultimaAtualizacao);
	}
	
}
