package br.edu.ftlf.ads.pbd.view.mask;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class DefaultMask extends MaskFormatter {

	private static final long serialVersionUID = -6966477780743816400L;

	public DefaultMask(String mask) {
		setOverwriteMode(true);
		setAllowsInvalid(false);
		setPlaceholderCharacter('_');
		setValueContainsLiteralCharacters(false);
		setCommitsOnValidEdit(true);  
		setAllowsInvalid(false);
		setMask(mask);
	}
	
	@Override
	public void setMask(String mask) {
		try {
			super.setMask(mask);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
