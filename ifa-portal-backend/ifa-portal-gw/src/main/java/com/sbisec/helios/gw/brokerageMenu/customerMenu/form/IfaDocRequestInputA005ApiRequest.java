package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 書類請求入力A005リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA005ApiRequest {

    /** 銘柄コード(上4桁) */
    private String fundNricode4;

    /** 銘柄コード(下3桁) */
    private String fundNricode3;
}
