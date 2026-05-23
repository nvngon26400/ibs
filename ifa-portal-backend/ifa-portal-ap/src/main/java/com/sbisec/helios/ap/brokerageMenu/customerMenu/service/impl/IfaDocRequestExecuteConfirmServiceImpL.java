package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct007;
import com.sbisec.helios.ap.bizcommon.component.Fct033;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct033Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct033Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDocRequestExecuteConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestExecuteConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestExecuteConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestExecuteConfirmSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestExecuteConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestExecuteConfirmSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestExecuteConfirmSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestExecuteConfirmSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestExecuteConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestExecuteConfirmA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestExecuteConfirmA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestExecuteConfirmA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDocRequestExecuteConfirmService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundFundDocReadHistoryBulkReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundFundDocReadHistoryBulkRes;
import com.sbisec.helios.ap.safe.service.SafeCommonService;
import com.sbisec.helios.ap.safe.service.SafeFundTradeService;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulk;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulkApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulkApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulkItem;
import com.sbisec.helios.ap.safe.utils.SafeApiUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0704-02_1 画面名：書類請求確認
 *
 * @author xin.huang
 */
@Component(value = "cmpIfaDocRequestExecuteConfirmService")
public class IfaDocRequestExecuteConfirmServiceImpL implements IfaDocRequestExecuteConfirmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDocRequestExecuteConfirmServiceImpL.class);

    @Autowired
    private IfaDocRequestExecuteConfirmDao dao;

    @Autowired
    private Fct001 fct001;

    @Autowired
    private Fct007 fct007;

    @Autowired
    private Fct033 fct033;

    @Autowired
    private SafeFundTradeService safeFundTradeService;

    @Autowired
    private SafeCommonService safeCommonService;

    @Autowired
    private IfaDateUtil ifaDateUtil;

    /** 権限なし */
    private static final String NO_AUTHORITY = "0";

    /** 取引停止口座 */
    private static final String SUSPENSION_ACCOUNT = "1";

    /** 枝番 */
    private static final String EDABAN = "1";

    /** 取消区分 */
    private static final String TORIKESHI_KBN = "0";

    /** 処理回数 */
    private static final String SHORI_KAISUU = "0";

    /** 削除フラグ */
    private static final String SAKUJO_FLG = "0";

    /** 前回超かんたんフラグ */
    private static final String ZENKAI_TYOUKANTAN_FLG = " ";

    /** 種別 */
    private static final String BM_KOUFU_SHUBETSU_ONE = "1";

    /** 種別 */
    private static final String BM_KOUFU_SHUBETSU_TWO = "2";

    /** データ出力区分 */
    private static final String DATA_OUTPUT_KBN_ZERO = "0";

    /** 目論見書閲覧区分 */
    private static final String PROSPECTUS_ZERO = "0";

    /** カレンダー区分 */
    private static final String CALENDAR_TYPE_ONE = "1";

    /** カレンダー区分 */
    private static final String CALENDAR_TYPE_ZERO = "0";

    /** 営業日チェックフラグ-非営業日 */
    private static final String BUSINESS_DAY_CHECK_FLAG_ZERO = "0";

    /** 営業日チェックフラグ-営業日 */
    private static final String BUSINESS_DAY_CHECK_FLAG_ONE = "1";

    /** 翌日フラグ */
    private static final String NEXT_DAY_FLG = "1";

    enum MessageId {
        // errors.butenAccountNotExis
        ERRORS_BUTENACCOUNTNOTEXIST("errors.butenAccountNotExist"),
        // errors.cmn.selectedAccount.outOfService
        ERRORS_OUT_OF_SERVICE("errors.cmn.selectedAccount.outOfService"),
        // errors.registerRequestPaper.failed
        ERRORS_REGISTERREQUESTPAPER_FAILED("errors.registerRequestPaper.failed"),
        // errors.cmn.ccsid.unregistered
        ERRORS_CMN_CCSID_UNREGISTERED("errors.cmn.ccsid.unregistered"),
        // errors.cmn.getProspectus.failed
        ERRORS_CMN_GETPROSPECTUS_FAILED("errors.cmn.getProspectus.failed"),
        // errors.cmn.getProspectus.invalid
        ERRORS_CMN_GETPROSPECTUS_INVALID("errors.cmn.getProspectus.invalid"),
        // errors.cmn.getEdelivAgreementCount.failed
        ERRORS_CMN_GETEDELIVAGREEMENTCOUNT_FAILED("errors.cmn.getEdelivAgreementCount.failed"),
        // errors.cmn.getEdelivAgreementCount.invalid
        ERRORS_CMN_GETEDELIVAGREEMENTCOUNT_INVALID("errors.cmn.getEdelivAgreementCount.invalid"),
        // errors.getBmDeliveryTime.failed
        ERRORS_GETBMDELIVERYTIME_FAILED("errors.getBmDeliveryTime.failed"),
        // errors.registerBmDelivery.failed
        ERRORS_REGISTERBMDELIVERY_FAILED("errors.registerBmDelivery.failed"),
        // errors.cmn.ccsid.maxLength
        ERRORS_CMN_CCSID_MAXLENGTH("errors.cmn.ccsid.maxLength");

        private String key;

        private MessageId(String key) {
            this.key = key;
        }
    }

    /**
     * アクションID：A002
     * アクション名：書類請求確認
     * Dtoレスポンス：IfaDocRequestExecuteConfirmA002RequestDto
     * Dtoレスポンス：IfaDocRequestExecuteConfirmA002ResponseDto
     * Modelリクエスト：IfaDocRequestExecuteConfirmSql001RequestModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求確認の際、例外が発生した場合
     */
    public DataList<IfaDocRequestExecuteConfirmA002ResponseDto> executeConfirmA002(IfaDocRequestExecuteConfirmA002RequestDto dtoReq)
            throws Exception {
        DataList<IfaDocRequestExecuteConfirmA002ResponseDto> dtoRes = new DataList<IfaDocRequestExecuteConfirmA002ResponseDto>();
        List<IfaDocRequestExecuteConfirmA002ResponseDto> resDtoList = new ArrayList<IfaDocRequestExecuteConfirmA002ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocRequestExecuteConfirmServiceImpL.executeConfirmA002");
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

        // ②ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ③入力した書類請求の登録を行う。
        IfaDocRequestExecuteConfirmSql001RequestModel sql001Req = new IfaDocRequestExecuteConfirmSql001RequestModel();
        // 書類請求NO(通番(10桁))をセットする
        sql001Req.setShoruiSeikyuuNo(dao.selectSeqTbRequestPapersIfa());
        // 枝番("1")をセットする
        sql001Req.setEdaban(EDABAN);
        // 部店コード(顧客共通情報.部店コード)をセットする
        sql001Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        sql001Req.setAccountNumber(cc.getAccountNumber());
        // 顧客コード(顧客共通情報.顧客コード)をセットする
        sql001Req.setKokyakuId(cc.getCustomerCode());
        // 書類コード(リクエスト.書類コード)をセットする
        sql001Req.setShoruiCd(dtoReq.getShoruiCd());
        // 書類名(リクエスト.書類名)をセットする
        sql001Req.setShoruimei(dtoReq.getShoruimei());
        // 書類分類コード(リクエスト.分類コード)をセットする
        sql001Req.setShoruiBunruiCd(dtoReq.getBunruiCd());
        // 書類分類名(リクエスト.分類名)をセットする
        sql001Req.setShoruiBunruiMei(dtoReq.getBunruimei());
        // 発送ステータス("01")をセットする
        sql001Req.setHassouSts(dtoReq.getHassouSts());
        // ユーザーID(ユーザー共通情報.ユーザーID)をセットする
        sql001Req.setUserId(IfaCommonUtil.getUserAccount().getUserId());
        // 取消区分("0")をセットする
        sql001Req.setTorikeshiKbn(TORIKESHI_KBN);
        // 部数(リクエスト.部数)をセットする
        sql001Req.setBusuu(dtoReq.getBusuu());
        // オプション1(リクエスト.オプション1(名前))をセットする
        sql001Req.setOption1(dtoReq.getOption1());
        // 選択名1(リクエスト.オプション1(入力))をセットする
        sql001Req.setSentakuMei1(dtoReq.getSentakuMei1());
        // オプション2(リクエスト.オプション2(名前))をセットする
        sql001Req.setOption2(dtoReq.getOption2());
        // 選択名2(リクエスト.オプション2(入力))をセットする
        sql001Req.setSentakuMei2(dtoReq.getSentakuMei2());
        // オプション3(リクエスト.オプション3(名前))をセットする
        sql001Req.setOption3(dtoReq.getOption3());
        // 選択名3(リクエスト.オプション3(入力))をセットする
        sql001Req.setSentakuMei3(dtoReq.getSentakuMei3());
        // テキスト1(リクエスト.テキスト1(名前))をセットする
        sql001Req.setTxt1(dtoReq.getTxt1());
        // 入力内容1(リクエスト.テキスト1(入力))をセットする
        sql001Req.setTxtNaiyou1(dtoReq.getTxtNaiyou1());
        // テキスト2(リクエスト.テキスト2(名前))をセットする
        sql001Req.setTxt2(dtoReq.getTxt2());
        // 入力内容2(リクエスト.テキスト2(入力))をセットする
        sql001Req.setTxtNaiyou2(dtoReq.getTxtNaiyou2());
        // テキスト3(リクエスト.テキスト3(名前))をセットする
        sql001Req.setTxt3(dtoReq.getTxt3());
        // 入力内容3(リクエスト.テキスト3(入力))をセットする
        sql001Req.setTxtNaiyou3(dtoReq.getTxtNaiyou3());
        // テキスト4(リクエスト.テキスト4(名前))をセットする
        sql001Req.setTxt4(dtoReq.getTxt4());
        // 入力内容4(リクエスト.テキスト4(入力))をセットする
        sql001Req.setTxtNaiyou4(dtoReq.getTxtNaiyou4());
        // テキスト5(リクエスト.テキスト5(名前))をセットする
        sql001Req.setTxt5(dtoReq.getTxt5());
        // 入力内容5(リクエスト.テキスト5(入力))をセットする
        sql001Req.setTxtNaiyou5(dtoReq.getTxtNaiyou5());
        // 内容キャプション(リクエスト.内容(名前))をセットする
        sql001Req.setNaiyouCaption(dtoReq.getNaiyouCaption());
        // 内容(リクエスト.内容(入力))をセットする
        sql001Req.setNaiyou(dtoReq.getNaiyou());
        // 備考キャプション(リクエスト.内容(名前))をセットする
        sql001Req.setBikouCaption(dtoReq.getBikouCaption());
        // 備考(リクエスト.内容(備考))をセットする
        sql001Req.setBikou(dtoReq.getBikou());
        // 処理回数("0")をセットする
        sql001Req.setShoriKaisuu(SHORI_KAISUU);
        // 削除フラグ
        sql001Req.setSakujoFlg(SAKUJO_FLG);
        // 前回超かんたんフラグ(" ")をセットする
        sql001Req.setZenkaiTyoukantanFlg(ZENKAI_TYOUKANTAN_FLG);
        try {
            dao.insertIfaDocRequestExecuteConfirmSql001(sql001Req);
        } catch (Exception e) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_REGISTERREQUESTPAPER_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_REGISTERREQUESTPAPER_FAILED.key));
            return dtoRes;
        }

        // レスポンスをコントローラーに返却する。
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

    /**
     * アクションID：A005
     * アクション名：BM交付情報登録
     * Dtoレスポンス：IfaDocRequestExecuteConfirmA005RequestDto
     * Dtoレスポンス：IfaDocRequestExecuteConfirmA005ResponseDto
     * Modelリクエスト：IfaDocRequestExecuteConfirmSql005RequestModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求確認の際、例外が発生した場合
     */
    public DataList<IfaDocRequestExecuteConfirmA005ResponseDto> executeConfirmA005(
            IfaDocRequestExecuteConfirmA005RequestDto dtoReq) throws Exception {
        DataList<IfaDocRequestExecuteConfirmA005ResponseDto> dtoRes = new DataList<IfaDocRequestExecuteConfirmA005ResponseDto>();
        List<IfaDocRequestExecuteConfirmA005ResponseDto> resDtoList = new ArrayList<IfaDocRequestExecuteConfirmA005ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDocRequestExecuteConfirmServiceImpL.executeConfirmA005");
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

        // ②ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_CCSID_UNREGISTERED.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ユーザ共通情報.CCSログイン用IDが9桁以上の場合、エラーを返す。
        if (IfaCommonUtil.getUserAccount().getCcsUserId().length() > 8) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_CCSID_MAXLENGTH.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_CCSID_MAXLENGTH.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ③リクエスト.種別が"1"の場合、以下の処理を実行する。
        if (BM_KOUFU_SHUBETSU_ONE.equals(dtoReq.getBmKoufuShubetsu())) {
            // リクエスト.購入可否判定区分が"6"以外の場合：投信閲覧履歴一括照会外部チャネル用APIを実行する。
            // Safe Api: /safe/fundTrade/fund/fund_doc_read_history/bulk
            GetFundFundDocReadHistoryBulkReq safeApi001Req = new GetFundFundDocReadHistoryBulkReq();
            safeApi001Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
            FundDocReadHistoryBulkApiIn fundDocReadHistoryBulkApiIn = new FundDocReadHistoryBulkApiIn();
            fundDocReadHistoryBulkApiIn.setDataOutputKbn(DATA_OUTPUT_KBN_ZERO); // "0"：未閲覧分の目論見書のみ出力
            FundDocReadHistoryBulkItem fundDocReadHistoryBulkItem = new FundDocReadHistoryBulkItem();
            fundDocReadHistoryBulkItem.setFundType(" "); // △:未入力、口数/金額の一括取得
            fundDocReadHistoryBulkItem.setFundCode(dtoReq.getFundCode()); // リクエスト.協会コード
            List<FundDocReadHistoryBulkItem> fundsList = new ArrayList<>();
            fundsList.add(fundDocReadHistoryBulkItem);
            fundDocReadHistoryBulkApiIn.setFunds(fundsList);
            safeApi001Req.setParameter(fundDocReadHistoryBulkApiIn);
            GetFundFundDocReadHistoryBulkRes safeApi001Res = null;
            try {
                safeApi001Res = safeFundTradeService.getBulkFundDocReadHistory(safeApi001Req);
            } catch (Exception e) {
                // エラー(API応答なし)の場合、エラーを返す。
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_GETPROSPECTUS_FAILED.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETPROSPECTUS_FAILED.key));
                // レスポンスを呼出元に返却する
                return dtoRes;
            }
            FundDocReadHistoryBulkApiOut fundDocReadHistoryBulkApiOut = safeApi001Res.getFundDocReadHistoryBulkApiOut();
            dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, fundDocReadHistoryBulkApiOut);
            boolean prospectus = false;
            if (fundDocReadHistoryBulkApiOut != null && fundDocReadHistoryBulkApiOut.getFunds() != null
                    && fundDocReadHistoryBulkApiOut.getFunds().size() > 0) {
                List<FundDocReadHistoryBulk> funds = fundDocReadHistoryBulkApiOut.getFunds();
                for (FundDocReadHistoryBulk fund : funds) {
                    if (PROSPECTUS_ZERO.equals(fund.getProspectus())) {
                        prospectus = true;
                        break;
                    }
                }
            } else {
                // ファンド一覧にデータが存在しない場合、エラーを返す。
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_GETPROSPECTUS_INVALID.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETPROSPECTUS_INVALID.key));
                // レスポンスを呼出元に返却する
                return dtoRes;
            }
            // ファンド一覧.目論見書閲覧区分の値が"0"のデータが存在する場合、エラーを返す。
            if (prospectus) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_GETPROSPECTUS_INVALID.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETPROSPECTUS_INVALID.key));
                // レスポンスを呼出元に返却する
                return dtoRes;
            }
        } else {
            // ④リクエスト.種別が"1"以外の場合、以下の処理を実行する。
            // 未閲読目論見書件数取得を実行する。
            IfaDocRequestExecuteConfirmSql002RequestModel sql002Req = new IfaDocRequestExecuteConfirmSql002RequestModel();
            // 銘柄コード
            sql002Req.setFundCode(dtoReq.getMeigaraCd());
            // 部店コード
            sql002Req.setButenCode(cc.getButenCode());
            // 口座番号
            sql002Req.setAccountNumber(cc.getAccountNumber());
            // 法人区分
            sql002Req.setCorporationType(cc.getCorporationType());
            DataList<IfaDocRequestExecuteConfirmSql002ResponseModel> sql010Res = new DataList<IfaDocRequestExecuteConfirmSql002ResponseModel>();
            try {
                sql010Res = dao.selectIfaDocRequestExecuteConfirmSql002(sql002Req);
            } catch (Exception e) {
                // 未閲読目論見書件数が取得できない場合、エラーを返す。
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_GETEDELIVAGREEMENTCOUNT_FAILED.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETEDELIVAGREEMENTCOUNT_FAILED.key));
                // レスポンスを呼出元に返却する
                return dtoRes;
            }
            // 未閲読目論見書件数が"0"の場合、エラーを返す。
            if (sql010Res.getDataList().isEmpty() || "0".equals(sql010Res.getDataList().get(0).getDataNum())) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_GETEDELIVAGREEMENTCOUNT_INVALID.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_GETEDELIVAGREEMENTCOUNT_INVALID.key));
                // レスポンスを呼出元に返却する
                return dtoRes;
            }
        }

        // ⑥～⑪の処理でBM配信予定日時の取得を行う。
        Date deliveryScheduleTime = null;
        // ⑤システム共通日付(Data型)を取得する。⑫の処理でBM交付日時として登録する。
        Date date = DateUtils.truncate(new Date(), Calendar.MINUTE);
        // ⑥ ⑤で取得したシステム共通日付(Data型)から時刻(数字4桁の文字列・HH24MI形式)を取得する。
        String sysTimeHHmm = new SimpleDateFormat("HHmm").format(date);
        // ⑦ ⑥で取得した時刻を条件に指定し、IFA用BMスケジュール情報を取得する。
        IfaDocRequestExecuteConfirmSql003RequestModel sql003Req = new IfaDocRequestExecuteConfirmSql003RequestModel();
        // 時刻
        sql003Req.setSysTimeHHmm(sysTimeHHmm);
        DataList<IfaDocRequestExecuteConfirmSql003ResponseModel> sql003Res = new DataList<IfaDocRequestExecuteConfirmSql003ResponseModel>();
        try {
            sql003Res = dao.selectIfaDocRequestExecuteConfirmSql003(sql003Req);
        } catch (Exception e) {
            // エラー発生時：エラーを返す。
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_GETBMDELIVERYTIME_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_GETBMDELIVERYTIME_FAILED.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // 正常(取得データ0件)：⑥で取得した時刻に2400を足した数値を条件に指定し、再度取得を行う。
        boolean dataNumFlg = false;
        if (sql003Res.getDataList().isEmpty()) {
            dataNumFlg = true;
            sql003Req.setSysTimeHHmm(String.valueOf(Integer.parseInt(sysTimeHHmm) + 2400));
            sql003Res = dao.selectIfaDocRequestExecuteConfirmSql003(sql003Req);
            if (sql003Res.getDataList().size() != 1) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_GETBMDELIVERYTIME_FAILED.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_GETBMDELIVERYTIME_FAILED.key));
                // レスポンスを呼出元に返却する
                return dtoRes;
            }
        } else if (sql003Res.getDataList().size() > 1) {
            // 正常(取得データ2件以上)：エラーを返す。
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_GETBMDELIVERYTIME_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_GETBMDELIVERYTIME_FAILED.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ⑧ ⑦で1回目の取得データ件数が0件の場合、⑤で取得したシステム共通日付の前日日付(Data型)を取得する。
        Date designatedDate = null;
        if (dataNumFlg) {
            InputFct007Dto inputFct007Dto = new InputFct007Dto();
            // 基準日
            inputFct007Dto.setStandardDate(date);
            // カレンダー区分
            inputFct007Dto.setCalendarType(CALENDAR_TYPE_ONE);
            // 日数 設定元： ー1
            inputFct007Dto.setDay(-1);
            OutputFct007Dto outputFct007Dto = new OutputFct007Dto();
            try {
                outputFct007Dto = fct007.getData(inputFct007Dto);
            } catch (Exception e) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_GETBMDELIVERYTIME_FAILED.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_GETBMDELIVERYTIME_FAILED.key));
                // レスポンスを呼出元に返却する
                return dtoRes;
            }
            designatedDate = outputFct007Dto.getDesignatedDate();
        }

        // ⑨
        // ⑧で前日日付を取得している場合は取得した前日日付(Data型)、それ以外の場合は⑤で取得したシステム共通日付(Data型)が営業日・非営業日のどちらであるかを判定する。
        InputFct033Dto inputDto = new InputFct033Dto();
        // 日付
        if (designatedDate != null) {
            inputDto.setDate(designatedDate);
        } else {
            inputDto.setDate(date);
        }
        OutputFct033Dto outputFct033Dto = new OutputFct033Dto();
        try {
            outputFct033Dto = fct033.doCheck(inputDto);
        } catch (Exception e) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_GETBMDELIVERYTIME_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_GETBMDELIVERYTIME_FAILED.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        // ⑩営業日情報チェックの結果が非営業日の場合、以下の処理を実行する。
        if (BUSINESS_DAY_CHECK_FLAG_ZERO.equals(outputFct033Dto.getBusinessDayCheckFlag())) {
            InputFct007Dto inputFct007Dto = new InputFct007Dto();
            // 基準日
            if (designatedDate != null) {
                inputFct007Dto.setStandardDate(designatedDate);
            } else {
                inputFct007Dto.setStandardDate(date);
            }
            // カレンダー区分
            inputFct007Dto.setCalendarType(CALENDAR_TYPE_ZERO);
            // 日数
            inputFct007Dto.setDay(1);
            OutputFct007Dto outputFct007Dto = new OutputFct007Dto();
            try {
                outputFct007Dto = fct007.getData(inputFct007Dto);
            } catch (Exception e) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_GETBMDELIVERYTIME_FAILED.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_GETBMDELIVERYTIME_FAILED.key));
                // レスポンスを呼出元に返却する
                return dtoRes;
            }
            // スケジュール番号が"1"のIFA用BMスケジュール情報データの配信予定時刻を取得する。
            DataList<IfaDocRequestExecuteConfirmSql004ResponseModel> sql004Res = new DataList<IfaDocRequestExecuteConfirmSql004ResponseModel>();
            try {
                sql004Res = dao.selectIfaDocRequestExecuteConfirmSql004();
            } catch (Exception e) {
                // エラー発生時：エラーを返す。
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_GETBMDELIVERYTIME_FAILED.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_GETBMDELIVERYTIME_FAILED.key));
                // レスポンスを呼出元に返却する
                return dtoRes;
            }
            // 翌営業日(Date型)の時・分を取得した配信予定時刻(数字4桁の文字列・HH24MI形式)の値に変更する。
            String deliveryScheduleTimeStr = sql004Res.getDataList().get(0).getDeliveryScheduleTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(outputFct007Dto.getDesignatedDate());
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(deliveryScheduleTimeStr.substring(0, 2)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(deliveryScheduleTimeStr.substring(2, 4)));
            deliveryScheduleTime = calendar.getTime();
        } else if (BUSINESS_DAY_CHECK_FLAG_ONE.equals(outputFct033Dto.getBusinessDayCheckFlag())) {
            // ⑪営業日情報チェックの結果が営業日の場合、以下の処理を実行する。
            // ⑦で取得したIFA用BMスケジュール情報の翌日フラグの値が"1" かつ
            // ⑧の処理を行っていない場合、⑤で取得したシステム共通日付の翌日日付(Date型)を取得する。
            Date designatedDate11 = null;
            if (NEXT_DAY_FLG.equals(sql003Res.get(0).getNextDayFlg()) && designatedDate == null) {
                InputFct007Dto inputFct007Dto = new InputFct007Dto();
                // 基準日
                inputFct007Dto.setStandardDate(date);
                // カレンダー区分
                inputFct007Dto.setCalendarType(CALENDAR_TYPE_ONE);
                // 日数
                inputFct007Dto.setDay(1);
                OutputFct007Dto outputFct007Dto = new OutputFct007Dto();
                try {
                    outputFct007Dto = fct007.getData(inputFct007Dto);
                } catch (Exception e) {
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_GETBMDELIVERYTIME_FAILED.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_GETBMDELIVERYTIME_FAILED.key));
                    // レスポンスを呼出元に返却する
                    return dtoRes;
                }
                designatedDate11 = outputFct007Dto.getDesignatedDate();
            }
            // 翌日日付(Date型)を取得した場合は翌日日付(Date型)、
            // それ以外の場合は⑤で取得したシステム共通日付の前日日付(Data型)の時・分を⑦で取得したIFA用BMスケジュール情報の配信予定時刻(数字4桁の文字列・HH24MI形式)の値に変更する。
            Date calendarDate = null;
            if (designatedDate11 != null) {
                calendarDate = designatedDate11;
            } else {
                calendarDate = date;
            }
            String timeHHmm = sql003Res.getDataList().get(0).getDeliveryScheduleTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(calendarDate);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeHHmm.substring(0, 2)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(timeHHmm.substring(2, 4)));
            deliveryScheduleTime = calendar.getTime();
        }

        // ⑫入力したBM交付情報の登録を行う。
        IfaDocRequestExecuteConfirmSql005RequestModel sql005Req = new IfaDocRequestExecuteConfirmSql005RequestModel();
        // BM交付番号
        String systemDate = ifaDateUtil.format(IfaDateUtil.YYYYMMDD);
        String seqNum = dao.selectSeqTbBmDeliveryIfa();
        sql005Req.setBmDeliveryNo(systemDate + seqNum);
        // 種別
        sql005Req.setShubetsu(dtoReq.getBmKoufuShubetsu());
        // 部店コード
        sql005Req.setButenCode(cc.getButenCode());
        // 口座番号
        sql005Req.setAccountNumber(cc.getAccountNumber());
        // 顧客ID
        sql005Req.setKokyakuId(cc.getCustomerCode());
        // 書類コード
        sql005Req.setShoruiCd(dtoReq.getShoruiCd());
        // 書類名
        sql005Req.setShoruimei(dtoReq.getShoruimei());
        // 書類分類コード
        sql005Req.setShoruiBunruiCd(dtoReq.getBunruiCd());
        // 書類分類名
        sql005Req.setShoruiBunruiMei(dtoReq.getBunruimei());
        // 銘柄コード
        sql005Req.setMeigaraCd(dtoReq.getMeigaraCd());
        // 銘柄名
        sql005Req.setMeigaraMei(dtoReq.getMeigaraMei());
        // 協会コード
        sql005Req.setKyokaiCd(BM_KOUFU_SHUBETSU_TWO.equals(dtoReq.getBmKoufuShubetsu()) ? null : dtoReq.getFundCode());
        // BM交付日時
        sql005Req.setBmDeliveryTime(date);
        // BM配信予定日時
        sql005Req.setBmDeliveryTimeSchedule(deliveryScheduleTime);
        // ユーザーID
        sql005Req.setUserId(IfaCommonUtil.getUserAccount().getUserId());
        // CCSログイン用ID
        sql005Req.setCcsUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
        // 仲介業者コード
        sql005Req.setBrokerCode(cc.getBrokerCode());
        // 仲介業者営業員コード
        sql005Req.setIntermediaryEmpCd(cc.getBrokerChargeCode());
        // ステータス
        sql005Req.setStatus(TORIKESHI_KBN);
        try {
            dao.insertIfaDocRequestExecuteConfirmSql005(sql005Req);
        } catch (Exception e) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_REGISTERBMDELIVERY_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_REGISTERBMDELIVERY_FAILED.key));
            return dtoRes;
        }

        if (dtoRes.getErrorLevel() == ErrorLevel.WARNING.getId()) {
            if (resDtoList != null) {
                dtoRes.setDataList(resDtoList);
            }
            dtoRes.setTotalSize(dtoRes.getDataList().size());
            dtoRes.setMaxRownum(dtoRes.getDataList().size());
            dtoRes.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            return dtoRes;
        }

        // レスポンスをコントローラーに返却する。
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }
}
