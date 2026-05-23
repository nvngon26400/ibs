package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

@Data
public class IfaTodayTradeListSql002ResponseModel {

	/** 証券コード. */
	private String secCode;

	/** ＩＳＩＮ. */
	private String isin;

	/** 銘柄略称. */
	private String brandShortName;

	/** 英語銘柄略称. */
	private String engBrandShortName;

	/** 銘柄名称. */
	private String brandName;

	/** 英語銘柄名称. */
	private String engBrandName;

	/** 読みカナ銘柄名称. */
	private String brandNameKana;

	/** 種類株区分. */
	private String stockKbn;

	/** 投資信託区分. */
	private String mutualFundKbn;

	/** 証券コード協議会業種コード. */
	private String secCodeCouncilCategoryCode;

	/** 主要取引所コード. */
	private String majorExchangeCode;

	/** SBI主要取引所コード. */
	private String sbiMajorExchangeCode;

	/** 単元株数. */
	private String unitStock;

}
