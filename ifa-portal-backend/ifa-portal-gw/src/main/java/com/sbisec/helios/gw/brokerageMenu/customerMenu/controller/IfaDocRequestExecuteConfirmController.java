package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestExecuteConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestExecuteConfirmA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestExecuteConfirmA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestExecuteConfirmA005ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestExecuteConfirmA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestExecuteConfirmA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestExecuteConfirmA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestExecuteConfirmA005ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0704-02_1 画面名：書類請求確認
 *
 * @author xin.huang
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0704-02_1", screenNumber = "")
public class IfaDocRequestExecuteConfirmController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDocRequestExecuteConfirmA002
     * アクションID：A002
     * アクション名：書類請求登録
     * APIリクエスト：IfaDocRequestExecuteConfirmA002ApiRequest
     * Apiレスポンス：IfaDocRequestExecuteConfirmA002ApiResponse
     * Dtoリクエスト：IfaDocRequestExecuteConfirmA002RequestDto
     * Dtoレスポンス：IfaDocRequestExecuteConfirmA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求登録の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDocRequestExecuteConfirmA002")
    public String executeConfirmA002(@RequestBody IfaDocRequestExecuteConfirmA002ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaDocRequestExecuteConfirmA002RequestDto appReq = new IfaDocRequestExecuteConfirmA002RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaDocRequestExecuteConfirmA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDocRequestExecuteConfirmService", "executeConfirmA002",
                new TypeReference<DataList<IfaDocRequestExecuteConfirmA002ResponseDto>>() {
                }, appReq);
        DataList<IfaDocRequestExecuteConfirmA002ApiResponse> apiRes = new DataList<IfaDocRequestExecuteConfirmA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDocBMRequestExecuteConfirmA005
     * アクションID：A005
     * アクション名：BM交付情報登録
     * APIリクエスト：IfaDocRequestExecuteConfirmA005ApiRequest
     * Apiレスポンス：IfaDocRequestExecuteConfirmA005ApiResponse
     * Dtoリクエスト：IfaDocRequestExecuteConfirmA005RequestDto
     * Dtoレスポンス：IfaDocRequestExecuteConfirmA005ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception BM交付登録の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDocBMRequestExecuteConfirmA005")
    public String executeConfirmA005(@RequestBody IfaDocRequestExecuteConfirmA005ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaDocRequestExecuteConfirmA005RequestDto appReq = new IfaDocRequestExecuteConfirmA005RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaDocRequestExecuteConfirmA005ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDocRequestExecuteConfirmService", "executeConfirmA005",
                new TypeReference<DataList<IfaDocRequestExecuteConfirmA005ResponseDto>>() {
                }, appReq);
        DataList<IfaDocRequestExecuteConfirmA005ApiResponse> apiRes = new DataList<IfaDocRequestExecuteConfirmA005ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
}
