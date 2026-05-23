package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;


import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;

/**
*
*
* @author BASE李
*
*/
@Data
public class IfaForeignMarginCollateralDeficientAlertListSql001RequestModel {

    /** ユーザ共通情報.権限コード. */
    private String privId;

    /** FCT030.仲介業者営業員リスト. */
    private List<BrokerCharge> brokerChargeList;

    /** リクエスト.仲介業者コード. */
    private String brokerCode;

    /** リクエスト.仲介業者除外. */
    private String chkBrokerCodeExclude;

    /** リクエスト.支店コード. */
    private String branchCode;

    /** リクエスト.営業員コード. */
    private String empCode;

    /** リクエスト.部店コード. */
    private String butenCode;

    /** リクエスト.口座番号. */
    private String accountNumber;

    /** リクエスト.顧客名（漢字／カナ）. */
    private String customerNameKanjiKana;

    /** リクエスト.顧客名（漢字／カナ）_条件. */
    private String customerNameKanjiKanaTerms;

    /** リクエスト.取引コース. */
    private String course;

}
