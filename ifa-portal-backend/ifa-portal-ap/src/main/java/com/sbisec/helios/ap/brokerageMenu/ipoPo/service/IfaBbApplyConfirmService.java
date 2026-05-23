package com.sbisec.helios.ap.brokerageMenu.ipoPo.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyConfirmA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0204_01-02_2
 * 画面名：BB申込確認
 *
 * @author BASE李
 * 
 2024/02/29 新規作成
 */
public interface IfaBbApplyConfirmService extends Service {

    /**
     * アクションID：A001
     * アクション名：申込登録
     * Dto リクエスト：IfaBbApplyConfirmA001RequestDto
     * Dto レスポンス：IfaBbApplyConfirmA001ResponseDto
     * model リクエスト：IfaBbApplyConfirmA001RequestModel
     * model レスポンス：IfaBbApplyConfirmA001ResponseModel
     *
     * @param dtoReq IfaBbApplyConfirmA001RequestDto
     * @return IfaBbApplyConfirmA001ResponseDto
     * @exception Exception システムエラー
     */
    public DataList<IfaBbApplyConfirmA001ResponseDto> applicationRegistrationA001(IfaBbApplyConfirmA001RequestDto dtoReq)
            throws Exception;
    
    

}
