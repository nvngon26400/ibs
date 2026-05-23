package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;
import lombok.Data;

@Data
public class IfaSecurityCashBalanceLookupA002DtoRequest {

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
	private List<IfaSecurityCashBalanceLookupSelectedDtoRequest> course;

	/** 閲覧可能部店（半角英数字）. */
	private String butenCodeArray;

	/** 期間指定. */
	private String periodYm;

	/** 証券　金銭. */
	private List<IfaSecurityCashBalanceLookupSelectedDtoRequest> securityTypeSecuritiesMoney;

	/** 信用・先OP. */
	private List<IfaSecurityCashBalanceLookupSelectedDtoRequest> securityTypeCreditFuturesOp;

	/** 銘柄コード（半角英数字）. */
	private String brandCode;

    /** 仲介業者コードリスト. */
    private List<String> brokerCodeList;

    /** 閲覧可能部店リスト. */
    private List<String> visibleButenCodeList;
}
