package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCurrencyDealtListA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCurrencyDealtListA001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0502-01
 * 画面名：取扱通貨一覧
 *
 * @author 池亀緑
 *
 *         2023/08/23 新規作成
 */
public interface IfaCurrencyDealtListService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaCurrencyDealtListA001DtoRequest
     * Dto レスポンス：IfaCurrencyDealtListA001DtoResponse
     * model リクエスト：IfaCurrencyDealtListA001RequestModel
     * model レスポンス：IfaCurrencyDealtListA001ResponseModel
     *
     * @param dtoReq {@code IfaCurrencyDealtListA001DtoRequest }
     * @return {@code DataList <IfaCurrencyDealtListA001DtoResponse>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaCurrencyDealtListA001DtoResponse> orderNewMarginA001(IfaCurrencyDealtListA001DtoRequest dtoReq)
            throws Exception;
    
}
