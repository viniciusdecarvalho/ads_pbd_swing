package br.edu.ftlf.ads.pbd.view.grid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.swing.table.TableCellEditor;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GridColumnEditor {

	/**
	 * 
	 * @return Render responsavel por exibir o dado
	 */
	Class<? extends TableCellEditor> value();
	
}
