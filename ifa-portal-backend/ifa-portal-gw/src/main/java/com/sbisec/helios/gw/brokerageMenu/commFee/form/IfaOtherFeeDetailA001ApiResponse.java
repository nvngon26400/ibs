package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import java.util.List;
import lombok.Data;

@Data
public class IfaOtherFeeDetailA001ApiResponse {

	/** その他報酬詳細リスト. */
	private List<IfaOtherFeeDetailA001ApiResponseOtherFeeDetailList> otherFeeDetailList;

}
