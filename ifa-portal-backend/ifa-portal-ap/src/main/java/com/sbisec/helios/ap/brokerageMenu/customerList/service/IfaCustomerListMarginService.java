package com.sbisec.helios.ap.brokerageMenu.customerList.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListMarginA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListMarginA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListMarginA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListMarginA005ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0201_02-01
 * 画面名：顧客一覧・信用
 * @author <author-name>
 *
 * 2024/01/10 新規作成
 */
public interface IfaCustomerListMarginService extends Service {

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaCustomerListMarginA002DtoRequest
     * Dto レスポンス：IfaCustomerListMarginA002DtoResponse
     * model リクエスト：IfaCustomerListMarginA002RequestModel
     * model レスポンス：IfaCustomerListMarginA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerListMarginA002ResponseDto> displayA002(IfaCustomerListMarginA002RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：CSV出力
     * Dto リクエスト：IfaCustomerListMarginA005DtoRequest
     * Dto レスポンス：IfaCustomerListMarginA005DtoResponse
     * model リクエスト：IfaCustomerListMarginA005RequestModel
     * model レスポンス：IfaCustomerListMarginA005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerListMarginA005ResponseDto> csvOutputA005(IfaCustomerListMarginA005RequestDto dtoReq, String sessionId)
            throws Exception;
}
