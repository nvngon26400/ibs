package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0204_02-04_1
 * 画面名：募集入力
 * 2024/2/6 新規作成
 *
 * @author SCSK 江口
 * 
 */
@Data
public class IfaSubscriptInputA002ApiResponse {

    /** 注意情報アラート（全角半角） */
    private String noticeInfoAlert;

    /** お知らせアラート（全角半角） */
    private String noticeAlert;

    /** コンプラランクチェック */
    private IfaSubscriptInputA002ComplianceRankCheckApiResponse complianceRankCheck;

    /** 預り区分アラート（全角半角） */
    private List<String> depositTypeAlert;

    /** 注意情報レベル */
    private String noticeInfoLevel;

    /** 顧客名（カナ）（全角半角） */
    private String customerNameKana;

    /** 注文フラグ（半角英数字） */
    private String orderFlag;

    /** 銘柄名称 */
    private String brandName;

    /** 銘柄コード（半角英数字） */
    private String brandCode;

    /** 発行価格（数値(小数)） */
    private String issueBbPrice;

    /** 募集期間（To） */
    private String bbPeriodTo;

    /** 顧客名（漢字）（全角半角） */
    private String customerNameKanji;

    /** 電子交付同意 */
    private String edelivAgreementDate;

    /** 目論見書閲覧 */
    private String readTime;

    /** 抽選結果 */
    private String lotteryResult;

    /** 当選株数（数値(整数)） */
    private String bbQuantityAlloc;

    /** 注文状況（全角半角） */
    private String orderStatus;

    /** 数量（数値(整数)） */
    private String quantity;

    /** 約定金額（数値(整数)） */
    private String contractAmount;

    /** 預り区分（全角半角） */
    private String depositType;

    /** 勧誘区分（全角半角） */
    private String kanyuKbn;

    /** 受注方法 */
    private String jutyuKbn;

    /** 目論見書の交付方法（半角英数字） */
    private String mokuromiKoufuKbn;

    /** 重要事項の説明 */
    private String importantMatterType;

    /** 備考（全角半角） */
    private String bbRemark;

    /** 部店コード（半角英数字） */
    private String butenCode;

    /** 口座番号（数字） */
    private String accountNumber;

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

    /** 顧客コード（数字） */
    private String customerCode;

    /** 上場日 */
    private String presentationDate;

    /** 更新時間（注文排他用） */
    private String updateTimeForOrderExclusivity;

    /** 売買単位（数値(整数)） */
    private String unit;

    /** 売買単位区分（半角英数字） */
    private String sellBuyUnitType;

    /** 配分上限株数（数値(整数)） */
    private String maxAllocation;

    /** 送信・訂正用ロジック処理判定フラグ（全角半角） */
    private String sendCorrectLogicJudgeFlag;

    /** 訂正前_勧誘区分 */
    private String solicitTypeName;

    /** 訂正前_受注方法 */
    private String receiveOrderTypeName;

    /** 訂正前_目論見書の交付方法 */
    private String prospectusIssueMethodWord;

    /** 訂正前_重要事項の説明 */
    private String importantMatterType2;

    /** 訂正前_備考 */
    private String bbRemark2;

    /** 訂正前_数量 */
    private String domesticQuantityInput;

    /** 訂正前_約定金額 */
    private String subscriptTradeAmount;

    /** 訂正前_預り区分 */
    private String depositType2;

}
