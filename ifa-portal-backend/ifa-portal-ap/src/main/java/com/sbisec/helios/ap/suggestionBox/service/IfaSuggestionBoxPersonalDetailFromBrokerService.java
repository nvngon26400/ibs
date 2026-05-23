package com.sbisec.helios.ap.suggestionBox.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.service.Service;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailFromBrokerA001DtoRequest;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailFromBrokerA001DtoResponse;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailFromBrokerA006DtoRequest;

/**
 * 画面ID：SUB0511_01-02
 * 画面名：仲介業者からの要望詳細
 * @author SCSK山岸
 * 2025/07/25 新規作成
 */
public interface IfaSuggestionBoxPersonalDetailFromBrokerService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaSuggestionBoxPersonalDetailFromBrokerA001DtoRequest
     * Dto レスポンス：IfaSuggestionBoxPersonalDetailFromBrokerA001DtoResponse
     * model リクエスト：IfaSuggestionBoxPersonalDetailFromBrokerA001RequestModel
     * model レスポンス：IfaSuggestionBoxPersonalDetailFromBrokerA001ResponseModel
     * @param req リクエストDTO
     * @return res レスポンスDTO
     * @exception Exception システムエラー
     * @see <reference item>
     */
    public DataList<IfaSuggestionBoxPersonalDetailFromBrokerA001DtoResponse> initializeA001(
            IfaSuggestionBoxPersonalDetailFromBrokerA001DtoRequest dtoReq
        ) throws Exception;

    /**
     * アクションID：A006
     * アクション名：登録
     * Dto リクエスト：IfaSuggestionBoxPersonalDetailFromBrokerA006DtoRequest
     * Dto レスポンス：String
     * model リクエスト：IfaSuggestionBoxPersonalDetailFromBrokerA006RequestModel
     * model レスポンス：String
     * @param req リクエストDTO
     * @return DataList<String>
     * @exception Exception システムエラー
     * @see <reference item>
     */
    public DataList<String> registerA006(
            IfaSuggestionBoxPersonalDetailFromBrokerA006DtoRequest dtoReq
        ) throws Exception;

}
