package br.edu.ftlf.ads.pbd.view.grid.renderer;

import javax.swing.ImageIcon;

public class OkCancelRenderer extends IconRenderer {

	private static final long serialVersionUID = -2804319311448917415L;

	private static ImageIcon RED = new ImageIcon(OkCancelRenderer.class.getResource("/br/edu/ftlf/ads/pbd/images/x16/remove.png"));
	private static ImageIcon GREEN = new ImageIcon(OkCancelRenderer.class.getResource("/br/edu/ftlf/ads/pbd/images/x16/accept.png"));
	
	@Override
	public void setValue(Object value) {
		boolean bool = ((Boolean)value).booleanValue();
		setToolTipText(bool ? "Ativo" : "Inativo");
		setIcon(bool ? GREEN : RED);
	}
}
