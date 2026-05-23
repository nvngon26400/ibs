package com.sbisec.helios.ap.safe;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbisec.helios.ap.safe.utils.SafeException;


/**
 * SafeOkHttpResponse
 *
 * @author nicksen.li
 * @version 1.0
 * @date 3/31/2025
 */
public class SafeOkHttpResponse {

	private ObjectMapper objectMapper;

	public SafeOkHttpResponse() {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	// リクエスト成功 => true:成功；false:失敗
	private Boolean successful;
	// HTTP状態 => 200:成功
	private Integer statusCode;
	// 応答の結果
	private String responseData;

	public Boolean getSuccessful() {
		return successful;
	}

	public void setSuccessful(Boolean successful) {
		this.successful = successful;
	}

	/**
	 * @return the statusCode
	 */
	public Integer getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getResponsData() {
		return responseData;
	}

	public <T> T getResponseData(Class<T> clazz) throws SafeException {
		try {
			return objectMapper.readValue(this.responseData, clazz);
		} catch (Exception e) {
			throw new SafeException(e);
		}
	}

	public void setResponseData(String responseMsg) {
		this.responseData = responseMsg;
	}

}
