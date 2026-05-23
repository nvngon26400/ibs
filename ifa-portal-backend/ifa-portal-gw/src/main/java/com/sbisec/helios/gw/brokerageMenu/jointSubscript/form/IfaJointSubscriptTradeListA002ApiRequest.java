package com.sbisec.helios.gw.brokerageMenu.jointSubscript.form;

import java.util.List;

import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTradeHistoryApiRequestSelected;

import lombok.Data;

@Data
public class IfaJointSubscriptTradeListA002ApiRequest {

    /** 仲介業者コード. */
    private String brokerCode;

    /** 仲介業者除外. */
    private String chkBrokerCodeExclude;

    /** 共募支店コード. */
    private String jointBranchCode;

    /** 営業員コード. */
    private String empCode;

    /** 部店コード. */
    private String butenCode;

    /** 口座番号. */
    private String accountNumber;

    /** 顧客名. */
    private String customerName;

    /** 顧客名検索オプション. */
    private String customerNameSearchType;

    /** 取引コース. */
    private List<IfaTradeHistoryApiRequestSelected> course;

    /** 期間指定From. */
    private String periodDateFrom;

    /** 期間指定To. */
    private String periodDateTo;

    /** 証券種別. */
    private List<IfaJointSubscriptTradeListApiRequestSelected> securityClass;

    /** 銘柄コード. */
    private String brandCode12;

}
