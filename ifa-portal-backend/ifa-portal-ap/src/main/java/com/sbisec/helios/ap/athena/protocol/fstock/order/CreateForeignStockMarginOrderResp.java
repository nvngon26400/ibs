package com.sbisec.helios.ap.athena.protocol.fstock.order;

import java.util.ArrayList;
import java.util.List;

import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrder;

import lombok.Data;

/**
 * 外国株式取引サービス - 外国株式信用新規建注文登録API
 * 
 * @author SCSK
 * @date: 02/11/2023
 */

@Data
public class CreateForeignStockMarginOrderResp {

	/**信用注文情報*/
	private MarginOrder marginOrder;
	
	/**注文ワーニングステータス*/
	List<String> warningStatuses = new ArrayList<String>();
}
