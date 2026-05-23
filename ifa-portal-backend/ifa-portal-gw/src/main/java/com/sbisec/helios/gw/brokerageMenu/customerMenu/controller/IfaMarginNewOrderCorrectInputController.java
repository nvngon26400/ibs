package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA004DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA010DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA010DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderCorrectInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderCorrectInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderCorrectInputA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderCorrectInputA004ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderCorrectInputA010ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderCorrectInputA010ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0212-02_1
 * 画面名：信用新規注文訂正入力
 * @author <author-name>
 *
 * 2024/03/28 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0212-02_1", screenNumber = "")
public class IfaMarginNewOrderCorrectInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaMarginNewOrderCorrectInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaMarginNewOrderCorrectInputA001ApiRequest
     * Apiレスポンス：IfaMarginNewOrderCorrectInputA001ApiResponse
     * Dtoリクエスト：IfaMarginNewOrderCorrectInputA001DtoRequest
     * Dtoレスポンス：IfaMarginNewOrderCorrectInputA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMarginNewOrderCorrectInputInitializeA001")
    public String initializeA001(@RequestBody IfaMarginNewOrderCorrectInputA001ApiRequest apiReq) throws Exception {
        
        IfaMarginNewOrderCorrectInputA001DtoRequest appReq = new IfaMarginNewOrderCorrectInputA001DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaMarginNewOrderCorrectInputA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginNewOrderCorrectInputService", "initializeA001",
                new TypeReference<DataList<IfaMarginNewOrderCorrectInputA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaMarginNewOrderCorrectInputA001ApiResponse> apiRes = new DataList<IfaMarginNewOrderCorrectInputA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaMarginNewOrderCorrectInputUpdateA004
     * アクションID：A004
     * アクション名：更新
     * APIリクエスト：IfaMarginNewOrderCorrectInputA004ApiRequest
     * Apiレスポンス：IfaMarginNewOrderCorrectInputA004ApiResponse
     * Dtoリクエスト：IfaMarginNewOrderCorrectInputA004DtoRequest
     * Dtoレスポンス：IfaMarginNewOrderCorrectInputA004DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMarginNewOrderCorrectInputUpdateA004")
    public String updateA004(@RequestBody IfaMarginNewOrderCorrectInputA004ApiRequest apiReq) throws Exception {
        
        IfaMarginNewOrderCorrectInputA004DtoRequest appReq = new IfaMarginNewOrderCorrectInputA004DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaMarginNewOrderCorrectInputA004DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginNewOrderCorrectInputService", "updateA004",
                new TypeReference<DataList<IfaMarginNewOrderCorrectInputA004DtoResponse>>() {
                }, appReq);
        
        DataList<IfaMarginNewOrderCorrectInputA004ApiResponse> apiRes = new DataList<IfaMarginNewOrderCorrectInputA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaMarginNewOrderCorrectInputCorrectionConfirmA010
     * アクションID：A010
     * アクション名：訂正確認
     * APIリクエスト：IfaMarginNewOrderCorrectInputA010ApiRequest
     * Apiレスポンス：IfaMarginNewOrderCorrectInputA010ApiResponse
     * Dtoリクエスト：IfaMarginNewOrderCorrectInputA010DtoRequest
     * Dtoレスポンス：IfaMarginNewOrderCorrectInputA010DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMarginNewOrderCorrectInputCorrectionConfirmA010")
    public String correctionConfirmA010(@RequestBody IfaMarginNewOrderCorrectInputA010ApiRequest apiReq)
            throws Exception {
        
        IfaMarginNewOrderCorrectInputA010DtoRequest appReq = new IfaMarginNewOrderCorrectInputA010DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaMarginNewOrderCorrectInputA010DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginNewOrderCorrectInputService", "correctionConfirmA010",
                new TypeReference<DataList<IfaMarginNewOrderCorrectInputA010DtoResponse>>() {
                }, appReq);
        
        DataList<IfaMarginNewOrderCorrectInputA010ApiResponse> apiRes = new DataList<IfaMarginNewOrderCorrectInputA010ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
