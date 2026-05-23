package com.sbisec.helios.ap.athena.protocol.common;

import java.io.Serializable;

import lombok.Data;

/**
 * 株価情報 Dto.
 *
 * 2023/11/22移植
 */
@Data
public class PriceData implements Serializable {
    
    private static final long serialVersionUID = 1042466304330391104L;
    
    public PriceData() {
    
    }
    
    /** 現在値 */
    private String last;
    
    /** 現在値日時 */
    private String lastDatetime;
    
    /** ティック矢印(アップorダウン) */
    private String tickArrow;
    
    /** 前日比 */
    private String change;
    
    /** 前日比(%) */
    private String changePercent;
    
    /** 始値 */
    private String open;
    
    /** 始値日時 */
    private String openDatetime;
    
    /** 高値 */
    private String high;
    
    /** 高値日時 */
    private String highDatetime;
    
    /** 安値 */
    private String low;
    
    /** 安値日時 */
    private String lowDatetime;
    
    /** 前日終値 */
    private String prevClose;
    
    /** 前日終値日付 */
    private String prevCloseDate;
    
    /** 現在値or前日終値 */
    private String lastToPrevClose;
    
    /** 出来高 */
    private String volume;
    
    /** 出来高加重平均価格 */
    private String vwap;
}
