package br.edu.ftlf.ads.pbd.view.menu;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

public class CrudContextMenu extends JPopupMenu {

	private static final long serialVersionUID = 5197516429016627336L;
	private JButton btnNew;
	private JButton btnEdit;
	private JButton btnDelete;
	
	public CrudContextMenu() {
		
		buildBtnNew();
		buildBtnEdit();
		buildBtnDelete();
		
	}
	
	public CrudContextMenu(ActionListener listener){
		this();
		addButtonsListener(listener);
	}

	private void buildBtnDelete() {
		btnDelete = new JButton();
		btnDelete.setToolTipText("Deletar Registro");
		btnDelete.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnDelete.setMargin(new Insets(2, 15, 2, 15));
		btnDelete.setPreferredSize(new Dimension(50, 25));
		btnDelete.setIcon(new ImageIcon(getClass().getResource("/br/edu/ftlf/ads/pbd/images/x16/remove.png")));
		btnDelete.setActionCommand("delete");
		add(btnDelete);
	}

	private void buildBtnEdit() {
		btnEdit = new JButton();
		btnEdit.setToolTipText("Editar Registro");
		btnEdit.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnEdit.setMargin(new Insets(2, 15, 2, 15));
		btnEdit.setPreferredSize(new Dimension(50, 25));
		btnEdit.setIcon(new ImageIcon(getClass().getResource("/br/edu/ftlf/ads/pbd/images/x16/note_edit.png")));
		btnEdit.setActionCommand("edit");
		add(btnEdit);
	}

	private void buildBtnNew() {
		btnNew = new JButton();
		btnNew.setToolTipText("Adicionar Registro");
		btnNew.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNew.setPreferredSize(new Dimension(50, 25));
		btnNew.setMargin(new Insets(2, 15, 2, 15));
		btnNew.setIcon(new ImageIcon(getClass().getResource("/br/edu/ftlf/ads/pbd/images/x16/add.png")));
		btnNew.setActionCommand("add");
		add(btnNew);
	}

	public JButton getNewButton() {
		return btnNew;
	}

	public JButton getEditButton() {
		return btnEdit;
	}

	public JButton getDeleteButton() {
		return btnDelete;
	}
	
	public void addButtonsListener(ActionListener listener){
		btnNew.addActionListener(listener);
		btnDelete.addActionListener(listener);
		btnEdit.addActionListener(listener);
	}
	
}
