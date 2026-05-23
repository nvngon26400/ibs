package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaWithdrawExecuteConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawExecuteConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawExecuteConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawExecuteConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawExecuteConfirmSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawExecuteConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawExecuteConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaWithdrawExecuteConfirmService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.CashPaymentOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.CashPaymentOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.CashPaymentOrderOutData;

/**
 * 画面ID：SUB0202_0601-02_1 画面名：出金確認
 *
 * @author xin.huang
 */
@Component(value = "cmpIfaWithdrawExecuteConfirmService")
public class IfaWithdrawExecuteConfirmServiceImpL implements IfaWithdrawExecuteConfirmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaWithdrawExecuteConfirmServiceImpL.class);

    @Autowired
    private IfaWithdrawExecuteConfirmDao dao;

    @Autowired
    private Fct001 fct001;

    @Autowired
    private Fct003 fct003;

    @Autowired
    private Fct021 fct021;

    @Autowired
    private ApiWrapper apiWrapper;

    @Autowired
    private CodeListService codelistservice;

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

    /** トランザクションID */
    private static final String TRANSACTION_ID = "00000000000000000000000000000000";

    /** DB仕訳NO */
    private static final String DB_POSTING_NO = "0";
    
    /** API仕訳NO */
    private static final String API_POSTING_NO = "   ";
    
    /** 共通スペース */
    private static final String SHARED_SPACE = " ";

    /** 送金料 */
    private static final String REMITTANCE_FEE = "    ";

    /** 発注区分 */
    private static final String HACCHUU_KBN = "P";

    /** 取消ステータスコード */
    private static final String TORIKESHI_STS_CD = "0";

    /** 送金料 */
    private static final String SOUKINNRYOU = "0";

    /** 商品区分 */
    private static final String SHOUHINN_KBN = "P";

    /** 削除フラグ */
    private static final String SAKUJO_FLG = "0";

    /** 即時入金区分 */
    private static final String SOKUJI_NYUUKIN_KBN = "1";

    /** アラート確認チェックボックス */
    private static final String ALERT_CONFIRM = "0";

    /** 受付経路区分 */
    private static final String UKETSUKE_KEIRO_KBN = "1";

    /** ステータスコード(受付可能) */
    private static final String ACCEPTED = "1";

    /** ステータスコード(受付不可) */
    private static final String REJECTED = "3";

    /** 枝番 */
    private static final String EDABAN = "1";

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
        ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE("errors.cmn.selectedAccount.outOfService"),
        // errors.cmn.selectedAccountCourse.unavailable
        ERRORS_SELECTED_ACCOUNT_COURSE_UNAVAILABLE("errors.cmn.selectedAccountCourse.unavailable"),
        // errors.cmn.doPayment.failed
        ERRORS_CMN_DOPAYMENT_FAILED("errors.cmn.doPayment.failed"),
        // errors.cmn.noticeErrorCheck
        ERRORS_NOTICE_ERROR_CHECK("errors.cmn.noticeErrorCheck"),
        // errors.informationCheck
        ERRORS_INFORMATION_CHECK("errors.informationCheck"),
        // errors.insertWithdrawHistory.failed
        ERRORS_INSERTWITHDRAWHISTORY_FAILED("errors.insertWithdrawHistory.failed"),
        // errors.cmn.information.occurs#3
        ERRORS_CMN_INFORMATION_OCCURS("errors.cmn.information.occurs#3"),
        // errors.cmn.ccsid.unregistered
        ERRORS_CMN_CCSID_UNREGISTERED("errors.cmn.ccsid.unregistered"),
        // warnings.cmn.doPayment.completed
        WARNINGS_CMN_DOPAYMENT_COMPLETED("warnings.cmn.doPayment.completed");

        private String key;

        private MessageId(String key) {
            this.key = key;
        }
    }

    /**
     * アクションID：A001
     * アクション名：出金実行
     * Dtoリクエスト：IfaWithdrawExecuteConfirmA001RequestDto
     * Dtoレスポンス：IfaWithdrawExecuteConfirmA001ResponseDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 出金実行の際、例外が発生した場合
     */
    public DataList<IfaWithdrawExecuteConfirmA001ResponseDto> executeConfirmA001(
            IfaWithdrawExecuteConfirmA001RequestDto dtoReq) throws Exception {
        DataList<IfaWithdrawExecuteConfirmA001ResponseDto> dtoRes = new DataList<IfaWithdrawExecuteConfirmA001ResponseDto>();
        List<IfaWithdrawExecuteConfirmA001ResponseDto> resDtoList = new ArrayList<IfaWithdrawExecuteConfirmA001ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaWithdrawExecuteConfirmServiceImpL.executeConfirmA001");
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
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE.key));
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
        // 注意情報アラート確認チェックボックス=OFF または 非表示：エラーを返す。
        if (StringUtils.equals(fct021Res.getNoteInfoAlertFlag(), YES)
                && StringUtils.equals(dtoReq.getNoticeInfoAlertConfirm(), ALERT_CONFIRM)) {
            // 注意情報アラート(warnings.cmn.noticeWarningCheck)を格納する
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_INFORMATION_OCCURS.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_INFORMATION_OCCURS.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // if(FCT021.お知らせアラート有無=="1"(あり)の場合){
        // お知らせアラート確認チェックボックス=OFF または 非表示：エラーを返す。
        if (StringUtils.equals(fct021Res.getNoteLimitAlertFlag(), YES)
                && StringUtils.equals(dtoReq.getNoticeAlertConfirm(), ALERT_CONFIRM)) {
            // お知らせアラート(warnings.cmm.informationCheck)を格納する
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_INFORMATION_OCCURS.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_INFORMATION_OCCURS.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ④ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // SEQUENCE(トランザクションID)を取得する
        String tranId = dao.selectSeqTbCashPayRecJournalIfa();

        // ⑤IFA入出金履歴テーブルに出金履歴を登録する。
        IfaWithdrawExecuteConfirmSql001RequestModel sqlReq001 = new IfaWithdrawExecuteConfirmSql001RequestModel();
        // トランザクションID(日付(8桁)+通番(10桁))をセットする
        sqlReq001.setTranId(tranId);
        // 枝番("1")をセットする
        sqlReq001.setEdaban(EDABAN);
        // 顧客ID(顧客共通情報.顧客コード)をセットする
        sqlReq001.setKokyakuId(cc.getCustomerCode());
        // 部店CD(顧客共通情報.部店コード)をセットする
        sqlReq001.setButenCd(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        sqlReq001.setKouzaNo(cc.getAccountNumber());
        // 発注区分("P")をセットする
        sqlReq001.setHacchuuKbn(HACCHUU_KBN);
        // ユーザーID(ユーザー共通情報.ユーザーID)をセットする
        sqlReq001.setUserId(IfaCommonUtil.getUserAccount().getUserId());
        // 取消ステータスコード("0")をセットする
        sqlReq001.setTorikeshiStsCd(TORIKESHI_STS_CD);
        // 金額(画面.出金額)をセットする
        sqlReq001.setKingaku(dtoReq.getPayAmount());
        // 振替区分("3△")をセットする
        sqlReq001.setFurikaeKbn(TRANSFER_ID);
        // 仕訳ＮＯ("0")をセットする
        sqlReq001.setShiwakeNo(DB_POSTING_NO);
        // 資金("△")をセットする
        sqlReq001.setShikinn(SHARED_SPACE);
        // 送金料("0")をセットする
        sqlReq001.setSoukinnryou(SOUKINNRYOU);
        // 受渡者("△")をセットする
        sqlReq001.setUkewatashisha(SHARED_SPACE);
        // 一括("△")をセットする
        sqlReq001.setIkkatsu(SHARED_SPACE);
        // 受付経路区分("1")をセットする
        sqlReq001.setUketsukeKeiroKbn(UKETSUKE_KEIRO_KBN);
        // 商品区分("P")をセットする
        sqlReq001.setShouhinnKbn(SHOUHINN_KBN);
        // 余力チェック区分("△")をセットする
        sqlReq001.setYoryokuCheckKbn(SHARED_SPACE);
        // 削除フラグ("0")をセットする
        sqlReq001.setSakujoFlg(SAKUJO_FLG);
        // 即時入金区分("1")をセットする
        sqlReq001.setSokujiNyuukinKbn(SOKUJI_NYUUKIN_KBN);
        // ジュニアNISA口座区分("△")をセットする
        sqlReq001.setJrnisaAccountKbn(SHARED_SPACE);
        // 作成者(ユーザー共通情報.ユーザーID)をセットする
        sqlReq001.setCreateUser(IfaCommonUtil.getUserAccount().getUserId());
        // 更新者(ユーザー共通情報.ユーザーID)をセットする
        sqlReq001.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
        try {
            dao.insertIfaWithdrawExecuteConfirmSql001(sqlReq001);
        } catch (Exception e) {
            LOGGER.warn("出金履歴を登録処理エラー:" + e.getMessage());
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_INSERTWITHDRAWHISTORY_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_INSERTWITHDRAWHISTORY_FAILED.key));
            return dtoRes;
        }

        // ⑥出金確認APIの呼び出し
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        CashPaymentOrderIn api001Req = new CashPaymentOrderIn();
        CashPaymentOrderInData api001ReqData = new CashPaymentOrderInData();
        // トランザクションID("00000000000000000000000000000000")をセットする
        api001ReqData.setTransactionId(TRANSACTION_ID);
        // 部店コード(顧客共通情報.部店コード)をセットする
        api001ReqData.setButenCd(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        api001ReqData.setKozaNo(cc.getAccountNumber());
        // 出金額(画面.出金額)をセットする
        api001ReqData.setPAmount(dtoReq.getPayAmount());
        // 振替区分("3△" ：銀行振込(送金))をセットする
        api001ReqData.setTransferId(TRANSFER_ID);
        // 仕訳NO(" ")をセットする
        api001ReqData.setPostingNo(API_POSTING_NO);
        // 資金("△")をセットする
        api001ReqData.setFund(SHARED_SPACE);
        // 送金料("△△△△")をセットする
        api001ReqData.setRemittanceFee(REMITTANCE_FEE);
        // 計上日(画面.出金日)をセットする
        String minPayDateYYYYMMDD = dtoReq.getPayDate().replace("/", "");
        String minPayDateMMDD = minPayDateYYYYMMDD.substring(minPayDateYYYYMMDD.length() - 4);
        api001ReqData.setPostingDate(minPayDateMMDD);
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
        CashPaymentOrderOutData api001Res = new CashPaymentOrderOutData();
        try {
            api001Res = apiWrapper.cashPaymentOrder(api001Req);
        } catch (Exception e) {
            LOGGER.warn("出金受付APIがエラーの場合(API応答なし)" + e.getMessage());
            try {
                IfaWithdrawExecuteConfirmSql004RequestModel sqlReq004 = new IfaWithdrawExecuteConfirmSql004RequestModel();
                // トランザクションID(日付(8桁)+通番(10桁))をセットする
                sqlReq004.setTranId(tranId);
                // 枝番("1")をセットする
                sqlReq004.setEdaban(EDABAN);
                // 更新者(ユーザー共通情報.ユーザーID)をセットする
                sqlReq004.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
                dao.updateIfaWithdrawExecuteConfirmSql004(sqlReq004);
            } catch (Exception dberror) {
                LOGGER.warn("出金受付APIがエラーの場合(API応答なし)、出金履歴を更新処理エラー:" + dberror.getMessage());
            }
            // ⑧APIレスポンスチェック
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_DOPAYMENT_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_DOPAYMENT_FAILED.key,
                            new String[] { api001Res.getCode(), api001Res.getMessage() }));
            return dtoRes;
        }

        // ⑦IFA入出金履歴テーブルに登録した出金履歴を更新する。
        apiErrorUtil.checkApiResponse(api001Res.getShubetu(), api001Res.getCode(), api001Res.getMessage());
        if (apiErrorUtil.isFatal()) {
            // 発注がエラーの場合
            IfaWithdrawExecuteConfirmSql003RequestModel sqlReq003 = new IfaWithdrawExecuteConfirmSql003RequestModel();
            // トランザクションID(日付(8桁)+通番(10桁))をセットする
            sqlReq003.setTranId(tranId);
            // 枝番("1")をセットする
            sqlReq003.setEdaban(EDABAN);
            // 受注日時(API001.受注日)をセットする
            sqlReq003.setJuchuuNichiji(api001Res.getAcceptDate());
            // ステータスコード("3"：REJECTED(受付不可))をセットする
            sqlReq003.setStsCd(REJECTED);
            // エラーコード(API001.エラーコード)をセットする
            sqlReq003.setErrorCd(api001Res.getCode());
            // エラーメッセージ(API001.エラーメッセージ)をセットする
            sqlReq003.setErrorMessage(api001Res.getMessage());
            // 種別(API001.種別)をセットする
            sqlReq003.setShubetsu(api001Res.getShubetu());
            // 更新者(ユーザー共通情報.ユーザーID)をセットする
            sqlReq003.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
            try {
                dao.updateIfaWithdrawExecuteConfirmSql003(sqlReq003);
            } catch (Exception e) {
                LOGGER.warn("出金受付APIがエラーの場合、出金履歴を更新処理エラー:" + e.getMessage());
            }
            // ⑦APIレスポンスチェック
            // 【IFAP刷新Ph.2_Day.3】UAT問合せ管理表のNo.4対応
            if (API_RESULT_CASH_PAYMENT_ERROR_CODE.equals(StringUtil.trim(api001Res.getCode()))) {
                api001Res.setMessage(API_RESULT_CASH_PAYMENT_ERROR_MESSAGE);
            }
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_DOPAYMENT_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_DOPAYMENT_FAILED.key,
                            new String[] { api001Res.getCode(), api001Res.getMessage() }));
            return dtoRes;
        } else {
            // 発注が正常の場合
            IfaWithdrawExecuteConfirmSql002RequestModel sqlReq002 = new IfaWithdrawExecuteConfirmSql002RequestModel();
            // トランザクションID(日付(8桁)+通番(10桁))をセットする
            sqlReq002.setTranId(tranId);
            // 枝番("1")をセットする
            sqlReq002.setEdaban(EDABAN);
            // 受注日時(API001.受注日)をセットする
            sqlReq002.setJuchuuNichiji(api001Res.getAcceptDate());
            // ステータスコード("1"：ACCEPTED(受付可能))をセットする
            sqlReq002.setStsCd(ACCEPTED);
            // エラーコード(API001.エラーコード)をセットする
            sqlReq002.setErrorCd(api001Res.getCode());
            // エラーメッセージ(API001.エラーメッセージ)をセットする
            sqlReq002.setErrorMessage(api001Res.getMessage());
            // EC入出金番号(API001.EC入出金番号)をセットする
            sqlReq002.setEcNyushukkinNo(api001Res.getRpNo());
            // 計上日(API001.出金予定日)をセットする
            if (api001Res.getPayday() != null && api001Res.getPayday().length() > 4) {
                sqlReq002.setKeijoubi(api001Res.getPayday().substring(4, 8));
            } else {
                sqlReq002.setKeijoubi(api001Res.getPayday());
            }
            // 種別(API001.種別)をセットする
            sqlReq002.setShubetsu(api001Res.getShubetu());
            // 出金可能金額(API001.出金可能金額)をセットする
            sqlReq002.setShukkinkanouKingaku(api001Res.getAcBalance());
            // 受付後の出金可能金額(API001.受付後の出金可能金額)をセットする
            sqlReq002.setUketsukeShukkinkanouKingaku(api001Res.getAcBalanceAfter());
            // 出金予定日(API001.出金予定日)をセットする
            sqlReq002.setShukkinYoteibi(api001Res.getPayday());
            // 更新者(ユーザー共通情報.ユーザーID)をセットする
            sqlReq002.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
            try {
                dao.updateIfaWithdrawExecuteConfirmSql002(sqlReq002);
            } catch (Exception e) {
                LOGGER.warn("出金受付APIが正常の場合、出金履歴を更新処理エラー:" + e.getMessage());
                // 登録エラーを格納し次の処理へ。
                apiErrorUtil.addDbError(MessageId.WARNINGS_CMN_DOPAYMENT_COMPLETED.key, null);
            }
        }
        IfaWithdrawExecuteConfirmA001ResponseDto resDto = new IfaWithdrawExecuteConfirmA001ResponseDto();
        // 出金可能金額
        resDto.setAcBalance(api001Res.getAcBalance());
        // 受付後の出金可能金額
        resDto.setAcBalanceAfter(api001Res.getAcBalanceAfter());
        resDtoList.add(resDto);
        // レスポンスをコントローラーに返却する。
        dtoRes = apiErrorUtil.createDataList(resDtoList, null);
        return dtoRes;
    }
}
