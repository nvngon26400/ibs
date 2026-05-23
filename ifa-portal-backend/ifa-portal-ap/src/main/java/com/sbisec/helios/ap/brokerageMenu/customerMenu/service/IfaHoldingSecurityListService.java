package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA012RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA013RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA012ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA013ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA019RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA019ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010201-01
 * 画面名：保有商品一覧
 *
 * @author SCSK
 *
 *     2023/09/21 新規作成
 */
public interface IfaHoldingSecurityListService extends Service {
    
    
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaHoldingSecurityListA001DtoRequest
     * Dto レスポンス：IfaHoldingSecurityListA001DtoResponse
     * model リクエスト：IfaHoldingSecurityListA001RequestModel
     * model レスポンス：IfaHoldingSecurityListA001ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaHoldingSecurityListA001ResponseDto> initializeA001(IfaHoldingSecurityListA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：国内株式売買
     * Dto リクエスト：IfaHoldingSecurityListA003RequestDto
     * Dto レスポンス：IfaHoldingSecurityListA003ResponseDto
     * model リクエスト：IfaHoldingSecurityListSql007RequestModel
     * model レスポンス：IfaHoldingSecurityListSql007ResponseModel
     *
     * @param dtoReq
     * @return レスポンス
     * @throws Exception
     */
    public DataList<?> domesticStockTradingA003(IfaHoldingSecurityListA003RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A012
     * アクション名：口座選択
     * Dto リクエスト：IfaHoldingSecurityListA012DtoRequest
     * Dto レスポンス：IfaHoldingSecurityListA012DtoResponse
     * model リクエスト：IfaHoldingSecurityListA012RequestModel
     * model レスポンス：IfaHoldingSecurityListA012ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exception Exception 口座選択処理で例外が発生した場合
     */
    public DataList<IfaHoldingSecurityListA012ResponseDto> accountSelectionA012(
            IfaHoldingSecurityListA012RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A013
     * アクション名：商品選択
     * Dto リクエスト：IfaHoldingSecurityListA013DtoRequest
     * Dto レスポンス：IfaHoldingSecurityListA013DtoResponse
     * model リクエスト：IfaHoldingSecurityListA013RequestModel
     * model レスポンス：IfaHoldingSecurityListA013ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exception Exception 商品選択処理で例外が発生した場合
     */
    public DataList<IfaHoldingSecurityListA013ResponseDto> productSelectionA013(
            IfaHoldingSecurityListA013RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A019
     * アクション名：投信積立設定
     * Dto リクエスト：IfaHoldingSecurityListA019DtoRequest
     * Dto レスポンス：IfaHoldingSecurityListA019DtoResponse
     * model リクエスト：IfaHoldingSecurityListA019RequestModel
     * model レスポンス：IfaHoldingSecurityListA019ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @throws Exception 選択された投信銘柄の目論見書未閲覧した場合
     */
    public DataList<IfaHoldingSecurityListA019ResponseDto> fundAccumulationA019(
            IfaHoldingSecurityListA019RequestDto dtoReq) throws Exception;
}
