package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamRequestDto;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamResponseDto;
import com.sbisec.helios.ap.common.dto.LinkUrlDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA001ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderInputA001ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA003ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderInputA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderInputA003ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA005ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderInputA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderInputA005ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA012RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA012ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderInputA012ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderInputA012ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA019RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA019ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderInputA019ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderInputA019ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA020RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderInputA020ResponseDto;

import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderInputA020ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderInputA020ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderInputA023ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderInputA023ApiResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 画面ID：SUB0202_0303-01_1
 * 画面名：米株信用取引新規注文入力
 *
 * @author SCSK池田
 *
    2023/10/24 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0303-01_1", screenNumber = "205")
public class IfaForeignMarginTradeNewOrderInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaForeignMarginTradeNewOrderInputA001ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeNewOrderInputA001ApiResponse
     * Dtoリクエスト：IfaForeignMarginTradeNewOrderInputA001RequestDto
     * Dtoレスポンス：IfaForeignMarginTradeNewOrderInputA001ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderInputInitializeA001")
    public String initializeA001(@RequestBody IfaForeignMarginTradeNewOrderInputA001ApiRequest apiReq)
            throws Exception {
        
        IfaForeignMarginTradeNewOrderInputA001RequestDto appReq = new IfaForeignMarginTradeNewOrderInputA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaForeignMarginTradeNewOrderInputA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeNewOrderInputService", "initializeA001",
                new TypeReference<DataList<IfaForeignMarginTradeNewOrderInputA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginTradeNewOrderInputA001ApiResponse> apiRes = new DataList<IfaForeignMarginTradeNewOrderInputA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderInputStockPriceDisplayA003
     * アクションID：A003
     * アクション名：株価表示
     * APIリクエスト：IfaForeignMarginTradeNewOrderInputA003ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeNewOrderInputA003ApiResponse
     * Dtoリクエスト：IfaForeignMarginTradeNewOrderInputA003RequestDto
     * Dtoレスポンス：IfaForeignMarginTradeNewOrderInputA003ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderInputStockPriceDisplayA003")
    public String stockPriceDisplayA003(@RequestBody IfaForeignMarginTradeNewOrderInputA003ApiRequest apiReq)
            throws Exception {
        
        IfaForeignMarginTradeNewOrderInputA003RequestDto appReq = new IfaForeignMarginTradeNewOrderInputA003RequestDto();
        
        // 銘柄コードを大文字に変換
        if (apiReq != null && apiReq.getBrandCode() != null) {
            apiReq.setBrandCode(apiReq.getBrandCode().toUpperCase());
        }

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaForeignMarginTradeNewOrderInputA003ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeNewOrderInputService", "stockPriceDisplayA003",
                new TypeReference<DataList<IfaForeignMarginTradeNewOrderInputA003ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginTradeNewOrderInputA003ApiResponse> apiRes = new DataList<IfaForeignMarginTradeNewOrderInputA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderInputUpdateA005
     * アクションID：A005
     * アクション名：更新
     * APIリクエスト：IfaForeignMarginTradeNewOrderInputA005ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeNewOrderInputA005ApiResponse
     * Dtoリクエスト：IfaForeignMarginTradeNewOrderInputA005RequestDto
     * Dtoレスポンス：IfaForeignMarginTradeNewOrderInputA005ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderInputUpdateA005")
    public String updateA005(@RequestBody IfaForeignMarginTradeNewOrderInputA005ApiRequest apiReq) throws Exception {
        
        IfaForeignMarginTradeNewOrderInputA005RequestDto appReq = new IfaForeignMarginTradeNewOrderInputA005RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaForeignMarginTradeNewOrderInputA005ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeNewOrderInputService", "updateA005",
                new TypeReference<DataList<IfaForeignMarginTradeNewOrderInputA005ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginTradeNewOrderInputA005ApiResponse> apiRes = new DataList<IfaForeignMarginTradeNewOrderInputA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderInputOrderConfirmA012
     * アクションID：A012
     * アクション名：注文確認
     * APIリクエスト：IfaForeignMarginTradeNewOrderInputA012ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeNewOrderInputA012ApiResponse
     * Dtoリクエスト：IfaForeignMarginTradeNewOrderInputA012RequestDto
     * Dtoレスポンス：IfaForeignMarginTradeNewOrderInputA012ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderInputOrderConfirmA012")
    public String orderConfirmA012(@RequestBody IfaForeignMarginTradeNewOrderInputA012ApiRequest apiReq)
            throws Exception {
        
        IfaForeignMarginTradeNewOrderInputA012RequestDto appReq = new IfaForeignMarginTradeNewOrderInputA012RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaForeignMarginTradeNewOrderInputA012ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeNewOrderInputService", "orderConfirmA012",
                new TypeReference<DataList<IfaForeignMarginTradeNewOrderInputA012ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginTradeNewOrderInputA012ApiResponse> apiRes = new DataList<IfaForeignMarginTradeNewOrderInputA012ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderInputDisplayReferenceMarginBalanceA019
     * アクションID：A019
     * アクション名：表示する（参考信用建余力）
     * APIリクエスト：IfaForeignMarginTradeNewOrderInputA019ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeNewOrderInputA019ApiResponse
     * Dtoリクエスト：IfaForeignMarginTradeNewOrderInputA019RequestDto
     * Dtoレスポンス：IfaForeignMarginTradeNewOrderInputA019ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderInputDisplayReferenceMarginBalanceA019")
    public String displayReferenceMarginBalanceA019(
            @RequestBody IfaForeignMarginTradeNewOrderInputA019ApiRequest apiReq) throws Exception {
        
        IfaForeignMarginTradeNewOrderInputA019RequestDto appReq = new IfaForeignMarginTradeNewOrderInputA019RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaForeignMarginTradeNewOrderInputA019ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeNewOrderInputService", "displayReferenceMarginBalanceA019",
                new TypeReference<DataList<IfaForeignMarginTradeNewOrderInputA019ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginTradeNewOrderInputA019ApiResponse> apiRes = new DataList<IfaForeignMarginTradeNewOrderInputA019ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderInputEstimatedConstructionFeeA020
     * アクションID：A020
     * アクション名：概算建代金
     * APIリクエスト：IfaForeignMarginTradeNewOrderInputA020ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeNewOrderInputA020ApiResponse
     * Dtoリクエスト：IfaForeignMarginTradeNewOrderInputA020RequestDto
     * Dtoレスポンス：IfaForeignMarginTradeNewOrderInputA020ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 実行結果
     * @exception Exception 業務例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderInputEstimatedConstructionFeeA020")
    public String estimatedConstructionFeeA020(@RequestBody IfaForeignMarginTradeNewOrderInputA020ApiRequest apiReq)
            throws Exception {
        
        IfaForeignMarginTradeNewOrderInputA020RequestDto appReq = new IfaForeignMarginTradeNewOrderInputA020RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaForeignMarginTradeNewOrderInputA020ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeNewOrderInputService", "estimatedConstructionFeeA020",
                new TypeReference<DataList<IfaForeignMarginTradeNewOrderInputA020ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginTradeNewOrderInputA020ApiResponse> apiRes = new DataList<IfaForeignMarginTradeNewOrderInputA020ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderInputGetNewMainSiteA023
     * アクションID：A023
     * アクション名：新メインサイトの設定
     * APIリクエスト：IfaForeignMarginTradeNewOrderInputA023ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeNewOrderInputA023ApiResponse
     *
     * @param apiReq {@code IfaForeignMarginTradeNewOrderInputA023ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderInputGetNewMainSiteA023")
    public String getNewMainSiteA023(@RequestBody IfaForeignMarginTradeNewOrderInputA023ApiRequest apiReq) throws Exception{

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
        DataList<IfaForeignMarginTradeNewOrderInputA023ApiResponse> apiRes = new DataList<IfaForeignMarginTradeNewOrderInputA023ApiResponse>();
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
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
