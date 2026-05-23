package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaJpyAmountUnpaidOverdraftAlertListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaJpyAmountUnpaidOverdraftAlertListA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020301_01-01
 * 画面名：円貨未入金・赤残アラート一覧
 *
 * @author BASE 李
 2024/05/23 新規作成
 */
public interface IfaJpyAmountUnpaidOverdraftAlertListService extends Service {

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaJpyAmountUnpaidOverdraftAlertListA002RequestDto
     * Dto レスポンス：IfaJpyAmountUnpaidOverdraftAlertListA002ResponseDto
     * model リクエスト：IfaJpyAmountUnpaidOverdraftAlertListA002RequestModel
     * model レスポンス：IfaJpyAmountUnpaidOverdraftAlertListA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJpyAmountUnpaidOverdraftAlertListA002ResponseDto> displayA002(IfaJpyAmountUnpaidOverdraftAlertListA002RequestDto dtoReq)
            throws Exception;

}
