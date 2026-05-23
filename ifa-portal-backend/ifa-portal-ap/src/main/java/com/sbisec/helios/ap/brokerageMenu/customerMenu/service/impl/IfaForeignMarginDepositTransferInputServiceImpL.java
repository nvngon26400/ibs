package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmMarginTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerHeadlineResp;
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
import com.sbisec.helios.ap.bizcommon.model.OutputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct018Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignMarginDepositTransferInputService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID：SUB0202_0304-01_1
 * 画面名：米株信用保証金振替入力
 *
 * @author SCSK
 *     2023/12/04 新規作成
 */
@Component(value = "cmpIfaForeignMarginDepositTransferInputService")
public class IfaForeignMarginDepositTransferInputServiceImpL implements IfaForeignMarginDepositTransferInputService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignMarginDepositTransferInputServiceImpL.class);
    
    /** 共通関数FCT001 */
    @Autowired
    private Fct001 fct001;
    
    /** 共通関数FCT003 */
    @Autowired
    private Fct003 fct003;
    
    /** 共通関数FCT004 */
    @Autowired
    private Fct004 fct004;
    
    /** 共通関数FCT018 */
    @Autowired
    private Fct018 fct018;
    
    /** 区分定義公開機能サービス */
    @Autowired
    private CodeListService codeListService;
    
    /** 口座情報 Service */
    @Autowired
    ForeignAccountService foreignAccountService;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    /** 権限チェックエラー  */
    private static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 権限チェックエラー値 */
    private static final String AUTH_ERROR_VALUE = "0";
    
    /** 取引停止口座エラー */
    private static final String ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** 取引停止口座エラー値 */
    private static final String SELECTEDACCOUNT_OUTOFSERVICE_ERROR_VALUE = "1";
    
    /** リクエスト.口座選択 「預り金　→　保証金」選択判定値*/
    private static final String REQUEST_ACCOUNT_SELECT_TO_SECURITY = "1";
    
    /** リクエスト.口座選択 「保証金　→　預り金」選択判定値*/
    private static final String REQUEST_ACCOUNT_SELECT_TO_DEPOSIT = "0";
    
    /** FCT003.媒介可否 判定値*/
    private static final String FCT003_MEDIATE_PROPRIETY_VALUE = "1";
    
    /** FCT003.媒介可否リスト.取引種別 「預り金　→　保証金」選択判定値*/
    private static final String FCT003_ACCOUNT_SELECT_TO_SECURITY = "7";
    
    /** FCT003.媒介可否リスト.取引種別 「保証金　→　預り金」選択判定値*/
    private static final String FCT003_CCOUNT_SELECT_TO_DEPOSIT = "8";
    
    /** FCT003.媒介可否リスト.通貨コード "USD"選択判定値*/
    private static final String FCT003_CURRENCY_CODE_USD = "USD";
    
    /** 取引不可エラー */
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 区分.対象取引（メッセージ表示用） 変換用：ドメインID */
    private static final String CODE_LIST_MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 区分.対象取引（メッセージ表示用） 変換用：表示区分 */
    private static final String CODE_LIST_KEY = "5";
    
    /** 区分.対象取引（メッセージ表示用） 変換用：表示パターン */
    private static final String CODE_LIST_DISP_PATTERN = "1";
    
    /** 外貨建口座取引開設状況 未開設判定値*/
    private static final String FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STATUS_VALUE = "0";
    
    /** 外貨建口座取引未開設エラー*/
    private static final String ERRORS_FOREIGNSTOCKACCOUNTCHECK = "errors.foreignStockAccountCheck";
    
    /** 顧客共通情報.信用口座区分（外国） 現物口座判定値*/
    private static final String FOREIGN_MARGIN_ACCOUNT_TYPE_VALUE = " ";
    
    /** 外国信用口座未開設エラー */
    private static final String ERRORS_FRS_FOREIGNSTOCKACCOUNT_NOTOPEN_1 = "errors.frs.foreignStockAccount.notOpen#1";
    
    /** 指示金額指定値エラー */
    private static final String ERRORS_REQUIRED = "errors.required";
    
    /** 指示金額指定値エラーの埋め込み文字 */
    private static final String ERRORS_REQUIRED_PARAM = "指示金額";
    
    /** 指示金額指定値エラー */
    private static final String ERRORS_FRS_MAXVALUE_EXCEEDED = "errors.frs.maxValue.exceeded";
    
    /** 指示金額指定値エラー */
    private static final String ERRORS_FRS_MAXVALUE_EXCEEDED_PARAM = "指示可能金額";
    
    /** 指示金額指定値エラー */
    private static final String ERRORS_FRS_INSTRUCTABLEAMOUNT_NOTHING = "errors.frs.instructableAmount.nothing";
    
    /** 振替サービス時間外エラー */
    private static final String ERRORS_FRS_TRANSFERSERVICEHOURS_OUTOFRANGE = "errors.frs.transferServiceHours.outOfRange";
    
    /** 口座選択 「預り金　→　保証金」選択値 */
    private static final String CASH_DEPOSIT = "CASH_DEPOSIT";
    /**
     * チェック処理に対する結果情報
     */
    @Data
    private static class CheckErrResult {
        
        /** エラーメッセージID */
        String errMsgId;
        
        /** エラーメッセージ */
        String errMsg;
    }
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignMarginDepositTransferInputA001RequestDto
     * Dto レスポンス：IfaForeignMarginDepositTransferInputA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaForeignMarginDepositTransferInputA001ResponseDto> initializeA001(
            IfaForeignMarginDepositTransferInputA001RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginDepositTransferInputServiceImplL.initializeA001");
        }
        
        DataList<IfaForeignMarginDepositTransferInputA001ResponseDto> dtoRes = new DataList<IfaForeignMarginDepositTransferInputA001ResponseDto>();
        List<IfaForeignMarginDepositTransferInputA001ResponseDto> resDtoList = new ArrayList<IfaForeignMarginDepositTransferInputA001ResponseDto>();
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        // ① 利用者の口座に対する権限チェックを行う。
        CheckErrResult rtnErrResult = null;
        if ((rtnErrResult = this.fct001Check(butenCode, accountNumber)) != null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, rtnErrResult.getErrMsgId(),
                    rtnErrResult.getErrMsg());
            return dtoRes;
        }
        
        // ② 口座の契約締結前交付書面コードのチェック　および　共同募集顧客(共募顧客)のチェックを行う
        if ((rtnErrResult = this.fct003Check(butenCode, accountNumber, "", false)) != null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, rtnErrResult.getErrMsgId(),
                    rtnErrResult.getErrMsg());
            return dtoRes;
        }
        
        // ③ 顧客共通情報.外国株式取引口座開設状況より、外貨建口座取引開設状況のチェックを行う。
        if ((rtnErrResult = this
                .foreignStockTradeAccountOpenStatusCheck(cc.getForeignStockTradeAccountOpenStatus())) != null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, rtnErrResult.getErrMsgId(),
                    rtnErrResult.getErrMsg());
            return dtoRes;
        }
        
        // ④ 顧客共通情報.信用口座区分（外国）より、外国信用口座開設状況のチェックを行う。
        if ((rtnErrResult = this.foreignMarginAccountTypeCheck(cc.getForeignMarginAccountType())) != null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, rtnErrResult.getErrMsgId(),
                    rtnErrResult.getErrMsg());
            return dtoRes;
        }
        
        // ⑤    余力情報を取得する。
        // ※画面.口座選択において、「預り金　→　保証金」（初期値）が設定されている
        OutputFct004Dto fct004Res = this.callFct004(butenCode, accountNumber, CASH_DEPOSIT);
        String destinationAbleAmount = null;
        try {
            destinationAbleAmount = this.getDestinationAbleAmount(butenCode, accountNumber, fct004Res);            
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        IfaForeignMarginDepositTransferInputA001ResponseDto res = new IfaForeignMarginDepositTransferInputA001ResponseDto();
        res.setDestinationAbleAmount(destinationAbleAmount);
        resDtoList.add(res);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "", "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：口座選択
     * Dto リクエスト：IfaForeignMarginDepositTransferInputA002RequestDto
     * Dto レスポンス：IfaForeignMarginDepositTransferInputA002ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 口座選択処理で例外が発生した場合
     */
    public DataList<IfaForeignMarginDepositTransferInputA002ResponseDto> accountSelectionA002(
            IfaForeignMarginDepositTransferInputA002RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginDepositTransferInputServiceImplL.accountSelectionA002");
        }
        
        DataList<IfaForeignMarginDepositTransferInputA002ResponseDto> dtoRes = new DataList<IfaForeignMarginDepositTransferInputA002ResponseDto>();
        List<IfaForeignMarginDepositTransferInputA002ResponseDto> resDtoList = new ArrayList<IfaForeignMarginDepositTransferInputA002ResponseDto>();
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        // ① 利用者の口座に対する権限チェックを行う。
        CheckErrResult rtnErrResult = null;
        if ((rtnErrResult = this.fct001Check(butenCode, accountNumber)) != null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, rtnErrResult.getErrMsgId(),
                    rtnErrResult.getErrMsg());
            return dtoRes;
        }
        
        // ② 口座の契約締結前交付書面コードのチェック　および　共同募集顧客(共募顧客)のチェックを行う
        if ((rtnErrResult = this.fct003Check(butenCode, accountNumber, dtoReq.getAccountSelect(), true)) != null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, rtnErrResult.getErrMsgId(),
                    rtnErrResult.getErrMsg());
            return dtoRes;
        }
        
        // ③ 顧客共通情報.外国株式取引口座開設状況より、外貨建口座取引開設状況のチェックを行う。
        if ((rtnErrResult = this
                .foreignStockTradeAccountOpenStatusCheck(cc.getForeignStockTradeAccountOpenStatus())) != null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, rtnErrResult.getErrMsgId(),
                    rtnErrResult.getErrMsg());
            return dtoRes;
        }
        
        // ④ 顧客共通情報.信用口座区分（外国）より、外国信用口座開設状況のチェックを行う。
        if ((rtnErrResult = this.foreignMarginAccountTypeCheck(cc.getForeignMarginAccountType())) != null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, rtnErrResult.getErrMsgId(),
                    rtnErrResult.getErrMsg());
            return dtoRes;
        }
        
        // 口座選択
        // 1   預り金　→　保証金
        // 0   保証金　→　預り金
        // ⑤  画面.口座選択を取得し、余力情報を算出する
        String destinationAbleAmount = null;
        if (StringUtils.equals(dtoReq.getAccountSelect(),
                IfaForeignMarginDepositTransferInputServiceImpL.REQUEST_ACCOUNT_SELECT_TO_SECURITY) == true) {
            
            // ■画面.口座選択が「預り金　→　保証金」の場合
            OutputFct004Dto fct004Res = this.callFct004(butenCode, accountNumber, CASH_DEPOSIT);
            try {                
                destinationAbleAmount = this.getDestinationAbleAmount(butenCode, accountNumber, fct004Res);
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
        } else {
            // ■画面.口座選択が「保証金　→　預り金」の場合
            try {                
                destinationAbleAmount = this.getDestinationAbleAmount(butenCode, accountNumber);
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
        }
        
        IfaForeignMarginDepositTransferInputA002ResponseDto res = new IfaForeignMarginDepositTransferInputA002ResponseDto();
        res.setDestinationAbleAmount(destinationAbleAmount);
        resDtoList.add(res);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "", "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A003
     * アクション名：結果を表示
     * Dto リクエスト：IfaForeignMarginDepositTransferInputA003RequestDto
     * Dto レスポンス：IfaForeignMarginDepositTransferInputA003ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 結果表示処理で例外が発生した場合
     */
    public DataList<IfaForeignMarginDepositTransferInputA003ResponseDto> displayResultA003(
            IfaForeignMarginDepositTransferInputA003RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginDepositTransferInputServiceImplL.displayResultA003");
        }
        
        DataList<IfaForeignMarginDepositTransferInputA003ResponseDto> dtoRes = new DataList<IfaForeignMarginDepositTransferInputA003ResponseDto>();
        List<IfaForeignMarginDepositTransferInputA003ResponseDto> resDtoList = new ArrayList<IfaForeignMarginDepositTransferInputA003ResponseDto>();
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        // ① 利用者の口座に対する権限チェックを行う。
        CheckErrResult rtnErrResult = null;
        if ((rtnErrResult = this.fct001Check(butenCode, accountNumber)) != null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, rtnErrResult.getErrMsgId(),
                    rtnErrResult.getErrMsg());
            return dtoRes;
        }
        
        // ② 口座の契約締結前交付書面コードのチェック　および　共同募集顧客(共募顧客)のチェックを行う
        if ((rtnErrResult = this.fct003Check(butenCode, accountNumber, dtoReq.getAccountSelect(), true)) != null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, rtnErrResult.getErrMsgId(),
                    rtnErrResult.getErrMsg());
            return dtoRes;
        }
        
        // ③ 顧客共通情報.外国株式取引口座開設状況より、外貨建口座取引開設状況のチェックを行う。
        if ((rtnErrResult = this
                .foreignStockTradeAccountOpenStatusCheck(cc.getForeignStockTradeAccountOpenStatus())) != null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, rtnErrResult.getErrMsgId(),
                    rtnErrResult.getErrMsg());
            return dtoRes;
        }
        
        // ④ 顧客共通情報.信用口座区分（外国）より、外国信用口座開設状況のチェックを行う。
        if ((rtnErrResult = this.foreignMarginAccountTypeCheck(cc.getForeignMarginAccountType())) != null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, rtnErrResult.getErrMsgId(),
                    rtnErrResult.getErrMsg());
            return dtoRes;
        }
        
        // 口座選択
        // 1   預り金　→　保証金
        // 0   保証金　→　預り金
        // ⑤  画面.口座選択を取得し、余力情報を算出する
        String destinationAbleAmount = null;
        if (StringUtils.equals(dtoReq.getAccountSelect(),
                IfaForeignMarginDepositTransferInputServiceImpL.REQUEST_ACCOUNT_SELECT_TO_SECURITY) == true) {
            
            // ■画面.口座選択が「預り金　→　保証金」の場合
            OutputFct004Dto fct004Res = this.callFct004(butenCode, accountNumber, CASH_DEPOSIT);
            try {
                destinationAbleAmount = this.getDestinationAbleAmount(butenCode, accountNumber, fct004Res);                
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
        } else {
            // ■画面.口座選択が「保証金　→　預り金」の場合
            try {                
                destinationAbleAmount = this.getDestinationAbleAmount(butenCode, accountNumber);
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
        }
        
        // ⑥ 入力項目「指示金額」をチェックする。
        BigDecimal destinationAbleAmountValue = new BigDecimal(destinationAbleAmount);
        BigDecimal destinationAmountValue = new BigDecimal(dtoReq.getDestinationAmount());
        if (destinationAbleAmountValue.signum() == 1) {
            //    ■”指示可能金額” ＞ ０ の場合
            
            if (destinationAmountValue.signum() == 0) {
                //    ”指示金額” ＝ 0：エラーを返す
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_REQUIRED,
                        IfaCommonUtil.getMessage(IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_REQUIRED,
                                new String[] {
                                        IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_REQUIRED_PARAM }));
                return dtoRes;
            } else if (destinationAmountValue.compareTo(destinationAbleAmountValue) == 1) {
                //    ”指示金額” ＞ 0：指示金額が指示可能金額以下であることをチェックする。
                //    エラーあり：エラーを返す。
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_FRS_MAXVALUE_EXCEEDED,
                        IfaCommonUtil.getMessage(
                                IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_FRS_MAXVALUE_EXCEEDED,
                                new String[] { IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_REQUIRED_PARAM,
                                        IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_FRS_MAXVALUE_EXCEEDED_PARAM }));
                return dtoRes;
            }
        } else {
            //    ■”指示可能金額” ≦ 0 の場合：エラーを返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_FRS_INSTRUCTABLEAMOUNT_NOTHING,
                    IfaCommonUtil.getMessage(
                            IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_FRS_INSTRUCTABLEAMOUNT_NOTHING));
            return dtoRes;
        }
        
        //⑦  振替結果（予定）を取得する。
        //    振替結果（予定）の新規建余力（振替前／振替後）
        //    振替結果（予定）の委託保証金率（振替前／振替後）
        String[] powerAndRate = null;
        try {
            powerAndRate = this.getMarginPositionPowerAndRate(butenCode, accountNumber, dtoReq.getAccountSelect(),dtoReq.getDestinationAmount());
        } catch(Exception e) {
            LOGGER.info("IfaForeignMarginDepositTransferInputServiceImplL.displayResultA003 : API003 Exception occured.");      
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        IfaForeignMarginDepositTransferInputA003ResponseDto res = new IfaForeignMarginDepositTransferInputA003ResponseDto();
        res.setDestinationAbleAmount(destinationAbleAmount);
        res.setMarginPositionPowerBefore(powerAndRate[0]);
        res.setMarginPositionPowerAfter(powerAndRate[1]);
        res.setMarginDepositRateBefore(powerAndRate[2]);
        res.setMarginDepositRateAfter(powerAndRate[3]);
        resDtoList.add(res);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "", "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A004
     * アクション名：振替確認
     * Dto リクエスト：IfaForeignMarginDepositTransferInputA004RequestDto
     * Dto レスポンス：IfaForeignMarginDepositTransferInputA004ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 振替確認処理で例外が発生した場合
     */
    public DataList<IfaForeignMarginDepositTransferInputA004ResponseDto> transferConfirmA004(
            IfaForeignMarginDepositTransferInputA004RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginDepositTransferInputServiceImplL.transferConfirmA004");
        }
        
        DataList<IfaForeignMarginDepositTransferInputA004ResponseDto> dtoRes = new DataList<IfaForeignMarginDepositTransferInputA004ResponseDto>();
        List<IfaForeignMarginDepositTransferInputA004ResponseDto> resDtoList = new ArrayList<IfaForeignMarginDepositTransferInputA004ResponseDto>();
        
        // ①  振替サービス時間のチェックを行う。
        //     OKの場合：次の処理へ
        //     NGの場合：エラーを返す
        InputFct018Dto fct018Req = new InputFct018Dto();
        fct018Req.setCountryCode("US");
        fct018Req.setForeignServiceHoursCheckTarget("1");
        
        OutputFct018Dto fct018Res = fct018.doCheck(fct018Req);
        if (StringUtils.equals("OK", fct018Res.getProcessResult()) == false) {
            // 
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_FRS_TRANSFERSERVICEHOURS_OUTOFRANGE,
                    IfaCommonUtil.getMessage(
                            IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_FRS_TRANSFERSERVICEHOURS_OUTOFRANGE));
            return dtoRes;
        }
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        // ② 利用者の口座に対する権限チェックを行う。
        CheckErrResult rtnErrResult = null;
        if ((rtnErrResult = this.fct001Check(butenCode, accountNumber)) != null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, rtnErrResult.getErrMsgId(),
                    rtnErrResult.getErrMsg());
            return dtoRes;
        }
        
        // ③ 口座の契約締結前交付書面コードのチェック　および　共同募集顧客(共募顧客)のチェックを行う
        if ((rtnErrResult = this.fct003Check(butenCode, accountNumber, dtoReq.getAccountSelect(), true)) != null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, rtnErrResult.getErrMsgId(),
                    rtnErrResult.getErrMsg());
            return dtoRes;
        }
        
        // ④ 顧客共通情報.外国株式取引口座開設状況より、外貨建口座取引開設状況のチェックを行う。
        if ((rtnErrResult = this
                .foreignStockTradeAccountOpenStatusCheck(cc.getForeignStockTradeAccountOpenStatus())) != null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, rtnErrResult.getErrMsgId(),
                    rtnErrResult.getErrMsg());
            return dtoRes;
        }
        
        // ⑤ 顧客共通情報.信用口座区分（外国）より、外国信用口座開設状況のチェックを行う。
        if ((rtnErrResult = this.foreignMarginAccountTypeCheck(cc.getForeignMarginAccountType())) != null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, rtnErrResult.getErrMsgId(),
                    rtnErrResult.getErrMsg());
            return dtoRes;
        }
        
        // ⑥  画面.口座選択を取得し、余力情報を算出する
        String destinationAbleAmount = null;
        OutputFct004Dto fct004Res = null;
        if (StringUtils.equals(dtoReq.getAccountSelect(),
                IfaForeignMarginDepositTransferInputServiceImpL.REQUEST_ACCOUNT_SELECT_TO_SECURITY) == true) {
            
            // ■画面.口座選択が「預り金　→　保証金」の場合
            fct004Res = this.callFct004(butenCode, accountNumber, CASH_DEPOSIT);
            try {                
                destinationAbleAmount = this.getDestinationAbleAmount(butenCode, accountNumber, fct004Res);
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
        } else {
            // ■画面.口座選択が「保証金　→　預り金」の場合
            try {
                destinationAbleAmount = this.getDestinationAbleAmount(butenCode, accountNumber);                
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
        }
        
        // ⑦ 入力項目「指示金額」をチェックする。
        BigDecimal destinationAbleAmountValue = new BigDecimal(destinationAbleAmount);
        BigDecimal destinationAmountValue = new BigDecimal(dtoReq.getDestinationAmount());
        if (destinationAbleAmountValue.signum() == 1) {
            //    ■”指示可能金額” ＞ ０ の場合
            
            if (destinationAmountValue.signum() == 0) {
                //    ”指示金額” ＝ 0：エラーを返す
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_REQUIRED,
                        IfaCommonUtil.getMessage(IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_REQUIRED,
                                new String[] {
                                        IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_REQUIRED_PARAM }));
                return dtoRes;
            } else if (destinationAmountValue.compareTo(destinationAbleAmountValue) == 1) {
                //    ”指示金額” ＞ 0：指示金額が指示可能金額以下であることをチェックする。
                //    エラーあり：エラーを返す。
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_FRS_MAXVALUE_EXCEEDED,
                        IfaCommonUtil.getMessage(
                                IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_FRS_MAXVALUE_EXCEEDED,
                                new String[] { IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_REQUIRED_PARAM,
                                        IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_FRS_MAXVALUE_EXCEEDED_PARAM }));
                return dtoRes;
            }
        } else {
            //    ■”指示可能金額” ≦ 0 の場合：エラーを返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_FRS_INSTRUCTABLEAMOUNT_NOTHING,
                    IfaCommonUtil.getMessage(
                            IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_FRS_INSTRUCTABLEAMOUNT_NOTHING));
            return dtoRes;
        }
        
        //⑦  振替結果（予定）を取得する。
        //    振替結果（予定）の新規建余力（振替前／振替後）
        //    振替結果（予定）の委託保証金率（振替前／振替後）
        String[] powerAndRate = null;
        try {
            powerAndRate = this.getMarginPositionPowerAndRate(butenCode, accountNumber, dtoReq.getAccountSelect(),dtoReq.getDestinationAmount());
        } catch(Exception e) {
            LOGGER.info("IfaForeignMarginDepositTransferInputServiceImplL.transferConfirmA004 : API003 Exception occured.");      
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        IfaForeignMarginDepositTransferInputA004ResponseDto res = new IfaForeignMarginDepositTransferInputA004ResponseDto();
        res.setDestinationAmount(dtoReq.getDestinationAmount());
        res.setAccountSelect(dtoReq.getAccountSelect());
        res.setMarginPositionPowerBefore(powerAndRate[0]);
        res.setMarginPositionPowerAfter(powerAndRate[1]);
        res.setMarginDepositRateBefore(powerAndRate[2]);
        res.setMarginDepositRateAfter(powerAndRate[3]);
        res.setTransferDate(powerAndRate[6]);
        // 指示可能金額を算出する
        BigDecimal afterTransferDestinationAbleAmount = null;
        BigDecimal currentTransferDestinationAbleAmount = null;
        if (StringUtils.equals(dtoReq.getAccountSelect(),
                IfaForeignMarginDepositTransferInputServiceImpL.REQUEST_ACCOUNT_SELECT_TO_SECURITY) == true) {
            
            // ■画面.口座選択が「預り金  →  保証金」の場合
            //   現在の振替指示可能額  ＝  API003.現在の余力情報.振替指示可能額 +FCT004.計算後の余力金額"
            //   受付後振替指示可能額  ＝  API003.受付後余力情報.振替指示可能額 + FCT004.計算後の余力金額"
            currentTransferDestinationAbleAmount = new BigDecimal(powerAndRate[4]);
            currentTransferDestinationAbleAmount = currentTransferDestinationAbleAmount
                    .add(fct004Res.getByingPowerMoneyAfterCalculate()); 
            afterTransferDestinationAbleAmount = new BigDecimal(powerAndRate[5]);
            afterTransferDestinationAbleAmount = afterTransferDestinationAbleAmount
                    .add(fct004Res.getByingPowerMoneyAfterCalculate());
        } else {
            // ■画面.口座選択が「保証金  →  預り金」の場合
            //   現在の振替指示可能額  ＝  API003.現在の余力情報.振替指示可能額
            currentTransferDestinationAbleAmount = new BigDecimal(powerAndRate[4]);
            //   受付後振替指示可能額  ＝  API003.受付後余力情報.振替指示可能額
            afterTransferDestinationAbleAmount = new BigDecimal(powerAndRate[5]);
        }
        res.setAfterTransferDestinationAbleAmount(afterTransferDestinationAbleAmount.toPlainString());
        res.setCurrentTransferDestinationAbleAmount(currentTransferDestinationAbleAmount.toPlainString());
        
        resDtoList.add(res);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "", "");
        
        return dtoRes;
    }
    
    /**
     * FCT001による、利用者口座に対する権限チェックを行う
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return エラーである場合エラー情報を返す、エラー出ない場合nullを返す。
     */
    private CheckErrResult fct001Check(String butenCode, String accountNumber) {
        
        CheckErrResult rtnErrResult = null;
        
        InputFct001Dto fct001InData = new InputFct001Dto();
        fct001InData.setButenCode(butenCode);
        fct001InData.setAccountNumber(accountNumber);
        OutputFct001Dto fct001Dto = fct001.doCheck(fct001InData);
        
        // 対象顧客参照権限有無＝"0"（権限なし）：権限なしエラーを返す。
        // 取引停止フラグ＝"1"（取引停止口座）：取引停止口座エラーを返す。
        // 上記以外：次の処理へ
        if (fct001Dto == null || StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(),
                IfaForeignMarginDepositTransferInputServiceImpL.AUTH_ERROR_VALUE)) {
            rtnErrResult = new CheckErrResult();
            rtnErrResult.setErrMsgId(IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS);
            rtnErrResult.setErrMsg(
                    IfaCommonUtil.getMessage(IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                            new String[] { butenCode, accountNumber }));
        } else if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(),
                IfaForeignMarginDepositTransferInputServiceImpL.SELECTEDACCOUNT_OUTOFSERVICE_ERROR_VALUE)) {
            rtnErrResult = new CheckErrResult();
            rtnErrResult.setErrMsgId(
                    IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE);
            rtnErrResult.setErrMsg(IfaCommonUtil.getMessage(
                    IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE));
        }
        
        return rtnErrResult;
    }
    
    /**
     * FCT003による、利用者口座に対する媒介可否を行う
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param accountSelect 口座選択
     * @param isSetTradeCd FCT003のパラメタとして取引種別、国籍コード、通貨コード指定有無
     * @return エラーである場合エラー情報を返す、エラー出ない場合nullを返す。
     */
    private CheckErrResult fct003Check(String butenCode, String accountNumber, String accountSelect,
            boolean isSetTradeCd) {
        
        CheckErrResult rtnErrResult = null;
        
        // 画面.口座選択を区分.取引種別（外国株式）に変換
        //   ■「預り金　→　保証金」選択時：保証金振替（預り金⇒保証金）
        //   ■「保証金　→　預り金」選択時：保証金振替（保証金⇒預り金）
        String tradeCd = "";
        if (StringUtils.equals(accountSelect,
                IfaForeignMarginDepositTransferInputServiceImpL.REQUEST_ACCOUNT_SELECT_TO_SECURITY)) {
            tradeCd = IfaForeignMarginDepositTransferInputServiceImpL.FCT003_ACCOUNT_SELECT_TO_SECURITY;
        } else if (StringUtils.equals(accountSelect,
                IfaForeignMarginDepositTransferInputServiceImpL.REQUEST_ACCOUNT_SELECT_TO_DEPOSIT)) {
            tradeCd = IfaForeignMarginDepositTransferInputServiceImpL.FCT003_CCOUNT_SELECT_TO_DEPOSIT;
        }
        
        InputFct003Dto fct003Req = new InputFct003Dto();
        fct003Req.setButenCode(butenCode);
        fct003Req.setAccountNumber(accountNumber);
        fct003Req.setProductCd("15");
        if (isSetTradeCd == true) {
            fct003Req.setTradeCd(tradeCd);
            fct003Req.setCountryCd("99");
            fct003Req.setCurrencyCode(IfaForeignMarginDepositTransferInputServiceImpL.FCT003_CURRENCY_CODE_USD);
        }
        OutputFct003Dto fct003Res = fct003.doCheck(fct003Req);
        
        //レスポンス.媒介可否リスト.取引種別が　"保証金振替（預り金⇒保証金）"　かつ　レスポンス.媒介可否リスト.通貨コードが　"USD"　かつ レスポンス.媒介可否リスト.媒介可否が"1"（媒介可）
        //または
        //レスポンス.媒介可否リスト.取引種別が　"保証金振替（保証金⇒預り金）"　かつ　レスポンス.媒介可否リスト.通貨コードが　"USD"　かつ レスポンス.媒介可否リスト.媒介可否が"1"（媒介可）
        //が存在するかチェックする
        boolean isError = true;
        
        for (OutputFct003Dto.MediatePropriety mediatePropriety : fct003Res.getMediateProprietyList()) {
            
            String checkTradeClass = mediatePropriety.getTradeClass();
            String checkMediatePropriety = mediatePropriety.getMediatePropriety();
            String checkCountryCd = mediatePropriety.getCurrencyCode();
            
            if (StringUtils.equals(checkTradeClass,
                    IfaForeignMarginDepositTransferInputServiceImpL.FCT003_ACCOUNT_SELECT_TO_SECURITY) == true
                    || StringUtils.equals(checkTradeClass,
                            IfaForeignMarginDepositTransferInputServiceImpL.FCT003_CCOUNT_SELECT_TO_DEPOSIT) == true) {
                
                if (isSetTradeCd == true 
                        || StringUtils.equals(checkCountryCd, IfaForeignMarginDepositTransferInputServiceImpL.FCT003_CURRENCY_CODE_USD) == true) {
                    if (StringUtils.equals(checkMediatePropriety,
                            IfaForeignMarginDepositTransferInputServiceImpL.FCT003_MEDIATE_PROPRIETY_VALUE) == true) {
                        isError = false;
                    }
                }
            }
            
            if (isError == false) {
                break;
            }
            
        }

        if (isError == true) {
            String msgItem = codeListService.getValue(
                    IfaForeignMarginDepositTransferInputServiceImpL.CODE_LIST_MSG_DISPLAY_TARGET_TRADE,
                    IfaForeignMarginDepositTransferInputServiceImpL.CODE_LIST_KEY,
                    IfaForeignMarginDepositTransferInputServiceImpL.CODE_LIST_DISP_PATTERN);
            
            rtnErrResult = new CheckErrResult();
            rtnErrResult.setErrMsgId(
                    IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE);
            rtnErrResult.setErrMsg(IfaCommonUtil.getMessage(
                    IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                    new String[] { msgItem }));
        }
        
        return rtnErrResult;
        
    }
    
    /**
     * 外貨建口座取引開設状況のチェック
     *
     * @param foreignStockTradeAccountOpenStatus 外国株式取引口座開設状況
     * @return 未開設済である場合エラー情報を返す、開設済である場合nullを返す。
     */
    private CheckErrResult foreignStockTradeAccountOpenStatusCheck(String foreignStockTradeAccountOpenStatus) {
        
        CheckErrResult rtnErrResult = null;
        if (StringUtils.equals(foreignStockTradeAccountOpenStatus,
                IfaForeignMarginDepositTransferInputServiceImpL.FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STATUS_VALUE)) {
            
            rtnErrResult = new CheckErrResult();
            rtnErrResult.setErrMsgId(IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_FOREIGNSTOCKACCOUNTCHECK);
            rtnErrResult.setErrMsg(IfaCommonUtil
                    .getMessage(IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_FOREIGNSTOCKACCOUNTCHECK));
        }
        
        return rtnErrResult;
    }
    
    /**
     * 外国信用口座開設状況のチェック
     *
     * @param foreignMarginAccountType 信用口座区分（外国）
     * @return 現物口座である場合エラー情報を返す、信用口座である場合nullを返す。
     */
    private CheckErrResult foreignMarginAccountTypeCheck(String foreignMarginAccountType) {
        
        CheckErrResult rtnErrResult = null;
        if (StringUtils.equals(foreignMarginAccountType,
                IfaForeignMarginDepositTransferInputServiceImpL.FOREIGN_MARGIN_ACCOUNT_TYPE_VALUE)) {
            
            rtnErrResult = new CheckErrResult();
            rtnErrResult.setErrMsgId(
                    IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_FRS_FOREIGNSTOCKACCOUNT_NOTOPEN_1);
            rtnErrResult.setErrMsg(IfaCommonUtil.getMessage(
                    IfaForeignMarginDepositTransferInputServiceImpL.ERRORS_FRS_FOREIGNSTOCKACCOUNT_NOTOPEN_1));
        }
        
        return rtnErrResult;
    }
    
    /**
     * IFAP米株 余力チェック（金額）(FCT004)
     *
     * @param butenCode 部店コード
     * @param accountNo 口座番号
     * @param tradeType 取引区分
     * @return FCT004レスポンス
     */
    private OutputFct004Dto callFct004(String butenCode, String accountNo, String tradeType)  throws Exception {
        
        // IFAポータル内の当日買付約定金額を取得する(FCT004)
        InputFct004Dto fct004Req = new InputFct004Dto();
        fct004Req.setButenCode(butenCode);
        fct004Req.setAccountNumber(accountNo);
        fct004Req.setDepositType("GENERAL");
        fct004Req.setOtcManageNumber("");
        fct004Req.setTradeType(tradeType);
        OutputFct004Dto fct004Res = fct004.doCheck(fct004Req);
        
        return fct004Res;
    }
    
    /**
     * 余力情報の取得(API002)
     *
     * @param butenCode 部店コード
     * @param accountNo 口座番号
     * @param fct004Res FCT004レスポンス
     * @return 指示可能金額
     * @throws Exception API002（ListForeignScheduleCashBalances）で例外が発生した場合
     */
    private String getDestinationAbleAmount(String butenCode, String accountNo, OutputFct004Dto fct004Res)
            throws Exception {
        
        // COMETの余力を取得する(API002)
        ListForeignScheduleCashBalancesReq api002Input = new ListForeignScheduleCashBalancesReq();
        api002Input.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));
        //通貨コード "USD"：米ドル　※初期値
        api002Input.getParameter().setCurrencyCode("USD");
        // 口座分類  "GENERAL"（総合）
        api002Input.getParameter().setAccountKind("GENERAL");
        api002Input.getParameter().setDays(1);
        ListForeignScheduleCashBalancesResp api002Output = null; 
        try {            
            api002Output = foreignAccountService.listForeignScheduleCashBalances(api002Input);
        } catch (Exception e) {
            throw e;
        }
        String paymentPossibleAmount = api002Output.getForeignCashBalances().get(0).getCurrencyCashBalances().get(0)
                .getForeignScheduleCashBalances().get(0).getPaymentPossibleAmount();
        
        BigDecimal paymentPossibleAmountValue = new BigDecimal(paymentPossibleAmount);
        // 指示可能金額を算出する
        // 指示可能金額　＝　API002.外貨金銭残高スケジュールリスト.外貨金銭残高スケジュール(全通貨).外貨金銭残高スケジュールリスト.出金余力
        //　　　　　　　　　　　　　+　FCT004.計算後の余力金額
        return paymentPossibleAmountValue.add(fct004Res.getByingPowerMoneyAfterCalculate()).toString();
    }
    
    /**
     * 余力情報の取得(API001)
     *
     * @param butenCode 部店コード
     * @param accountNo 口座番号
     * @return 指示可能金額
     * @throws Exception API002（GetMarginPowerHeadline）で例外が発生した場合
     */
    private String getDestinationAbleAmount(String butenCode, String accountNo) throws Exception {
        
        GetMarginPowerHeadlineResp api001Ouput = null; 
        try {            
            api001Ouput = foreignAccountService.getMarginPowerHeadline(butenCode, accountNo, "US");
        } catch (Exception e) {
            throw e;
        }
        return api001Ouput.getMarginWithdrawable();
    }
    
    /**
     * 振替結果（予定）の取得(API003)
     *
     * @param butenCode 部店コード
     * @param accountNo 口座番号
     * @param accountSelect 口座選択
     * @param destinationAmount 指示金額
     * @return 信用建余力、預託率情報
     * @throws Exception API002（GetMarginPowerHeadline）で例外が発生した場合
     */
    private String[] getMarginPositionPowerAndRate(String butenCode, String accountNo, String accountSelect,
            String destinationAmount) throws Exception {
        
        ConfirmMarginTransferResp api003Output = foreignAccountService.confirmMarginTransfer(butenCode, accountNo,
                "USD", accountSelect, destinationAmount);
        
        String[] powerAndRate = new String[7];
        powerAndRate[0] = api003Output.getCurrentPower().getMarginBuyingPower();
        powerAndRate[1] = api003Output.getAfterPower().getMarginBuyingPower();
        powerAndRate[2] = api003Output.getCurrentPower().getDepositRate();
        powerAndRate[3] = api003Output.getAfterPower().getDepositRate();
        powerAndRate[4] = api003Output.getCurrentPower().getMarginWithdrawable();
        powerAndRate[5] = api003Output.getAfterPower().getMarginWithdrawable();
        powerAndRate[6] = api003Output.getTransferDate();
        
        return powerAndRate;
    }
}
