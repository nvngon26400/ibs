package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelCompleteA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelCompleteA001ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0110-02_2
 * 画面名：その他余力拘束注文取消完了
 *
 * @author 大連 えん
 * 
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0110-02_2", screenNumber = "")
public class IfaOtherRemainPowerRestrainCancelCompleteController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    
    

    /**
     * アクセス：/brokerageMenu/customerMenu/IfaOtherRemainPowerRestrainCancelCompleteInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * Dtoリクエスト：IfaOtherRemainPowerRestrainCancelCompleteA001RequestDto
     * Dtoレスポンス：IfaOtherRemainPowerRestrainCancelCompleteA001ResponseDto
     *
     * @param appReq リクエスト
     * @return appRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/IfaOtherRemainPowerRestrainCancelCompleteInitializeA001")
    public String initializeA001(@RequestBody IfaOtherRemainPowerRestrainCancelCompleteA001RequestDto appReq) throws Exception {

         DataList<IfaOtherRemainPowerRestrainCancelCompleteA001ResponseDto> appRes = ApiRequestUtil.invoke(
                 "cmpIfaOtherRemainPowerRestrainCancelCompleteService", "initializeA001",
                 new TypeReference<DataList<IfaOtherRemainPowerRestrainCancelCompleteA001ResponseDto>>() {
                 }, appReq);

         return jc.toString(appRes);
    }
    

}

