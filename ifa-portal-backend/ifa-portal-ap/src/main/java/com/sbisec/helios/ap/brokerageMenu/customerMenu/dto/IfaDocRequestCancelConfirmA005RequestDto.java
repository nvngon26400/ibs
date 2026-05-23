package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 書類請求取消A005リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestCancelConfirmA005RequestDto {

    /** BM交付番号 */
    private String bmDeliveryNo;

}