package br.edu.ftlf.ads.pbd.view.util;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.error.ErrorInfo;

public class MessageBox extends JOptionPane {

	private static final long serialVersionUID = 4484009451762971056L;

	private MessageBox() {}
	
	private MessageBox(Object message, int messageType, int optionType) {
		super(message, messageType, optionType);
	}
	
	/**
	 * Exibe uma mensagem simples
	 * @param message
	 * @param title
	 */
	public static void showMessage(final String message, final String title) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showMessageDialog(null, message, title, INFORMATION_MESSAGE);
			}
		});
	}

	/**
	 * Exibe uma mensagem simples
	 * @param message
	 * @param title
	 */
	public static void showMessage(final Component component, final String message, final String title) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showMessageDialog(component, message, title, INFORMATION_MESSAGE);
			}
		});
	}
	
	/**
	 * Exibe uma caixa de mensagem esperando retorno sim ou não
	 * @param message
	 * @param title
	 * @return JOptionPane.YES_OPTION ou JOptionPane.NO_OPTION
	 */
	public static boolean showQuestion(String message, String title) {
		return showConfirmDialog(null, message, title, YES_NO_OPTION, QUESTION_MESSAGE) == YES_OPTION;
	}
	
	/**
	 * Exibe uma caixa de mensagem esperando retorno sim ou não
	 * @param message
	 * @param title
	 * @return JOptionPane.YES_OPTION ou JOptionPane.NO_OPTION
	 */
	public static boolean showQuestion(Component component, String message, String title) {
		return showConfirmDialog(component, message, title, YES_NO_OPTION, QUESTION_MESSAGE) == YES_OPTION;
	}

	/**
	 * Mostra caixa de mensagem esperado uma resposta com String
	 * @param message
	 * @return String informado
	 */
	public static String showInput(String message) {
		return showInputDialog(null, message);
	}
	
	/**
	 * Mostra caixa de mensagem esperado uma resposta com String
	 * @param message
	 * @return String informado
	 */
	public static String showInput(Component component, String message) {
		return showInputDialog(component, message);
	}

	/**
	 * Mostra mensagem de Erro
	 * @param message
	 */
	public static void showError(final String message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showMessageDialog(null, message, "Erro.", ERROR_MESSAGE);
			}
		});		
	}
	
	/**
	 * Mostra mensagem de Erro
	 * @param message
	 */
	public static void showError(final Exception exception) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JXErrorPane.showDialog(exception);
			}
		});		
	}
	
	/**
	 * Mostra mensagem de Erro
	 * @param message
	 */
	public static void showError(final Component component, final String message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showMessageDialog(component, message, "Erro.", ERROR_MESSAGE);
			}
		});		
	}
	
	/**
	 * Mostra mensagem de Erro
	 * @param message
	 */
	public static void showError(final Component component, final String title, final String message, final Exception exception) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ErrorInfo errorInfo = new ErrorInfo(title, message, null, exception.getMessage(), exception, Level.SEVERE, null);
				JXErrorPane.showDialog(component,errorInfo);
			}
		});		
	}
	
	@Override
	public JDialog createDialog(Component parentComponent, String title)
			throws HeadlessException {
		final JDialog dialog = super.createDialog(parentComponent, title);
		dialog.addWindowStateListener(new WindowAdapter() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				if (e.getWindow().isVisible()) 
					fadeIn(dialog);
				else
					fadeOut(dialog);
			}
		});
		return dialog;
	}
	
	
	/**
	 * Creates an animation to fade the dialog opacity from 0 to 1.
	 */
	private static void fadeIn(final JDialog dialog) {
		final Timer timer = new Timer(10, null);
		timer.setRepeats(true);
		timer.addActionListener(new ActionListener() {
			private float opacity = 0;
			@Override public void actionPerformed(ActionEvent e) {
				opacity += 0.15f;
				dialog.setOpacity(Math.min(opacity, 1));
				if (opacity >= 1) 
					timer.stop();
			}
		});

		dialog.setOpacity(0);
		timer.start();
		dialog.setVisible(true);
	}

	/**
	 * Creates an animation to fade the dialog opacity from 1 to 0.
	 */
	private static void fadeOut(final JDialog dialog) {
		final Timer timer = new Timer(10, null);
		timer.setRepeats(true);
		timer.addActionListener(new ActionListener() {
			private float opacity = 1;
			@Override public void actionPerformed(ActionEvent e) {
				opacity -= 0.15f;
				dialog.setOpacity(Math.max(opacity, 0));
				if (opacity <= 0) {
					timer.stop();
					dialog.dispose();
				}
			}
		});

		dialog.setOpacity(1);
		timer.start();
	}
	
}
