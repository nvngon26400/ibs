package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignAmountUnpaidOverdraftAlertListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignAmountUnpaidOverdraftAlertListA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020301_01-03
 * 画面名：外貨未入金・赤残アラート一覧
 *
 * @author BASE 李
 2024/06/12 新規作成
 */
public interface IfaForeignAmountUnpaidOverdraftAlertListService extends Service {

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaForeignAmountUnpaidOverdraftAlertListA002RequestDto
     * Dto レスポンス：IfaForeignAmountUnpaidOverdraftAlertListA002ResponseDto
     * model リクエスト：IfaForeignAmountUnpaidOverdraftAlertListA002RequestModel
     * model レスポンス：IfaForeignAmountUnpaidOverdraftAlertListA002ResponseModel
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaForeignAmountUnpaidOverdraftAlertListA002ResponseDto> displayA002(IfaForeignAmountUnpaidOverdraftAlertListA002RequestDto dtoReq)
            throws Exception;

}
