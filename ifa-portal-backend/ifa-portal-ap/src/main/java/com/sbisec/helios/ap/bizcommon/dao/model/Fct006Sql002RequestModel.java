package com.sbisec.helios.ap.bizcommon.dao.model;

import lombok.Data;

@Data
public class Fct006Sql002RequestModel {
	private String brDomesticFgnInd; // 国内外国区分
	private String brBrandInd; // 商品区分
	private String brandCode1; // 銘柄コード１
	private String brandCode2; // 銘柄コード２
	private String countryCode; // SQL005.国籍コード
	private String requestCountryCode; // リクエスト.国籍コード
	private String productCode; // SQL006.銘柄コード
}
