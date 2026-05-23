package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectX003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectX003ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerSelectA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerSelectA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerSelectA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerSelectA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerSelectX003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerSelectX003ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_00-01
 * 画面名：顧客選択
 *
 * @author SCSK
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_00-01", screenNumber = "")
public class IfaCustomerSelectController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaCustomerSelectInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaCustomerSelectA001ApiRequest
     * Apiレスポンス：IfaCustomerSelectA001ApiResponse
     * Dtoリクエスト：IfaCustomerSelectA001RequestDto
     * Dtoレスポンス：IfaCustomerSelectA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaCustomerSelectInitializeA001")
    public String initializeA001(@RequestBody IfaCustomerSelectA001ApiRequest apiReq) throws Exception {
        
        IfaCustomerSelectA001RequestDto appReq = new IfaCustomerSelectA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaCustomerSelectA001ResponseDto> appRes = ApiRequestUtil.invoke("ifaCustomerSelectService",
                "initializeA001", new TypeReference<DataList<IfaCustomerSelectA001ResponseDto>>() {
                }, appReq);
        
        // サービスからのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaCustomerSelectA001ApiResponse> apiRes = new DataList<IfaCustomerSelectA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaCustomerSelectSearchX003
     * アクションID：X003
     * アクション名：検索
     * APIリクエスト：IfaCustomerSelectX003ApiRequest
     * Apiレスポンス：IfaCustomerSelectX003ApiResponse
     * Dtoリクエスト：IfaCustomerSelectX003RequestDto
     * Dtoレスポンス：IfaCustomerSelectX003ResponseDto
     *
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception 検索処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaCustomerSelectSearchX003")
    public String searchX003(@RequestBody IfaCustomerSelectX003ApiRequest apiReq) throws Exception {
        
        IfaCustomerSelectX003RequestDto appReq = new IfaCustomerSelectX003RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaCustomerSelectX003ResponseDto> appRes = ApiRequestUtil.invoke("ifaCustomerSelectService",
                "searchX003", new TypeReference<DataList<IfaCustomerSelectX003ResponseDto>>() {
                }, appReq);
        
        // サービスからのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaCustomerSelectX003ApiResponse> apiRes = new DataList<IfaCustomerSelectX003ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaCustomerSelectFavoriteRegisterUnregisterA004
     * アクションID：A004
     * アクション名：お気に入り登録・解除
     * APIリクエスト：IfaCustomerSelectA004ApiRequest
     * Apiレスポンス：IfaCustomerSelectA004ApiResponse
     * Dtoリクエスト：IfaCustomerSelectA004RequestDto
     * Dtoレスポンス：IfaCustomerSelectA004ResponseDto
     *
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception お気に入り登録・解除処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaCustomerSelectFavoriteRegisterUnregisterA004")
    public String favoriteRegisterUnregisterA004(@RequestBody IfaCustomerSelectA004ApiRequest apiReq) throws Exception {
        
        IfaCustomerSelectA004RequestDto appReq = new IfaCustomerSelectA004RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<Object> appRes = ApiRequestUtil.invoke("ifaCustomerSelectService", "favoriteRegisterUnregisterA004",
                new TypeReference<DataList<Object>>() {
                }, appReq);
        
        return jc.toString(appRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaCustomerSelectCustomerSelectA005
     * アクションID：A005
     * アクション名：顧客選択
     * APIリクエスト：IfaCustomerSelectA005ApiRequest
     * Apiレスポンス：IfaCustomerSelectA005ApiResponse
     * Dtoリクエスト：IfaCustomerSelectA005RequestDto
     * Dtoレスポンス：IfaCustomerSelectA005ResponseDto
     *
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception 顧客選択処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaCustomerSelectCustomerSelectA005")
    public String customerSelectA005(@RequestBody IfaCustomerSelectA005ApiRequest apiReq) throws Exception {
        
        IfaCustomerSelectA005RequestDto appReq = new IfaCustomerSelectA005RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<Object> appRes = ApiRequestUtil.invoke("ifaCustomerSelectService", "customerSelectA005",
                new TypeReference<DataList<Object>>() {
                }, appReq);
        
        return jc.toString(appRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
