package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA016RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA016ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderInputA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderInputA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderInputA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderInputA005ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderInputA016ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderInputA016ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0208-01_1
 * 画面名：国内株式注文入力
 *
 * @author SCSK
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0208-01_1", screenNumber = "")
public class IfaDomesticStockOrderInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticStockOrderInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaDomesticStockOrderInputA001ApiRequest
     * Apiレスポンス：IfaDomesticStockOrderInputA001ApiResponse
     * Dtoリクエスト：IfaDomesticStockOrderInputA001RequestDto
     * Dtoレスポンス：IfaDomesticStockOrderInputA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticStockOrderInputInitializeA001")
    public String initializeA001(@RequestBody IfaDomesticStockOrderInputA001ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaDomesticStockOrderInputA001RequestDto appReq = new IfaDomesticStockOrderInputA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDomesticStockOrderInputA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticStockOrderInputService", "initializeA001",
                new TypeReference<DataList<IfaDomesticStockOrderInputA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDomesticStockOrderInputA001ApiResponse> apiRes = new DataList<IfaDomesticStockOrderInputA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticStockOrderInputBrandSelectionMarketSelectionA002
     * アクションID：A002
     * アクション名：銘柄選択、市場選択
     * APIリクエスト：IfaDomesticStockOrderInputA002ApiRequest
     * Apiレスポンス：IfaDomesticStockOrderInputA002ApiResponse
     * Dtoリクエスト：IfaDomesticStockOrderInputA002RequestDto
     * Dtoレスポンス：IfaDomesticStockOrderInputA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticStockOrderInputBrandSelectionMarketSelectionA002")
    public String brandSelectionMarketSelectionA002(@RequestBody IfaDomesticStockOrderInputA002ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaDomesticStockOrderInputA002RequestDto appReq = new IfaDomesticStockOrderInputA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDomesticStockOrderInputA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticStockOrderInputService", "brandSelectionMarketSelectionA002",
                new TypeReference<DataList<IfaDomesticStockOrderInputA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDomesticStockOrderInputA002ApiResponse> apiRes = new DataList<IfaDomesticStockOrderInputA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticStockOrderInputUpdateA005
     * アクションID：A005
     * アクション名：更新
     * APIリクエスト：IfaDomesticStockOrderInputA005ApiRequest
     * Apiレスポンス：IfaDomesticStockOrderInputA005ApiResponse
     * Dtoリクエスト：IfaDomesticStockOrderInputA005RequestDto
     * Dtoレスポンス：IfaDomesticStockOrderInputA005ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticStockOrderInputUpdateA005")
    public String updateA005(@RequestBody IfaDomesticStockOrderInputA005ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaDomesticStockOrderInputA005RequestDto appReq = new IfaDomesticStockOrderInputA005RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDomesticStockOrderInputA005ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticStockOrderInputService", "updateA005",
                new TypeReference<DataList<IfaDomesticStockOrderInputA005ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDomesticStockOrderInputA005ApiResponse> apiRes = new DataList<IfaDomesticStockOrderInputA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticStockOrderInputOrderConfirmA016
     * アクションID：A016
     * アクション名：注文確認
     * APIリクエスト：IfaDomesticStockOrderInputA016ApiRequest
     * Apiレスポンス：IfaDomesticStockOrderInputA016ApiResponse
     * Dtoリクエスト：IfaDomesticStockOrderInputA016RequestDto
     * Dtoレスポンス：IfaDomesticStockOrderInputA016ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticStockOrderInputOrderConfirmA016")
    public String orderConfirmA016(@RequestBody IfaDomesticStockOrderInputA016ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaDomesticStockOrderInputA016RequestDto appReq = new IfaDomesticStockOrderInputA016RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDomesticStockOrderInputA016ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticStockOrderInputService", "orderConfirmA016",
                new TypeReference<DataList<IfaDomesticStockOrderInputA016ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDomesticStockOrderInputA016ApiResponse> apiRes = new DataList<IfaDomesticStockOrderInputA016ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
