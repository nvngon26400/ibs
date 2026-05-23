package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaStockCertificateDetailA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaStockCertificateDetailA001DtoResponse;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaStockCertificateDetailA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaStockCertificateDetailA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

/**
 * 画面ID：SUB0202_0703-03
 * 画面名：株券詳細
 *
 * @author SBI大連 董
 *2025/03/20 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
// screenNumber TODO
@ScreenId(groupId = "MAIN02", id = "SUB0202_0703-03", screenNumber = "")
public class IfaStockCertificateDetailController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaStockCertificateDetailInitializeA001
     * アクションID：A001
     * アクション名：初期化 
     * APIリクエスト：IfaStockCertificateDetailA001ApiRequest
     * APIレスポンス：IfaStockCertificateDetailA001ApiResponse
     * DTOリクエスト：IfaStockCertificateDetailA001DtoRequest
     * DTOレスポンス：IfaStockCertificateDetailA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaStockCertificateDetailInitializeA001")
    public String initializeA001(@RequestBody IfaStockCertificateDetailA001ApiRequest apiReq) throws Exception {

        IfaStockCertificateDetailA001DtoRequest appReq = new IfaStockCertificateDetailA001DtoRequest();

        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaStockCertificateDetailA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaStockCertificateDetailService", "initializeA001",
                new TypeReference<DataList<IfaStockCertificateDetailA001DtoResponse>>() {
                }, appReq);

        DataList<IfaStockCertificateDetailA001ApiResponse> apiRes = new DataList<IfaStockCertificateDetailA001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }
    @Override
    protected String getFirstViewName() {

        return null;
    }

}
