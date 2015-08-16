package br.edu.ftlf.ads.pbd.view;

import br.edu.ftlf.ads.pbd.bean.Fornecedor;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridColumn;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridColumnRender;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridViewModel;
import br.edu.ftlf.ads.pbd.view.grid.annotation.HAlign;
import br.edu.ftlf.ads.pbd.view.grid.renderer.CpfCnpjRenderer;

@GridViewModel(Fornecedor.class)
public class FornecedorGridModel {

	private Fornecedor fornecedor;

	public FornecedorGridModel(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@GridColumn(name=" ", index = 0, flex = 0)
	public String get_() {
		return "";
	}
	
	@GridColumn(index = 1, flex = 5, hAlign = HAlign.LEFT)
	public String getNome() {
		return fornecedor.getRazaoSocial();
	}

	@GridColumn(index = 2, name = "Cpf/Cnpj", flex=2)
	@GridColumnRender(CpfCnpjRenderer.class)
	public String getCpfCnpj() {
		return fornecedor.getCpfCnpj();
	}

}
