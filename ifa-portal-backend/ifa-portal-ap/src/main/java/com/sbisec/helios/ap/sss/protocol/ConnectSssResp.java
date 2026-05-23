package com.sbisec.helios.ap.sss.protocol;

public class ConnectSssResp {
	
	public ConnectSssResp() {
	}

	// token
	private String code;
	// エラーコード
	private String errorCode;
	// エラーメッセージ
	private String errorMessage;


	//コード
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	// エラーコード
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	// エラーメッセージ
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

