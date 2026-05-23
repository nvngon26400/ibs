package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 書類請求確認A005リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestExecuteConfirmA005ApiRequest {

    /** 種別 */
    private String bmKoufuShubetsu;

    /** 分類コード */
    private String bunruiCd;

    /** 分類名 */
    private String bunruimei;

    /** 書類コード */
    private String shoruiCd;

    /** 書類名 */
    private String shoruimei;

    /** 投信銘柄コード */
    private String meigaraCd;

    /** 投信銘柄名 */
    private String meigaraMei;

    /** 協会コード */
    private String fundCode;
}
