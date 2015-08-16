package br.edu.ftlf.ads.pbd.view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatterFactory;

import br.edu.ftlf.ads.pbd.bean.Enums.FornecedorTipo;
import br.edu.ftlf.ads.pbd.bean.Fornecedor;
import br.edu.ftlf.ads.pbd.view.field.RadioButtonUtils;
import br.edu.ftlf.ads.pbd.view.field.RadioButtonUtils.Orientation;
import br.edu.ftlf.ads.pbd.view.form.Form;
import br.edu.ftlf.ads.pbd.view.form.bind.Bind;
import br.edu.ftlf.ads.pbd.view.form.bind.Binds;
import br.edu.ftlf.ads.pbd.view.mask.CnpjMask;
import br.edu.ftlf.ads.pbd.view.mask.CpfMask;

public class FornecedorForm extends Form<Fornecedor> {

	private static final long serialVersionUID = -3420355203480373201L;

	@Bind(property = "razaoSocial", expression = "text")
	private JTextField razaoSocialJTextField;

	@Binds({ @Bind(property = "cpfCnpj", expression = "value"),
			@Bind(property = "cpfCnpj ne ''", expression = "enabled") })
	private JFormattedTextField cpfCnpjJTextField;

	@Bind(property = "ativo", expression = "selected")
	private JCheckBox ativoJCheckBox;

	@Binds({
			@Bind(property = "tipo eq 'FISICA'", expression = "group.selection.selected"),
			@Bind(property = "tipo eq 'JURIDICA'", expression = "group.selection.selected") })
	private Container tiposGroup;

	/**
	 * Create the panel.
	 */
	public FornecedorForm() {
		setLayout(null);

		razaoSocialJTextField = new JTextField();
		razaoSocialJTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		razaoSocialJTextField.setBounds(10, 31, 520, 20);
		add(razaoSocialJTextField);

		JLabel razaoSocialJLabel = new JLabel("RazaoSocial:");
		razaoSocialJLabel.setLabelFor(razaoSocialJTextField);
		razaoSocialJLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		razaoSocialJLabel.setBounds(10, 14, 85, 14);
		add(razaoSocialJLabel);

		final JLabel cpfCnpjJLabel = new JLabel("Cpf/Cnpj:");
		cpfCnpjJLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		cpfCnpjJLabel.setBounds(204, 62, 61, 14);
		add(cpfCnpjJLabel);

		cpfCnpjJTextField = new JFormattedTextField();
		cpfCnpjJLabel.setLabelFor(cpfCnpjJTextField);
		cpfCnpjJTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		cpfCnpjJTextField.setBounds(203, 81, 144, 20);
		add(cpfCnpjJTextField);

		ativoJCheckBox = new JCheckBox();
		ativoJCheckBox.setFont(new Font("Dialog", Font.BOLD, 12));
		ativoJCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
		ativoJCheckBox.setText("Ativo");
		ativoJCheckBox.setBounds(353, 81, 53, 20);
		add(ativoJCheckBox);

		tiposGroup = RadioButtonUtils.createRadioButtonGrouping(
				FornecedorTipo.values(), Orientation.HORIZONTAL, "Tipo", null,
				new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						AbstractButton button = (AbstractButton) e.getSource();
						cpfCnpjMask(FornecedorTipo.valueOf(button.getText()));
					}
				}, null);
		tiposGroup.setBounds(10, 55, 180, 50);
		add(tiposGroup);

	}

	private void cpfCnpjMask(FornecedorTipo tipo) {
		String text = cpfCnpjJTextField.getText();
		if (tipo == FornecedorTipo.FISICA) {
			cpfCnpjJTextField.setFormatterFactory(new DefaultFormatterFactory(
					new CpfMask()));
		}
		if (tipo == FornecedorTipo.JURIDICA) {
			cpfCnpjJTextField.setFormatterFactory(new DefaultFormatterFactory(
					new CnpjMask()));
		}
		cpfCnpjJTextField.setText(text);
		cpfCnpjJTextField.setEnabled(true);
	}
}
