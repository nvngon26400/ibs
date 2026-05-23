package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderConfirmA001bResponseDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderConfirmA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0401-02_2
 * 画面名：国内投信注文確認
 * @author <author-name>
 *
 * 2024/03/26 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0401-02_2", screenNumber = "")
public class IfaDomesticMutualFundOrderConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderConfirmOrderPlacementA001
     * アクションID：A001
     * アクション名：注文発注
     * APIリクエスト：IfaDomesticMutualFundOrderConfirmA001ApiRequest
     * Apiレスポンス：IfaDomesticMutualFundOrderConfirmA001ApiResponse
     * Dtoリクエスト：IfaDomesticMutualFundOrderConfirmA001DtoRequest
     * Dtoレスポンス：IfaDomesticMutualFundOrderConfirmA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderConfirmOrderPlacementA001")
    public String orderPlacementA001(@RequestBody IfaDomesticMutualFundOrderConfirmA001ApiRequest apiReq)
            throws Exception {
        
        IfaDomesticMutualFundOrderConfirmA001aRequestDto appReq = new IfaDomesticMutualFundOrderConfirmA001aRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaDomesticMutualFundOrderConfirmA001aResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticMutualFundOrderConfirmService", "orderPlacementA001a",
                new TypeReference<DataList<IfaDomesticMutualFundOrderConfirmA001aResponseDto>>() {
                }, appReq);
        
        // エラー
        if (appRes.getErrorLevel() == ErrorLevel.FATAL.getId()) {
            DataList<IfaDomesticMutualFundOrderConfirmA001ApiResponse> apiRes = new DataList<IfaDomesticMutualFundOrderConfirmA001ApiResponse>();
            
            BeanUtils.copyProperties(apiRes, appRes);
            
            return jc.toString(apiRes);
        }
        
        IfaDomesticMutualFundOrderConfirmA001bRequestDto appReqb = new IfaDomesticMutualFundOrderConfirmA001bRequestDto();
        BeanUtils.copyProperties(appReqb, apiReq);
        // IFA注文番号
        appReqb.setIfaOrderNo(appRes.getDataList().get(0).getIfaOrderNo());
        
        // A001bサービスを呼び出す
        DataList<IfaDomesticMutualFundOrderConfirmA001bResponseDto> appResb = ApiRequestUtil.invoke(
                "cmpIfaDomesticMutualFundOrderConfirmService", "orderPlacementA001b",
                new TypeReference<DataList<IfaDomesticMutualFundOrderConfirmA001bResponseDto>>() {
                }, appReqb);
        
        DataList<IfaDomesticMutualFundOrderConfirmA001ApiResponse> apiRes = new DataList<IfaDomesticMutualFundOrderConfirmA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appResb);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
