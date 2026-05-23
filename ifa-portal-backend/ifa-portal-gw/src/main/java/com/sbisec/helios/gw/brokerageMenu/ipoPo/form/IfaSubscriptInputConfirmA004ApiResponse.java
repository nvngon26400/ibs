package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize
public class IfaSubscriptInputConfirmA004ApiResponse {

	/** 部店コード. */
    private String butenCode;

    /** 口座番号. */
    private String accountNumber;

    /** 送信・訂正用ロジック処理判定フラグ. */
    private String sendCorrectLogicJudgeFlag;

    /** 上場日. */
    private String presentationDate;

    /** 抽選結果. */
    private String lotteryResult;

    /** 当選株数. */
    private String bbQuantityAlloc;

    /** 売買単位. */
    private String unit;

    /** 売買単位区分. */
    private String sellBuyUnitType;

    /** 銘柄コード. */
    private String brandCode;

    /** ブックビルディング申込期間（開始）. */
    private String bookBuildingPresentationFrom;

    /** 注文状況. */
    private String orderStatus;

    /** 明細番号. */
    private String detailNumber;

    /** 更新時間（注文排他用）. */
    private String updateTimeForOrderExclusivity;

    /** 数量. */
    private String quantity;

    /** 約定金額. */
    private String contractAmount;

    /** 預り区分. */
    private String depositType;

    /** 勧誘区分. */
    private String kanyuKbn;

    /** 受注方法. */
    private String jutyuKbn;

    /** 目論見書の交付方法. */
    private String mokuromiKoufuKbn;

    /** 重要事項の説明. */
    private String importantMatterType;

    /** 訂正前_勧誘区分. */
    private String solicitTypeName;

    /** 訂正前_受注方法. */
    private String receiveOrderTypeName;

    /** 訂正前_目論見書の交付方法. */
    private String prospectusIssueMethodWord;

    /** 訂正前_重要事項の説明. */
    private String importantMatterType2;

    /** 訂正前_備考. */
    private String bbRemark2;

    /** 注文フラグ. */
    private String orderFlag;

}
