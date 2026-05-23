package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA003RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA003ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA004RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDto;
import com.sbisec.helios.ap.service.Service;
/**
 * 画面ID：SUB0401_01-01
 * 画面名：コンプライアンス通信閲覧状況照会(内部管理責任者用)
 * @author <author-name>
 *
 * 2023/12/04 新規作成
 */
public interface IfaComplianceReportViewStatusLookupInternalAdminService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaComplianceReportViewStatusLookupInternalAdminA001DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupInternalAdminA001DtoResponse
     * model リクエスト：IfaComplianceReportViewStatusLookupInternalAdminA001RequestModel
     * model レスポンス：IfaComplianceReportViewStatusLookupInternalAdminA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDto> initializeA001(IfaComplianceReportViewStatusLookupInternalAdminA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A002
     * アクション名：一覧表示
     * Dto リクエスト：IfaComplianceReportViewStatusLookupInternalAdminA002DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupInternalAdminA002DtoResponse
     * model リクエスト：IfaComplianceReportViewStatusLookupInternalAdminA002RequestModel
     * model レスポンス：IfaComplianceReportViewStatusLookupInternalAdminA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDto> listDisplayA002(IfaComplianceReportViewStatusLookupInternalAdminA002RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A003
     * アクション名：CSV出力
     * Dto リクエスト：IfaComplianceReportViewStatusLookupInternalAdminA003DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupInternalAdminA003DtoResponse
     * model リクエスト：IfaComplianceReportViewStatusLookupInternalAdminA003RequestModel
     * model レスポンス：IfaComplianceReportViewStatusLookupInternalAdminA003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupInternalAdminA003ResponseDto> csvOutputA003(IfaComplianceReportViewStatusLookupInternalAdminA003RequestDto dtoReq, String sessionId)
            throws Exception;

    /**
     * アクションID：A004
     * アクション名：検索表示（ポータル）
     * Dto リクエスト：IfaComplianceReportViewStatusLookupInternalAdminA004DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupInternalAdminA004DtoResponse
     * model リクエスト：IfaComplianceReportViewStatusLookupInternalAdminA004RequestModel
     * model レスポンス：IfaComplianceReportViewStatusLookupInternalAdminA004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDto> searchDisplayPortalA004(IfaComplianceReportViewStatusLookupInternalAdminA004RequestDto dtoReq)
            throws Exception;

}
