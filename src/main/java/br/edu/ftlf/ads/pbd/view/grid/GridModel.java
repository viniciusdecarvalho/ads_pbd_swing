package br.edu.ftlf.ads.pbd.view.grid;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import br.edu.ftlf.ads.pbd.view.grid.annotation.GridColumn;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridColumnEditor;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridColumnRender;
import br.edu.ftlf.ads.pbd.view.grid.annotation.GridViewModel;

/**
 * @autor viniciusdecarvalho
 */
public class GridModel<T> extends AbstractTableModel implements IGridModel<T> {

	private static final long serialVersionUID = -7430407267354039048L;

	private Class<?> beanClass;
	
	private List<T> items = new ArrayList<T>();
	private List<GridColumnDef> columns;

	public GridModel(Class<?> beanClass) {
		setBean(beanClass);
	}

	// TODO implementar o metodo para editar valor da celula
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			
	}

	@Override
	public int getColumnCount() {
		return this.columns.size();
	}

	@Override
	public int getRowCount() {
		return items.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 && rowIndex >= items.size()) 
			return "";
		
		try {
			GridColumnDef columnDef = columns.get(columnIndex);
			if (columnDef.isVisible()) {
				T item = this.items.get(rowIndex);
				String getMethodName = "get".concat(capitalize(columnDef.getPropertyName()));	
				Class<?> itemClass = beanClass.getAnnotation(GridViewModel.class).value();
				Object beanModel = beanClass.getConstructor(itemClass).newInstance(item);
				Method getterMethod = beanClass.getMethod(getMethodName);
				return getterMethod.invoke(beanModel);
			}
		} catch (Exception e) {
			throw new IllegalStateException("Construtor nao encontrado", e);
		}
		return "";
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex >= 0 && columnIndex < columns.size()) 
			return columns.get(columnIndex).getColumnClass();
		return Object.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex >= 0 && columnIndex < columns.size())
			return columns.get(columnIndex).isEditable();
		return false;
	}

	@Override
	public String getColumnName(int columnIndex) {
		if (columnIndex >= 0 && columnIndex < columns.size()) 
			return columns.get(columnIndex).getColumnName();
		return "";
	}


	protected void processAnnotations() {
		List<Method> gridColumns = getBeanAnnotationMethods(beanClass);		
		this.columns = new ArrayList<GridColumnDef>(gridColumns.size());
		try {
			for (Method method : gridColumns) {
				GridColumn gridColumn = method.getAnnotation(GridColumn.class);

				Class<?> columnClass = method.getReturnType();
				String columnName = (gridColumn.name().isEmpty() ? buildName(method) : gridColumn.name());
				int columnIndex = gridColumn.index();
				double flex = gridColumn.flex();
				boolean editable = gridColumn.editable();
				boolean resizable = gridColumn.resizable();
				boolean visible = gridColumn.visible();
				String name = method.getName().replaceFirst("get", "");
				
				if (name.length() > 1) {
					name = name.substring(0, 1).toLowerCase().concat(name.substring(1));
				}
				
				GridColumnDef column = new GridColumnDef(columnIndex, columnName);
				column.setColumnClass(columnClass);
				column.setEditable(editable);
				column.sethAlign(gridColumn.hAlign());
				column.setvAlign(gridColumn.vAlign());
				column.setVisible(visible);
				column.setFlex(flex);
				column.setPropertyName(name);
				column.setResizable(resizable);
				
				if (method.isAnnotationPresent(GridColumnRender.class)) {
					column.setCellRenderer(method.getAnnotation(GridColumnRender.class).value().newInstance());
				}
				if (method.isAnnotationPresent(GridColumnEditor.class)) {
					column.setCellEditor(method.getAnnotation(GridColumnEditor.class).value().newInstance());
				}
				
				columns.add(columnIndex, column);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private String buildName(Method method) {
		String name = method.getName();
		return capitalize(name.replaceAll("get|set", ""));
	}
	
	private String capitalize(String value) {		
		return value.replaceFirst(String.valueOf(value.charAt(0)), String
				.valueOf(value.charAt(0)).toUpperCase());
	}
	
	private List<Method> getBeanAnnotationMethods(Class<?> beanClass) {
		List<Method> methods = new ArrayList<Method>();
		for (Method method : beanClass.getDeclaredMethods()) {
			if (method.isAnnotationPresent(GridColumn.class)) 
				methods.add(method);
		}
		Collections.sort(methods, new Comparator<Method>() {
			@Override
			public int compare(Method a, Method b) {
				int indexA = a.getAnnotation(GridColumn.class).index();
				int indexB = b.getAnnotation(GridColumn.class).index();
				return Integer.compare(indexA, indexB);
			}
		});
		return methods;
	}
	
	@Override
	public T getItem(int index) {
		if (index >= 0 && index < items.size())
			return items.get(index);
		return null;
	}

	@Override
	public List<T> getItems(int[] indexs) {
		List<T> models = new ArrayList<T>(indexs.length);
		for (int i = 0; i < indexs.length; i++) {
			models.add(getItem(indexs[i]));
		}
		return models;
	}

	@Override
	public List<T> getAllItems() {
		return Collections.unmodifiableList(items);
	}

	@Override
	public void addItem(T model) {
		this.items.add(model);
		int lastRow = getRowCount() - 1;
		fireTableRowsInserted(lastRow, lastRow);
	}

	@Override
	public void addItems(List<T> items) {
		int row = getRowCount();
		if (!items.isEmpty()) {
			this.items.addAll(items);
			fireTableRowsInserted(row, row + items.size() - 1);
		}
	}

	@Override
	public void setItem(int index, T model) {
		if (index >= 0 && index < items.size()) {
			items.set(index, model);
			fireTableRowsUpdated(index, index);
		}
	}

	@Override
	public void setItems(List<T> items) {
		clear();
		addItems(items);
	}

	@Override
	public void removeItems(List<T> items) {
		this.items.removeAll(items);
		fireTableDataChanged();
	}

	@Override
	public void removeItem(int index) {
		if (index >= 0 && index < items.size()) {
			this.items.remove(index);
			fireTableRowsDeleted(index, index);
		}
	}

	@Override
	public void removeItem(T model) {
		removeItem(items.indexOf(model));
	}

	@Override
	public void clear() {
		this.items.clear();
		fireTableDataChanged();
	}

	@Override
	public Class<?> getBean() {
		return beanClass;
	}

	@Override
	public void setBean(Class<?> beanClass) {
		if (!beanClass.isAnnotationPresent(GridViewModel.class)) {
			throw new IllegalArgumentException("A classe deve possuir a anotação GridType.");
		}
		this.beanClass = beanClass;
		processAnnotations();
	}
	
	@Override
	public TableColumnModel getColumnModel() {
		DefaultTableColumnModel columnModel = new DefaultTableColumnModel();
		for (TableColumn aColumn : columns) {
			columnModel.addColumn(aColumn);
		}
		return columnModel;
	}

	@Override
	public List<GridColumnDef> getColumns() {
		return columns;
	}
	
}
