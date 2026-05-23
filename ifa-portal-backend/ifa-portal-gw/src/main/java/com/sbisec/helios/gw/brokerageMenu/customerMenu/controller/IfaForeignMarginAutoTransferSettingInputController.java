package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingInputA002ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginAutoTransferSettingInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginAutoTransferSettingInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginAutoTransferSettingInputA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginAutoTransferSettingInputA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0306-01_1
 * 画面名：米株信用自動振替設定入力
 * @author <author-name>
 *
 * 2023/11/10 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0306-01_1", screenNumber = "")
public class IfaForeignMarginAutoTransferSettingInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginAutoTransferSettingInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaForeignMarginAutoTransferSettingInputA001ApiRequest
     * Apiレスポンス：IfaForeignMarginAutoTransferSettingInputA001ApiResponse
     * Dtoリクエスト：IfaForeignMarginAutoTransferSettingInputA001DtoRequest
     * Dtoレスポンス：IfaForeignMarginAutoTransferSettingInputA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginAutoTransferSettingInputInitializeA001")
    public String initializeA001(@RequestBody IfaForeignMarginAutoTransferSettingInputA001ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaForeignMarginAutoTransferSettingInputA001RequestDto appReq = new IfaForeignMarginAutoTransferSettingInputA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaForeignMarginAutoTransferSettingInputA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginAutoTransferSettingInputService", "initializeA001",
                new TypeReference<DataList<IfaForeignMarginAutoTransferSettingInputA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginAutoTransferSettingInputA001ApiResponse> apiRes = new DataList<IfaForeignMarginAutoTransferSettingInputA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginAutoTransferSettingInputSettingConfirmA002
     * アクションID：A002
     * アクション名：設定確認
     * APIリクエスト：IfaForeignMarginAutoTransferSettingInputA002ApiRequest
     * Apiレスポンス：IfaForeignMarginAutoTransferSettingInputA002ApiResponse
     * Dtoリクエスト：IfaForeignMarginAutoTransferSettingInputA002DtoRequest
     * Dtoレスポンス：IfaForeignMarginAutoTransferSettingInputA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginAutoTransferSettingInputSettingConfirmA002")
    public String settingConfirmA002(@RequestBody IfaForeignMarginAutoTransferSettingInputA002ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaForeignMarginAutoTransferSettingInputA002RequestDto appReq = new IfaForeignMarginAutoTransferSettingInputA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaForeignMarginAutoTransferSettingInputA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginAutoTransferSettingInputService", "settingConfirmA002",
                new TypeReference<DataList<IfaForeignMarginAutoTransferSettingInputA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginAutoTransferSettingInputA002ApiResponse> apiRes = new DataList<IfaForeignMarginAutoTransferSettingInputA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
