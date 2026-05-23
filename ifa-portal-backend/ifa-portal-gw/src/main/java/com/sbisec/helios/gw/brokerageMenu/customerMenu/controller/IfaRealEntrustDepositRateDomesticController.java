package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateDomesticX001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateDomesticX001DtoResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaRealEntrustDepositRateDomesticX001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaRealEntrustDepositRateDomesticX001ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateDomesticA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateDomesticA002DtoResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaRealEntrustDepositRateDomesticA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaRealEntrustDepositRateDomesticA002ApiResponse;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_010302-03
 * 画面名：リアル委託保証金率（国内）
 
 * SCSK
 * 2024/07/29 新規作成
 */

@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010302-03", screenNumber = "")
public class IfaRealEntrustDepositRateDomesticController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    /**

     * アクセス：/brokerageMenu/customerMenu/ifaRealEntrustDepositRateDomesticInitializeX001
     * アクションID：X001
     * アクション名：初期化
     * APIリクエスト：IfaRealEntrustDepositRateDomesticX001ApiRequest
     * Apiレスポンス：IfaRealEntrustDepositRateDomesticX001ApiResponse
     * Dtoリクエスト：IfaRealEntrustDepositRateDomesticX001DtoRequest
     * Dtoレスポンス：IfaRealEntrustDepositRateDomesticX001DtoResponse

     * @param apiReq リクエスト
     * @return String レスポンス
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaRealEntrustDepositRateDomesticInitializeX001")
    public String initializeX001(@RequestBody IfaRealEntrustDepositRateDomesticX001ApiRequest apiReq)throws Exception {
        // 顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        IfaRealEntrustDepositRateDomesticX001DtoRequest appReq = new IfaRealEntrustDepositRateDomesticX001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaRealEntrustDepositRateDomesticX001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaRealEntrustDepositRateDomesticService",
                "initializeX001", new TypeReference<DataList<IfaRealEntrustDepositRateDomesticX001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaRealEntrustDepositRateDomesticX001ApiResponse> apiRes = new DataList<IfaRealEntrustDepositRateDomesticX001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**

     * アクセス：/brokerageMenu/customerMenu/ifaRealEntrustDepositRateDomesticUpdateA002
     * アクションID：A002
     * アクション名：更新
     * APIリクエスト：IfaRealEntrustDepositRateDomesticA002ApiRequest
     * Apiレスポンス：IfaRealEntrustDepositRateDomesticA002ApiResponse
     * Dtoリクエスト：IfaRealEntrustDepositRateDomesticA002DtoRequest
     * Dtoレスポンス：IfaRealEntrustDepositRateDomesticA002DtoResponse

     * @param apiReq リクエスト
     * @return String レスポンス
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaRealEntrustDepositRateDomesticUpdateA002")
    public String updateA002(@RequestBody IfaRealEntrustDepositRateDomesticA002ApiRequest apiReq)throws Exception {
        // 顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        IfaRealEntrustDepositRateDomesticA002DtoRequest appReq = new IfaRealEntrustDepositRateDomesticA002DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaRealEntrustDepositRateDomesticA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaRealEntrustDepositRateDomesticService",
                "updateA002", new TypeReference<DataList<IfaRealEntrustDepositRateDomesticA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaRealEntrustDepositRateDomesticA002ApiResponse> apiRes = new DataList<IfaRealEntrustDepositRateDomesticA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        return null;
    }
}

