package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA004DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA010DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA010DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderCorrectInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderCorrectInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderCorrectInputA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderCorrectInputA004ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderCorrectInputA010ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderCorrectInputA010ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0208-03_1
 * 画面名：国内株式注文訂正入力
 * 2023/01/09 新規作成
 *
 * @author 齋藤
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0208-03_1", screenNumber = "")
public class IfaDomesticStockOrderCorrectInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticStockOrderCorrectInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaDomesticStockOrderCorrectInputA001ApiRequest
     * Apiレスポンス：IfaDomesticStockOrderCorrectInputA001ApiResponse
     * Dtoリクエスト：IfaDomesticStockOrderCorrectInputA001DtoRequest
     * Dtoレスポンス：IfaDomesticStockOrderCorrectInputA001DtoResponse
     *
     * @param apiReq リクエストボディ
     * @return 画面の初期化に必要な情報
     * @exception Exception システムエラー
     */
    
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticStockOrderCorrectInputInitializeA001")
    public String initializeA001(@RequestBody IfaDomesticStockOrderCorrectInputA001ApiRequest apiReq) throws Exception {
        
        IfaDomesticStockOrderCorrectInputA001DtoRequest appReq = new IfaDomesticStockOrderCorrectInputA001DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaDomesticStockOrderCorrectInputA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticStockOrderCorrectInputService", "initializeA001",
                new TypeReference<DataList<IfaDomesticStockOrderCorrectInputA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaDomesticStockOrderCorrectInputA001ApiResponse> apiRes = new DataList<IfaDomesticStockOrderCorrectInputA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticStockOrderCorrectInputUpdateA004
     * アクションID：A004
     * アクション名：更新
     * APIリクエスト：IfaDomesticStockOrderCorrectInputA004ApiRequest
     * Apiレスポンス：IfaDomesticStockOrderCorrectInputA004ApiResponse
     * Dtoリクエスト：IfaDomesticStockOrderCorrectInputA004DtoRequest
     * Dtoレスポンス：IfaDomesticStockOrderCorrectInputA004DtoResponse
     *
     * @param apiReq リクエストボディ
     * @return 画面の更新に必要な値
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticStockOrderCorrectInputUpdateA004")
    public String updateA004(@RequestBody IfaDomesticStockOrderCorrectInputA004ApiRequest apiReq) throws Exception {
        
        IfaDomesticStockOrderCorrectInputA004DtoRequest appReq = new IfaDomesticStockOrderCorrectInputA004DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaDomesticStockOrderCorrectInputA004DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticStockOrderCorrectInputService", "updateA004",
                new TypeReference<DataList<IfaDomesticStockOrderCorrectInputA004DtoResponse>>() {
                }, appReq);
        
        DataList<IfaDomesticStockOrderCorrectInputA004ApiResponse> apiRes = new DataList<IfaDomesticStockOrderCorrectInputA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticStockOrderCorrectInputOrderConfirmA010
     * アクションID：A010
     * アクション名：注文確認
     * APIリクエスト：IfaDomesticStockOrderCorrectInputA010ApiRequest
     * Apiレスポンス：IfaDomesticStockOrderCorrectInputA010ApiResponse
     * Dtoリクエスト：IfaDomesticStockOrderCorrectInputA010DtoRequest
     * Dtoレスポンス：IfaDomesticStockOrderCorrectInputA010DtoResponse
     *
     * @param apiReq リクエストボディ
     * @return 注文訂正確認画面の初期化に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticStockOrderCorrectInputOrderConfirmA010")
    public String orderConfirmA010(@RequestBody IfaDomesticStockOrderCorrectInputA010ApiRequest apiReq)
            throws Exception {
        
        IfaDomesticStockOrderCorrectInputA010DtoRequest appReq = new IfaDomesticStockOrderCorrectInputA010DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaDomesticStockOrderCorrectInputA010DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticStockOrderCorrectInputService", "orderConfirmA010",
                new TypeReference<DataList<IfaDomesticStockOrderCorrectInputA010DtoResponse>>() {
                }, appReq);
        
        DataList<IfaDomesticStockOrderCorrectInputA010ApiResponse> apiRes = new DataList<IfaDomesticStockOrderCorrectInputA010ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        return null;
    }
}
