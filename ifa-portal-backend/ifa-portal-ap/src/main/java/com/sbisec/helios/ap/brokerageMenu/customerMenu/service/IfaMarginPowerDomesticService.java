package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerDomesticA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerDomesticA001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010302-01
 * 画面名：信用余力（国内）
 * アクションID：A001
 * アクション名：初期化
 * @author <author-name>
 *
 * 2023/08/14 新規作成
 */
public interface IfaMarginPowerDomesticService extends Service {

    /**
     * 
     * Dto リクエスト：IfaMarginPowerDomesticA001DtoRequest
     * Dto レスポンス：IfaMarginPowerDomesticA001DtoResponse
     * model リクエスト：IfaMarginPowerDomesticA001RequestModel
     * model レスポンス：IfaMarginPowerDomesticA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginPowerDomesticA001DtoResponse> initializeA001(IfaMarginPowerDomesticA001DtoRequest dtoReq)
            throws Exception;

}
