package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

@Data
public class IfaTodayTradeListSql001ResponseModel {
    
    /** 総件数 */
    private String totalCount;

	/** 部店コード（半角英数字）. */
	private String butenCode;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** 顧客名_姓名(漢字). */
	private String customerNameKanji;

	/** 顧客名_姓名(カナ). */
	private String customerNameKana;

	/** 取引コース（全角半角）. */
	private String course;

	/** 契約締結前交付書面コード（全角半角）. */
	private String customerAttribute;

	/** 信用属性. */
	private String marginProfile;

	/** 仲介業者営業員コード（半角英数字）. */
	private String empCode;

	/** 仲介業者担当者名（全角半角）. */
	private String brokerChargeName;

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 仲介業者名（全角半角）. */
	private String brokerName;

	/** 仲介業支店コード. */
	private String branchCode;

	/** 仲介業者支店名. */
	private String branchName;

}
