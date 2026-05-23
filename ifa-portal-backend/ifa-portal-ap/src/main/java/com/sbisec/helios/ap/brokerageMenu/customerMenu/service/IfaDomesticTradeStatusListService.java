package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticTradeStatusListA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticTradeStatusListA001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0105-01
 * 画面名：国内株当日約定状況（一覧）
 * @author <author-name>
 *
 * 2023/09/22 新規作成
 */
public interface IfaDomesticTradeStatusListService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDomesticTradeStatusListA001DtoRequest
     * Dto レスポンス：IfaDomesticTradeStatusListA001DtoResponse
     * model リクエスト：IfaDomesticTradeStatusListA001RequestModel
     * model レスポンス：IfaDomesticTradeStatusListA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDomesticTradeStatusListA001DtoResponse> initializeA001(IfaDomesticTradeStatusListA001DtoRequest dtoReq)
            throws Exception;

}
