package br.edu.ftlf.ads.pbd.bean.validator;

import java.util.EnumSet;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumSetValidator implements ConstraintValidator<NotEmptyEnumSet, EnumSet<?>> {

	@Override
	public void initialize(NotEmptyEnumSet annotation) {
		
	}

	@Override
	public boolean isValid(EnumSet<?> enumSet, ConstraintValidatorContext context) {
		
		String message = context.getDefaultConstraintMessageTemplate();
		context.buildConstraintViolationWithTemplate(message)
			.addConstraintViolation()
			.disableDefaultConstraintViolation();
		
		return !enumSet.isEmpty();
	}

}
