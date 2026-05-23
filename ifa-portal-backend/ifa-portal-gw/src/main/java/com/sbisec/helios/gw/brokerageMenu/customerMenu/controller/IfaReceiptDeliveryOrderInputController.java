package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputA003DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputA003DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputA006DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputA006DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputX001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputX001DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaReceiptDeliveryOrderInputA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaReceiptDeliveryOrderInputA003ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaReceiptDeliveryOrderInputA006ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaReceiptDeliveryOrderInputA006ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaReceiptDeliveryOrderInputX001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaReceiptDeliveryOrderInputX001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * SUB0202_0212-08_1_現引現渡注文入力
 *
 * @author 池亀緑
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0212-08_1", screenNumber = "", ignoreCheck = true)
public class IfaReceiptDeliveryOrderInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaReceiptDeliveryOrderInputInitializeX001
     * アクションID：X001
     * アクション名：初期化
     * APIリクエスト：IfaReceiptDeliveryOrderInputX001ApiRequest
     * Apiレスポンス：IfaReceiptDeliveryOrderInputX001ApiResponse
     * Dtoリクエスト：IfaReceiptDeliveryOrderInputX001DtoRequest
     * Dtoレスポンス：IfaReceiptDeliveryOrderInputX001DtoResponse
     *
     * @param apiReq {@code IfaReceiptDeliveryOrderInputX001ApiRequest }
     * @return {@code String}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaReceiptDeliveryOrderInputInitializeX001")
    public String initializeX001(@RequestBody IfaReceiptDeliveryOrderInputX001ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaReceiptDeliveryOrderInputX001DtoRequest appReq = new IfaReceiptDeliveryOrderInputX001DtoRequest();
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaReceiptDeliveryOrderInputX001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaReceiptDeliveryOrderInputService", "initializeX001",
                new TypeReference<DataList<IfaReceiptDeliveryOrderInputX001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaReceiptDeliveryOrderInputX001ApiResponse> apiRes = new DataList<IfaReceiptDeliveryOrderInputX001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaReceiptDeliveryOrderInputUpdateA003
     * アクションID：A003
     * アクション名：更新
     * APIリクエスト：IfaReceiptDeliveryOrderInputA003ApiRequest
     * Apiレスポンス：IfaReceiptDeliveryOrderInputA003ApiResponse
     * Dtoリクエスト：IfaReceiptDeliveryOrderInputA003DtoRequest
     * Dtoレスポンス：IfaReceiptDeliveryOrderInputA003DtoResponse
     *
     * @param apiReq {@code IfaReceiptDeliveryOrderInputA003ApiRequest }
     * @return {@code String}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaReceiptDeliveryOrderInputUpdateA003")
    public String updateA003(@RequestBody IfaReceiptDeliveryOrderInputA003ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaReceiptDeliveryOrderInputA003DtoRequest appReq = new IfaReceiptDeliveryOrderInputA003DtoRequest();
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaReceiptDeliveryOrderInputA003DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaReceiptDeliveryOrderInputService", "updateA003",
                new TypeReference<DataList<IfaReceiptDeliveryOrderInputA003DtoResponse>>() {
                }, appReq);
        
        DataList<IfaReceiptDeliveryOrderInputA003ApiResponse> apiRes = new DataList<IfaReceiptDeliveryOrderInputA003ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaReceiptDeliveryOrderInputOrderConfirmA006
     * アクションID：A006
     * アクション名：更新
     * APIリクエスト：IfaReceiptDeliveryOrderInputA006ApiRequest
     * Apiレスポンス：IfaReceiptDeliveryOrderInputA006ApiResponse
     * Dtoリクエスト：IfaReceiptDeliveryOrderInputA006DtoRequest
     * Dtoレスポンス：IfaReceiptDeliveryOrderInputA006DtoResponse
     *
     * @param apiReq {@code IfaReceiptDeliveryOrderInputA006ApiRequest }
     * @return {@code String}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaReceiptDeliveryOrderInputOrderConfirmA006")
    public String orderConfirmA006(@RequestBody IfaReceiptDeliveryOrderInputA006ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaReceiptDeliveryOrderInputA006DtoRequest appReq = new IfaReceiptDeliveryOrderInputA006DtoRequest();
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaReceiptDeliveryOrderInputA006DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaReceiptDeliveryOrderInputService", "orderConfirmA006",
                new TypeReference<DataList<IfaReceiptDeliveryOrderInputA006DtoResponse>>() {
                }, appReq);
        
        DataList<IfaReceiptDeliveryOrderInputA006ApiResponse> apiRes = new DataList<IfaReceiptDeliveryOrderInputA006ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
}
