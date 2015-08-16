package br.edu.ftlf.ads.pbd.view.form;

import java.awt.Component;

import org.jdesktop.beansbinding.BindingGroup;

import br.edu.ftlf.ads.pbd.view.form.bind.IBinder;
import br.edu.ftlf.ads.pbd.view.form.validator.IFormValidator;

public interface IForm<T> {

	IForm<T> setModel(T model);

	void setModel(T model, boolean update);

	void bindRefresh(String name);

	void bindRefresh();

	T getModel();

	void validateModel();

	void validateModel(String property);
	
	IForm<T> setBinder(IBinder binder);

	IBinder getBinder();

	IForm<T> setValidator(IFormValidator validator);

	IFormValidator getValidator();

	BindingGroup getBindingGroup();

	Component getField(String fieldName);

	IForm<T> focus(String fieldName);

}