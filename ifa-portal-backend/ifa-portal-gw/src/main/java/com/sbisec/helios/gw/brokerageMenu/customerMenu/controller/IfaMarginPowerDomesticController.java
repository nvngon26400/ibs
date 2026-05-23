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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerDomesticA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerDomesticA001DtoResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginPowerDomesticA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginPowerDomesticA001ApiResponse;

/**
 * 画面ID：SUB0202_010302-01
 * 画面名：信用余力（国内）
 *
 * @author 島崎 聡士 2023/08/14 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "SUB0202_010302-01", id = "SUB0202_010302-01", screenNumber = "1", ignoreCheck = true)
public class IfaMarginPowerDomesticController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginPowerDomesticInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaMarginPowerDomesticA001ApiRequest
     * Apiレスポンス：IfaMarginPowerDomesticA001ApiResponse
     * Dtoリクエスト：IfaMarginPowerDomesticA001DtoRequest
     * Dtoレスポンス：IfaMarginPowerDomesticA001DtoResponse
     *
     * @param apiReq {@code IfaMarginPowerDomesticA001ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */

    @PostMapping("/brokerageMenu/customerMenu/ifaMarginPowerDomesticInitializeA001")
    public String initializeA001(@RequestBody IfaMarginPowerDomesticA001ApiRequest apiReq)throws Exception {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaMarginPowerDomesticA001DtoRequest appReq = new IfaMarginPowerDomesticA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaMarginPowerDomesticA001DtoResponse> appRes = 
                ApiRequestUtil.invoke("cmpIfaMarginPowerDomesticService",
                "initializeA001", new TypeReference<DataList<IfaMarginPowerDomesticA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaMarginPowerDomesticA001ApiResponse> apiRes = new DataList<IfaMarginPowerDomesticA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

