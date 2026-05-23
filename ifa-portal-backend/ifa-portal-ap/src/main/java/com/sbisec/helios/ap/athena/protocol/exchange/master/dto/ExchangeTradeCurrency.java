package com.sbisec.helios.ap.athena.protocol.exchange.master.dto;

import lombok.Data;

@Data
public class ExchangeTradeCurrency {
    
    /**通貨アイテム.通貨コード*/
    private String currencyCode;
    
    /**通貨アイテム.通貨名*/
    private String currencyName;
    
    /**通貨アイテム.買単位*/
    private String buyUnit;
    
    /**通貨アイテム.売単位*/
    private String sellUnit;
    
    /**通貨アイテム.買下限*/
    private String buyLimitMin;
    
    /**通貨アイテム.売下限*/
    private String sellLimitMin;
    
    /**通貨アイテム.買上限*/
    private String buyLimitMax;
    
    /**通貨アイテム.売上限*/
    private String sellLimitMax;
    
    /**通貨アイテム.小数部有効桁数*/
    private Integer decimalLength;
    
    /**通貨アイテム.為替グループ*/
    private String exchangeGroup;
    
    /**通貨アイテム.国コード*/
    private String countryCode;
    
    /**通貨アイテム.受渡日数*/
    private Integer paymentDateOffset;
    
    /**通貨アイテム.締時間1。"HH:mm"形式*/
    private String closeTime1;
    
    /**通貨アイテム.締時間2。"HH:mm"形式*/
    private String closeTime2;
    
    /**通貨アイテム.為替取引*/
    private String exchangeTradeType;
    
    /**通貨アイテム.IFA向けリアルタイム為替有無*/
    private Boolean ifaTradable;
    
    /**通貨アイテム.買単位（IFAリアルタイム為替）*/
    private String ifaBuyUnit;
    
    /**通貨アイテム.売単位（IFAリアルタイム為替）*/
    private String ifaSellUnit;
    
    /**通貨アイテム.買下限（IFAリアルタイム為替）*/
    private String ifaBuyLimitMin;
    
    /**通貨アイテム.売下限（IFAリアルタイム為替）*/
    private String ifaSellLimitMin;
    
    /**通貨アイテム.買上限（IFAリアルタイム為替）*/
    private String ifaBuyLimitMax;
    
    /**通貨アイテム.売上限（IFAリアルタイム為替）*/
    private String ifaSellLimitMax;
    
    /**通貨アイテム.注文ワーニングしきい値*/
    private String warningThreshold;
    
    /**通貨アイテム.注文ワーニングしきい値（IFAリアルタイム為替）*/
    private String ifaWarningThreshold;
}
