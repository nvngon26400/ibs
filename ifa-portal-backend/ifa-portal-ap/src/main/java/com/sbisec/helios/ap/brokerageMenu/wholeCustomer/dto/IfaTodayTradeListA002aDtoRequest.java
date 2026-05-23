package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaTodayTradeListA002aDtoRequest {

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
	private String customerNameKanjiKanaTerms;

	/** 取引コース（全角半角）. */
	private String course;

	/** 銘柄コード（半角英数字）. */
	private String brandCode;

}
