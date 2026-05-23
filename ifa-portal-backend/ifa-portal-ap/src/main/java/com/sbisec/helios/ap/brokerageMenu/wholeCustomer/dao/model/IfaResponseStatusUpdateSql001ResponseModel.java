package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

@Data
public class IfaResponseStatusUpdateSql001ResponseModel {

	/** 下落率区分（数字）. */
	private String declineRateKbn;

	/** 部店コード（半角英数字）. */
	private String butenCode;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** 投信協会コード（半角英数字）. */
	private String toushinKyoukaiCode;

	/** 基準日（To）. */
	private String baseDateTo;

	/** 投信銘柄名（全角半角）. */
	private String toushinBrandName;

	/** 基準価額（To）. */
	private String basePriceTo;

	/** 前日比（数値(小数)）. */
	private String zenjitsuHi;

	/** 下落率（数値(小数)）. */
	private String declineRate;

	/** 顧客名_姓名(漢字). */
	private String nameKanji;

	/** ステータス区分. */
	private String statusKbn;

	/** 対応方法区分. */
	private String methodsKbn;

	/** その他内容区分. */
	private String otherContentsKbn;

	/** その他詳細. */
	private String otherDetails;

	/** 顧客対応日. */
	private String customerSupportDate;

}
