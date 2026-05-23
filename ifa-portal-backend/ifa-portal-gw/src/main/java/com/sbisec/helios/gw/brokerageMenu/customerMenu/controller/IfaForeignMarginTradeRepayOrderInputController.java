package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.sbibits.earth.model.DataList;
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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA012RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA012ResponseDto;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeRepayOrderInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeRepayOrderInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeRepayOrderInputA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeRepayOrderInputA005ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeRepayOrderInputA012ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeRepayOrderInputA012ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeRepayOrderInputA021ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeRepayOrderInputA021ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 画面ID：SUB0202_0303-04_1
 * 画面名：米株信用取引返済注文入力
 * @author <author-name>
 *
 * 2023/11/01 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0303-04_1", screenNumber = "")
public class IfaForeignMarginTradeRepayOrderInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaForeignMarginTradeRepayOrderInputA001ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeRepayOrderInputA001ApiResponse
     * Dtoリクエスト：IfaForeignMarginTradeRepayOrderInputA001DtoRequest
     * Dtoレスポンス：IfaForeignMarginTradeRepayOrderInputA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderInputInitializeA001")
    public String initializeA001(@RequestBody IfaForeignMarginTradeRepayOrderInputA001ApiRequest apiReq)
            throws Exception {
        
        IfaForeignMarginTradeRepayOrderInputA001RequestDto appReq = new IfaForeignMarginTradeRepayOrderInputA001RequestDto();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaForeignMarginTradeRepayOrderInputA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeRepayOrderInputService", "initializeA001",
                new TypeReference<DataList<IfaForeignMarginTradeRepayOrderInputA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginTradeRepayOrderInputA001ApiResponse> apiRes = new DataList<IfaForeignMarginTradeRepayOrderInputA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderInputUpdateA005
     * アクションID：A005
     * アクション名：更新
     * APIリクエスト：IfaForeignMarginTradeRepayOrderInputA005ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeRepayOrderInputA005ApiResponse
     * Dtoリクエスト：IfaForeignMarginTradeRepayOrderInputA005DtoRequest
     * Dtoレスポンス：IfaForeignMarginTradeRepayOrderInputA005DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderInputUpdateA005")
    public String updateA005(@RequestBody IfaForeignMarginTradeRepayOrderInputA005ApiRequest apiReq) throws Exception {
        
        IfaForeignMarginTradeRepayOrderInputA005RequestDto appReq = new IfaForeignMarginTradeRepayOrderInputA005RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaForeignMarginTradeRepayOrderInputA005ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeRepayOrderInputService", "updateA005",
                new TypeReference<DataList<IfaForeignMarginTradeRepayOrderInputA005ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginTradeRepayOrderInputA005ApiResponse> apiRes = new DataList<IfaForeignMarginTradeRepayOrderInputA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderInputOrderConfirmA012
     * アクションID：A012
     * アクション名：注文確認
     * APIリクエスト：IfaForeignMarginTradeRepayOrderInputA012ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeRepayOrderInputA012ApiResponse
     * Dtoリクエスト：IfaForeignMarginTradeRepayOrderInputA012DtoRequest
     * Dtoレスポンス：IfaForeignMarginTradeRepayOrderInputA012DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderInputOrderConfirmA012")
    public String orderConfirmA012(@RequestBody IfaForeignMarginTradeRepayOrderInputA012ApiRequest apiReq)
            throws Exception {
        
        IfaForeignMarginTradeRepayOrderInputA012RequestDto appReq = new IfaForeignMarginTradeRepayOrderInputA012RequestDto();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaForeignMarginTradeRepayOrderInputA012ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeRepayOrderInputService", "orderConfirmA012",
                new TypeReference<DataList<IfaForeignMarginTradeRepayOrderInputA012ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginTradeRepayOrderInputA012ApiResponse> apiRes = new DataList<IfaForeignMarginTradeRepayOrderInputA012ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderInputGetNewMainSiteA021
     * アクションID：A021
     * アクション名：新メインサイトの設定
     * APIリクエスト：IfaForeignMarginTradeRepayOrderInputA021ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeRepayOrderInputA021ApiResponse
     *
     * @param apiReq {@code IfaForeignMarginTradeRepayOrderInputA021ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderInputGetNewMainSiteA021")
    public String getNewMainSiteA021(@RequestBody IfaForeignMarginTradeRepayOrderInputA021ApiRequest apiReq) throws Exception{

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
        DataList<IfaForeignMarginTradeRepayOrderInputA021ApiResponse> apiRes = new DataList<IfaForeignMarginTradeRepayOrderInputA021ApiResponse>();
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
