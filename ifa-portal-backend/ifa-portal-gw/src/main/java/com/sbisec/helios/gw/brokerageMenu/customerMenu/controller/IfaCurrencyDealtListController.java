package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCurrencyDealtListA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCurrencyDealtListA001DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCurrencyDealtListA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCurrencyDealtListA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0502-01
 * 画面名：取扱通貨一覧
 *
 * @author 池亀緑
 *
 *         2023/08/23 新規作成
 *         2023/10/10 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0502-01", screenNumber = "", ignoreCheck = true)
public class IfaCurrencyDealtListController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaCurrencyDealtListOrderNewMarginA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaCurrencyDealtListA001ApiRequest
     * Apiレスポンス：IfaCurrencyDealtListA001ApiResponse
     * Dtoリクエスト：IfaCurrencyDealtListA001DtoRequest
     * Dtoレスポンス：IfaCurrencyDealtListA001DtoResponse
     *
     * @param apiReq {@code IfaCurrencyDealtListA001ApiRequest }
     * @return {@code String}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaCurrencyDealtListOrderNewMarginA001")
    public String orderNewMarginA001(@RequestBody IfaCurrencyDealtListA001ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaCurrencyDealtListA001DtoRequest appReq = new IfaCurrencyDealtListA001DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaCurrencyDealtListA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaCurrencyDealtListService",
                "orderNewMarginA001", new TypeReference<DataList<IfaCurrencyDealtListA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaCurrencyDealtListA001ApiResponse> apiRes = new DataList<IfaCurrencyDealtListA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaCurrencyDealtListOrderNewMarginBackA003
     * アクションID：A003
     * アクション名：初期化(戻る)
     * APIリクエスト：IfaCurrencyDealtListA001ApiRequest
     * Apiレスポンス：IfaCurrencyDealtListA001ApiResponse
     * Dtoリクエスト：IfaCurrencyDealtListA001DtoRequest
     * Dtoレスポンス：IfaCurrencyDealtListA001DtoResponse
     *
     * @param apiReq {@code IfaCurrencyDealtListA001ApiRequest }
     * @return {@code String}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaCurrencyDealtListOrderNewMarginBackA003")
    public String orderNewMarginBackA003(@RequestBody IfaCurrencyDealtListA001ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaCurrencyDealtListA001DtoRequest appReq = new IfaCurrencyDealtListA001DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaCurrencyDealtListA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaCurrencyDealtListService",
                "orderNewMarginA001", new TypeReference<DataList<IfaCurrencyDealtListA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaCurrencyDealtListA001ApiResponse> apiRes = new DataList<IfaCurrencyDealtListA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
