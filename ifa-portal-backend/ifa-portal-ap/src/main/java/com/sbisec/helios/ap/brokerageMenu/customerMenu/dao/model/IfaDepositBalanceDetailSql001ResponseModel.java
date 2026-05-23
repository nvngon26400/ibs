package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaDepositBalanceDetailSql001ResponseModel {

	/** 基準価額単位（数値(整数)）. */
	private String basePriceUnit;

    /** 協会コード（全角半角）. */
    private String kyoukaiCd;

}
