package com.sbisec.helios.ap.pom.utils;

/**
 * API間で共通で使用する区分
 *
 * @author SCSK
 *
 */
public enum PomCode {
    
    CHANNEL_TYPE(99, "チャネル区分 その他"),
    RESULT_SUCCESS(0, "正常応答"),
    POINT_NONE(0, "ポイント利用なし");
    
    private final Integer value;
    
    private final String desc;
    
    private PomCode(final Integer value, final String desc) {
        
        this.value = value;
        this.desc = desc;
    }
    
    public Integer getValue() {
        
        return this.value;
    }
    
    public String getDesc() {
        
        return this.desc;
    }
}
