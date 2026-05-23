package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA006ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA009RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA009ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA010RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA010ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderInputA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderInputA004ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderInputA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderInputA005ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderInputA006ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderInputA006ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderInputA009ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderInputA009ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderInputA010ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderInputA010ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0401-02_1
 * 画面名：国内投信注文入力
 *
 * @author SCSK
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0401-02_1", screenNumber = "")
public class IfaDomesticMutualFundOrderInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaDomesticMutualFundOrderInputA001ApiRequest
     * Apiレスポンス：IfaDomesticMutualFundOrderInputA001ApiResponse
     * Dtoリクエスト：IfaDomesticMutualFundOrderInputA001RequestDto
     * Dtoレスポンス：IfaDomesticMutualFundOrderInputA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderInputInitializeA001")
    public String initializeA001(@RequestBody IfaDomesticMutualFundOrderInputA001ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaDomesticMutualFundOrderInputA001RequestDto appReq = new IfaDomesticMutualFundOrderInputA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDomesticMutualFundOrderInputA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticMutualFundOrderInputService", "initializeA001",
                new TypeReference<DataList<IfaDomesticMutualFundOrderInputA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDomesticMutualFundOrderInputA001ApiResponse> apiRes = new DataList<IfaDomesticMutualFundOrderInputA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderInputResetA004
     * アクションID：A004
     * アクション名：リセット
     * APIリクエスト：IfaDomesticMutualFundOrderInputA004ApiRequest
     * Apiレスポンス：IfaDomesticMutualFundOrderInputA004ApiResponse
     * Dtoリクエスト：IfaDomesticMutualFundOrderInputA004RequestDto
     * Dtoレスポンス：IfaDomesticMutualFundOrderInputA004ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception リセットの際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderInputResetA004")
    public String resetA004(@RequestBody IfaDomesticMutualFundOrderInputA004ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaDomesticMutualFundOrderInputA004RequestDto appReq = new IfaDomesticMutualFundOrderInputA004RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDomesticMutualFundOrderInputA004ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticMutualFundOrderInputService", "resetA004",
                new TypeReference<DataList<IfaDomesticMutualFundOrderInputA004ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDomesticMutualFundOrderInputA004ApiResponse> apiRes = new DataList<IfaDomesticMutualFundOrderInputA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderInputAccountSelectionA005
     * アクションID：A005
     * アクション名：口座選択
     * APIリクエスト：IfaDomesticMutualFundOrderInputA005ApiRequest
     * Apiレスポンス：IfaDomesticMutualFundOrderInputA005ApiResponse
     * Dtoリクエスト：IfaDomesticMutualFundOrderInputA005RequestDto
     * Dtoレスポンス：IfaDomesticMutualFundOrderInputA005ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 口座選択の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderInputAccountSelectionA005")
    public String accountSelectionA005(@RequestBody IfaDomesticMutualFundOrderInputA005ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaDomesticMutualFundOrderInputA005RequestDto appReq = new IfaDomesticMutualFundOrderInputA005RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDomesticMutualFundOrderInputA005ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticMutualFundOrderInputService", "accountSelectionA005",
                new TypeReference<DataList<IfaDomesticMutualFundOrderInputA005ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDomesticMutualFundOrderInputA005ApiResponse> apiRes = new DataList<IfaDomesticMutualFundOrderInputA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderInputTransferPreferentialFrameChangeA006
     * アクションID：A006
     * アクション名：乗換優遇枠適用変更
     * APIリクエスト：IfaDomesticMutualFundOrderInputA006ApiRequest
     * Apiレスポンス：IfaDomesticMutualFundOrderInputA006ApiResponse
     * Dtoリクエスト：IfaDomesticMutualFundOrderInputA006RequestDto
     * Dtoレスポンス：IfaDomesticMutualFundOrderInputA006ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 乗換優遇枠適用変更の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderInputTransferPreferentialFrameChangeA006")
    public String transferPreferentialFrameChangeA006(@RequestBody IfaDomesticMutualFundOrderInputA006ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaDomesticMutualFundOrderInputA006RequestDto appReq = new IfaDomesticMutualFundOrderInputA006RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDomesticMutualFundOrderInputA006ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticMutualFundOrderInputService", "transferPreferentialFrameChangeA006",
                new TypeReference<DataList<IfaDomesticMutualFundOrderInputA006ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDomesticMutualFundOrderInputA006ApiResponse> apiRes = new DataList<IfaDomesticMutualFundOrderInputA006ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderInputDepositCategoryChangeA009
     * アクションID：A009
     * アクション名：預り区分変更
     * APIリクエスト：IfaDomesticMutualFundOrderInputA009ApiRequest
     * Apiレスポンス：IfaDomesticMutualFundOrderInputA009ApiResponse
     * Dtoリクエスト：IfaDomesticMutualFundOrderInputA009RequestDto
     * Dtoレスポンス：IfaDomesticMutualFundOrderInputA009ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 預り区分変更の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderInputDepositCategoryChangeA009")
    public String depositCategoryChangeA009(@RequestBody IfaDomesticMutualFundOrderInputA009ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaDomesticMutualFundOrderInputA009RequestDto appReq = new IfaDomesticMutualFundOrderInputA009RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDomesticMutualFundOrderInputA009ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticMutualFundOrderInputService", "depositCategoryChangeA009",
                new TypeReference<DataList<IfaDomesticMutualFundOrderInputA009ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDomesticMutualFundOrderInputA009ApiResponse> apiRes = new DataList<IfaDomesticMutualFundOrderInputA009ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderInputOrderConfirmA010
     * アクションID：A010
     * アクション名：注文確認
     * APIリクエスト：IfaDomesticMutualFundOrderInputA010ApiRequest
     * Apiレスポンス：IfaDomesticMutualFundOrderInputA010ApiResponse
     * Dtoリクエスト：IfaDomesticMutualFundOrderInputA010RequestDto
     * Dtoレスポンス：IfaDomesticMutualFundOrderInputA010ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 注文確認の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderInputOrderConfirmA010")
    public String orderConfirmA010(@RequestBody IfaDomesticMutualFundOrderInputA010ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaDomesticMutualFundOrderInputA010RequestDto appReq = new IfaDomesticMutualFundOrderInputA010RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDomesticMutualFundOrderInputA010ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticMutualFundOrderInputService", "orderConfirmA010",
                new TypeReference<DataList<IfaDomesticMutualFundOrderInputA010ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDomesticMutualFundOrderInputA010ApiResponse> apiRes = new DataList<IfaDomesticMutualFundOrderInputA010ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
