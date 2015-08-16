package br.edu.ftlf.ads.pbd.view.grid.renderer;

import br.edu.ftlf.ads.pbd.view.mask.FoneMask;

public class FoneRenderer extends GridCellRenderer {

	private FoneMask render = new FoneMask();
	private static final long serialVersionUID = 1L;

	@Override
	protected void setValue(Object value) {
		try {
			setText(render.valueToString(value.toString()));
		} catch (Exception e) {
			setText("");
		}
	}
}
