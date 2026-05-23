package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyListSql003ResponseModel {

    /** 募集期間FROM. */
    private String periodFrom;

    /** 募集期間TO. */
    private String periodTo;

    /** 入金予定日. */
    private String paymentDate;

    /** 上場日. */
    private String presentationDate;

    /** 発行価格（数値(小数)）. */
    private String issueBbPrice;

    /** 電子交付のみフラグ. */
    private String edelivOnlyFlag;

    /** 発行価格区分. */
    private String issuePriceType;

    /** 抽選結果. */
    private String lotteryResult;

    /** 数量（数値(整数)）. */
    private String quantity;

    /** 電子交付承諾日付. */
    private String edelivAgreementDate;

    /** 閲覧日時. */
    private String readTime;

}
