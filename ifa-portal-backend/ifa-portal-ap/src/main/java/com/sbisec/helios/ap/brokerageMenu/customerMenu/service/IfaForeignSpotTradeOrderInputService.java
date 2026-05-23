package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA013RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA013ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0301-01_1
 * 画面名：外国現物取引注文入力
 * 2024/02/07 新規作成
 *
 * @author SCSK 宇田川達弥
 */
public interface IfaForeignSpotTradeOrderInputService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignSpotTradeOrderInputA001RequestDto
     * Dto レスポンス：IfaForeignSpotTradeOrderInputA001ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignSpotTradeOrderInputA001ResponseDto> initializeA001(
            IfaForeignSpotTradeOrderInputA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：株価表示
     * Dto リクエスト：IfaForeignSpotTradeOrderInputA003RequestDto
     * Dto レスポンス：IfaForeignSpotTradeOrderInputA003ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignSpotTradeOrderInputA003ResponseDto> stockPriceDisplayA003(
            IfaForeignSpotTradeOrderInputA003RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：更新
     * Dto リクエスト：IfaForeignSpotTradeOrderInputA005RequestDto
     * Dto レスポンス：IfaForeignSpotTradeOrderInputA005ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignSpotTradeOrderInputA005ResponseDto> updateA005(
            IfaForeignSpotTradeOrderInputA005RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A013
     * アクション名：注文確認
     * Dto リクエスト：IfaForeignSpotTradeOrderInputA013RequestDto
     * Dto レスポンス：IfaForeignSpotTradeOrderInputA013ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignSpotTradeOrderInputA013ResponseDto> orderConfirmA013(
            IfaForeignSpotTradeOrderInputA013RequestDto dtoReq) throws Exception;
    
}
