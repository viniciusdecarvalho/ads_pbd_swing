package br.edu.ftlf.ads.pbd.view.grid;

import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import br.edu.ftlf.ads.pbd.view.grid.renderer.CellRenderer;
import br.edu.ftlf.ads.pbd.view.grid.renderer.CurrencyRenderer;
import br.edu.ftlf.ads.pbd.view.grid.renderer.DateRenderer;
import br.edu.ftlf.ads.pbd.view.grid.renderer.DoubleRenderer;
import br.edu.ftlf.ads.pbd.view.grid.renderer.GridCellRenderer;
import br.edu.ftlf.ads.pbd.view.grid.renderer.IconRenderer;
import br.edu.ftlf.ads.pbd.view.grid.renderer.NumberRenderer;

public class Grid<T> extends JTable {

	private static final long serialVersionUID = 1L;

	private Grid(GridModel<T> model) {
		super(model, model.getColumnModel());
		defaultProperties();
	}

	@Override
	protected void createDefaultRenderers() {
		super.createDefaultRenderers();
		setDefaultRenderer(Object.class, new GridCellRenderer());
		
		// Numbers
		setDefaultRenderer(Number.class, new NumberRenderer());
		setDefaultRenderer(Float.class, new DoubleRenderer());
		setDefaultRenderer(Double.class, new DoubleRenderer());
		setDefaultRenderer(BigDecimal.class, new CurrencyRenderer());

		// Dates
		setDefaultRenderer(Date.class, new DateRenderer());

		// Icons and ImageIcons
		setDefaultRenderer(Icon.class, new IconRenderer());
		setDefaultRenderer(ImageIcon.class, new IconRenderer());

		// Booleans - default JTable renderer
		//setDefaultRenderer(Boolean.class, new OkCancelRenderer());

	}	
	
	public boolean print(String header, String footer) {
		try {
			MessageFormat h = new MessageFormat(header);
			MessageFormat f = new MessageFormat(footer);
			return print(PrintMode.FIT_WIDTH, h, f, true, null, true, null);
		} catch (PrinterException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	protected void defaultProperties() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setColumnSelectionAllowed(false);
		setRowSelectionAllowed(true);
		setAutoCreateRowSorter(true);			
		
		Enumeration<TableColumn> columns = getColumnModel().getColumns();
		while(columns.hasMoreElements()) {
			
		 	GridColumnDef columnDef = (GridColumnDef) columns.nextElement();
			
			int index = columnDef.getModelIndex();
			int hAlign = columnDef.gethAlign().getValue();
			int vAlign = columnDef.getvAlign().getValue();
			
			setColumnAlign(index, hAlign, vAlign);
		}
	}

	@Override
	public Object getValueAt(int row, int column) {
		return super.getValueAt(row, column);
	}
	
	public static <T> Grid<T> create(Class<T> beanClass, Class<?> gridModelClass) {
		return new Grid<T>(new GridModel<T>(gridModelClass));
	}

	@Override
	public void setModel(TableModel dataModel) {
		if (!(dataModel instanceof IGridModel)) {
			throw new IllegalArgumentException("tablemodel deve ser instancia de IGridModel.");
		}
		super.setModel(dataModel);
	}
	
	public void setModel(IGridModel<T> dataModel) {
		super.setModel(dataModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IGridModel<T> getModel() {
		return (IGridModel<T>) super.getModel();
	}
	
	private boolean isSingleSelection() {
		return getSelectionModel().getSelectionMode() == ListSelectionModel.SINGLE_SELECTION;
	}

	public T getSelectedItem() {
		int[] rows = getSelectedRows();
		if (rows.length == 0) {
			throw new IllegalStateException("Selecione uma linha na tabela.");
		}
		if (isSingleSelection() && rows.length > 1) {
			throw new IllegalStateException(
					"Somente uma linha na tabela deve estar selecionada.");
		}
		return getItem(rows[0]);
	}

	public List<T> getSelectedItems() {
		int[] rows = getSelectedRows();
		if (rows.length == 0) {
			throw new IllegalStateException("Selecione uma linha na tabela.");
		}
		return getItems(rows);
	}

	public T getItem(int row) {
		int modelIndex = convertRowIndexToModel(row);
		return getModel().getItem(modelIndex);
	}

	public List<T> getItems(int[] rows) {
		int[] indexs = new int[rows.length];
		for (int i = 0; i < indexs.length; i++) {
			indexs[i] = convertRowIndexToModel(rows[i]);
		}
		return getModel().getItems(indexs);
	}

	public List<T> getAllItems() {
		return getModel().getAllItems();
	}

	public Grid<T> addItem(T model) {
		getModel().addItem(model);
		return this;
	}

	public Grid<T> addItems(List<T> items) {
		getModel().addItems(items);
		return this;
	}

	public Grid<T> setItem(int row, T model) {
		int index = convertRowIndexToModel(row);
		getModel().setItem(index, model);
		return this;
	}

	public Grid<T> setItems(List<T> items) {
		getModel().setItems(items);
		return this;
	}

	public Grid<T> removeItems(List<T> items) {
		getModel().removeItems(items);
		return this;
	}

	public Grid<T> removeItem(T model) {
		getModel().removeItem(model);
		return this;
	}

	public Grid<T> clear() {
		getModel().clear();
		return this;
	}

	public Grid<T> setColumnAlign(int columnIndex, int hAlign, int vAlign) {
		TableColumn column = getModel().getColumnModel().getColumn(columnIndex);
		TableCellRenderer cellRenderer = column.getCellRenderer();
		if (cellRenderer instanceof CellRenderer) {
			((CellRenderer)cellRenderer).setHorizontalAlignment(hAlign);
			((CellRenderer)cellRenderer).setVerticalAlignment(vAlign);
		}
		return this;
	}

	public Grid<T> setFlexWidth(int gridWidth) {
		setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		int columnCount = getColumnModel().getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			
			GridColumnDef tableColumn = (GridColumnDef) getColumnModel().getColumn(i);
			double value = (gridWidth / columnCount) * tableColumn.getFlex();
			
			tableColumn.setWidth((int) value);
			tableColumn.setPreferredWidth((int) value);
		}
		setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		return this;
	}

}
