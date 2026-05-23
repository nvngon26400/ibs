package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA002aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA002aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA005aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA005aDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020302_0102-01
 * 画面名：国内株当日約定一覧
 * @author <author-name>
 *
 * 2023/11/21 新規作成
 */
public interface IfaTodayTradeListService extends Service {

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaTodayTradeListA002DtoRequest
     * Dto レスポンス：IfaTodayTradeListA002DtoResponse
     * model リクエスト：IfaTodayTradeListA002RequestModel
     * model レスポンス：IfaTodayTradeListA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaTodayTradeListA002aDtoResponse> displayA002a(IfaTodayTradeListA002aDtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：CSV出力
     * Dto リクエスト：IfaTodayTradeListA005DtoRequest
     * Dto レスポンス：IfaTodayTradeListA005DtoResponse
     * model リクエスト：IfaTodayTradeListA005RequestModel
     * model レスポンス：IfaTodayTradeListA005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaTodayTradeListA005aDtoResponse> csvOutputA005(IfaTodayTradeListA005aDtoRequest dtoReq)
            throws Exception;

}
