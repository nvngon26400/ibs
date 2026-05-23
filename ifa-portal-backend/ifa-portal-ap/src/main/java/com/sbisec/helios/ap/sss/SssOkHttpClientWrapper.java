package com.sbisec.helios.ap.sss;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.sss.util.SssConfig;
import com.sbisec.helios.ap.sss.util.SssConnectionException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * <p>
 * Description:OkHttpClientWrapper
 * </p>
 *
 * @author toma.hayashi
 * @version 1.0
 * @date 11/08/2023
 */
public class SssOkHttpClientWrapper implements Cloneable {

	private static class OkHttpClientWrapperInternal {
		private static final SssOkHttpClientWrapper INSTANCE = new SssOkHttpClientWrapper();
	}

	private static volatile boolean isCreate = false;

	private ObjectMapper objectMapper = new ObjectMapper();

	private final String ENCODE = "UTF-8";

	/** JSON変換クラスのインスタンス */
	protected JsonConverter jc = JsonConverter.getInstance();

	/** ロガー */
	private static final Logger logger = LoggerFactory.getLogger(SssOkHttpClientWrapper.class);

	private SssOkHttpClientWrapper() {
		if (isCreate)
			throw new RuntimeException("Cannot instantiate repeatedly!");
		isCreate = true;

		SssOkHttpClientManager.getInstance().setConnTimeout(SssConfig.getHttpClientConnectTimeout())
		.setReadTimeout(SssConfig.getHttpClientReadTimeout())
		.setWriteTimeout(SssConfig.getHttpClientWriteTimeout())
		.setConnPoolTimeout(SssConfig.getHttpClientConnPoolTimeout())
		.setConnPoolCachedMax(SssConfig.getHttpClientConnPoolCachedMax())
		.setRetryCount(SssConfig.getHttpClientRetryCount());
	}

	public static final SssOkHttpClientWrapper getInstance() {
		return OkHttpClientWrapperInternal.INSTANCE;
	}

	/**
	 * @param sssOkHttpRequest
	 * @return
	 * @throws SssConnectionException
	 */
	public SssOkHttpResponse get(SssOkHttpRequest sssOkHttpRequest) throws SssConnectionException {
		// Check the required items
		String url = sssOkHttpRequest.getUrl();
		// url non empty check
		if (StringUtil.isNullOrEmpty(url)) {
			logger.error("Url is null or empty!");
			throw new SssConnectionException("Url is null or empty!");
		}
		logger.info("Sss Api Url From GET:{}" , url);

		// Create a URL wrapper class
		HttpUrl.Builder urlBuilder = HttpUrl.Companion.parse(url).newBuilder();

		// Add parameters if exists
		if (null != sssOkHttpRequest.getParameters() && !sssOkHttpRequest.getParameters().isEmpty()) {
			try {
				for (String key : sssOkHttpRequest.getParameters().keySet()) {

					Object obj = sssOkHttpRequest.getParameters().get(key);
					if (obj instanceof List) {
						// パラメータが配列の場合、
						for (String str : (List<String>) obj) {
							// 例：/information/market_price/prices?countryCode=US&rics=MCD&rics=BBD
							if (!StringUtil.isNullOrEmpty(str)) {
								urlBuilder.addQueryParameter(key, str);
							}
						}
					} else {
						if (obj != null && !StringUtil.isNullOrEmpty(obj.toString())) {
							urlBuilder.addQueryParameter(key, obj.toString());
						}
					}
				}
				logger.info("Sss Api Parameters From GET:{}" , sssOkHttpRequest.getParameters());
			} catch (Exception e) {
				logger.error("Sss Exception occured.", e.getMessage());
				logger.info("Sss Exception occured.", e);
				throw new SssConnectionException(e);
			}
		}

		// Define response
		Response response = null;
		try {
			// Build request protocols
			Request request = new Request.Builder().url(urlBuilder.build()).build();
			// Synchronization request-response
			response = SssOkHttpClientManager.getInstance().getClient().newCall(request).execute();
			if (null == response)
				return null;

			SssOkHttpResponse okHttpResponse = new SssOkHttpResponse();
			/*
			 * Results of the request. true:Successful false:fail
			 */
			okHttpResponse.setSuccessful(response.isSuccessful());
			// Response code,please refer to HTTP status code for specific value.
			okHttpResponse.setStatusCode(response.code());
			// Parse the response message.
			okHttpResponse.setResponseData(new String(response.body().bytes(), this.ENCODE));

			return okHttpResponse;

		} catch (Exception e) {
			logger.error("Sss Exception occured.", e.getMessage());
			logger.info("Sss Exception occured.", e);
			throw new SssConnectionException(e);
		} finally {
			if (null != response) {
				response.close();
			}
		}
	}

	/**
	 * @param sssOkHttpRequest
	 * @return SssOkHttpResponse
	 * @throws SssConnectionException
	 */
	public SssOkHttpResponse post(SssOkHttpRequest sssOkHttpRequest) throws SssConnectionException {

		// Check the required items
		String url = sssOkHttpRequest.getUrl();
		// url non empty check
		if (StringUtil.isNullOrEmpty(url)) {
			logger.error("Url is null or empty!");
			throw new SssConnectionException("Url is null or empty!");
		}
		logger.info("Sss Api Url From POST:{}" , url);
		// contentType non empty check
		MediaType contentType = sssOkHttpRequest.getContentType();
		if (null == contentType) {
			logger.error("ContentType is empty!");
			throw new SssConnectionException("ContentType is empty!");
		}


		// Serialize request message
		String jsonBody = "";
		try {
			if (null != sssOkHttpRequest.getParameters()) {
				jsonBody = objectMapper.writeValueAsString(sssOkHttpRequest.getParameters());
			}
			logger.info("Sss Api Parameters From POST:{}" , jc.toString(sssOkHttpRequest.getParameters()));
		} catch (Exception e) {
			logger.error("Sss Exception occured.", e.getMessage());
			logger.info("Sss Exception occured.", e);
			throw new SssConnectionException(e);
		}

		// Define response
		Response response = null;
		try {
			// Build request protocols
			// Define request
			Request request = new Request.Builder().url(url)
					.post(RequestBody.Companion.create(jsonBody, contentType)).build();
			// Synchronization request-response
			response = SssOkHttpClientManager.getInstance().getClient().newCall(request).execute();
			if (null == response)
				return null;

			SssOkHttpResponse okHttpResponse = new SssOkHttpResponse();
			/*
			 * Results of the request. true:Successful false:fail
			 */
			okHttpResponse.setSuccessful(response.isSuccessful());
			// Response code,please refer to HTTP status code for specific value.
			okHttpResponse.setStatusCode(response.code());
			// Parse the response message.
			okHttpResponse.setResponseData(new String(response.body().bytes(), this.ENCODE));

			logger.info("Sss Api Response From POST:{}", okHttpResponse.getResponsData());

			return okHttpResponse;

		} catch (Exception e) {
			logger.error("Sss Exception occured.", e.getMessage());
			logger.info("Sss Exception occured.", e);
			throw new SssConnectionException(e);
		} finally {
			if (null != response) {
				response.close();
			}
		}
	}

	/**
	 * Creates and returns a copy of this object. The precise meaning of "copy" may
	 * depend on the class of the object. The general intent is that, for any object
	 * {@code x}, the expression: <blockquote>
	 * 
	 * <pre>
	 * x.clone() != x
	 * </pre>
	 * 
	 * </blockquote> will be true, and that the expression: <blockquote>
	 * 
	 * <pre>
	 * x.clone().getClass() == x.getClass()
	 * </pre>
	 * 
	 * </blockquote> will be {@code true}, but these are not absolute requirements.
	 * While it is typically the case that: <blockquote>
	 * 
	 * <pre>
	 * x.clone().equals(x)
	 * </pre>
	 * 
	 * </blockquote> will be {@code true}, this is not an absolute requirement.
	 * <p>
	 * By convention, the returned object should be obtained by calling
	 * {@code super.clone}. If a class and all of its superclasses (except
	 * {@code Object}) obey this convention, it will be the case that
	 * {@code x.clone().getClass() == x.getClass()}.
	 * <p>
	 * By convention, the object returned by this method should be independent of
	 * this object (which is being cloned). To achieve this independence, it may be
	 * necessary to modify one or more fields of the object returned by
	 * {@code super.clone} before returning it. Typically, this means copying any
	 * mutable objects that comprise the internal "deep structure" of the object
	 * being cloned and replacing the references to these objects with references to
	 * the copies. If a class contains only primitive fields or references to
	 * immutable objects, then it is usually the case that no fields in the object
	 * returned by {@code super.clone} need to be modified.
	 * <p>
	 * The method {@code clone} for class {@code Object} performs a specific cloning
	 * operation. First, if the class of this object does not implement the
	 * interface {@code Cloneable}, then a {@code CloneNotSupportedException} is
	 * thrown. Note that all arrays are considered to implement the interface
	 * {@code Cloneable} and that the return type of the {@code clone} method of an
	 * array type {@code T[]} is {@code T[]} where T is any reference or primitive
	 * type. Otherwise, this method creates a new instance of the class of this
	 * object and initializes all its fields with exactly the contents of the
	 * corresponding fields of this object, as if by assignment; the contents of the
	 * fields are not themselves cloned. Thus, this method performs a "shallow copy"
	 * of this object, not a "deep copy" operation.
	 * <p>
	 * The class {@code Object} does not itself implement the interface
	 * {@code Cloneable}, so calling the {@code clone} method on an object whose
	 * class is {@code Object} will result in throwing an exception at run time.
	 *
	 * @return a clone of this instance.
	 * @throws CloneNotSupportedException if the object's class does not support the
	 * {@code Cloneable} interface. Subclasses that override the {@code clone}
	 * method can also throw this exception to indicate that an instance cannot be
	 * cloned.
	 * @see Cloneable
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return OkHttpClientWrapperInternal.INSTANCE;
	}
}
