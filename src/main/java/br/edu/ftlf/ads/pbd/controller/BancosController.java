package br.edu.ftlf.ads.pbd.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ftlf.ads.pbd.bean.Banco;
import br.edu.ftlf.ads.pbd.repository.IBancosRepository;
import br.edu.ftlf.ads.pbd.repository.Stores;
import br.edu.ftlf.ads.pbd.view.BancoDialog;
import br.edu.ftlf.ads.pbd.view.BancoForm;
import br.edu.ftlf.ads.pbd.view.BancoFrame;
import br.edu.ftlf.ads.pbd.view.grid.Grid;
import br.edu.ftlf.ads.pbd.view.menu.CrudContextMenu;
import br.edu.ftlf.ads.pbd.view.menu.MenuAction;
import br.edu.ftlf.ads.pbd.view.toolbar.CrudToolbar;
import br.edu.ftlf.ads.pbd.view.util.MessageBox;
import br.edu.ftlf.ads.pbd.view.util.UIHelper;

@Controller
public class BancosController {
	
	@Autowired
	private IBancosRepository iBancos;
	
	@Autowired
	private MDIController mdi;
	
	private BancoFrame frame;
	private Grid<Banco> grid;
	
	private Map<String, ActionListener> actions;
	
	public BancosController() {
		
		actions = new HashMap<String, ActionListener>();
		
		actions.put("add", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				edit(new Banco());
			}
		});
		
		actions.put("edit", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				edit(grid.getSelectedItem());
			}
		});
		
		actions.put("delete", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delete(grid.getSelectedItems());
			}
		});
		
		actions.put("print", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grid.print("Bancos", "App");
			}
		});
		
		actions.put("refresh", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
	}
	
	private void createBancoFrame() {
		
		ActionListener addAction = actions.get("add");
		ActionListener editAction = actions.get("edit");
		ActionListener printAction = actions.get("print");
		ActionListener deleteAction = actions.get("delete");
		ActionListener refreshAction = actions.get("refresh");
		
		frame = new BancoFrame();
		
		CrudToolbar toolbar = frame.getToolbar();
		toolbar.getNewButton().addActionListener(addAction);
		toolbar.getEditButton().addActionListener(editAction);
		toolbar.getPrintButton().addActionListener(printAction);
		toolbar.getDeleteButton().addActionListener(deleteAction);
		toolbar.getLoadButton().addActionListener(refreshAction);
		
		CrudContextMenu gridMenu = frame.getContextMenu();
		gridMenu.getNewButton().addActionListener(addAction);
		gridMenu.getEditButton().addActionListener(editAction);
		gridMenu.getDeleteButton().addActionListener(deleteAction);
		
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
	
	public BancoForm edit(Banco banco) {
		BancoDialog dlg = createForm(banco);
		dlg.setTitle(banco.isNew() ? "Adicionar Banco" : "Editar Banco");		
		return dlg.showWindow();
	}

	private BancoDialog createForm(final Banco banco) {
		final BancoDialog dialog = new BancoDialog();
		dialog.getForm().setModel(banco);
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
	private void save(BancoDialog dialog) {
		BancoForm form = dialog.getForm();
		UIHelper.waitCursorIn(form);
		try {
			form.validateModel();
			iBancos.saveAndFlush(form.getModel());
			refresh();
			if (dialog.getContinueCheckBox().isSelected()) {
				form.setModel(new Banco());	
				form.getField("nome").requestFocus();
			} else {
				MessageBox.showMessage(form, "Banco salvo com sucesso!", "Salvando dados...");
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
				List<Banco> findAll = iBancos.findAll();
				Stores.getBancos().addAll(findAll);
				grid.setItems(findAll);
			}
		});
	}
	
	public void delete(List<Banco> bancos) {
		boolean confirm = MessageBox.showQuestion(frame, "Tem certeza que deseja excluir este registro?", "Operacao de Exclus�o");
		if (confirm) {
			iBancos.delete(bancos);
			MessageBox.showMessage(frame, "Banco removido com sucesso!", "Excluindo dados...");
			grid.removeItems(bancos);
		}
	}
	
	public BancoFrame getList() {
		if (frame == null)
			createBancoFrame();
		return frame;
	}
	
	@MenuAction(text = "Bancos", root = "cadastro", mnemonic = KeyEvent.VK_B)
	public void list() {
		mdi.showWindow(getList());
	}
	
}
