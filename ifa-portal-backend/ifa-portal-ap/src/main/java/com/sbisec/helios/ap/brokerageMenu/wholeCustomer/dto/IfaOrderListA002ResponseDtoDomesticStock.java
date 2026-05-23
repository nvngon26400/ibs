package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;


import lombok.Data;

/**
 * 国内株式
 *
 * @author SCSK
 *
 */
@Data
public class IfaOrderListA002ResponseDtoDomesticStock {

    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 契約締結前交付書面コード（半角英数字）. */
    private String customerAttribute;
    
    /** 顧客名_姓名(漢字). */
    private String customerNameKanji;
    
    /** 顧客名_姓名(カナ). */
    private String customerNameKana;
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 市場（全角）. */
    private String market;
    
    /** 注文状況（全角半角）. */
    private String orderStatus;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 注文種別(一覧). */
    private String orderClassList;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
    /** 手数料区分(採用). */
    private String comIdR;
    
    /** 有効期限（全角半角）. */
    private String yukoKigen;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String orderMethod;
    
    /** ユーザー名. */
    private String userName;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
    /** 仲介業支店コード. */
    private String brokerageBranchCode;
    
    /** 仲介業者支店名. */
    private String brokerBranchName;
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String brokerChargeCode;
    
    /** 仲介業者担当者名（全角半角）. */
    private String employeeName;
    
    /** 閲覧可能部店コード. */
    private String viewAblrButenCode;
    
    /** 指成区分（半角英数字）. */
    private String sasinariKbn;
    
    /** 指値（数値(小数)）. */
    private String sashine;
    
    /** 現在値（数値(小数)）. */
    private String currentPrice;
    
    /** 注文条件. */
    private String orderTerms;
    
    /** 受注日時（算出）. */
    private String orderDayTimeCalculation;

    /** 弁済期限（算出） */
    private String paymentDeadlineCalculation;

    /** 受付有効期限 */
    private String acceptLimit;

}
