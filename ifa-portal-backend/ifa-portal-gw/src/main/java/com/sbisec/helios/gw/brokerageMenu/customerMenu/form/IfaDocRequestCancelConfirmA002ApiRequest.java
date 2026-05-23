package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 書類請求取消A002リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestCancelConfirmA002ApiRequest {

    /** 書類請求NO */
    private String shoruiSeikyuuNo;

    /** 枝番 */
    private String edaban;
}
