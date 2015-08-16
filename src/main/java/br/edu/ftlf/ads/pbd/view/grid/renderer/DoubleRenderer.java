package br.edu.ftlf.ads.pbd.view.grid.renderer;

import java.text.NumberFormat;

public class DoubleRenderer extends NumberRenderer {

	private static final long serialVersionUID = 2084996443156556495L;
	private NumberFormat formatter = NumberFormat.getInstance();

	public DoubleRenderer() {
		super();
	}

	public void setValue(Object value) {
		setText((value == null) ? "" : formatter.format(value));
	}
}