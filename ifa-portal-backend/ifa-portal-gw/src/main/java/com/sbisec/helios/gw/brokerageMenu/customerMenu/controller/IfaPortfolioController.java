package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaPortfolioA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaPortfolioA001ApiResponse;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * 2023/12/26 新規作成
 *
 * @author SCSK 江口
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0101-01", screenNumber = "")
public class IfaPortfolioController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaPortfolioInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaPortfolioA001ApiRequest
     * Apiレスポンス：IfaPortfolioA001ApiResponse
     * Dtoリクエスト：IfaPortfolioA001RequestDto
     * Dtoレスポンス：IfaPortfolioA001ResponseDto
     *
     * @param apiReq リクエストボディ(POST)
     * @return 画面の初期化に必要な情報。
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaPortfolioInitializeA001")
    public String initializeA001(@RequestBody IfaPortfolioA001ApiRequest apiReq) throws Exception {

        // 顧客共通情報をリクエストスコープにセットする
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaPortfolioA001RequestDto appReq = new IfaPortfolioA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaPortfolioA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaPortfolioService",
                "initializeA001",
                new TypeReference<DataList<IfaPortfolioA001ResponseDto>>() { }, 
                appReq
        );

        // サービスからのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaPortfolioA001ApiResponse> apiRes = new DataList<IfaPortfolioA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 結果をJSON形式で返却
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }

}
