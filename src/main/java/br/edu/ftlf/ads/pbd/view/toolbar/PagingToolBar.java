package br.edu.ftlf.ads.pbd.view.toolbar;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ftlf.ads.pbd.view.grid.Grid;

/**
 * Component para paginacao dos registros
 * 
 * @author Marcus Vinicius de Carvalho Silva
 * 
 * @param <T>
 */
public class PagingToolBar extends Container {
	private static final long serialVersionUID = 3061098749526771546L;

	private static final String IMG_PATH = "/br/edu/ftlf/ads/pbd/images/paging/";
	private static final ImageIcon LAST_ICON = new ImageIcon(
			PagingToolBar.class.getResource(IMG_PATH + "page-last.gif"));
	private static final ImageIcon NEXT_ICON = new ImageIcon(
			PagingToolBar.class.getResource(IMG_PATH + "page-next.gif"));
	private static final ImageIcon PREV_ICON = new ImageIcon(
			PagingToolBar.class.getResource(IMG_PATH + "page-prev.gif"));
	private static final ImageIcon FIRST_ICON = new ImageIcon(
			PagingToolBar.class.getResource(IMG_PATH + "page-first.gif"));

	private final JSpinner numberPageSpinner;
	private final JLabel totalLabel;
	private final JButton refreshButton;
	private final JButton firstButton;
	private final JButton previousButton;
	private final JButton nextButton;
	private final JButton lastButton;
	private final JComboBox<Integer> pageSizeComboBox;

	private JLabel messageLabel;
	private JPanel pnlPaging;
	private JPanel pnlMessage;
	public Page<?> page;

	private JpaRepository<?, ?> repository;
	private Grid<?> grid;

	public PagingToolBar(Grid<?> grid ) {
		this();
		setGrid(grid);
		grid.add(this, BorderLayout.SOUTH);
	}
	
	private PagingToolBar() {
		super();
		setPreferredSize(new Dimension(637, 30));
		pnlPaging = new JPanel();
		pnlPaging.setOpaque(false);
		pnlPaging.setAlignmentX(Component.LEFT_ALIGNMENT);
		pnlPaging.setMaximumSize(new Dimension(300, 25));
		setLayout(new BorderLayout());
		add(pnlPaging, BorderLayout.WEST);

		GridBagLayout gbl_pnlPaging = new GridBagLayout();
		gbl_pnlPaging.columnWidths = new int[] { 25, 0, 0, 0, 0, 0, 0, 0, 0, 60 };
		gbl_pnlPaging.rowHeights = new int[] { 22, 0 };
		gbl_pnlPaging.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_pnlPaging.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlPaging.setLayout(gbl_pnlPaging);

		firstButton = new JButton();
		firstButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		firstButton.setIcon(FIRST_ICON);
		firstButton.setSize(new Dimension(20, 20));
		firstButton.setPreferredSize(new Dimension(22, 22));
		firstButton.setMinimumSize(new Dimension(30, 30));
		firstButton.setMaximumSize(new Dimension(30, 30));
		firstButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		firstButton.setActionCommand("first");
		firstButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				numberPageSpinner.setValue(new Integer(1));
				refresh();
			}
		});

		GridBagConstraints gbc_btnFirst = new GridBagConstraints();
		gbc_btnFirst.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFirst.anchor = GridBagConstraints.NORTH;
		gbc_btnFirst.insets = new Insets(0, 0, 0, 5);
		gbc_btnFirst.gridx = 0;
		gbc_btnFirst.gridy = 0;
		pnlPaging.add(firstButton, gbc_btnFirst);

		previousButton = new JButton();
		previousButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		previousButton.setIcon(PREV_ICON);
		previousButton.setSize(new Dimension(20, 20));
		previousButton.setPreferredSize(new Dimension(22, 22));
		previousButton.setMinimumSize(new Dimension(30, 30));
		previousButton.setMaximumSize(new Dimension(30, 30));
		previousButton.setBorder(new BevelBorder(BevelBorder.RAISED, null,
				null, null, null));
		previousButton.setActionCommand("previous");
		previousButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer value = (Integer) numberPageSpinner.getValue();
				numberPageSpinner.setValue(--value);
				refresh();
			}
		});

		GridBagConstraints gbc_btnPrevious = new GridBagConstraints();
		gbc_btnPrevious.anchor = GridBagConstraints.NORTH;
		gbc_btnPrevious.insets = new Insets(0, 0, 0, 5);
		gbc_btnPrevious.gridx = 1;
		gbc_btnPrevious.gridy = 0;
		pnlPaging.add(previousButton, gbc_btnPrevious);

		JLabel lblPagina = new JLabel("P\u00E1g");
		GridBagConstraints gbc_lblPagina = new GridBagConstraints();
		gbc_lblPagina.anchor = GridBagConstraints.EAST;
		gbc_lblPagina.insets = new Insets(0, 0, 0, 5);
		gbc_lblPagina.gridx = 2;
		gbc_lblPagina.gridy = 0;
		pnlPaging.add(lblPagina, gbc_lblPagina);
		lblPagina.setBounds(50, 8, 41, 15);
		lblPagina.setFont(new Font("Tahoma", Font.BOLD, 12));

		numberPageSpinner = new JSpinner();
		numberPageSpinner.setModel(new SpinnerNumberModel(new Integer(1),
				new Integer(1), null, new Integer(1)));
		numberPageSpinner.setSize(new Dimension(40, 30));
		numberPageSpinner.setBounds(96, 6, 50, 20);
		numberPageSpinner.setFont(new Font("Tahoma", Font.BOLD, 12));
		numberPageSpinner.setPreferredSize(new Dimension(40, 22));
		numberPageSpinner.setMaximumSize(new Dimension(40, 30));
		numberPageSpinner.setMinimumSize(new Dimension(40, 30));
		numberPageSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				refresh();
			}
		});

		GridBagConstraints gbc_spnPage = new GridBagConstraints();
		gbc_spnPage.anchor = GridBagConstraints.NORTHWEST;
		gbc_spnPage.insets = new Insets(0, 0, 0, 5);
		gbc_spnPage.gridx = 3;
		gbc_spnPage.gridy = 0;
		pnlPaging.add(numberPageSpinner, gbc_spnPage);

		JLabel lblDe = new JLabel("de");
		lblDe.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblDe.setBounds(155, 9, 15, 15);
		lblDe.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblDe = new GridBagConstraints();
		gbc_lblDe.anchor = GridBagConstraints.WEST;
		gbc_lblDe.insets = new Insets(0, 0, 0, 5);
		gbc_lblDe.gridx = 4;
		gbc_lblDe.gridy = 0;
		pnlPaging.add(lblDe, gbc_lblDe);

		nextButton = new JButton();
		nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		nextButton.setIcon(NEXT_ICON);
		nextButton.setSize(new Dimension(20, 20));
		nextButton.setPreferredSize(new Dimension(22, 22));
		nextButton.setMinimumSize(new Dimension(30, 30));
		nextButton.setMaximumSize(new Dimension(30, 30));
		nextButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		nextButton.setActionCommand("next");
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer value = (Integer) numberPageSpinner.getValue();
				numberPageSpinner.setValue(++value);
				refresh();
			}
		});

		totalLabel = new JLabel();
		totalLabel.setText("1");
		totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		totalLabel.setBounds(175, 9, 50, 15);
		totalLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.anchor = GridBagConstraints.WEST;
		gbc_lblTotal.insets = new Insets(0, 0, 0, 5);
		gbc_lblTotal.gridx = 5;
		gbc_lblTotal.gridy = 0;
		pnlPaging.add(totalLabel, gbc_lblTotal);
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.anchor = GridBagConstraints.NORTH;
		gbc_btnNext.insets = new Insets(0, 0, 0, 5);
		gbc_btnNext.gridx = 6;
		gbc_btnNext.gridy = 0;
		pnlPaging.add(nextButton, gbc_btnNext);

		lastButton = new JButton();
		lastButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		lastButton.setIcon(LAST_ICON);
		lastButton.setSize(new Dimension(20, 20));
		lastButton.setPreferredSize(new Dimension(22, 22));
		lastButton.setMinimumSize(new Dimension(30, 30));
		lastButton.setMaximumSize(new Dimension(30, 30));
		lastButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		lastButton.setActionCommand("last");
		lastButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				numberPageSpinner.setValue(page.getTotalPages());
				refresh();
			}
		});

		GridBagConstraints gbc_btnLast = new GridBagConstraints();
		gbc_btnLast.anchor = GridBagConstraints.NORTH;
		gbc_btnLast.insets = new Insets(0, 0, 0, 5);
		gbc_btnLast.gridx = 7;
		gbc_btnLast.gridy = 0;
		pnlPaging.add(lastButton, gbc_btnLast);

		refreshButton = new JButton();
		refreshButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		refreshButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		refreshButton.setSize(new Dimension(20, 20));
		refreshButton.setPreferredSize(new Dimension(22, 22));
		refreshButton.setBounds(10, 4, 30, 25);
		refreshButton.setMinimumSize(new Dimension(30, 30));
		refreshButton.setMaximumSize(new Dimension(30, 30));
		refreshButton.setIcon(new ImageIcon(PagingToolBar.class
				.getResource(IMG_PATH + "refresh.gif")));
		refreshButton.setActionCommand("refresh");
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});

		GridBagConstraints gbc_btnRefresh = new GridBagConstraints();
		gbc_btnRefresh.insets = new Insets(0, 0, 0, 5);
		gbc_btnRefresh.anchor = GridBagConstraints.NORTH;
		gbc_btnRefresh.gridx = 8;
		gbc_btnRefresh.gridy = 0;
		pnlPaging.add(refreshButton, gbc_btnRefresh);
		
		pageSizeComboBox = new JComboBox<Integer>();
		pageSizeComboBox.setMinimumSize(new Dimension(28, 22));
		pageSizeComboBox.setSize(new Dimension(28, 22));
		pageSizeComboBox.setPreferredSize(new Dimension(28, 22));
		pageSizeComboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		pageSizeComboBox.setToolTipText("Selecione o numero de registros por p\u00E1gina");
		pageSizeComboBox.setModel(new DefaultComboBoxModel<Integer>(new Integer[] { 10, 25, 50, 100 }));
		pageSizeComboBox.setSelectedIndex(1);
		pageSizeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (firstButton.isEnabled()) {
					firstButton.doClick();
				} else {
					refresh();
				}
			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 9;
		gbc_comboBox.gridy = 0;
		pnlPaging.add(pageSizeComboBox, gbc_comboBox);
		
		pnlMessage = new JPanel();
		pnlMessage.setOpaque(false);
		add(pnlMessage, BorderLayout.EAST);
		pnlMessage.setMaximumSize(new Dimension(300, 25));
		pnlMessage.setLayout(new BorderLayout(0, 0));
		messageLabel = new JLabel("9999 registros.");
		messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		messageLabel.setBounds(401, 7, 128, 15);
		messageLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		pnlMessage.add(messageLabel, BorderLayout.EAST);

	}

	public void setPaging(Page<?> page) {
		setMessage(page);
		configComponents(page);
		repaint();
	}

	private void setMessage(Page<?> page) {
		long total = page.getTotalElements();
		String message = "Sem registros.";
		if (total > 0) {
			long start = ((page.getNumber() + 1) * page.getSize()) - page.getSize();
			long last = (page.getNumber() + 1) * page.getSize();
			if (last > total)
				last = total;
			message = String.format("Exibindo de %d até %d do total de %d registros.", ++start, last, total);
			
		}
		messageLabel.setText(message);
	}

	private void configComponents(Page<?> page) {
		firstButton.setEnabled(page.hasPreviousPage());
		previousButton.setEnabled(page.hasPreviousPage());
		nextButton.setEnabled(page.hasNextPage());
		lastButton.setEnabled(page.hasNextPage());
		totalLabel.setText("" + page.getTotalPages());

		int number = page.getNumber();
		int totalPages = page.getTotalPages();
		SpinnerNumberModel model = new SpinnerNumberModel(
														new Integer(++number), 
														new Integer(1),
														new Integer(totalPages == 0 ? 1 : totalPages), 
														new Integer(1));
		numberPageSpinner.setModel(model);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void refresh() {
		if (repository == null) {
			throw new IllegalStateException("Repositorio nao definido.");
		}
		int pageSize = (Integer) pageSizeComboBox.getSelectedItem();
		int value = (Integer) numberPageSpinner.getValue();
		page = repository.findAll(new PageRequest(--value, pageSize));
		List content = page.getContent();
		grid.setItems(content);
		setMessage(page);
		configComponents(page);
	}

	public JpaRepository<?, ?> getRepository() {
		return repository;
	}

	public void setRepository(JpaRepository<?, ?> repository) {
		this.repository = repository;
//		refresh();
	}

	public Grid<?> getGrid() {
		return grid;
	}

	public PagingToolBar setGrid(Grid<?> grid) {
		this.grid = grid;
		return this;
	}
	
	public JLabel getMessageLabel() {
		return messageLabel;
	}

	public void setMessageLabel(JLabel messageLabel) {
		this.messageLabel = messageLabel;
	}

	public JSpinner getNumberPageSpinner() {
		return numberPageSpinner;
	}

	public JLabel getTotalLabel() {
		return totalLabel;
	}

	public JButton getRefreshButton() {
		return refreshButton;
	}

	public JButton getFirstButton() {
		return firstButton;
	}

	public JButton getPreviousButton() {
		return previousButton;
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public JButton getLastButton() {
		return lastButton;
	}
}
