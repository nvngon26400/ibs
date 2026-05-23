package com.sbisec.helios.ap.brokerageMenu.jointSubscript.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.IfaJointSubscriptTrustFeeDao;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptTrustFeeSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptTrustFeeSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA002TrustFeeDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA004aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeCsvItems;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.service.IfaJointSubscriptTrustFeeService;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.util.IfaJointSubscriptTrustFeeCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB0206_03-01
 * 画面名：共同募集　信託報酬
 *
 * @author SBI 苗萌
 * 2024/12/18 新規作成
 */
@Component(value = "cmpIfaJointSubscriptTrustFeeService")
public class IfaJointSubscriptTrustFeeServiceImpL implements IfaJointSubscriptTrustFeeService {
    
    /** ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaJointSubscriptTrustFeeServiceImpL.class);
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    
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
    private static final String CSV_FILENAME_SCREEN_NAME = "共募信託報酬";
    
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
    private static final String M_CODE_CODE_2_SCREEN_COMMENT_JOINT_SUBSCRIPT_TRUST_FEE = "04";
    
    /** 最小取得件数 */
    private static final int MIN_COUNT = 1;
    
    /** 最大取得件数（表示用） */
    private static final int MAX_COUNT_DISPLAY = 5000;
    
    /** 画面ID */
    private static final String SCREEN_ID = "SUB0206_03-01";
    
    @Autowired
    private IfaJointSubscriptTrustFeeDao dao;
    
    @Autowired
    private MCodeService mcodeService;
    
    @Autowired
    private Fct038 fct038;
    
    @Autowired
    private ComplianceService complianceService;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * DTO リクエスト：IfaJointSubscriptTrustFeeA001DtoRequest
     * DTO レスポンス：IfaJointSubscriptTrustFeeA001DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptTrustFeeA001DtoResponse> initializeA001(IfaJointSubscriptTrustFeeA001DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointSubscriptTrustFeeServiceImpL.initializeA001");
        }
        
        List<IfaJointSubscriptTrustFeeA001DtoResponse> resList = new ArrayList<IfaJointSubscriptTrustFeeA001DtoResponse>();
        IfaJointSubscriptTrustFeeA001DtoResponse res = new IfaJointSubscriptTrustFeeA001DtoResponse();
        
        // 共同募集　信託報酬コメントを取得
        List<MCode> selSql002Res = mcodeService.getMCodeList(M_CODE_CODE_TYPE_SCREEN_COMMENT,
                M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT, M_CODE_CODE_2_SCREEN_COMMENT_JOINT_SUBSCRIPT_TRUST_FEE);
        
        DataList<IfaJointSubscriptTrustFeeA001DtoResponse> dtoRes = new DataList<IfaJointSubscriptTrustFeeA001DtoResponse>();
        
        // レスポンスの設定
        if (selSql002Res != null && selSql002Res.size() > 0) {
            res.setCommComment(selSql002Res.get(0).getName());
        }
        resList.add(res);
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * DTO リクエスト：IfaJointSubscriptTrustFeeA002DtoRequest
     * DTO レスポンス：IfaJointSubscriptTrustFeeA002DtoResponse
     * model リクエスト：IfaJointSubscriptTrustFeeSql001RequestModel
     * model レスポンス：IfaJointSubscriptTrustFeeSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptTrustFeeA002DtoResponse> displayA002(IfaJointSubscriptTrustFeeA002DtoRequest dtoReq) throws Exception {
        
        DataList<IfaJointSubscriptTrustFeeA002DtoResponse> dtoRes = new DataList<IfaJointSubscriptTrustFeeA002DtoResponse>();
        List<IfaJointSubscriptTrustFeeA002DtoResponse> resList = new ArrayList<IfaJointSubscriptTrustFeeA002DtoResponse>();
        List<IfaJointSubscriptTrustFeeA002TrustFeeDtoResponse> trustFeeList = new ArrayList<IfaJointSubscriptTrustFeeA002TrustFeeDtoResponse>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointSubscriptTrustFeeServiceImpL.displayA002");
        }
        
        // ユーザ共通情報.権限コードを取得
        String privId = IfaCommonUtil.getUserAccount().getPrivId();
        //ユーザ共通情報.ユーザーIDを取得
        String userId = IfaCommonUtil.getUserAccount().getUserId();

        // SQL001のリクエスト値を設定
        IfaJointSubscriptTrustFeeSql001RequestModel selSql001Req = new IfaJointSubscriptTrustFeeSql001RequestModel();
        BeanUtils.copyProperties(dtoReq, selSql001Req);
        selSql001Req.setMaxRowNum(MAX_COUNT_DISPLAY);
        selSql001Req.setPrivId(privId);
        selSql001Req.setUserId(userId);
        
        // 共募信託報酬リストを取得する
        DataList<IfaJointSubscriptTrustFeeSql001ResponseModel> selSql001ResList = new DataList<IfaJointSubscriptTrustFeeSql001ResponseModel>();
        selSql001ResList = dao.selectIfaJointSubscriptTrustFeeSql001(selSql001Req);
        
        // 共募信託報酬リストの件数が0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql001ResList) || selSql001ResList.size() < MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
            return dtoRes;
        }
        
        IfaJointSubscriptTrustFeeA002DtoResponse res = new IfaJointSubscriptTrustFeeA002DtoResponse();
        
        // レスポンスの設定
        for (IfaJointSubscriptTrustFeeSql001ResponseModel selSql001Res : selSql001ResList.getDataList()) {
            IfaJointSubscriptTrustFeeA002TrustFeeDtoResponse trustFee = new IfaJointSubscriptTrustFeeA002TrustFeeDtoResponse();
            BeanUtils.copyProperties(selSql001Res, trustFee);
            trustFeeList.add(trustFee);
        }
        res.setTrustFeeList(trustFeeList);
        resList.add(res);
        
        // 共募信託報酬リストの総件数が最大取得件数を超過していた場合、件数超過メッセージをセットして終了
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
     * DTO リクエスト：IfaJointSubscriptTrustFeeA004aDtoRequest
     * DTO レスポンス：IfaJointSubscriptTrustFeeA004aDtoResponse
     * model リクエスト：IfaJointSubscriptTrustFeeSql001RequestModel
     * model レスポンス：IfaJointSubscriptTrustFeeSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @param frameworkSessionId フレームワークセッションID
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptTrustFeeA004aDtoResponse> csvOutputA004a(IfaJointSubscriptTrustFeeA004aDtoRequest dtoReq,
            String frameworkSessionId) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointSubscriptTrustFeeServiceImpL.csvOutputA004a");
        }
        
        DataList<IfaJointSubscriptTrustFeeA004aDtoResponse> dtoRes = new DataList<IfaJointSubscriptTrustFeeA004aDtoResponse>();
        List<IfaJointSubscriptTrustFeeA004aDtoResponse> resList = new ArrayList<IfaJointSubscriptTrustFeeA004aDtoResponse>();
        
        // ユーザ共通情報.権限コードを取得
        String privId = IfaCommonUtil.getUserAccount().getPrivId();
        //ユーザ共通情報.ユーザーIDを取得
        String userId = IfaCommonUtil.getUserAccount().getUserId();
        
        // FCT038.CSVダウンロードMAX件数を取得
        InputFct038Dto fct038InputDto = new InputFct038Dto();
        fct038InputDto.setScreenId(SCREEN_ID);
        fct038InputDto.setUserAuthority(privId);
        OutputFct038Dto fct038OutputDto = fct038.getData(fct038InputDto);
        int maxCountCsv = fct038OutputDto.getCsvDownloadNum();
        
        // 検索条件により呼び出すSQLを決定
        String patternNo = PATTERN
                .get(dtoReq.getDailyMonthlyCountingUnitTotal() + dtoReq.getDetailCustomerCurrencyCountingUnit());
        
        // SQL001のリクエスト値を設定
        IfaJointSubscriptTrustFeeSql001RequestModel selSql001Req = new IfaJointSubscriptTrustFeeSql001RequestModel();
        BeanUtils.copyProperties(dtoReq, selSql001Req);
        selSql001Req.setMaxRowNum(maxCountCsv);
        selSql001Req.setPrivId(privId);
        selSql001Req.setUserId(userId);
        
        // 共募信託報酬リストを取得する
        DataList<IfaJointSubscriptTrustFeeSql001ResponseModel> selSql001ResList = new DataList<IfaJointSubscriptTrustFeeSql001ResponseModel>();
        selSql001ResList = dao.selectIfaJointSubscriptTrustFeeSql001(selSql001Req);
        
        // 共募信託報酬リストの件数が0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql001ResList) || selSql001ResList.size() < MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
            return dtoRes;
        }
        
        // レスポンス設定
        IfaJointSubscriptTrustFeeA004aDtoResponse res = new IfaJointSubscriptTrustFeeA004aDtoResponse();
        res.setPattern(patternNo);
        resList.add(res);
        
        // 共募信託報酬リストの総件数が最大取得件数を超過していた場合、件数超過メッセージをセット
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
        
        // CSV出力の内容に共募信託報酬リストをセット
        List<IfaJointSubscriptTrustFeeCsvItems> csvItemList = new ArrayList<IfaJointSubscriptTrustFeeCsvItems>();
        for (IfaJointSubscriptTrustFeeSql001ResponseModel selSql001Res : selSql001ResList.getDataList()) {
            IfaJointSubscriptTrustFeeCsvItems csvItem = new IfaJointSubscriptTrustFeeCsvItems();
            BeanUtils.copyProperties(selSql001Res, csvItem);
            csvItemList.add(csvItem);
        }
        
        // CSVファイルを作成
        IfaJointSubscriptTrustFeeCsvOut csvOut = new IfaJointSubscriptTrustFeeCsvOut(complianceService);
        DataList<IfaJointSubscriptTrustFeeCsvItems> csvDataList = new DataList<IfaJointSubscriptTrustFeeCsvItems>();
        csvDataList.setDataList(csvItemList);
        String csvTmpFileName = csvOut.doCreateCsvFile(csvDataList, frameworkSessionId, userId, patternNo);
        
        // ファイル名を決定
        String csvFileName = getCsvFileName(patternNo);
        
        dtoRes.setTitle(csvTmpFileName + "," + csvFileName);
        dtoRes.setTotalSize(selSql001ResList.get(0).getTotalCount());
        return dtoRes;
    }

    /**
     * CSVファイル名取得
     *
     * @param patternNo パターンNo
     * @return String CSVファイル名
     */
    private String getCsvFileName(String patternNo) {
        
        String csvFileName = StringUtil.EMPTY_STRING;
        
        // 共同募集　信託報酬リストを取得する
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
