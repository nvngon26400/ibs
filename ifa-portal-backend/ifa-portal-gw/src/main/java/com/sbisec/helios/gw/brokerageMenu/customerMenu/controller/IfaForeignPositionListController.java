package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignPositionListA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignPositionListA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignPositionListA002DtoRequest;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignPositionListA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignPositionListA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignPositionListA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignPositionListA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_010203-01
 * 画面名：米株建玉一覧
 *
 * @author SCSK
 *
 *     2023/11/01 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010203-01", screenNumber = "")
public class IfaForeignPositionListController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaForeignPositionListInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaForeignPositionListA001ApiRequest
     * Apiレスポンス：IfaForeignPositionListA001ApiResponse
     * Dtoリクエスト：IfaForeignPositionListA001DtoRequest
     * Dtoレスポンス：IfaForeignPositionListA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignPositionListInitializeA001")
    public String initializeA001(@RequestBody IfaForeignPositionListA001ApiRequest apiReq) throws Exception {
        
        IfaForeignPositionListA001DtoRequest appReq = new IfaForeignPositionListA001DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaForeignPositionListA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "IfaForeignPositionListService", "initializeA001",
                new TypeReference<DataList<IfaForeignPositionListA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaForeignPositionListA001ApiResponse> apiRes = new DataList<IfaForeignPositionListA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaForeignPositionListInitializeA001
     * アクションID：A002
     * アクション名：建玉返済
     * APIリクエスト：IfaForeignPositionListA002ApiRequest
     * Apiレスポンス：IfaForeignPositionListA002ApiResponse
     * Dtoリクエスト：IfaForeignPositionListA002DtoRequest
     * Dtoレスポンス：IfaForeignPositionListA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignPositionListPositionRepaymentA002")
    public String positionRepaymentA002(@RequestBody IfaForeignPositionListA002ApiRequest apiReq) throws Exception {
        
        IfaForeignPositionListA002DtoRequest appReq = new IfaForeignPositionListA002DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<Object> appRes = ApiRequestUtil.invoke(
                "IfaForeignPositionListService", "positionRepaymentA002",
                new TypeReference<DataList<Object>>() {
                }, appReq);
        
        DataList<IfaForeignPositionListA002ApiResponse> apiRes = new DataList<IfaForeignPositionListA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
