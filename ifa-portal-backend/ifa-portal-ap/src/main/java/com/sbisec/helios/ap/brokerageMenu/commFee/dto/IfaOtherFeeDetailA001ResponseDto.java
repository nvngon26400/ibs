package com.sbisec.helios.ap.brokerageMenu.commFee.dto;

import java.util.List;
import lombok.Data;

@Data
public class IfaOtherFeeDetailA001ResponseDto {

	/** その他報酬詳細リスト. */
	private List<IfaOtherFeeDetailA001ResponseDtoOtherFeeDetailList> otherFeeDetailList;

}
