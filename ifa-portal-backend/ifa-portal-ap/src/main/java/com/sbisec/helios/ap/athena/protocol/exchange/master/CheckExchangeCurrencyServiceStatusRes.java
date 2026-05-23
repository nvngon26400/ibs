package com.sbisec.helios.ap.athena.protocol.exchange.master;

import lombok.Data;

@Data
public class CheckExchangeCurrencyServiceStatusRes {
    
    public CheckExchangeCurrencyServiceStatusRes() {
        
    }
    
    /**利用可否*/
    private boolean available;
    
    /** 利用不可開始日時 */
    private String unavailableDatetimeFrom;
    
    /** 利用不可終了日時 */
    private String unavailableDatetimeTo;
    
    /** メッセージ */
    private String message;
    
}
