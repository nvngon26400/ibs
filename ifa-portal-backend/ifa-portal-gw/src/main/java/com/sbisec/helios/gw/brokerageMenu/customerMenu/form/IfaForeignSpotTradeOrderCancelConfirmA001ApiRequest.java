package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 外国現物取引注文取消確認初期化APIリクエスト
 *
 * @author 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderCancelConfirmA001ApiRequest {
    
    /** 国籍コード（全角半角）. */
    @NotEmpty(message = "国籍コード")
    private String countryCd;
    
    /** 注文Sub番号（数字）. */
    @NotEmpty(message = "注文Sub番号")
    @Size(max = 18, message = "注文Sub番号")
    private String orderSubNumber;
    
}
