package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaStructuredBondBrandInfoA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaStructuredBondBrandInfoA001DtoResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaStructuredBondBrandInfoA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaStructuredBondBrandInfoA001ApiResponse;

/**
 * 画面ID：SUB020302_0104-02
 * 画面名：仕組債銘柄情報
 *
 * @author SCSK川崎
 2024/06/11 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020302_0104-02", screenNumber = "")
public class IfaStructuredBondBrandInfoController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
	
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaStructuredBondBrandInfoInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaStructuredBondBrandInfoA001ApiRequest
     * Apiレスポンス：IfaStructuredBondBrandInfoA001ApiResponse
     * Dtoリクエスト：IfaStructuredBondBrandInfoA001DtoRequest
     * Dtoレスポンス：IfaStructuredBondBrandInfoA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaStructuredBondBrandInfoInitializeA001")
    public String initializeA001(@RequestBody IfaStructuredBondBrandInfoA001ApiRequest apiReq) throws Exception {

        IfaStructuredBondBrandInfoA001DtoRequest appReq = new IfaStructuredBondBrandInfoA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaStructuredBondBrandInfoA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaStructuredBondBrandInfoService", "initializeA001",
                new TypeReference<DataList<IfaStructuredBondBrandInfoA001DtoResponse>>() {}, appReq);
        
        DataList<IfaStructuredBondBrandInfoA001ApiResponse> apiRes = 
                new DataList<IfaStructuredBondBrandInfoA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

