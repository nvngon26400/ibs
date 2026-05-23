package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderConfirmA001bResponseDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderConfirmA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0208-01_3
 * 画面名：国内株式注文確認
 *
 * @author
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0208-01_3", screenNumber = "")
public class IfaDomesticStockOrderConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticStockOrderConfirmOrderPlacementA001
     * アクションID：A001
     * アクション名：注文発注
     * APIリクエスト：IfaDomesticStockOrderConfirmA001ApiRequest
     * Apiレスポンス：IfaDomesticStockOrderConfirmA001ApiResponse
     * Dtoリクエスト：IfaDomesticStockOrderConfirmA001DtoRequest
     * Dtoレスポンス：IfaDomesticStockOrderConfirmA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return レスポンス
     * @throws Exception 注文発注の際、例外が発生した場合
     */
    @RequestMapping(value = "/brokerageMenu/customerMenu/ifaDomesticStockOrderConfirmOrderPlacementA001", method = {
            RequestMethod.POST })
    public String orderPlacementA001(@RequestBody IfaDomesticStockOrderConfirmA001ApiRequest apiReq) throws Exception {
        
        IfaDomesticStockOrderConfirmA001aRequestDto appReqa = new IfaDomesticStockOrderConfirmA001aRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqa, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaDomesticStockOrderConfirmA001aResponseDto> appResa = ApiRequestUtil.invoke(
                "cmpIfaDomesticStockOrderConfirmService", "orderPlacementA001a",
                new TypeReference<DataList<IfaDomesticStockOrderConfirmA001aResponseDto>>() {
                }, appReqa);
        
        DataList<IfaDomesticStockOrderConfirmA001ApiResponse> apiRes = new DataList<IfaDomesticStockOrderConfirmA001ApiResponse>();
        if (appResa.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            // エラーの場合は返却
            BeanUtils.copyProperties(apiRes, appResa);
            return jc.toString(apiRes);
        }
        
        IfaDomesticStockOrderConfirmA001bRequestDto appReqb = new IfaDomesticStockOrderConfirmA001bRequestDto();
        appReqb.setIfaOrderNo(appResa.getDataList().get(0).getIfaOrderNo());
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqb, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaDomesticStockOrderConfirmA001bResponseDto> appResb = ApiRequestUtil.invoke(
                "cmpIfaDomesticStockOrderConfirmService", "orderPlacementA001b",
                new TypeReference<DataList<IfaDomesticStockOrderConfirmA001bResponseDto>>() {
                }, appReqb);
        
        if (appResb.getDataList().size() != 0) {
            appResb.getDataList().get(0).setRequestContents(appReqa);
        }
        
        BeanUtils.copyProperties(apiRes, appResb);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
