package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

/**
 * 為替取引履歴　取引コース

 * @author SCSK川崎
 */
@Data
public class IfaFxTradeHistoryDtoRequestCourseSelected {
    
    private String id;
    
    private Boolean isSelected;
    
}
