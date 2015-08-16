package br.edu.ftlf.ads.pbd.view.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class GroupDocument extends PlainDocument {

	private static final long serialVersionUID = 6831960455441996740L;

	private Document[] documents;
	
	public GroupDocument(Document... documents) {
		this.documents = documents;
	}
	
	@Override
	public void insertString(int offset, String newValue, AttributeSet attr)
			throws BadLocationException {
		for (Document document : documents) {
			document.insertString(offset, newValue, attr);
		}
	}
}
