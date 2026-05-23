package com.sbisec.helios.gw.suggestionBox.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA001DtoRequest;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA001DtoResponse;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA002DtoRequest;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA002DtoResponse;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonA001ApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonA001ApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonA002ApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonA002ApiResponse;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB00_02-06_1
 * 画面名：皆様からの要望
 * @author 林
 *
 * 2025/06/18 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "SUB00_02-06_1", screenNumber = "")
public class IfaSuggestionBoxCommonFromBrokerController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/suggestionBox/ifaSuggestionBoxCommonBrokerA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaSuggestionBoxCommonA001ApiRequest
     * Apiレスポンス：IfaSuggestionBoxCommonA001ApiResponse
     * Dtoリクエスト：IfaSuggestionBoxCommonA001DtoRequest
     * Dtoレスポンス：IfaSuggestionBoxCommonA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/suggestionBox/ifaSuggestionBoxCommonBrokerA001")
    public String initializeA001(@RequestBody IfaSuggestionBoxCommonA001ApiRequest apiReq) throws Exception {
        
    	IfaSuggestionBoxCommonA001DtoRequest appReq = new IfaSuggestionBoxCommonA001DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaSuggestionBoxCommonA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaSuggestionBoxCommonService", "initializeA001",
                new TypeReference<DataList<IfaSuggestionBoxCommonA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaSuggestionBoxCommonA001ApiResponse> apiRes = new DataList<IfaSuggestionBoxCommonA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    
    /**
     * 
     * アクセス：/suggestionBox/ifaSuggestionBoxCommonBrokerA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaSuggestionBoxCommonA002ApiRequest
     * Apiレスポンス：IfaSuggestionBoxCommonA002ApiResponse
     * Dtoリクエスト：IfaSuggestionBoxCommonA002DtoRequest
     * Dtoレスポンス：IfaSuggestionBoxCommonA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/suggestionBox/ifaSuggestionBoxCommonBrokerA002")
    public String displayA002(@RequestBody IfaSuggestionBoxCommonA002ApiRequest apiReq) throws Exception {
        
    	IfaSuggestionBoxCommonA002DtoRequest appReq = new IfaSuggestionBoxCommonA002DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaSuggestionBoxCommonA002DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaSuggestionBoxCommonService", "displayA002",
                new TypeReference<DataList<IfaSuggestionBoxCommonA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaSuggestionBoxCommonA002ApiResponse> apiRes = new DataList<IfaSuggestionBoxCommonA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    

    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
