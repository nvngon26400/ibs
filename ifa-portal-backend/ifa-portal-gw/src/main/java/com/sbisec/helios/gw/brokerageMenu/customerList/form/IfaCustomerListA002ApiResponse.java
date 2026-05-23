package com.sbisec.helios.gw.brokerageMenu.customerList.form;

import lombok.Data;

/**
 * 顧客一覧_基本 表示 レスポンスパラメータ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaCustomerListA002ApiResponse {
    
    /** Cランク. */
    private String tcCompRank;
    
    /** 契約締結前交付書面コード名. */
    private String customerAttributeName;
    
    /** 顧客名（カナ）（全角半角）. */
    private String customerNameKana;
    
    /** 顧客名（漢字）（全角半角）. */
    private String customerNameKanji;
    
    /** 口座開設日. */
    private String openAcctDate;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 郵便番号(前)(後). */
    private String zipCodeBeforeAndAfter;
    
    /** 住所(漢字). */
    private String adressKanji;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String brokerChargeCode;
    
    /** 電話番号（英数字記号A(+-_./@*#%)）. */
    private String corporatePhoneNumber;
    
    /** 生年月日. */
    private String corBirthFlg;
    
    /** 年齢（数値(整数)）. */
    private String age;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 仲介業者支店名（支店名用）. */
    private String branchNameOfBranch;
    
    /** 仲介業者支店名（仲介業者名用）. */
    private String branchNameOfBroker;
    
    /** 担当者名（全角半角）. */
    private String chargeName;
    
    /** 仲介業者支店コード（数字）. */
    private String subBrokerId;
    
    /** 最終約定日. */
    private String lastTradeDate;
    
    /** 評価損益. */
    private String customerListProfitAndLoss;
    
    /** 総資産の合計. */
    private String totalAssets;
    
    /** 手数料累計の合計(年間手数料用). */
    private String commTotalAmountOfYear;
    
    /** 手数料累計の合計(累計手数料用). */
    private String commTotalAmount;
    
    /** 閲覧可能部店コード. */
    private String viewAblrButenCode;
    
    /** 金融資産区分（全角半角）. */
    private String financialAssetsType;
    
    /** 評価額（円貨）の合計. */
    private String valuationTotalJpyAmount;
    
    /** 電子交付承諾日付. */
    private String electronicDocConsentDate;
    
    /** 職業名. */
    private String professionName;

    /** NISA口座表示情報. */
    private String nisaContractKbnViewInfo;
    
    /** 外国証券取引口座表示情報. */
    private String foreignSecurityAccountViewInfo;
    
    /** 変更完了日時. */
    private String changeFinishDateTime;

    /** 取引制限. */
    private String tradeRestriction;
    
}
