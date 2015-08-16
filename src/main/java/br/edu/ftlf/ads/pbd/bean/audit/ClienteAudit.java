package br.edu.ftlf.ads.pbd.bean.audit;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.ftlf.ads.pbd.bean.Cliente;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridColumn;
import br.edu.ftlf.ads.pbd.view.grid.annotation.HAlign;

/**
 * The persistent class for the tbclientes database table.
 * 
 */
@Entity
@Table(name = "TbAudClientes")
public class ClienteAudit extends Auditable<Cliente> {

	private static final long serialVersionUID = 1L;

	public ClienteAudit() {
	}
	
	public Integer getId() {
		return getBean().getId();
	}
	
	public void setId(Integer id) {
		getBean().setId(id);
	}
	
	public Boolean getAtivo() {
		return getBean().getAtivo();
	}

	public void setAtivo(Boolean ativo) {
		getBean().setAtivo(ativo);
	}

	@GridColumn(name = "Cpf/Cnpj", index = 1, flex = 2)
	public String getCpfCnpj() {
		return getBean().getCpfCnpj();
	}

	public void setCpfCnpj(String cpfCnpj) {
		getBean().setCpfCnpj(cpfCnpj);
	}

	@GridColumn(name = "Razao Social", index = 0, flex = 3, hAlign = HAlign.LEFT)
	public String getRazaoSocial() {
		return getBean().getRazaoSocial();
	}

	public void setRazaoSocial(String razaoSocial) {
		getBean().setRazaoSocial(razaoSocial);
	}

	public String getCep() {
		return getBean().getCep();
	}

	public void setCep(String cep) {
		getBean().setCep(cep);
	}

	public String getLogradouro() {
		return getBean().getLogradouro();
	}

	public void setLogradouro(String logradouro) {
		getBean().setLogradouro(logradouro);
	}

	public String getComplemento() {
		return getBean().getComplemento();
	}

	public void setComplemento(String complemento) {
		getBean().setComplemento(complemento);
	}

	public String getCidade() {
		return getBean().getCidade();
	}

	public void setCidade(String cidade) {
		getBean().setCidade(cidade);
	}

	public String getEstado() {
		return getBean().getEstado();
	}

	public void setEstado(String estado) {
		getBean().setEstado(estado);
	}

	public String getFone() {
		return getBean().getFone();
	}

	public void setFone(String fone) {
		getBean().setFone(fone);
	}

	public String getCelular() {
		return getBean().getCelular();
	}

	public void setCelular(String celular) {
		getBean().setCelular(celular);		
	}

	public String getEmail() {
		return getBean().getEmail();
	}

	public void setEmail(String email) {
		getBean().setEmail(email);
	}

}