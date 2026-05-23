package com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.controller;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA003RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA003ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA004RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.util.IfaComplianceReportViewStatusLookupInternalAdminCsvOut;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaComplianceReportViewStatusLookupInternalAdminA001ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaComplianceReportViewStatusLookupInternalAdminA001ApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaComplianceReportViewStatusLookupInternalAdminA002ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaComplianceReportViewStatusLookupInternalAdminA002ApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaComplianceReportViewStatusLookupInternalAdminA003aApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaComplianceReportViewStatusLookupInternalAdminA003aApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaComplianceReportViewStatusLookupInternalAdminA003bApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaComplianceReportViewStatusLookupInternalAdminA004ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaComplianceReportViewStatusLookupInternalAdminA004ApiResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0401_01-01
 * 画面名：コンプライアンス通信閲覧状況照会(内部管理責任者用)
 * @author <author-name>
 *
 * 2023/12/05 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN04", id = "SUB0401_01-01", screenNumber = "", ignoreCheck = true)
public class IfaComplianceReportViewStatusLookupInternalAdminController extends BaseController {
    
    private static final String CSV_FILE_NAME = "コンプライアンス通信閲覧状況照会(内部管理責任者用)";
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    @Override
    protected String getCsvHeader() {
        return IfaComplianceReportViewStatusLookupInternalAdminCsvOut.getHeaders();
    }
    
    /**
     * 
     * アクセス：/internalAdminMenu/monthlyImplementationItem/ifaComplianceReportViewStatusLookupInternalAdminInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaComplianceReportViewStatusLookupInternalAdminA001ApiRequest
     * Apiレスポンス：IfaComplianceReportViewStatusLookupInternalAdminA001ApiResponse
     * Dtoリクエスト：IfaComplianceReportViewStatusLookupInternalAdminA001DtoRequest
     * Dtoレスポンス：IfaComplianceReportViewStatusLookupInternalAdminA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value = "/internalAdminMenu/monthlyImplementationItem/ifaComplianceReportViewStatusLookupInternalAdminInitializeA001", method = {
            RequestMethod.POST })
    public String initializeA001(@RequestBody IfaComplianceReportViewStatusLookupInternalAdminA001ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaComplianceReportViewStatusLookupInternalAdminA001RequestDto appReq = new IfaComplianceReportViewStatusLookupInternalAdminA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaComplianceReportViewStatusLookupInternalAdminService", "initializeA001",
                new TypeReference<DataList<IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaComplianceReportViewStatusLookupInternalAdminA001ApiResponse> apiRes = new DataList<IfaComplianceReportViewStatusLookupInternalAdminA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/internalAdminMenu/monthlyImplementationItem/ifaComplianceReportViewStatusLookupInternalAdminListDisplayA002
     * アクションID：A002
     * アクション名：一覧表示
     * APIリクエスト：IfaComplianceReportViewStatusLookupInternalAdminA002ApiRequest
     * Apiレスポンス：IfaComplianceReportViewStatusLookupInternalAdminA002ApiResponse
     * Dtoリクエスト：IfaComplianceReportViewStatusLookupInternalAdminA002DtoRequest
     * Dtoレスポンス：IfaComplianceReportViewStatusLookupInternalAdminA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value = "/internalAdminMenu/monthlyImplementationItem/ifaComplianceReportViewStatusLookupInternalAdminListDisplayA002", method = {
            RequestMethod.POST })
    public String listDisplayA002(@RequestBody IfaComplianceReportViewStatusLookupInternalAdminA002ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaComplianceReportViewStatusLookupInternalAdminA002RequestDto appReq = new IfaComplianceReportViewStatusLookupInternalAdminA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaComplianceReportViewStatusLookupInternalAdminService", "listDisplayA002",
                new TypeReference<DataList<IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaComplianceReportViewStatusLookupInternalAdminA002ApiResponse> apiRes = new DataList<IfaComplianceReportViewStatusLookupInternalAdminA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/internalAdminMenu/monthlyImplementationItem/ifaComplianceReportViewStatusLookupInternalAdminCsvOutputA003a
     * アクションID：A003a
     * アクション名：CSV出力
     * APIリクエスト：IfaComplianceReportViewStatusLookupInternalAdminA003aApiRequest
     * Apiレスポンス：IfaComplianceReportViewStatusLookupInternalAdminA003aApiResponse
     * Dtoリクエスト：IfaComplianceReportViewStatusLookupInternalAdminA003aDtoRequest
     * Dtoレスポンス：IfaComplianceReportViewStatusLookupInternalAdminA003aDtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value = "/internalAdminMenu/monthlyImplementationItem/ifaComplianceReportViewStatusLookupInternalAdminCsvOutputA003a", method = {
            RequestMethod.POST })
    public String csvOutputA003a(@RequestBody IfaComplianceReportViewStatusLookupInternalAdminA003aApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaComplianceReportViewStatusLookupInternalAdminA003RequestDto appReq = new IfaComplianceReportViewStatusLookupInternalAdminA003RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaComplianceReportViewStatusLookupInternalAdminA003ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaComplianceReportViewStatusLookupInternalAdminService", "csvOutputA003",
                new TypeReference<DataList<IfaComplianceReportViewStatusLookupInternalAdminA003ResponseDto>>() {
                }, appReq, IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class));
        
        DataList<IfaComplianceReportViewStatusLookupInternalAdminA003aApiResponse> apiRes = new DataList<IfaComplianceReportViewStatusLookupInternalAdminA003aApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/internalAdminMenu/monthlyImplementationItem/ifaComplianceReportViewStatusLookupInternalAdminCsvOutputA003b
     * アクションID：A003b
     * アクション名：CSV出力
     * APIリクエスト：IfaComplianceReportViewStatusLookupInternalAdminA003bApiRequest
     * Apiレスポンス：IfaComplianceReportViewStatusLookupInternalAdminA003bApiResponse
     * Dtoリクエスト：IfaComplianceReportViewStatusLookupInternalAdminA003bDtoRequest
     * Dtoレスポンス：IfaComplianceReportViewStatusLookupInternalAdminA003bDtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value = "/internalAdminMenu/monthlyImplementationItem/ifaComplianceReportViewStatusLookupInternalAdminCsvOutputA003b", method = {
            RequestMethod.POST })
    public void csvOutputA003b(@RequestBody IfaComplianceReportViewStatusLookupInternalAdminA003bApiRequest apiReq,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 共通のダウンロード処理を実施
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(CSV_FILE_NAME),
                IfaCommonUtil.getUserAccount());
    }
    
    /**
     * 
     * アクセス：/internalAdminMenu/monthlyImplementationItem/ifaComplianceReportViewStatusLookupInternalAdminSearchDisplayPortalA004
     * アクションID：A004
     * アクション名：検索表示（ポータル）
     * APIリクエスト：IfaComplianceReportViewStatusLookupInternalAdminA004ApiRequest
     * Apiレスポンス：IfaComplianceReportViewStatusLookupInternalAdminA004ApiResponse
     * Dtoリクエスト：IfaComplianceReportViewStatusLookupInternalAdminA004DtoRequest
     * Dtoレスポンス：IfaComplianceReportViewStatusLookupInternalAdminA004DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value = "/internalAdminMenu/monthlyImplementationItem/ifaComplianceReportViewStatusLookupInternalAdminSearchDisplayPortalA004", method = {
            RequestMethod.POST })
    public String searchDisplayPortalA004(
            @RequestBody IfaComplianceReportViewStatusLookupInternalAdminA004ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaComplianceReportViewStatusLookupInternalAdminA004RequestDto appReq = new IfaComplianceReportViewStatusLookupInternalAdminA004RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaComplianceReportViewStatusLookupInternalAdminService", "searchDisplayPortalA004",
                new TypeReference<DataList<IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDto>>() {
                }, appReq);
        
        DataList<IfaComplianceReportViewStatusLookupInternalAdminA004ApiResponse> apiRes = new DataList<IfaComplianceReportViewStatusLookupInternalAdminA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
