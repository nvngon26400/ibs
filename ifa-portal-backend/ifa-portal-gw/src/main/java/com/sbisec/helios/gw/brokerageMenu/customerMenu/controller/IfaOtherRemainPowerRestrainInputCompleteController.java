package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputCompleteA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaOtherRemainPowerRestrainInputCompleteA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaOtherRemainPowerRestrainInputCompleteA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0110-01_3
 * 画面名：その他余力拘束注文完了
 *
 * @author 大連 えん
 * 
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0110-01_3", screenNumber = "")
public class IfaOtherRemainPowerRestrainInputCompleteController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    
    

    /**
     * アクセス：/brokerageMenu/customerMenu/IfaOtherRemainPowerRestrainInputCompleteInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaOtherRemainPowerRestrainInputCompleteA001ApiRequest
     * Apiレスポンス：IfaOtherRemainPowerRestrainInputCompleteA001ApiResponse
     * Dtoリクエスト：IfaOtherRemainPowerRestrainInputCompleteA001RequestDto
     * Dtoレスポンス：IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/IfaOtherRemainPowerRestrainInputCompleteInitializeA001")
    public String initializeA001(@RequestBody IfaOtherRemainPowerRestrainInputCompleteA001ApiRequest apiReq) throws Exception {

         IfaOtherRemainPowerRestrainInputCompleteA001RequestDto appReq = new IfaOtherRemainPowerRestrainInputCompleteA001RequestDto();
         
         // Beanコピー用共通部品。
         BeanUtils.copyProperties(appReq, apiReq);
         
         DataList<IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto> appRes = ApiRequestUtil.invoke(
                 "cmpIfaOtherRemainPowerRestrainInputCompleteService", "initializeA001",
                 new TypeReference<DataList<IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto>>() {
                 }, appReq);
         
         DataList<IfaOtherRemainPowerRestrainInputCompleteA001ApiResponse> apiRes = new DataList<IfaOtherRemainPowerRestrainInputCompleteA001ApiResponse>();
         
         BeanUtils.copyProperties(apiRes, appRes);
         
         return jc.toString(apiRes);
    }
    

}

