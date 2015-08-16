package br.edu.ftlf.ads.pbd.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;

import br.edu.ftlf.ads.pbd.bean.Cliente;
import br.edu.ftlf.ads.pbd.view.grid.Grid;
import br.edu.ftlf.ads.pbd.view.menu.CrudContextMenu;
import br.edu.ftlf.ads.pbd.view.toolbar.CrudToolbar;
import br.edu.ftlf.ads.pbd.view.toolbar.PagingToolBar;
import br.edu.ftlf.ads.pbd.view.util.UIHelper;

public class ClienteFrame extends JInternalFrame {

	private static final long serialVersionUID = -6112835463443525159L;
	private final Grid<Cliente> grid;
	private final CrudToolbar toolbar;
	private PagingToolBar pagingToolBar;
	private CrudContextMenu contextMenu;

	/**
	 * Create the frame.
	 */
	public ClienteFrame() {
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("Clientes");
		setResizable(true);
		setMinimumSize(new Dimension(800, 540));
		setSize(new Dimension(800, 540));
		setPreferredSize(new Dimension(800, 540));

		grid = Grid.create(Cliente.class, ClienteGridModel.class);
		Font font = new Font("Tahoma", Font.BOLD, 12);
		grid.setFont(font);		
		grid.setFlexWidth(getSize().width);	
		pagingToolBar = new PagingToolBar(grid);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(grid);
		contextMenu = new CrudContextMenu();

		UIHelper.addPopup(scrollPane, contextMenu);
		UIHelper.addPopup(grid, contextMenu);
		
		JPanel pnlAll = new JPanel();
		setContentPane(pnlAll);
		CrudToolbar crudToolbar = new CrudToolbar();
		toolbar = crudToolbar.withLoad().withNew().withEdit().withDelete().withClose();
		toolbar.getCloseButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		pnlAll.setLayout(new BorderLayout(5, 5));
		pnlAll.add(toolbar, BorderLayout.SOUTH);		
		pnlAll.add(scrollPane, BorderLayout.CENTER);
		pnlAll.add(new Container(), BorderLayout.NORTH);
		pnlAll.add(new Container(), BorderLayout.WEST);
		pnlAll.add(new Container(), BorderLayout.EAST);

		//Abilita botoes se alguma linha na tabela for selecionada
		ELProperty<Object, Object> sourceProperty = ELProperty.create("${selectedElement != null}");
		ELProperty<Object, Object> targetProperty = ELProperty.create("${enabled}");
		
		Bindings
		.createAutoBinding(UpdateStrategy.READ, grid, sourceProperty, toolbar.getEditButton(), targetProperty)
		.bind();
		
		Bindings
		.createAutoBinding(UpdateStrategy.READ, grid, sourceProperty, toolbar.getDeleteButton(), targetProperty)
		.bind();
	}
	
	public Grid<Cliente> getGrid() {
		return grid;
	}

	public CrudToolbar getToolbar() {
		return toolbar;
	}

	public PagingToolBar getPagingToolBar() {
		return pagingToolBar;
	}

	public CrudContextMenu getContextMenu() {
		return contextMenu;
	}
	
	public static void main(String[] args) {
		
		final ClienteFrame frame = new ClienteFrame();
		frame.setVisible(true);
		
		JFrame jFrame = new JFrame();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jFrame.add(frame);
		jFrame.setVisible(true);
		
		frame.getToolbar().getEditButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { 
				final ClienteDialog dialog = new ClienteDialog();
				final int row = frame.getGrid().getSelectedRow();
				
				Cliente cliente = (Cliente)frame.getGrid().getSelectedItem().clone();
				dialog.getForm().setModel(cliente);
				
				dialog.getToolbar().getOkButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Cliente selected = dialog.getForm().getModel();
						frame.getGrid().setItem(row, selected); 
						dialog.setVisible(false);
					}
				});
				dialog.showWindow();
			}
		});
				
		for (int i = 0; i < 10000; i++) {
			Cliente cliente = new Cliente();
			cliente.setRazaoSocial(String.format("Teste %s", i));
			cliente.setCpfCnpj("00.000.000/0001-99");
			cliente.setCelular("9898808302");
			cliente.setFone("89894384938");
			cliente.setEmail("viniciusdecarvalho.tec@gmail.com");
			cliente.setAtivo((i % 2 == 0) || (i % 3 == 0) || (i % 5 == 0));
			frame.getGrid().addItem(cliente);		
			frame.setTitle(String.format("Clientes - %d", (int) (i * 100/10000) + 1));
			frame.repaint();
		}
		
	}
}
