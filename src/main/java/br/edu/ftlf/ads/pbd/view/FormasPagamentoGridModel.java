package br.edu.ftlf.ads.pbd.view;

import br.edu.ftlf.ads.pbd.bean.FormasPagamento;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridColumn;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridViewModel;

@GridViewModel(FormasPagamento.class)
public class FormasPagamentoGridModel {

	private FormasPagamento formaPagamento;

	public FormasPagamentoGridModel(FormasPagamento formaPagemento) {
		this.formaPagamento = formaPagemento;
	}

	@GridColumn(index = 0, flex = 5)
	public String getNome() {
		return formaPagamento.getNome();
	}

	@GridColumn(index = 1, flex = 2)
	public String getTipo() {
		return formaPagamento.getTipo().toString();
	}

	@GridColumn(index = 2, flex = 1)
	public Boolean getAtivo() {
		return formaPagamento.getAtivo();
	}

	public FormasPagamento getFormasPagamento() {
		return formaPagamento;
	}

	public void setFormasPagamento(FormasPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
}
