package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA008DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA008DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0503-02_1
 * 画面名：【IFA】為替注文入力
 * @author <author-name>
 *
 * 2023/09/07 新規作成
 */
public interface IfaIfaFxOrderInputService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaIfaFxOrderInputA001DtoRequest
     * Dto レスポンス：IfaIfaFxOrderInputA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    public DataList<IfaIfaFxOrderInputA001DtoResponse> orderNewMarginA001(IfaIfaFxOrderInputA001DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：更新
     * Dto リクエスト：IfaIfaFxOrderInputA001DtoRequest
     * Dto レスポンス：IfaIfaFxOrderInputA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    public DataList<IfaIfaFxOrderInputA002DtoResponse> orderUpdateMarginA002(IfaIfaFxOrderInputA002DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A008
     * アクション名：注文確認
     * Dto リクエスト：IfaIfaFxOrderInputA008DtoRequest
     * Dto レスポンス：IfaIfaFxOrderInputA008DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    public DataList<IfaIfaFxOrderInputA008DtoResponse> orderConfirmationMarginA008(IfaIfaFxOrderInputA008DtoRequest dtoReq)
            throws Exception;
    
}
