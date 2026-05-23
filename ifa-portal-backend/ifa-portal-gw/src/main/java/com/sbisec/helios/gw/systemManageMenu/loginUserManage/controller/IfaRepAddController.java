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
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA001DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA001DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA002DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA002DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA003DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA003DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA005DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA005DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA006DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA006DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA008DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA008DtoResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaRepAddA001ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaRepAddA001ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaRepAddA002ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaRepAddA002ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaRepAddA003ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaRepAddA003ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaRepAddA005ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaRepAddA005ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaRepAddA006ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaRepAddA006ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaRepAddA008ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaRepAddA008ApiResponse;

/**
 * 画面ID：SUB0601_01-06_1
 * 画面名：担当者追加
 *
 * @author fin-w66253 2023/09/08 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN06", id = "SUB0601_01-06_1", screenNumber = "1", ignoreCheck = true)
public class IfaRepAddController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/systemManageMenu/loginUserManage/ifaRepAddInitialDisplayA001
     * アクションID：A001
     * アクション名：初期表示
     * APIリクエスト：IfaRepAddA001ApiRequest
     * Apiレスポンス：IfaRepAddA001ApiResponse
     * Dtoリクエスト：IfaRepAddA001DtoRequest
     * Dtoレスポンス：IfaRepAddA001DtoResponse
     *
     * @param apiReq APIリクエスト
     * @return Apiレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaRepAddInitialDisplayA001")
    public String initialDisplayA001(@RequestBody IfaRepAddA001ApiRequest apiReq) throws Exception {
        
        IfaRepAddA001DtoRequest appReq = new IfaRepAddA001DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaRepAddA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaRepAddService", "initialDisplayA001",
                new TypeReference<DataList<IfaRepAddA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaRepAddA001ApiResponse> apiRes = new DataList<IfaRepAddA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/systemManageMenu/loginUserManage/ifaRepAddBrokerNameUpdateA002
     * アクションID：A002
     * アクション名：仲介業者名更新
     * APIリクエスト：IfaRepAddA002ApiRequest
     * Apiレスポンス：IfaRepAddA002ApiResponse
     * Dtoリクエスト：IfaRepAddA002DtoRequest
     * Dtoレスポンス：IfaRepAddA002DtoResponse
     *
     * @param apiReq APIリクエスト
     * @return Apiレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaRepAddBrokerNameUpdateA002")
    public String brokerNameUpdateA002(@RequestBody IfaRepAddA002ApiRequest apiReq) throws Exception {
        
        IfaRepAddA002DtoRequest appReq = new IfaRepAddA002DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaRepAddA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaRepAddService",
                "brokerNameUpdateA002", new TypeReference<DataList<IfaRepAddA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaRepAddA002ApiResponse> apiRes = new DataList<IfaRepAddA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/systemManageMenu/loginUserManage/ifaRepAddBrokerBranchNameUpdateA003
     * アクションID：A003
     * アクション名：仲介業者支店名更新
     * APIリクエスト：IfaRepAddA003ApiRequest
     * Apiレスポンス：IfaRepAddA003ApiResponse
     * Dtoリクエスト：IfaRepAddA003DtoRequest
     * Dtoレスポンス：IfaRepAddA003DtoResponse
     *
     * @param apiReq APIリクエスト
     * @return Apiレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaRepAddBrokerBranchNameUpdateA003")
    public String brokerBranchNameUpdateA003(@RequestBody IfaRepAddA003ApiRequest apiReq) throws Exception {
        
        IfaRepAddA003DtoRequest appReq = new IfaRepAddA003DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaRepAddA003DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaRepAddService",
                "brokerBranchNameUpdateA003", new TypeReference<DataList<IfaRepAddA003DtoResponse>>() {
                }, appReq);
        
        DataList<IfaRepAddA003ApiResponse> apiRes = new DataList<IfaRepAddA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/systemManageMenu/loginUserManage/ifaRepAddAddA005
     * アクションID：A005
     * アクション名：追加
     * APIリクエスト：IfaRepAddA005ApiRequest
     * Apiレスポンス：IfaRepAddA005ApiResponse
     * Dtoリクエスト：IfaRepAddA005DtoRequest
     * Dtoレスポンス：IfaRepAddA005DtoResponse
     *
     * @param apiReq APIリクエスト
     * @return Apiレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaRepAddAddA005")
    public String addA005(@RequestBody IfaRepAddA005ApiRequest apiReq) throws Exception {
        
        IfaRepAddA005DtoRequest appReq = new IfaRepAddA005DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaRepAddA005DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaRepAddService", "addA005",
                new TypeReference<DataList<IfaRepAddA005DtoResponse>>() {
                }, appReq);
        
        DataList<IfaRepAddA005ApiResponse> apiRes = new DataList<IfaRepAddA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/systemManageMenu/loginUserManage/ifaRepAddAdditionOkA006
     * アクションID：A006
     * アクション名：追加（OK）
     * APIリクエスト：IfaRepAddA006ApiRequest
     * Apiレスポンス：IfaRepAddA006ApiResponse
     * Dtoリクエスト：IfaRepAddA006DtoRequest
     * Dtoレスポンス：IfaRepAddA006DtoResponse
     *
     * @param apiReq APIリクエスト
     * @return Apiレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaRepAddAdditionOkA006")
    public String additionOkA006(@RequestBody IfaRepAddA006ApiRequest apiReq) throws Exception {
        
        IfaRepAddA006DtoRequest appReq = new IfaRepAddA006DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaRepAddA006DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaRepAddService", "additionOkA006",
                new TypeReference<DataList<IfaRepAddA006DtoResponse>>() {
                }, appReq);
        
        DataList<IfaRepAddA006ApiResponse> apiRes = new DataList<IfaRepAddA006ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/systemManageMenu/loginUserManage/ifaRepAddBranchNameUpdateA008
     * アクションID：A008
     * アクション名：本店／支店名更新
     * APIリクエスト：IfaRepAddA008ApiRequest
     * Apiレスポンス：IfaRepAddA008ApiResponse
     * Dtoリクエスト：IfaRepAddA008DtoRequest
     * Dtoレスポンス：IfaRepAddA008DtoResponse
     *
     * @param apiReq APIリクエスト
     * @return Apiレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaRepAddBranchNameUpdateA008")
    public String branchNameUpdateA008(@RequestBody IfaRepAddA008ApiRequest apiReq) throws Exception {
        
        IfaRepAddA008DtoRequest appReq = new IfaRepAddA008DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaRepAddA008DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaRepAddService",
                "branchNameUpdateA008", new TypeReference<DataList<IfaRepAddA008DtoResponse>>() {
                }, appReq);
        
        DataList<IfaRepAddA008ApiResponse> apiRes = new DataList<IfaRepAddA008ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
