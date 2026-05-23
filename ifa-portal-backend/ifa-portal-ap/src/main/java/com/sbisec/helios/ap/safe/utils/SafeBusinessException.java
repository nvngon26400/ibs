package com.sbisec.helios.ap.safe.utils;

import com.sbisec.helios.ap.safe.common.exception.enums.ErrorLevel;
import com.sbisec.helios.ap.safe.common.exception.enums.ServiceType;

/**
 * Business exception handling class of safe
 * 
 * @author nicksen.li
 * @date 03/31/2025
 */
public class SafeBusinessException extends Exception {
	
	private static final long serialVersionUID = 1L;

    public SafeBusinessException() {
		super();
	}

	public SafeBusinessException(String msg) {
		super(msg);
	}

	public SafeBusinessException(String msg, Throwable thro) {
		super(msg, thro);
	}

	public SafeBusinessException(Throwable thro) {
		super(thro);
	}

	private String message;
	private String errorCode;
	private Integer statusCode;
	private ErrorLevel errorLevel;
	private ServiceType serviceType;

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
	public ErrorLevel getErrorLevel() {
		return errorLevel;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorLevel(ErrorLevel errorLevel) {
		this.errorLevel = errorLevel;
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
	
	/**
	 * @return the serviceType
	 */
	public ServiceType getServiceType() {
		return serviceType;
	}

	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

}
