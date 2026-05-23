package com.sbisec.helios.ap.sss.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sbisec.helios.ap.sss.SssOkHttpClientWrapper;
import com.sbisec.helios.ap.sss.SssOkHttpRequest;
import com.sbisec.helios.ap.sss.SssOkHttpResponse;
import com.sbisec.helios.ap.sss.protocol.ConnectSssBaseRequest;
import com.sbisec.helios.ap.sss.util.SssConfig;
import com.sbisec.helios.ap.sss.util.SssConnectionException;
import com.sbisec.helios.ap.sss.util.SssValidationException;

/**
 * SssConnectionBaseService .
 * 
 * @author touma.hayashi
 * @date 11/06/2023
 */
public abstract class SssConnectionBaseService {

	private static final Logger LOG = LoggerFactory.getLogger(SssConnectionBaseService.class);

	/** Sss api response error message key */
	private static final String SSS_ERROR_MSG_KEY_MESSAGE = "errorMessage";
	private static final String SSS_ERROR_MSG_KEY_ERRORCODE = "errorCode";

	/**
	 * Get url address
	 * 
	 * @param api 必須『SSS API - interface - Api』
	 * @return
	 */
	protected final String getUrl(String api, String sssType) {
	    String[] ips = SssConfig.getHostIp().split(",");
	    String ip = "";
	    if ("1".equals(sssType)) {
	        ip = ips[0];
	    } else {
	        ip = ips[1];
	    }
		return SssConfig.getHostProtocol() + "://" + ip + ":" + SssConfig.getHostPort()
		+ api;
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
	protected final SssOkHttpResponse post(String url, ConnectSssBaseRequest req) throws Exception {

		// Create Sss's request protocol class
		SssOkHttpRequest request = new SssOkHttpRequest();
		// Set URL
		request.setUrl(url);
		// Set request header
		request.setHeaders(req.getHeader());
		// Set request parameters
		request.setParameters(req.getParameter());
		// Execute get request
		SssOkHttpResponse res = null;

		//API接続でエラーが発生した場合は、SssConnectionExceptionとして処理する。
		try {
			res = SssOkHttpClientWrapper.getInstance().post(request);
		}catch(Exception e) {
			LOG.debug("Api connection failed");
			throw new SssConnectionException();
		}

		// Check Response
		checkResponseExcrption(res);

		// Return response.
		return res;
	}

	@SuppressWarnings("unchecked")
	private void checkResponseExcrption(SssOkHttpResponse res) throws Exception {
		if (null == res) {
			LOG.warn("Sss api Response is null!");
			throw new SssConnectionException("Response is null!");
		}else if(!res.getSuccessful()) {
			LOG.warn("Api connection failed, HTTP Status:[{}]", res.getStatusCode());
			throw new SssConnectionException();
		}else {
			Map<String, Object> responseData = res.getResponseData(Map.class);

			// when SSS_ERROR_MSG_KEY_ERRORCODE is exists 
			//       And SSS_ERROR_MSG_KEY_ERRORCODE value is not null
			// then throw SssValidationException pve
			if((responseData.containsKey(SSS_ERROR_MSG_KEY_ERRORCODE)&&
					(String)responseData.get(SSS_ERROR_MSG_KEY_ERRORCODE) != null)
					|| (responseData.containsKey(SSS_ERROR_MSG_KEY_MESSAGE)&&
							(String)responseData.get(SSS_ERROR_MSG_KEY_MESSAGE) != null)) {

				SssValidationException pve = new SssValidationException();

				// set errorCode
				pve.setErrorCode((String) responseData.get(SSS_ERROR_MSG_KEY_ERRORCODE));

				// set errorMessage 
				pve.setErrorMessage((String) responseData.get(SSS_ERROR_MSG_KEY_MESSAGE));

				LOG.warn("Error Code:{}, Error Message: {}",pve.getErrorCode(),pve.getErrorMessage());
				throw pve;
			}
		}
		LOG.debug("Get sss response data : success");
	}
}
