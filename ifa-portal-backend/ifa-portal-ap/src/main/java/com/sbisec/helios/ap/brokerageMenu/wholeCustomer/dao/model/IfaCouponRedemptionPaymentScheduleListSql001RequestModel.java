package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import java.util.List;
import lombok.Data;

@Data
public class IfaCouponRedemptionPaymentScheduleListSql001RequestModel {

    /** 部店コード. */
    private String butenCode;

    /** 口座番号. */
    private String accountNumber;

    /** 期間指定From. */
    private String periodYmFrom;

    /** 期間指定To. */
    private String periodYmTo;

    /** 証券種別. */
    private List<String> securiytClassList;

    /** 銘柄コード. */
    private String brandCode;

    /** 権限ID. */
    private String privId;

    /** 仲介業者コードリスト. */
    private List<String> brokerCodeList;

    /** 仲介業者除外. */
    private String chkBrokerCodeExclude;

    /** 営業員コード. */
    private String empCode;

    /** 営業員コードリスト. */
    private List<IfaCouponRedemptionPaymentScheduleListSql001RequestModelBrokerCharge> brokerChargeList;

    /** 支店コード */
    private String branchCode;

    /** 顧客名（漢字／カナ）. */
    private String customerNameKanjiKana;

    /** 顧客名（漢字／カナ）_条件. */
    private String customerNameKanjiKanaTerms;

    /** 取引コース. */
    private List<IfaCouponRedemptionPaymentScheduleListSql001RequestModelCourseSelected> course;

    /** 件数. */
    private String rownum;

}
