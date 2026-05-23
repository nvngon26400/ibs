package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 投信詳細情報 SQL009応答
 *
 * @author SCSK
 *
 */
@Data
public class IfaMutualFundDetailInfoSql009ResponseModel {
    
    /** 協会コード（全角半角）. */
    private String fDCode;
    
    /** 協会分類コード. */
    private String fDGroup;
    
    /** 運用方針（全角半角）. */
    private String fDPolicy;
    
    /** 信託報酬. */
    private String fDTrustCharge;
    
    /** 信託財産留保額（全角半角）. */
    private String fDReservedAsset;
    
    /** 申込手数料補足. */
    private String fDEntryText;
    
    /** 解約手数料. */
    private String fDKaiyakuText;
    
    /** 決算日. */
    private String fDAccountingDate;
    
    /** 設定日. */
    private String fDSettingDate;
    
    /** 分配金（扱い）（全角半角）. */
    private String fDDividendsSchedule;
    
    /** 備考（注意事項). */
    private String fDInstructions;
    
    /** 平均信託金. */
    private String fDTrustMoneyAverage;
    
    /** 休日. */
    private String fDHoliday;
    
    /** 信託報酬率（販社）. */
    private String fDTrustChargeRatioSales;
    
    /** 信託報酬率（委託）. */
    private String fDTrustChargeRatioTrust;
    
    /** 信託報酬率（受託）. */
    private String fDTrustChargeRatioTrustee;
    
    /** Star III 反映日&Web反映日. */
    private String fDDaysWeb;
    
    /** 備考（社内用）. */
    private String fDRemarks;
    
    /** 売却方法. */
    private String fDSellWay;
    
    /** 償還日区分. */
    private String fDSyokanKbn;
    
}
