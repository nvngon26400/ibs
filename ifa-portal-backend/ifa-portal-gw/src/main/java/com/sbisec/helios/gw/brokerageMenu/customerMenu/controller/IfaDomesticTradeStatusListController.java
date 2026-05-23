package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticTradeStatusListA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticTradeStatusListA001DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticTradeStatusListA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticTradeStatusListA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0212-01_1
 * 画面名：国内株当日約定状況（一覧）
 * @author <author-name>
 *
 * 2023/09/20 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0212-01_1", screenNumber = "", ignoreCheck = true)
public class IfaDomesticTradeStatusListController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/IfaDomesticTradeStatusListInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaDomesticTradeStatusListA001ApiRequest
     * Apiレスポンス：IfaDomesticTradeStatusListA001ApiResponse
     * Dtoリクエスト：IfaDomesticTradeStatusListA001DtoRequest
     * Dtoレスポンス：IfaDomesticTradeStatusListA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticTradeStatusListInitializeA001")
    public String initializeA001(@RequestBody IfaDomesticTradeStatusListA001ApiRequest apiReq) throws Exception {
        
        IfaDomesticTradeStatusListA001DtoRequest appReq = new IfaDomesticTradeStatusListA001DtoRequest();
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDomesticTradeStatusListA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticTradeStatusListService", "initializeA001",
                new TypeReference<DataList<IfaDomesticTradeStatusListA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaDomesticTradeStatusListA001ApiResponse> apiRes = new DataList<IfaDomesticTradeStatusListA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
