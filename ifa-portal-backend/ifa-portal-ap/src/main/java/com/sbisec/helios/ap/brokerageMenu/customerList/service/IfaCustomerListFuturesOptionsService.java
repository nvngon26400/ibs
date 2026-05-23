package com.sbisec.helios.ap.brokerageMenu.customerList.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListFuturesOptionsA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListFuturesOptionsA005aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListFuturesOptionsCommonRequestDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0201_03-01
 * 画面名：顧客一覧・先OP
 *
 * @author SCSK
 2024/05/29 新規作成
 */
public interface IfaCustomerListFuturesOptionsService extends Service {
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaCustomerListFuturesOptionsA002RequestDto
     * Dto レスポンス：IfaCustomerListFuturesOptionsA002ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCustomerListFuturesOptionsA002ResponseDto> displayA002(
            IfaCustomerListFuturesOptionsCommonRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：CSV出力
     * Dto リクエスト：IfaCustomerListFuturesOptionsA005RequestDto
     * Dto レスポンス：IfaCustomerListFuturesOptionsA005ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCustomerListFuturesOptionsA005aResponseDto> csvOutputA005(
            IfaCustomerListFuturesOptionsCommonRequestDto dtoReq) throws Exception;
    
}
