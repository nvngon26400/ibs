package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import lombok.Data;

@Data
public class IfaTodayTradeListA005TodayTradeApiResponse {
    
    /** 当日約定リスト.部店. */
    private String buten;

    /** 当日約定リスト.口座番号. */
    private String accountNumber;

    /** 当日約定リスト.取引コース. */
    private String course;

    /** 当日約定リスト.顧客名(漢字). */
    private String customerNameKanji;

    /** 当日約定リスト.顧客名(カナ). */
    private String customerNameKana;

    /** 当日約定リスト.銘柄名. */
    private String brandName;

    /** 当日約定リスト.銘柄コード. */
    private String brandCode;

    /** 当日約定リスト.取引. */
    private String openTradeKbn;

    /** 当日約定リスト.預り区分. */
    private String depositType;

    /** 当日約定リスト.約定日. */
    private String tradeDate;

    /** 当日約定リスト.受渡日. */
    private String settlementDate;

    /** 当日約定リスト.約定株数. */
    private String contractStock;

    /** 当日約定リスト.平均約定単価. */
    private String averageTradePrice;

    /** 当日約定リスト.営業員コード. */
    private String empCode;

    /** 当日約定リスト.営業員名. */
    private String brokerChargeName;

    /** 当日約定リスト.仲介業者コード. */
    private String brokerCode;

    /** 当日約定リスト.仲介業者名. */
    private String brokerName;

    /** 当日約定リスト.支店コード. */
    private String branchCode;

    /** 当日約定リスト.支店名. */
    private String branchName;
}
