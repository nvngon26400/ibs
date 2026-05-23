package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA008RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA008ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0302-01
 * 画面名：単価表照会（外国株式銘柄一覧）
 * 2024/03/27 新規作成
 *
 * @author <author-name>
 */
public interface IfaPriceViewLookupForeignStockBrandListService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaPriceViewLookupForeignStockBrandListA001RequestDto
     * Dto レスポンス：IfaPriceViewLookupForeignStockBrandListA001ResponseDto
     * model リクエスト：IfaPriceViewLookupForeignStockBrandListA001RequestModel
     * model レスポンス：IfaPriceViewLookupForeignStockBrandListA001ResponseModel
     *
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaPriceViewLookupForeignStockBrandListA001ResponseDto> initializeA001(
            IfaPriceViewLookupForeignStockBrandListA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaPriceViewLookupForeignStockBrandListA002RequestDto
     * Dto レスポンス：IfaPriceViewLookupForeignStockBrandListA002ResponseDto
     * model リクエスト：IfaPriceViewLookupForeignStockBrandListA002RequestModel
     * model レスポンス：IfaPriceViewLookupForeignStockBrandListA002ResponseModel
     *
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaPriceViewLookupForeignStockBrandListA002ResponseDto> displayA002(
            IfaPriceViewLookupForeignStockBrandListA002RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A008
     * アクション名：戻るボタン再表示
     * Dto リクエスト：IfaPriceViewLookupForeignStockBrandListA008RequestDto
     * Dto レスポンス：IfaPriceViewLookupForeignStockBrandListA008ResponseDto
     * model リクエスト：IfaPriceViewLookupForeignStockBrandListA008RequestModel
     * model レスポンス：IfaPriceViewLookupForeignStockBrandListA008ResponseModel
     *
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaPriceViewLookupForeignStockBrandListA008ResponseDto> backButtonRedisplayA008(
            IfaPriceViewLookupForeignStockBrandListA008RequestDto dtoReq) throws Exception;
    
}
