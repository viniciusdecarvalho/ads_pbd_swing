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

import br.edu.ftlf.ads.pbd.bean.Cliente;
import br.edu.ftlf.ads.pbd.repository.IClientesRepository;
import br.edu.ftlf.ads.pbd.view.ClienteDialog;
import br.edu.ftlf.ads.pbd.view.ClienteForm;
import br.edu.ftlf.ads.pbd.view.ClienteFrame;
import br.edu.ftlf.ads.pbd.view.grid.Grid;
import br.edu.ftlf.ads.pbd.view.menu.CrudContextMenu;
import br.edu.ftlf.ads.pbd.view.menu.MenuAction;
import br.edu.ftlf.ads.pbd.view.toolbar.CrudToolbar;
import br.edu.ftlf.ads.pbd.view.util.MessageBox;
import br.edu.ftlf.ads.pbd.view.util.UIHelper;

@Controller
public class ClientesController {
	
	@Autowired
	private MDIController mdi;
	
	@Autowired
	private IClientesRepository iClientes;

	private ClienteFrame frame;
	private Grid<Cliente> grid;
	
	private void createClienteFrame() {
		
		ActionListener addAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				edit(new Cliente());
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
		
		frame = new ClienteFrame();
		
		CrudToolbar toolbar = frame.getToolbar();
		toolbar.getNewButton().addActionListener(addAction);
		toolbar.getEditButton().addActionListener(editAction);
		toolbar.getDeleteButton().addActionListener(deleteAction);
		toolbar.getLoadButton().addActionListener(loadAction);
		
		CrudContextMenu contextMenu = frame.getContextMenu();
		contextMenu.getNewButton().addActionListener(addAction);
		contextMenu.getEditButton().addActionListener(editAction);
		contextMenu.getDeleteButton().addActionListener(deleteAction);
		
		
		frame.getPagingToolBar().setRepository(iClientes);
		
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
	
	public ClienteForm edit(Cliente cliente) {
		ClienteDialog dlg = createForm(cliente);
		dlg.setTitle(cliente.isNew() ? "Adicionar Cliente" : "Editar Cliente");
		return dlg.showWindow();
	}

	private ClienteDialog createForm(final Cliente cliente) {
		final ClienteDialog dialog = new ClienteDialog();
		dialog.getForm().setModel((Cliente) cliente.clone());
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
	private void save(ClienteDialog dialog) {
		ClienteForm form = dialog.getForm();
		UIHelper.waitCursorIn(form);
		try {
			form.validateModel();
			iClientes.saveAndFlush(form.getModel());
			refresh();
			if (dialog.getContinueCheckBox().isSelected()) {
				form.setModel(new Cliente());
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
				grid.setItems(iClientes.findAll());
			}
		});
	}
	
	public void delete(Cliente cliente) {
		boolean confirm = MessageBox.showQuestion(frame, "Tem certeza que deseja excluir este registro?", "Operacao de Exclusao");
		if (confirm) {
			try {
				iClientes.delete(cliente);
				MessageBox.showMessage(frame, "Cliente removido com sucesso!", "Excluindo dados...");
				refresh();
			} catch (Exception e) {
				MessageBox.showError(frame, "Falha", "Operacao de exclusao falhou!", e);
			}
		}
	}
	
	public ClienteFrame getList() {
		if (frame == null)
			createClienteFrame();
		return frame;
	}
	
	@MenuAction(text = "Clientes", root = "cadastro", mnemonic = KeyEvent.VK_C)
	public void list() {
		mdi.showWindow(getList());
	}
	
}
