package br.edu.ftlf.ads.pbd.bean.validator;

public final class ValidationError {
	
	private final String property;
	private final String message;

	public ValidationError(String property, String message) {
		this.property = property;
		this.message = message;
	}

	public String getProperty() {
		return property;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return String.format("propertyName: %s - errorMessage: %s", property, message);
	}
}