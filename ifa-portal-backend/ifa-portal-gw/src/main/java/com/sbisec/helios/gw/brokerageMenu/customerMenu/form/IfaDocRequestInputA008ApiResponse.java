package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql007ResponseModel;

import lombok.Data;

/**
 * 書類請求入力A008レスポンス
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA008ApiResponse {

    /** 書類請求データ */
    private IfaDocRequestInputSql007ResponseModel shoruiModel;
}
