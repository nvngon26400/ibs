package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 書類請求入力A003リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA003RequestDto {

    /** 分類コード */
    private String bunruiCd;

    /** 書類コード */
    private String shoruiCd;
}
