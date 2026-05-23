package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import lombok.Data;

@Data
public class IfaSbiWrapManageFeeApiSbiWrapManageFeeInfoList {

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 仲介業者名（全角半角）. */
	private String brokerName;

	/** 営業員コード（半角英数字）. */
	private String empCode;

	/** 営業員名（全角半角）. */
	private String brokerChargeName;

	/** 部店. */
	private String buten;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** 扱者コード（半角英数字）. */
	private String dealerNumber;

	/** 顧客名（全角半角）. */
	private String customerName;

    /** 対象年月日（YYYY/MM/DD）. */
    private String targetDateYmd;

    /** 運用報酬徴収情報ID. */
    private String operationFeeCollectionInfoId;

    /** NAME. */
    private String name;

	/** 手数料（円貨）（数値(小数)）. */
	private String yenFee;

}
