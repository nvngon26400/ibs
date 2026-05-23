package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestCancelConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestCancelConfirmA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestCancelConfirmA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestCancelConfirmA005ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestCancelConfirmA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestCancelConfirmA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestCancelConfirmA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestCancelConfirmA005ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0704-03_1 画面名：書類請求取消
 *
 * @author xin.huang
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0704-03_1", screenNumber = "")
public class IfaDocRequestCancelConfirmController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDocRequestCancelConfirmA002
     * アクションID：A002
     * アクション名：書類請求取消
     * APIリクエスト：IfaDocRequestCancelConfirmA002ApiRequest
     * Apiレスポンス：IfaDocRequestCancelConfirmA002ApiResponse
     * Dtoリクエスト：IfaDocRequestCancelConfirmA002RequestDto
     * Dtoレスポンス：IfaDocRequestCancelConfirmA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求取消の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDocRequestCancelConfirmA002")
    public String cancelConfirmA002(@RequestBody IfaDocRequestCancelConfirmA002ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaDocRequestCancelConfirmA002RequestDto appReq = new IfaDocRequestCancelConfirmA002RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaDocRequestCancelConfirmA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDocRequestCancelConfirmService", "cancelConfirmA002",
                new TypeReference<DataList<IfaDocRequestCancelConfirmA002ResponseDto>>() {
                }, appReq);
        DataList<IfaDocRequestCancelConfirmA002ApiResponse> apiRes = new DataList<IfaDocRequestCancelConfirmA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDocBMRequestCancelConfirmA005
     * アクションID：A005
     * アクション名：BM交付取消
     * APIリクエスト：IfaDocRequestCancelConfirmA005ApiRequest
     * Apiレスポンス：IfaDocRequestCancelConfirmA005ApiResponse
     * Dtoリクエスト：IfaDocRequestCancelConfirmA005RequestDto
     * Dtoレスポンス：IfaDocRequestCancelConfirmA005ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求取消の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDocBMRequestCancelConfirmA005")
    public String cancelConfirmA005(@RequestBody IfaDocRequestCancelConfirmA005ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaDocRequestCancelConfirmA005RequestDto appReq = new IfaDocRequestCancelConfirmA005RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaDocRequestCancelConfirmA005ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDocRequestCancelConfirmService", "cancelConfirmA005",
                new TypeReference<DataList<IfaDocRequestCancelConfirmA005ResponseDto>>() {
                }, appReq);
        DataList<IfaDocRequestCancelConfirmA005ApiResponse> apiRes = new DataList<IfaDocRequestCancelConfirmA005ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
}
