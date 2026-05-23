package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 書類請求取消A002リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestCancelConfirmA002RequestDto {

    /** 書類請求NO */
    private String shoruiSeikyuuNo;

    /** 枝番 */
    private String edaban;
}
