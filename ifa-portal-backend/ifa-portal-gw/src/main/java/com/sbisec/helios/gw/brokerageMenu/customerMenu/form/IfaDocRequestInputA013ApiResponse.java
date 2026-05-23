package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql012ResponseModel;

import lombok.Data;

/**
 * 書類請求入力A013レスポンス
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA013ApiResponse {

    /** BM交付取消データ */
    private IfaDocRequestInputSql012ResponseModel bmShoruiModel;
}
