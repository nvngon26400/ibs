package com.sbisec.helios.gw.systemManageMenu.loginUserManage.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA001RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA001ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA002RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA002ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA003RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA003ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA004RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA004ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA005RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA005ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA006RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA006ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA011RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA011ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdNewRegisterA001ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdNewRegisterA001ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdNewRegisterA002ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdNewRegisterA002ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdNewRegisterA003ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdNewRegisterA003ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdNewRegisterA004ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdNewRegisterA004ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdNewRegisterA005ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdNewRegisterA005ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdNewRegisterA006ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdNewRegisterA006ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdNewRegisterA011ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginIdNewRegisterA011ApiResponse;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0601_01-02_1
 * 画面名：ログインID新規登録
 * @author 布施佑太
 *
 * 2023/11/09 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN06", id = "SUB0601_01-02_1", screenNumber = "")
public class IfaLoginIdNewRegisterController extends BaseController{

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterInitialDisplayA001
     * アクションID：A001
     * アクション名：初期表示
     * APIリクエスト：IfaLoginIdNewRegisterA001ApiRequest
     * Apiレスポンス：IfaLoginIdNewRegisterA001ApiResponse
     * Dtoリクエスト：IfaLoginIdNewRegisterA001RequestDto
     * Dtoレスポンス：IfaLoginIdNewRegisterA001ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterInitialDisplayA001")
    public String initialDisplayA001(@RequestBody IfaLoginIdNewRegisterA001ApiRequest apiReq)throws Exception
    {

        IfaLoginIdNewRegisterA001RequestDto appReq = new IfaLoginIdNewRegisterA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaLoginIdNewRegisterA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaLoginIdNewRegisterService",
                "initialDisplayA001", new TypeReference<DataList<IfaLoginIdNewRegisterA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaLoginIdNewRegisterA001ApiResponse> apiRes = new DataList<IfaLoginIdNewRegisterA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterPersonInChargeNameUpdateA013
     * アクションID：A002
     * アクション名：所属権限更新
     * APIリクエスト：IfaLoginIdNewRegisterA002ApiRequest
     * Apiレスポンス：IfaLoginIdNewRegisterA002ApiResponse
     * Dtoリクエスト：IfaLoginIdNewRegisterA002RequestDto
     * Dtoレスポンス：IfaLoginIdNewRegisterA002ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterAffiliationAuthorityUpdateA002")
    public String affiliationAuthorityUpdateA002(@RequestBody IfaLoginIdNewRegisterA002ApiRequest apiReq)throws Exception
    {

        IfaLoginIdNewRegisterA002RequestDto appReq = new IfaLoginIdNewRegisterA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaLoginIdNewRegisterA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaLoginIdNewRegisterService",
                "affiliationAuthorityUpdateA002", new TypeReference<DataList<IfaLoginIdNewRegisterA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaLoginIdNewRegisterA002ApiResponse> apiRes = new DataList<IfaLoginIdNewRegisterA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterHeadOfficeNameUpdateA003
     * アクションID：A003
     * アクション名：本支名更新
     * APIリクエスト：IfaLoginIdNewRegisterA003ApiRequest
     * Apiレスポンス：IfaLoginIdNewRegisterA003ApiResponse
     * Dtoリクエスト：IfaLoginIdNewRegisterA003RequestDto
     * Dtoレスポンス：IfaLoginIdNewRegisterA003ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterHeadOfficeNameUpdateA003")
    public String headOfficeNameUpdateA003(@RequestBody IfaLoginIdNewRegisterA003ApiRequest apiReq)throws Exception
    {

        IfaLoginIdNewRegisterA003RequestDto appReq = new IfaLoginIdNewRegisterA003RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaLoginIdNewRegisterA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaLoginIdNewRegisterService",
                "headOfficeNameUpdateA003", new TypeReference<DataList<IfaLoginIdNewRegisterA003ResponseDto>>() {
                }, appReq);
        
        DataList<IfaLoginIdNewRegisterA003ApiResponse> apiRes = new DataList<IfaLoginIdNewRegisterA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterBrokerNameUpdateA004
     * アクションID：A004
     * アクション名：仲介業者名更新
     * APIリクエスト：IfaLoginIdNewRegisterA004ApiRequest
     * Apiレスポンス：IfaLoginIdNewRegisterA004ApiResponse
     * Dtoリクエスト：IfaLoginIdNewRegisterA004RequestDto
     * Dtoレスポンス：IfaLoginIdNewRegisterA004ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterBrokerNameUpdateA004")
    public String brokerNameUpdateA004(@RequestBody IfaLoginIdNewRegisterA004ApiRequest apiReq)throws Exception
    {

        IfaLoginIdNewRegisterA004RequestDto appReq = new IfaLoginIdNewRegisterA004RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaLoginIdNewRegisterA004ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaLoginIdNewRegisterService",
                "brokerNameUpdateA004", new TypeReference<DataList<IfaLoginIdNewRegisterA004ResponseDto>>() {
                }, appReq);
        
        DataList<IfaLoginIdNewRegisterA004ApiResponse> apiRes = new DataList<IfaLoginIdNewRegisterA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterBrokerBranchNameUpdateA005
     * アクションID：A005
     * アクション名：仲介業者支店名更新
     * APIリクエスト：IfaLoginIdNewRegisterA005ApiRequest
     * Apiレスポンス：IfaLoginIdNewRegisterA005ApiResponse
     * Dtoリクエスト：IfaLoginIdNewRegisterA005RequestDto
     * Dtoレスポンス：IfaLoginIdNewRegisterA005ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterBrokerBranchNameUpdateA005")
    public String brokerBranchNameUpdateA005(@RequestBody IfaLoginIdNewRegisterA005ApiRequest apiReq)throws Exception
    {

        IfaLoginIdNewRegisterA005RequestDto appReq = new IfaLoginIdNewRegisterA005RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaLoginIdNewRegisterA005ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaLoginIdNewRegisterService",
                "brokerBranchNameUpdateA005", new TypeReference<DataList<IfaLoginIdNewRegisterA005ResponseDto>>() {
                }, appReq);
        
        DataList<IfaLoginIdNewRegisterA005ApiResponse> apiRes = new DataList<IfaLoginIdNewRegisterA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterResetA006
     * アクションID：A006
     * アクション名：リセット
     * APIリクエスト：IfaLoginIdNewRegisterA006ApiRequest
     * Apiレスポンス：IfaLoginIdNewRegisterA006ApiResponse
     * Dtoリクエスト：IfaLoginIdNewRegisterA006RequestDto
     * Dtoレスポンス：IfaLoginIdNewRegisterA006ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterResetA006")
    public String resetA006(@RequestBody IfaLoginIdNewRegisterA006ApiRequest apiReq)throws Exception
    {

        IfaLoginIdNewRegisterA006RequestDto appReq = new IfaLoginIdNewRegisterA006RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaLoginIdNewRegisterA006ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaLoginIdNewRegisterService",
                "resetA006", new TypeReference<DataList<IfaLoginIdNewRegisterA006ResponseDto>>() {
                }, appReq);
        
        DataList<IfaLoginIdNewRegisterA006ApiResponse> apiRes = new DataList<IfaLoginIdNewRegisterA006ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterRegistrationOkA011
     * アクションID：A011
     * アクション名：登録（OK）
     * APIリクエスト：IfaLoginIdNewRegisterA011ApiRequest
     * Apiレスポンス：IfaLoginIdNewRegisterA011ApiResponse
     * Dtoリクエスト：IfaLoginIdNewRegisterA011RequestDto
     * Dtoレスポンス：IfaLoginIdNewRegisterA011ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterRegistrationOkA011")
    public String registrationOkA011(@RequestBody IfaLoginIdNewRegisterA011ApiRequest apiReq)throws Exception
    {

        IfaLoginIdNewRegisterA011RequestDto appReq = new IfaLoginIdNewRegisterA011RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaLoginIdNewRegisterA011ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaLoginIdNewRegisterService",
                "registrationOkA011", new TypeReference<DataList<IfaLoginIdNewRegisterA011ResponseDto>>() {
                }, appReq);
        
        DataList<IfaLoginIdNewRegisterA011ApiResponse> apiRes = new DataList<IfaLoginIdNewRegisterA011ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

