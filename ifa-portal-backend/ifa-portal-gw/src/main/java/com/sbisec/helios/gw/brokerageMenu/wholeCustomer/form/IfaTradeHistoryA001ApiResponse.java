package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import lombok.Data;

@Data
public class IfaTradeHistoryA001ApiResponse {

	/** コメント（全角半角）. */
	private String comment;

    /** 現株ポイント参照可能件数 */
    private String count;
    
}
