package br.edu.ftlf.ads.pbd.view.grid.renderer;

public class IDFormatRenderer extends GridCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	protected void setValue(Object value) {
		setText(String.format("%05d", value.toString()));
	}

}