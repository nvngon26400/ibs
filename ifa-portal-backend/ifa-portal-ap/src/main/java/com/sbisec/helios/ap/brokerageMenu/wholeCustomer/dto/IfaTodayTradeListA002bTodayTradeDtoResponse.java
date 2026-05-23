package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

@Data
public class IfaTodayTradeListA002bTodayTradeDtoResponse {

	/** 商品. */
	private String commodity;

	/** 部店. */
	private String buten;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** コース. */
	private String course;

	/** 顧客名(漢字). */
	private String customerNameKanji;

	/** 顧客名(カナ). */
	private String customerNameKana;

	/** 銘柄名（全角半角）. */
	private String brandName;

	/** 銘柄コード（半角英数字）. */
	private String brandCode;

	/** 取引（全角半角）. */
	private String openTradeKbn;

	/** 預り区分（全角半角）. */
	private String depositType;

	/** 約定日. */
	private String tradeDate;

	/** 受渡日. */
	private String settlementDate;

	/** 約定株数（数値(整数)）. */
	private String contractStock;

	/** 平均約定単価（数値(小数)）. */
	private String averageTradePrice;

	/** 営業員コード（半角英数字）. */
	private String empCode;

	/** 営業員名（全角半角）. */
	private String brokerChargeName;

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 仲介業者名（全角半角）. */
	private String brokerName;

	/** 支店コード（数字）. */
	private String branchCode;

	/** 支店名（全角半角）. */
	private String branchName;

}
