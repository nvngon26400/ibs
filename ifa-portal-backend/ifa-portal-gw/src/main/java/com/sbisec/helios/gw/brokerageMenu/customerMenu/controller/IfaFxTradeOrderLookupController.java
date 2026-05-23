package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA003DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA003DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxTradeOrderLookupA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxTradeOrderLookupA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxTradeOrderLookupA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaFxTradeOrderLookupA003ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0501-01
 * 画面名：為替取引注文照会
 * @author scsk
 *
 * 2023/09/12 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0501-01", screenNumber = "", ignoreCheck = true)
public class IfaFxTradeOrderLookupController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaFxTradeOrderLookupInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaFxTradeOrderLookupA001ApiRequest
     * Apiレスポンス：IfaFxTradeOrderLookupA001ApiResponse
     * Dtoリクエスト：IfaFxTradeOrderLookupA001DtoRequest
     * Dtoレスポンス：IfaFxTradeOrderLookupA001DtoResponse
     * @param A001リクエスト
     * @return A001レスポンス
     * @exception 例外処理
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaFxTradeOrderLookupInitializeA001")
    public String ifaFxTradeOrderLookupInitializeA001(@RequestBody IfaFxTradeOrderLookupA001ApiRequest apiReq)
            throws Exception {
        
        IfaFxTradeOrderLookupA001DtoRequest appReq = new IfaFxTradeOrderLookupA001DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaFxTradeOrderLookupA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaFxTradeOrderLookupService",
                "initializeA001", new TypeReference<DataList<IfaFxTradeOrderLookupA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaFxTradeOrderLookupA001ApiResponse> apiRes = new DataList<IfaFxTradeOrderLookupA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaFxTradeOrderLookupDisplayA003
     * アクションID：A003
     * アクション名：表示
     * APIリクエスト：IfaFxTradeOrderLookupA003ApiRequest
     * Apiレスポンス：IfaFxTradeOrderLookupA003ApiResponse
     * Dtoリクエスト：IfaFxTradeOrderLookupA003DtoRequest
     * Dtoレスポンス：IfaFxTradeOrderLookupA003DtoResponse
     * @param A001リクエスト
     * @return A001レスポンス
     * @exception 例外処理
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaFxTradeOrderLookupDisplayA003")
    public String ifaFxTradeOrderLookupDisplayA003(@RequestBody IfaFxTradeOrderLookupA003ApiRequest apiReq)
            throws Exception {
        
        IfaFxTradeOrderLookupA003DtoRequest appReq = new IfaFxTradeOrderLookupA003DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaFxTradeOrderLookupA003DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaFxTradeOrderLookupService",
                "displayA003", new TypeReference<DataList<IfaFxTradeOrderLookupA003DtoResponse>>() {
                }, appReq);
        
        DataList<IfaFxTradeOrderLookupA003ApiResponse> apiRes = new DataList<IfaFxTradeOrderLookupA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
