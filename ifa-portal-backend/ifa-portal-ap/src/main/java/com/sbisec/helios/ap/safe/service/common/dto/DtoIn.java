package com.sbisec.helios.ap.safe.service.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ServiceのInputのDtoベース
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DtoIn {

    /** サービス名 */
    private final String service;
    /** バージョン */
    private final String version;
    /** APIアクセス時のid */
    private final String id;

    /**
     * コンストラクタ
     * @param service サービス名
     * @param version バージョン
     * @param id APIアクセス時のid
     */
    public DtoIn(String service, String version, String id) {
        this.service = service;
        this.version = version;
        this.id = id;
    }

    @JsonIgnore
    public String getService() {
        return service;
    }

    @JsonIgnore
    public String getVersion() {
        return version;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }
}
