package com.sbisec.helios.ap.pom.utils;

/**
 * API Url
 *
 * @author SCSK
 *
 */
public enum PomUrl {
    
    /** ポイント残高照会 */
    REFER_POINT("/api/refer_point");
    
    private final String url;
    
    private PomUrl(final String url) {
        
        this.url = url;
    }
    
    public String getUrl() {
        
        return this.url;
    }
    
}
