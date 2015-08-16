package br.edu.ftlf.ads.pbd.view;

import br.edu.ftlf.ads.pbd.bean.CentroCusto;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridColumn;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridViewModel;

@GridViewModel(CentroCusto.class)
public class CentroCustoGridModel {

	private CentroCusto centroCusto;

	public CentroCustoGridModel(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}

	@GridColumn(index = 0, flex = 5)
	public String getNome() {
		return centroCusto.getNome();
	}

	@GridColumn(index = 1, flex = 2)
	public String getTipo() {
		return centroCusto.getTipo().toString();
	}

	@GridColumn(index = 2, flex = 1)
	public Boolean getAtivo() {
		return centroCusto.getAtivo();
	}

	public CentroCusto getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}
}
