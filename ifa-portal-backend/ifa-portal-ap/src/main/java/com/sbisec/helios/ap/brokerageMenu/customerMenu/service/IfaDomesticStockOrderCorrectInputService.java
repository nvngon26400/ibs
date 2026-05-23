package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA004DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA010DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA010DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0208-03
 * 画面名：国内株式注文訂正入力
 * アクションID：A001
 * アクション名：初期化
 * 2024/01/11 新規作成
 *
 * @author 齋藤
 */
public interface IfaDomesticStockOrderCorrectInputService extends Service {

    /**
     * Dto リクエスト：IfaDomesticStockOrderCorrectInputA001DtoRequest
     * Dto レスポンス：IfaDomesticStockOrderCorrectInputA001DtoResponse
     *
     * @param dtoReq リクエストパラメータ
     * @return 画面の初期化に必要な情報
     * @exception Exception システエラー
     */
    public DataList<IfaDomesticStockOrderCorrectInputA001DtoResponse> initializeA001(IfaDomesticStockOrderCorrectInputA001DtoRequest dtoReq)
            throws Exception;
    
    /**
     * Dto リクエスト：IfaDomesticStockOrderCorrectInputA004DtoRequest
     * Dto レスポンス：IfaDomesticStockOrderCorrectInputA004DtoResponse
     *
     * @param dtoReq リクエストパラメータ
     * @return 画面の更新に必要な情報
     * @exception Exception システエラー
     */
    public DataList<IfaDomesticStockOrderCorrectInputA004DtoResponse> updateA004(IfaDomesticStockOrderCorrectInputA004DtoRequest dtoReq)
            throws Exception;
    
    /**
     * 
     * Dto リクエスト：IfaDomesticStockOrderCorrectInputA010DtoRequest
     * Dto レスポンス：IfaDomesticStockOrderCorrectInputA010DtoResponse
     *
     * @param dtoReq リクエストパラメータ
     * @return 国内株式注文訂正確認画面の初期化に必要な情報
     * @exception Exception システエラー
     */
    public DataList<IfaDomesticStockOrderCorrectInputA010DtoResponse> orderConfirmA010(IfaDomesticStockOrderCorrectInputA010DtoRequest dtoReq)
            throws Exception;

}
