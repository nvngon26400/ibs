package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;

import lombok.Data;

@Data
public class IfaSubscriptInputConfirmA006RequestDto {

    /** 部店コード. */
    private String butenCode;

    /** 口座番号. */
    private String accountNumber;

    /** 送信・訂正用ロジック処理判定フラグ. */
    private String sendCorrectLogicJudgeFlag;

    /** 銘柄コード(上場日). */
    private String presentationDate;

    /** 勧誘区分. */
    private String kanyuKbn;

    /** 受注方法. */
    private String jutyuKbn;

    /** コンプラランクチェック確認. */
    private String invitationCheck;

    /** コンプラランクチェック.チェックボックス文言. */
    private String complianceCheckMsg;

    /** 備考. */
    private String bbRemark;

    /** 訂正前_備考. */
    private String bbRemark2;

    /** 募集期間（To）. */
    private String bbPeriodTo;

    /** 顧客コード. */
    private String customerCode;

    /** 目論見書の交付方法. */
    private String mokuromiKoufuKbn;

    /** 重要事項の説明. */
    private String importantMatterType;

    /** 銘柄コード. */
    private String brandCode;

    /** ブックビルディング申込期間（開始）. */
    private String bookBuildingPresentationFrom;

    /** 明細番号. */
    private String detailNumber;

    /** 更新時間（注文排他用）. */
    private String updateTimeForOrderExclusivity;

    /** 抽選結果. */
    private String lotteryResult;

    /** 当選株数. */
    private String bbQuantityAlloc;

    /** 売買単位区分. */
    private String sellBuyUnitType;

    /** 注文状況. */
    private String orderStatus;

    /** 数量. */
    private String quantity;

    /** 約定金額. */
    private String contractAmount;

    /** 預り区分. */
    private String depositType;

    /** 訂正前_勧誘区分. */
    private String solicitTypeName;

    /** 訂正前_受注方法. */
    private String receiveOrderTypeName;

    /** 訂正前_目論見書の交付方法. */
    private String prospectusIssueMethodWord;

    /** 訂正前_重要事項の説明. */
    private String importantMatterType2;

    /** 注文フラグ. */
    private String orderFlag;

}
