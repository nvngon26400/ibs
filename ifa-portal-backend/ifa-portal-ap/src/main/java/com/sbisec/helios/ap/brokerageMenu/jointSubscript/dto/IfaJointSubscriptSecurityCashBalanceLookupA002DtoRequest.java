package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaJointSubscriptSecurityCashBalanceLookupA002DtoRequest {

    /** 仲介業者コード（数字）. */
    private String brokerCode;

    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;

    /** 共募支店コード（数字）. */
    private String jointBranchCode;
    
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
    private List<IfaJointSubscriptSecurityCashBalanceLookupSelectedDtoRequest> course;

    /** 閲覧可能部店（半角英数字）. */
    private String butenCodeArray;

    /** 期間指定. */
    private String periodYm;
    
    /** 集計単位(集計/明細) */
    private String aggregatorDetailsCountingUnit;

    /** 証券　金銭. */
    private List<IfaJointSubscriptSecurityCashBalanceLookupSelectedDtoRequest> securityTypeSecuritiesMoney;

    /** 信用・先OP. */
    private List<IfaJointSubscriptSecurityCashBalanceLookupSelectedDtoRequest> securityTypeCreditFuturesOp;

    /** 銘柄コード（半角英数字）. */
    private String brandCode;

    /** 仲介業者コードリスト. */
    private List<String> brokerCodeList;

    /** 閲覧可能部店リスト. */
    private List<String> visibleButenCodeList;
}
