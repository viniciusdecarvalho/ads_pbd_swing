package br.edu.ftlf.ads.pbd.view.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumericDocument extends PlainDocument {

	private static final long serialVersionUID = 4935924396023186746L;

	@Override
	public void insertString(int offset, String newValue, AttributeSet attr)
			throws BadLocationException {

		if (newValue == null)
			return;

		try {
			new Integer(newValue);
		} catch (NumberFormatException e) {
			return;
		}
		super.insertString(offset, newValue, attr);
	}
}
