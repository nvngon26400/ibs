package com.sbisec.helios.ap.sss.util;

/**
 * SSS API Util.
 * 
 * @author toma.hayashi
 * @date 11/07/2023
 */
public class SssApiReturnCode {

	/** Sss api error */
	public static final String SSS_API_ERROR_CODE = "sss_api_error";
	/** Sss connection error*/
	public static final String SSS_API_CONNECTION_ERROR_CODE = "sss_connection_error";

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
		/** エラー:SSSに接続できません。*/
		public static final String ERRORS_SSS_API_CONNECTION = "errors.sssApiConnection";
	}

}
