package br.edu.ftlf.ads.pbd.view.grid.annotation;

import javax.swing.SwingConstants;

public enum VAlign {
	
	TOP(SwingConstants.TOP), 
	CENTER(SwingConstants.CENTER), 
	BOTTOM(SwingConstants.BOTTOM);
	
	private int value;
	
	private VAlign(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}