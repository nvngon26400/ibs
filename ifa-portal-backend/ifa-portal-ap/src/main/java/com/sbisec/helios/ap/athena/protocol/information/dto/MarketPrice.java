package com.sbisec.helios.ap.athena.protocol.information.dto;

import java.io.Serializable;

import com.sbisec.helios.ap.athena.protocol.common.PriceData;

import lombok.Data;

/**
 * マーケット価格 Dto.
 * 
 * 2023/11/22移植
 */
@Data
public class MarketPrice implements Serializable {
    
    private static final long serialVersionUID = -181685067076191022L;
    
    public MarketPrice() {
        
    }
    
    /** 国コード */
    private String countryCode;
    
    /** RIC */
    private String ric;
    
    /** リアルタイム種別 */
    private String realTimeType;
    
    /** 価格基本情報 */
    private PriceData price;
    
    /** 52週高値 */
    private String yearHigh;
    
    /** 52週高値日付("yyyy-MM-dd"形式日付) */
    private String yearHighDate;
    
    /** 52週安値 */
    private String yearLow;
    
    /** 52週安値日付("yyyy-MM-dd"形式日付) */
    private String yearLowDate;
    
    /** 株価収益率（予想） */
    private String expPer;
    
    /** 一株利益（予想） */
    private String expEps;
    
    /** 一株配当（予想） */
    private String expDividend;
    
    /** 配当利回り（予想） */
    private String expDivYield;
    
    /** 配当落ち日("yyyy-MM-dd"形式日付) */
    private String exDivDate;
    
    /** 配当支払日("yyyy-MM-dd"形式日付) */
    private String divPayDate;
    
    /** 買気配ベスト１０ */
    private String[] bid;
    
    /** 買気配ベスト１０数量 */
    private String[] bidSize;
    
    /** 売気配ベスト１０ */
    private String[] ask;
    
    /** 売気配ベスト１０数量 */
    private String[] askSize;
    
    /** デノミネーションレート */
    private String denominationRate;
}
