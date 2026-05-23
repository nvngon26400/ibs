package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA010aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA010aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA010bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeOrderCancelConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeOrderCancelConfirmA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeOrderCancelConfirmA010ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginTradeOrderCancelConfirmA010ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0303-03_1
 * 画面名：米株信用取引注文取消確認
 * @author <author-name>
 *
 * 2023/09/16 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0303-03_1", screenNumber = "")
public class IfaForeignMarginTradeOrderCancelConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeOrderCancelConfirmInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaForeignMarginTradeOrderCancelConfirmA001ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeOrderCancelConfirmA001ApiResponse
     * Dtoリクエスト：IfaForeignMarginTradeOrderCancelConfirmA001DtoRequest
     * Dtoレスポンス：IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginTradeOrderCancelConfirmInitializeA001")
    public String initializeA001(@RequestBody IfaForeignMarginTradeOrderCancelConfirmA001ApiRequest apiReq)
            throws Exception {
        
        IfaForeignMarginTradeOrderCancelConfirmA001DtoRequest appReq = new IfaForeignMarginTradeOrderCancelConfirmA001DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeOrderCancelConfirmService", "initializeA001",
                new TypeReference<DataList<IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaForeignMarginTradeOrderCancelConfirmA001ApiResponse> apiRes = new DataList<IfaForeignMarginTradeOrderCancelConfirmA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeOrderCancelConfirmOrderCancellationA010
     * アクションID：A010
     * アクション名：注文取消
     * APIリクエスト：IfaForeignMarginTradeOrderCancelConfirmA010ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeOrderCancelConfirmA010ApiResponse
     * Dtoリクエスト：IfaForeignMarginTradeOrderCancelConfirmA010DtoRequest
     * Dtoレスポンス：IfaForeignMarginTradeOrderCancelConfirmA010DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginTradeOrderCancelConfirmOrderCancellationA010")
    public String orderCancellationA010(@RequestBody IfaForeignMarginTradeOrderCancelConfirmA010ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        //返却値
        DataList<IfaForeignMarginTradeOrderCancelConfirmA010ApiResponse> apiRes = new DataList<IfaForeignMarginTradeOrderCancelConfirmA010ApiResponse>();
        
        //トランザクション分割(A)
        IfaForeignMarginTradeOrderCancelConfirmA010aDtoRequest appReqA = new IfaForeignMarginTradeOrderCancelConfirmA010aDtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqA, apiReq);
        
        DataList<IfaForeignMarginTradeOrderCancelConfirmA010aDtoResponse> appResA = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeOrderCancelConfirmService", "orderCancellationA010a",
                new TypeReference<DataList<IfaForeignMarginTradeOrderCancelConfirmA010aDtoResponse>>() {
                }, appReqA);
        
        //正常終了していなかったら、エラー返却のためAのDatalistを返却する
        if (appResA.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            BeanUtils.copyProperties(apiRes, appResA);
            return jc.toString(apiRes);
        }
        
        //トランザクション分割(B)
        IfaForeignMarginTradeOrderCancelConfirmA010bDtoRequest appReqB = new IfaForeignMarginTradeOrderCancelConfirmA010bDtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqB, apiReq);
        appReqB.setIfaOrderNo(appResA.get(0).getIfaOrderNo());
        appReqB.setIfaOrderSubNo(appResA.get(0).getIfaOrderSubNo());
        
        DataList<IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse> appResB = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginTradeOrderCancelConfirmService", "orderCancellationA010b",
                new TypeReference<DataList<IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse>>() {
                }, appReqB);
        
        BeanUtils.copyProperties(apiRes, appResB);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
