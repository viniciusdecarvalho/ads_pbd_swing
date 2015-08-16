package br.edu.ftlf.ads.pbd.view.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitDocument extends PlainDocument {

	private static final long serialVersionUID = 4935924396023186746L;

	private int maxLength;

	public LimitDocument(int maxLength) {
		this.maxLength = maxLength;
	}

	@Override
	public void insertString(int offset, String newValue, AttributeSet attr)
			throws BadLocationException {

		if (newValue == null) return;  
		  
		// aceitara qualquer no. de caracteres  
        if (maxLength <= 0) {  
            super.insertString(offset, newValue, attr);  
            return;  
        }  
  
        int ilen = (getLength() + newValue.length());  
        if (ilen <= maxLength)    // se o comprimento final for menor...  
            super.insertString(offset, newValue, attr);   // ...aceita str
        else  
        {  
            if (getLength() == maxLength) return; // nada a fazer  
            String newStr = newValue.substring(0, (maxLength - getLength()));  
            super.insertString(offset, newStr, attr);  
        }  
	}
}
