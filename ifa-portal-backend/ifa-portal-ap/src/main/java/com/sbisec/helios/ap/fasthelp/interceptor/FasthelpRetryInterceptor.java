package com.sbisec.helios.ap.fasthelp.interceptor;

import java.io.IOException;
import java.net.SocketTimeoutException;

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
 * @author dalian
 * @version 1.0
 * @date 3/31/2025
 */
public class FasthelpRetryInterceptor implements Interceptor {

    private static final Logger LOG = LoggerFactory.getLogger(FasthelpRetryInterceptor.class);

    private int retryCount;

    public FasthelpRetryInterceptor(int retryCount) {
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
        try {
            response = chain.proceed(request);
        } catch (SocketTimeoutException ste) {
            int retryCount = 0;

            while (null == response && this.retryCount > retryCount) {
                LOG.info("The request failed and will be retried soon. Request:" + request.toString());
                retryCount++;
                try {
                    response = chain.proceed(request);
                } catch (SocketTimeoutException ste_) {
                    continue;
                }
                LOG.info("Retrying request completed: " + retryCount);
            }

            if (null == response)
                throw ste;
        }

        return response;
    }
}
