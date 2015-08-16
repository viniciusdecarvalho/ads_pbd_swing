package br.edu.ftlf.ads.pbd.view.grid.annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @autor viniciusdecarvalho
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GridColumn {
	
	/**
	 * @return Posicao da coluna da esquerda pra direita
	 */
	int index();

	/**
	 * @return Nome da Coluna
	 */
	String name() default "";
	
	/**
	 * 
	 * @return se a coluna pode ser editada
	 */
	boolean editable() default false;
	
	/**
	 * 
	 * @return se a coluna pode ter seu tamanho alterado
	 */
	boolean resizable() default true;
	
	/**
	 * @return tamanho da coluna proporcional ao total de colunas
	 * TODO ainda nao implementado 
	 */
	double flex() default 1;
	
	/**
	 * @return alinhamento horizontal do valor do campo
	 */
	HAlign hAlign() default HAlign.CENTER;
	
	/**
	 * @return alinhamento vertical do valor do campo
	 */
	VAlign vAlign() default VAlign.CENTER;

	/**
	 * 
	 * @return O estado padrao de visibilidade da coluna
	 */
	boolean visible() default true;

}