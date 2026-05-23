package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerForeignA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerForeignA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerForeignA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerForeignA002DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010303-01
 * 画面名：買付余力(外国)

 * @author SCSK渡辺
    2023/10/10 新規作成
 */
public interface IfaBuyingPowerForeignService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaBuyingPowerForeignA001DtoRequest
     * Dto レスポンス：IfaBuyingPowerForeignA001DtoResponse
     * model リクエスト：IfaBuyingPowerForeignA001RequestModel
     * model レスポンス：IfaBuyingPowerForeignA001ResponseModel
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     */
    public DataList<IfaBuyingPowerForeignA001DtoResponse> 
            initializeA001(IfaBuyingPowerForeignA001DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：再検索
     * Dto リクエスト：IfaBuyingPowerForeignA002DtoRequest
     * Dto レスポンス：IfaBuyingPowerForeignA001DtoResponse
     * model リクエスト：IfaBuyingPowerForeignA002RequestModel
     * model レスポンス：IfaBuyingPowerForeignA002ResponseModel
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     */
    public DataList<IfaBuyingPowerForeignA001DtoResponse> 
            reSearchA002(IfaBuyingPowerForeignA002DtoRequest dtoReq)
            throws Exception;

}