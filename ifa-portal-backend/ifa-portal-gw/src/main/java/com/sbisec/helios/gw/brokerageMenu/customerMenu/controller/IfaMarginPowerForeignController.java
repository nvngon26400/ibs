package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerForeignA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerForeignA001DtoResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginPowerForeignA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginPowerForeignA001ApiResponse;

/**
 * 画面ID：SUB0202_010304-01
 * 画面名：信用余力(米株)
 * 
 * @author SCSK 矢口
 * 2023/12/1 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010304-01", screenNumber = "")
public class IfaMarginPowerForeignController extends BaseController{

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginPowerForeignInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaMarginPowerForeignA001ApiRequest
     * Apiレスポンス：IfaMarginPowerForeignA001ApiResponse
     * Dtoリクエスト：IfaMarginPowerForeignA001DtoRequest
     * Dtoレスポンス：IfaMarginPowerForeignA001DtoResponse
     * 
     * @param apiReq {@code IfaMarginPowerForeignA001ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    
    @PostMapping("/brokerageMenu/customerMenu/ifaMarginPowerForeignInitializeA001")
    public String initializeA001(@RequestBody IfaMarginPowerForeignA001ApiRequest apiReq) throws Exception {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaMarginPowerForeignA001DtoRequest appReq = new IfaMarginPowerForeignA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaMarginPowerForeignA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginPowerForeignService", "initializeA001",
                new TypeReference<DataList<IfaMarginPowerForeignA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaMarginPowerForeignA001ApiResponse> apiRes = new DataList<IfaMarginPowerForeignA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return "/test";
    }
}

