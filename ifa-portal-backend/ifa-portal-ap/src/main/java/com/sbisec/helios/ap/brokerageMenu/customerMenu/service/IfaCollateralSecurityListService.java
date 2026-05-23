package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityListA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityListA004ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010204-01
 * 画面名：代用有価証券一覧
 * アクションID：A001
 * アクション名：初期表示
 *
 * @author SCSK
 */
public interface IfaCollateralSecurityListService extends Service {
    
    /**
     * Dto リクエスト：IfaCollateralSecurityListA001DtoRequest
     * Dto レスポンス：IfaCollateralSecurityListA001DtoResponse
     * model リクエスト：IfaCollateralSecurityListA001RequestModel
     * model レスポンス：IfaCollateralSecurityListA001ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaCollateralSecurityListA001ResponseDto> initialA001(
            IfaCollateralSecurityListA001RequestDto dtoReq) throws Exception;
    
    /**
     * Dto リクエスト：IfaCollateralSecurityListA004DtoRequest
     * Dto レスポンス：IfaCollateralSecurityListA004DtoResponse
     * model リクエスト：IfaCollateralSecurityListA004RequestModel
     * model レスポンス：IfaCollateralSecurityListA004ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスDTO
     * @exception Exception 更新化処理で例外が発生した場合
     */
    public DataList<IfaCollateralSecurityListA004ResponseDto> updateA004(IfaCollateralSecurityListA004RequestDto dtoReq)
            throws Exception;
    
}
