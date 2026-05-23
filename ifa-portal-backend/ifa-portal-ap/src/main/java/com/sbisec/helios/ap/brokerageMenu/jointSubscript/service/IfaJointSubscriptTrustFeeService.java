package com.sbisec.helios.ap.brokerageMenu.jointSubscript.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA004aDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0206_03-01
 * 画面名：共同募集　信託報酬
 *
 * @author SBI 苗萌
 * 2024/12/18 新規作成
 */
public interface IfaJointSubscriptTrustFeeService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * DTO リクエスト：IfaJointSubscriptTrustFeeA001DtoRequest
     * DTO レスポンス：IfaJointSubscriptTrustFeeA001DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptTrustFeeA001DtoResponse> initializeA001(IfaJointSubscriptTrustFeeA001DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * DTO リクエスト：IfaJointSubscriptTrustFeeA002DtoRequest
     * DTO レスポンス：IfaJointSubscriptTrustFeeA002DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptTrustFeeA002DtoResponse> displayA002(IfaJointSubscriptTrustFeeA002DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A004a
     * アクション名：CSV出力
     * DTO リクエスト：IfaJointSubscriptTrustFeeA004aDtoRequest
     * DTO レスポンス：IfaJointSubscriptTrustFeeA004aDtoResponse
     *
     * @param dtoReq リクエスト
     * @param frameworkSessionId フレームワークセッションID
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptTrustFeeA004aDtoResponse> csvOutputA004a(IfaJointSubscriptTrustFeeA004aDtoRequest dtoReq,
            String frameworkSessionId) throws Exception;
    
}
