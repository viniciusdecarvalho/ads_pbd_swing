package br.edu.ftlf.ads.pbd.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

public class SplashWindow extends JWindow {

	private static final long serialVersionUID = 1L;
	private static final String IMG_PATH = "/br/edu/ftlf/ads/pbd/images/splash/splash_small.jpg";

	private JLabel jLabelSplashImage;
	private JLabel jLabelTextoCarregamento;
	private static JLabel jLabelTextoDinamicoPlugins;
	private static JProgressBar jProgressBarSistema;

	public SplashWindow() {
		initComponents();
	}

	private void initComponents() {
		/**
		 * Inicializando as variavaeis utilizadas
		 */
		jProgressBarSistema = new JProgressBar();
		jLabelSplashImage = new JLabel();
		jLabelTextoCarregamento = new JLabel();
		jLabelTextoDinamicoPlugins = new JLabel();
		/**
		 * Carregando a imagem do Splash e adicionando a imagem ao componente
		 * jLabelSplashImage
		 */
		ImageIcon imageIcon = new ImageIcon(getClass().getResource(IMG_PATH));
		jLabelSplashImage.setIcon(imageIcon);
		/**
		 * Definindo dinamicamente o tamando do container segundo o tamanho da
		 * imagem.
		 */
		setMinimumSize(new Dimension(imageIcon.getIconWidth(),
				imageIcon.getIconHeight()));
		jLabelSplashImage.setBounds(0, 0, imageIcon.getIconWidth(),
				imageIcon.getIconHeight());
		/**
		 * A definicao do layout=null e importante para possibilitar que os
		 * componentes fiquem sobrescritros em tempo de execucao
		 */
		getContentPane().setLayout(null);
		/**
		 * Definindo a localizacao do splash no centro da tela
		 */
		setLocationRelativeTo(null);

		/**
		 * Setando parametros da variavel jProgressBarSistema
		 */
		jProgressBarSistema.setForeground(Color.BLUE);
		jProgressBarSistema.setPreferredSize(new Dimension(getSize().width, 5));
		jProgressBarSistema.setBounds(0, 200, 500, 10);
		jProgressBarSistema.setBorderPainted(false);
		jProgressBarSistema.setIndeterminate(true);
		/**
		 * Adicionando o jProgressBarSistema a classe SplashJProgressBar
		 */
		getContentPane().add(jProgressBarSistema);

		/**
		 * Setando parametros da variavel jProgressBarSistema
		 */
		jLabelTextoCarregamento.setForeground(new Color(0, 0, 204));
		jLabelTextoCarregamento.setFont(new Font("DialogInput", Font.BOLD, 12));
		jLabelTextoCarregamento.setBounds(110, 220, 100, 20);
		jLabelTextoCarregamento.setText("Carregando...");

		/**
		 * Adicionando o jProgressBarSistema a classe SplashJProgressBar
		 */
		getContentPane().add(jLabelTextoCarregamento);

		/**
		 * Setando parametros da variavel jProgressBarSistema
		 */
		jLabelTextoDinamicoPlugins.setForeground(new Color(0, 0, 204));
		jLabelTextoDinamicoPlugins.setFont(new Font("DialogInput", 0, 11));
		jLabelTextoDinamicoPlugins.setBounds(360, 285, 230, 20);
		/**
		 * Adicionando o jProgressBarSistema a classe SplashJProgressBar
		 */
		getContentPane().add(jLabelTextoDinamicoPlugins);

		/**
		 * O Ultimo item adicionado no conteiner deve ser o componente que
		 * comtem a imagem do Splah
		 */
		getContentPane().add(jLabelSplashImage);
		pack();
		setVisible(true);
//		setAlwaysOnTop(true);
	}

	public static void main(String args[]) {

		new SplashWindow().setVisible(true);

		/**
		 * Rotina para exibicao de um texto qualquer no carregamento do seu
		 * sistema
		 */
		int i = 10;
		for (int j = 1; j <= 1000; j++) {

			if (j == (1000 / i)) {

				// jLabelTextoDinamicoPlugins.setText("Exibicao Texto Qualquer: "
				// + i);
				i--;
				try {
					Thread.sleep(1200);
				} catch (Exception e) {

				}

			}

		}

		System.exit(0);
	}

}
