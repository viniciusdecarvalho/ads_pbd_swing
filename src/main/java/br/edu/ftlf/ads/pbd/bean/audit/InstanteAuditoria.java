package br.edu.ftlf.ads.pbd.bean.audit;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TbInstantes")
public class InstanteAuditoria {

	public enum Instante {
		BEFORE, AFTER
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Instante instante;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="auditoriaId")
	private Auditoria auditoria;
	
	public InstanteAuditoria(Instante instante, Auditoria auditoria) {
		this.instante = instante;
		this.auditoria = auditoria;
	}
	
	public InstanteAuditoria() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instante getInstante() {
		return instante;
	}

	public void setInstante(Instante instante) {
		this.instante = instante;
	}

	public Auditoria getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}
	
}
