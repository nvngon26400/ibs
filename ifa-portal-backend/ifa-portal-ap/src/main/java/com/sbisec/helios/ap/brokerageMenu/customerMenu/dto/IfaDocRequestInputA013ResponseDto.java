package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql012ResponseModel;

import lombok.Data;

/**
 * 書類請求入力A0013レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA013ResponseDto {

    /** BM交付取消データ */
    private IfaDocRequestInputSql012ResponseModel bmShoruiModel;
}
