package com.sbisec.helios.gw.extapi.servicenow.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA010ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA011ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA012ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA013ResponseDto;
import com.sbisec.helios.gw.extapi.servicenow.form.IfaServiceNowMenuAndAclManagerA010ApiRequest;
import com.sbisec.helios.gw.extapi.servicenow.form.IfaServiceNowMenuAndAclManagerA011ApiRequest;
import com.sbisec.helios.gw.extapi.servicenow.form.IfaServiceNowMenuAndAclManagerA012ApiRequest;
import com.sbisec.helios.gw.extapi.servicenow.form.IfaServiceNowMenuAndAclManagerA013ApiRequest;

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
public class IfaServiceNowMenuAndAclManagerController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
    * メニュー取得
    *
    * @param apiReq {@code IfaServiceNowMenuAndAclManagerA010ApiRequest }
    * @return {@code String}
    * @throws Exception 例外が発生した場合
    */
    @PostMapping("/ifaRefAclByPrivId")
    public String invokeA010(@Validated @RequestBody IfaServiceNowMenuAndAclManagerA010ApiRequest apiReq)
            throws Exception {
        
        IfaServiceNowMenuAndAclManagerA010ResponseDto appRes = ApiRequestUtil.invoke(
                "cmpIfaServiceNowMenuAndAclManagerService", "invokeA010",
                new TypeReference<IfaServiceNowMenuAndAclManagerA010ResponseDto>() {
                }, apiReq);
        
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(appRes);
        
        // 戻り結果をJsonで戻す
        return resultJson;
    }
    
    /**
    * ユーザ利用できるメニューを削除
    *
    * @param apiReq {@code IfaServiceNowMenuAndAclManagerA011ApiRequest }
    * @return {@code String}
    * @throws Exception 例外が発生した場合
    */
    @PostMapping("/ifaDelAvailableMenuListByUserId")
    public String invokeA011(@Validated @RequestBody IfaServiceNowMenuAndAclManagerA011ApiRequest apiReq)
            throws Exception {
        
        IfaServiceNowMenuAndAclManagerA011ResponseDto appRes = ApiRequestUtil.invoke(
                "cmpIfaServiceNowMenuAndAclManagerService", "invokeA011",
                new TypeReference<IfaServiceNowMenuAndAclManagerA011ResponseDto>() {
                }, apiReq);
        
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(appRes);
        
        // 戻り結果をJsonで戻す
        return resultJson;
    }
    
    /**
    * ユーザ利用できるメニューを登録
    *
    * @param apiReq {@code IfaServiceNowMenuAndAclManagerA012ApiRequest }
    * @return {@code String}
    * @throws Exception 例外が発生した場合
    */
    @PostMapping("/ifaCreAvailableMenuListByUserId")
    public String invokeA012(@Validated @RequestBody IfaServiceNowMenuAndAclManagerA012ApiRequest apiReq)
            throws Exception {
        
        IfaServiceNowMenuAndAclManagerA012ResponseDto appRes = ApiRequestUtil.invoke(
                "cmpIfaServiceNowMenuAndAclManagerService", "invokeA012",
                new TypeReference<IfaServiceNowMenuAndAclManagerA012ResponseDto>() {
                }, apiReq);
        
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(appRes);
        
        // 戻り結果をJsonで戻す
        return resultJson;
    }
    
    /**
    * ユーザ利用可能メニュー一覧取得
    *
    * @param apiReq {@code IfaServiceNowMenuAndAclManagerA013ApiRequest }
    * @return {@code String}
    * @throws Exception 例外が発生した場合
    */
    @PostMapping("/ifaRefAvailableMenuListByUserId")
    public String invokeA013(@Validated @RequestBody IfaServiceNowMenuAndAclManagerA013ApiRequest apiReq)
            throws Exception {
        
        IfaServiceNowMenuAndAclManagerA013ResponseDto appRes = ApiRequestUtil.invoke(
                "cmpIfaServiceNowMenuAndAclManagerService", "invokeA013",
                new TypeReference<IfaServiceNowMenuAndAclManagerA013ResponseDto>() {
                }, apiReq);
        
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(appRes);
        
        // 戻り結果をJsonで戻す
        return resultJson;
    }
    
}
