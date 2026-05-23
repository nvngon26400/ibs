package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

@Data
public class IfaDeliverInOutDetailSql001ResponseModel {
    
    /** 総件数. */
    private int totalRow;

	/** 部店. */
	private String buten;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** 取引コース（全角半角）. */
	private String course;

	/** 顧客名(漢字). */
	private String customerNameKanji;

	/** 顧客名(カナ). */
	private String customerNameKana;

	/** 銘柄名（全角半角）. */
	private String brandName;

	/** 商品区分（全角半角）. */
	private String securityType;

	/** 入出庫区分. */
	private String deliverInOutClassification;

	/** 数量（数値(整数)）. */
	private String quantity;

	/** 入出庫日時価. */
	private String deliverInOutDateMarketValue;

	/** 合計金額. */
	private String totalAmount;

	/** 入出庫日. */
	private String checkInOutDay;

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
