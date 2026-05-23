package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql007ResponseModel;

import lombok.Data;

/**
 * 書類請求入力A008レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA009ResponseDto {

    /** 書類請求データ */
    private IfaDocRequestInputSql007ResponseModel shoruiModel;
}
