package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateMarginAccountAutoTransferSettingResp;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignMarginAutoTransferSettingConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingConfirmSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingConfirmSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto_ApiRes;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.model.ApiStatusModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignMarginAutoTransferSettingConfirmService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.AmericaStockUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignMarginAccountType;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.MarginBuyingPowerShortfallCashCheck;
import com.sbisec.helios.ap.common.enums.MarginBuyingPowerShortfallSecuritiesCheck;
import com.sbisec.helios.ap.common.enums.MarginShortfallCashCheck;
import com.sbisec.helios.ap.common.enums.MarginShortfallSecuritiesCheck;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID：SUB0202_0306-01_2
 * 画面名：米株信用自動振替設定確認
 * @author 卞
 *
 * 2023/11/10 新規作成
 */
@Component(value = "cmpIfaForeignMarginAutoTransferSettingConfirmService")
public class IfaForeignMarginAutoTransferSettingConfirmServiceImpL
        implements IfaForeignMarginAutoTransferSettingConfirmService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaForeignMarginAutoTransferSettingConfirmServiceImpL.class);
    
    /**
     * FCT001 利用者顧客参照権限チェック
     */
    @Autowired
    private Fct001 fct001;
    
    /**
     * FCT003 取引コース媒介可否チェック
     */
    @Autowired
    private Fct003 fct003;
    
    /**
     * 区分値取得クラス
     */
    @Autowired
    private CodeListService codeListService;
    
    /**
     * DAO
     */
    @Autowired
    private IfaForeignMarginAutoTransferSettingConfirmDao dao;
    
    /**
     * API呼び出しクラス(Athena)
     */
    @Autowired
    private ForeignStockService foreignStockService;
    
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
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 外国株式口座が未開設です。 */
    private static final String ERRORS_FOREIGNSTOCKACCOUNTCHECK = "errors.foreignStockAccountCheck";
    
    /** 米株信用口座が未開設です。 */
    private static final String ERRORS_FRS_FOREIGNSTOCKACCOUNT_NOTOPEN_NO1 = "errors.frs.foreignStockAccount.notOpen#1";
    
    /** {0}があるため、「信用新規建(新規買・新規売)の注文時の自動振替設定」の「{1}」自動振替設定をチェックできません。設定変更は、{0}がない翌営業日以降に実施してください。 
     * 取得件数が1件以上の場合(SQL001) : {0}：店頭取引注文 {1}：米国株式（保護預り）
     * 当日の店頭取引買付注文が1件以上　かつ　外債買付代金入力の件数が０件の場合(SQL002) : {0}："店頭取引注文" {1}："米ドル預り金"
     * 当日の店頭取引買付注文が0件　かつ　外債買付代金入力の件数が1件以上の場合(SQL002) : {0}："外債買付代金" {1}："米ドル預り金"
     * 当日の店頭取引買付注文と外債買付代金入力の件数がともに1件以上の場合(SQL002) : {0}："店頭取引注文および外債買付代金" {1}："米ドル預り金"
     */
    private static final String ERRORS_FRS_COUNTERORFOREIGNBONDORDERTODAY_EXIST = "errors.frs.counterOrForeignBondOrderToday.exist";
    
    /** 設定指示前の指示データが登録できないため、設定指示しませんでした。 */
    private static final String ERRORS_FRS_PREAUTOTRANSFERSETTING_FAILED = "errors.frs.preAutoTransferSetting.failed";
    
    /** 設定指示後の指示データが更新できませんでした。設定指示は完了しています。 */
    private static final String WARNINGS_FRS_POSTAUTOTRANSFERSETTING_COMPLETED = "warnings.frs.postAutoTransferSetting.completed";
    
    /** 注文処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1})  */
    private static final String ERRORS_CMN_ORDEREXECUTION_FAILED = "errors.cmn.orderExecution.failed";
    
    // --------------------------------
    // 設定値
    // --------------------------------
    /** FCT003：証券金銭種別 "外国株式" */
    private static final String FOREIGN_STOCK = "15";
    
    /** FCT003：取引種別 "13"(自動振替設定) */
    private static final String TRADE_CLASS = "13";
    
    /** FCT003：国籍コード "99" */
    private static final String COUNTRY_CODE = "99";
    
    /** FCT003：通貨コード "USD" */
    private static final String CURRENCY_CODE = "USD";
    
    /** API設定値　HeaderRequestId=""*/
    private static final String API_HEADER_REQUESTID = "";
    
    /** 区分定義.ドメインID_対象取引 */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 対象取引 区分値：5 */
    private static final String MSG_DISPLAY_TARGET_TRADE_KUBUN = "5";
    
    /** APIステータス:正常 */
    private static final String API_STATUS_OK = "200";
    
    /** 正常終了メッセージコード */
    private static final String SUCCESS_MESSAGE_CODE = "SUCCESS";
    
    /** エラーメッセージ(ERRORS_FRS_COUNTERORFOREIGNBONDORDERTODAY_EXIST) {0}:"店頭取引注文" */
    private static final String OVER_COUNTER_ORDER = "店頭取引注文";
    
    /** エラーメッセージ(ERRORS_FRS_COUNTERORFOREIGNBONDORDERTODAY_EXIST) {0}:"外債買付代金" */
    private static final String FOREIGN_DEBT_PURCHASE_PRICE = "外債買付代金";
    
    /** エラーメッセージ(ERRORS_FRS_COUNTERORFOREIGNBONDORDERTODAY_EXIST) {0}:"店頭取引注文および外債買付代金" */
    private static final String OVER_COUNTER_ORDER_AND_FOREIGN_DEBT_PURCHASE = "店頭取引注文および外債買付代金";
    
    /** エラーメッセージ(ERRORS_FRS_COUNTERORFOREIGNBONDORDERTODAY_EXIST) {1}:"米国株式（保護預り）" */
    private static final String USA_STOCK = "米国株式（保護預り）";
    
    /** エラーメッセージ(ERRORS_FRS_COUNTERORFOREIGNBONDORDERTODAY_EXIST) {1}:"米ドル預り金" */
    private static final String USA_DEPOSIT = "米ドル預り金";
    
    /**
     * アクションID：A001a
     * アクション名：設定指示
     * Dto リクエスト：IfaForeignMarginAutoTransferSettingConfirmA001RequestDto
     * Dto レスポンス：IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto
     * model リクエスト：IfaForeignMarginAutoTransferSettingConfirmSql004RequestModel
     * model レスポンス：IfaForeignMarginAutoTransferSettingConfirmSql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginAutoTransferSettingConfirmA001aResponseDto> settingInstructionA001a(
            IfaForeignMarginAutoTransferSettingConfirmA001aRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaForeignMarginAutoTransferSettingConfirmServiceImplL.settingInstructionA001a");
        
        DataList<IfaForeignMarginAutoTransferSettingConfirmA001aResponseDto> dtoRes = new DataList<IfaForeignMarginAutoTransferSettingConfirmA001aResponseDto>();
        List<IfaForeignMarginAutoTransferSettingConfirmA001aResponseDto> resDto = new ArrayList<IfaForeignMarginAutoTransferSettingConfirmA001aResponseDto>();
        IfaForeignMarginAutoTransferSettingConfirmA001aResponseDto response = new IfaForeignMarginAutoTransferSettingConfirmA001aResponseDto();
        
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = new String[2];
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        //action内チェック処理
        if (!checkValidation(cc, errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        /* SQL001:当日店頭取引売却注文のチェック */
        if (!checkSQL001Count(dtoReq, cc, errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        /* SQL002:当日店頭取引買付注文および外債買付代金の入力のチェック */
        if (!checkSQL002Count(dtoReq, cc, errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        //設定指示前に指示内容をDBに登録:SQL003
        String autoTransferSettingNo = null;
        try {
            autoTransferSettingNo = insertSettingInstruction(dtoReq, cc);
        } catch (Exception e) {
            errorInfo[0] = ERRORS_FRS_PREAUTOTRANSFERSETTING_FAILED;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0]);
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        //レスポンス項目の設定
        response.setAutoTransferSettingNo(autoTransferSettingNo);
        
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                null);
        return dtoRes;
        
    }
    
    /**
     * アクションID：A001b
     * アクション名：設定指示
     * Dto リクエスト：IfaForeignMarginAutoTransferSettingConfirmA001RequestDto
     * Dto レスポンス：IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto
     * model リクエスト：IfaForeignMarginAutoTransferSettingConfirmSql004RequestModel
     * model レスポンス：IfaForeignMarginAutoTransferSettingConfirmSql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto> settingInstructionA001b(
            IfaForeignMarginAutoTransferSettingConfirmA001bRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaForeignMarginAutoTransferSettingConfirmServiceImplL.settingInstructionA001b");
        
        DataList<IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto> dtoRes = new DataList<IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto>();
        List<IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto> resDto = new ArrayList<IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto>();
        IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto response = new IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto();
        IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto_ApiRes apiRes = new IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto_ApiRes();
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        //API001:設定指示結果を格納
        CreateMarginAccountAutoTransferSettingResp api001Res = null;
        
        Boolean marginBuyingPowerShortfallCash = convBooleanValue(MarginBuyingPowerShortfallCashCheck.TRUE.getId(),
                dtoReq.getMarginBuyingPowerShortfallCash());
        Boolean marginBuyingPowerShortfallSecurities = convBooleanValue(
                MarginBuyingPowerShortfallSecuritiesCheck.TRUE.getId(),
                dtoReq.getMarginBuyingPowerShortfallSecurities());
        Boolean marginShortfallCash = convBooleanValue(MarginShortfallCashCheck.TRUE.getId(),
                dtoReq.getMarginShortfallCash());
        Boolean marginShortfallSecurities = convBooleanValue(MarginShortfallSecuritiesCheck.TRUE.getId(),
                dtoReq.getMarginShortfallSecurities());
        ApiStatusModel apiStatusModel = null;
        
        DataList<IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto> dtoResApiErr = null;
        try {
            api001Res = createMarginAccountAutoTransferSetting(cc.getButenCode(), cc.getAccountNumber(),
                    API_HEADER_REQUESTID, marginBuyingPowerShortfallCash, marginBuyingPowerShortfallSecurities,
                    marginShortfallCash, marginShortfallSecurities,
                    dtoReq.getDepositType());
            setApi001Result(apiRes, api001Res);
        } catch (AthenaBusinessException e) {
            //API業務異常時、APIのステータスを取得する
            apiStatusModel = AmericaStockUtil.getApiStatusInfo(e);
            dtoResApiErr = cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, "", null), e);
        }
        
        ErrorLevel el = ErrorLevel.SUCCESS;
        String messageId = SUCCESS_MESSAGE_CODE;
        String message = null;
        //設定指示後に指示結果をDBに反映:SQL004
        try {
            updateSettingInstruction(dtoReq, cc, dtoReq.getAutoTransferSettingNo(), apiStatusModel);
        } catch (Exception e) {
            messageId = WARNINGS_FRS_POSTAUTOTRANSFERSETTING_COMPLETED;
            message = IfaCommonUtil.getMessage(WARNINGS_FRS_POSTAUTOTRANSFERSETTING_COMPLETED);
            el = ErrorLevel.WARNING;
        }
        // API結果がエラーの場合はエラーで返す
        if (dtoResApiErr != null) {
            return dtoResApiErr;
        }
        
        /*レスポンス設定*/
        response.setApiRes(apiRes);
        
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, el, messageId, message);
        
        return dtoRes;
    }
    
    /**
     * String→Booleanへの変換処理
     * @param id
     * @param inputValue
     * @return
     */
    private Boolean convBooleanValue(String id, String inputValue) {
        
        return StringUtils.equals(id, inputValue) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    /**
     * FCT001チェック
     * @param cc 顧客共通情報
     * @param errorInfo エラー情報
     * @return チェック結果
     */
    private boolean callFCT001(CustomerCommon cc, String[] errorInfo) {
        
        InputFct001Dto input = new InputFct001Dto();
        input.setAccountNumber(cc.getAccountNumber());
        input.setButenCode(cc.getButenCode());
        
        OutputFct001Dto output = fct001.doCheck(input);
        if (output != null) {
            if (StringUtils.equals(output.getTargetCustomerRefAuthFlag(),
                    TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
                errorInfo[0] = ERRORS_BUTENACCOUNTNOTEXIST;
                errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0],
                        new String[] { cc.getButenCode(), cc.getAccountNumber() });
                return false;
            }
            if (StringUtils.equals(output.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
                errorInfo[0] = ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE;
                errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0]);
                return false;
            }
        }
        return true;
    }
    
    /**
     * FCT003チェック
     * 
     * @param cc 顧客共通情報
     * @param errorInfo エラー情報
     * @return チェック結果
     */
    private boolean callFCT003(CustomerCommon cc, String[] errorInfo) {
        
        InputFct003Dto input = new InputFct003Dto();
        input.setAccountNumber(cc.getAccountNumber());
        input.setButenCode(cc.getButenCode());
        input.setProductCd(FOREIGN_STOCK);
        input.setTradeCd(TRADE_CLASS);
        input.setCountryCd(COUNTRY_CODE);
        input.setCurrencyCode(CURRENCY_CODE);
        
        OutputFct003Dto output = fct003.doCheck(input);
        if (StringUtils.equals(MediateAbleTradeFlag.NASHI.getId(), output.getMediateAbleTradeFlag())) {
            errorInfo[0] = ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0], new String[] {
                    codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN) });
            return false;
        }
        
        return true;
    }
    
    /**
     * action内チェック処理
     * 
     * @param cc 顧客共通情報
     * @param errorInfo エラー情報
     */
    
    private boolean checkValidation(CustomerCommon cc, String[] errorInfo) {
        
        // FCT001
        if (!callFCT001(cc, errorInfo)) {
            return false;
        }
        
        // FCT003
        if (!callFCT003(cc, errorInfo)) {
            return false;
        }
        
        //外貨建口座取引開設状況のチェック
        if (StringUtils.equals(cc.getForeignStockTradeAccountOpenStatus(),
                ForeignStockTradeAccountOpenStatus.CLOSED.getId())) {
            errorInfo[0] = ERRORS_FOREIGNSTOCKACCOUNTCHECK;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0]);
            return false;
        }
        
        //外国信用口座開設状況のチェック
        if (StringUtils.equals(cc.getForeignMarginAccountType(), ForeignMarginAccountType.SPOT.getId())) {
            errorInfo[0] = ERRORS_FRS_FOREIGNSTOCKACCOUNT_NOTOPEN_NO1;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0]);
            return false;
        }
        
        return true;
    }
    
    /**
     * API001呼び出し処理
     * 
     * @param dtoReq A001リクエストDTO
     * @return API出力結果
     */
    private CreateMarginAccountAutoTransferSettingResp createMarginAccountAutoTransferSetting(String butenCode,
            String accountNumber, String requestId, Boolean marginBuyingPowerShortfallCash,
            Boolean marginBuyingPowerShortfallSecurities, Boolean marginShortfallCash,
            Boolean marginShortfallSecurities, String depositType) throws Exception {
        
        return foreignStockService.createMarginAccountAutoTransferSetting(butenCode, accountNumber, requestId,
                marginBuyingPowerShortfallCash, marginBuyingPowerShortfallSecurities, marginShortfallCash,
                marginShortfallSecurities, depositType);
    }
    
    /**
     * API001の結果をActionレスポンスDTOに格納する
     * 
     * @param res　A001_ApiResレスポンスDTO
     * @return api001res API001の出力結果
     */
    private void setApi001Result(IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto_ApiRes res,
            CreateMarginAccountAutoTransferSettingResp api001Res) {
        
        // 建余力不足 自動振替設定(米国株式)
        res.setMarginBuyingPowerShortfallSecurities(api001Res.getConvMarginBuyingPowerShortfallSecurities());
        // 建余力不足 自動振替設定(米ドル)
        res.setMarginBuyingPowerShortfallCash(api001Res.getConvMarginBuyingPowerShortfallCash());
        //保証金不足 自動振替設定(米国株式)
        res.setMarginShortfallSecurities(api001Res.getConvMarginShortfallSecurities());
        //保証金不足 自動振替設定(米ドル)
        res.setMarginShortfallCash(api001Res.getConvMarginShortfallCash());
        //現物買付時 株式自動振替先設定
        res.setDepositType(api001Res.getDepositType());

        
    }
    
    /**
     * 当日店頭取引売却注文のチェック:SQL001
     * 
     * @param dtoReq A001リクエストDTO
     * @param cc 顧客共通情報
     * @param errorInfo エラー情報
     * @throws Exception 
     */
    private boolean checkSQL001Count(IfaForeignMarginAutoTransferSettingConfirmA001aRequestDto dtoReq,
            CustomerCommon cc, String[] errorInfo) throws Exception {
        
        //リクエスト.建余力不足.米国株式がtrue（チェックON）の場合,当日の店頭取引売却注文件数を取得
        if (StringUtils.equals(dtoReq.getMarginBuyingPowerShortfallSecurities(),
                MarginBuyingPowerShortfallCashCheck.TRUE.getId())) {
            IfaForeignMarginAutoTransferSettingConfirmSql001RequestModel selSql001Req = new IfaForeignMarginAutoTransferSettingConfirmSql001RequestModel();
            selSql001Req.setButenCode(cc.getButenCode());
            selSql001Req.setAccountNumber(cc.getAccountNumber());
            
            DataList<IfaForeignMarginAutoTransferSettingConfirmSql001ResponseModel> selSql001Res = dao
                    .selectIfaForeignMarginAutoTransferSettingConfirmSql001(selSql001Req);
            if (!CollectionUtils.isEmpty(selSql001Res.getDataList())
                    && Integer.valueOf(selSql001Res.getDataList().get(0).getCount()) >= 1) {
                errorInfo[0] = ERRORS_FRS_COUNTERORFOREIGNBONDORDERTODAY_EXIST;
                errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0], new String[] { OVER_COUNTER_ORDER, USA_STOCK });
                return false;
            }
            return true;
        }
        return true;
    }
    
    /**
     * 当日店頭取引買付注文および外債買付代金の入力のチェック:SQL002
     * 
     * @param dtoReq A001リクエストDTO
     * @param cc 顧客共通情報
     * @param errorInfo エラー情報
     * @throws Exception 
     */
    private boolean checkSQL002Count(IfaForeignMarginAutoTransferSettingConfirmA001aRequestDto dtoReq,
            CustomerCommon cc, String[] errorInfo) throws Exception {
        
        // リクエスト.建余力不足.米ドルがtrue（チェックON）の場合,当日の店頭取引買付注文と外債買付代金入力の件数を取得
        if (StringUtils.equals(dtoReq.getMarginBuyingPowerShortfallCash(),
                MarginBuyingPowerShortfallSecuritiesCheck.TRUE.getId())) {
            IfaForeignMarginAutoTransferSettingConfirmSql002RequestModel selSql002Req = new IfaForeignMarginAutoTransferSettingConfirmSql002RequestModel();
            
            // SQL002_3:遡る時間を取得
            DataList<IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel> selSql002Res3 = dao
                    .selectIfaForeignMarginAutoTransferSettingConfirmSql002_3(selSql002Req);
            
            selSql002Req.setButenCode(cc.getButenCode());
            selSql002Req.setAccountNumber(cc.getAccountNumber());
            selSql002Req.setTimeInterval(CollectionUtils.isEmpty(selSql002Res3.getDataList()) ? 0
                    : Integer.valueOf(selSql002Res3.getDataList().get(0).getName()));
            
            DataList<IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel> selSql002Res1 = dao
                    .selectIfaForeignMarginAutoTransferSettingConfirmSql002_1(selSql002Req);
            DataList<IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel> selSql002Res2 = dao
                    .selectIfaForeignMarginAutoTransferSettingConfirmSql002_2(selSql002Req);
            
            int sqlRes1 = CollectionUtils.isEmpty(selSql002Res1.getDataList()) ? 0
                    : Integer.valueOf(selSql002Res1.getDataList().get(0).getCount());
            int sqlRes2 = CollectionUtils.isEmpty(selSql002Res1.getDataList()) ? 0
                    : Integer.valueOf(selSql002Res2.getDataList().get(0).getCount());
            
            if (sqlRes1 >= 1 && sqlRes2 == 0) {
                // 当日の店頭取引買付注文が1件以上　かつ　外債買付代金入力の件数が０件
                errorInfo[0] = ERRORS_FRS_COUNTERORFOREIGNBONDORDERTODAY_EXIST;
                errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0], new String[] { OVER_COUNTER_ORDER, USA_DEPOSIT });
                return false;
            } else if (sqlRes1 == 0 && sqlRes2 >= 1) {
                // 当日の店頭取引買付注文が0件　かつ　外債買付代金入力の件数が1件以上
                errorInfo[0] = ERRORS_FRS_COUNTERORFOREIGNBONDORDERTODAY_EXIST;
                errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0],
                        new String[] { FOREIGN_DEBT_PURCHASE_PRICE, USA_DEPOSIT });
                return false;
            } else if (sqlRes1 >= 1 && sqlRes2 >= 1) {
                // 当日の店頭取引買付注文と外債買付代金入力の件数がともに1件以上
                errorInfo[0] = ERRORS_FRS_COUNTERORFOREIGNBONDORDERTODAY_EXIST;
                errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0],
                        new String[] { OVER_COUNTER_ORDER_AND_FOREIGN_DEBT_PURCHASE, USA_DEPOSIT });
                return false;
            }
        }
        return true;
    }
    
    /**
     * 設定指示前に指示内容をDBに登録(SQL003の発行)
     * 
     * @param dtoReq A001リクエストDTO
     * @param cc 顧客共通情報
     * @return 
     * @throws Exception
     */
    private String insertSettingInstruction(IfaForeignMarginAutoTransferSettingConfirmA001aRequestDto dtoReq,
            CustomerCommon cc) throws Exception {
        
        IfaForeignMarginAutoTransferSettingConfirmSql003RequestModel sql003Req = new IfaForeignMarginAutoTransferSettingConfirmSql003RequestModel();
        //ユーザー共通情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        String autoTransferSettingNo = dao.selectIfaForeignMarginAutoTransferSettingConfirmSql005();
        
        //IFA自動振替設定指示番号
        sql003Req.setAutoTransferSettingNo(autoTransferSettingNo);
        //部店コード
        sql003Req.setButenCode(cc.getButenCode());
        //口座番号
        sql003Req.setAccountNumber(cc.getAccountNumber());
        //建余力不足 自動振替設定(米ドル)
        sql003Req.setMarginBuypowShortfallCash(dtoReq.getMarginBuyingPowerShortfallCash());
        //建余力不足 自動振替設定(米国株式)
        sql003Req.setMarginBuypowShortfallSec(dtoReq.getMarginBuyingPowerShortfallSecurities());
        //保証金不足 自動振替設定(米ドル)
        sql003Req.setMarginShortfallCash(dtoReq.getMarginShortfallCash());
        //保証金不足 自動振替設定(米国株式)
        sql003Req.setMarginShortfallSec(dtoReq.getMarginShortfallSecurities());
        //現物買付時 株式自動振替先設定
        sql003Req.setDepositType(dtoReq.getDepositType());
        //作成者
        sql003Req.setCreateUser(ua.getUserId());
        //更新者
        sql003Req.setUpdateUser(ua.getUserId());
        
        if (1 != dao.insertIfaForeignMarginAutoTransferSettingConfirmSql003(sql003Req)) {
            if (LOGGER.isDebugEnabled())
                LOGGER.debug("===insert Error===");
            
            throw new Exception();
        }
        
        return autoTransferSettingNo;
        
    }
    
    /**
     * 設定指示後に指示結果をDBに反映(SQL004の発行)
     * 
     * @param dtoReq A001リクエストDTO
     * @param cc 顧客共通情報
     * @param autoTransferSettingNo IFA自動振替設定指示番号
     * @param apiStatusModel API001のAPIステータス
     * @return 
     * @throws Exception
     */
    private void updateSettingInstruction(IfaForeignMarginAutoTransferSettingConfirmA001bRequestDto dtoReq,
            CustomerCommon cc, String autoTransferSettingNo, ApiStatusModel apiStatusModel) throws Exception {
        
        IfaForeignMarginAutoTransferSettingConfirmSql004RequestModel sql004Req = new IfaForeignMarginAutoTransferSettingConfirmSql004RequestModel();
        //ユーザー共通情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        //SQL003.IFA自動振替設定指示番号
        sql004Req.setAutoTransferSettingNo(autoTransferSettingNo);
        //APIステータスコード（数字）
        sql004Req.setApiStatusCode(API_STATUS_OK);
        //更新者
        sql004Req.setUpdateUser(ua.getUserId());
        
        if (apiStatusModel != null) {
            //APIステータスコード（数字）
            sql004Req.setApiStatusCode(apiStatusModel.getApiStatusCode().toString());
            //APIエラーコード（全角半角）
            sql004Req.setApiErrorCode(apiStatusModel.getApiErrorCode());
            //APIメッセージ（全角半角）
            sql004Req.setApiMsg(apiStatusModel.getApiMessage());
        }
        
        if (1 != dao.updateIfaForeignMarginAutoTransferSettingConfirmSql004(sql004Req)) {
            if (LOGGER.isDebugEnabled())
                LOGGER.debug("===update Error===");
            
            throw new Exception();
        }
    }
    
}
