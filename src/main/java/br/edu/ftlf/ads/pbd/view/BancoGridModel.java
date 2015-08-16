package br.edu.ftlf.ads.pbd.view;

import br.edu.ftlf.ads.pbd.bean.Banco;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridColumn;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridViewModel;

@GridViewModel(Banco.class)
public class BancoGridModel {

	private Banco banco;

	public BancoGridModel(Banco banco) {
		this.banco = banco;
	}

	@GridColumn(name=" ", index = 0, flex = 0)
	public String get_() {
		return "";
	}
	
	@GridColumn(index = 1, flex = 5)
	public String getNome() {
		return banco.getNome();
	}

	@GridColumn(index = 2, flex = 1)
	public Boolean getAtivo() {
		return banco.getAtivo();
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
}
