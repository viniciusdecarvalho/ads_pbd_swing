package br.edu.ftlf.ads.pbd.bean.validator;

import java.util.List;

public interface BeanValidator {

	void validate(Object bean);

	boolean hasErrors();

	List<ValidationError> getErrors();

}