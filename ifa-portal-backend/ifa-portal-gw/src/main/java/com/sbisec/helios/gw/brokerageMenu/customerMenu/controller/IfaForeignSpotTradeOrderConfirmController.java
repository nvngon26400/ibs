package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamRequestDto;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA010aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA010aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA010bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA010bResponseDto;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamResponseDto;
import com.sbisec.helios.ap.common.dto.LinkUrlDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderConfirmA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderConfirmA004ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderConfirmA010ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderConfirmA010ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderConfirmA019ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderConfirmA019ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

/**
 * 画面ID：SUB0202_0301-01_2
 * 画面名：外国現物取引注文確認
 * 2024/02/08 新規作成
 *
 * @author 福岡　利基
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0301-01_2", screenNumber = "")
public class IfaForeignSpotTradeOrderConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderConfirmUpdateA004
     * アクションID：A004
     * アクション名：更新
     * APIリクエスト：IfaForeignSpotTradeOrderConfirmA004ApiRequest
     * Apiレスポンス：IfaForeignSpotTradeOrderConfirmA004ApiResponse
     * Dtoリクエスト：IfaForeignSpotTradeOrderConfirmA004DtoRequest
     * Dtoレスポンス：IfaForeignSpotTradeOrderConfirmA004DtoResponse
     *
     * @param apiReq リクエスト
     * @return APIレスポンス
     * @exception Exception 例外処理
     */
    @RequestMapping(value = "/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderConfirmUpdateA004", method = {
            RequestMethod.POST })
    public String updateA004(@RequestBody IfaForeignSpotTradeOrderConfirmA004ApiRequest apiReq) throws Exception {
        
        IfaForeignSpotTradeOrderConfirmA004RequestDto appReq = new IfaForeignSpotTradeOrderConfirmA004RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaForeignSpotTradeOrderConfirmA004ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignSpotTradeOrderConfirmService", "updateA004",
                new TypeReference<DataList<IfaForeignSpotTradeOrderConfirmA004ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignSpotTradeOrderConfirmA004ApiResponse> apiRes = new DataList<IfaForeignSpotTradeOrderConfirmA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderConfirmOrderPlacementA010
     * アクションID：A010
     * アクション名：更新
     * APIリクエスト：IfaForeignSpotTradeOrderConfirmA010ApiRequest
     * Apiレスポンス：IfaForeignSpotTradeOrderConfirmA010ApiResponse
     * Dtoリクエスト：IfaForeignSpotTradeOrderConfirmA010DtoRequest
     * Dtoレスポンス：IfaForeignSpotTradeOrderConfirmA010DtoResponse
     *
     * @param apiReq リクエスト
     * @return APIレスポンス
     * @exception Exception 例外処理
     */
    @RequestMapping(value = "/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderConfirmOrderPlacementA010", method = {
            RequestMethod.POST })
    public String orderPlacementA010(@RequestBody IfaForeignSpotTradeOrderConfirmA010ApiRequest apiReq)
            throws Exception {
        
        IfaForeignSpotTradeOrderConfirmA010aRequestDto appReqA = new IfaForeignSpotTradeOrderConfirmA010aRequestDto();
        IfaForeignSpotTradeOrderConfirmA010bRequestDto appReqB = new IfaForeignSpotTradeOrderConfirmA010bRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqA, apiReq);
        BeanUtils.copyProperties(appReqB, apiReq);
        
        // A010aを呼び出す
        DataList<IfaForeignSpotTradeOrderConfirmA010aResponseDto> appResA = ApiRequestUtil.invoke(
                "cmpIfaForeignSpotTradeOrderConfirmService", "orderPlacementA010a",
                new TypeReference<DataList<IfaForeignSpotTradeOrderConfirmA010aResponseDto>>() {
                }, appReqA);
        DataList<IfaForeignSpotTradeOrderConfirmA010ApiResponse> apiRes = new DataList<IfaForeignSpotTradeOrderConfirmA010ApiResponse>();
        if (appResA.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            BeanUtils.copyProperties(apiRes, appResA);
            return jc.toString(apiRes);
        }
        
        // A010aのレスポンス項目をA010bのリクエスト項目へコピーする
        BeanUtils.copyProperties(appReqB, appResA.getDataList().get(0));
        // A010bを呼び出す
        DataList<IfaForeignSpotTradeOrderConfirmA010bResponseDto> appResB = ApiRequestUtil.invoke(
                "cmpIfaForeignSpotTradeOrderConfirmService", "orderPlacementA010b",
                new TypeReference<DataList<IfaForeignSpotTradeOrderConfirmA010bResponseDto>>() {
                }, appReqB);
        BeanUtils.copyProperties(apiRes, appResB);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderConfirmGetNewMainSiteA019
     * アクションID：A019 
     * アクション名：新メインサイトの設定
     * APIリクエスト：IfaForeignSpotTradeOrderConfirmA019ApiRequest
     * Apiレスポンス：IfaForeignSpotTradeOrderConfirmA019ApiResponse
     *
     * @param apiReq {@code IfaForeignSpotTradeOrderConfirmA019ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderConfirmGetNewMainSiteA019")
    public String getNewMainSiteA019(@RequestBody IfaForeignSpotTradeOrderConfirmA019ApiRequest apiReq)
            throws Exception {

        // リンクURL取得
        LinkUrlDto result = ApiRequestUtil.invoke(ServiceNameConstants.LINK_URL_SERVICE, "getLinkUrl",
                new TypeReference<LinkUrlDto>() {
                }, apiReq.getUrlId(), apiReq.getPatternId(), apiReq.getHttpMethod());

        // 新メインサイト用パラメータ取得
        LinkNewMainSiteParamRequestDto appReq = new LinkNewMainSiteParamRequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        LinkNewMainSiteParamResponseDto paramResult = ApiRequestUtil.invoke(ServiceNameConstants.LINK_NEW_MAIN_SITE_PARAM_SERVICE,
                "getNewMainSiteParam",new TypeReference<LinkNewMainSiteParamResponseDto>() {
                }, appReq);
        DataList<IfaForeignSpotTradeOrderConfirmA019ApiResponse> apiRes = new DataList<IfaForeignSpotTradeOrderConfirmA019ApiResponse>();
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
        appRes = IfaCommonUtil.createDataList(linkList, ErrorLevel.SUCCESS,ErrorLevel.SUCCESS.name(), StringUtil.EMPTY_STRING);
        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }
}
