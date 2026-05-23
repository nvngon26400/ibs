package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA010aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA010aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA010bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA010bResponseDto;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamRequestDto;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamResponseDto;
import com.sbisec.helios.ap.common.dto.LinkUrlDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderConfirmA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderConfirmA004ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderConfirmA010ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderConfirmA010ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderConfirmA012ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeNewOrderConfirmA012ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 画面ID：SUB0202_0303-01_2
 * 画面名：米株信用取引新規注文確認
 *
 * @author SCSK 金志
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0303-01_2", screenNumber = "")
public class IfaForeignMarginTradeNewOrderConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：brokerageMenu/customerMenu/foreignStock
     * アクションID：A004
     * アクション名：更新
     * APIリクエスト：IfaForeignMarginTradeNewOrderConfirmA004ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeNewOrderConfirmA004ApiResponse
     * Dtoリクエスト：IfaForeignMarginTradeNewOrderConfirmA004DtoRequest
     * Dtoレスポンス：IfaForeignMarginTradeNewOrderConfirmA004DtoResponse
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 更新の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderConfirmUpdateA004")
    public String updateA004(@RequestBody IfaForeignMarginTradeNewOrderConfirmA004ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaForeignMarginTradeNewOrderConfirmA004RequestDto appReq = new IfaForeignMarginTradeNewOrderConfirmA004RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaForeignMarginTradeNewOrderConfirmA004ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeNewOrderConfirmService", "updateA004",
                new TypeReference<DataList<IfaForeignMarginTradeNewOrderConfirmA004ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginTradeNewOrderConfirmA004ApiResponse> apiRes = new DataList<IfaForeignMarginTradeNewOrderConfirmA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：brokerageMenu/customerMenu/foreignStock
     * アクションID：A010
     * アクション名：注文発注
     * APIリクエスト：IfaForeignMarginTradeNewOrderConfirmA010ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeNewOrderConfirmA010ApiResponse
     * Dtoリクエスト：IfaForeignMarginTradeNewOrderConfirmA010DtoRequest
     * Dtoレスポンス：IfaForeignMarginTradeNewOrderConfirmA010DtoResponse
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 注文発注aの際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderConfirmOrderPlacementA010")
    public String orderPlacementA010(@RequestBody IfaForeignMarginTradeNewOrderConfirmA010ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaForeignMarginTradeNewOrderConfirmA010aRequestDto appReq = new IfaForeignMarginTradeNewOrderConfirmA010aRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // A010aサービスを呼び出す
        DataList<IfaForeignMarginTradeNewOrderConfirmA010aResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeNewOrderConfirmService", "orderPlacementA010a",
                new TypeReference<DataList<IfaForeignMarginTradeNewOrderConfirmA010aResponseDto>>() {
                }, appReq);
        
        // エラー
        if (appRes.getErrorLevel() == ErrorLevel.FATAL.getId()) {
            DataList<IfaForeignMarginTradeNewOrderConfirmA010ApiResponse> apiRes = new DataList<IfaForeignMarginTradeNewOrderConfirmA010ApiResponse>();
            
            BeanUtils.copyProperties(apiRes, appRes);
            
            return jc.toString(apiRes);
        }
        
        IfaForeignMarginTradeNewOrderConfirmA010bRequestDto appReqb = new IfaForeignMarginTradeNewOrderConfirmA010bRequestDto();
        BeanUtils.copyProperties(appReqb, apiReq);
        // 銘柄種別
        appReqb.setBrandClass(appRes.getDataList().get(0).getBrandClass());
        // 注意銘柄
        appReqb.setTradeLimitTitle(appRes.getDataList().get(0).getTradeLimitTitle());
        // 有効期間一覧
        appReqb.setValidityPeriodList(appRes.getDataList().get(0).getValidityPeriodList());
        // IFA注文番号
        appReqb.setIfaOrderNo(appRes.getDataList().get(0).getIfaOrderNo());
        
        // A010bサービスを呼び出す
        DataList<IfaForeignMarginTradeNewOrderConfirmA010bResponseDto> appResb = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeNewOrderConfirmService", "orderPlacementA010b",
                new TypeReference<DataList<IfaForeignMarginTradeNewOrderConfirmA010bResponseDto>>() {
                }, appReqb);
        
        DataList<IfaForeignMarginTradeNewOrderConfirmA010ApiResponse> apiRes = new DataList<IfaForeignMarginTradeNewOrderConfirmA010ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appResb);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderConfirmGetNewMainSiteA012
     * アクションID：A012
     * アクション名：新メインサイトの設定
     * APIリクエスト：IfaForeignMarginTradeNewOrderConfirmA012ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeNewOrderConfirmA012ApiResponse
     *
     * @param apiReq {@code IfaForeignMarginTradeNewOrderConfirmA012ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaForeignMarginTradeNewOrderConfirmGetNewMainSiteA012")
    public String getNewMainSiteA012(@RequestBody IfaForeignMarginTradeNewOrderConfirmA012ApiRequest apiReq) throws Exception{

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
        DataList<IfaForeignMarginTradeNewOrderConfirmA012ApiResponse> apiRes = new DataList<IfaForeignMarginTradeNewOrderConfirmA012ApiResponse>();
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
