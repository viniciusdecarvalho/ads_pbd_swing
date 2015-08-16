package br.edu.ftlf.ads.pbd.view;

import java.awt.Color;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class MDIFrame extends JFrame {

	private static final long serialVersionUID = 5895778974834037120L;
	private final JDesktopPane desktop = new JDesktopPane();
	private final Map<String, AbstractButton> menus = new HashMap<String, AbstractButton>();

	/**
	 * Create the frame.
	 */
	public MDIFrame() {

		setExtendedState(Frame.MAXIMIZED_BOTH);
		setName("mdi");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
						MDIFrame.class.getResource("/br/edu/ftlf/ads/pbd/images/x16/pause.png")));
		setBackground(SystemColor.inactiveCaptionBorder);
		setTitle("SysGATE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(new JMenuBar());

//		JMenu mnCadastrar = new JMenu("Cadastro");
//		mnCadastrar.setActionCommand("cadastro");
//		Font font = new Font("SansSerif", Font.BOLD, 12);
//		mnCadastrar.setFont(font);
//		getJMenuBar().add(mnCadastrar);
//
//		JMenuItem mntmCliente = new JMenuItem("Cliente");
//		mntmCliente.setActionCommand("cadatro.cliente");
//		menus.put("cadatro.cliente", mntmCliente);
//		mntmCliente.setMnemonic(KeyEvent.VK_C);
//		mntmCliente.setIcon(new ImageIcon(
//						MDIFrame.class.getResource("/br/edu/ftlf/ads/pbd/images/x16/window.png")));
//		mntmCliente.setFont(font);
//
//		mnCadastrar.add(mntmCliente);
//
//		JMenuItem mntmFornecedor = new JMenuItem("Fornecedor");
//		mntmFornecedor.setActionCommand("cadastro.fornecedor");
//		menus.put("cadastro.fornecedor", mntmFornecedor);
//		mntmFornecedor.setMnemonic(KeyEvent.VK_C);
//		mntmFornecedor.setIcon(new ImageIcon(
//						MDIFrame.class.getResource("/br/edu/ftlf/ads/pbd/images/x16/window.png")));
//		mntmFornecedor.setFont(font);
//
//		mnCadastrar.add(mntmFornecedor);
//
//		JMenuItem mntmBanco = new JMenuItem("Banco");
//		mntmBanco.setFont(new Font("Tahoma", Font.BOLD, 12));
//		mnCadastrar.add(mntmBanco);
//		menus.put("cadastro.banco", mntmBanco);
//
//		JMenuItem mntmCentroCusto = new JMenuItem("Centro de Custo");
//		mntmCentroCusto.setFont(new Font("Tahoma", Font.BOLD, 12));
//		mnCadastrar.add(mntmCentroCusto);
//		menus.put("cadastro.centrocusto", mntmCentroCusto);
//		
//		JMenuItem mntmFormaPagamento = new JMenuItem("Formas de Pagamento");
//		mntmFormaPagamento.setFont(new Font("Tahoma", Font.BOLD, 12));
//		mnCadastrar.add(mntmFormaPagamento);
//		menus.put("cadastro.formaPagamento", mntmFormaPagamento);
//
//		JMenu mnFinanceiro = new JMenu("Financeiro");
//		mnFinanceiro.setFont(font);
//		getJMenuBar().add(mnFinanceiro);
//
//		JMenu mnRelatorio = new JMenu("Relatorio");
//		mnRelatorio.setFont(font);
//		getJMenuBar().add(mnRelatorio);

		setContentPane(desktop);
		desktop.setSize(getSize());
		desktop.setPreferredSize(getSize());
		desktop.setBackground(new Color(230, 230, 250));
		desktop.setDragMode(JDesktopPane.FRAME_CONTENT_LAYER);

	}

	public JDesktopPane getDesktopPane() {
		return desktop;
	}

	public Map<String, AbstractButton> getMenus() {
		return menus;
	}
	
}
