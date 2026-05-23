package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA013RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA013ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputA021ApiResponse;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamRequestDto;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamResponseDto;
import com.sbisec.helios.ap.common.dto.LinkUrlDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderInputA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderInputA003ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderInputA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderInputA005ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderInputA013ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderInputA013ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderInputA021ApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 画面ID：SUB0202_0301-01_1
 * 画面名：外国現物取引注文入力
 * 2024/02/07 新規作成
 *
 * @author SCSK 宇田川達弥
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0301-01_1", screenNumber = "")
public class IfaForeignSpotTradeOrderInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaForeignSpotTradeOrderInputA001ApiRequest
     * Apiレスポンス：IfaForeignSpotTradeOrderInputA001ApiResponse
     * Dtoリクエスト：IfaForeignSpotTradeOrderInputA001RequestDto
     * Dtoレスポンス：IfaForeignSpotTradeOrderInputA001ResponseDto
     *
     * @param apiReq リクエストDto
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderInputInitializeA001")
    @ResponseBody
    @ResponseJson
    public String initializeA001(@RequestBody IfaForeignSpotTradeOrderInputA001ApiRequest apiReq) throws Exception {
        
        IfaForeignSpotTradeOrderInputA001RequestDto appReq = new IfaForeignSpotTradeOrderInputA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 顧客共通情報の取得
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaForeignSpotTradeOrderInputA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignSpotTradeOrderInputService", "initializeA001",
                new TypeReference<DataList<IfaForeignSpotTradeOrderInputA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignSpotTradeOrderInputA001ApiResponse> apiRes = new DataList<IfaForeignSpotTradeOrderInputA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderInputStockPriceDisplayA003
     * アクションID：A003
     * アクション名：株価表示
     * APIリクエスト：IfaForeignSpotTradeOrderInputA003ApiRequest
     * Apiレスポンス：IfaForeignSpotTradeOrderInputA003ApiResponse
     * Dtoリクエスト：IfaForeignSpotTradeOrderInputA003RequestDto
     * Dtoレスポンス：IfaForeignSpotTradeOrderInputA003ResponseDto
     *
     * @param apiReq リクエストDto
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderInputStockPriceDisplayA003")
    @ResponseBody
    @ResponseJson
    public String stockPriceDisplayA003(@RequestBody IfaForeignSpotTradeOrderInputA003ApiRequest apiReq)
            throws Exception {
        
        IfaForeignSpotTradeOrderInputA003RequestDto appReq = new IfaForeignSpotTradeOrderInputA003RequestDto();
        
        // 銘柄コードを大文字に変換
        if (apiReq != null && apiReq.getBrandCode() != null) {
            apiReq.setBrandCode(apiReq.getBrandCode().toUpperCase());
        }

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 顧客共通情報の取得
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaForeignSpotTradeOrderInputA003ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignSpotTradeOrderInputService", "stockPriceDisplayA003",
                new TypeReference<DataList<IfaForeignSpotTradeOrderInputA003ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignSpotTradeOrderInputA003ApiResponse> apiRes = new DataList<IfaForeignSpotTradeOrderInputA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderInputUpdateA005
     * アクションID：A005
     * アクション名：更新
     * APIリクエスト：IfaForeignSpotTradeOrderInputA005ApiRequest
     * Apiレスポンス：IfaForeignSpotTradeOrderInputA005ApiResponse
     * Dtoリクエスト：IfaForeignSpotTradeOrderInputA005RequestDto
     * Dtoレスポンス：IfaForeignSpotTradeOrderInputA005ResponseDto
     *
     * @param apiReq リクエストDto
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderInputUpdateA005")
    @ResponseBody
    @ResponseJson
    public String updateA005(@RequestBody IfaForeignSpotTradeOrderInputA005ApiRequest apiReq) throws Exception {
        
        IfaForeignSpotTradeOrderInputA005RequestDto appReq = new IfaForeignSpotTradeOrderInputA005RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 顧客共通情報の取得
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaForeignSpotTradeOrderInputA005ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignSpotTradeOrderInputService", "updateA005",
                new TypeReference<DataList<IfaForeignSpotTradeOrderInputA005ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignSpotTradeOrderInputA005ApiResponse> apiRes = new DataList<IfaForeignSpotTradeOrderInputA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderInputOrderConfirmA013
     * アクションID：A013
     * アクション名：注文確認
     * APIリクエスト：IfaForeignSpotTradeOrderInputA013ApiRequest
     * Apiレスポンス：IfaForeignSpotTradeOrderInputA013ApiResponse
     * Dtoリクエスト：IfaForeignSpotTradeOrderInputA013RequestDto
     * Dtoレスポンス：IfaForeignSpotTradeOrderInputA013ResponseDto
     *
     * @param apiReq リクエストDto
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderInputOrderConfirmA013")
    @ResponseBody
    @ResponseJson
    public String orderConfirmA013(@RequestBody IfaForeignSpotTradeOrderInputA013ApiRequest apiReq) throws Exception {
        
        IfaForeignSpotTradeOrderInputA013RequestDto appReq = new IfaForeignSpotTradeOrderInputA013RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 顧客共通情報の取得
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaForeignSpotTradeOrderInputA013ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignSpotTradeOrderInputService", "orderConfirmA013",
                new TypeReference<DataList<IfaForeignSpotTradeOrderInputA013ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignSpotTradeOrderInputA013ApiResponse> apiRes = new DataList<IfaForeignSpotTradeOrderInputA013ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderInputGetNewMainSiteA021
     * アクションID：A021
     * アクション名：新メインサイトの設定
     * APIリクエスト：IfaForeignSpotTradeOrderInputA021ApiRequest
     * Apiレスポンス：IfaForeignSpotTradeOrderInputA021ApiResponse
     *
     * @param apiReq {@code IfaForeignSpotTradeOrderInputA021ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderInputGetNewMainSiteA021")
    public String getNewMainSiteA021(@RequestBody IfaForeignSpotTradeOrderInputA021ApiRequest apiReq) throws Exception{

        // リンクURL取得
        LinkUrlDto result = ApiRequestUtil.invoke(ServiceNameConstants.LINK_URL_SERVICE, "getLinkUrl",
                new TypeReference<LinkUrlDto>() {
                }, apiReq.getUrlId(), apiReq.getPatternId(), apiReq.getHttpMethod());

        // 新メインサイト用パラメータ取得
        LinkNewMainSiteParamRequestDto appReq = new LinkNewMainSiteParamRequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        LinkNewMainSiteParamResponseDto paramResult = ApiRequestUtil.invoke(ServiceNameConstants.LINK_NEW_MAIN_SITE_PARAM_SERVICE,
                "getNewMainSiteParam", new TypeReference<LinkNewMainSiteParamResponseDto>() {
                }, appReq);
        DataList<IfaForeignSpotTradeOrderInputA021ApiResponse> apiRes = new DataList<IfaForeignSpotTradeOrderInputA021ApiResponse>();
        DataList<LinkNewMainSiteParamResponseDto> appRes = new DataList<LinkNewMainSiteParamResponseDto>();
        LinkNewMainSiteParamResponseDto linkRes = new LinkNewMainSiteParamResponseDto();
        // URLを取得する
        linkRes.setLinkUrl(result.getLinkUrl());
        // データベースからパラメータを取得する
        linkRes.setPostRequest(result.getPostRequest());
        // 新メインサイトを取得する
        linkRes.setNewMainSiteParamList(paramResult.getNewMainSiteParamList());
        List<LinkNewMainSiteParamResponseDto> linkList = new ArrayList<LinkNewMainSiteParamResponseDto>();
        linkList.add(linkRes);
        appRes = IfaCommonUtil.createDataList(linkList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), StringUtil.EMPTY_STRING);
        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }
    
}
