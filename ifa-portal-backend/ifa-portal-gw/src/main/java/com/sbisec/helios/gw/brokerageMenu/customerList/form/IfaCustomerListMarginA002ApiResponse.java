package com.sbisec.helios.gw.brokerageMenu.customerList.form;

import java.util.List;
import lombok.Data;

@Data
public class IfaCustomerListMarginA002ApiResponse {

	/** 顧客一覧リスト. */
	private List<IfaCustomerListMarginA002ApiResponseCustomerList> customerListList;

}
