package com.sbisec.helios.ap.safe.utils;

/**
 * Exception of SafeApi
 * 
 * @author nicksen.li
 *
 */
public class SafeException extends Exception {
	
	private static final long serialVersionUID = 7691649771878162037L;

    private String title ;
	
	private String message;

	private String content;

	public SafeException() {
		super();
	}

	public SafeException(String msg) {
		super(msg);
	}

	public SafeException(String msg, Throwable thro) {
		super(msg, thro);
	}

	public SafeException(Throwable thro) {
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
