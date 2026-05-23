package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderConfirmA001bResponseDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderConfirmA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0212-01_2
 * 画面名：信用新規注文確認
 * @author <author-name>
 *
 * 2023/10/13 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0212-01_2", screenNumber = "")
public class IfaMarginNewOrderConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaMarginNewOrderConfirmOrderPlacementA001
     * アクションID：A001
     * アクション名：注文発注
     * APIリクエスト：brokerageMenu.customerMenuA001ApiRequest
     * Apiレスポンス：brokerageMenu.customerMenuA001ApiResponse
     * Dtoリクエスト：brokerageMenu.customerMenuA001DtoRequest
     * Dtoレスポンス：brokerageMenu.customerMenuA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMarginNewOrderConfirmOrderPlacementA001")
    public String orderPlacementA001(@RequestBody IfaMarginNewOrderConfirmA001ApiRequest apiReq) throws Exception {
        
        IfaMarginNewOrderConfirmA001aRequestDto appReqa = new IfaMarginNewOrderConfirmA001aRequestDto();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqa, apiReq);
        
        DataList<IfaMarginNewOrderConfirmA001aResponseDto> appResa = ApiRequestUtil.invoke(
                "cmpIfaMarginNewOrderConfirmService", "orderPlacementA001a",
                new TypeReference<DataList<IfaMarginNewOrderConfirmA001aResponseDto>>() {
                }, appReqa);
        
        DataList<IfaMarginNewOrderConfirmA001ApiResponse> apiRes = new DataList<IfaMarginNewOrderConfirmA001ApiResponse>();
        if (appResa.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            // エラーの場合は返却
            BeanUtils.copyProperties(apiRes, appResa);
            return jc.toString(apiRes);
        }
        
        IfaMarginNewOrderConfirmA001bRequestDto appReqb = new IfaMarginNewOrderConfirmA001bRequestDto();
        BeanUtils.copyProperties(appReqb, apiReq);
        appReqb.setIfaOrderNo(appResa.getDataList().get(0).getIfaOrderNo());
        DataList<IfaMarginNewOrderConfirmA001bResponseDto> appResb = ApiRequestUtil.invoke(
                "cmpIfaMarginNewOrderConfirmService", "orderPlacementA001b",
                new TypeReference<DataList<IfaMarginNewOrderConfirmA001bResponseDto>>() {
                }, appReqb);
        
        BeanUtils.copyProperties(apiRes, appResb);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
