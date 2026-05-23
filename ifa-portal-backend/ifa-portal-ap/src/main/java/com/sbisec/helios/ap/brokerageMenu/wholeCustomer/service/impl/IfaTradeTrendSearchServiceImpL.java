package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaTradeTrendSearchDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeTrendSearchSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeTrendSearchSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchA004aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchCsvItems;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaTradeTrendSearchService;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaTradeTrendSearchCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB020302_0401-01
 * 画面名：取引動向検索
 *
 * @author SBI 苗萌
 * 2025/04/10 新規作成
 */
@Component(value = "cmpIfaTradeTrendSearchService")
public class IfaTradeTrendSearchServiceImpL implements IfaTradeTrendSearchService {
    
    /** ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaTradeTrendSearchServiceImpL.class);
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    
    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";
    
    /** "検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。" */
    private static final String WARNINGS_DATA_LIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";
    
    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のCSV出力を行います。 */
    private static final String WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    /** {0}件のcsv出力を行います。 */
    private static final String WARNINGS_DATALIST_CSV_OUTPUT = "warnings.dataList.csv.output";
    
    /** 参照可能な仲介業者コード／営業員コードが存在しません。. */
    private static final String ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST = "errors.cmn.ifaAgentCodes.notExist";

    /** "{0}は{1}以内を正しく入力して下さい。" */
    private static final String ERRORS_DATERANGE = "errors.dateRange";
    
    /** "{0}は{1}以前を入力してください。" */
    private static final String ERRORS_DATERANGE_TO = "errors.beforeDate";
    
    
    // --------------------------------
    // その他定数定義
    // --------------------------------
    /** コードマスタテーブル.種別 （'99':画面コメント） */
    private static final String M_CODE_CODE_TYPE_SCREEN_COMMENT = "99";
    
    /** コードマスタテーブル.CODE_1 （'01':デフォルト） */
    private static final String M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT = "01";
    
    /** コードマスタテーブル.CODE_2 （'19':取引動向検索画面コメント） */
    private static final String M_CODE_CODE_2_SCREEN_COMMENT_JOINT_SUBSCRIPT_TRUST_FEE = "19";
    
    /** 最小取得件数 */
    private static final int MIN_COUNT = 1;
    
    /** 最大取得件数（表示用） */
    private static final int MAX_COUNT_DISPLAY = 5000;
    
    /** 画面ID */
    private static final String SCREEN_ID = "SUB020302_0401-01";
    
    /** 権限コード：SBI証券本店 の場合. */
    private static final String AUTH_CODE_SBI = "1";
    
    // --------------------------------
    // 表示明細/CSV出力項目パターンNo
    // --------------------------------   
    /** パターンNo1.集計単位：仲介業者 */
    private static final String PATTERN_NO1 = "1";
    
    /** パターンNo2.集計単位：営業員 */
    private static final String PATTERN_NO2 = "2";
    
    /** パターンNo3.集計単位：顧客 */
    private static final String PATTERN_NO3 = "3";
    
    // CSVのみ：期間指定（From）= 期間指定（To）
    /** パターンNo4.集計単位：顧客 */
    private static final String PATTERN_NO4 = "4";
    
    /** パターンNo5.集計単位：顧客 */
    private static final String PATTERN_NO5 = "5";
    
    /** パターンNo6.集計単位：顧客 */
    private static final String PATTERN_NO6 = "6";
    
    // --------------------------------
    // 表示明細/CSV出力項目パターン
    // --------------------------------   
    /** パターンNo1.集計単位：仲介業者 */
    private static final String PATTERN_NO1_BROKER = "1";
    
    /** パターンNo2.集計単位：営業員 */
    private static final String PATTERN_NO2_BROKER_CHARGE = "2";
    
    /** パターンNo3.集計単位：顧客 */
    private static final String PATTERN_NO3_CUSTOMER = "3";
    
    private static final Map<String, String> PATTERN = new HashMap<>();
    
    static {
        PATTERN.put(PATTERN_NO1_BROKER,PATTERN_NO1);
        PATTERN.put(PATTERN_NO2_BROKER_CHARGE,PATTERN_NO2);
        PATTERN.put(PATTERN_NO3_CUSTOMER,PATTERN_NO3);
    }
    
    /** 区分.契約締結前交付書面コード */
    private static final String PRE_CONTRACT_DOC_CODE = "PRE_CONTRACT_DOC_CODE";
    
    /** 1年 */
    private static final String YEAR = "1年";
    
    /** 期間指定From */
    private static final String PERIOD_FROM = "期間指定From";
    
    /** 期間指定To */
    private static final String PERIOD_TO = "期間指定To";
    
    /** 当月 */
    private static final String NEW_MONTH = "当月";
    
    /** ドロップダウンボックス項目CODE_TYPE */
    private static final String CODE_TYPE = "72";
    /** 個人コード */
    private static final String PERSONAL_KBN = "0";
    /** 法人コード */
    private static final String CORPORATION_KBN = "1";
    /** 本人職業(個人) */
    private static final String OCCUPATION_0 = "OCCUPATION_0";
    /** 本人職業(法人) */
    private static final String OCCUPATION_1 = "OCCUPATION_1";
    /** 投資の方針(個人) */
    private static final String INVESTMENTPLAN_0 = "INVESTMENTPLAN_0";
    /** 投資の方針(法人) */
    private static final String INVESTMENTPLAN_1 = "INVESTMENTPLAN_1";
    /** 資金の性格(個人) */
    private static final String FUNDCHARACTER_0 = "FUNDCHARACTER_0";
    /** 資金の性格 (法人) */
    private static final String FUNDCHARACTER_1 = "FUNDCHARACTER_1";
    /** 資産運用期間(個人/法人共通) */
    private static final String EMLOYMENTPERIOD_2 = "EMLOYMENTPERIOD_2";
    /** 主な収入源(個人) */
    private static final String INCOMEFORM_0 = "INCOMEFORM_0";
    /** 年収(個人) */
    private static final String ANNUALINCOME_0 = "ANNUALINCOME_0";
    /** 金融資産(個人) */
    private static final String FINANCIALASSETS_0 = "FINANCIALASSETS_0";
    /** "-"を設定する */
    private static final String VALUE = "-";
    
    
    @Autowired
    private IfaTradeTrendSearchDao dao;
    
    @Autowired
    private MCodeService mcodeService;
    
    @Autowired
    private Fct038 fct038;
    
    @Autowired
    private ComplianceService complianceService;
    
    @Autowired
    private Fct030 brokerCodeChargeListAcquire;
    
    @Autowired
    private CodeListService codeListService;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * DTO リクエスト：IfaTradeTrendSearchA001DtoRequest
     * DTO レスポンス：IfaTradeTrendSearchA001DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeTrendSearchA001DtoResponse> initializeA001(IfaTradeTrendSearchA001DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaTradeTrendSearchServiceImpL.initializeA001");
        }
        
        List<IfaTradeTrendSearchA001DtoResponse> resList = new ArrayList<IfaTradeTrendSearchA001DtoResponse>();
        IfaTradeTrendSearchA001DtoResponse res = new IfaTradeTrendSearchA001DtoResponse();
        
        // 取引動向検索コメントを取得
        List<MCode> selSql002Res = mcodeService.getMCodeList(M_CODE_CODE_TYPE_SCREEN_COMMENT,
                M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT, M_CODE_CODE_2_SCREEN_COMMENT_JOINT_SUBSCRIPT_TRUST_FEE);
        
        DataList<IfaTradeTrendSearchA001DtoResponse> dtoRes = new DataList<IfaTradeTrendSearchA001DtoResponse>();
        
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
     * DTO リクエスト：IfaTradeTrendSearchA002DtoRequest
     * DTO レスポンス：IfaTradeTrendSearchA002DtoResponse
     * model リクエスト：IfaTradeTrendSearchSql002RequestModel
     * model レスポンス：IfaTradeTrendSearchSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeTrendSearchA002DtoResponse> displayA002(IfaTradeTrendSearchA002DtoRequest dtoReq) throws Exception {
        
        DataList<IfaTradeTrendSearchA002DtoResponse> dtoRes = new DataList<IfaTradeTrendSearchA002DtoResponse>();
        List<IfaTradeTrendSearchA002DtoResponse> resList = new ArrayList<IfaTradeTrendSearchA002DtoResponse>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaTradeTrendSearchServiceImpL.displayA002");
        }

        // チェック
        // 仲介業者営業員リスト
        OutputFct030Dto outputBrokerCodeChargeListAcquireDto = new OutputFct030Dto();
        
        // ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        if (! StringUtils.equals(AUTH_CODE_SBI, userAccount.getPrivId())) {
            // 参照可能な仲介業者コードと営業員コードを取得する。
            InputFct030Dto fct030InData = new InputFct030Dto();
            outputBrokerCodeChargeListAcquireDto = brokerCodeChargeListAcquire.getData(fct030InData);

            if (CollectionUtils.isEmpty(outputBrokerCodeChargeListAcquireDto.getBrokerChargeList()) 
                    || outputBrokerCodeChargeListAcquireDto.getBrokerChargeList().size() == 0) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST));
            }
        }
        
        // 期間指定From
        String periodFrom = dtoReq.getPeriodFrom();
        // 期間指定To
        String periodTo = dtoReq.getPeriodTo();
        
        if(periodFrom.compareTo(periodTo) > 0) {
            // 期間指定From > 期間指定Toの場合､期間指定Fromと期間指定Toを入れ替える
            // 期間指定From
             dtoReq.setPeriodFrom(periodTo);
            // 期間指定To
             dtoReq.setPeriodTo(periodFrom);
        }

        // システム日付取得
        String systemDate =  DateUtil.format(DateUtil.now(), IfaDateUtil.SEPARATED_YYYYMM);
       
        
        // 期間指定From < 1年前年月の場合、エラーを返す
        if(!DateUtil.maximumXMonthsFromTo(dtoReq.getPeriodFrom(), systemDate, 13, DateUtil.YYYYMM,
                DateUtil.SEPARATED_YYYYMM)) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_DATERANGE,
                    IfaCommonUtil.getMessage(ERRORS_DATERANGE, new String[] { PERIOD_FROM, YEAR }));
            return dtoRes;
        
        }
        // 期間指定To > 当月の場合、エラーを返す
        if(systemDate.compareTo(dtoReq.getPeriodTo()) < 0) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_DATERANGE_TO,
                    IfaCommonUtil.getMessage(ERRORS_DATERANGE_TO, new String[] { PERIOD_TO, NEW_MONTH }));
            return dtoRes;
        }
        
        
        // SQL002のリクエスト値を設定
        IfaTradeTrendSearchSql002RequestModel selSql002Req = new IfaTradeTrendSearchSql002RequestModel();
        BeanUtils.copyProperties(dtoReq, selSql002Req);
        // 最大取得件数
        selSql002Req.setMaxRowNum(MAX_COUNT_DISPLAY);
        // 権限コード
        selSql002Req.setPrivId(userAccount.getPrivId());
        // FCT030.仲介業者営業員リスト
        selSql002Req.setBrokerChargeList(outputBrokerCodeChargeListAcquireDto.getBrokerChargeList());

        
        // 取引動向検索リストを取得する
        DataList<IfaTradeTrendSearchSql002ResponseModel> selSql002ResList = new DataList<IfaTradeTrendSearchSql002ResponseModel>();
        selSql002ResList = dao.selectIfaTradeTrendSearchSql002(selSql002Req);
        
        // 取引動向検索リストの件数が0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql002ResList) || selSql002ResList.size() < MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
            return dtoRes;
        }
        
        
        // コードマスター 種別72コード変換を行う｡
        List<MCode> mCodeList = new ArrayList<>();
        Map<String, Map<String, String>> mCodeMap = new HashMap<>();
            // 法人区分の下のドロップダウンボックス項目取得する
            mCodeList = mcodeService.getMCodeList(CODE_TYPE);
            // 法人区分の下のドロップダウンボックス項目MAP作成
            mCodeMap = mCodeList.stream()
                    .collect(Collectors.groupingBy(MCode::getCode1, Collectors.toMap(MCode::getCode2, MCode::getName)));
        
        // レスポンスの設定
        for (IfaTradeTrendSearchSql002ResponseModel selSql002Res : selSql002ResList.getDataList()) {
            IfaTradeTrendSearchA002DtoResponse TradeTrendSearch = new IfaTradeTrendSearchA002DtoResponse();

                // 個人の場合
                if (PERSONAL_KBN.equals(selSql002Res.getCorporationKbn())) {
                    // 投資方針
                    selSql002Res.setInvestmentPlan(mCodeMap.get(INVESTMENTPLAN_0).get(selSql002Res.getInvestmentPlan()));
                    // 職業
                    if (selSql002Res.getOccupation() !=null) {
                        selSql002Res.setOccupation(mCodeMap.get(OCCUPATION_0).get(selSql002Res.getOccupation().trim()));
                    }
                    // 資金性格
                    selSql002Res.setFundCharacter(mCodeMap.get(FUNDCHARACTER_0).get(selSql002Res.getFundCharacter()));
                    // 年収
                    selSql002Res.setAnnualIncome(mCodeMap.get(ANNUALINCOME_0).get(selSql002Res.getAnnualIncome()));
                    // 金融資産
                    selSql002Res.setFinancialAssets(mCodeMap.get(FINANCIALASSETS_0).get(selSql002Res.getFinancialAssets()));
                    // 主な収入源
                    selSql002Res.setIncomeForm(mCodeMap.get(INCOMEFORM_0).get(selSql002Res.getIncomeForm()));
                    
                    // 法人の場合
                } else if (CORPORATION_KBN.equals(selSql002Res.getCorporationKbn())) {
                    // 投資方針
                    if (selSql002Res.getInvestmentPlan() != null) {
                        selSql002Res.setInvestmentPlan(mCodeMap.get(INVESTMENTPLAN_1).get(selSql002Res.getInvestmentPlan().trim()));
                    }
                    // 職業
                    selSql002Res.setOccupation(mCodeMap.get(OCCUPATION_1).get(selSql002Res.getOccupation()));
                    // 資金性格
                    if (selSql002Res.getFundCharacter() != null) {
                        selSql002Res.setFundCharacter(mCodeMap.get(FUNDCHARACTER_1).get(selSql002Res.getFundCharacter().trim()));
                    }
                    // 年収
                    selSql002Res.setAnnualIncome(VALUE);
                    // 金融資産
                    selSql002Res.setFinancialAssets(VALUE);
                    // 主な収入源
                    selSql002Res.setIncomeForm(VALUE);
                }
                // 資産運用期間
                selSql002Res.setEmloymentPeriod(mCodeMap.get(EMLOYMENTPERIOD_2).get(selSql002Res.getEmloymentPeriod()));
            
            
            BeanUtils.copyProperties(selSql002Res, TradeTrendSearch);
            resList.add(TradeTrendSearch);
        }

        
        // 取引動向検索リストの総件数が最大取得件数を超過していた場合、件数超過メッセージをセットして終了
        if (selSql002ResList.get(0).getTotalCount() > MAX_COUNT_DISPLAY) {
            String msg = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                    new String[] { String.valueOf(MAX_COUNT_DISPLAY),
                            String.valueOf(selSql002ResList.get(0).getTotalCount()) });
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
     * DTO リクエスト：IfaTradeTrendSearchA004aDtoRequest
     * DTO レスポンス：IfaTradeTrendSearchA004aDtoResponse
     * model リクエスト：IfaTradeTrendSearchSql002RequestModel
     * model レスポンス：IfaTradeTrendSearchSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @param frameworkSessionId フレームワークセッションID
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeTrendSearchA004aDtoResponse> csvOutputA004a(IfaTradeTrendSearchA004aDtoRequest dtoReq,
            String frameworkSessionId) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaTradeTrendSearchServiceImpL.csvOutputA004a");
        }

        // 権限チェック
        // 仲介業者営業員リスト
        OutputFct030Dto outputBrokerCodeChargeListAcquireDto = new OutputFct030Dto();
        
        // ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        if (! StringUtils.equals(AUTH_CODE_SBI, userAccount.getPrivId())) {
            // 参照可能な仲介業者コードと営業員コードを取得する。
            InputFct030Dto fct030InData = new InputFct030Dto();
            outputBrokerCodeChargeListAcquireDto = brokerCodeChargeListAcquire.getData(fct030InData);

            if (CollectionUtils.isEmpty(outputBrokerCodeChargeListAcquireDto.getBrokerChargeList()) 
                    || outputBrokerCodeChargeListAcquireDto.getBrokerChargeList().size() == 0) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST));
            }
        }
        
        DataList<IfaTradeTrendSearchA004aDtoResponse> dtoRes = new DataList<IfaTradeTrendSearchA004aDtoResponse>();
        List<IfaTradeTrendSearchA004aDtoResponse> resList = new ArrayList<IfaTradeTrendSearchA004aDtoResponse>();
        
        // 期間指定From
        String periodFrom = dtoReq.getPeriodFrom();
        // 期間指定To
        String periodTo = dtoReq.getPeriodTo();
        
        if(periodFrom.compareTo(periodTo) > 0) {
            // 期間指定From > 期間指定Toの場合､期間指定Fromと期間指定Toを入れ替える
            // 期間指定From
             dtoReq.setPeriodFrom(periodTo);
            // 期間指定To
             dtoReq.setPeriodTo(periodFrom);
        }

        // システム日付取得
        String systemDate =  DateUtil.format(DateUtil.now(), IfaDateUtil.SEPARATED_YYYYMM);
       
        
        // 期間指定From < 1年前年月の場合、エラーを返す
        if(!DateUtil.maximumXMonthsFromTo(dtoReq.getPeriodFrom(), systemDate, 13, DateUtil.YYYYMM,
                DateUtil.SEPARATED_YYYYMM)) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_DATERANGE,
                    IfaCommonUtil.getMessage(ERRORS_DATERANGE, new String[] { PERIOD_FROM, YEAR }));
            return dtoRes;
        
        }
        // 期間指定To > 当月の場合、エラーを返す
        if(systemDate.compareTo(dtoReq.getPeriodTo()) < 0) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_DATERANGE_TO,
                    IfaCommonUtil.getMessage(ERRORS_DATERANGE_TO, new String[] { PERIOD_TO, NEW_MONTH }));
            return dtoRes;
        }
        
        
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
        
        // 一覧のヘッダ情報
        String patternNo = PATTERN.get(dtoReq.getCountingUnit());
        
        // SQL002のリクエスト値を設定
        IfaTradeTrendSearchSql002RequestModel selSql002Req = new IfaTradeTrendSearchSql002RequestModel();
        BeanUtils.copyProperties(dtoReq, selSql002Req);
        // 最大取得件数
        selSql002Req.setMaxRowNum(maxCountCsv);
        // 権限コード
        selSql002Req.setPrivId(userAccount.getPrivId());
        // FCT030.仲介業者営業員リスト
        selSql002Req.setBrokerChargeList(outputBrokerCodeChargeListAcquireDto.getBrokerChargeList());
        
        // 取引動向検索リストを取得する
        DataList<IfaTradeTrendSearchSql002ResponseModel> selSql002ResList = new DataList<IfaTradeTrendSearchSql002ResponseModel>();
        selSql002ResList = dao.selectIfaTradeTrendSearchSql002(selSql002Req);
        
        // 取引動向検索リストの件数が0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql002ResList) || selSql002ResList.size() < MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
            return dtoRes;
        }
        
        // レスポンス設定
        IfaTradeTrendSearchA004aDtoResponse res = new IfaTradeTrendSearchA004aDtoResponse();
        res.setPattern(patternNo);
        resList.add(res);
        
        // 取引動向検索リストの総件数が最大取得件数を超過していた場合、件数超過メッセージをセット
        if (selSql002ResList.get(0).getTotalCount() > maxCountCsv) {
            String msg = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                    new String[] { String.valueOf(maxCountCsv),
                            String.valueOf(selSql002ResList.get(0).getTotalCount()),
                            String.valueOf(maxCountCsv) });
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.WARNING, WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                    msg);
        } else {
            // 正常
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.WARNING,
                    Integer.toString(ErrorLevel.WARNING.getId()),
                    IfaCommonUtil.getMessage(WARNINGS_DATALIST_CSV_OUTPUT,
                            new String[] { String.valueOf(selSql002ResList.get(0).getTotalCount()) }));
        }
        
        // コードマスター 種別72コード変換を行う｡
        List<MCode> mCodeList = new ArrayList<>();
        Map<String, Map<String, String>> mCodeMap = new HashMap<>();
            // 法人区分の下のドロップダウンボックス項目取得する
            mCodeList = mcodeService.getMCodeList(CODE_TYPE);
            // 法人区分の下のドロップダウンボックス項目MAP作成
            mCodeMap = mCodeList.stream()
                    .collect(Collectors.groupingBy(MCode::getCode1, Collectors.toMap(MCode::getCode2, MCode::getName)));
        
        // CSV出力の内容に取引動向検索リストをセット
        List<IfaTradeTrendSearchCsvItems> csvItemList = new ArrayList<IfaTradeTrendSearchCsvItems>();
        for (IfaTradeTrendSearchSql002ResponseModel selSql002Res : selSql002ResList.getDataList()) {
            IfaTradeTrendSearchCsvItems csvItem = new IfaTradeTrendSearchCsvItems();
            
            // 個人の場合
            if (PERSONAL_KBN.equals(selSql002Res.getCorporationKbn())) {
                // 投資方針
                selSql002Res.setInvestmentPlan(mCodeMap.get(INVESTMENTPLAN_0).get(selSql002Res.getInvestmentPlan()));
                // 職業
                if (selSql002Res.getOccupation() !=null) {
                    selSql002Res.setOccupation(mCodeMap.get(OCCUPATION_0).get(selSql002Res.getOccupation().trim()));
                }
                // 資金性格
                selSql002Res.setFundCharacter(mCodeMap.get(FUNDCHARACTER_0).get(selSql002Res.getFundCharacter()));
                // 年収
                selSql002Res.setAnnualIncome(mCodeMap.get(ANNUALINCOME_0).get(selSql002Res.getAnnualIncome()));
                // 金融資産
                selSql002Res.setFinancialAssets(mCodeMap.get(FINANCIALASSETS_0).get(selSql002Res.getFinancialAssets()));
                // 主な収入源
                selSql002Res.setIncomeForm(mCodeMap.get(INCOMEFORM_0).get(selSql002Res.getIncomeForm()));
                
                // 法人の場合
            } else if (CORPORATION_KBN.equals(selSql002Res.getCorporationKbn())) {
                // 投資方針
                if (selSql002Res.getInvestmentPlan() != null) {
                    selSql002Res.setInvestmentPlan(mCodeMap.get(INVESTMENTPLAN_1).get(selSql002Res.getInvestmentPlan().trim()));
                }
                // 職業
                selSql002Res.setOccupation(mCodeMap.get(OCCUPATION_1).get(selSql002Res.getOccupation()));
                // 資金性格
                if (selSql002Res.getFundCharacter() != null) {
                    selSql002Res.setFundCharacter(mCodeMap.get(FUNDCHARACTER_1).get(selSql002Res.getFundCharacter().trim()));
                }
                // 年収
                selSql002Res.setAnnualIncome(VALUE);
                // 金融資産
                selSql002Res.setFinancialAssets(VALUE);
                // 主な収入源
                selSql002Res.setIncomeForm(VALUE);
            }
            // 資産運用期間
            selSql002Res.setEmloymentPeriod(mCodeMap.get(EMLOYMENTPERIOD_2).get(selSql002Res.getEmloymentPeriod()));
            
            BeanUtils.copyProperties(selSql002Res, csvItem);
            // 取引コース
            csvItem.setCustomerAttribute(codeListService.getValue(PRE_CONTRACT_DOC_CODE, selSql002Res.getCustomerAttribute()));
            csvItemList.add(csvItem);
        }
        
        // CSVファイルを作成
        IfaTradeTrendSearchCsvOut csvOut = new IfaTradeTrendSearchCsvOut(complianceService);
        DataList<IfaTradeTrendSearchCsvItems> csvDataList = new DataList<IfaTradeTrendSearchCsvItems>();
        csvDataList.setDataList(csvItemList);
        
        // 期間指定（From）<> 期間指定（To）: patternNo ⇒ 仲介業者=1 営業員 =2 顧客 =3
        // 期間指定（From）= 期間指定（To） : patternNo ⇒ 仲介業者=4 営業員 =5 顧客 =6
        // 集計単位：仲介業者
        if (periodFrom.equals(periodTo) && PATTERN_NO1.equals(patternNo)) {
            patternNo = PATTERN_NO4;
            
         // 集計単位：営業員
        } else if (periodFrom.equals(periodTo) && PATTERN_NO2.equals(patternNo)) {
            patternNo = PATTERN_NO5;
            
         // 集計単位：顧客
        } else if (periodFrom.equals(periodTo) && PATTERN_NO3.equals(patternNo)) {
            patternNo = PATTERN_NO6;
        }
        String csvTmpFileName = csvOut.doCreateCsvFile(csvDataList, frameworkSessionId, userId, patternNo);
        dtoRes.setTitle(csvTmpFileName);
        dtoRes.setTotalSize(selSql002ResList.get(0).getTotalCount());
        return dtoRes;
    }
   
}
