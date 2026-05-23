package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ibm.icu.math.BigDecimal;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmMarginTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.CreateMarginTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.dto.CurrencyCashBalance;
import com.sbisec.helios.ap.athena.protocol.account.dto.ForeignCashBalance;
import com.sbisec.helios.ap.athena.protocol.account.dto.ForeignScheduleCashBalance;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerHeadlineResp;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.athena.utils.RequestUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct004;
import com.sbisec.helios.ap.bizcommon.component.Fct018;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct018Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto.MediatePropriety;
import com.sbisec.helios.ap.bizcommon.model.OutputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct018Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignMarginDepositTransferConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginDepositTransferConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginDepositTransferConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferConfirmA001bResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.model.ApiStatusModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignMarginDepositTransferConfirmService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.AmericaStockUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0304-01_2
 * 画面名：米株信用保証金振替確認
 *
 * @author SCSK
 */
@Component(value = "cmpIfaForeignMarginDepositTransferConfirmService")
public class IfaForeignMarginDepositTransferConfirmServiceImpL
        implements IfaForeignMarginDepositTransferConfirmService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaForeignMarginDepositTransferConfirmServiceImpL.class);
    
    @Autowired
    private IfaForeignMarginDepositTransferConfirmDao dao;
    
    @Autowired
    private CodeListService codelistservice;
    
    @Autowired
    private Fct018 fct018;
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct004 fct004;
    
    @Autowired
    private ForeignAccountService foreignAccountService;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    /** US */
    private static final String COUNTRY_CODE_US = "US";
    
    /** NG */
    private static final String NG = "NG";
    
    /** 振替 */
    private static final String TRANSFER = "1";
    
    /** 権限なし */
    private static final String NO_AUTHORIZED = "0";
    
    /** 取引停止口座 */
    private static final String STOP_TRADE_ACCOUNT = "1";
    
    /** 外国株式.区分値 */
    private static final String FOREIGN_STOCKS = "15";
    
    /** 預り金 → 保証金 */
    private static final String FROM_DEPOSIT = "1";
    
    /** 保証金 → 預り金 */
    private static final String FROM_BOND = "0";
    
    /** 保証金振替（預り金⇒保証金） */
    private static final String TRANSFER_F_DEPOSIT = "7";
    
    /** 保証金振替（保証金⇒預り金） */
    private static final String TRANSFER_F_BOND = "8";
    
    /** 国コード 99 */
    private static final String COUNTRY_CODE_99 = "99";
    
    /** 通貨コード USD */
    private static final String CURRENCY_CODE_USD = "USD";
    
    /** 媒介可 */
    private static final String MEDIATE_POSSIBLE = "1";
    
    /** 対象取引（メッセージ表示用） */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 未開設 */
    private static final String NO_ESTABLISHMENT = "0";
    
    /** 半角スペース */
    private static final String HALFWIDTH_SPACE = " ";
    
    /** 指示金額 */
    private static final String INSTRUCTION_AMOUNT = "指示金額";
    
    /** 指示可能金額 */
    private static final String INSTRUCTION_POSSIBLE_AMOUNT = "指示可能金額";
    
    /** GENERAL 総合 */
    private static final String GENERAL = "GENERAL";
    
    /** T+0 */
    private static final int T_0 = 1;
    
    /** CASH_DEPOSIT */
    private static final String CASH_DEPOSIT = "CASH_DEPOSIT";
    
    /** HTTPのレスポンスコード */
    private static final String RESPONSE_CODE = "200";
    
    enum MessageId {
        
        // errors.frs.transferServiceHours.outOfRange
        ERRORS_HOURS_OUT_OF_RANGE("errors.frs.transferServiceHours.outOfRange"),
        // errors.butenAccountNotExist
        ERRORS_BUTEN_ACCOUNT_NOT_EXIST("errors.butenAccountNotExist"),
        // errors.cmn.selectedAccount.outOfService
        ERRORS_ACCOUNT_OUT_OF_RANGE("errors.cmn.selectedAccount.outOfService"),
        // errors.cmn.selectedAccountCourse.unavailable
        ERRORS_ACCOUNT_COURSE_UNAVAILABLE("errors.cmn.selectedAccountCourse.unavailable"),
        // errors.foreignStockAccountCheck
        ERRORS_ACCOUNT_CHECK("errors.foreignStockAccountCheck"),
        // errors.frs.foreignStockAccount.notOpen#1
        ERRORS_NOT_OPEN_1("errors.frs.foreignStockAccount.notOpen#1"),
        // errors.required
        ERRORS_REQUIRED("errors.required"),
        // errors.frs.maxValue.exceeded
        ERRORS_MAXVALUE_EXCEEDED("errors.frs.maxValue.exceeded"),
        // errors.frs.instructableAmount.nothing
        ERRORS_AMOUNT_NOTHING("errors.frs.instructableAmount.nothing"),
        // errors.frs.transferLimit.overflow
        ERRORS_TRANSFER_LIMIT_OVERFLOW("errors.frs.transferLimit.overflow"),
        // errors.frs.preTransferOrder.failed
        ERRORS_PRETRANSFER_ORDER_FAILED("errors.frs.preTransferOrder.failed"),
        // warnings.frs.postOrderTransfer.completed
        WARNINGS_POST_ORDER_TRANSFER_COMPLETED("warnings.frs.postOrderTransfer.completed"),
        //errors.cmn.orderExecution.failed
        ERRORS_CMN_ORDEREXECUTION_FAILED("errors.cmn.orderExecution.failed");
        
        private String key;
        
        private MessageId(String key) {
            
            this.key = key;
        }
    }
    
    /**
     * アクションID：A001a
     * アクション名：振替指示
     * Dto リクエスト：IfaForeignMarginDepositTransferConfirmA001aRequestDto
     * Dto レスポンス：IfaForeignMarginDepositTransferConfirmA001aResponseDto
     * model リクエスト：IfaForeignMarginDepositTransferConfirmSql001RequestModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaForeignMarginDepositTransferConfirmA001aResponseDto> transferInstructionA001a(
            IfaForeignMarginDepositTransferConfirmA001aRequestDto dtoReq) throws Exception {
        
        DataList<IfaForeignMarginDepositTransferConfirmA001aResponseDto> dtoRes = new DataList<IfaForeignMarginDepositTransferConfirmA001aResponseDto>();
        List<IfaForeignMarginDepositTransferConfirmA001aResponseDto> resDtoList = new ArrayList<IfaForeignMarginDepositTransferConfirmA001aResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginDepositTransferConfirmServiceImplL.transferInstructionA001a");
        }
        
        // ①振替サービス時間のチェックを行う。
        InputFct018Dto fct018Req = new InputFct018Dto();
        // 国コード（"US"）をセット
        fct018Req.setCountryCode(COUNTRY_CODE_US);
        // サービス時間チェック対象（外国）　（"1"（振替））をセット
        fct018Req.setForeignServiceHoursCheckTarget(TRANSFER);
        // 共通関数FCT018を呼び出す
        OutputFct018Dto fct018Res = new OutputFct018Dto();
        fct018Res = fct018.doCheck(fct018Req);
        // 処理結果 == "NG"の場合
        if (StringUtils.equals(fct018Res.getProcessResult(), NG)) {
            // レスポンスにエラー(errors.frs.transferServiceHours.outOfRange)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_HOURS_OUT_OF_RANGE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_HOURS_OUT_OF_RANGE.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        
        // ②利用者の口座に対する権限チェックを行う。
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        InputFct001Dto fct001Req = new InputFct001Dto();
        // 部店コード（顧客共通情報.部店コード）をセット
        fct001Req.setButenCode(cc.getButenCode());
        // 口座番号（顧客共通情報.口座番号）をセット
        fct001Req.setAccountNumber(cc.getAccountNumber());
        // 共通関数FCT001を呼び出す
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        // 対象顧客参照権限有無 == "0"（権限なし）の場合
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), NO_AUTHORIZED)) {
            // レスポンスにエラー(errors.butenAccountNotExist)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTEN_ACCOUNT_NOT_EXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTEN_ACCOUNT_NOT_EXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // if(取引停止フラグ＝"1"（取引停止口座）の場合){
        if (StringUtils.equals(fct001Res.getTradeSuspendFlag(), STOP_TRADE_ACCOUNT)) {
            // レスポンスにエラー(errors.cmn.selectedAccount.outOfService)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_ACCOUNT_OUT_OF_RANGE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_ACCOUNT_OUT_OF_RANGE.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        
        // ③口座の契約締結前交付書面コードのチェック　および　共同募集顧客(共募顧客)のチェックを行う
        InputFct003Dto fct003Req = new InputFct003Dto();
        // 顧客部店コード（顧客共通情報.部店コード）をセット
        fct003Req.setButenCode(cc.getButenCode());
        // 顧客口座番号（顧客共通情報.口座番号）をセット
        fct003Req.setAccountNumber(cc.getAccountNumber());
        // 証券金銭種別（"15"（外国株式.区分値））をセット
        fct003Req.setProductCd(FOREIGN_STOCKS);
        // リクエスト.委託保証金振替区分 == "1"（預り金　→　保証金）の場合
        if (StringUtils.equals(dtoReq.getEntrustDepositTransferClassification(), FROM_DEPOSIT)) {
            // 取引種別（"7"（保証金振替（預り金⇒保証金））をセット
            fct003Req.setTradeCd(TRANSFER_F_DEPOSIT);
            
            // リクエスト.委託保証金振替区分 == "0"（保証金　→　預り金）の場合
        } else if (StringUtils.equals(dtoReq.getEntrustDepositTransferClassification(), FROM_BOND)) {
            // 取引種別（"8"（保証金振替（保証金⇒預り金））をセット
            fct003Req.setTradeCd(TRANSFER_F_BOND);
        }
        // 国籍コード（"99"）をセット
        fct003Req.setCountryCd(COUNTRY_CODE_99);
        // 通貨コード（"USD"）をセット
        fct003Req.setCurrencyCode(CURRENCY_CODE_USD);
        // 共通関数FCT003を呼び出す
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        // レスポンス.媒介可否リスト.媒介可否が"1"（媒介可）の場合
        int num = 0;
        for (MediatePropriety loop : fct003Res.getMediateProprietyList()) {
            if (StringUtils.equals(loop.getMediatePropriety(), MEDIATE_POSSIBLE)) {
                // 存在した場合プラス
                num++;
            }
        }
        if (num == 0) {
            // レスポンスにエラー(errors.cmn.selectedAccountCourse.unavailable)を返す
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "5", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_ACCOUNT_COURSE_UNAVAILABLE.key, IfaCommonUtil
                            .getMessage(MessageId.ERRORS_ACCOUNT_COURSE_UNAVAILABLE.key, new String[] { codeName }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        
        // ④顧客共通情報.外国株式取引口座開設状況より、外貨建口座取引開設状況のチェックを行う。
        // if(顧客共通情報.外国株式取引口座開設状況==0（未開設）の場合){
        if (StringUtils.equals(cc.getForeignStockTradeAccountOpenStatus(), NO_ESTABLISHMENT)) {
            // レスポンスにエラー(errors.foreignStockAccountCheck)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_ACCOUNT_CHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_ACCOUNT_CHECK.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        
        // ⑤顧客共通情報.信用口座区分（外国）より、外国信用口座開設状況のチェックを行う。
        // if(顧客共通情報.信用口座区分（外国）="△"の場合){
        if (StringUtils.equals(cc.getForeignMarginAccountType(), HALFWIDTH_SPACE)) {
            // レスポンスにエラー(errors.frs.foreignStockAccount.notOpen#1)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_NOT_OPEN_1.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOT_OPEN_1.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        
        BigDecimal possibleTotal = BigDecimal.ZERO;
        ListForeignScheduleCashBalancesResp api003Res = new ListForeignScheduleCashBalancesResp();
        OutputFct004Dto fct004Res = new OutputFct004Dto();
        GetMarginPowerHeadlineResp api002Res = new GetMarginPowerHeadlineResp();
        // ⑥画面.振替口座を取得し、余力情報を算出する
        // リクエスト.委託保証金振替区分 == "1"（預り金　→　保証金）の場合
        if (StringUtils.equals(dtoReq.getEntrustDepositTransferClassification(), FROM_DEPOSIT)) {
            // ・COMETの余力を取得する
            ListForeignScheduleCashBalancesReq api003Req = new ListForeignScheduleCashBalancesReq();
            // Header.token（顧客共通情報.部店コード + "-" + 顧客共通情報.口座番号）をセット
            api003Req.getHeader().setToken(RequestUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
            // パラメータ.currencyCode（USD"：米ドル）をセット
            api003Req.getParameter().setCurrencyCode(CURRENCY_CODE_USD);
            // パラメータ.accountKind（"GENERAL"（総合））をセット
            api003Req.getParameter().setAccountKind(GENERAL);
            // パラメータ.days（"1"（T+0））をセット
            api003Req.getParameter().setDays(T_0);
            // API003を呼び出す
            try {
                api003Res = foreignAccountService.listForeignScheduleCashBalances(api003Req);
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
            
            // ・IFAポータル内の当日買付約定金額を取得する
            InputFct004Dto fct004Req = new InputFct004Dto();
            // 部店コード（顧客共通情報.部店コード）をセット
            fct004Req.setButenCode(cc.getButenCode());
            // 口座番号（顧客共通情報.口座番号）をセット
            fct004Req.setAccountNumber(cc.getAccountNumber());
            // 預り区分("GENERAL")をセット
            fct004Req.setDepositType(GENERAL);
            // 取引区分（"CASH_DEPOSIT"）をセット
            fct004Req.setTradeType(CASH_DEPOSIT);
            // 共通関数FCT004を呼び出す
            fct004Res = fct004.doCheck(fct004Req);
            // ・指示可能金額を算出する
            // 指示可能金額　＝　API003.外貨金銭残高スケジュールリスト.出金余力 + FCT004.計算後の余力金額
            if (api003Res.getForeignCashBalances().size() != 0) {
                for (ForeignCashBalance foreignCashBalance : api003Res.getForeignCashBalances()) {
                    for (CurrencyCashBalance currencyCashBalance : foreignCashBalance.getCurrencyCashBalances()) {
                        for (ForeignScheduleCashBalance foreignScheduleCashBalance : currencyCashBalance
                                .getForeignScheduleCashBalances()) {
                            if (foreignScheduleCashBalance.getPaymentPossibleAmount() != null) {
                                possibleTotal = possibleTotal
                                        .add(new BigDecimal(foreignScheduleCashBalance.getPaymentPossibleAmount()));
                            }
                        }
                    }
                }
            }
            possibleTotal = possibleTotal.add(new BigDecimal(fct004Res.getByingPowerMoneyAfterCalculate()));
            
            // リクエスト.委託保証金振替区分 == "0"（保証金　→　預り金）の場合
        } else if (StringUtils.equals(dtoReq.getEntrustDepositTransferClassification(), FROM_BOND)) {
            // ・COMETの余力を取得する
            // Header.token（顧客共通情報.部店コード + "-" + 顧客共通情報.口座番号）をセット
            // パラメータ.countryCode（"US"：米国）をセット
            // API002を呼び出す
            try {
                api002Res = foreignAccountService.getMarginPowerHeadline(cc.getButenCode(), cc.getAccountNumber(),
                        COUNTRY_CODE_US);
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
            // ・指示可能金額を算出する
            // 指示可能金額　＝　API002.出金指示可能額
            possibleTotal = new BigDecimal(api002Res.getMarginWithdrawable());
            
        }
        
        // ⑦入力項目「指示金額」をチェックする。
        // 指示可能金額 ＞ ０の場合
        if (possibleTotal.compareTo(BigDecimal.ZERO) > 0) {
            // リクエスト.指示金額 == ０の場合
            if (new BigDecimal(dtoReq.getDestinationAmount()).compareTo(BigDecimal.ZERO) == 0) {
                // レスポンスにエラー(errors.required)を返す
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_REQUIRED.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_REQUIRED.key, new String[] { INSTRUCTION_AMOUNT }));
                // レスポンスを呼出元に返却する
                return dtoRes;
                
                // リクエスト.指示金額 > ０の場合
            } else if (new BigDecimal(dtoReq.getDestinationAmount()).compareTo(BigDecimal.ZERO) > 0) {
                // if(リクエスト.指示金額 > 指示可能金額の場合){
                if (possibleTotal.compareTo(new BigDecimal(dtoReq.getDestinationAmount())) < 0) {
                    // レスポンスにエラー(errors.frs.maxValue.exceeded)を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_MAXVALUE_EXCEEDED.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_MAXVALUE_EXCEEDED.key,
                                    new String[] { INSTRUCTION_AMOUNT, INSTRUCTION_POSSIBLE_AMOUNT }));
                    // レスポンスを呼出元に返却する
                    return dtoRes;
                }
            }
            
            // 指示可能金額 <= ０の場合
        } else if (possibleTotal.compareTo(BigDecimal.ZERO) <= 0) {
            // レスポンスにエラー(errors.frs.instructableAmount.nothing)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_AMOUNT_NOTHING.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_AMOUNT_NOTHING.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        
        ConfirmMarginTransferResp api004Res = new ConfirmMarginTransferResp();
        // ⑧画面.振替口座が「預り金→保証金」への振替の場合、金銭残高チェックを行う。
        // if(リクエスト.委託保証金振替区分 == "1"（預り金　→　保証金）の場合){
        if (StringUtils.equals(dtoReq.getEntrustDepositTransferClassification(), FROM_DEPOSIT)) {
            // ・現在の余力情報.振替指示可能額を取得する。
            // Header.token（顧客共通情報.部店コード + "-" + 顧客共通情報.口座番号）をセット
            // パラメータ.transfer.currencyCode（"USD"：米ドル）をセット
            // パラメータ.transfer.chashTransferType（リクエスト.委託保証金振替区分の外部コード）をセット
            // パラメータ.transfer.amount（リクエスト.振替金額）をセット
            // API004を呼び出す
            try {
                api004Res = foreignAccountService.confirmMarginTransfer(cc.getButenCode(), cc.getAccountNumber(),
                        CURRENCY_CODE_USD, dtoReq.getEntrustDepositTransferClassification(), dtoReq.getDestinationAmount());
            } catch(Exception e) {
                LOGGER.info("IfaForeignMarginDepositTransferConfirmServiceImpL.transferInstructionA001a : API004 Exception occured.");      
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);

            }
            // if(リクエスト.振替金額 ＞ 現在の余力情報.振替指示可能額 ＋ FCT004で算出した「計算後の余力金額」){
            if (new BigDecimal(dtoReq.getDestinationAmount())
                    .compareTo(new BigDecimal(api004Res.getCurrentPower().getMarginWithdrawable())
                            .add(new BigDecimal(fct004Res.getByingPowerMoneyAfterCalculate()))) > 0) {
                // レスポンスにエラー(errors.frs.transferLimit.overflow)を返す
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_TRANSFER_LIMIT_OVERFLOW.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_TRANSFER_LIMIT_OVERFLOW.key,
                                new String[] { api004Res.getAmount(),
                                        api004Res.getCurrentPower().getMarginWithdrawable(),
                                        fct004Res.getOtcBuyingContractAmountToday().toString(),
                                        fct004Res.getContractAmountTodayWithinForeignBond().toString() }));
                // レスポンスを呼出元に返却する
                return dtoRes;
            }
        }
        
        IfaForeignMarginDepositTransferConfirmSql001RequestModel insSql001Req = new IfaForeignMarginDepositTransferConfirmSql001RequestModel();
        // ⑨振替指示前に指示内容をDBに登録する。
        // 部店コード（顧客共通情報.部店コード）をセット
        insSql001Req.setButenCode(cc.getButenCode());
        // 口座番号（顧客共通情報.口座番号）をセット
        insSql001Req.setAccountNumber(cc.getAccountNumber());
        // 通貨コード（API004.通貨コード）をセット
        if (StringUtils.equals(dtoReq.getEntrustDepositTransferClassification(), FROM_DEPOSIT)) {
            insSql001Req.setCurrencyCode(api004Res.getCurrencyCode());
        } else if (StringUtils.equals(dtoReq.getEntrustDepositTransferClassification(), FROM_BOND)) {
            insSql001Req.setCurrencyCode("USD");
        }
        // 委託保証金振替区分（リクエスト.委託保証金振替区分）をセット
        insSql001Req.setEntrustDepositTransferClassification(dtoReq.getEntrustDepositTransferClassification());
        // 指示金額（リクエスト.指示金額）をセット
        insSql001Req.setDestinationAmount(dtoReq.getDestinationAmount());
        // ユーザーID（ユーザ共通情報.ユーザーID）をセット
        UserAccount ua = IfaCommonUtil.getUserAccount();
        insSql001Req.setUserId(ua.getUserId());
        try {
            // SQL001を発行
            dao.insertIfaForeignMarginDepositTransferConfirmSql001(insSql001Req);
        } catch (Exception e) {
            // レスポンスにエラー(errors.frs.preTransferOrder.failed)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_PRETRANSFER_ORDER_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_PRETRANSFER_ORDER_FAILED.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        
        IfaForeignMarginDepositTransferConfirmA001aResponseDto resDto = new IfaForeignMarginDepositTransferConfirmA001aResponseDto();
        // 委託保証金振替区分
        resDto.setEntrustDepositTransferClassification(dtoReq.getEntrustDepositTransferClassification());
        // 指示金額
        resDto.setDestinationAmount(dtoReq.getDestinationAmount());
        // 通貨コード
        if (StringUtils.equals(dtoReq.getEntrustDepositTransferClassification(), FROM_DEPOSIT)) {
            resDto.setCurrencyCode(api004Res.getCurrencyCode());
        } else if (StringUtils.equals(dtoReq.getEntrustDepositTransferClassification(), FROM_BOND)) {
            resDto.setCurrencyCode("USD");
        }
        // IFA保証金振替指示番号
        resDto.setDepositTransferNo(insSql001Req.getDepositTransferNo());
        resDtoList.add(resDto);
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A001b
     * アクション名：振替指示
     * Dto リクエスト：IfaForeignMarginDepositTransferConfirmA001bRequestDto
     * Dto レスポンス：IfaForeignMarginDepositTransferConfirmA001bResponseDto
     * model リクエスト：IfaForeignMarginDepositTransferConfirmSql002RequestModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaForeignMarginDepositTransferConfirmA001bResponseDto> transferInstructionA001b(
            IfaForeignMarginDepositTransferConfirmA001bRequestDto dtoReq) throws Exception {
        
        DataList<IfaForeignMarginDepositTransferConfirmA001bResponseDto> dtoRes = new DataList<IfaForeignMarginDepositTransferConfirmA001bResponseDto>();
        List<IfaForeignMarginDepositTransferConfirmA001bResponseDto> resDtoList = new ArrayList<IfaForeignMarginDepositTransferConfirmA001bResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginDepositTransferConfirmServiceImplL.transferInstructionA001b");
        }
        
        // ⑩振替指示を行う。
        // Header.token（顧客共通情報.部店コード + "-" + 顧客共通情報.口座番号）をセット
        // Header.request_id（""）をセット
        // パラメータ.transfer.currencyCode（"USD"：米ドル）をセット
        // パラメータ.transfer.chashTransferType（リクエスト.委託保証金振替区分の外部コード）をセット
        // パラメータ.transfer.amount（リクエスト.指示金額）をセット
        // API001を呼び出す
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        CreateMarginTransferResp api001Res = new CreateMarginTransferResp();
        ApiStatusModel apiStatusModel = null;
        DataList<IfaForeignMarginDepositTransferConfirmA001bResponseDto> dtoResApiErr = null;
        try {
            api001Res = foreignAccountService.createMarginTransfer(cc.getButenCode(), cc.getAccountNumber(), "",
                    CURRENCY_CODE_USD, dtoReq.getEntrustDepositTransferClassification(), dtoReq.getDestinationAmount());
            apiStatusModel = null;
        } catch (AthenaBusinessException e) {
            // API業務異常時、APIのステータスを取得する
            apiStatusModel = AmericaStockUtil.getApiStatusInfo(e);
            api001Res = null;
            dtoResApiErr = cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, "", null), e);
        }
        
        IfaForeignMarginDepositTransferConfirmSql002RequestModel updSql002Req = new IfaForeignMarginDepositTransferConfirmSql002RequestModel();
        // ⑪振替指示後に指示結果をDBに反映する。
        // IFA保証金振替指示番号（リクエスト.IFA保証金振替指示番号）をセット
        updSql002Req.setDepositTransferNo(dtoReq.getDepositTransferNo());
        // ユーザーID（ユーザ共通情報.ユーザーID）をセット
        UserAccount ua = IfaCommonUtil.getUserAccount();
        updSql002Req.setUserId(ua.getUserId());
        // HTTPのレスポンスコードをセット
        updSql002Req.setResponseCode(RESPONSE_CODE);
        try {
            if (apiStatusModel == null) {
                // SQL002を発行
                dao.updateIfaForeignMarginDepositTransferConfirmSql002(updSql002Req);
            } else {
                // エラーコード
                updSql002Req.setCode(apiStatusModel.getApiErrorCode());
                // API.エラーメッセージ
                updSql002Req.setErrMessage(apiStatusModel.getApiMessage());
                // SQL002（エラー）を発行
                dao.updateIfaForeignMarginDepositTransferConfirmSql002(updSql002Req);
            }
        } catch (Exception e) {
            // レスポンスにワーニング(warnings.frs.postOrderTransfer.completed)を格納
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING,
                    MessageId.WARNINGS_POST_ORDER_TRANSFER_COMPLETED.key,
                    IfaCommonUtil.getMessage(MessageId.WARNINGS_POST_ORDER_TRANSFER_COMPLETED.key));
        }
        
        // ⑫以下の表示を行う。
        // APIエラー：エラーを表示する。
        if (dtoResApiErr != null) {
            // レスポンスを呼出元に返却する
            return dtoResApiErr;
        }
        // DB更新エラー：API正常の場合、米株信用保証金振替完了画面を表示し、DB更新ワーニングを表示する。
        // API正常：米株信用保証金振替完了画面を表示する。
        
        IfaForeignMarginDepositTransferConfirmA001bResponseDto resDto = new IfaForeignMarginDepositTransferConfirmA001bResponseDto();
        // 信用建余力_振替指示後
        resDto.setMarginPositionPowerAfter(api001Res.getAfterPower().getMarginBuyingPower());
        // 委託保証金率_振替指示後
        resDto.setMarginDepositRateAfter(api001Res.getAfterPower().getDepositRate());
        // 通貨コード
        resDto.setCurrencyCode(dtoReq.getCurrencyCode());
        resDtoList.add(resDto);
        
        if (dtoRes.getErrorLevel() != ErrorLevel.WARNING.getId()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        }
        
        return dtoRes;
    }
}
