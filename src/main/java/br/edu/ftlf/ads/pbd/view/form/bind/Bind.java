package br.edu.ftlf.ads.pbd.view.form.bind;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.jdesktop.beansbinding.Converter;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bind {
    
    String expression() default "";
    
    String property() default "";
    
    String name() default "";
    
    boolean validate() default true; 
    
    String whenSourceIsNull() default "";
    
    String whenTargetIsNull() default "";
    
    String strategy() default "READ_WRITE";
    
    @SuppressWarnings("rawtypes")
	Class<? extends Converter> converter() default Converter.class;
}
