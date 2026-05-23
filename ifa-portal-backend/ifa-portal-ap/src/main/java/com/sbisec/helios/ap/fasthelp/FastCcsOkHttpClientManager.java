package com.sbisec.helios.ap.fasthelp;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sbisec.helios.ap.fasthelp.exception.FastCcsException;
import com.sbisec.helios.ap.fasthelp.interceptor.FasthelpRetryInterceptor;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

/**
 * <p>
 * Description:FastCcsOkHttpClientManager
 * </p>
 *
 * @author dalian
 * @version 1.0
 * @date 3/31/2025
 */
public class FastCcsOkHttpClientManager implements Cloneable {

    private static class FasthelpOkHttpClientInternal {
        private static final FastCcsOkHttpClientManager INSTANCE = new FastCcsOkHttpClientManager();
    }

    private static final Logger LOG = LoggerFactory.getLogger(FastCcsOkHttpClientManager.class);

    private static volatile boolean isCreate = false;

    private OkHttpClient client;

    private long connTimeout;
    private long readTimeout;
    private long writeTimeout;
    private int connPoolTimeout;
    private int connPoolCachedMax;
    private int retryCount;

    private FastCcsOkHttpClientManager() {
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

    private synchronized void create() throws FastCcsException {
        try {
            ConnectionPool pool = new ConnectionPool(this.connPoolTimeout, this.connPoolCachedMax, TimeUnit.MINUTES);
            this.client = new OkHttpClient().newBuilder().connectTimeout(this.connTimeout, TimeUnit.SECONDS)
                    .readTimeout(this.readTimeout, TimeUnit.SECONDS)
                    .writeTimeout(this.writeTimeout, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false)
                    .addInterceptor(new FasthelpRetryInterceptor(this.retryCount))
                    .connectionPool(pool).build();
        } catch (Exception e) {
            LOG.error("Fasthelp Exception occured.", e);
            throw e;
        }
    }

    public FastCcsOkHttpClientManager setReadTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public FastCcsOkHttpClientManager setWriteTimeout(long writeTimeout) {
        this.writeTimeout = writeTimeout;
        return this;
    }

    public FastCcsOkHttpClientManager setConnTimeout(long connTimeout) {
        this.connTimeout = connTimeout;
        return this;
    }

    public FastCcsOkHttpClientManager setConnPoolTimeout(int connPoolTimeout) {
        this.connPoolTimeout = connPoolTimeout;
        return this;
    }

    public FastCcsOkHttpClientManager setConnPoolCachedMax(int connPoolCachedMax) {
        this.connPoolCachedMax = connPoolCachedMax;
        return this;
    }

    public FastCcsOkHttpClientManager setRetryCount(int retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public OkHttpClient getClient() throws FastCcsException {
        if (null == this.client) {
            synchronized (this) {
                if (null == this.client) {
                    this.create();
                }
            }
        }
        return this.client;
    }

    protected static final FastCcsOkHttpClientManager getInstance() {
        return FasthelpOkHttpClientInternal.INSTANCE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return FasthelpOkHttpClientInternal.INSTANCE;
    }
}
