package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA003DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA003DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA004aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA005DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA005DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
* 画面ID：SUB0202_0703-01
 * 画面名：受発信状況照会
 *
 * @author SBI大連 董
 *2025/03/20 新規作成
 */

public interface IfaSendReceiveStatusLookupService extends Service{

    
    /**
     * アクションID：A001
     * アクション名：初期化
     * DTO リクエスト：IfaSendReceiveStatusLookupA001DtoRequest
     * DTO レスポンス：IfaSendReceiveStatusLookupA001DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList< IfaSendReceiveStatusLookupA001DtoResponse> initializeA001( IfaSendReceiveStatusLookupA001DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示　書類コード
     * DTO リクエスト：IfaSendReceiveStatusLookupA002DtoRequest
     * DTO レスポンス：IfaSendReceiveStatusLookupA002DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList< IfaSendReceiveStatusLookupA002DtoResponse> displayA002( IfaSendReceiveStatusLookupA002DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：検索ボタン　検索(キーワード)
     * DTO リクエスト：IfaSendReceiveStatusLookupA003DtoRequest
     * DTO レスポンス：IfaSendReceiveStatusLookupA003DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList< IfaSendReceiveStatusLookupA003DtoResponse> displayA003( IfaSendReceiveStatusLookupA003DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A004a
     * アクション名：CSV出力
     * DTO リクエスト：IfaSendReceiveStatusLookupA004aDtoRequest
     * DTO レスポンス：IfaSendReceiveStatusLookupA004aDtoResponse
     *
     * @param dtoReq リクエスト
     * @param frameworkSessionId フレームワークセッションID
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList< IfaSendReceiveStatusLookupA004aDtoResponse> csvOutputA004a( IfaSendReceiveStatusLookupA004aDtoRequest dtoReq,
            String frameworkSessionId) throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：popup書類請求付加情報詳細
     * DTO リクエスト：IfaSendReceiveStatusLookupA005DtoRequest
     * DTO レスポンス：IfaSendReceiveStatusLookupA005DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList< IfaSendReceiveStatusLookupA005DtoResponse> selectdocRequestAddInfoA005( IfaSendReceiveStatusLookupA005DtoRequest dtoReq) throws Exception;
}

