package com.sbisec.helios.ap.sss.model;

import com.sbibits.earth.model.ModelBase;

public class SssCertificationInfoModel extends ModelBase{
	
	/**
	 * シリアルバージョンID
	 */
	private static final long serialVersionUID = 1L;

	// code
	private String code;
	// エラーコード
	private String errorCode;
	// エラーメッセージ
	private String errorMessage;


	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
