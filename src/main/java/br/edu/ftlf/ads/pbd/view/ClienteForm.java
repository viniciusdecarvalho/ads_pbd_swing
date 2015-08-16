package br.edu.ftlf.ads.pbd.view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatterFactory;

import org.hibernate.validator.constraints.Email;

import br.edu.ftlf.ads.pbd.bean.Cliente;
import br.edu.ftlf.ads.pbd.bean.Enums.ClienteTipo;
import br.edu.ftlf.ads.pbd.view.field.RadioButtonUtils;
import br.edu.ftlf.ads.pbd.view.field.RadioButtonUtils.Orientation;
import br.edu.ftlf.ads.pbd.view.form.Form;
import br.edu.ftlf.ads.pbd.view.form.bind.Bind;
import br.edu.ftlf.ads.pbd.view.form.bind.Binds;
import br.edu.ftlf.ads.pbd.view.mask.CepMask;
import br.edu.ftlf.ads.pbd.view.mask.CnpjMask;
import br.edu.ftlf.ads.pbd.view.mask.CpfMask;
import br.edu.ftlf.ads.pbd.view.mask.FoneMask;

public class ClienteForm extends Form<Cliente> {
	
	private static final long serialVersionUID = -3420355203480373201L;
	
	@Bind(property = "razaoSocial", expression = "text")
	private JTextField razaoSocialJTextField;
	
	@Binds({
		@Bind(property = "cpfCnpj", expression = "value"),
		@Bind(property = "cpfCnpj ne ''", expression = "enabled")
	})
	private JFormattedTextField cpfCnpjJTextField;
	
	@Bind(property = "ativo", expression = "selected")
	private JCheckBox ativoJCheckBox;
	
	@Bind(property = "cep", expression = "value")
	private JFormattedTextField cepJTextField;
	
	@Bind(property = "estado", expression = "text")
	private JTextField estadoJTextField;
	
	@Bind(property = "cidade", expression = "text")
	private JTextField cidadeJTextField;
	
	@Bind(property = "logradouro", expression = "text")
	private JTextField logradouroJTextField;

	@Bind(property = "complemento", expression = "text")
	private JTextField complementoJTextField;
	
	@Bind(property = "fone", expression = "value")
	private JFormattedTextField foneJTextField;
	
	@Bind(property = "celular", expression = "value")
	private JFormattedTextField celularJTextField;
	
	@Email
	@Bind(property = "email", expression = "text")
	private JTextField emailJTextField;

	@Binds({
		@Bind(property = "tipo eq 'FISICA'", expression = "group.selection.text", strategy="READ"),
		@Bind(property = "tipo eq 'JURIDICA'", expression = "group.selection.text", strategy="READ")
	})
	private Container tiposGroup;

//	@Bind(expression = "tipo eq 'FISICA'", property = "selected")
//	private JRadioButton rdbtnFisica;
//
//	@Bind(expression = "tipo eq 'JURIDICA'", property = "selected")
//	private JRadioButton rdbtnJuridica;

	/**
	 * Create the panel.
	 */
	public ClienteForm() {
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
		cpfCnpjJLabel.setBounds(102, 62, 61, 14);
		add(cpfCnpjJLabel);
		
		cpfCnpjJTextField = new JFormattedTextField();
		cpfCnpjJLabel.setLabelFor(cpfCnpjJTextField);
		cpfCnpjJTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		cpfCnpjJTextField.setBounds(101, 81, 144, 20);
		add(cpfCnpjJTextField);
		
		ativoJCheckBox = new JCheckBox();
		ativoJCheckBox.setFont(new Font("Dialog", Font.BOLD, 12));
		ativoJCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
		ativoJCheckBox.setText("Ativo");
		ativoJCheckBox.setBounds(477, 81, 53, 20);
		add(ativoJCheckBox);
		
		JLabel cepJLabel = new JLabel("Cep:");
		cepJLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		cepJLabel.setBounds(10, 161, 37, 14);
		add(cepJLabel);
		
		cepJTextField = new JFormattedTextField(new CepMask());
		cepJLabel.setLabelFor(cepJTextField);
		cepJTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		cepJTextField.setBounds(10, 180, 74, 20);
		add(cepJTextField);
		
		estadoJTextField = new JTextField();
		estadoJTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		estadoJTextField.setBounds(91, 180, 144, 20);
		add(estadoJTextField);
		
		JLabel estadoJLabel = new JLabel("Estado:");
		estadoJLabel.setLabelFor(estadoJTextField);
		estadoJLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		estadoJLabel.setBounds(91, 161, 62, 14);
		add(estadoJLabel);
		
		cidadeJTextField = new JTextField();
		cidadeJTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		cidadeJTextField.setBounds(249, 180, 281, 20);
		add(cidadeJTextField);
		
		JLabel cidadeJLabel = new JLabel("Cidade:");
		cidadeJLabel.setLabelFor(cidadeJTextField);
		cidadeJLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		cidadeJLabel.setBounds(249, 161, 61, 14);
		add(cidadeJLabel);
		
		JLabel logradouroJLabel = new JLabel("Logradouro:");
		logradouroJLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		logradouroJLabel.setBounds(10, 211, 100, 14);
		add(logradouroJLabel);
		
		logradouroJTextField = new JTextField();
		logradouroJLabel.setLabelFor(logradouroJTextField);
		logradouroJTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		logradouroJTextField.setBounds(10, 229, 520, 20);
		add(logradouroJTextField);
		
		JLabel complementoJLabel = new JLabel("Complemento:");
		complementoJLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		complementoJLabel.setBounds(10, 260, 100, 14);
		add(complementoJLabel);
		
		complementoJTextField = new JTextField();
		complementoJLabel.setLabelFor(complementoJTextField);
		complementoJTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		complementoJTextField.setBounds(10, 278, 520, 20);
		add(complementoJTextField);
		
		JLabel foneJLabel = new JLabel("Fone:");
		foneJLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		foneJLabel.setBounds(255, 62, 59, 14);
		add(foneJLabel);
		
		foneJTextField = new JFormattedTextField(new FoneMask());
		foneJLabel.setLabelFor(foneJTextField);
		foneJTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		foneJTextField.setBounds(255, 81, 105, 20);
		add(foneJTextField);
		
		celularJTextField = new JFormattedTextField(new FoneMask());
		celularJTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		celularJTextField.setBounds(366, 81, 105, 20);
		add(celularJTextField);
		
		JLabel celularJLabel = new JLabel("Celular:");
		celularJLabel.setLabelFor(celularJTextField);
		celularJLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		celularJLabel.setBounds(366, 62, 61, 14);
		add(celularJLabel);
		
		JLabel emailJLabel = new JLabel("Email:");
		emailJLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		emailJLabel.setBounds(10, 112, 85, 14);
		add(emailJLabel);
		
		emailJTextField = new JTextField();
		emailJLabel.setLabelFor(emailJTextField);
		emailJTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		emailJTextField.setBounds(10, 130, 520, 20);
		add(emailJTextField);
		
		tiposGroup = RadioButtonUtils.createRadioButtonGrouping(ClienteTipo.values(), Orientation.VERTICAL, "Tipo", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton button = (AbstractButton) e.getSource();
				ClienteTipo tipo = ClienteTipo.valueOf(button.getText());
				if (tipo != null) {
					String text = cpfCnpjJTextField.getText();
					if (tipo == ClienteTipo.FISICA) {
						cpfCnpjJTextField.setFormatterFactory(new DefaultFormatterFactory(new CpfMask()));
					}
					if (tipo == ClienteTipo.JURIDICA) {
						cpfCnpjJTextField.setFormatterFactory(new DefaultFormatterFactory(new CnpjMask()));
					}
					cpfCnpjJTextField.setText(text);
				}
				cpfCnpjJTextField.setEnabled(tipo != null);
			}
		});
		tiposGroup.setBounds(10, 55, 90, 50);		
		add(tiposGroup);
		
	}
	
	public static void main(String[] args) {
		ClienteForm form = new ClienteForm();
		form.setModel(new Cliente());
		JDialog dialog = new JDialog();
		dialog.getContentPane().add(form);
		dialog.setSize(700, 500);
		dialog.setVisible(true);
	}
}
