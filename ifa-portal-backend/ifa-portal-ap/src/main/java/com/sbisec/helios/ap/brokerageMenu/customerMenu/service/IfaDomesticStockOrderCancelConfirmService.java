package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA002aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA002aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA002bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA002bDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0208-04_1
 * 画面名：国内株式注文取消確認
 * 2024/01/10 新規作成
 *
 * @author 卞智ホ
 */
public interface IfaDomesticStockOrderCancelConfirmService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDomesticStockOrderCancelConfirmA001DtoRequest
     * Dto レスポンス：IfaDomesticStockOrderCancelConfirmA001DtoResponse
     * model リクエスト：IfaDomesticStockOrderCancelConfirmA001RequestModel
     * model レスポンス：IfaDomesticStockOrderCancelConfirmA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return 画面に返す情報
     * @exception Exception システムエラー
     */
    public DataList<IfaDomesticStockOrderCancelConfirmA001DtoResponse> initializeA001(
            IfaDomesticStockOrderCancelConfirmA001DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A002a
     * アクション名：注文発注
     * Dto リクエスト：IfaDomesticStockOrderCancelConfirmA002DtoRequest
     * Dto レスポンス：IfaDomesticStockOrderCancelConfirmA002DtoResponse
     * model リクエスト：IfaDomesticStockOrderCancelConfirmA002RequestModel
     * model レスポンス：IfaDomesticStockOrderCancelConfirmA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return 画面に返す情報
     * @exception Exception システムエラー
     */
    public DataList<IfaDomesticStockOrderCancelConfirmA002aDtoResponse> orderPlacementA002a(
            IfaDomesticStockOrderCancelConfirmA002aDtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A002b
     * アクション名：注文発注
     * Dto リクエスト：IfaDomesticStockOrderCancelConfirmA002DtoRequest
     * Dto レスポンス：IfaDomesticStockOrderCancelConfirmA002DtoResponse
     * model リクエスト：IfaDomesticStockOrderCancelConfirmA002RequestModel
     * model レスポンス：IfaDomesticStockOrderCancelConfirmA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return 画面に返す情報
     * @exception Exception システムエラー
     */
    public DataList<IfaDomesticStockOrderCancelConfirmA002bDtoResponse> orderPlacementA002b(
            IfaDomesticStockOrderCancelConfirmA002bDtoRequest dtoReq)
            throws Exception;

}
