package com.sbisec.helios.ap.fasthelp;

import okio.Buffer;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.fasthelp.exception.FastCcsException;
import com.sbisec.helios.ap.fasthelp.utils.FastCcsConfig;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * <p>
 * Description:FastCcsOkHttpClientWrapper
 * </p>
 *
 * @author dalian
 * @version 1.0
 * @date 3/31/2025
 */
public class FastCcsOkHttpClientWrapper implements Cloneable {

    private static class FasthelpOkHttpClientWrapperInternal {
        private static final FastCcsOkHttpClientWrapper INSTANCE = new FastCcsOkHttpClientWrapper();
    }

    private static volatile boolean isCreate = false;

    /** ロガー */
    private static final Logger logger = LoggerFactory.getLogger(FastCcsOkHttpClientWrapper.class);

    private FastCcsOkHttpClientWrapper() {
        if (isCreate)
            throw new RuntimeException("Cannot instantiate repeatedly!");
        isCreate = true;

        FastCcsOkHttpClientManager.getInstance()
                .setConnTimeout(FastCcsConfig.getHttpClientConnectTimeout())
                .setReadTimeout(FastCcsConfig.getHttpClientReadTimeout())
                .setWriteTimeout(FastCcsConfig.getHttpClientWriteTimeout())
                .setConnPoolTimeout(FastCcsConfig.getHttpClientConnPoolTimeout())
                .setConnPoolCachedMax(FastCcsConfig.getHttpClientConnPoolCachedMax())
                .setRetryCount(FastCcsConfig.getHttpClientRetryCount());
    }

    public static final FastCcsOkHttpClientWrapper getInstance() {
        return FasthelpOkHttpClientWrapperInternal.INSTANCE;
    }

    /**
     * @param okHttpRequest
     * @return
     * @throws FastCcsException
     */
    public FastCcsOkHttpResponse post(FastCcsOkHttpRequest okHttpRequest, RequestBody reqBody) throws Exception {

        // Check the required items
        String url = okHttpRequest.getUrl();
        // url non empty check
        if (StringUtil.isNullOrEmpty(url)) {
            logger.error("Url is null or empty!");
            throw new FastCcsException("Url is null or empty!");
        }
        logger.info("Fasthelp Api Url From POST:{}", url);

        String header = "application/x-www-form-urlencoded;charset=Shift_JIS";
        logger.info("Fasthelp Api Headers From POST:{}", "Content-Type:" + header);

        if (null != reqBody) {
            Buffer buffer = new Buffer();
            reqBody.writeTo(buffer);
            MediaType mediaType = reqBody.contentType();
            Charset charset = mediaType != null ? mediaType.charset(Charset.forName("Shift_JIS")) : Charset.forName("Shift_JIS");
            String requestBodyString = buffer.readString(charset);
            logger.info("Fasthelp Api FormBody From POST:{}", requestBodyString);
        }
        // Define response
        Response response = null;
        try {
            // Build request protocols
            // Define request
            Request request = new Request.Builder()
                    .url(url)
                    .header("Content-Type", header)
                    .post(reqBody)
                    .build();

            // Synchronization request-response
            response = FastCcsOkHttpClientManager.getInstance().getClient().newCall(request).execute();
            if (null == response)
                return null;

            FastCcsOkHttpResponse okHttpResponse = new FastCcsOkHttpResponse();
            //Results of the request. true:Successful false:fail
            okHttpResponse.setSuccessful(response.isSuccessful());
            // Response code,please refer to HTTP status code for specific value.
            okHttpResponse.setStatusCode(response.code());
            // Parse the response message.
            String responseBody = new String(response.body().bytes(), "SHIFT-JIS");
            if (responseBody != null && !responseBody.isEmpty()) {
                if (responseBody.contains("\n")) {
                    responseBody = responseBody.replace("\n", "");
                }
            }
            okHttpResponse.setResponseData(responseBody);
            logger.info("Fasthelp Api Response From POST:{}", okHttpResponse.getResponsData());
            return okHttpResponse;
        } catch (Exception e) {
            logger.error("Fasthelp Exception occured.", e);
            throw new FastCcsException(e);
        } finally {
            if (null != response) {
                response.close();
            }
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return FasthelpOkHttpClientWrapperInternal.INSTANCE;
    }

}
