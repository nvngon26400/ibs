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
import com.sbisec.helios.ap.athena.protocol.account.CreateCollateralSecuritiesTransferReq;
import com.sbisec.helios.ap.athena.protocol.account.CreateCollateralSecuritiesTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.dto.CollateralSecuritiesTransferDetailInput;
import com.sbisec.helios.ap.athena.protocol.account.dto.CollateralSecuritiesTransferInput;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignMarginCollateralTransferConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginCollateralTransferConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginCollateralTransferConfirmSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginCollateralTransferConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginCollateralTransferConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferConfirmA001aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferConfirmA001bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferConfirmA001aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferConfirmA001bDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferConfirmInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferPowerInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.model.ApiStatusModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignMarginCollateralTransferConfirmService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.AmericaStockUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignMarginAccountType;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID：SUB0202_0305-01_2
 * 画面名：米株信用代用振替確認
 *
 * @author SCSK川崎
 *
   2024/03/19 新規作成
 */
@Component(value = "cmpIfaForeignMarginCollateralTransferConfirmService")
public class IfaForeignMarginCollateralTransferConfirmServiceImpL
        implements IfaForeignMarginCollateralTransferConfirmService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaForeignMarginCollateralTransferConfirmServiceImpL.class);
    
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
    private IfaForeignMarginCollateralTransferConfirmDao dao;
    
    /** API呼び出しクラス(Athena) */
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
    
    /** 当該預りは店頭取引で売却注文が行われているため、振替できません。<br>銘柄：[{0}]、預り区分：[{1}] */
    private static final String ERRORS_FRS_COUNTERORDER_EXIST = "errors.frs.counterOrder.exist";
    
    /** ただいまの時間は取引時間外のため振替できません。 */
    private static final String ERRORS_FRS_TRANSFERSERVICEHOURS_OUTOFRANGE = 
            "errors.frs.transferServiceHours.outOfRange";
    
    /** 振替指示前の指示データが登録できないため、振替指示しませんでした。  */
    private static final String ERRORS_FRS_PRETRANSFERORDER_FAILED = "errors.frs.preTransferOrder.failed";
    
    /** 振替指示後の指示データが更新できませんでした。振替指示は完了しています。  */
    private static final String WARNINGS_FRS_POSTORDERTRANSFER_COMPLETED = "warnings.frs.postOrderTransfer.completed";
    
    /** 注文処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1})  */
    private static final String ERRORS_CMN_ORDEREXECUTION_FAILED = "errors.cmn.orderExecution.failed";
    
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
    
    /** 空文字 */
    private static final String BLANK = "";
    
    /** 商品コード "外国株式" */
    private static final String PRODUCT_CODE = "FOREIGN_STOCK";
    
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
    
    /** 代用振替区分（0:代用預り→保護預り） */
    private static final String STOCK_TRANSFER_TYPE_ZERO = "0";
    
    /** 代用振替区分（1:保護預り→代用預り） */
    private static final String STOCK_TRANSFER_TYPE_ONE = "1";
    
    /** 正常終了メッセージコード */
    private static final String SUCCESS_MESSAGE_CODE = "SUCCESS";
    
    /** APIステータス:正常 */
    private static final String API_STATUS_OK = "200";
    
    /**
     * アクションID：A001a
     * アクション名：振替指示
     * Dto リクエスト：IfaForeignMarginCollateralTransferConfirmA001aDtoRequest
     * Dto レスポンス：IfaForeignMarginCollateralTransferConfirmA001aDtoResponse
     * model リクエスト：IfaForeignMarginCollateralTransferConfirmSql001RequestModel
     * model レスポンス：IfaForeignMarginCollateralTransferConfirmSql001ResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignMarginCollateralTransferConfirmA001aDtoResponse> transferInstructionA001a(
            IfaForeignMarginCollateralTransferConfirmA001aDtoRequest dtoReq) throws Exception {
        
        DataList<IfaForeignMarginCollateralTransferConfirmA001aDtoResponse> dtoRes = 
                new DataList<IfaForeignMarginCollateralTransferConfirmA001aDtoResponse>();
        List<IfaForeignMarginCollateralTransferConfirmA001aDtoResponse> resDto = 
                new ArrayList<IfaForeignMarginCollateralTransferConfirmA001aDtoResponse>();
        IfaForeignMarginCollateralTransferConfirmA001aDtoResponse res = 
                new IfaForeignMarginCollateralTransferConfirmA001aDtoResponse();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginCollateralTransferConfirmServiceImplL.transferInstructionA001a");
        }
        
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = null;
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // action内チェック処理
        errorInfo = checkValidation(cc, dtoReq.getTransferClassification());
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
        
        // SQL002:振替指示前に指示内容をDBに登録
        String ifaStockTransferNo = null;
        try {
            ifaStockTransferNo = insertStockTransfer(dtoReq, cc);
        } catch (Exception e) {
            // DB登録NGの場合
            String errorCode = ERRORS_FRS_PRETRANSFERORDER_FAILED;
            String errorMessage = IfaCommonUtil.getMessage(errorCode);
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        
        // IFA代用振替指示番号をセット
        res.setStockTransferNo(ifaStockTransferNo);
        
        resDto.add(res);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, SUCCESS_MESSAGE_CODE, null);
        return dtoRes;
        
    }
    
    /**
     * アクションID：A001b
     * アクション名：振替指示
     * Dto リクエスト：IfaForeignMarginCollateralTransferConfirmA001bDtoRequest
     * Dto レスポンス：IfaForeignMarginCollateralTransferConfirmA001bDtoResponse
     * model リクエスト：IfaForeignMarginCollateralTransferConfirmSql001RequestModel
     * model レスポンス：IfaForeignMarginCollateralTransferConfirmSql001ResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignMarginCollateralTransferConfirmA001bDtoResponse> transferInstructionA001b(
            IfaForeignMarginCollateralTransferConfirmA001bDtoRequest dtoReq) throws Exception {
        
        DataList<IfaForeignMarginCollateralTransferConfirmA001bDtoResponse> dtoRes = 
                new DataList<IfaForeignMarginCollateralTransferConfirmA001bDtoResponse>();
        List<IfaForeignMarginCollateralTransferConfirmA001bDtoResponse> resDto = 
                new ArrayList<IfaForeignMarginCollateralTransferConfirmA001bDtoResponse>();
        IfaForeignMarginCollateralTransferConfirmA001bDtoResponse res = 
                new IfaForeignMarginCollateralTransferConfirmA001bDtoResponse();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginCollateralTransferConfirmServiceImplL.transferInstructionA001b");
        }
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
    
        // API001:振替指示を実行
        ApiStatusModel apiStatusModel = null;
        DataList<IfaForeignMarginCollateralTransferConfirmA001bDtoResponse> dtoResApiErr = null;
        try {
            CreateCollateralSecuritiesTransferResp api001Res = 
                    callApi001(dtoReq.getTransferClassification(), dtoReq.getCollateralSecurityTransferInfoList(), cc);
            setApi001Result(res, api001Res);
        } catch (AthenaBusinessException e) {
            // APIエラーの場合、エラー情報をapiStatusModelに保存
            if (LOGGER.isDebugEnabled()) {
                LOGGER.error("IfaForeignMarginCollateralTransferConfirmServiceImpL.transferInstructionA001b callAPI001",
                        e);
            }
            apiStatusModel = AmericaStockUtil.getApiStatusInfo(e);
            dtoResApiErr = cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, "", null), e);
        }
        
        // SQL003:振替指示後に指示結果をDBに反映
        ErrorLevel el = ErrorLevel.SUCCESS;
        String messageId = SUCCESS_MESSAGE_CODE;
        String message = null;
        try {
            updateStockTransfer(dtoReq.getStockTransferNo(), dtoReq.getCollateralSecurityTransferInfoList().size(), 
                    apiStatusModel);
        } catch (Exception e) {
            // DB更新NGの場合
            messageId = WARNINGS_FRS_POSTORDERTRANSFER_COMPLETED;
            message = IfaCommonUtil.getMessage(messageId);
            el = ErrorLevel.WARNING;
        }

        // API001がエラーの場合
        if (dtoResApiErr != null) {
            return dtoResApiErr;
        }
        
        resDto.add(res);
        dtoRes = IfaCommonUtil.createDataList(resDto, el, messageId, message);
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
                return new String[] { errorCode, errorMessage};
            }
            if (StringUtils.equals(output.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
                // 取引停止口座の場合
                errorCode = ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE;
                errorMessage = IfaCommonUtil.getMessage(errorCode);
                return new String[] { errorCode, errorMessage};
            }
        }
        return null;
    }
    
    /**
     * FCT003チェック
     *
     * @param cc 顧客共通情報
     * @param collateralSecurityType 代用有価証券振替区分
     * @return エラー情報
     */
    private String[] callFct003(CustomerCommon cc, String collateralSecurityType) {
        
        String errorCode = StringUtil.EMPTY_STRING;
        String errorMessage = StringUtil.EMPTY_STRING;
        
        InputFct003Dto input = new InputFct003Dto();
        input.setAccountNumber(cc.getAccountNumber());
        input.setButenCode(cc.getButenCode());
        input.setProductCd(FOREIGN_STOCK);
        
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
            return new String[] { errorCode, errorMessage};
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
                return new String[] { errorCode, errorMessage};
            }
        }
        
        return null;
    }
    
    /**
     * action内チェック処理
     *
     * @param cc 顧客共通情報
     * @param collateralSecurityType 代用有価証券振替区分
     * @return エラー情報
     */
    
    private String[] checkValidation(CustomerCommon cc, String collateralSecurityType) {
        
        String errorCode = StringUtil.EMPTY_STRING;
        String errorMessage = StringUtil.EMPTY_STRING;
        String[] errorInfo = null;
        
        // FCT018
        errorInfo = callFct018();
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return errorInfo;
        }
        
        // FCT001
        errorInfo = callFct001(cc);
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return errorInfo;
        }
        
        // FCT003
        errorInfo = callFct003(cc, collateralSecurityType);
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return errorInfo;
        }
        
        //外国株式口座取引開設状況のチェック
        if (StringUtils.equals(cc.getForeignStockTradeAccountOpenStatus(),
                ForeignStockTradeAccountOpenStatus.CLOSED.getId())) {
            errorCode = ERRORS_FOREIGNSTOCKACCOUNTCHECK;
            errorMessage = IfaCommonUtil.getMessage(errorCode);
            return new String[] { errorCode, errorMessage};
        }
        
        //外国信用口座開設状況のチェック
        if (StringUtils.equals(cc.getForeignMarginAccountType(), ForeignMarginAccountType.SPOT.getId())) {
            errorCode = ERRORS_FRS_FOREIGNSTOCKACCOUNT_NOTOPEN_NO1;
            errorMessage = IfaCommonUtil.getMessage(errorCode);
            return new String[] { errorCode, errorMessage};
        }
        
        return null;
    }
    
    /**
     * API001呼び出し処理
     *
     * @param collateralSecurityType 代用有価証券振替区分
     * @param collateralTransferList 代用有価証券振替情報リスト
     * @param cc 顧客共通情報
     * @return API出力結果
     * @throws Exception 例外
     */
    private CreateCollateralSecuritiesTransferResp callApi001(String collateralSecurityType,
            List<IfaForeignMarginCollateralTransferConfirmInfo> collateralTransferList, CustomerCommon cc)
                    throws Exception {
        
        // API001 Request
        CreateCollateralSecuritiesTransferReq req = new CreateCollateralSecuritiesTransferReq();
        
        // Header.tokenとしてリクエスト.部店コード + "-" + リクエスト.口座番号をセット
        req.getHeader().setToken(RequestUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        
        // request_idをセット
        req.getHeader().setRequest_id(BLANK);
        
        CollateralSecuritiesTransferInput collateralSecuritiesTransferInput = new CollateralSecuritiesTransferInput();
        // 代用有価証券振替区分をセット
        collateralSecuritiesTransferInput.setSecuritiesTransferType(collateralSecurityType);
        
        // 振替詳細情報をセット
        List<CollateralSecuritiesTransferDetailInput> detailInputList = 
                new ArrayList<CollateralSecuritiesTransferDetailInput>();
        for (IfaForeignMarginCollateralTransferConfirmInfo collateralSecurity : collateralTransferList) {
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
        
        return foreignAccountService.createCollateralSecuritiesTransfer(req);
    }
    
    /**
     * API001の結果をActionレスポンスDTOに格納する
     *
     * @param res レスポンスDTO
     * @param api001Res API001の出力結果
     */
    private void setApi001Result(IfaForeignMarginCollateralTransferConfirmA001bDtoResponse res,
            CreateCollateralSecuritiesTransferResp api001Res) {
        
        // 振替指示後余力情報
        IfaForeignMarginCollateralTransferPowerInfo afterPower = new IfaForeignMarginCollateralTransferPowerInfo();
        afterPower.setMarginBuyingPower(api001Res.getAfterPower().getMarginBuyingPower());
        afterPower.setDepositRate(api001Res.getAfterPower().getDepositRate());
        afterPower.setTotalCollateralValue(api001Res.getAfterPower().getTotalCollateralValue());
        res.setAfterPower(afterPower);
        
    }
    
    /**
     * 対象預りが店頭取引で売却注文されているかチェック:SQL001
     *
     * @param collateralTransferList 代用有価証券振替情報リスト
     * @param cc 顧客共通情報
     * @return エラー情報
     * @throws Exception 例外
     */
    private String[] checkSql001(List<IfaForeignMarginCollateralTransferConfirmInfo> collateralTransferList,
            CustomerCommon cc) throws Exception {
        
        String errorCode = StringUtil.EMPTY_STRING;
        String errorMessage = StringUtil.EMPTY_STRING;
        
        IfaForeignMarginCollateralTransferConfirmSql001RequestModel selSql001Req = 
                new IfaForeignMarginCollateralTransferConfirmSql001RequestModel();
        selSql001Req.setButenCode(cc.getButenCode());
        selSql001Req.setAccountNumber(cc.getAccountNumber());
        
        DataList<IfaForeignMarginCollateralTransferConfirmSql001ResponseModel> selSql001Res = dao
                .selectIfaForeignMarginCollateralTransferConfirmSql001(selSql001Req);
        
        // SQL結果とリクエストの預り情報（銘柄コード、預り区分）を照合する
        if (!CollectionUtils.isEmpty(selSql001Res.getDataList())) {
            String placeholder = StringUtil.EMPTY_STRING;
            for (IfaForeignMarginCollateralTransferConfirmSql001ResponseModel sql001ResData : selSql001Res
                    .getDataList()) {
                for (IfaForeignMarginCollateralTransferConfirmInfo reqData : collateralTransferList) {
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
    
    /**
     * 振替指示前に指示内容をDBに登録(SQL002の発行)
     *
     * @param dtoReq A001リクエストDTO
     * @param cc 顧客共通情報
     * @return シーケンス（IFA代用振替指示番号）
     * @throws Exception 例外
     */
    private String insertStockTransfer(IfaForeignMarginCollateralTransferConfirmA001aDtoRequest dtoReq,
            CustomerCommon cc) throws Exception {
        
        IfaForeignMarginCollateralTransferConfirmSql002RequestModel sql002Req = 
                new IfaForeignMarginCollateralTransferConfirmSql002RequestModel();
        
        // ユーザー共通情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        // IFA代用振替指示番号のシーケンスを取得
        String ifaStockTransferNo = dao.selectIfaForeignMarginCollateralTransferConfirmSql004();
        
        // 代用振替区分の変換
        String stockTransferType = null;
        if (SECURITIES_WITHDRAWAL.equals(dtoReq.getTransferClassification())) {
            stockTransferType = STOCK_TRANSFER_TYPE_ZERO;
        } else if (SECURITIES_DEPOSIT.equals(dtoReq.getTransferClassification())) {
            stockTransferType = STOCK_TRANSFER_TYPE_ONE;
        }
        
        // 銘柄分SQL002のインサートを繰り返す
        int subNo = 1;
        for (IfaForeignMarginCollateralTransferConfirmInfo req : dtoReq.getCollateralSecurityTransferInfoList()) {
            // IFA代用振替指示番号
            sql002Req.setStockTransferNo(ifaStockTransferNo);
            // IFA代用振替指示サブ番号
            sql002Req.setStockTransferSubNo(String.format("%04d", subNo));
            // 部店コード
            sql002Req.setButenCode(cc.getButenCode());
            // 口座番号
            sql002Req.setAccountNumber(cc.getAccountNumber());
            // 国コード
            sql002Req.setCountryCode(COUNTRY_CODE);
            // ティッカーコード
            sql002Req.setBrandCode(req.getCollateralDepositListBrandCode());
            // 預り区分
            sql002Req.setDepositType(req.getSpecificAccountCode());
            // 代用振替区分
            sql002Req.setStockTransferType(stockTransferType);
            // 振替数量
            sql002Req.setTransferQuantity(req.getTransferQuantity());
            // 作成者
            sql002Req.setCreateUser(ua.getUserId());
            // 更新者
            sql002Req.setUpdateUser(ua.getUserId());
            
            if (1 != dao.insertIfaForeignMarginCollateralTransferConfirmSql002(sql002Req)) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("===insert Error===");
                }
                
                throw new Exception();
            }
            
            subNo++;
        }
        
        return ifaStockTransferNo;
    }
    
    /**
     * 振替指示後に指示結果をDBに反映(SQL003の発行)
     *
     * @param ifaStockTransferNo シーケンス（IFA代用振替指示番号）
     * @param updateCount 更新対象のレコード数
     * @param apiStatusModel API001のAPIステータス
     * @throws Exception 例外
     */
    private void updateStockTransfer(String ifaStockTransferNo, int updateCount, ApiStatusModel apiStatusModel)
            throws Exception {
        
        IfaForeignMarginCollateralTransferConfirmSql003RequestModel sql003Req = 
                new IfaForeignMarginCollateralTransferConfirmSql003RequestModel();
       
        // ユーザー共通情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        // IFA自動振替設定指示番号
        sql003Req.setStockTransferNo(ifaStockTransferNo);
        // APIステータスコード（数字）
        sql003Req.setApiStatusCode(API_STATUS_OK);
        //更新者
        sql003Req.setUpdateUser(ua.getUserId());
        
        // API001がエラーの場合
        if (apiStatusModel != null) {
            //APIステータスコード（数字）
            sql003Req.setApiStatusCode(apiStatusModel.getApiStatusCode().toString());
            //APIエラーコード（全角半角）
            sql003Req.setApiErrorCode(apiStatusModel.getApiErrorCode());
            //APIメッセージ（全角半角）
            sql003Req.setApiMessage(apiStatusModel.getApiMessage());
        }
        
        if (updateCount != dao.updateIfaForeignMarginCollateralTransferConfirmSql003(sql003Req)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("===update Error===");
            }
            
            throw new Exception();
        }
    }
}
