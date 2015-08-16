package br.edu.ftlf.ads.pbd.main;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.edu.ftlf.ads.pbd.spring.AutowireHelper;

public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		logger.info("Iniciando sistema.");
		
		Locale.setDefault(new Locale("pt", "BR"));
		UI.setLookAndFell();
		try {
			UI.showSplash();
			logger.info("Carregando contexto do Spring.");
			ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring_jpa-config.xml");
			AutowireHelper.getInstance().setApplicationContext(context);
			UI.disposeSplash();			
			
			logger.info("Iniciando UI do sistema.");
			context.getBean(UI.class).start();
		} catch (Exception e) {
			e.printStackTrace();
			AppLogger.error(e);
			logger.info("Falha na inicializacao do sistema, {}.", e.getMessage());
			UI.disposeSplash();
			showMessageDialog(null, e.getMessage(), "Erro", ERROR_MESSAGE);
			System.exit(0);
		} 
	}

	

}
