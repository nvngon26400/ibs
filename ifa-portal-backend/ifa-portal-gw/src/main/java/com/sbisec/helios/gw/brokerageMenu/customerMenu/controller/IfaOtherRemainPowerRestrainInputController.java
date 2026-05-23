package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputOrderConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputOrderConfirmA002ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaOtherRemainPowerRestrainInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaOtherRemainPowerRestrainInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaOtherRemainPowerRestrainInputOrderConfirmA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaOtherRemainPowerRestrainInputOrderConfirmA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0110-01_1
 * 画面名：その他余力拘束注文入力
 *
 * @author 大連 えん
 * 
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0110-01_1", screenNumber = "")
public class IfaOtherRemainPowerRestrainInputController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    
    

    /**
     * アクセス：/brokerageMenu/customerMenu/IfaOtherRemainPowerRestrainInputInitializeA001
     * アクションID：A001
     * アクション名：初期化（その他余力拘束注文入力遷移）
     * APIリクエスト：IfaOtherRemainPowerRestrainInputA001ApiRequest
     * Apiレスポンス：IfaOtherRemainPowerRestrainInputA001ApiResponse
     * Dtoリクエスト：IfaOtherRemainPowerRestrainInputA001RequestDto
     * Dtoレスポンス：IfaOtherRemainPowerRestrainInputA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/IfaOtherRemainPowerRestrainInputInitializeA001")
    public String initializeA001(@RequestBody IfaOtherRemainPowerRestrainInputA001ApiRequest apiReq) throws Exception {

         IfaOtherRemainPowerRestrainInputA001RequestDto appReq = new IfaOtherRemainPowerRestrainInputA001RequestDto();
         
         // Beanコピー用共通部品。
         BeanUtils.copyProperties(appReq, apiReq);
         
         DataList<IfaOtherRemainPowerRestrainInputA001ResponseDto> appRes = ApiRequestUtil.invoke(
                 "cmpIfaOtherRemainPowerRestrainInputService", "initializeA001",
                 new TypeReference<DataList<IfaOtherRemainPowerRestrainInputA001ResponseDto>>() {
                 }, appReq);
         
         DataList<IfaOtherRemainPowerRestrainInputA001ApiResponse> apiRes = new DataList<IfaOtherRemainPowerRestrainInputA001ApiResponse>();
         
         BeanUtils.copyProperties(apiRes, appRes);
         
         return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/IfaOtherRemainPowerRestrainInputOrderConfirmA002
     * アクションID：A002
     * アクション名：注文確認
     * APIリクエスト：IfaOtherRemainPowerRestrainInputOrderConfirmA002ApiRequest
     * Apiレスポンス：IfaOtherRemainPowerRestrainInputOrderConfirmA002ApiResponse
     * Dtoリクエスト：IfaOtherRemainPowerRestrainInputOrderConfirmA002RequestDto
     * Dtoレスポンス：IfaOtherRemainPowerRestrainInputOrderConfirmA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/IfaOtherRemainPowerRestrainInputOrderConfirmA002")
    public String orderConfirmA002(@RequestBody IfaOtherRemainPowerRestrainInputOrderConfirmA002ApiRequest apiReq) throws Exception {

    	IfaOtherRemainPowerRestrainInputOrderConfirmA002RequestDto appReq = new IfaOtherRemainPowerRestrainInputOrderConfirmA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaOtherRemainPowerRestrainInputOrderConfirmA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaOtherRemainPowerRestrainInputService",
                "orderConfirmA002", new TypeReference<DataList<IfaOtherRemainPowerRestrainInputOrderConfirmA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaOtherRemainPowerRestrainInputOrderConfirmA002ApiResponse> apiRes = new DataList<IfaOtherRemainPowerRestrainInputOrderConfirmA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

}

