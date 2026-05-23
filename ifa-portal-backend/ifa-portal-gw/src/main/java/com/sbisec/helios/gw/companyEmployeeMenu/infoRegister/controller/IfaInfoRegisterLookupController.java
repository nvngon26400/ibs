package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupA009RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupA009ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupX001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterLookupX001ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoRegisterLookupA001ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoRegisterLookupA001ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoRegisterLookupA009ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoRegisterLookupA009ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoRegisterLookupX001ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoRegisterLookupX001ApiResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0501_01-01
 * 画面名：情報登録照会
 *
 * @author SCSK今井
 2024/05/23 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0501_01-01", screenNumber = "")
public class IfaInfoRegisterLookupController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/companyEmployeeMenu/infoRegister/ifaInfoRegisterLookupInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaInfoRegisterLookupA001ApiRequest
     * APIレスポンス：IfaInfoRegisterLookupA001ApiResponse
     * Dtoリクエスト：IfaInfoRegisterLookupA001RequestDto
     * Dtoレスポンス：IfaInfoRegisterLookupA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaInfoRegisterLookupInitializeA001")
    public String initializeA001(@RequestBody IfaInfoRegisterLookupA001ApiRequest apiReq) throws Exception {
        
        IfaInfoRegisterLookupA001RequestDto appReq = new IfaInfoRegisterLookupA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaInfoRegisterLookupA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaInfoRegisterLookupService",
                "initializeA001", new TypeReference<DataList<IfaInfoRegisterLookupA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaInfoRegisterLookupA001ApiResponse> apiRes = new DataList<IfaInfoRegisterLookupA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/companyEmployeeMenu/infoRegister/ifaInfoRegisterLookupSearchDisplayX001
     * アクションID：X001
     * アクション名：検索表示
     * APIリクエスト：IfaInfoRegisterLookupX001ApiRequest
     * Apiレスポンス：IfaInfoRegisterLookupX001ApiResponse
     * Dtoリクエスト：IfaInfoRegisterLookupX001RequestDto
     * Dtoレスポンス：IfaInfoRegisterLookupX001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaInfoRegisterLookupSearchDisplayX001")
    public String searchDisplayX001(@RequestBody IfaInfoRegisterLookupX001ApiRequest apiReq) throws Exception {
        
        IfaInfoRegisterLookupX001RequestDto appReq = new IfaInfoRegisterLookupX001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaInfoRegisterLookupX001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaInfoRegisterLookupService",
                "searchDisplayX001", new TypeReference<DataList<IfaInfoRegisterLookupX001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaInfoRegisterLookupX001ApiResponse> apiRes = new DataList<IfaInfoRegisterLookupX001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/companyEmployeeMenu/infoRegister/ifaInfoRegisterLookupDeleteA009
     * アクションID：A009
     * アクション名：削除
     * APIリクエスト：IfaInfoRegisterLookupA009ApiRequest
     * APIレスポンス：IfaInfoRegisterLookupA009ApiResponse
     * Dtoリクエスト：IfaInfoRegisterLookupA009RequestDto
     * Dtoレスポンス：IfaInfoRegisterLookupA009ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaInfoRegisterLookupDeleteA009")
    public String deleteA009(@RequestBody IfaInfoRegisterLookupA009ApiRequest apiReq) throws Exception {
        
        IfaInfoRegisterLookupA009RequestDto appReq = new IfaInfoRegisterLookupA009RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaInfoRegisterLookupA009ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaInfoRegisterLookupService",
                "deleteA009", new TypeReference<DataList<IfaInfoRegisterLookupA009ResponseDto>>() {
                }, appReq);
        
        DataList<IfaInfoRegisterLookupA009ApiResponse> apiRes = new DataList<IfaInfoRegisterLookupA009ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
