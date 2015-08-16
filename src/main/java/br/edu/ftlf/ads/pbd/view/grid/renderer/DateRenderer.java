package br.edu.ftlf.ads.pbd.view.grid.renderer;

import java.text.DateFormat;

public class DateRenderer extends GridCellRenderer {

	private static final long serialVersionUID = -3798283472587572396L;
	private DateFormat formatter = DateFormat.getDateInstance();

	public DateRenderer() {
		super();
		setHorizontalAlignment(CENTER);
	}

	public void setValue(Object value) {
		setText((value == null) ? "" : formatter.format(value));
	}
}