package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 書類請求入力A008リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA008ApiRequest {

    /** 書類請求NO */
    private String shoruiSeikyuuNo;

    /** 枝番 */
    private String edaban;
}
