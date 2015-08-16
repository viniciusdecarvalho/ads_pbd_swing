package br.edu.ftlf.ads.pbd.bean.audit;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.edu.ftlf.ads.pbd.bean.Usuario;
import br.edu.ftlf.ads.pbd.bean.audit.InstanteAuditoria.Instante;

@Entity
@Table(name="TbAuditorias")
public class Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Operacao {
		INSERT, UPDATE, DELETE, LOAD
	}
	
	public Auditoria() {
	}
	
	public Auditoria(Instante instante, Operacao operacao) {
		this.instante = new InstanteAuditoria(instante, this);
		this.operacao = operacao;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Operacao operacao;

	@Transient
	private InstanteAuditoria instante;
	
	public InstanteAuditoria getInstante() {
		return instante;
	}
	
	public void setInstante(InstanteAuditoria instante) {
		this.instante = instante;
	}

	@Column(insertable=false, updatable=false)
	private Timestamp data;
	
	@ManyToOne
	@JoinColumn(name = "usuarioId")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Auditoria [operacao=" + operacao + ", instante=" + instante
				+ ", data=" + data + ", usuario=" + usuario + "]";
	}

}