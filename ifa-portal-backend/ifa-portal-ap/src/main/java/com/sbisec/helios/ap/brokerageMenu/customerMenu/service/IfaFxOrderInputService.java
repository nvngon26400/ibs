package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA008DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA008DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0502-02_1
 * 画面名：為替注文入力
 * @author <author-name>
 *
 * 2023/09/16 新規作成
 */
public interface IfaFxOrderInputService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaFxOrderInputA001DtoRequest
     * Dto レスポンス：IfaFxOrderInputA001DtoResponse
     * model リクエスト：IfaFxOrderInputA001RequestModel
     * model レスポンス：IfaFxOrderInputA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaFxOrderInputA001DtoResponse> initializeA001(IfaFxOrderInputA001DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：更新
     * Dto リクエスト：IfaFxOrderInputA002DtoRequest
     * Dto レスポンス：IfaFxOrderInputA002DtoResponse
     * model リクエスト：IfaFxOrderInputA002RequestModel
     * model レスポンス：IfaFxOrderInputA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaFxOrderInputA002DtoResponse> updateA002(IfaFxOrderInputA002DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A008
     * アクション名：注文確認
     * Dto リクエスト：IfaFxOrderInputA008DtoRequest
     * Dto レスポンス：IfaFxOrderInputA008DtoResponse
     * model リクエスト：IfaFxOrderInputA008RequestModel
     * model レスポンス：IfaFxOrderInputA008ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaFxOrderInputA008DtoResponse> orderConfirmationA008(IfaFxOrderInputA008DtoRequest dtoReq)
            throws Exception;
    
}
