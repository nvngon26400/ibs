package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class IfaDepositBalanceDetailA004ApiRequest {

	/** 商品名. */
	@NotEmpty(message = "商品名")
	private String productName;

	/** 銘柄コード（半角英数字）. */
	@NotEmpty(message = "銘柄コード")
	@Size(max = 5, message = "銘柄コード")
	private String brandCode;

	/** 銘柄名（全角半角）. */
	@NotEmpty(message = "銘柄名")
	@Size(max = 40, message = "銘柄名")
	private String brandName;

	/** 商品区分（全角半角）. */
	@NotEmpty(message = "商品区分")
	@Size(max = 20, message = "商品区分")
	private String securityType;

	/** 国内外国区分（全角半角）. */
	@NotEmpty(message = "国内外国区分")
	@Size(min = 1, max = 1, message = "国内外国区分")
	private String kokunaiGaiKbn;

	/** 商品種別１（全角半角）. */
	@NotEmpty(message = "商品種別１")
	@Size(min = 1, max = 1, message = "商品種別１")
	private String SecurityClass1;

	/** 商品種別2（全角半角）. */
	@NotEmpty(message = "商品種別2")
	@Size(min = 1, max = 1, message = "商品種別2")
	private String SecurityClass2;

	/** 会社ｺｰﾄﾞ（数字）. */
	@NotEmpty(message = "会社ｺｰﾄﾞ")
	@Pattern(regexp="0-9", message = "会社ｺｰﾄﾞ")
	@Size(min = 5, max = 5, message = "会社ｺｰﾄﾞ")
	private String companyCode;

	/** 権利区分（全角半角）. */
	@NotEmpty(message = "権利区分")
	@Size(min = 1, max = 1, message = "権利区分")
	private String rightType;

	/** 新旧区分（全角半角）. */
	@NotEmpty(message = "新旧区分")
	@Size(min = 1, max = 1, message = "新旧区分")
	private String newOldType;

	/** 回数（数値(整数)）. */
	@NotEmpty(message = "回数")
	@Size(max = 2, message = "回数")
	private String times;

	/** 号1（全角半角）. */
	@NotEmpty(message = "号1")
	@Size(max = 10, message = "号1")
	private String issue1;

	/** 号2（全角半角）. */
	@NotEmpty(message = "号2")
	@Size(max = 10, message = "号2")
	private String issue2;

	/** 上場国ｺｰﾄﾞ（全角半角）. */
	@NotEmpty(message = "上場国ｺｰﾄﾞ")
	@Size(min = 3, max = 3, message = "上場国ｺｰﾄﾞ")
	private String listedCountryCode;

	/** 非特定預り区分. */
	@NotEmpty(message = "非特定預り区分")
	private String nonSpecificDepositCategory;

	/** 取得口座区分. */
	@NotEmpty(message = "取得口座区分")
	private String getAccountCategory;

	/** 商品コード（全角半角）. */
	@NotEmpty(message = "商品コード")
	@Size(max = 12, message = "商品コード")
	private String SecurityClass;

	/** 国コード（全角半角）. */
	@NotEmpty(message = "国コード")
	@Size(min = 2, max = 2, message = "国コード")
	private String countryCode;

	/** 通貨コード（全角半角）. */
	@NotEmpty(message = "通貨コード")
	@Size(max = 3, message = "通貨コード")
	private String currencyCode;

	/** 預り区分（全角半角）. */
	@NotEmpty(message = "預り区分")
	@Size(max = 20, message = "預り区分")
	private String depositType;

}
