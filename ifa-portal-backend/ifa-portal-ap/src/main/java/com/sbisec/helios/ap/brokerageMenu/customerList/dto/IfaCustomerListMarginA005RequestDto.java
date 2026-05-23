package com.sbisec.helios.ap.brokerageMenu.customerList.dto;

import lombok.Data;

@Data
public class IfaCustomerListMarginA005RequestDto {
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;

    /** 支店コード（数字）. */
    private String branchCode;

    /** 営業員コード（半角英数字）. */
    private String empCode;

    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 顧客名(漢字/カナ)（全角半角）. */
    private String customerNameKanjiKana;

    /** 顧客名(漢字/カナ)　（条件リスト）. */
    private String customerNameKanjiKanaConditionList;

    /** 取引コース（全角半角）. */
    private String course;

    /** 前日評価損益（From）（数値(整数)）. */
    private String beforeProfitAndLossFrom;

    /** 前日評価損益（To）（数値(整数)）. */
    private String beforeProfitAndLossTo;

    /** 前日保証金残高（From）（数値(整数)）. */
    private String beforeDepositBalanceFrom;

    /** 前日保証金残高（To）（数値(整数)）. */
    private String beforeDepositBalanceTo;

    /** 前日委託保証金率（From）（数値(小数)）. */
    private String beforeEntrustDepositRateFrom;

    /** 前日委託保証金率（To）（数値(小数)）. */
    private String beforeEntrustDepositRateTo;

    /** 追証（チェック）（半角英数字）. */
    private String marginCallCheck;

    /** 引出金不足（チェック）（半角英数字）. */
    private String withdrawalDeficientCheck;
    
    private String pattern;
    
}
