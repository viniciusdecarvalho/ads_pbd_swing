package br.edu.ftlf.ads.pbd.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.edu.ftlf.ads.pbd.bean.UserInfo;
import br.edu.ftlf.ads.pbd.bean.Usuario;
import br.edu.ftlf.ads.pbd.main.App;
import br.edu.ftlf.ads.pbd.repository.IUsuariosRepository;
import br.edu.ftlf.ads.pbd.view.LoginWindow;
import br.edu.ftlf.ads.pbd.view.util.MessageBox;
import br.edu.ftlf.ads.pbd.view.util.UIHelper;

@Controller
public class LoginController {
	
	@Autowired
	private IUsuariosRepository iUsuarios;
	
	@Autowired
	private MDIController mdi;
	
	@Autowired
	private UserInfo usuarioInfo;

	private LoginWindow form;
	
	private void createLoginFrame() {
		
		createDefaultUser();
		
		form = new LoginWindow();
		form.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				close();
			}
		});
		form.getLogarButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UIHelper.waitCursorIn(form);
				login();
				UIHelper.defaultCursorIn(form);
			}
		});
		form.getFecharButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
	}
	
	private void login() {
		String login = form.getLoginTextField().getText().trim();
		String password = String.valueOf(form.getSenhaTextField().getPassword());
		usuarioInfo.setUsuario(iUsuarios.login(login, password));
		if (!usuarioInfo.isLogged()) {
			MessageBox.showError(form, "Usuario invalido. Login e/ou senha invalidos.");
			throw new  IllegalStateException();
		}
		App.setUserInfo(usuarioInfo);
		form.setVisible(false);
		mdi.initComponents();
		mdi.getMdiWindow().setVisible(true);
	}
	
	public LoginWindow getFormLogin() {
		if (form == null)
			createLoginFrame();
		return form;
	}
	
	private void close() {
		System.exit(0);
	}
	
	private void createDefaultUser() {
		Usuario admin = iUsuarios.findByLogin("admin");
		if (admin == null) {
			admin = new Usuario();
			admin.setNome("admin");
			admin.setLogin("admin");
			admin.setCpf("00000000000");
			admin.setAtivo(true);
			admin.setSenha("admin");
			iUsuarios.saveAndFlush(admin);
		}		
	}
}
