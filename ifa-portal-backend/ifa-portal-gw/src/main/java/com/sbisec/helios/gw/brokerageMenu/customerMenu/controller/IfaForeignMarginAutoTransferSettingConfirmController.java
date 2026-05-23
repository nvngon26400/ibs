package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingConfirmA001bRequestDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginAutoTransferSettingConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginAutoTransferSettingConfirmA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0306-01_2
 * 画面名：米株信用自動振替設定確認
 * @author 卞
 *
 * 2023/11/10 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0306-01_2", screenNumber = "")
public class IfaForeignMarginAutoTransferSettingConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginAutoTransferSettingConfirmSettingInstructionA001
     * アクションID：A001
     * アクション名：設定指示
     * APIリクエスト：IfaForeignMarginAutoTransferSettingConfirmA001ApiRequest
     * Apiレスポンス：IfaForeignMarginAutoTransferSettingConfirmA001ApiResponse
     * Dtoリクエスト：IfaForeignMarginAutoTransferSettingConfirmA001DtoRequest
     * Dtoレスポンス：IfaForeignMarginAutoTransferSettingConfirmA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginAutoTransferSettingConfirmSettingInstructionA001")
    public String settingInstructionA001(@RequestBody IfaForeignMarginAutoTransferSettingConfirmA001ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaForeignMarginAutoTransferSettingConfirmA001aRequestDto appReqa = new IfaForeignMarginAutoTransferSettingConfirmA001aRequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqa, apiReq);
        
        DataList<IfaForeignMarginAutoTransferSettingConfirmA001aResponseDto> appResa = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginAutoTransferSettingConfirmService", "settingInstructionA001a",
                new TypeReference<DataList<IfaForeignMarginAutoTransferSettingConfirmA001aResponseDto>>() {
                }, appReqa);
        
        DataList<IfaForeignMarginAutoTransferSettingConfirmA001ApiResponse> apiRes = new DataList<IfaForeignMarginAutoTransferSettingConfirmA001ApiResponse>();
        if (appResa.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            BeanUtils.copyProperties(apiRes, appResa);
            
            return jc.toString(apiRes);
        }
        
        IfaForeignMarginAutoTransferSettingConfirmA001bRequestDto appReqb = new IfaForeignMarginAutoTransferSettingConfirmA001bRequestDto();
        BeanUtils.copyProperties(appReqb, apiReq);
        appReqb.setAutoTransferSettingNo(appResa.getDataList().get(0).getAutoTransferSettingNo());
        DataList<IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto> appResb = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginAutoTransferSettingConfirmService", "settingInstructionA001b",
                new TypeReference<DataList<IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto>>() {
                }, appReqb);
        
        BeanUtils.copyProperties(apiRes, appResb);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
