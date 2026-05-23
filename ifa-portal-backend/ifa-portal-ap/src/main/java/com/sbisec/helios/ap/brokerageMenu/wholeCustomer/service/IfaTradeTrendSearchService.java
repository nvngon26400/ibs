package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA004aDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020302_0401-01
 * 画面名：取引動向検索
 *
 * @author SBI 苗萌
 * 2025/04/10 新規作成
 */
public interface IfaTradeTrendSearchService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * DTO リクエスト：IfaTradeTrendSearchA001DtoRequest
     * DTO レスポンス：IfaTradeTrendSearchA001DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeTrendSearchA001DtoResponse> initializeA001(IfaTradeTrendSearchA001DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * DTO リクエスト：IfaTradeTrendSearchA002DtoRequest
     * DTO レスポンス：IfaTradeTrendSearchA002DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeTrendSearchA002DtoResponse> displayA002(IfaTradeTrendSearchA002DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A004a
     * アクション名：CSV出力
     * DTO リクエスト：IfaTradeTrendSearchA004aDtoRequest
     * DTO レスポンス：IfaTradeTrendSearchA004aDtoResponse
     *
     * @param dtoReq リクエスト
     * @param frameworkSessionId フレームワークセッションID
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeTrendSearchA004aDtoResponse> csvOutputA004a(IfaTradeTrendSearchA004aDtoRequest dtoReq,
            String frameworkSessionId) throws Exception;
    
}
