package com.sbisec.helios.ap.athena.utils;

/**
 * Exception handling class of athena
 * 
 * @author shuchen.xin
 *
 */
public class AthenaException extends Exception {
	
	private String title ;
	
	private String message;

	private String content;

	public AthenaException() {
		super();
	}

	public AthenaException(String msg) {
		super(msg);
	}

	public AthenaException(String msg, Throwable thro) {
		super(msg, thro);
	}

	public AthenaException(Throwable thro) {
		super(thro);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
