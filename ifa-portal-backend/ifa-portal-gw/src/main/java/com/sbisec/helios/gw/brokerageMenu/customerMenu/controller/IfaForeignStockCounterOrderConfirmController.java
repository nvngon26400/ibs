package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import java.util.ArrayList;
import java.util.List;

import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamRequestDto;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderConfirmA002ResponseDto;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamResponseDto;
import com.sbisec.helios.ap.common.dto.LinkUrlDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignStockCounterOrderConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignStockCounterOrderConfirmA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignStockCounterOrderConfirmA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignStockCounterOrderConfirmA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignStockCounterOrderConfirmA019ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignStockCounterOrderConfirmA019ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0302-02_2
 * 画面名：外国株式店頭注文確認
 * 2024/05/10 新規作成
 *
 * @author SCSK 江口
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0302-02_2", screenNumber = "")
public class IfaForeignStockCounterOrderConfirmController extends BaseController{

    private JsonConverter jc = JsonConverter.getInstance();


    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaForeignStockCounterOrderConfirmOrderPlacementA001
     * アクションID：A001
     * アクション名：注文発注
     * APIリクエスト：IfaForeignStockCounterOrderConfirmA001ApiRequest
     * Apiレスポンス：IfaForeignStockCounterOrderConfirmA001ApiResponse
     * Dtoリクエスト：IfaForeignStockCounterOrderConfirmA001RequestDto
     * Dtoレスポンス：IfaForeignStockCounterOrderConfirmA001ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 注文発注に必要な情報
     * @exception Exception システムエラー
     */
	@PostMapping("/brokerageMenu/customerMenu/ifaForeignStockCounterOrderConfirmOrderPlacementA001")
    public String orderPlacementA001(
            @RequestBody IfaForeignStockCounterOrderConfirmA001ApiRequest apiReq
    ) throws Exception {

        // 顧客共通情報をリクエストスコープにセットする
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaForeignStockCounterOrderConfirmA001RequestDto appReq = new IfaForeignStockCounterOrderConfirmA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaForeignStockCounterOrderConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignStockCounterOrderConfirmService",
                "orderPlacementA001",
                new TypeReference<DataList<IfaForeignStockCounterOrderConfirmA001ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaForeignStockCounterOrderConfirmA001ApiResponse> apiRes = new DataList<IfaForeignStockCounterOrderConfirmA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaForeignStockCounterOrderConfirmUpdateA002
     * アクションID：A002
     * アクション名：更新
     * APIリクエスト：IfaForeignStockCounterOrderConfirmA002ApiRequest
     * Apiレスポンス：IfaForeignStockCounterOrderConfirmA002ApiResponse
     * Dtoリクエスト：IfaForeignStockCounterOrderConfirmA002RequestDto
     * Dtoレスポンス：IfaForeignStockCounterOrderConfirmA002ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 更新に必要な情報
     * @exception Exception システムエラー
     */
	@PostMapping("/brokerageMenu/customerMenu/ifaForeignStockCounterOrderConfirmUpdateA002")
    public String updateA002(
            @RequestBody IfaForeignStockCounterOrderConfirmA002ApiRequest apiReq
    ) throws Exception {

        // 顧客共通情報をリクエストスコープにセットする
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaForeignStockCounterOrderConfirmA002RequestDto appReq = new IfaForeignStockCounterOrderConfirmA002RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaForeignStockCounterOrderConfirmA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignStockCounterOrderConfirmService",
                "updateA002",
                new TypeReference<DataList<IfaForeignStockCounterOrderConfirmA002ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaForeignStockCounterOrderConfirmA002ApiResponse> apiRes = new DataList<IfaForeignStockCounterOrderConfirmA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignStockCounterOrderConfirmGetNewMainSiteA019
     * アクションID：A019
     * アクション名：新メインサイトの設定
     * APIリクエスト：IfaForeignStockCounterOrderConfirmA019ApiRequest
     * Apiレスポンス：IfaForeignStockCounterOrderConfirmA019ApiResponse
     *
     * @param apiReq {@code IfaForeignStockCounterOrderConfirmA019ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaForeignStockCounterOrderConfirmGetNewMainSiteA019")
    public String getNewMainSiteA019(@RequestBody IfaForeignStockCounterOrderConfirmA019ApiRequest apiReq)
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
        DataList<IfaForeignStockCounterOrderConfirmA019ApiResponse> apiRes = new DataList<IfaForeignStockCounterOrderConfirmA019ApiResponse>();
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
        appRes = IfaCommonUtil.createDataList(linkList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),StringUtil.EMPTY_STRING);
        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
