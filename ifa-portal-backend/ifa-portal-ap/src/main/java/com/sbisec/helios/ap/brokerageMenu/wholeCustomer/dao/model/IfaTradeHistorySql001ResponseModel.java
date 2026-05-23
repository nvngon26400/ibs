package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

/**
 * 取引履歴 SQL001レスポンス
 *
 * @author SCSK
 *
 */
@Data
public class IfaTradeHistorySql001ResponseModel {

    /** 総件数. */
    private int totalRow;
    
    /** 閲覧可能部店 */
    private String visibleButenCode;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
    /** 仲介業支店コード. */
    private String branchCode;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 営業員名（全角半角）. */
    private String brokerChargeName;
    
    /** 顧客ID（数字）. */
    private String kokyakuId;
    
    /** 部店. */
    private String buten;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客名漢字. */
    private String customerNameKanji;
    
    /** 顧客名カナ. */
    private String customerNameKana;
    
    /** Cランク. */
    private String tcCompRank;
    
    /** 扱者コード（半角英数字）. */
    private String dealerNumber;
    
    /** コースコード. */
    private String customerAttribute;
    
    /** コース名. */
    private String courseName;
    
    /** 証券種別コード. */
    private String productCd;
    
    /** 証券種別. */
    private String securityClass;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 商品ランク（半角英数字）. */
    private String productRank;
    
    /** 単価（全角半角）. */
    private String price;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 取引種別コード. */
    private String tradeCd;
    
    /** 取引種別（全角半角）. */
    private String tradeTypeName;
    
    /** 約定日. */
    private String tradeDate;
    
    /** 受渡日. */
    private String settlementDate;
    
    /** 約定金額(円貨). */
    private String contractAmountJpyAmount;
    
    /** 手数料(円貨). */
    private String yenFee;
    
    /** 受渡金額(円貨). */
    private String yenDeliveryAmount;
    
    /** 通貨. */
    private String currency;
    
    /** 約定金額(外貨). */
    private String contractAmountForeign;
    
    /** 手数料(外貨). */
    private String foreignFee;
    
    /** 受渡金額(外貨). */
    private String foreignDeliveryAmount;
    
    /** 為替レート（数値(小数)）. */
    private String fxRate;
    
    /** 報酬率. */
    private String rewardRate;
    
    /** 仲介業支店名. */
    private String branchName;
    
    /** 債券　媒介/非媒介. */
    private String bondIntermediary;
    
    /** 米国株　店頭/委託. */
    private String usStockStoreEntrust;
    
    /** 仕組債区分. */
    private String structuredBondClassification;

	/** 預り区分. */
	private String depositName;
}
