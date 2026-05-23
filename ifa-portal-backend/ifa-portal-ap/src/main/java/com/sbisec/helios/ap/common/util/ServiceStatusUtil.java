package com.sbisec.helios.ap.common.util;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sbisec.helios.ap.common.dao.ServiceStatusDao;


public class ServiceStatusUtil {


	private static final Logger logger = LoggerFactory.getLogger(ServiceStatusUtil.class);

	private ServiceStatusDao dao;

	/** Expire time of cache. Default : 5 min. */
	private long expireTime = 300L * 1000L;

	/** NewsCache */
	private LoadingCache<String, Boolean> cache = null;

	/**
	 * Setting ServiceStatusDao.<br>
	 * DI from spring.
	 *
	 * @param dao
	 */
	public void setServiceStatusDao(ServiceStatusDao dao) {
		this.dao = dao;
		logger.debug("DI: setServiceStatusDao:[{}] of ServiceStatusUtil:[{}]", dao, this);
	}

	/**
	 * Setting expireTime.<br>
	 * DI from spring.
	 *
	 * @param expireTime
	 */
	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
		logger.debug("DI: setExpireTime:[{}] of ServiceStatusUtil:[{}]", expireTime, this);
	}

	/**
	 * コンストラクタ。
	 *
	 */
	public ServiceStatusUtil(){
	}

	/**
	 * createCache.<br>
	 * 有効期限(cacheExpire)を基にキャッシュを作成する。<br><br>
	 *
	 * getを行ってエントリがなかった場合、有効期限(cacheExpire)が切れている場合は、<br>
	 * キャッシュ側に対象のデータをロードする
	 *
	 * @param long cacheExpire 有効期限
	 * @return LoadingCache<String , Boolean>
	 */
	public LoadingCache<String , Boolean> createCache(long cacheExpire) {
		LoadingCache<String, Boolean> cache = CacheBuilder.newBuilder()
		.expireAfterWrite(cacheExpire, TimeUnit.MILLISECONDS)
		.build(new CacheLoader<String, Boolean>(){
			public Boolean load(String key) throws Exception {
				logger.debug("ServiceStatusUtil.LoadingCache[{}]", key);
				return dao.getServiceStatus(key);
			}
		});

		return cache;
	}
	/** 指定画面ID、画面表示できるかを取得する
	 *  キャッシュが構築されていない場合プロパティのexpireTimeによりキャッシュを作成する。
	 * @param productCd 銘柄コード
	 * @return Boolean
	 * @throws SQLException
	 */
	public Boolean getServiceStatus(String ServiceID) throws SQLException {
		try {
			if(cache == null){
				logger.debug("ServiceStatusUtil.getServiceStatus createCache cacheExpire:[{}]", expireTime);
				cache = createCache(expireTime);
			}
			return cache.get(ServiceID);
		} catch (ExecutionException e) {
			return Boolean.TRUE;
		}
	}

}
