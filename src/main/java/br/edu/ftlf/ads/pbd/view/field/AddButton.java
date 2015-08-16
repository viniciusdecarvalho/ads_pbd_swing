package br.edu.ftlf.ads.pbd.view.field;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class AddButton<T> extends JButton {
	
	private static final long serialVersionUID = 6962345575510586335L;

	private JComboBox<T> comboBox;
	
	public AddButton(JComboBox<T> comboBox) {
		
		this.comboBox = comboBox;
		this.comboBox.add(this);
		setMinimumSize(new Dimension(25, 25));
		setMaximumSize(new Dimension(25, 25));
		setPreferredSize(new Dimension(25, 25));
		setIcon(new ImageIcon(AddButton.class.getResource("/br/edu/ftlf/ads/pbd/images/x16/add.png")));
	}
	
	public JComboBox<T> getComboBox() {
		return comboBox;
	}
	
}
