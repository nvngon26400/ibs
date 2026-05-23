package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupSelectedDtoRequest;

import lombok.Data;

@Data
public class IfaSecurityCashBalanceLookupSql001RequestModel {

    /** 部店コード */
    private String butenCode;
    
    /** 口座番号 */
    private String accountNumber;

    /** 期間指定. */
	private String periodYm;

    /** 銘柄コード. */
	private String brandCode;

    /** 支店コード（数字）. */
	private String branchCode;

    /** 権限コード */
    private String privId;
    
    /** 仲介業者除外（半角英数字）. */
	private String chkBrokerCodeExclude;

    /** 営業員コード（半角英数字）. */
	private String empCode;
    
    /** 顧客名（全角半角）. */
	private String customerName;

    /** 顧客名検索オプション. */
	private String customerNameSearchType;

    /** 仲介業者コードリスト. */
    private List<String> brokerCodeList;

    /** 仲介業者・営業員コードリスト */
    private List<IfaSecurityCashBalanceLookupSql001RequestModelBrokerCharge> brokerChargeList;

    /** 取引コースリスト */
    private List<IfaSecurityCashBalanceLookupSelectedDtoRequest> course;

    /** 証券　金銭リスト */
    private List<IfaSecurityCashBalanceLookupSelectedDtoRequest> securityTypeSecuritiesMoney;
    
    /** 証券　金銭リスト選択有無 */
    private boolean securityTypeSecuritiesMoneySelected;

    /** 信用・先OPリスト */
    private List<IfaSecurityCashBalanceLookupSelectedDtoRequest> securityTypeCreditFuturesOp;
    
    /** 信用・先OPリスト選択有無 */
    private boolean securityTypeCreditFuturesOpSelected;

    /** 閲覧可能部店リスト. */
    private List<String> visibleButenCodeList;

    /** 件数 */
    private String rownum;

}
