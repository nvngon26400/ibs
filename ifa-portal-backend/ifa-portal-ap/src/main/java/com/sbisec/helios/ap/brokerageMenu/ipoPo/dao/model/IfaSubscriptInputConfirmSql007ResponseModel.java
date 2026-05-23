package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

@Data
public class IfaSubscriptInputConfirmSql007ResponseModel {

	/** 特定口座区分（半角英数字）. */
	private String tokuteiKouzaKbn;

	/** ISA契約区分. */
	private String isaContractType;

	/** ISA買付可能判定区分（当年）（半角英数字）. */
	private String isaBuyAbleJudgeClassificationYear;

	/** 顧客コード */
    private String customerId;

}
