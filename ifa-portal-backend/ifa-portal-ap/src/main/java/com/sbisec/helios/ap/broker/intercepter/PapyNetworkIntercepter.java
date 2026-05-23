package com.sbisec.helios.ap.broker.intercepter;

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
 * @author katsuhiko.kagoshima
 * @date 12/27/2022
 */
public class PapyNetworkIntercepter implements Interceptor {
    
    private static final Logger LOG = LoggerFactory.getLogger(PapyNetworkIntercepter.class);
    
    /** 定数- Http request method - POST */
    private static final String STR_F_HTTP_REQUEST_METHOD_POST = "POST";
    
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        
        Request request = chain.request();
        
        if (null != request) {
            if (STR_F_HTTP_REQUEST_METHOD_POST.equals(request.method())) {
                LOG.info("Papy API request data:{} ", request.toString());
            }
        }
        
        Response response = chain.proceed(request);
        
        return response;
    }
}
