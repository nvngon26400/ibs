package com.sbisec.helios.ap.athena.protocol.exchange.master;

import com.sbisec.helios.ap.athena.enums.BuySell;
import com.sbisec.helios.ap.athena.enums.CurrencyCode;

import lombok.Data;

@Data
public class ExchangeTradeRates {
    
    /**通貨コード*/
    private CurrencyCode currencyCode;
    
    /**売買区分*/
    private BuySell buySellCode;
    
    /**デノミ*/
    private String basePrice;
    
    /**レート価格*/
    private String exchangePrice;
    
    /**スプレッド*/
    private String adjustPrice;
    
    /**レート日時*/
    private String exchangeRateDatetime;
    
    /**参考レート*/
    private String referenceExchangeRate;
    
    /**概算用レート*/
    private String computeExchangeRate;
    
    /**リアルタイム発注レート*/
    private String lmReferenceExchangeRate;
    
    /**上乗せ区分*/
    private String adjustType;
    
    /**上乗せ金額(金額指定)*/
    private String adjustAmount;
    
    /**上乗せ金額(パーセント指定)*/
    private String adjustPercent;
}
