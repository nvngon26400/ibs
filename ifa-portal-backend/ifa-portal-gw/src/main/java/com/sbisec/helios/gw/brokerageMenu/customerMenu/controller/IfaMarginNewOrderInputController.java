package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA005DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA005DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA016DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA016DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderInputA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderInputA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderInputA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderInputA005ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderInputA016ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderInputA016ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0212-01_1
 * 画面名：信用新規注文入力
 * @author <author-name>
 *
 * 2023/08/17 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0212-01_1", screenNumber = "")
public class IfaMarginNewOrderInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaMarginNewOrderInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaMarginNewOrderInputA001ApiRequest
     * Apiレスポンス：IfaMarginNewOrderInputA001ApiResponse
     * Dtoリクエスト：IfaMarginNewOrderInputA001DtoRequest
     * Dtoレスポンス：IfaMarginNewOrderInputA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMarginNewOrderInputInitializeA001")
    public String initializeA001(@RequestBody IfaMarginNewOrderInputA001ApiRequest apiReq) throws Exception {
        
        IfaMarginNewOrderInputA001DtoRequest appReq = new IfaMarginNewOrderInputA001DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaMarginNewOrderInputA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginNewOrderInputService", "initializeA001",
                new TypeReference<DataList<IfaMarginNewOrderInputA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaMarginNewOrderInputA001ApiResponse> apiRes = new DataList<IfaMarginNewOrderInputA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaMarginNewOrderInputBrandSelectionMarketSelectionA002
     * アクションID：A002
     * アクション名：銘柄選択、市場選択
     * APIリクエスト：IfaMarginNewOrderInputA002ApiRequest
     * Apiレスポンス：IfaMarginNewOrderInputA002ApiResponse
     * Dtoリクエスト：IfaMarginNewOrderInputA002DtoRequest
     * Dtoレスポンス：IfaMarginNewOrderInputA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMarginNewOrderInputBrandSelectionMarketSelectionA002")
    public String brandSelectionMarketSelectionA002(@RequestBody IfaMarginNewOrderInputA002ApiRequest apiReq)
            throws Exception {
        
        IfaMarginNewOrderInputA002DtoRequest appReq = new IfaMarginNewOrderInputA002DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaMarginNewOrderInputA002DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginNewOrderInputService", "brandSelectionMarketSelectionA002",
                new TypeReference<DataList<IfaMarginNewOrderInputA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaMarginNewOrderInputA002ApiResponse> apiRes = new DataList<IfaMarginNewOrderInputA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaMarginNewOrderInputUpdateA005
     * アクションID：A005
     * アクション名：更新
     * APIリクエスト：IfaMarginNewOrderInputA005ApiRequest
     * Apiレスポンス：IfaMarginNewOrderInputA005ApiResponse
     * Dtoリクエスト：IfaMarginNewOrderInputA005DtoRequest
     * Dtoレスポンス：IfaMarginNewOrderInputA005DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMarginNewOrderInputUpdateA005")
    public String updateA005(@RequestBody IfaMarginNewOrderInputA005ApiRequest apiReq) throws Exception {
        
        IfaMarginNewOrderInputA005DtoRequest appReq = new IfaMarginNewOrderInputA005DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaMarginNewOrderInputA005DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginNewOrderInputService", "updateA005",
                new TypeReference<DataList<IfaMarginNewOrderInputA005DtoResponse>>() {
                }, appReq);
        
        DataList<IfaMarginNewOrderInputA005ApiResponse> apiRes = new DataList<IfaMarginNewOrderInputA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaMarginNewOrderInputOrderConfirmA016
     * アクションID：A016
     * アクション名：注文確認
     * APIリクエスト：IfaMarginNewOrderInputA016ApiRequest
     * Apiレスポンス：IfaMarginNewOrderInputA016ApiResponse
     * Dtoリクエスト：IfaMarginNewOrderInputA016DtoRequest
     * Dtoレスポンス：IfaMarginNewOrderInputA016DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMarginNewOrderInputOrderConfirmA016")
    public String orderConfirmA016(@RequestBody IfaMarginNewOrderInputA016ApiRequest apiReq) throws Exception {
        
        IfaMarginNewOrderInputA016DtoRequest appReq = new IfaMarginNewOrderInputA016DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaMarginNewOrderInputA016DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginNewOrderInputService", "orderConfirmA016",
                new TypeReference<DataList<IfaMarginNewOrderInputA016DtoResponse>>() {
                }, appReq);
        
        DataList<IfaMarginNewOrderInputA016ApiResponse> apiRes = new DataList<IfaMarginNewOrderInputA016ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
