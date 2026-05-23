package com.sbisec.helios.ap.brokerageMenu.commFee.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA004aDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020503-01
 * 画面名：信託報酬
 *
 * @author SCSK 仁井田
 2024/06/11 新規作成
 */
public interface IfaTrustFeeService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * DTO リクエスト：IfaTrustFeeA001DtoRequest
     * DTO レスポンス：IfaTrustFeeA001DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTrustFeeA001DtoResponse> initializeA001(IfaTrustFeeA001DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * DTO リクエスト：IfaTrustFeeA002DtoRequest
     * DTO レスポンス：IfaTrustFeeA002DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTrustFeeA002DtoResponse> displayA002(IfaTrustFeeA002DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A004a
     * アクション名：CSV出力
     * DTO リクエスト：IfaTrustFeeA004bDtoRequest
     * DTO レスポンス：IfaTrustFeeA004bDtoResponse
     *
     * @param dtoReq リクエスト
     * @param frameworkSessionId フレームワークセッションID
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTrustFeeA004aDtoResponse> csvOutputA004a(IfaTrustFeeA004aDtoRequest dtoReq,
            String frameworkSessionId) throws Exception;
    
}
