package com.sbisec.helios.ap.common.exception;

import com.sbisec.helios.ap.common.enums.ErrorLevel;

public class ApiError extends FowardException {

	private static final long serialVersionUID = -6666482182809662715L;
	private String code = "";
	private String message = "";
	private ErrorLevel errorLevel;
	private String shubetu ="";


	/**
	 * Fatal時のコンストラクタ
	 * @param code
	 * @param message
	 */
	public ApiError(String code, String message) {

		super("ApiError at API call. code:[" + code + "], message:[" + message + "]");

		this.code = code;
		this.message = message;
	}
	/**
	 * コンストラクタ
	 * @param errorLevel
	 * @param code
	 * @param message
	 */
	public ApiError(ErrorLevel errorLevel, String code, String message) {

		super("ApiError at API call. [" + errorLevel + "] code:[" + code + "], message:[" + message + "]");

		this.code = code;
		this.message = message;
		this.errorLevel = errorLevel;
	}
	
	/**
	 * トラクタ
	 * @param code
	 * @param message
	 * @param errorLevel
	 * @param shubetu
	 */
	public ApiError( ErrorLevel errorLevel, String shubetu, String code, String message) {
		super("ApiError at API call. [" + errorLevel + "] shubetu:[" + shubetu + "], code:[" + code +  "], message:[" + message + "]");
		this.code = code;
		this.message = message;
		this.errorLevel = errorLevel;
		this.shubetu = shubetu;
	}
	
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public ErrorLevel getErrorLevel() {
		return errorLevel;
	}
	

	public String getShubetu() {
		return shubetu;
	}
	
	public String toString() {
		return super.getMessage();
	}
}
