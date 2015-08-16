package br.edu.ftlf.ads.pbd.view.toolbar;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * Implementacao padrao para toolbar usando 
 * as operacoes basicas de adicao, edicao, remocao, listagem
 * @author Marcus Vinicius de Carvalho Silva
 *
 */
public class CrudToolbar extends JToolBar {


	private static final long serialVersionUID = -776747581658325970L;

	private static final ImageIcon ICON_NEW = new ImageIcon(CrudToolbar.class.getResource("/br/edu/ftlf/ads/pbd/images/x16/page_add.png"));
	private static final ImageIcon ICON_EDIT = new ImageIcon(CrudToolbar.class.getResource("/br/edu/ftlf/ads/pbd/images/x16/note_edit.png"));
	private static final ImageIcon ICON_DELETE = new ImageIcon(CrudToolbar.class.getResource("/br/edu/ftlf/ads/pbd/images/x16/note_remove.png"));
	private static final ImageIcon ICON_LOAD = new ImageIcon(CrudToolbar.class.getResource("/br/edu/ftlf/ads/pbd/images/x16/repeat.png"));
	private static final ImageIcon ICON_CLOSE = new ImageIcon(CrudToolbar.class.getResource("/br/edu/ftlf/ads/pbd/images/x16/window_remove.png"));
	private static final Font FONT = new Font("Courier New", Font.BOLD, 12);
	private static final Dimension SIZE = new Dimension(50, 25);
	private static final Border BUTTON_BORDER = BorderFactory.createEmptyBorder();
	private static final Insets BUTTON_MARGIM = new Insets(5, 5, 5, 5);
	
	private JButton newButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton closeButton;
	private JButton loadButton;

	private JButton printButton;
	
	public CrudToolbar() {
		
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setPreferredSize(SIZE);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setFloatable(false);
		
	}
	
	public CrudToolbar(ActionListener listener) {
		this();
		addButtonsListener(listener);
	}

	private void buildBtnNew() {
		newButton = new JButton();
		newButton.setFont(FONT);
		newButton.setBorder(BUTTON_BORDER);
		newButton.setToolTipText("Adicionar novo Registro");
		newButton.setActionCommand("add");
		newButton.setIcon(ICON_NEW);
		newButton.setMargin(BUTTON_MARGIM);
		newButton.setPreferredSize(SIZE);
		newButton.setMaximumSize(SIZE);
		newButton.setSize(SIZE);
		add(newButton);
	}

	private void buildBtnEdit() {
		editButton = new JButton();
		editButton.setFont(FONT);
		editButton.setBorder(BUTTON_BORDER);
		editButton.setIcon(ICON_EDIT);
		editButton.setToolTipText("Editar Registro selecionado.");
		editButton.setActionCommand("edit");
		editButton.setMargin(BUTTON_MARGIM);
		editButton.setPreferredSize(SIZE);
		editButton.setMaximumSize(SIZE);
		editButton.setSize(SIZE);
		add(editButton);
	}

	private void buildBtnDelete() {
		deleteButton = new JButton();
		deleteButton.setFont(FONT);
		deleteButton.setBorder(BUTTON_BORDER);
		deleteButton.setIcon(ICON_DELETE);
		deleteButton.setToolTipText("Deletar Registro Selecionado.");
		deleteButton.setActionCommand("delete");
		deleteButton.setMargin(BUTTON_MARGIM);
		deleteButton.setPreferredSize(SIZE);
		deleteButton.setMaximumSize(SIZE);
		deleteButton.setSize(SIZE);
		add(deleteButton);
	}

	private void buildBtnLoad() {
		loadButton = new JButton();
		loadButton.setFont(FONT);
		loadButton.setBorder(BUTTON_BORDER);
		loadButton.setIcon(ICON_LOAD);
		loadButton.setToolTipText("Carregar registros.");
		loadButton.setActionCommand("load");
		loadButton.setPreferredSize(SIZE);
		loadButton.setMaximumSize(SIZE);
		loadButton.setMargin(BUTTON_MARGIM);
		loadButton.setSize(SIZE);
		add(loadButton);
	}

	private void buildBtnClose() {
		closeButton = new JButton();
		closeButton.setFont(FONT);
		closeButton.setBorder(BUTTON_BORDER);
		closeButton.setIcon(ICON_CLOSE);
		closeButton.setToolTipText("Fechar janela.");
		closeButton.setActionCommand("close");
		closeButton.setMargin(BUTTON_MARGIM);
		closeButton.setPreferredSize(SIZE);
		closeButton.setMaximumSize(SIZE);
		closeButton.setSize(SIZE);
		add(closeButton);
	}
	
	private void buildBtnPrint() {
		printButton = new JButton();
		printButton.setFont(FONT);
		printButton.setBorder(BUTTON_BORDER);
		printButton.setIcon(ICON_CLOSE);
		printButton.setToolTipText("Fechar janela.");
		printButton.setActionCommand("close");
		printButton.setMargin(BUTTON_MARGIM);
		printButton.setPreferredSize(SIZE);
		printButton.setMaximumSize(SIZE);
		printButton.setSize(SIZE);
		add(printButton);
	}

	public void addButtonsListener(ActionListener listener) {
		if (newButton != null) {
			newButton.addActionListener(listener);
		}
		if (editButton != null) {
			editButton.addActionListener(listener);
		}
		if (deleteButton != null) {
			deleteButton.addActionListener(listener);
		}
		if (loadButton != null) {
			loadButton.addActionListener(listener);
		}
		if (printButton != null) {
			printButton.addActionListener(listener);
		}
		if (closeButton != null) {
			closeButton.addActionListener(listener);
		}
	}
	
	public CrudToolbar withNew() {
		buildBtnNew();
		return this;
	}
	
	public CrudToolbar withEdit() {
		buildBtnEdit();
		return this;
	}
	
	public CrudToolbar withDelete() {
		buildBtnDelete();
		return this;
	}
	
	public CrudToolbar withLoad() {
		buildBtnLoad();
		return this;
	}
	
	public CrudToolbar withPrint() {
		buildBtnPrint();
		return this;
	}
	
	public CrudToolbar withClose() {
		buildBtnClose();
		return this;
	}

	public JButton getNewButton() {
		return newButton;
	}

	public JButton getEditButton() {
		return editButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public JButton getCloseButton() {
		return closeButton;
	}

	public JButton getLoadButton() {
		return loadButton;
	}
	
	public JButton getPrintButton() {
		return printButton;
	}
	
	public JButton[] getButtons(){
		return new JButton[] {newButton, editButton, deleteButton, loadButton, printButton, closeButton};
	}
}
