package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignPositionListA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignPositionListA001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010203-01
 * 画面名：米株建玉一覧
 * @author <author-name>
 *
 * 2023/11/01 新規作成
 */
public interface IfaForeignPositionListService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignPositionListA001DtoRequest
     * Dto レスポンス：IfaForeignPositionListA001DtoResponse
     * model リクエスト：IfaForeignPositionListA001RequestModel
     * model レスポンス：IfaForeignPositionListA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignPositionListA001DtoResponse> initializeA001(IfaForeignPositionListA001DtoRequest dtoReq)
            throws Exception;

}
