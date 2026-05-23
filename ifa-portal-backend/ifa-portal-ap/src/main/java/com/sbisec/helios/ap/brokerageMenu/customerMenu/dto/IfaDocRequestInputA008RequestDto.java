package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 書類請求入力A008リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA008RequestDto {

    /** 書類請求NO */
    private String shoruiSeikyuuNo;

    /** 枝番 */
    private String edaban;
}
