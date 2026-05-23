package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferConfirmA001aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferConfirmA001bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferConfirmA001aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferConfirmA001bDtoResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginCollateralTransferConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginCollateralTransferConfirmA001ApiResponse;

/**
 * 画面ID：SUB0202_0305-01_2
 * 画面名：米株信用代用振替確認
 *
 * @author SCSK川崎
 *
   2024/03/19 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0305-01_2", screenNumber = "")
public class IfaForeignMarginCollateralTransferConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginCollateralTransferConfirmTransferInstructionA001
     * アクションID：A001
     * アクション名：振替指示
     * APIリクエスト：IfaForeignMarginCollateralTransferConfirmA001ApiRequest
     * Apiレスポンス：IfaForeignMarginCollateralTransferConfirmA001ApiResponse
     * Dtoリクエスト：IfaForeignMarginCollateralTransferConfirmA001aDtoRequest, 
     *           IfaForeignMarginCollateralTransferConfirmA001bDtoRequest
     * Dtoレスポンス：IfaForeignMarginCollateralTransferConfirmA001aDtoResponse, 
     *           IfaForeignMarginCollateralTransferConfirmA001bDtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaForeignMarginCollateralTransferConfirmTransferInstructionA001")
    public String transferInstructionA001(@RequestBody IfaForeignMarginCollateralTransferConfirmA001ApiRequest apiReq)
            throws Exception {
        
        IfaForeignMarginCollateralTransferConfirmA001aDtoRequest appReqa = 
                new IfaForeignMarginCollateralTransferConfirmA001aDtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqa, apiReq);
        
        DataList<IfaForeignMarginCollateralTransferConfirmA001aDtoResponse> appResa = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginCollateralTransferConfirmService", "transferInstructionA001a",
                new TypeReference<DataList<IfaForeignMarginCollateralTransferConfirmA001aDtoResponse>>() {
                }, appReqa);
        
        DataList<IfaForeignMarginCollateralTransferConfirmA001ApiResponse> apiRes = 
                new DataList<IfaForeignMarginCollateralTransferConfirmA001ApiResponse>();
        if (appResa.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            BeanUtils.copyProperties(apiRes, appResa);
            
            return jc.toString(apiRes);
        }
        
        IfaForeignMarginCollateralTransferConfirmA001bDtoRequest appReqb = 
                new IfaForeignMarginCollateralTransferConfirmA001bDtoRequest();
        BeanUtils.copyProperties(appReqb, apiReq);
        appReqb.setStockTransferNo(appResa.getDataList().get(0).getStockTransferNo());
        DataList<IfaForeignMarginCollateralTransferConfirmA001bDtoResponse> appResb = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginCollateralTransferConfirmService", "transferInstructionA001b",
                new TypeReference<DataList<IfaForeignMarginCollateralTransferConfirmA001bDtoResponse>>() {
                }, appReqb);
        
        BeanUtils.copyProperties(apiRes, appResb);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
