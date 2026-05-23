package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class IfaTodayTradeListA005aDtoRequest {
    
    /** CSV出力データ. */
    private List<IfaTodayTradeListA005aTodayTradeDtoRequest> csvData = new ArrayList<IfaTodayTradeListA005aTodayTradeDtoRequest>();
}
