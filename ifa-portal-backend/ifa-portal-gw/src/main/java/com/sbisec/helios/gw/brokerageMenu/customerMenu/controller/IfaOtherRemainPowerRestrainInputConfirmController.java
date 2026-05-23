package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputConfirmA002ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaOtherRemainPowerRestrainInputConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaOtherRemainPowerRestrainInputConfirmA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaOtherRemainPowerRestrainInputConfirmA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaOtherRemainPowerRestrainInputConfirmA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0110-01_2
 * 画面名：その他余力拘束注文確認
 *
 * @author 大連 えん
 * 
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0110-01_2", screenNumber = "")
public class IfaOtherRemainPowerRestrainInputConfirmController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    
    

    /**
     * アクセス：/brokerageMenu/customerMenu/IfaOtherRemainPowerRestrainInputConfirmInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaOtherRemainPowerRestrainInputConfirmA001ApiRequest
     * Apiレスポンス：IfaOtherRemainPowerRestrainInputConfirmA001ApiResponse
     * Dtoリクエスト：IfaOtherRemainPowerRestrainInputConfirmA001RequestDto
     * Dtoレスポンス：IfaOtherRemainPowerRestrainInputConfirmA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/IfaOtherRemainPowerRestrainInputConfirmInitializeA001")
    public String initializeA001(@RequestBody IfaOtherRemainPowerRestrainInputConfirmA001ApiRequest apiReq) throws Exception {

    	IfaOtherRemainPowerRestrainInputConfirmA001RequestDto appReq = new IfaOtherRemainPowerRestrainInputConfirmA001RequestDto();
         
         // Beanコピー用共通部品。
         BeanUtils.copyProperties(appReq, apiReq);
         
         DataList<IfaOtherRemainPowerRestrainInputConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke(
                 "cmpIfaOtherRemainPowerRestrainInputConfirmService", "initializeA001",
                 new TypeReference<DataList<IfaOtherRemainPowerRestrainInputConfirmA001ResponseDto>>() {
                 }, appReq);
         
         DataList<IfaOtherRemainPowerRestrainInputConfirmA001ApiResponse> apiRes = new DataList<IfaOtherRemainPowerRestrainInputConfirmA001ApiResponse>();
         
         BeanUtils.copyProperties(apiRes, appRes);
         
         return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/IfaOtherRemainPowerRestrainInputOrderConfirmA002
     * アクションID：A002
     * アクション名：注文発注
     * APIリクエスト：IfaOtherRemainPowerRestrainInputConfirmA002ApiRequest
     * Apiレスポンス：IfaOtherRemainPowerRestrainInputConfirmA002ApiResponse
     * Dtoリクエスト：IfaOtherRemainPowerRestrainInputConfirmA002RequestDto
     * Dtoレスポンス：IfaOtherRemainPowerRestrainInputConfirmA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/IfaOtherRemainPowerRestrainInputConfirmOrderPlacementA002")
    public String orderPlacementA002(@RequestBody IfaOtherRemainPowerRestrainInputConfirmA002ApiRequest apiReq) throws Exception {

    	IfaOtherRemainPowerRestrainInputConfirmA002RequestDto appReq = new IfaOtherRemainPowerRestrainInputConfirmA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaOtherRemainPowerRestrainInputConfirmA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaOtherRemainPowerRestrainInputConfirmService",
                "orderPlacementA002", new TypeReference<DataList<IfaOtherRemainPowerRestrainInputConfirmA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaOtherRemainPowerRestrainInputConfirmA002ApiResponse> apiRes = new DataList<IfaOtherRemainPowerRestrainInputConfirmA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

}

