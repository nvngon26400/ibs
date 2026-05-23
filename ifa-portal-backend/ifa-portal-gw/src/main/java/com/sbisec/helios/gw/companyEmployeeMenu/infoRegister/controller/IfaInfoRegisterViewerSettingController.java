package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA002DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA002DtoResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoRegisterViewerSettingA002ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoRegisterViewerSettingA002ApiResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA012DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA012DtoResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoRegisterViewerSettingA012ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoRegisterViewerSettingA012ApiResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA009DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA009DtoResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoRegisterViewerSettingA009ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoRegisterViewerSettingA009ApiResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA016DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA016DtoResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoRegisterViewerSettingA016ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoRegisterViewerSettingA016ApiResponse;

/**
 * 画面ID：SUB0501_01-04
 * 画面名：情報登録閲覧者設定
 *
 * @author SCSK 矢口
 2024/05/24 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0501_01-04", screenNumber = "")
public class IfaInfoRegisterViewerSettingController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/companyEmployeeMenu/infoRegister/ifaInfoRegisterViewerSettingInitializeInfoUpdateA002
     * アクションID：A002
     * アクション名：初期化（情報更新）
     * APIリクエスト：IfaInfoRegisterViewerSettingA002ApiRequest
     * Apiレスポンス：IfaInfoRegisterViewerSettingA002ApiResponse
     * Dtoリクエスト：IfaInfoRegisterViewerSettingA002DtoRequest
     * Dtoレスポンス：IfaInfoRegisterViewerSettingA002DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaInfoRegisterViewerSettingInitializeInfoUpdateA002")
    public String initializeInfoUpdateA002(@RequestBody IfaInfoRegisterViewerSettingA002ApiRequest apiReq)
            throws Exception {
        
        IfaInfoRegisterViewerSettingA002DtoRequest appReq = new IfaInfoRegisterViewerSettingA002DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaInfoRegisterViewerSettingA002DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaInfoRegisterViewerSettingService", "initializeInfoUpdateA002",
                new TypeReference<DataList<IfaInfoRegisterViewerSettingA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaInfoRegisterViewerSettingA002ApiResponse> apiRes = new DataList<IfaInfoRegisterViewerSettingA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/companyEmployeeMenu/infoRegister/ifaInfoRegisterViewerSettingIntermediarySearchA009
     * アクションID：A009
     * アクション名：仲介業者検索
     * APIリクエスト：IfaInfoRegisterViewerSettingA009ApiRequest
     * Apiレスポンス：IfaInfoRegisterViewerSettingA009ApiResponse
     * Dtoリクエスト：IfaInfoRegisterViewerSettingA009DtoRequest
     * Dtoレスポンス：IfaInfoRegisterViewerSettingA009DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaInfoRegisterViewerSettingIntermediarySearchA009")
    public String intermediarySearchA009(@RequestBody IfaInfoRegisterViewerSettingA009ApiRequest apiReq)
            throws Exception {
        
        IfaInfoRegisterViewerSettingA009DtoRequest appReq = new IfaInfoRegisterViewerSettingA009DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaInfoRegisterViewerSettingA009DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaInfoRegisterViewerSettingService", "intermediarySearchA009",
                new TypeReference<DataList<IfaInfoRegisterViewerSettingA009DtoResponse>>() {
                }, appReq);
        
        DataList<IfaInfoRegisterViewerSettingA009ApiResponse> apiRes = new DataList<IfaInfoRegisterViewerSettingA009ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/companyEmployeeMenu/infoRegister/ifaInfoRegisterViewerSettingPersonInChargeListDisplayA012
     * アクションID：A012
     * アクション名：担当者一覧表示
     * APIリクエスト：IfaInfoRegisterViewerSettingA012ApiRequest
     * Apiレスポンス：IfaInfoRegisterViewerSettingA012ApiResponse
     * Dtoリクエスト：IfaInfoRegisterViewerSettingA012DtoRequest
     * Dtoレスポンス：IfaInfoRegisterViewerSettingA012DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaInfoRegisterViewerSettingPersonInChargeListDisplayA012")
    public String personInChargeListDisplayA012(@RequestBody IfaInfoRegisterViewerSettingA012ApiRequest apiReq)
            throws Exception {
        
        IfaInfoRegisterViewerSettingA012DtoRequest appReq = new IfaInfoRegisterViewerSettingA012DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaInfoRegisterViewerSettingA012DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaInfoRegisterViewerSettingService", "personInChargeListDisplayA012",
                new TypeReference<DataList<IfaInfoRegisterViewerSettingA012DtoResponse>>() {
                }, appReq);
        
        DataList<IfaInfoRegisterViewerSettingA012ApiResponse> apiRes = new DataList<IfaInfoRegisterViewerSettingA012ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/companyEmployeeMenu/infoRegister/ifaInfoRegisterViewerSettingPersonInChargeSearchA016
     * アクションID：A016
     * アクション名：担当者検索
     * APIリクエスト：IfaInfoRegisterViewerSettingA016ApiRequest
     * Apiレスポンス：IfaInfoRegisterViewerSettingA016ApiResponse
     * Dtoリクエスト：IfaInfoRegisterViewerSettingA016DtoRequest
     * Dtoレスポンス：IfaInfoRegisterViewerSettingA016DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaInfoRegisterViewerSettingPersonInChargeSearchA016")
    public String personInChargeSearchA016(@RequestBody IfaInfoRegisterViewerSettingA016ApiRequest apiReq)
            throws Exception {
        
        IfaInfoRegisterViewerSettingA016DtoRequest appReq = new IfaInfoRegisterViewerSettingA016DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaInfoRegisterViewerSettingA016DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaInfoRegisterViewerSettingService", "personInChargeSearchA016",
                new TypeReference<DataList<IfaInfoRegisterViewerSettingA016DtoResponse>>() {
                }, appReq);
        
        DataList<IfaInfoRegisterViewerSettingA016ApiResponse> apiRes = new DataList<IfaInfoRegisterViewerSettingA016ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
