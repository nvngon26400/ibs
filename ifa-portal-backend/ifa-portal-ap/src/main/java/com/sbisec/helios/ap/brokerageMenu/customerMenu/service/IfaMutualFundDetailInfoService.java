package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundDetailInfoA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundDetailInfoA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0401-03
 * 画面名：投信詳細情報
 *
 * @author SCSK
 *
 *     2024/04/15 新規作成
 */
public interface IfaMutualFundDetailInfoService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMutualFundDetailInfoA001DtoRequest
     * Dto レスポンス：IfaMutualFundDetailInfoA001DtoResponse
     * model リクエスト：IfaMutualFundDetailInfoA001RequestModel
     * model レスポンス：IfaMutualFundDetailInfoA001ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return dtoレスポンス
     * @exception Exception 実行時例外
     */
    public DataList<IfaMutualFundDetailInfoA001ResponseDto> initializeA001(IfaMutualFundDetailInfoA001RequestDto dtoReq)
            throws Exception;
    
}
