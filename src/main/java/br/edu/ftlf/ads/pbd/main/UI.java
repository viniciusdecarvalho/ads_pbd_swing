package br.edu.ftlf.ads.pbd.main;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ftlf.ads.pbd.bean.UserInfo;
import br.edu.ftlf.ads.pbd.controller.LoginController;
import br.edu.ftlf.ads.pbd.view.SplashWindow;

@Component
public class UI {

	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	private static UserInfo usuarioInfo;
	private static SplashWindow splash = new SplashWindow();
	
	@Autowired
	private LoginController login;
	
	public void start() {
		logger.info("init UI app.");
		login.getFormLogin().setVisible(true);		
	}
	
	public static void setLookAndFell() {
		try {
			String lookAndFell = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(lookAndFell);
			
			UIManager.put("Table.selectionBackground", Color.blue);
			UIManager.put("Table.focusCellHighlightBorder", Color.lightGray);
			
			logger.info("look and fell modified to {}", lookAndFell);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			logger.info("System LookAndFeel nao suportado.");
		}
	}
	
	public static void showSplash() {
		splash.setVisible(true);
		logger.info("show splash window.");
	}
	
	public static void disposeSplash() {
		splash.setVisible(false);
		logger.info("dispose splash window.");
	}

	public static UserInfo getUsuarioInfo() {
		return usuarioInfo;
	}
	
	public static void setUsuarioInfo(UserInfo usuarioInfo) {
		UI.usuarioInfo = usuarioInfo;
	}
	
}
