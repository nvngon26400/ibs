package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticTradeStatusListA001ResponseDto_ReceiptDeliveryTradeList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticTradeStatusListA001ResponseDto_SpotMarginTradeList;

import lombok.Data;

@Data
public class IfaDomesticTradeStatusListA001ApiResponse {
    
    /** 現物信用約定リスト. */
    private List<IfaDomesticTradeStatusListA001ResponseDto_SpotMarginTradeList> spotMarginTradeList;
    
    /** 現物信用約定取得件数（算出）. */
    private String spotMarginTradeCount;
    
    /** 現引現渡約定リスト. */
    private List<IfaDomesticTradeStatusListA001ResponseDto_ReceiptDeliveryTradeList> receiptDeliveryTradeList;
    
    /** 現引現渡約定取得件数（算出）. */
    private String receiptDeliveryTradeCount;
    
}
