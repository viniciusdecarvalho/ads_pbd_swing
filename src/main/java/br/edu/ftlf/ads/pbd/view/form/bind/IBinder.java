package br.edu.ftlf.ads.pbd.view.form.bind;

import org.jdesktop.beansbinding.BindingGroup;

/**
 * Objeto para ligar(bind) elementos de uma entidade a um component grafico
 *
 */
public interface IBinder {

	/**
	 * 
	 * @param source
	 * @param component
	 * @return
	 */
	BindingGroup bind(Object source, Object target);

}