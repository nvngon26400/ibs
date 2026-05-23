    package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA001DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA001DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA003DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA003DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA010DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA010DtoResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoRegisterManagerA001ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoRegisterManagerA001ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoRegisterManagerA003ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoRegisterManagerA003ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoRegisterManagerA010ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoRegisterManagerA010ApiResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0505_01-01
 * 画面名：コンプライアンス通信情報登録（管理者用）
 * @author <author-name>
 *
 * 2023/12/12 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0505_01-01", screenNumber = "", ignoreCheck = true)
public class IfaComplianceReportInfoRegisterManagerController extends BaseController{

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * 
     * アクセス：/companyEmployeeMenu/complianceReport/ifaComplianceReportInfoRegisterManagerInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaComplianceReportInfoRegisterManagerA001ApiRequest
     * Apiレスポンス：IfaComplianceReportInfoRegisterManagerA001ApiResponse
     * Dtoリクエスト：IfaComplianceReportInfoRegisterManagerA001DtoRequest
     * Dtoレスポンス：IfaComplianceReportInfoRegisterManagerA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value="/companyEmployeeMenu/complianceReport/ifaComplianceReportInfoRegisterManagerInitializeA001",method={RequestMethod.POST})
    public String initializeA001(@RequestBody IfaComplianceReportInfoRegisterManagerA001ApiRequest apiReq)throws Exception
    {

        IfaComplianceReportInfoRegisterManagerA001DtoRequest appReq = new IfaComplianceReportInfoRegisterManagerA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaComplianceReportInfoRegisterManagerA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaComplianceReportInfoRegisterManagerService",
                "initializeA001", new TypeReference<DataList<IfaComplianceReportInfoRegisterManagerA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaComplianceReportInfoRegisterManagerA001ApiResponse> apiRes = new DataList<IfaComplianceReportInfoRegisterManagerA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/companyEmployeeMenu/complianceReport/ifaComplianceReportInfoRegisterManagerStateA003
     * アクションID：A003
     * アクション名：状態
     * APIリクエスト：IfaComplianceReportInfoRegisterManagerA003ApiRequest
     * Apiレスポンス：IfaComplianceReportInfoRegisterManagerA003ApiResponse
     * Dtoリクエスト：IfaComplianceReportInfoRegisterManagerA003DtoRequest
     * Dtoレスポンス：IfaComplianceReportInfoRegisterManagerA003DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value="/companyEmployeeMenu/complianceReport/ifaComplianceReportInfoRegisterManagerStateA003",method={RequestMethod.POST})
    public String stateA003(@RequestBody IfaComplianceReportInfoRegisterManagerA003ApiRequest apiReq)throws Exception
    {

        IfaComplianceReportInfoRegisterManagerA003DtoRequest appReq = new IfaComplianceReportInfoRegisterManagerA003DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaComplianceReportInfoRegisterManagerA003DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaComplianceReportInfoRegisterManagerService",
                "stateA003", new TypeReference<DataList<IfaComplianceReportInfoRegisterManagerA003DtoResponse>>() {
                }, appReq);
        
        DataList<IfaComplianceReportInfoRegisterManagerA003ApiResponse> apiRes = new DataList<IfaComplianceReportInfoRegisterManagerA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/companyEmployeeMenu/complianceReport/ifaComplianceReportInfoRegisterManagerDeleteA010
     * アクションID：A010
     * アクション名：削除
     * APIリクエスト：IfaComplianceReportInfoRegisterManagerA010ApiRequest
     * Apiレスポンス：IfaComplianceReportInfoRegisterManagerA010ApiResponse
     * Dtoリクエスト：IfaComplianceReportInfoRegisterManagerA010DtoRequest
     * Dtoレスポンス：IfaComplianceReportInfoRegisterManagerA010DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value="/companyEmployeeMenu/complianceReport/ifaComplianceReportInfoRegisterManagerDeleteA010",method={RequestMethod.POST})
    public String deleteA010(@RequestBody IfaComplianceReportInfoRegisterManagerA010ApiRequest apiReq)throws Exception
    {

        IfaComplianceReportInfoRegisterManagerA010DtoRequest appReq = new IfaComplianceReportInfoRegisterManagerA010DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaComplianceReportInfoRegisterManagerA010DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaComplianceReportInfoRegisterManagerService",
                "deleteA010", new TypeReference<DataList<IfaComplianceReportInfoRegisterManagerA010DtoResponse>>() {
                }, appReq);
        
        DataList<IfaComplianceReportInfoRegisterManagerA010ApiResponse> apiRes = new DataList<IfaComplianceReportInfoRegisterManagerA010ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        return null;
    }
}

