package com.sbisec.helios.gw.brokerageMenu.customerList.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaCustomerListMarginA002ApiRequest {

	/** 仲介業者コード（数字）. */
	@NotEmpty(message = "仲介業者コード")
	@Pattern(regexp="0-9", message = "仲介業者コード")
	@Size(max = 4, message = "仲介業者コード")
	private String brokerCode;

	/** 支店コード（数字）. */
	@NotEmpty(message = "支店コード")
	@Pattern(regexp="0-9", message = "支店コード")
	@Size(max = 3, message = "支店コード")
	private String branchCode;

	/** 営業員コード（半角英数字）. */
	@NotEmpty(message = "営業員コード")
	@Size(min = 4, max = 4, message = "営業員コード")
	private String empCode;

	/** 仲介業者除外（半角英数字）. */
	@NotEmpty(message = "仲介業者除外")
	@Size(min = 1, max = 1, message = "仲介業者除外")
	private String chkBrokerCodeExclude;

	/** 部店コード（半角英数字）. */
	@NotEmpty(message = "部店コード")
	@Size(min = 3, max = 3, message = "部店コード")
	private String butenCode;

	/** 口座番号（数字）. */
	@NotEmpty(message = "口座番号")
	@Pattern(regexp="0-9", message = "口座番号")
	@Size(max = 7, message = "口座番号")
	private String accountNumber;

	/** 顧客名(漢字/カナ)（全角半角）. */
	@NotEmpty(message = "顧客名(漢字/カナ)")
	@Size(max = 72, message = "顧客名(漢字/カナ)")
	private String customerNameKanjiKana;

	/** 顧客名(漢字/カナ)　（条件リスト）. */
	@NotEmpty(message = "顧客名(漢字/カナ)　（条件リスト）")
	private String customerNameKanjiKanaConditionList;

	/** 取引コース（全角半角）. */
	@NotEmpty(message = "取引コース")
	@Size(max = 40, message = "取引コース")
	private String course;

	/** 前日評価損益（From）（数値(整数)）. */
	@Digits(integer = 19, fraction = 0, message = "前日評価損益（From）")
	@NotEmpty(message = "前日評価損益（From）")
	@Size(max = 25, message = "前日評価損益（From）")
	private String beforeProfitAndLossFrom;

	/** 前日評価損益（To）（数値(整数)）. */
	@Digits(integer = 19, fraction = 0, message = "前日評価損益（To）")
	@NotEmpty(message = "前日評価損益（To）")
	@Size(max = 25, message = "前日評価損益（To）")
	private String beforeProfitAndLossTo;

	/** 前日保証金残高（From）（数値(整数)）. */
	@Digits(integer = 17, fraction = 0, message = "前日保証金残高（From）")
	@NotEmpty(message = "前日保証金残高（From）")
	@Size(max = 22, message = "前日保証金残高（From）")
	private String beforeDepositBalanceFrom;

	/** 前日保証金残高（To）（数値(整数)）. */
	@Digits(integer = 17, fraction = 0, message = "前日保証金残高（To）")
	@NotEmpty(message = "前日保証金残高（To）")
	@Size(max = 22, message = "前日保証金残高（To）")
	private String beforeDepositBalanceTo;

	/** 前日委託保証金率（From）（数値(小数)）. */
	@Digits(integer = 6, fraction = 2, message = "前日委託保証金率（From）")
	@NotEmpty(message = "前日委託保証金率（From）")
	@Size(max = 10, message = "前日委託保証金率（From）")
	private String beforeEntrustDepositRateFrom;

	/** 前日委託保証金率（To）（数値(小数)）. */
	@Digits(integer = 6, fraction = 2, message = "前日委託保証金率（To）")
	@NotEmpty(message = "前日委託保証金率（To）")
	@Size(max = 10, message = "前日委託保証金率（To）")
	private String beforeEntrustDepositRateTo;

	/** 追証（チェック）（半角英数字）. */
	@NotEmpty(message = "追証（チェック）")
	@Size(min = 1, max = 1, message = "追証（チェック）")
	private String marginCallCheck;

	/** 引出金不足（チェック）（半角英数字）. */
	@NotEmpty(message = "引出金不足（チェック）")
	@Size(min = 1, max = 1, message = "引出金不足（チェック）")
	private String withdrawalDeficientCheck;

}
