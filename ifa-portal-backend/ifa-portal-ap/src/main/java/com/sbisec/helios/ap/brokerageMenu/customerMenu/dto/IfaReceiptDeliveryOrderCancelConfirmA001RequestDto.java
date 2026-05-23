package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 現引現渡注文取消確認　A001リクエスト
 * 2024/05/21 新規作成
 *
 * @author SCSK
 */
@Data
public class IfaReceiptDeliveryOrderCancelConfirmA001RequestDto {

    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;

}
