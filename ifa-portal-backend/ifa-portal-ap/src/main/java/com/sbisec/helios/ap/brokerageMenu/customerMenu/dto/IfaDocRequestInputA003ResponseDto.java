package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql004ResponseModel;

import lombok.Data;

/**
 * 書類請求入力A003レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA003ResponseDto {

    /** 書類データ */
    private IfaDocRequestInputSql004ResponseModel shoruiDate;
}
