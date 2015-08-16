package br.edu.ftlf.ads.pbd.view.grid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Comparator;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GridColumnComparator {

	/**
	 * 
	 * @return Comparator
	 */
	@SuppressWarnings("rawtypes")
	Class<? extends Comparator> value();
	
}
