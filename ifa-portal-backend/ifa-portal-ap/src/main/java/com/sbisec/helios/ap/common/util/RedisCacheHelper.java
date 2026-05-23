package com.sbisec.helios.ap.common.util;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;

import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.exception.SystemErrorException;

import lombok.extern.slf4j.Slf4j;

/**
 * Redisキャッシュヘルパークラス。<br>
 * Filterクラス等のコンストラクタでの利用を考慮しBean登録は行わない。
 *
 * @author SCSK宮坂
 */
@Slf4j
public class RedisCacheHelper {
    
    /** JSON形式Redisキャッシュ済み設定インターフェース */
    public static interface RedisCachedJsonConfig extends RedisCachedDataObject {
    }
    
    /** Redisキャッシュ済みデータオブジェクトインターフェース */
    public static interface RedisCachedDataObject {
        
        /**
         * すべての設定項目に対するフィールド名とその値を連結した文字列表現を取得する。<br>
         * lombokによるtoStringメソッドの自動生成を推奨する。
         * 
         * @return すべてのフィールド名とその値の文字列表現。
         */
        public String toString();
    }
    
    /** Redisグループ名（設定情報） */
    private static final String REDIS_GROUP_CONFIG = "environment";
    
    /** キャッシュマネージャー */
    private CacheManager cacheManager = null;
    
    /**
     * コンストラクタ。<br>
     * 
     * @param cacheManager キャッシュマネージャー。
     */
    public RedisCacheHelper(CacheManager cacheManager) {
        
        this.cacheManager = cacheManager;
    }
    
    /**
     * JSON形式のRedisキャッシュ済み設定を取得する。
     * 
     * @param <T>                    JSON形式Redisキャッシュ済み設定。RedisCachedConfigインターフェースを実装したキャッシュデータのデシリアライズ結果を示すクラス。
     * @param key                    キャッシュキー。
     * @param redisCachedConfigClass JSON形式Redisキャッシュ済み設定クラス。
     * @return JSON形式Redisキャッシュ済み設定。
     * @throws SystemErrorException JSONデータのデシリアライズに失敗した場合。引数なしコンストラクタによるインスタンス生成に失敗した場合。
     */
    public <T extends RedisCachedJsonConfig> T getJsonConfig(String key, Class<T> redisCachedJsonConfigClass)
            throws SystemErrorException {
        
        T redisCachedConfig = null;
        
        // キャッシュからフィルタ設定のJSON文字列データを取得
        String redisCachedConfigJsonText = getCacheData(REDIS_GROUP_CONFIG, key, String.class);
        
        try {
            if (!StringUtils.isEmpty(redisCachedConfigJsonText)) {
                // 取得できた場合はJSONデータをデシリアライズ
                redisCachedConfig = JsonConverter.getInstance().toObject(redisCachedConfigJsonText,
                        redisCachedJsonConfigClass);
            } else {
                // 引数なしコンストラクタのリフレクションオブジェクトを取得
                Constructor<T> constructor = redisCachedJsonConfigClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                
                // 空の場合は引数なしコンストラクタによるデフォルト値を採用
                redisCachedConfig = constructor.newInstance();
            }
        } catch (IOException | NoSuchMethodException | IllegalAccessException | InvocationTargetException
                | InstantiationException e) {
            throw new SystemErrorException(e);
        }
        
        // 内容をデバッグログ出力
        log.debug(redisCachedConfig.toString());
        
        return redisCachedConfig;
    }
    
    /**
     * Redisキャッシュ済みデータオブジェクトを取得する。
     * 
     * @param <T>                        Redisキャッシュ済みデータオブジェクト。RedisCachedDataObjectインターフェースを実装したキャッシュデータのデシリアライズ結果オブジェクトを示すクラス。
     * @param group                      キャッシュグループ。
     * @param key                        キャッシュキー。
     * @param redisCachedDataObjectClass Redisキャッシュ済みデータオブジェクトクラス。
     * @return Redisキャッシュ済みデータオブジェクト。
     * @throws SystemErrorException Redisキャッシュデータの取得に失敗した場合。
     */
    public <T extends RedisCachedDataObject> T getDataObject(String group, String key,
            Class<T> redisCachedDataObjectClass) throws SystemErrorException {
        
        T redisCachedDataObject = null;
        
        // Redisキャッシュを取得して指定クラスでデシリアライズ
        redisCachedDataObject = getCacheData(group, key, redisCachedDataObjectClass);
        
        if (log.isDebugEnabled()) {
            if (redisCachedDataObject != null) {
                // 内容をデバッグログ出力
                log.debug(redisCachedDataObject.toString());
            }
        }
        
        return redisCachedDataObject;
    }
    
    /**
     * Redisキャッシュデータが存在するか判定する。
     * 
     * @param group キャッシュグループ。
     * @param key   キャッシュキー。
     * @return Redisキャッシュデータ。
     */
    public boolean existsCacheData(String group, String key) throws SystemErrorException {
        
        boolean existsCacheData = false;
        
        try {
            // Redisキャッシュデータを指定型で取得
            ValueWrapper valueWrapper = cacheManager.getCache(group).get(key);
            if (valueWrapper != null) {
                existsCacheData = true;
            }
        } catch (Exception e) {
            // すべてのエラーはシステムエラーとする
            throw new SystemErrorException(e);
        }
        
        return existsCacheData;
    }
    
    /**
     * Redisキャッシュデータを取得する。
     * 
     * @param <T>           Redisキャッシュデータ型。
     * @param group         キャッシュグループ。
     * @param key           キャッシュキー。
     * @param cacheDataType Redisキャッシュデータ型クラス。
     * @return Redisキャッシュデータ。
     * @throws SystemErrorException Redisキャッシュの取得に失敗した場合。
     */
    public <T> T getCacheData(String group, String key, Class<T> cacheDataType) throws SystemErrorException {
        
        T cacheData = null;
        
        try {
            // Redisキャッシュデータを指定型で取得
            cacheData = cacheManager.getCache(group).get(key, cacheDataType);
        } catch (Exception e) {
            // すべてのエラーはシステムエラーとする
            throw new SystemErrorException(e);
        }
        
        return cacheData;
    }
    
    /**
     * Redisキャッシュデータを設定する。
     * 
     * @param group     キャッシュグループ。
     * @param key       キャッシュキー。
     * @param cacheData Redisキャッシュデータ。
     * @throws SystemErrorException Redisキャッシュの設定に失敗した場合。
     */
    public void putCacheData(String group, String key, Object cacheData) throws SystemErrorException {
        
        try {
            // Redisキャッシュデータを設定
            cacheManager.getCache(group).put(key, cacheData);
        } catch (Exception e) {
            // すべてのエラーはシステムエラーとする
            throw new SystemErrorException(e);
        }
    }
    
    /**
     * Redisキャッシュデータを削除する。
     * 
     * @param group キャッシュグループ。
     * @param key   キャッシュキー。
     * @throws SystemErrorException Redisキャッシュの削除に失敗した場合。
     */
    public void removeCacheData(String group, String key) throws SystemErrorException {
        
        try {
            // Redisキャッシュデータを設定
            cacheManager.getCache(group).evict(key);
        } catch (Exception e) {
            // すべてのエラーはシステムエラーとする
            throw new SystemErrorException(e);
        }
    }
}
