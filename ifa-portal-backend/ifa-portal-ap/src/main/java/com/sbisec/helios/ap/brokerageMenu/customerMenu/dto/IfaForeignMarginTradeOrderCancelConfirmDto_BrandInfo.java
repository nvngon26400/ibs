package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaForeignMarginTradeOrderCancelConfirmDto_BrandInfo {

	/** 銘柄コード（半角英数字）. */
	private String brandCode;

	/** 銘柄名（全角半角）. */
	private String brandName;

}
