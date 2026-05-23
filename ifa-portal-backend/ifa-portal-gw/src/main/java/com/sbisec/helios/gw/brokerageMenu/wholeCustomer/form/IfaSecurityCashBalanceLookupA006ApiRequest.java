package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class IfaSecurityCashBalanceLookupA006ApiRequest {

	/** 仲介業者コード（数字）. */
	@NotEmpty(message = "仲介業者コード")
	@Pattern(regexp="0-9", message = "仲介業者コード")
	@Size(max = 4, message = "仲介業者コード")
	private String brokerCode;

	/** 仲介業者除外（半角英数字）. */
	@NotEmpty(message = "仲介業者除外")
	@Size(min = 1, max = 1, message = "仲介業者除外")
	private String chkBrokerCodeExclude;

	/** 支店コード（数字）. */
	@NotEmpty(message = "支店コード")
	@Pattern(regexp="0-9", message = "支店コード")
	@Size(max = 3, message = "支店コード")
	private String branchCode;

	/** 営業員コード（半角英数字）. */
	@NotEmpty(message = "営業員コード")
	@Size(min = 4, max = 4, message = "営業員コード")
	private String empCode;

	/** 部店コード（半角英数字）. */
	@NotEmpty(message = "部店コード")
	@Size(min = 3, max = 3, message = "部店コード")
	private String butenCode;

	/** 口座番号（数字）. */
	@NotEmpty(message = "口座番号")
	@Pattern(regexp="0-9", message = "口座番号")
	@Size(max = 7, message = "口座番号")
	private String accountNumber;

	/** 顧客名（全角半角）. */
	@NotEmpty(message = "顧客名")
	@Size(max = 100, message = "顧客名")
	private String customerName;

	/** 顧客名検索オプション. */
	@NotEmpty(message = "顧客名検索オプション")
	private String customerNameSearchType;

	/** 取引コース（全角半角）. */
	@NotEmpty(message = "取引コース")
	@Size(max = 40, message = "取引コース")
	private List<IfaSecurityCashBalanceLookupSelectedApiRequest> course;

	/** 閲覧可能部店（半角英数字）. */
	@NotEmpty(message = "閲覧可能部店")
	@Size(min = 3, max = 3, message = "閲覧可能部店")
	private String butenCodeArray;

	/** 期間指定. */
	@DateTimeFormat(pattern="yy/MM")
	@JsonFormat(pattern="yy/MM")
	@NotEmpty(message = "期間指定")
	@Size(min = 7, max = 7, message = "期間指定")
	private String periodYm;

	/** 証券　金銭. */
	@NotEmpty(message = "証券　金銭")
	private List<IfaSecurityCashBalanceLookupSelectedApiRequest> securityTypeSecuritiesMoney;

	/** 信用・先OP. */
	@NotEmpty(message = "信用・先OP")
	private List<IfaSecurityCashBalanceLookupSelectedApiRequest> securityTypeCreditFuturesOp;

	/** 銘柄コード（半角英数字）. */
	@NotEmpty(message = "銘柄コード")
	@Size(max = 5, message = "銘柄コード")
	private String brandCode;
}
