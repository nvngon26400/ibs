package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerForeignA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerForeignA001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010304-01
 * 画面名：信用余力(米株)
 * アクションID：A001
 * アクション名：初期化

 * @author SCSK 矢口
    2023/12/1 新規作成
 */
public interface IfaMarginPowerForeignService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMarginPowerForeignA001DtoRequest
     * Dto レスポンス：IfaMarginPowerForeignA001DtoResponse
     * model リクエスト：brokerageMenu.customerMenuA001RequestModel
     * model レスポンス：brokerageMenu.customerMenuA001ResponseModel

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaMarginPowerForeignA001DtoResponse> initializeA001(IfaMarginPowerForeignA001DtoRequest dtoReq)
            throws Exception;
}
