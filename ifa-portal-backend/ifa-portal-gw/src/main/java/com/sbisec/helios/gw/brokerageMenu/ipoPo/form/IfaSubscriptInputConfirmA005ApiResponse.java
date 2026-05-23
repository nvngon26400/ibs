package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import lombok.Data;

@Data
public class IfaSubscriptInputConfirmA005ApiResponse {

    /** 部店コード. */
    private String butenCode;

    /** 口座番号. */
    private String accountNumber;

    /** 送信・訂正用ロジック処理判定フラグ. */
    private String sendCorrectLogicJudgeFlag;

    /** 上場日. */
    private String presentationDate;

    /** 募集期間（To）. */
    private String bbPeriodTo;

    /** 数量. */
    private String quantity;

    /** 売買単位. */
    private String unit;

    /** 売買単位区分. */
    private String sellBuyUnitType;

    /** 当選株数. */
    private String bbQuantityAlloc;

    /** 配分上限株数. */
    private String maxAllocation;

    /** 注意情報アラート確認. */
    private String noteInfoCheckbox;

    /** お知らせアラート確認. */
    private String noteLimitCheck;

    /** 顧客コード. */
    private String customerCode;

    /** 預り区分. */
    private String depositType;

    /** 預り区分アラート確認. */
    private String depositTypeConfirm;

    /** コンプラランクチェック確認. */
    private String invitationCheck;

    /** コンプラランクチェック.チェックボックス文言. */
    private String complianceCheckMsg;

    /** 約定金額. */
    private String contractAmount;

    /** 備考. */
    private String bbRemark;

    /** 訂正前_備考. */
    private String bbRemark2;

    /** 電子交付同意. */
    private String edelivAgreementDate;

    /** 目論見書閲覧. */
    private String readTime;

    /** 銘柄コード. */
    private String brandCode;

    /** ブックビルディング申込期間（開始）. */
    private String bookBuildingPresentationFrom;

    /** 発行価格. */
    private String issueBbPrice;

    /** 勧誘区分. */
    private String kanyuKbn;

    /** 受注方法. */
    private String jutyuKbn;

    /** 目論見書の交付方法. */
    private String mokuromiKoufuKbn;

    /** 重要事項の説明. */
    private String importantMatterType;

    /** 明細番号. */
    private String detailNumber;

    /** 更新時間（注文排他用）. */
    private String updateTimeForOrderExclusivity;

    /** 抽選結果. */
    private String lotteryResult;

    /** 注文状況. */
    private String orderStatus;

    /** 注文フラグ. */
    private String orderFlag;

}
