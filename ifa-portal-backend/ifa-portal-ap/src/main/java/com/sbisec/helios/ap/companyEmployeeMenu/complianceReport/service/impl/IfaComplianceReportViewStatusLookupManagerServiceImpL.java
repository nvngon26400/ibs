package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.IfaComplianceReportViewStatusLookupManagerDao;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql005ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql006RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql007RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA001ResponseDtoComplianceReport;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA002ResponseDtoComplianceReportList;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA005ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA005ResponseDtoComplianceReportList;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA007RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA007ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReport;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReportList;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA008RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA008ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.service.IfaComplianceReportViewStatusLookupManagerService;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.util.IfaComplianceReportViewStatusLookupManagerCsvOut;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0505_02-01
 * 画面名：コンプライアンス通信閲覧状況照会（管理者用）
 * @author <author-name>
 *
 * 2023/12/27 新規作成
 */
@Component(value = "cmpIfaComplianceReportViewStatusLookupManagerService")
public class IfaComplianceReportViewStatusLookupManagerServiceImpL
        implements IfaComplianceReportViewStatusLookupManagerService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaComplianceReportViewStatusLookupManagerServiceImpL.class);
    
    @Autowired
    private IfaComplianceReportViewStatusLookupManagerDao dao;
    
    /**
     * FCT038CSVダウンロードMAX件数取得
     */
    @Autowired
    private Fct038 fct038;
    
    /**
     * ComplianceService(CSV証跡登録用)クラス
     */
    @Autowired
    private ComplianceService complianceService;
    
    /** 全て入力なし：エラー */
    public static final String ERRORS_REQUIRED = "errors.required";
    
    public static final String ERRORS_REQUIRED_MSG = "\"仲介業者名\"、\"営業員名\"、\"タイトル\"";
    
    /** 検索結果が0件です。条件を設定して再度検索して下さい。 */
    public static final String ERRORS_DATALIST_NOTFOUND = "errors.dataList.notfound";
    
    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 */
    public static final String WARNINGS_DATALIST_OVERMAXROWNUM = "warnings.dataList.overMaxRownum";
    
    public static final String LIMIT_VALUE = "5000";
    
    public static final String ZERO_VALUE = "0";
    
    /** FCT038 画面ID*/
    private static final String SCREEN_ID = "SUB0505_02-01";
    
    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のcsv出力を行います。*/
    private static final String WARNINGS_DATALIST_CSV_OVERMAXROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    /**閲覧不要設定。*/
    private static final String VIEW_EXCLUDE_SETTING_1 = "1";
    
    private static final String VIEW_EXCLUDE_SETTING_0 = "0";

    /** 閲覧報告フラグ："1"（未報告） */
    private static final String VIEW_REPORT_FLAG_1 = "1";

    /** 閲覧報告フラグ："0"（報告済） */
    private static final String VIEW_REPORT_FLAG_0 = "0";

    /** INFO.コンプライアンス通信の閲覧報告分を更新しました。 */
    private static final String INFO_COR_COMPLIANCE_CONFIRMATION_COR_CONFIRMATION_REPORT = "info.COR_COMPLIANCE_CONFIRMATION.COR_CONFIRMATION_REPORT";

    /** ERRORS.コンプライアンス通信の閲覧報告分を更新できませんでした。 */
    private static final String ERRORS_COR_COMPLIANCE_CONFIRMATION_COR_CONFIRMATION_REPORT = "errors.COR_COMPLIANCE_CONFIRMATION.COR_CONFIRMATION_REPORT";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaComplianceReportViewStatusLookupManagerA001DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupManagerA001DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupManagerA001ResponseDto> initializeA001(
            IfaComplianceReportViewStatusLookupManagerA001RequestDto dtoReq) throws Exception {
        
        DataList<IfaComplianceReportViewStatusLookupManagerA001ResponseDto> dtoRes = new DataList<IfaComplianceReportViewStatusLookupManagerA001ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaComplianceReportViewStatusLookupManagerServiceImplL.initializeA001");
        List<IfaComplianceReportViewStatusLookupManagerA001ResponseDto> complianceReportViewStatusLookupManagerList = new ArrayList<IfaComplianceReportViewStatusLookupManagerA001ResponseDto>();
        IfaComplianceReportViewStatusLookupManagerA001ResponseDto a001ResponseDto = new IfaComplianceReportViewStatusLookupManagerA001ResponseDto();
        List<IfaComplianceReportViewStatusLookupManagerA001ResponseDtoComplianceReport> complianceReport = new ArrayList<IfaComplianceReportViewStatusLookupManagerA001ResponseDtoComplianceReport>();
        //① コンプライアンス通信のタイトルを取得する。
        IfaComplianceReportViewStatusLookupManagerSql001RequestModel sql001Request = new IfaComplianceReportViewStatusLookupManagerSql001RequestModel();
        
        DataList<IfaComplianceReportViewStatusLookupManagerSql001ResponseModel> sql001Response = dao
                .selectIfaComplianceReportViewStatusLookupManagerSql001(sql001Request);
        
        for (IfaComplianceReportViewStatusLookupManagerSql001ResponseModel sql001ResponseModel : sql001Response
                .getDataList()) {
            
            IfaComplianceReportViewStatusLookupManagerA001ResponseDtoComplianceReport a001ResponseDtoComplianceReport = new IfaComplianceReportViewStatusLookupManagerA001ResponseDtoComplianceReport();
            BeanUtils.copyProperties(a001ResponseDtoComplianceReport, sql001ResponseModel);
            complianceReport.add(a001ResponseDtoComplianceReport);
        }
        a001ResponseDto.setComplianceReportList(complianceReport);
        complianceReportViewStatusLookupManagerList.add(a001ResponseDto);
        dtoRes = IfaCommonUtil.createDataList(complianceReportViewStatusLookupManagerList, ErrorLevel.SUCCESS, "0", "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：一覧表示
     * Dto リクエスト：IfaComplianceReportViewStatusLookupManagerA002DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupManagerA002DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupManagerA002ResponseDto> listDisplayA002(
            IfaComplianceReportViewStatusLookupManagerA002RequestDto dtoReq) throws Exception {
        
        DataList<IfaComplianceReportViewStatusLookupManagerA002ResponseDto> dtoRes = new DataList<IfaComplianceReportViewStatusLookupManagerA002ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaComplianceReportViewStatusLookupManagerServiceImplL.listDisplayA002");
        
        List<IfaComplianceReportViewStatusLookupManagerA002ResponseDto> complianceReportViewStatusLookupManagerList = new ArrayList<IfaComplianceReportViewStatusLookupManagerA002ResponseDto>();
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        //① 仲介業者名,営業員名,タイトルの入力チェックを行う。
        //全て入力なし：エラーを返す。
        if (StringUtil.isNullOrEmpty(dtoReq.getBrokerName()) && StringUtil.isNullOrEmpty(dtoReq.getBrokerChargeName())
                && StringUtil.isNullOrEmpty(dtoReq.getTitle())) {
            dtoRes = IfaCommonUtil.createDataList(complianceReportViewStatusLookupManagerList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_REQUIRED, new String[] { ERRORS_REQUIRED_MSG }));
            return dtoRes;
        }
        
        //③ コンプライアンス通信情報を取得する。
        IfaComplianceReportViewStatusLookupManagerSql005RequestModel sql005Request = new IfaComplianceReportViewStatusLookupManagerSql005RequestModel();
        BeanUtils.copyProperties(sql005Request, dtoReq);
        sql005Request.setPrivId(userAccount.getMedUsers().getPrivId());
        sql005Request.setBranchId(userAccount.getMedUsers().getBranchId());
        DataList<IfaComplianceReportViewStatusLookupManagerSql005ResponseModel> sql005Response = dao
                .selectIfaComplianceReportViewStatusLookupManagerSql005(sql005Request);
        
        // 検索結果が0件の場合：取得結果０件エラーを返す。
        if (ZERO_VALUE.equals(sql005Response.get(0).getCount())) {
            dtoRes = IfaCommonUtil.createDataList(complianceReportViewStatusLookupManagerList, ErrorLevel.INFO,
                    Integer.toString(ErrorLevel.INFO.getId()), IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND));
            return dtoRes;
        } else if (sql005Response.get(0).getCount().length() > LIMIT_VALUE.length() ||
                Integer.parseInt(LIMIT_VALUE) < Integer.parseInt(sql005Response.get(0).getCount())){
            // 検索結果が5,000件超過の場合：メッセージを表示し、コンプライアンス通信一覧リストに前から5,000件をセットする。
            IfaComplianceReportViewStatusLookupManagerSql002RequestModel sql002Request = new IfaComplianceReportViewStatusLookupManagerSql002RequestModel();
            
            BeanUtils.copyProperties(sql002Request, dtoReq);
            
            sql002Request.setRownum(LIMIT_VALUE);
            
            DataList<IfaComplianceReportViewStatusLookupManagerSql002ResponseModel> sql002Response = dao
                    .selectIfaComplianceReportViewStatusLookupManagerSql002(sql002Request);
            
            List<IfaComplianceReportViewStatusLookupManagerA002ResponseDtoComplianceReportList> a002ComplianceReportList = 
                    new ArrayList<IfaComplianceReportViewStatusLookupManagerA002ResponseDtoComplianceReportList>();
            for (IfaComplianceReportViewStatusLookupManagerSql002ResponseModel sql002ResponseModel : sql002Response
                    .getDataList()) {
                IfaComplianceReportViewStatusLookupManagerA002ResponseDtoComplianceReportList a002ResponseDtoComplianceReportList = 
                        new IfaComplianceReportViewStatusLookupManagerA002ResponseDtoComplianceReportList();
                BeanUtils.copyProperties(a002ResponseDtoComplianceReportList, sql002ResponseModel);
                // 閲覧報告フラグを算出する
                if (StringUtil.isNullOrEmpty(sql002ResponseModel.getViewReportUser())) { // 閲覧報告者がNULLの場合：閲覧報告フラグに"1"（未報告）を設定する
                    a002ResponseDtoComplianceReportList.setViewReportFlag(VIEW_REPORT_FLAG_1);
                } else { // 閲覧報告者がNULLではない場合：閲覧報告フラグにを"0"（報告済）設定する
                    a002ResponseDtoComplianceReportList.setViewReportFlag(VIEW_REPORT_FLAG_0);
                }
                a002ComplianceReportList.add(a002ResponseDtoComplianceReportList);
            }
            IfaComplianceReportViewStatusLookupManagerA002ResponseDto a002ResponseDto = new IfaComplianceReportViewStatusLookupManagerA002ResponseDto();
            a002ResponseDto.setComplianceReportListList(a002ComplianceReportList);
            complianceReportViewStatusLookupManagerList.add(a002ResponseDto);
            String wholeCount = "0";
            if (sql002Response.size() != 0) {
                wholeCount = sql002Response.get(0).getWholeCount();
            }
            dtoRes = IfaCommonUtil.createDataList(complianceReportViewStatusLookupManagerList, ErrorLevel.WARNING,
                    Integer.toString(ErrorLevel.WARNING.getId()),
                    IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVERMAXROWNUM,
                            new String[] { LIMIT_VALUE, wholeCount }));
            return dtoRes;
        } else {
            //上記以外：コンプライアンス通信一覧リストに全件セット
            IfaComplianceReportViewStatusLookupManagerSql002RequestModel sql002Request = new IfaComplianceReportViewStatusLookupManagerSql002RequestModel();
            sql002Request.setPrivId(userAccount.getMedUsers().getPrivId());
            sql002Request.setBranchId(userAccount.getMedUsers().getBranchId());
            BeanUtils.copyProperties(sql002Request, dtoReq);
            DataList<IfaComplianceReportViewStatusLookupManagerSql002ResponseModel> sql002Response = dao
                    .selectIfaComplianceReportViewStatusLookupManagerSql002(sql002Request);
            
            List<IfaComplianceReportViewStatusLookupManagerA002ResponseDtoComplianceReportList> a002ComplianceReportList = 
                    new ArrayList<IfaComplianceReportViewStatusLookupManagerA002ResponseDtoComplianceReportList>();
            
            for (IfaComplianceReportViewStatusLookupManagerSql002ResponseModel sql002ResponseModel : sql002Response
                    .getDataList()) {
                IfaComplianceReportViewStatusLookupManagerA002ResponseDtoComplianceReportList a002ResponseDtoComplianceReportList = 
                        new IfaComplianceReportViewStatusLookupManagerA002ResponseDtoComplianceReportList();
                BeanUtils.copyProperties(a002ResponseDtoComplianceReportList, sql002ResponseModel);
                // 閲覧報告フラグを算出する
                if (StringUtil.isNullOrEmpty(sql002ResponseModel.getViewReportUser())) { // 閲覧報告者がNULLの場合：閲覧報告フラグに"1"（未報告）を設定する
                    a002ResponseDtoComplianceReportList.setViewReportFlag(VIEW_REPORT_FLAG_1);
                } else { // 閲覧報告者がNULLではない場合：閲覧報告フラグにを"0"（報告済）設定する
                    a002ResponseDtoComplianceReportList.setViewReportFlag(VIEW_REPORT_FLAG_0);
                }
                a002ComplianceReportList.add(a002ResponseDtoComplianceReportList);
            }
            IfaComplianceReportViewStatusLookupManagerA002ResponseDto a002ResponseDto = new IfaComplianceReportViewStatusLookupManagerA002ResponseDto();
            a002ResponseDto.setComplianceReportListList(a002ComplianceReportList);
            complianceReportViewStatusLookupManagerList.add(a002ResponseDto);
            dtoRes = IfaCommonUtil.createDataList(complianceReportViewStatusLookupManagerList, ErrorLevel.SUCCESS, "0",
                    "");
            return dtoRes;
        }
        
    }
    
    /**
     * アクションID：A003
     * アクション名：CSV出力
     * Dto リクエスト：IfaComplianceReportViewStatusLookupManagerA003DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupManagerA003DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    
    public DataList<IfaComplianceReportViewStatusLookupManagerA003ResponseDto> csvOutputA003(
            IfaComplianceReportViewStatusLookupManagerA003RequestDto dtoReq, String sessionId) throws Exception {
        
        DataList<IfaComplianceReportViewStatusLookupManagerA003ResponseDto> dtoRes = new DataList<IfaComplianceReportViewStatusLookupManagerA003ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaComplianceReportViewStatusLookupManagerServiceImplL.csvOutputA003");
        
        List<IfaComplianceReportViewStatusLookupManagerA003ResponseDto> complianceReportViewStatusLookupManagerList = new ArrayList<IfaComplianceReportViewStatusLookupManagerA003ResponseDto>();
        
        
        //① 仲介業者名,営業員名,タイトルの入力チェックを行う。
        //全て入力なし：エラーを返す。
        if (StringUtil.isNullOrEmpty(dtoReq.getBrokerName()) && StringUtil.isNullOrEmpty(dtoReq.getBrokerChargeName())
                && StringUtil.isNullOrEmpty(dtoReq.getTitle())) {
            dtoRes = IfaCommonUtil.createDataList(complianceReportViewStatusLookupManagerList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_REQUIRED, new String[] { ERRORS_REQUIRED_MSG }));
            return dtoRes;
        }
        
        //② CSVダウンロードMAX件数を取得し、最大取得件数にセットする。
        InputFct038Dto inputFct038Dto = new InputFct038Dto();
        inputFct038Dto.setScreenId(SCREEN_ID);
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        inputFct038Dto.setUserAuthority(userAccount.getPrivId());
        OutputFct038Dto outputFct038Dto = fct038.getData(inputFct038Dto);
        
        //③ コンプライアンス通信情報を取得する。
        IfaComplianceReportViewStatusLookupManagerSql005RequestModel sql005Request = new IfaComplianceReportViewStatusLookupManagerSql005RequestModel();
        BeanUtils.copyProperties(sql005Request, dtoReq);
        sql005Request.setPrivId(userAccount.getMedUsers().getPrivId());
        sql005Request.setBranchId(userAccount.getMedUsers().getBranchId());
        DataList<IfaComplianceReportViewStatusLookupManagerSql005ResponseModel> sql005Response = dao
                .selectIfaComplianceReportViewStatusLookupManagerSql005(sql005Request);
        
        // 検索結果が0件の場合：取得結果０件エラーを返す。
        DataList<IfaComplianceReportViewStatusLookupManagerSql002ResponseModel> sql002Response = new DataList<IfaComplianceReportViewStatusLookupManagerSql002ResponseModel>();
        if (ZERO_VALUE.equals(sql005Response.get(0).getCount())) {
            dtoRes = IfaCommonUtil.createDataList(complianceReportViewStatusLookupManagerList, ErrorLevel.INFO,
                    Integer.toString(ErrorLevel.INFO.getId()), IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND));
            return dtoRes;
        } 
        if (outputFct038Dto.getCsvDownloadNum() <
                Integer.parseInt(sql005Response.get(0).getCount())) {
            //検索結果が最大取得件数超過の場合：メッセージを表示し、コンプライアンス通信一覧リストに前から最大取得件数をセットする
            IfaComplianceReportViewStatusLookupManagerSql002RequestModel sql002Request = new IfaComplianceReportViewStatusLookupManagerSql002RequestModel();
            BeanUtils.copyProperties(sql002Request, dtoReq);
            sql002Request.setPrivId(userAccount.getMedUsers().getPrivId());
            sql002Request.setBranchId(userAccount.getMedUsers().getBranchId());
            sql002Request.setRownum(String.valueOf(outputFct038Dto.getCsvDownloadNum()));
            sql002Response = dao.selectIfaComplianceReportViewStatusLookupManagerSql002(sql002Request);
            dtoRes = IfaCommonUtil.createDataList(complianceReportViewStatusLookupManagerList, ErrorLevel.WARNING,
                    Integer.toString(ErrorLevel.WARNING.getId()),
                    IfaCommonUtil.getMessage(WARNINGS_DATALIST_CSV_OVERMAXROWNUM,
                            new String[] { String.valueOf(outputFct038Dto.getCsvDownloadNum()),
                                    sql002Response.get(0).getWholeCount(),
                                    String.valueOf(outputFct038Dto.getCsvDownloadNum()) }));
        } else {
            //上記以外：コンプライアンス通信一覧リストに全件セット
            IfaComplianceReportViewStatusLookupManagerSql002RequestModel sql002Request = new IfaComplianceReportViewStatusLookupManagerSql002RequestModel();
            sql002Request.setPrivId(userAccount.getMedUsers().getPrivId());
            sql002Request.setBranchId(userAccount.getMedUsers().getBranchId());
            BeanUtils.copyProperties(sql002Request, dtoReq);
            sql002Response = dao.selectIfaComplianceReportViewStatusLookupManagerSql002(sql002Request);
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.SUCCESS, "0", "");
        }
        //④一覧をCSV出力する。
        IfaComplianceReportViewStatusLookupManagerCsvOut csvOut = new IfaComplianceReportViewStatusLookupManagerCsvOut(
                complianceService);

        dtoRes.setTitle(csvOut.doCreateCsvFile(sql002Response, sessionId, userAccount.getUserId(), userAccount.getPrivId()));
        dtoRes.setTotalSize(sql002Response.size());
        return dtoRes;
    }
    
    /**
     * アクションID：A005
     * アクション名：閲覧不要設定
     * Dto リクエスト：IfaComplianceReportViewStatusLookupManagerA005DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupManagerA005DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>s
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupManagerA005ResponseDto> noNeedToViewSettingA005(
            IfaComplianceReportViewStatusLookupManagerA005RequestDto dtoReq) throws Exception {
        
        DataList<IfaComplianceReportViewStatusLookupManagerA005ResponseDto> dtoRes = new DataList<IfaComplianceReportViewStatusLookupManagerA005ResponseDto>();
        
        List<IfaComplianceReportViewStatusLookupManagerA005ResponseDto> a005ResponseDtoList = new ArrayList<IfaComplianceReportViewStatusLookupManagerA005ResponseDto>();
        
        IfaComplianceReportViewStatusLookupManagerA005ResponseDto a005ResponseDto = new IfaComplianceReportViewStatusLookupManagerA005ResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaComplianceReportViewStatusLookupManagerServiceImplL.noNeedToViewSettingA005");
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        //①コンプライアンス通信の閲覧不要設定を行う。
        //閲覧不要設定が閲覧不要の場合：コンプライアンス通信閲覧要否管理個別テーブルに閲覧不要設定を登録する。
        if (VIEW_EXCLUDE_SETTING_1.equals(dtoReq.getViewExcludeSetting())) {
            IfaComplianceReportViewStatusLookupManagerSql003RequestModel sql003Request = new IfaComplianceReportViewStatusLookupManagerSql003RequestModel();
            String userId = userAccount.getMedUsers().getUserId();
            sql003Request.setCreateBy(userId);
            sql003Request.setUpdateBy(userId);
            BeanUtils.copyProperties(sql003Request, dtoReq);
            sql003Request.setEmployeeId(dtoReq.getEmpCode());
            int sql003Response = dao.insertIfaComplianceReportViewStatusLookupManagerSql003(sql003Request);
        } else if (VIEW_EXCLUDE_SETTING_0.equals(dtoReq.getViewExcludeSetting())) {
            IfaComplianceReportViewStatusLookupManagerSql004RequestModel sql004Request = new IfaComplianceReportViewStatusLookupManagerSql004RequestModel();
            BeanUtils.copyProperties(sql004Request, dtoReq);
            int sql004Response = dao.deleteIfaComplianceReportViewStatusLookupManagerSql004(sql004Request);
        }
        List<IfaComplianceReportViewStatusLookupManagerA005ResponseDtoComplianceReportList> complianceReportListList = 
                new ArrayList<IfaComplianceReportViewStatusLookupManagerA005ResponseDtoComplianceReportList>();
        IfaComplianceReportViewStatusLookupManagerA005ResponseDtoComplianceReportList complianceReportList = new IfaComplianceReportViewStatusLookupManagerA005ResponseDtoComplianceReportList();
        complianceReportList.setCorBrowseFlag(dtoReq.getViewExcludeSetting());
        complianceReportListList.add(complianceReportList);
        a005ResponseDto.setComplianceReportListList(complianceReportListList);
        a005ResponseDtoList.add(a005ResponseDto);
        dtoRes = IfaCommonUtil.createDataList(a005ResponseDtoList, ErrorLevel.SUCCESS, "0", "");
        return dtoRes;
    }
    
    /**
     * アクションID：A007
     * アクション名：検索表示（ポータル）
     * Dto リクエスト：IfaComplianceReportViewStatusLookupManagerA007DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupManagerA007DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupManagerA007ResponseDto> searchDisplayPortalA007(
            IfaComplianceReportViewStatusLookupManagerA007RequestDto dtoReq) throws Exception {
        
        DataList<IfaComplianceReportViewStatusLookupManagerA007ResponseDto> dtoRes = new DataList<IfaComplianceReportViewStatusLookupManagerA007ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaComplianceReportViewStatusLookupManagerServiceImplL.searchDisplayPortalA007");
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        List<IfaComplianceReportViewStatusLookupManagerA007ResponseDto> complianceReportViewStatusLookupManagerListA007 = new ArrayList<IfaComplianceReportViewStatusLookupManagerA007ResponseDto>();
        IfaComplianceReportViewStatusLookupManagerA007ResponseDto a007ResponseDto = new IfaComplianceReportViewStatusLookupManagerA007ResponseDto();
        List<IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReport> complianceReport = new ArrayList<IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReport>();
        //①A001を呼び出す
        IfaComplianceReportViewStatusLookupManagerSql001RequestModel sql001Request = new IfaComplianceReportViewStatusLookupManagerSql001RequestModel();
        DataList<IfaComplianceReportViewStatusLookupManagerSql001ResponseModel> sql001Response = dao
                .selectIfaComplianceReportViewStatusLookupManagerSql001(sql001Request);
        for (IfaComplianceReportViewStatusLookupManagerSql001ResponseModel sql001ResponseModel : sql001Response
                .getDataList()) {
            IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReport a007ResponseDtoComplianceReport = new IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReport();
            BeanUtils.copyProperties(a007ResponseDtoComplianceReport, sql001ResponseModel);
            complianceReport.add(a007ResponseDtoComplianceReport);
        }
        a007ResponseDto.setComplianceReportList(complianceReport);
        
        //② コンプライアンス通信情報を取得する。
        IfaComplianceReportViewStatusLookupManagerSql005RequestModel sql005Request = new IfaComplianceReportViewStatusLookupManagerSql005RequestModel();
        BeanUtils.copyProperties(sql005Request, dtoReq);
        sql005Request.setPrivId(userAccount.getMedUsers().getPrivId());
        sql005Request.setBranchId(userAccount.getMedUsers().getBranchId());
        // Actionパラメータにタイトル（過去月）、タイトル（当月）のいずれかに指定がある場合は、指定があるパラメータの値を画面.タイトルの値として扱う
        if (!StringUtil.isNullOrEmpty(dtoReq.getTitleThisMonth())) {
            sql005Request.setTitle(dtoReq.getTitleThisMonth());
        } else if (!StringUtil.isNullOrEmpty(dtoReq.getTitleLastMonth())) {
            sql005Request.setTitle(dtoReq.getTitleLastMonth());
        }
        DataList<IfaComplianceReportViewStatusLookupManagerSql005ResponseModel> sql005Response = dao
                .selectIfaComplianceReportViewStatusLookupManagerSql005(sql005Request);
       
        // 検索結果が0件の場合：取得結果０件エラーを返す。
        if (ZERO_VALUE.equals(sql005Response.get(0).getCount())) {
            dtoRes = IfaCommonUtil.createDataList(complianceReportViewStatusLookupManagerListA007, ErrorLevel.INFO,
                    Integer.toString(ErrorLevel.INFO.getId()), IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND));
            return dtoRes;
        } else if (sql005Response.get(0).getCount().length() > LIMIT_VALUE.length() ||
                Integer.parseInt(LIMIT_VALUE) < Integer.parseInt(sql005Response.get(0).getCount())) {
            // 検索結果が5,000件超過の場合：メッセージを表示し、コンプライアンス通信一覧リストに前から5,000件をセットする。
            IfaComplianceReportViewStatusLookupManagerSql002RequestModel sql002Request = new IfaComplianceReportViewStatusLookupManagerSql002RequestModel();
            BeanUtils.copyProperties(sql002Request, dtoReq);
            sql002Request.setRownum(LIMIT_VALUE);
            // Actionパラメータにタイトル（過去月）、タイトル（当月）のいずれかに指定がある場合は、指定があるパラメータの値を画面.タイトルの値として扱う
            if (!StringUtil.isNullOrEmpty(dtoReq.getTitleThisMonth())) {
                sql002Request.setTitle(dtoReq.getTitleThisMonth());
            } else if (!StringUtil.isNullOrEmpty(dtoReq.getTitleLastMonth())) {
                sql002Request.setTitle(dtoReq.getTitleLastMonth());
            }
            DataList<IfaComplianceReportViewStatusLookupManagerSql002ResponseModel> sql002Response = dao
                    .selectIfaComplianceReportViewStatusLookupManagerSql002(sql002Request);
            List<IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReportList> a007ComplianceReportList = 
                    new ArrayList<IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReportList>();
            
            for (IfaComplianceReportViewStatusLookupManagerSql002ResponseModel sql002ResponseModel : sql002Response
                    .getDataList()) {
                IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReportList a007ResponseDtoComplianceReportList = 
                        new IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReportList();
                BeanUtils.copyProperties(a007ResponseDtoComplianceReportList, sql002ResponseModel);
                // 閲覧報告フラグを算出する
                if (StringUtil.isNullOrEmpty(sql002ResponseModel.getViewReportUser())) { // 閲覧報告者がNULLの場合：閲覧報告フラグに"1"（未報告）を設定する
                    a007ResponseDtoComplianceReportList.setViewReportFlag(VIEW_REPORT_FLAG_1);
                } else { // 閲覧報告者がNULLではない場合：閲覧報告フラグにを"0"（報告済）設定する
                    a007ResponseDtoComplianceReportList.setViewReportFlag(VIEW_REPORT_FLAG_0);
                }
                a007ComplianceReportList.add(a007ResponseDtoComplianceReportList);
            }
            a007ResponseDto.setComplianceReportListList(a007ComplianceReportList);
            complianceReportViewStatusLookupManagerListA007.add(a007ResponseDto);
            String wholeCount = "0";
            if (sql002Response.size() != 0) {
                wholeCount = sql002Response.get(0).getWholeCount();
            }
            
            dtoRes = IfaCommonUtil.createDataList(complianceReportViewStatusLookupManagerListA007, ErrorLevel.WARNING,
                    Integer.toString(ErrorLevel.WARNING.getId()),
                    IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVERMAXROWNUM,
                            new String[] { LIMIT_VALUE, wholeCount }));
        } else {
            IfaComplianceReportViewStatusLookupManagerSql002RequestModel sql002Request = new IfaComplianceReportViewStatusLookupManagerSql002RequestModel();
            sql002Request.setPrivId(userAccount.getMedUsers().getPrivId());
            sql002Request.setBranchId(userAccount.getMedUsers().getBranchId());
            // Actionパラメータにタイトル（過去月）、タイトル（当月）のいずれかに指定がある場合は、指定があるパラメータの値を画面.タイトルの値として扱う
            if (!StringUtil.isNullOrEmpty(dtoReq.getTitleThisMonth())) {
                sql002Request.setTitle(dtoReq.getTitleThisMonth());
            } else if (!StringUtil.isNullOrEmpty(dtoReq.getTitleLastMonth())) {
                sql002Request.setTitle(dtoReq.getTitleLastMonth());
            }
            BeanUtils.copyProperties(sql002Request, dtoReq);
            DataList<IfaComplianceReportViewStatusLookupManagerSql002ResponseModel> sql002Response = dao
                    .selectIfaComplianceReportViewStatusLookupManagerSql002(sql002Request);
            List<IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReportList> a007ComplianceReportList = 
                    new ArrayList<IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReportList>();
            for (IfaComplianceReportViewStatusLookupManagerSql002ResponseModel sql002ResponseModel : sql002Response
                    .getDataList()) {
                IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReportList a007ResponseDtoComplianceReportList = 
                        new IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReportList();
                BeanUtils.copyProperties(a007ResponseDtoComplianceReportList, sql002ResponseModel);
                // 閲覧報告フラグを算出する
                if (StringUtil.isNullOrEmpty(sql002ResponseModel.getViewReportUser())) { // 閲覧報告者がNULLの場合：閲覧報告フラグに"1"（未報告）を設定する
                    a007ResponseDtoComplianceReportList.setViewReportFlag(VIEW_REPORT_FLAG_1);
                } else { // 閲覧報告者がNULLではない場合：閲覧報告フラグにを"0"（報告済）設定する
                    a007ResponseDtoComplianceReportList.setViewReportFlag(VIEW_REPORT_FLAG_0);
                }
                a007ComplianceReportList.add(a007ResponseDtoComplianceReportList);
            }
            a007ResponseDto.setComplianceReportListList(a007ComplianceReportList);
            complianceReportViewStatusLookupManagerListA007.add(a007ResponseDto);
            dtoRes = IfaCommonUtil.createDataList(complianceReportViewStatusLookupManagerListA007, ErrorLevel.SUCCESS,
                    "0", "");
        }
        
        return dtoRes;
        
    }
    
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
    public DataList<IfaComplianceReportViewStatusLookupManagerA008ResponseDto> viewReportA008(
            IfaComplianceReportViewStatusLookupManagerA008RequestDto dtoReq) throws Exception {
        
        /* =============================================================================== */
        /* 1. コンプライアンス通信の閲覧報告の更新を行う。                                     */
        /* =============================================================================== */

        IfaComplianceReportViewStatusLookupManagerA008ResponseDto innerData = new IfaComplianceReportViewStatusLookupManagerA008ResponseDto();
        int recordCnt = 0; // 挿入、更新、削除件数

        if (VIEW_REPORT_FLAG_1.equals(dtoReq.getViewReportFlag())) { // リクエスト.閲覧報告フラグが"1"（未報告）の場合
            IfaComplianceReportViewStatusLookupManagerSql006RequestModel sql006Req = new IfaComplianceReportViewStatusLookupManagerSql006RequestModel();
            
            sql006Req.setLectureId(dtoReq.getLectureId()); // LECTURE_ID
            sql006Req.setUserId(dtoReq.getUserId()); // ユーザーID
            sql006Req.setUserAccountUserId(IfaCommonUtil.getUserAccount().getUserId()); // ユーザ共通情報.ユーザーID
            
            //  閲覧報告データを記録する。SQL006
            recordCnt = dao.updateIfaComplianceReportViewStatusLookupManagerSql006(sql006Req);
            
            // 閲覧報告フラグに"0"（報告済）をセットする
            innerData.setViewReportFlag(VIEW_REPORT_FLAG_0);
        } else { // リクエスト.閲覧報告フラグが"0"（報告済）の場合
            IfaComplianceReportViewStatusLookupManagerSql007RequestModel sql007Req = new IfaComplianceReportViewStatusLookupManagerSql007RequestModel();
            
            sql007Req.setLectureId(dtoReq.getLectureId()); // LECTURE_ID
            sql007Req.setUserId(dtoReq.getUserId()); // ユーザーID

            // 確認済レコードを削除する。SQL007
            recordCnt = dao.deleteIfaComplianceReportViewStatusLookupManagerSql007(sql007Req);

            // 閲覧報告フラグに"1"（未報告）をセットする
            innerData.setViewReportFlag(VIEW_REPORT_FLAG_1);
        }

        DataList<IfaComplianceReportViewStatusLookupManagerA008ResponseDto> dtoRes = null;
        if (recordCnt == 0) { // 挿入、更新、削除件数が0件の場合、エラーを返し処理を終了する。
            dtoRes = IfaCommonUtil.createDataList(
                null,
                ErrorLevel.FATAL,
                ERRORS_COR_COMPLIANCE_CONFIRMATION_COR_CONFIRMATION_REPORT,
                IfaCommonUtil.getMessage(ERRORS_COR_COMPLIANCE_CONFIRMATION_COR_CONFIRMATION_REPORT)
            );
        } else { // 挿入、更新、削除件数が1件以上の場合、INFOメッセージを返却する。
            dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.INFO,
                INFO_COR_COMPLIANCE_CONFIRMATION_COR_CONFIRMATION_REPORT,
                IfaCommonUtil.getMessage(INFO_COR_COMPLIANCE_CONFIRMATION_COR_CONFIRMATION_REPORT)
            );
        }

        return dtoRes;
    }
}
