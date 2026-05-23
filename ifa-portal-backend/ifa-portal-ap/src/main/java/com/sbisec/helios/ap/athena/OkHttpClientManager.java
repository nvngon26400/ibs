package com.sbisec.helios.ap.athena;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sbisec.helios.ap.athena.interceptor.NetworkIntercepter;
import com.sbisec.helios.ap.athena.interceptor.RetryInterceptor;
import com.sbisec.helios.ap.athena.utils.AthenaException;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

/**
 * <p>
 * Description:OkHttpClientManager
 * </p>
 *
 * @author shuchen.xin
 * @version 1.0
 * @date 5/20/2021
 */
public class OkHttpClientManager implements Cloneable {

	private static class OkHttpClientInternal {
		private static final OkHttpClientManager INSTANCE = new OkHttpClientManager();
	}

	private static final Logger LOG = LoggerFactory.getLogger(OkHttpClientManager.class);

	private static volatile boolean isCreate = false;

	private OkHttpClient client;

	private long connTimeout;
	private long readTimeout;
	private long writeTimeout;
	private int connPoolTimeout;
	private int connPoolCachedMax;
	private int retryCount;

	private OkHttpClientManager() {
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

	private synchronized void create() throws AthenaException {
		try {
			ConnectionPool pool = new ConnectionPool(this.connPoolTimeout, this.connPoolCachedMax, TimeUnit.MINUTES);
			this.client = new OkHttpClient().newBuilder().connectTimeout(this.connTimeout, TimeUnit.SECONDS)
					.readTimeout(this.readTimeout, TimeUnit.SECONDS).writeTimeout(this.writeTimeout, TimeUnit.SECONDS)
					.retryOnConnectionFailure(false).addInterceptor(new RetryInterceptor(this.retryCount))
					.addNetworkInterceptor(new NetworkIntercepter()).connectionPool(pool).build();
		} catch (Exception e) {
			LOG.error("Comet Exception occured.", e);
			throw e;
		}
	}

	public OkHttpClientManager setReadTimeout(long readTimeout) {
		this.readTimeout = readTimeout;
		return this;
	}

	public OkHttpClientManager setWriteTimeout(long writeTimeout) {
		this.writeTimeout = writeTimeout;
		return this;
	}

	public OkHttpClientManager setConnTimeout(long connTimeout) {
		this.connTimeout = connTimeout;
		return this;
	}

	public OkHttpClientManager setConnPoolTimeout(int connPoolTimeout) {
		this.connPoolTimeout = connPoolTimeout;
		return this;
	}

	public OkHttpClientManager setConnPoolCachedMax(int connPoolCachedMax) {
		this.connPoolCachedMax = connPoolCachedMax;
		return this;
	}

	public OkHttpClientManager setRetryCount(int retryCount) {
		this.retryCount = retryCount;
		return this;
	}

	public OkHttpClient getClient() throws AthenaException {
		if (null == this.client) {
			synchronized (this) {
				if (null == this.client) {
					this.create();
				}
			}
		}
		return this.client;
	}

	protected static final OkHttpClientManager getInstance() {
		return OkHttpClientInternal.INSTANCE;
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
		return OkHttpClientInternal.INSTANCE;
	}
}
