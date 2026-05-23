package com.sbisec.helios.ap.common.constants;

/**
 * Set all common variables in the program.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
public class AppConstants {

    public static final String ENV_ID_DEV = "DEV";
    public static final String ENV_ID_UAT = "UAT";
    public static final String ENV_ID_PROD = "PROD";

    public static final String ENCODE = "UTF-8";
    public static final String RESPONSE_ENCODE = "application/json;charset=UTF-8";

    public static final String URL_ALL = "/*";
    public static final String URL_EXT_ALL = "/ext/*";
    public static final String URL_INT_ALL = "/int/*";
    public static final String URL_INT_API_ALL = "/int/api/*";
    public static final String URL_EXT_LOGIN = "/ext/login";
    public static final String URL_EXT_LOGOUT = "/ext/logout";

    public static final String FLG_ON = "1";
    public static final String FLG_OFF = "0";

    public static final String JWT_PAYLOAD_USER_KEY = "userId";

    public static final int DEFAULT_KEY_SIZE = 2048;
    public static final String DEFAULT_KEY_TYPE = "RSA";
    public static final String DEFAULT_TOKEN_PREFIX = "Bearer ";

    public static final String INIT_KEY_PERMISSION = "permissions";
    public static final String INIT_KEY_UUID = "uuid";

    public static final String LOGIN_USERID = "userId";
    public static final String LOGIN_PASSWORD = "password";
    public static final String COOKIES_ID = "JSESSIONID";
    public static final String API_TOKEN = "apiToken";
    public static final String API_BATCHID = "batch_id";

    public static final String APP_VERSION = "Version";

    public static final String ALL_PERMISSION = "*:*:*";
    
    public static final int DAY_0 = 0;

	/** ログコンテキストフォーマット（Thread ID） */
	public static final String MDC_FORMAT_TID = "TID{0}";
	/** ログコンテキストフォーマット（Function ID） */
	public static final String MDC_FORMAT_FID = "{0}:{1}";
	/** ログコンテキストフォーマット（Inheriting ID） */
	public static final String MDC_FORMAT_INH_ID = "{0}|{1}";
}
