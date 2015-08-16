package br.edu.ftlf.ads.pbd.view.grid.renderer;

import javax.swing.Icon;

public class IconRenderer extends GridCellRenderer {

	private static final long serialVersionUID = -3785095743111009239L;

	public IconRenderer() {
		super();
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}

	public void setValue(Object value) {
		setIcon((value instanceof Icon) ? (Icon) value : null);
	}
}