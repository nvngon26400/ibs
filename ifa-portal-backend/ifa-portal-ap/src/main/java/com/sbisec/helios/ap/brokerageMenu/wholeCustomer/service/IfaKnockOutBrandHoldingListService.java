package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaKnockOutBrandHoldingListA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaKnockOutBrandHoldingListA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaKnockOutBrandHoldingListA004aDtoRequest;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020301_03-03
 * 画面名：ノックアウト銘柄保有一覧

 * @author 大崎 辰弥
    2024/06/13 新規作成
 */

public interface IfaKnockOutBrandHoldingListService extends Service {

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaKnockOutBrandHoldingListA002DtoRequest
     * Dto レスポンス：IfaKnockOutBrandHoldingListA002DtoResponse
     * model リクエスト：IfaKnockOutBrandHoldingListA002RequestModel
     * model レスポンス：IfaKnockOutBrandHoldingListA002ResponseModel

     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaKnockOutBrandHoldingListA002DtoResponse> displayA002(IfaKnockOutBrandHoldingListA002DtoRequest dtoReq)
            throws Exception;

      /**
     * アクションID：A004
     * アクション名：PDF取得
     * Dto リクエスト：IfaKnockOutBrandHoldingListA004aDtoRequest
     * model リクエスト：IfaKnockOutBrandHoldingListA004aRequestModel

     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<String> getPdfA004a(IfaKnockOutBrandHoldingListA004aDtoRequest dtoReq)
            throws Exception;

}
