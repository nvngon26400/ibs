package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerPortalA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerPortalA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerPortalA011RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerPortalA011ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_00-02
 * 画面名：顧客ポータル_顧客情報
 * @author <author-name>
 *
 * 2023/11/30 新規作成
 */
public interface IfaCustomerPortalService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaCustomerPortalA001DtoRequest
     * Dto レスポンス：IfaCustomerPortalA001DtoResponse
     * model リクエスト：IfaCustomerPortalA001RequestModel
     * model レスポンス：IfaCustomerPortalA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerPortalA001ResponseDto> initializeA001(IfaCustomerPortalA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A011
     * アクション名：メモ(IFA専用)更新
     * Dto リクエスト：IfaCustomerPortalA011DtoRequest
     * Dto レスポンス：IfaCustomerPortalA011DtoResponse
     * model リクエスト：IfaCustomerPortalA011RequestModel
     * model レスポンス：IfaCustomerPortalA011ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerPortalA011ResponseDto> memoIFAUpdateA011(IfaCustomerPortalA011RequestDto dtoReq)
            throws Exception;
    
}
