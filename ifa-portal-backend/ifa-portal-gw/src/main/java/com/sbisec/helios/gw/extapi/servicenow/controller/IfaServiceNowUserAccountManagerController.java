package com.sbisec.helios.gw.extapi.servicenow.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA005ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA006ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA007ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA008ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA009ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA014ResponseDto;
import com.sbisec.helios.gw.extapi.servicenow.form.IfaServiceNowUserAccountManagerA005ApiRequest;
import com.sbisec.helios.gw.extapi.servicenow.form.IfaServiceNowUserAccountManagerA006ApiRequest;
import com.sbisec.helios.gw.extapi.servicenow.form.IfaServiceNowUserAccountManagerA007ApiRequest;
import com.sbisec.helios.gw.extapi.servicenow.form.IfaServiceNowUserAccountManagerA008ApiRequest;
import com.sbisec.helios.gw.extapi.servicenow.form.IfaServiceNowUserAccountManagerA009ApiRequest;
import com.sbisec.helios.gw.extapi.servicenow.form.IfaServiceNowUserAccountManagerA014ApiRequest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
public class IfaServiceNowUserAccountManagerController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
    * ログインID登録
    *
    * @param apiReq {@code IfaServiceNowUserAccountManagerA005ApiRequest }
    * @return {@code String}
    * @throws Exception 例外が発生した場合
    */
    @PostMapping("/ifaCreUserAccountAndAcl")
    public String invokeA005(@Validated @RequestBody IfaServiceNowUserAccountManagerA005ApiRequest apiReq)
            throws Exception {
        
        IfaServiceNowUserAccountManagerA005ResponseDto appRes = ApiRequestUtil.invoke(
                "cmpIfaServiceNowUserAccountManagerService", "invokeA005",
                new TypeReference<IfaServiceNowUserAccountManagerA005ResponseDto>() {
                }, apiReq);
        
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(appRes);
        
        // 戻り結果をJsonで戻す
        return resultJson;
    }
    
    /**
    * 申請情報項目一覧を取得
    *
    * @param apiReq {@code IfaServiceNowUserAccountManagerA006ApiRequest }
    * @return {@code String}
    * @throws Exception 例外が発生した場合
    */
    @PostMapping("/ifaRefUserAccountAndAclInfoByUserId")
    public String invokeA006(@Validated @RequestBody IfaServiceNowUserAccountManagerA006ApiRequest apiReq)
            throws Exception {
        
        IfaServiceNowUserAccountManagerA006ResponseDto appRes = ApiRequestUtil.invoke(
                "cmpIfaServiceNowUserAccountManagerService", "invokeA006",
                new TypeReference<IfaServiceNowUserAccountManagerA006ResponseDto>() {
                }, apiReq);
        
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(appRes);
        
        // 戻り結果をJsonで戻す
        return resultJson;
    }
    
    /**
    * ログインID更新
    *
    * @param apiReq {@code IfaUpdUserAccountAndAclRequest }
    * @return {@code String}
    * @throws Exception 例外が発生した場合
    */
    @PostMapping("/ifaUpdUserAccountAndAcl")
    public String invokeA007(@Validated @RequestBody IfaServiceNowUserAccountManagerA007ApiRequest apiReq)
            throws Exception {
        
        IfaServiceNowUserAccountManagerA007ResponseDto appRes = ApiRequestUtil.invoke(
                "cmpIfaServiceNowUserAccountManagerService", "invokeA007",
                new TypeReference<IfaServiceNowUserAccountManagerA007ResponseDto>() {
                }, apiReq);
        
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(appRes);
        
        // 戻り結果をJsonで戻す
        return resultJson;
    }
    
    /**
    * ユーザ&&利用できるメニューを削除
    *
    * @param apiReq {@code IfaServiceNowUserAccountManagerA008ApiRequest }
    * @return {@code String}
    * @throws Exception 例外が発生した場合
    */
    @PostMapping("/ifaDelUserAccountAndAcl")
    public String invokeA008(@Validated @RequestBody IfaServiceNowUserAccountManagerA008ApiRequest apiReq)
            throws Exception {
        
        IfaServiceNowUserAccountManagerA008ResponseDto appRes = ApiRequestUtil.invoke(
                "cmpIfaServiceNowUserAccountManagerService", "invokeA008",
                new TypeReference<IfaServiceNowUserAccountManagerA008ResponseDto>() {
                }, apiReq);
        
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(appRes);
        
        // 戻り結果をJsonで戻す
        return resultJson;
    }
    
    /**
    * 権限一覧取得
    *
    * @return {@code String}
    * @throws Exception 例外が発生した場合
    */
    @GetMapping("/ifaRefPrivList")
    public String invokeA009() throws Exception {
        
        IfaServiceNowUserAccountManagerA009ResponseDto appRes = ApiRequestUtil.invoke(
                "cmpIfaServiceNowUserAccountManagerService", "invokeA009",
                new TypeReference<IfaServiceNowUserAccountManagerA009ResponseDto>() {
                }, new IfaServiceNowUserAccountManagerA009ApiRequest());
        
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(appRes);
        
        // 戻り結果をJsonで戻す
        return resultJson;
    }

    /**
    * ログインID検索
    *
    * @param apiReq {@code IfaServiceNowUserAccountManagerA014ApiRequest }
    * @return {@code String}
    * @throws Exception 例外が発生した場合
    */
    @PostMapping("/ifaRefUserAccountAndAcl")
    public String invokeA014(@Validated @RequestBody IfaServiceNowUserAccountManagerA014ApiRequest apiReq)
            throws Exception {
        
        IfaServiceNowUserAccountManagerA014ResponseDto appRes = ApiRequestUtil.invoke(
                "cmpIfaServiceNowUserAccountManagerService", "invokeA014",
                new TypeReference<IfaServiceNowUserAccountManagerA014ResponseDto>() {
                }, apiReq);
        
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(appRes);
        
        // 戻り結果をJsonで戻す
        return resultJson;
    }
    
}
