package com.sbisec.helios.ap.athena.protocol.exchange.order.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class Errors implements Serializable {
    
    private static final long serialVersionUID = -4118638602706564830L;
    
    public Errors() {
        
    }
    
    /** メッセージ */
    private String message;
    
    /** エラー詳細 */
    private Extensions extensions;
    
}
