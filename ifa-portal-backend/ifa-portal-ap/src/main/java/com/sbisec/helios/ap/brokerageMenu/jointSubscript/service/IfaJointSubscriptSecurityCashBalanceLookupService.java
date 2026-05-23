package com.sbisec.helios.ap.brokerageMenu.jointSubscript.service;


import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA004DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0206_04-01
 * 画面名：共同募集　証券・金銭・残高照会
 *
 * @author SBIえん
 2024/12/10 新規作成
 */
public interface IfaJointSubscriptSecurityCashBalanceLookupService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaJointSubscriptSecurityCashBalanceLookupA001DtoRequest
     * Dto レスポンス：IfaJointSubscriptSecurityCashBalanceLookupA001DtoResponse
     * model リクエスト：IfaJointSubscriptSecurityCashBalanceLookupA001RequestModel
     * model レスポンス：IfaJointSubscriptSecurityCashBalanceLookupA001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptSecurityCashBalanceLookupA001DtoResponse> initializeA001(IfaJointSubscriptSecurityCashBalanceLookupA001DtoRequest dtoReq)
            throws Exception;

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaJointSubscriptSecurityCashBalanceLookupA002DtoRequest
     * Dto レスポンス：IfaJointSubscriptSecurityCashBalanceLookupA002DtoResponse
     * model リクエスト：IfaJointSubscriptSecurityCashBalanceLookupA002RequestModel
     * model レスポンス：IfaJointSubscriptSecurityCashBalanceLookupA002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptSecurityCashBalanceLookupA002DtoResponse> displayA002(IfaJointSubscriptSecurityCashBalanceLookupA002DtoRequest dtoReq)
            throws Exception;

    /**s
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaJointSubscriptSecurityCashBalanceLookupA004DtoRequest
     * Dto レスポンス：IfaJointSubscriptSecurityCashBalanceLookupA004DtoResponse
     * model リクエスト：IfaJointSubscriptSecurityCashBalanceLookupA004RequestModel
     * model レスポンス：IfaJointSubscriptSecurityCashBalanceLookupA004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptSecurityCashBalanceLookupA004DtoResponse> csvOutputA004(IfaJointSubscriptSecurityCashBalanceLookupA004DtoRequest dtoReq, String fwSessionId)
            throws Exception;

}
