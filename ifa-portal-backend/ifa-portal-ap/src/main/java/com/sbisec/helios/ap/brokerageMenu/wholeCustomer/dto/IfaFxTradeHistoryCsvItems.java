package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 為替取引履歴　CSV項目
 *
 * @author SCSK川崎
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IfaFxTradeHistoryCsvItems extends ModelBase {
    
    /** シリアルナンバー */
    private static final long serialVersionUID = 3695133006142501392L;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者名 */
    private String brokerName;
    
    /** 営業員コード */
    private String empCode;
    
    /** 営業員名 */
    private String brokerChargeName;
    
    /** 部店 */
    private String buten;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** 扱者コード */
    private String dealerNumber;
    
    /** 取引コース */
    private String courseName;
    
    /** 顧客名(漢字) */
    private String customerNameKanji;
    
    /** 顧客名(カナ) */
    private String customerNameKana;
    
    /** 約定日 */
    private String tradeDate;
    
    /** 受渡日 */
    private String settlementDate;
    
    /** 取引種別 */
    private String tradeTypeName;
    
    /** 通貨 */
    private String currency;
    
    /** 為替レート */
    private String fxRate;
    
    /** 為替スプレッド */
    private String exchangeSpread;
    
    /** 円額 */
    private String yenAmount;
    
    /** 外貨額 */
    private String foreignAmount;
    
    /** 為替手数料（数値 */
    private String exchangeFee;
    
    /** 支店コード */
    private String brokerageBranchCode;
    
    /** 支店名 */
    private String brokerBranchName;
    
}
