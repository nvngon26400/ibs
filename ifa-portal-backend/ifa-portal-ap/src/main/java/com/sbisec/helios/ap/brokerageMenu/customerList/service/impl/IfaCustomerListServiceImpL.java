package com.sbisec.helios.ap.brokerageMenu.customerList.service.impl;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ibm.icu.text.Transliterator;
import com.ibm.icu.util.Calendar;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.IfaCustomerListDao;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA006ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListPersonalCorporation;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListSql001ResponseModelCsvOutModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListTradingCourse;
import com.sbisec.helios.ap.brokerageMenu.customerList.service.IfaCustomerListService;
import com.sbisec.helios.ap.brokerageMenu.customerList.util.IfaCustomerListCsvUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.service.ServiceStatusService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0201_01-01
 * 画面名：顧客一覧・基本
 *
 * @author SCSK池田
 *
   2023/09/13 新規作成
 */
@Component(value = "cmpIfaCustomerListService")
public class IfaCustomerListServiceImpL implements IfaCustomerListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCustomerListServiceImpL.class);

    private static final String SUCCESS_MESSAGE = "";

    private static final String SBI_HEAD_OFFICE = "1";

    private static final String FCT030_NOT_EXIST = "errors.cmn.ifaAgentCodes.notExist";

    private static final String INFO_DATALIST_NOT_FOUND = "errors.dataList.notfound";

    private static final String WARNING_DATALIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";

    private static final String A006_NOT_EXIST = "errors.butenAccountNotExist";

    private static final String VALUE_EMPTY = "";

    private static final int VIEW_LIMIT_VALUE = 5000;

    private static final String NRI_API = "NRI_API";

    private static final String RETURN_CODE_W0006 = "W0006";

    // ドロップダウンボックス項目CODE_TYPE
    private static final String CODE_TYPE = "72";
    // ドロップダウンボックスを選択した場合
    private static final String TRUE = "true";
    // 投資の方針(個人)
    private static final String INVESTMENTPLAN_0 = "INVESTMENTPLAN_0";
    // 投資の方針(法人)
    private static final String INVESTMENTPLAN_1 = "INVESTMENTPLAN_1";
    // 資金の性格(個人)
    private static final String FUNDCHARACTER_0 = "FUNDCHARACTER_0";
    // 資金の性格 (法人)
    private static final String FUNDCHARACTER_1 = "FUNDCHARACTER_1";
    // 主な収入源(個人)
    private static final String INCOMEFORM_0 = "INCOMEFORM_0";
    // 資産運用期間(個人/法人共通)
    private static final String EMLOYMENTPERIOD_2 = "EMLOYMENTPERIOD_2";
    // 年収(個人)
    private static final String ANNUALINCOME_0 = "ANNUALINCOME_0";
    // 金融資産(個人)
    private static final String FINANCIALASSETS_0 = "FINANCIALASSETS_0";
    // 職業(個人)
    private static final String OCCUPATION_0 = "OCCUPATION_0";
    // 職業(法人)
    private static final String OCCUPATION_1 = "OCCUPATION_1";
    // 個人コード
    private static final String PERSONAL_KBN = "0";
    // 法人コード
    private static final String CORPORATION_KBN = "1";
    // 全てコード
    private static final String SELECT_ALL = "2";
    // 投資経験有コード
    private static final String INVESTMENTEXP_OK = "1";
    // 投資経験無コード
    private static final String INVESTMENTEXP_NO = "2";
    // 投資経験有名前
    private static final String INVESTMENTEXP_NAME_OK = "あり";
    // 投資経験無名前
    private static final String INVESTMENTEXP_NAME_NO = "なし";
    // 投資経験年数単位
    private static final String INVESTMENTEXP_YEAR_UNIT = "年";
    // 投資経験年数が空の場合は0を設定
    private static final String INVESTMENTEXP_NULL_ZERO = "0";

    /** 顧客名 条件リスト */
    private enum CustomerNameConditionList {

        // と等しい
        EQUAL_TO("1"),
        // で始まる
        STARTS_WITH("2"),
        // を含む
        INCLUDING("3");

        private final String value;

        CustomerNameConditionList(final String value) {

            this.value = value;
        }

        private String getValue() {

            return value;
        }
    }

    /** 住所 条件リスト */
    private enum AddressConditionList {

        // と等しい
        EQUAL_TO("1"),
        // で始まる
        STARTS_WITH("2"),
        // を含む
        INCLUDING("3");

        private final String value;

        AddressConditionList(final String value) {

            this.value = value;
        }

        private String getValue() {

            return value;
        }
    }

    private enum CommTotalListCondition {

        // 全て
        ALL("1"),
        // 当月
        CURRENT_MONTH("2"),
        // 前月
        LAST_MONTH("3"),
        // 前々月
        PREVIOUS_MONTH("4"),
        // 期間指定
        PERIOD_SPECIFICATION("5");

        private final String value;

        CommTotalListCondition(final String value) {

            this.value = value;
        }

        private String getValue() {

            return value;
        }
    }

    @Autowired
    private IfaCustomerListDao dao;

    @Autowired
    private Fct030 fct030;

    @Autowired
    private Fct038 fct038;

    @Autowired
    private ServiceStatusService serviceStatusService;

    @Autowired
    MCodeService mcodeService;

    /**
     * ComplianceService(CSV証跡登録用)クラス
     */
    @Autowired
    private ComplianceService complianceService;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaCustomerListA001DtoRequest
     * Dto レスポンス：IfaCustomerListA001DtoResponse
     * model リクエスト：IfaCustomerListSql004RequestModel
     * model レスポンス：IfaCustomerListSql004ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return IfaCustomerListSql004ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaCustomerListSql004ResponseModel> initializeA001(IfaCustomerListA001RequestDto dtoReq)
            throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCustomerListServiceImplL.initializeA001");
        }

        DataList<IfaCustomerListSql004ResponseModel> selSql004Res = dao.selectIfaCustomerListSql004();

        return selSql004Res;
    }

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaCustomerListA002DtoRequest
     * Dto レスポンス：IfaCustomerListA002DtoResponse
     * model リクエスト：IfaCustomerListSql001RequestModel
     * model レスポンス：IfaCustomerListSql001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return IfaCustomerListA002ResponseDto
     * @exception Exception 例外
     */
    public DataList<IfaCustomerListA002ResponseDto> displayA002(IfaCustomerListA002RequestDto dtoReq) throws Exception {

        DataList<IfaCustomerListA002ResponseDto> dtoRes = new DataList<IfaCustomerListA002ResponseDto>();
        List<IfaCustomerListA002ResponseDto> dtoResList = new ArrayList<IfaCustomerListA002ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCustomerListServiceImplL.displayA002");
        }

        // 入力チェックは画面側で実施の想定
        // ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        // 仲介業者コードと営業員コードの参照可能チェックを行う。
        OutputFct030Dto fct030ResDto = new OutputFct030Dto();

        // SBI証券本店ではない場合にチェックを行う。
        if (!StringUtils.equals(userAccount.getPrivId(), SBI_HEAD_OFFICE)) {
            fct030ResDto = fct030.getData(null);
            // FCT030.仲介業者担当者リストの件数が0件の場合
            if (fct030ResDto.getBrokerChargeList().isEmpty() || fct030ResDto.getBrokerChargeList().size() == 0) {
                return IfaCommonUtil.createDataList(dtoResList, ErrorLevel.FATAL, FCT030_NOT_EXIST,
                        IfaCommonUtil.getMessage(FCT030_NOT_EXIST, new String[] {}));
            }
        }

        // SQLレスポンスの作成
        IfaCustomerListSql001RequestModel selSql001Req = new IfaCustomerListSql001RequestModel();
        // 「法人区分」全て以外の場合は、次の処理を行う
        List<MCode> mCodeList = new ArrayList<>();
        Map<String, Map<String, String>> mCodeMap = new HashMap<>();
        if (!SELECT_ALL.equals(dtoReq.getPersonalCorporation())) {
            // 法人区分の下のドロップダウンボックス項目取得する
            mCodeList = mcodeService.getMCodeList(CODE_TYPE);
            // 法人区分の下のドロップダウンボックス項目MAP作成("INVESTMENTPLAN_0" = ["01","利回り・安定重視"])
            mCodeMap = mCodeList.stream()
                    .collect(Collectors.groupingBy(MCode::getCode1, Collectors.toMap(MCode::getCode2, MCode::getName)));
        }
        // SQLパラメータの設定
        selSql001Req = setSql001Parameter(dtoReq.getBrokerCodeList(), dtoReq.getChkBrokerCodeExclude(),
                dtoReq.getBranchCode(), dtoReq.getEmpCode(), dtoReq.getButenCode(), dtoReq.getAccountNumber(),
                dtoReq.getCustomerNameKanjiKana(), dtoReq.getCustomerNameConditionList(), dtoReq.getCourse(),
                dtoReq.getTradeRestrictionCheckbox(), dtoReq.getComplianceLankFrom(), dtoReq.getComplianceLankTo(),
                dtoReq.getAdress(), dtoReq.getAdressConditionList(), dtoReq.getCorporatePhoneNumber(),
                dtoReq.getAgeFrom(), dtoReq.getAgeTo(), dtoReq.getBirthDateFrom(), dtoReq.getBirthDateTo(),
                dtoReq.getOpenAccountFrom(), dtoReq.getOpenAccountTo(), dtoReq.getButenCodeArray(),
                dtoReq.getValuationFrom(), dtoReq.getValuationTo(), dtoReq.getValuationConditionList(),
                dtoReq.getCommTotalList(), dtoReq.getCommTotalPeriodFrom(), dtoReq.getCommTotalPeriodTo(),
                dtoReq.getCommTotalAmountFrom(), dtoReq.getCommTotalAmountTo(),
                dtoReq.getCommTotalAmountConditionList(), dtoReq.getLastTradeDateFrom(), dtoReq.getLastTradeDateTo(), dtoReq.getLastTradeDateDisplay(),
                dtoReq.getNisaContractKbnList(), dtoReq.getCourseChangeFinishDateFrom(), dtoReq.getCourseChangeFinishDateTo(),
                dtoReq.getForeignSecurityAccountList(), dtoReq.getPersonalCorporation(), dtoReq.getInvestmentPlan(),
                dtoReq.getFundCharacter(), dtoReq.getIncomeForm(), dtoReq.getEmploymentPeriod(),
                dtoReq.getAnnualIncome(), dtoReq.getFinancialAssets(), dtoReq.getOccupation(),
                dtoReq.getInvestmentExp(), dtoReq.getInvestmentExpDisplay(),
                dtoReq.getNisaContractKbnDisplay(), mCodeList, userAccount.getPrivId(),
                fct030ResDto);
        // 上限件数の設定
        selSql001Req.setSearchLimitRow(VIEW_LIMIT_VALUE);

        DataList<IfaCustomerListSql001ResponseModel> selSql001Res = new DataList<IfaCustomerListSql001ResponseModel>();
        try {
            selSql001Res = dao.selectIfaCustomerListSql001(selSql001Req);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                StackTraceElement[] trace = e.getStackTrace();
                LOGGER.debug(trace.toString());
                LOGGER.debug(e.getMessage());
                LOGGER.debug(e.getCause().toString());
            }
            throw e;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCustomerListServiceImplL.selectIfaCustomerListSql001正常終了");
        }

        // 上限件数を超過判断変数
        int totalRow = 0;
        if (
                CollectionUtils.isNotEmpty(selSql001Res.getDataList())
                && StringUtils.isNotEmpty(selSql001Res.get(0).getTotalRow())
        ) {
            totalRow = Integer.parseInt(selSql001Res.get(0).getTotalRow());
        }

        // SQLの実行結果が0件の場合はレスポンス結果を処理せず終了する
        if (totalRow == 0) {
            dtoRes = IfaCommonUtil.createDataList(
                    dtoResList,
                    ErrorLevel.INFO,
                    INFO_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(INFO_DATALIST_NOT_FOUND)
            );
            return dtoRes;
        }

        // レスポンスデータのつくりこみ
        for (IfaCustomerListSql001ResponseModel resSql : selSql001Res.getDataList()) {
         // 最大件数を超過した時点でSQLの結果をレスポンスにコピーする処理を終了
            if (VIEW_LIMIT_VALUE <= dtoResList.size()) {
                break;
            }

            IfaCustomerListA002ResponseDto resDto = new IfaCustomerListA002ResponseDto();
            // コンプラランク
            resDto.setTcCompRank(StringUtil.nullToEmpty(resSql.getTcCompRank()));
            // 契約締結前交付書面コード名
            resDto.setCustomerAttributeName(StringUtil.nullToEmpty(resSql.getCustomerAttributeName()));
            // 顧客名（カナ）
            resDto.setCustomerNameKana(StringUtil.nullToEmpty(resSql.getCustomerNameKana()));
            // 顧客名（漢字）
            resDto.setCustomerNameKanji(StringUtil.nullToEmpty(resSql.getCustomerNameKanji()));
            // 口座開設日
            resDto.setOpenAcctDate(StringUtil.nullToEmpty(resSql.getOpenAcctDate()));
            // 口座番号
            resDto.setAccountNumber(StringUtil.nullToEmpty(resSql.getAccountNumber()));
            // 郵便番号
            resDto.setZipCodeBeforeAndAfter(StringUtil.nullToEmpty(resSql.getZipCodeBeforeAndAfter()));
            // 住所
            resDto.setAdressKanji(StringUtil.nullToEmpty(resSql.getAdressKanji()));
            // 仲介業者コード
            resDto.setBrokerCode(StringUtil.nullToEmpty(resSql.getBrokerCode()));
            // 仲介業者営業員コード
            resDto.setBrokerChargeCode(StringUtil.nullToEmpty(resSql.getBrokerChargeCode()));
            // 電話番号
            resDto.setCorporatePhoneNumber(StringUtil.nullToEmpty(resSql.getCorporatePhoneNumber()));
            // 生年月日
            resDto.setCorBirthFlg(StringUtil.nullToEmpty(resSql.getCorBirthFlg()));
            // 年齢
            resDto.setAge(StringUtil.nullToEmpty(resSql.getAge()));
            // 部店コード
            resDto.setButenCode(StringUtil.nullToEmpty(resSql.getButenCode()));
            // 仲介業者支店名
            resDto.setBranchNameOfBranch(StringUtil.nullToEmpty(resSql.getBranchNameOfBranch()));
            // 仲介業者支店名
            resDto.setBranchNameOfBroker(StringUtil.nullToEmpty(resSql.getBranchNameOfBroker()));
            // 担当者名
            resDto.setChargeName(StringUtil.nullToEmpty(resSql.getChargeName()));
            // 仲介業者支店コード
            resDto.setSubBrokerId(StringUtil.nullToEmpty(resSql.getSubBrokerId()));
            // 最終約定日
            resDto.setLastTradeDate(StringUtil.nullToEmpty(resSql.getLastTradeDate()));
            // 評価損益
            resDto.setCustomerListProfitAndLoss(StringUtil.nullToEmpty(resSql.getCustomerListProfitAndLoss()));
            // 総資産の合計
            resDto.setTotalAssets(StringUtil.nullToEmpty(resSql.getTotalAssets()));
            // 手数料累計の合計(年間手数料用)
            resDto.setCommTotalAmountOfYear(StringUtil.nullToEmpty(resSql.getCommTotalAmountOfYear()));
            // 手数料累計の合計(累計手数料用)
            resDto.setCommTotalAmount(StringUtil.nullToEmpty(resSql.getCommTotalAmount()));
            // 閲覧可能部店コード
            resDto.setViewAblrButenCode(StringUtil.nullToEmpty(resSql.getViewAblrButenCode()));
            // 評価額（円貨）の合計
            resDto.setValuationTotalJpyAmount(StringUtil.nullToEmpty(resSql.getValuationTotalJpyAmount()));
            // 電子交付承諾日付
            resDto.setElectronicDocConsentDate(StringUtil.nullToEmpty(resSql.getElectronicDocConsentDate()));
            // NISA口座表示情報
            resDto.setNisaContractKbnViewInfo(
                    StringUtil.nullToEmpty(resSql.getNisaContractKbnViewInfo()));
            // 外国証券取引口座表示情報
            resDto.setForeignSecurityAccountViewInfo(
                    StringUtil.nullToEmpty(resSql.getForeignSecurityAccountViewInfo()));
            // 変更完了日時
            resDto.setChangeFinishDateTime(StringUtil.nullToEmpty(resSql.getChangeFinishDateTime()));
            // 取引制限
            resDto.setTradeRestriction(StringUtil.nullToEmpty(resSql.getTradeRestriction()));
            // 「法人区分」全て以外の場合は、次の処理を行う
            if (!SELECT_ALL.equals(dtoReq.getPersonalCorporation())) {
                // 個人の場合
                if (PERSONAL_KBN.equals(resSql.getCorporationKbn())) {
                    // 投資方針
                    resDto.setInvestmentPlan(mCodeMap.get(INVESTMENTPLAN_0).get(resSql.getInvestmentPlan()));
                    // 職業
                    resDto.setOccupation(mCodeMap.get(OCCUPATION_0).get(resSql.getOccupation()));
                    // 資金性格
                    resDto.setFundCharacter(mCodeMap.get(FUNDCHARACTER_0).get(resSql.getFundCharacter()));
                    // 法人の場合
                } else if (CORPORATION_KBN.equals(resSql.getCorporationKbn())) {
                    // 投資方針
                    resDto.setInvestmentPlan(mCodeMap.get(INVESTMENTPLAN_1).get(resSql.getInvestmentPlan()));
                    // 職業
                    resDto.setOccupation(mCodeMap.get(OCCUPATION_1).get(resSql.getOccupation()));
                    // 資金性格
                    resDto.setFundCharacter(mCodeMap.get(FUNDCHARACTER_1).get(resSql.getFundCharacter()));
                }
                // 年収
                resDto.setAnnualIncome(mCodeMap.get(ANNUALINCOME_0).get(resSql.getAnnualIncome()));
                // 資産運用期間
                resDto.setEmploymentPeriod(mCodeMap.get(EMLOYMENTPERIOD_2).get(resSql.getEmploymentPeriod()));
                // 金融資産
                resDto.setFinancialAssets(mCodeMap.get(FINANCIALASSETS_0).get(resSql.getFinancialAssets()));
                // 主な収入源
                resDto.setIncomeForm(mCodeMap.get(INCOMEFORM_0).get(resSql.getIncomeForm()));
                // 投資経験項目設定
                // 投資経験有無(株式現物)
                resDto.setStockExpKbn(getInvestmentExpKbn(resSql.getStockExpKbn()));
                // 投資経験年数(株式現物)
                resDto.setStockExp(getInvestmentExp(resSql.getStockExp()));
                // 投資経験有無(債券)
                resDto.setDebentureExpKbn(getInvestmentExpKbn(resSql.getDebentureExpKbn()));
                // 投資経験年数(債券)
                resDto.setDebentureExp(getInvestmentExp(resSql.getDebentureExp()));
                // 投資経験有無(転換社債)
                resDto.setCbExpKbn(getInvestmentExpKbn(resSql.getCbExpKbn()));
                // 投資経験年数(転換社債)
                resDto.setCbExp(getInvestmentExp(resSql.getCbExp()));
                // 投資経験有無(株式信用)
                resDto.setMarginExpKbn(getInvestmentExpKbn(resSql.getMarginExpKbn()));
                // 投資経験年数(株式信用)
                resDto.setMarginExp(getInvestmentExp(resSql.getMarginExp()));
                // 投資経験有無(ワラント)
                resDto.setWarrantExpKbn(getInvestmentExpKbn(resSql.getWarrantExpKbn()));
                // 投資経験年数(ワラント)
                resDto.setWarrantExp(getInvestmentExp(resSql.getWarrantExp()));
                // 投資経験有無(先物・ＯＰ)
                resDto.setFutureopExpKbn(getInvestmentExpKbn(resSql.getFutureopExpKbn()));
                // 投資経験年数(先物・ＯＰ)
                resDto.setFutureopExp(getInvestmentExp(resSql.getFutureopExp()));
                // 投資経験有無(貯蓄型投信)
                resDto.setSavedtypefundExpKbn(getInvestmentExpKbn(resSql.getSavedtypefundExpKbn()));
                // 投資経験年数(貯蓄型投信)
                resDto.setSavedtypefundExp(getInvestmentExp(resSql.getSavedtypefundExp()));
                // 投資経験有無(外国証券)
                resDto.setForeignExpKbn(getInvestmentExpKbn(resSql.getForeignExpKbn()));
                // 投資経験年数(外国証券)
                resDto.setForeignExp(getInvestmentExp(resSql.getForeignExp()));
                // 投資経験有無(その他投信)
                resDto.setOtherfundExpKbn(getInvestmentExpKbn(resSql.getOtherfundExpKbn()));
                // 投資経験年数(その他投信)
                resDto.setOtherfundExp(getInvestmentExp(resSql.getOtherfundExp()));
            }

            dtoResList.add(resDto);
        }

        dtoRes.setDataList(dtoResList);

        if (VIEW_LIMIT_VALUE < totalRow) { // 5000件超過ワーニング
            dtoRes = IfaCommonUtil.createDataList(
                    dtoResList,
                    ErrorLevel.WARNING,
                    WARNING_DATALIST_OVER_MAX_ROWNUM,
                    IfaCommonUtil.getMessage(
                            WARNING_DATALIST_OVER_MAX_ROWNUM,
                            new String[] {
                                    String.format("%,d", VIEW_LIMIT_VALUE),
                                    String.format("%,d", totalRow)
                            }
                    )
            );
            dtoRes.setOverMaxRownum(true);
            dtoRes.setMaxRownum(VIEW_LIMIT_VALUE);
            dtoRes.setTotalSize(totalRow);

            return dtoRes;
        }

        // 正常終了
        dtoRes = IfaCommonUtil.createDataList(
                dtoResList,
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                SUCCESS_MESSAGE
        );
        dtoRes.setMaxRownum(totalRow);
        dtoRes.setTotalSize(totalRow);

        return dtoRes;
    }


    /**
     * アクションID：A005
     * アクション名：CSV出力
     * Dto リクエスト：IfaCustomerListA005DtoRequest
     * Dto レスポンス：IfaCustomerListA005DtoResponse
     * model リクエスト：IfaCustomerListSql002RequestModel
     * model レスポンス：IfaCustomerListSql002ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return IfaCustomerListA005ResponseDto
     * @exception Exception 例外
     */
    public DataList<IfaCustomerListA005ResponseDto> csvOutputA005(IfaCustomerListA005RequestDto dtoReq, String sessionId)
            throws Exception {

        DataList<IfaCustomerListA005ResponseDto> dtoRes = new DataList<IfaCustomerListA005ResponseDto>();
        List<IfaCustomerListA005ResponseDto> dtoResList = new ArrayList<IfaCustomerListA005ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCustomerListServiceImplL.csvOutputA005");
        }

        // ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        // 仲介業者コードと営業員コードの参照可能チェックを行う。
        OutputFct030Dto fct030ResDto = new OutputFct030Dto();

        // SBI証券本店ではない場合にチェックを行う。
        if (!StringUtils.equals(userAccount.getPrivId(), SBI_HEAD_OFFICE)) {
            fct030ResDto = fct030.getData(null);
            // FCT030.仲介業者担当者リストの件数が0件の場合
            if (fct030ResDto.getBrokerChargeList().isEmpty() || fct030ResDto.getBrokerChargeList().size() == 0) {
                return IfaCommonUtil.createDataList(dtoResList, ErrorLevel.FATAL, FCT030_NOT_EXIST,
                        IfaCommonUtil.getMessage(FCT030_NOT_EXIST, new String[] {}));
            }
        }
        // CSVダウンロード件数取得
        InputFct038Dto in038Dto = new InputFct038Dto();
        in038Dto.setScreenId("SUB0201_01-01");
        in038Dto.setUserAuthority(userAccount.getPrivId());
        OutputFct038Dto out038Dto = new OutputFct038Dto();
        out038Dto = fct038.getData(in038Dto);

        // SQLレスポンスの作成
        IfaCustomerListSql001RequestModel selSql001Req = new IfaCustomerListSql001RequestModel();
        // 「法人区分」全て以外の場合は、次の処理を行う
        List<MCode> mCodeList = new ArrayList<>();
        Map<String, Map<String, String>> mCodeMap = new HashMap<>();
        if (!SELECT_ALL.equals(dtoReq.getPersonalCorporation())) {
            // 法人区分の下のドロップダウンボックス項目取得する
            mCodeList = mcodeService.getMCodeList(CODE_TYPE);
            // 法人区分の下のドロップダウンボックス項目MAP作成("INVESTMENTPLAN_0" = ["01","利回り・安定重視"])
            mCodeMap = mCodeList.stream()
                    .collect(Collectors.groupingBy(MCode::getCode1, Collectors.toMap(MCode::getCode2, MCode::getName)));
        }
        // SQLパラメータの設定
        selSql001Req = setSql001Parameter(dtoReq.getBrokerCodeList(), dtoReq.getChkBrokerCodeExclude(),
                dtoReq.getBranchCode(), dtoReq.getEmpCode(), dtoReq.getButenCode(), dtoReq.getAccountNumber(),
                dtoReq.getCustomerNameKanjiKana(), dtoReq.getCustomerNameConditionList(), dtoReq.getCourse(),
                dtoReq.getTradeRestrictionCheckbox(), dtoReq.getComplianceLankFrom(), dtoReq.getComplianceLankTo(),
                dtoReq.getAdress(), dtoReq.getAdressConditionList(), dtoReq.getCorporatePhoneNumber(),
                dtoReq.getAgeFrom(), dtoReq.getAgeTo(), dtoReq.getBirthDateFrom(), dtoReq.getBirthDateTo(),
                dtoReq.getOpenAccountFrom(), dtoReq.getOpenAccountTo(), dtoReq.getButenCodeArray(),
                dtoReq.getValuationFrom(), dtoReq.getValuationTo(), dtoReq.getValuationConditionList(),
                dtoReq.getCommTotalList(), dtoReq.getCommTotalPeriodFrom(), dtoReq.getCommTotalPeriodTo(),
                dtoReq.getCommTotalAmountFrom(), dtoReq.getCommTotalAmountTo(),
                dtoReq.getCommTotalAmountConditionList(), dtoReq.getLastTradeDateFrom(), dtoReq.getLastTradeDateTo(), dtoReq.getLastTradeDateDisplay(),
                dtoReq.getNisaContractKbnList(), dtoReq.getCourseChangeFinishDateFrom(), dtoReq.getCourseChangeFinishDateTo(),
                dtoReq.getForeignSecurityAccountList(), dtoReq.getPersonalCorporation(), dtoReq.getInvestmentPlan(),
                dtoReq.getFundCharacter(), dtoReq.getIncomeForm(), dtoReq.getEmploymentPeriod(),
                dtoReq.getAnnualIncome(), dtoReq.getFinancialAssets(), dtoReq.getOccupation(),
                dtoReq.getInvestmentExp(), dtoReq.getInvestmentExpDisplay(),
                dtoReq.getNisaContractKbnDisplay(), mCodeList, userAccount.getPrivId(),
                fct030ResDto);
        // 上限件数の設定
        int maxCsvRow = out038Dto.getCsvDownloadNum();
        selSql001Req.setSearchLimitRow(maxCsvRow);

        // SQL001実行
        DataList<IfaCustomerListSql001ResponseModel> selSql001Res = dao.selectIfaCustomerListSql001(selSql001Req);

        // SQLの実行結果が0件の場合はCSVファイルを出力せず終了する
        if (CollectionUtils.isEmpty(selSql001Res.getDataList())) {
            // 0件の場合、SQL実行結果を返却する（エラーレベル、エラーメッセージはコントローラにて設定）
            dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, SUCCESS_MESSAGE, SUCCESS_MESSAGE);
            return dtoRes;
        }
        int totalCount = Integer.parseInt(selSql001Res.getDataList().get(0).getTotalRow());
        // 顧客一覧_基本のCSVユーティリティを呼出す
        //　コンプライアンスサービスは現行踏襲でそのまま定義
        IfaCustomerListCsvUtil csvOut = new IfaCustomerListCsvUtil(complianceService);
        // CSV出力を行うためのDTO定義でDataListを定義する
        // ModelBaseクラスを継承する必要あり
        DataList<IfaCustomerListSql001ResponseModelCsvOutModel> exportList = new DataList<IfaCustomerListSql001ResponseModelCsvOutModel>();
        // SQLの実行結果をCSV出力が出来るように詰め替え処理を行う
        // A002で行っているSQL実行結果をresposeDtoに詰め替えている処理と同様
        exportList.setDataList(convertCsvOutModel(selSql001Res, mCodeMap, dtoReq.getPersonalCorporation()));

        // DataListに最終的に設定するエラーレベルなどはController側で設定する(BaseController.setStatusAndMessageToDataList()で使用)る
        dtoRes.setDataList(dtoResList);
        dtoRes.setErrorLevel(0);
        dtoRes.setMaxRownum(out038Dto.getCsvDownloadNum());
        dtoRes.setOverMaxRownum(true);
        // titleにCSVファイルのフルパス名をセット
        dtoRes.setTitle(csvOut.doCreateCsvFile(exportList, sessionId, userAccount.getUserId(), null, dtoReq));
        // ここでは分析関数で取得した件数をTotalsizeに設定
        dtoRes.setTotalSize(totalCount);
        return dtoRes;
    }

    /**
     * アクションID：A006
     * アクション名：BB申込
     * Dto リクエスト：IfaCustomerListA006DtoRequest
     * Dto レスポンス：IfaCustomerListA006DtoResponse
     * model リクエスト：IfaCustomerListSql003RequestModel
     * model レスポンス：IfaCustomerListSql003ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return IfaCustomerListA006ResponseDto
     * @exception Exception 例外
     */
    public DataList<IfaCustomerListA006ResponseDto> bbApplyA006(IfaCustomerListA006RequestDto dtoReq) throws Exception {

        DataList<IfaCustomerListA006ResponseDto> dtoRes = new DataList<IfaCustomerListA006ResponseDto>();
        List<IfaCustomerListA006ResponseDto> dtoResList = new ArrayList<IfaCustomerListA006ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCustomerListServiceImplL.bbApplyA006");
        }

        // サービス閉塞チェックで、サービス閉塞状態を判定する。
        Boolean status = serviceStatusService.getServiceStatus(NRI_API);
        // サービス閉塞状態がfalse（閉塞）の場合、メッセージを表示して処理を終了する。
        if (!status) {
            return IfaCommonUtil.createDataList(dtoResList, ErrorLevel.FATAL, RETURN_CODE_W0006,
                    IfaCommonUtil.getMessage(RETURN_CODE_W0006));
        }
        // サービス閉塞状態がtrue（解放）の場合、以下の処理を継続する。

        // 入力チェックは画面側で実施の想定
        // ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        OutputFct030Dto fct030ResDto = new OutputFct030Dto();
        // SBI証券本店ではない場合にチェックを行う。
        if (!StringUtils.equals(userAccount.getPrivId(), SBI_HEAD_OFFICE)) {
            fct030ResDto = fct030.getData(null);
            // FCT030.仲介業者担当者リストの件数が0件の場合
            if (fct030ResDto.getBrokerChargeList().isEmpty() || fct030ResDto.getBrokerChargeList().size() == 0) {
                return IfaCommonUtil.createDataList(dtoResList, ErrorLevel.FATAL, FCT030_NOT_EXIST,
                        IfaCommonUtil.getMessage(FCT030_NOT_EXIST, new String[] {}));
            }
        }

        IfaCustomerListSql003RequestModel selSql003Req = new IfaCustomerListSql003RequestModel();
        selSql003Req.setAccountNumber(dtoReq.getAccountNumber());
        selSql003Req.setButenCode(dtoReq.getButenCode());
        selSql003Req.setBrokerCode(dtoReq.getBrokerCode());
        selSql003Req.setEmpCode(dtoReq.getEmpCode());
        if (!CollectionUtils.isEmpty(fct030ResDto.getBrokerChargeList())) {
            List<IfaCustomerListSql003RequestModel.BrokerCharge> chargeList = new ArrayList<IfaCustomerListSql003RequestModel.BrokerCharge>();
            for (BrokerCharge resDto : fct030ResDto.getBrokerChargeList()) {
                IfaCustomerListSql003RequestModel.BrokerCharge charge = new IfaCustomerListSql003RequestModel.BrokerCharge();
                charge.setBrokerCode(resDto.getBrokerCode());
                charge.setEmpCode(resDto.getEmpCode());
                chargeList.add(charge);
            }
            selSql003Req.setBrokerChargeList(chargeList);
        }
        DataList<IfaCustomerListSql003ResponseModel> selSql003Res = dao.selectIfaCustomerListSql003(selSql003Req);
        // checkStyleに伴うからメソッド
        if (selSql003Res.getDataList().size() == 0) {
            return dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.FATAL, A006_NOT_EXIST, IfaCommonUtil
                    .getMessage(A006_NOT_EXIST, new String[] { dtoReq.getButenCode(), dtoReq.getAccountNumber() }));
        }
        dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, SUCCESS_MESSAGE, SUCCESS_MESSAGE);
        return dtoRes;
    }

    /**
     * 取得したSQLの結果をCSV出力機能にあわせたクラス名のDataListに変換する.
     *
     * @param res                 SQL001実行結果
     * @param mCodeMap            法人区分の下のドロップダウンボックス項目MAP
     * @param personalCorporation 法人区分
     * @return CSV出力データモデル
     */
    private List<IfaCustomerListSql001ResponseModelCsvOutModel> convertCsvOutModel(
            DataList<IfaCustomerListSql001ResponseModel> res, Map<String, Map<String, String>> mCodeMap,
            String personalCorporation) {

        List<IfaCustomerListSql001ResponseModelCsvOutModel> csvList = new ArrayList<IfaCustomerListSql001ResponseModelCsvOutModel>();

        for (IfaCustomerListSql001ResponseModel sqlRes : res.getDataList()) {

            IfaCustomerListSql001ResponseModelCsvOutModel csvData = new IfaCustomerListSql001ResponseModelCsvOutModel();

            // コンプラランク
            csvData.setTcCompRank(StringUtil.nullToEmpty(sqlRes.getTcCompRank()));
            // 契約締結前交付書面コード名
            csvData.setCustomerAttributeName(StringUtil.nullToEmpty(sqlRes.getCustomerAttributeName()));
            // 顧客名（カナ）
            csvData.setCustomerNameKana(StringUtil.nullToEmpty(sqlRes.getCustomerNameKana()));
            // 顧客名（漢字）
            csvData.setCustomerNameKanji(StringUtil.nullToEmpty(sqlRes.getCustomerNameKanji()));
            // 口座開設日
            csvData.setOpenAcctDate(StringUtil.nullToEmpty(sqlRes.getOpenAcctDate()));
            // 口座番号
            csvData.setAccountNumber(StringUtil.nullToEmpty(sqlRes.getAccountNumber()));
            // 郵便番号
            csvData.setZipCodeBeforeAndAfter(StringUtil.nullToEmpty(sqlRes.getZipCodeBeforeAndAfter()));
            // 住所
            csvData.setAdressKanji(StringUtil.nullToEmpty(sqlRes.getAdressKanji()));
            // 仲介業者コード
            csvData.setBrokerCode(StringUtil.nullToEmpty(sqlRes.getBrokerCode()));
            // 仲介業者営業員コード
            csvData.setBrokerChargeCode(StringUtil.nullToEmpty(sqlRes.getBrokerChargeCode()));
            // 電話番号
            csvData.setCorporatePhoneNumber(StringUtil.nullToEmpty(sqlRes.getCorporatePhoneNumber()));
            // 生年月日
            csvData.setCorBirthFlg(StringUtil.nullToEmpty(sqlRes.getCorBirthFlg()));
            // 年齢
            csvData.setAge(StringUtil.nullToEmpty(sqlRes.getAge()));
            // 部店コード
            csvData.setButenCode(StringUtil.nullToEmpty(sqlRes.getButenCode()));
            // 仲介業者支店名
            csvData.setBranchNameOfBranch(StringUtil.nullToEmpty(sqlRes.getBranchNameOfBranch()));
            // 仲介業者支店名
            csvData.setBranchNameOfBroker(StringUtil.nullToEmpty(sqlRes.getBranchNameOfBroker()));
            // 担当者名
            csvData.setChargeName(StringUtil.nullToEmpty(sqlRes.getChargeName()));
            // 仲介業者支店コード
            csvData.setSubBrokerId(StringUtil.nullToEmpty(sqlRes.getSubBrokerId()));
            // 最終約定日
            csvData.setLastTradeDate(StringUtil.nullToEmpty(sqlRes.getLastTradeDate()));
            // 評価損益
            csvData.setCustomerListProfitAndLoss(StringUtil.nullToEmpty(sqlRes.getCustomerListProfitAndLoss()));
            // 総資産の合計
            csvData.setTotalAssets(StringUtil.nullToEmpty(sqlRes.getTotalAssets()));
            // 手数料累計の合計(年間手数料用)
            csvData.setCommTotalAmountOfYear(StringUtil.nullToEmpty(sqlRes.getCommTotalAmountOfYear()));
            // 手数料累計の合計(累計手数料用)
            csvData.setCommTotalAmount(StringUtil.nullToEmpty(sqlRes.getCommTotalAmount()));
            // 閲覧可能部店コード
            csvData.setViewAblrButenCode(StringUtil.nullToEmpty(sqlRes.getViewAblrButenCode()));
            // NISA口座表示情報
            csvData.setNisaContractKbnViewInfo(
                    StringUtil.nullToEmpty(sqlRes.getNisaContractKbnViewInfo()));
            // 評価額（円貨）の合計
            csvData.setValuationTotalJpyAmount(StringUtil.nullToEmpty(sqlRes.getValuationTotalJpyAmount()));
            // 電子交付承諾日付
            csvData.setElectronicDocConsentDate(StringUtil.nullToEmpty(sqlRes.getElectronicDocConsentDate()));
            // 外国証券取引口座表示情報
            csvData.setForeignSecurityAccountViewInfo(
                    StringUtil.nullToEmpty(sqlRes.getForeignSecurityAccountViewInfo()));
            // 変更完了日時
            csvData.setChangeFinishDateTime(StringUtil.nullToEmpty(sqlRes.getChangeFinishDateTime()));
            // 取引制限
            csvData.setTradeRestriction(StringUtil.nullToEmpty(sqlRes.getTradeRestriction()));
            // 「法人区分」全て以外の場合は、次の処理を行う
            if (!SELECT_ALL.equals(personalCorporation)) {
                // 個人の場合
                if (PERSONAL_KBN.equals(sqlRes.getCorporationKbn())) {
                    // 投資方針
                    csvData.setInvestmentPlan(mCodeMap.get(INVESTMENTPLAN_0).get(sqlRes.getInvestmentPlan()));
                    // 職業
                    csvData.setOccupation(mCodeMap.get(OCCUPATION_0).get(sqlRes.getOccupation()));
                    // 資金性格
                    csvData.setFundCharacter(mCodeMap.get(FUNDCHARACTER_0).get(sqlRes.getFundCharacter()));
                    // 法人の場合
                } else if (CORPORATION_KBN.equals(sqlRes.getCorporationKbn())) {
                    // 投資方針
                    csvData.setInvestmentPlan(mCodeMap.get(INVESTMENTPLAN_1).get(sqlRes.getInvestmentPlan()));
                    // 職業
                    csvData.setOccupation(mCodeMap.get(OCCUPATION_1).get(sqlRes.getOccupation()));
                    // 資金性格
                    csvData.setFundCharacter(mCodeMap.get(FUNDCHARACTER_1).get(sqlRes.getFundCharacter()));
                }
                // 年収
                csvData.setAnnualIncome(mCodeMap.get(ANNUALINCOME_0).get(sqlRes.getAnnualIncome()));
                // 資産運用期間
                csvData.setEmploymentPeriod(mCodeMap.get(EMLOYMENTPERIOD_2).get(sqlRes.getEmploymentPeriod()));
                // 金融資産
                csvData.setFinancialAssets(mCodeMap.get(FINANCIALASSETS_0).get(sqlRes.getFinancialAssets()));
                // 主な収入源
                csvData.setIncomeForm(mCodeMap.get(INCOMEFORM_0).get(sqlRes.getIncomeForm()));
                // 投資経験項目設定
                // 投資経験有無(株式現物)
                csvData.setStockExpKbn(getInvestmentExpKbn(sqlRes.getStockExpKbn()));
                // 投資経験年数(株式現物)
                csvData.setStockExp(getInvestmentExp(sqlRes.getStockExp()));
                // 投資経験有無(債券)
                csvData.setDebentureExpKbn(getInvestmentExpKbn(sqlRes.getDebentureExpKbn()));
                // 投資経験年数(債券)
                csvData.setDebentureExp(getInvestmentExp(sqlRes.getDebentureExp()));
                // 投資経験有無(転換社債)
                csvData.setCbExpKbn(getInvestmentExpKbn(sqlRes.getCbExpKbn()));
                // 投資経験年数(転換社債)
                csvData.setCbExp(getInvestmentExp(sqlRes.getCbExp()));
                // 投資経験有無(株式信用)
                csvData.setMarginExpKbn(getInvestmentExpKbn(sqlRes.getMarginExpKbn()));
                // 投資経験年数(株式信用)
                csvData.setMarginExp(getInvestmentExp(sqlRes.getMarginExp()));
                // 投資経験有無(ワラント)
                csvData.setWarrantExpKbn(getInvestmentExpKbn(sqlRes.getWarrantExpKbn()));
                // 投資経験年数(ワラント)
                csvData.setWarrantExp(getInvestmentExp(sqlRes.getWarrantExp()));
                // 投資経験有無(先物・ＯＰ)
                csvData.setFutureopExpKbn(getInvestmentExpKbn(sqlRes.getFutureopExpKbn()));
                // 投資経験年数(先物・ＯＰ)
                csvData.setFutureopExp(getInvestmentExp(sqlRes.getFutureopExp()));
                // 投資経験有無(貯蓄型投信)
                csvData.setSavedtypefundExpKbn(getInvestmentExpKbn(sqlRes.getSavedtypefundExpKbn()));
                // 投資経験年数(貯蓄型投信)
                csvData.setSavedtypefundExp(getInvestmentExp(sqlRes.getSavedtypefundExp()));
                // 投資経験有無(外国証券)
                csvData.setForeignExpKbn(getInvestmentExpKbn(sqlRes.getForeignExpKbn()));
                // 投資経験年数(外国証券)
                csvData.setForeignExp(getInvestmentExp(sqlRes.getForeignExp()));
                // 投資経験有無(その他投信)
                csvData.setOtherfundExpKbn(getInvestmentExpKbn(sqlRes.getOtherfundExpKbn()));
                // 投資経験年数(その他投信)
                csvData.setOtherfundExp(getInvestmentExp(sqlRes.getOtherfundExp()));
            }
            csvList.add(csvData);

        }

        return csvList;

    }

    /**
     * SQL001 のリクエストパラメータを設定する。
     * A002、A005で同じSQLを実行するが、リクエストパラメータの内容は同じであるものの型が異なるためリクエストパラメータから取得した値を引数として設定する
     *
     * @param brokerCodeList               仲介業者コード(複数入力可)
     * @param chkBrokerCodeExclude         仲介業者除外チェック(true or false)
     * @param branchCode                   支店コード
     * @param empCode                      営業員コード(画面入力値)
     * @param butenCode                    部店コード
     * @param accountNumber                口座番号
     * @param customerNameKanjiKana        顧客名(漢字／カナ)
     * @param customerNameConditionList    顧客名(条件リスト：と等しい、で始まる、 を含む)
     * @param course                       取引コース
     * @param tradeRestrictionCheckbox     取引制限ありチェックボックス
     * @param complianceLankFrom           コンプラランク(From)
     * @param complianceLankTo             コンプラランク(To)
     * @param adress                       住所
     * @param adressConditionList          住所（条件リスト：と等しい、で始まる、を含む）
     * @param corporatePhoneNumber         電話番号
     * @param ageFrom                      年齢(From)
     * @param ageTo                        年齢(To)
     * @param birthDateFrom                生年月日(From)
     * @param birthDateTo                  生年月日(To)
     * @param openAccountFrom              口座開設日(From)
     * @param openAccountTo                口座開設日(To)
     * @param butenCodeArray               閲覧可能部店(複数入力)
     * @param valuationFrom                評価額(From)
     * @param valuationTo                  評価額(To)
     * @param valuationConditionList       評価額(条件リスト：設計書参照)
     * @param commTotalList                手数料累計(ラジオボタンの値)
     * @param commTotalPeriodFrom          手数料累計期間(From)
     * @param commTotalPeriodTo            手数料累計期間(To)
     * @param commTotalAmountFrom          手数料累計額(From)
     * @param commTotalAmountTo            手数料累計額(To)
     * @param commTotalAmountConditionList 手数料累計額（条件リスト：設計書参照）
     * @param lastTradeDateFrom            最終約定日（From）
     * @param lastTradeDateTo              最終約定日（To）
     * @param lastTradeDateDisplay         最終約定日表示（チェック）
     * @param nisaContractKbnList          NISA口座
     * @param courseChangeFinishDateFrom   コース変更完了日（From）
     * @param courseChangeFinishDateTo     コース変更完了日（To）
     * @param foreignSecurityAccountList   外国証券取引口座
     * @param personalCorporation          法人区分
     * @param investmentPlan               投資の方針
     * @param fundCharacter                資金の性格
     * @param incomeForm                   主な収入源
     * @param employmentPeriod             資産運用期間
     * @param annualIncome                 年収
     * @param financialAssets              金融資産
     * @param occupation                   職業
     * @param investmentExp                投資経験
     * @param investmentExpDisplay         投資経験（チェック）
     * @param nisaContractKbnDisplay       NISA口座表示（チェック）
     * @param mCodeList                    法人区分の下のドロップダウンボックス項目
     * @param privId                       ユーザ共通情報.権限コード
     * @param fct030ResDto                 仲介業者営業員
     * @return SQL001実行のパラメータ
     */
    private IfaCustomerListSql001RequestModel setSql001Parameter(List<String> brokerCodeList,
            String chkBrokerCodeExclude, String branchCode, String empCode, String butenCode, String accountNumber,
            String customerNameKanjiKana, String customerNameConditionList, List<IfaCustomerListTradingCourse> course,
            String tradeRestrictionCheckbox, String complianceLankFrom, String complianceLankTo, String adress,
            String adressConditionList, String corporatePhoneNumber, String ageFrom, String ageTo, String birthDateFrom,
            String birthDateTo, String openAccountFrom, String openAccountTo, List<String> butenCodeArray,
            String valuationFrom, String valuationTo, String valuationConditionList, String commTotalList,
            String commTotalPeriodFrom, String commTotalPeriodTo, String commTotalAmountFrom, String commTotalAmountTo,
            String commTotalAmountConditionList, String lastTradeDateFrom, String lastTradeDateTo, String lastTradeDateDisplay,
            String nisaContractKbnList, String courseChangeFinishDateFrom, String courseChangeFinishDateTo, 
            String foreignSecurityAccountList, String personalCorporation, 
            List<IfaCustomerListPersonalCorporation> investmentPlan, 
            List<IfaCustomerListPersonalCorporation> fundCharacter, List<IfaCustomerListPersonalCorporation> incomeForm,
            List<IfaCustomerListPersonalCorporation> employmentPeriod,
            List<IfaCustomerListPersonalCorporation> annualIncome,
            List<IfaCustomerListPersonalCorporation> financialAssets,
            List<IfaCustomerListPersonalCorporation> occupation, List<IfaCustomerListPersonalCorporation> investmentExp,
            String investmentExpDisplay, String nisaContractKbnDisplay, List<MCode> mCodeList, String privId,
            OutputFct030Dto fct030ResDto) {

        IfaCustomerListSql001RequestModel selSql001Req = new IfaCustomerListSql001RequestModel();

        // 仲介業者コードの入力がある場合に仲介業者リストを設定する
        if (!CollectionUtils.isEmpty(brokerCodeList)) {
            if (!brokerCodeList.get(0).isEmpty()) {
                selSql001Req.setBrokerCodeList(brokerCodeList);
            }
        }
        // 仲介業者除外チェックボックスのチェック有無判定
        if (StringUtils.equals(chkBrokerCodeExclude, "true")) {
            selSql001Req.setBrokerCodeExcludeCheckbox("1");
        } else {
            selSql001Req.setBrokerCodeExcludeCheckbox("0");
        }

        String searchName = VALUE_EMPTY;

        // ORACLEではLIKE関数で"%"を使用した正規表現の検索は不可のため、REGEXP関数を使用する
        // 現行では拗音、促音の考慮がないため、LIKE関数でワイルドカードを使用した検索となっている模様
        // 結果的に業務要件が満たせればよいと判断し、正規表現の検索条件を作成する
        // 顧客名条件による分岐処理
        if (!StringUtils.equals(customerNameKanjiKana, VALUE_EMPTY)) {

            searchName = convertCustomerName(toFullWidth(customerNameKanjiKana));
            // 「と等しい」の場合
            if (StringUtils.equals(customerNameConditionList, CustomerNameConditionList.EQUAL_TO.getValue())) {
                // 完全一致となるように整形
                searchName = String.format("^%s$", searchName);
                // 「を含む」の場合
            } else if (StringUtils.equals(customerNameConditionList, CustomerNameConditionList.INCLUDING.getValue())) {
                // 部分一致となるように整形
                searchName = String.format(".*%s", searchName);
                // 「で始まる」の場合
            } else if (StringUtils.equals(customerNameConditionList,
                    CustomerNameConditionList.STARTS_WITH.getValue())) {
                // 前方一致となるように整形
                searchName = String.format("^%s", searchName);
            }
        }
        selSql001Req.setCustomerNameKanjiKana(searchName);
        selSql001Req.setCustomerNameSearchMethod(customerNameConditionList);

        String searchAddress = VALUE_EMPTY;
        // 住所条件による分岐処理
        if (!StringUtils.equals(adress, VALUE_EMPTY)) {
            // 等しい以外の場合
            if (!StringUtils.equals(adressConditionList, AddressConditionList.EQUAL_TO.getValue())) {
                // を含むの場合
                if (StringUtils.equals(adressConditionList, AddressConditionList.INCLUDING.getValue())) {
                    searchAddress = String.format("%%%s%%", adress);
                } else if (StringUtils.equals(adressConditionList, AddressConditionList.STARTS_WITH.getValue())) {
                    searchAddress = String.format("%s%%", adress);
                }
            } else {
                // 等しいの場合
                searchAddress = adress;
            }
        }
        selSql001Req.setAdress(searchAddress);
        selSql001Req.setAdressConditionList(adressConditionList);
        // 閲覧可能部店の設定
        if (!CollectionUtils.isEmpty(butenCodeArray)) {
            if (!butenCodeArray.get(0).isEmpty()) {
                selSql001Req.setButenCodeArray(butenCodeArray);
            }
        }
        // コース変更完了日FROMの設定
        if (!StringUtils.equals(courseChangeFinishDateFrom, VALUE_EMPTY)) {
            selSql001Req.setCourseChangeFinishDateFrom(courseChangeFinishDateFrom + "000000");
        } else {
            selSql001Req.setCourseChangeFinishDateFrom(VALUE_EMPTY);
        }
        // コース変更完了日TOの設定
        if (!StringUtils.equals(courseChangeFinishDateTo, VALUE_EMPTY)) {
            selSql001Req.setCourseChangeFinishDateTo(courseChangeFinishDateTo + "235959");
        } else {
            selSql001Req.setCourseChangeFinishDateTo(VALUE_EMPTY);
        }

        String periodFrom = VALUE_EMPTY;
        String periodTo = VALUE_EMPTY;

        Calendar calendar = Calendar.getInstance();

        //日付をyyyyMMddの形で出力する
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        // 手数料累計のラジオボタンで選択された条件に紐づけたSQLへ引き渡すパラメータを作成する
        if (StringUtils.equals(commTotalList, CommTotalListCondition.CURRENT_MONTH.getValue())) {
            periodFrom = sdf.format(calendar.getTime());
            periodTo = sdf.format(calendar.getTime());
        } else if (StringUtils.equals(commTotalList, CommTotalListCondition.LAST_MONTH.getValue())) {
            calendar.add(Calendar.MONTH, -1);
            periodFrom = sdf.format(calendar.getTime());
            periodTo = sdf.format(calendar.getTime());
        } else if (StringUtils.equals(commTotalList, CommTotalListCondition.PREVIOUS_MONTH.getValue())) {
            calendar.add(Calendar.MONTH, -2);
            periodFrom = sdf.format(calendar.getTime());
            periodTo = sdf.format(calendar.getTime());
        } else if (StringUtils.equals(commTotalList, CommTotalListCondition.PERIOD_SPECIFICATION.getValue())) {
            periodFrom = commTotalPeriodFrom;
            periodTo = commTotalPeriodTo;
        }

        // 手数料累計期間
        selSql001Req.setCommTotalPeriodFrom(periodFrom);
        selSql001Req.setCommTotalPeriodTo(periodTo);

        // 取引コースの設定
        // TODO 10/22 AP基盤のバグにより、通常の呼び出しでは独自クラスのリストが生成できない状態。テスト用コントローラで実行しなければエラーとなる
        boolean allSelectFlg = true;
        List<String> searchCourseList = new ArrayList<String>();

        for (IfaCustomerListTradingCourse tradingCourse : course) {
            if (StringUtils.equals(tradingCourse.getIsSelected(), "true")) {
                searchCourseList.add(tradingCourse.getId());
            } else {
                allSelectFlg = false;
            }
        }

        if (allSelectFlg) {
            selSql001Req.setTradeCourseList(new ArrayList<String>());
        } else {
            selSql001Req.setTradeCourseList(searchCourseList);
        }

        // FCT030の実行結果が存在する場合、仲介業者営業員リストを作成する
        if (!CollectionUtils.isEmpty(fct030ResDto.getBrokerChargeList())) {
            List<IfaCustomerListSql001RequestModel.BrokerCharge> chargeList = new ArrayList<IfaCustomerListSql001RequestModel.BrokerCharge>();
            for (BrokerCharge resDto : fct030ResDto.getBrokerChargeList()) {
                IfaCustomerListSql001RequestModel.BrokerCharge charge = new IfaCustomerListSql001RequestModel.BrokerCharge();
                charge.setBrokerCode(resDto.getBrokerCode());
                charge.setEmpCode(resDto.getEmpCode());
                chargeList.add(charge);
            }
            selSql001Req.setBrokerChargeList(chargeList);
        }

        // その他SQLパラメータの設定
        // 仲介業者営業員コード
        selSql001Req.setBrokerChargeCode(empCode);
        // 支店コードの設定
        selSql001Req.setBranchCode(branchCode);
        // 最終約定日FROMの設定
        selSql001Req.setLastTradeDateFrom(lastTradeDateFrom);
        // 最終約定日Toの設定
        selSql001Req.setLastTradeDateTo(lastTradeDateTo);
        // 最終約定日表示（チェック）の設定
        selSql001Req.setLastTradeDateDisplay(lastTradeDateDisplay);
        // 評価額FROMの設定
        selSql001Req.setValuationFrom(valuationFrom);
        // 評価額TOの設定
        selSql001Req.setValuationTo(valuationTo);
        // 評価額条件リストに紐づくTARGET_FLGの値の設定
        selSql001Req.setValuationConditionList(valuationConditionList);
        // 手数料累計額FROM
        selSql001Req.setCommTotalAmountFrom(commTotalAmountFrom);
        // 手数料累計額TO
        selSql001Req.setCommTotalAmountTo(commTotalAmountTo);
        // 年齢FROMの設定
        selSql001Req.setAgeFrom(ageFrom);
        // 年齢TOの設定
        selSql001Req.setAgeTo(ageTo);
        // 生年月日FROMの設定
        selSql001Req.setBirthDateFrom(birthDateFrom);
        // 生年月日TOの設定
        selSql001Req.setBirthDateTo(birthDateTo);
        // 取引制限ありチェックボックスの設定
        selSql001Req.setTradeRestrictionCheckbox(tradeRestrictionCheckbox);
        // 部店コードの設定
        selSql001Req.setButenCode(butenCode);
        // 口座番号の設定
        selSql001Req.setAccountNumber(accountNumber);
        // 電話番号の設定
        selSql001Req.setCorporatePhoneNumber(corporatePhoneNumber);
        // 部店コードの設定
        selSql001Req.setButenCode(butenCode);
        // 口座開設年月日FROMの設定
        selSql001Req.setOpenAccountFrom(openAccountFrom);
        // 口座開設年月日FROMTOの設定
        selSql001Req.setOpenAccountTo(openAccountTo);
        // NISA口座
        selSql001Req.setNisaContractKbnList(nisaContractKbnList);
        // 外国証券取引口座
        selSql001Req.setForeignSecurityAccountList(foreignSecurityAccountList);
        // 手数料累計
        selSql001Req.setCommTotalList(commTotalList);
        // 手数料累計額
        selSql001Req.setCommTotalAmountConditionList(commTotalAmountConditionList);
        // コンプラランク（From）入力の場合
        selSql001Req.setComplianceLankTo(complianceLankTo);
        // コンプラランク（To）入力の場合
        selSql001Req.setComplianceLankFrom(complianceLankFrom);
        // NISA口座の内容選択の判定
        if (nisaContractKbnList.isEmpty() && !TRUE.equals(nisaContractKbnDisplay)) {
            selSql001Req.setSelectNisaFlg(null);
        } else {
            selSql001Req.setSelectNisaFlg(TRUE);
        }
        // 法人区分
        selSql001Req.setPersonalCorporation(personalCorporation);
        // 「法人区分」全て以外の場合は、次の処理を行う
        if (!SELECT_ALL.equals(personalCorporation)) {
            // 投資方針
            List<String> investmentPlanList = investmentPlan.stream().filter(f -> f.getIsSelected().equals(TRUE))
                    .map(m -> m.getId()).collect(Collectors.toList());
            selSql001Req.setInvestmentPlanList(investmentPlanList);
            // 資金の性格
            List<String> fundCharacterList = fundCharacter.stream().filter(f -> f.getIsSelected().equals(TRUE))
                    .map(m -> m.getId()).collect(Collectors.toList());
            selSql001Req.setFundCharacterList(fundCharacterList);
            // 主な収入源
            List<String> incomeFormList = incomeForm.stream().filter(f -> f.getIsSelected().equals(TRUE))
                    .map(m -> m.getId()).collect(Collectors.toList());
            selSql001Req.setIncomeFormList(incomeFormList);
            // 資産運用期間
            List<String> employmentPeriodList = employmentPeriod.stream().filter(f -> f.getIsSelected().equals(TRUE))
                    .map(m -> m.getId()).collect(Collectors.toList());
            selSql001Req.setEmploymentPeriodList(employmentPeriodList);
            // 年収
            List<String> annualIncomeList = annualIncome.stream().filter(f -> f.getIsSelected().equals(TRUE))
                    .map(m -> m.getId()).collect(Collectors.toList());
            selSql001Req.setAnnualIncomeList(annualIncomeList);
            // 金融資産
            List<String> financialAssetsList = financialAssets.stream().filter(f -> f.getIsSelected().equals(TRUE))
                    .map(m -> m.getId()).collect(Collectors.toList());
            selSql001Req.setFinancialAssetsList(financialAssetsList);
            // 職業
            List<String> occupationList = occupation.stream().filter(f -> f.getIsSelected().equals(TRUE))
                    .map(m -> m.getId()).collect(Collectors.toList());
            selSql001Req.setOccupationList(occupationList);
            // 投資経験
            // 画面パラメータ
            List<String> investmentExpList = investmentExp.stream().filter(f -> f.getIsSelected().equals(TRUE))
                    .map(m -> m.getId()).collect(Collectors.toList());
            // 投資経験パラメータ作成
            if (!investmentExpList.isEmpty()) {
                investmentExpGroupBy(selSql001Req, investmentExpList);
            }
            // ドロップダウンボックスの内容選択の判定
            if (investmentPlanList.isEmpty() && fundCharacterList.isEmpty() && incomeFormList.isEmpty()
                    && employmentPeriodList.isEmpty() && annualIncomeList.isEmpty() && financialAssetsList.isEmpty()
                    && occupationList.isEmpty() && investmentExpList.isEmpty() && !TRUE.equals(investmentExpDisplay)) {
                selSql001Req.setSelectFlg(null);
            } else {
                selSql001Req.setSelectFlg(TRUE);
            }
            // 「未登録」検索パラメータMap作成
            selSql001Req.setNotLoggedMap(mCodeList.stream()
                    // 画面「法人区分」の選択に応じたフィルタリング
                    .filter(MCode -> MCode.getCode1().substring(MCode.getCode1().length() - 1)
                            .equals(selSql001Req.getPersonalCorporation())
                            || "2".equals(MCode.getCode1().substring(MCode.getCode1().length() - 1)))
                    // MAP作成({INVESTMENTPLAN=[01, 02, 03, 04, 05, 06, 07, 09], EMLOYMENTPERIOD=[01,
                    // 02, 03, 09], ...])
                    .collect(Collectors.groupingBy(
                            MCode -> ((MCode) MCode).getCode1().substring(0, ((MCode) MCode).getCode1().length() - 2),
                            Collectors.mapping(MCode -> ((MCode) MCode).getCode2(), Collectors.toList()))));

        }

        // 権限コードの設定
        selSql001Req.setPrivId(privId);

        return selSql001Req;
    }

    /**
     * 投資経験パラメータ作成
     *
     * @param selSql001Req      SQL001 リクエストモデル
     * @param investmentExpList 画面パラメータ
     */
    private void investmentExpGroupBy(IfaCustomerListSql001RequestModel selSql001Req, List<String> investmentExpList) {
        // 投資経験グループ、投資経験map作成
        List<String> investmentExpKeyList = new ArrayList<>();
        Map<String, List<String>> investmentExpKbnMap = new HashMap<>();
        for (String investmentExp : investmentExpList) {
            // パラメータ変更doubleタイプ
            double parseDouble = Double.parseDouble(investmentExp);
            // 奇数を1に設定(1:あり) 偶数を2に設定(2:なし)
            String value = parseDouble % 2 == 1 ? "1" : "2";
            // パラメータを2で割ると、結果は1増加
            Double d = Math.ceil(parseDouble / 2);
            String key = "key" + Integer.toString(d.intValue());
            // investmentExpList書式:("key1","key2","key3"......"key9")
            investmentExpKeyList.add(key);
            // investmentExpKbnMap書式:("key1"=["1","2"],"key2"=["1","2"],"key3"=["1","2"]......"key9"=["1","2"])
            investmentExpKbnMap.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }
        // 投資経験グループ重複を除去
        selSql001Req.setInvestmentExpKeyList(investmentExpKeyList.stream().distinct().collect(Collectors.toList()));
        selSql001Req.setInvestmentExpKbnMap(investmentExpKbnMap);
    }

    /**
     * 投資経験有無設定
     *
     * @param expKbn 投資経験有無
     * @return String 投資経験有無(あり/なし)
     */
    private String getInvestmentExpKbn(String expKbn) {
        return INVESTMENTEXP_OK.equals(expKbn) ? INVESTMENTEXP_NAME_OK
                : INVESTMENTEXP_NO.equals(expKbn) ? INVESTMENTEXP_NAME_NO : StringUtil.EMPTY_STRING;
    }

    /**
     * 投資経験年数設定
     *
     * @param exp 投資経験年数
     * @return String 投資経験年数(n年)
     */
    private String getInvestmentExp(String exp) {
        return StringUtil.isNullOrEmpty(exp) ? INVESTMENTEXP_NULL_ZERO + INVESTMENTEXP_YEAR_UNIT
                : StringUtil.isNullOrEmpty(exp.trim().replaceFirst("^0*", ""))
                        ? INVESTMENTEXP_NULL_ZERO + INVESTMENTEXP_YEAR_UNIT
                        : exp.trim().replaceFirst("^0*", "") + INVESTMENTEXP_YEAR_UNIT;
    }

    /**
     * 顧客名の拗音・促音変換
     *
     * @param name 顧客名(漢字／カナ)
     * @return 検索用顧客名
     */
    private String convertCustomerName(String name) {

        name = name.replaceAll("ッ", "ツ");
        name = name.replaceAll("ャ", "ヤ");
        name = name.replaceAll("ュ", "ユ");
        name = name.replaceAll("ョ", "ヨ");
        name = name.replaceAll("ツ", "[ツッ]");
        name = name.replaceAll("ヤ", "[ヤャ]");
        name = name.replaceAll("ユ", "[ユュ]");
        name = name.replaceAll("ヨ", "[ヨョ]");

        return name;

    }

    private String toFullWidth(String src) {
        // Unicode normalization
        // (half Kanner full-width kana conversion, full-width alphanumeric symbol - alphanumeric symbol conversion)
        src = Normalizer.normalize(src, Normalizer.Form.NFKC);

        // Half-size special symbol full-width conversion
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < src.length(); i++) {
            sb.append(toFullWidthChar(src.charAt(i)));
        }
        String result = sb.toString();

        // ICU (International Components for Unicode) Halfwidth-Fullwidth
        Transliterator transliterator = Transliterator.getInstance("Halfwidth-Fullwidth");
        result = transliterator.transliterate(result);

        return result;
    }


    private char toFullWidthChar(char value) {
        if (value == '\'') {        // In the case of single-byte apostrophe
            return '’';
        } else if (value == '\"') { // In the case of single-byte quotation marks
            return '”';
        } else if (value == '`')  { // In the case of half-grave accent em angle quotation marks (start)
            return '‘';
        } else if (value == '\\')  { // In the case of single-byte ¥
            return '￥';
        } else {
            return value;
        }
    }

}
