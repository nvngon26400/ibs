package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityDeliverInOutDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityDeliverInOutDetailA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010204-02
 * 画面名：代用有価証券入出庫-個別詳細
 * アクションID：A001
 * アクション名：初期化
 *
 * @author SCSK
 *
 */
public interface IfaCollateralSecurityDeliverInOutDetailService extends Service {
    
    /**
     * Dto リクエスト：IfaCollateralSecurityDeliverInOutDetailA001RequestDto
     * Dto レスポンス：IfaCollateralSecurityDeliverInOutDetailA001ResponseDto
     * model リクエスト：IfaCollateralSecurityDeliverInOutDetailA001RequestModel
     * model レスポンス：IfaCollateralSecurityDeliverInOutDetailA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaCollateralSecurityDeliverInOutDetailA001ResponseDto> initializeA001(
            IfaCollateralSecurityDeliverInOutDetailA001RequestDto dtoReq) throws Exception;
    
}
