package com.sbisec.helios.ap.brokerageMenu.commFee.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.IfaTrustFeeDao;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaTrustFeeSql001ToSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaTrustFeeSql001ToSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA002TrustFeeDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA004aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeCsvItems;
import com.sbisec.helios.ap.brokerageMenu.commFee.service.IfaTrustFeeService;
import com.sbisec.helios.ap.brokerageMenu.commFee.util.IfaTrustFeeCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB020503-01
 * 画面名：信託報酬
 *
 * @author SCSK 仁井田
 2024/06/11 新規作成
 */
@Component(value = "cmpIfaTrustFeeService")
public class IfaTrustFeeServiceImpL implements IfaTrustFeeService {
    
    /** ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaTrustFeeServiceImpL.class);
    
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

    /** SQL001～SQL006 FULL SCANのヒント句を付与する仲介業者（特にデータ量の多い仲介業者） */
    private static final List<String> USE_HINT_BROKER_CODES = Arrays.asList("1228", "0508", "1133", "1135"); 
    
    // --------------------------------
    // CSVファイル名
    // --------------------------------   
    /** 画面名 */
    private static final String CSV_FILENAME_SCREEN_NAME = "信託報酬";
    
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
    private static final String SCREEN_ID = "SUB020503-01";
    
    @Autowired
    private IfaTrustFeeDao dao;
    
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
     * DTO リクエスト：IfaTrustFeeA001DtoRequest
     * DTO レスポンス：IfaTrustFeeA001DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTrustFeeA001DtoResponse> initializeA001(IfaTrustFeeA001DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaTrustFeeServiceImplL.initializeA001");
        }
        
        List<IfaTrustFeeA001DtoResponse> resList = new ArrayList<IfaTrustFeeA001DtoResponse>();
        IfaTrustFeeA001DtoResponse res = new IfaTrustFeeA001DtoResponse();
        
        // 信託報酬コメントを取得
        List<MCode> selSql007Res = mcodeService.getMCodeList(M_CODE_CODE_TYPE_SCREEN_COMMENT,
                M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT, M_CODE_CODE_2_SCREEN_COMMENT_TRUST_FEE);
        
        DataList<IfaTrustFeeA001DtoResponse> dtoRes = new DataList<IfaTrustFeeA001DtoResponse>();
        
        // レスポンスの設定
        if (selSql007Res != null && selSql007Res.size() > 0) {
            res.setTrustFeeComment(selSql007Res.get(0).getName());
        }
        resList.add(res);
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * DTO リクエスト：IfaTrustFeeA002DtoRequest
     * DTO レスポンス：IfaTrustFeeA002DtoResponse
     * model リクエスト：IfaTrustFeeSql006RequestModel
     * model レスポンス：IfaTrustFeeSql006ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTrustFeeA002DtoResponse> displayA002(IfaTrustFeeA002DtoRequest dtoReq) throws Exception {
        
        DataList<IfaTrustFeeA002DtoResponse> dtoRes = new DataList<IfaTrustFeeA002DtoResponse>();
        List<IfaTrustFeeA002DtoResponse> resList = new ArrayList<IfaTrustFeeA002DtoResponse>();
        List<IfaTrustFeeA002TrustFeeDtoResponse> trustFeeList = new ArrayList<IfaTrustFeeA002TrustFeeDtoResponse>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaTrustFeeServiceImplL.displayA002");
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
        
        // 検索条件により呼び出すSQLを決定
        String patternNo = PATTERN
                .get(dtoReq.getDailyMonthlyCountingUnitTotal() + dtoReq.getDetailCustomerCurrencyCountingUnit());
        
        // SQL001～006のリクエスト値を設定
        IfaTrustFeeSql001ToSql006RequestModel selSql001ToSql006Req = new IfaTrustFeeSql001ToSql006RequestModel();
        BeanUtils.copyProperties(dtoReq, selSql001ToSql006Req);
        selSql001ToSql006Req.setBrokerChargeList(brokerChargeList);
        selSql001ToSql006Req.setPatternNo(patternNo);
        selSql001ToSql006Req.setMaxRowNum(MAX_COUNT_DISPLAY);
        selSql001ToSql006Req.setPrivId(privId);
        
        // 信託報酬リストを取得する
        DataList<IfaTrustFeeSql001ToSql006ResponseModel> selSql001ToSql006ResList = new DataList<IfaTrustFeeSql001ToSql006ResponseModel>();
        selSql001ToSql006ResList = getTrustFeeList(patternNo, selSql001ToSql006Req);
        
        // 信託報酬リストの件数が0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql001ToSql006ResList) || selSql001ToSql006ResList.size() < MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
            return dtoRes;
        }
        
        IfaTrustFeeA002DtoResponse res = new IfaTrustFeeA002DtoResponse();
        
        // レスポンスの設定
        for (IfaTrustFeeSql001ToSql006ResponseModel selSql001ToSql006Res : selSql001ToSql006ResList.getDataList()) {
            IfaTrustFeeA002TrustFeeDtoResponse trustFee = new IfaTrustFeeA002TrustFeeDtoResponse();
            BeanUtils.copyProperties(selSql001ToSql006Res, trustFee);
            trustFeeList.add(trustFee);
        }
        res.setTrustFeeList(trustFeeList);
        resList.add(res);
        
        // 信託報酬リストの総件数が最大取得件数を超過していた場合、件数超過メッセージをセットして終了
        if (selSql001ToSql006ResList.get(0).getTotalCount() > MAX_COUNT_DISPLAY) {
            String msg = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                    new String[] { String.valueOf(MAX_COUNT_DISPLAY),
                            String.valueOf(selSql001ToSql006ResList.get(0).getTotalCount()) });
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
     * DTO リクエスト：IfaTrustFeeA004bDtoRequest
     * DTO レスポンス：IfaTrustFeeA004bDtoResponse
     *
     * @param dtoReq リクエスト
     * @param frameworkSessionId フレームワークセッションID
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTrustFeeA004aDtoResponse> csvOutputA004a(IfaTrustFeeA004aDtoRequest dtoReq,
            String frameworkSessionId) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaTrustFeeServiceImplL.csvOutputA004a");
        }
        
        DataList<IfaTrustFeeA004aDtoResponse> dtoRes = new DataList<IfaTrustFeeA004aDtoResponse>();
        List<IfaTrustFeeA004aDtoResponse> resList = new ArrayList<IfaTrustFeeA004aDtoResponse>();
        
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
        
        // 検索条件により呼び出すSQLを決定
        String patternNo = PATTERN
                .get(dtoReq.getDailyMonthlyCountingUnitTotal() + dtoReq.getDetailCustomerCurrencyCountingUnit());
        
        // SQL001～006のリクエスト値を設定
        IfaTrustFeeSql001ToSql006RequestModel selSql001ToSql006Req = new IfaTrustFeeSql001ToSql006RequestModel();
        BeanUtils.copyProperties(dtoReq, selSql001ToSql006Req);
        selSql001ToSql006Req.setMaxRowNum(maxCountCsv);
        selSql001ToSql006Req.setPatternNo(patternNo);
        selSql001ToSql006Req.setBrokerChargeList(brokerChargeList);
        selSql001ToSql006Req.setPrivId(privId);
        
        // 信託報酬リストを取得する
        DataList<IfaTrustFeeSql001ToSql006ResponseModel> selSql001ToSql006ResList = new DataList<IfaTrustFeeSql001ToSql006ResponseModel>();
        selSql001ToSql006ResList = getTrustFeeList(patternNo, selSql001ToSql006Req);
        
        // 信託報酬リストの件数が0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql001ToSql006ResList) || selSql001ToSql006ResList.size() < MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
            return dtoRes;
        }
        
        // レスポンス設定
        IfaTrustFeeA004aDtoResponse res = new IfaTrustFeeA004aDtoResponse();
        res.setPattern(patternNo);
        resList.add(res);
        
        // 信託報酬リストの総件数が最大取得件数を超過していた場合、件数超過メッセージをセット
        if (selSql001ToSql006ResList.get(0).getTotalCount() > maxCountCsv) {
            String msg = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                    new String[] { String.valueOf(maxCountCsv),
                            String.valueOf(selSql001ToSql006ResList.get(0).getTotalCount()),
                            String.valueOf(maxCountCsv) });
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.WARNING, WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                    msg);
        } else {
            // 正常
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        }
        
        // CSV出力の内容に信託報酬リストをセット
        List<IfaTrustFeeCsvItems> csvItemList = new ArrayList<IfaTrustFeeCsvItems>();
        for (IfaTrustFeeSql001ToSql006ResponseModel selSql001ToSql006Res : selSql001ToSql006ResList.getDataList()) {
            IfaTrustFeeCsvItems csvItem = new IfaTrustFeeCsvItems();
            BeanUtils.copyProperties(selSql001ToSql006Res, csvItem);
            csvItemList.add(csvItem);
        }
        
        // ユーザ共通情報.ユーザIDを取得
        String userId = IfaCommonUtil.getUserAccount().getUserId();
        
        // CSVファイルを作成
        IfaTrustFeeCsvOut csvOut = new IfaTrustFeeCsvOut(complianceService);
        DataList<IfaTrustFeeCsvItems> csvDataList = new DataList<IfaTrustFeeCsvItems>();
        csvDataList.setDataList(csvItemList);
        String csvTmpFileName = csvOut.doCreateCsvFile(csvDataList, frameworkSessionId, userId, patternNo);
        
        // ファイル名を決定
        String csvFileName = getCsvFileName(patternNo);
        
        dtoRes.setTitle(csvTmpFileName + "," + csvFileName);
        dtoRes.setTotalSize(selSql001ToSql006ResList.get(0).getTotalCount());
        return dtoRes;
    }
    
    /**
     * 信託報酬一覧取得
     *
     * @param patternNo パターンNo
     * @param selSql001ToSql006Req SQL001~006 リクエスト
     * @return IfaTrustFeeSql001ToSql006ResponseModel 信託報酬一覧
     */
    private DataList<IfaTrustFeeSql001ToSql006ResponseModel> getTrustFeeList(String patternNo,
            IfaTrustFeeSql001ToSql006RequestModel selSql001ToSql006Req) throws Exception {
        
        DataList<IfaTrustFeeSql001ToSql006ResponseModel> selSql001ToSql006ResList = new DataList<IfaTrustFeeSql001ToSql006ResponseModel>();
        
        if (patternNo == null) {
            return selSql001ToSql006ResList;
        }

        // リクエスト.仲介業者コードが空の場合、もしくはリクエスト.仲介業者コードが"1228", "0508", "1133", "1135"のいずれかを含む場合、ヒント句使用フラグをTrueにする。
        // (データ件数が多い仲介業者コード指定の場合は、性能改善ためFULL SCANのヒント句を付与する。)
        Boolean useHintFlag = selSql001ToSql006Req.getBrokerCodeList() == null
                    || selSql001ToSql006Req.getBrokerCodeList().isEmpty()
                    || selSql001ToSql006Req.getBrokerCodeList().stream().anyMatch(USE_HINT_BROKER_CODES::contains);

        selSql001ToSql006Req.setUseHintFlag(useHintFlag);
        
        // 信託報酬リストを取得する
        switch (patternNo) {
            case PATTERN_NO1_DAILY_DETAIL:
                // パターンNo1.日次-明細
                selSql001ToSql006ResList = dao.selectIfaTrustFeeSql001(selSql001ToSql006Req);
                break;
            case PATTERN_NO2_DAILY_CUSTOMER:
                // パターンNo2.日次-顧客・商品分類・通貨毎
                selSql001ToSql006ResList = dao.selectIfaTrustFeeSql002(selSql001ToSql006Req);
                break;
            case PATTERN_NO3_DAILY_CURRENCY:
                // パターンNo3.日次-通貨毎
                selSql001ToSql006ResList = dao.selectIfaTrustFeeSql003(selSql001ToSql006Req);
                break;
            case PATTERN_NO4_MONTHLY_DETAIL:
                // パターンNo4.月次累計-明細
                selSql001ToSql006ResList = dao.selectIfaTrustFeeSql004(selSql001ToSql006Req);
                break;
            case PATTERN_NO5_MONTHLY_CUSTOMER:
                // パターンNo5.月次累計-顧客・商品分類・通貨毎
                selSql001ToSql006ResList = dao.selectIfaTrustFeeSql005(selSql001ToSql006Req);
                break;
            case PATTERN_NO6_MONTHLY_CURRENCY:
                // パターンNo6.月次累計-通貨毎
                selSql001ToSql006ResList = dao.selectIfaTrustFeeSql006(selSql001ToSql006Req);
                break;
            default:
        } 
        
        return selSql001ToSql006ResList;
    }

    /**
     * CSVファイル名取得
     *
     * @param patternNo パターンNo
     * @return String CSVファイル名
     */
    private String getCsvFileName(String patternNo) {
        
        String csvFileName = StringUtil.EMPTY_STRING;
        
        // 信託報酬リストを取得する
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
