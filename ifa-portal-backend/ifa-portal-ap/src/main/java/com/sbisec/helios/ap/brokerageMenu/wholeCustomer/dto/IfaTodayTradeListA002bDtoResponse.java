package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaTodayTradeListA002bDtoResponse {

	/** 当日約定リスト. */
	private List<IfaTodayTradeListA002bTodayTradeDtoResponse> todayTradeList;

}
