package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawExecuteConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawExecuteConfirmA001ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaWithdrawExecuteConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaWithdrawExecuteConfirmA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0601-02_1 画面名：出金確認
 *
 * @author xin.huang
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0601-02_1", screenNumber = "")
public class IfaWithdrawExecuteConfirmController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaWithdrawExecuteConfirmA001
     * アクションID：A001
     * アクション名：出金実行
     * Apiレスポンス：IfaWithdrawExecuteConfirmA001ApiResponse
     * Apiリクエスト：IfaWithdrawExecuteConfirmA001ApiRequest
     * Dtoレスポンス：IfaWithdrawExecuteConfirmA001ResponseDto
     * Dtoリクエスト：IfaWithdrawExecuteConfirmA001RequestDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 出金実行の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaWithdrawExecuteConfirmA001")
    public String executeConfirmA001(@RequestBody IfaWithdrawExecuteConfirmA001ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaWithdrawExecuteConfirmA001RequestDto appReq = new IfaWithdrawExecuteConfirmA001RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaWithdrawExecuteConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaWithdrawExecuteConfirmService", "executeConfirmA001",
                new TypeReference<DataList<IfaWithdrawExecuteConfirmA001ResponseDto>>() {
                }, appReq);
        DataList<IfaWithdrawExecuteConfirmA001ApiResponse> apiRes = new DataList<IfaWithdrawExecuteConfirmA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
}
