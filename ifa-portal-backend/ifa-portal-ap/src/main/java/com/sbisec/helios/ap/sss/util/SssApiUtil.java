package com.sbisec.helios.ap.sss.util;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;

public class SssApiUtil<T> {
	/**
	 * 業務異常の処理
	 * @param dataList 画面変数DTO
	 * @param exception 例外
	 * @return httpStatusCode
	 */
	public static <T> DataList<T> getValidationException(DataList<T> dataList, Exception exception) {

		String sssErrorCode = null;
		String errorCode = null;
		String errorMessage = null;

		// SssValidationExceptionの場合はSSS-APIのエラー
		// それ以外の例外は接続エラー
		if (exception instanceof SssValidationException) {

			// エラーコードを設定する
			errorCode = ((SssValidationException) exception).getErrorCode();
			sssErrorCode = SssApiReturnCode.SSS_API_ERROR_CODE;
			dataList.setErrorLevel(ErrorLevel.FATAL.getId());

			// エラーメッセージを設定する
			errorMessage = ((SssValidationException) exception).getErrorMessage();

		}else {
			dataList.setErrorLevel(ErrorLevel.WARNING.getId());

			// 接続エラー・接続エラーメッセージを設定
			sssErrorCode = SssApiReturnCode.SSS_API_CONNECTION_ERROR_CODE;
			errorMessage = SssApiReturnCode.MsgCode.ERRORS_SSS_API_CONNECTION;

		}

		dataList.setTitle(errorCode);
		dataList.setMessage(errorMessage);	
		dataList.setReturnCode(sssErrorCode);

		return dataList;
	}

	public class ErrorFlg {
		/**SSS認証画面へ遷移*/
		public static final String SCREEN_ID_SUCCESS = "success";
		/** SSS認証エラー画面へ遷移*/
		public static final String SCREEN_ID_ERROR = "error";
		/** SSS認証ワーニング画面へ遷移*/
		public static final String SCREEN_ID_WARNING = "warning";
	}
}