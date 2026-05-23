package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaExternalLinkDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaExternalLinkDtoResponse;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamRequestDto;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamResponseDto;
import com.sbisec.helios.ap.common.dto.LinkUrlDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaExternalLinkA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaExternalLinkA003ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaExternalLinkApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaExternalLinkApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

/**
 * 外部リンク
 * 2025/05/12 新規作成
 *
 * @author 大連 葉
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_08-01", screenNumber = "")
public class IfaExternalLinkController extends BaseController{

    JsonConverter jc = JsonConverter.getInstance();
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaExternalLinkInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaExternalLinkApiRequest
     * Apiレスポンス：IfaExternalLinkApiResponse
     * Dtoリクエスト：IfaExternalLinkDtoRequest
     * Dtoレスポンス：IfaExternalLinkDtoResponse
     * 
     * @param apiReq {@code IfaExternalLinkApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaExternalLinkInitializeA001")
    public String initializeA001(@RequestBody IfaExternalLinkApiRequest apiReq) throws Exception{
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaExternalLinkDtoRequest appReq = new IfaExternalLinkDtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaExternalLinkDtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaExternalLinkService", "initializeA001",
                new TypeReference<DataList<IfaExternalLinkDtoResponse>>() {
                }, appReq);
        DataList<IfaExternalLinkApiResponse> apiRes = new DataList<IfaExternalLinkApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaExternalLinkGetNewMainSiteA003
     * アクションID：A003
     * アクション名：新メインサイトの設定
     * APIリクエスト：IfaExternalLinkA003ApiRequest
     * Apiレスポンス：IfaExternalLinkA003ApiResponse
     * 
     * @param apiReq {@code IfaExternalLinkA003ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaExternalLinkGetNewMainSiteA003")
    public String getNewMainSiteA003(@RequestBody IfaExternalLinkA003ApiRequest apiReq) throws Exception{

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
        DataList<IfaExternalLinkA003ApiResponse> apiRes = new DataList<IfaExternalLinkA003ApiResponse>();
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
