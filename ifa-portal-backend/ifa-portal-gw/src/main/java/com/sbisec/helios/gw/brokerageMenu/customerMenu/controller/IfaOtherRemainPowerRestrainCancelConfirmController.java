package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelConfirmA002ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaOtherRemainPowerRestrainCancelConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaOtherRemainPowerRestrainCancelConfirmA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaOtherRemainPowerRestrainCancelConfirmA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaOtherRemainPowerRestrainCancelConfirmA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0110-02_1
 * 画面名：その他余力拘束注文取消確認
 *
 * @author 大連 えん
 * 
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0110-02_1", screenNumber = "")
public class IfaOtherRemainPowerRestrainCancelConfirmController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    
    

    /**
     * アクセス：/brokerageMenu/customerMenu/IfaOtherRemainPowerRestrainCancelConfirmInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaOtherRemainPowerRestrainCancelConfirmA001ApiRequest
     * Apiレスポンス：IfaOtherRemainPowerRestrainCancelConfirmA001ApiResponse
     * Dtoリクエスト：IfaOtherRemainPowerRestrainCancelConfirmA001RequestDto
     * Dtoレスポンス：IfaOtherRemainPowerRestrainCancelConfirmA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/IfaOtherRemainPowerRestrainCancelConfirmInitializeA001")
    public String initializeA001(@RequestBody IfaOtherRemainPowerRestrainCancelConfirmA001ApiRequest apiReq) throws Exception {

    	IfaOtherRemainPowerRestrainCancelConfirmA001RequestDto appReq = new IfaOtherRemainPowerRestrainCancelConfirmA001RequestDto();
         
         // Beanコピー用共通部品。
         BeanUtils.copyProperties(appReq, apiReq);
         
         DataList<IfaOtherRemainPowerRestrainCancelConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke(
                 "cmpIfaOtherRemainPowerRestrainCancelConfirmService", "initializeA001",
                 new TypeReference<DataList<IfaOtherRemainPowerRestrainCancelConfirmA001ResponseDto>>() {
                 }, appReq);
         
         DataList<IfaOtherRemainPowerRestrainCancelConfirmA001ApiResponse> apiRes = new DataList<IfaOtherRemainPowerRestrainCancelConfirmA001ApiResponse>();
         
         BeanUtils.copyProperties(apiRes, appRes);
         
         return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/IfaOtherRemainPowerRestrainCancelConfirmOrderPlacementA002
     * アクションID：A002
     * アクション名：注文取消
     * APIリクエスト：IfaOtherRemainPowerRestrainCancelConfirmA002ApiRequest
     * Apiレスポンス：IfaOtherRemainPowerRestrainCancelConfirmA002ApiResponse
     * Dtoリクエスト：IfaOtherRemainPowerRestrainCancelConfirmA002RequestDto
     * Dtoレスポンス：IfaOtherRemainPowerRestrainCancelConfirmA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/IfaOtherRemainPowerRestrainCancelConfirmOrderPlacementA002")
    public String cancelConfirmA002(@RequestBody IfaOtherRemainPowerRestrainCancelConfirmA002ApiRequest apiReq) throws Exception {

    	IfaOtherRemainPowerRestrainCancelConfirmA002ApiRequest appReq = new IfaOtherRemainPowerRestrainCancelConfirmA002ApiRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaOtherRemainPowerRestrainCancelConfirmA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaOtherRemainPowerRestrainCancelConfirmService",
                "cancelConfirmA002", new TypeReference<DataList<IfaOtherRemainPowerRestrainCancelConfirmA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaOtherRemainPowerRestrainCancelConfirmA002ApiResponse> apiRes = new DataList<IfaOtherRemainPowerRestrainCancelConfirmA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

}

