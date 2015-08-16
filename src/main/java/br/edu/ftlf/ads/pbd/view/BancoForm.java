package br.edu.ftlf.ads.pbd.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.edu.ftlf.ads.pbd.bean.Banco;
import br.edu.ftlf.ads.pbd.view.form.Form;
import br.edu.ftlf.ads.pbd.view.form.bind.Bind;

public class BancoForm extends Form<Banco> {

	private static final long serialVersionUID = 9212739750626482783L;
	
	@Bind(property = "nome", expression = "text", name = "nome")
	private JTextField nomeJTextField;
	
	@Bind(property = "ativo", expression = "selected", name = "ativo")
	private JCheckBox ativoJCheckBox;

	public BancoForm() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0E-4 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0E-4 };
		setLayout(gridBagLayout);

		JLabel nomeLabel = new JLabel("Nome:");
		GridBagConstraints labelGbc_0 = new GridBagConstraints();
		labelGbc_0.insets = new Insets(5, 5, 5, 5);
		labelGbc_0.gridx = 0;
		labelGbc_0.gridy = 0;
		add(nomeLabel, labelGbc_0);

		nomeJTextField = new JTextField();
		GridBagConstraints componentGbc_0 = new GridBagConstraints();
		componentGbc_0.insets = new Insets(5, 0, 5, 5);
		componentGbc_0.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_0.gridx = 1;
		componentGbc_0.gridy = 0;
		add(nomeJTextField, componentGbc_0);

		JLabel ativoLabel = new JLabel("Ativo:");
		GridBagConstraints labelGbc_1 = new GridBagConstraints();
		labelGbc_1.insets = new Insets(5, 5, 5, 5);
		labelGbc_1.gridx = 0;
		labelGbc_1.gridy = 1;
		add(ativoLabel, labelGbc_1);

		ativoJCheckBox = new JCheckBox();
		GridBagConstraints componentGbc_1 = new GridBagConstraints();
		componentGbc_1.insets = new Insets(5, 0, 5, 5);
		componentGbc_1.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_1.gridx = 1;
		componentGbc_1.gridy = 1;
		add(ativoJCheckBox, componentGbc_1);

	}

}
