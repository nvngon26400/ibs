package com.sbisec.helios.ap.broker.util;

/**
 * COMET API Util.
 * 
 * @author katsuhiko.kagoshima
 * @date 12/23/2022
 */
public class PapyApiReturnCode {

	/** Papy api error */
	public static final String PAPY_API_ERROR_CODE = "papy_api_error";
	/** Papy api warning*/
	public static final String PAPY_API_WARNING_CODE = "papy_api_warning";
	/** Papy connection error*/
	public static final String PAPY_API_CONNECTION_ERROR_CODE = "papy_connection_error";

	/** HTTP Status Code */
	public class HttpStatusCode {
		/** OK */
		public static final int HTTP_200_CODE = 200;
		/** Bad Request */
		public static final int HTTP_400_CODE = 400;
		/** Forbidden */
		public static final int HTTP_403_CODE = 403;
		/** Not Found */
		public static final int HTTP_404_CODE = 404;
		/** Request Timeout */
		public static final int HTTP_408_CODE = 408;
		/** Internal Server */
		public static final int HTTP_500_CODE = 500;
		/** Service Unavailable */
		public static final int HTTP_503_CODE = 503;
	}
	
	/** メッセージ code */
	public class MsgCode {
		/** エラー:PAPYに接続できません。*/
		public static final String ERRORS_PAPY_API_CONNECTION = "errors.papyApiConnection";
	}

}
