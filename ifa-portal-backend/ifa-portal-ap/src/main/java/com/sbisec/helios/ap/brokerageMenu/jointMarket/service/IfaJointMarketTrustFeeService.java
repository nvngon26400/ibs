package com.sbisec.helios.ap.brokerageMenu.jointMarket.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA004aDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0208_02
 * 画面名：共同店舗 信託報酬
 *
 * @author SBI大連 董
 2024/12/12 新規作成
 */
public interface IfaJointMarketTrustFeeService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * DTO リクエスト：IfaJointMarketTrustFeeA001DtoRequest
     * DTO レスポンス：IfaJointMarketTrustFeeA001DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList< IfaJointMarketTrustFeeA001DtoResponse> initializeA001( IfaJointMarketTrustFeeA001DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * DTO リクエスト：IfaJointMarketTrustFeeA002DtoRequest
     * DTO レスポンス：IfaJointMarketTrustFeeA002DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList< IfaJointMarketTrustFeeA002DtoResponse> displayA002( IfaJointMarketTrustFeeA002DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A004a
     * アクション名：CSV出力
     * DTO リクエスト：IfaJointMarketTrustFeeA004bDtoRequest
     * DTO レスポンス：IfaJointMarketTrustFeeA004bDtoResponse
     *
     * @param dtoReq リクエスト
     * @param frameworkSessionId フレームワークセッションID
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList< IfaJointMarketTrustFeeA004aDtoResponse> csvOutputA004a( IfaJointMarketTrustFeeA004aDtoRequest dtoReq,
            String frameworkSessionId) throws Exception;
    
}
