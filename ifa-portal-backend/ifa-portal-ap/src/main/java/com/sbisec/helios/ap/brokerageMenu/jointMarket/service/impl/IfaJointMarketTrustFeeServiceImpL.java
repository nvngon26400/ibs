package com.sbisec.helios.ap.brokerageMenu.jointMarket.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.IfaJointMarketTrustFeeDao;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model.IfaJointMarketTrustFeeSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model.IfaJointMarketTrustFeeSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA002TrustFeeDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA004aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeCsvItems;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.service.IfaJointMarketTrustFeeService;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.util.IfaJointMarketTrustFeeCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 画面ID：SUB0208_02-01
 * 画面名：共同店舗 信託報酬
 *
 * @author SBI(大連) 董
 2024/12/20 新規作成
 */
@Component(value = "cmpIfaJointMarketTrustFeeService")
public class IfaJointMarketTrustFeeServiceImpL implements IfaJointMarketTrustFeeService {
    
    /** ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaJointMarketTrustFeeServiceImpL.class);
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";
    
    /** "検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。" */
    private static final String WARNINGS_DATA_LIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";
    
    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のCSV出力を行います。 */
    private static final String WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    // --------------------------------
    // 表示明細/CSV出力項目パターンNo
    // --------------------------------   
    /** パターンNo1.日次-明細 */
    private static final String PATTERN_NO1_DAILY_DETAIL = "1";
    
    /** パターンNo2.日次-顧客・商品分類・通貨毎 */
    private static final String PATTERN_NO2_DAILY_CUSTOMER = "2";
    
    /** パターンNo3.日次-通貨毎 */
    private static final String PATTERN_NO3_DAILY_CURRENCY = "3";
    
    /** パターンNo4.月次累計-明細 */
    private static final String PATTERN_NO4_MONTHLY_DETAIL = "4";
    
    /** パターンNo5.月次累計-顧客・商品分類・通貨毎 */
    private static final String PATTERN_NO5_MONTHLY_CUSTOMER = "5";
    
    /** パターンNo6.月次累計-通貨毎 */
    private static final String PATTERN_NO6_MONTHLY_CURRENCY = "6";
    
    // --------------------------------
    // 条件
    // --------------------------------   
    /** 集計単位(日次/月次累計).日次 */
    private static final String DAILY_MONTHLY_COUNTING_UNIT_DAILY = "0";
    
    /** 集計単位(日次/月次累計).月次累計 */
    private static final String DAILY_MONTHLY_COUNTING_UNIT_MONTHLY = "1";
    
    /** 集計単位(明細/顧客/通貨毎).明細 */
    private static final String DETAIL_CUSTOMER_CURRENCY_COUNTING_UNIT_DETAIL = "0";
    
    /** 集計単位(明細/顧客/通貨毎).顧客・商品分類・通貨毎 */
    private static final String DETAIL_CUSTOMER_CURRENCY_COUNTING_UNIT_CUSTOMER = "1";
    
    /** 集計単位(明細/顧客/通貨毎).通貨毎 */
    private static final String DETAIL_CUSTOMER_CURRENCY_COUNTING_UNIT_CURRENCY = "2";
    
    private static final Map<String, String> PATTERN = new HashMap<>();
    
    static {
        // 日次
        PATTERN.put(DAILY_MONTHLY_COUNTING_UNIT_DAILY + DETAIL_CUSTOMER_CURRENCY_COUNTING_UNIT_DETAIL,
                PATTERN_NO1_DAILY_DETAIL);
        PATTERN.put(DAILY_MONTHLY_COUNTING_UNIT_DAILY + DETAIL_CUSTOMER_CURRENCY_COUNTING_UNIT_CUSTOMER,
                PATTERN_NO2_DAILY_CUSTOMER);
        PATTERN.put(DAILY_MONTHLY_COUNTING_UNIT_DAILY + DETAIL_CUSTOMER_CURRENCY_COUNTING_UNIT_CURRENCY,
                PATTERN_NO3_DAILY_CURRENCY);
        
        // 月次累計
        PATTERN.put(DAILY_MONTHLY_COUNTING_UNIT_MONTHLY + DETAIL_CUSTOMER_CURRENCY_COUNTING_UNIT_DETAIL,
                PATTERN_NO4_MONTHLY_DETAIL);
        PATTERN.put(DAILY_MONTHLY_COUNTING_UNIT_MONTHLY + DETAIL_CUSTOMER_CURRENCY_COUNTING_UNIT_CUSTOMER,
                PATTERN_NO5_MONTHLY_CUSTOMER);
        PATTERN.put(DAILY_MONTHLY_COUNTING_UNIT_MONTHLY + DETAIL_CUSTOMER_CURRENCY_COUNTING_UNIT_CURRENCY,
                PATTERN_NO6_MONTHLY_CURRENCY);
    }
    // --------------------------------
    // CSVファイル名
    // --------------------------------   
    /** 画面名 */
    private static final String CSV_FILENAME_SCREEN_NAME = "共同店舗信託報酬";
    
    /** 日次 */
    private static final String CSV_FILENAME_DAILY = "日次";
    
    /** 月次累計 */
    private static final String CSV_FILENAME_MONTHLY = "月次累計";
    
    /** 明細 */
    private static final String CSV_FILENAME_DETAIL = "明細";
    
    /** 顧客・商品分類・通貨毎 */
    private static final String CSV_FILENAME_CUSTOMER = "顧客・商品分類・通貨毎";
    
    /** 通貨毎 */
    private static final String CSV_FILENAME_CURRENCY = "通貨毎";
    
    // --------------------------------
    // その他定数定義
    // --------------------------------
    /** コードマスタテーブル.種別 （'99':画面コメント） */
    private static final String M_CODE_CODE_TYPE_SCREEN_COMMENT = "99";
    
    /** コードマスタテーブル.CODE_1 （'01':デフォルト） */
    private static final String M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT = "01";
    
    /** コードマスタテーブル.CODE_2 （'04':信託報酬画面コメント） */
    private static final String M_CODE_CODE_2_SCREEN_COMMENT_TRUST_FEE = "04";
    
    /** 最小取得件数 */
    private static final int MIN_COUNT = 1;
    
    /** 最大取得件数（表示用） */
    private static final int MAX_COUNT_DISPLAY = 5000;
    
    /** 画面ID */
    private static final String SCREEN_ID = "SUB0208_02-01";
    
    @Autowired
    private IfaJointMarketTrustFeeDao dao;
    
    @Autowired
    private MCodeService mcodeService;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private Fct038 fct038;
    
    @Autowired
    private ComplianceService complianceService;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * DTO リクエスト：IfaJointMarketTrustFeeA001DtoRequest
     * DTO レスポンス：IfaJointMarketTrustFeeA001DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointMarketTrustFeeA001DtoResponse> initializeA001(IfaJointMarketTrustFeeA001DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointMarketTrustFeeServiceImpL.initializeA001");
        }
        
        List<IfaJointMarketTrustFeeA001DtoResponse> resList = new ArrayList<IfaJointMarketTrustFeeA001DtoResponse>();
        IfaJointMarketTrustFeeA001DtoResponse res = new IfaJointMarketTrustFeeA001DtoResponse();
        
        // 　共同店舗信託報酬コメントを取得
        List<MCode> selSql002Res = mcodeService.getMCodeList(M_CODE_CODE_TYPE_SCREEN_COMMENT,
                M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT, M_CODE_CODE_2_SCREEN_COMMENT_TRUST_FEE);
        
        DataList<IfaJointMarketTrustFeeA001DtoResponse> dtoRes = new DataList<IfaJointMarketTrustFeeA001DtoResponse>();
        
        // レスポンスの設定
        if (selSql002Res != null && selSql002Res.size() > 0) {
            res.setTrustFeeComment(selSql002Res.get(0).getName());
        }
        resList.add(res);
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * DTO リクエスト：IfaJointMarketTrustFeeA002DtoRequest
     * DTO レスポンス：IfaJointMarketTrustFeeA002DtoResponse
     * model リクエスト：IfaJointMarketTrustFeeSql006RequestModel
     * model レスポンス：IfaJointMarketTrustFeeSql006ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointMarketTrustFeeA002DtoResponse> displayA002(IfaJointMarketTrustFeeA002DtoRequest dtoReq) throws Exception {
        
        DataList<IfaJointMarketTrustFeeA002DtoResponse> dtoRes = new DataList<IfaJointMarketTrustFeeA002DtoResponse>();
        List<IfaJointMarketTrustFeeA002DtoResponse> resList = new ArrayList<IfaJointMarketTrustFeeA002DtoResponse>();
        List<IfaJointMarketTrustFeeA002TrustFeeDtoResponse> jointMarketTrustFeeList = new ArrayList<IfaJointMarketTrustFeeA002TrustFeeDtoResponse>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointMarketTrustFeeServiceImpL.displayA002");
        }
        
        // ユーザ共通情報.権限コードを取得
        String privId = IfaCommonUtil.getUserAccount().getPrivId();
        
        // ユーザ共通情報.権限コード != SBI証券本店の場合、FCT030.仲介業者営業員リストを取得
        List<BrokerCharge> brokerChargeList = new ArrayList<BrokerCharge>();
        if (!StringUtils.equals(privId, PrivId.HEAD_OFFICE.getId())) {
            // FCT030を呼び出す
            InputFct030Dto fct030InputDto = new InputFct030Dto();
            OutputFct030Dto fct030OutputDto = fct030.getData(fct030InputDto);
            
            // FCT030.仲介業者営業員リストの件数が0件の場合、エラーメッセージを返す
            if (fct030OutputDto == null || fct030OutputDto.getBrokerChargeList().size() < MIN_COUNT) {
                dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST));
                return dtoRes; 
            }
            
            brokerChargeList = fct030OutputDto.getBrokerChargeList();
        }
        // SQL001のリクエスト値を設定
        IfaJointMarketTrustFeeSql001RequestModel selSql001Req = new IfaJointMarketTrustFeeSql001RequestModel();
        BeanUtils.copyProperties(dtoReq, selSql001Req);
        selSql001Req.setBrokerChargeList(brokerChargeList);
        selSql001Req.setMaxRowNum(MAX_COUNT_DISPLAY);
        selSql001Req.setPrivId(privId);
        
        // 共同店舗信託報酬リストを取得する
        DataList<IfaJointMarketTrustFeeSql001ResponseModel> selSql001ResList = new DataList<IfaJointMarketTrustFeeSql001ResponseModel>();
        selSql001ResList = getIfaJointMarketTrustFeeList(selSql001Req);
        
        // 共同店舗信託報酬リストの件数が0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql001ResList) || selSql001ResList.size() < MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
            return dtoRes;
        }
        
        IfaJointMarketTrustFeeA002DtoResponse res = new IfaJointMarketTrustFeeA002DtoResponse();
        
        // レスポンスの設定
        for (IfaJointMarketTrustFeeSql001ResponseModel selSql001Res : selSql001ResList.getDataList()) {
            IfaJointMarketTrustFeeA002TrustFeeDtoResponse trustFee = new IfaJointMarketTrustFeeA002TrustFeeDtoResponse();
            BeanUtils.copyProperties(selSql001Res, trustFee);
            jointMarketTrustFeeList.add(trustFee);
        }
        res.setTrustFeeList(jointMarketTrustFeeList);
        resList.add(res);
        
        // 共同店舗信託報酬リストの総件数が最大取得件数を超過していた場合、件数超過メッセージをセットして終了
        if (selSql001ResList.get(0).getTotalCount() > MAX_COUNT_DISPLAY) {
            String msg = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                    new String[] { String.valueOf(MAX_COUNT_DISPLAY),
                            String.valueOf(selSql001ResList.get(0).getTotalCount()) });
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.WARNING, WARNINGS_DATA_LIST_OVER_MAX_ROWNUM, msg);
        } else {
            // 正常終了
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        }
        
        return dtoRes;
    }
    
    /**
     * アクションID：A004a
     * アクション名：CSV出力
     * DTO リクエスト：IfaJointMarketTrustFeeA004bDtoRequest
     * DTO レスポンス：IfaJointMarketTrustFeeA004bDtoResponse
     *
     * @param dtoReq リクエスト
     * @param frameworkSessionId フレームワークセッションID
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointMarketTrustFeeA004aDtoResponse> csvOutputA004a(IfaJointMarketTrustFeeA004aDtoRequest dtoReq,
            String frameworkSessionId) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointMarketTrustFeeServiceImpL.csvOutputA004a");
        }
        
        DataList<IfaJointMarketTrustFeeA004aDtoResponse> dtoRes = new DataList<IfaJointMarketTrustFeeA004aDtoResponse>();
        List<IfaJointMarketTrustFeeA004aDtoResponse> resList = new ArrayList<IfaJointMarketTrustFeeA004aDtoResponse>();
        
        // ユーザ共通情報.権限コードを取得
        String privId = IfaCommonUtil.getUserAccount().getPrivId();
        
        // ユーザ共通情報.権限コード != SBI証券本店の場合、FCT030.仲介業者営業員リストを取得
        List<BrokerCharge> brokerChargeList = new ArrayList<BrokerCharge>();
        if (!StringUtils.equals(privId, PrivId.HEAD_OFFICE.getId())) {
            // FCT030を呼び出す
            InputFct030Dto fct030InputDto = new InputFct030Dto();
            OutputFct030Dto fct030OutputDto = fct030.getData(fct030InputDto);
            
            // FCT030.仲介業者営業員リストの件数が0件の場合、エラーメッセージを返す
            if (fct030OutputDto == null || fct030OutputDto.getBrokerChargeList().size() < MIN_COUNT) {
                dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST));
                return dtoRes; 
            }
            
            brokerChargeList = fct030OutputDto.getBrokerChargeList();
        }
        
        // FCT038.CSVダウンロードMAX件数を取得
        InputFct038Dto fct038InputDto = new InputFct038Dto();
        fct038InputDto.setScreenId(SCREEN_ID);
        fct038InputDto.setUserAuthority(privId);
        OutputFct038Dto fct038OutputDto = fct038.getData(fct038InputDto);
        int maxCountCsv = fct038OutputDto.getCsvDownloadNum();
        
        // 検索条件によりパターンを設定する
        String patternNo = PATTERN
                .get(dtoReq.getDailyMonthlyCountingUnitTotal() + dtoReq.getDetailCustomerCurrencyCountingUnit());
        
        // SQL001のリクエスト値を設定
        IfaJointMarketTrustFeeSql001RequestModel selSql001Req = new IfaJointMarketTrustFeeSql001RequestModel();
        BeanUtils.copyProperties(dtoReq, selSql001Req);
        selSql001Req.setMaxRowNum(maxCountCsv);
        selSql001Req.setBrokerChargeList(brokerChargeList);
        selSql001Req.setPrivId(privId);
        
        // 共同店舗信託報酬リストを取得する
        DataList<IfaJointMarketTrustFeeSql001ResponseModel> selSql001ResList = new DataList<IfaJointMarketTrustFeeSql001ResponseModel>();
        selSql001ResList = getIfaJointMarketTrustFeeList(selSql001Req);
        
        // 共同店舗信託報酬リストの件数が0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql001ResList) || selSql001ResList.size() < MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
            return dtoRes;
        }
        
        // レスポンス設定
        IfaJointMarketTrustFeeA004aDtoResponse res = new IfaJointMarketTrustFeeA004aDtoResponse();
        res.setPattern(patternNo);
        resList.add(res);
        
        // 共同店舗信託報酬リストの総件数が最大取得件数を超過していた場合、件数超過メッセージをセット
        if (selSql001ResList.get(0).getTotalCount() > maxCountCsv) {
            String msg = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                    new String[] { String.valueOf(maxCountCsv),
                            String.valueOf(selSql001ResList.get(0).getTotalCount()),
                            String.valueOf(maxCountCsv) });
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.WARNING, WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                    msg);
        } else {
            // 正常
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        }
        
        // CSV出力の内容に信託報酬リストをセット
        List<IfaJointMarketTrustFeeCsvItems> csvItemList = new ArrayList<IfaJointMarketTrustFeeCsvItems>();
        for (IfaJointMarketTrustFeeSql001ResponseModel selSql001Res : selSql001ResList.getDataList()) {
            IfaJointMarketTrustFeeCsvItems csvItem = new IfaJointMarketTrustFeeCsvItems();
            BeanUtils.copyProperties(selSql001Res, csvItem);
            csvItemList.add(csvItem);
        }
        
        // ユーザ共通情報.ユーザIDを取得
        String userId = IfaCommonUtil.getUserAccount().getUserId();
        
        // CSVファイルを作成
        IfaJointMarketTrustFeeCsvOut csvOut = new IfaJointMarketTrustFeeCsvOut(complianceService);
        DataList<IfaJointMarketTrustFeeCsvItems> csvDataList = new DataList<IfaJointMarketTrustFeeCsvItems>();
        csvDataList.setDataList(csvItemList);
        String csvTmpFileName = csvOut.doCreateCsvFile(csvDataList, frameworkSessionId, userId, patternNo);
        
        // ファイル名を決定
        String csvFileName = getCsvFileName(patternNo);
        
        dtoRes.setTitle(csvTmpFileName + "," + csvFileName);
        dtoRes.setTotalSize(selSql001ResList.get(0).getTotalCount());
        return dtoRes;
    }
    
    /**
     * 共同店舗　信託報酬一覧取得
     *
     * @param selSql001Req SQL001 リクエスト
     * @return IfaJointMarketTrustFeeSql001ResponseModel 共同店舗　信託報酬一覧
     */
    private DataList<IfaJointMarketTrustFeeSql001ResponseModel> getIfaJointMarketTrustFeeList(
            IfaJointMarketTrustFeeSql001RequestModel selSql001Req) throws Exception {
        
        DataList<IfaJointMarketTrustFeeSql001ResponseModel> selSql001ResList = new DataList<IfaJointMarketTrustFeeSql001ResponseModel>();
     // 共同店舗　信託報酬リストを取得する
        selSql001ResList = dao.selectIfaJointMarketTrustFeeSql001(selSql001Req); 
        return selSql001ResList;
    }

    /**
     * CSVファイル名取得
     *
     * @param patternNo パターンNo
     * @return String CSVファイル名
     */
    private String getCsvFileName(String patternNo) {
        
        String csvFileName = StringUtil.EMPTY_STRING;
        
        // 共同店舗 信託報酬リストを取得する
        switch (patternNo) {
            case PATTERN_NO1_DAILY_DETAIL:
                // パターンNo1.日次-明細
                csvFileName = CSV_FILENAME_SCREEN_NAME + "_" + CSV_FILENAME_DAILY + "_" + CSV_FILENAME_DETAIL;
                break;
            case PATTERN_NO2_DAILY_CUSTOMER:
                // パターンNo2.日次-顧客・商品分類・通貨毎
                csvFileName = CSV_FILENAME_SCREEN_NAME + "_" + CSV_FILENAME_DAILY + "_" + CSV_FILENAME_CUSTOMER;
                break;
            case PATTERN_NO3_DAILY_CURRENCY:
                // パターンNo3.日次-通貨毎
                csvFileName = CSV_FILENAME_SCREEN_NAME + "_" + CSV_FILENAME_DAILY + "_" + CSV_FILENAME_CURRENCY;
                break;
            case PATTERN_NO4_MONTHLY_DETAIL:
                // パターンNo4.月次累計-明細
                csvFileName = CSV_FILENAME_SCREEN_NAME + "_" + CSV_FILENAME_MONTHLY + "_" + CSV_FILENAME_DETAIL;
                break;
            case PATTERN_NO5_MONTHLY_CUSTOMER:
                // パターンNo5.月次累計-顧客・商品分類・通貨毎
                csvFileName = CSV_FILENAME_SCREEN_NAME + "_" + CSV_FILENAME_MONTHLY + "_" + CSV_FILENAME_CUSTOMER;
                break;
            case PATTERN_NO6_MONTHLY_CURRENCY:
                // パターンNo6.月次累計-通貨毎
                csvFileName = CSV_FILENAME_SCREEN_NAME + "_" + CSV_FILENAME_MONTHLY + "_" + CSV_FILENAME_CURRENCY;
                break;
            default:
        }
        
        return csvFileName;
    }
}
