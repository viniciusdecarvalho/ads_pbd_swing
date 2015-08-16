package br.edu.ftlf.ads.pbd.view.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class CaseDocument extends PlainDocument {

	private static final long serialVersionUID = -932444358809516235L;
	public enum Case { UPPER, LOW, NORMAL }
	private Case _case;
	
	public CaseDocument(Case _case) {
		this._case = _case;
	}

	@Override
	public void insertString(int offset, String newValue, AttributeSet attr)
			throws BadLocationException {
		if (newValue == null) return;  
		switch (_case) {
		case UPPER:
			super.insertString(offset, newValue.toUpperCase(), attr);
			break;
		case LOW:
			super.insertString(offset, newValue.toLowerCase(), attr);
			break;
		case NORMAL:
			super.insertString(offset, newValue, attr);
			break;
		default:
			break;
		}
	}

}
