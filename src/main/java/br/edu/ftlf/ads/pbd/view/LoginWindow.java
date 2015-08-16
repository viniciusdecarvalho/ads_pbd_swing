package br.edu.ftlf.ads.pbd.view;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

public class LoginWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField loginTextField;
	private JPasswordField senhaTextField;
	private JButton logarButton;
	private JButton cancelarButton;

	public LoginWindow(){
		setTitle("Autenticacao");
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 359, 208);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(10, 11, 25, 14);
		panel.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 67, 30, 14);
		panel.add(lblSenha);
		
		loginTextField = new JTextField();
		loginTextField.setBounds(10, 31, 323, 25);
		panel.add(loginTextField);
		loginTextField.setColumns(10);
		
		senhaTextField = new JPasswordField();
		senhaTextField.setBounds(10, 87, 323, 25);
		panel.add(senhaTextField);
		
//		menuBar = new JMenuBar();						
//		menuBar.add(SkinMenu.buildSkinMenu(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {				
//				updateUI();
//			}
//		}));
//		setJMenuBar(menuBar);
		
		JToolBar toolbar = new JToolBar();
		toolbar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		toolbar.setPreferredSize(new Dimension(25, 30));
		toolbar.setFloatable(false);
		contentPane.add(toolbar, BorderLayout.SOUTH);
		
		logarButton = new JButton("Entrar");
		logarButton.setActionCommand("login");
		logarButton.setIcon(new ImageIcon(LoginWindow.class.getResource("/br/edu/ftlf/ads/pbd/images/x16/lock_off.png")));
		logarButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		toolbar.add(logarButton);
		
		cancelarButton = new JButton("Fechar");
		cancelarButton.setActionCommand("close");
		cancelarButton.setIcon(new ImageIcon(LoginWindow.class.getResource("/br/edu/ftlf/ads/pbd/images/x16/lock.png")));
		cancelarButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		toolbar.add(cancelarButton);
		setLocationRelativeTo(null);
		
		setModal(true);
	}

	public JTextField getLoginTextField() {
		return loginTextField;
	}

	public JPasswordField getSenhaTextField() {
		return senhaTextField;
	}

	public JButton getLogarButton() {
		return logarButton;
	}

	public JButton getFecharButton() {		
		return cancelarButton;
	}
	
}
