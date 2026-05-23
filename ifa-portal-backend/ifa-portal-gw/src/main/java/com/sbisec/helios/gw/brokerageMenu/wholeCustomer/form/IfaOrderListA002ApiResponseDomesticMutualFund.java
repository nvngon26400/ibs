package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;


import lombok.Data;

/**
 * 国内投資信託
 *
 * @author BASE李
 *
 */
@Data
public class IfaOrderListA002ApiResponseDomesticMutualFund {

    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
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
    
    /** ファンド正式名. */
    private String fundName;
    
    /** 注文状況（全角半角）. */
    private String orderStatus;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** ポイント種別（半角英数字）. */
    private String pointType;
    
    /** 注文時ポイント. */
    private String orderPoint;
    
    /** 分配金受取方法指定. */
    private String distributionReceiveMethodDesignation;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String orderMethod;
    
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
    
    /** ファンドコード（算出）. */
    private String fundCodeCalculation;
    
    /** 口数/金額（算出）. */
    private String unitAmountCalculation;
    
    /** 受注日時（算出）. */
    private String orderDayTimeCalculation;

    /** 商品区分 */
    private String orderType;
    
    /** 売買区分 */
    private String tradeKbn;

    /** 発注日 */
    private String orderPlacementDate;

    /** 特定口座区分 */
    private String specificAccountType;

}
