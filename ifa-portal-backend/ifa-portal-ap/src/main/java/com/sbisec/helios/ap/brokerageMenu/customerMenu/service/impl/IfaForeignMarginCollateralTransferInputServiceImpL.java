package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.account.ListPossibleCollateralSecuritiesTransfersReq;
import com.sbisec.helios.ap.athena.protocol.account.ListPossibleCollateralSecuritiesTransfersResp;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmCollateralSecuritiesTransferReq;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmCollateralSecuritiesTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.dto.CollateralSecuritiesTransfer;
import com.sbisec.helios.ap.athena.protocol.account.dto.CollateralSecuritiesTransferDetailInput;
import com.sbisec.helios.ap.athena.protocol.account.dto.CollateralSecuritiesTransferInput;
import com.sbisec.helios.ap.athena.protocol.account.dto.PossibleCollateralSecuritiesTransfer;
import com.sbisec.helios.ap.athena.protocol.fstock.lending.GetForeignStockLendingSubscribedStatusResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerHeadlineResp;
import com.sbisec.helios.ap.athena.utils.RequestUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct018;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct018Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct018Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignMarginCollateralTransferInputDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginCollateralTransferInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginCollateralTransferInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA003DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA003DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA004DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputCollateralSecurity;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferPowerInfo;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignMarginCollateralTransferInputService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignMarginAccountType;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID：SUB0202_0305-01_1
 * 画面名：米株信用代用振替入力
 *
 * @author SCSK川崎
 *
   2024/03/19新規作成
 */
@Component(value = "cmpIfaForeignMarginCollateralTransferInputService")
public class IfaForeignMarginCollateralTransferInputServiceImpL
        implements IfaForeignMarginCollateralTransferInputService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaForeignMarginCollateralTransferInputServiceImpL.class);
    
    /** FCT001 利用者顧客参照権限チェック */
    @Autowired
    private Fct001 fct001;
    
    /** FCT003 取引コース媒介可否チェック */
    @Autowired
    private Fct003 fct003;
    
    /** FCT018 サービス時間チェック（外国） */
    @Autowired
    private Fct018 fct018;
    
    /** 区分値取得クラス */
    @Autowired
    private CodeListService codeListService;
    
    /** DAO */
    @Autowired
    private IfaForeignMarginCollateralTransferInputDao dao;
    
    /** API呼び出しクラス(Athena) */
    @Autowired
    private ForeignStockService foreignStockService;
    
    @Autowired
    private ForeignAccountService foreignAccountService;

    @Autowired
    private CometCommonService cometCommonService;
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}] */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 取引停止口座のため処理を進めることができません。 */
    private static final String ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** {0}ができないコースです。 \{0}：区分.対象取引（メッセージ表示用）　（区分値：5　＠表示パターン：1）　*/
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = 
            "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 外国株式口座が未開設です。 */
    private static final String ERRORS_FOREIGNSTOCKACCOUNTCHECK = "errors.foreignStockAccountCheck";
    
    /** 米株信用口座が未開設です。 */
    private static final String ERRORS_FRS_FOREIGNSTOCKACCOUNT_NOTOPEN_NO1 = "errors.frs.foreignStockAccount.notOpen#1";
    
    /** 振替選択の可能件数は30件までです。 */
    private static final String ERRORS_FRS_TRANSFERORDER_EXCEEDED = "errors.frs.transferOrder.exceeded";
    
    /** 当該預りは店頭取引で売却注文が行われているため、振替できません。<br>銘柄：[{0}]、預り区分：[{1}] */
    private static final String ERRORS_FRS_COUNTERORDER_EXIST = "errors.frs.counterOrder.exist";
    
    /** ただいまの時間は取引時間外のため振替できません。 */
    private static final String ERRORS_FRS_TRANSFERSERVICEHOURS_OUTOFRANGE = 
            "errors.frs.transferServiceHours.outOfRange";
    
    // --------------------------------
    // 設定値
    // --------------------------------
    /** 証券金銭種別 "外国株式" */
    private static final String FOREIGN_STOCK = "15";
    
    /** 取引種別 "9"(代用振替（保護⇒代用）) */
    private static final String TRADE_CLASS_PROTECTION_TRANSFERS = "9";
    
    /** 取引種別 "10"(代用振替（代用⇒保護）) */
    private static final String TRADE_CLASS_COLLATERAL_TRANSFERS = "10";
    
    /** 国籍コード "US" */
    private static final String COUNTRY_CODE = "US";
    
    /** 通貨コード "999" */
    private static final String CURRENCY_CODE_999 = "999";
    
    /** サービス時間チェック対象（外国） "1"（振替） */
    private static final String FOREIGN_SERVICE_HOURS_CHECK_TARGET = "1";
    
    /** 判定結果：OK*/
    private static final String RESULT_OK = "OK";
    
    /** 商品コード "外国株式" */
    private static final String PRODUCT_CODE = "FOREIGN_STOCK";
    
    /** 通貨コード "USドル" */
    private static final String CURRENCY_CODE_USD = "USD";
    
    /** 媒介可否(可) */
    private static final String FCT003_MEDIATE_PROPRIETY_VALUE_1 = "1";
    
    /** 区分定義.ドメインID_対象取引（メッセージ表示用） */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 対象取引 区分値：5 */
    private static final String MSG_DISPLAY_TARGET_TRADE_KUBUN = "5";
    
    /** 区分定義.ドメインID_預り区分（外国） */
    private static final String FOREIGN_DEPOSIT_TYPE = "FOREIGN_DEPOSIT_TYPE";
    
    /** 代用有価証券振替区分 （保護預り→代用預り） */
    private static final String SECURITIES_DEPOSIT = "SECURITIES_DEPOSIT";
    
    /** 代用有価証券振替区分 （代用預り→保護預り） */
    private static final String SECURITIES_WITHDRAWAL = "SECURITIES_WITHDRAWAL";
    
    /** リストの最大インプット数 */
    private static final int MAX_SIZE = 30;
    
    /** 正常終了メッセージコード */
    private static final String SUCCESS_MESSAGE_CODE = "SUCCESS";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignMarginCollateralTransferInputA001DtoRequest
     * Dto レスポンス：IfaForeignMarginCollateralTransferInputA001DtoResponse
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignMarginCollateralTransferInputA001DtoResponse> initializeA001(
            IfaForeignMarginCollateralTransferInputA001DtoRequest dtoReq) throws Exception {
        
        DataList<IfaForeignMarginCollateralTransferInputA001DtoResponse> dtoRes = 
                new DataList<IfaForeignMarginCollateralTransferInputA001DtoResponse>();
        List<IfaForeignMarginCollateralTransferInputA001DtoResponse> resDto = 
                new ArrayList<IfaForeignMarginCollateralTransferInputA001DtoResponse>();
        IfaForeignMarginCollateralTransferInputA001DtoResponse res = 
                new IfaForeignMarginCollateralTransferInputA001DtoResponse();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginCollateralTransferInputServiceImplL.initializeA001");
        }
        
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = null;
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // action内チェック処理
        errorInfo = checkValidation(cc, false, StringUtil.EMPTY_STRING);
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        // API004:貸株サービス加入のチェック
        try {
            GetForeignStockLendingSubscribedStatusResp api004Res = callApi004(cc);
            res.setSubscribed(api004Res.getSubscribed());
            if (api004Res.getSubscribed()) {
                // 加入済みの場合
                resDto.add(res);
                return IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, SUCCESS_MESSAGE_CODE, null);
            }
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.error("IfaForeignMarginCollateralTransferInputServiceImplL.initializeA001 callAPI004", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // API002:代用有価証券余力情報を取得
        try {
            ListPossibleCollateralSecuritiesTransfersResp api002Res = callApi002(cc);
            setApi002Result(res, api002Res);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.error("IfaForeignMarginCollateralTransferInputServiceImplL.initializeA001 callAPI002", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // API001:保護預りへの振替可能額を取得
        try {
            GetMarginPowerHeadlineResp api001Res = callApi001(cc);
            res.setAbleWithdrawal(api001Res.getCollateralWithdrawable());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.error("IfaForeignMarginCollateralTransferInputServiceImplL.initializeA001 callAPI001", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        resDto.add(res);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, SUCCESS_MESSAGE_CODE, null);
        return dtoRes;
    }
    
    /**
     * アクションID：A003
     * アクション名：結果を表示
     * Dto リクエスト：IfaForeignMarginCollateralTransferInputA003DtoRequest
     * Dto レスポンス：IfaForeignMarginCollateralTransferInputA003DtoResponse
     * model リクエスト：IfaForeignMarginCollateralTransferInputSql001RequestModel
     * model レスポンス：IfaForeignMarginCollateralTransferInputSql001ResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignMarginCollateralTransferInputA003DtoResponse> displayResultA003(
            IfaForeignMarginCollateralTransferInputA003DtoRequest dtoReq) throws Exception {
        
        DataList<IfaForeignMarginCollateralTransferInputA003DtoResponse> dtoRes = 
                new DataList<IfaForeignMarginCollateralTransferInputA003DtoResponse>();
        List<IfaForeignMarginCollateralTransferInputA003DtoResponse> resDto = 
                new ArrayList<IfaForeignMarginCollateralTransferInputA003DtoResponse>();
        IfaForeignMarginCollateralTransferInputA003DtoResponse res = 
                new IfaForeignMarginCollateralTransferInputA003DtoResponse();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginCollateralTransferInputServiceImplL.displayResultA003");
        }
        
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = null;
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // 振替選択30件チェック
        errorInfo = checkExceed(dtoReq.getCollateralSecurityTransferInfoList().size());
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        // action内チェック処理
        errorInfo = checkValidation(cc, true, dtoReq.getTransferClassification());
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        // （「保護預り→代用預り」選択時のみ）SQL001:対象預りが店頭取引で売却注文されているかチェック
        if (SECURITIES_DEPOSIT.equals(dtoReq.getTransferClassification())) {
            errorInfo = checkSql001(dtoReq.getCollateralSecurityTransferInfoList(), cc);
            if (!ObjectUtils.isEmpty(errorInfo)) {
                return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
            }
        }
        
        // API003:振替結果（振替指示前／指示後）を取得
        try {
            ConfirmCollateralSecuritiesTransferResp api003Res = callApi003(dtoReq.getTransferClassification(),
                    dtoReq.getCollateralSecurityTransferInfoList(), cc);
            setApi003ResultAsA003(res, api003Res);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.error("IfaForeignMarginCollateralTransferInputServiceImplL.displayResultA003 callAPI003", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        resDto.add(res);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, SUCCESS_MESSAGE_CODE, null);
        return dtoRes;
    }
    
    /**
     * アクションID：A004
     * アクション名：振替確認
     * Dto リクエスト：IfaForeignMarginCollateralTransferInputA004DtoRequest
     * Dto レスポンス：IfaForeignMarginCollateralTransferInputA004DtoResponse
     * model リクエスト：IfaForeignMarginCollateralTransferInputSql001RequestModel
     * model レスポンス：IfaForeignMarginCollateralTransferInputSql001ResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignMarginCollateralTransferInputA004DtoResponse> transferConfirmA004(
            IfaForeignMarginCollateralTransferInputA004DtoRequest dtoReq) throws Exception {
        
        DataList<IfaForeignMarginCollateralTransferInputA004DtoResponse> dtoRes = 
                new DataList<IfaForeignMarginCollateralTransferInputA004DtoResponse>();
        List<IfaForeignMarginCollateralTransferInputA004DtoResponse> resDto = 
                new ArrayList<IfaForeignMarginCollateralTransferInputA004DtoResponse>();
        IfaForeignMarginCollateralTransferInputA004DtoResponse res = 
                new IfaForeignMarginCollateralTransferInputA004DtoResponse();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginCollateralTransferInputServiceImplL.transferConfirmA004");
        }
        
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = null;
        
        // 振替選択30件チェック
        errorInfo = checkExceed(dtoReq.getCollateralSecurityTransferInfoList().size());
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        // 振替サービス時間のチェック
        errorInfo = callFct018();
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }

        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // action内チェック処理
        errorInfo = checkValidation(cc, true, dtoReq.getTransferClassification());
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        // （「保護預り→代用預り」選択時のみ）SQL001:対象預りが店頭取引で売却注文されているかチェック
        if (SECURITIES_DEPOSIT.equals(dtoReq.getTransferClassification())) {
            errorInfo = checkSql001(dtoReq.getCollateralSecurityTransferInfoList(), cc);
            if (!ObjectUtils.isEmpty(errorInfo)) {
                return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
            }
        }
        
        // API003:振替結果（振替指示前／指示後）を取得
        try {
            ConfirmCollateralSecuritiesTransferResp api003Res = callApi003(dtoReq.getTransferClassification(),
                    dtoReq.getCollateralSecurityTransferInfoList(), cc);
            setApi003ResultAsA004(res, api003Res);
            res.setTransferClassification(dtoReq.getTransferClassification());
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.error("IfaForeignMarginCollateralTransferInputServiceImplL.transferConfirmA004 callAPI003", e);
            }
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        resDto.add(res);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, SUCCESS_MESSAGE_CODE, null);
        return dtoRes;
    }
    
    /**
     * FCT001チェック
     *
     * @param cc 顧客共通情報
     * @return エラー情報
     */
    private String[] callFct001(CustomerCommon cc) {
        
        String errorCode = StringUtil.EMPTY_STRING;
        String errorMessage = StringUtil.EMPTY_STRING;
        
        InputFct001Dto input = new InputFct001Dto();
        input.setAccountNumber(cc.getAccountNumber());
        input.setButenCode(cc.getButenCode());
        
        OutputFct001Dto output = fct001.doCheck(input);
        if (output != null) {
            if (StringUtils.equals(output.getTargetCustomerRefAuthFlag(),
                    TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
                // 権限なしの場合
                errorCode = ERRORS_BUTENACCOUNTNOTEXIST;
                errorMessage = IfaCommonUtil.getMessage(errorCode,
                        new String[] { cc.getButenCode(), cc.getAccountNumber() });
                return new String[] { errorCode, errorMessage };
            }
            if (StringUtils.equals(output.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
                // 取引停止口座の場合
                errorCode = ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE;
                errorMessage = IfaCommonUtil.getMessage(errorCode);
                return new String[] { errorCode, errorMessage };
            }
        }
        return null;
    }
    
    /**
     * FCT003チェック
     *
     * @param cc 顧客共通情報
     * @param setFlg A003,A004から呼び出しの場合true
     * @param collateralSecurityType 代用有価証券振替区分
     * @return エラー情報
     */
    private String[] callFct003(CustomerCommon cc, boolean setFlg, String collateralSecurityType) {
        
        String errorCode = StringUtil.EMPTY_STRING;
        String errorMessage = StringUtil.EMPTY_STRING;
        
        InputFct003Dto input = new InputFct003Dto();
        input.setAccountNumber(cc.getAccountNumber());
        input.setButenCode(cc.getButenCode());
        input.setProductCd(FOREIGN_STOCK);
        // A003,A004の場合
        if (setFlg) {
            // 「保護預り→代用預り」選択時
            if (SECURITIES_DEPOSIT.equals(collateralSecurityType)) {
                input.setTradeCd(TRADE_CLASS_PROTECTION_TRANSFERS);
            }
            // 「代用預り→保護預り」選択時
            if (SECURITIES_WITHDRAWAL.equals(collateralSecurityType)) {
                input.setTradeCd(TRADE_CLASS_COLLATERAL_TRANSFERS);
            }
            input.setCountryCd(COUNTRY_CODE);
            input.setCurrencyCode(CURRENCY_CODE_999);
        }
        
        boolean result = false;
        
        OutputFct003Dto output = fct003.doCheck(input);
        if (output == null || CollectionUtils.isEmpty(output.getMediateProprietyList())) {
            result = false;
        } else {
            // レスポンス.媒介可否リスト.国籍コードが"US" かつ 
            // レスポンス.媒介可否リスト.媒介可否＝"1"（媒介可） かつ　
            // （レスポンス.媒介可否リスト.取引種別が　"代用振替（保護⇒代用）"　または"代用振替（代用⇒保護）"）が存在する
            result = output.getMediateProprietyList().stream()
                    .filter(m -> (StringUtils.equals(COUNTRY_CODE, m.getNationalityCode())
                            && StringUtils.equals(FCT003_MEDIATE_PROPRIETY_VALUE_1, m.getMediatePropriety()))
                            && (StringUtils.equals(TRADE_CLASS_PROTECTION_TRANSFERS, m.getTradeClass())
                                    || StringUtils.equals(TRADE_CLASS_COLLATERAL_TRANSFERS, m.getTradeClass())))
                    .findFirst().isPresent();
        }
        if (!result) {
            errorCode = ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE;
            errorMessage = IfaCommonUtil.getMessage(errorCode, new String[] {
                    codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN) });
            return new String[] { errorCode, errorMessage };
        }
        
        return null;
    }
    
    /**
     * FCT018チェック
     *
     * @return エラー情報
     */
    private String[] callFct018() {
        
        String errorCode = StringUtil.EMPTY_STRING;
        String errorMessage = StringUtil.EMPTY_STRING;
        
        InputFct018Dto input = new InputFct018Dto();
        input.setCountryCode(COUNTRY_CODE);
        input.setForeignServiceHoursCheckTarget(FOREIGN_SERVICE_HOURS_CHECK_TARGET);
        
        OutputFct018Dto output = fct018.doCheck(input);
        if (output != null) {
            if (!StringUtils.equals(output.getProcessResult(), RESULT_OK)) {
                // 振替サービス時間外の場合
                errorCode = ERRORS_FRS_TRANSFERSERVICEHOURS_OUTOFRANGE;
                errorMessage = IfaCommonUtil.getMessage(errorCode);
                return new String[] { errorCode, errorMessage };
            }
        }
        
        return null;
    }
    
    /**
     * action内チェック処理
     *
     * @param cc 顧客共通情報
     * @param setFlg A003,A004から呼び出しの場合true
     * @param collateralSecurityType 代用有価証券振替区分
     * @return エラー情報
     */
    
    private String[] checkValidation(CustomerCommon cc, boolean setFlg, String collateralSecurityType) {
        
        String errorCode = StringUtil.EMPTY_STRING;
        String errorMessage = StringUtil.EMPTY_STRING;
        String[] errorInfo = null;
        
        // FCT001
        errorInfo = callFct001(cc);
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return errorInfo;
        }
        
        // FCT003
        errorInfo = callFct003(cc, setFlg, collateralSecurityType);
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return errorInfo;
        }
        
        //外国株式口座取引開設状況のチェック
        if (StringUtils.equals(cc.getForeignStockTradeAccountOpenStatus(),
                ForeignStockTradeAccountOpenStatus.CLOSED.getId())) {
            errorCode = ERRORS_FOREIGNSTOCKACCOUNTCHECK;
            errorMessage = IfaCommonUtil.getMessage(errorCode);
            return new String[] { errorCode, errorMessage };
        }
        
        //外国信用口座開設状況のチェック
        if (StringUtils.equals(cc.getForeignMarginAccountType(), ForeignMarginAccountType.SPOT.getId())) {
            errorCode = ERRORS_FRS_FOREIGNSTOCKACCOUNT_NOTOPEN_NO1;
            errorMessage = IfaCommonUtil.getMessage(errorCode);
            return new String[] { errorCode, errorMessage };
        }
        
        return null;
    }
    
    /**
     * 振替選択30件チェック
     *
     * @param size 代用有価証券振替情報リストの件数
     * @return エラー情報
     */
    
    private String[] checkExceed(int size) {
        
        String errorCode = StringUtil.EMPTY_STRING;
        String errorMessage = StringUtil.EMPTY_STRING;

        if (size > MAX_SIZE) {
            errorCode = ERRORS_FRS_TRANSFERORDER_EXCEEDED;
            errorMessage = IfaCommonUtil.getMessage(errorCode);
            return new String[] { errorCode, errorMessage };
        }
        
        return null;
    }
    
    /**
     * API001呼び出し処理
     *
     * @param cc 顧客共通情報
     * @return API出力結果
     * @throws Exception 例外
     */
    private GetMarginPowerHeadlineResp callApi001(CustomerCommon cc) throws Exception {
        
        // API001 Response
        GetMarginPowerHeadlineResp result = new GetMarginPowerHeadlineResp();
        
        result = foreignAccountService.getMarginPowerHeadline(cc.getButenCode(), cc.getAccountNumber(), COUNTRY_CODE);
        
        return result;
    }
    
    /**
     * API002呼び出し処理
     *
     * @param cc 顧客共通情報
     * @return API出力結果
     * @throws Exception 例外
     */
    private ListPossibleCollateralSecuritiesTransfersResp callApi002(CustomerCommon cc) throws Exception {
        
        // API002 Request
        ListPossibleCollateralSecuritiesTransfersReq req = new ListPossibleCollateralSecuritiesTransfersReq();
        
        // Header.tokenとしてリクエスト.部店コード + "-" + リクエスト.口座番号をセット
        req.getHeader().setToken(RequestUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        // 商品コードをセット
        req.getParameter().setProductCode(PRODUCT_CODE);
        // 国コードをセット
        req.getParameter().setCountryCode(COUNTRY_CODE);
        // 通貨コードをセット
        req.getParameter().setCurrencyCode(CURRENCY_CODE_USD);

        // API002 Response
        ListPossibleCollateralSecuritiesTransfersResp result = new ListPossibleCollateralSecuritiesTransfersResp();
        result = foreignAccountService.listPossibleCollateralSecuritiesTransfers(req);
        
        return result;
    }
    
    /**
     * API002の結果をActionレスポンスDTOに格納する
     *
     * @param res レスポンスDTO
     * @param api002Res API002の出力結果
     */
    private void setApi002Result(IfaForeignMarginCollateralTransferInputA001DtoResponse res,
            ListPossibleCollateralSecuritiesTransfersResp api002Res) {
        
        // 振替指示入力(代用→保護)の設定
        List<IfaForeignMarginCollateralTransferInputCollateralSecurity> substitutionTransferList = new ArrayList<>();
        for (PossibleCollateralSecuritiesTransfer data : api002Res.getCollateralTransfers()) {
            IfaForeignMarginCollateralTransferInputCollateralSecurity collateralSecurity = 
                    new IfaForeignMarginCollateralTransferInputCollateralSecurity();
            collateralSecurity.setCollateralDepositListBrandName(data.getSecurities().getSecuritiesName());
            collateralSecurity.setCollateralDepositListBrandCode(data.getSecurities().getSecuritiesCode());
            collateralSecurity.setCollateralAssessment(data.getAssessmentRate());
            collateralSecurity.setSpecificAccountCode(data.getSpecificAccountCode());
            collateralSecurity.setTransferQuantity(data.getSecuritiesQuantity());
            collateralSecurity.setNotReceivedQuantity(data.getUnPaymentQuantity());
            collateralSecurity.setValuatePrice(data.getEvaluationPrice());
            collateralSecurity.setCollateralValuation(data.getEvaluationAmount());
            collateralSecurity.setDepositType(data.getDepositType());
            substitutionTransferList.add(collateralSecurity);
        }
        res.setSubstitution(substitutionTransferList);
        
        // 振替指示入力(保護→代用)の設定
        List<IfaForeignMarginCollateralTransferInputCollateralSecurity> protectionTransferList = new ArrayList<>();
        for (PossibleCollateralSecuritiesTransfer data : api002Res.getProtectionTransfers()) {
            IfaForeignMarginCollateralTransferInputCollateralSecurity collateralSecurity = 
                    new IfaForeignMarginCollateralTransferInputCollateralSecurity();
            collateralSecurity.setCollateralDepositListBrandName(data.getSecurities().getSecuritiesName());
            collateralSecurity.setCollateralDepositListBrandCode(data.getSecurities().getSecuritiesCode());
            collateralSecurity.setCollateralAssessment(data.getAssessmentRate());
            collateralSecurity.setSpecificAccountCode(data.getSpecificAccountCode());
            collateralSecurity.setTransferQuantity(data.getSecuritiesQuantity());
            collateralSecurity.setNotReceivedQuantity(data.getUnPaymentQuantity());
            collateralSecurity.setValuatePrice(data.getEvaluationPrice());
            collateralSecurity.setCollateralValuation(data.getEvaluationAmount());
            collateralSecurity.setDepositType(data.getDepositType());
            protectionTransferList.add(collateralSecurity);
        }
        res.setProtection(protectionTransferList);
    }
    
    /**
     * API003呼び出し処理
     *
     * @param collateralSecurityType 代用有価証券振替区分
     * @param collateralTransferList 代用有価証券振替情報リスト
     * @param cc 顧客共通情報
     * @return API出力結果
     * @throws Exception 例外
     */
    private ConfirmCollateralSecuritiesTransferResp callApi003(String collateralSecurityType,
            List<IfaForeignMarginCollateralTransferInputInfo> collateralTransferList, CustomerCommon cc)
            throws Exception {
        
        // API003 Request
        ConfirmCollateralSecuritiesTransferReq req = new ConfirmCollateralSecuritiesTransferReq();
        
        // Header.tokenとしてリクエスト.部店コード + "-" + リクエスト.口座番号をセット
        req.getHeader().setToken(RequestUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        
        CollateralSecuritiesTransferInput collateralSecuritiesTransferInput = new CollateralSecuritiesTransferInput();
        // 代用有価証券振替区分をセット
        collateralSecuritiesTransferInput.setSecuritiesTransferType(collateralSecurityType);
        
        // 振替詳細情報をセット
        List<CollateralSecuritiesTransferDetailInput> detailInputList = 
                new ArrayList<CollateralSecuritiesTransferDetailInput>();
        for (IfaForeignMarginCollateralTransferInputInfo collateralSecurity : collateralTransferList) {
            CollateralSecuritiesTransferDetailInput detailInput = new CollateralSecuritiesTransferDetailInput();
            // 国コードをセット
            detailInput.setCountryCode(COUNTRY_CODE);
            // 商品コードをセット
            detailInput.setProductCode(PRODUCT_CODE);
            // 銘柄コードをセット
            detailInput.setSecuritiesCode(collateralSecurity.getCollateralDepositListBrandCode());
            // 預り区分をセット
            detailInput.setSpecificAccountCode(collateralSecurity.getSpecificAccountCode());
            // 保護区分をセット
            detailInput.setDepositType(collateralSecurity.getDepositType());
            detailInputList.add(detailInput);
        }
        collateralSecuritiesTransferInput.setTransferDetails(detailInputList);
        
        // パラメータをセット
        req.getParameter().setTransfers(Arrays.asList(collateralSecuritiesTransferInput));
     
        // API003 Response
        ConfirmCollateralSecuritiesTransferResp result = new ConfirmCollateralSecuritiesTransferResp();
        result = foreignAccountService.confirmCollateralSecuritiesTransfer(req);
        
        return result;
    }
    
    /**
     * API003の結果をA003のActionレスポンスDTOに格納する
     *
     * @param res レスポンスDTO
     * @param api003Res API003の出力結果
     */
    private void setApi003ResultAsA003(IfaForeignMarginCollateralTransferInputA003DtoResponse res,
            ConfirmCollateralSecuritiesTransferResp api003Res) {
        
        // 現在の余力情報
        IfaForeignMarginCollateralTransferPowerInfo currentPower = new IfaForeignMarginCollateralTransferPowerInfo();
        currentPower.setMarginBuyingPower(api003Res.getCurrentPower().getMarginBuyingPower());
        currentPower.setDepositRate(api003Res.getCurrentPower().getDepositRate());
        currentPower.setTotalCollateralValue(api003Res.getCurrentPower().getTotalCollateralValue());
        res.setCurrentPower(currentPower);
        
        // 受付後余力情報
        IfaForeignMarginCollateralTransferPowerInfo afterPower = new IfaForeignMarginCollateralTransferPowerInfo();
        afterPower.setMarginBuyingPower(api003Res.getAfterPower().getMarginBuyingPower());
        afterPower.setDepositRate(api003Res.getAfterPower().getDepositRate());
        afterPower.setTotalCollateralValue(api003Res.getAfterPower().getTotalCollateralValue());
        res.setAfterPower(afterPower);
        
    }
    
    /**
     * API003の結果をA004のActionレスポンスDTOに格納する
     *
     * @param res レスポンスDTO
     * @param api003Res API003の出力結果
     */
    private void setApi003ResultAsA004(IfaForeignMarginCollateralTransferInputA004DtoResponse res,
            ConfirmCollateralSecuritiesTransferResp api003Res) {
        
        // 代用有価証券振替情報
        List<IfaForeignMarginCollateralTransferInputCollateralSecurity> collateralSecurityList = 
                new ArrayList<IfaForeignMarginCollateralTransferInputCollateralSecurity>();
        for (CollateralSecuritiesTransfer transfer : api003Res.getTransfers()) {
            IfaForeignMarginCollateralTransferInputCollateralSecurity collateralSecurity = 
                    new IfaForeignMarginCollateralTransferInputCollateralSecurity();
            collateralSecurity.setCollateralDepositListBrandCode(transfer.getSecurities().getSecuritiesCode());
            collateralSecurity.setCollateralDepositListBrandName(transfer.getSecurities().getSecuritiesName());
            collateralSecurity.setSpecificAccountCode(transfer.getSpecificAccountCode());
            collateralSecurity.setDepositType(transfer.getDepositType());
            collateralSecurity.setValuatePrice(transfer.getEvaluationPrice());
            collateralSecurity.setCollateralAssessment(transfer.getAssessmentRate());
            collateralSecurity.setCollateralValuation(transfer.getEvaluationAmount());
            collateralSecurity.setTransferQuantity(transfer.getSecuritiesQuantity());
            collateralSecurity.setNotReceivedQuantity(transfer.getUnPaymentQuantity());
            collateralSecurityList.add(collateralSecurity);
        }
        res.setCollateralSecurityTransferInfo(collateralSecurityList);
        
        // 現在の余力情報
        IfaForeignMarginCollateralTransferPowerInfo currentPower = new IfaForeignMarginCollateralTransferPowerInfo();
        currentPower.setMarginBuyingPower(api003Res.getCurrentPower().getMarginBuyingPower());
        currentPower.setDepositRate(api003Res.getCurrentPower().getDepositRate());
        currentPower.setTotalCollateralValue(api003Res.getCurrentPower().getTotalCollateralValue());
        res.setCurrentPower(currentPower);
        
        // 受付後余力情報
        IfaForeignMarginCollateralTransferPowerInfo afterPower = new IfaForeignMarginCollateralTransferPowerInfo();
        afterPower.setMarginBuyingPower(api003Res.getAfterPower().getMarginBuyingPower());
        afterPower.setDepositRate(api003Res.getAfterPower().getDepositRate());
        afterPower.setTotalCollateralValue(api003Res.getAfterPower().getTotalCollateralValue());
        res.setAfterPower(afterPower);
        
    }
    
    /**
     * API004呼び出し処理
     *
     * @param cc 顧客共通情報
     * @return API出力結果
     * @throws Exception 例外
     */
    private GetForeignStockLendingSubscribedStatusResp callApi004(CustomerCommon cc) throws Exception {
        
        //API004 Response
        GetForeignStockLendingSubscribedStatusResp result = new GetForeignStockLendingSubscribedStatusResp();
        
        result = foreignStockService.getForeignStockLendingSubscribedStatus(cc.getButenCode(), cc.getAccountNumber());
        
        return result;
    }
    
    /**
     * 対象預りが店頭取引で売却注文されているかチェック:SQL001
     *
     * @param collateralTransferList 代用有価証券振替情報リスト
     * @param cc 顧客共通情報
     * @return エラー情報
     * @throws Exception 例外
     */
    private String[] checkSql001(List<IfaForeignMarginCollateralTransferInputInfo> collateralTransferList,
            CustomerCommon cc) throws Exception {
        
        String errorCode = StringUtil.EMPTY_STRING;
        String errorMessage = StringUtil.EMPTY_STRING;
        
        IfaForeignMarginCollateralTransferInputSql001RequestModel selSql001Req = 
                new IfaForeignMarginCollateralTransferInputSql001RequestModel();
        selSql001Req.setButenCode(cc.getButenCode());
        selSql001Req.setAccountNumber(cc.getAccountNumber());
        
        DataList<IfaForeignMarginCollateralTransferInputSql001ResponseModel> selSql001Res = dao
                .selectIfaForeignMarginCollateralTransferInputSql001(selSql001Req);
        
        // SQL結果とリクエストの預り情報（銘柄コード、預り区分）を照合する
        if (!CollectionUtils.isEmpty(selSql001Res.getDataList())) {
            String placeholder = StringUtil.EMPTY_STRING;
            for (IfaForeignMarginCollateralTransferInputSql001ResponseModel sql001ResData : selSql001Res
                    .getDataList()) {
                for (IfaForeignMarginCollateralTransferInputInfo reqData : collateralTransferList) {
                    // 預り情報（銘柄コード、預り区分）が一致した場合、エラーメッセージを作成
                    if (StringUtils.equals(reqData.getCollateralDepositListBrandCode(), sql001ResData.getBrandCode())
                            && StringUtils.equals(reqData.getSpecificAccountCode(), sql001ResData.getDepositType())) {
                        placeholder += "<br>銘柄：[" + reqData.getCollateralDepositListBrandCode() 
                                + "]、預り区分：[" + codeListService.getValue(
                                        FOREIGN_DEPOSIT_TYPE, reqData.getSpecificAccountCode()) + "]";
                    }
                }
            }
            
            // 預り情報の一致がある場合はエラー情報を返却
            if (!ObjectUtils.isEmpty(placeholder)) {
                errorCode = ERRORS_FRS_COUNTERORDER_EXIST;
                errorMessage = IfaCommonUtil.getMessage(errorCode, new String[] { placeholder });
                return new String[] { errorCode, errorMessage};  
            }
        }
        
        return null;
    }
}
