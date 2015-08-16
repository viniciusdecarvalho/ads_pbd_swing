package br.edu.ftlf.ads.pbd.view.grid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.edu.ftlf.ads.pbd.view.grid.renderer.CellRenderer;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GridColumnRender {

	/**
	 * 
	 * @return Render responsavel por exibir o dado
	 */
	Class<? extends CellRenderer> value();
	
}
