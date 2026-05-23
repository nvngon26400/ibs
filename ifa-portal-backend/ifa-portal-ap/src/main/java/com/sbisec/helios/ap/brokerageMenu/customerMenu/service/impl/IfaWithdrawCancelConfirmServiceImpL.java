package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaWithdrawCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaWithdrawExecuteConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawCancelConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawCancelConfirmSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawCancelConfirmSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawCancelConfirmTrandIdAndEdabanRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawCancelConfirmTrandIdAndEdabanResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaWithdrawCancelConfirmService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.CashPaymentCancelOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.CashPaymentCancelOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.CashPaymentCancelOrderOutData;

/**
 * 画面ID：SUB0202_0601-03_1 画面名：取消確認
 *
 * @author xin.huang
 */
@Component(value = "cmpIfaWithdrawCancelConfirmService")
public class IfaWithdrawCancelConfirmServiceImpL implements IfaWithdrawCancelConfirmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaWithdrawCancelConfirmServiceImpL.class);

    @Autowired
    private IfaWithdrawCancelConfirmDao dao;

    @Autowired
    private IfaWithdrawExecuteConfirmDao sequenceDao;

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
    private static final String TRANSACTION_ID = "0";

    /** 商品区分 */
    private static final String ORDER_TYPE = "P";

    /** アラート確認チェックボックス */
    private static final String ALERT_CONFIRM = "0";

    /** 削除フラグ1 */
    private static final String EXECUTE_SAKUJO_FLG = "1";

    /** 削除フラグ0 */
    private static final String CANCEL_SAKUJO_FLG = "0";

    /** 発注区分 */
    private static final String HACCHUU_KBN = "P";

    /** 取消ステータスコード */
    private static final String TORIKESHI_STS_CD = "0";

    /** 振替区分 */
    private static final String TRANSFER_ID = "3 ";

    /** 仕訳NO */
    private static final String POSTING_NO = "0";

    /** 送金料 */
    private static final String SOUKINNRYOU = "0";

    /** 共通スペース */
    private static final String SHARED_SPACE = " ";

    /** 商品区分 */
    private static final String SHOUHINN_KBN = "P";

    /** 受付経路区分 */
    private static final String UKETSUKE_KEIRO_KBN = "1";
    
    /** 即時入金区分 */
    private static final String SOKUJI_NYUUKIN_KBN = "1";

    /** ステータスコード(受付可能) */
    private static final String ACCEPTED = "1";

    /** ステータスコード(受付不可) */
    private static final String REJECTED = "3";

    /** 枝番 */
    private static final String EDABAN = "1";

    /** 区分.対象取引（メッセージ表示用） */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";

    enum MessageId {
        // errors.butenAccountNotExis
        ERRORS_BUTENACCOUNTNOTEXIST("errors.butenAccountNotExist"),
        // errors.cmn.selectedAccount.outOfService
        ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE("errors.cmn.selectedAccount.outOfService"),
        // errors.cmn.selectedAccountCourse.unavailable
        ERRORS_SELECTED_ACCOUNT_COURSE_UNAVAILABLE("errors.cmn.selectedAccountCourse.unavailable"),
        // errors.cmn.cancelPayment.failed
        ERRORS_CMN_CANCELPAYMENT_FAILED("errors.cmn.cancelPayment.failed"),
        // errors.cmn.noticeErrorCheck
        ERRORS_NOTICE_ERROR_CHECK("errors.cmn.noticeErrorCheck"),
        // errors.informationCheck
        ERRORS_INFORMATION_CHECK("errors.informationCheck"),
        // errors.cmn.information.occurs#3
        ERRORS_CMN_INFORMATION_OCCURS("errors.cmn.information.occurs#3"),
        // errors.cmn.ccsid.unregistered
        ERRORS_CMN_CCSID_UNREGISTERED("errors.cmn.ccsid.unregistered"),
        // errors.insertWithdrawCancelHistory.failed
        ERRORS_INSERTWITHDRAWCANCELHISTORY_FAILED("errors.insertWithdrawCancelHistory.failed"),
        // warnings.cmn.cancelPayment.completed
        WARNINGS_CMN_CANCELPAYMENT_COMPLETED("warnings.cmn.cancelPayment.completed");

        private String key;

        private MessageId(String key) {
            this.key = key;
        }
    }

    /**
     * アクションID：A001
     * アクション名：取消実行
     * Dtoレスポンス：IfaWithdrawCancelConfirmA001ResponseDto
     * Dtoリクエスト：IfaWithdrawCancelConfirmA001RequestDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 取消実行の際、例外が発生した場合
     */
    public DataList<IfaWithdrawCancelConfirmA001ResponseDto> cancelConfirmA001(
            IfaWithdrawCancelConfirmA001RequestDto dtoReq) throws Exception {
        DataList<IfaWithdrawCancelConfirmA001ResponseDto> dtoRes = new DataList<IfaWithdrawCancelConfirmA001ResponseDto>();
        List<IfaWithdrawCancelConfirmA001ResponseDto> resDtoList = new ArrayList<IfaWithdrawCancelConfirmA001ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaWithdrawCancelConfirmServiceImpL.cancelConfirmA001");
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

        // 既存の出金履歴トランザクションID、枝番を取得する
        IfaWithdrawCancelConfirmTrandIdAndEdabanRequestModel reqModel = new IfaWithdrawCancelConfirmTrandIdAndEdabanRequestModel();
        // 部店CD(顧客共通情報.部店コード)をセットする
        reqModel.setButenCd(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        reqModel.setAccountNumber(cc.getAccountNumber());
        // 発注区分("P")をセットする
        reqModel.setHacchuuKbn(HACCHUU_KBN);
        // EC入出金番号(画面遷移時パラメーター.EC入出金番号)をセットする
        reqModel.setEcNyushukkinNo(dtoReq.getRpNumber());
        List<IfaWithdrawCancelConfirmTrandIdAndEdabanResponseModel> tranIdAndEdabanList = new ArrayList<>();
        try {
            tranIdAndEdabanList = dao.getIfaWithdrawCancelConfirmSql006(reqModel);
        } catch (Exception e) {
            LOGGER.warn("既存の出金履歴トランザクションID、枝番を取得処理エラー:" + e.getMessage());
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_INSERTWITHDRAWCANCELHISTORY_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_INSERTWITHDRAWCANCELHISTORY_FAILED.key));
            return dtoRes;
        }

        // トランザクションID
        String tranId = null;
        // 枝番
        String edaban = null;
        if (tranIdAndEdabanList.size() == 1) {
            tranId = tranIdAndEdabanList.get(0).getTranId();
            edaban = tranIdAndEdabanList.get(0).getEdaban();
        } else {
            // SEQUENCE(トランザクションID)を取得する
            tranId = sequenceDao.selectSeqTbCashPayRecJournalIfa();
            edaban = EDABAN;
        }
        // 登録日時
        Date tourokuNichiji = ifaDateUtil.getCurrentDate();
        // ⑤出金取消時用の出金履歴データをIFA入出金履歴テーブルに新規登録する。
        IfaWithdrawCancelConfirmSql002RequestModel sqlReq002 = new IfaWithdrawCancelConfirmSql002RequestModel();
        // トランザクションID(既存トランザクションID)をセットする
        sqlReq002.setTranId(tranId);
        // 枝番(既存枝番 + 1)をセットする
        sqlReq002.setEdaban(edaban);
        // 顧客ID(既存顧客コード)をセットする
        sqlReq002.setKokyakuId(cc.getCustomerCode());
        // 部店CD(顧客共通情報.部店コード)をセットする
        sqlReq002.setButenCd(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        sqlReq002.setKouzaNo(cc.getAccountNumber());
        // 発注区分("P")をセットする
        sqlReq002.setHacchuuKbn(HACCHUU_KBN);
        // 取消ステータスコード("0")をセットする
        sqlReq002.setTorikeshiStsCd(TORIKESHI_STS_CD);
        // 取消ユーザーID(ユーザー共通情報.ユーザーID)をセットする
        sqlReq002.setTorikeshiUserId(IfaCommonUtil.getUserAccount().getUserId());
        // EC入出金番号(画面遷移時パラメーター.EC入出金番号)をセットする
        sqlReq002.setEcNyushukkinNo(dtoReq.getRpNumber());
        // 振替区分("3△")をセットする
        sqlReq002.setFurikaeKbn(TRANSFER_ID);
        // 仕訳ＮＯ("0")をセットする
        sqlReq002.setShiwakeNo(POSTING_NO);
        // 資金("△")をセットする
        sqlReq002.setShikinn(SHARED_SPACE);
        // 送金料("0")をセットする
        sqlReq002.setSoukinnryou(SOUKINNRYOU);
        // 受渡者("△")をセットする
        sqlReq002.setUkewatashisha(SHARED_SPACE);
        // 一括("△")をセットする
        sqlReq002.setIkkatsu(SHARED_SPACE);
        // 受付経路区分("1")をセットする
        sqlReq002.setUketsukeKeiroKbn(UKETSUKE_KEIRO_KBN);
        // 商品区分("P")をセットする
        sqlReq002.setShouhinnKbn(SHOUHINN_KBN);
        // 余力チェック区分("△")をセットする
        sqlReq002.setYoryokuCheckKbn(SHARED_SPACE);
        // 削除フラグ("0")をセットする
        sqlReq002.setSakujoFlg(CANCEL_SAKUJO_FLG);
        // 即時入金区分("1")をセットする
        sqlReq002.setSokujiNyuukinKbn(SOKUJI_NYUUKIN_KBN);
        // ジュニアNISA口座区分("△")をセットする
        sqlReq002.setJrnisaAccountKbn(SHARED_SPACE);
        // 登録日時(現在日時)をセットする
        sqlReq002.setTourokuNichiji(tourokuNichiji);
        // 作成者(ユーザー共通情報.ユーザーID)をセットする
        sqlReq002.setCreateUser(IfaCommonUtil.getUserAccount().getUserId());
        // 更新者(ユーザー共通情報.ユーザーID)をセットする
        sqlReq002.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
        try {
            dao.insertIfaWithdrawCancelConfirmSql002(sqlReq002);
        } catch (Exception e) {
            LOGGER.warn("出金取消履歴を登録処理エラー:" + e.getMessage());
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_INSERTWITHDRAWCANCELHISTORY_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_INSERTWITHDRAWCANCELHISTORY_FAILED.key));
            return dtoRes;
        }

        // ⑥出金取消APIの呼び出し
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        CashPaymentCancelOrderIn api001Req = new CashPaymentCancelOrderIn();
        CashPaymentCancelOrderInData api001ReqData = new CashPaymentCancelOrderInData();
        // トランザクションID("0")をセットする
        api001ReqData.setTransactionId(TRANSACTION_ID);
        // 部店コード(顧客共通情報.部店コード)をセットする
        api001ReqData.setButenCd(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        api001ReqData.setKozaNo(cc.getAccountNumber());
        // 商品区分("P"：出金)をセットする
        api001ReqData.setOrderType(ORDER_TYPE);
        // EC入出金番号(画面.EC入出金番号)をセットする
        api001ReqData.setRpNumber(dtoReq.getRpNumber());
        // 取消受付経路区分("1")をセットする
        api001ReqData.setCxlCallcenterKbn(CALLCENTER_KBN);
        // 取消ユーザーID(ユーザ共通情報.CCSログイン用ID)をセットする
        if (IfaCommonUtil.getUserAccount().getCcsUserId().length() > 10) {
            api001ReqData.setCxlUserId(IfaCommonUtil.getUserAccount().getCcsUserId().substring(0, 10));
        } else {
            api001ReqData.setCxlUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
        }
        api001Req.setIndata(api001ReqData);
        CashPaymentCancelOrderOutData api001Res = new CashPaymentCancelOrderOutData();
        try {
            api001Res = apiWrapper.cashPaymentCancelOrder(api001Req);
        } catch (Exception e) {
            LOGGER.warn("出金取消APIがエラーの場合(API応答なし)" + e.getMessage());
            try {
                IfaWithdrawCancelConfirmSql005RequestModel SqlReq005 = new IfaWithdrawCancelConfirmSql005RequestModel();
                // トランザクションID(既存トランザクションID)をセットする
                SqlReq005.setTranId(tranId);
                // 枝番(既存枝番 + 1)をセットする
                SqlReq005.setEdaban(edaban);
                // 更新者(ユーザー共通情報.ユーザーID)をセットする
                SqlReq005.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
                // 計上日(API001.出金予定日)をセットする
                String sysDate = DateUtil.format(ifaDateUtil.getCurrentDate(), DateUtil.YYYYMMDD);
                SqlReq005.setKeijoubi(sysDate.substring(4, 8));
                dao.updateIfaWithdrawCancelConfirmSql005(SqlReq005);
            } catch (Exception dberror) {
                LOGGER.warn("出金取消APIがエラーの場合(API応答なし)、出金取消履歴を更新処理エラー:" + dberror.getMessage());
            }
            // APIレスポンスチェック
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_CANCELPAYMENT_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_CANCELPAYMENT_FAILED.key,
                            new String[] { api001Res.getCode(), api001Res.getMessage() }));
            return dtoRes;
        }

        // ⑦出金後にIFA入出金履歴テーブルの出金履歴データを再度更新する。
        apiErrorUtil.checkApiResponse(api001Res.getShubetu(), api001Res.getCode(), api001Res.getMessage());
        if (apiErrorUtil.isFatal()) {
            // 発注がエラーの場合
            IfaWithdrawCancelConfirmSql004RequestModel SqlReq004 = new IfaWithdrawCancelConfirmSql004RequestModel();
            // トランザクションID(既存トランザクションID)をセットする
            SqlReq004.setTranId(tranId);
            // 枝番(既存枝番 + 1)をセットする
            SqlReq004.setEdaban(edaban);
            // 取消ステータスコード("3"：REJECTED(受付不可))をセットする
            SqlReq004.setTorikeshiStsCd(REJECTED);
            // エラーコード(API001.エラーコード)をセットする
            SqlReq004.setErrorCd(api001Res.getCode());
            // エラーメッセージ(API001.エラーメッセージ)をセットする
            SqlReq004.setErrorMessage(api001Res.getMessage());
            // 計上日(API001.出金予定日)をセットする
            if (api001Res.getPayday() != null && api001Res.getPayday().length() > 4) {
                SqlReq004.setKeijoubi(api001Res.getPayday().substring(4, 8));
            } else {
                SqlReq004.setKeijoubi(api001Res.getPayday());
            }
            // 種別(API001.種別)をセットする
            SqlReq004.setShubetsu(api001Res.getShubetu());
            // 更新者(ユーザー共通情報.ユーザーID)をセットする
            SqlReq004.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
            try {
                dao.updateIfaWithdrawCancelConfirmSql004(SqlReq004);
            } catch (Exception e) {
                LOGGER.warn("出金取消APIがエラーの場合、出金取消履歴を更新処理エラー:" + e.getMessage());
            }
            // ⑧APIレスポンスチェック
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_CANCELPAYMENT_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_CANCELPAYMENT_FAILED.key,
                            new String[] { api001Res.getCode(), api001Res.getMessage() }));
            return dtoRes;
        } else {
            // 出金取消APIが正常の場合、出金取消履歴を更新処理
            IfaWithdrawCancelConfirmSql003RequestModel SqlReq003 = new IfaWithdrawCancelConfirmSql003RequestModel();
            // トランザクションID(既存トランザクションID)をセットする
            SqlReq003.setTranId(tranId);
            // 枝番(既存枝番 + 1)をセットする
            SqlReq003.setEdaban(edaban);
            // 取消ステータスコード("1"：ACCEPTED(受付可能))をセットする
            SqlReq003.setTorikeshiStsCd(ACCEPTED);
            // エラーコード(API001.エラーコード)をセットする
            SqlReq003.setErrorCd(api001Res.getCode());
            // エラーメッセージ(API001.エラーメッセージ)をセットする
            SqlReq003.setErrorMessage(api001Res.getMessage());
            // 金額(画面遷移時パラメーター.出金額)
            SqlReq003.setKingaku(dtoReq.getPayAmount());
            // 計上日(API001.出金予定日)をセットする
            if (api001Res.getPayday() != null && api001Res.getPayday().length() > 4) {
                SqlReq003.setKeijoubi(api001Res.getPayday().substring(4, 8));
            } else {
                SqlReq003.setKeijoubi(api001Res.getPayday());
            }
            // 種別(API001.種別)をセットする
            SqlReq003.setShubetsu(api001Res.getShubetu());
            // 取消受付経路区分("1")をセットする
            SqlReq003.setTorikeshiUketsukeKeiroKbn(CALLCENTER_KBN);
            // 取消出金可能金額(API001.出金可能金額)をセットする
            SqlReq003.setTorikeshiKingaku(api001Res.getAcBalance());
            // 取消受付後出金可能金額(API001.受付後の出金可能金額)をセットする
            SqlReq003.setTorikeshiGoKingaku(api001Res.getAcBalanceAfter());
            // 出金予定日(API001.出金予定日)をセットする
            SqlReq003.setShukkinYoteibi(api001Res.getPayday());
            // 削除フラグ("1")をセットする
            SqlReq003.setSakujoFlg(EXECUTE_SAKUJO_FLG);
            // 更新者(ユーザー共通情報.ユーザーID)をセットする
            SqlReq003.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
            try {
                dao.updateIfaWithdrawCancelConfirmSql003(SqlReq003);
            } catch (Exception e) {
                LOGGER.warn("出金取消APIが正常の場合、出金取消履歴を更新処理エラー:" + e.getMessage());
                // 登録エラーを格納し次の処理へ。
                apiErrorUtil.addDbError(MessageId.WARNINGS_CMN_CANCELPAYMENT_COMPLETED.key, null);
            }
        }
        IfaWithdrawCancelConfirmA001ResponseDto resDto = new IfaWithdrawCancelConfirmA001ResponseDto();
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
