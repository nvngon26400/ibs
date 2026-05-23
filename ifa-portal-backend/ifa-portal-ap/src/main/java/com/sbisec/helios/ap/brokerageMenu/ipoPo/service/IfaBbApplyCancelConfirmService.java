package com.sbisec.helios.ap.brokerageMenu.ipoPo.service;


import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0204_02-03_2
 * 画面名：BB申込取消確認
 *
 * @author BASE 李
 2024/05/14 新規作成
 */
public interface IfaBbApplyCancelConfirmService extends Service {

    /**
     * アクションID：A001
     * アクション名：申込取消
     * Dto リクエスト：IfaBbApplyCancelConfirmA001RequestDto
     * Dto レスポンス：IfaBbApplyCancelConfirmA001ResponseDto
     * model リクエスト：IfaBbApplyCancelConfirmA001RequestModel
     * model レスポンス：IfaBbApplyCancelConfirmA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCancelConfirmA001ResponseDto> applicationCancellationA001(IfaBbApplyCancelConfirmA001RequestDto dtoReq)
            throws Exception;

}
