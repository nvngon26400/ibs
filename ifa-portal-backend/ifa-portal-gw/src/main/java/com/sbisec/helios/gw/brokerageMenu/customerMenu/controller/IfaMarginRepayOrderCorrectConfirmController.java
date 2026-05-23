package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectConfirmA001bResponseDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderCorrectConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderCorrectConfirmA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0212-06_2
 * 画面名：信用返済注文確認
 * 2024/05/24 新規作成
 *
 * @author SCSK 笹倉 秀行
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0212-06_2", screenNumber = "")
public class IfaMarginRepayOrderCorrectConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginRepayOrderCorrectConfirmCorrectionOrderA001
     * アクションID：A001
     * アクション名：訂正発注
     * APIリクエスト：IfaMarginRepayOrderCorrectConfirmA001ApiRequest
     * Apiレスポンス：IfaMarginRepayOrderCorrectConfirmA001ApiResponse
     * Dtoリクエスト：IfaMarginRepayOrderCorrectConfirmA001aRequestDto
     * Dtoリクエスト：IfaMarginRepayOrderCorrectConfirmA001bRequestDto
     * Dtoレスポンス：IfaMarginRepayOrderCorrectConfirmA001aResponseDto
     * Dtoレスポンス：IfaMarginRepayOrderCorrectConfirmA001bResponseDto
     *
     * @param apiReq APIリクエスト
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaMarginRepayOrderCorrectConfirmCorrectionOrderA001")
    public String correctionOrderA001(@RequestBody IfaMarginRepayOrderCorrectConfirmA001ApiRequest apiReq)
            throws Exception {
        
        final IfaMarginRepayOrderCorrectConfirmA001aRequestDto appReqA = new IfaMarginRepayOrderCorrectConfirmA001aRequestDto();
        BeanUtils.copyProperties(appReqA, apiReq);
        final DataList<IfaMarginRepayOrderCorrectConfirmA001aResponseDto> appResA = ApiRequestUtil.invoke(
                "cmpIfaMarginRepayOrderCorrectConfirmService", "correctionOrderA001a",
                new TypeReference<DataList<IfaMarginRepayOrderCorrectConfirmA001aResponseDto>>() {
                }, appReqA);
        if (appResA.getErrorLevel() < ErrorLevel.SUCCESS.getId()) {
            return jc.toString(appResA);
        }
        
        final IfaMarginRepayOrderCorrectConfirmA001bRequestDto appReqB = new IfaMarginRepayOrderCorrectConfirmA001bRequestDto();
        appReqB.setIfaOrderNo(appResA.getDataList().get(0).getIfaOrderNo());
        appReqB.setIfaOrderSubNo(appResA.getDataList().get(0).getIfaOrderSubNo());
        appReqB.setRequest(appReqA);
        final DataList<IfaMarginRepayOrderCorrectConfirmA001bResponseDto> appResB = ApiRequestUtil.invoke(
                "cmpIfaMarginRepayOrderCorrectConfirmService", "correctionOrderA001b",
                new TypeReference<DataList<IfaMarginRepayOrderCorrectConfirmA001bResponseDto>>() {
                }, appReqB);
        
        final DataList<IfaMarginRepayOrderCorrectConfirmA001ApiResponse> apiRes = new DataList<IfaMarginRepayOrderCorrectConfirmA001ApiResponse>();
        apiRes.setErrorLevel(appResB.getErrorLevel());
        apiRes.setMessage(appResB.getMessage());
        apiRes.setRequestedTime(appResB.getRequestedTime());
        apiRes.setMaxRownum(appResB.getMaxRownum());
        apiRes.setTitle(appResB.getTitle());
        apiRes.setTotalSize(appResB.getTotalSize());
        apiRes.setReturnCode(appResB.getReturnCode());
        if (CollectionUtils.isNotEmpty(appResB.getDataList())) {
            apiRes.setDataList(jc.toObject(jc.toString(appResB.getDataList()),
                    new TypeReference<List<IfaMarginRepayOrderCorrectConfirmA001ApiResponse>>() {
                    }));
        }
        return jc.toString(apiRes);
    }
}
