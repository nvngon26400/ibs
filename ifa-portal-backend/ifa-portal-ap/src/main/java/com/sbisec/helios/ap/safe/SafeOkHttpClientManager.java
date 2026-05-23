package com.sbisec.helios.ap.safe;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sbisec.helios.ap.safe.interceptor.SafeRetryInterceptor;
import com.sbisec.helios.ap.safe.utils.SafeException;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

/**
 * <p>
 * Description:SafeOkHttpClientManager
 * </p>
 *
 * @author nicksen.li
 * @version 1.0
 * @date 3/31/2025
 */
public class SafeOkHttpClientManager implements Cloneable {

	private static class SafeOkHttpClientInternal {
		private static final SafeOkHttpClientManager INSTANCE = new SafeOkHttpClientManager();
	}

	private static final Logger LOG = LoggerFactory.getLogger(SafeOkHttpClientManager.class);

	private static volatile boolean isCreate = false;

	private OkHttpClient client;

	private long connTimeout;
	private long readTimeout;
	private long writeTimeout;
	private int connPoolTimeout;
	private int connPoolCachedMax;
	private int retryCount;

	private SafeOkHttpClientManager() {
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

	private synchronized void create() throws SafeException {
		try {
			ConnectionPool pool = new ConnectionPool(this.connPoolTimeout, this.connPoolCachedMax, TimeUnit.MINUTES);
			this.client = new OkHttpClient().newBuilder().connectTimeout(this.connTimeout, TimeUnit.SECONDS)
					.readTimeout(this.readTimeout, TimeUnit.SECONDS)
					.writeTimeout(this.writeTimeout, TimeUnit.SECONDS)
					.retryOnConnectionFailure(false)
					.addInterceptor(new SafeRetryInterceptor(this.retryCount))
					.connectionPool(pool).build();
		} catch (Exception e) {
			LOG.error("Safe Exception occured.", e);
			throw e;
		}
	}

	public SafeOkHttpClientManager setReadTimeout(long readTimeout) {
		this.readTimeout = readTimeout;
		return this;
	}

	public SafeOkHttpClientManager setWriteTimeout(long writeTimeout) {
		this.writeTimeout = writeTimeout;
		return this;
	}

	public SafeOkHttpClientManager setConnTimeout(long connTimeout) {
		this.connTimeout = connTimeout;
		return this;
	}

	public SafeOkHttpClientManager setConnPoolTimeout(int connPoolTimeout) {
		this.connPoolTimeout = connPoolTimeout;
		return this;
	}

	public SafeOkHttpClientManager setConnPoolCachedMax(int connPoolCachedMax) {
		this.connPoolCachedMax = connPoolCachedMax;
		return this;
	}

	public SafeOkHttpClientManager setRetryCount(int retryCount) {
		this.retryCount = retryCount;
		return this;
	}

	public OkHttpClient getClient() throws SafeException {
		if (null == this.client) {
			synchronized (this) {
				if (null == this.client) {
					this.create();
				}
			}
		}
		return this.client;
	}

	protected static final SafeOkHttpClientManager getInstance() {
		return SafeOkHttpClientInternal.INSTANCE;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return SafeOkHttpClientInternal.INSTANCE;
	}
}
