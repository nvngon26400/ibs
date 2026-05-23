package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql012ResponseModel;

import lombok.Data;

/**
 * 書類請求入力A014レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA014ResponseDto {

    /** BM交付詳細データ */
    private IfaDocRequestInputSql012ResponseModel bmShoruiModel;
}
