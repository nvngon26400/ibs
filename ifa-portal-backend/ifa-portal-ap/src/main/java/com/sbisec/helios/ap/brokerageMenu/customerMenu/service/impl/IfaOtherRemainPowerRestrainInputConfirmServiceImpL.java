package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaOtherRemainPowerRestrainInputConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainInputConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainInputConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputConfirmA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaOtherRemainPowerRestrainInputConfirmService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaOtherRemainPowerRestrainUtil;
import com.sbisec.helios.ap.common.dao.SystemDateDao;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;

import jp.co.sbisec.pcenter.dto.yanap.AdditionalPlaceOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.AdditionalPlaceOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.AdditionalPlaceOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceOutData;
import lombok.Data;

/**
 * 画面ID：SUB0202_0110-01_2
 * 画面名：その他余力拘束注文確認

 * @author 大連 えん
    2025/02/28 新規作成
 */
@Component(value = "cmpIfaOtherRemainPowerRestrainInputConfirmService")
public class IfaOtherRemainPowerRestrainInputConfirmServiceImpL implements IfaOtherRemainPowerRestrainInputConfirmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaOtherRemainPowerRestrainInputConfirmServiceImpL.class);

    /**
     * API呼び出しクラス
     */
    @Autowired
    private ApiWrapper apiWrapper;

    /**
     * 日付用共通部品
     */
    @Autowired
    private IfaDateUtil ifaDateUtil;

    /**
     * 日付用共通部品
     */
    @Autowired
    private SystemDateDao systemDateDao;

    /**
     * その他余力拘束注文確認Dao
     */
    @Autowired
    private IfaOtherRemainPowerRestrainInputConfirmDao dao;

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
     * Dto リクエスト：IfaOtherRemainPowerRestrainInputConfirmA001RequestDto
     * Dto レスポンス：IfaOtherRemainPowerRestrainInputConfirmA001ResponseDto
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaOtherRemainPowerRestrainInputConfirmA001ResponseDto> initializeA001(IfaOtherRemainPowerRestrainInputConfirmA001RequestDto dtoReq)
            throws Exception {

        List<IfaOtherRemainPowerRestrainInputConfirmA001ResponseDto> resDto = new ArrayList<IfaOtherRemainPowerRestrainInputConfirmA001ResponseDto>();
        IfaOtherRemainPowerRestrainInputConfirmA001ResponseDto responseDto = new IfaOtherRemainPowerRestrainInputConfirmA001ResponseDto();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaOtherRemainPowerRestrainInputConfirmServiceImpL.initializeA001");
        }

        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();

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
     * アクション名：注文発注
     * Dto リクエスト：IfaOtherRemainPowerRestrainInputConfirmA002RequestDto
     * Dto レスポンス：IfaOtherRemainPowerRestrainInputConfirmA002ResponseDto
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @throws exception 再検索処理で例外が発生した場合
     */
    public DataList<IfaOtherRemainPowerRestrainInputConfirmA002ResponseDto> orderPlacementA002(
            IfaOtherRemainPowerRestrainInputConfirmA002RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaOtherRemainPowerRestrainInputConfirmServiceImpL.orderPlacementA002");
        }
        // 戻り値の初期化
        IfaOtherRemainPowerRestrainInputConfirmA002ResponseDto responseDto = new IfaOtherRemainPowerRestrainInputConfirmA002ResponseDto();
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客共通情報の取得
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

        /* ↓↓↓3.発注前に注文内容をその他注文履歴テーブルへ記録する。↓↓↓ */
        TablePrimaryKeys priKeys = this.insertIfaOtherRemainPowerRestrainInputConfirmSql001(dtoReq);
        // DB登録NG：DB登録エラーを返す
        if (priKeys == null) {
            return IfaCommonUtil.createDataList(
                    Collections.emptyList(),
                    ErrorLevel.FATAL,
                    IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_FRS_PREORDEREXECUTION_FAILED.key,
                    IfaCommonUtil.getMessage(IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_FRS_PREORDEREXECUTION_FAILED.key));
        }
        /* ↓↓↓4.余力拘束を行う。↓↓↓ */
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
            this.updateIfaOtherRemainPowerRestrainInputConfirmSql002(priKeys, apiError, null);
            return IfaCommonUtil.createDataList(
                    Collections.emptyList(),
                    ErrorLevel.FATAL,
                    IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_CMN_ORDEREXECUTION_FAILED.key,
                    IfaCommonUtil.getMessage(IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_CMN_ORDEREXECUTION_FAILED.key));
        }
        // A002返却項目設定
        confirmResponseColumnsDirects(dtoReq, responseDto, api002OutData);
        /* ↓↓↓5.発注後に注文内容をその他注文履歴テーブルへ記録する。↓↓↓ */
        if (!this.updateIfaOtherRemainPowerRestrainInputConfirmSql002(priKeys, apiError, api002OutData)) {
            // DB登録NG：DB登録エラーを格納し次の処理へ
            apiErrorUtil.addDbError(IfaOtherRemainPowerRestrainUtil.Msg.WARNINGS_FRS_POSTORDEREXECUTION_COMPLETED.key, null);
        }
        /* ↓↓↓6.以下の表示を行う。↓↓↓ */
        // APIエラー：エラーを表示する(該当画面戻す)。
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(Collections.emptyList(),  IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_CMN_ORDEREXECUTION_FAILED.key);
        }
        // API正常：その他余力拘束注文取消完了画面を表示する
        // レスポンスを返却する(DB登録OK)
        return apiErrorUtil.createDataList(Arrays.asList(responseDto), null);
    }

    /**
     * 共通処理
     * @param cc 顧客共通情報
     * @param response レスポンス
     * @throws Exception エラー
     */
    private void getResponseColumns(IfaOtherRemainPowerRestrainInputConfirmA001RequestDto dtoReq, IfaOtherRemainPowerRestrainInputConfirmA001ResponseDto responseDto,
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
            IfaOtherRemainPowerRestrainInputConfirmA002RequestDto dtoReq, String ccsUserId) throws Exception {

        AdditionalPlaceOrderIn input = new AdditionalPlaceOrderIn();
        AdditionalPlaceOrderInData inData = new AdditionalPlaceOrderInData();
        // トランザクションID
        inData.setTransactionId("0");
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
        // 拘束種別が'1'(買付余力) または  '6'(買付余力・NISA（成長投資枠）投資可能枠) または
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
        inData.setCxlStatus(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 受渡方法
        inData.setSettleCode(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 受注日
        String sysDate = ifaDateUtil.format(IfaDateUtil.YYYYMMDD);
        inData.setInputDate(sysDate);
        // 受注時刻
        String systemTime = DateUtil.format(systemDateDao.getSystemDate(), "HHmmss");
        inData.setInputTime(systemTime);
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
        inData.setCcy(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 為替レート
        inData.setExchangeRate("000100000");
        // クーポンレート
        inData.setCouponRate("000000000");
        // 受付経路区分
        inData.setCallcenterKbn(IfaOtherRemainPowerRestrainUtil.UketsukeKeiroKbn.CCS.key);
        // ユーザーＩＤ
        inData.setUserId(ccsUserId);
        // 余力チェック区分
        inData.setCheckId(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
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
        inData.setAccountDesKbn(dtoReq.getAccountType());

        input.setIndata(inData);

        return apiWrapper.additionalPlaceOrder(input);
    }


    /**
     * A001入力項目設定
     * @param response 返却Dto
     * @param api001OutData API001処理結果
     * @param checkDto API001返却Dtoチェック結果
     */
    private void editResponseColumnsDirects(IfaOtherRemainPowerRestrainInputConfirmA001RequestDto dtoReq,
            IfaOtherRemainPowerRestrainInputConfirmA001ResponseDto response, QueryAccountBalanceOutData api001OutData) {
        
        // 口座区分
         response.setAccountType(dtoReq.getAccountType());
        // 拘束種別
        response.setRestrainType(dtoReq.getRestrainType());
        // 金額（買付余力）
        response.setNetAmount(dtoReq.getNetAmount());
        // 金額（NISA成長投資枠）
        response.setIsaSeityoLimitAmount(dtoReq.getIsaSeityoLimitAmount());
        // 金額（NISAつみたて投資枠）
        response.setIsaTsumitateLimitAmount(dtoReq.getIsaTsumitateLimitAmount());
        // 拘束期限
        response.setRestrainDateTo(dtoReq.getRestrainDateTo());
        // 拘束理由
        response.setRestrainReason(dtoReq.getRestrainReason());
        // 注文後の買付可能金額
        response.setBuyingPowerTotalAfter(dtoReq.getBuyingPowerTotalAfter());
        // 注文後NISA成長投資枠
        BigDecimal isaSeityoLimitAmountAfter = BigDecimal.ZERO;
        // 総合NISA(成長投資枠）買付可能枠(当年)
        BigDecimal isaSeityoBuyLimit = ifaOrprUtil.convert2BigDecimal(api001OutData.getIsaSeityoBuyLimit());
        // 金額（NISA成長投資枠）
        BigDecimal isaSeityoLimitAmount = ifaOrprUtil.convert2BigDecimal(dtoReq.getIsaSeityoLimitAmount());
        // 拘束種別が'4'(NISA（成長投資枠）投資可能枠)、'6'(買付余力・NISA（成長投資枠）投資可能枠）のいずれかの場合
        if (IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT.key.equals(dtoReq.getRestrainType())
                || IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT_POWER_TOTAL.key.equals(dtoReq.getRestrainType())) {
            // 総合NISA(成長投資枠）買付可能枠(当年) - 金額（NISA成長投資枠）
            isaSeityoLimitAmountAfter = isaSeityoBuyLimit.subtract(isaSeityoLimitAmount);
            // 注文後NISA成長投資枠
            response.setIsaSeityoLimitAmountAfter(isaSeityoLimitAmountAfter.toString());
        } else {
            // 注文後NISA成長投資枠(総合NISA(成長投資枠）買付可能枠(当年))
            response.setIsaSeityoLimitAmountAfter(api001OutData.getIsaSeityoBuyLimit());
        }
        // 注文後NISAつみたて投資枠
        BigDecimal isaTsumitateLimitAmountAfter = BigDecimal.ZERO;
        // 総合NISA(つみたて）買付可能枠(当年)
        BigDecimal isaTsumitateBuyLimit = ifaOrprUtil.convert2BigDecimal(api001OutData.getIsaTsumitateBuyLimit());
        // 金額（NISAつみたて投資枠）
        BigDecimal isaTsumitateLimitAmount = ifaOrprUtil.convert2BigDecimal(dtoReq.getIsaTsumitateLimitAmount());
        // 拘束種別が'5'(NISA（つみたて投資枠）投資可能枠)、'7'(買付余力・NISA（つみたて投資枠）投資可能枠）のいずれかの場合
        if (IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT.key.equals(dtoReq.getRestrainType()) ||
                IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT_POWER_TOTAL.key.equals(dtoReq.getRestrainType())) {
            // 総合NISA(つみたて）買付可能枠(当年) - 金額（NISAつみたて投資枠）
        	isaTsumitateLimitAmountAfter = isaTsumitateBuyLimit.subtract(isaTsumitateLimitAmount);
            // 注文後NISA成長投資枠
            response.setIsaTsumitateLimitAmountAfter(isaTsumitateLimitAmountAfter.toString());
        } else {
            // 注文後NISA成長投資枠(総合NISA(つみたて）買付可能枠(当年))
            response.setIsaTsumitateLimitAmountAfter(api001OutData.getIsaTsumitateBuyLimit());
        }
        // ジュニアNISA口座フラグ
        response.setJrNisageneralAccountFlag(dtoReq.getJrNisageneralAccountFlag());
    }
    
    /**
     * A002返却項目設定
     * @param response 返却Dto
     * @param api002OutData API002処理結果
     * @param checkDto API002返却Dtoチェック結果
     */
    private void confirmResponseColumnsDirects(IfaOtherRemainPowerRestrainInputConfirmA002RequestDto dtoReq,
            IfaOtherRemainPowerRestrainInputConfirmA002ResponseDto responseDto,
            AdditionalPlaceOrderOutData api002OutData) {

        // EC受注番号
        responseDto.setOrderNo(api002OutData.getOrderNum());
        // 注文後の買付可能金額
        responseDto.setBuyingPowerTotalAfter(api002OutData.getAcBalanceAfter());
        // 受注日時
        responseDto.setAcceptDateTime(ifaOrprUtil.formatDateTime(api002OutData.getAcceptDate(), api002OutData.getAcceptTime()));
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
    }

    /**
     * 発注前に注文内容をその他注文履歴テーブルへ記録する
     * @param dtoReq IfaOtherRemainPowerRestrainInputConfirmA002RequestDto
     * @return TablePrimaryKeys IFAその他注文履歴テーブルの主キー格納クラス
     * @throws Exception
     */
    private TablePrimaryKeys insertIfaOtherRemainPowerRestrainInputConfirmSql001(
            IfaOtherRemainPowerRestrainInputConfirmA002RequestDto dtoReq) throws Exception {

        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();

        /* ↓↓↓SQL001用リクエストモデル(挿入項目)をセットする↓↓↓ */
        IfaOtherRemainPowerRestrainInputConfirmSql001RequestModel sql001Req = 
                new IfaOtherRemainPowerRestrainInputConfirmSql001RequestModel();
        // 枝番
        sql001Req.setEdaban(IfaOtherRemainPowerRestrainUtil.TbAdditionalOrderIfaPrimaryKey.EDABAN.key);
        // 顧客ID:    顧客共通情報.顧客コード
        sql001Req.setKokyakuId(cc.getCustomerCode());
        // 部店CD:    顧客共通情報.部店コード
        sql001Req.setButenCd(cc.getButenCode());
        // 口座番号:    顧客共通情報.口座番号
        sql001Req.setKouzaNo(cc.getAccountNumber());
        // 発注区分:    3その他余力拘束
        sql001Req.setHattyuuKbn(IfaOtherRemainPowerRestrainUtil.HattyuuKbn.OTHER.key);
        // 特定預り売買区分:
        //      ジュニアNISA口座開設フラグが'1'(開設済)かつ選択口座が'1'(ジュニアNISA口座)の場合 '1'：ジュニアNISA口座
        //      その他、null：総合口座
        sql001Req.setTokuteiAzukariBaibaiKbn(IfaOtherRemainPowerRestrainUtil.IsOpenedJrNisa.YES.key
                .equals(dtoReq.getJrNisageneralAccountFlag())
                && IfaOtherRemainPowerRestrainUtil.AccountType.JUNIOR_NISA_ACCOUNT.key.equals(dtoReq.getAccountType())
                        ? IfaOtherRemainPowerRestrainUtil.TokuteiAzukariBaibaiKbn.JRNISA.key
                        : IfaOtherRemainPowerRestrainUtil.TokuteiAzukariBaibaiKbn.GENERAL.key);
        // 特定口座区分:    顧客共通情報.特定口座区分
        sql001Req.setTokuteiKouzaKbn(cc.getSpecificAccountType());
        // 受注日:    △ char8
        sql001Req.setJuchubi(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 8));
        // EC受注番号（その他商品）:    △ char6
        sql001Req.setEcJuchuuNo(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 6));
        // 継投区分:    △ char2
        sql001Req.setKeitouKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 2));
        // 会社コード:    null
        sql001Req.setKaishaCd(StringUtil.EMPTY_STRING);
        // 新旧区分:    △ char1
        sql001Req.setShinkyuKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 回数:    null
        sql001Req.setKaisuu(StringUtil.EMPTY_STRING);
        // 号: △ char3
        sql001Req.setGou(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 3));
        // 商品名:    null
        sql001Req.setShouhinmei(StringUtil.EMPTY_STRING);
        // 商品区分:    Z9その他
        sql001Req.setShouhinKbn(IfaOtherRemainPowerRestrainUtil.SecId.Z9.key);
        // ステータスコード:    0未発注
        sql001Req.setStsCd(IfaOtherRemainPowerRestrainUtil.StsCd.UNORDER.key);
        // ユーザーＩＤ:    ユーザー共通情報.ユーザーＩＤ
        sql001Req.setUserId(IfaCommonUtil.getUserAccount().getUserId());
        // エラーコード:    null
        sql001Req.setErrorCd(StringUtil.EMPTY_STRING);
        // エラーメッセージ:    null
        sql001Req.setErrorMessage(StringUtil.EMPTY_STRING);
        // EC受注日時:    △
        sql001Req.setEcJuchuuNichiji(StringUtil.EMPTY_STRING);
        // 売買区分:    K買い
        sql001Req.setBaibaiKbn(IfaOtherRemainPowerRestrainUtil.BuySellKbn.BUY.key);
        // 精算金額:    リクエスト.拘束金額(買付余力)
        sql001Req.setSeisanKingaku(dtoReq.getNetAmount());
        // 確定精算金額:    null
        sql001Req.setKakuteiSeisanKingaku(StringUtil.EMPTY_STRING);
        // 注文・出来区分:    △
        sql001Req.setChumonDekiKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 取消区分:    △
        sql001Req.setTorikeshiKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 受渡方法:    △
        sql001Req.setUkewatashiHouho(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 支払い方法:    null
        sql001Req.setSiharaiHouho(StringUtil.EMPTY_STRING);
        // 数量:    0
        sql001Req.setSuryou("0");
        // 出来数量:    null
        sql001Req.setDekiSuryou(StringUtil.EMPTY_STRING);
        // 指成区分:    null
        sql001Req.setSashinariKbn(StringUtil.EMPTY_STRING);
        // 単価:    null
        sql001Req.setTanka(StringUtil.EMPTY_STRING);
        // 経過利子:    null
        sql001Req.setKeikaRisi(StringUtil.EMPTY_STRING);
        // 譲渡益税区分:    △
        sql001Req.setJoutoekizeiKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 通貨:    JPY
        sql001Req.setTsuuka(IfaOtherRemainPowerRestrainUtil.Ccy.JPY.key);
        // 為替レート:    1
        sql001Req.setKawaseRate("1");
        // 為替手数料:    null
        sql001Req.setKawaseTesuuryou(StringUtil.EMPTY_STRING);
        // クーポンレート:    null
        sql001Req.setCouponRate(StringUtil.EMPTY_STRING);
        // クーポン回数（年）:    null
        sql001Req.setCouponKasu(StringUtil.EMPTY_STRING);
        // 受付経路区分:    1
        sql001Req.setUketsukeKeiroKbn(IfaOtherRemainPowerRestrainUtil.UketsukeKeiroKbn.CCS.key);
        // 余力チェック区分:    △
        sql001Req.setYoryokuCheckKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 種別:    null
        sql001Req.setShubetsu(StringUtil.EMPTY_STRING);
        // 買付可能金額:    null
        sql001Req.setKaitsukekanouKingaku(StringUtil.EMPTY_STRING);
        // 注文後の買付可能金額:    null
        sql001Req.setChumonKaitsukekanouKingaku(StringUtil.EMPTY_STRING);
        // 失効区分:    △
        sql001Req.setShikkouKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 市場:    null
        sql001Req.setShijou(StringUtil.EMPTY_STRING);
        // 処理フラグ:    1
        sql001Req.setShoriFlg("1");
        // 解約指定区分:    null
        sql001Req.setKaiyakuShitei_kbn(StringUtil.EMPTY_STRING);
        // 約定予定日:    リクエスト.拘束期限
        sql001Req.setYakujouYoteibi(dtoReq.getRestrainDateTo());
        // 受渡予定日:    リクエスト.拘束期限
        sql001Req.setUkewatashiYoteibi(dtoReq.getRestrainDateTo());
        // 出来日時:    null
        sql001Req.setDekiHichiji(StringUtil.EMPTY_STRING);
        // 国内約定日:    null
        sql001Req.setKokunaiYakujoubi(StringUtil.EMPTY_STRING);
        // 国内受渡日:    null
        sql001Req.setKokunaiUkewatashibi(StringUtil.EMPTY_STRING);
        // 現地受渡日:    null
        sql001Req.setGenchiUkewatashibi(StringUtil.EMPTY_STRING);
        // 発注理由:    リクエスト.拘束理由
        sql001Req.setHattyuRiyu(dtoReq.getRestrainReason());
        // 印刷フラグ:    null
        sql001Req.setInsatsuFlg(StringUtil.EMPTY_STRING);
        // 変更日時:    null
        sql001Req.setHenkouNichiji(StringUtil.EMPTY_STRING);
        // 削除日時:    null
        sql001Req.setSakujo_nichiji(StringUtil.EMPTY_STRING);
        // 削除フラグ:    0未削除
        sql001Req.setSakujoFlg(IfaOtherRemainPowerRestrainUtil.SakujoFlg.UNDELETE.key);
        // 勧誘区分:    null
        sql001Req.setKannyuuKbn(StringUtil.EMPTY_STRING);
        // 受注方法:    null
        sql001Req.setJyuuchuuHouhou(StringUtil.EMPTY_STRING);
        // 資金性格:    null
        sql001Req.setShikinSeikaku(StringUtil.EMPTY_STRING);
        // マル優口座区分:
        //      顧客共通情報.マル優適格者区分が'△'またはnullの場合、△マル優口座以外
        //      以外の場合、1マル優口座
        sql001Req.setMaruyuKouzaKbn(
                StringUtil.isNullOrEmpty(cc.getTaxExemptQualifiedPersonType()) 
                ? IfaOtherRemainPowerRestrainUtil.MaruyuKouzaKbn.NO.key
                : IfaOtherRemainPowerRestrainUtil.MaruyuKouzaKbn.YES.key);
        // 拘束区分:    △
        sql001Req.setKousokuKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 拘束金額（NISA）:
        String val = StringUtil.EMPTY_STRING;
        // 拘束種別が'4'(NISA（成長投資枠）投資可能枠) または '6'(買付余力・NISA（成長投資枠）投資可能枠)の場合
        if (IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT.key.equals(dtoReq.getRestrainType())
                || IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT_POWER_TOTAL.key.equals(dtoReq.getRestrainType())) {
            // 値は 拘束金額(NISA成長投資枠)
            val = dtoReq.getIsaSeityoLimitAmount();
        // 拘束種別が'5'(NISA（つみたて投資枠）投資可能枠) または '7'(買付余力・NISA（つみたて投資枠）投資可能枠)の場合
        } else if (IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT.key.equals(dtoReq.getRestrainType())
                || IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT_POWER_TOTAL.key.equals(dtoReq.getRestrainType())) {
            // 値は 拘束金額(NISAつみたて投資枠)
            val = dtoReq.getIsaTsumitateLimitAmount();
         // その他
        } else {
            val = "0";
        }
        sql001Req.setNisaKingaku(val);
        // 拘束種別:    リクエスト.拘束種別
        sql001Req.setKousokuSyubetu(dtoReq.getRestrainType());
        // CCS送付日:    null
        sql001Req.setCcsSendDate(StringUtil.EMPTY_STRING);

        /* ↓↓↓DB処理を行う↓↓↓ */
        // 戻り値の初期化
        TablePrimaryKeys priKeys = null;
        try {
            if (dao.insertIfaOtherRemainPowerRestrainInputConfirmSql001(sql001Req) > 0) {
                priKeys = new TablePrimaryKeys();
                priKeys.setTranId(sql001Req.getTranId());
                priKeys.setEdaban(sql001Req.getEdaban());
            }
        } catch (Exception e) {
            priKeys = null;
        }
        return priKeys;
    }

    /**
     * 発注後に注文内容をその他注文履歴テーブルへ記録する
     * @param TablePrimaryKeys インサート時の主キー
     * @param apiResp API発注状態
     * @param apiRes API002応答のレスポンス
     * @return エラーがあれば、false
     * @throws Exception
     */
    private boolean updateIfaOtherRemainPowerRestrainInputConfirmSql002(
            TablePrimaryKeys priKeys, String apiResp, AdditionalPlaceOrderOutData api002OutData) throws Exception {

        /* ↓↓↓SQL002用リクエストモデル(挿入項目)をセットする↓↓↓ */
        IfaOtherRemainPowerRestrainInputConfirmSql002RequestModel sql002Req = 
                new IfaOtherRemainPowerRestrainInputConfirmSql002RequestModel();
        // トランザクションＩＤ
        sql002Req.setTranId(priKeys.getTranId());
        // 枝番
        sql002Req.setEdaban(priKeys.getEdaban());
        // APIエラーかどうかフラグ
        sql002Req.setApiError(apiResp);
        // API応答なし以外の場合
        if (!IfaOtherRemainPowerRestrainUtil.ApiResp.API_RESP_NG_OFF.key.equals(apiResp)) {
            // 受注日
            sql002Req.setJuchubi(DateUtil.dateFormat(api002OutData.getAcceptDate(), DateUtil.YYYYMMDD, DateUtil.YYYYMMDD));
            // エラーコード
            sql002Req.setErrorCd(StringUtil.fillRight(api002OutData.getCode(), ' ', 6));
            // エラーメッセージ
            sql002Req.setErrorMessage(api002OutData.getMessage());
            // 種別
            sql002Req.setShubetsu(api002OutData.getShubetu());
            // API応答正常終了の場合
            if (IfaOtherRemainPowerRestrainUtil.ApiResp.API_RESP_OK.key.equals(apiResp)) {
                // EC受注番号（その他商品）
                sql002Req.setEcJuchuuNo(api002OutData.getOrderNum());
                // EC受注日時
                sql002Req.setEcJuchuuNichiji(
                        ifaOrprUtil.formatDateTime(api002OutData.getAcceptDate(), api002OutData.getAcceptTime()));
                // ステータスコード
                sql002Req.setStsCd(IfaOtherRemainPowerRestrainUtil.StsCd.ORDERED.key);
                // 買付可能金額
                sql002Req.setKaitsukekanouKingaku(api002OutData.getAcBalance());
                // 注文後の買付可能金額
                sql002Req.setChumonKaitsukekanouKingaku(api002OutData.getAcBalanceAfter());
                // 入力受注日時
                sql002Req.setNyuuryokuJuchuuNichiji(
                        ifaOrprUtil.formatDateTime(api002OutData.getAcceptDate(), api002OutData.getAcceptTime()));
            }
        }
        /* ↓↓↓DB処理を行う↓↓↓ */
        // 戻り値の初期化
        boolean rtnBol = false;
        try {
            if (dao.updateIfaOtherRemainPowerRestrainInputConfirmSql002(sql002Req) > 0) {
                rtnBol = true;
            }   
        } catch (Exception e) {
            rtnBol = false;
        }
        return rtnBol;
    }
}
