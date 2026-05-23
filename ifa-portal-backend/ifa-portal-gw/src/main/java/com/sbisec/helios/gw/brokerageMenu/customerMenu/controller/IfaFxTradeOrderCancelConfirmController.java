package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA002aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA002aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA002bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA002bDtoResponse;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxTradeOrderCancelConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxTradeOrderCancelConfirmA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxTradeOrderCancelConfirmA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxTradeOrderCancelConfirmA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0501-02_1
 * 画面名：為替取引注文取消確認
 * @author 鄒
 *
 * 2023/11/20 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0501-02_1", screenNumber = "", ignoreCheck = true)
public class IfaFxTradeOrderCancelConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaFxTradeOrderCancelConfirmInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaFxTradeOrderCancelConfirmA001ApiRequest
     * Apiレスポンス：IfaFxTradeOrderCancelConfirmA001ApiResponse
     * Dtoリクエスト：IfaFxTradeOrderCancelConfirmA001DtoRequest
     * Dtoレスポンス：IfaFxTradeOrderCancelConfirmA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaFxTradeOrderCancelConfirmInitializeA001")
    public String initializeA001(@RequestBody IfaFxTradeOrderCancelConfirmA001ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaFxTradeOrderCancelConfirmA001DtoRequest appReq = new IfaFxTradeOrderCancelConfirmA001DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaFxTradeOrderCancelConfirmA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaFxTradeOrderCancelConfirmService", "initializeA001",
                new TypeReference<DataList<IfaFxTradeOrderCancelConfirmA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaFxTradeOrderCancelConfirmA001ApiResponse> apiRes = new DataList<IfaFxTradeOrderCancelConfirmA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaFxTradeOrderCancelConfirmOrderCancellationA002
     * アクションID：A002
     * アクション名：注文取消
     * APIリクエスト：IfaFxTradeOrderCancelConfirmA002ApiRequest
     * Apiレスポンス：IfaFxTradeOrderCancelConfirmA002ApiResponse
     * Dtoリクエスト：IfaFxTradeOrderCancelConfirmA002DtoRequest
     * Dtoレスポンス：IfaFxTradeOrderCancelConfirmA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaFxTradeOrderCancelConfirmOrderCancellationA002")
    public String orderCancellationA002(@RequestBody IfaFxTradeOrderCancelConfirmA002ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaFxTradeOrderCancelConfirmA002aDtoRequest appReqa = new IfaFxTradeOrderCancelConfirmA002aDtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqa, apiReq);
        
        DataList<IfaFxTradeOrderCancelConfirmA002aDtoResponse> appResa = ApiRequestUtil.invoke(
                "cmpIfaFxTradeOrderCancelConfirmService", "orderCancellationA002a",
                new TypeReference<DataList<IfaFxTradeOrderCancelConfirmA002aDtoResponse>>() {
                }, appReqa);
        
        DataList<IfaFxTradeOrderCancelConfirmA002aDtoResponse> apiRes = new DataList<IfaFxTradeOrderCancelConfirmA002aDtoResponse>();
        
        if (appResa.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            // エラーの場合は返却
            BeanUtils.copyProperties(apiRes, appResa);
            return jc.toString(apiRes);
        }
        
        IfaFxTradeOrderCancelConfirmA002bDtoRequest appReqb = new IfaFxTradeOrderCancelConfirmA002bDtoRequest();
        BeanUtils.copyProperties(appReqb, apiReq);
        appReqb.setIfaOrderNo(appResa.getDataList().get(0).getIfaOrderNo());
        appReqb.setIfaOrderSubNo(appResa.getDataList().get(0).getIfaOrderSubNo());
        DataList<IfaFxTradeOrderCancelConfirmA002bDtoResponse> appResb = ApiRequestUtil.invoke(
                "cmpIfaFxTradeOrderCancelConfirmService", "orderCancellationA002b",
                new TypeReference<DataList<IfaFxTradeOrderCancelConfirmA002bDtoResponse>>() {
                }, appReqb);
        
        DataList<IfaFxTradeOrderCancelConfirmA002ApiResponse> apiResb = new DataList<IfaFxTradeOrderCancelConfirmA002ApiResponse>();
        
        BeanUtils.copyProperties(apiResb, appResb);
        
        return jc.toString(apiResb);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
