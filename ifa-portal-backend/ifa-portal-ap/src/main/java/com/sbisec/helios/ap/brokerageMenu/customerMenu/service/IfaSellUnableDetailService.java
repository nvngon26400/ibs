package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSellUnableDetailA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSellUnableDetailA001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010201-03
 * 画面名：売却不可明細
 * アクションID：A001
 * アクション名：初期化

 * @author SCSK渡辺
    2023/08/02 新規作成
 */
public interface IfaSellUnableDetailService extends Service {

    /**
     * Dto リクエスト：IfaSellUnableDetailA001DtoRequest
     * Dto レスポンス：IfaSellUnableDetailA001DtoResponse
     * model リクエスト：IfaSellUnableDetailA001RequestModel
     * model レスポンス：IfaSellUnableDetailA001ResponseModel
     */
    public DataList<IfaSellUnableDetailA001DtoResponse> initializeA001(IfaSellUnableDetailA001DtoRequest dtoReq)
            throws Exception;

}

