package br.edu.ftlf.ads.pbd.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ftlf.ads.pbd.bean.FormasPagamento;
import br.edu.ftlf.ads.pbd.repository.IFormasPagamentoRepository;
import br.edu.ftlf.ads.pbd.view.FormasPagamentoDialog;
import br.edu.ftlf.ads.pbd.view.FormasPagamentoForm;
import br.edu.ftlf.ads.pbd.view.FormasPagamentoFrame;
import br.edu.ftlf.ads.pbd.view.grid.Grid;
import br.edu.ftlf.ads.pbd.view.menu.CrudContextMenu;
import br.edu.ftlf.ads.pbd.view.menu.MenuAction;
import br.edu.ftlf.ads.pbd.view.toolbar.CrudToolbar;
import br.edu.ftlf.ads.pbd.view.util.MessageBox;
import br.edu.ftlf.ads.pbd.view.util.UIHelper;

@Controller
public class FormasPagamentosController {
	
	@Autowired
	private MDIController mdi;
	
	@Autowired
	private IFormasPagamentoRepository iFormasPagamentos;

	private FormasPagamentoFrame frame;
	private Grid<FormasPagamento> grid;

	private void createFormaPagamentoFrame() {
		
		ActionListener addAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				edit(new FormasPagamento());
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
				delete(grid.getSelectedItems());
			}
		};
		ActionListener loadAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		};
		
		frame = new FormasPagamentoFrame();
		
		CrudToolbar toolbar = frame.getToolbar();
		toolbar.getNewButton().addActionListener(addAction);
		toolbar.getEditButton().addActionListener(editAction);
		toolbar.getDeleteButton().addActionListener(deleteAction);
		toolbar.getLoadButton().addActionListener(loadAction);
		
		CrudContextMenu gridMenu = frame.getGridPopUp();
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
	
	public FormasPagamentoForm edit(FormasPagamento formasPagamento) {
		FormasPagamentoDialog dlg = createForm(formasPagamento);
		dlg.setTitle(formasPagamento.isNew() ? "Adicionar Forma de Pagamento" : "Editar Forma de Pagamento");
		return dlg.showWindow();
	}

	private FormasPagamentoDialog createForm(final FormasPagamento formasPagamento) {
		final FormasPagamentoDialog dialog = new FormasPagamentoDialog();
		dialog.getForm().setModel(formasPagamento);
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
	private void save(FormasPagamentoDialog dialog) {
		FormasPagamentoForm form = dialog.getForm();
		UIHelper.waitCursorIn(form);
		try {
			form.validateModel();
			iFormasPagamentos.saveAndFlush(form.getModel());
			refresh();
			if (dialog.getContinueCheckBox().isSelected()) {
				form.setModel(new FormasPagamento());
			} else {
				MessageBox.showMessage(form, "Forma de Pagamento salvo com sucesso!", "Salvando dados...");
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
				grid.setItems(iFormasPagamentos.findAll());
			}
		});
	}
	
	public void delete(List<FormasPagamento> formasPagamentos) {
		boolean confirm = MessageBox.showQuestion(frame, "Tem certeza que deseja excluir este registro?", "Operacao de Exclusao");
		if (confirm) {
			iFormasPagamentos.delete(formasPagamentos);
			MessageBox.showMessage(frame, "Forma de Pagamento removido com sucesso!", "Excluindo dados...");
			grid.removeItems(formasPagamentos);
		}
	}
	
	public FormasPagamentoFrame getList() {
		if (frame == null)
			createFormaPagamentoFrame();
		return frame;
	}
	
	@MenuAction(text = "Formas de Pagamento", root = "cadastro", mnemonic = KeyEvent.VK_P)
	public void list() {
		mdi.showWindow(getList());
	}
	
}
