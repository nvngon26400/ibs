package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaOtherRemainPowerRestrainCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainCancelConfirmSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainCancelConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainCancelConfirmSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelConfirmA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaOtherRemainPowerRestrainCancelConfirmService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaOtherRemainPowerRestrainUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import jp.co.sbisec.pcenter.dto.yanap.AdditionalPlaceOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.AdditionalPlaceOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.AdditionalPlaceOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceOutData;
import lombok.Data;

/**
 * 画面ID：SUB0202_0110-02_1
 * 画面名：その他余力拘束注文取消確認

 * @author 大連 えん
    2025/02/28 新規作成
 */
@Component(value = "cmpIfaOtherRemainPowerRestrainCancelConfirmService")
public class IfaOtherRemainPowerRestrainCancelConfirmServiceImpL implements IfaOtherRemainPowerRestrainCancelConfirmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaOtherRemainPowerRestrainCancelConfirmServiceImpL.class);

    /**
     * API呼び出しクラス
     */
    @Autowired
    private ApiWrapper apiWrapper;

    /**
     * その他余力拘束注文取消確認Dao
     */
    @Autowired
    private IfaOtherRemainPowerRestrainCancelConfirmDao dao;

    /**
     * その他余力拘束の共通部品
     */
    @Autowired
    private IfaOtherRemainPowerRestrainUtil ifaOrprUtil;

    /**
     * IFAその他注文履歴テーブルの主キー格納クラス
     */
    @Data
    private class TablePrimaryKeys {
        /** トランザクションＩＤ */
        private String tranId;
        /** 枝番 */
        private Integer edaban;
    }

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaOtherRemainPowerRestrainCancelConfirmA001RequestDto
     * Dto レスポンス：IfaOtherRemainPowerRestrainCancelConfirmA001ResponseDto
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaOtherRemainPowerRestrainCancelConfirmA001ResponseDto> initializeA001(
            IfaOtherRemainPowerRestrainCancelConfirmA001RequestDto dtoReq) throws Exception {

        List<IfaOtherRemainPowerRestrainCancelConfirmA001ResponseDto> resDto = new ArrayList<IfaOtherRemainPowerRestrainCancelConfirmA001ResponseDto>();
        IfaOtherRemainPowerRestrainCancelConfirmA001ResponseDto responseDto = new IfaOtherRemainPowerRestrainCancelConfirmA001ResponseDto();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaOtherRemainPowerRestrainCancelConfirmServiceImpL.initializeA001");
        }

        // 顧客共通情報を取得する
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // FCT001 利用者の口座に対する権限チェックを行う
        IfaOtherRemainPowerRestrainUtil.ErrorResponseDto errorDto = ifaOrprUtil.callFct001(cc);
        if (errorDto.isErrorFlg()) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorDto.getErrorMessageId(),
                    errorDto.getErrorMessage());
        }

        // API結果
        QueryAccountBalanceOutData api001OutData = new QueryAccountBalanceOutData();

        // API001呼び出し
        api001OutData = queryAccountBalance(cc);
        // API処理結果確認
        apiErrorUtil.checkApiResponse(api001OutData.getShubetu(), api001OutData.getCode(), api001OutData.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }

        // 共通処理
        getResponseColumns(dtoReq, responseDto, api001OutData);

        resDto.add(responseDto);

        return apiErrorUtil.createDataList(resDto, null);
    }

    /**
     * アクションID：A002
     * アクション名：注文取消
     * Dto リクエスト：IfaOtherRemainPowerRestrainCancelConfirmA002RequestDto
     * Dto レスポンス：IfaOtherRemainPowerRestrainCancelConfirmA002ResponseDto
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @throws exception 再検索処理で例外が発生した場合
     */
    public DataList<IfaOtherRemainPowerRestrainCancelConfirmA002ResponseDto> cancelConfirmA002(
            IfaOtherRemainPowerRestrainCancelConfirmA002RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaOtherRemainPowerRestrainCancelConfirmServiceImpL.cancelConfirmA002");
        }
        // 戻り値の初期化
        IfaOtherRemainPowerRestrainCancelConfirmA002ResponseDto responseDto = new IfaOtherRemainPowerRestrainCancelConfirmA002ResponseDto();
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();

        // 顧客共通情報を取得する
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();

        /* ↓↓↓1.FCT001 利用者の口座に対する権限チェックを行う。↓↓↓ */
        IfaOtherRemainPowerRestrainUtil.ErrorResponseDto errorDto = ifaOrprUtil.callFct001(cc);
        // エラーがあれば、メッセージを表示して、処理を終了する
        if (errorDto.isErrorFlg()) {
            return IfaCommonUtil.createDataList(
                    Collections.emptyList(),
                    ErrorLevel.FATAL,
                    errorDto.getErrorMessageId(),
                    errorDto.getErrorMessage());
        }

        /* ↓↓↓2.ユーザ共通情報.CCSログイン用IDのチェックを行う。↓↓↓ */
        // ユーザ共通情報.CCSログイン用IDを取得する
        String ccsUserId = IfaCommonUtil.getUserAccount().getCcsUserId();
        // ユーザーＩＤは10桁以上の場合、10桁を設定する。
        if (!StringUtil.isNullOrEmpty(ccsUserId) && ccsUserId.length() > 10) {
            ccsUserId = ccsUserId.substring(0, 10);
        }
        // 未設定(Null または空文字）の場合：取引不可エラーを返す。
        if (StringUtil.isNullOrEmpty(ccsUserId)) {
            return IfaCommonUtil.createDataList(
                    Collections.emptyList(),
                    ErrorLevel.FATAL,
                    IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_CMN_CCSID_UNREGISTERED.key,
                    IfaCommonUtil.getMessage(IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_CMN_CCSID_UNREGISTERED.key));
        }

        TablePrimaryKeys priKeys = null;
        /* ↓↓↓3.その他注文履歴情報(トランザクションID・枝番)を取得する。↓↓↓ */
        IfaOtherRemainPowerRestrainCancelConfirmSql001ResponseModel sql001Res = this.selectIfaOtherRemainPowerRestrainCancelConfirmSql001(dtoReq);
        if (sql001Res != null) {
            priKeys = new TablePrimaryKeys();
            priKeys.setTranId(sql001Res.getTranId());
            priKeys.setEdaban(sql001Res.getEdaban());
        // 未設定(Null または空文字）の場合：取引不可エラーを返す。
        } else {
            return IfaCommonUtil.createDataList(
                Collections.emptyList(),
                ErrorLevel.FATAL,
                IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_CMN_ORDERCANCEL_OUTOFSERVICE.key,
                IfaCommonUtil.getMessage(IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_CMN_ORDERCANCEL_OUTOFSERVICE.key));
        }

        /* ↓↓↓4.その他注文履歴情報(取消発注)の件数を取得する。↓↓↓ */
        // 取得件数が1の場合：メッセージを表示して、処理を終了する。
        if (!this.selectIfaOtherRemainPowerRestrainCancelConfirmSql002(priKeys, dtoReq)) {
            return IfaCommonUtil.createDataList(
                    Collections.emptyList(),
                    ErrorLevel.FATAL,
                    IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_CMN_ORDERCANCEL_OUTOFSERVICE.key,
                    IfaCommonUtil.getMessage(IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_CMN_ORDERCANCEL_OUTOFSERVICE.key));
        }

        /* ↓↓↓5.発注前に注文内容をその他注文履歴テーブルへ記録する。↓↓↓ */
        // DB登録NG：DB登録エラーを返す
        if (!this.insertIfaOtherRemainPowerRestrainCancelConfirmSql003(priKeys, dtoReq)) {
            return IfaCommonUtil.createDataList(
                    Collections.emptyList(),
                    ErrorLevel.FATAL,
                    IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_FRS_PREORDEREXECUTION_FAILED.key,
                    IfaCommonUtil.getMessage(IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_FRS_PREORDEREXECUTION_FAILED.key));
        }
        // 枝番 + 1
        priKeys.setEdaban(priKeys.getEdaban() + 1);
        /* ↓↓↓6.余力拘束を行う。↓↓↓ */
        AdditionalPlaceOrderOutData api002OutData = new AdditionalPlaceOrderOutData();
        String apiError = IfaOtherRemainPowerRestrainUtil.ApiResp.API_RESP_OK.key;
        try {
            // API002 その他商品の注文・出来・取消呼び出し
            api002OutData = additionalplaceorder(cc, dtoReq, ccsUserId);
            if (api002OutData != null) {
                // APIレスポンスチェック
                apiErrorUtil.checkApiResponse(api002OutData.getShubetu(), api002OutData.getCode(), api002OutData.getMessage());
                // APIエラー(応答あり)の場合
                if (apiErrorUtil.isFatal()) {
                    apiError = IfaOtherRemainPowerRestrainUtil.ApiResp.API_RESP_NG_ON.key;
                }
            }
        } catch (Exception apiEx) {
            // APIエラー(応答なし)の場合
            apiError = IfaOtherRemainPowerRestrainUtil.ApiResp.API_RESP_NG_OFF.key;
            this.updateIfaOtherRemainPowerRestrainCancelConfirmSql004(priKeys, apiError, null);
            return IfaCommonUtil.createDataList(
                    Collections.emptyList(),
                    ErrorLevel.FATAL,
                    IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED.key,
                    IfaCommonUtil.getMessage(IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED.key));
        }
        /* ↓↓↓7.発注後に注文内容をその他注文履歴テーブルへ記録する。↓↓↓ */
        if (!this.updateIfaOtherRemainPowerRestrainCancelConfirmSql004(priKeys, apiError, api002OutData)) {
            // DB登録NG：DB登録エラーを格納し次の処理へ
            apiErrorUtil.addDbError(IfaOtherRemainPowerRestrainUtil.Msg.WARNINGS_FRS_POSTORDEREXECUTION_COMPLETED.key, null);
        }
        /* ↓↓↓8.以下の表示を行う。↓↓↓ */
        // APIエラー：エラーを表示する(該当画面戻す)。
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(Collections.emptyList(),  IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED.key);
        }
        // A002 その他商品の注文・出来・取消返却項目設定
        cancelConfirmResponseColumnsDirects(dtoReq, responseDto, api002OutData);
        // API正常：その他余力拘束注文取消完了画面を表示する
        // レスポンスを返却する(DB登録OK)
        return apiErrorUtil.createDataList(Arrays.asList(responseDto), null);
    }

    /**
     * 共通処理
     * @param cc       顧客共通情報
     * @param response レスポンス
     * @throws Exception エラー
     */
    private void getResponseColumns(IfaOtherRemainPowerRestrainCancelConfirmA001RequestDto dtoReq, IfaOtherRemainPowerRestrainCancelConfirmA001ResponseDto responseDto,
            QueryAccountBalanceOutData api001OutData)
            throws Exception {
        // 画面項目編集
        if (ifaOrprUtil.checkNotNullObjects(api001OutData)) {
            // 入力項目設定
            editResponseColumnsDirects(dtoReq, responseDto, api001OutData);
        }
    }

    /**
     * API001呼び出し処理
     * @param dtoReq A001リクエストDTO
     * @return API出力結果
     */
    private QueryAccountBalanceOutData queryAccountBalance(CustomerCommon cc) throws Exception {

        QueryAccountBalanceIn input = new QueryAccountBalanceIn();
        QueryAccountBalanceInData inData = new QueryAccountBalanceInData();
        // 部店コード
        inData.setButenCd(cc.getButenCode());
        // 口座番号
        inData.setKozaNo(ifaOrprUtil.createApiRequestAccountNo(cc.getAccountNumber()));

        input.setIndata(inData);

        return apiWrapper.queryAccountBalance(input);
    }

    /**
     * API002 その他商品の注文・出来・取消呼び出し処理
     * @param dtoReq A002リクエストDTO
     * @return API出力結果
     */
    private AdditionalPlaceOrderOutData additionalplaceorder(CustomerCommon cc,
            IfaOtherRemainPowerRestrainCancelConfirmA002RequestDto dtoReq, String ccsUserId) throws Exception {

        AdditionalPlaceOrderIn input = new AdditionalPlaceOrderIn();
        AdditionalPlaceOrderInData inData = new AdditionalPlaceOrderInData();
        // トランザクションID
        inData.setTransactionId("");
        // 部店コード
        inData.setButenCd(cc.getButenCode());
        // 口座番号
        inData.setKozaNo(ifaOrprUtil.createApiRequestAccountNo(cc.getAccountNumber()));
        // 商品区分
        inData.setSecId(IfaOtherRemainPowerRestrainUtil.SecId.Z9.key);
        // 継投区分
        inData.setAccmulateId(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 2));
        // 会社コード
        inData.setCompanyCode(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 5));
        // 新旧区分
        inData.setNewOldId(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 回数
        inData.setSerNo(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 4));
        // 号
        inData.setSubCode(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 3));
        // 商品名
        inData.setBrandName(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 30));
        // 約定予定日
        inData.setTradeDate(DateUtil.dateFormat(dtoReq.getRestrainDateTo()));
        // 受渡予定日
        inData.setSettleDate(DateUtil.dateFormat(dtoReq.getRestrainDateTo()));
        // 売買区分
        inData.setBuySell(IfaOtherRemainPowerRestrainUtil.BuySellKbn.BUY.key);
        // 拘束種別が'1'(買付余力) または '6'(買付余力・NISA（成長投資枠）投資可能枠) または
        // '7'(買付余力・NISA（つみたて投資枠）投資可能枠)の場合、金額(買付余力)
        if (IfaOtherRemainPowerRestrainUtil.RestrainType.BUYING_POWER_TOTAL.key.equals(dtoReq.getRestrainType())
                || IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT_POWER_TOTAL.key.equals(dtoReq.getRestrainType())
                || IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT_POWER_TOTAL.key.equals(dtoReq.getRestrainType())) {
            inData.setNetAmount(StringUtil.fillLeft(dtoReq.getNetAmount(), '0', 15));
            // 上記以外、ALL0
        } else {
            inData.setNetAmount(StringUtil.fillLeft(StringUtil.EMPTY_STRING, '0', 15));
        }
        // 注文・出来区分
        inData.setOrderStatus(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 取消区分
        inData.setCxlStatus("1");
        // 受渡方法
        inData.setSettleCode(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 受注日時
        String acceptDateTime = dtoReq.getAcceptDateTime();
        // 受注日
        inData.setInputDate(acceptDateTime.substring(0, 8));
        // 受注時刻
        inData.setInputTime(acceptDateTime.substring(9, 15));
        // 数量
        inData.setQuantity(StringUtil.fillLeft(StringUtil.EMPTY_STRING, '0', 15));
        // 指成区分
        inData.setSasinariKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 単価
        inData.setPrice(StringUtil.fillLeft(StringUtil.EMPTY_STRING, '0', 1));
        // 市場名
        inData.setMarketName(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 譲渡益税税区分
        inData.setCgTaxId(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 通貨
        inData.setCcy(IfaOtherRemainPowerRestrainUtil.Ccy.JPY.key);
        // 為替レート
        inData.setExchangeRate("000100000");
        // クーポンレート
        inData.setCouponRate("000000000");
        // 受付経路区分
        inData.setCallcenterKbn(IfaOtherRemainPowerRestrainUtil.UketsukeKeiroKbn.CCS.key);
        // ユーザーＩＤ
        inData.setUserId(ccsUserId);
        // 余力チェック区分
        inData.setCheckId("1");
        // 拘束区分
        inData.setHoldKbn(dtoReq.getRestrainType());
        // 拘束金額(ISA買付可能枠)
        // 拘束種別が'4'(NISA（成長投資枠）投資可能枠) または 6'(買付余力・NISA（成長投資枠）投資可能枠)の場合、金額(NISA成長投資枠)
        if (IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT.key.equals(dtoReq.getRestrainType())
                || IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT_POWER_TOTAL.key.equals(dtoReq.getRestrainType())) {
            inData.setIsaBuyLimitHold(dtoReq.getIsaSeityoLimitAmount());
            // 拘束種別が'5'(NISA（つみたて投資枠）投資可能枠) または
            // '7'(買付余力・NISA（つみたて投資枠）投資可能枠)の場合、金額(NISAつみたて投資枠)
        } else if (IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT.key.equals(dtoReq.getRestrainType())
                || IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT_POWER_TOTAL.key.equals(dtoReq.getRestrainType())) {
            inData.setIsaBuyLimitHold(dtoReq.getIsaTsumitateLimitAmount());
            // 上記以外
        } else {
            inData.setIsaBuyLimitHold(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 10));
        }
        // 指定口座区分
        if (IfaOtherRemainPowerRestrainUtil.IsOpenedJrNisa.NO.key.equals(dtoReq.getJrNisageneralAccountFlag())) {
            inData.setAccountDesKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        } else {
            inData.setAccountDesKbn(dtoReq.getAccountType());
        }
        // API呼出し
        input.setIndata(inData);
        return apiWrapper.additionalPlaceOrder(input);
    }

    /**
     * A001入力項目設定
     * @param response 返却Dto
     * @param api001OutData API001処理結果
     * @param checkDto API001返却Dtoチェック結果
     */
    private void editResponseColumnsDirects(IfaOtherRemainPowerRestrainCancelConfirmA001RequestDto dtoReq,
            IfaOtherRemainPowerRestrainCancelConfirmA001ResponseDto response,
            QueryAccountBalanceOutData api001OutData) {
        // 選択口座
         response.setAccountType(dtoReq.getAccountType());
        // 拘束種別
        response.setRestrainType(dtoReq.getRestrainType());
        // 拘束金額（買付余力）
        response.setNetAmount(dtoReq.getNetAmount());
        // 拘束金額（NISA成長投資枠）
        response.setIsaSeityoLimitAmount(dtoReq.getIsaSeityoLimitAmount());
        // 拘束金額（NISAつみたて投資枠）
        response.setIsaTsumitateLimitAmount(dtoReq.getIsaTsumitateLimitAmount());
        // 拘束期限
        response.setRestrainDateTo(dtoReq.getRestrainDateTo());
        // 拘束理由
        response.setRestrainReason(dtoReq.getRestrainReason());
        // 受注日時
        response.setAcceptDateTime(dtoReq.getAcceptDateTime());
        // 注文後買付余力
        BigDecimal netAmountAfter = BigDecimal.ZERO;
        // リクエスト.金額(買付余力)
        BigDecimal netAmount = ifaOrprUtil.convert2BigDecimal(dtoReq.getNetAmount());
        // ジュニアNISA口座開設フラグが'1'(開設済)、かつ口座区分が'1'(ジュニアNISA口座)の場合
        if (IfaOtherRemainPowerRestrainUtil.IsOpenedJrNisa.YES.key.equals(dtoReq.getJrNisageneralAccountFlag()) && 
                IfaOtherRemainPowerRestrainUtil.AccountType.JUNIOR_NISA_ACCOUNT.key.equals(dtoReq.getAccountType())) {
            // API001.買付余力(JrNISA)
            BigDecimal buyingPowerTotalJrnisa = ifaOrprUtil.convert2BigDecimal(api001OutData.getT2Jr().getBuyingPowerTotalJrnisa());
            // API001.買付余力(JrNISA) + リクエスト.金額(買付余力)
            netAmountAfter = buyingPowerTotalJrnisa.add(netAmount);
            // 注文後買付余力
            response.setBuyingPowerTotalAfter(netAmountAfter.toString());
        } else {
            // API001.買付余力
            BigDecimal buyingPowerTotal = ifaOrprUtil.convert2BigDecimal(api001OutData.getT2().getBuyingPowerTotal());
            // API001.買付余力 + リクエスト.金額(買付余力)
            netAmountAfter = buyingPowerTotal.add(netAmount);
            // 注文後買付余力
            response.setBuyingPowerTotalAfter(netAmountAfter.toString());
        }
        // 注文後NISA成長投資枠
        BigDecimal isaSeityoLimitAmountAfter = BigDecimal.ZERO;
        // 拘束種別が'4'(NISA（成長投資枠）投資可能枠)、'6'(買付余力・NISA（成長投資枠）投資可能枠）のいずれかの場合
        if (IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT.key.equals(dtoReq.getRestrainType()) || 
                IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT_POWER_TOTAL.key.equals(dtoReq.getRestrainType())) {
            // 総合NISA(成長投資枠）買付可能枠(当年)
            BigDecimal isaSeityoBuyLimit = ifaOrprUtil.convert2BigDecimal(api001OutData.getIsaSeityoBuyLimit());
            // 金額（NISA成長投資枠）
            BigDecimal isaSeityoLimitAmount = ifaOrprUtil.convert2BigDecimal(dtoReq.getIsaSeityoLimitAmount().replaceAll("\\s", ""));
            // 総合NISA(成長投資枠）買付可能枠(当年) - 金額（NISA成長投資枠）
            isaSeityoLimitAmountAfter = isaSeityoBuyLimit.add(isaSeityoLimitAmount);
            // 注文後NISA成長投資枠
            response.setIsaSeityoLimitAmountAfter(isaSeityoLimitAmountAfter.toString());
        } else {
            // 注文後NISA成長投資枠(総合NISA(成長投資枠）買付可能枠(当年))
            response.setIsaSeityoLimitAmountAfter(api001OutData.getIsaSeityoBuyLimit());
        }
        // 注文後NISAつみたて投資枠
        BigDecimal isaTsumitateLimitAmountAfter = BigDecimal.ZERO;
        // 拘束種別が'5'(NISA（つみたて投資枠）投資可能枠)、'7'(買付余力・NISA（つみたて投資枠）投資可能枠）のいずれかの場合
        if (IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT.key.equals(dtoReq.getRestrainType()) ||
                IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT_POWER_TOTAL.key.equals(dtoReq.getRestrainType())) {
        	// 総合NISA(つみたて）買付可能枠(当年)
            BigDecimal isaTsumitateBuyLimit = ifaOrprUtil.convert2BigDecimal(api001OutData.getIsaTsumitateBuyLimit());
            // 金額（NISAつみたて投資枠）
            BigDecimal isaTsumitateLimitAmount = ifaOrprUtil.convert2BigDecimal(dtoReq.getIsaTsumitateLimitAmount().replaceAll("\\s", ""));
            // 総合NISA(つみたて）買付可能枠(当年) - 金額（NISAつみたて投資枠）
        	isaTsumitateLimitAmountAfter = isaTsumitateBuyLimit.add(isaTsumitateLimitAmount);
            // 注文後NISA成長投資枠
            response.setIsaTsumitateLimitAmountAfter(isaTsumitateLimitAmountAfter.toString());
        } else {
            // 注文後NISA成長投資枠(総合NISA(つみたて）買付可能枠(当年))
            response.setIsaTsumitateLimitAmountAfter(api001OutData.getIsaTsumitateBuyLimit());
        }
        // ジュニアNISA口座フラグ
        response.setJrNisageneralAccountFlag(dtoReq.getJrNisageneralAccountFlag());
        // EC受注番号
        response.setOrderNo(dtoReq.getOrderNo());
    }

    /**
     * A002返却項目設定
     * @param response 返却Dto
     * @param api002OutData API002処理結果
     * @param checkDto API002返却Dtoチェック結果
     */
    private void cancelConfirmResponseColumnsDirects(IfaOtherRemainPowerRestrainCancelConfirmA002RequestDto dtoReq,
            IfaOtherRemainPowerRestrainCancelConfirmA002ResponseDto responseDto,
            AdditionalPlaceOrderOutData api002OutData) {
        // 注文後の買付可能金額
        responseDto.setBuyingPowerTotalAfter(api002OutData.getAcBalanceAfter());
        // 受注日時
        responseDto.setAcceptDateTime(
                ifaOrprUtil.formatDateTime(api002OutData.getAcceptDate(), api002OutData.getAcceptTime()));
        // 選択口座
        responseDto.setAccountType(dtoReq.getAccountType());
        // 拘束種別
        responseDto.setRestrainType(dtoReq.getRestrainType());
        // 拘束金額（買付余力）
        responseDto.setNetAmount(dtoReq.getNetAmount());
        // 拘束金額（NISA成長投資枠）
        responseDto.setIsaSeityoLimitAmount(dtoReq.getIsaSeityoLimitAmount());
        // 拘束金額（NISAつみたて投資枠）
        responseDto.setIsaTsumitateLimitAmount(dtoReq.getIsaTsumitateLimitAmount());
        // 拘束期限
        responseDto.setRestrainDateTo(dtoReq.getRestrainDateTo());
        // 拘束理由
        responseDto.setRestrainReason(dtoReq.getRestrainReason());
        // ジュニアNISA口座開設フラグ
        responseDto.setJrNisageneralAccountFlag(dtoReq.getJrNisageneralAccountFlag());
        // EC受注番号
        responseDto.setOrderNo(dtoReq.getOrderNo());
        // 注文後NISA成長投資枠
        responseDto.setIsaSeityoLimitAmountAfter(dtoReq.getIsaSeityoLimitAmountAfter());
        // 注文後NISAつみたて投資枠
        responseDto.setIsaTsumitateLimitAmountAfter(dtoReq.getIsaTsumitateLimitAmountAfter());
    }

    /**
     * その他注文履歴情報取得
     * @param dtoReq IfaOtherRemainPowerRestrainCancelConfirmA002RequestDto
     * @return List sql001Res
     * @throws Exception
     */
    private IfaOtherRemainPowerRestrainCancelConfirmSql001ResponseModel selectIfaOtherRemainPowerRestrainCancelConfirmSql001(
            IfaOtherRemainPowerRestrainCancelConfirmA002RequestDto dtoReq) throws Exception {

        /* ↓↓↓sql001用リクエストモデル(挿入項目)をセットする↓↓↓ */
        IfaOtherRemainPowerRestrainCancelConfirmSql001RequestModel sql001Req =
                new IfaOtherRemainPowerRestrainCancelConfirmSql001RequestModel();
        // EC受注番号（その他商品）:    リクエスト.EC受注番号
        sql001Req.setEcJuchuuNo(dtoReq.getOrderNo());
        /* ↓↓↓DB処理を行う↓↓↓ */
        DataList<IfaOtherRemainPowerRestrainCancelConfirmSql001ResponseModel> sql001Res = null;
        // 取得できない時、nullを戻す
        try {
            sql001Res = dao.selectIfaOtherRemainPowerRestrainCancelConfirmSql001(sql001Req);
        } catch (Exception e) {
            return null;
        }
        if (CollectionUtils.isEmpty(sql001Res.getDataList())) {
            return null;
        }
        // 抽出項目を戻す
        return sql001Res.getDataList().get(0);
    }

    /**
     * その他注文履歴情報(取消発注)の件数取得
     * @param pk TablePrimaryKeys
     * @param dtoReq IfaOtherRemainPowerRestrainCancelConfirmA002RequestDto
     * @return エラーがあれば、false
     * @throws Exception
     */
    private boolean selectIfaOtherRemainPowerRestrainCancelConfirmSql002(TablePrimaryKeys pk,
            IfaOtherRemainPowerRestrainCancelConfirmA002RequestDto dtoReq) throws Exception {

        /* ↓↓↓sql002用リクエストモデル(挿入項目)をセットする↓↓↓ */
        IfaOtherRemainPowerRestrainCancelConfirmSql002RequestModel sql002Req =
                new IfaOtherRemainPowerRestrainCancelConfirmSql002RequestModel();
        // トランザクションID:    SQL001で取得したトランザクションIDの値
        sql002Req.setTranId(pk.getTranId());
        // ステータスコード:    1発注済
        sql002Req.setStsCd(IfaOtherRemainPowerRestrainUtil.StsCd.ORDERED.key);
        // 取消区分:    1取消済
        sql002Req.setTorikeshiKbn(IfaOtherRemainPowerRestrainUtil.TorikeshiKbn.CANCELED.key);

        /* ↓↓↓DB処理を行う↓↓↓ */
        // 戻り値の初期化
        boolean rtnBol = false;
        try {
            if (dao.selectIfaOtherRemainPowerRestrainCancelConfirmSql002(sql002Req) == 0) {
                rtnBol = true;
            }
        } catch (Exception e) {
            rtnBol = false;
        }
        return rtnBol;
    }
    
    /**
     * 発注前に注文内容をその他注文履歴テーブルへ記録する
     * @param pk TablePrimaryKeys
     * @param dtoReq IfaOtherRemainPowerRestrainCancelConfirmA002RequestDto
     * @throws Exception
     */
    private boolean insertIfaOtherRemainPowerRestrainCancelConfirmSql003(TablePrimaryKeys pk,
            IfaOtherRemainPowerRestrainCancelConfirmA002RequestDto dtoReq) throws Exception {

        /* ↓↓↓sql003用リクエストモデル(挿入項目)をセットする↓↓↓ */
        IfaOtherRemainPowerRestrainCancelConfirmSql003RequestModel sql003Req = 
                new IfaOtherRemainPowerRestrainCancelConfirmSql003RequestModel();
        // トランザクションＩＤ   サブクエリ①.トランザクションID
        sql003Req.setTranId(pk.getTranId());
        // 枝番   サブクエリ①.枝番+1
        sql003Req.setEdaban(pk.getEdaban());
        // 顧客ID:     DAO側実装 サブクエリ①.顧客ID
        sql003Req.setKokyakuId(StringUtil.EMPTY_STRING);
        // 部店CD:     DAO側実装 サブクエリ①.部店CD
        sql003Req.setButenCd(StringUtil.EMPTY_STRING);
        // 口座番号:     DAO側実装 サブクエリ①.口座番号
        sql003Req.setKouzaNo(StringUtil.EMPTY_STRING);
        // 発注区分:    3:その他余力拘束
        sql003Req.setHattyuuKbn(IfaOtherRemainPowerRestrainUtil.HattyuuKbn.OTHER.key);
        // 特定預り売買区分: ジュニアNISA口座開設フラグが'1'(開設済)　かつ　選択口座が'1'(ジュニアNISA口座)の場合、'1'：ジュニアNISA口座
        // 上記以外の場合、null：総合口座
        sql003Req.setTokuteiAzukariBaibaiKbn(IfaOtherRemainPowerRestrainUtil.IsOpenedJrNisa.YES.key
                .equals(dtoReq.getJrNisageneralAccountFlag())
                && IfaOtherRemainPowerRestrainUtil.AccountType.JUNIOR_NISA_ACCOUNT.key.equals(dtoReq.getAccountType())
                        ? IfaOtherRemainPowerRestrainUtil.TokuteiAzukariBaibaiKbn.JRNISA.key
                        : IfaOtherRemainPowerRestrainUtil.TokuteiAzukariBaibaiKbn.GENERAL.key);
        // 特定口座区分:    DAO側実装 サブクエリ①.特定口座区分
        sql003Req.setTokuteiKouzaKbn(StringUtil.EMPTY_STRING);
        // 受注日:    △ char8
        sql003Req.setJuchubi(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 8));
        // EC受注番号（その他商品）:    DAO側実装 サブクエリ①.EC受注番号
        sql003Req.setEcJuchuuNo(StringUtil.EMPTY_STRING);
        // 継投区分:    △
        sql003Req.setKeitouKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 会社コード:    null
        sql003Req.setKaishaCd(StringUtil.EMPTY_STRING);
        // 新旧区分:    △
        sql003Req.setShinkyuKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 回数:    null
        sql003Req.setKaisuu(StringUtil.EMPTY_STRING);
        // 号: △ char3
        sql003Req.setGou(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 3));
        // 商品名:    null
        sql003Req.setShouhinmei(StringUtil.EMPTY_STRING);
        // 商品区分:    Z9:その他
        sql003Req.setShouhinKbn(IfaOtherRemainPowerRestrainUtil.SecId.Z9.key);
        // ステータスコード:    1:発注済
        sql003Req.setStsCd(IfaOtherRemainPowerRestrainUtil.StsCd.ORDERED.key);
        // ユーザーＩＤ:    ユーザー共通情報.ユーザーＩＤ
        sql003Req.setUserId(IfaCommonUtil.getUserAccount().getUserId());
        // エラーコード:    null
        sql003Req.setErrorCd(StringUtil.EMPTY_STRING);
        // エラーメッセージ:    null
        sql003Req.setErrorMessage(StringUtil.EMPTY_STRING);
        // EC受注日時:    DAO側実装 サブクエリ①.受注日時
        sql003Req.setEcJuchuuNichiji(StringUtil.EMPTY_STRING);
        // 売買区分:    K：買い
        sql003Req.setBaibaiKbn(IfaOtherRemainPowerRestrainUtil.BuySellKbn.BUY.key);
        // 精算金額:    DAO側実装 サブクエリ①.拘束金額(買付余力)
        sql003Req.setSeisanKingaku(StringUtil.EMPTY_STRING);
        // 確定精算金額:    null
        sql003Req.setKakuteiSeisanKingaku(StringUtil.EMPTY_STRING);
        // 注文・出来区分:    △
        sql003Req.setChumonDekiKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 取消区分:    1：取消済
        sql003Req.setTorikeshiKbn(IfaOtherRemainPowerRestrainUtil.TorikeshiKbn.CANCELED.key);
        // 受渡方法:    △
        sql003Req.setUkewatashiHouho(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 支払い方法:    null
        sql003Req.setSiharaiHouho(StringUtil.EMPTY_STRING);
        // 数量:    0
        sql003Req.setSuryou("0");
        // 出来数量:    null
        sql003Req.setDekiSuryou(StringUtil.EMPTY_STRING);
        // 指成区分:    null
        sql003Req.setSashinariKbn(StringUtil.EMPTY_STRING);
        // 単価:    null
        sql003Req.setTanka(StringUtil.EMPTY_STRING);
        // 経過利子:    null
        sql003Req.setKeikaRisi(StringUtil.EMPTY_STRING);
        // 譲渡益税区分:    △
        sql003Req.setJoutoekizeiKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 通貨:    JPY
        sql003Req.setTsuuka(IfaOtherRemainPowerRestrainUtil.Ccy.JPY.key);
        // 為替レート:    1
        sql003Req.setKawaseRate("1");
        // 為替手数料:    null
        sql003Req.setKawaseTesuuryou(StringUtil.EMPTY_STRING);
        // クーポンレート:    null
        sql003Req.setCouponRate(StringUtil.EMPTY_STRING);
        // クーポン回数（年）:    null
        sql003Req.setCouponKasu(StringUtil.EMPTY_STRING);
        // 受付経路区分:    DAO側実装 サブクエリ①.受付経路区分
        sql003Req.setUketsukeKeiroKbn(StringUtil.EMPTY_STRING);
        // 余力チェック区分:    △
        sql003Req.setYoryokuCheckKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 種別:    null
        sql003Req.setShubetsu(StringUtil.EMPTY_STRING);
        // 買付可能金額:    null
        sql003Req.setKaitsukekanouKingaku(StringUtil.EMPTY_STRING);
        // 注文後の買付可能金額:    null
        sql003Req.setChumonKaitsukekanouKingaku(StringUtil.EMPTY_STRING);
        // 失効区分:    △
        sql003Req.setShikkouKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 市場:    null
        sql003Req.setShijou(StringUtil.EMPTY_STRING);
        // 処理フラグ:    1
        sql003Req.setShoriFlg("1");
        // 解約指定区分:    null
        sql003Req.setKaiyakuShitei_kbn(StringUtil.EMPTY_STRING);
        // 約定予定日:    DAO側実装 サブクエリ①.拘束期限
        sql003Req.setYakujouYoteibi(StringUtil.EMPTY_STRING);
        // 受渡予定日:    DAO側実装 サブクエリ①.拘束期限
        sql003Req.setUkewatashiYoteibi(StringUtil.EMPTY_STRING);
        // 入力受注日時:    DAO側実装 サブクエリ①.受注日時
        sql003Req.setNyuuryokuJuchuuNichiji(StringUtil.EMPTY_STRING);
        // 出来日時:    null
        sql003Req.setDekiHichiji(StringUtil.EMPTY_STRING);
        // 国内約定日:    null
        sql003Req.setKokunaiYakujoubi(StringUtil.EMPTY_STRING);
        // 国内受渡日:    null
        sql003Req.setKokunaiUkewatashibi(StringUtil.EMPTY_STRING);
        // 現地受渡日:    null
        sql003Req.setGenchiUkewatashibi(StringUtil.EMPTY_STRING);
        // 発注理由:    リクエスト.拘束理由
        sql003Req.setHattyuRiyu(dtoReq.getRestrainReason());
        // 印刷フラグ:    null
        sql003Req.setInsatsuFlg(StringUtil.EMPTY_STRING);
        // 登録日時:    DAO側実装 システム日時
        sql003Req.setTourokuNichiji(StringUtil.EMPTY_STRING);
        // 変更日時:    null
        sql003Req.setHenkouNichiji(StringUtil.EMPTY_STRING);
        // 削除日時:    null
        sql003Req.setSakujo_nichiji(StringUtil.EMPTY_STRING);
        // 削除フラグ:    0未削除
        sql003Req.setSakujoFlg(IfaOtherRemainPowerRestrainUtil.SakujoFlg.UNDELETE.key);
        // 勧誘区分:    null
        sql003Req.setKannyuuKbn(StringUtil.EMPTY_STRING);
        // 受注方法:    null
        sql003Req.setJyuuchuuHouhou(StringUtil.EMPTY_STRING);
        // 資金性格:    null
        sql003Req.setShikinSeikaku(StringUtil.EMPTY_STRING);
        // マル優口座区分:    DAO側実装 サブクエリ①.マル優適格者区分
        sql003Req.setMaruyuKouzaKbn(StringUtil.EMPTY_STRING);
        // 拘束区分:    DAO側実装 サブクエリ①.拘束区分
        sql003Req.setKousokuKbn(StringUtil.EMPTY_STRING);
        // 拘束金額（NISA）:    DAO側実装 サブクエリ①.拘束金額（NISA）
        sql003Req.setNisaKingaku(StringUtil.EMPTY_STRING);
        // 拘束種別:    DAO側実装 サブクエリ①.拘束種別
        sql003Req.setKousokuSyubetu(StringUtil.EMPTY_STRING);
        // CCS送付日:    null
        sql003Req.setCcsSendDate(StringUtil.EMPTY_STRING);

        /* ↓↓↓DB処理を行う↓↓↓ */
        // 戻り値の初期化
        boolean rtnBol = false;
        try {
            if (dao.insertIfaOtherRemainPowerRestrainCancelConfirmSql003(sql003Req) > 0) {
                rtnBol = true;
            }
        } catch (Exception e) {
            rtnBol = false;
        }
        return rtnBol;
    }

    /**
     * 発注後に注文内容をその他注文履歴テーブルへ記録する
     * @param TablePrimaryKeys インサート時の主キー
     * @param apiResp API発注状態
     * @param apiRes API002応答のレスポンス
     * @return エラーがあれば、false
     * @throws Exception
     */
    private boolean updateIfaOtherRemainPowerRestrainCancelConfirmSql004(
            TablePrimaryKeys priKeys, String apiResp, AdditionalPlaceOrderOutData apiOutData) throws Exception {

        /* ↓↓↓sql004用リクエストモデル(挿入項目)をセットする↓↓↓ */
        IfaOtherRemainPowerRestrainCancelConfirmSql004RequestModel sql004Req =
                new IfaOtherRemainPowerRestrainCancelConfirmSql004RequestModel();
        // トランザクションＩＤ
        sql004Req.setTranId(priKeys.getTranId());
        // 枝番
        sql004Req.setEdaban(priKeys.getEdaban());
        // APIエラーかどうかフラグ
        sql004Req.setApiError(apiResp);
        // API応答なし以外の場合
        if (!IfaOtherRemainPowerRestrainUtil.ApiResp.API_RESP_NG_OFF.key.equals(apiResp)) {
            // 受注日
            sql004Req.setJuchubi(apiOutData.getAcceptDate());
            // エラーコード
            sql004Req.setErrorCd(StringUtil.fillRight(apiOutData.getCode(), ' ', 6));
            // エラーメッセージ
            sql004Req.setErrorMessage(apiOutData.getMessage());
            // 種別
            sql004Req.setShubetsu(apiOutData.getShubetu());
            // API応答正常終了の場合
            if (IfaOtherRemainPowerRestrainUtil.ApiResp.API_RESP_OK.key.equals(apiResp)) {
                // EC受注番号（その他商品）
                sql004Req.setEcJuchuuNo(apiOutData.getOrderNum());
                // 約定予定日
                sql004Req.setYakujouYoteibi(apiOutData.getAcceptDate());
            }
        }
        /* ↓↓↓DB処理を行う↓↓↓ */
        // 戻り値の初期化
        boolean rtnBol = false;
        try {
            if (dao.updateIfaOtherRemainPowerRestrainCancelConfirmSql004(sql004Req) > 0) {
                rtnBol = true;
            }   
        } catch (Exception e) {
            rtnBol = false;
        }
        return rtnBol;
    }
    
}