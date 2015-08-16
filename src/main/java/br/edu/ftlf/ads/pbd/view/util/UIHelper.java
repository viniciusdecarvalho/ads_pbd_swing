package br.edu.ftlf.ads.pbd.view.util;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

public class UIHelper {

	private static final Cursor WAIT_CURSOR = new Cursor(Cursor.WAIT_CURSOR);
	private static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);

	private UIHelper() {}
	
	public static void centerOnScreen(Component c) {  
        final Dimension paneSize = c.getSize();  
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        c.setLocation((screenSize.width - paneSize.width) / 2,  
                (screenSize.height - paneSize.height) / 2);  
	} 
	
	public static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	public static void defaultCursorIn(Component component) {
		if (component != null) {
			component.setCursor(DEFAULT_CURSOR);
		}
	}

	public static void waitCursorIn(Component component) {
		if (component != null) {
			component.setCursor(WAIT_CURSOR);
		}
	}		
}
