package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA012RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA012ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA019RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA019ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA020RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA020ResponseDto;

import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0303-01_1
 * 画面名：米株信用取引新規注文入力
 *
 * @author SCSK池田
 *
    2023/10/24 新規作成
 */
public interface IfaForeignMarginTradeNewOrderInputService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignMarginTradeNewOrderInputA001DtoRequest
     * Dto レスポンス：IfaForeignMarginTradeNewOrderInputA001DtoResponse
     * model リクエスト：IfaForeignMarginTradeNewOrderInputA001RequestModel
     * model レスポンス：IfaForeignMarginTradeNewOrderInputA001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    public DataList<IfaForeignMarginTradeNewOrderInputA001ResponseDto> initializeA001(
            IfaForeignMarginTradeNewOrderInputA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：株価表示
     * Dto リクエスト：IfaForeignMarginTradeNewOrderInputA003DtoRequest
     * Dto レスポンス：IfaForeignMarginTradeNewOrderInputA003DtoResponse
     * model リクエスト：IfaForeignMarginTradeNewOrderInputA003RequestModel
     * model レスポンス：IfaForeignMarginTradeNewOrderInputA003ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    public DataList<IfaForeignMarginTradeNewOrderInputA003ResponseDto> stockPriceDisplayA003(
            IfaForeignMarginTradeNewOrderInputA003RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：更新
     * Dto リクエスト：IfaForeignMarginTradeNewOrderInputA005DtoRequest
     * Dto レスポンス：IfaForeignMarginTradeNewOrderInputA005DtoResponse
     * model リクエスト：IfaForeignMarginTradeNewOrderInputA005RequestModel
     * model レスポンス：IfaForeignMarginTradeNewOrderInputA005ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    public DataList<IfaForeignMarginTradeNewOrderInputA005ResponseDto> updateA005(
            IfaForeignMarginTradeNewOrderInputA005RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A012
     * アクション名：注文確認
     * Dto リクエスト：IfaForeignMarginTradeNewOrderInputA012DtoRequest
     * Dto レスポンス：IfaForeignMarginTradeNewOrderInputA012DtoResponse
     * model リクエスト：IfaForeignMarginTradeNewOrderInputA012RequestModel
     * model レスポンス：IfaForeignMarginTradeNewOrderInputA012ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    public DataList<IfaForeignMarginTradeNewOrderInputA012ResponseDto> orderConfirmA012(
            IfaForeignMarginTradeNewOrderInputA012RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A019
     * アクション名：表示する（参考信用建余力）
     * Dto リクエスト：IfaForeignMarginTradeNewOrderInputA019DtoRequest
     * Dto レスポンス：IfaForeignMarginTradeNewOrderInputA019DtoResponse
     * model リクエスト：IfaForeignMarginTradeNewOrderInputA019RequestModel
     * model レスポンス：IfaForeignMarginTradeNewOrderInputA019ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    public DataList<IfaForeignMarginTradeNewOrderInputA019ResponseDto> displayReferenceMarginBalanceA019(
            IfaForeignMarginTradeNewOrderInputA019RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A020
     * アクション名：概算建代金
     * Dto リクエスト：IfaForeignMarginTradeNewOrderInputA020DtoRequest
     * Dto レスポンス：IfaForeignMarginTradeNewOrderInputA020DtoResponse
     * model リクエスト：IfaForeignMarginTradeNewOrderInputA020RequestModel
     * model レスポンス：IfaForeignMarginTradeNewOrderInputA020ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    public DataList<IfaForeignMarginTradeNewOrderInputA020ResponseDto> estimatedConstructionFeeA020(
            IfaForeignMarginTradeNewOrderInputA020RequestDto dtoReq) throws Exception;
    
}
