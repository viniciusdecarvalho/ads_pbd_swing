package br.edu.ftlf.ads.pbd.view.form.validator;

public class InvalidFormValuesException extends IllegalStateException {

	private static final long serialVersionUID = 1L;

	public InvalidFormValuesException(String message) {
		super(message);
	}
	
	public InvalidFormValuesException(Throwable e) {
		super(e.getCause());
	}
	
	public InvalidFormValuesException(String message, Throwable e) {
		super(message, e.getCause());
	}
	
	public InvalidFormValuesException() {
		super();
	}
}