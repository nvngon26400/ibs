package com.sbisec.helios.ap.safe.service.common.dto;

import javax.validation.constraints.NotBlank;

/**
 * キャッシュクリアInDto
 */
public class CacheClearIn extends DtoIn {

    /**
     * コンストラクタ
     */
    public CacheClearIn() {
        super("common", "1.0.0", "cache.clear");
    }

    /** キャッシュ名(FQCNを含む) */
    @NotBlank
    private String cacheName;
    /** キャッシュ内のkey */
    private String cacheKey;

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }
}
