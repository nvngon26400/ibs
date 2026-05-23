package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA004DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA010DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA010DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0212-02_1
 * 画面名：信用新規注文訂正入力
 *
 * @author SCSK
   2024/03/28 新規作成
 */
public interface IfaMarginNewOrderCorrectInputService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMarginNewOrderCorrectInputA001DtoRequest
     * Dto レスポンス：IfaMarginNewOrderCorrectInputA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return IfaMarginNewOrderCorrectInputA001DtoResponse
     * @exception Exception 例外
     */
    public DataList<IfaMarginNewOrderCorrectInputA001DtoResponse> initializeA001(
            IfaMarginNewOrderCorrectInputA001DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：更新
     * Dto リクエスト：IfaMarginNewOrderCorrectInputA004DtoRequest
     * Dto レスポンス：IfaMarginNewOrderCorrectInputA004DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return IfaMarginNewOrderCorrectInputA004DtoResponse
     * @exception Exception 例外
     */
    public DataList<IfaMarginNewOrderCorrectInputA004DtoResponse> updateA004(
            IfaMarginNewOrderCorrectInputA004DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A010
     * アクション名：訂正確認
     * Dto リクエスト：IfaMarginNewOrderCorrectInputA010DtoRequest
     * Dto レスポンス：IfaMarginNewOrderCorrectInputA010DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return IfaMarginNewOrderCorrectInputA010DtoResponse
     * @exception Exception 例外
     */
    public DataList<IfaMarginNewOrderCorrectInputA010DtoResponse> correctionConfirmA010(
            IfaMarginNewOrderCorrectInputA010DtoRequest dtoReq) throws Exception;
}
