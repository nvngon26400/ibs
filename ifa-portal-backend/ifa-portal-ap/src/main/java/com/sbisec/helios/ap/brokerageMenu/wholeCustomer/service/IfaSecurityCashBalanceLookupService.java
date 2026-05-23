package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;


import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA006DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA006DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020302_0301-01
 * 画面名：証券・金銭・残高照会
 *
 * @author SCSK濱田
 2024/05/07 新規作成
 */
public interface IfaSecurityCashBalanceLookupService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaSecurityCashBalanceLookupA001DtoRequest
     * Dto レスポンス：IfaSecurityCashBalanceLookupA001DtoResponse
     * model リクエスト：IfaSecurityCashBalanceLookupA001RequestModel
     * model レスポンス：IfaSecurityCashBalanceLookupA001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSecurityCashBalanceLookupA001DtoResponse> initializeA001(IfaSecurityCashBalanceLookupA001DtoRequest dtoReq)
            throws Exception;

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaSecurityCashBalanceLookupA002DtoRequest
     * Dto レスポンス：IfaSecurityCashBalanceLookupA002DtoResponse
     * model リクエスト：IfaSecurityCashBalanceLookupA002RequestModel
     * model レスポンス：IfaSecurityCashBalanceLookupA002ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSecurityCashBalanceLookupA002DtoResponse> displayA002(IfaSecurityCashBalanceLookupA002DtoRequest dtoReq)
            throws Exception;

    /**
     * アクションID：A006
     * アクション名：CSV出力
     * Dto リクエスト：IfaSecurityCashBalanceLookupA006DtoRequest
     * Dto レスポンス：IfaSecurityCashBalanceLookupA006DtoResponse
     * model リクエスト：IfaSecurityCashBalanceLookupA006RequestModel
     * model レスポンス：IfaSecurityCashBalanceLookupA006ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSecurityCashBalanceLookupA006DtoResponse> csvOutputA006(IfaSecurityCashBalanceLookupA006DtoRequest dtoReq, String fwSessionId)
            throws Exception;

}
