package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA003DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA003DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA004DtoResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginCollateralTransferInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginCollateralTransferInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginCollateralTransferInputA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginCollateralTransferInputA003ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginCollateralTransferInputA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginCollateralTransferInputA004ApiResponse;

/**
 * 画面ID：SUB0202_0305-01_1
 * 画面名：米株信用代用振替入力
 *
 * @author SCSK川崎
 * 
   2024/03/19 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0305-01_1", screenNumber = "")
public class IfaForeignMarginCollateralTransferInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginCollateralTransferInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaForeignMarginCollateralTransferInputA001ApiRequest
     * Apiレスポンス：IfaForeignMarginCollateralTransferInputA001ApiResponse
     * Dtoリクエスト：IfaForeignMarginCollateralTransferInputA001DtoRequest
     * Dtoレスポンス：IfaForeignMarginCollateralTransferInputA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaForeignMarginCollateralTransferInputInitializeA001")
    public String initializeA001(@RequestBody IfaForeignMarginCollateralTransferInputA001ApiRequest apiReq)
            throws Exception {
        
        IfaForeignMarginCollateralTransferInputA001DtoRequest appReq = 
                new IfaForeignMarginCollateralTransferInputA001DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaForeignMarginCollateralTransferInputA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginCollateralTransferInputService", "initializeA001",
                new TypeReference<DataList<IfaForeignMarginCollateralTransferInputA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaForeignMarginCollateralTransferInputA001ApiResponse> apiRes = 
                new DataList<IfaForeignMarginCollateralTransferInputA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginCollateralTransferInputDisplayResultA003
     * アクションID：A003
     * アクション名：結果を表示
     * APIリクエスト：IfaForeignMarginCollateralTransferInputA003ApiRequest
     * Apiレスポンス：IfaForeignMarginCollateralTransferInputA003ApiResponse
     * Dtoリクエスト：IfaForeignMarginCollateralTransferInputA003DtoRequest
     * Dtoレスポンス：IfaForeignMarginCollateralTransferInputA003DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaForeignMarginCollateralTransferInputDisplayResultA003")
    public String displayResultA003(@RequestBody IfaForeignMarginCollateralTransferInputA003ApiRequest apiReq)
            throws Exception {
        
        IfaForeignMarginCollateralTransferInputA003DtoRequest appReq = 
                new IfaForeignMarginCollateralTransferInputA003DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaForeignMarginCollateralTransferInputA003DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginCollateralTransferInputService", "displayResultA003",
                new TypeReference<DataList<IfaForeignMarginCollateralTransferInputA003DtoResponse>>() {
                }, appReq);
        
        DataList<IfaForeignMarginCollateralTransferInputA003ApiResponse> apiRes = 
                new DataList<IfaForeignMarginCollateralTransferInputA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginCollateralTransferInputTransferConfirmA004
     * アクションID：A004
     * アクション名：振替確認
     * APIリクエスト：IfaForeignMarginCollateralTransferInputA004ApiRequest
     * Apiレスポンス：IfaForeignMarginCollateralTransferInputA004ApiResponse
     * Dtoリクエスト：IfaForeignMarginCollateralTransferInputA004DtoRequest
     * Dtoレスポンス：IfaForeignMarginCollateralTransferInputA004DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaForeignMarginCollateralTransferInputTransferConfirmA004")
    public String transferConfirmA004(@RequestBody IfaForeignMarginCollateralTransferInputA004ApiRequest apiReq)
            throws Exception {
        
        IfaForeignMarginCollateralTransferInputA004DtoRequest appReq = 
                new IfaForeignMarginCollateralTransferInputA004DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaForeignMarginCollateralTransferInputA004DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignMarginCollateralTransferInputService", "transferConfirmA004",
                new TypeReference<DataList<IfaForeignMarginCollateralTransferInputA004DtoResponse>>() {
                }, appReq);
        
        DataList<IfaForeignMarginCollateralTransferInputA004ApiResponse> apiRes = 
                new DataList<IfaForeignMarginCollateralTransferInputA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
