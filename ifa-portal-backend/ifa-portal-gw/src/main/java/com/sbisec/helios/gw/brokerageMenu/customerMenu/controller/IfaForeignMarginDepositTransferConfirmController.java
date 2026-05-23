package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferConfirmA001bResponseDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginDepositTransferConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginDepositTransferConfirmA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0304-01_2
 * 画面名：米株信用保証金振替確認
 *
 * @author
 *
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0304-01_2", screenNumber = "")
public class IfaForeignMarginDepositTransferConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginDepositTransferConfirmTransferInstructionA001
     * アクションID：A001
     * アクション名：振替指示
     * APIリクエスト：IfaForeignMarginDepositTransferConfirmA001ApiRequest
     * Apiレスポンス：IfaForeignMarginDepositTransferConfirmA001ApiResponse
     * Dtoリクエスト：IfaForeignMarginDepositTransferConfirmA001DtoRequest
     * Dtoレスポンス：IfaForeignMarginDepositTransferConfirmA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginDepositTransferConfirmTransferInstructionA001")
    public String transferInstructionA001(@RequestBody IfaForeignMarginDepositTransferConfirmA001ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaForeignMarginDepositTransferConfirmA001aRequestDto appReqA = new IfaForeignMarginDepositTransferConfirmA001aRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqA, apiReq);
        
        DataList<IfaForeignMarginDepositTransferConfirmA001aResponseDto> appResA = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginDepositTransferConfirmService", "transferInstructionA001a",
                new TypeReference<DataList<IfaForeignMarginDepositTransferConfirmA001aResponseDto>>() {
                }, appReqA);
        
        // エラー
        if (appResA.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            DataList<IfaForeignMarginDepositTransferConfirmA001ApiResponse> apiRes = new DataList<IfaForeignMarginDepositTransferConfirmA001ApiResponse>();
            
            BeanUtils.copyProperties(apiRes, appResA);
            
            return jc.toString(apiRes);
        }
        
        IfaForeignMarginDepositTransferConfirmA001bRequestDto appReqB = new IfaForeignMarginDepositTransferConfirmA001bRequestDto();
        // 委託保証金振替区分
        appReqB.setEntrustDepositTransferClassification(appResA.get(0).getEntrustDepositTransferClassification());
        // 指示金額
        appReqB.setDestinationAmount(appResA.get(0).getDestinationAmount());
        // 通貨コード
        appReqB.setCurrencyCode(appResA.get(0).getCurrencyCode());
        // IFA保証金振替指示番号
        appReqB.setDepositTransferNo(appResA.get(0).getDepositTransferNo());
        
        DataList<IfaForeignMarginDepositTransferConfirmA001bResponseDto> appResB = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginDepositTransferConfirmService", "transferInstructionA001b",
                new TypeReference<DataList<IfaForeignMarginDepositTransferConfirmA001bResponseDto>>() {
                }, appReqB);
        
        DataList<IfaForeignMarginDepositTransferConfirmA001ApiResponse> apiRes = new DataList<IfaForeignMarginDepositTransferConfirmA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appResB);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
