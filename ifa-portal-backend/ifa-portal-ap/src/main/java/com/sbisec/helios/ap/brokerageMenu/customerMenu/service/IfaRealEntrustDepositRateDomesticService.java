package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateDomesticX001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateDomesticX001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateDomesticA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateDomesticA002DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010302-03
 * 画面名：リアル委託保証金率（国内）
 
 * SCSK
 * 2024/07/29 新規作成
 */
public interface IfaRealEntrustDepositRateDomesticService extends Service {

    /**
     * アクションID：X001
     * アクション名：初期化
     * Dto リクエスト：IfaRealEntrustDepositRateDomesticX001DtoRequest
     * Dto レスポンス：IfaRealEntrustDepositRateDomesticX001DtoResponse
     * model リクエスト：IfaRealEntrustDepositRateDomesticX001RequestModel
     * model レスポンス：IfaRealEntrustDepositRateDomesticX001ResponseModel

     * @param dtoReq リクエスト
     * @return IfaRealEntrustDepositRateDomesticX001DtoResponse レスポンス
     * @exception Exception 例外
     */
    public DataList<IfaRealEntrustDepositRateDomesticX001DtoResponse> initializeX001(IfaRealEntrustDepositRateDomesticX001DtoRequest dtoReq)
            throws Exception;

    /**
     * アクションID：A002
     * アクション名：更新
     * Dto リクエスト：IfaRealEntrustDepositRateDomesticA002DtoRequest
     * Dto レスポンス：IfaRealEntrustDepositRateDomesticA002DtoResponse
     * model リクエスト：IfaRealEntrustDepositRateDomesticA002RequestModel
     * model レスポンス：IfaRealEntrustDepositRateDomesticA002ResponseModel

     * @param dtoReq リクエスト
     * @return IfaRealEntrustDepositRateDomesticA002DtoResponse レスポンス
     * @exception Exception 例外
     */
    public DataList<IfaRealEntrustDepositRateDomesticA002DtoResponse> updateA002(IfaRealEntrustDepositRateDomesticA002DtoRequest dtoReq)
            throws Exception;
}
