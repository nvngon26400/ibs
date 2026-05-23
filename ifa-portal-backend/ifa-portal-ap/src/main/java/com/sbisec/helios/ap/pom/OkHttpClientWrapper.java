package com.sbisec.helios.ap.pom;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.pom.utils.PomConfig;
import com.sbisec.helios.ap.pom.utils.PomException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.Headers;
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
 * @author shuchen.xin
 * @version 1.0
 * @date 5/20/2021
 */
public class OkHttpClientWrapper implements Cloneable {
    
    private static class OkHttpClientWrapperInternal {
        
        private static final OkHttpClientWrapper INSTANCE = new OkHttpClientWrapper();
    }
    
    private static volatile boolean isCreate = false;
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    private static final String ENCODE = "UTF-8";
    
    /** ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttpClientWrapper.class);
    
    private OkHttpClientWrapper() {
        
        if (isCreate) {
            throw new RuntimeException("Cannot instantiate repeatedly!");
        }
        isCreate = true;
        
        OkHttpClientManager.getInstance().setConnTimeout(PomConfig.getHttpClientConnectTimeout())
                .setReadTimeout(PomConfig.getHttpClientReadTimeout())
                .setWriteTimeout(PomConfig.getHttpClientWriteTimeout())
                .setConnPoolTimeout(PomConfig.getHttpClientConnPoolTimeout())
                .setConnPoolCachedMax(PomConfig.getHttpClientConnPoolCachedMax())
                .setRetryCount(PomConfig.getHttpClientRetryCount());
    }
    
    public static final OkHttpClientWrapper getInstance() {
        
        return OkHttpClientWrapperInternal.INSTANCE;
    }
    
    /**
     * @param okHttpRequest 要求
     * @return 応答
     * @throws PomException システム例外
     */
    @SuppressWarnings("unchecked")
    public OkHttpResponse get(OkHttpRequest okHttpRequest) throws PomException {
        
        // Check the required items
        String url = okHttpRequest.getUrl();
        // url non empty check
        if (StringUtil.isNullOrEmpty(url)) {
            LOGGER.error("Url is null or empty!");
            throw new PomException("Url is null or empty!");
        }
        LOGGER.info("Pom Api Url From GET:{}", url);
        
        // Create a URL wrapper class
        HttpUrl.Builder urlBuilder = Optional.ofNullable(HttpUrl.Companion.parse(url)) //
                .map(HttpUrl::newBuilder) //
                .orElse(null);
        if (Objects.isNull(urlBuilder)) {
            LOGGER.error("Failed to build url!");
            throw new PomException("Failed to build url!");
            
        }
        
        // Add parameters if exists
        if (null != okHttpRequest.getParameters() && !okHttpRequest.getParameters().isEmpty()) {
            try {
                for (String key : okHttpRequest.getParameters().keySet()) {
                    
                    Object obj = okHttpRequest.getParameters().get(key);
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
                LOGGER.info("Pom Api Parameters From GET:{}", okHttpRequest.getParameters());
            } catch (Exception e) {
                LOGGER.error("Pom Exception occured.", e.getMessage());
                LOGGER.info("Pom Exception occured.", e);
                throw new PomException(e);
            }
        }
        
        // Add headers if exists
        Headers.Builder headers = new Headers.Builder();
        if (null != okHttpRequest.getHeaders() && !okHttpRequest.getHeaders().isEmpty()) {
            for (String key : okHttpRequest.getHeaders().keySet()) {
                String value = String.valueOf(okHttpRequest.getHeaders().get(key));
                headers.add(key, value);
            }
            LOGGER.info("Pom Api Headers From GET:{}", okHttpRequest.getHeaders());
        }
        
        // Define response
        Response response = null;
        try {
            // Build request protocols
            Request request = new Request.Builder().url(urlBuilder.build()).headers(headers.build()).build();
            // Synchronization request-response
            response = OkHttpClientManager.getInstance().getClient().newCall(request).execute();
            if (null == response) {
                return null;
            }
            OkHttpResponse okHttpResponse = new OkHttpResponse();
            /*
             * Results of the request. true:Successful false:fail
             */
            okHttpResponse.setSuccessful(response.isSuccessful());
            // Response code,please refer to HTTP status code for specific value.
            okHttpResponse.setStatusCode(response.code());
            // Parse the response message.
            var body = response.body();
            if (Objects.isNull(body)) {
                LOGGER.error("body is null.");
                throw new PomException("body is null.");
            }
            okHttpResponse.setResponseData(new String(body.bytes(), ENCODE));
            return okHttpResponse;
            
        } catch (Exception e) {
            LOGGER.error("Pom Exception occured.", e.getMessage());
            LOGGER.info("Pom Exception occured.", e);
            throw new PomException(e);
        } finally {
            if (null != response) {
                response.close();
            }
        }
    }
    
    /**
     * @param okHttpRequest 要求
     * @return 応答
     * @throws PomException システム例外
     */
    public OkHttpResponse post(OkHttpRequest okHttpRequest) throws PomException {
        
        // Check the required items
        String url = okHttpRequest.getUrl();
        // url non empty check
        if (StringUtil.isNullOrEmpty(url)) {
            LOGGER.error("Url is null or empty!");
            throw new PomException("Url is null or empty!");
        }
        LOGGER.info("Pom Api Url From POST:{}", url);
        // contentType non empty check
        MediaType contentType = okHttpRequest.getContentType();
        if (null == contentType) {
            LOGGER.error("ContentType is empty!");
            throw new PomException("ContentType is empty!");
        }
        
        // Add headers if exists
        Headers.Builder headers = new Headers.Builder();
        if (null != okHttpRequest.getHeaders() && !okHttpRequest.getHeaders().isEmpty()) {
            for (String key : okHttpRequest.getHeaders().keySet()) {
                String value = String.valueOf(okHttpRequest.getHeaders().get(key));
                headers.add(key, value);
            }
            LOGGER.info("Pom Api Headers From POST:{}", okHttpRequest.getHeaders());
        }
        
        // Serialize request message
        String jsonBody = "";
        try {
            if (null != okHttpRequest.getParameters()) {
                jsonBody = objectMapper.writeValueAsString(okHttpRequest.getParameters());
            }
            LOGGER.info("Pom Api Parameters From POST:{}", jsonBody);
        } catch (Exception e) {
            LOGGER.error("Pom Exception occured.", e.getMessage());
            LOGGER.info("Pom Exception occured.", e);
            throw new PomException(e);
        }
        
        // Define response
        Response response = null;
        try {
            // Build request protocols
            // Define request
            Request request = new Request.Builder().url(url).headers(headers.build())
                    .post(RequestBody.Companion.create(jsonBody, contentType)).build();
            // Synchronization request-response
            response = OkHttpClientManager.getInstance().getClient().newCall(request).execute();
            if (null == response) {
                return null;
            }
            OkHttpResponse okHttpResponse = new OkHttpResponse();
            /*
             * Results of the request. true:Successful false:fail
             */
            okHttpResponse.setSuccessful(response.isSuccessful());
            // Response code,please refer to HTTP status code for specific value.
            okHttpResponse.setStatusCode(response.code());
            // Parse the response message.
            var body = response.body();
            if (Objects.isNull(body)) {
                LOGGER.error("body is null.");
                throw new PomException("body is null.");
            }
            okHttpResponse.setResponseData(new String(body.bytes(), ENCODE));
            
            LOGGER.info("Pom Api Response From POST:{}", okHttpResponse.getResponsData());
            
            return okHttpResponse;
            
        } catch (Exception e) {
            LOGGER.error("Pom Exception occured.", e.getMessage());
            LOGGER.info("Pom Exception occured.", e);
            throw new PomException(e);
        } finally {
            if (null != response) {
                response.close();
            }
        }
    }
    
    /**
     * Delete processing is performed.
     *
     * @param okHttpRequest 要求
     * @return 応答
     * @throws PomException システム例外
     */
    public OkHttpResponse delete(OkHttpRequest okHttpRequest) throws PomException {
        
        String url = okHttpRequest.getUrl();
        // url non empty check
        if (StringUtil.isNullOrEmpty(url)) {
            LOGGER.error("Url is null or empty!");
            throw new PomException("Url is null or empty!");
        }
        LOGGER.info("Pom Api Url From DELETE:{}", url);
        // contentType non empty check
        MediaType contentType = okHttpRequest.getContentType();
        if (null == contentType) {
            LOGGER.error("ContentType is empty!");
            throw new PomException("ContentType is empty!");
        }
        
        // Add headers if exists
        Headers.Builder headers = new Headers.Builder();
        if (null != okHttpRequest.getHeaders() && !okHttpRequest.getHeaders().isEmpty()) {
            for (String key : okHttpRequest.getHeaders().keySet()) {
                String value = String.valueOf(okHttpRequest.getHeaders().get(key));
                headers.add(key, value);
            }
            LOGGER.info("Pom Api Headers From DELETE:{}", okHttpRequest.getHeaders());
        }
        
        // Serialize request message
        String jsonBody = "";
        try {
            // Serialize only if the request parameter exists
            if (null != okHttpRequest.getParameters()) {
                jsonBody = objectMapper.writeValueAsString(okHttpRequest.getParameters());
            }
            LOGGER.info("Pom Api Parameters From DELETE:{}", okHttpRequest.getParameters());
        } catch (Exception e) {
            LOGGER.error("Pom Exception occured.", e.getMessage());
            LOGGER.info("Pom Exception occured.", e);
            throw new PomException(e);
        }
        
        // Define response
        Response response = null;
        try {
            // Build request protocols
            Request request = null;
            if (StringUtil.isNullOrEmpty(jsonBody)) {
                // Delete method is used when there are no parameters
                request = new Request.Builder().url(url).headers(headers.build()).delete().build();
            } else {
                // Delete(requestbody) method is used when there are no parameters
                request = new Request.Builder().url(url).headers(headers.build())
                        .delete(RequestBody.Companion.create(jsonBody, contentType)).build();
            }
            
            response = OkHttpClientManager.getInstance().getClient().newCall(request).execute();
            if (null == response) {
                return null;
            }
            
            // Encapsulate okhttpresponse according to the result of response
            OkHttpResponse okHttpResponse = new OkHttpResponse();
            // Results of the request. true:Successful false:fail
            okHttpResponse.setSuccessful(response.isSuccessful());
            // Response code,please refer to HTTP status code for specific value.
            okHttpResponse.setStatusCode(response.code());
            // Parse the response message.
            var body = response.body();
            if (Objects.isNull(body)) {
                LOGGER.error("body is null.");
                throw new PomException("body is null.");
            }
            okHttpResponse.setResponseData(new String(body.bytes(), ENCODE));
            LOGGER.info("Pom Api Response From DELETE:{}", okHttpResponse.getResponsData());
            
            return okHttpResponse;
        } catch (Exception e) {
            LOGGER.error("Pom Exception occured.", e.getMessage());
            LOGGER.info("Pom Exception occured.", e);
            throw new PomException(e);
        } finally {
            if (null != response) {
                response.close();
            }
        }
        
    }
    
    /**
     * Update processing is performed.
     *
     * @param okHttpRequest 要求
     * @return 応答
     * @throws PomException システム例外
     */
    public OkHttpResponse put(OkHttpRequest okHttpRequest) throws PomException {
        
        String url = okHttpRequest.getUrl();
        // url non empty check
        if (StringUtil.isNullOrEmpty(url)) {
            LOGGER.error("Url is null or empty!");
            throw new PomException("Url is null or empty!");
        }
        LOGGER.info("Pom Api Url From PUT:{}", url);
        // contentType non empty check
        MediaType contentType = okHttpRequest.getContentType();
        if (null == contentType) {
            LOGGER.error("ContentType is empty!");
            throw new PomException("ContentType is empty!");
        }
        
        // Add headers if exists
        Headers.Builder headers = new Headers.Builder();
        if (null != okHttpRequest.getHeaders() && !okHttpRequest.getHeaders().isEmpty()) {
            for (String key : okHttpRequest.getHeaders().keySet()) {
                String value = String.valueOf(okHttpRequest.getHeaders().get(key));
                headers.add(key, value);
            }
            LOGGER.info("Pom Api Headers From PUT:{}", okHttpRequest.getHeaders());
        }
        
        // Serialize request message
        String jsonBody = "";
        try {
            // Serialize only if the request parameter exists
            if (null != okHttpRequest.getParameters()) {
                jsonBody = objectMapper.writeValueAsString(okHttpRequest.getParameters());
            }
            LOGGER.info("Pom Api Parameters From PUT:{}", jsonBody);
        } catch (Exception e) {
            LOGGER.error("Pom Exception occured.", e.getMessage());
            LOGGER.info("Pom Exception occured.", e);
            throw new PomException(e);
        }
        
        // Define response
        Response response = null;
        try {
            // Build request protocols
            // put(requestbody) method is used when there are no parameters
            Request request = new Request.Builder().url(url).headers(headers.build())
                    .put(RequestBody.Companion.create(jsonBody, contentType)).build();
            response = OkHttpClientManager.getInstance().getClient().newCall(request).execute();
            if (null == response) {
                return null;
            }
            
            // Encapsulate okhttpresponse according to the result of response
            OkHttpResponse okHttpResponse = new OkHttpResponse();
            // Results of the request. true:Successful false:fail
            okHttpResponse.setSuccessful(response.isSuccessful());
            // Response code,please refer to HTTP status code for specific value.
            okHttpResponse.setStatusCode(response.code());
            // Parse the response message.
            var body = response.body();
            if (Objects.isNull(body)) {
                LOGGER.error("body is null.");
                throw new PomException("body is null.");
            }
            okHttpResponse.setResponseData(new String(body.bytes(), ENCODE));
            LOGGER.info("Pom Api Response From PUT:{}", okHttpResponse.getResponsData());
            return okHttpResponse;
        } catch (Exception e) {
            LOGGER.error("Pom Exception occured.", e.getMessage());
            LOGGER.info("Pom Exception occured.", e);
            throw new PomException(e);
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
