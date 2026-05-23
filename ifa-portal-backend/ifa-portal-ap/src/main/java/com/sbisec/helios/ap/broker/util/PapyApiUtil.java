package com.sbisec.helios.ap.broker.util;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PapyCodeCheck;

public class PapyApiUtil<T> {
	/**
	 * 業務異常の処理
	 * @param dataList 画面変数DTO
	 * @param exception 例外
	 * @return httpStatusCode
	 */
	public static <T> DataList<T> getValidationException(DataList<T> dataList , Exception exception) {

		String papyErrorCode = null;
		String errorCode = null;
		String errorMessage = null;
		
		// PapyValidationExceptionの場合はPAPY-APIのエラー
		// それ以外の例外は接続エラー
		if (exception instanceof PapyValidationException) {
			
			// エラーコードからエラーレベルを設定する
			errorCode = ((PapyValidationException) exception).getErrorCode();
			// warningレベルに設定されているエラーコードの場合は、warningレベルを設定する
			if(PapyCodeCheck.Info.getCodeList().contains(errorCode)) {
				papyErrorCode = PapyApiReturnCode.PAPY_API_WARNING_CODE;
				dataList.setErrorLevel(ErrorLevel.WARNING.getId());
			}else{
				papyErrorCode = PapyApiReturnCode.PAPY_API_ERROR_CODE;
				dataList.setErrorLevel(ErrorLevel.FATAL.getId());
			}
			
			// エラーメッセージを設定する
			errorMessage = ((PapyValidationException) exception).getErrorMessage();
		
		}else {
			dataList.setErrorLevel(ErrorLevel.WARNING.getId());

			// 接続エラー・接続エラーメッセージを設定
			papyErrorCode = PapyApiReturnCode.PAPY_API_CONNECTION_ERROR_CODE;
			errorMessage = PapyApiReturnCode.MsgCode.ERRORS_PAPY_API_CONNECTION;
		
		}

		dataList.setTitle(errorCode);
		dataList.setMessage(errorMessage);	
		dataList.setReturnCode(papyErrorCode);
		
		return dataList;
	}
	
	public class ErrorFlg {
		/** 印影照会画面へ遷移*/
		public static final String SCREEN_ID_SUCCESS = "success";
		/** 印影照会エラー画面へ遷移*/
		public static final String SCREEN_ID_ERROR = "error";
		/** 印影照会ワーニング画面へ遷移*/
		public static final String SCREEN_ID_WARNING = "warning";
	}
}