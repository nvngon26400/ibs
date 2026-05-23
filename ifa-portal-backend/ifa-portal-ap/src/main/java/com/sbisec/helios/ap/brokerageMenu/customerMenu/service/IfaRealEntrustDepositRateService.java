package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateA002DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010304-02
 * 画面名：リアル委託保証金率
 
 * SCSK　大崎辰弥
 * 2023/12/01 新規作成
 */
public interface IfaRealEntrustDepositRateService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaRealEntrustDepositRateA001DtoRequest
     * Dto レスポンス：IfaRealEntrustDepositRateA001DtoResponse
     * model リクエスト：IfaRealEntrustDepositRateA001RequestModel
     * model レスポンス：IfaRealEntrustDepositRateA001ResponseModel

     * @param dtoReq リクエスト
     * @return IfaRealEntrustDepositRateA001DtoResponse レスポンス
     * @exception Exception 例外
     */
    public DataList<IfaRealEntrustDepositRateA001DtoResponse> initializeA001(IfaRealEntrustDepositRateA001DtoRequest dtoReq)
            throws Exception;

    /**
     * アクションID：A002
     * アクション名：更新
     * Dto リクエスト：IfaRealEntrustDepositRateA002DtoRequest
     * Dto レスポンス：IfaRealEntrustDepositRateA002DtoResponse
     * model リクエスト：IfaRealEntrustDepositRateA002RequestModel
     * model レスポンス：IfaRealEntrustDepositRateA002ResponseModel

     * @param dtoReq リクエスト
     * @return IfaRealEntrustDepositRateA002DtoResponse レスポンス
     * @exception Exception 例外
     */
    public DataList<IfaRealEntrustDepositRateA002DtoResponse> updateA002(IfaRealEntrustDepositRateA002DtoRequest dtoReq)
            throws Exception;
}
