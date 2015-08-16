package br.edu.ftlf.ads.pbd.bean.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EnumSetValidator.class)
@Target(value= { METHOD, FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyEnumSet {

	String message() default "Um dos itens deve ser selecionado";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
