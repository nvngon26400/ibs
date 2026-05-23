package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.controller;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA005ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA007RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA007ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA008RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA008ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.util.IfaComplianceReportViewStatusLookupManagerCsvOut;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportViewStatusLookupManagerA001ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportViewStatusLookupManagerA001ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportViewStatusLookupManagerA002ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportViewStatusLookupManagerA002ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportViewStatusLookupManagerA003ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportViewStatusLookupManagerA003aApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportViewStatusLookupManagerA003bApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportViewStatusLookupManagerA005ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportViewStatusLookupManagerA005ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportViewStatusLookupManagerA007ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportViewStatusLookupManagerA007ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportViewStatusLookupManagerA008ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportViewStatusLookupManagerA008ApiResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0505_02-01
 * 画面名：コンプライアンス通信閲覧状況照会（管理者用）
 * @author <author-name>
 *
 * 2024/01/15 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0505_02-01", screenNumber = "", ignoreCheck =true)
public class IfaComplianceReportViewStatusLookupManagerController extends BaseController{
    
    private static String CSV_FILE_NAME = "コンプライアンス通信閲覧状況照会";

    private JsonConverter jc = JsonConverter.getInstance();
    
    @Override
    protected String getCsvHeader(String pattern) {
        return IfaComplianceReportViewStatusLookupManagerCsvOut.getHeaders(pattern);
    }
    
    /**
     * 
     * アクセス：/companyEmployeeMenu/complianceReport/ifaComplianceReportViewStatusLookupManagerInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaComplianceReportViewStatusLookupManagerA001ApiRequest
     * Apiレスポンス：IfaComplianceReportViewStatusLookupManagerA001ApiResponse
     * Dtoリクエスト：IfaComplianceReportViewStatusLookupManagerA001DtoRequest
     * Dtoレスポンス：IfaComplianceReportViewStatusLookupManagerA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value="/companyEmployeeMenu/complianceReport/ifaComplianceReportViewStatusLookupManagerInitializeA001",method={RequestMethod.POST})
    public String initializeA001(@RequestBody IfaComplianceReportViewStatusLookupManagerA001ApiRequest apiReq)throws Exception
    {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaComplianceReportViewStatusLookupManagerA001RequestDto appReq = new IfaComplianceReportViewStatusLookupManagerA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaComplianceReportViewStatusLookupManagerA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaComplianceReportViewStatusLookupManagerService",
                "initializeA001", new TypeReference<DataList<IfaComplianceReportViewStatusLookupManagerA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaComplianceReportViewStatusLookupManagerA001ApiResponse> apiRes = new DataList<IfaComplianceReportViewStatusLookupManagerA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/companyEmployeeMenu/complianceReport/ifaComplianceReportViewStatusLookupManagerListDisplayA002
     * アクションID：A002
     * アクション名：一覧表示
     * APIリクエスト：IfaComplianceReportViewStatusLookupManagerA002ApiRequest
     * Apiレスポンス：IfaComplianceReportViewStatusLookupManagerA002ApiResponse
     * Dtoリクエスト：IfaComplianceReportViewStatusLookupManagerA002DtoRequest
     * Dtoレスポンス：IfaComplianceReportViewStatusLookupManagerA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value="/companyEmployeeMenu/complianceReport/ifaComplianceReportViewStatusLookupManagerListDisplayA002",method={RequestMethod.POST})
    public String listDisplayA002(@RequestBody IfaComplianceReportViewStatusLookupManagerA002ApiRequest apiReq)throws Exception
    {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaComplianceReportViewStatusLookupManagerA002RequestDto appReq = new IfaComplianceReportViewStatusLookupManagerA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaComplianceReportViewStatusLookupManagerA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaComplianceReportViewStatusLookupManagerService",
                "listDisplayA002", new TypeReference<DataList<IfaComplianceReportViewStatusLookupManagerA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaComplianceReportViewStatusLookupManagerA002ApiResponse> apiRes = new DataList<IfaComplianceReportViewStatusLookupManagerA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/companyEmployeeMenu/complianceReport/ifaComplianceReportViewStatusLookupManagerCsvOutputA003a
     * アクションID：A003
     * アクション名：CSV出力
     * APIリクエスト：IfaComplianceReportViewStatusLookupManagerA003ApiRequest
     * Apiレスポンス：IfaComplianceReportViewStatusLookupManagerA003ApiResponse
     * Dtoリクエスト：IfaComplianceReportViewStatusLookupManagerA003DtoRequest
     * Dtoレスポンス：IfaComplianceReportViewStatusLookupManagerA003DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value="/companyEmployeeMenu/complianceReport/ifaComplianceReportViewStatusLookupManagerCsvOutputA003a",method={RequestMethod.POST})
    public String csvOutputA003a(@RequestBody IfaComplianceReportViewStatusLookupManagerA003aApiRequest apiReq)throws Exception
    {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaComplianceReportViewStatusLookupManagerA003RequestDto appReq = new IfaComplianceReportViewStatusLookupManagerA003RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaComplianceReportViewStatusLookupManagerA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaComplianceReportViewStatusLookupManagerService",
                "csvOutputA003", new TypeReference<DataList<IfaComplianceReportViewStatusLookupManagerA003ResponseDto>>() {
                }, appReq, IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class));
        
        DataList<IfaComplianceReportViewStatusLookupManagerA003ApiResponse> apiRes = new DataList<IfaComplianceReportViewStatusLookupManagerA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/companyEmployeeMenu/complianceReport/ifaComplianceReportViewStatusLookupManagerCsvOutputA003b
     * アクションID：A003
     * アクション名：CSV出力
     * APIリクエスト：IfaComplianceReportViewStatusLookupManagerA003ApiRequest
     * Apiレスポンス：IfaComplianceReportViewStatusLookupManagerA003ApiResponse
     * Dtoリクエスト：IfaComplianceReportViewStatusLookupManagerA003DtoRequest
     * Dtoレスポンス：IfaComplianceReportViewStatusLookupManagerA003DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value="/companyEmployeeMenu/complianceReport/ifaComplianceReportViewStatusLookupManagerCsvOutputA003b",method={RequestMethod.POST})
    public void csvOutputA003b(@RequestBody IfaComplianceReportViewStatusLookupManagerA003bApiRequest apiReq,HttpServletResponse response)throws Exception
    {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 共通のダウンロード処理を実施
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(CSV_FILE_NAME),
                IfaCommonUtil.getUserAccount(), IfaCommonUtil.getUserAccount().getPrivId());

        
        
    }
    
    /**
     * 
     * アクセス：/companyEmployeeMenu/complianceReport/ifaComplianceReportViewStatusLookupManagerNoNeedToViewSettingA005
     * アクションID：A005
     * アクション名：閲覧不要設定
     * APIリクエスト：IfaComplianceReportViewStatusLookupManagerA005ApiRequest
     * Apiレスポンス：IfaComplianceReportViewStatusLookupManagerA005ApiResponse
     * Dtoリクエスト：IfaComplianceReportViewStatusLookupManagerA005DtoRequest
     * Dtoレスポンス：IfaComplianceReportViewStatusLookupManagerA005DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value="/companyEmployeeMenu/complianceReport/ifaComplianceReportViewStatusLookupManagerNoNeedToViewSettingA005",method={RequestMethod.POST})
    public String noNeedToViewSettingA005(@RequestBody IfaComplianceReportViewStatusLookupManagerA005ApiRequest apiReq)throws Exception
    {

        IfaComplianceReportViewStatusLookupManagerA005RequestDto appReq = new IfaComplianceReportViewStatusLookupManagerA005RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaComplianceReportViewStatusLookupManagerA005ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaComplianceReportViewStatusLookupManagerService",
                "noNeedToViewSettingA005", new TypeReference<DataList<IfaComplianceReportViewStatusLookupManagerA005ResponseDto>>() {
                }, appReq);
        
        DataList<IfaComplianceReportViewStatusLookupManagerA005ApiResponse> apiRes = new DataList<IfaComplianceReportViewStatusLookupManagerA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/companyEmployeeMenu/complianceReport/ifaComplianceReportViewStatusLookupManagerSearchDisplayPortalA007
     * アクションID：A007
     * アクション名：検索表示（ポータル）
     * APIリクエスト：IfaComplianceReportViewStatusLookupManagerA007ApiRequest
     * Apiレスポンス：IfaComplianceReportViewStatusLookupManagerA007ApiResponse
     * Dtoリクエスト：IfaComplianceReportViewStatusLookupManagerA007DtoRequest
     * Dtoレスポンス：IfaComplianceReportViewStatusLookupManagerA007DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value="/companyEmployeeMenu/complianceReport/ifaComplianceReportViewStatusLookupManagerSearchDisplayPortalA007",method={RequestMethod.POST})
    public String searchDisplayPortalA007(@RequestBody IfaComplianceReportViewStatusLookupManagerA007ApiRequest apiReq)throws Exception
    {

        IfaComplianceReportViewStatusLookupManagerA007RequestDto appReq = new IfaComplianceReportViewStatusLookupManagerA007RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaComplianceReportViewStatusLookupManagerA007ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaComplianceReportViewStatusLookupManagerService",
                "searchDisplayPortalA007", new TypeReference<DataList<IfaComplianceReportViewStatusLookupManagerA007ResponseDto>>() {
                }, appReq);
        
        DataList<IfaComplianceReportViewStatusLookupManagerA007ApiResponse> apiRes = new DataList<IfaComplianceReportViewStatusLookupManagerA007ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/companyEmployeeMenu/complianceReport/ifaComplianceReportViewStatusLookupManagerViewReportA008
     * アクションID：A008
     * アクション名：閲覧報告
     * APIリクエスト：IfaComplianceReportViewStatusLookupManagerA008ApiRequest
     * Apiレスポンス：IfaComplianceReportViewStatusLookupManagerA008ApiResponse
     * Dtoリクエスト：IfaComplianceReportViewStatusLookupManagerA008DtoRequest
     * Dtoレスポンス：IfaComplianceReportViewStatusLookupManagerA008DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value="/companyEmployeeMenu/complianceReport/ifaComplianceReportViewStatusLookupManagerViewReportA008",method={RequestMethod.POST})
    public String viewReportA008(@RequestBody IfaComplianceReportViewStatusLookupManagerA008ApiRequest apiReq)throws Exception
    {

        IfaComplianceReportViewStatusLookupManagerA008RequestDto appReq = new IfaComplianceReportViewStatusLookupManagerA008RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaComplianceReportViewStatusLookupManagerA008ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaComplianceReportViewStatusLookupManagerService",
                "viewReportA008", new TypeReference<DataList<IfaComplianceReportViewStatusLookupManagerA008ResponseDto>>() {
                }, appReq);
        
        DataList<IfaComplianceReportViewStatusLookupManagerA008ApiResponse> apiRes = new DataList<IfaComplianceReportViewStatusLookupManagerA008ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

