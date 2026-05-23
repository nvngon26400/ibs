package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.service;

import com.sbibits.earth.model.DataList;
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

import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0505_02-01
 * 画面名：コンプライアンス通信閲覧状況照会（管理者用）
 * @author <author-name>
 *
 * 2023/12/27 新規作成
 */
public interface IfaComplianceReportViewStatusLookupManagerService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaComplianceReportViewStatusLookupManagerA001DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupManagerA001DtoResponse
     * model リクエスト：IfaComplianceReportViewStatusLookupManagerA001RequestModel
     * model レスポンス：IfaComplianceReportViewStatusLookupManagerA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupManagerA001ResponseDto> initializeA001(IfaComplianceReportViewStatusLookupManagerA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：一覧表示
     * Dto リクエスト：IfaComplianceReportViewStatusLookupManagerA002DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupManagerA002DtoResponse
     * model リクエスト：IfaComplianceReportViewStatusLookupManagerA002RequestModel
     * model レスポンス：IfaComplianceReportViewStatusLookupManagerA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupManagerA002ResponseDto> listDisplayA002(IfaComplianceReportViewStatusLookupManagerA002RequestDto dtoReq)
            throws Exception;
    

    /**
     * アクションID：A003
     * アクション名：CSV出力
     * Dto リクエスト：IfaComplianceReportViewStatusLookupManagerA003DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupManagerA003DtoResponse
     * model リクエスト：IfaComplianceReportViewStatusLookupManagerA003RequestModel
     * model レスポンス：IfaComplianceReportViewStatusLookupManagerA003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupManagerA003ResponseDto> csvOutputA003(IfaComplianceReportViewStatusLookupManagerA003RequestDto dtoReq, String sessionId)
            throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：閲覧不要設定
     * Dto リクエスト：IfaComplianceReportViewStatusLookupManagerA005DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupManagerA005DtoResponse
     * model リクエスト：IfaComplianceReportViewStatusLookupManagerA005RequestModel
     * model レスポンス：IfaComplianceReportViewStatusLookupManagerA005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupManagerA005ResponseDto> noNeedToViewSettingA005(IfaComplianceReportViewStatusLookupManagerA005RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A007
     * アクション名：検索表示（ポータル）
     * Dto リクエスト：IfaComplianceReportViewStatusLookupManagerA007DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupManagerA007DtoResponse
     * model リクエスト：IfaComplianceReportViewStatusLookupManagerA007RequestModel
     * model レスポンス：IfaComplianceReportViewStatusLookupManagerA007ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupManagerA007ResponseDto> searchDisplayPortalA007(IfaComplianceReportViewStatusLookupManagerA007RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A008
     * アクション名：閲覧報告
     * Dto リクエスト：IfaComplianceReportViewStatusLookupManagerA008DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupManagerA008DtoResponse
     * model リクエスト：IfaComplianceReportViewStatusLookupManagerA008RequestModel
     * model レスポンス：IfaComplianceReportViewStatusLookupManagerA008ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupManagerA008ResponseDto> viewReportA008(IfaComplianceReportViewStatusLookupManagerA008RequestDto dtoReq)
            throws Exception;

}
