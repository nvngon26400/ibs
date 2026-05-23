package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.extapi.ApiConnectionException;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct020;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct020Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct020Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaMarginPositionListDomesticDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql001RequestModel_BrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA002aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA002aRequestDtoCourseSelected;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA002aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA002bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA002bResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA004aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticDto_MarginPositionListDomestic;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticErrorModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums.AutoSweepKbn;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums.BuysellKbn;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums.CapabilitySetKbn;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums.DeficitSetKbn;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums.IppanMgPaymentKbn;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums.IppanMgPaymentLimit;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums.LastTradeDate;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums.StRightId;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums.TokuteiContractId;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaMarginPositionListDomesticService;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaMarginPositionListDomesticCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContractIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContractInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContractOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContractOutVec;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstCapabilityWebHtsOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstCapabilityWebIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstCapabilityWebInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstCapabilityWebSettlementDateT;

/**
 * 画面ID：SUB020302_0302-01
 * 画面名：信用建玉一覧（国内）
 * 2023/09/07 新規作成
 *
 * @author 松田
 */
@Component(value = "cmpIfaMarginPositionListDomesticService")
public class IfaMarginPositionListDomesticServiceImpL implements IfaMarginPositionListDomesticService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginPositionListDomesticServiceImpL.class);

    // --------------------------------
    // メッセージ
    // --------------------------------

    /** 取引コースを選択してください。 */
    private static final String ERRORS_DMS_ACCOUNT_COURSE_INSUFFICIENT = "errors.dms.accountCourse.insufficient";

    /** {0}を正しく入力して下さい。 */
    private static final String ERRORS_ACCURATELY = "errors.accurately";

    /** 参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST = "errors.cmn.ifaAgentCodes.notExist";

    /** 仲介業者コード／営業員コードを正しく入力して下さい。 */
    private static final String ERRORS_DMS_IFA_AGENT_CODES_UNAVAILABLE = "errors.dms.ifaAgentCodes.unavailable";

    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATA_LIST_NOTFOUND = "errors.dataList.notfound";

    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 */
    private static final String WARNINGS_DATA_LIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";

    /** 信用建玉件数が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String INFO_HOLDMARGINLIST_NOTFOUND = "info.holdMarginList.notfound";

    /** アクセスが集中しているため口座情報が取得できませんでした。<br>部店コード:[{0}] 口座番号:[{1}] */
    private static final String W0001 = "W0001";

    /** {0}情報の取得ができませんでした。<br>部店コード:[{1}] 口座番号:[{2}] */
    private static final String W0002 = "W0002";

    /** アクセスが集中しているため口座維持率情報が取得できませんでした。<br>部店コード:[{0}] 口座番号:[{1}] */
    private static final String W0003 = "W0003";

    /** 口座維持率情報が取得できませんでした。<br>部店コード:[{0}] 口座番号:[{1}] */
    private static final String W0004 = "W0004";

    // --------------------------------
    // 変数定義
    // --------------------------------
    /** 権限コード：SBI証券本店 の場合 */
    private static final String AUTH_CODE_SBI = "1";

    /** 権限コード：仲介業者.内部管理責任者 の場合 */
    private static final String AUTH_CODE_BROKER_INTERNAL_ADMIN = "3";

    /** 権限コード：仲介業者.営業責任者 の場合 */
    private static final String AUTH_CODE_SALES_MANAGER = "4";

    /** 権限コード：仲介業者.外務員 の場合 */
    private static final String AUTH_CODE_SECURITIES_REPRESENTATIVE = "5";

    /** APIリクエストタイプ(固定値) */
    private static final String API_REQUEST_TYPE = "1";

    /** ハイフン */
    private static final String HYPHEN = "-";

    /** 日付値「99991231」時の設定値 */
    private static final String DATE_HYPHEN = "----/--/--";

    /** 評価損益：現在値チェック値 */
    private static final String CURRENCY_CHECK_VALUE = "0";

    /** SQL002処理タイミング(入力チェック時) */
    private static final String SQL002_1 = "1";

    /** SQL002処理タイミング(信用建玉一覧検索時) */
    private static final String SQL002_2 = "2";

    /** 区分値名:弁済期限 */
    private static final String PAYMENT_DEADLINE = "PAYMENT_DEADLINE";

    @Autowired
    private IfaMarginPositionListDomesticDao dao;

    /**
     * ComplianceService(CSV証跡登録用)クラス
     */
    @Autowired
    private ComplianceService complianceService;

    /**
     * API呼び出しクラス
     */
    @Autowired
    private ApiWrapper apiWrapper;

    /**
     * FCT020処理
     */
    @Autowired
    private Fct020 fct020;

    /**
     * FCT030処理
     */
    @Autowired
    private Fct030 fct030;

    /**
     * FCT038処理
     */
    @Autowired
    private Fct038 fct038;

    /**
     * 区分値変換
     */
    @Autowired
    private CodeListService codeListService;

    /** 取引文言 */
    private static String createOpenTradeKbn(String title, String paymentLimit) {

        return title + "建(" + paymentLimit + ")";
    }

    /**
     * アクションID：A002a
     * アクション名：表示 顧客口座情報
     * Dto リクエスト：IfaMarginPositionListDomesticA002DtoRequest
     * Dto レスポンス：IfaMarginPositionListDomesticA002DtoResponse
     * model リクエスト：IfaMarginPositionListDomesticSql003RequestModel
     * model レスポンス：IfaMarginPositionListDomesticSql003ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 顧客口座情報
     * @exception Exception システムエラー
     */
    public DataList<IfaMarginPositionListDomesticA002aResponseDto> displayA002a(
            IfaMarginPositionListDomesticA002aRequestDto dtoReq) throws Exception {

        DataList<IfaMarginPositionListDomesticA002aResponseDto> dtoRes = new DataList<IfaMarginPositionListDomesticA002aResponseDto>();
        List<IfaMarginPositionListDomesticA002aResponseDto> responseList = new ArrayList<IfaMarginPositionListDomesticA002aResponseDto>();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginPositionListDomesticServiceImplL.displayA002a");
        }

        // ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        // 仲介業者営業員リスト
        OutputFct030Dto fct030Result = new OutputFct030Dto();
        // エラーメッセージを格納
        IfaMarginPositionListDomesticErrorModel error = new IfaMarginPositionListDomesticErrorModel();

        // 入力チェック
        if (!validationCheck(dtoReq.getCourse(), dtoReq.getBrandCode(), dtoReq.getBrokerCode(), dtoReq.getEmpCode(),
                userAccount.getPrivId(), fct030Result, error)) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        }

        // CSVダウンロードMAX件数の取得
        OutputFct038Dto fct038Result = getCsvMaxCount(dtoReq.getScreenId(), userAccount.getPrivId());

        // SQL001(顧客口座情報の検索)
        DataList<IfaMarginPositionListDomesticSql001ResponseModel> selSql001Res = getManagingContractList(dtoReq,
                dtoReq.getCourse(), fct030Result);
        // 取得件数チェック
        if (selSql001Res == null || selSql001Res.getDataList().size() == 0) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.INFO, ERRORS_DATA_LIST_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATA_LIST_NOTFOUND));
        } else if (selSql001Res.getDataList().size() > fct038Result.getCsvDownloadNum()) {
            dtoRes = IfaCommonUtil.createDataList(null, ErrorLevel.WARNING, WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                    IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                            new String[] { String.valueOf(fct038Result.getCsvDownloadNum()),
                                    String.valueOf(selSql001Res.getDataList().size()) }));
        }
        for (IfaMarginPositionListDomesticSql001ResponseModel model : selSql001Res.getDataList()) {
            IfaMarginPositionListDomesticA002aResponseDto mc = new IfaMarginPositionListDomesticA002aResponseDto();
            BeanUtils.copyProperties(mc, model);
            mc.setBrandCode(dtoReq.getBrandCode()); // 銘柄コード
            mc.setCourse(model.getCustomerAttribute()); // 取引コース
            responseList.add(mc);
        }
        if (selSql001Res.getDataList().size() > fct038Result.getCsvDownloadNum()) {
            dtoRes.setDataList(responseList);
            return dtoRes;
        }
        return IfaCommonUtil.createDataList(responseList, ErrorLevel.SUCCESS, "0", "");
    }

    /**
     * アクションID：A002b
     * アクション名：表示 信用建玉情報検索
     * Dto リクエスト：IfaMarginPositionListDomesticA002DtoRequest
     * Dto レスポンス：IfaMarginPositionListDomesticA002DtoResponse
     * model リクエスト：IfaMarginPositionListDomesticSql003RequestModel
     * model レスポンス：IfaMarginPositionListDomesticSql003ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 信用建玉情報検索
     * @exception Exception システムエラー
     */
    public DataList<IfaMarginPositionListDomesticA002bResponseDto> displayA002b(
            IfaMarginPositionListDomesticA002bRequestDto dtoReq) throws Exception {

        DataList<IfaMarginPositionListDomesticA002bResponseDto> dtoRes = new DataList<IfaMarginPositionListDomesticA002bResponseDto>();
        List<IfaMarginPositionListDomesticA002bResponseDto> resultList = new ArrayList<IfaMarginPositionListDomesticA002bResponseDto>();
        IfaMarginPositionListDomesticA002bResponseDto result = new IfaMarginPositionListDomesticA002bResponseDto();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginPositionListDomesticServiceImplL.displayA002b");
        }

        // エラーメッセージを格納
        IfaMarginPositionListDomesticErrorModel error = new IfaMarginPositionListDomesticErrorModel();
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // API001 建玉検索
        List<QueryMarginContractOutData> marginContractOutputList = new ArrayList<QueryMarginContractOutData>();
        try {
            marginContractOutputList = callQueryMarginContractOutputAll(dtoReq.getButenCode(),
                    dtoReq.getAccountNumber(), dtoReq.getBrandCode());
        } catch (Exception e) {
            LOGGER.error("ListServiceImplL.getHoldMarginList apiWrapper.queryMarginContractOutputAll Exception[{}]",
                    e.getMessage());
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            if (e instanceof ApiConnectionException) {
                error.setErrorCode(W0001);
                error.setErrorMessage(IfaCommonUtil.getMessage(W0001,
                        new String[] { dtoReq.getButenCode(), dtoReq.getAccountNumber() }));

            } else {
                error.setErrorCode(W0002);
                error.setErrorMessage(IfaCommonUtil.getMessage(W0002,
                        new String[] { "信用建玉", dtoReq.getButenCode(), dtoReq.getAccountNumber() }));
            }
            return IfaCommonUtil.createDataList(null, ErrorLevel.WARNING, error.getErrorCode(),
                    error.getErrorMessage());
        }

        List<IfaMarginPositionListDomesticDto_MarginPositionListDomestic> responseList = new ArrayList<IfaMarginPositionListDomesticDto_MarginPositionListDomestic>();
        // 信用建玉情報リスト分処理をLOOP
        for (QueryMarginContractOutData data : marginContractOutputList) {
            if (apiErrorUtil.isError(data.getShubetu(), data.getCode(), data.getMessage())) {
                error.setErrorCode(W0002);
                error.setErrorMessage(IfaCommonUtil.getMessage(W0002,
                        new String[] { "信用建玉", dtoReq.getButenCode(), dtoReq.getAccountNumber() }));
                return IfaCommonUtil.createDataList(null, ErrorLevel.WARNING, error.getErrorCode(), error.getErrorMessage());
            }
            if (!CollectionUtils.isEmpty(data.getQueryMarginContractData())) {
                for (QueryMarginContractOutVec position : data.getQueryMarginContractData()) {
                    // 銘柄情報取得
                    IfaMarginPositionListDomesticSql002ResponseModel sql002Result = getBrandInfo(
                            position.getCompanyCode() + position.getNewOldId(), dtoReq.getBrandCode(), SQL002_2, error);
                    if (!StringUtil.isNullOrEmpty(error.getErrorCode())
                            && !StringUtil.isNullOrEmpty(error.getErrorMessage())) {
                        return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, error.getErrorCode(),
                                error.getErrorMessage());
                    }
                    responseList.add(setResponse(position, sql002Result, dtoReq, data.getNightBatchEndFlg()));
                }
            }

        }
        // 維持率の取得
        String actualGrntRate = null;
        try {
            actualGrntRate = getActualGrntRate(dtoReq.getButenCode(), dtoReq.getAccountNumber(), apiErrorUtil);
            if (actualGrntRate == null) {
                error.setErrorCode(W0004);
                error.setErrorMessage(IfaCommonUtil.getMessage(W0004,
                        new String[] { dtoReq.getButenCode(), dtoReq.getAccountNumber() }));
            }
            // 取得した値で各レコードの維持率を設定する
            for (IfaMarginPositionListDomesticDto_MarginPositionListDomestic posRes : responseList) {
                posRes.setDomesticMarginPositionListActualGrntRate(actualGrntRate);
            }
        } catch (Exception e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            if (e instanceof ApiConnectionException) {
                error.setErrorCode(W0003);
                error.setErrorMessage(IfaCommonUtil.getMessage(W0003,
                        new String[] { dtoReq.getButenCode(), dtoReq.getAccountNumber() }));
            } else {
                error.setErrorCode(W0004);
                error.setErrorMessage(IfaCommonUtil.getMessage(W0004,
                        new String[] { dtoReq.getButenCode(), dtoReq.getAccountNumber() }));
            }
        }
        result.setMarginPositionListDomesticList(responseList);
        resultList.add(result);
        
        if (!StringUtil.isNullOrEmpty(error.getErrorCode()) && !StringUtil.isNullOrEmpty(error.getErrorMessage())) {
            dtoRes = IfaCommonUtil.createDataList(resultList, ErrorLevel.WARNING, error.getErrorCode(),
                    error.getErrorMessage());
        }
        else if (ObjectUtils.isEmpty(result.getMarginPositionListDomesticList())) {
            //③   信用建玉一覧リストが0件の場合、メッセージを表示する
            dtoRes = IfaCommonUtil.createDataList(resultList, ErrorLevel.INFO, INFO_HOLDMARGINLIST_NOTFOUND,
                    IfaCommonUtil.getMessage(INFO_HOLDMARGINLIST_NOTFOUND));
        }
        else {
            dtoRes = apiErrorUtil.createDataList(resultList, "");
        }

        return dtoRes;
    }

    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaMarginPositionListDomesticA004DtoRequest
     * Dto レスポンス：IfaMarginPositionListDomesticA004DtoResponse
     *
     * @param dtoReq リクエストパラメータ
     * @return CSV情報
     * @exception Exception システムエラー
     */
    public DataList<IfaMarginPositionListDomesticA004aResponseDto> csvOutputA004a(
            IfaMarginPositionListDomesticA004aRequestDto dtoReq, String fwSessionId) throws Exception {

        DataList<IfaMarginPositionListDomesticA004aResponseDto> dtoRes = new DataList<IfaMarginPositionListDomesticA004aResponseDto>();
        List<IfaMarginPositionListDomesticA004aResponseDto> responseList = new ArrayList<IfaMarginPositionListDomesticA004aResponseDto>();
        IfaMarginPositionListDomesticA004aResponseDto response = new IfaMarginPositionListDomesticA004aResponseDto();
        IfaMarginPositionListDomesticCsvOut csvOut = new IfaMarginPositionListDomesticCsvOut(complianceService);
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaMarginPositionListDomesticServiceImplL.csvOutputA004a");

        DataList<IfaMarginPositionListDomesticDto_MarginPositionListDomestic> exportList = new DataList<IfaMarginPositionListDomesticDto_MarginPositionListDomestic>();
        exportList.setDataList(convertCsvOutModel(dtoReq.getMarginPositionListDomestic()));

        responseList.add(response);

        dtoRes = IfaCommonUtil.createDataList(responseList, ErrorLevel.SUCCESS, "0", "");

        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        dtoRes.setTitle(csvOut.doCreateCsvFile(exportList, fwSessionId, userAccount.getUserId(), null));

        return dtoRes;
    }

    /**
     * A004aリクエストをCSV出力形式に変換する
     *
     * @param req A004リクエスト
     * @return CSV出力データモデル
     */
    private List<IfaMarginPositionListDomesticDto_MarginPositionListDomestic> convertCsvOutModel(
            List<IfaMarginPositionListDomesticDto_MarginPositionListDomestic> req) {

        for (IfaMarginPositionListDomesticDto_MarginPositionListDomestic marginPosition : req) {
            // 取引コース
            marginPosition.setCourse(codeListService.getValue("PRE_CONTRACT_DOC_CODE", marginPosition.getCourse()));
        }

        return req;
    }

    /**
     * レスポンス項目編集
     *
     * @param position 建玉
     * @param sql002Result SQL002結果
     * @param dtoReq リクエストパラメータ
     * @param nightBatchEndFlg 夜間バッチフラグ
     * @param selSql001Res SQL001結果
     * @return レスポンス
     * @throws Exception システムエラー
     */
    private IfaMarginPositionListDomesticDto_MarginPositionListDomestic setResponse(QueryMarginContractOutVec position,
            IfaMarginPositionListDomesticSql002ResponseModel sql002Result,
            IfaMarginPositionListDomesticA002bRequestDto dtoReq, String nightBatchEndFlg) throws Exception {

        IfaMarginPositionListDomesticDto_MarginPositionListDomestic response = new IfaMarginPositionListDomesticDto_MarginPositionListDomestic();
        String brandCode = position.getCompanyCode().trim() + position.getNewOldId();
        BuysellKbn buysellKbn = BuysellKbn.getInstanceByNriCode(position.getOpenTradeKbn());
        // 部店
        response.setButenCode(dtoReq.getButenCode());
        // 口座
        response.setAccountNumber(dtoReq.getAccountNumber());
        // コース
        response.setCourse(dtoReq.getCourse());
        // 顧客名（漢字）（全角半角）
        response.setCustomerNameKanji(dtoReq.getCustomerNameKanji());
        // 顧客名(カナ)
        response.setCustomerNameKana(dtoReq.getCustomerNameKana());
        // 維持率（%）　後続処理で個別に設定
        // 銘柄コード
        response.setBrandCode(brandCode);
        // 銘柄名
        if (sql002Result == null) {
            response.setBrandName(position.getSecNameS());
        } else {
            response.setBrandName(sql002Result.getBrandName());
        }
        // 市場
        response.setMarket(getMarket(position.getOpenMarket()));
        // 取引
        response.setOpenTradeKbn(getOpenTradeKbn(buysellKbn, position.getIppanMgPaymentKbn(),
                position.getIppanMgPaymentLimit(), position.getPaymentLimit()));
        // 建日 YYYY/MM/DD
        response.setOpenTradeDate(DateFormatUtil.convertDateString(position.getOpenTradeDate()));
        // 返済期限
        response.setLastTradeDate(null != LastTradeDate.getInstanceByCode(position.getLastTradeDate()) ? DATE_HYPHEN
                : DateFormatUtil.convertDateString(position.getLastTradeDate()));
        // 預り
        response.setDepositFullHalf13(null != TokuteiContractId.getInstanceByCode(position.getTokuteiContractId())
                ? TokuteiContractId.getInstanceByCode(position.getTokuteiContractId()).getShortTitle()
                : HYPHEN);
        // 建株数
        response.setContPositionTotal(getValueBigDecimalToString(position.getContPosition()));
        // 注文中
        response.setUnactualQuantity(getValueBigDecimalToString(position.getUnactualQuantity()));
        // 建代金
        response.setOpenAmount(getValueBigDecimalToString(position.getOpenAmount()));
        // 建単価
        response.setUnitPriceForeign(getValueBigDecimalDivide(position.getOpenPrice()));
        // 現在値
        response.setCurrentPrice(getCurrentPrice(brandCode, position.getStRightId(), nightBatchEndFlg));
        // 諸経費等合計
        response.setCost(getValueBigDecimalToString(position.getCost()));
        // 評価損益
        response.setDomesticPositionValuation(getDomesticPositionValuation(position.getStRightId(),
                position.getUnrealizedPl(), response.getCurrentPrice(), response.getContPositionTotal(),
                response.getOpenAmount(), response.getCost(), buysellKbn));
        // 仲介業者コード
        response.setBrokerCode(dtoReq.getBrokerCode());
        // 仲介業者名
        response.setBrokerName(dtoReq.getBrokerName());
        // 支店コード
        response.setBranchCode(dtoReq.getBranchCode());
        // 支店名
        response.setBranchName(dtoReq.getBranchName());
        // 営業員コード
        response.setEmpCode(dtoReq.getEmpCode());
        // 営業員名
        response.setBrokerChargeName(dtoReq.getBrokerChargeName());
        // 新規売買区分
        response.setNewCreditOrderType(position.getOpenTradeKbn());
        // 新規建玉指定番号
        response.setNewOpenInterestNumber(position.getOpenContractNo());
        // 弁済期限
        response.setPaymentDeadline(position.getPaymentLimit());
        // 親株新規約定日
        response.setParentStockTradeDate(position.getOrgNewTradeDate());
        // 新規約定日
        response.setNewTradeDate(position.getOpenTradeDate());
        // 新規市場
        response.setNewOpenMarket(position.getOpenMarket());

        return response;
    }

    /**
     * SQL001処理
     *
     * @param dtoReq リクエスト情報
     * @return SQL処理結果
     * @throws Exception システムエラー
     */
    private DataList<IfaMarginPositionListDomesticSql001ResponseModel> getManagingContractList(
            IfaMarginPositionListDomesticA002aRequestDto dtoReq,
            List<IfaMarginPositionListDomesticA002aRequestDtoCourseSelected> course, OutputFct030Dto fct030Result)
            throws Exception {

        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        IfaMarginPositionListDomesticSql001RequestModel selSql001Req = new IfaMarginPositionListDomesticSql001RequestModel();
        selSql001Req.setAccountNumber(dtoReq.getAccountNumber());
        selSql001Req.setBrokerCode(dtoReq.getBrokerCode());
        selSql001Req.setEmpCode(dtoReq.getEmpCode());
        selSql001Req.setButenCode(dtoReq.getButenCode());
        selSql001Req.setPrivId(userAccount.getPrivId());
        selSql001Req.setCustomerNameKanjiKana(dtoReq.getCustomerNameKanjiKana());
        selSql001Req.setCustomerNameKanjiKanaTerms(dtoReq.getCustomerNameKanjiKanaTerms());

        // 取引コースの設定
        // boolean allSelectFlg = true;
        List<String> searchCourseList = new ArrayList<String>();

        for (IfaMarginPositionListDomesticA002aRequestDtoCourseSelected courseList : course) {
            if (courseList.getIsSelected()) {
                searchCourseList.add(courseList.getId());
            } else {
                continue;
            }
        }
        selSql001Req.setCourse(searchCourseList);

        List<IfaMarginPositionListDomesticSql001RequestModel_BrokerCharge> brokerChargeList = new ArrayList<IfaMarginPositionListDomesticSql001RequestModel_BrokerCharge>();
        if (fct030Result != null && !CollectionUtils.isEmpty(fct030Result.getBrokerChargeList())) {
            for (BrokerCharge fctRes : fct030Result.getBrokerChargeList()) {
                IfaMarginPositionListDomesticSql001RequestModel_BrokerCharge brokerCharge = new IfaMarginPositionListDomesticSql001RequestModel_BrokerCharge();
                brokerCharge.setBrokerCode(fctRes.getBrokerCode());
                brokerCharge.setEmpCode(fctRes.getEmpCode());
                brokerChargeList.add(brokerCharge);
            }
        }
        selSql001Req.setBrokerChargeList(brokerChargeList);

        return dao.selectIfaMarginPositionListDomesticSql001(selSql001Req);
    }

    /**
     * 評価損益取得処理
     *
     * @param stRightId 権利区分
     * @param unrealizedPl 評価損益
     * @param currentPrice 現在値
     * @param contPositionTotal 建株数
     * @param openAmount 建代金
     * @param cost 諸経費等合計
     * @param buysellKbn 新規売買区分
     * @return 評価損益（算出値）
     */
    private String getDomesticPositionValuation(String stRightId, String unrealizedPl, String currentPrice,
            String contPositionTotal, String openAmount, String cost, BuysellKbn buysellKbn) {

        // 権利区分が「1(株価異常状態)なら評価損益を返却
        if (StRightId.ABNORMAL_PRICE.getCode().equals(stRightId)) {
            return getValueBigDecimalToString(unrealizedPl);
        }

        // 現在値がnull,または0ならnullを返却
        if (currentPrice == null || StringUtils.equals(currentPrice, CURRENCY_CHECK_VALUE)) {
            return null;
        }
        // 現在値
        BigDecimal currentPriceBd = StringUtil.parseBigDecimal(currentPrice);
        // 建株数
        BigDecimal contPositionTotalBd = StringUtil.parseBigDecimal(contPositionTotal);
        // 建代金
        BigDecimal openAmountbd = StringUtil.parseBigDecimal(openAmount);
        // 諸経費等合計
        BigDecimal costbd = StringUtil.parseBigDecimal(cost);

        // 計算結果
        BigDecimal result = BigDecimal.ZERO;

        if (BuysellKbn.BUY.equals(buysellKbn)) {
            // 現在値 × 建株数 - 建代金 - 諸経費等合計の小数点以下を切り捨てた値
            result = currentPriceBd.multiply(contPositionTotalBd).subtract(openAmountbd).subtract(costbd);
        } else {
            // 建代金 - (現在値 × 建株数) - 諸経費等合計の小数点以下を切り捨てた値
            result = openAmountbd.subtract(currentPriceBd.multiply(contPositionTotalBd)).subtract(costbd);
        }

        return result.setScale(0, RoundingMode.DOWN).toString();
    }

    /**
     * API処理結果項目:小数点以下切り捨て値取得処理
     *
     * @param value 項目値
     * @return 小数点以下切り捨てした値
     */
    private String getValueBigDecimalToString(String value) {

        if (StringUtil.isNullOrEmpty(value)) {
            return null;
        }

        return StringUtil.parseBigDecimal(value.trim()).setScale(0, RoundingMode.DOWN).toString();
    }

    /**
     * API処理結果項目:トリム後100で割った値取得処理
     *
     * @param value 項目値
     * @return 100で割った値
     */
    private String getValueBigDecimalDivide(String value) {

        if (StringUtil.isNullOrEmpty(value)) {
            return null;
        }

        BigDecimal hundred = new BigDecimal("100");
        return StringUtil.parseBigDecimal(value.trim()).divide(hundred).toString();
    }

    /**
     * 取引値取得処理
     *
     * @param openTradeKbn 新規売買区分
     * @param ippanMgPaymentKbn 一般信用売弁済期限年月日区分
     * @param ippanMgPaymentLimit 一般信用売弁済期限年月日数
     * @param paymentLimit 弁済期限
     * @return 取引値
     */
    private String getOpenTradeKbn(BuysellKbn buysellKbn, String ippanMgPaymentKbn, String ippanMgPaymentLimit, String paymentLimit) {

        String StrPaymentLimit = null;
        if (ippanMgPaymentLimit == null || ippanMgPaymentLimit.isEmpty()) {
            return StringUtil.EMPTY_STRING;
        }
        if (!ippanMgPaymentLimit.equals(IppanMgPaymentLimit.UNLIMITED.getCode())
                && !ippanMgPaymentKbn.equals(IppanMgPaymentKbn.UNLIMITED.getCode())) {
            StrPaymentLimit = ippanMgPaymentLimit.replaceFirst("^0+", "")
                    + (null != IppanMgPaymentKbn.getInstanceByCode(ippanMgPaymentKbn)
                            ? IppanMgPaymentKbn.getInstanceByCode(ippanMgPaymentKbn).getTitle()
                            : "");
        }

        // 上記以外の場合、信用建玉情報リスト.契約情報.弁済期限の変換値（区分.弁済期限　表示パターン1）
        StrPaymentLimit = (!StringUtil.isNullOrEmpty(StrPaymentLimit)) ? StrPaymentLimit : codeListService.getValue(PAYMENT_DEADLINE, paymentLimit);

        return createOpenTradeKbn(buysellKbn.getTitle(), StrPaymentLimit);
    }

    /**
     * 市場値取得処理
     *
     * @param openMarket 新規市場
     * @return 市場
     */
    private String getMarket(String openMarket) {

        if (openMarket == null) {
            return null;
        }

        String mkt = codeListService.getValue("NEW_MARKET", openMarket);

        return mkt == null ? HYPHEN : mkt;
    }

    /**
     * 現在値取得処理
     *
     * @param brandCode 銘柄コード;
     * @param stRightId 権利区分
     * @param nightBatchEndFlg CT夜間バッチ終了フラグ
     * @return 現在値
     */
    private String getCurrentPrice(String brandCode, String stRightId, String nightBatchEndFlg) {

        InputFct020Dto input = new InputFct020Dto();
        input.setBrandCode(brandCode);
        input.setRightType(stRightId);
        input.setCtNightBatchEndFlag(nightBatchEndFlg);

        OutputFct020Dto result = fct020.getData(input);
        return result == null ? null : result.getCurrentValueForEvaluation();

    }

    /**
     * SQL002 銘柄情報取得処理
     *
     * @param brandCode 銘柄コード
     * @param reqBrandCode A002.銘柄コード
     * @return 取得結果
     * @throws Exception システムエラー
     */
    private IfaMarginPositionListDomesticSql002ResponseModel getBrandInfo(String brandCode, String reqBrandcode,
            String sqlSyori, IfaMarginPositionListDomesticErrorModel error) throws Exception {

        IfaMarginPositionListDomesticSql002RequestModel sql002Req = new IfaMarginPositionListDomesticSql002RequestModel();
        sql002Req.setBrandCode(brandCode);
        sql002Req.setReqBrandCode(reqBrandcode);
        sql002Req.setSqlSyori(sqlSyori);

        DataList<IfaMarginPositionListDomesticSql002ResponseModel> result = dao
                .selectIfaMarginPositionListDomesticSql002(sql002Req);

        if (CollectionUtils.isEmpty(result.getDataList())) {
            return null;
        } else if (result.getDataList().size() > 1) {
            // TODO 想定件数以上取得時のエラー未決定
            error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_ACCURATELY, new String[] { "銘柄コード" }));
            error.setErrorCode(ERRORS_ACCURATELY);
            return null;
        }

        return result.getDataList().get(0);
    }

    /**
     * 入力チェック
     *
     * @param course 取引コード
     * @param brandCode 銘柄コード
     * @param brokerCode 仲介業者コード
     * @param empCode 営業員コード
     * @throws Exception システムエラー
     */
    private boolean validationCheck(List<IfaMarginPositionListDomesticA002aRequestDtoCourseSelected> course,
            String brandCode, String brokerCode, String empCode, String privId, OutputFct030Dto fct030Result,
            IfaMarginPositionListDomesticErrorModel error) throws Exception {

        // 取引コースの判定
        if (course == null || course.size() == 0) {
            error.setErrorCode(ERRORS_DMS_ACCOUNT_COURSE_INSUFFICIENT);
            error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_DMS_ACCOUNT_COURSE_INSUFFICIENT));
            return false;
        }
        // 銘柄コードの設定確認
        if (!StringUtil.isNullOrEmpty(brandCode)) {
            if (getBrandInfo(null, brandCode, SQL002_1, error) == null) {
                error.setErrorCode(ERRORS_ACCURATELY);
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_ACCURATELY, new String[] { "銘柄コード" }));
                return false;
            }
        }

        // 参照可能チェック
        if (!canReferenceUserCheck(brokerCode, empCode, privId, fct030Result, error)) {
            return false;
        }
        return true;
    }

    /**
     * 参照権限チェック処理
     *
     * @param brokerCode 仲介業者コード
     * @param empCode 営業員コード
     * @throws Exception システムエラー
     * @throws InvocationTargetException システムエラー
     * @throws IllegalAccessException システムエラー
     */
    private boolean canReferenceUserCheck(String brokerCode, String empCode, String privId,
            OutputFct030Dto fct030Result, IfaMarginPositionListDomesticErrorModel error) throws Exception {

        InputFct030Dto input = new InputFct030Dto();

        // input情報に設定する情報無し
        OutputFct030Dto fct030ResultTmp = fct030.getData(input);

        // 参照権限：SBI証券本店以外の場合のみチェックを行う
        if (StringUtils.equals(AUTH_CODE_SBI, privId)) {
            return true;
        }

        // 仲介業者営業員リストが取得できない場合はエラー
        if (fct030ResultTmp == null || fct030ResultTmp.getBrokerChargeList() == null
                || fct030ResultTmp.getBrokerChargeList().size() == 0) {
            error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST));
            error.setErrorCode(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST);
            return false;
        }

        if (
                StringUtils.equals(AUTH_CODE_BROKER_INTERNAL_ADMIN, privId)
                || StringUtils.equals(AUTH_CODE_SALES_MANAGER, privId)
                || StringUtils.equals(AUTH_CODE_SECURITIES_REPRESENTATIVE, privId)
        ) {
            // 権限3, 4, 5の場合
            // 営業員リストにリクエストの仲介業者コードが存在しない場合はエラー
            if (!fct030ResultTmp.getBrokerChargeList().stream().filter(
                    s -> StringUtils.equals(s.getBrokerCode(), brokerCode))
                    .findFirst().isPresent()) {
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_DMS_IFA_AGENT_CODES_UNAVAILABLE));
                error.setErrorCode(ERRORS_DMS_IFA_AGENT_CODES_UNAVAILABLE);
                return false;
            }
        } else {
            // 権限1, 3, 4, 5以外の場合
            // 営業員リストにリクエストの仲介業者コード/営業員コードの組み合わせが存在しない場合はエラー
            if (!fct030ResultTmp.getBrokerChargeList().stream().filter(
                    s -> StringUtils.equals(s.getBrokerCode(), brokerCode) && StringUtils.equals(s.getEmpCode(), empCode))
                    .findFirst().isPresent()) {
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_DMS_IFA_AGENT_CODES_UNAVAILABLE));
                error.setErrorCode(ERRORS_DMS_IFA_AGENT_CODES_UNAVAILABLE);
                return false;
            }
        }

        fct030Result.setBrokerChargeList(fct030ResultTmp.getBrokerChargeList());
        return true;
    }

    /**
     * FCT038呼び出し処理
     *
     * @return FCT038結果
     */
    private OutputFct038Dto getCsvMaxCount(String screenId, String privId) {

        InputFct038Dto input = new InputFct038Dto();

        input.setScreenId(screenId);
        input.setUserAuthority(privId);

        return fct038.getData(input);
    }

    /**
     * API001：信用建玉一覧リクエスト呼び出し処理
     *
     * @param dtoReq リクエスト情報
     * @return 信用建玉一覧リクエスト結果
     * @throws Exception システムエラー
     */
    private List<QueryMarginContractOutData> callQueryMarginContractOutputAll(String butenCode, String accountNumber,
            String brandCode) throws Exception {

        QueryMarginContractIn input = new QueryMarginContractIn();
        QueryMarginContractInData indata = new QueryMarginContractInData();

        // 部店コード
        indata.setButenCd(butenCode);
        // 口座番号
        indata.setKozaNo(String.format("%7s", accountNumber).replace(" ", "0"));
        // 銘柄コード
        indata.setBrandCd(brandCode);
        // リクエストタイプ
        indata.setRequestType(API_REQUEST_TYPE);

        // inputの設定
        input.setIndata(indata);

        List<QueryMarginContractOutData> output = apiWrapper.queryMarginContract(input);

        return output;
    }

    /**
     * 維持率取得処理
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return 維持率(小数点以下切り捨て値)
     * @throws Exception システムエラー
     */
    private String getActualGrntRate(String butenCode, String accountNumber, ApiErrorUtil apiErrorUtil) throws Exception {

        QueryMgEstCapabilityWebIn input = new QueryMgEstCapabilityWebIn();
        QueryMgEstCapabilityWebInData inData = new QueryMgEstCapabilityWebInData();
        inData.setButenCd(butenCode);
        inData.setKozaNo(String.format("%7s", accountNumber).replace(" ", "0"));
        inData.setRequestKbn1(CapabilitySetKbn.ALL.getCode());
        inData.setRequestKbn2(DeficitSetKbn.ALL.getCode());
        input.setIndata(inData);

        QueryMgEstCapabilityWebHtsOutData result = apiWrapper.queryMgEstCapabilityWebHts(input);
        if (apiErrorUtil.isError(result.getShubetu(), result.getCode(), result.getMessage())) {
            return null;
        }
        // APIのoutputから維持率を取得
        AutoSweepKbn autoSweepKbn = AutoSweepKbn.getInstanceByCompleted(result.getAutoSweepKbn());
        List<QueryMgEstCapabilityWebSettlementDateT> settlementList = result.getSettlementDateT();
        QueryMgEstCapabilityWebSettlementDateT settlement = settlementList.get(0);

        BigDecimal base = BigDecimal.ZERO;

        // 預り金自動スイープ未開設
        if (autoSweepKbn == null) {
            base = StringUtil.parseBigDecimal(settlement.getActualGrntRate());
        } else {
            base = StringUtil.parseBigDecimal(settlement.getEtKeepRate());
        }

        return base.divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN).toString();

    }

}
