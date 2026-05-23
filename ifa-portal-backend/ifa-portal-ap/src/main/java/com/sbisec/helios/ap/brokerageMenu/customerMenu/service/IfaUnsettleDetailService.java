package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaUnsettleDetailA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaUnsettleDetailA001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010301-02
 * 画面名：未精算明細
 * @author <author-name>
 *
 * 2023/09/15 新規作成
 */
public interface IfaUnsettleDetailService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaUnsettleDetailA001DtoRequest
     * Dto レスポンス：IfaUnsettleDetailA001DtoResponse
     * model リクエスト：IfaUnsettleDetailA001RequestModel
     * model レスポンス：IfaUnsettleDetailA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaUnsettleDetailA001DtoResponse> initializeA001(IfaUnsettleDetailA001DtoRequest dtoReq)
            throws Exception;

}
