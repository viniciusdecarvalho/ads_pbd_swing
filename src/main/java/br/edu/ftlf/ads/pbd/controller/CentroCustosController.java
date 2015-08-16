package br.edu.ftlf.ads.pbd.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ftlf.ads.pbd.bean.CentroCusto;
import br.edu.ftlf.ads.pbd.repository.ICentroCustoRepository;
import br.edu.ftlf.ads.pbd.view.CentroCustoDialog;
import br.edu.ftlf.ads.pbd.view.CentroCustoForm;
import br.edu.ftlf.ads.pbd.view.CentroCustoFrame;
import br.edu.ftlf.ads.pbd.view.grid.Grid;
import br.edu.ftlf.ads.pbd.view.menu.CrudContextMenu;
import br.edu.ftlf.ads.pbd.view.menu.MenuAction;
import br.edu.ftlf.ads.pbd.view.toolbar.CrudToolbar;
import br.edu.ftlf.ads.pbd.view.util.MessageBox;
import br.edu.ftlf.ads.pbd.view.util.UIHelper;

@Controller
public class CentroCustosController {
	
	@Autowired
	private MDIController mdi;
	
	@Autowired
	private ICentroCustoRepository iCentroCustos;

	private CentroCustoFrame frame;
	private Grid<CentroCusto> grid;
	
	private void createCentroCustoFrame() {
		
		ActionListener addAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				edit(new CentroCusto());
			}
		};
		ActionListener editAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				edit(grid.getSelectedItem());
			}
		};
		ActionListener deleteAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delete(grid.getSelectedItem());
			}
		};
//		ActionListener loadAction = new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				refresh();
//			}
//		};
		
		frame = new CentroCustoFrame();
		
		CrudToolbar toolbar = frame.getToolbar();
		toolbar.getNewButton().addActionListener(addAction);
		toolbar.getEditButton().addActionListener(editAction);
		toolbar.getDeleteButton().addActionListener(deleteAction);
		
		CrudContextMenu scrollMenu = frame.getScrollPopUp();
		scrollMenu.getNewButton().addActionListener(addAction);
		scrollMenu.getEditButton().addActionListener(editAction);
		scrollMenu.getDeleteButton().addActionListener(deleteAction);
		
		frame.getPagingToolBar().setRepository(iCentroCustos);
		
		frame.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				refresh();
			}
		});
		
		grid = frame.getGrid();
		grid.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					edit(grid.getSelectedItem());
				}
			}
		});
	}
	
	public CentroCustoForm edit(CentroCusto centroCusto) {
		CentroCustoDialog dlg = createForm(centroCusto);
		dlg.setTitle(centroCusto.isNew() ? "Adicionar Centro de Custo" : "Editar Centro de Custo");
		return dlg.showWindow();
	}

	private CentroCustoDialog createForm(final CentroCusto centroCusto) {
		final CentroCustoDialog dialog = new CentroCustoDialog();
		dialog.getForm().setModel(centroCusto);
		dialog.setLocationRelativeTo(frame);
		dialog.getToolbar().getOkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				save(dialog);
			}
		});
		return dialog;
	}

	@Transactional
	private void save(CentroCustoDialog dialog) {
		CentroCustoForm form = dialog.getForm();
		UIHelper.waitCursorIn(form);
		try {
			form.validateModel();
			iCentroCustos.saveAndFlush(form.getModel());
			refresh();
			if (dialog.getContinueCheckBox().isSelected()) {
				form.setModel(new CentroCusto());
			} else {
				dialog.dispose();
			}
		} catch (Exception e) {
			MessageBox.showError(dialog, e.getMessage());
		}
		UIHelper.defaultCursorIn(form);
	}
	
	public void refresh() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.getPagingToolBar().refresh();
			}
		});
	}
	
	public void delete(CentroCusto centroCusto) {
		boolean confirm = MessageBox.showQuestion(frame, "Tem certeza que deseja excluir este registro?", "Operacao de Exclusao");
		if (confirm) {
			iCentroCustos.delete(centroCusto);
			MessageBox.showMessage(frame, "Centro de Custo removido com sucesso!", "Excluindo dados...");
			grid.removeItem(centroCusto);
		}
	}
	
	public CentroCustoFrame getList() {
		if (frame == null)
			createCentroCustoFrame();
		return frame;
	}

	@MenuAction(text = "Centros de Custo", root = "cadastro", mnemonic = KeyEvent.VK_T)
	public void list() {
		mdi.showWindow(getList());
	}
	
}

