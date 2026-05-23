package com.sbisec.helios.ap.athena.interceptor;

import java.io.IOException;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p>
 * Description:Processing log information for requests / responses
 * </p>
 *
 * @author shuchen.xin
 * @version 1.0
 * @date 5/27/2021
 */
public class NetworkIntercepter implements Interceptor {

	private static final Logger LOG = LoggerFactory.getLogger(NetworkIntercepter.class);

	/** 定数- Http request method - POST */
	private static final String STR_F_HTTP_REQUEST_METHOD_POST = "POST";
	/** 定数- Http request method - PUT */
	private static final String STR_F_HTTP_REQUEST_METHOD_PUT = "PUT";
	/** 定数- Http request method - DELETE */
	private static final String STR_F_HTTP_REQUEST_METHOD_DELETE = "DELETE";

	@NotNull
	@Override
	public Response intercept(@NotNull Chain chain) throws IOException {

		Request request = chain.request();

		if (null != request) {
			if (STR_F_HTTP_REQUEST_METHOD_POST.equals(request.method())
					|| STR_F_HTTP_REQUEST_METHOD_PUT.equals(request.method())
					|| STR_F_HTTP_REQUEST_METHOD_DELETE.equals(request.method())) {
				// LOG.info("Comet API request data:{} ", request.toString());
			}
		}

		Response response = chain.proceed(request);

		return response;
	}
}
