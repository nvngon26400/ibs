package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 信用新規注文取消確認注文発注APIレスポンス.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginNewOrderCancelConfirmA002ApiResponse {
    
    /** 受注日時. */
    private String orderDayTime;
    
    /** リクエスト内容. */
    private IfaMarginNewOrderCancelConfirmA002ApiRequest request;
    
}
