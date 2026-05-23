package com.sbisec.helios.gw.systemManageMenu.loginUserManage.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA001RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA001ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA002RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA002ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA007RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA007ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginUserManageManagerLookupA001ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginUserManageManagerLookupA001ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginUserManageManagerLookupA002ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginUserManageManagerLookupA002ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginUserManageManagerLookupA007ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.loginUserManage.form.IfaLoginUserManageManagerLookupA007ApiResponse;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0601_01-01
 * 画面名：ログイン者管理（管理者用）照会
 * @author <author-name>
 *
 * 2023/11/02 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN06", id = "SUB0601_01-01", screenNumber = "")
public class IfaLoginUserManageManagerLookupController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginUserManageManagerLookupInitialDisplayA001
     * アクションID：A001
     * アクション名：初期表示
     * APIリクエスト：IfaLoginUserManageManagerLookupA001ApiRequest
     * Apiレスポンス：IfaLoginUserManageManagerLookupA001ApiResponse
     * Dtoリクエスト：IfaLoginUserManageManagerLookupA001RequestDto
     * Dtoレスポンス：IfaLoginUserManageManagerLookupA001ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginUserManageManagerLookupInitialDisplayA001")
    public String initialDisplayA001(@RequestBody IfaLoginUserManageManagerLookupA001ApiRequest apiReq)
            throws Exception {
        
        IfaLoginUserManageManagerLookupA001RequestDto appReq = new IfaLoginUserManageManagerLookupA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaLoginUserManageManagerLookupA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaLoginUserManageManagerLookupService", "initialDisplayA001",
                new TypeReference<DataList<IfaLoginUserManageManagerLookupA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaLoginUserManageManagerLookupA001ApiResponse> apiRes = new DataList<IfaLoginUserManageManagerLookupA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginUserManageManagerLookupSearchA002
     * アクションID：A002
     * アクション名：検索
     * APIリクエスト：IfaLoginUserManageManagerLookupA002ApiRequest
     * Apiレスポンス：IfaLoginUserManageManagerLookupA002ApiResponse
     * Dtoリクエスト：IfaLoginUserManageManagerLookupA002RequestDto
     * Dtoレスポンス：IfaLoginUserManageManagerLookupA002ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginUserManageManagerLookupSearchA002")
    public String searchA002(@RequestBody IfaLoginUserManageManagerLookupA002ApiRequest apiReq) throws Exception {
        
        IfaLoginUserManageManagerLookupA002RequestDto appReq = new IfaLoginUserManageManagerLookupA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaLoginUserManageManagerLookupA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaLoginUserManageManagerLookupService", "searchA002",
                new TypeReference<DataList<IfaLoginUserManageManagerLookupA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaLoginUserManageManagerLookupA002ApiResponse> apiRes = new DataList<IfaLoginUserManageManagerLookupA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginUserManageManagerLookupDeleteA007
     * アクションID：A007
     * アクション名：削除
     * APIリクエスト：IfaLoginUserManageManagerLookupA007ApiRequest
     * Apiレスポンス：IfaLoginUserManageManagerLookupA007ApiResponse
     * Dtoリクエスト：IfaLoginUserManageManagerLookupA007DtoRequest
     * Dtoレスポンス：IfaLoginUserManageManagerLookupA007DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginUserManageManagerLookupDeleteA007")
    public String deleteA007(@RequestBody IfaLoginUserManageManagerLookupA007ApiRequest apiReq) throws Exception {
        
        IfaLoginUserManageManagerLookupA007RequestDto appReq = new IfaLoginUserManageManagerLookupA007RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaLoginUserManageManagerLookupA007ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaLoginUserManageManagerLookupService", "deleteA007",
                new TypeReference<DataList<IfaLoginUserManageManagerLookupA007ResponseDto>>() {
                }, appReq);
        
        DataList<IfaLoginUserManageManagerLookupA007ApiResponse> apiRes = new DataList<IfaLoginUserManageManagerLookupA007ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
