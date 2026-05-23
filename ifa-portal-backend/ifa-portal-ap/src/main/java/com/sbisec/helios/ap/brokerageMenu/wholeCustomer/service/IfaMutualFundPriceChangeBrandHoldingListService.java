package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingListA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingListA004aResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020301_03-01
 * 画面名：投信基準価額変動の銘柄保有一覧
 * @author <author-name>
 *
 * 2024/04/12 新規作成
 */
public interface IfaMutualFundPriceChangeBrandHoldingListService extends Service {
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaMutualFundPriceChangeBrandHoldingListA002DtoRequest
     * Dto レスポンス：IfaMutualFundPriceChangeBrandHoldingListA002DtoResponse
     * model リクエスト：IfaMutualFundPriceChangeBrandHoldingListA002RequestModel
     * model レスポンス：IfaMutualFundPriceChangeBrandHoldingListA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMutualFundPriceChangeBrandHoldingListA002ResponseDto> displayA002(
            IfaMutualFundPriceChangeBrandHoldingListA002RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaMutualFundPriceChangeBrandHoldingListA004DtoRequest
     * Dto レスポンス：IfaMutualFundPriceChangeBrandHoldingListA004DtoResponse
     * model リクエスト：IfaMutualFundPriceChangeBrandHoldingListA004RequestModel
     * model レスポンス：IfaMutualFundPriceChangeBrandHoldingListA004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMutualFundPriceChangeBrandHoldingListA004aResponseDto> csvOutputA004a(
            IfaMutualFundPriceChangeBrandHoldingListA004aRequestDto dtoReq, String fwSessionId) throws Exception;
    
}
