package com.sbisec.helios.ap.athena.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpClientWrapper;
import com.sbisec.helios.ap.athena.OkHttpRequest;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.athena.utils.AthenaConfig;
import com.sbisec.helios.ap.athena.utils.AthenaException;

/**
 * AbstractBaseService.
 * 
 * @author shuchen.xin
 * @date 01/12/2022
 */
public abstract class AbstractBaseService {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractBaseService.class);

	/** Comet api response error message key */
	private static final String COMET_ERROR_MSG_KEY_MESSAGE = "message";
	private static final String COMET_ERROR_MSG_KEY_ERRORCODE = "errorCode";
	private static final String COMET_ERROR_MSG_KEY_STATUSCODE = "statusCode";

	/** Final length */
	protected static final int BUTENCODE_MAX_LENGTH = 3;// 部店コード
	protected static final int ACCOUNTNO_MAX_LENGTH = 7;// 口座番号
	protected static final int SECURITIESCODE_MAX_LENGTH = 10;// 銘柄コード
	protected static final int SECURITIES_SEARCHKEYWORD_MAX_LENGTH = 128;// 外国株式銘柄検索用キーワードMAX

	// 注文数量(MIN、MAX)
	protected static final BigDecimal AMOUNT_MIN_VALUE = BigDecimal.ZERO;
	protected static final BigDecimal AMOUNT_MAX_VALUE = new BigDecimal("9999999999");
	// 注文単価(MIN、MAX)
	protected static final BigDecimal ORDERPRICE_MIN_VALUE = BigDecimal.ZERO;
	protected static final BigDecimal ORDERPRICE_MAX_VALUE = new BigDecimal("999999999.99999999");
	// 発火条件価格(MIN、MAX)
	protected static final BigDecimal STOPPRICE_MIN_VALUE = BigDecimal.ZERO;
	protected static final BigDecimal STOPPRICE_MAX_VALUE = new BigDecimal("999999999.99999999");
	// トレールストップ幅(MIN、MAX)
	protected static final BigDecimal TRAILINGSTOPAMOUNT_MIN_VALUE = BigDecimal.ZERO;
	protected static final BigDecimal TRAILINGSTOPAMOUNT_MAX_VALUE = new BigDecimal("9999999999999.9999");
	// 成行基準価格(MIN、MAX)
	protected static final BigDecimal NOLIMITPRICE_MIN_VALUE = BigDecimal.ZERO;
	protected static final BigDecimal NOLIMITPRICE_MAX_VALUE = new BigDecimal("999999999.99999999");
	// 振替金額(MIN、MAX)
	protected static final BigDecimal TRANSFERAMOUNT_MIN_VALUE = BigDecimal.ZERO;
	protected static final BigDecimal TRANSFERAMOUNT_MAX_VALUE = new BigDecimal("9999999999999.9999");
	// 振替数量(MIN、MAX)
	protected static final BigDecimal QUANTITY_MIN_VALUE = BigDecimal.ZERO;
	protected static final BigDecimal QUANTITY_MAX_VALUE = new BigDecimal("999999999999");
	// 新規建単価「外貨/円貨」(MIN、MAX)
	protected static final BigDecimal FRNPOSITIONPRICE_MIN_VALUE = BigDecimal.ZERO;
	protected static final BigDecimal FRNPOSITIONPRICE_MAX_VALUE = new BigDecimal("999999999999.9999");
	// 建単価(MIN、MAX)
	protected static final BigDecimal POSITION_PRICE_MIN_VALUE = BigDecimal.ZERO;
	protected static final BigDecimal POSITION_PRICE_MAX_VALUE = new BigDecimal("999999999.99999999");
	// 代用掛目(MIN、MAX)
	protected static final BigDecimal ASSESSMENT_RATE_MIN_VALUE = BigDecimal.ZERO;
	protected static final BigDecimal ASSESSMENT_RATE_MAX_VALUE = new BigDecimal("999");
	// 評価単価(MIN、MAX)
	protected static final BigDecimal EVALUATION_PRICE_MIN_VALUE = BigDecimal.ZERO;
	protected static final BigDecimal EVALUATION_PRICE_MAX_VALUE = new BigDecimal("99999999999999999");
	/** Final others */
	protected static final String REQUEST_HEADER_INVALIDPARAM = StringUtil.EMPTY_STRING;
	protected static final String FORMAT_YEAR_MONTH_DAY_DASH = "yyyy-MM-dd";
	protected static final String REGEX = "[a-zA-Z0-9]{3}-\\d{7}";
	/** Check error msg */
	protected static final String MSG_REQUEST_IS_NULL = "Request is null!";
	protected static final String MSG_TOKEN_FORMAT = "The format of the token is incorrect!";

	/**
	 * Get url address
	 * 
	 * @param api 必須『COMET／FLARE API - interface - Api』
	 * @return
	 */
	protected final String getUrl(String api) {
		return AthenaConfig.getHostProtocol() + "://" + AthenaConfig.getHostIp() + ":" + AthenaConfig.getHostPort()
				+ api;
	}

	/**
	 * tokenの正確性を確認する.
	 * 
	 * @param token
	 * @return チェック結果
	 */
	protected final boolean checkToken(String token) {
		Pattern noCheck = Pattern.compile(REGEX);
		Matcher noMatcher = noCheck.matcher(token);
		// 入力ルールに合わなければ、falseを設定する
		if (!noMatcher.matches()) {
			return false;
		} else {
			// 入力ルールに合えば、trueを設定する
			return true;
		}
	}

	/**
	 * 
	 * @param url API
	 * @param req 検索条件</BR>
	 * req.getHeader() 『Header』</BR>
	 * req.getParameter() 『パラメータ』
	 * @return OkHttpResponse
	 * @throws Exception
	 */
	protected final OkHttpResponse get(String url, BaseRequest req) throws Exception {

		// Create Athena's request protocol class
		OkHttpRequest request = new OkHttpRequest();
		// Set URL
		request.setUrl(url);
		// Set request header
		request.setHeaders(req.getHeader());
		// Set request parameters
		request.setParameters(req.getParameter());
		// Execute get request
		OkHttpResponse res = OkHttpClientWrapper.getInstance().get(request);

		// Check Response
		checkResponseExcrption(res);

		// Return response.
		return res;

	}

	/**
	 * 
	 * @param url API
	 * @param req 検索条件</BR>
	 * req.getHeader() 『Header』</BR>
	 * req.getParameter() 『パラメータ』
	 * @return OkHttpResponse
	 * @throws Exception
	 */
	protected final OkHttpResponse post(String url, BaseRequest req) throws Exception {

		// Create Athena's request protocol class
		OkHttpRequest request = new OkHttpRequest();
		// Set URL
		request.setUrl(url);
		// Set request header
		request.setHeaders(req.getHeader());
		// Set request parameters
		request.setParameters(req.getParameter());
		// Execute get request
		OkHttpResponse res = OkHttpClientWrapper.getInstance().post(request);

		// Check Response
		checkResponseExcrption(res);

		// Return response.
		return res;
	}

	/**
	 * 
	 * @param url API
	 * @param req 検索条件</BR>
	 * req.getHeader() 『Header』</BR>
	 * req.getParameter() 『パラメータ』
	 * @return OkHttpResponse
	 * @throws Exception
	 */
	protected final OkHttpResponse put(String url, BaseRequest req) throws Exception {

		// Create Athena's request protocol class
		OkHttpRequest request = new OkHttpRequest();
		// Set URL
		request.setUrl(url);
		// Set request header
		request.setHeaders(req.getHeader());
		// Set request parameters
		request.setParameters(req.getParameter());
		// Execute get request
		OkHttpResponse res = OkHttpClientWrapper.getInstance().put(request);

		// Check Response
		checkResponseExcrption(res);

		// Return response.
		return res;
	}

	/**
	 * 
	 * @param url API
	 * @param req 検索条件</BR>
	 * req.getHeader() 『Header』</BR>
	 * req.getParameter() 『パラメータ』
	 * @return OkHttpResponse
	 * @throws Exception
	 */
	protected final OkHttpResponse delete(String url, BaseRequest req) throws Exception {

		// Create Athena's request protocol class
		OkHttpRequest request = new OkHttpRequest();
		// Set URL
		request.setUrl(url);
		// Set request header
		request.setHeaders(req.getHeader());
		// Set request parameters
		request.setParameters(req.getParameter());
		// Execute get request
		OkHttpResponse res = OkHttpClientWrapper.getInstance().delete(request);

		// Check Response
		checkResponseExcrption(res);

		// Return response.
		return res;
	}

	@SuppressWarnings("unchecked")
	private void checkResponseExcrption(OkHttpResponse res) throws Exception {
		if (null == res) {
			LOG.warn("Comet api Response is null!");
			throw new AthenaException("Response is null!");
		}
		if (!res.getSuccessful()) {
			if (res.getStatusCode() >= 500 && res.getStatusCode() < 600) { 
				LOG.error("Comet Exception request failed:{}", res.getResponsData());
			} else {
				LOG.info("Comet api request failed:{}", res.getResponsData());
			}
			Map<String, Object> errorMsg = res.getResponseData(Map.class);
			AthenaBusinessException abe = new AthenaBusinessException();
			abe.setMessage(errorMsg.containsKey(COMET_ERROR_MSG_KEY_MESSAGE)
					? (String) errorMsg.get(COMET_ERROR_MSG_KEY_MESSAGE)
					: null);
			abe.setErrorCode(errorMsg.containsKey(COMET_ERROR_MSG_KEY_ERRORCODE)
					? (String) errorMsg.get(COMET_ERROR_MSG_KEY_ERRORCODE)
					: null);
			abe.setStatusCode(errorMsg.containsKey(COMET_ERROR_MSG_KEY_STATUSCODE)
					? (Integer) errorMsg.get(COMET_ERROR_MSG_KEY_STATUSCODE)
					: null);
			throw abe;
		}
	}

	/**
	 * @Description 金額や数量等の範囲チェック
	 * 
	 * @param target 金額や数量
	 * @param min 下限値
	 * @param max 上限値
	 * @return TRUE：チェック成功； FALSE：チェック失敗
	 */
	protected boolean checkRange(BigDecimal target, BigDecimal min, BigDecimal max) {

		if (target != null && min != null && min.compareTo(target) == 1) {
			// 下限値より、小さいの場合
			return false;
		}

		if (target != null && max != null && target.compareTo(max) == 1) {
			// 上限値を超える場合
			return false;
		}

		return true;
	}
}
