package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaKnockInBrandHoldingListA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaKnockInBrandHoldingListA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaKnockInBrandHoldingListA004aDtoRequest;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020301_03-02
 * 画面名：ノックイン銘柄保有一覧
 *
 * @author SCSK 大崎
 2024/06/12 新規作成
 */
public interface IfaKnockInBrandHoldingListService extends Service {

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaKnockInBrandHoldingListA002DtoRequest
     * Dto レスポンス：IfaKnockInBrandHoldingListA002DtoResponse
     * model リクエスト：IfaKnockInBrandHoldingListA002RequestModel
     * model レスポンス：IfaKnockInBrandHoldingListA002ResponseModel

     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaKnockInBrandHoldingListA002DtoResponse> displayA002(IfaKnockInBrandHoldingListA002DtoRequest dtoReq)
            throws Exception;
        
    /**
     * アクションID：A004
     * アクション名：PDF取得
     * Dto リクエスト：IfaKnockInBrandHoldingListA004aDtoRequest
     * model リクエスト：IfaKnockInBrandHoldingListA004aRequestModel

     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<String> getPdfA004a(IfaKnockInBrandHoldingListA004aDtoRequest dtoReq)
            throws Exception;

}
