package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 書類請求入力A007レスポンス
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA007ApiResponse {

    /** 投信銘柄名 */
    private String fundName;

    /** 協会コード */
    private String fundCode;
}
