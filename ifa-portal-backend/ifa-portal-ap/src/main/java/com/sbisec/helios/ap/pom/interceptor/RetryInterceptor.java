package com.sbisec.helios.ap.pom.interceptor;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.SocketException;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p>
 * Description:Processing socket connection timed out.
 * </p>
 *
 * @author shuchen.xin
 * @version 1.0
 * @date 5/27/2021
 */
public class RetryInterceptor implements Interceptor {

	private static final Logger LOG = LoggerFactory.getLogger(RetryInterceptor.class);

	private int retryCount;

	public RetryInterceptor(int retryCount) {
		this.retryCount = retryCount;
	}

	@NotNull
	@Override
	public Response intercept(@NotNull Chain chain) throws IOException {

		return this.retry(chain);
	}

	public Response retry(Chain chain) throws IOException {
		Request request = chain.request();
		Response response = null;
        int attempt = 0;

        while (attempt <= retryCount) {
            try {
                response = chain.proceed(request);
                return response;
            } catch (IOException e) {
                if (attempt >= retryCount || !isRetryableException(e)) {
                    LOG.error("Request failed after {} attempts", attempt, e);
                    throw e;
                }
                LOG.info("The request failed and will be retried. Attempt: {}. Request: {}", attempt + 1, request);
                attempt++;
                try {
                    Thread.sleep(2000); // 2秒待つ
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt(); // スレッドの割り込み状態を復元
                    throw new IOException("Retry interrupted", ie);
                }
            }
        }

		return response;
	}
	
    private boolean isRetryableException(IOException e) {
        return e instanceof SocketTimeoutException || e instanceof SocketException || e.getMessage().contains("unexpected end of stream");
    }
}
