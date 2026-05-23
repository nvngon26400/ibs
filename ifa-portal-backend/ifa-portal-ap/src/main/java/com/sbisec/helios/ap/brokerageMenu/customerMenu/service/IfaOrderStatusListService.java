package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListA004DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListX001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListX001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0104-01
 * 画面名：注文状況一覧
 *
 * @author 齋藤
 *
 *          2023/10/16 新規作成
 */
public interface IfaOrderStatusListService extends Service {
    
    /**
     * アクションID：X001
     * アクション名：初期化
     * Dto リクエスト：brokerageMenu.customerMenuX001DtoRequest
     * Dto レスポンス：brokerageMenu.customerMenuX001DtoResponse
     * model リクエスト：brokerageMenu.customerMenuX001RequestModel
     * model レスポンス：brokerageMenu.customerMenuX001ResponseModel
     *
     * @param dtoReq IfaOrderStatusListX001DtoRequest
     * @return IfaOrderStatusListX001DtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaOrderStatusListX001DtoResponse> initializeX001(IfaOrderStatusListX001DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：初期化（注文一覧）
     * Dto リクエスト：brokerageMenu.customerMenuA002DtoRequest
     * Dto レスポンス：brokerageMenu.customerMenuA002DtoResponse
     * model リクエスト：brokerageMenu.customerMenuA002RequestModel
     * model レスポンス：brokerageMenu.customerMenuA002ResponseModel
     *
     * @param dtoReq IfaOrderStatusListA002DtoRequest
     * @return IfaOrderStatusListA002DtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaOrderStatusListA002DtoResponse> initializeOrderListA002(IfaOrderStatusListA002DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：外国株式店頭詳細表示
     * Dto リクエスト：brokerageMenu.customerMenuA004DtoRequest
     * Dto レスポンス：brokerageMenu.customerMenuA004DtoResponse
     * model リクエスト：brokerageMenu.customerMenuA004RequestModel
     * model レスポンス：brokerageMenu.customerMenuA004ResponseModel
     *
     * @param dtoReq IfaOrderStatusListA004DtoRequest
     * @return IfaOrderStatusListA004DtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaOrderStatusListA004DtoResponse> foreignStockCounterDetailDisplayA004(
            IfaOrderStatusListA004DtoRequest dtoReq) throws Exception;
    
}
