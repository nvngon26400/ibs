package com.sbisec.helios.ap.sss.util;

/**
 * Exception handling class of sss
 * 
 * @author toma.hayashi
 *
 */
public class SssConnectionException extends Exception {
	
	private String title ;
	
	private String errorMessage;

	private String content;

	public SssConnectionException() {
		super();
	}

	public SssConnectionException(String msg) {
		super(msg);
	}

	public SssConnectionException(String msg, Throwable thro) {
		super(msg, thro);
	}

	public SssConnectionException(Throwable thro) {
		super(thro);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String message) {
		this.errorMessage = message;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
