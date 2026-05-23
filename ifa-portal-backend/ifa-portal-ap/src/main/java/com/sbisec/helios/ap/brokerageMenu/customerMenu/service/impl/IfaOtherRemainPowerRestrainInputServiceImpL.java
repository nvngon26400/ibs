package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaOtherRemainPowerRestrainInputDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputOrderConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputOrderConfirmA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputOrderData;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaOtherRemainPowerRestrainInputService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaOtherRemainPowerRestrainUtil;
import com.sbisec.helios.ap.common.dao.SystemDateDao;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.JrIsaContractType;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;

import jp.co.sbisec.pcenter.dto.yanap.EstimateAdditionalOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.EstimateAdditionalOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateAdditionalOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAdditionalOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryAdditionalOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAdditionalOrderOutData;

/**
 * 画面ID：SUB0202_0110-01_1
 * 画面名：その他余力拘束注文入力

 * @author 大連 えん
    2025/02/28 新規作成
 */
@Component(value = "cmpIfaOtherRemainPowerRestrainInputService")
public class IfaOtherRemainPowerRestrainInputServiceImpL implements IfaOtherRemainPowerRestrainInputService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaOtherRemainPowerRestrainInputServiceImpL.class);

    @Autowired
    private IfaDateUtil ifaDateUtil;

    @Autowired
    private SystemDateDao systemDateDao;

    @Autowired
    IfaOtherRemainPowerRestrainInputDao dao;

    /**
     * その他余力拘束の共通部品
     */
    @Autowired
    private IfaOtherRemainPowerRestrainUtil ifaOrprUtil;

    /**
     * API呼び出しクラス
     */
    @Autowired
    private ApiWrapper apiWrapper;

    /** 払出制限解除フラグ:払出制限中 */
    private static final String WITHDRAWAL_RESTRICTION_CANCEL_FLAG_TRUE = "1";

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaOtherRemainPowerRestrainInputA001RequestDto
     * Dto レスポンス：IfaOtherRemainPowerRestrainInputA001ResponseDto
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaOtherRemainPowerRestrainInputA001ResponseDto> initializeA001(
            IfaOtherRemainPowerRestrainInputA001RequestDto dtoReq) throws Exception {

        List<IfaOtherRemainPowerRestrainInputA001ResponseDto> resDto = new ArrayList<IfaOtherRemainPowerRestrainInputA001ResponseDto>();
        IfaOtherRemainPowerRestrainInputA001ResponseDto responseDto = new IfaOtherRemainPowerRestrainInputA001ResponseDto();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaOtherRemainPowerRestrainInputServiceImpL.initializeA001");
        }
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();

        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();

        //* ↓↓↓1.FCT001 利用者の口座に対する権限チェックを行う↓↓↓ */
        IfaOtherRemainPowerRestrainUtil.ErrorResponseDto errorDto = ifaOrprUtil.callFct001(cc);
        if (errorDto.isErrorFlg()) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorDto.getErrorMessageId(),
                    errorDto.getErrorMessage());
        }

        //* ↓↓↓2.ジュニアNISA口座開設判定↓↓↓ */
        // 顧客共通情報.ジュニアISA契約区分＝1かつ顧客共通情報.払出制限解除フラグ＝1（払出制限中）の場合
        // ジュニアNISA口座開設判定フラグ
        Boolean jrNisageneralAccountFlag = true;
        if (StringUtils.equals(cc.getJrIsaContractType(), JrIsaContractType.CONTRACT.getId()) && StringUtils
                .equals(cc.getWithdrawalRestrictionCancelFlag(), WITHDRAWAL_RESTRICTION_CANCEL_FLAG_TRUE)) {
            // ジュニアNISA口座フラグ:'1'(開設済)
            responseDto.setJrNisageneralAccountFlag(IfaOtherRemainPowerRestrainUtil.IsOpenedJrNisa.YES.key);
            // '1'(開設済)の場合、true
            jrNisageneralAccountFlag = true;
        } else {
            // ジュニアNISA口座フラグ:'0'(未開設)
            responseDto.setJrNisageneralAccountFlag(IfaOtherRemainPowerRestrainUtil.IsOpenedJrNisa.NO.key);
            // '0'(未開設)の場合、false
            jrNisageneralAccountFlag = false;
        }

        //* ↓↓↓3.買付余力情報を取得する↓↓↓ */
        // 買付余力照会API結果
        QueryAccountBalanceOutData api001OutData = new QueryAccountBalanceOutData();

        // API001買付余力照会呼び出し
        api001OutData = queryAccountBalance(cc);
        // 買付余力照会API処理結果確認
        apiErrorUtil.checkApiResponse(api001OutData.getShubetu(), api001OutData.getCode(), api001OutData.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }

        /* ↓↓↓4.APIからその他余力拘束一覧を取得する。↓↓↓ */
        // その他余力拘束状況リクエストAPI結果
        QueryAdditionalOrderOutData api002OutData = new QueryAdditionalOrderOutData();

        // API002その他余力拘束状況リクエスト呼び出し
        api002OutData = queryAdditionalOrder(cc, jrNisageneralAccountFlag);
        // その他余力拘束状況リクエストAPI処理結果確認
        apiErrorUtil.checkApiResponse(api002OutData.getShubetu(), api002OutData.getCode(), api002OutData.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        // APIを呼び出すその他注文履歴情報を取得する
        DataList<IfaOtherRemainPowerRestrainInputSql001ResponseModel> sql001Res = this.selectIfaOtherRemainPowerRestrainInputSql001(cc, api002OutData);
        Map<String, List<String>> inputSql001Map = getIfaOtherRemainPowerRestrainInputSql001(sql001Res);
        // 共通処理
        getResponseColumns(cc, responseDto, api001OutData, api002OutData, inputSql001Map);

        resDto.add(responseDto);

        return apiErrorUtil.createDataList(resDto, null);
    }

    /**
     * アクションID：A002
     * アクション名：注文確認
     * Dto リクエスト：IfaOtherRemainPowerRestrainInputOrderConfirmA002RequestDto
     * Dto レスポンス：IfaOtherRemainPowerRestrainInputOrderConfirmA002ResponseDto
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @throws exception 再検索処理で例外が発生した場合
     */
    public DataList<IfaOtherRemainPowerRestrainInputOrderConfirmA002ResponseDto> orderConfirmA002(
            IfaOtherRemainPowerRestrainInputOrderConfirmA002RequestDto dtoReq) throws Exception {
        List<IfaOtherRemainPowerRestrainInputOrderConfirmA002ResponseDto> resDto = new ArrayList<IfaOtherRemainPowerRestrainInputOrderConfirmA002ResponseDto>();
        IfaOtherRemainPowerRestrainInputOrderConfirmA002ResponseDto responseDto = new IfaOtherRemainPowerRestrainInputOrderConfirmA002ResponseDto();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaOtherRemainPowerRestrainInputServiceImpL.initializeA001");
        }
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 利用者の口座に対する権限チェックを行う
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();

        // FCT001 利用者の口座に対する権限チェックを行う
        IfaOtherRemainPowerRestrainUtil.ErrorResponseDto errorDto = ifaOrprUtil.callFct001(cc);
        if (errorDto.isErrorFlg()) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorDto.getErrorMessageId(),
                    errorDto.getErrorMessage());
        }
        // ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            // 未設定(Null または空文字）の場合：取引不可エラーを返す。
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_CMN_CCSID_UNREGISTERED.key,
                    IfaCommonUtil.getMessage(IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_CMN_CCSID_UNREGISTERED.key));
        }
        // ジュニアNISA口座開設フラグが'1'(開設済)の場合、口座区分の必須チェックを行う
        if (IfaOtherRemainPowerRestrainUtil.IsOpenedJrNisa.YES.key.equals(dtoReq.getJrNisageneralAccountFlag())) {
            if (dtoReq.getAccountType() == null || dtoReq.getAccountType() == "") {
                return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_REQUIRED.key,
                        IfaCommonUtil.getMessage(IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_REQUIRED.key, new String[] { "口座" }));
            }
        }
        // 拘束種別が'4'(NISA（成長投資枠）投資可能枠) または '5'(
        // NISA（つみたて投資枠）投資可能枠)以外の場合、金額（買付余力）の必須チェックを行う
        if (!(IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT.key.equals(dtoReq.getRestrainType())
                || IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT.key.equals(dtoReq.getRestrainType()))) {
            if (StringUtil.isNullOrEmpty(dtoReq.getNetAmount())) {
                return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL,
                        IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_REQUIRED.key,
                        IfaCommonUtil.getMessage(IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_REQUIRED.key,
                                new String[] { "拘束金額（買付余力）" }));
            }
        }
        // ジュニアNISA口座開設フラグが'0'(未開設) または (ジュニアNISA口座開設フラグが'1'(開設済)、かつ 口座が'△'(総合口座))の場合
        // 拘束種別が'4'(NISA（成長投資枠）投資可能枠) または
        // '6'(買付余力・NISA（成長投資枠）投資可能枠)の場合、金額（NISA成長投資枠）の必須チェックを行う
        if (IfaOtherRemainPowerRestrainUtil.IsOpenedJrNisa.NO.key.equals(dtoReq.getJrNisageneralAccountFlag())
                || (IfaOtherRemainPowerRestrainUtil.IsOpenedJrNisa.YES.key.equals(dtoReq.getJrNisageneralAccountFlag())
                        && IfaOtherRemainPowerRestrainUtil.AccountType.GENERAL_ACCOUNT.key.equals(dtoReq.getAccountType()))) {
            if (IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT.key.equals(dtoReq.getRestrainType())
                    || IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT_POWER_TOTAL.key
                            .equals(dtoReq.getRestrainType())) {
                if (StringUtil.isNullOrEmpty(dtoReq.getIsaSeityoLimitAmount())) {
                    return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL,
                            IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_REQUIRED.key,
                            IfaCommonUtil.getMessage(IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_REQUIRED.key,
                                    new String[] { "拘束金額（NISA成長投資枠）" }));
                }
            }
        }
        // ジュニアNISA口座開設フラグが'0'(未開設)　または (ジュニアNISA口座開設フラグが'1'(開設済)、かつ 口座が'△'(総合口座))の場合
        // 拘束種別が'5'(NISA（つみたて投資枠）投資可能枠) または
        // '7'(買付余力・NISA（つみたて投資枠）投資可能枠)の場合、金額（NISAつみたて投資枠）の必須チェックを行う
        if (IfaOtherRemainPowerRestrainUtil.IsOpenedJrNisa.NO.key.equals(dtoReq.getJrNisageneralAccountFlag())
                || (IfaOtherRemainPowerRestrainUtil.IsOpenedJrNisa.YES.key.equals(dtoReq.getJrNisageneralAccountFlag())
                        && IfaOtherRemainPowerRestrainUtil.AccountType.GENERAL_ACCOUNT.key.equals(dtoReq.getAccountType()))) {
            if (IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT.key.equals(dtoReq.getRestrainType())
                    || IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT_POWER_TOTAL.key
                            .equals(dtoReq.getRestrainType())) {
                if (StringUtil.isNullOrEmpty(dtoReq.getIsaTsumitateLimitAmount())) {
                    return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL,
                            IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_REQUIRED.key,
                            IfaCommonUtil.getMessage(IfaOtherRemainPowerRestrainUtil.Msg.ERRORS_REQUIRED.key,
                                    new String[] { "拘束金額（NISAつみたて投資枠）" }));
                }
            }
        }

        // API結果
        EstimateAdditionalOrderOutData api003OutData = new EstimateAdditionalOrderOutData();
        // ユーザ共通情報.CCSログイン用ID
        String ccsUserId = IfaCommonUtil.getUserAccount().getCcsUserId();
        // ユーザーＩＤは10桁以上の場合、10桁を設定する。
        if (!StringUtil.isNullOrEmpty(ccsUserId) && ccsUserId.length() > 10) {
            ccsUserId = ccsUserId.substring(0, 10);
        }
        // API003呼び出し
        api003OutData = estimateadditionalorder(cc, dtoReq, ccsUserId);
        // API処理結果確認
        apiErrorUtil.checkApiResponse(api003OutData.getShubetu(), api003OutData.getCode(), api003OutData.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }

        // A002返却項目設定
        confirmResponseColumnsDirects(dtoReq, responseDto, api003OutData);

        resDto.add(responseDto);

        return apiErrorUtil.createDataList(resDto, null);
    }

    /**
     * 共通処理
     * @param cc       顧客共通情報
     * @param response レスポンス
     * @throws Exception エラー
     */
    private void getResponseColumns(CustomerCommon cc, IfaOtherRemainPowerRestrainInputA001ResponseDto responseDto,
            QueryAccountBalanceOutData api001OutData, QueryAdditionalOrderOutData api002OutData,
            Map<String, List<String>> inputSql001Map) throws Exception {
        // 画面項目編集
        if (ifaOrprUtil.checkNotNullObjects(api001OutData)) {
            // 入力項目設定
            editResponseColumnsDirects(responseDto, api001OutData);
        }
        if (ifaOrprUtil.checkNotNullObjects(api002OutData)) {
            // 一覧項目の設定
            List<IfaOtherRemainPowerRestrainInputOrderData> orderDataList = new ArrayList<IfaOtherRemainPowerRestrainInputOrderData>();
            orderDataColumnsDirects(responseDto, orderDataList, api002OutData, inputSql001Map);
        }
    }

    /**
     * API002呼び出し処理
     * 
     * @param dtoReq A002リクエストDTO
     * @return API出力結果
     */
    private QueryAdditionalOrderOutData queryAdditionalOrder(CustomerCommon cc, Boolean jrNisageneralAccountFlag) throws Exception {
        
        QueryAdditionalOrderIn input = new QueryAdditionalOrderIn();
        QueryAdditionalOrderInData inData = new QueryAdditionalOrderInData();
        // 部店コード
        inData.setButenCd(cc.getButenCode());
        // 口座番号
        inData.setKozaNo(ifaOrprUtil.createApiRequestAccountNo(cc.getAccountNumber()));
        // 検索番号指定ＦＲＯＭ
        inData.setRefFrom("00001");
        // 検索番号指定ＴＯ
        inData.setRefTo("00100");
        // 取得口座区分
        // 顧客共通情報.ジュニアISA契約区分 = "1":契約 の場合、"2"：JrNISA口座(第一、第二口座両方)
        if (jrNisageneralAccountFlag) {
            inData.setAccountGetKbn("2");
        // 顧客共通情報.ジュニアISA契約区分 ≠ "1":契約 の場合、"△":通常口座およびJrNISA口座の第一口座
        } else {
            inData.setAccountGetKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        }
        
        input.setIndata(inData);
        
        return apiWrapper.queryAdditionalOrder(input);
    }

    /**
     * API001呼び出し処理
     * 
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
     * API003呼び出し処理
     * @param dtoReq A001リクエストDTO
     * @return API出力結果
     */
    private EstimateAdditionalOrderOutData estimateadditionalorder(CustomerCommon cc,
            IfaOtherRemainPowerRestrainInputOrderConfirmA002RequestDto dtoReq, String ccsUserId) throws Exception {

        EstimateAdditionalOrderIn input = new EstimateAdditionalOrderIn();
        EstimateAdditionalOrderInData inData = new EstimateAdditionalOrderInData();
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
        // 精算金額
        // 拘束種別が'1'(買付余力) または '6'(買付余力・NISA（成長投資枠）投資可能枠) または '7'(買付余力・NISA（つみたて投資枠）投資可能枠)の場合、金額(買付余力)
        if (IfaOtherRemainPowerRestrainUtil.RestrainType.BUYING_POWER_TOTAL.key.equals(dtoReq.getRestrainType()) || 
                IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT_POWER_TOTAL.key.equals(dtoReq.getRestrainType()) || 
                IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT_POWER_TOTAL.key.equals(dtoReq.getRestrainType())) {
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
        inData.setQuantity("000000000000000");
        // 指成区分
        inData.setSasinariKbn(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 単価
        inData.setPrice("0");
        // 市場名
        inData.setMarketName(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 譲渡益税税区分
        inData.setCgTaxId(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 1));
        // 通貨
        inData.setCcy(IfaOtherRemainPowerRestrainUtil.Ccy.JPY.key);
        // 為替レート
        inData.setExchangeRate("000000000");
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
        if (IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT.key.equals(dtoReq.getRestrainType()) || 
                IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT_POWER_TOTAL.key.equals(dtoReq.getRestrainType())) {
            inData.setIsaBuyLimitHold(dtoReq.getIsaSeityoLimitAmount());
        // 拘束種別が'5'(NISA（つみたて投資枠）投資可能枠) または '7'(買付余力・NISA（つみたて投資枠）投資可能枠)の場合、金額(NISAつみたて投資枠)
        } else if (IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT.key.equals(dtoReq.getRestrainType()) || 
                IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT_POWER_TOTAL.key.equals(dtoReq.getRestrainType())) {
            inData.setIsaBuyLimitHold(dtoReq.getIsaTsumitateLimitAmount());
        // 上記以外
        } else {
            inData.setIsaBuyLimitHold(StringUtil.fillLeft(StringUtil.EMPTY_STRING, ' ', 10));
        }
        // 指定口座区分
        inData.setAccountDesKbn(dtoReq.getAccountType());

        input.setIndata(inData);
        
        return apiWrapper.estimateadditionalorder(input);
    }

    /**
     * API002呼び出し内容をその他注文履歴テーブルへ記録する
     * @param apiRes API002応答のレスポンス
     * @return sql001Resp
     * @throws Exception
     */
    private DataList<IfaOtherRemainPowerRestrainInputSql001ResponseModel> selectIfaOtherRemainPowerRestrainInputSql001(
            CustomerCommon cc, QueryAdditionalOrderOutData api002OutData) throws Exception {

        /* ↓↓↓SQL001用リクエストモデル(挿入項目)をセットする↓↓↓ */
        IfaOtherRemainPowerRestrainInputSql001RequestModel sql001Req = 
                new IfaOtherRemainPowerRestrainInputSql001RequestModel();
        // 部店CD:顧客共通情報.部店コード
        sql001Req.setButenCd(cc.getButenCode());
        // 口座番号:顧客共通情報.口座番号
        sql001Req.setKouzaNo(cc.getAccountNumber());
        // ステータスコード:1発注済
        sql001Req.setStsCd(IfaOtherRemainPowerRestrainUtil.StsCd.ORDERED.key);
        // 削除フラグ:0未削除
        sql001Req.setSakujoFlg(IfaOtherRemainPowerRestrainUtil.SakujoFlg.UNDELETE.key);
        // EC受注番号（その他商品）
        List<String> orderNolist = api002OutData.getQueryAdditionalOrderData().stream()
                .map(obj -> obj.getOrderNo())
                .collect(Collectors.toList());
        sql001Req.setEcJuchuuNo(JsonConverter.getInstance().toString(orderNolist));

        /* ↓↓↓DB処理を行う↓↓↓ */
        DataList<IfaOtherRemainPowerRestrainInputSql001ResponseModel> sql001Resp = null;
        sql001Resp = dao.selectIfaOtherRemainPowerRestrainInputSql001(sql001Req);

        return sql001Resp;
    }

    /**
     * A001入力項目設定
     * @param response 返却Dto
     * @param api001OutData API001処理結果
     * @param checkDto API001返却Dtoチェック結果
     */
    private void editResponseColumnsDirects(IfaOtherRemainPowerRestrainInputA001ResponseDto response,
            QueryAccountBalanceOutData api001OutData) {
        // 買付余力
        response.setBuyingPowerTotal(api001OutData.getT2().getBuyingPowerTotal());
        // 買付余力(JrNISA)
        response.setBuyingPowerTotalJrnisa(api001OutData.getT2Jr().getBuyingPowerTotalJrnisa());
        // 総合NISA(成長投資枠）買付可能枠(当年)
        response.setIsaSeityoBuyLimit(api001OutData.getIsaSeityoBuyLimit());
        // 総合NISA(つみたて）買付可能枠(当年)
        response.setIsaTsumitateBuyLimit(api001OutData.getIsaTsumitateBuyLimit());
    }

    /**
     * A001一覧項目の設定
     * @param response      返却Dto
     * @param api002OutData API002処理結果
     * @param inputSql001Map sql001処理結果
     */
    private void orderDataColumnsDirects(IfaOtherRemainPowerRestrainInputA001ResponseDto response,
            List<IfaOtherRemainPowerRestrainInputOrderData> orderDataList, QueryAdditionalOrderOutData api002OutData,
            Map<String, List<String>> inputSql001Map) {

        IfaOtherRemainPowerRestrainInputOrderData orderData = null;
        if (api002OutData != null && api002OutData.getQueryAdditionalOrderData().size() > 0) {
            for (int i = 0; i < api002OutData.getQueryAdditionalOrderData().size(); i++) {
                orderData = new IfaOtherRemainPowerRestrainInputOrderData();
                /* ↓↓↓EC受注番号に対応するDBレコード取得↓↓↓ */
                // 拘束理由
                String restrainReason = StringUtil.EMPTY_STRING;
                // 取消区分
                String torikeshiKbn = StringUtil.EMPTY_STRING;
                // EC受注番号に対応するレコード取得
                List<String> inputSql001InfoList = inputSql001Map.get(api002OutData.getQueryAdditionalOrderData().get(i).getOrderNo());
                // レコード取得できた場合、設定
                if (inputSql001InfoList != null && CollectionUtils.isNotEmpty(inputSql001InfoList)) {
                    // 拘束理由
                    restrainReason = inputSql001InfoList.get(0);
                    // 取消区分
                    torikeshiKbn = inputSql001InfoList.get(1);
                // レコード取得できない場合、"-"を設定
                } else {
                    // 拘束理由
                    restrainReason = "-";
                    // 取消区分
                    torikeshiKbn = "-";
                    continue;
                }

                /* ↓↓↓一覧項目の設定↓↓↓ */
                // EC受注番号
                orderData.setOrderNo(api002OutData.getQueryAdditionalOrderData().get(i).getOrderNo());
                // 口座区分
                orderData.setAccountType(api002OutData.getQueryAdditionalOrderData().get(i).getKozaKbn());
                // 拘束区分
                orderData.setRestrainType(api002OutData.getQueryAdditionalOrderData().get(i).getHoldKbn());
                // 金額（買付余力）
                // 拘束種別が'1'(買付余力) または '6'(買付余力・NISA（成長投資枠）投資可能枠) または '7'(買付余力・NISA（つみたて投資枠）投資可能枠)の場合、設定:API002.精算金額
                if (IfaOtherRemainPowerRestrainUtil.RestrainType.BUYING_POWER_TOTAL.key.equals(api002OutData.getQueryAdditionalOrderData().get(i).getHoldKbn()) || 
                        IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT_POWER_TOTAL.key.equals(api002OutData.getQueryAdditionalOrderData().get(i).getHoldKbn()) || 
                        IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT_POWER_TOTAL.key.equals(api002OutData.getQueryAdditionalOrderData().get(i).getHoldKbn())) {
                    orderData.setNetAmount(api002OutData.getQueryAdditionalOrderData().get(i).getNetAmount());
                } else {
                    orderData.setNetAmount("0");
                }
                // 金額（NISA成長投資枠）
                // 拘束種別が'4'(NISA（成長投資枠）投資可能枠) または  '6'(買付余力・NISA（成長投資枠）投資可能枠)の場合、設定:API002.拘束金額(ISA買付可能枠)
                if (IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT.key.equals(api002OutData.getQueryAdditionalOrderData().get(i).getHoldKbn()) || 
                        IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_SEITYO_BUY_LIMIT_POWER_TOTAL.key.equals(api002OutData.getQueryAdditionalOrderData().get(i).getHoldKbn())) {
                    orderData.setIsaSeityoLimitAmount(api002OutData.getQueryAdditionalOrderData().get(i).getIsaBuyLimitHold());
                } else {
                    orderData.setIsaSeityoLimitAmount("0");
                }
                
                // 金額（NISAつみたて投資枠）
                // 拘束種別が'5'(NISA（つみたて投資枠）投資可能枠) または  '7'(買付余力・NISA（つみたて投資枠）投資可能枠)の場合、設定:API002.拘束金額(ISA買付可能枠)
                if (IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT.key.equals(api002OutData.getQueryAdditionalOrderData().get(i).getHoldKbn()) || 
                        IfaOtherRemainPowerRestrainUtil.RestrainType.ISA_TSUMITATE_BUY_LIMIT_POWER_TOTAL.key.equals(api002OutData.getQueryAdditionalOrderData().get(i).getHoldKbn())) {
                    orderData.setIsaTsumitateLimitAmount(api002OutData.getQueryAdditionalOrderData().get(i).getIsaBuyLimitHold());
                } else {
                    orderData.setIsaTsumitateLimitAmount("0");
                }
                // 拘束開始日
                orderData.setRestrainDateFrom(api002OutData.getQueryAdditionalOrderData().get(i).getInputDate());
                // 拘束期限
                orderData.setRestrainDateTo(api002OutData.getQueryAdditionalOrderData().get(i).getPreSettleDate());
                // 拘束理由(DB)
                orderData.setRestrainReason(restrainReason);
                // 受注日時 API002.受注日 + " " + API002.受注時刻
                orderData.setAcceptDateTime(api002OutData.getQueryAdditionalOrderData().get(i).getInputDate() + " " + api002OutData.getQueryAdditionalOrderData().get(i).getInputTime());
                // 発注日
                orderData.setOrderDate(api002OutData.getQueryAdditionalOrderData().get(i).getInputDate());
                // 商品区分
                orderData.setSecId(api002OutData.getQueryAdditionalOrderData().get(i).getSecId());
                // 受付経路区分
                orderData.setCallcenterId(api002OutData.getQueryAdditionalOrderData().get(i).getCallcenterId());
                // 有効区分
                orderData.setValidId(api002OutData.getQueryAdditionalOrderData().get(i).getValidId());
                // 取消区分
                orderData.setOrderId(api002OutData.getQueryAdditionalOrderData().get(i).getCxlOrderId());
                // 取消区分(DB)
                orderData.setTorikeshiKbn(torikeshiKbn);
                // リストにセット
                orderDataList.add(orderData);
            }
        }
        // 一覧項目にセット
        response.setOrderData(orderDataList);
    }

    /**
     * A002返却項目設定
     * @param response 返却Dto
     * @param api003OutData API003処理結果
     * @param checkDto API003返却Dtoチェック結果
     */
    private void confirmResponseColumnsDirects(IfaOtherRemainPowerRestrainInputOrderConfirmA002RequestDto dtoReq,
            IfaOtherRemainPowerRestrainInputOrderConfirmA002ResponseDto responseDto,
            EstimateAdditionalOrderOutData api003OutData) {
        // 注文後の買付可能金額
        responseDto.setBuyingPowerTotalAfter(api003OutData.getAcBalanceAfter());
        // 口座区分
        responseDto.setAccountType(dtoReq.getAccountType());
        // 拘束種別
        responseDto.setRestrainType(dtoReq.getRestrainType());
        // 金額（買付余力）
        responseDto.setNetAmount(dtoReq.getNetAmount());
        // 金額（NISA成長投資枠）
        responseDto.setIsaSeityoLimitAmount(dtoReq.getIsaSeityoLimitAmount());
        // 金額（NISAつみたて投資枠）
        responseDto.setIsaTsumitateLimitAmount(dtoReq.getIsaTsumitateLimitAmount());
        // 拘束期限
        responseDto.setRestrainDateTo(dtoReq.getRestrainDateTo());
        // 拘束理由
        responseDto.setRestrainReason(dtoReq.getRestrainReason());
        // 確認項目
        responseDto.setConfirmItem(dtoReq.getConfirmItem());
        // ジュニアNISA口座開設フラグ
        responseDto.setJrNisageneralAccountFlag(dtoReq.getJrNisageneralAccountFlag());

    }
    
    /**
     * その他注文履歴情報を取得する.
     * 
     * @return その他注文履歴情報
     */
    private Map<String, List<String>> getIfaOtherRemainPowerRestrainInputSql001(DataList<IfaOtherRemainPowerRestrainInputSql001ResponseModel> sql001Res) throws Exception {

        // その他注文履歴情報
        Map<String, List<String>> inputSql001InfoMap = new HashMap<>();

        if (sql001Res != null && CollectionUtils.isNotEmpty(sql001Res.getDataList())) {

            for (IfaOtherRemainPowerRestrainInputSql001ResponseModel sql001Info : sql001Res.getDataList()) {
                List<String> info = new ArrayList<>();
                // 拘束理由
                info.add(0, sql001Info.getHattyuRiyu());
                // 取消区分
                info.add(1, sql001Info.getTorikeshiKbn());
                // EC受注番号（その他商品）
                inputSql001InfoMap.put(sql001Info.getEcJuchuuNo(), info);
            }
        }
        return inputSql001InfoMap;
    }
}
