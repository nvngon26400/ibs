package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderConfirmA001aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderConfirmA001aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderConfirmA001bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderConfirmA001bDtoResponse;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxOrderConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxOrderConfirmA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0502-02_2
 * 画面名：為替注文確認
 * @author 松田
 *
 * 2023/09/25 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0502-02_2", screenNumber = "")
public class IfaFxOrderConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaFxOrderInputOrderConfirmationMarginA001
     * アクションID：A001
     * アクション名：注文発注
     * APIリクエスト：IfaFxOrderConfirmA001ApiRequest
     * Apiレスポンス：IfaFxOrderConfirmA001ApiResponse
     * Dtoリクエスト：IfaFxOrderConfirmA001DtoRequest
     * Dtoレスポンス：IfaFxOrderConfirmA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaFxOrderConfirmOrderPlacementA001")
    public String orderPlacementA001(@RequestBody IfaFxOrderConfirmA001ApiRequest apiReq) throws Exception {
        
        IfaFxOrderConfirmA001aDtoRequest appReqa = new IfaFxOrderConfirmA001aDtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqa, apiReq);
        
        DataList<IfaFxOrderConfirmA001aDtoResponse> appResa = ApiRequestUtil.invoke("cmpIfaFxOrderConfirmService",
                "orderPlacementA001a", new TypeReference<DataList<IfaFxOrderConfirmA001aDtoResponse>>() {
                }, appReqa);
        DataList<IfaFxOrderConfirmA001ApiResponse> apiRes = new DataList<IfaFxOrderConfirmA001ApiResponse>();
        if (appResa.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            // エラーの場合は返却
            BeanUtils.copyProperties(apiRes, appResa);
            return jc.toString(apiRes);
        }
        IfaFxOrderConfirmA001bDtoRequest appReqb = new IfaFxOrderConfirmA001bDtoRequest();
        BeanUtils.copyProperties(appReqb, apiReq);
        appReqb.setIfaOrderNo(appResa.getDataList().get(0).getIfaOrderNo());
        DataList<IfaFxOrderConfirmA001bDtoResponse> appResb = ApiRequestUtil.invoke("cmpIfaFxOrderConfirmService",
                "orderPlacementA001b", new TypeReference<DataList<IfaFxOrderConfirmA001bDtoResponse>>() {
                }, appReqb);
        
        BeanUtils.copyProperties(apiRes, appResb);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
