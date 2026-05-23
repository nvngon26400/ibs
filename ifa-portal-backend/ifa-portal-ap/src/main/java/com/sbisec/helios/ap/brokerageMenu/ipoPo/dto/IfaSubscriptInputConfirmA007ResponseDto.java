package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;

import lombok.Data;

@Data
public class IfaSubscriptInputConfirmA007ResponseDto {

    /** 部店コード. */
    private String butenCode;

    /** 口座番号. */
    private String accountNumber;

    /** 送信・訂正用ロジック処理判定フラグ. */
    private String sendCorrectLogicJudgeFlag;

    /** 募集期間（To）. */
    private String bbPeriodTo;

    /** 銘柄コード. */
    private String brandCode;

    /** ブックビルディング申込期間（開始）. */
    private String bookBuildingPresentationFrom;

    /** 明細番号. */
    private String detailNumber;

    /** 更新時間（注文排他用）. */
    private String updateTimeForOrderExclusivity;

    /** 募集受注日時. */
    private String recruitmentOrderDate;

    /** 約定金額. */
    private String contractAmount;

    /** 預り区分. */
    private String depositType;

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

    /** 勧誘区分. */
    private String kanyuKbn;

    /** 受注方法. */
    private String jutyuKbn;

    /** 目論見書の交付方法. */
    private String mokuromiKoufuKbn;

    /** 重要事項の説明. */
    private String importantMatterType;

    /** 注文フラグ. */
    private String orderFlag;

}
