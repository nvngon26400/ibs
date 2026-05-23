package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamRequestDto;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamResponseDto;
import com.sbisec.helios.ap.common.dto.LinkUrlDto;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeRepayOrderConfirmA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeRepayOrderConfirmA004ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeRepayOrderConfirmA010ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeRepayOrderConfirmA010ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeRepayOrderConfirmA012ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeRepayOrderConfirmA012ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 画面ID：SUB0202_0303-04_2
 * 画面名：米株信用取引返済注文確認
 * 2023/09/09 新規作成
 *
 * @author SCSK
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0303-04_2", screenNumber = "145")
public class IfaForeignMarginTradeRepayOrderConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderConfirmUpdateA004
     * アクションID：A004
     * アクション名：更新
     * APIリクエスト：IfaForeignMarginTradeRepayOrderConfirmA004ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeRepayOrderConfirmA004ApiResponse
     * Dtoリクエスト：IfaForeignMarginTradeRepayOrderConfirmA004DtoRequest
     * Dtoレスポンス：IfaForeignMarginTradeRepayOrderConfirmA004DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderConfirmUpdateA004")
    public String updateA004(@RequestBody IfaForeignMarginTradeRepayOrderConfirmA004ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaForeignMarginTradeRepayOrderConfirmA004RequestDto appReq = new IfaForeignMarginTradeRepayOrderConfirmA004RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaForeignMarginTradeRepayOrderConfirmA004ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeRepayOrderConfirmService", "updateA004",
                new TypeReference<DataList<IfaForeignMarginTradeRepayOrderConfirmA004ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginTradeRepayOrderConfirmA004ApiResponse> apiRes = new DataList<IfaForeignMarginTradeRepayOrderConfirmA004ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderConfirmOrderPlacementA010
     * アクションID：A010
     * アクション名：注文発注
     * APIリクエスト：IfaForeignMarginTradeRepayOrderConfirmA010ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeRepayOrderConfirmA010ApiResponse
     * Dtoリクエスト：IfaForeignMarginTradeRepayOrderConfirmA010RequestDto
     * Dtoレスポンス：IfaForeignMarginTradeRepayOrderConfirmA010ResponseDto
     *
     * @param apiReq A010のリクエスト
     * @return String　JSONレスポンス
     * @exception Exception エクセプション
     */
    
    @PostMapping(path = "/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderConfirmOrderPlacementA010")
    public String orderPlacementA010(@RequestBody IfaForeignMarginTradeRepayOrderConfirmA010ApiRequest apiReq)
            throws Exception {
        
        DataList<IfaForeignMarginTradeRepayOrderConfirmA010ApiResponse> apiRes = new DataList<IfaForeignMarginTradeRepayOrderConfirmA010ApiResponse>();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto appReqA = new IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto();
        IfaForeignMarginTradeRepayOrderConfirmA010bRequestDto appReqB = new IfaForeignMarginTradeRepayOrderConfirmA010bRequestDto();
        // Beanコピー用共通部品。       
        BeanUtils.copyProperties(appReqA, apiReq);
        BeanUtils.copyProperties(appReqB, apiReq);
        // A010aを呼び出す
        DataList<IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto> appResA = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeRepayOrderConfirmService", "orderPlacementA010a",
                new TypeReference<DataList<IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto>>() {
                }, appReqA);
        
        if (appResA.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            BeanUtils.copyProperties(apiRes, appResA);
            return jc.toString(apiRes);
        }
        // A010aのレスポンス項目をA010bのリクエスト項目へコピーする
        BeanUtils.copyProperties(appReqB, appResA.getDataList().get(0));
        // A010bを呼び出す
        DataList<IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto> appResB = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeRepayOrderConfirmService", "orderPlacementA010b",
                new TypeReference<DataList<IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto>>() {
                }, appReqB);
        BeanUtils.copyProperties(apiRes, appResB);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderConfirmGetNewMainSiteA012
     * アクションID：A012
     * アクション名：新メインサイトの設定
     * APIリクエスト：IfaForeignMarginTradeRepayOrderConfirmA012ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeRepayOrderConfirmA012ApiResponse
     *
     * @param apiReq {@code IfaForeignMarginTradeRepayOrderConfirmA012ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderConfirmGetNewMainSiteA012")
    public String getNewMainSiteA012(@RequestBody IfaForeignMarginTradeRepayOrderConfirmA012ApiRequest apiReq) throws Exception{

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
        DataList<IfaForeignMarginTradeRepayOrderConfirmA012ApiResponse> apiRes = new DataList<IfaForeignMarginTradeRepayOrderConfirmA012ApiResponse>();
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
