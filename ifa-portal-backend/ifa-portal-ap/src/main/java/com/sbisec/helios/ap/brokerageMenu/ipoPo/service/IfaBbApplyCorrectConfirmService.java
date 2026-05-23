package com.sbisec.helios.ap.brokerageMenu.ipoPo.service;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectConfirmA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0204_02-02_2
 * 画面名：BB申込訂正確認
 *
 * @author BASE 李
 2024/04/23 新規作成
 */
public interface IfaBbApplyCorrectConfirmService extends Service {

    /**
     * アクションID：A001
     * アクション名：申込訂正
     * Dto リクエスト：IfaBbApplyCorrectConfirmA001RequestDto
     * Dto レスポンス：IfaBbApplyCorrectConfirmA001ResponseDto
     * model リクエスト：IfaBbApplyCorrectConfirmA001RequestModel
     * model レスポンス：IfaBbApplyCorrectConfirmA001ResponseModel
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectConfirmA001ResponseDto> applicationCorrectionA001(IfaBbApplyCorrectConfirmA001RequestDto dtoReq)
            throws Exception;

}
