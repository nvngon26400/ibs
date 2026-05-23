package com.sbisec.helios.ap.athena.protocol.exchange.master;

import com.sbisec.helios.ap.athena.enums.CurrencyCode;

import lombok.Data;

@Data
public class GetExchangeTradeCurrencyRes {
    
    /** 通貨コード */
    private CurrencyCode currencyCode;
    
    /**通貨名*/
    private String currencyName;
    
    /**買単位*/
    private String buyUnit;
    
    /**売単位*/
    private String sellUnit;
    
    /**買下限*/
    private String buyLimitMin;
    
    /**売下限*/
    private String sellLimitMin;
    
    /**買上限*/
    private String buyLimitMax;
    
    /**売上限*/
    private String sellLimitMax;
    
    /**小数部有効桁数*/
    private int decimalLength;
    
    /**為替グループ*/
    private String exchangeGroup;
    
    /** 国コード */
    private String countryCode;
    
    /** 締時間1 */
    private String closeTime1;
    
    /** 締時間2 */
    private String closeTime2;
    
    /** 為替取引 */
    private String exchangeTradeType;
    
    /** IFA向けリアルタイム為替有無 */
    private Boolean ifaTradable;
    
    /** 買単位 */
    private String ifaBuyUnit;
    
    /** 売単位 */
    private String ifaSellUnit;
    
    /** 買下限 */
    private String ifaBuyLimitMin;
    
    /** 売下限 */
    private String ifaSellLimitMin;
    
    /** 買上限 */
    private String ifaBuyLimitMax;
    
    /** 売上限 */
    private String ifaSellLimitMax;
    
    /** 注文ワーニングしきい値 */
    private String warningThreshold;
    
    /** 注文ワーニングしきい値（IFAリアルタイム為替） */
    private String ifaWarningThreshold;
    
    /** 受渡日数 */
    private int paymentDateOffset;
}
