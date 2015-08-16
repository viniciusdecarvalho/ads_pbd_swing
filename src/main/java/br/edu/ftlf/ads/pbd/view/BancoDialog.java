package br.edu.ftlf.ads.pbd.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import br.edu.ftlf.ads.pbd.bean.Banco;
import br.edu.ftlf.ads.pbd.view.form.bind.Bind;
import br.edu.ftlf.ads.pbd.view.toolbar.OkCancelToolbar;

public class BancoDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private BancoForm form;
	private OkCancelToolbar toolbar;

	@Bind(property = "model.new", expression = "visible", name = "continue")
	private JCheckBox continueCheckBox;

	/**
	 * Create the dialog.
	 */
	public BancoDialog() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		setLayout(new BorderLayout());
		
		form = new BancoForm();
		add(form, BorderLayout.CENTER);
		toolbar = new OkCancelToolbar();
		toolbar.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		add(toolbar, BorderLayout.SOUTH);
		
		continueCheckBox = new JCheckBox("Continue");
		continueCheckBox.setToolTipText("Deixar formulario pronto para adicionar novo registro apos confirmar.");
		continueCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
		continueCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		toolbar.add(continueCheckBox, BorderLayout.NORTH);

		setAlwaysOnTop(true);
		setModal(true);
	}
	
	public BancoForm showWindow() {
		form.getBinder().bind(form, this);
		form.getField("nome").requestFocus();
		setVisible(true);		
		return form;
	}
	
	public BancoForm getForm() {
		return form;
	}
	
	public OkCancelToolbar getToolbar() {
		return toolbar;
	}
	
	public JCheckBox getContinueCheckBox() {
		return continueCheckBox;
	}
	
	public static void main(String[] args) {
		
		BancoDialog dialog = new BancoDialog();
		Banco model = new Banco();
		model.setId(new Short("2"));
		dialog.getForm().setModel( model );
		dialog.showWindow();
		
	}
}
