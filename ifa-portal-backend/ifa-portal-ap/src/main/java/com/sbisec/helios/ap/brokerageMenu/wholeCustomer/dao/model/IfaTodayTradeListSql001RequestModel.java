package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import java.util.List;

import lombok.Data;

@Data
public class IfaTodayTradeListSql001RequestModel {

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 営業員コード（半角英数字）. */
	private String empCode;

	/** 部店コード（半角英数字）. */
	private String butenCode;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** 顧客名（漢字／カナ）（全角半角）. */
	private String customerNameKanjiKana;

	/** 顧客名（漢字／カナ）_条件. */
	private String customerNameSearchType;

	/** 取引コース（全角半角）. */
	private String course;

	/** FCT030.仲介業者営業員リスト.仲介業者コード. */
	private String fct030BrokerCode;

	/** FCT030.仲介業者営業員リスト.営業員コード. */
	private String fct030EmpCode;
	
	/** FCT030.仲介業者営業員リスト */
	private List fct030Code;
	
	/** 権限コード */
	private String privId;

}
