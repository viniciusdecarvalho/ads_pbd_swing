package br.edu.ftlf.ads.pbd.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.ftlf.ads.pbd.bean.CentroCusto;
import br.edu.ftlf.ads.pbd.bean.Enums.CentroCustoTipo;
import br.edu.ftlf.ads.pbd.view.field.EnumSetUtils;
import br.edu.ftlf.ads.pbd.view.form.Form;
import br.edu.ftlf.ads.pbd.view.form.bind.Bind;
import br.edu.ftlf.ads.pbd.view.form.bind.Binds;

public class CentroCustoForm extends Form<CentroCusto> {

	private static final long serialVersionUID = 8024275989898151515L;

	@Bind(property = "nome", expression = "text")
	private JTextField nomeJTextField;

	@Bind(property = "ativo", expression = "selected")
	private JCheckBox ativoJCheckBox;

	@Binds({
		@Bind(property = "tipo", expression = "enumSet")
	})	
	private EnumSetUtils<CentroCustoTipo> enumSetContainer;

	public CentroCustoForm() {

		setLayout(new BorderLayout());
		
		JPanel formPane = new JPanel();
		//
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 30, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0E-4 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0E-4 };
		formPane.setLayout(gridBagLayout);
		
		add(formPane, BorderLayout.CENTER);

		JLabel nomeLabel = new JLabel("Nome:");
		GridBagConstraints labelGbc_0 = new GridBagConstraints();
		labelGbc_0.insets = new Insets(5, 5, 5, 5);
		labelGbc_0.gridx = 0;
		labelGbc_0.gridy = 1;
		formPane.add(nomeLabel, labelGbc_0);

		nomeJTextField = new JTextField();
		GridBagConstraints componentGbc_0 = new GridBagConstraints();
		componentGbc_0.insets = new Insets(5, 0, 5, 0);
		componentGbc_0.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_0.gridx = 1;
		componentGbc_0.gridy = 1;
		formPane.add(nomeJTextField, componentGbc_0);

		JLabel tipoLabel = new JLabel("Tipo:");
		GridBagConstraints labelGbc_1 = new GridBagConstraints();
		labelGbc_1.insets = new Insets(5, 5, 5, 5);
		labelGbc_1.gridx = 0;
		labelGbc_1.gridy = 2;
		formPane.add(tipoLabel, labelGbc_1);

		enumSetContainer = EnumSetUtils.createEnumSetContainer(CentroCustoTipo.class);
		GridBagConstraints gbc_enumSetContainer = new GridBagConstraints();
		gbc_enumSetContainer.insets = new Insets(0, 0, 5, 0);
		gbc_enumSetContainer.gridx = 1;
		gbc_enumSetContainer.gridy = 2;
		formPane.add(enumSetContainer, gbc_enumSetContainer);

		JLabel ativoLabel = new JLabel("Ativo:");
		GridBagConstraints labelGbc_2 = new GridBagConstraints();
		labelGbc_2.fill = GridBagConstraints.VERTICAL;
		labelGbc_2.insets = new Insets(5, 5, 0, 5);
		labelGbc_2.gridx = 0;
		labelGbc_2.gridy = 3;
		formPane.add(ativoLabel, labelGbc_2);

		ativoJCheckBox = new JCheckBox();
		GridBagConstraints componentGbc_2 = new GridBagConstraints();
		componentGbc_2.insets = new Insets(5, 0, 0, 0);
		componentGbc_2.fill = GridBagConstraints.BOTH;
		componentGbc_2.gridx = 1;
		componentGbc_2.gridy = 3;
		formPane.add(ativoJCheckBox, componentGbc_2);

	}

	public JTextField getNomeJTextField() {
		return nomeJTextField;
	}

	public JCheckBox getAtivoJCheckBox() {
		return ativoJCheckBox;
	}

	public EnumSetUtils<CentroCustoTipo> getEnumSetContainer() {
		return enumSetContainer;
	}
	
}