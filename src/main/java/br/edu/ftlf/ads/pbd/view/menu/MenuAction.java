package br.edu.ftlf.ads.pbd.view.menu;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuAction {

	String text();
	String root();	
	String icon() default "";
	int mnemonic() default -1;

}
