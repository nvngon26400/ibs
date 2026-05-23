package com.sbisec.helios.ap.athena.utils;

/**
 * Business exception handling class of athena
 * 
 * @author shuchen.xin
 * @date 04/12/2022
 */
public class AthenaBusinessException extends Exception {

	public AthenaBusinessException() {
		super();
	}

	public AthenaBusinessException(String msg) {
		super(msg);
	}

	public AthenaBusinessException(String msg, Throwable thro) {
		super(msg, thro);
	}

	public AthenaBusinessException(Throwable thro) {
		super(thro);
	}

	private String message;
	private String errorCode;
	private Integer statusCode;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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

	/**
	 * @return the statusCode
	 */
	public Integer getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

}
