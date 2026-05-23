package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignAmountUnpaidOverdraftAlertListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignAmountUnpaidOverdraftAlertListA002ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaForeignAmountUnpaidOverdraftAlertListA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaForeignAmountUnpaidOverdraftAlertListA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB020301_01-03
 * 画面名：外貨未入金・赤残アラート一覧
 *
 * @author BASE李
 2024/06/12 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020301_01-03", screenNumber = "")
public class IfaForeignAmountUnpaidOverdraftAlertListController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaForeignAmountUnpaidOverdraftAlertListDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaForeignAmountUnpaidOverdraftAlertListA002ApiRequest
     * Apiレスポンス：IfaForeignAmountUnpaidOverdraftAlertListA002ApiResponse
     * Dtoリクエスト：IfaForeignAmountUnpaidOverdraftAlertListA002RequestDto
     * Dtoレスポンス：IfaForeignAmountUnpaidOverdraftAlertListA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaForeignAmountUnpaidOverdraftAlertListDisplayA002")
    public String displayA002(@RequestBody IfaForeignAmountUnpaidOverdraftAlertListA002ApiRequest apiReq) throws Exception {

        IfaForeignAmountUnpaidOverdraftAlertListA002RequestDto appReq = new IfaForeignAmountUnpaidOverdraftAlertListA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaForeignAmountUnpaidOverdraftAlertListA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaForeignAmountUnpaidOverdraftAlertListService",
                "displayA002", new TypeReference<DataList<IfaForeignAmountUnpaidOverdraftAlertListA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignAmountUnpaidOverdraftAlertListA002ApiResponse> apiRes = new DataList<IfaForeignAmountUnpaidOverdraftAlertListA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

