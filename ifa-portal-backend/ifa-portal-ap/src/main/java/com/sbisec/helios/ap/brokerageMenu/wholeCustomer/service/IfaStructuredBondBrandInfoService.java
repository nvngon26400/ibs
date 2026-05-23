package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaStructuredBondBrandInfoA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaStructuredBondBrandInfoA001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020302_0104-02
 * 画面名：仕組債銘柄情報
 *
 * @author SCSK川崎
 2024/06/11 新規作成
 */
public interface IfaStructuredBondBrandInfoService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaStructuredBondBrandInfoA001DtoRequest
     * Dto レスポンス：IfaStructuredBondBrandInfoA001DtoResponse
     * model リクエスト：IfaStructuredBondBrandInfoA001RequestModel
     * model レスポンス：IfaStructuredBondBrandInfoA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStructuredBondBrandInfoA001DtoResponse> initializeA001(
            IfaStructuredBondBrandInfoA001DtoRequest dtoReq) throws Exception;

}
