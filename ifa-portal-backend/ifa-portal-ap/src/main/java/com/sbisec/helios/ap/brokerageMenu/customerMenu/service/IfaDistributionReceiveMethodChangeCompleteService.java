package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeCompleteA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeCompleteA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeCompleteA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeCompleteA001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010201-02_2
 * 画面名：分配金受取方法変更完了
 * @author <author-name>
 *
 * 2023/12/04 新規作成
 */
public interface IfaDistributionReceiveMethodChangeCompleteService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDistributionReceiveMethodChangeCompleteA001DtoRequest
     * Dto レスポンス：IfaDistributionReceiveMethodChangeCompleteA001DtoResponse
     * model リクエスト：IfaDistributionReceiveMethodChangeCompleteA001RequestModel
     * model レスポンス：IfaDistributionReceiveMethodChangeCompleteA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDistributionReceiveMethodChangeCompleteA001DtoResponse> initializeA001(IfaDistributionReceiveMethodChangeCompleteA001DtoRequest dtoReq)
            throws Exception;

}
