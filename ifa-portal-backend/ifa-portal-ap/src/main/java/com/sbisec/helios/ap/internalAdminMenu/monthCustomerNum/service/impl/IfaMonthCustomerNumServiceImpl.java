package com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.IfaMonthCustomerNumDao;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.model.IfaMonthCustomerNumSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.model.IfaMonthCustomerNumSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.model.IfaMonthCustomerNumSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.model.IfaMonthCustomerNumSql003ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA004CsvItem;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA004RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA004ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA005CsvItem;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA005RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA005ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.service.IfaMonthCustomerNumService;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.util.IfaBrokerCustomerCsvOutput;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.util.IfaMonthCustomerNumCsvOut;

/**
 * 画面ID：SUB0407_01
 * 画面名：月末口座数
 *
 * @author SBI大連 チョウ
   2025/05/22 新規作成
 */
@Component(value = "cmpIfaMonthCustomerNumService")
public class IfaMonthCustomerNumServiceImpl implements IfaMonthCustomerNumService{

    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMonthCustomerNumServiceImpl.class);
    
    @Autowired
    private MCodeService mcodeService;
    
    @Autowired
    private CodeListService codeListService;
    
    /** コードマスタテーブル.種別 （'99':画面コメント） */
    private static final String M_CODE_CODE_TYPE_SCREEN_COMMENT = "99";
    
    /** コードマスタテーブル.CODE_1 （'01':デフォルト） */
    private static final String M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT = "01";
    
    /** コードマスタテーブル.CODE_2 （'26':月末口座数画面コメント） */
    private static final String M_CODE_CODE_2_SCREEN_COMMENT_MONTH_CUSTOMER_NUM = "26";
    
    /** 表示パターン */
    private static final String DISPLAY_PATTERN_1 = "1";
    /** 区分.年齢層 */
    private static final String AGE_GROUP = "AGE_GROUP";
    /** 区分．法人区分 */
    private static final String CORPORATION_TYPE = "CORPORATION_TYPE";
    /** 区分．性別区分 */
    private static final String SEX_KBN = "SEX_KBN";
    /** 区分．投資方針区分 */
    private static final String INVESTMENT_POLICY_TYPE = "INVESTMENT_POLICY_TYPE";
    /** 区分．資金性格区分（個人） */
    private static final String FUND_CHARACTER_PERSONAL_TYPE = "FUND_CHARACTER_PERSONAL_TYPE";
    /** 区分．資金性格区分（法人） */
    private static final String FUND_CHARACTER_CORPORATION_TYPE = "FUND_CHARACTER_CORPORATION_TYPE";
    /** 区分.主な収入源区分 */
    private static final String MAIN_INCOME = "MAIN_INCOME";
    /** 区分.取引動機区分 */
    private static final String TRADING_MOTIVE = "TRADING_MOTIVE";
    /** 区分.資産運用期間 */
    private static final String ASSET_MANAGEMENT_PERIOD = "ASSET_MANAGEMENT_PERIOD";
    /** 区分.推定年収区分 */
    private static final String ANNUAL_INCOME = "ANNUAL_INCOME";
    /** 区分.金融資産 */
    private static final String FINANCIAL_ASSETS = "FINANCIAL_ASSETS";
    /** 区分.興味のあるお取引 */
    private static final String INTEREST_FINANCIAL_PROD = "INTEREST_FINANCIAL_PROD";
    /** 法人区分-0:個人 */
    private static final String CORPORATION_TYPE_0 = "0";
    /** 法人区分-1:法人 */
    private static final String CORPORATION_TYPE_1 = "1";
    
    /** 固定文言：年 */
    private static final String STRING_YEAR = "年";
    /** 60ヶ月 */
    private static final int MONTH_60_INT = 60;
    
    /** 取得件数上限 */
    private static final Integer MAX_ROWNUM = 5000;
    
    /** 営業員コード：0000*/
    private static final String EMP_CD = "0000";
    
    /** 月末口座数CSV(A004)-画面ID */
    private static final String SCREEN_ID_1 = "SUB0407-01";
    /** 仲介業者顧客CSV(A005)-画面ID */
    private static final String SCREEN_ID_2 = "SUB0407-02";

    /** エラー.{0}は{1}以内を正しく入力して下さい。 */
    private static final String ERRORS_DATE_RANGE = "errors.dateRange";
    /** 項目名:対象年月 */
    private static final String ITEM_NAME_DATE_RANGE = "対象年月";
    /** 対象年月範囲 */
    private static final String DATE_RANGE_LIMIT_TEXT = "5年";
    
    /** エラー.参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    /** エラー.検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";
    
    /**  ワーニング.検索結果が5000件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 */
    private static final String WARNINGS_DATALIST_OVERMAXROWNUM = "warnings.dataList.overMaxRownum";
    
    /**  ワーニング.検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のcsv出力を行います。 */
    private static final String WARNINGS_DATALIST_CSV_OVERMAXROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    /** ワーニング.{0}件のcsv出力を行います。 */
    private static final String WARNINGS_DATALIST_CSV_OUTPUT = "warnings.dataList.csv.output";
    
    @Autowired
    private IfaMonthCustomerNumDao dao;
    
    @Autowired
    private ComplianceService complianceService;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private Fct038 fct038;
    
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMonthCustomerNumA001RequestDto
     * Dto レスポンス：IfaMonthCustomerNumA001ResponseDto
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMonthCustomerNumA001ResponseDto> initializeA001(IfaMonthCustomerNumA001RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaMonthCustomerNumA001ResponseDto> dtoRes = new DataList<IfaMonthCustomerNumA001ResponseDto>();
        List<IfaMonthCustomerNumA001ResponseDto> dtoResList = dtoRes.getDataList();
        IfaMonthCustomerNumA001ResponseDto a001Res = new IfaMonthCustomerNumA001ResponseDto();
        dtoResList.add(a001Res);
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMonthCustomerNumServiceImpl.initializeA001");
        }
        
        /* =============================================================================== */
        /* 1. 月末口座数コメントを取得する                                                         */
        /* =============================================================================== */
        List<MCode> selSqlA001Res = mcodeService.getMCodeList(
                M_CODE_CODE_TYPE_SCREEN_COMMENT,
                M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT,
                M_CODE_CODE_2_SCREEN_COMMENT_MONTH_CUSTOMER_NUM);
        
        if (selSqlA001Res != null && selSqlA001Res.size() > 0) {
            a001Res.setComment(selSqlA001Res.get(0).getName());
        }
        dtoRes = IfaCommonUtil.createDataList(
                dtoResList, 
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING);
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaMonthCustomerNumA002RequestDto
     * Dto レスポンス：IfaMonthCustomerNumA002ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMonthCustomerNumA002ResponseDto> displayA002(IfaMonthCustomerNumA002RequestDto dtoReq)
            throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMonthCustomerNumServiceImpl.displayA002");
        }
        
        DataList<IfaMonthCustomerNumA002ResponseDto> dtoRes = new DataList<IfaMonthCustomerNumA002ResponseDto>();
        List<IfaMonthCustomerNumA002ResponseDto> resList = new ArrayList<IfaMonthCustomerNumA002ResponseDto>();

        /* =============================================================================== */
        /* 1. 仲介業者月末顧客口座属性テーブルから直近の基準年月を取得（SQL004）                          */
        /* =============================================================================== */
        String baseDateYm = "";
        String sql004Res = dao.selectIfaMonthCustomerNumSql004();
        if (StringUtil.isNullOrEmpty(sql004Res)) {
            /* 業務的にこういう場合がないのですが、システムエラーを発生しないように追加*/
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
            baseDateYm = currentDate.format(formatter);
        } else {
            baseDateYm = sql004Res;
        }
        // 基準年月をLocalDateに変換（年月の最初の日付）
        LocalDate baseDate = LocalDate.parse(baseDateYm + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 60ヶ月前の日付を計算
        LocalDate date60MonthsAgo = baseDate.minusMonths(MONTH_60_INT);
        
        // 画面からの対象年月範囲
        String targetYmFrom = dtoReq.getTargetDateYmFrom().replace("/", "");
        String targetYmTo = dtoReq.getTargetDateYmTo().replace("/", "");
        // 画面の年月範囲もLocalDateに変換
        LocalDate targetFromDate = LocalDate.parse(targetYmFrom + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate targetToDate = LocalDate.parse(targetYmTo + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));

        /*  画面.対象年月From～Toが5年以上前を指定していないかチェックを行う                                 */
        /*    （取得した基準年月－60ヶ月）≧画面.対象年月Fromまたは画面.対象年月Toの場合：メッセージを表示し処理終了  */
        /*    （取得した基準年月－60ヶ月）＜画面.対象年月Fromおよび画面.対象年月Toの場合：次の処理へ            */
        if (!date60MonthsAgo.isBefore(targetFromDate) || !date60MonthsAgo.isBefore(targetToDate)) {
            // エラーメッセージを返す
            dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_DATE_RANGE,
                    IfaCommonUtil.getMessage(ERRORS_DATE_RANGE,
                            new String[] { ITEM_NAME_DATE_RANGE, DATE_RANGE_LIMIT_TEXT }));
            return dtoRes;
        }

        /* =============================================================================== */
        /* 2. ユーザ共通情報.権限コードを取得する                                                   */
        /* =============================================================================== */
        String privId = IfaCommonUtil.getUserAccount().getPrivId();
        List<BrokerCharge> brokerChargeList = new ArrayList<BrokerCharge>();
        
        /* ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する   */
        if (!StringUtils.equals(privId, PrivId.HEAD_OFFICE.getId())) {
            // FCT030.仲介業者営業員リストを取得
            InputFct030Dto fct030InputDto = new InputFct030Dto();
            OutputFct030Dto fct030OutputDto = fct030.getData(fct030InputDto);
            
            // FCT030.仲介業者営業員リストの件数が0件の場合
            if (fct030OutputDto == null || fct030OutputDto.getBrokerChargeList().size() < 1) {
                // エラーメッセージを返す
                dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST));
                return dtoRes;
            }
            brokerChargeList = fct030OutputDto.getBrokerChargeList();
        }
        
        /* =============================================================================== */
        /* 3. FCT030.仲介業者営業員リストの営業員コードは削除し、仲介業者コードを重複削除する                 */
        /* =============================================================================== */
        List<BrokerCharge> newBrokerChargeList = removeEmpCodeAndDeduplicate(brokerChargeList);
        
        /* =============================================================================== */
        /* 4. 月末口座数リストを取得する                                                          */
        /* =============================================================================== */
        // SQL002のリクエスト値を設定
        IfaMonthCustomerNumSql002RequestModel selSql002Req = new IfaMonthCustomerNumSql002RequestModel();
        BeanUtils.copyProperties(selSql002Req, dtoReq);
        selSql002Req.setBrokerChargeList(newBrokerChargeList);
        // 最大取得件数に5,000件をセット
        selSql002Req.setMaxRowNum(MAX_ROWNUM);
        
        // 月末口座数リストを取得（SQL002）
        DataList<IfaMonthCustomerNumSql002ResponseModel> selSql002ResList = dao.selectIfaMonthCustomerNumSql002(selSql002Req);
        List<IfaMonthCustomerNumSql002ResponseModel> sql002Res = new ArrayList<IfaMonthCustomerNumSql002ResponseModel>();
        
        String returnCode = "";
        String message = "";
        ErrorLevel errorLevel = null;
        
        // 月末口座数リストの件数が0件の場合
        if (ObjectUtils.isEmpty(selSql002ResList) || selSql002ResList.size() < 1) {
            // メッセージを返す
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
            return dtoRes;
        } else if (selSql002ResList.get(0).getTotalRow() > MAX_ROWNUM) {
            // SQL002.総件数が出力件数上限（5000件）を超過している場合、メッセージを表示し、5000件までの検索結果を明細に表示する。
            returnCode = WARNINGS_DATALIST_OVERMAXROWNUM;
            message = IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVERMAXROWNUM,
                    new String[] { Integer.toString(MAX_ROWNUM), Integer.toString(selSql002ResList.get(0).getTotalRow()) });
            errorLevel = ErrorLevel.WARNING;
        } else {
            errorLevel = ErrorLevel.SUCCESS;
        }
        sql002Res = selSql002ResList.getDataList();
        for (IfaMonthCustomerNumSql002ResponseModel sql002ResData : sql002Res) {
            IfaMonthCustomerNumA002ResponseDto a002Res = new IfaMonthCustomerNumA002ResponseDto();
            BeanUtils.copyProperties(a002Res, sql002ResData);
            resList.add(a002Res);
        }
        
        dtoRes = IfaCommonUtil.createDataList(resList, errorLevel, returnCode, message);
        return dtoRes;
    }
    
    /**
     * アクションID：A004
     * アクション名：月末口座数CSV出力
     * Dto リクエスト：IfaMonthCustomerNumA004RequestDto
     * Dto レスポンス：IfaMonthCustomerNumA004ResponseDto
     *
     * @param dtoReq リクエスト
     * @param fwSessionId フレームワークセッションID
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMonthCustomerNumA004ResponseDto> csvOutputA004(IfaMonthCustomerNumA004RequestDto dtoReq, String fwSessionId)
    throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMonthCustomerNumServiceImpl.csvOutputA004");
        }
        
        DataList<IfaMonthCustomerNumA004ResponseDto> dtoRes = new DataList<IfaMonthCustomerNumA004ResponseDto>();
        List<IfaMonthCustomerNumA004ResponseDto> resList = new ArrayList<IfaMonthCustomerNumA004ResponseDto>();
        
        /* =============================================================================== */
        /* 1. 仲介業者月末顧客口座属性テーブルから直近の基準年月を取得（SQL001）                          */
        /* =============================================================================== */
        String baseDateYm = "";
        String sql004Res = dao.selectIfaMonthCustomerNumSql004();
        if (StringUtil.isNullOrEmpty(sql004Res)) {
            /* 業務的にこういう場合がないのですが、システムエラーを発生しないように追加*/
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
            baseDateYm = currentDate.format(formatter);
        } else {
            baseDateYm = sql004Res;
        }
        // 基準年月をLocalDateに変換（年月の最初の日付）
        LocalDate baseDate = LocalDate.parse(baseDateYm + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 60ヶ月前の日付を計算
        LocalDate date60MonthsAgo = baseDate.minusMonths(MONTH_60_INT);
        
        // 画面からの対象年月範囲
        String targetYmFrom = dtoReq.getTargetDateYmFrom().replace("/", "");
        String targetYmTo = dtoReq.getTargetDateYmTo().replace("/", "");
        // 画面の年月範囲もLocalDateに変換
        LocalDate targetFromDate = LocalDate.parse(targetYmFrom + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate targetToDate = LocalDate.parse(targetYmTo + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));

        /*  画面.対象年月From～Toが5年以上前を指定していないかチェックを行う                                 */
        /*    （取得した基準年月－60ヶ月）≧画面.対象年月Fromまたは画面.対象年月Toの場合：メッセージを表示し処理終了  */
        /*    （取得した基準年月－60ヶ月）＜画面.対象年月Fromおよび画面.対象年月Toの場合：次の処理へ            */
        if (!date60MonthsAgo.isBefore(targetFromDate) || !date60MonthsAgo.isBefore(targetToDate)) {
            // エラーメッセージを返す
            dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_DATE_RANGE,
                    IfaCommonUtil.getMessage(ERRORS_DATE_RANGE,
                            new String[] { ITEM_NAME_DATE_RANGE, DATE_RANGE_LIMIT_TEXT }));
            return dtoRes;
        }
        
        /* =============================================================================== */
        /* 2. ユーザ共通情報.権限コードを取得する                                                   */
        /* =============================================================================== */
        String privId = IfaCommonUtil.getUserAccount().getPrivId();
        List<BrokerCharge> brokerChargeList = new ArrayList<BrokerCharge>();
        
        /* ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する   */
        if (!StringUtils.equals(privId, PrivId.HEAD_OFFICE.getId())) {
            // FCT030.仲介業者営業員リストを取得
            InputFct030Dto fct030InputDto = new InputFct030Dto();
            OutputFct030Dto fct030OutputDto = fct030.getData(fct030InputDto);
            
            // FCT030.仲介業者営業員リストの件数が0件の場合
            if (fct030OutputDto == null || fct030OutputDto.getBrokerChargeList().size() < 1) {
                // エラーメッセージを返す
                return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST));
            }
            brokerChargeList = fct030OutputDto.getBrokerChargeList();
        }
        
        /* =============================================================================== */
        /* 3. FCT030.仲介業者営業員リストの営業員コードは削除し、仲介業者コードを重複削除する                 */
        /* =============================================================================== */
        List<BrokerCharge> newBrokerChargeList = removeEmpCodeAndDeduplicate(brokerChargeList);
        
        /* =============================================================================== */
        /* 4. CSVダウンロードMAX件数を取得し、取得最大件数（閾値）にセットする                              */
        /* =============================================================================== */
        InputFct038Dto fct038In = new InputFct038Dto();
        fct038In.setScreenId(SCREEN_ID_1);
        fct038In.setUserAuthority(privId);
        OutputFct038Dto fct038Out = fct038.getData(fct038In);
        int csvDownloadNum = fct038Out.getCsvDownloadNum();
        
        /* =============================================================================== */
        /* 3. 月末口座数リストを取得する                                                          */
        /* =============================================================================== */
        // SQL002のリクエスト値を設定
        IfaMonthCustomerNumSql002RequestModel selSql002Req = new IfaMonthCustomerNumSql002RequestModel();
        BeanUtils.copyProperties(selSql002Req, dtoReq);
        selSql002Req.setBrokerChargeList(newBrokerChargeList);
        // CSV最大取得件数に50000件をセット
        selSql002Req.setMaxRowNum(csvDownloadNum);
        
        // 月末口座数リストを取得（SQL002）
        DataList<IfaMonthCustomerNumSql002ResponseModel> selSql002ResList = dao.selectIfaMonthCustomerNumSql002(selSql002Req);
        List<IfaMonthCustomerNumSql002ResponseModel> sql002Res = new ArrayList<IfaMonthCustomerNumSql002ResponseModel>();

        String returnCode = "";
        String message = "";
        ErrorLevel errorLevel = null;
        
        // 月末口座数リストの件数が0件の場合
        if (ObjectUtils.isEmpty(selSql002ResList) || selSql002ResList.size() < 1) {
            // メッセージを返す
            // SQL002.総件数が0件の場合、エラーメッセージを表示し、処理終了。
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
        } else if (selSql002ResList.get(0).getTotalRow() > csvDownloadNum) {
            // SQL002.総件数がCSVダウンロードMAX件数を超える場合、メッセージを表示し、CSVダウンロードMAX件数までの検索結果をCSV出力する。
            returnCode = WARNINGS_DATALIST_CSV_OVERMAXROWNUM;
            message = IfaCommonUtil.getMessage(WARNINGS_DATALIST_CSV_OVERMAXROWNUM,
                    new String[] { Integer.toString(csvDownloadNum),
                            Integer.toString(selSql002ResList.get(0).getTotalRow()), Integer.toString(csvDownloadNum) });
            errorLevel = ErrorLevel.WARNING;
        } else {
             //正常終了の場合のレスポンス
             returnCode = WARNINGS_DATALIST_CSV_OUTPUT;
             errorLevel = ErrorLevel.WARNING;
             message = IfaCommonUtil.getMessage(
                     WARNINGS_DATALIST_CSV_OUTPUT,
                     new String[] { Integer.toString(selSql002ResList.get(0).getTotalRow()) }
             );
        }
        sql002Res = selSql002ResList.getDataList();
        List<IfaMonthCustomerNumA004CsvItem> csvItemList = new ArrayList<>();
        for (IfaMonthCustomerNumSql002ResponseModel sql002ResData : sql002Res) {
            IfaMonthCustomerNumA004CsvItem csvItem = new IfaMonthCustomerNumA004CsvItem();
            BeanUtils.copyProperties(csvItem, sql002ResData);
            csvItemList.add(csvItem);
        }
        
        // CSV出力
        DataList<IfaMonthCustomerNumA004CsvItem> csvDownList = new DataList<>();
        csvDownList.setDataList(csvItemList);
        CsvOutPutUtil csvOutPutUtil = new IfaMonthCustomerNumCsvOut(complianceService);
        String csvFileName = csvOutPutUtil.doCreateCsvFile(csvDownList, fwSessionId,
                IfaCommonUtil.getUserAccount().getUserId(), null);

        dtoRes = IfaCommonUtil.createDataList(resList, errorLevel, returnCode, message);
        dtoRes.setTitle(csvFileName);
        dtoRes.setTotalSize(selSql002ResList.get(0).getTotalRow());

        return dtoRes;
    }
    
    /**
     * アクションID：A005
     * アクション名：仲介業者顧客CSV出力
     * Dto リクエスト：IfaMonthCustomerNumA005RequestDto
     * Dto レスポンス：IfaMonthCustomerNumA005ResponseDto
     *
     * @param dtoReq リクエスト
     * @param fwSessionId フレームワークセッションID
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMonthCustomerNumA005ResponseDto> csvOutputA005(IfaMonthCustomerNumA005RequestDto dtoReq, String fwSessionId)
    throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMonthCustomerNumServiceImpl.csvOutputA005");
        }
        
        DataList<IfaMonthCustomerNumA005ResponseDto> dtoRes = new DataList<IfaMonthCustomerNumA005ResponseDto>();
        List<IfaMonthCustomerNumA005ResponseDto> resList = new ArrayList<IfaMonthCustomerNumA005ResponseDto>();
        
        /* =============================================================================== */
        /* 1. ユーザ共通情報.権限コードを取得する                                                   */
        /* =============================================================================== */
        String privId = IfaCommonUtil.getUserAccount().getPrivId();
        List<BrokerCharge> brokerChargeList = new ArrayList<BrokerCharge>();
        
        /* ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する   */
        if (!StringUtils.equals(privId, PrivId.HEAD_OFFICE.getId())) {
            // FCT030.仲介業者営業員リストを取得
            InputFct030Dto fct030InputDto = new InputFct030Dto();
            OutputFct030Dto fct030OutputDto = fct030.getData(fct030InputDto);
            
            // FCT030.仲介業者営業員リストの件数が0件の場合
            if (fct030OutputDto == null || fct030OutputDto.getBrokerChargeList().size() < 1) {
                // エラーメッセージを返す
                return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST));
            }
            brokerChargeList = fct030OutputDto.getBrokerChargeList();
        }
        
        /* =============================================================================== */
        /* 2. FCT030.仲介業者営業員リストの営業員コードは削除し、仲介業者コードを重複削除する                 */
        /* =============================================================================== */
        List<BrokerCharge> newBrokerChargeList = removeEmpCodeAndDeduplicate(brokerChargeList);
        
        /* =============================================================================== */
        /* 3. CSVダウンロードMAX件数を取得する                                                     */
        /* =============================================================================== */
        InputFct038Dto fct038In = new InputFct038Dto();
        fct038In.setScreenId(SCREEN_ID_2);
        fct038In.setUserAuthority(privId);
        OutputFct038Dto fct038Out = fct038.getData(fct038In);
        int csvDownloadNum = fct038Out.getCsvDownloadNum();
        
        /* =============================================================================== */
        /* 4. 仲介業者顧客リストを取得する                                                        */
        /* =============================================================================== */
        // SQL003のリクエスト値を設定
        IfaMonthCustomerNumSql003RequestModel selSql003Req = new IfaMonthCustomerNumSql003RequestModel();
        BeanUtils.copyProperties(selSql003Req, dtoReq);
        selSql003Req.setBrokerChargeList(newBrokerChargeList);
        // CSV最大取得件数に1,000,000件をセット
        selSql003Req.setMaxRowNum(csvDownloadNum);
        
        // 仲介業者顧客リストを取得する（SQL003）
        DataList<IfaMonthCustomerNumSql003ResponseModel> selSql003ResList = dao.selectIfaMonthCustomerNumSql003(selSql003Req);
        List<IfaMonthCustomerNumSql003ResponseModel> sql003Res = new ArrayList<IfaMonthCustomerNumSql003ResponseModel>();
        
        String returnCode = "";
        String message = "";
        ErrorLevel errorLevel = null;
        
        // 仲介業者顧客リストの件数が0件の場合
        if (ObjectUtils.isEmpty(selSql003ResList) || selSql003ResList.size() < 1) {
            // メッセージを返す
            // SQL003.総件数が0件の場合、エラーメッセージを表示し、処理終了。
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
        } else if (selSql003ResList.get(0).getTotalRow() > csvDownloadNum) {
            // SQL003.仲介業者顧客リストの件数がCSVダウンロードMAX件数より大きい場合、メッセージを表示し、CSVダウンロードMAX件数までの検索結果をCSV出力する。
            returnCode = WARNINGS_DATALIST_CSV_OVERMAXROWNUM;
            message = IfaCommonUtil.getMessage(WARNINGS_DATALIST_CSV_OVERMAXROWNUM,
                    new String[] { Integer.toString(csvDownloadNum),
                            Integer.toString(selSql003ResList.get(0).getTotalRow()), Integer.toString(csvDownloadNum) });
            errorLevel = ErrorLevel.WARNING;
        } else {
             //正常終了の場合のレスポンス
            returnCode = WARNINGS_DATALIST_CSV_OUTPUT;
            errorLevel = ErrorLevel.WARNING;
            message = IfaCommonUtil.getMessage(
                    WARNINGS_DATALIST_CSV_OUTPUT,
                    new String[] { Integer.toString(selSql003ResList.get(0).getTotalRow()) }
            );
        }
        sql003Res = selSql003ResList.getDataList();
        List<IfaMonthCustomerNumA005CsvItem> csvItemList = new ArrayList<>();
        for (IfaMonthCustomerNumSql003ResponseModel sql003ResData : sql003Res) {
            IfaMonthCustomerNumSql003ResponseModel sql003ResList = new IfaMonthCustomerNumSql003ResponseModel();
            sql003ResList = editBrokerCustomerCsvData(sql003ResData);
            IfaMonthCustomerNumA005CsvItem csvItem = new IfaMonthCustomerNumA005CsvItem();
            BeanUtils.copyProperties(csvItem, sql003ResList);
            csvItemList.add(csvItem);
        }
        
        // CSV出力
        DataList<IfaMonthCustomerNumA005CsvItem> csvDownList = new DataList<>();
        csvDownList.setDataList(csvItemList);
        CsvOutPutUtil csvOutPutUtil = new IfaBrokerCustomerCsvOutput(complianceService);
        String csvFileName = csvOutPutUtil.doCreateCsvFile(csvDownList, fwSessionId,
                IfaCommonUtil.getUserAccount().getUserId(), null);

        dtoRes = IfaCommonUtil.createDataList(resList, errorLevel, returnCode, message);
        dtoRes.setTitle(csvFileName);
        dtoRes.setTotalSize(selSql003ResList.get(0).getTotalRow());

        return dtoRes;

    }
    
    /**
     * FCT030.仲介業者営業員リストの営業員コードは削除し、仲介業者コードを重複削除する
     * 
     * @param FCT030.仲介業者営業員リスト リクエスト
     * @return 仲介業者コードリスト
     * @exception exception システムエラー
     */
    private static List<BrokerCharge> removeEmpCodeAndDeduplicate(List<BrokerCharge> list) 
    throws Exception {

        return list.stream()
                .collect(Collectors.toMap(
                    BrokerCharge::getBrokerCode,
                    bc -> {
                        BrokerCharge newBrokerCode = new BrokerCharge();
                        newBrokerCode.setBrokerCode(bc.getBrokerCode());
                        newBrokerCode.setEmpCode(null);
                        return newBrokerCode;
                    },
                    (existing, replacement) -> existing
                ))
                .values()
                .stream()
                .collect(Collectors.toList());
    }
    
    /**
     * 仲介業者顧客CSVデータ編集
     * 
     * @param 仲介業者顧客CSVデータ
     * @return 仲介業者顧客情報リスト
     * @exception exception システムエラー
     */
    private IfaMonthCustomerNumSql003ResponseModel editBrokerCustomerCsvData(IfaMonthCustomerNumSql003ResponseModel sql003ResModel) 
    throws Exception {

        IfaMonthCustomerNumSql003ResponseModel sql003Res = new IfaMonthCustomerNumSql003ResponseModel();
        // 対象年月
        sql003Res.setBaseDateYm(sql003ResModel.getBaseDateYm());
        // 仲介業者名
        sql003Res.setBrokerName(sql003ResModel.getBrokerName());
        // 仲介業者コード
        sql003Res.setBrokerCode(sql003ResModel.getBrokerCode());
        // 扱者コード
        sql003Res.setDealerNumber(sql003ResModel.getDealerNumber());
        // 営業員コード
        sql003Res.setIntermediaryEmpCd(sql003ResModel.getIntermediaryEmpCd());
        // 営業員名
        if (EMP_CD.equals(sql003ResModel.getIntermediaryEmpCd())) {
            // 項目「営業員コード」＝"0000"の場合、設定無し
            sql003Res.setBrokerChargeName(null);
        } else {
            sql003Res.setBrokerChargeName(sql003ResModel.getBrokerChargeName());
        }
        // 部店
        sql003Res.setButenCode(sql003ResModel.getButenCode());
        // 口座番号
        sql003Res.setAccountNumber(sql003ResModel.getAccountNumber());
        // 取引コース
        sql003Res.setCustomerAttributeName(sql003ResModel.getCustomerAttributeName());
        // 顧客名（漢字）
        sql003Res.setNameKanji(sql003ResModel.getNameKanji());
        // 顧客名（カナ）
        sql003Res.setNameKana(sql003ResModel.getNameKana());
        // 年齢
        sql003Res.setAge(StringUtil.isNullOrEmpty(sql003ResModel.getAge()) ? null :sql003ResModel.getAge().trim());
        // 年代:区分.年齢層(@表示パターン：1)
        sql003Res.setAgeGroup(codeListService.getValue(AGE_GROUP, sql003ResModel.getAgeGroup(), DISPLAY_PATTERN_1));
        // 個人/法人:区分.法人区分(@表示パターン：1)
        sql003Res.setCorporationKbn(codeListService.getValue(CORPORATION_TYPE, sql003ResModel.getCorporationKbn(), DISPLAY_PATTERN_1));
        // 項目「個人/法人」＝個人の場合
        if(CORPORATION_TYPE_0.equals(sql003ResModel.getCorporationKbn())) {
            // 性別：区分.性別区分 1："男"　2："女"
            sql003Res.setSexKbn((codeListService.getValue(SEX_KBN, sql003ResModel.getSexKbn(), DISPLAY_PATTERN_1)));
        // 項目「個人/法人」＝法人の場合
        } else if (CORPORATION_TYPE_1.equals(sql003ResModel.getCorporationKbn())) {
            // 設定なし
            sql003Res.setSexKbn(null);
        }
        // Cランク
        sql003Res.setTcCompRank(sql003ResModel.getTcCompRank());
        // 支店コード
        sql003Res.setBrokerBranchCode(sql003ResModel.getBrokerBranchCode());
        // 支店名
        sql003Res.setBrokerBranchName(sql003ResModel.getBrokerBranchName());
        // 住所
        sql003Res.setAddressKanji1(sql003ResModel.getAddressKanji1());
        // 口座開設日
        sql003Res.setOpenAcctDate(sql003ResModel.getOpenAcctDate());
        // 電話番号
        sql003Res.setPhoneNumber(StringUtil.isNullOrEmpty(sql003ResModel.getPhoneNumber()) ? null :sql003ResModel.getPhoneNumber().trim());
        // 投資方針:区分.投資方針区分(@表示パターン：1)
        sql003Res.setQaInvestmentPlan(codeListService.getValue(INVESTMENT_POLICY_TYPE, sql003ResModel.getQaInvestmentPlan(), DISPLAY_PATTERN_1));
        // 項目「個人/法人」＝個人の場合
        if(CORPORATION_TYPE_0.equals(sql003ResModel.getCorporationKbn())) {
            // 資金の性格:区分.資金性格区分（個人）(@表示パターン：1)
            sql003Res.setQaFundCharacter(codeListService.getValue(FUND_CHARACTER_PERSONAL_TYPE, sql003ResModel.getQaFundCharacter(), DISPLAY_PATTERN_1));
            // 主な収入源:区分.主な収入源区分(@表示パターン：1)
            sql003Res.setQaIncomeForm(codeListService.getValue(MAIN_INCOME, sql003ResModel.getQaIncomeForm(), DISPLAY_PATTERN_1));
        // 項目「個人/法人」＝法人の場合
        } else if (CORPORATION_TYPE_1.equals(sql003ResModel.getCorporationKbn())) {
            // 区分.資金性格区分（法人）(@表示パターン：1)
            sql003Res.setQaFundCharacter(codeListService.getValue(FUND_CHARACTER_CORPORATION_TYPE, 
                    StringUtil.isNullOrEmpty(sql003ResModel.getQaFundCharacter()) ? null :sql003ResModel.getQaFundCharacter().trim(), DISPLAY_PATTERN_1));
            // 主な収入源:設定なし
            sql003Res.setQaIncomeForm(null);
        }
        // 取引の動機:区分.取引動機区分(@表示パターン：1)
        sql003Res.setQaTradingMotive(codeListService.getValue(TRADING_MOTIVE, sql003ResModel.getQaTradingMotive(), DISPLAY_PATTERN_1));
        // 資産運用期間:区分.資産運用期間(@表示パターン：1)
        sql003Res.setQaEmploymentPeriod(codeListService.getValue(ASSET_MANAGEMENT_PERIOD, sql003ResModel.getQaEmploymentPeriod(), DISPLAY_PATTERN_1));
        // 年収:区分.推定年収区分(@表示パターン：1)
        sql003Res.setQaAnnualIncome(codeListService.getValue(ANNUAL_INCOME, sql003ResModel.getQaAnnualIncome(), DISPLAY_PATTERN_1));
        // 金融資産:区分.金融資産(@表示パターン：1)
        sql003Res.setQaFinancialAssets(codeListService.getValue(FINANCIAL_ASSETS, sql003ResModel.getQaFinancialAssets(), DISPLAY_PATTERN_1));
        // 興味ある取引:区分.興味のあるお取引(@表示パターン：1)
        sql003Res.setQaInterestedTrading(codeListService.getValue(INTEREST_FINANCIAL_PROD, sql003ResModel.getQaInterestedTrading(), DISPLAY_PATTERN_1));
        // 投資経験（株式現物）
        sql003Res.setExpStock(StringUtil.isNullOrEmpty(sql003ResModel.getExpStock()) ? null : sql003ResModel.getExpStock().trim() + STRING_YEAR );
        // 投資経験（債券）
        sql003Res.setExpDebenture(StringUtil.isNullOrEmpty(sql003ResModel.getExpDebenture()) ? null : sql003ResModel.getExpDebenture().trim() + STRING_YEAR);
        // 投資経験（転換社債）
        sql003Res.setExpCb(StringUtil.isNullOrEmpty(sql003ResModel.getExpCb()) ? null : sql003ResModel.getExpCb().trim() + STRING_YEAR);
        // 投資経験（信用）
        sql003Res.setExpMargin(StringUtil.isNullOrEmpty(sql003ResModel.getExpMargin()) ? null : sql003ResModel.getExpMargin().trim() + STRING_YEAR);
        // 投資経験（ワラント）
        sql003Res.setExpWarrant(StringUtil.isNullOrEmpty(sql003ResModel.getExpWarrant()) ? null : sql003ResModel.getExpWarrant().trim() + STRING_YEAR);
        // 投資経験（先物OP）
        sql003Res.setExpFutureop(StringUtil.isNullOrEmpty(sql003ResModel.getExpFutureop()) ? null : sql003ResModel.getExpFutureop().trim() + STRING_YEAR);
        // 投資経験（貯蓄型投信）
        sql003Res.setExpSavedtypefund(StringUtil.isNullOrEmpty(sql003ResModel.getExpSavedtypefund()) ? null : sql003ResModel.getExpSavedtypefund().trim() + STRING_YEAR);
        // 投資経験（その他投信）
        sql003Res.setExpOtherfund(StringUtil.isNullOrEmpty(sql003ResModel.getExpOtherfund()) ? null : sql003ResModel.getExpOtherfund().trim() + STRING_YEAR);
        // 投資経験（外国証券）
        sql003Res.setExpForeign(StringUtil.isNullOrEmpty(sql003ResModel.getExpForeign()) ? null : sql003ResModel.getExpForeign().trim() + STRING_YEAR);

        return sql003Res;
    }
}
