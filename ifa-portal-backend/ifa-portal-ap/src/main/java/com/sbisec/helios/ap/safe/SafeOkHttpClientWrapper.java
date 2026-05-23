package com.sbisec.helios.ap.safe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.safe.utils.SafeConfig;
import com.sbisec.helios.ap.safe.utils.SafeException;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * <p>
 * Description:SafeOkHttpClientWrapper
 * </p>
 *
 * @author nicksen.li
 * @version 1.0
 * @date 3/31/2025
 */
public class SafeOkHttpClientWrapper implements Cloneable {

    private static class SafeOkHttpClientWrapperInternal {
        private static final SafeOkHttpClientWrapper INSTANCE = new SafeOkHttpClientWrapper();
    }

    private static volatile boolean isCreate = false;

    private ObjectMapper objectMapper = new ObjectMapper();

    private final String ENCODE = "UTF-8";

    /** JSON変換クラスのインスタンス */
    protected JsonConverter jc = JsonConverter.getInstance();

    /** ロガー */
    private static final Logger logger = LoggerFactory.getLogger(SafeOkHttpClientWrapper.class);

    private SafeOkHttpClientWrapper() {
        if (isCreate)
            throw new RuntimeException("Cannot instantiate repeatedly!");
        isCreate = true;

        SafeOkHttpClientManager.getInstance()
                .setConnTimeout(SafeConfig.getHttpClientConnectTimeout())
                .setReadTimeout(SafeConfig.getHttpClientReadTimeout())
                .setWriteTimeout(SafeConfig.getHttpClientWriteTimeout())
                .setConnPoolTimeout(SafeConfig.getHttpClientConnPoolTimeout())
                .setConnPoolCachedMax(SafeConfig.getHttpClientConnPoolCachedMax())
                .setRetryCount(SafeConfig.getHttpClientRetryCount());
    }

    public static final SafeOkHttpClientWrapper getInstance() {
        return SafeOkHttpClientWrapperInternal.INSTANCE;
    }

    /**
     * @param okHttpRequest
     * @return
     * @throws SafeException
     */
    public SafeOkHttpResponse post(SafeOkHttpRequest okHttpRequest) throws SafeException {

        // Check the required items
        String url = okHttpRequest.getUrl();
        // url non empty check
        if (StringUtil.isNullOrEmpty(url)) {
            logger.error("Url is null or empty!");
            throw new SafeException("Url is null or empty!");
        }
        logger.info("Safe Api Url From POST:{}", url);
        // contentType non empty check
        MediaType contentType = okHttpRequest.getContentType();
        if (null == contentType) {
            logger.error("ContentType is empty!");
            throw new SafeException("ContentType is empty!");
        }

        // Add headers if exists
        Headers.Builder headers = new Headers.Builder();
        if (null != okHttpRequest.getHeaders() && !okHttpRequest.getHeaders().isEmpty()) {
            for (String key : okHttpRequest.getHeaders().keySet()) {
                String value = String.valueOf(okHttpRequest.getHeaders().get(key));
                headers.add(key, value);
            }
            logger.info("Safe Api Headers From POST:{}", okHttpRequest.getHeaders());
        }

        // Serialize request message
        String jsonBody = "";
        try {
            if (null != okHttpRequest.getParameters()) {
                jsonBody = objectMapper.writeValueAsString(okHttpRequest.getParameters());
            }
            logger.info("Safe Api Parameters From POST:{}", jc.toString(okHttpRequest.getParameters()));
        } catch (Exception e) {
            logger.error("Safe Exception occured.", e);
            throw new SafeException(e);
        }

        // Define response
        Response response = null;
        try {
            // Build request protocols
            // Define request
            Request request = new Request.Builder()
                    .url(url)
                    .headers(headers.build())
                    .post(RequestBody.Companion.create(jsonBody, contentType)).build();

            // Synchronization request-response
            response = SafeOkHttpClientManager.getInstance().getClient().newCall(request).execute();
            if (null == response)
                return null;

            SafeOkHttpResponse okHttpResponse = new SafeOkHttpResponse();
            /*
             * Results of the request. true:Successful false:fail
             */
            okHttpResponse.setSuccessful(response.isSuccessful());
            // Response code,please refer to HTTP status code for specific value.
            okHttpResponse.setStatusCode(response.code());
            // Parse the response message.
            okHttpResponse.setResponseData(new String(response.body().bytes(), this.ENCODE));

            logger.info("Safe Api Response From POST:{}", okHttpResponse.getResponsData());

            return okHttpResponse;

        } catch (Exception e) {
            logger.error("Safe Exception occured.", e);
            throw new SafeException(e);
        } finally {
            if (null != response) {
                response.close();
            }
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return SafeOkHttpClientWrapperInternal.INSTANCE;
    }

}
