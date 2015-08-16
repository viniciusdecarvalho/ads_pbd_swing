package br.edu.ftlf.ads.pbd.view.grid.renderer;

import javax.swing.table.TableCellRenderer;

public interface CellRenderer extends TableCellRenderer {

	void setHorizontalAlignment(int alignment);
	
	void setVerticalAlignment(int alignment);
}
