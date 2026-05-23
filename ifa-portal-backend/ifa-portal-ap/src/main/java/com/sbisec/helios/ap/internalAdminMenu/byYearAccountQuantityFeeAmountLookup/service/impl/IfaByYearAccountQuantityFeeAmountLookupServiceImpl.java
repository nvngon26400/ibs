package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct032;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct032Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct032Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.IfaByYearAccountQuantityFeeAmountLookupDao;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaByYearAccountQuantityFeeAmountLookupSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaByYearAccountQuantityFeeAmountLookupSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaByYearAccountQuantityFeeAmountLookupSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaByYearAccountQuantityFeeAmountLookupSql004RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaByYearAccountQuantityFeeAmountLookupSql004ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaByYearAccountQuantityFeeAmountLookupSql005RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaByYearAccountQuantityFeeAmountLookupSql005ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA002List;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA004RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA004ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA005RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA005ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA007Items;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA007RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA007ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.enums.GetClosingKbn;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA004Items;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.service.IfaByYearAccountQuantityFeeAmountLookupService;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.util.IfaByYearAccountQuantityFeeAmountLookupA004CsvOut;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.util.IfaByYearAccountQuantityFeeAmountLookupA007CsvOut;

/**
 * 画面ID：SUB0406-01
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 夏
 * @date   2025/05/22
 */

@Component(value = "cmpIfaByYearAccountQuantityFeeAmountLookupService")
public class IfaByYearAccountQuantityFeeAmountLookupServiceImpl implements IfaByYearAccountQuantityFeeAmountLookupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaByYearAccountQuantityFeeAmountLookupServiceImpl.class);
    
    /** 参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String ERRORS_CMN_IFAAGENTCODES_NOTEXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";
    
    /** 最大取得件数（表示用） */
    private static final int MAX_COUNT_DISPLAY = 5000;

    /** 検索結果が5000件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 */
    private static final String WARNINGS_DATA_LIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";

    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のCSV出力を行います。 */
    private static final String WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    /** 決算月が未設定です。 */
    private static final String ERRORS_CLOSINGMONTH_NOTEXIST =  "errors.closingMonth.notExist";
    
    /** コードマスタテーブル.種別 （'99':画面コメント） */
    private static final String M_CODE_CODE_TYPE_SCREEN_COMMENT = "99";

    /** コードマスタテーブル.CODE_1 （'01':デフォルト） */
    private static final String M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT = "01";

    /** コードマスタテーブル.CODE_2 （'25':媒介口座明細 コメント） */
    private static final String M_CODE_CODE_2_SCREEN_COMMENT_MEDIATE_ACCOUNT_DETAIL = "25";
    
    /** 画面ID */
    private static final String SCREEN_ID_1 = "SUB0406-01-01";
    private static final String SCREEN_ID_2 = "SUB0406-01-02";
    
    private static final String DATE6 = "yyyy/MM";
    private static final String DATE6_WITHOUT_SEPARATOR = "yyyyMM";
    private static final String DATE_SEPARATOR_TIME = "yyyy-MM-dd HH:mm:ss";
    
    /** 媒介可否区分の初期値 */
    private static final String KBN = "0";
    
    @Autowired
    private MCodeService mcodeService;

    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private Fct032 fct032;

    @Autowired
    private Fct038 fct038;
    
    @Autowired
    private IfaByYearAccountQuantityFeeAmountLookupDao dao;
    
    @Autowired
    private ComplianceService complianceService;
    
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaByYearAccountQuantityFeeAmountLookupA001RequestDto
     * Dto レスポンス：IfaByYearAccountQuantityFeeAmountLookupA001ResponseDto
     * model リクエスト：IfaByYearAccountQuantityFeeAmountLookupSql002RequestModel
     * model レスポンス：IfaByYearAccountQuantityFeeAmountLookupSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaByYearAccountQuantityFeeAmountLookupA001ResponseDto> initializeA001(
        IfaByYearAccountQuantityFeeAmountLookupA001RequestDto dtoReq) throws Exception {
        
        DataList<IfaByYearAccountQuantityFeeAmountLookupA001ResponseDto> dtoRes = new DataList<IfaByYearAccountQuantityFeeAmountLookupA001ResponseDto>();
        List<IfaByYearAccountQuantityFeeAmountLookupA001ResponseDto> dtoResList = dtoRes.getDataList();
        IfaByYearAccountQuantityFeeAmountLookupA001ResponseDto a001Res = new IfaByYearAccountQuantityFeeAmountLookupA001ResponseDto();
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaByYearAccountQuantityFeeAmountLookupServiceImpl.initializeA001");
        
        // コードマスタより、媒介口座明細 コメントを取得する。
        List<MCode> codeList = mcodeService.getMCodeList(M_CODE_CODE_TYPE_SCREEN_COMMENT,
            M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT, M_CODE_CODE_2_SCREEN_COMMENT_MEDIATE_ACCOUNT_DETAIL);
        
        if (codeList != null && codeList.size() > 0) {
            a001Res.setComment(codeList.get(0).getName());
        }
        
        // ユーザ共通情報.権限コード を取得
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();

        if (PrivId.HEAD_OFFICE.getId().equals(privId) || PrivId.BRANCH.getId().equals(privId)) {
            
            dtoResList.add(a001Res);
            dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, "", "");
        }
        
        List<BrokerCharge> brokerChargeList = null;
        
        // 仲介業者- 内部管理責任者
        if (PrivId.B_INNER_MANAGEMENT.getId().equals(privId)) {

            InputFct032Dto fct032InData = new InputFct032Dto();
            OutputFct032Dto fct032Dto = fct032.getData(fct032InData);
            a001Res.setBrokerCodeAutoDispFlag(fct032Dto.getBrokerCodeAutoSettingJudge());
            DataList<IfaByYearAccountQuantityFeeAmountLookupSql002ResponseModel> sql001Res = getBrokerClosingMonth(IfaCommonUtil.getUserAccount().getMedUsers().getBrokerId(), privId, brokerChargeList);
            if (sql001Res != null && CollectionUtils.isNotEmpty(sql001Res.getDataList())
                && sql001Res.getDataList().get(0) != null) {
                IfaByYearAccountQuantityFeeAmountLookupSql002ResponseModel res = sql001Res.getDataList().get(0);
                a001Res.setBrokerCode(res.getBrokerCode());
                a001Res.setClosingMonth(res.getClosingMonth());
            } else {
                a001Res.setErrorMessage(ERRORS_CLOSINGMONTH_NOTEXIST);
            }
            dtoResList.add(a001Res);
            dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, "", "");
        }
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：年度別口座数・報酬額情報取得
     * Dto リクエスト：IfaByYearAccountQuantityFeeAmountLookupA002RequestDto
     * Dto レスポンス：IfaByYearAccountQuantityFeeAmountLookupA002ResponseDto
     * model リクエスト：IfaByYearAccountQuantityFeeAmountLookupSql003RequestModel
     * model レスポンス：IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaByYearAccountQuantityFeeAmountLookupA002ResponseDto> displayA002(
        IfaByYearAccountQuantityFeeAmountLookupA002RequestDto dtoReq) throws Exception {
        
        DataList<IfaByYearAccountQuantityFeeAmountLookupA002ResponseDto> dtoRes = new DataList<IfaByYearAccountQuantityFeeAmountLookupA002ResponseDto>();
        List<IfaByYearAccountQuantityFeeAmountLookupA002ResponseDto> resList = new ArrayList<IfaByYearAccountQuantityFeeAmountLookupA002ResponseDto>();
        List<IfaByYearAccountQuantityFeeAmountLookupA002List> byYearAccountQuantityFeeAmountLookupA002List = new ArrayList<IfaByYearAccountQuantityFeeAmountLookupA002List>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaByYearAccountQuantityFeeAmountLookupServiceImpl.displayA002");
        }
        // Sql003リクエスト用
        IfaByYearAccountQuantityFeeAmountLookupSql003RequestModel sqlReq = new IfaByYearAccountQuantityFeeAmountLookupSql003RequestModel();
        if (StringUtils.isNotEmpty(dtoReq.getBrokerCode())) {
            sqlReq.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        }
        // Sql005リクエスト用
        IfaByYearAccountQuantityFeeAmountLookupSql005RequestModel sqlReq05 = new IfaByYearAccountQuantityFeeAmountLookupSql005RequestModel();
        if (StringUtils.isNotEmpty(dtoReq.getBrokerCode())) {
            sqlReq05.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        }
        // ユーザ共通情報.権限コード != SBI証券本店の場合、FCT030.仲介業者営業員リストを取得
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        List<BrokerCharge> brokerChargeList = null;
        if (!PrivId.HEAD_OFFICE.getId().equals(privId)) {
            InputFct030Dto fct030In = new InputFct030Dto();
            OutputFct030Dto fct030Out = fct030.getData(fct030In);
            brokerChargeList = fct030Out.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_IFAAGENTCODES_NOTEXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
            }
        }
        // 検索条件入力項目で仲介業者媒介可否情報リストを取得する。
        sqlReq05.setPrivId(privId);
        sqlReq05.setBrokerChargeList(brokerChargeList);
        sqlReq05.setChkBrokerCodeExclude(dtoReq.getChkBrokerCodeExclude());
        DataList<IfaByYearAccountQuantityFeeAmountLookupSql005ResponseModel> sqlRes05 = dao.selectIfaByYearAccountQuantityFeeAmountLookupSql005(sqlReq05);
       
        // 検索条件入力項目で年度別口座数・報酬額リストを取得する。
        sqlReq.setPrivId(privId);
        sqlReq.setBrokerChargeList(brokerChargeList);
        sqlReq.setSettleYearList(dtoReq.getSettleYearList());
        sqlReq.setChkBrokerCodeExclude(dtoReq.getChkBrokerCodeExclude());
        String returnCode = "";
        String message = "";
        ErrorLevel errorLevel = null;
        DataList<IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel> sqlRes = new DataList<IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel>();
        sqlRes = dao.selectIfaByYearAccountQuantityFeeAmountLookupSql003(sqlReq);
        
        if (sqlRes == null || ObjectUtils.isEmpty(sqlRes.getDataList()) || sqlRes.size() == 0) {
            return IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
        }
        
        for (IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel selSql003Res : sqlRes.getDataList()) {
          IfaByYearAccountQuantityFeeAmountLookupA002List response = new IfaByYearAccountQuantityFeeAmountLookupA002List();
          BeanUtils.copyProperties(response, selSql003Res);
          // 1. 年度別口座数・報酬額リストに、媒介可否区分の初期値として、「0（媒介）」を設定する。
          response.setMediateProprietyKBN(KBN);
          if (sqlRes05 != null && !ObjectUtils.isEmpty(sqlRes05.getDataList()) && sqlRes05.size() != 0) {
              // 2.対象要素の仲介業者コードと決算年月を取得して、仲介業者媒介可否情報リストから、下記の条件に一致する媒介可否情報を取得する。
              // 2.1 最新適用開始日を取得する
              IfaByYearAccountQuantityFeeAmountLookupSql005ResponseModel sql005Model = findLatestByCodeAndDate(
                      sqlRes05.getDataList(), selSql003Res.getBrokerCode(), selSql003Res.getClosingYearMonth());
              if (sql005Model != null) {
              // 2.2 年度別口座数・報酬額リストの媒介可否区分を取得した値で更新する
                  response.setMediateProprietyKBN(sql005Model.getMediateProprietyKBN());
             // 3.取得した媒介可否情報が、「1（非媒介）」であるか判定を行って、「1（非媒介）」の場合：対象要素の媒介口座数を0に更新
                  if ("1".equals(sql005Model.getMediateProprietyKBN())) {
                      response.setNumberOfActiveAccounts("0");
                  }
              }
          }
          // 4.その他受入手数料 < 0 であるか判定を行う。
          if (StringUtils.isNotEmpty(response.getOtherCom())) {
              BigDecimal otherComF = new BigDecimal(response.getOtherCom());
              if (otherComF.compareTo(BigDecimal.ZERO)<0 ) {
                  // 「その他受入手数料 < 0」の場合：その他受入手数料を0に更新する
                  response.setOtherCom("0");
              }
          }
          // 各計算処理
          IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel resultCalculations = pramCalculations (response.getMediateCom(),response.getOtherCom());
          //計
          response.setTotal(resultCalculations.getTotal());
          // 媒介手数料（千円）
          response.setMediateComThousand(resultCalculations.getMediateComThousand());
          // その他受入手数料（千円）
          response.setOtherComThousand(resultCalculations.getOtherComThousand());
          // 計（千円）
          response.setTotalThousand(resultCalculations.getTotalThousand());
          
          byYearAccountQuantityFeeAmountLookupA002List.add(response);
        }
        
        IfaByYearAccountQuantityFeeAmountLookupA002ResponseDto res = new IfaByYearAccountQuantityFeeAmountLookupA002ResponseDto();
        
        if (byYearAccountQuantityFeeAmountLookupA002List.size() > MAX_COUNT_DISPLAY) {
            returnCode = WARNINGS_DATA_LIST_OVER_MAX_ROWNUM;
            message = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                new String[] { String.valueOf(MAX_COUNT_DISPLAY),
                        String.valueOf(byYearAccountQuantityFeeAmountLookupA002List.size()) });
            errorLevel = ErrorLevel.WARNING;
            res.setIfaByYearAccountQuantityFeeAmountLookupA002List(byYearAccountQuantityFeeAmountLookupA002List.subList(0, MAX_COUNT_DISPLAY));
            resList.add(res);
        } else {
            errorLevel = ErrorLevel.SUCCESS;
            res.setIfaByYearAccountQuantityFeeAmountLookupA002List(byYearAccountQuantityFeeAmountLookupA002List);
            resList.add(res);
        }
        
        dtoRes = IfaCommonUtil.createDataList(resList, errorLevel, returnCode, message);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A004
     * アクション名：年度別口座数・報酬額CSV出力
     * Dto リクエスト：IfaByYearAccountQuantityFeeAmountLookupA004RequestDto
     * Dto レスポンス：IfaByYearAccountQuantityFeeAmountLookupA004ResponseDto
     * model リクエスト：IfaByYearAccountQuantityFeeAmountLookupSql003RequestModel
     * model レスポンス：IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaByYearAccountQuantityFeeAmountLookupA004ResponseDto> csvOutputA004(
        IfaByYearAccountQuantityFeeAmountLookupA004RequestDto dtoReq, String fwSessionId) throws Exception {
        
        DataList<IfaByYearAccountQuantityFeeAmountLookupA004ResponseDto> dtoRes = new DataList<IfaByYearAccountQuantityFeeAmountLookupA004ResponseDto>();
        List<IfaByYearAccountQuantityFeeAmountLookupA004Items> byYearAccountQuantityFeeAmountLookupCsvList = new ArrayList<IfaByYearAccountQuantityFeeAmountLookupA004Items>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaByYearAccountQuantityFeeAmountLookupServiceImpl.csvOutputA004");
        }
        // Sql003リクエスト用
        IfaByYearAccountQuantityFeeAmountLookupSql003RequestModel sqlReq = new IfaByYearAccountQuantityFeeAmountLookupSql003RequestModel();
        if (StringUtils.isNotEmpty(dtoReq.getBrokerCode())) {
            sqlReq.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        }
        // Sql005リクエスト用
        IfaByYearAccountQuantityFeeAmountLookupSql005RequestModel sqlReq05 = new IfaByYearAccountQuantityFeeAmountLookupSql005RequestModel();
        if (StringUtils.isNotEmpty(dtoReq.getBrokerCode())) {
            sqlReq.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        }
        // ユーザ共通情報.権限コード != SBI証券本店の場合、FCT030.仲介業者営業員リストを取得
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        List<BrokerCharge> brokerChargeList = null;
        if (!PrivId.HEAD_OFFICE.getId().equals(privId)) {
            InputFct030Dto fct030In = new InputFct030Dto();
            OutputFct030Dto fct030Out = fct030.getData(fct030In);
            brokerChargeList = fct030Out.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_IFAAGENTCODES_NOTEXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
            }
        }
        
        // ３．CSVダウンロードMAX件数を取得し、取得最大件数（閾値）にセットする。
        InputFct038Dto fct038In = new InputFct038Dto();
        fct038In.setScreenId(SCREEN_ID_1);
        fct038In.setUserAuthority(privId);
        OutputFct038Dto fct038Out = fct038.getData(fct038In);
        int csvDownloadNum = fct038Out.getCsvDownloadNum();
        // 検索条件入力項目で仲介業者媒介可否情報リストを取得する。
        sqlReq05.setPrivId(privId);
        sqlReq05.setBrokerChargeList(brokerChargeList);
        sqlReq05.setChkBrokerCodeExclude(dtoReq.getChkBrokerCodeExclude());
        DataList<IfaByYearAccountQuantityFeeAmountLookupSql005ResponseModel> sqlRes05 = dao.selectIfaByYearAccountQuantityFeeAmountLookupSql005(sqlReq05);
        
        // 検索条件入力項目で年度別口座数・報酬額リストを取得する。
        sqlReq.setPrivId(privId);
        sqlReq.setBrokerChargeList(brokerChargeList);
        sqlReq.setSettleYearList(dtoReq.getSettleYearList());
        sqlReq.setChkBrokerCodeExclude(dtoReq.getChkBrokerCodeExclude());
        String returnCode = "";
        String message = "";
        ErrorLevel errorLevel = null;
        DataList<IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel> sqlRes = new DataList<IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel>();
        sqlRes = dao.selectIfaByYearAccountQuantityFeeAmountLookupSql003(sqlReq);
        
        // CSV出力
        DataList<IfaByYearAccountQuantityFeeAmountLookupA004Items> csvDownList = new DataList<IfaByYearAccountQuantityFeeAmountLookupA004Items>();
        
        if (sqlRes == null || ObjectUtils.isEmpty(sqlRes.getDataList()) || sqlRes.size() == 0) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
        }
        
        for (IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel selSql003Res : sqlRes.getDataList()) {
            IfaByYearAccountQuantityFeeAmountLookupA004Items response =
                new IfaByYearAccountQuantityFeeAmountLookupA004Items();
            BeanUtils.copyProperties(response, selSql003Res);
            response.setClosingKbn(GetClosingKbn.nameOfValue(selSql003Res.getClosingKbn()).getValue());
            response.setClosingYearMonth(DateTimeFormatter.ofPattern(DATE6).format(YearMonth
                .parse(selSql003Res.getClosingYearMonth(), DateTimeFormatter.ofPattern(DATE6_WITHOUT_SEPARATOR))));
            if(StringUtils.isNotEmpty(selSql003Res.getNumberOfAccountsIncreaseDecrease())) {
                response.setNumberOfAccountsIncreaseDecrease(formatWithSign(selSql003Res.getNumberOfAccountsIncreaseDecrease()));
            }
         // 1. 年度別口座数・報酬額リストに、媒介可否区分の初期値として、「0（媒介）」を設定する。
            response.setMediateProprietyKBN(KBN);
            if (sqlRes05 != null && !ObjectUtils.isEmpty(sqlRes05.getDataList()) && sqlRes05.size() != 0) {
                // 2.対象要素の仲介業者コードと決算年月を取得して、仲介業者媒介可否情報リストから、下記の条件に一致する媒介可否情報を取得する。
                // 2.1 最新適用開始日を取得する
                IfaByYearAccountQuantityFeeAmountLookupSql005ResponseModel sql005Model = findLatestByCodeAndDate(
                        sqlRes05.getDataList(), selSql003Res.getBrokerCode(), selSql003Res.getClosingYearMonth());
                if (sql005Model != null) {
                // 2.2 年度別口座数・報酬額リストの媒介可否区分を取得した値で更新する
                    response.setMediateProprietyKBN(sql005Model.getMediateProprietyKBN());
                // 3.取得した媒介可否情報が、「1（非媒介）」であるか判定を行って、「1（非媒介）」の場合：対象要素の媒介口座数を0に更新
                    if ("1".equals(sql005Model.getMediateProprietyKBN())) {
                        response.setNumberOfActiveAccounts("0");
                    }
                }
            }
            // 4.その他受入手数料 < 0 であるか判定を行う。
           if (StringUtils.isNotEmpty(response.getOtherCom())) {
               BigDecimal otherComF = new BigDecimal(response.getOtherCom());
               if (otherComF.compareTo(BigDecimal.ZERO)<0 ) {
                   // 「その他受入手数料 < 0」の場合：その他受入手数料を0に更新する
                   response.setOtherCom("0");
               }
           }
           // 各計算処理
           IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel resultCalculations = pramCalculations (response.getMediateCom(),response.getOtherCom());
           //計
           response.setTotal(resultCalculations.getTotal());
           // 媒介手数料（千円）
           response.setMediateComThousand(resultCalculations.getMediateComThousand());
           // その他受入手数料（千円）
           response.setOtherComThousand(resultCalculations.getOtherComThousand());
           // 計（千円）
           response.setTotalThousand(resultCalculations.getTotalThousand());
           
            byYearAccountQuantityFeeAmountLookupCsvList.add(response);
        }
        
        if (byYearAccountQuantityFeeAmountLookupCsvList.size() > csvDownloadNum) {
            returnCode = WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM;
            message = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                new String[] { String.valueOf(csvDownloadNum), String.valueOf(byYearAccountQuantityFeeAmountLookupCsvList.size()), String.valueOf(csvDownloadNum) });
            errorLevel = ErrorLevel.WARNING;
            csvDownList.setDataList(byYearAccountQuantityFeeAmountLookupCsvList.subList(0, csvDownloadNum));
        } else {
            errorLevel = ErrorLevel.SUCCESS;
            csvDownList.setDataList(byYearAccountQuantityFeeAmountLookupCsvList);
        }

        CsvOutPutUtil csvOutPutUtil = new IfaByYearAccountQuantityFeeAmountLookupA004CsvOut(complianceService);
        
        String csvFileName =
            csvOutPutUtil.doCreateCsvFile(csvDownList, fwSessionId, IfaCommonUtil.getUserAccount().getUserId(), null);
        
        List<IfaByYearAccountQuantityFeeAmountLookupA004ResponseDto> dtoResList = new ArrayList<>();
        
        dtoRes = IfaCommonUtil.createDataList(dtoResList, errorLevel, returnCode, message);
        
        dtoRes.setTitle(csvFileName);
        
        dtoRes.setTotalSize(csvDownloadNum);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A005
     * アクション名：仲介業者決算月情報取得
     * Dto リクエスト：IfaByYearAccountQuantityFeeAmountLookupA005RequestDto
     * Dto レスポンス：IfaByYearAccountQuantityFeeAmountLookupA005ResponseDto
     * model リクエスト：IfaByYearAccountQuantityFeeAmountLookupSql002RequestModel
     * model レスポンス：IfaByYearAccountQuantityFeeAmountLookupSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaByYearAccountQuantityFeeAmountLookupA005ResponseDto> getClosingMonthA005(
        IfaByYearAccountQuantityFeeAmountLookupA005RequestDto dtoReq) throws Exception {
        
        DataList<IfaByYearAccountQuantityFeeAmountLookupA005ResponseDto> dtoRes = new DataList<IfaByYearAccountQuantityFeeAmountLookupA005ResponseDto>();
        List<IfaByYearAccountQuantityFeeAmountLookupA005ResponseDto> dtoResList = dtoRes.getDataList();
        IfaByYearAccountQuantityFeeAmountLookupA005ResponseDto a001Res = new IfaByYearAccountQuantityFeeAmountLookupA005ResponseDto();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaByYearAccountQuantityFeeAmountLookupServiceImpl.getClosingMonthA005");
        }
        
        // ユーザ共通情報.権限コード != SBI証券本店の場合、FCT030.仲介業者営業員リストを取得
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        List<BrokerCharge> brokerChargeList = null;
        if (!PrivId.HEAD_OFFICE.getId().equals(privId)) {
            InputFct030Dto fct030In = new InputFct030Dto();
            OutputFct030Dto fct030Out = fct030.getData(fct030In);
            brokerChargeList = fct030Out.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_IFAAGENTCODES_NOTEXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
            }
        }
        
        DataList<IfaByYearAccountQuantityFeeAmountLookupSql002ResponseModel> sql001Res = getBrokerClosingMonth(dtoReq.getBrokerCode(), privId, brokerChargeList);
        if (sql001Res != null && CollectionUtils.isNotEmpty(sql001Res.getDataList()) && sql001Res.getDataList().get(0) != null) {
            IfaByYearAccountQuantityFeeAmountLookupSql002ResponseModel res = sql001Res.getDataList().get(0);
            a001Res.setBrokerCode(res.getBrokerCode());
            a001Res.setClosingMonth(res.getClosingMonth());
        } else {
            a001Res.setErrorMessage(ERRORS_CLOSINGMONTH_NOTEXIST);
        }
        dtoResList.add(a001Res);
        dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, "", "");
        return dtoRes;
    }


    /**
     * アクションID：A007
     * アクション名：当期末口座明細
     * Dto リクエスト：IfaByYearAccountQuantityFeeAmountLookupA007RequestDto
     * Dto レスポンス：IfaByYearAccountQuantityFeeAmountLookupA007ResponseDto
     * model リクエスト：IfaByYearAccountQuantityFeeAmountLookupSql004RequestModel
     * model レスポンス：IfaByYearAccountQuantityFeeAmountLookupSql004ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaByYearAccountQuantityFeeAmountLookupA007ResponseDto> csvOutputA007(
        IfaByYearAccountQuantityFeeAmountLookupA007RequestDto dtoReq, String fwSessionId) throws Exception {
        
        DataList<IfaByYearAccountQuantityFeeAmountLookupA007ResponseDto> dtoRes = new DataList<IfaByYearAccountQuantityFeeAmountLookupA007ResponseDto>();
        List<IfaByYearAccountQuantityFeeAmountLookupA007Items> byYearAccountQuantityFeeAmountLookupCsvList = new ArrayList<IfaByYearAccountQuantityFeeAmountLookupA007Items>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaByYearAccountQuantityFeeAmountLookupServiceImpl.csvOutputA007");
        }
        
        // ユーザ共通情報.権限コード != SBI証券本店の場合、FCT030.仲介業者営業員リストを取得
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        List<BrokerCharge> brokerChargeList = null;
        if (!PrivId.HEAD_OFFICE.getId().equals(privId)) {
            InputFct030Dto fct030In = new InputFct030Dto();
            OutputFct030Dto fct030Out = fct030.getData(fct030In);
            brokerChargeList = fct030Out.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_IFAAGENTCODES_NOTEXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
            }
        }
        
        // 3．CSVダウンロードMAX件数を取得し、取得最大件数（閾値）にセットする。
        InputFct038Dto fct038In = new InputFct038Dto();
        fct038In.setScreenId(SCREEN_ID_2);
        fct038In.setUserAuthority(privId);
        OutputFct038Dto fct038Out = fct038.getData(fct038In);
        int csvDownloadNum = fct038Out.getCsvDownloadNum();
        
        IfaByYearAccountQuantityFeeAmountLookupSql004RequestModel sqlReq = new IfaByYearAccountQuantityFeeAmountLookupSql004RequestModel();
        
        String returnCode = "";
        String message = "";
        ErrorLevel errorLevel = null;
        sqlReq.setPrivId(privId);
        sqlReq.setBrokerCode(dtoReq.getBrokerCode());
        sqlReq.setClosingYearMonth(dtoReq.getClosingYearMonth());
        sqlReq.setBrokerName(dtoReq.getBrokerName());
        sqlReq.setBrokerChargeList(brokerChargeList);
        DataList<IfaByYearAccountQuantityFeeAmountLookupSql004ResponseModel> sqlRes = new DataList<IfaByYearAccountQuantityFeeAmountLookupSql004ResponseModel>();
        sqlRes = dao.selectIfaByYearAccountQuantityFeeAmountLookupSql004(sqlReq);
        
        // CSV出力
        DataList<IfaByYearAccountQuantityFeeAmountLookupA007Items> csvDownList = new DataList<IfaByYearAccountQuantityFeeAmountLookupA007Items>();
        
        if (sqlRes == null || ObjectUtils.isEmpty(sqlRes.getDataList()) || sqlRes.size() == 0) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
        }
        
        for (IfaByYearAccountQuantityFeeAmountLookupSql004ResponseModel selSql004 : sqlRes.getDataList()) {
            IfaByYearAccountQuantityFeeAmountLookupA007Items response = new IfaByYearAccountQuantityFeeAmountLookupA007Items();
            BeanUtils.copyProperties(response, selSql004);
            response.setClosingMonth(
                DateTimeFormatter.ofPattern(DATE6).format(YearMonth.parse(
                    selSql004.getClosingMonth(),
                    DateTimeFormatter.ofPattern(DATE6_WITHOUT_SEPARATOR)
            )));
            // 画面.媒介可否区分が、「1（非媒介）」であることをハンチして媒介口座明細情報の全要素の「取引の有無」を"Ｘ"に置き換える
            if (StringUtils.isNotEmpty(dtoReq.getMediateProprietyKBN()) && dtoReq.getMediateProprietyKBN().equals("1")) {
                response.setTradeKbn("Ｘ");
            }
            byYearAccountQuantityFeeAmountLookupCsvList.add(response);
        }
        
        if (byYearAccountQuantityFeeAmountLookupCsvList.size() > csvDownloadNum) {
            returnCode = WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM;
            message = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                new String[] { String.valueOf(csvDownloadNum), String.valueOf(byYearAccountQuantityFeeAmountLookupCsvList.size()), String.valueOf(csvDownloadNum) });
            errorLevel = ErrorLevel.WARNING;
            csvDownList.setDataList(byYearAccountQuantityFeeAmountLookupCsvList.subList(0, csvDownloadNum));
        } else {
            errorLevel = ErrorLevel.SUCCESS;
            csvDownList.setDataList(byYearAccountQuantityFeeAmountLookupCsvList);
        }
        
        CsvOutPutUtil csvOutPutUtil = new IfaByYearAccountQuantityFeeAmountLookupA007CsvOut(complianceService);
        
        String csvFileName =
            csvOutPutUtil.doCreateCsvFile(csvDownList, fwSessionId, IfaCommonUtil.getUserAccount().getUserId(), null);
        
        List<IfaByYearAccountQuantityFeeAmountLookupA007ResponseDto> dtoResList = new ArrayList<>();
        
        dtoRes = IfaCommonUtil.createDataList(dtoResList, errorLevel, returnCode, message);
        
        dtoRes.setTitle(csvFileName);
        
        dtoRes.setTotalSize(csvDownloadNum);
        
        return dtoRes;
        
    }
    
    /** 
     * 仲介業者コードと決算月取得
     * 
     * @param brokerCode リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     *  
     */
    private DataList<IfaByYearAccountQuantityFeeAmountLookupSql002ResponseModel> getBrokerClosingMonth(String brokerCode, String privId, List<BrokerCharge> brokerChargeList) throws Exception {
        
        IfaByYearAccountQuantityFeeAmountLookupSql002RequestModel sqlReq = new IfaByYearAccountQuantityFeeAmountLookupSql002RequestModel();
        sqlReq.setBrokerCode(brokerCode);
        sqlReq.setPrivId(privId);
        sqlReq.setBrokerChargeList(brokerChargeList);
        
        DataList<IfaByYearAccountQuantityFeeAmountLookupSql002ResponseModel> sqlRes = new DataList<IfaByYearAccountQuantityFeeAmountLookupSql002ResponseModel>();
        sqlRes = dao.selectIfaByYearAccountQuantityFeeAmountLookupSql002(sqlReq);
        
        return sqlRes;
    }
    
    /**
     * 仲介業者コード編集
     * 
     * @param brokerCode
     * @return brokerCodeList
     */
    private List<String> splitBrokerCode(String brokerCode) {

        List<String> brokerCodeList = new ArrayList<String>();
        if (!StringUtil.isNullOrEmpty(brokerCode)) {
            brokerCodeList.addAll(Arrays.asList(brokerCode.split(",")));
        }
        return brokerCodeList;
    }
    
    /**
     * プラスマイナス記号
     * 
     * @param 口座数増減 NumberOfAccountsIncreaseDecrease
     * @return ±#####0
     */
    private String formatWithSign(String numberStr) {
        
        BigDecimal number = new BigDecimal(numberStr);
        String plain = number.toPlainString();
        return number.signum() > 0 ? "+" + plain : plain;
    }
    
    /**
     * 最新の適用開始日を取得する
     * 
     * @param 仲介業者媒介可否情報リスト
     * @param 年度別口座数・報酬額リスト.仲介業者コード
     * @param 年度別口座数・報酬額リスト.決算年月
     * @return 最新適用開始日
     */
    private IfaByYearAccountQuantityFeeAmountLookupSql005ResponseModel findLatestByCodeAndDate(
            List<IfaByYearAccountQuantityFeeAmountLookupSql005ResponseModel> list, String brokerCode,
            String closingYearMonth) {
        // 仲介業者コードが一致しており、対象要素の決算年月よりも、古い適用開始日の媒介可否情報が存在している場合(適用開始日の年月＜=決算年月)、その中で最新の適用開始日を取得する
        return list.stream()
                .filter(item -> brokerCode.equals(item.getBrokerCode())
                        && convertDateFormat(item.getEffectiveDate()).compareTo(closingYearMonth) <= 0)
                .max(Comparator.comparing(IfaByYearAccountQuantityFeeAmountLookupSql005ResponseModel::getEffectiveDate))
                .orElse(null);
    }
    
    /**
     * 日付フォーマット用
     * 
     * @param 適用開始日 yyyy-MM-dd HH:mm:ss
     * @return フォーマット後日付 適用開始日 yyyyMM
     */
    
    private String convertDateFormat(String input) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(DATE_SEPARATOR_TIME);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(DATE6_WITHOUT_SEPARATOR);
        LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter);
        return dateTime.format(outputFormatter);
    }
   
    /**
     * 各計算を行う
     * 
     * @param response.媒介手数料（円）
     * @param response.その他受入手数料（円）
     * @return 各計算結果
     */
    
    private IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel pramCalculations(String mediateCom,
            String otherCom) {
        IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel result003Model = new IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel();
        // 媒介手数料（円）
        BigDecimal mediateComF = new BigDecimal(mediateCom);
        // その他受入手数料（円）
        BigDecimal otherComF = new BigDecimal(otherCom);
        // 年度別口座数・報酬額リスト. 計
        String totalResult = mediateComF.add(otherComF).toString();
        result003Model.setTotal(totalResult);
        // 年度別口座数・報酬額リスト. 媒介手数料（千円） 媒介手数料 / 1000
        String mediateComThousandResult = mediateComF.divide(new BigDecimal("1000"), 0, RoundingMode.DOWN).toString();
        result003Model.setMediateComThousand(mediateComThousandResult);
        // 年度別口座数・報酬額リスト. その他受入手数料（千円） その他受入手数料 / 1000
        String otherComFResult = otherComF.divide(new BigDecimal("1000"), 0, RoundingMode.DOWN).toString();
        result003Model.setOtherComThousand(otherComFResult);
        // 年度別口座数・報酬額リスト. 計（千円） 媒介手数料（千円） + その他受入手数料（千円）
        String totalThousandResult = mediateComF.divide(new BigDecimal("1000"), 0, RoundingMode.DOWN)
                .add(otherComF.divide(new BigDecimal("1000"), 0, RoundingMode.DOWN)).toString();
        result003Model.setTotalThousand(totalThousandResult);
        return result003Model;
    }
}
