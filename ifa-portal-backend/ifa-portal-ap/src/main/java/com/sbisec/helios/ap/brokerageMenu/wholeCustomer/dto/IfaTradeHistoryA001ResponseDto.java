package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

/**
 * 取引履歴　
 *
 * @author SCSK
 *
 */
@Data
public class IfaTradeHistoryA001ResponseDto {

    /** コメント（全角半角）. */
    private String comment;
    
    /** 現株ポイント参照可能件数 */
    private String count;

}
