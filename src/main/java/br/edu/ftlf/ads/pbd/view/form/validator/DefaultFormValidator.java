package br.edu.ftlf.ads.pbd.view.form.validator;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import br.edu.ftlf.ads.pbd.view.form.bind.Bind;

public class DefaultFormValidator implements IFormValidator {

	private Validator validator;

	public DefaultFormValidator() {
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	public void validate(Object target, Object beanRules) throws InvalidFormValuesException {
		validadeProperties(target, beanRules, resolveFields(target, true));
	}

	@Override
	public void validate(Object target, Object bean, String propertyName)
			throws InvalidFormValuesException {
		validadeProperties(target, bean, resolveField(target, propertyName));		
	}
	
	@Override
	public List<Field> getFields(Object target) {
		return Arrays.asList(resolveFields(target, true));
	}
	
	@Override
	public Validator getValidator() {
		return validator;
	}
	
	@Override
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
	private void validadeProperties(Object target, Object bean, final Field... fields) {
		boolean valid = true;
		
		System.out.println(bean);
		
		for (Field field : fields) {

			String beanProperty = field.getAnnotation(Bind.class).property();
			
			final Set<ConstraintViolation<Object>> beanViolations = validator.validateProperty(bean, beanProperty);
			final Set<ConstraintViolation<Object>> targetViolations = validator.validateProperty(target, field.getName());
			
			beanViolations.addAll(targetViolations);
			
			JComponent component = null;
			try {
				component = (JComponent) field.get(target);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				Logger.getLogger("FormBinder").log(Level.SEVERE, "can't validate field {0}", field.getName());
				e.printStackTrace();
				continue;
			}
			
			if (!beanViolations.isEmpty()) {				
				valid = false;
				final StringBuilder messages = new StringBuilder();
				for (ConstraintViolation<Object> violation : beanViolations) {
					messages.append(violation.getMessage()).append("\n");
				}
				component.setToolTipText(messages.toString());
				component.setBorder(new LineBorder(Color.RED));
				component.addFocusListener(new BorderListener());
			} else {
				component.setToolTipText("");
				updateBorder(component);
			}
		}

		if (!valid) {
			throw new InvalidFormValuesException("O formulario possui campos invalidos.");
		}
	}

	private Field[] resolveFields(Object target, boolean asAccessible) {
		final List<Field> fields = new ArrayList<Field>(0);
		for (Field field : target.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(Bind.class) && field.getAnnotation(Bind.class).validate()) {
				if (!field.isAccessible() && asAccessible)
					field.setAccessible(true);
				fields.add(field);
			}
		}
		return fields.toArray(new Field[0]);
	}
	
	private Field resolveField(Object target, String propertyName) {
		for (Field field : resolveFields(target, true)) {
			String property = field.getAnnotation(Bind.class).property();
			if (property.equals(propertyName)) {
				return field;
			}
		}
		throw new RuntimeException("validate property name not found");
	}

	private class BorderListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent event) {
			final JComponent component = (JComponent) event.getSource();
			updateBorder(component);
			component.setToolTipText(null);
		}
	}
	
	private void updateBorder(final JComponent component) {
		if (component instanceof JComboBox) {
			component.setBorder(UIManager.getBorder("ComboBox.border"));
		} else if (component instanceof JTextComponent) {
			component.setBorder(UIManager.getBorder("TextField.border"));
		} else {
			component.setBorder(null);
		}
	}

}
