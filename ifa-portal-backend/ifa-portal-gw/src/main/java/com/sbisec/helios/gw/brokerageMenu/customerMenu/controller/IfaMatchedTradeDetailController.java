package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMatchedTradeDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMatchedTradeDetailA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMatchedTradeDetailA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMatchedTradeDetailA003ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMatchedTradeDetailA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMatchedTradeDetailA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMatchedTradeDetailA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMatchedTradeDetailA003ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0105-02
 * 画面名：出来明細
 * @author <author-name>
 *
 * 2023/09/14 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0105-02", screenNumber = "2", ignoreCheck = true)
public class IfaMatchedTradeDetailController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/accountManage
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaMatchedTradeDetailA001ApiRequest
     * Apiレスポンス：IfaMatchedTradeDetailA001ApiResponse
     * Dtoリクエスト：IfaMatchedTradeDetailA001DtoRequest
     * Dtoレスポンス：IfaMatchedTradeDetailA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMatchedTradeDetailInitializeA001")
    public String initializeA001(@RequestBody IfaMatchedTradeDetailA001ApiRequest apiReq) throws Exception {
        
        IfaMatchedTradeDetailA001RequestDto appReq = new IfaMatchedTradeDetailA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaMatchedTradeDetailA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaMatchedTradeDetailService",
                "initializeA001", new TypeReference<DataList<IfaMatchedTradeDetailA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaMatchedTradeDetailA001ApiResponse> apiRes = new DataList<IfaMatchedTradeDetailA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/accountManage
     * アクションID：A003
     * アクション名：更新
     * APIリクエスト：IfaMatchedTradeDetailA003ApiRequest
     * Apiレスポンス：IfaMatchedTradeDetailA003ApiResponse
     * Dtoリクエスト：IfaMatchedTradeDetailA003DtoRequest
     * Dtoレスポンス：IfaMatchedTradeDetailA003DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMatchedTradeDetailInitializeA003")
    public String updateA003(@RequestBody IfaMatchedTradeDetailA003ApiRequest apiReq) throws Exception {
        
        IfaMatchedTradeDetailA003RequestDto appReq = new IfaMatchedTradeDetailA003RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaMatchedTradeDetailA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaMatchedTradeDetailService",
                "updateA003", new TypeReference<DataList<IfaMatchedTradeDetailA003ResponseDto>>() {
                }, appReq);
        
        DataList<IfaMatchedTradeDetailA003ApiResponse> apiRes = new DataList<IfaMatchedTradeDetailA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
