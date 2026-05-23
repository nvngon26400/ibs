package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaWithdrawCancelConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaWithdrawCancelConfirmA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0601-03_1 画面名：取消確認
 *
 * @author xin.huang
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0601-03_1", screenNumber = "")
public class IfaWithdrawCancelConfirmController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaWithdrawCancelConfirmA001
     * アクションID：A001
     * アクション名：取消実行
     * Apiレスポンス：IfaWithdrawCancelConfirmA001ApiResponse
     * Apiリクエスト：IfaWithdrawCancelConfirmA001ApiRequest
     * Dtoレスポンス：IfaWithdrawCancelConfirmA001ResponseDto
     * Dtoリクエスト：IfaWithdrawCancelConfirmA001RequestDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 取消実行の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaWithdrawCancelConfirmA001")
    public String cancelConfirmA001(@RequestBody IfaWithdrawCancelConfirmA001ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaWithdrawCancelConfirmA001RequestDto appReq = new IfaWithdrawCancelConfirmA001RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaWithdrawCancelConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaWithdrawCancelConfirmService", "cancelConfirmA001",
                new TypeReference<DataList<IfaWithdrawCancelConfirmA001ResponseDto>>() {
                }, appReq);
        DataList<IfaWithdrawCancelConfirmA001ApiResponse> apiRes = new DataList<IfaWithdrawCancelConfirmA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
}