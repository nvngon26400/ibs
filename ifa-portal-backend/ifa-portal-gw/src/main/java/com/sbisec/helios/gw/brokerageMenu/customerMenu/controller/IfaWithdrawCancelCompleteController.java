package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawCancelCompleteA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawCancelCompleteA002ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaWithdrawCancelCompleteA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaWithdrawCancelCompleteA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0601-03_2 画面名：出金取消完了
 *
 * @author xin.huang
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0601-03_2", screenNumber = "")
public class IfaWithdrawCancelCompleteController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/IfaWithdrawCancelCompleteA002
     * アクションID：A001
     * アクション名：取消実行
     * Apiレスポンス：IfaWithdrawCancelCompleteA002ApiResponse
     * Apiリクエスト：IfaWithdrawCancelCompleteA002ApiRequest
     * Dtoレスポンス：IfaWithdrawCancelCompleteA002ResponseDto
     * Dtoリクエスト：IfaWithdrawCancelCompleteA002RequestDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 取消実行の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/IfaWithdrawCancelCompleteA002")
    public String cancelCompleteA002(@RequestBody IfaWithdrawCancelCompleteA002ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaWithdrawCancelCompleteA002RequestDto appReq = new IfaWithdrawCancelCompleteA002RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaWithdrawCancelCompleteA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaWithdrawCancelCompleteService", "cancelCompleteA002",
                new TypeReference<DataList<IfaWithdrawCancelCompleteA002ResponseDto>>() {
                }, appReq);
        DataList<IfaWithdrawCancelCompleteA002ApiResponse> apiRes = new DataList<IfaWithdrawCancelCompleteA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
}