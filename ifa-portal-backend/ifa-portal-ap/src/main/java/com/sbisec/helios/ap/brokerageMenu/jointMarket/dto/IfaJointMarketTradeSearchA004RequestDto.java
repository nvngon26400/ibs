package com.sbisec.helios.ap.brokerageMenu.jointMarket.dto;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryDtoRequestSelected;

import lombok.Data;

/**
 * 画面ID：SUB0208_01-01
 * 画面名：共同店舗取引検索
 * 
 * @author lianhua.xia
 * 2024/12/06 新規作成
 */

@Data
public class IfaJointMarketTradeSearchA004RequestDto {

    /** 仲介業者コード（数字）. */
    private String brokerCode;

    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;

    /** 支店コード（数字）. */
    private String branchCode;

    /** 営業員コード（半角英数字）. */
    private String empCode;

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 顧客名（全角半角）. */
    private String customerName;

    /** 顧客名検索オプション. */
    private String customerNameSearchType;

    /** 取引コース（全角半角）. */
    private List<IfaTradeHistoryDtoRequestSelected> course;

    /** 期間指定From. */
    private String periodDateFrom;

    /** 期間指定To. */
    private String periodDateTo;

    /** 証券種別. */
    private List<IfaJointMarketTradeSearchDtoRequestSelected> securityClass;

    /** 銘柄コード（半角英数字）. */
    private String brandCode12;
}
