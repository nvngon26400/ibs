package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql002ResponseModel;

import lombok.Data;

/**
 * 書類請求入力A001レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA001ResponseDto {

    /** 書類分類リスト */
    private List<IfaDocRequestInputSql001ResponseModel> shoruiBunruiSelect;

    /** 書類請求一覧データ */
    private List<IfaDocRequestInputSql002ResponseModel> shoruiList;

}
