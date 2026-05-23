package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 信用新規注文訂正入力　A004リクエスト
 *
 * @author SCSK
 *
 */
@Data
public class IfaMarginNewOrderCorrectInputA004DtoRequest {
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
}
