package com.sbisec.helios.ap.faq.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.faq.dto.IfaFaqA002DtoRequest;
import com.sbisec.helios.ap.faq.dto.IfaFaqA002DtoResponse;
import com.sbisec.helios.ap.faq.dto.IfaFaqA005DtoRequest;
import com.sbisec.helios.ap.faq.dto.IfaFaqA005DtoResponse;
import com.sbisec.helios.ap.faq.dto.IfaFaqX001DtoRequest;
import com.sbisec.helios.ap.faq.dto.IfaFaqX001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB00-05
 * 画面名：よくある質問
 *
 * @author SCSK 仁井田
 2024/05/30 新規作成
 */
public interface IfaFaqService extends Service {
    
    /**
     * アクションID：X001
     * アクション名：初期表示
     * DTO リクエスト：IfaFaqX001DtoRequest
     * DTO レスポンス：IfaFaqX001DtoResponse
     * model リクエスト：IfaFaqX001RequestModel
     * model レスポンス：IfaFaqX001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaFaqX001DtoResponse> initialDisplayX001(IfaFaqX001DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：全文検索
     * DTO リクエスト：IfaFaqA002DtoRequest
     * DTO レスポンス：IfaFaqA002DtoResponse
     * model リクエスト：IfaFaqA002RequestModel
     * model レスポンス：IfaFaqA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaFaqA002DtoResponse> fullTextSearchA002(IfaFaqA002DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：コンテンツ表示
     * DTO リクエスト：IfaFaqA005DtoRequest
     * DTO レスポンス：IfaFaqA005DtoResponse
     * model リクエスト：IfaFaqA005RequestModel
     * model レスポンス：IfaFaqA005ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaFaqA005DtoResponse> contentsDisplayA005(IfaFaqA005DtoRequest dtoReq) throws Exception;
    
}
