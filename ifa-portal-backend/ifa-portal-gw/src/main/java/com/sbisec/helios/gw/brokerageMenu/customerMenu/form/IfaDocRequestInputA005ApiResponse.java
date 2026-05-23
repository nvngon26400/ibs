package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 書類請求入力A005レスポンス
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA005ApiResponse {

    /** 投信銘柄名 */
    private String fundName;

    /** 銘柄コード(上4桁) */
    private String fundNricode4;

    /** 銘柄コード(下3桁) */
    private String fundNricode3;

    /** 協会コード */
    private String fundCode;

    /** 購入可否判定区分 */
    private String fundMdBuyKubun;
}
