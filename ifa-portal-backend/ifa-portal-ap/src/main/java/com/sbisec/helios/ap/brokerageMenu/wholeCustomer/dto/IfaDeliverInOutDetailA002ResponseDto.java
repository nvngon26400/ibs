package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;
import lombok.Data;

@Data
public class IfaDeliverInOutDetailA002ResponseDto {

	/** メッセージ. */
	private String message;

	/** 取得件数. */
	private String acquireCount;

	/** データ件数. */
	private String dataCount;

	/** 入出庫明細. */
	private List<IfaDeliverInOutDetailDeliverInOutDetail> deliverInOutDetail;
	
}
