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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA008RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA008ResponseDto;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamResponseDto;
import com.sbisec.helios.ap.common.dto.LinkUrlDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignStockCounterOrderInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignStockCounterOrderInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignStockCounterOrderInputA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignStockCounterOrderInputA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignStockCounterOrderInputA008ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignStockCounterOrderInputA008ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignStockCounterOrderInputA019ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignStockCounterOrderInputA019ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0302-02_1
 * 画面名：外国株式店頭注文入力
 * 2024/05/07 新規作成
 *
 * @author SCSK 江口
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0302-02_1", screenNumber = "")
public class IfaForeignStockCounterOrderInputController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();


    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignStockCounterOrderInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaForeignStockCounterOrderInputA001ApiRequest
     * Apiレスポンス：IfaForeignStockCounterOrderInputA001ApiResponse
     * Dtoリクエスト：IfaForeignStockCounterOrderInputA001RequestDto
     * Dtoレスポンス：IfaForeignStockCounterOrderInputA001ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 初期化に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignStockCounterOrderInputInitializeA001")
    public String initializeA001(
            @RequestBody IfaForeignStockCounterOrderInputA001ApiRequest apiReq
    ) throws Exception {

        // 顧客共通情報をリクエストスコープにセットする
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaForeignStockCounterOrderInputA001RequestDto appReq = new IfaForeignStockCounterOrderInputA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaForeignStockCounterOrderInputA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignStockCounterOrderInputService",
                "initializeA001",
                new TypeReference<DataList<IfaForeignStockCounterOrderInputA001ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaForeignStockCounterOrderInputA001ApiResponse> apiRes = new DataList<IfaForeignStockCounterOrderInputA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignStockCounterOrderInputUpdateA002
     * アクションID：A002
     * アクション名：更新
     * APIリクエスト：IfaForeignStockCounterOrderInputA002ApiRequest
     * Apiレスポンス：IfaForeignStockCounterOrderInputA002ApiResponse
     * Dtoリクエスト：IfaForeignStockCounterOrderInputA002RequestDto
     * Dtoレスポンス：IfaForeignStockCounterOrderInputA002ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 更新に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignStockCounterOrderInputUpdateA002")
    public String updateA002(
            @RequestBody IfaForeignStockCounterOrderInputA002ApiRequest apiReq
    ) throws Exception {

        // 顧客共通情報をリクエストスコープにセットする
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaForeignStockCounterOrderInputA002RequestDto appReq = new IfaForeignStockCounterOrderInputA002RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaForeignStockCounterOrderInputA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignStockCounterOrderInputService",
                "updateA002",
                new TypeReference<DataList<IfaForeignStockCounterOrderInputA002ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaForeignStockCounterOrderInputA002ApiResponse> apiRes = new DataList<IfaForeignStockCounterOrderInputA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignStockCounterOrderInputOrderConfirmA008
     * アクションID：A008
     * アクション名：注文確認
     * APIリクエスト：IfaForeignStockCounterOrderInputA008ApiRequest
     * Apiレスポンス：IfaForeignStockCounterOrderInputA008ApiResponse
     * Dtoリクエスト：IfaForeignStockCounterOrderInputA008RequestDto
     * Dtoレスポンス：IfaForeignStockCounterOrderInputA008ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 注文確認に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignStockCounterOrderInputOrderConfirmA008")
    public String orderConfirmA008(
            @RequestBody IfaForeignStockCounterOrderInputA008ApiRequest apiReq
    ) throws Exception {

        // 顧客共通情報をリクエストスコープにセットする
        IfaGwCommonUtil.setCustomerCommonToRequestScope();


        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaForeignStockCounterOrderInputA008RequestDto appReq = new IfaForeignStockCounterOrderInputA008RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaForeignStockCounterOrderInputA008ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignStockCounterOrderInputService",
                "orderConfirmA008",
                new TypeReference<DataList<IfaForeignStockCounterOrderInputA008ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaForeignStockCounterOrderInputA008ApiResponse> apiRes = new DataList<IfaForeignStockCounterOrderInputA008ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignStockCounterOrderInputGetNewMainSiteA019
     * アクションID：A019
     * アクション名：新メインサイトの設定
     * APIリクエスト：IfaForeignStockCounterOrderInputA019ApiRequest
     * Apiレスポンス：IfaForeignStockCounterOrderInputA019ApiResponse
     *
     * @param apiReq {@code IfaForeignStockCounterOrderInputA019ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaForeignStockCounterOrderInputGetNewMainSiteA019")
    public String getNewMainSiteA019(@RequestBody IfaForeignStockCounterOrderInputA019ApiRequest apiReq)
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
        DataList<IfaForeignStockCounterOrderInputA019ApiResponse> apiRes = new DataList<IfaForeignStockCounterOrderInputA019ApiResponse>();
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
