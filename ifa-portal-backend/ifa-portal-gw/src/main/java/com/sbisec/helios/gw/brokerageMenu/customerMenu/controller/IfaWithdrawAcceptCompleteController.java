package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawAcceptCompleteA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawAcceptCompleteA002ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaWithdrawAcceptCompleteA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaWithdrawAcceptCompleteA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202-0601-02_2 画面名：出金完了
 *
 * @author xin.huang
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0601-02_2", screenNumber = "")
public class IfaWithdrawAcceptCompleteController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/IfaWithdrawAcceptCompleteA002
     * アクションID：A002
     * アクション名：出金完了
     * Apiレスポンス：IfaWithdrawAcceptCompleteA002ApiResponse
     * Apiリクエスト：IfaWithdrawAcceptCompleteA002ApiRequest
     * Dtoレスポンス：IfaWithdrawAcceptCompleteA002ResponseDto
     * Dtoリクエスト：IfaWithdrawAcceptCompleteA002RequestDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 取消実行の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/IfaWithdrawAcceptCompleteA002")
    public String acceptCompleteA002(@RequestBody IfaWithdrawAcceptCompleteA002ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaWithdrawAcceptCompleteA002RequestDto appReq = new IfaWithdrawAcceptCompleteA002RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaWithdrawAcceptCompleteA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaWithdrawAcceptCompleteService", "acceptCompleteA002",
                new TypeReference<DataList<IfaWithdrawAcceptCompleteA002ResponseDto>>() {
                }, appReq);
        DataList<IfaWithdrawAcceptCompleteA002ApiResponse> apiRes = new DataList<IfaWithdrawAcceptCompleteA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
}