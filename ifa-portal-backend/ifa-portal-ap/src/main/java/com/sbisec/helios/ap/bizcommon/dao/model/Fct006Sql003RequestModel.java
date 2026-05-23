package com.sbisec.helios.ap.bizcommon.dao.model;

import lombok.Data;

@Data
public class Fct006Sql003RequestModel {
	private String tcCompRank; // コンプラランク
	private String invitationType; // 勧誘区分
	private String orderMethod; // 受注方法
	private String complaCheckFundCharacter; // コンプラチェック用資金性格
	private String productRank; // 商品ランク
}
