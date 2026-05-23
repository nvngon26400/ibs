package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaStockDetailInfoA002ApiRequest {
	/** 銘柄コード（半角英数字）. */
	private String brandCode;

	/** 市場. */
	private String market;
}
