package br.edu.ftlf.ads.pbd.view.form.validator;

import java.lang.reflect.Field;
import java.util.List;

import javax.validation.Validator;


public interface IFormValidator {

	void validate(Object target, Object beanRules) throws InvalidFormValuesException;
	
	void validate(Object target, Object beanRules, String propertyName) throws InvalidFormValuesException;

	List<Field> getFields(Object target);

	Validator getValidator();

	void setValidator(Validator validator);

}