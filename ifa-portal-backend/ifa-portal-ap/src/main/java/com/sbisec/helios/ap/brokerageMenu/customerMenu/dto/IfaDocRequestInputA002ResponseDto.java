package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql003ResponseModel;

import lombok.Data;

/**
 * 書類請求入力A002レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA002ResponseDto {

    /** 書類リスト */
    private List<IfaDocRequestInputSql003ResponseModel> shoruiSelect;

}
