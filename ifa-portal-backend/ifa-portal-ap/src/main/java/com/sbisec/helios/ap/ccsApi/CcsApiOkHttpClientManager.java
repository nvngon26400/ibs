package com.sbisec.helios.ap.ccsApi;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sbisec.helios.ap.ccsApi.exception.CcsApiException;
import com.sbisec.helios.ap.ccsApi.interceptor.CcsApiRetryInterceptor;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

/**
 * Description:CcsOkHttpClientManager
 */
public class CcsApiOkHttpClientManager implements Cloneable {

    private static class FasthelpOkHttpClientInternal {
        private static final CcsApiOkHttpClientManager INSTANCE = new CcsApiOkHttpClientManager();
    }

    private static final Logger LOG = LoggerFactory.getLogger(CcsApiOkHttpClientManager.class);

    private static volatile boolean isCreate = false;

    private OkHttpClient client;

    private long connTimeout;
    private long readTimeout;
    private long writeTimeout;
    private int connPoolTimeout;
    private int connPoolCachedMax;
    private int retryCount;

    private CcsApiOkHttpClientManager() {
        if (isCreate)
            throw new RuntimeException("Cannot instantiate repeatedly!");
        isCreate = true;

        this.connTimeout = 3L;
        this.readTimeout = 10L;
        this.writeTimeout = 5L;
        this.connPoolTimeout = 10;
        this.connPoolCachedMax = 5;
        this.retryCount = 1;
    }

    private synchronized void create() throws CcsApiException {
        try {
            ConnectionPool pool = new ConnectionPool(
                    this.connPoolTimeout, 
                    this.connPoolCachedMax, 
                    TimeUnit.MINUTES);
            this.client = new OkHttpClient()
                    .newBuilder()
                    .connectTimeout(this.connTimeout, TimeUnit.SECONDS)
                    .readTimeout(this.readTimeout, TimeUnit.SECONDS)
                    .writeTimeout(this.writeTimeout, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false)
                    .addInterceptor(new CcsApiRetryInterceptor(this.retryCount))
                    .connectionPool(pool).build();
        } catch (Exception e) {
            LOG.error("CcsApi Exception occured.", e);
            throw e;
        }
    }

    public CcsApiOkHttpClientManager setReadTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public CcsApiOkHttpClientManager setWriteTimeout(long writeTimeout) {
        this.writeTimeout = writeTimeout;
        return this;
    }

    public CcsApiOkHttpClientManager setConnTimeout(long connTimeout) {
        this.connTimeout = connTimeout;
        return this;
    }

    public CcsApiOkHttpClientManager setConnPoolTimeout(int connPoolTimeout) {
        this.connPoolTimeout = connPoolTimeout;
        return this;
    }

    public CcsApiOkHttpClientManager setConnPoolCachedMax(int connPoolCachedMax) {
        this.connPoolCachedMax = connPoolCachedMax;
        return this;
    }

    public CcsApiOkHttpClientManager setRetryCount(int retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public OkHttpClient getClient() throws CcsApiException {
        if (null == this.client) {
            synchronized (this) {
                if (null == this.client) {
                    this.create();
                }
            }
        }
        return this.client;
    }

    protected static final CcsApiOkHttpClientManager getInstance() {
        return FasthelpOkHttpClientInternal.INSTANCE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return FasthelpOkHttpClientInternal.INSTANCE;
    }
}
