package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql004ResponseModel;

import lombok.Data;

/**
 * 書類請求入力A003レスポンス
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA003ApiResponse {

    /** 書類データ */
    private IfaDocRequestInputSql004ResponseModel shoruiDate;
}
