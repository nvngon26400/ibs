package com.sbisec.helios.ap.bizcommon.dao.model;

import lombok.Data;

@Data
public class Fct006Sql001ResponseModel {

	private String butenCode; // 部店コード
	private String accountNumber; // 口座番号
	private String corporationKbn; // 法人区分
	private String tcCompRank; // コンプラランク
	private String uaiQaFundCharacter; // 資金性格区分

}
