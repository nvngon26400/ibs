package com.sbisec.helios.gw.common.util;

import java.util.concurrent.Callable;

import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 *　業務用個別キャッシュ
 *
 * @author 河口
 *
 */
public class IfaCacheAccessor implements Cache {
    
    /* キャッシュに登録する際に登録する際の接頭辞 */
    private static final String KEY_PREFIX = "bizCustom.";
    
    /* ラッピング対象のキャッシュ情報　*/
    private Cache cache;

    /**
     * コンストラクタ
     */
    private IfaCacheAccessor() {
        
        CacheManager cacheManager = IfaCommonUtil.getWebApplicationContext().getBean(CacheManager.class);
        
        // ここで設定した接頭辞は、自動で付与されるため個々のメソッドで接頭辞を付与する対応は不要
        this.cache = cacheManager.getCache(
                KEY_PREFIX + IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class));
    }
    
    /**
     * IfaCacheAccessorのインスタンスを生成する
     *
     * @return IfaCacheAccessorのインスタンス
     */
    public static IfaCacheAccessor createInstance() {
        
        return new IfaCacheAccessor();
    }

    @Override
    public String getName() {
        
        return cache.getName();
    }
    
    @Override
    public Object getNativeCache() {
        
        return cache.getNativeCache();
    }
    
    @Override
    public ValueWrapper get(Object key) {
        
        return cache.get(key);
    }
    
    @Override
    public <T> T get(Object key, Class<T> type) {
        
        return cache.get(key, type);
    }
    
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        
        return cache.get(key, valueLoader);
    }
    
    @Override
    public void put(Object key, Object value) {
        
        cache.put(key, value);
    }
    
    @Override
    public void evict(Object key) {
        
        cache.evict(key);
    }
    
    @Override
    public void clear() {
        
        cache.clear();
    }
}
