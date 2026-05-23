package com.sbisec.helios.ap.common.exception;

public class ApiConnectionException extends Exception {

	private static final long serialVersionUID = -5866471397166867088L;

    public ApiConnectionException() {
        super();
    }

    public ApiConnectionException(String message) {
        super(message);
    }

    public ApiConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

	public ApiConnectionException(Throwable cause) {
		super(cause);
	}
}
