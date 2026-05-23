package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

@Data
public class IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel {
    
    /** 総数 */
    private int totalRow;
    
    /** 下落率区分（数字）. */
    private String declineRateKbn;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 契約締結前交付書面コード（半角英数字）. */
    private String customerAttribute;
    
    /** 顧客名_姓名(漢字). */
    private String nameKanji;
    
    /** 顧客名_姓名(カナ). */
    private String nameKana;
    
    /** 扱者コード（半角英数字）. */
    private String dealerNumber;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名. */
    private String branchName;
    
    /** 仲介業支店コード. */
    private String brokerBranchCode;
    
    /** 仲介業者支店名. */
    private String brokerBranchName;
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String brokerEmployeeCode;
    
    /** 仲介業者担当者名（全角半角）. */
    private String brokerChargeName;
    
    /** 投信協会コード（半角英数字）. */
    private String toushinKyoukaiCode;
    
    /** 投信銘柄名（全角半角）. */
    private String toushinBrandName;
    
    /** 基準日（From）. */
    private String baseDateFrom;
    
    /** 基準価額（From）. */
    private String basePriceFrom;
    
    /** 基準日（To）. */
    private String baseDateTo;
    
    /** 基準価額（To）. */
    private String basePriceTo;
    
    /** 前日比（数値(小数)）. */
    private String zenjitsuHi;
    
    /** 下落率（数値(小数)）. */
    private String declineRate;
    
    /** ステータス区分. */
    private String statusKbn;
    
    /** 対応方法区分. */
    private String methodsKbn;
    
    /** その他内容区分. */
    private String otherContentsKbn;
    
    /** その他詳細. */
    private String otherDetails;
    
    /** 顧客対応日. */
    private String customerSupportDate;
    
    /** 対応完了確認日. */
    private String completionConfirmationDate;
    
}
