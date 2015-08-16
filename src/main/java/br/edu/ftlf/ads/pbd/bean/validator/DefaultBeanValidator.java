package br.edu.ftlf.ads.pbd.bean.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

@Component
public class DefaultBeanValidator implements BeanValidator {

	private Validator validator;
	private Set<ConstraintViolation<Object>> errors;

	public DefaultBeanValidator() {
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Override
	public void validate(Object bean) {
		errors = validator.validate(bean);
		if (hasErrors()) {
			throw new BeanValidatorException(this);
		}
	}

	@Override
	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	@Override
	public List<ValidationError> getErrors() {
		List<ValidationError> violations = new ArrayList<>(errors.size());
		Iterator<ConstraintViolation<Object>> it = errors.iterator();
		if (it.hasNext()) {
			ConstraintViolation<Object> violation = it.next();
			String name = violation.getPropertyPath().iterator().next().getName();
			String message = violation.getMessage();
			violations.add(new ValidationError(name, message));
		}
		return violations;
	}

}
