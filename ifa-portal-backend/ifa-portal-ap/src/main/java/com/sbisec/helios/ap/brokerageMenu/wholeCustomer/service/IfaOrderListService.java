package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA005aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA005aResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020302_0101-01
 * 画面名：注文一覧
 * @author <author-name>
 *
 * 2024/03/30 新規作成
 */
public interface IfaOrderListService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaOrderListA001RequestDto
     * Dto レスポンス：IfaOrderListA001ResponseDto
     * model リクエスト：IfaOrderListA001RequestModel
     * model レスポンス：IfaOrderListA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaOrderListA001ResponseDto> initializeA001(IfaOrderListA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaOrderListA002RequestDto
     * Dto レスポンス：IfaOrderListA002ResponseDto
     * model リクエスト：IfaOrderListA002RequestModel
     * model レスポンス：IfaOrderListA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaOrderListA002ResponseDto> displayA002(IfaOrderListA002RequestDto dtoReq)
            throws Exception;
    
    
    /**
     * アクションID：A005
     * アクション名：CSV出力
     * Dto リクエスト：IfaOrderListA005RequestDto
     * Dto レスポンス：IfaOrderListA005ResponseDto
     * model リクエスト：IfaOrderListA005RequestModel
     * model レスポンス：IfaOrderListA005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaOrderListA005aResponseDto> csvOutputA005(IfaOrderListA005aRequestDto dtoReq, String sessionId)
            throws Exception;

}
