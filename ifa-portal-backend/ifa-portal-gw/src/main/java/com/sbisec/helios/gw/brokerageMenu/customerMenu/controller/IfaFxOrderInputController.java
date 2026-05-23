package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA008DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA008DtoResponse;
//import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA008DtoRequest;
//import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderInputA008DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxOrderInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxOrderInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxOrderInputA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxOrderInputA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxOrderInputA008ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxOrderInputA008ApiResponse;
//import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxOrderInputA008ApiRequest;
//import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxOrderInputA008ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0502-02_1
 * 画面名：為替注文入力
 * @author 松田
 *
 * 2023/09/16 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0502-02_1", screenNumber = "")
public class IfaFxOrderInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaFxOrderInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaFxOrderInputA001ApiRequest
     * Apiレスポンス：IfaFxOrderInputA001ApiResponse
     * Dtoリクエスト：IfaFxOrderInputA001DtoRequest
     * Dtoレスポンス：IfaFxOrderInputA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaFxOrderInputInitializeA001")
    public String initializeA001(@RequestBody IfaFxOrderInputA001ApiRequest apiReq) throws Exception {
        
        IfaFxOrderInputA001DtoRequest appReq = new IfaFxOrderInputA001DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaFxOrderInputA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaFxOrderInputService",
                "initializeA001", new TypeReference<DataList<IfaFxOrderInputA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaFxOrderInputA001ApiResponse> apiRes = new DataList<IfaFxOrderInputA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaFxOrderInputUpdateA002
     * アクションID：A002
     * アクション名：更新
     * APIリクエスト：IfaFxOrderInputA002ApiRequest
     * Apiレスポンス：IfaFxOrderInputA002ApiResponse
     * Dtoリクエスト：IfaFxOrderInputA002DtoRequest
     * Dtoレスポンス：IfaFxOrderInputA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaFxOrderInputUpdateA002")
    public String updateA002(@RequestBody IfaFxOrderInputA002ApiRequest apiReq) throws Exception {
        
        IfaFxOrderInputA002DtoRequest appReq = new IfaFxOrderInputA002DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaFxOrderInputA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaFxOrderInputService",
                "updateA002", new TypeReference<DataList<IfaFxOrderInputA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaFxOrderInputA002ApiResponse> apiRes = new DataList<IfaFxOrderInputA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaFxOrderInputOrderConfirmA008
     * アクションID：A008
     * アクション名：注文確認
     * APIリクエスト：IfaFxOrderInputA008ApiRequest
     * Apiレスポンス：IfaFxOrderInputA008ApiResponse
     * Dtoリクエスト：IfaFxOrderInputA008DtoRequest
     * Dtoレスポンス：IfaFxOrderInputA008DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaFxOrderInputOrderConfirmA008")
    public String orderConfirmationA008(@RequestBody IfaFxOrderInputA008ApiRequest apiReq) throws Exception {
        
        IfaFxOrderInputA008DtoRequest appReq = new IfaFxOrderInputA008DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaFxOrderInputA008DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaFxOrderInputService",
                "orderConfirmationA008", new TypeReference<DataList<IfaFxOrderInputA008DtoResponse>>() {
                }, appReq);
        
        DataList<IfaFxOrderInputA008ApiResponse> apiRes = new DataList<IfaFxOrderInputA008ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
