package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;


import lombok.Data;

/**
 * 国内投資信託
 *
 * @author SCSK
 *
 */
@Data
public class IfaOrderListSql002ResponseModel {
    
    /** 総件数 */
    private int totalCount;
    
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
    
    /** ファンドコード（回数）（半角英数字）. */
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    private String fundCodeIssues;
    
    /** ファンド正式名. */
    private String fundName;
    
    /** 注文状況（全角半角）. */
    private String orderStatus;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 受注日. */
    private String orderDate;
    
    /** 受注時刻. */
    private String orderTime;
    
    /** 口数（数値(整数)）. */
    private String unit;
    
    /** 金額（数値(整数)）. */
    private String amount;
    
    /** ポイント. */
    private String pointType;
    
    /** 注文時ポイント */
    private String orderPoint;
    
    /** 分配金受取方法指定. */
    private String distributionReceiveMethodDesignation;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String orderMethod;
    
    /** 乗換優遇区分（半角英数字）. */
    private String norikaeYuguKbn;
    
    /** アラート内容確認. */
    private String checkCompWrnAlert;
    
    /** 目論見書の交付方法（半角英数字）. */
    private String mokuromiKoufuKbn;
    
    /** 乗換勧誘（半角英数字）. */
    private String norikaeKanyuKbn;
    
    /** 利益相反可能性の説明. */
    private String conflictOfInterestExplain;
    
    /** 確認項目.費用について説明済. */
    private String confirmItemHiyou;
    
    /** 確認項目.複数取引業者での手数料等明示済 */
    private String confirmItemFukusu;
    
    /** 同一通貨/同一国の乗換. */
    private String douitsuTukaKuniKbn;
    
    /** 他社間投信乗換勧誘（半角英数字）. */
    private String tashaNorikaeKbn;
    
    /** 短期売却確認（半角英数字）. */
    private String tankiSellKbn;
    
    /** 償還前売却確認（半角英数字）. */
    private String shokanMaeKbn;
    
    /** ユーザー名. */
    private String userName;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名. */
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
    
    /** 商品区分 */
    private String orderType;
    
    /** 売買区分 */
    private String tradeKbn;

    /** 発注日 */
    private String orderPlacementDate;

    /** 特定口座区分 */
    private String specificAccountType;
}
