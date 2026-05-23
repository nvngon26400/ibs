package com.sbisec.helios.ap.athena.protocol.exchange.order.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class Extensions implements Serializable {
    
    private static final long serialVersionUID = -2589423042508433150L;
    
    public Extensions() {
        
    }
    
    /** エラーコード */
    private String errorCode;
    
    /** ステータスコード */
    private String statusCode;
    
    /** エラータイプ */
    private String classification;
    
}
