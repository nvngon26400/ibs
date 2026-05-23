package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;

import lombok.Data;

/**
 * 取引履歴　
 *
 * @author SCSK
 *
 */
@Data
public class IfaTradeHistoryA002RequestDto {

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
    
    /** 閲覧可能部店（半角英数字）. */
    private String butenCodeArray;

    /** 期間指定From. */
    private String periodDateFrom;
    
    /** 期間指定To. */
    private String periodDateTo;

    /** 証券種別. */
    private List<IfaTradeHistoryDtoRequestSelected> securityClass;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode12;

}
