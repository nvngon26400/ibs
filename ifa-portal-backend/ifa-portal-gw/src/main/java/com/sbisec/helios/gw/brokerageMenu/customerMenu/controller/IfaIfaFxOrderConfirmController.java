package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderConfirmA001aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderConfirmA001aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderConfirmA001bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderConfirmA001bDtoResponse;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaIfaFxOrderConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaIfaFxOrderConfirmA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0503-02_2
 * 画面名：【IFA】為替注文確認
 * @author <author-name>
 *
 * 2023/11/09 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0503-02_2", screenNumber = "175", ignoreCheck = true)
public class IfaIfaFxOrderConfirmController extends BaseController{

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaIfaFxOrderConfirmOrderPlacementA001
     * アクションID：A001
     * アクション名：注文発注
     * APIリクエスト：IfaIfaFxOrderConfirmA001ApiRequest
     * Apiレスポンス：IfaIfaFxOrderConfirmA001ApiResponse
     * Dtoリクエスト：IfaIfaFxOrderConfirmA001DtoRequest
     * Dtoレスポンス：IfaIfaFxOrderConfirmA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaIfaFxOrderConfirmOrderPlacementA001")
    public String orderPlacementA001(@RequestBody IfaIfaFxOrderConfirmA001ApiRequest apiReq)throws Exception
    {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaIfaFxOrderConfirmA001aDtoRequest appReqa = new IfaIfaFxOrderConfirmA001aDtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqa, apiReq);

        DataList<IfaIfaFxOrderConfirmA001aDtoResponse> appResa = ApiRequestUtil.invoke("cmpIfaIfaFxOrderConfirmService",
                "orderPlacementA001a", new TypeReference<DataList<IfaIfaFxOrderConfirmA001aDtoResponse>>() {
                }, appReqa);
        
        DataList<IfaIfaFxOrderConfirmA001ApiResponse> apiRes = new DataList<IfaIfaFxOrderConfirmA001ApiResponse>();
        
        if (appResa.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            // エラーの場合は返却
            BeanUtils.copyProperties(apiRes, appResa);
            return jc.toString(apiRes);
        }
        
        IfaIfaFxOrderConfirmA001bDtoRequest appReqb = new IfaIfaFxOrderConfirmA001bDtoRequest(); 
        BeanUtils.copyProperties(appReqb, apiReq);
        appReqb.setIfaOrderNo(appResa.getDataList().get(0).getIfaOrderNo());
        DataList<IfaIfaFxOrderConfirmA001bDtoResponse> appResb = ApiRequestUtil.invoke("cmpIfaIfaFxOrderConfirmService",
                "orderPlacementA001b", new TypeReference<DataList<IfaIfaFxOrderConfirmA001bDtoResponse>>() {
                }, appReqb);
        
        BeanUtils.copyProperties(apiRes, appResb);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

