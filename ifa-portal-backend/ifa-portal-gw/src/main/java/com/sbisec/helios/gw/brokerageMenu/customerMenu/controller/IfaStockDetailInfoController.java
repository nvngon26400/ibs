package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaStockDetailInfoA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaStockDetailInfoA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaStockDetailInfoA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaStockDetailInfoA002DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaStockDetailInfoA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaStockDetailInfoA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaStockDetailInfoA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaStockDetailInfoA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

/**
 * 画面ID：SUB0202_0208-02 画面名：株式詳細情報
 * 
 * @author <author-name>
 *
 *         2023/07/31 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0208-02", screenNumber = "")
public class IfaStockDetailInfoController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaStockDetailInfoInitializeA001 アクションID：A001 アクション名：初期化
     * APIリクエスト：IfaStockDetailInfoA001ApiRequest
     * Apiレスポンス：IfaStockDetailInfoA001ApiResponse
     * Dtoリクエスト：IfaStockDetailInfoA001DtoRequest
     * Dtoレスポンス：IfaStockDetailInfoA001DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaStockDetailInfoInitializeA001")
    public String initializeA001(@RequestBody IfaStockDetailInfoA001ApiRequest apiReq) throws Exception {
        
        IfaStockDetailInfoA001DtoRequest appReq = new IfaStockDetailInfoA001DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaStockDetailInfoA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaStockDetailInfoService",
                "initializeA001", new TypeReference<DataList<IfaStockDetailInfoA001DtoResponse>>() {
                }, appReq);
        DataList<IfaStockDetailInfoA001ApiResponse> apiRes = new DataList<IfaStockDetailInfoA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaStockDetailInfoUpdate アクションID：A002 アクション名：更新
     * APIリクエスト：IfaStockDetailInfoA002ApiRequest
     * Apiレスポンス：IfaStockDetailInfoA002ApiResponse
     * Dtoリクエスト：IfaStockDetailInfoA002DtoRequest
     * Dtoレスポンス：IfaStockDetailInfoA002DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaStockDetailInfoUpdateA002")
    public String updateA002(@RequestBody IfaStockDetailInfoA002ApiRequest apiReq) throws Exception {
        
        IfaStockDetailInfoA002DtoRequest appReq = new IfaStockDetailInfoA002DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaStockDetailInfoA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaStockDetailInfoService",
                "updateA002", new TypeReference<DataList<IfaStockDetailInfoA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaStockDetailInfoA002ApiResponse> apiRes = new DataList<IfaStockDetailInfoA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(appRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
