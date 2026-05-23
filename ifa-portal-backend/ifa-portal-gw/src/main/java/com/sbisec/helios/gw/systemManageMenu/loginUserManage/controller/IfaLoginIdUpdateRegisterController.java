package com.sbisec.helios.gw.systemManageMenu.loginUserManage.controller;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA001DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA001DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA002DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA002DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA003DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA003DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA004DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA004DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA005DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA005DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA006DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA006DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA014DeleteCcsDataDtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA014DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA014DtoResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdUpdateRegisterA001ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdUpdateRegisterA001ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdUpdateRegisterA002ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdUpdateRegisterA002ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdUpdateRegisterA003ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdUpdateRegisterA003ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdUpdateRegisterA004ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdUpdateRegisterA004ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdUpdateRegisterA005ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdUpdateRegisterA005ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdUpdateRegisterA006ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdUpdateRegisterA006ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdUpdateRegisterA014ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdUpdateRegisterA014ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdUpdateRegisterA014DeleteCcsDataApiRequest;

/**
 * 画面ID：SUB0601_01-03_1
 * 画面名：ログインID更新登録
 * @author 齋藤
 *
 * 2023/11/06 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN06", id = "SUB0601_01-03_1", screenNumber = "")
public class IfaLoginIdUpdateRegisterController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterInitialDisplayA001
     * アクションID：A001
     * アクション名：初期表示
     * APIリクエスト：IfaLoginIdUpdateRegisterA001ApiRequest
     * Apiレスポンス：IfaLoginIdUpdateRegisterA001ApiResponse
     * Dtoリクエスト：IfaLoginIdUpdateRegisterA001DtoRequest
     * Dtoレスポンス：IfaLoginIdUpdateRegisterA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterInitialDisplayA001")
    public String initialDisplayA001(@RequestBody IfaLoginIdUpdateRegisterA001ApiRequest apiReq) throws Exception {
        
        IfaLoginIdUpdateRegisterA001DtoRequest appReq = new IfaLoginIdUpdateRegisterA001DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaLoginIdUpdateRegisterA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaLoginIdUpdateRegisterService", "initialDisplayA001",
                new TypeReference<DataList<IfaLoginIdUpdateRegisterA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaLoginIdUpdateRegisterA001ApiResponse> apiRes = new DataList<IfaLoginIdUpdateRegisterA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterAffiliationAuthorityUpdateA002
     * アクションID：A002
     * アクション名：所属権限更新
     * APIリクエスト：IfaLoginIdUpdateRegisterA002ApiRequest
     * Apiレスポンス：IfaLoginIdUpdateRegisterA002ApiResponse
     * Dtoリクエスト：IfaLoginIdUpdateRegisterA002DtoRequest
     * Dtoレスポンス：IfaLoginIdUpdateRegisterA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterAffiliationAuthorityUpdateA002")
    public String affiliationAuthorityUpdateA002(@RequestBody IfaLoginIdUpdateRegisterA002ApiRequest apiReq)
            throws Exception {
        
        IfaLoginIdUpdateRegisterA002DtoRequest appReq = new IfaLoginIdUpdateRegisterA002DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaLoginIdUpdateRegisterA002DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaLoginIdUpdateRegisterService", "affiliationAuthorityUpdateA002",
                new TypeReference<DataList<IfaLoginIdUpdateRegisterA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaLoginIdUpdateRegisterA002ApiResponse> apiRes = new DataList<IfaLoginIdUpdateRegisterA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterHeadOfficeNameUpdateA003
     * アクションID：A003
     * アクション名：本支名更新
     * APIリクエスト：IfaLoginIdUpdateRegisterA003ApiRequest
     * Apiレスポンス：IfaLoginIdUpdateRegisterA003ApiResponse
     * Dtoリクエスト：IfaLoginIdUpdateRegisterA003DtoRequest
     * Dtoレスポンス：IfaLoginIdUpdateRegisterA003DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterHeadOfficeNameUpdateA003")
    public String headOfficeNameUpdateA003(@RequestBody IfaLoginIdUpdateRegisterA003ApiRequest apiReq)
            throws Exception {
        
        IfaLoginIdUpdateRegisterA003DtoRequest appReq = new IfaLoginIdUpdateRegisterA003DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaLoginIdUpdateRegisterA003DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaLoginIdUpdateRegisterService", "headOfficeNameUpdateA003",
                new TypeReference<DataList<IfaLoginIdUpdateRegisterA003DtoResponse>>() {
                }, appReq);
        
        DataList<IfaLoginIdUpdateRegisterA003ApiResponse> apiRes = new DataList<IfaLoginIdUpdateRegisterA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterBrokerNameUpdateA004
     * アクションID：A004
     * アクション名：仲介業者名更新
     * APIリクエスト：IfaLoginIdUpdateRegisterA004ApiRequest
     * Apiレスポンス：IfaLoginIdUpdateRegisterA004ApiResponse
     * Dtoリクエスト：IfaLoginIdUpdateRegisterA004DtoRequest
     * Dtoレスポンス：IfaLoginIdUpdateRegisterA004DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterBrokerNameUpdateA004")
    public String brokerNameUpdateA004(@RequestBody IfaLoginIdUpdateRegisterA004ApiRequest apiReq) throws Exception {
        
        IfaLoginIdUpdateRegisterA004DtoRequest appReq = new IfaLoginIdUpdateRegisterA004DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaLoginIdUpdateRegisterA004DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaLoginIdUpdateRegisterService", "brokerNameUpdateA004",
                new TypeReference<DataList<IfaLoginIdUpdateRegisterA004DtoResponse>>() {
                }, appReq);
        
        DataList<IfaLoginIdUpdateRegisterA004ApiResponse> apiRes = new DataList<IfaLoginIdUpdateRegisterA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterBrokerBranchNameUpdateA005
     * アクションID：A005
     * アクション名：仲介業者支店名更新
     * APIリクエスト：IfaLoginIdUpdateRegisterA005ApiRequest
     * Apiレスポンス：IfaLoginIdUpdateRegisterA005ApiResponse
     * Dtoリクエスト：IfaLoginIdUpdateRegisterA005DtoRequest
     * Dtoレスポンス：IfaLoginIdUpdateRegisterA005DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterBrokerBranchNameUpdateA005")
    public String brokerBranchNameUpdateA005(@RequestBody IfaLoginIdUpdateRegisterA005ApiRequest apiReq)
            throws Exception {
        
        IfaLoginIdUpdateRegisterA005DtoRequest appReq = new IfaLoginIdUpdateRegisterA005DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaLoginIdUpdateRegisterA005DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaLoginIdUpdateRegisterService", "brokerBranchNameUpdateA005",
                new TypeReference<DataList<IfaLoginIdUpdateRegisterA005DtoResponse>>() {
                }, appReq);
        
        DataList<IfaLoginIdUpdateRegisterA005ApiResponse> apiRes = new DataList<IfaLoginIdUpdateRegisterA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterResetA006
     * アクションID：A006
     * アクション名：リセット
     * APIリクエスト：IfaLoginIdUpdateRegisterA006ApiRequest
     * Apiレスポンス：IfaLoginIdUpdateRegisterA006ApiResponse
     * Dtoリクエスト：IfaLoginIdUpdateRegisterA006DtoRequest
     * Dtoレスポンス：IfaLoginIdUpdateRegisterA006DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterResetA006")
    public String resetA006(@RequestBody IfaLoginIdUpdateRegisterA006ApiRequest apiReq) throws Exception {
        
        IfaLoginIdUpdateRegisterA006DtoRequest appReq = new IfaLoginIdUpdateRegisterA006DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaLoginIdUpdateRegisterA006DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaLoginIdUpdateRegisterService", "resetA006",
                new TypeReference<DataList<IfaLoginIdUpdateRegisterA006DtoResponse>>() {
                }, appReq);
        
        DataList<IfaLoginIdUpdateRegisterA006ApiResponse> apiRes = new DataList<IfaLoginIdUpdateRegisterA006ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterUpdateOkA014
     * アクションID：A014
     * アクション名：更新（OK）
     * APIリクエスト：IfaLoginIdUpdateRegisterA014ApiRequest
     * Apiレスポンス：IfaLoginIdUpdateRegisterA014ApiResponse
     * Dtoリクエスト：IfaLoginIdUpdateRegisterA014DtoRequest
     * Dtoレスポンス：IfaLoginIdUpdateRegisterA014DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterUpdateOkA014")
    public String updateOkA014(@RequestBody IfaLoginIdUpdateRegisterA014ApiRequest apiReq) throws Exception {
        
        IfaLoginIdUpdateRegisterA014DtoRequest appReq = new IfaLoginIdUpdateRegisterA014DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaLoginIdUpdateRegisterA014DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaLoginIdUpdateRegisterService", "updateOkA014",
                new TypeReference<DataList<IfaLoginIdUpdateRegisterA014DtoResponse>>() {
                }, appReq);
        
        DataList<IfaLoginIdUpdateRegisterA014ApiResponse> apiRes = new DataList<IfaLoginIdUpdateRegisterA014ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterDeleteCcsData
     * アクションID：A014　CCSリセット処理
     * アクション名：A014　CCSリセット処理
     * APIリクエスト：IfaLoginIdUpdateRegisterA014ApiRequest
     * Apiレスポンス：IfaLoginIdUpdateRegisterA014ApiResponse
     * Dtoリクエスト：IfaLoginIdUpdateRegisterA014DtoRequest
     * Dtoレスポンス：IfaLoginIdUpdateRegisterA014DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterDeleteCcsData")
    public String deleteCcsData(@RequestBody IfaLoginIdUpdateRegisterA014DeleteCcsDataApiRequest apiReq)
            throws Exception {
        
        IfaLoginIdUpdateRegisterA014DeleteCcsDataDtoRequest appReq = new IfaLoginIdUpdateRegisterA014DeleteCcsDataDtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<Object> appRes = ApiRequestUtil.invoke("cmpIfaLoginIdUpdateRegisterService", "custMgmtUpdateCcsData",
                new TypeReference<DataList<Object>>() {
                }, appReq);
        
        DataList<Object> apiRes = new DataList<Object>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
