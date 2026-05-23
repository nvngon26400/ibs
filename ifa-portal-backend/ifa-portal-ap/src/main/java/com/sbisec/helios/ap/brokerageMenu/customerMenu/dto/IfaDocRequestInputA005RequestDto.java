package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 書類請求入力A005リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA005RequestDto {

    /** 銘柄コード(上4桁) */
    private String fundNricode4;

    /** 銘柄コード(下3桁) */
    private String fundNricode3;
}
