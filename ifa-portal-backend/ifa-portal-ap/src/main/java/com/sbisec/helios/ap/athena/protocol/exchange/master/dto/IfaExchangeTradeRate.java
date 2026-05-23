package com.sbisec.helios.ap.athena.protocol.exchange.master.dto;

import lombok.Data;

@Data
public class IfaExchangeTradeRate {

    /**レート情報.通貨コード*/
    private String currencyCode;
    
    /**レート情報.売買区分*/
    private String buySellCode;
    
    /**レート情報.デノミ*/
    private String basePrice;
    
    /**レート価格*/
    private String exchangePrice;
    
    /**レート情報.スプレッド*/
    private String adjustPrice;
    
    /**レート日時*/
    private String exchangeRateDatetime;
    
    /**レート情報.参考レート*/
    private String referenceExchangeRate;
    
    /**レート情報.概算用レート*/
    private String computeExchangeRate;
    
    /**リアルタイム発注レート*/
    private String lmReferenceExchangeRate;
    
    /**レート情報.上乗せ区分*/
    private String adjustType;
    
    /**レート情報.上乗せ金額(金額指定)*/
    private String adjustAmount;
    
    /**レート情報.上乗せ金額(パーセント指定)*/
    private String adjustPercent;
}
