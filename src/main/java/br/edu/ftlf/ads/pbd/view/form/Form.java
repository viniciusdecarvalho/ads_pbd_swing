package br.edu.ftlf.ads.pbd.view.form;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;

import br.edu.ftlf.ads.pbd.bean.BeanImpl;
import br.edu.ftlf.ads.pbd.view.form.bind.Bind;
import br.edu.ftlf.ads.pbd.view.form.bind.DefaultBinder;
import br.edu.ftlf.ads.pbd.view.form.bind.IBinder;
import br.edu.ftlf.ads.pbd.view.form.validator.DefaultFormValidator;
import br.edu.ftlf.ads.pbd.view.form.validator.IFormValidator;

public abstract class Form<T extends BeanImpl<?>> extends Container implements IForm<T> {

	private static final long serialVersionUID = -3763820778915072331L;
	private IBinder binder;
	private IFormValidator validator;
	private BindingGroup bindingGroup;
	private T model;

	public Form(IBinder binder, IFormValidator validator) {
		setBinder(binder);
		setValidator(validator);
	}
	
	public Form() {
		this(new DefaultBinder(), new DefaultFormValidator());
	}
	
	public Form(IBinder binder, IFormValidator validator, T model) {
		this(binder, validator);
		setModel(model);
	}
	
	public Form(T model) {
		this();
		setModel(model);
	}
	
	@Override
	public IForm<T> setModel(T model) {
		setModel(model, true);
		return this;
	}
	
	@Override
	public void setModel(T model, boolean update) {
		if (model == null) 
			throw new IllegalStateException("model doesn't is null");
		this.model = model;
		if (update) {
			unbind();
			initDataBindings();
			validateModelConstraints();
			validateFormConstraints();			
		}
	}

	protected void validateFormConstraints() {
		try {
			for (final Field field : validator.getFields(this)) {
				JComponent jComponent = (JComponent)field.get(this);
				jComponent.setFocusable(true);
				jComponent.addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent e) {
						validateModel(field.getAnnotation(Bind.class).property());
					}
				});
			}
			addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					validateModel();
				}
			});
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	protected void validateModelConstraints() {
		for (Field field : validator.getFields(this)) {
			final String property = field.getAnnotation(Bind.class).property();
			if (!field.isAccessible())
				field.setAccessible(true);
			this.model.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					if (evt.equals(property)) 
						validateModel(evt.getPropertyName());
				}
			});
		}
	}
	
	@Override
	public void bindRefresh(String name) {
		Binding<?, ?, ?, ?> binding = bindingGroup.getBinding(name);
		if (binding != null) {
			binding.refresh();
		}
	}
	
	@Override
	public void bindRefresh() {
		for (Binding<?, ?, ?, ?> binding : bindingGroup.getBindings()) {
			binding.refresh();
		}
	}

	private void unbind() {
		if (bindingGroup != null) {
			bindingGroup.unbind();
			bindingGroup = null;
		}
	}

	@Override
	public T getModel() {
		return this.model;
	}
	
	protected BindingGroup initDataBindings() {
		bindingGroup = binder.bind(this.model, this);
		bindingGroup.bind();
		return bindingGroup;
	}

	@Override
	public void validateModel() {
		validator.validate(this, this.model);
	}
	
	@Override
	public void validateModel(String property) {
		validator.validate(this, this.model, property);
	}
	
	@Override
	public IForm<T> setBinder(IBinder binder) {
		if (binder == null) {
			throw new IllegalStateException("binder doesn't is null");
		}
		this.binder = binder;
		return this;
	}
	
	@Override
	public IBinder getBinder() {
		return binder;
	}

	@Override
	public IForm<T> setValidator(IFormValidator validator) {
		if (validator == null) {
			throw new IllegalStateException("validator doesn't is null");
		}
		this.validator = validator;
		return this;
	}
	
	@Override
	public IFormValidator getValidator() {
		return validator;
	}
	
	@Override
	public BindingGroup getBindingGroup() {
		return bindingGroup;
	}
	
	@SuppressWarnings("unchecked")
	public <E> List<E> getFields(Class<E> fieldType) {
		List<E> fields = new ArrayList<E>();
		for (Field field : getClass().getDeclaredFields()) {
			if ( field.getType() == fieldType ) {
				try {
					fields.add((E)field.get(this));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return fields;
	}
	
	@Override
	public Component getField(String name) {
		return (Component) bindingGroup.getBinding(name).getTargetObject();
	}
	
	@Override
	public IForm<T> focus(String name) {		
		try {
			((Component) getField(name)).requestFocus();
		} catch (Exception e) {	}
		return this;
	}
	
}
