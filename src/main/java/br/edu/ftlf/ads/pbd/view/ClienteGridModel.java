package br.edu.ftlf.ads.pbd.view;

import br.edu.ftlf.ads.pbd.bean.Cliente;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridColumn;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridColumnRender;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridViewModel;
import br.edu.ftlf.ads.pbd.view.grid.annotation.HAlign;
import br.edu.ftlf.ads.pbd.view.grid.renderer.CpfCnpjRenderer;
import br.edu.ftlf.ads.pbd.view.grid.renderer.FoneRenderer;
import br.edu.ftlf.ads.pbd.view.grid.renderer.OkCancelRenderer;

@GridViewModel(Cliente.class)
public class ClienteGridModel {

	private Cliente cliente;

	public ClienteGridModel(Cliente cliente) {
		this.cliente = cliente;
	}

	@GridColumn(name=".", index = 0, flex = 0)
	public String get_() {
		return "";
	}
	
	@GridColumn(index = 1, flex = 5, hAlign = HAlign.LEFT)
	public String getNome() {
		return cliente.getRazaoSocial();
	}

	@GridColumn(index = 2, name = "Cpf/Cnpj", flex=2)
	@GridColumnRender(CpfCnpjRenderer.class)
	public String getCpfCnpj() {
		return cliente.getCpfCnpj();
	}

	@GridColumn(index = 3, flex = 2)
	@GridColumnRender(FoneRenderer.class)
	public String getCelular() {
		return cliente.getCelular();
	}

	@GridColumn(index = 4, flex = 2)
	@GridColumnRender(FoneRenderer.class)
	public String getFone() {
		return cliente.getFone();
	}

	@GridColumn(index = 5, flex = 2, hAlign=HAlign.LEFT)
	public String getEmail() {
		return cliente.getEmail();
	}

	@GridColumn(index = 6, flex = .5)
	@GridColumnRender(OkCancelRenderer.class)
	public Boolean getAtivo() {
		return cliente.getAtivo();
	}

}
