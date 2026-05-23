package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA016RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA016ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0208-01_1
 * 画面名：国内株式注文入力
 *
 * @author SCSK
 */
public interface IfaDomesticStockOrderInputService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDomesticStockOrderInputA001RequestDto
     * Dto レスポンス：IfaDomesticStockOrderInputA001ResponseDto
     * model リクエスト：IfaDomesticStockOrderInputA001RequestModel
     * model レスポンス：IfaDomesticStockOrderInputA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaDomesticStockOrderInputA001ResponseDto> initializeA001(
            IfaDomesticStockOrderInputA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：銘柄選択、市場選択
     * Dto リクエスト：IfaDomesticStockOrderInputA002RequestDto
     * Dto レスポンス：IfaDomesticStockOrderInputA002ResponseDto
     * model リクエスト：IfaDomesticStockOrderInputA002RequestModel
     * model レスポンス：IfaDomesticStockOrderInputA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaDomesticStockOrderInputA002ResponseDto> brandSelectionMarketSelectionA002(
            IfaDomesticStockOrderInputA002RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：更新
     * Dto リクエスト：IfaDomesticStockOrderInputA005RequestDto
     * Dto レスポンス：IfaDomesticStockOrderInputA005ResponseDto
     * model リクエスト：IfaDomesticStockOrderInputA005RequestModel
     * model レスポンス：IfaDomesticStockOrderInputA005ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaDomesticStockOrderInputA005ResponseDto> updateA005(
            IfaDomesticStockOrderInputA005RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A016
     * アクション名：注文確認
     * Dto リクエスト：IfaDomesticStockOrderInputA016RequestDto
     * Dto レスポンス：IfaDomesticStockOrderInputA016ResponseDto
     * model リクエスト：IfaDomesticStockOrderInputA016RequestModel
     * model レスポンス：IfaDomesticStockOrderInputA016ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaDomesticStockOrderInputA016ResponseDto> orderConfirmA016(
            IfaDomesticStockOrderInputA016RequestDto dtoReq) throws Exception;
    
}
