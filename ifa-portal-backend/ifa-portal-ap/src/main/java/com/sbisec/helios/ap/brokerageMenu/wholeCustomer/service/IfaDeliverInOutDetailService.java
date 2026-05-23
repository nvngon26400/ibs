package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDeliverInOutDetailA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDeliverInOutDetailA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDeliverInOutDetailA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDeliverInOutDetailA004ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020302_0204-01
 * 画面名：入出庫明細
 * @author <author-name>
 *
 * 2024/04/03 新規作成
 */
public interface IfaDeliverInOutDetailService extends Service {
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaDeliverInOutDetailA002DtoRequest
     * Dto レスポンス：IfaDeliverInOutDetailA002DtoResponse
     * model リクエスト：IfaDeliverInOutDetailA002RequestModel
     * model レスポンス：IfaDeliverInOutDetailA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDeliverInOutDetailA002ResponseDto> displayA002(IfaDeliverInOutDetailA002RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaDeliverInOutDetailA004DtoRequest
     * Dto レスポンス：IfaDeliverInOutDetailA004DtoResponse
     * model リクエスト：IfaDeliverInOutDetailA004RequestModel
     * model レスポンス：IfaDeliverInOutDetailA004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDeliverInOutDetailA004ResponseDto> csvOutputA004(IfaDeliverInOutDetailA004RequestDto dtoReq,
            String fwSessionId) throws Exception;
}
