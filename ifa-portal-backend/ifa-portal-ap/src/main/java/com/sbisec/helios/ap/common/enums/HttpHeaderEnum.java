package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * HTTPヘッダenum。
 *
 * @author SCSK宮坂
 */
@JsonFormat(shape = Shape.OBJECT)
public enum HttpHeaderEnum {
    
    /** セッションID */
    SESSION_ID("frameworkSessionId");
    
    /** ヘッダ名 */
    private final String name;
    
    /**
     * コンストラクタ。
     *
     * @param name ヘッダ名。
     */
    private HttpHeaderEnum(String name) {
        
        this.name = name;
    }
    
    /**
     * ヘッダ名を取得する。
     *
     * @return ヘッダ名。
     */
    public String getName() {
        
        return name;
    }
}
