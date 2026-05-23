package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaStockCertificateDetailA001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0703-03
 * 画面名：株券詳細
 *
 * @author SBI大連 董
 *2025/03/20 新規作成
 */
public interface IfaStockCertificateDetailService extends Service {

    /**
     * アクションID：A001 
     * アクション名：初期化 
     * DTO リクエスト：IfaStockCertificateDetailA001DtoRequest
     * DTO レスポンス：IfaStockCertificateDetailA001DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStockCertificateDetailA001DtoResponse> initializeA001(
            IfaStockCertificateDetailA001DtoResponse dtoReq) throws Exception;

}
