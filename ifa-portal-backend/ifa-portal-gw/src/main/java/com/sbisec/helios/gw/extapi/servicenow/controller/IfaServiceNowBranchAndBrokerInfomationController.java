package com.sbisec.helios.gw.extapi.servicenow.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA001ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA002ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA003ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA004ResponseDto;
import com.sbisec.helios.gw.extapi.servicenow.form.IfaServiceNowBranchAndBrokerInfomationA001ApiRequest;
import com.sbisec.helios.gw.extapi.servicenow.form.IfaServiceNowBranchAndBrokerInfomationA002ApiRequest;
import com.sbisec.helios.gw.extapi.servicenow.form.IfaServiceNowBranchAndBrokerInfomationA003ApiRequest;
import com.sbisec.helios.gw.extapi.servicenow.form.IfaServiceNowBranchAndBrokerInfomationA004ApiRequest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ServiceNow向けAPI コントローラ
 *
 * @author SCSK
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN20", id = "EXTAPI_01-C01")
@RequestMapping(value = "/extapi/servicenow", produces = "application/json")
public class IfaServiceNowBranchAndBrokerInfomationController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
    * 本店・支店情報取得
    *
    * @param apiReq {@code IfaServiceNowBranchAndBrokerInfomationA001ApiRequest }
    * @return {@code String}
    * @throws Exception 例外が発生した場合
    */
    @PostMapping("/ifaRefBranchListByBranchKind")
    public String invokeA001(@Validated @RequestBody IfaServiceNowBranchAndBrokerInfomationA001ApiRequest apiReq)
            throws Exception {
        
        IfaServiceNowBranchAndBrokerInfomationA001ResponseDto appRes = ApiRequestUtil.invoke(
                "cmpIfaServiceNowBranchAndBrokerInfomationService", "invokeA001",
                new TypeReference<IfaServiceNowBranchAndBrokerInfomationA001ResponseDto>() {
                }, apiReq);
        
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(appRes);
        
        // 戻り結果をJsonで戻す
        return resultJson;
    }
    
    /**
    * SBI証券本支店の仲介業者支店を取得
    *
    * @param apiReq {@code IfaServiceNowBranchAndBrokerInfomationA002ApiRequest }
    * @return {@code String}
    * @throws Exception 例外が発生した場合
    */
    @PostMapping("/ifaRefBrokerBranchListByBranchCode")
    public String invokeA002(@Validated @RequestBody IfaServiceNowBranchAndBrokerInfomationA002ApiRequest apiReq)
            throws Exception {
        
        IfaServiceNowBranchAndBrokerInfomationA002ResponseDto appRes = ApiRequestUtil.invoke(
                "cmpIfaServiceNowBranchAndBrokerInfomationService", "invokeA002",
                new TypeReference<IfaServiceNowBranchAndBrokerInfomationA002ResponseDto>() {
                }, apiReq);
        
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(appRes);
        
        // 戻り結果をJsonで戻す
        return resultJson;
    }
    
    /**
    * 仲介業社支店一覧取得
    *
    * @param apiReq {@code IfaServiceNowBranchAndBrokerInfomationA003ApiRequest }
    * @return {@code String}
    * @throws Exception 例外が発生した場合
    */
    @PostMapping("/ifaRefSubBrokerBranchListByBrokerCode")
    public String invokeA003(@Validated @RequestBody IfaServiceNowBranchAndBrokerInfomationA003ApiRequest apiReq)
            throws Exception {
        
        IfaServiceNowBranchAndBrokerInfomationA003ResponseDto appRes = ApiRequestUtil.invoke(
                "cmpIfaServiceNowBranchAndBrokerInfomationService", "invokeA003",
                new TypeReference<IfaServiceNowBranchAndBrokerInfomationA003ResponseDto>() {
                }, apiReq);
        
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(appRes);
        
        // 戻り結果をJsonで戻す
        return resultJson;
    }
    
    /**
    * 営業員一覧を取得
    *
    * @param apiReq {@code IfaServiceNowBranchAndBrokerInfomationA004ApiRequest }
    * @return {@code String}
    * @throws Exception 例外が発生した場合
    */
    @PostMapping("/ifaRefBrokerChargeListByBrokerAndBrokerBranchCode")
    public String invokeA004(@Validated @RequestBody IfaServiceNowBranchAndBrokerInfomationA004ApiRequest apiReq)
            throws Exception {
        
        IfaServiceNowBranchAndBrokerInfomationA004ResponseDto appRes = ApiRequestUtil.invoke(
                "cmpIfaServiceNowBranchAndBrokerInfomationService", "invokeA004",
                new TypeReference<IfaServiceNowBranchAndBrokerInfomationA004ResponseDto>() {
                }, apiReq);
        
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(appRes);
        
        // 戻り結果をJsonで戻す
        return resultJson;
    }
    
}
