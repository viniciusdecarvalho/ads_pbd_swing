package br.edu.ftlf.ads.pbd.view.toolbar;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * Toolbar com as op��es de confimar ou cancelar a opera��o
 * @author Marcus Vinicius de Carvalho Silva
 *
 */
public class OkCancelToolbar extends JToolBar {

	private static final Dimension SIZE = new Dimension(100, 30);
	private static final long serialVersionUID = -6436726086327959489L;
	private JButton okButton;
	private JButton cancelButton;

	public OkCancelToolbar() {

		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setPreferredSize(SIZE);
		setFloatable(false);
		setVisible(true);
		buildCancelButton();
		add(new Separator());
		buildOkButton();
		
	}
	
	public OkCancelToolbar(ActionListener listener) {
		this();
		addButtonsListener(listener);
	}

	/**
	 * Monta o bot�o de Ok
	 */
	private void buildOkButton() {
		okButton = new JButton("Ok");
		okButton.setName("ok");
		okButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		okButton.setMaximumSize(SIZE);
		okButton.setSize(SIZE);
		okButton.setToolTipText("Salvar Dados.");
		okButton.setActionCommand("save");
		okButton.setIcon(new ImageIcon(OkCancelToolbar.class.getResource("/br/edu/ftlf/ads/pbd/images/x16/accept.png")));
		add(okButton);
	}
	
	/**
	 * Monta o bot�o de cancelar
	 */
	private void buildCancelButton() {
		cancelButton = new JButton("Cancelar");
		cancelButton.setName("cancel");
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		cancelButton.setMaximumSize(SIZE);
		cancelButton.setSize(SIZE);
		cancelButton.setToolTipText("Cancelar operacao");
		cancelButton.setActionCommand("cancel");
		cancelButton.setIcon(new ImageIcon(OkCancelToolbar.class.getResource("/br/edu/ftlf/ads/pbd/images/x16/remove.png")));
		add(cancelButton);
	}
	
	/**
	 * Permite adicionar um componente para setar os eventos
	 * @param listener
	 */
	public void addButtonsListener(ActionListener listener) {
		okButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
	
}
