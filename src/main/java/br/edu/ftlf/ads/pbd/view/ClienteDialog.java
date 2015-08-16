package br.edu.ftlf.ads.pbd.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import br.edu.ftlf.ads.pbd.view.form.bind.Bind;
import br.edu.ftlf.ads.pbd.view.toolbar.OkCancelToolbar;

public class ClienteDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private ClienteForm form;
	private OkCancelToolbar toolbar;

	@Bind(property = "model.new", expression = "visible", name = "continue")
	private JCheckBox continueCheckBox;

	/**
	 * Create the dialog.
	 */
	public ClienteDialog() {
		setResizable(false);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 546, 373);
		getContentPane().setLayout(new BorderLayout());
		
		form = new ClienteForm();
		getContentPane().add(form, BorderLayout.CENTER);
		
		toolbar = new OkCancelToolbar();
		toolbar.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		getContentPane().add(toolbar, BorderLayout.SOUTH);
		
		continueCheckBox = new JCheckBox("Continue");
		continueCheckBox.setToolTipText("Deixar formulario pronto para adicionar novo registro apos confirmar.");
		continueCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
		continueCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		toolbar.add(continueCheckBox, BorderLayout.NORTH);

		setModal(true);
	}
	
	public ClienteForm showWindow() {
		form.getBinder().bind(form, this);
		setVisible(true);
		return form;
	}
	
	public ClienteForm getForm() {
		return form;
	}
	
	public OkCancelToolbar getToolbar() {
		return toolbar;
	}
	
	public JCheckBox getContinueCheckBox() {
		return continueCheckBox;
	}
	
}
