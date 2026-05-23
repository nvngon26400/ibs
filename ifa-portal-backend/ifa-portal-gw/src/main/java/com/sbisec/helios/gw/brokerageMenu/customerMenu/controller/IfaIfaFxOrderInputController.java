package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA008DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA008DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaIfaFxOrderInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaIfaFxOrderInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaIfaFxOrderInputA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaIfaFxOrderInputA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaIfaFxOrderInputA008ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaIfaFxOrderInputA008ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0503-02_1
 * 画面名：【IFA】為替注文入力
 * @author <author-name>
 *
 * 2023/09/07 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN01", id = "SUB0202_0503-02_1", screenNumber = "188", ignoreCheck = true)
public class IfaIfaFxOrderInputController extends BaseController{

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaIfaFxOrderInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaIfaFxOrderInputA001ApiRequest
     * Apiレスポンス：IfaIfaFxOrderInputA001ApiResponse
     * Dtoリクエスト：IfaIfaFxOrderInputA001DtoRequest
     * Dtoレスポンス：IfaIfaFxOrderInputA001DtoResponse
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaIfaFxOrderInputInitializeA001")
    public String initializeA001(@RequestBody IfaIfaFxOrderInputA001ApiRequest apiReq)throws Exception
    {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaIfaFxOrderInputA001DtoRequest appReq = new IfaIfaFxOrderInputA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaIfaFxOrderInputA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaIfaFxOrderInputService",
                "orderNewMarginA001", new TypeReference<DataList<IfaIfaFxOrderInputA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaIfaFxOrderInputA001ApiResponse> apiRes = new DataList<IfaIfaFxOrderInputA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaIfaFxOrderInputUpdateA002
     * アクションID：A002
     * アクション名：更新
     * APIリクエスト：IfaIfaFxOrderInputA001ApiRequest
     * Apiレスポンス：IfaIfaFxOrderInputA001ApiResponse
     * Dtoリクエスト：IfaIfaFxOrderInputA001DtoRequest
     * Dtoレスポンス：IfaIfaFxOrderInputA001DtoResponse
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    
    @PostMapping("/brokerageMenu/customerMenu/ifaIfaFxOrderInputUpdateA002")
    public String updateA002(@RequestBody IfaIfaFxOrderInputA002ApiRequest apiReq)throws Exception
    {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaIfaFxOrderInputA002DtoRequest appReq = new IfaIfaFxOrderInputA002DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaIfaFxOrderInputA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaIfaFxOrderInputService",
                "orderUpdateMarginA002", new TypeReference<DataList<IfaIfaFxOrderInputA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaIfaFxOrderInputA002ApiResponse> apiRes = new DataList<IfaIfaFxOrderInputA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaIfaFxOrderInputOrderConfirmA008
     * アクションID：A008
     * アクション名：注文確認
     * APIリクエスト：IfaIfaFxOrderInputA001ApiRequest
     * Apiレスポンス：IfaIfaFxOrderInputA001ApiResponse
     * Dtoリクエスト：IfaIfaFxOrderInputA001DtoRequest
     * Dtoレスポンス：IfaIfaFxOrderInputA001DtoResponse
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaIfaFxOrderInputOrderConfirmA008")
    public String orderConfirmA008(@RequestBody IfaIfaFxOrderInputA008ApiRequest apiReq)throws Exception
    {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaIfaFxOrderInputA008DtoRequest appReq = new IfaIfaFxOrderInputA008DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaIfaFxOrderInputA008DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaIfaFxOrderInputService",
                "orderConfirmationMarginA008", new TypeReference<DataList<IfaIfaFxOrderInputA008DtoResponse>>() {
                }, appReq);
        
        DataList<IfaIfaFxOrderInputA008ApiResponse> apiRes = new DataList<IfaIfaFxOrderInputA008ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

