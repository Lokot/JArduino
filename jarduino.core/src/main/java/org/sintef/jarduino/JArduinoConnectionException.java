package org.sintef.jarduino;

public class JArduinoConnectionException extends Exception {

	private static final long serialVersionUID = 3273204798091181139L;

	public JArduinoConnectionException() {
		super();
	}

	public JArduinoConnectionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public JArduinoConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public JArduinoConnectionException(String message) {
		super(message);
	}

	public JArduinoConnectionException(Throwable cause) {
		super(cause);
	}

}
