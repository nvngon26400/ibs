package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 外国現物取引注文取消確認初期化リクエスト
 *
 * @author 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderCancelConfirmA001RequestDto {
    
    /** 国籍コード（全角半角）. */
    private String countryCd;
    
    /** 注文Sub番号（数字）. */
    private String orderSubNumber;
    
}
