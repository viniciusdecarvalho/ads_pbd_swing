package br.edu.ftlf.ads.pbd.bean.validator;

import java.util.List;

public class BeanValidatorException extends IllegalStateException {

	private static final long serialVersionUID = 6320858029532396613L;

	private List<ValidationError> errors;

	public BeanValidatorException(BeanValidator validator) {
		this.errors = validator.getErrors();
	}

	public List<ValidationError> getErrors() {
		return errors;
	}

}