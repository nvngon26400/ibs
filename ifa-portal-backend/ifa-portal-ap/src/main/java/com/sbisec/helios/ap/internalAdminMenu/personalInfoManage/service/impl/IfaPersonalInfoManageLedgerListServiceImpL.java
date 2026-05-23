package com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.IfaPersonalInfoManageLedgerListDao;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql001ResponseModel;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListRequestModelBrokerCharge;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql003ResponseModel;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql004RequestModel;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql005RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql005ResponseModel;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA001DtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA001DtoResponse;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA003DtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA003DtoResponse;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA003DtoResponsePersonalInfoManageLedgerListInfo;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA005aDtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA005aDtoResponse;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListDtoResponsePersonalInfoManageLedgerListInfo;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA006DtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA006DtoResponse;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA006DtoRequestPersonalInfoManageLedger;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.service.IfaPersonalInfoManageLedgerListService;

import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;

import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections.CollectionUtils;

import org.apache.commons.beanutils.BeanUtils;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.util.IfaPersonalInfoManageLedgerListCsvOut;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 画面ID：SUB0403-01
 * 画面名：個人情報管理台帳一覧
 *
 * @author 大崎 辰弥
 *
    2023/12/29 新規作成
 */

@Component(value = "cmpIfaPersonalInfoManageLedgerListService")
public class IfaPersonalInfoManageLedgerListServiceImpL implements IfaPersonalInfoManageLedgerListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaPersonalInfoManageLedgerListServiceImpL.class);
    
    // --------------------------------
    // メッセージ
    // --------------------------------

    /** "個人情報管理台帳作成ディレクトリがDBに登録されていません。システム管理者にご連絡ください。" */
    
    private static final String IFO_ITA_PERSONALYLEDGER_DIRECTORY_NOTFOUND = "info.ita.PersonalyLedgerDirectory.notfound";

    /** "参照可能な仲介業者コード／営業員コードが存在しません。" */
    
    private static final String ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST = "errors.cmn.ifaAgentCodes.notExist";

    /** "検索結果が0件です。\n条件を設定して再度検索して下さい。" */
    
    private static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";

    /**
     * "検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。" {0}："5,000"
     */
    
    private static final String WARNINGS_DATALIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";

    /** "{0}が失敗しました。" */
    
    private static final String ERRORS_PROCESSING_FAILED = "errors.processingFailed";
    
    /** "{0}を更新しました。" */
    
    private static final String INFO_UPDATE_COMPLETED = "info.updateCompleted";
    
    // --------------------------------
    // 変数定義
    // -------------------------------- 
    /** 権限コード：SBI証券本店 の場合 */
    
    private static final String AUTH_CODE_SBI = "1";
    
    /** 最大データリストサイズ */
    
    private static final int MAX_ROW_NUM = 5000;
    
    /** 画面ID */
    
    private static final String SCREEN_ID = "SUB0403-01";

    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private Fct038 fct038;

    @Autowired
    private IfaPersonalInfoManageLedgerListDao dao;

    /**
     * ComplianceService(CSV証跡登録用)クラス
     */
    
    @Autowired
    private ComplianceService complianceService;

    /**
     * アクションID：A001
     * アクション名：初期表示
     * Dto リクエスト：IfaPersonalInfoManageLedgerListA001DtoRequest
     * Dto レスポンス：IfaPersonalInfoManageLedgerListA001DtoResponse
     * model リクエスト：IfaPersonalInfoManageLedgerListSql004RequestModel
     * model レスポンス：IfaPersonalInfoManageLedgerListSql004ResponseModel

     * @param dtoReq リクエスト
     * @return IfaPersonalInfoManageLedgerListA001DtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaPersonalInfoManageLedgerListA001DtoResponse> initializeA001(IfaPersonalInfoManageLedgerListA001DtoRequest dtoReq)
            throws Exception {
        
        // 個人情報管理台帳作成ディレクトリを取得
        DataList<IfaPersonalInfoManageLedgerListSql001ResponseModel> sql001Res = dao.selectIfaPersonalInfoManageLedgerListSql001();
        
        // 個人情報管理台帳作成ディレクトリ取得結果が0件の場合、メッセージを表示し処理終了
        if (sql001Res.getDataList() == null || sql001Res.getDataList().size() == 0) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.INFO, 
            IFO_ITA_PERSONALYLEDGER_DIRECTORY_NOTFOUND, IfaCommonUtil.getMessage(IFO_ITA_PERSONALYLEDGER_DIRECTORY_NOTFOUND));
        }
        
        IfaPersonalInfoManageLedgerListA001DtoResponse response = new IfaPersonalInfoManageLedgerListA001DtoResponse();
        response.setFileDirectory(sql001Res.get(0).getFileDirectory());

        return IfaCommonUtil.createDataList(List.of(response), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }

    /**
     * アクションID：A003
     * アクション名：表示
     * Dto リクエスト：IfaPersonalInfoManageLedgerListA003DtoRequest
     * Dto レスポンス：IfaPersonalInfoManageLedgerListA003DtoResponse
     * model リクエスト：IfaPersonalInfoManageLedgerListSql004RequestModel
     * model レスポンス：IfaPersonalInfoManageLedgerListSql004ResponseModel

     * @param dtoReq リクエスト
     * @return IfaPersonalInfoManageLedgerListA003DtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaPersonalInfoManageLedgerListA003DtoResponse> displayA003(IfaPersonalInfoManageLedgerListA003DtoRequest dtoReq)
            throws Exception {
        //==============================
        // FCT030
        //==============================
        
        // ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // 仲介業者営業員リスト
        OutputFct030Dto fct030Dto = new OutputFct030Dto();
        
        // 権限チェック
        // 参照権限：SBI証券本店以外の場合のみチェックを行う
        if (!StringUtils.equals(AUTH_CODE_SBI, userAccount.getPrivId())) {
            // FCT030.仲介業者営業員リストの件数が0件の場合：メッセージを表示し、処理終了
            InputFct030Dto fct030InData = new InputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);
            
            if (CollectionUtils.isEmpty(fct030Dto.getBrokerChargeList()) || fct030Dto.getBrokerChargeList().size() == 0) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST));
            }
        }

        //==============================
        // 個人情報管理台帳一覧情報件数を取得（SQL005）
        //==============================

        String processDayTimeFrom = DateFormatUtil.dateFormatToHyphen(dtoReq.getProcessDayTimeFrom());
        String processDayTimeTo = DateFormatUtil.dateFormatToHyphen(dtoReq.getProcessDayTimeTo());
        List<IfaPersonalInfoManageLedgerListRequestModelBrokerCharge> brokerChargeListSqlReq = getBrokerChargeListSqlReq(fct030Dto);

        // SQL005.総件数
        int personalInfoManageLedgerListInfoCount = getPersonalInfoManageLedgerListInfoCount(brokerChargeListSqlReq, userAccount.getPrivId(), processDayTimeFrom, processDayTimeTo);
        if (personalInfoManageLedgerListInfoCount == 0) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.INFO, 
                    ERRORS_DATALIST_NOT_FOUND, IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
        }

        //==============================
        // 個人情報管理台帳一覧情報を取得（SQL005）
        //==============================
                
        IfaPersonalInfoManageLedgerListSql003RequestModel selSql003Req = new IfaPersonalInfoManageLedgerListSql003RequestModel();
        selSql003Req.setBrokerChargeList(brokerChargeListSqlReq);
        selSql003Req.setPrivId(userAccount.getPrivId());
        selSql003Req.setProcessTargetPeriodFrom(processDayTimeFrom);
        selSql003Req.setProcessTargetPeriodTo(processDayTimeTo);
        selSql003Req.setMaxRow(MAX_ROW_NUM);        
        
        List<IfaPersonalInfoManageLedgerListA003DtoResponsePersonalInfoManageLedgerListInfo> personalInfoManageLedgerListInfo = new ArrayList<>();
        DataList<IfaPersonalInfoManageLedgerListSql003ResponseModel> selSql003Res = dao.selectIfaPersonalInfoManageLedgerListSql003(selSql003Req);
        for (IfaPersonalInfoManageLedgerListSql003ResponseModel sqlResponse : selSql003Res.getDataList()) {
            IfaPersonalInfoManageLedgerListA003DtoResponsePersonalInfoManageLedgerListInfo dtoResponse = new IfaPersonalInfoManageLedgerListA003DtoResponsePersonalInfoManageLedgerListInfo();
            BeanUtils.copyProperties(dtoResponse, sqlResponse);
            personalInfoManageLedgerListInfo.add(dtoResponse);       
        }

        IfaPersonalInfoManageLedgerListA003DtoResponse response = new IfaPersonalInfoManageLedgerListA003DtoResponse();
        response.setPersonalInfoManageLedgerListInfo(personalInfoManageLedgerListInfo);
        
        // SQL005.総件数が5000件超過の場合、対象メッセージを表示
        if (MAX_ROW_NUM < personalInfoManageLedgerListInfoCount) {
            return IfaCommonUtil.createDataList(List.of(response), ErrorLevel.WARNING, WARNINGS_DATALIST_OVER_MAX_ROWNUM, IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVER_MAX_ROWNUM,
                    new String[] { String.valueOf(MAX_ROW_NUM), String.valueOf(personalInfoManageLedgerListInfoCount) }));
        }
        
        return IfaCommonUtil.createDataList(List.of(response), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }

    /**
     * アクションID：A005a
     * アクション名：表示
     * Dto リクエスト：IfaPersonalInfoManageLedgerListA005aDtoRequest
     * Dto レスポンス：IfaPersonalInfoManageLedgerListA005aDtoResponse
     * model リクエスト：IfaPersonalInfoManageLedgerListSql004RequestModel
     * model レスポンス：IfaPersonalInfoManageLedgerListSql004ResponseModel

     * @param dtoReq リクエスト
     * @return IfaPersonalInfoManageLedgerListA005aDtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaPersonalInfoManageLedgerListA005aDtoResponse> csvOutputA005a(IfaPersonalInfoManageLedgerListA005aDtoRequest dtoReq,
            String fwSessionId)
                    throws Exception {

        //==============================
        // FCT030
        //==============================
        
        // ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // 仲介業者営業員リスト
        OutputFct030Dto fct030Dto = new OutputFct030Dto();
        
        // 権限チェック
        // 参照権限：SBI証券本店以外の場合のみチェックを行う
        if (!StringUtils.equals(AUTH_CODE_SBI, userAccount.getPrivId())) {
            // FCT030.仲介業者営業員リストの件数が0件の場合：メッセージを表示し、処理終了
            InputFct030Dto fct030InData = new InputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);
            
            if (CollectionUtils.isEmpty(fct030Dto.getBrokerChargeList()) || fct030Dto.getBrokerChargeList().size() == 0) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST));
            }
        }
        //==============================
        // 個人情報管理台帳一覧情報件数を取得
        //==============================
        String processDayTimeFrom = DateFormatUtil.dateFormatToHyphen(dtoReq.getProcessDayTimeFrom());
        String processDayTimeTo = DateFormatUtil.dateFormatToHyphen(dtoReq.getProcessDayTimeTo());
        List<IfaPersonalInfoManageLedgerListRequestModelBrokerCharge> brokerChargeListSqlReq = getBrokerChargeListSqlReq(fct030Dto);

        // SQL005.総件数
        int personalInfoManageLedgerListInfoCount = getPersonalInfoManageLedgerListInfoCount(brokerChargeListSqlReq, userAccount.getPrivId(), processDayTimeFrom, processDayTimeTo);
        if (personalInfoManageLedgerListInfoCount == 0) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.INFO, 
                    ERRORS_DATALIST_NOT_FOUND, IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
        }

        //==============================
        // 個人情報管理台帳一覧情報を取得
        //==============================
        List<IfaPersonalInfoManageLedgerListDtoResponsePersonalInfoManageLedgerListInfo> personalInfoManageLedgerListInfo = new ArrayList<>();
        IfaPersonalInfoManageLedgerListSql003RequestModel selSql003Req = new IfaPersonalInfoManageLedgerListSql003RequestModel();
        selSql003Req.setBrokerChargeList(brokerChargeListSqlReq);
        selSql003Req.setPrivId(userAccount.getPrivId());
        selSql003Req.setProcessTargetPeriodFrom(processDayTimeFrom);
        selSql003Req.setProcessTargetPeriodTo(processDayTimeTo);
        
        //==============================
        // CSVダウンロードMAX件数を取得し、取得最大件数にセットする。
        //==============================
        InputFct038Dto fct038InData = new InputFct038Dto();
        fct038InData.setScreenId(SCREEN_ID);
        fct038InData.setUserAuthority(userAccount.getPrivId());
        
        OutputFct038Dto fct038Dto = fct038.getData(fct038InData);
        int maxCsvRowNum = fct038Dto.getCsvDownloadNum();
        selSql003Req.setMaxRow(maxCsvRowNum);

        DataList<IfaPersonalInfoManageLedgerListSql003ResponseModel> selSql003Res = dao.selectIfaPersonalInfoManageLedgerListSql003(selSql003Req);
        
        for (IfaPersonalInfoManageLedgerListSql003ResponseModel sqlResponse : selSql003Res.getDataList()) {
            IfaPersonalInfoManageLedgerListDtoResponsePersonalInfoManageLedgerListInfo dtoResponse = new IfaPersonalInfoManageLedgerListDtoResponsePersonalInfoManageLedgerListInfo();
            BeanUtils.copyProperties(dtoResponse, sqlResponse);
            personalInfoManageLedgerListInfo.add(dtoResponse);       
        }

        //==============================
        // 取得した情報を元にCSV出力用のファイルを作成する。
        //==============================
        DataList<IfaPersonalInfoManageLedgerListDtoResponsePersonalInfoManageLedgerListInfo> exportList = new DataList<IfaPersonalInfoManageLedgerListDtoResponsePersonalInfoManageLedgerListInfo>();
        exportList.setDataList(personalInfoManageLedgerListInfo);

        // DataListに最終的に設定するエラーレベルなどはController側で設定する(BaseController.setStatusAndMessageToDataList()で使用)る
        DataList<IfaPersonalInfoManageLedgerListA005aDtoResponse> dtoRes = new DataList<IfaPersonalInfoManageLedgerListA005aDtoResponse>();
        dtoRes = IfaCommonUtil.createDataList(null, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        dtoRes.setMaxRownum(maxCsvRowNum);
        dtoRes.setOverMaxRownum(true);
        dtoRes.setTotalSize(personalInfoManageLedgerListInfoCount);
      
        // CSV出力用のファイルを作成する
        IfaPersonalInfoManageLedgerListCsvOut csvOut = new IfaPersonalInfoManageLedgerListCsvOut(complianceService);
        dtoRes.setTitle(csvOut.doCreateCsvFile(exportList, fwSessionId, userAccount.getUserId(), null));
        return dtoRes;
    }

    /**
     * アクションID：A006
     * アクション名：OK
     * Dto リクエスト：IfaPersonalInfoManageLedgerListA006DtoRequest
     * Dto レスポンス：IfaPersonalInfoManageLedgerListA006DtoResponse
     * model リクエスト：IfaPersonalInfoManageLedgerListSql006RequestModel
     * model レスポンス：IfaPersonalInfoManageLedgerListSql004ResponseModel

     * @param dtoReq リクエスト
     * @return IfaPersonalInfoManageLedgerListA006DtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaPersonalInfoManageLedgerListA006DtoResponse> okA006(IfaPersonalInfoManageLedgerListA006DtoRequest dtoReq)
            throws Exception {
 
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaPersonalInfoManageLedgerListServiceImplL.okA006");  
        }
        
        // ユーザ共通情報
        UserAccount ua = IfaCommonUtil.getUserAccount();
        // ユーザ共通情報.ユーザID
        String userId = ua.getUserId();
        
        for (IfaPersonalInfoManageLedgerListA006DtoRequestPersonalInfoManageLedger personalInfo : dtoReq.getPersonalInfoManageLedger()) {
            IfaPersonalInfoManageLedgerListSql004RequestModel updSql004Req = new IfaPersonalInfoManageLedgerListSql004RequestModel();
            BeanUtils.copyProperties(updSql004Req, personalInfo);
            updSql004Req.setLoginId(userId);
            trimInputValue(updSql004Req);
            int updSql004Res = dao.updateIfaPersonalInfoManageLedgerListSql004(updSql004Req);
            
            // 1件でも更新失敗になればエラー返却（現行踏襲）
            if (updSql004Res == 0) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_PROCESSING_FAILED,
                        IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED, new String[] {"個人情報管理台帳の更新"}));
            }
        }
        
        return IfaCommonUtil.createDataList(null, ErrorLevel.INFO, INFO_UPDATE_COMPLETED,
                    IfaCommonUtil.getMessage(INFO_UPDATE_COMPLETED, new String[] {"個人情報管理台帳"}));
    }
    
    private void trimInputValue(IfaPersonalInfoManageLedgerListSql004RequestModel updSql004Req) {
        updSql004Req.setStorageSendingMedium(StringUtils.trim(updSql004Req.getStorageSendingMedium()));
        updSql004Req.setDepositDestinations(StringUtils.trim(updSql004Req.getDepositDestinations()));
        updSql004Req.setDestination(StringUtils.trim(updSql004Req.getDestination()));
        updSql004Req.setStorageSpace(StringUtils.trim(updSql004Req.getStorageSpace()));
        updSql004Req.setPreservePeriod(StringUtils.trim(updSql004Req.getPreservePeriod()));
        updSql004Req.setDisposeMethod(StringUtils.trim(updSql004Req.getDisposeMethod()));
        updSql004Req.setNotDispose(StringUtils.trim(updSql004Req.getNotDispose()));
        updSql004Req.setDisposeDateYmd(StringUtils.trim(updSql004Req.getDisposeDateYmd()));
        updSql004Req.setCorDepositOutline(StringUtils.trim(updSql004Req.getCorDepositOutline()));
        updSql004Req.setCorDonationOutline(StringUtils.trim(updSql004Req.getCorDonationOutline()));
        updSql004Req.setCorDepositoryOutline(StringUtils.trim(updSql004Req.getCorDepositoryOutline()));
    }

    /**
     * SqlReq用のbrokerChargeListを返却

     * @param fct030Dto 仲介業者営業員リスト
     * @return SQL処理結果
     */
    
    private List<IfaPersonalInfoManageLedgerListRequestModelBrokerCharge> getBrokerChargeListSqlReq(OutputFct030Dto fct030Dto) {
        List<IfaPersonalInfoManageLedgerListRequestModelBrokerCharge> brokerChargeList = new ArrayList<IfaPersonalInfoManageLedgerListRequestModelBrokerCharge>();
        if (fct030Dto != null && !CollectionUtils.isEmpty(fct030Dto.getBrokerChargeList())) {
            for (BrokerCharge fctRes : fct030Dto.getBrokerChargeList()) {
                IfaPersonalInfoManageLedgerListRequestModelBrokerCharge brokerCharge = new IfaPersonalInfoManageLedgerListRequestModelBrokerCharge();
                brokerCharge.setBrokerCode(fctRes.getBrokerCode());
                brokerChargeList.add(brokerCharge);
            }
        }
        return brokerChargeList;
    }

    /**
     * SQL005.総件数を返却

     * @param brokerChargeListSqlReq 仲介業者営業員リスト
     * @param privId 権限コード
     * @param processDayTimeFrom 処理日時（From）
     * @param processDayTimeTo 処理日時（To）
     * @return SQL005.総件数
     * @throws Exception SQLExceptionなど
     */

    private int getPersonalInfoManageLedgerListInfoCount(
            List<IfaPersonalInfoManageLedgerListRequestModelBrokerCharge> brokerChargeListSqlReq, String privId, String processDayTimeFrom, String processDayTimeTo)
                    throws Exception {
        IfaPersonalInfoManageLedgerListSql005RequestModel selSql005Req = new IfaPersonalInfoManageLedgerListSql005RequestModel();
        selSql005Req.setBrokerChargeList(brokerChargeListSqlReq);
        selSql005Req.setPrivId(privId);
        selSql005Req.setProcessTargetPeriodFrom(processDayTimeFrom);
        selSql005Req.setProcessTargetPeriodTo(processDayTimeTo);

        DataList<IfaPersonalInfoManageLedgerListSql005ResponseModel> selSql005Res = dao.selectIfaPersonalInfoManageLedgerListSql005(selSql005Req);

        // SQL005.総件数を返却
        if (selSql005Res.getDataList() == null || selSql005Res.getDataList().size() == 0 || selSql005Res.getDataList().get(0).getRowCount() == 0) {
            return 0;
        }
        
        return selSql005Res.getDataList().get(0).getRowCount();

    }

}
