package br.edu.ftlf.ads.pbd.view.field;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import br.edu.ftlf.ads.pbd.bean.BeanImpl;

public class BeanComboBox<T extends BeanImpl<?>> extends JComponent {

	private static final long serialVersionUID = 1L;
	private AddButton<T> addButton;
	private JComboBox<T> comboBox;

	/**
	 * Create the panel.
	 */
	public BeanComboBox() {
		setLayout(new BorderLayout());
		
		comboBox = new JComboBox<T>();		
		add(comboBox, BorderLayout.CENTER);
		
		addButton = new AddButton<T>(comboBox);
		add(addButton, BorderLayout.EAST);

	}
	
	public JComboBox<T> getComboBox() {
		return comboBox;
	}
	
	public AddButton<T> getAddButton() {
		return addButton;
	}
	
}
