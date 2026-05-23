package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;

@Data
public class IfaDeliverInOutDetailSql001RequestModel {

	/** 仲介業者コード（数字）. */
    private List<String> brokerCodeList;

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

	/** 顧客名(漢字/カナ)（全角半角）. */
	private String customerNameKanjiKana;

	/** 顧客名(漢字/カナ)_条件. */
	private String customerNameKanjiKanaTerms;

	/** 取引コース（全角半角）. */
	private List<String> course;

	/** 期間指定（From). */
	private String periodYmFrom;

	/** 期間指定（To). */
	private String periodYmTo;

	/** FCT030.仲介業者営業員リスト */
	private List<BrokerCharge> brokerChargeList;

	/** ユーザ共通情報.権限コード */
	private String privId;
	
    /** 最大取得件数 */
    private int querySizeLimit;
}
