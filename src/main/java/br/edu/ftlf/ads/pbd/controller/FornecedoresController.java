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

import br.edu.ftlf.ads.pbd.bean.Fornecedor;
import br.edu.ftlf.ads.pbd.repository.IFornecedoresRepository;
import br.edu.ftlf.ads.pbd.view.FornecedorDialog;
import br.edu.ftlf.ads.pbd.view.FornecedorForm;
import br.edu.ftlf.ads.pbd.view.FornecedorFrame;
import br.edu.ftlf.ads.pbd.view.grid.Grid;
import br.edu.ftlf.ads.pbd.view.menu.CrudContextMenu;
import br.edu.ftlf.ads.pbd.view.menu.MenuAction;
import br.edu.ftlf.ads.pbd.view.toolbar.CrudToolbar;
import br.edu.ftlf.ads.pbd.view.util.MessageBox;
import br.edu.ftlf.ads.pbd.view.util.UIHelper;

@Controller
public class FornecedoresController {
	
	@Autowired
	private MDIController mdi;
	
	@Autowired
	private IFornecedoresRepository iFornecedores;

	private FornecedorFrame frame;
	private Grid<Fornecedor> grid;
	
	private void createFornecedorFrame() {
		
		ActionListener addAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				edit(new Fornecedor());
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
		ActionListener loadAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		};
		
		frame = new FornecedorFrame();
		
		CrudToolbar toolbar = frame.getToolbar();
		toolbar.getNewButton().addActionListener(addAction);
		toolbar.getEditButton().addActionListener(editAction);
		toolbar.getDeleteButton().addActionListener(deleteAction);
		toolbar.getLoadButton().addActionListener(loadAction);
		
		CrudContextMenu contextMenu = frame.getContextMenu();
		contextMenu.getNewButton().addActionListener(addAction);
		contextMenu.getEditButton().addActionListener(editAction);
		contextMenu.getDeleteButton().addActionListener(deleteAction);
		
		
		frame.getPagingToolBar().setRepository(iFornecedores);
		
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
	
	public FornecedorForm edit(Fornecedor cliente) {
		FornecedorDialog dlg = createForm(cliente);
		dlg.setTitle(cliente.isNew() ? "Adicionar Fornecedor" : "Editar Fornecedor");
		return dlg.showWindow();
	}

	private FornecedorDialog createForm(final Fornecedor cliente) {
		final FornecedorDialog dialog = new FornecedorDialog();
		dialog.getForm().setModel((Fornecedor) cliente.clone());
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
	private void save(FornecedorDialog dialog) {
		FornecedorForm form = dialog.getForm();
		UIHelper.waitCursorIn(form);
		try {
			form.validateModel();
			iFornecedores.saveAndFlush(form.getModel());
			refresh();
			if (dialog.getContinueCheckBox().isSelected()) {
				form.setModel(new Fornecedor());
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
				grid.setItems(iFornecedores.findAll());
			}
		});
	}
	
	public void delete(Fornecedor cliente) {
		boolean confirm = MessageBox.showQuestion(frame, "Tem certeza que deseja excluir este registro?", "Operacao de Exclusao");
		if (confirm) {
			try {
				iFornecedores.delete(cliente);
				MessageBox.showMessage(frame, "Fornecedor removido com sucesso!", "Excluindo dados...");
				refresh();
			} catch (Exception e) {
				MessageBox.showError(frame, "Falha", "Operacao de exclusao falhou!", e);
			}
		}
	}
	
	public FornecedorFrame getList() {
		if (frame == null)
			createFornecedorFrame();
		return frame;
	}
	
	@MenuAction(text = "Fornecedores", root = "cadastro", mnemonic = KeyEvent.VK_F)
	public void list() {
		mdi.showWindow(getList());
	}
	
}
