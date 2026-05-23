package com.sbisec.helios.ap.athena.model;

import com.sbibits.earth.model.ModelBase;

/**
 * Athenaエラーメッセージマスタ Model
 * 
 * @author SCSK
 *
 */
public class AthenaErrorMessageModel extends ModelBase {

	private static final long serialVersionUID = -1L;

	// APIエラーコード
	private String apiErrorCode;
	// エラーメッセージ
	private String errorMessage;

	public String getApiErrorCode() {
		return apiErrorCode;
	}

	public void setApiErrorCode(String apiErrorCode) {
		this.apiErrorCode = apiErrorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}

