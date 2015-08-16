package br.edu.ftlf.ads.pbd.view.grid;

import javax.swing.table.TableColumn;

import br.edu.ftlf.ads.pbd.view.grid.annotation.HAlign;
import br.edu.ftlf.ads.pbd.view.grid.annotation.VAlign;

public class GridColumnDef extends TableColumn {

	private static final long serialVersionUID = 4104264714817876211L;
	
	private String propertyName;
	private Class<?> columnClass;
	private String columnName;
	private boolean editable;
	private boolean visible;
	private double flex;
	private HAlign hAlign;
	private VAlign vAlign;
	
	public GridColumnDef(int index, String columnName, 
						Class<?> columnClass, 
						boolean editable,
						boolean resizable,
						boolean visible, 
						int flex, 
						HAlign hAlign, 
						VAlign vAlign, 
						String propertyName) {
		setModelIndex(index);
		setColumnClass(columnClass);
		setColumnName(columnName);
		setEditable(editable);
		setVisible(visible);
		setFlex(flex);
		sethAlign(hAlign);
		setvAlign(vAlign);
		setPropertyName(propertyName);
	}
	
	public GridColumnDef(int index, String columnName, Class<?> columnClass) {
		this(index, columnName, columnClass, false, true, true, 1, HAlign.CENTER, VAlign.CENTER, null);
	}
	
	public GridColumnDef(int index, String columnName) {
		this(index, columnName, Object.class);
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	
	public GridColumnDef setPropertyName(String propertyName) {
		this.propertyName = propertyName;
		setIdentifier(propertyName);
		return this;
	}

	public Class<?> getColumnClass() {
		return columnClass;
	}

	public GridColumnDef setColumnClass(Class<?> columnClass) {
		this.columnClass = columnClass;
		return this;
	}

	public String getColumnName() {
		return columnName;
	}

	public GridColumnDef setColumnName(String columnName) {
		this.columnName = columnName;
		setHeaderValue(columnName);
		return this;
	}

	public boolean isEditable() {
		return editable;
	}

	public GridColumnDef setEditable(boolean editable) {
		this.editable = editable;
		return this;
	}

	public boolean isVisible() {
		return visible;
	}

	public GridColumnDef setVisible(boolean visible) {
		this.visible = visible;
		return this;
	}
	
	public double getFlex() {
		return flex;
	}

	public GridColumnDef setFlex(double flexWidth) {
		this.flex = flexWidth;
		return this;
	}

	public HAlign gethAlign() {
		return hAlign;
	}

	public GridColumnDef sethAlign(HAlign hAlign) {
		this.hAlign = hAlign;
		return this;
	}

	public VAlign getvAlign() {
		return vAlign;
	}

	public GridColumnDef setvAlign(VAlign vAlign) {
		this.vAlign = vAlign;
		return this;
	}

	@Override
	public String toString() {
		return "GridColumnDef ["
				+ (columnClass != null ? "columnClass=" + columnClass + ", "
						: "")
				+ (columnName != null ? "columnName=" + columnName : "") + "]";
	}
	
	

}