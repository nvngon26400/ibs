package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 書類請求入力A003リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA003ApiRequest {

    /** 分類コード */
    private String bunruiCd;

    /** 書類コード */
    private String shoruiCd;
}
