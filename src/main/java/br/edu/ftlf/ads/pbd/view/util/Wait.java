package br.edu.ftlf.ads.pbd.view.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Wait extends JDialog {

	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar;

	/**
	 * Create the dialog.
	 */
	public Wait() {		
		setTitle("Por favor aguarde...");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(new Dimension(400, 80));
		getContentPane().setLayout(new BorderLayout());
		Dimension paneSize = getSize();  
        Dimension screenSize = getToolkit().getScreenSize().getSize();  
		setLocation((screenSize.width - paneSize.width) / 2, 
					(screenSize.height - paneSize.height) / 2);
		
		progressBar = new JProgressBar();
		progressBar.setValue(50);
		progressBar.setBounds(10, 30, 372, 25);
		progressBar.setIndeterminate(true);
		progressBar.setForeground(Color.BLUE);
		getContentPane().add(progressBar, BorderLayout.CENTER);
		
		getContentPane().add(new JPanel(), BorderLayout.NORTH);
		getContentPane().add(new JPanel(), BorderLayout.EAST);
		getContentPane().add(new JPanel(), BorderLayout.WEST);
		getContentPane().add(new JPanel(), BorderLayout.SOUTH);
		
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		
		setAlwaysOnTop(true);
	}
	
	public static void main(String[] args) {
		Wait wait = new Wait();
		wait.setVisible(true);
	}
}
