package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;

import lombok.Data;

/**
 * 画面ID：SUB0204_02-04_1
 * 画面名：募集入力
 * 2024/2/6 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaSubscriptInputA001ResponseDto {

    /** 銘柄コード（BB） */
    private String brandCode;

    /** 銘柄名称 */
    private String brandName;

    /** たばこ開示（全角半角） */
    private String smokingCigarette;

    /** 電子交付のみ（全角半角） */
    private String onlyElectronicDelivery;

    /** 発行価格（数値(小数)） */
    private String issueBbPrice;

    /** 売買単位（数値(整数)） */
    private String unit;

    /** 売買単位区分（半角英数字） */
    private String sellBuyUnitType;

    /** 募集期間（From） */
    private String bbPeriodFrom;

    /** 募集期間（To） */
    private String bbPeriodTo;

    /** 入金予定日（募集最終日） */
    private String paymentDate;

    /** 手数料 */
    private String subscriptComm;

    /** 部店コード（半角英数字） */
    private String butenCode;

    /** 口座番号（数字） */
    private String accountNumber;

    /** 顧客名（漢字）（全角半角） */
    private String customerNameKanji;

    /** 口座開設年月日 */
    private String openAcctDate;

    /** 本人職業区分 */
    private String profession;

    /** 勤務先名(漢字) */
    private String officeName;

    /** コンプラランク（半角英数字） */
    private String complianceRank;

    /** 投資方針区分 */
    private String investmentPolicyType;

    /** 投資経験年数(株式現物)（数字） */
    private String stockSpotInvestExperienceYears;

    /** 金融資産区分（半角英数字） */
    private String financialAssetsType;

    /** 本年の年間裁量配分割当回数（数値(整数)） */
    private String discretionAlloCount;

    /** 本年の年間裁量配分可能回数（数値(整数)） */
    private String discretionAllocateAbleTimes;

    /** 電子承諾日付 */
    private String edelivAgreementDate;

    /** 閲覧日時 */
    private String readTime;

    /** 抽選結果 */
    private String lotteryResult;

    /** 当選株数（数値(整数)） */
    private String bbQuantityAlloc;

    /** BB申込株数（数値(整数)） */
    private String bbQuantity;

    /** 注文状況（全角半角） */
    private String orderStatus;

    /** 募集受注日時 */
    private String recruitmentOrderDate;

    /** 数量（数値(整数)） */
    private String quantity;

    /** 約定金額（数値(整数)） */
    private String contractAmount;

    /** 募集取消日時 */
    private String subscriptCancelDayTime;

    /** 銘柄コード（対面） */
    private String brandCode12;

    /** 預り区分（全角半角） */
    private String depositType;

    /** 勧誘区分（全角半角） */
    private String kanyuKbn;

    /** 受注方法 */
    private String jutyuKbn;

    /** 目論見書 */
    private String prospectus;

    /** 重要事項の説明 */
    private String importantMatterType;

    /** ワーニング申請（全角半角） */
    private String warningApply;

    /** 備考（全角半角） */
    private String bbRemark;

    /** 顧客コード（数字） */
    private String customerCode;

    /** ブックビルディング申込期間（開始）（全角半角） */
    private String bookBuildingPresentationFrom;

    /** 明細番号（全角半角） */
    private String detailNumber;

    /** 仲介業者コード（数字） */
    private String brokerCode;

    /** 仲介業者営業員コード（半角英数字） */
    private String brokerChargeCode;

    /** 扱者コード（半角英数字） */
    private String dealerNumber;

    /** 上場日 */
    private String presentationDate;

    /** 配分上限株数（数値(整数)） */
    private String maxAllocation;

    /** 銘柄コード１付き */
    private String brandCodeWith1;

    /** 特定口座区分（半角英数字） */
    private String tokuteiKouzaKbn;

    /** ISA契約区分 */
    private String isaContractType;

    /** ISA買付可能判定区分（当年）（半角英数字） */
    private String isaBuyAbleJudgeClassificationYear;

    /** 更新時間（注文排他用） */
    private String updateTimeForOrderExclusivity;

    /** つみたてNISAフラグ（半角英数字） */
    private String accumulateNisaFlag;

    /** 預り資産額（数値(整数)） */
    private String depositAssetAmount;

    /** 送信・訂正用ロジック処理判定フラグ（全角半角） */
    private String sendCorrectLogicJudgeFlag;

    /** 買付余力 */
    private String yenBuyingPowerGeneralAccount;

    /** ジュニアNISAフラグ（半角英数字） */
    private String juniorNisaFlag;

    /** NISA買付可能額 */
    private String nisaBuyPotentialAmount;
}
