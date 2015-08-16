package br.edu.ftlf.ads.pbd.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

public class SkinMenu {

	private static final LookAndFeelInfo[] lookAndFeels = UIManager.getInstalledLookAndFeels();
	private static final Logger LOG = Logger.getLogger(SkinMenu.class);
	
	public static JMenu buildSkinMenu(ActionListener listener) {
		JMenu menu = new JMenu("Skins");
		for (JMenuItem item : buildSkinMenuItens()) {
			item.addActionListener(listener);
			menu.add(item);
		}
		return menu;
	}

	private static JMenuItem[] buildSkinMenuItens() {
		String lookAndFell = UIManager.getLookAndFeel().getName();
		ButtonGroup group = new ButtonGroup();
		JMenuItem[] menus = new JMenuItem[lookAndFeels.length];
		for (int i = 0; i < lookAndFeels.length; i++) {
			JRadioButtonMenuItem item = new JRadioButtonMenuItem(lookAndFeels[i].getName());			
			item.setSelected(lookAndFell.equals(lookAndFeels[i].getName()));
			item.addActionListener(new SkinListener(i));
			menus[i] = item;
			group.add(item);
		}
		return menus;
	}
	
	private static class SkinListener implements ActionListener {
		
		private int selectedSkin;

		public SkinListener(int selectedSkin) {
			this.selectedSkin = selectedSkin;
		}
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			String current = UIManager.getLookAndFeel().getName();			
			String selected = lookAndFeels[selectedSkin].getName();
			try {
				if (!current.equals(selected)) {
					UIManager.setLookAndFeel(lookAndFeels[selectedSkin].getClassName());
					LOG.info(String.format("update lookandFell from: %s to: %s", 
							current, selected));
				}
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e) {
				LOG.info(String.format("update lookandFell from: %s to: %s fail", 
							current, selected));
				e.printStackTrace();
			}
		}
	}
}

