package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.IfaComplianceReportViewStatusLookupInternalAdminDao;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql001RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDtoComplianceReport;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDtoComplianceReport;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA003RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA003ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA004RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDtoComplianceReport;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.service.IfaComplianceReportViewStatusLookupInternalAdminService;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.util.IfaComplianceReportViewStatusLookupInternalAdminCsvOut;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.util.internal.StringUtil;

/**
 * 画面ID：SUB0401_01-01
 * 画面名：コンプライアンス通信閲覧状況照会(内部管理責任者用)
 * @author 鄒
 *
 * 2023/12/04 新規作成
 */
@Component(value = "cmpIfaComplianceReportViewStatusLookupInternalAdminService")
public class IfaComplianceReportViewStatusLookupInternalAdminServiceImpL
        implements IfaComplianceReportViewStatusLookupInternalAdminService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaComplianceReportViewStatusLookupInternalAdminServiceImpL.class);
    
    /**
     * FCT030仲介業者コード営業員リスト取得
     */
    @Autowired
    private Fct030 fct030;
    
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
    
    @Autowired
    private IfaComplianceReportViewStatusLookupInternalAdminDao dao;
    
    /** 全て入力なし：エラー */
    public static final String ERRORS_REQUIRED = "errors.required";
    
    public static final String ERRORS_REQUIRED_MSG = "\"仲介業者名\"、\"営業員名\"、\"タイトル\"";
    
    /** "参照可能な仲介業者コード／営業員コードが存在しません。"*/
    private static final String ERRORS_CMN_IFAAGENTCODES_NOTEXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    /** 検索結果が0件です。条件を設定して再度検索して下さい。 */
    public static final String ERRORS_DATALIST_NOTFOUND = "errors.dataList.notfound";
    
    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 */
    public static final String WARNINGS_DATALIST_OVERMAXROWNUM = "warnings.dataList.overMaxRownum";
    
    public static final Integer LIMIT_VALUE = 5000;
    
    /** FCT038 画面ID*/
    private static final String SCREEN_ID = "SUB0401_01-01";
    
    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のcsv出力を行います。*/
    private static final String WARNINGS_DATALIST_CSV_OVERMAXROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaComplianceReportViewStatusLookupInternalAdminA001DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupInternalAdminA001DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDto> initializeA001(
            IfaComplianceReportViewStatusLookupInternalAdminA001RequestDto dtoReq) throws Exception {
        
        DataList<IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDto> dtoRes = new DataList<IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaComplianceReportViewStatusLookupInternalAdminServiceImplL.initializeA001");
        
        List<IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDto> resDtoList = new ArrayList<IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDto>();
        
        IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDto a001ResponseDto = new IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDto();
        //① コンプライアンス通信のタイトルを取得する。
        IfaComplianceReportViewStatusLookupInternalAdminSql001RequestModel sql001Req = new IfaComplianceReportViewStatusLookupInternalAdminSql001RequestModel();
        
        DataList<IfaComplianceReportViewStatusLookupInternalAdminSql001ResponseModel> sql001Res = dao
                .selectIfaComplianceReportViewStatusLookupInternalAdminSql001(sql001Req);
        
        List<IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDtoComplianceReport> complianceReportList = new ArrayList<IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDtoComplianceReport>();
        for (IfaComplianceReportViewStatusLookupInternalAdminSql001ResponseModel sql001ResponseModel : sql001Res
                .getDataList()) {
            IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDtoComplianceReport a001ResponseDtoComplianceReport = new IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDtoComplianceReport();
            BeanUtils.copyProperties(a001ResponseDtoComplianceReport, sql001ResponseModel);
            complianceReportList.add(a001ResponseDtoComplianceReport);
            
        }
        a001ResponseDto.setComplianceReportList(complianceReportList);
        resDtoList.add(a001ResponseDto);
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：一覧表示
     * Dto リクエスト：IfaComplianceReportViewStatusLookupInternalAdminA002DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupInternalAdminA002DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDto> listDisplayA002(
            IfaComplianceReportViewStatusLookupInternalAdminA002RequestDto dtoReq) throws Exception {
        
        DataList<IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDto> dtoRes = new DataList<IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDto>();
        
        List<IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDto> resDtoList = new ArrayList<IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDto>();
        
        IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDto a002ResponseDto = new IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaComplianceReportViewStatusLookupInternalAdminServiceImplL.listDisplayA002");
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        //① 仲介業者名,営業員名,タイトルの入力チェックを行う。
        //全て入力なし：エラーを返す。
        if (StringUtil.isNullOrEmpty(dtoReq.getBrokerName()) && StringUtil.isNullOrEmpty(dtoReq.getBrokerChargeName())
                && StringUtil.isNullOrEmpty(dtoReq.getTitle())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_REQUIRED, new String[] { ERRORS_REQUIRED_MSG }));
            return dtoRes;
        }
        
        //② 参照可能な仲介業者コードと営業員コードを取得する。
        OutputFct030Dto outputFct030Dto = new OutputFct030Dto();
        if (!"1".equals(userAccount.getPrivId())) {
            outputFct030Dto = fct030.getData(new InputFct030Dto());
            //FCT030.仲介業者営業員リストの件数が０件の場合：メッセージを表示し、処理終了。
            if (null == outputFct030Dto.getBrokerChargeList() || outputFct030Dto.getBrokerChargeList().size() == 0) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()),
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
                return dtoRes;
            }
        }
        //③ コンプライアンス通信情報を取得する。
        IfaComplianceReportViewStatusLookupInternalAdminSql002RequestModel sql002Req = new IfaComplianceReportViewStatusLookupInternalAdminSql002RequestModel();
        BeanUtils.copyProperties(sql002Req, dtoReq);
        sql002Req.setBrokerChargeList(outputFct030Dto.getBrokerChargeList());
        sql002Req.setPrivId(userAccount.getMedUsers().getPrivId());
        sql002Req.setMaxRowNum(LIMIT_VALUE);
        DataList<IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel> sql002Res = dao
                .selectIfaComplianceReportViewStatusLookupInternalAdminSql002(sql002Req);
        
        // 検索結果が0件の場合：取得結果０件エラーを返す。
        if (null == sql002Res.getDataList() || sql002Res.getDataList().size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()), IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND));
            return dtoRes;
        }
        // 検索結果が5,000件超過の場合：メッセージを表示し、前から5,000件の一覧を表示する。
        if (Integer.parseInt(sql002Res.get(0).getTotalCount()) > LIMIT_VALUE) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING,
                    Integer.toString(ErrorLevel.WARNING.getId()),
                    IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVERMAXROWNUM, new String[] {
                            String.valueOf(LIMIT_VALUE), sql002Res.get(0).getTotalCount() }));
            
        } else {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        }
        
        List<IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDtoComplianceReport> complianceReportList = new ArrayList<IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDtoComplianceReport>();
        
        for (IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel sql002ResponseModel : sql002Res
                .getDataList()) {
            IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDtoComplianceReport a002ResponseDtoComplianceReport = new IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDtoComplianceReport();
            BeanUtils.copyProperties(a002ResponseDtoComplianceReport, sql002ResponseModel);
            a002ResponseDtoComplianceReport.setEmployeeName(sql002ResponseModel.getUserId());
            complianceReportList.add(a002ResponseDtoComplianceReport);
        }
        a002ResponseDto.setComplianceReportList(complianceReportList);
        resDtoList.add(a002ResponseDto);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A003
     * アクション名：CSV出力
     * Dto リクエスト：IfaComplianceReportViewStatusLookupInternalAdminA003DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupInternalAdminA003DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupInternalAdminA003ResponseDto> csvOutputA003(
            IfaComplianceReportViewStatusLookupInternalAdminA003RequestDto dtoReq, String sessionId) throws Exception {
        
        DataList<IfaComplianceReportViewStatusLookupInternalAdminA003ResponseDto> dtoRes = new DataList<IfaComplianceReportViewStatusLookupInternalAdminA003ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaComplianceReportViewStatusLookupInternalAdminServiceImplL.csvOutputA003");
        
        List<IfaComplianceReportViewStatusLookupInternalAdminA003ResponseDto> resDtoList = new ArrayList<IfaComplianceReportViewStatusLookupInternalAdminA003ResponseDto>();
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        //① 仲介業者名,営業員名,タイトルの入力チェックを行う。
        //全て入力なし：エラーを返す。
        if (StringUtil.isNullOrEmpty(dtoReq.getBrokerName()) && StringUtil.isNullOrEmpty(dtoReq.getBrokerChargeName())
                && StringUtil.isNullOrEmpty(dtoReq.getTitle())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_REQUIRED, new String[] { ERRORS_REQUIRED_MSG }));
            return dtoRes;
        }
        //② 参照可能な仲介業者コードと営業員コードを取得する。
        OutputFct030Dto outputFct030Dto = new OutputFct030Dto();
        if (!"1".equals(userAccount.getPrivId())) {
            outputFct030Dto = fct030.getData(new InputFct030Dto());
            //FCT030.仲介業者営業員リストの件数が０件の場合：メッセージを表示し、処理終了。
            if (null == outputFct030Dto.getBrokerChargeList() || outputFct030Dto.getBrokerChargeList().size() == 0) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()),
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
                return dtoRes;
            }
        }
        //③ CSVダウンロードMAX件数を取得し、最大取得件数にセットする。
        InputFct038Dto inputFct038Dto = new InputFct038Dto();
        inputFct038Dto.setScreenId(SCREEN_ID);
        inputFct038Dto.setUserAuthority(userAccount.getPrivId());
        OutputFct038Dto outputFct038Dto = fct038.getData(inputFct038Dto);
        
        //④ コンプライアンス通信閲覧状況照会一覧情報をCSVへ出力する。
        IfaComplianceReportViewStatusLookupInternalAdminSql002RequestModel sql002Req = new IfaComplianceReportViewStatusLookupInternalAdminSql002RequestModel();
        BeanUtils.copyProperties(sql002Req, dtoReq);
        sql002Req.setBrokerChargeList(outputFct030Dto.getBrokerChargeList());
        sql002Req.setPrivId(userAccount.getMedUsers().getPrivId());
        sql002Req.setMaxRowNum(outputFct038Dto.getCsvDownloadNum());
        DataList<IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel> sql002Res = dao
                .selectIfaComplianceReportViewStatusLookupInternalAdminSql002(sql002Req);
        // 検索結果が0件の場合：取得結果０件エラーを返す。
        if (null == sql002Res.getDataList() || sql002Res.getDataList().size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()), IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND));
            return dtoRes;
        }
        IfaComplianceReportViewStatusLookupInternalAdminCsvOut csvOut = new IfaComplianceReportViewStatusLookupInternalAdminCsvOut(
                complianceService);
        
        // 検索結果が最大取得件数超過の場合：メッセージを表示し、前から最大取得件数分の一覧をCSV出力する。
        if (Integer.parseInt(sql002Res.get(0).getTotalCount()) > outputFct038Dto.getCsvDownloadNum()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING,
                    Integer.toString(ErrorLevel.WARNING.getId()),
                    IfaCommonUtil.getMessage(WARNINGS_DATALIST_CSV_OVERMAXROWNUM,
                            new String[] { String.valueOf(outputFct038Dto.getCsvDownloadNum()),
                                    sql002Res.get(0).getTotalCount(),
                                    String.valueOf(outputFct038Dto.getCsvDownloadNum()) }));
            DataList<IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel> sql002ResSub = new DataList<IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel>();
            sql002ResSub.setDataList(sql002Res.getDataList().subList(0, outputFct038Dto.getCsvDownloadNum()));
            
            dtoRes.setTitle(csvOut.doCreateCsvFile(sql002ResSub, sessionId, userAccount.getUserId(), null));
            dtoRes.setTotalSize(outputFct038Dto.getCsvDownloadNum());
        } else {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
            dtoRes.setTitle(csvOut.doCreateCsvFile(sql002Res, sessionId, userAccount.getUserId(), null));
            dtoRes.setTotalSize(sql002Res.getDataList().size());
        }
        
        return dtoRes;
    }
    
    /**
     * アクションID：A004
     * アクション名：検索表示（ポータル）
     * Dto リクエスト：IfaComplianceReportViewStatusLookupInternalAdminA004DtoRequest
     * Dto レスポンス：IfaComplianceReportViewStatusLookupInternalAdminA004DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDto> searchDisplayPortalA004(
            IfaComplianceReportViewStatusLookupInternalAdminA004RequestDto dtoReq) throws Exception {
        
        DataList<IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDto> dtoRes = new DataList<IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaComplianceReportViewStatusLookupInternalAdminServiceImplL.searchDisplayPortalA004");
        
        List<IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDto> resDtoList = new ArrayList<IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDto>();
        
        IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDto a004ResponseDto = new IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDto();
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        //① 参照可能な仲介業者コードと営業員コードを取得する。
        OutputFct030Dto outputFct030Dto = fct030.getData(new InputFct030Dto());
        //FCT030.仲介業者営業員リストの件数が０件の場合：メッセージを表示し、処理終了。
        if (null == outputFct030Dto || outputFct030Dto.getBrokerChargeList().size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
            return dtoRes;
        }
        //② A001を呼び出す
        IfaComplianceReportViewStatusLookupInternalAdminSql001RequestModel sql001Req = new IfaComplianceReportViewStatusLookupInternalAdminSql001RequestModel();
        
        DataList<IfaComplianceReportViewStatusLookupInternalAdminSql001ResponseModel> sql001Res = dao
                .selectIfaComplianceReportViewStatusLookupInternalAdminSql001(sql001Req);
        
        List<IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDtoComplianceReport> complianceReporTitletList = new ArrayList<IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDtoComplianceReport>();
        for (IfaComplianceReportViewStatusLookupInternalAdminSql001ResponseModel sql001ResponseModel : sql001Res
                .getDataList()) {
            IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDtoComplianceReport a001ResponseDtoComplianceReport = new IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDtoComplianceReport();
            BeanUtils.copyProperties(a001ResponseDtoComplianceReport, sql001ResponseModel);
            complianceReporTitletList.add(a001ResponseDtoComplianceReport);
            
        }
        a004ResponseDto.setComplianceReportTitleList(complianceReporTitletList);
        
        //③ コンプライアンス通信情報を取得する。
        IfaComplianceReportViewStatusLookupInternalAdminSql002RequestModel sql002Req = new IfaComplianceReportViewStatusLookupInternalAdminSql002RequestModel();
        BeanUtils.copyProperties(sql002Req, dtoReq);
        sql002Req.setBrokerChargeList(outputFct030Dto.getBrokerChargeList());
        sql002Req.setPrivId(userAccount.getMedUsers().getPrivId());
        sql002Req.setMaxRowNum(LIMIT_VALUE);
        // Actionパラメータにタイトル（過去月）、タイトル（当月）のいずれかに指定がある場合は、指定があるパラメータの値を画面.タイトルの値として扱う
        if (!StringUtil.isNullOrEmpty(dtoReq.getTitleThisMonth())) {
            sql002Req.setTitle(dtoReq.getTitleThisMonth());
        } else if (!StringUtil.isNullOrEmpty(dtoReq.getTitleLastMonth())) {
            sql002Req.setTitle(dtoReq.getTitleLastMonth());
        }
        DataList<IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel> sql002Res = dao
                .selectIfaComplianceReportViewStatusLookupInternalAdminSql002(sql002Req);
        
        // 検索結果が0件の場合：取得結果０件エラーを返す。
        if (null == sql002Res.getDataList() || sql002Res.getDataList().size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()), IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND));
            return dtoRes;
        }
        
        List<IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDtoComplianceReport> complianceReportList = new ArrayList<IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDtoComplianceReport>();
        
        for (IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel sql002ResponseModel : sql002Res
                .getDataList()) {
            IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDtoComplianceReport a004ResponseDtoComplianceReport = new IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDtoComplianceReport();
            BeanUtils.copyProperties(a004ResponseDtoComplianceReport, sql002ResponseModel);
            a004ResponseDtoComplianceReport.setEmployeeName(sql002ResponseModel.getUserId());
            complianceReportList.add(a004ResponseDtoComplianceReport);
        }
        a004ResponseDto.setComplianceReportList(complianceReportList);
        resDtoList.add(a004ResponseDto);
        
        // 検索結果が5,000件超過の場合：メッセージを表示し、前から5,000件の一覧を表示する。
        if (Integer.parseInt(sql002Res.getDataList().get(0).getTotalCount()) > LIMIT_VALUE) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING,
                    Integer.toString(ErrorLevel.WARNING.getId()),
                    IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVERMAXROWNUM, new String[] {
                            String.valueOf(LIMIT_VALUE), sql002Res.getDataList().get(0).getTotalCount() }));
            
        } else {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        }
        
        return dtoRes;
    }
}
