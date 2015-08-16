package br.edu.ftlf.ads.pbd.controller;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import br.edu.ftlf.ads.pbd.view.MDIFrame;
import br.edu.ftlf.ads.pbd.view.menu.MenuFactory;
import br.edu.ftlf.ads.pbd.view.menu.MenuFactory.Action;
import br.edu.ftlf.ads.pbd.view.util.UIHelper;

@Component
public class MDIController {

	private static String lookAndFell = "";
	
	@Autowired
	private ApplicationContext applicationContext;
	
	private MDIFrame mdi;
	private Map<String, JMenu> menus;
	
	public void initComponents() {
		mdi = new MDIFrame();
		menus = new HashMap<String, JMenu>();
		buildMenu();
	}

	private void buildMenu() {			
		buildRootMenu();
		Font font = new Font("SansSerif", Font.PLAIN, 12);
		MenuFactory menuFactory = new MenuFactory(applicationContext);
		Map<String, Action> actions = menuFactory.getActions();
		Iterator<String> it = actions.keySet().iterator();
		while (it.hasNext()) {
			String actionKey = it.next();
			String[] keys = actionKey.split("\\.");
			Action action = actions.get(actionKey);
			if (keys.length > 1) {
				JMenu jMenu = menus.get(keys[0]);
				JMenuItem mItem = new JMenuItem(action.getText());
				mItem.setActionCommand(actionKey);
				mItem.setMnemonic(action.getMnemonic());
				mItem.setFont(font);
				mItem.addActionListener(action.getListener());
				jMenu.add(mItem);
			}
		}
	}
	
	private void buildRootMenu() {
		Font font = new Font("SansSerif", Font.BOLD, 12);
		
		JMenu mnCadastrar = new JMenu("Cadastro");
		mnCadastrar.setActionCommand("cadastro");
		mnCadastrar.setMnemonic(KeyEvent.VK_C);
		mnCadastrar.setFont(font);
		menus.put("cadastro", mnCadastrar);
		mdi.getJMenuBar().add(mnCadastrar);
		
		JMenu mnFinanceiro = new JMenu("Financeiro");
		mnCadastrar.setActionCommand("financeiro");
		mnFinanceiro.setFont(font);
		menus.put("financeiro", mnFinanceiro);
		mdi.getJMenuBar().add(mnFinanceiro);
		
		JMenu mnRelatorio = new JMenu("Relatorio");
		mnCadastrar.setActionCommand("relatorio");
		mnRelatorio.setFont(font);
		menus.put("relatorio", mnRelatorio);
		mdi.getJMenuBar().add(mnRelatorio);
	}
	
//	@PostConstruct
//	private void init() {
//		mdi = new MDIFrame();
//		
//		mdi.getMenus().get("cadastro.banco")
//		.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				showWindow(bancosController.getList());
//			}
//		});
//		mdi.getMenus().get("cadastro.centrocusto")
//		.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				showWindow(centroCustoController.getList());
//			}
//		});
//		mdi.getMenus().get("cadastro.formaPagamento")
//		.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				showWindow(formaPagamentoController.getList());
//			}
//		});
//		mdi.getMenus().get("cadatro.cliente")
//		.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				showWindow(clientesController.getList());
//			}
//		});
//		mdi.getMenus().get("cadastro.fornecedor")
//		.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				showWindow(fornecedoresController.getList());
//			}
//		});
//	}

	public MDIFrame getMdiWindow() {
		SwingUtilities.updateComponentTreeUI(mdi);
		return mdi;
	}

	public void showWindow(final JInternalFrame frame) {
		UIHelper.waitCursorIn(mdi);
		
		if (!lookAndFell.equals(UIManager.getLookAndFeel().getName())) {
	    	SwingUtilities.updateComponentTreeUI(mdi);
	    	lookAndFell = UIManager.getLookAndFeel().getName();
	    }
		
		int indexOf = mdi.getDesktopPane().getIndexOf(frame);
		if (indexOf == -1) {
			mdi.getDesktopPane().add(frame);
			frame.pack();
		}
		mdi.getDesktopPane().moveToFront(frame);
		frame.setFrameIcon(new ImageIcon(mdi.getIconImage()));
		frame.setVisible(true);		
		frame.requestFocus();
		UIHelper.centerOnScreen(frame);

		UIHelper.defaultCursorIn(mdi);
	}

}
