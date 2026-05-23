package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql003ResponseModel;

import lombok.Data;

/**
 * 書類請求入力A002レスポンス
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA002ApiResponse {

    /** 書類リスト */
    private List<IfaDocRequestInputSql003ResponseModel> shoruiSelect;
}
