package br.edu.ftlf.ads.pbd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ftlf.ads.pbd.bean.Enums.ClienteTipo;
import br.edu.ftlf.ads.pbd.bean.audit.Audit;
import br.edu.ftlf.ads.pbd.bean.audit.AuditorListener;
import br.edu.ftlf.ads.pbd.bean.audit.ClienteAudit;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridColumn;
import br.edu.ftlf.ads.pbd.view.grid.annotation.HAlign;

/**
 * The persistent class for the tbclientes database table.
 * 
 */
@Entity
@Table(name = "TbClientes")
@EntityListeners(AuditorListener.class)
@Audit(ClienteAudit.class)
public class Cliente extends BeanImpl<Integer> {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private boolean ativo;
	
	@Transient
	private ClienteTipo tipo = ClienteTipo.FISICA;

	@NotEmpty
	@Length(max=20)
	@Column(nullable = false, length = 20)
	private String cpfCnpj;

	@NotEmpty
	@Length(max=100)
	@Column(nullable = false, length = 100)
	private String razaoSocial;

	private String cep;

	private String logradouro;

	private String complemento;

	private String cidade;

	private String estado;
	
	private String fone;

	private String celular;

	@Email
	private String email;

	public Cliente() {
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		firePropertyChange("ativo", this.ativo, this.ativo = ativo);
	}

	@GridColumn(name = "Cpf/Cnpj", index = 1, flex = 2)
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		firePropertyChange("cpfCnpj", this.cpfCnpj, this.cpfCnpj = cpfCnpj);
		firePropertyChange("tipo", this.tipo, 
				this.tipo = cpfCnpj != null ? 
				(cpfCnpj.length() > 11 ? ClienteTipo.JURIDICA : ClienteTipo.FISICA) :
					ClienteTipo.FISICA);
	}

	@GridColumn(name = "Razao Social", index = 0, flex = 3, hAlign = HAlign.LEFT)
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		firePropertyChange("razaoSocial", this.razaoSocial,
				this.razaoSocial = razaoSocial);
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		firePropertyChange("cep", this.cep, this.cep = cep);
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		firePropertyChange("logradouro", this.logradouro, this.logradouro = logradouro);
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		firePropertyChange("complemento", this.complemento, this.complemento = complemento);
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		firePropertyChange("cidade", this.cidade, this.cidade = cidade);
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		firePropertyChange("estado", this.estado, this.estado = estado);
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		firePropertyChange("fone", this.fone, this.fone = fone);
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		firePropertyChange("celular", this.celular, this.celular = celular);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		firePropertyChange("email", this.email, this.email = email);
	}

	@Override
	public String toString() {
		return "Cliente ["
				+ ("tipo=" + tipo + ", ")
				+ ("ativo=" + ativo + ", ")
				+ (cpfCnpj != null ? "cpfCnpj=" + cpfCnpj + ", " : "")
				+ (razaoSocial != null ? "razaoSocial=" + razaoSocial + ", "
						: "")
				+ (cep != null ? "cep=" + cep + ", " : "")
				+ (logradouro != null ? "logradouro=" + logradouro + ", " : "")
				+ (complemento != null ? "complemento=" + complemento + ", "
						: "")
				+ (cidade != null ? "cidade=" + cidade + ", " : "")
				+ (estado != null ? "estado=" + estado + ", " : "")
				+ (fone != null ? "fone=" + fone + ", " : "")
				+ (celular != null ? "celular=" + celular + ", " : "")
				+ (email != null ? "email=" + email : "") + "]";
	}


	// public List<Matricula> getMatriculas() {
	// return matriculas;
	// }
	//
	// public void setMatriculas(List<Matricula> matriculas) {
	// this.matriculas = matriculas;
	// }

}