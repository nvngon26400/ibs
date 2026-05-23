package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

/**
 * 取引履歴　コース
 *
 * @author SCSK
 *
 */
@Data
public class IfaTradeHistoryDtoRequestSelected {

    private String id;
    
    private Boolean isSelected;
}
