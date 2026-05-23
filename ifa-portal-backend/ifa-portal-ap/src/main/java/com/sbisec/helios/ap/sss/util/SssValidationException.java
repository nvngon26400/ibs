package com.sbisec.helios.ap.sss.util;

/**
 * Exception handling class of sss
 * 
 * @author toma.hayashi
 *
 */
public class SssValidationException extends Exception {

	public SssValidationException() {
		super();
	}

	public SssValidationException(String msg) {
		super(msg);
	}

	public SssValidationException(String msg, Throwable thro) {
		super(msg, thro);
	}

	public SssValidationException(Throwable thro) {
		super(thro);
	}

	private String errorMessage;
	private String errorCode;

	/**
	 * @return the message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param message the message to set
	 */
	public void setErrorMessage(String message) {
		this.errorMessage = message;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
