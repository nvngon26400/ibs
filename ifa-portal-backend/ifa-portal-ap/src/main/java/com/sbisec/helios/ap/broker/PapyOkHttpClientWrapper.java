package com.sbisec.helios.ap.broker;

import java.util.Map;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.broker.util.PapyConfig;
import com.sbisec.helios.ap.broker.util.PapyConnectionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * <p>
 * Description:OkHttpClientWrapper
 * </p>
 *
 * @author katsuhiko.kagoshima
 * @date 12/23/2022
 */
public class PapyOkHttpClientWrapper implements Cloneable {
    
    private static class OkHttpClientWrapperInternal {
        
        private static final PapyOkHttpClientWrapper INSTANCE = new PapyOkHttpClientWrapper();
    }
    
    private static volatile boolean isCreate = false;
    
    private final String ENCODE = "UTF-8";
    
    /** ロガー */
    private static final Logger logger = LoggerFactory.getLogger(PapyOkHttpClientWrapper.class);
    
    private PapyOkHttpClientWrapper() {
        
        if (isCreate)
            throw new RuntimeException("Cannot instantiate repeatedly!");
        isCreate = true;
        
        PapyOkHttpClientManager.getInstance().setConnTimeout(PapyConfig.getHttpClientConnectTimeout())
                .setReadTimeout(PapyConfig.getHttpClientReadTimeout())
                .setWriteTimeout(PapyConfig.getHttpClientWriteTimeout())
                .setConnPoolTimeout(PapyConfig.getHttpClientConnPoolTimeout())
                .setConnPoolCachedMax(PapyConfig.getHttpClientConnPoolCachedMax())
                .setRetryCount(PapyConfig.getHttpClientRetryCount());
    }
    
    public static final PapyOkHttpClientWrapper getInstance() {
        
        return OkHttpClientWrapperInternal.INSTANCE;
    }
    
    /**
     * @param okHttpRequest
     * @return
     * @throws PapyConnectionException
     */
    public PapyOkHttpResponse post(PapyOkHttpRequest okHttpRequest) throws PapyConnectionException {
        
        // Check the required items
        String url = okHttpRequest.getUrl();
        // url non empty check
        if (StringUtil.isNullOrEmpty(url)) {
            logger.error("Url is null or empty!");
            throw new PapyConnectionException("Url is null or empty!");
        }
        logger.info("Papy Api Url From POST:{}", url);
        // contentType non empty check
        MediaType contentType = okHttpRequest.getContentType();
        if (null == contentType) {
            logger.error("ContentType is empty!");
            throw new PapyConnectionException("ContentType is empty!");
        }
        
        // Add headers if exists
        Headers.Builder headers = new Headers.Builder();
        if (null != okHttpRequest.getHeaders() && !okHttpRequest.getHeaders().isEmpty()) {
            for (String key : okHttpRequest.getHeaders().keySet()) {
                String value = String.valueOf(okHttpRequest.getHeaders().get(key));
                headers.add(key, value);
            }
            logger.info("Papy Api Headers From POST:{}", okHttpRequest.getHeaders());
        }
        
        // Serialize request message
        RequestBody requestBody = null;
        try {
            if (null != okHttpRequest.getParameters()) {
                requestBody = createEncodedUrlRequest(okHttpRequest.getParameters());
            }
            logger.info("Papy Api Parameters From POST:{}", okHttpRequest.getParameters());
        } catch (Exception e) {
            logger.info("Papy Exception occured.:", e);
            throw new PapyConnectionException();
        }
        
        // Define response
        Response response = null;
        try {
            // Build request protocols
            // Define request
            Request request = new Request.Builder().url(url).headers(headers.build()).post(requestBody).build();
            // Synchronization request-response
            response = PapyOkHttpClientManager.getInstance().getClient().newCall(request).execute();
            if (null == response)
                return null;
            
            PapyOkHttpResponse okHttpResponse = new PapyOkHttpResponse();
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
            logger.info("Papy Exception occured.");
            throw new PapyConnectionException(e);
        } finally {
            if (null != response) {
                response.close();
            }
        }
    }
    
    /**
     * x-www-form-unlencoded形式のリクエストボディを生成する。
     * parameters
     * @return
     */
    private RequestBody createEncodedUrlRequest(Map<String, Object> parameters) {
        
        // 各パラメータをx-www-form-urlencoded形式に変換するオブジェクトの呼出し 
        FormBody.Builder formBuilder = new FormBody.Builder();
        // 各パラメータを取得し、FormBody.Builderに設定する。
        for (String key : parameters.keySet()) {
            String value = String.valueOf(parameters.get(key));
            formBuilder.add(key, value);
        }
        
        return formBuilder.build();
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
