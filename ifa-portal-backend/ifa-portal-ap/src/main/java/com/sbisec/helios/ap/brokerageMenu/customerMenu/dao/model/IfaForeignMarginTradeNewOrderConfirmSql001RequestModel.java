package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import java.util.Date;

import lombok.Data;

/**
 * 発注前の注文登録リクエストモデル
 *
 * @author SCSK 金志
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderConfirmSql001RequestModel {
    
    /** 日付(8桁)+通番(5桁). */
    private String ifaOrderNo;
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 注文Sub番号（数字）. */
    private String orderSubNumber;
    
    /** 顧客共通情報.部店コード. */
    private String butenCode;
    
    /** 顧客共通情報.口座番号. */
    private String accountNumber;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 市場コード（全角半角）. */
    private String marketCode;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 注文数量. */
    private Long foreignQuantity;
    
    /** 価格条件. */
    private String orderPriceKindList;
    
    /** 注文単価（数値(小数)）. */
    private String hiddenOrderPrice;
    
    /** 発火条件価格. */
    private String stopOrderPrice2;
    
    /** 取引通貨. */
    private String stopOrderPriceText2;
    
    /** 決済方法（全角半角）. */
    private String kessaiHoho;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String orderClassification;
    
    /** 重要事項の説明. */
    private String importantMatterType;
    
    /** 英文開示銘柄. */
    private String engPubText;
    
    /** 確認項目.インサイダー確認（全角半角）. */
    private String checkInsider;
    
    /** 注文日時. */
    private Date orderDateTime;
    
    /** 国内約定日. */
    private String tradeDate;
    
    /** 期間. */
    private String period;
    
    /** 顧客共通情報.仲介業者コード. */
    private String brokerCode;
    
    /** 顧客共通情報.仲介業者営業員コード. */
    private String brokerChargeCode;
    
    /** ユーザ共通情報.ユーザーID. */
    private String userId;
    
}
