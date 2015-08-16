package br.edu.ftlf.ads.pbd.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;

import br.edu.ftlf.ads.pbd.bean.FormasPagamento;
import br.edu.ftlf.ads.pbd.view.grid.Grid;
import br.edu.ftlf.ads.pbd.view.menu.CrudContextMenu;
import br.edu.ftlf.ads.pbd.view.toolbar.CrudToolbar;
import br.edu.ftlf.ads.pbd.view.util.UIHelper;

public class FormasPagamentoFrame extends JInternalFrame {

	private static final long serialVersionUID = -6112835463443525159L;
	private final Grid<FormasPagamento> grid;
	private final CrudToolbar toolbar;
	private final CrudContextMenu contextMenu;

	/**
	 * Create the frame.
	 */
	public FormasPagamentoFrame() {
		
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("Cadastro de FormasPagamentos");
		setResizable(true);
		setMinimumSize(new Dimension(600, 500));
		setSize(new Dimension(600, 500));
		setPreferredSize(new Dimension(600, 500));

		grid = Grid.create(FormasPagamento.class, FormasPagamentoGridModel.class);
		Font font = new Font("Tahoma", Font.BOLD, 12);
		grid.setFont(font);
		grid.setFlexWidth(getSize().width);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(grid);

		contextMenu = new CrudContextMenu();
		
		UIHelper.addPopup(grid, contextMenu);
		UIHelper.addPopup(scrollPane, contextMenu);
		
		JPanel pnlAll = new JPanel();
		setContentPane(pnlAll);
		toolbar = new CrudToolbar().withLoad().withNew().withEdit().withDelete().withClose();
		toolbar.getCloseButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		pnlAll.setLayout(new BorderLayout(5, 5));
		pnlAll.add(toolbar, BorderLayout.NORTH);		
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
	
	public Grid<FormasPagamento> getGrid() {
		return grid;
	}

	public CrudToolbar getToolbar() {
		return toolbar;
	}

	public CrudContextMenu getGridPopUp() {
		return contextMenu;
	}

}
