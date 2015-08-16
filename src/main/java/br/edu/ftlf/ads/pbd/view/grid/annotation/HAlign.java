package br.edu.ftlf.ads.pbd.view.grid.annotation;

import javax.swing.SwingConstants;

public enum HAlign {
	
	LEFT(SwingConstants.LEFT), 
	CENTER(SwingConstants.CENTER), 
	RIGHT(SwingConstants.RIGHT);
	
	private int value;
	
	private HAlign(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}