package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 信用新規注文訂正入力　A001リクエスト
 *
 * @author SCSK
 *
 */
@Data
public class IfaMarginNewOrderCorrectInputA001DtoRequest {
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
}
