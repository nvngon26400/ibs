package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct007;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.component.Fct033;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct033Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct033Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaWithdrawInputDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaWithdrawInputService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.EstimateCashPaymentOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.EstimateCashPaymentOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateCashPaymentOrderOutData;

/**
 * 画面ID：SUB0202_06-01_1 画面名：出金入力
 *
 * @author xin.huang
 */
@Component(value = "cmpIfaWithdrawInputService")
public class IfaWithdrawInputServiceImpL implements IfaWithdrawInputService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaWithdrawInputServiceImpL.class);

    @Autowired
    private IfaWithdrawInputDao dao;

    @Autowired
    private Fct001 fct001;

    @Autowired
    private Fct003 fct003;

    @Autowired
    private Fct021 fct021;

    @Autowired
    private Fct033 fct033;

    @Autowired
    private Fct007 fct007;

    @Autowired
    private ApiWrapper apiWrapper;

    @Autowired
    private CodeListService codelistservice;

    @Autowired
    private IfaDateUtil ifaDateUtil;

    /** 権限なし */
    private static final String NO_AUTHORITY = "0";

    /** 取引停止口座 */
    private static final String SUSPENSION_ACCOUNT = "1";

    /** あり */
    private static final String YES = "1";

    /** なし */
    private static final String NON = "0";

    /** 振替区分 */
    private static final String TRANSFER_ID = "3 ";

    /** 受付経路区分 */
    private static final String CALLCENTER_KBN = "1";

    /** 国籍コード */
    private static final String COUNTRY_CD = "99";

    /** 証券金銭種別 */
    private static final String PRODUCT_CD = "99";

    /** 通貨コード */
    private static final String CURRENCY_CODE = "999";

    /** 取引種別 */
    private static final String TRADE_CD = "1";

    /** 仕訳NO */
    private static final String POSTING_NO = "   ";

    /** 共通スペース */
    private static final String SHARED_SPACE = " ";

    /** 共通空文字 */
    private static final String SHARED_STR = "";

    /** 送金料 */
    private static final String REMITTANCE_FEE = "    ";

    /** カレンダー区分("0"（証券営業日ベース）) */
    private static final String CALENDARTYPE = "0";

    /** 営業日チェックフラグ.営業日 */
    private static final String BUSINESS_DAY_CHECK_FLAG_BUSINESS_DAY = "1";

    /** 日数 "1"（翌営業日を取得する場合） */
    private static final Integer ONE_DAY_F = 1;

    /** 日数 "2"（翌々営業日を取得する場合） */
    private static final Integer TWO_DAY_F = 2;
    
    /** 日数 "4"（翌営業日を取得する場合） */
    private static final Integer FOUR_DAY_T = 4;
    
    /** 日数 "5"（翌々営業日を取得する場合） */
    private static final Integer FIVE_DAY_T = 5;

    /** 区分.対象取引（メッセージ表示用） */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";

    /** 出金余力不足のエラーコード */
    public static final String API_RESULT_CASH_PAYMENT_ERROR_CODE = "EH0007";

    /** 出金余力不足のエラーメッセージ */
    public static final String API_RESULT_CASH_PAYMENT_ERROR_MESSAGE = "出金可能額を超過しています";

    enum MessageId {
        // errors.butenAccountNotExis
        ERRORS_BUTENACCOUNTNOTEXIST("errors.butenAccountNotExist"),
        // errors.cmn.selectedAccount.outOfService
        ERRORS_OUT_OF_SERVICE("errors.cmn.selectedAccount.outOfService"),
        // errors.cmn.selectedAccountCourse.unavailable
        ERRORS_SELECTED_ACCOUNT_COURSE_UNAVAILABLE("errors.cmn.selectedAccountCourse.unavailable"),
        // errors.cmn.transferBankNotExist
        ERRORS_TRANSFERBANKNOTEXIST("errors.cmn.transferBankNotExist"),
        // errors.cmn.estimateCashPayment.failed
        ERRORS_ESTIMATECASHPAYMENT_FAILED("errors.cmn.estimateCashPayment.failed"),
        // errors.cmn.noticeErrorCheck
        ERRORS_NOTICE_ERROR_CHECK("errors.cmn.noticeErrorCheck"),
        // errors.informationCheck
        ERRORS_INFORMATION_CHECK("errors.informationCheck"),
        // errors.cmn.ccsid.unregistered
        ERRORS_CMN_CCSID_UNREGISTERED("errors.cmn.ccsid.unregistered"),
        // warnings.cmn.noticeWarningCheck
        WARNINGS_NOTICE_WARNING_CHECK("warnings.cmn.noticeWarningCheck"),
        // warnings.cmm.informationCheck
        WARNINGS_CMM_INFORMATION_CHECK("warnings.cmm.informationCheck");


        private String key;

        private MessageId(String key) {
            this.key = key;
        }
    }

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dtoリクエスト：IfaWithdrawInputA001RequestDto
     * Dtoレスポンス：IfaWithdrawInputA001ResponseDto
     * Modelリクエスト：IfaWithdrawInputSql001RequestModel
     * Modelレスポンス：IfaWithdrawInputSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaWithdrawInputA001ResponseDto> initializeA001(IfaWithdrawInputA001RequestDto dtoReq)
            throws Exception {

        DataList<IfaWithdrawInputA001ResponseDto> dtoRes = new DataList<IfaWithdrawInputA001ResponseDto>();
        List<IfaWithdrawInputA001ResponseDto> resDtoList = new ArrayList<IfaWithdrawInputA001ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaWithdrawInputServiceImpL.initializeA001");
        }

        // ①利用者の口座に対する権限チェックを行う
        InputFct001Dto fct001Req = new InputFct001Dto();
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct001Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct001Req.setAccountNumber(cc.getAccountNumber());
        // 共通関数FCT001を呼び出す
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        // FCT001.対象顧客参照権限有無 == "0"(権限なし)の場合
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), NO_AUTHORITY)) {
            // レスポンスに権限なしエラー(errors.butenAccountNotExist)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // FCT001.取引停止フラグ == "1"(取引停止口座)の場合
        if (StringUtils.equals(fct001Res.getTradeSuspendFlag(), SUSPENSION_ACCOUNT)) {
            // レスポンスに取引停止口座エラー(errors.cmn.selectedAccount.outOfService)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_OUT_OF_SERVICE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_OUT_OF_SERVICE.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ②取引コース媒介可否チェックを行う
        InputFct003Dto fct003Req = new InputFct003Dto();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct003Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct003Req.setAccountNumber(cc.getAccountNumber());
        // 証券金銭種別(99)をセットする
        fct003Req.setProductCd(PRODUCT_CD);
        // 取引種別(1)をセットする
        fct003Req.setTradeCd(TRADE_CD);
        // 国籍コード(99)をセットする
        fct003Req.setCountryCd(COUNTRY_CD);
        // 通貨コード(999)をセットする
        fct003Req.setCurrencyCode(CURRENCY_CODE);
        // 共通関数FCT003を呼び出す
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        // 媒介可取引有無 = "0"(なし)
        if (StringUtils.equals(fct003Res.getMediateAbleTradeFlag(), NON)) {
            // レスポンスに取引不可エラー(errors.cmn.selectedAccountCourse.unavailable)を返す
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "9", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTED_ACCOUNT_COURSE_UNAVAILABLE.key, IfaCommonUtil.getMessage(
                            MessageId.ERRORS_SELECTED_ACCOUNT_COURSE_UNAVAILABLE.key, new String[] { codeName }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ③ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ④振込先銀行口座情報を取得する
        IfaWithdrawInputSql001RequestModel selSql001Req = new IfaWithdrawInputSql001RequestModel();
        // SQLパラメータの設定
        selSql001Req.setCustomerCode(cc.getCustomerCode());
        DataList<IfaWithdrawInputSql001ResponseModel> selSql001Res = new DataList<IfaWithdrawInputSql001ResponseModel>();
        selSql001Res = dao.selectIfaWithdrawInputSql001(selSql001Req);
        // SQLの実行結果が0件の場合はレスポンス結果を処理せず終了する
        if (selSql001Res.getDataList().isEmpty()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_TRANSFERBANKNOTEXIST.key, IfaCommonUtil.getMessage(
                            MessageId.ERRORS_TRANSFERBANKNOTEXIST.key, new String[] { cc.getCustomerCode() }));
            return dtoRes;
        }

        // ⑤出金確認APIの呼び出し
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 計上日(出金日で直近の日付)をセットする
        String postingDate = SHARED_SPACE;
        if (!StringUtil.isNullOrEmpty(dtoReq.getMinPayDate())) {
            String minPayDateYYYYMMDD = dtoReq.getMinPayDate().replace("/", "");
            postingDate = minPayDateYYYYMMDD.substring(minPayDateYYYYMMDD.length() - 4);
        }
        EstimateCashPaymentOrderOutData api001Res = new EstimateCashPaymentOrderOutData();
        try {
            api001Res = estimateCashPaymentOrder(cc.getButenCode(), cc.getAccountNumber(), SHARED_STR, postingDate);
        } catch (Exception e) {
            LOGGER.warn("出金確認APIがエラーの場合(API応答なし)" + e.getMessage());
            // ⑤APIレスポンスチェック
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_ESTIMATECASHPAYMENT_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_ESTIMATECASHPAYMENT_FAILED.key,
                            new String[] { api001Res.getCode(), api001Res.getMessage() }));
            return dtoRes;
        }
        // ⑥APIレスポンスチェック
        apiErrorUtil.checkApiResponse(api001Res.getShubetu(), api001Res.getCode(), api001Res.getMessage());
        if (apiErrorUtil.isFatal()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_ESTIMATECASHPAYMENT_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_ESTIMATECASHPAYMENT_FAILED.key,
                            new String[] { api001Res.getCode(), api001Res.getMessage() }));
            return dtoRes;
        }

        // ⑦システム共通日付を取得する。
        Date currentDate = ifaDateUtil.getCurrentDate();

        // ⑧システム共通日付が営業日か非営業日かを営業日情報チェックで判定する。
        OutputFct033Dto outputFct003Dto = new OutputFct033Dto();
        InputFct033Dto inputFct003Dto = new InputFct033Dto();
        inputFct003Dto.setDate(currentDate);
        try {
            outputFct003Dto = fct033.doCheck(inputFct003Dto);
        } catch (Exception e) {
            LOGGER.warn("営業日情報チェックで判定がエラー:" + e.getMessage());
            // ⑤画面を閉じて、出金入力画面を表示する。
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
            return dtoRes;
        }

        // ⑨翌営業日または翌々営業日の日付を出金可能日(From)として取得する。
        // ⑩4営業日後または5営業日後の日付を出金可能日(To)として取得する。
        Integer dayF = ONE_DAY_F;
        Integer dayT = FOUR_DAY_T;
        // 本日の時間16:00に取得
        LocalTime localTime = LocalTime.of(16, 00, 0);
        LocalDateTime todayLocalTime = LocalDateTime.of(LocalDate.now(), localTime);
        // システム共通日付が営業日かつ時刻が16:00より前の場合は翌営業日
        if (StringUtils.equals(outputFct003Dto.getBusinessDayCheckFlag(), BUSINESS_DAY_CHECK_FLAG_BUSINESS_DAY)
                && todayLocalTime.isAfter(LocalDateTime.now())) {
            dayF = ONE_DAY_F;
            dayT = FOUR_DAY_T;
        } else {
            dayF = TWO_DAY_F;
            dayT = FIVE_DAY_T;
        }
        InputFct007Dto inputFct007DtoF = new InputFct007Dto();
        // 基準日(システム共通日付)をセットする
        inputFct007DtoF.setStandardDate(currentDate);
        // カレンダー区分("0"（証券営業日ベース）)をセットする
        inputFct007DtoF.setCalendarType(CALENDARTYPE);
        // 日数("1"または"2")をセットする
        inputFct007DtoF.setDay(dayF);

        InputFct007Dto inputFct007DtoT = new InputFct007Dto();
        // 基準日(システム共通日付)をセットする
        inputFct007DtoT.setStandardDate(currentDate);
        // カレンダー区分("0"（証券営業日ベース）)をセットする
        inputFct007DtoT.setCalendarType(CALENDARTYPE);
        // 日数("4"または"5")をセットする
        inputFct007DtoT.setDay(dayT);

        OutputFct007Dto outputFct007DtoF = new OutputFct007Dto();
        OutputFct007Dto outputFct007DtoT = new OutputFct007Dto();
        try {
            outputFct007DtoF = fct007.getData(inputFct007DtoF);
            outputFct007DtoT = fct007.getData(inputFct007DtoT);
        } catch (Exception e) {
            LOGGER.warn("出金可能日(From)または出金可能日(To)を取得がエラー:" + e.getMessage());
            // ⑤画面を閉じて、出金入力画面を表示する。
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
            return dtoRes;
        }

        IfaWithdrawInputA001ResponseDto resDto = new IfaWithdrawInputA001ResponseDto();
        // 出金可能金額
        if (!StringUtil.isNullOrEmpty(dtoReq.getMinPayDate())) {
            resDto.setAcBalance(api001Res.getAcBalance());
        }
        // 出金可能日(From)
        resDto.setPaymentDayFrom(DateUtil.format(outputFct007DtoF.getDesignatedDate(), DateUtil.SEPARATED_YYYYMMDD));
        // 出金可能日(To)
        resDto.setPaymentDayTo(DateUtil.format(outputFct007DtoT.getDesignatedDate(), DateUtil.SEPARATED_YYYYMMDD));
        // 振込先銀行口座情報
        resDto.setBankAccountInfo(selSql001Res.getDataList());
        // 出金明細リスト
        resDto.setPayEstimateData(api001Res.getPayEstimateData());
        resDtoList.add(resDto);
        // レスポンスをコントローラーに返却する。
        dtoRes = apiErrorUtil.createDataList(resDtoList, null);
        return dtoRes;
    }

    /**
     * アクションID：A002
     * アクション名：出金可能額を取得
     * リクエスト：IfaWithdrawInputA002RequestDto Dto
     * レスポンス：IfaWithdrawInputA002ResponseDto Dto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 出金可能額を取得の際、例外が発生した場合
     */
    public DataList<IfaWithdrawInputA002ResponseDto> selectAcBalanceA002(IfaWithdrawInputA002RequestDto dtoReq)
            throws Exception {
        DataList<IfaWithdrawInputA002ResponseDto> dtoRes = new DataList<IfaWithdrawInputA002ResponseDto>();
        List<IfaWithdrawInputA002ResponseDto> resDtoList = new ArrayList<IfaWithdrawInputA002ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaWithdrawInputServiceImpL.selectAcBalanceA002");
        }
        
        // ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ①出金確認APIの呼び出し
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 計上日(出金日)をセットする
        String minPayDateYYYYMMDD = dtoReq.getPayDate().replace("/", "");
        String postingDate = minPayDateYYYYMMDD.substring(minPayDateYYYYMMDD.length() - 4);
        EstimateCashPaymentOrderOutData api001Res = new EstimateCashPaymentOrderOutData();
        try {
            api001Res = estimateCashPaymentOrder(cc.getButenCode(), cc.getAccountNumber(), SHARED_STR, postingDate);
        } catch (Exception e) {
            LOGGER.warn("出金確認APIがエラーの場合(API応答なし)" + e.getMessage());
            // APIレスポンスチェック
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_ESTIMATECASHPAYMENT_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_ESTIMATECASHPAYMENT_FAILED.key,
                            new String[] { api001Res.getCode(), api001Res.getMessage() }));
            return dtoRes;
        }
        // APIレスポンスチェック
        apiErrorUtil.checkApiResponse(api001Res.getShubetu(), api001Res.getCode(), api001Res.getMessage());
        if (apiErrorUtil.isFatal()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_ESTIMATECASHPAYMENT_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_ESTIMATECASHPAYMENT_FAILED.key,
                            new String[] { api001Res.getCode(), api001Res.getMessage() }));
            return dtoRes;
        }
        IfaWithdrawInputA002ResponseDto resDto = new IfaWithdrawInputA002ResponseDto();
        resDto.setAcBalance(api001Res.getAcBalance());
        resDtoList.add(resDto);
        // レスポンスをコントローラーに返却する。
        dtoRes = apiErrorUtil.createDataList(resDtoList, null);
        return dtoRes;
    }

    /**
     * アクションID：A003
     * アクション名：出金確認
     * リクエスト：IfaWithdrawInputA003RequestDto Dto
     * レスポンス：IfaWithdrawInputA003ResponseDto model
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 出金確認の際、例外が発生した場合
     */
    public DataList<IfaWithdrawInputA003ResponseDto> executeConfirmCheckA003(IfaWithdrawInputA003RequestDto dtoReq)
            throws Exception {
        DataList<IfaWithdrawInputA003ResponseDto> dtoRes = new DataList<IfaWithdrawInputA003ResponseDto>();
        List<IfaWithdrawInputA003ResponseDto> resDtoList = new ArrayList<IfaWithdrawInputA003ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaWithdrawInputServiceImpL.executeConfirmCheckA003");
        }

        // ①利用者の口座に対する権限チェックを行う
        InputFct001Dto fct001Req = new InputFct001Dto();
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct001Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct001Req.setAccountNumber(cc.getAccountNumber());
        // 共通関数FCT001を呼び出す
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        // FCT001.対象顧客参照権限有無 == "0"(権限なし)の場合
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), NO_AUTHORITY)) {
            // レスポンスに権限なしエラー(errors.butenAccountNotExist)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // FCT001.取引停止フラグ == "1"(取引停止口座)の場合
        if (StringUtils.equals(fct001Res.getTradeSuspendFlag(), SUSPENSION_ACCOUNT)) {
            // レスポンスに取引停止口座エラー(errors.cmn.selectedAccount.outOfService)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_OUT_OF_SERVICE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_OUT_OF_SERVICE.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ②取引コース媒介可否チェックを行う
        InputFct003Dto fct003Req = new InputFct003Dto();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct003Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct003Req.setAccountNumber(cc.getAccountNumber());
        // 証券金銭種別(99)をセットする
        fct003Req.setProductCd(PRODUCT_CD);
        // 取引種別(1)をセットする
        fct003Req.setTradeCd(TRADE_CD);
        // 国籍コード(99)をセットする
        fct003Req.setCountryCd(COUNTRY_CD);
        // 通貨コード(999)をセットする
        fct003Req.setCurrencyCode(CURRENCY_CODE);
        // 共通関数FCT003を呼び出す
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        // 媒介可取引有無 = "0"(なし)
        if (StringUtils.equals(fct003Res.getMediateAbleTradeFlag(), NON)) {
            // レスポンスに取引不可エラー(errors.cmn.selectedAccountCourse.unavailable)を返す
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "9", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTED_ACCOUNT_COURSE_UNAVAILABLE.key, IfaCommonUtil.getMessage(
                            MessageId.ERRORS_SELECTED_ACCOUNT_COURSE_UNAVAILABLE.key, new String[] { codeName }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ③口座の取引制限チェックを行う
        InputFct021Dto fct021Req = new InputFct021Dto();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct021Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct021Req.setAccountNumber(cc.getAccountNumber());
        // 証券金銭種別(99)をセットする
        fct021Req.setProductCd(PRODUCT_CD);
        // 取引種別(1)をセットする
        fct021Req.setTradeCd(TRADE_CD);
        // 国籍コード(99)をセットする
        fct021Req.setCountryCd(COUNTRY_CD);
        // 通貨コード(999)をセットする
        fct021Req.setCurrencyCode(CURRENCY_CODE);
        // 共通関数FCT021を呼び出す
        OutputFct021Dto fct021Res = new OutputFct021Dto();
        fct021Res = fct021.doCheck(fct021Req);
        // FCT021.注意情報エラー有無=="1"(あり)の場合
        if (StringUtils.equals(fct021Res.getNoteInfoErrFlag(), YES)) {
            // レスポンスにエラー(errors.cmn.noticeErrorCheck)を返す
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "9", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_NOTICE_ERROR_CHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOTICE_ERROR_CHECK.key, new String[] { codeName }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // FCT021.お知らせエラー有無=="1"(あり)の場合
        if (StringUtils.equals(fct021Res.getNoteLimitErrFlag(), YES)) {
            // レスポンスにエラー(errors.informationCheck)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_INFORMATION_CHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATION_CHECK.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // FCT021.注意情報アラート有無=="1"(あり)の場合
        IfaWithdrawInputA003ResponseDto resDto = new IfaWithdrawInputA003ResponseDto();
        if (StringUtils.equals(fct021Res.getNoteInfoAlertFlag(), YES)) {
            // 注意情報アラート(warnings.cmn.noticeWarningCheck)を格納する
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "9", "1");
            resDto.setNoticeInfoAlert(
                    IfaCommonUtil.getMessage(MessageId.WARNINGS_NOTICE_WARNING_CHECK.key, new String[] { codeName }));
        }
        // if(FCT021.お知らせアラート有無=="1"(あり)の場合){
        if (StringUtils.equals(fct021Res.getNoteLimitAlertFlag(), YES)) {
            // お知らせアラート(warnings.cmm.informationCheck)を格納する
            resDto.setNoticeAlert(IfaCommonUtil.getMessage(MessageId.WARNINGS_CMM_INFORMATION_CHECK.key));
        }

        // ④ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ⑤出金確認APIの呼び出し
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 計上日(出金日)をセットする
        String minPayDateYYYYMMDD = dtoReq.getPayDate().replace("/", "");
        String postingDate = minPayDateYYYYMMDD.substring(minPayDateYYYYMMDD.length() - 4);
        EstimateCashPaymentOrderOutData api001Res = new EstimateCashPaymentOrderOutData();
        try {
            api001Res = estimateCashPaymentOrder(cc.getButenCode(), cc.getAccountNumber(), dtoReq.getPayAmount(),
                    postingDate);
        } catch (Exception e) {
            LOGGER.warn("出金確認APIがエラーの場合(API応答なし)" + e.getMessage());
            // APIレスポンスチェック
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_ESTIMATECASHPAYMENT_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_ESTIMATECASHPAYMENT_FAILED.key,
                            new String[] { api001Res.getCode(), api001Res.getMessage() }));
            return dtoRes;
        }
        // APIレスポンスチェック
        apiErrorUtil.checkApiResponse(api001Res.getShubetu(), api001Res.getCode(), api001Res.getMessage());
        if (apiErrorUtil.isFatal()) {
            // 【IFAP刷新Ph.2_Day.3】UAT問合せ管理表のNo.4対応
            // 出金可能額を超えた出金額を入力した際のエラーメッセージ変更：「返却余力が不足しています」 ⇒ 「出金可能額を超過しています」
            if (API_RESULT_CASH_PAYMENT_ERROR_CODE.equals(StringUtil.trim(api001Res.getCode()))) {
                api001Res.setMessage(API_RESULT_CASH_PAYMENT_ERROR_MESSAGE);
            }
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_ESTIMATECASHPAYMENT_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_ESTIMATECASHPAYMENT_FAILED.key,
                            new String[] { api001Res.getCode(), api001Res.getMessage() }));
            return dtoRes;
        }
        resDto.setAcBalance(api001Res.getAcBalance());
        resDto.setAcBalanceAfter(api001Res.getAcBalanceAfter());
        resDtoList.add(resDto);
        // レスポンスをコントローラーに返却する。
        dtoRes = apiErrorUtil.createDataList(resDtoList, null);
        return dtoRes;
    }

    /**
     * アクションID：A004
     * アクション名：出金取消確認
     * レスポンス：IfaWithdrawInputA004ResponseDto Dto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 出金取消確認の際、例外が発生した場合
     */
    public DataList<IfaWithdrawInputA004ResponseDto> cancelConfirmCheckA004(IfaWithdrawInputA004RequestDto dtoReq)
            throws Exception {
        DataList<IfaWithdrawInputA004ResponseDto> dtoRes = new DataList<IfaWithdrawInputA004ResponseDto>();
        List<IfaWithdrawInputA004ResponseDto> resDtoList = new ArrayList<IfaWithdrawInputA004ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaWithdrawInputServiceImpL.cancelConfirmCheckA004");
        }

        // ①利用者の口座に対する権限チェックを行う
        InputFct001Dto fct001Req = new InputFct001Dto();
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct001Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct001Req.setAccountNumber(cc.getAccountNumber());
        // 共通関数FCT001を呼び出す
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        // FCT001.対象顧客参照権限有無 == "0"(権限なし)の場合
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), NO_AUTHORITY)) {
            // レスポンスに権限なしエラー(errors.butenAccountNotExist)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // FCT001.取引停止フラグ == "1"(取引停止口座)の場合
        if (StringUtils.equals(fct001Res.getTradeSuspendFlag(), SUSPENSION_ACCOUNT)) {
            // レスポンスに取引停止口座エラー(errors.cmn.selectedAccount.outOfService)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_OUT_OF_SERVICE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_OUT_OF_SERVICE.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ②取引コース媒介可否チェックを行う
        InputFct003Dto fct003Req = new InputFct003Dto();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct003Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct003Req.setAccountNumber(cc.getAccountNumber());
        // 証券金銭種別(99)をセットする
        fct003Req.setProductCd(PRODUCT_CD);
        // 取引種別(1)をセットする
        fct003Req.setTradeCd(TRADE_CD);
        // 国籍コード(99)をセットする
        fct003Req.setCountryCd(COUNTRY_CD);
        // 通貨コード(999)をセットする
        fct003Req.setCurrencyCode(CURRENCY_CODE);
        // 共通関数FCT003を呼び出す
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        // 媒介可取引有無 = "0"(なし)
        if (StringUtils.equals(fct003Res.getMediateAbleTradeFlag(), NON)) {
            // レスポンスに取引不可エラー(errors.cmn.selectedAccountCourse.unavailable)を返す
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "9", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTED_ACCOUNT_COURSE_UNAVAILABLE.key, IfaCommonUtil.getMessage(
                            MessageId.ERRORS_SELECTED_ACCOUNT_COURSE_UNAVAILABLE.key, new String[] { codeName }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ③口座の取引制限チェックを行う
        InputFct021Dto fct021Req = new InputFct021Dto();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct021Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct021Req.setAccountNumber(cc.getAccountNumber());
        // 証券金銭種別(99)をセットする
        fct021Req.setProductCd(PRODUCT_CD);
        // 取引種別(1)をセットする
        fct021Req.setTradeCd(TRADE_CD);
        // 国籍コード(99)をセットする
        fct021Req.setCountryCd(COUNTRY_CD);
        // 通貨コード(999)をセットする
        fct021Req.setCurrencyCode(CURRENCY_CODE);
        // 共通関数FCT021を呼び出す
        OutputFct021Dto fct021Res = new OutputFct021Dto();
        fct021Res = fct021.doCheck(fct021Req);
        // FCT021.注意情報エラー有無=="1"(あり)の場合
        if (StringUtils.equals(fct021Res.getNoteInfoErrFlag(), YES)) {
            // レスポンスにエラー(errors.cmn.noticeErrorCheck)を返す
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "9", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_NOTICE_ERROR_CHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOTICE_ERROR_CHECK.key, new String[] { codeName }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // FCT021.お知らせエラー有無=="1"(あり)の場合
        if (StringUtils.equals(fct021Res.getNoteLimitErrFlag(), YES)) {
            // レスポンスにエラー(errors.informationCheck)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_INFORMATION_CHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATION_CHECK.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // FCT021.注意情報アラート有無=="1"(あり)の場合
        IfaWithdrawInputA004ResponseDto resDto = new IfaWithdrawInputA004ResponseDto();
        if (StringUtils.equals(fct021Res.getNoteInfoAlertFlag(), YES)) {
            // 注意情報アラート(warnings.cmn.noticeWarningCheck)を格納する
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "9", "1");
            resDto.setNoticeInfoAlert(
                    IfaCommonUtil.getMessage(MessageId.WARNINGS_NOTICE_WARNING_CHECK.key, new String[] { codeName }));
        }
        // if(FCT021.お知らせアラート有無=="1"(あり)の場合){
        if (StringUtils.equals(fct021Res.getNoteLimitAlertFlag(), YES)) {
            // お知らせアラート(warnings.cmm.informationCheck)を格納する
            resDto.setNoticeAlert(IfaCommonUtil.getMessage(MessageId.WARNINGS_CMM_INFORMATION_CHECK.key));
        }

        // ④ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // レスポンスをコントローラーに返却する。
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

    /**
     * 出金確認APIの呼び出し
     *
     * @param butenCode     部店コード
     * @param accountNumber 口座番号
     * @param pAmount       出金額
     * @param postingDate    計上日
     * @return outdata
     * @throws Exception システムエラー
     */
    private EstimateCashPaymentOrderOutData estimateCashPaymentOrder(String butenCode, String accountNumber,
            String pAmount, String postingDate) throws Exception {

        EstimateCashPaymentOrderIn api001Req = new EstimateCashPaymentOrderIn();
        EstimateCashPaymentOrderInData api001ReqData = new EstimateCashPaymentOrderInData();
        // 部店コード(顧客共通情報.部店コード)をセットする
        api001ReqData.setButenCd(butenCode);
        // 口座番号(顧客共通情報.口座番号)をセットする
        api001ReqData.setKozaNo(accountNumber);
        // 出金額(画面.出金額)をセットする
        api001ReqData.setPAmount(pAmount);
        // 振替区分("3△" ：銀行振込(送金))をセットする
        api001ReqData.setTransferId(TRANSFER_ID);
        // 仕訳NO("   ")をセットする
        api001ReqData.setPostingNo(POSTING_NO);
        // 資金("△")をセットする
        api001ReqData.setFund(SHARED_SPACE);
        // 送金料("△△△△")をセットする
        api001ReqData.setRemittanceFee(REMITTANCE_FEE);
        // 計上日(出金日)をセットする
        api001ReqData.setPostingDate(postingDate);
        // 一括("△")をセットする
        api001ReqData.setSumUpId(SHARED_SPACE);
        // 受渡者("△")をセットする
        api001ReqData.setDelivery(SHARED_SPACE);
        // 受付経路区分("1")をセットする
        api001ReqData.setCallcenterKbn(CALLCENTER_KBN);
        // ユーザーID(ユーザ共通情報.CCSログイン用ID)をセットする
        if (IfaCommonUtil.getUserAccount().getCcsUserId().length() > 10) {
            api001ReqData.setUserId(IfaCommonUtil.getUserAccount().getCcsUserId().substring(0, 10));
        } else {
            api001ReqData.setUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
        }
        // 余力チェック区分("△")をセットする
        api001ReqData.setCheckId(SHARED_SPACE);
        // 指定口座区分("△")をセットする
        api001ReqData.setAccountDesKbn(SHARED_SPACE);
        api001Req.setIndata(api001ReqData);
        return apiWrapper.estimateCashPaymentOrder(api001Req);
    }
}
