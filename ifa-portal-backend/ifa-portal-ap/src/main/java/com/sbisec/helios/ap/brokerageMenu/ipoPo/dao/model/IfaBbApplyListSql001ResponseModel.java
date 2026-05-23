package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyListSql001ResponseModel {
    /** 総件数 */
    private int totalCount;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;

    /** 銘柄名（全角半角）. */
    private String brandName;

    /** 仲介業者コード（数字）. */
    private String brokerCode;

    /** 仲介業者名. */
    private String brokerName;

    /** 仲介業支店コード. */
    private String brokerBranchCode;

    /** 仲介業者支店名. */
    private String brokerBranchName;
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String brokerChargeCode;

    /** 仲介業者担当者名（全角半角）. */
    private String employeeName;

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 顧客名_姓名(漢字). */
    private String customerNameKanji;

    /** 顧客名_姓名(カナ). */
    private String customerNameKana;

    /** 契約締結前交付書面コード（半角英数字）. */
    private String course;
    
    /** 投資家属性名 */
    private String investorAttributeName;

    /** 数量（数値(整数)）. */
    private String bbQuantity;

    /** 発行価格区分. */
    private String issuePriceType;
    
    /** 裁量配分希望数量. */
    private String quantitySairyou;
    
    /** 申込価格. */
    private String bbPrice;
    
    /** 抽選結果. */
    private String lotteryResult;
    
    /** 抽選株数. */
    private String bbQuantityAlloc;
    
    /** 注文状況（全角半角）. */
    private String orderStatus;
    
    /** 注文株数 */
    private String orderQuantity;
    
    /** 預り区分 */
    private String depositType;
    
    /** 勧誘区分 */
    private String kanyuKbn;
    
    /** ワーニング申請チェック */
    private String warningApplyArranged;
    
    /** 明細番号 */
    private String detailNumber;
    
    /** 作成支店名. */
    private String sectionName;
    
    /** 備考（全角半角）. */
    private String bbRemark;
    
    /** 更新日. */
    private String updateDate;
    
    /** 上場日，受渡期日. */
    private String presentationDate;
    
    /** ブックビルディング申込期間（開始）（全角半角）. */
    private String bookBuildingPresentationFrom;
    
    /** 作成者. */
    private String bbCreateUserName;

    /** 作成日. */
    private String bbCreateDate;
    
    /** システム日付 */
    private String sysDate;
    
    /** uploadKey */
    private String uploadKey;
    
    /** 電子交付承諾日付 */
    private String edelivAgreementDate;
    
    /** 閲覧日時. */
    private String readTime;

}
