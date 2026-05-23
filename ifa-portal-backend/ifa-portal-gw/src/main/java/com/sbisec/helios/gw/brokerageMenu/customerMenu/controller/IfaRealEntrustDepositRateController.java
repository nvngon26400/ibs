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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateA001DtoResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaRealEntrustDepositRateA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaRealEntrustDepositRateA001ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateA002DtoResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaRealEntrustDepositRateA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaRealEntrustDepositRateA002ApiResponse;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
/**
 * 画面ID：SUB0202_010304-02
 * 画面名：リアル委託保証金率
 
 * SCSK　大崎辰弥
 * 2023/12/01 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010304-02", screenNumber = "")
public class IfaRealEntrustDepositRateController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaRealEntrustDepositRateInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaRealEntrustDepositRateA001ApiRequest
     * Apiレスポンス：IfaRealEntrustDepositRateA001ApiResponse
     * Dtoリクエスト：IfaRealEntrustDepositRateA001DtoRequest
     * Dtoレスポンス：IfaRealEntrustDepositRateA001DtoResponse

     * @param apiReq リクエスト
     * @return String レスポンス
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaRealEntrustDepositRateInitializeA001")
    public String initializeA001(@RequestBody IfaRealEntrustDepositRateA001ApiRequest apiReq)throws Exception {
        // 顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        IfaRealEntrustDepositRateA001DtoRequest appReq = new IfaRealEntrustDepositRateA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaRealEntrustDepositRateA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaRealEntrustDepositRateService",
                "initializeA001", new TypeReference<DataList<IfaRealEntrustDepositRateA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaRealEntrustDepositRateA001ApiResponse> apiRes = new DataList<IfaRealEntrustDepositRateA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaRealEntrustDepositRateUpdateA002
     * アクションID：A002
     * アクション名：更新
     * APIリクエスト：IfaRealEntrustDepositRateA002ApiRequest
     * Apiレスポンス：IfaRealEntrustDepositRateA002ApiResponse
     * Dtoリクエスト：IfaRealEntrustDepositRateA002DtoRequest
     * Dtoレスポンス：IfaRealEntrustDepositRateA002DtoResponse

     * @param apiReq リクエスト
     * @return String レスポンス
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaRealEntrustDepositRateUpdateA002")
    public String updateA002(@RequestBody IfaRealEntrustDepositRateA002ApiRequest apiReq)throws Exception {
        // 顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        IfaRealEntrustDepositRateA002DtoRequest appReq = new IfaRealEntrustDepositRateA002DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaRealEntrustDepositRateA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaRealEntrustDepositRateService",
                "updateA002", new TypeReference<DataList<IfaRealEntrustDepositRateA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaRealEntrustDepositRateA002ApiResponse> apiRes = new DataList<IfaRealEntrustDepositRateA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        return null;
    }
}

