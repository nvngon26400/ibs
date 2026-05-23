package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyListX002BbApplyListResponseDto {
    /** 銘柄コード */
    private String brandCode;
    /** 銘柄名 */
    private String brandName;
    /** 仲介業者コード */
    private String brokerCode;
    /** 仲介業者名 */
    private String brokerName;
    /** 仲介業支店コード */
    private String brokerBranchCode;
    /** 仲介業者支店名 */
    private String brokerBranchName;
    /** 仲介業者営業員コード */
    private String brokerChargeCode;
    /** 仲介業者担当者名 */
    private String employeeName;
    /** 部店コード */
    private String butenCode;
    /** 口座番号 */
    private String accountNumber;
    /** 顧客名_姓名(漢字) */
    private String customerNameKanji;
    /** 顧客名_姓名(カナ) */
    private String customerNameKana;
    /** 取引コース */
    private String course;
    /** 投資家属性名 */
    private String investorAttributeName;
    /** BB申込株数 */
    private String bbQuantity;
    /** 申込価格 */
    private String bbPrice;
    /** 裁量希望株数 */
    private String quantitySairyou;
    /** 当選株数 */
    private String bbQuantityAlloc;
    /** 抽選結果 */
    private String lotteryResult;
    /** 注文状況 */
    private String orderStatus;
    /** 注文株数 */
    private String orderQuantity;
    /** 預り区分 */
    private String depositType;
    /** 申込日時 */
    private String bbCreateDate;
    /** 申込者 */
    private String bbCreateUserName;
    /** セクション名 */
    private String sectionName;
    /** 電子交付同意 */
    private String edelivAgreementDate;
    /** 目論見書閲覧 */
    private String readTime;
    /** 備考 */
    private String bbRemark;
    /** 更新日 */
    private String updateDate;
    /** ブックビルディング申込期間（開始） */
    private String bookBuildingPresentationFrom;
    /** 勧誘区分 */
    private String kanyuKbn;
    /** ワーニング申請済 */
    private String warningApplyArranged;
    /** 明細番号 */
    private String detailNumber;
}
