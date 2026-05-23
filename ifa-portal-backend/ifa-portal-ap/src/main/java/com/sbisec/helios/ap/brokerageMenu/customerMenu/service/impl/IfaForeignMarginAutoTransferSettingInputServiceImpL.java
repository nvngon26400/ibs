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
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginAccountAutoTransferSettingReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginAccountAutoTransferSettingResp;
import com.sbisec.helios.ap.athena.utils.RequestUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignMarginAutoTransferSettingInputDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingInputA002ResponseDto_ApiRes;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingInputActionResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignMarginAutoTransferSettingInputService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignMarginAccountType;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.MarginBuyingPowerShortfallCashCheck;
import com.sbisec.helios.ap.common.enums.MarginBuyingPowerShortfallSecuritiesCheck;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID：SUB0202_0306-01_1
 * 画面名：米株信用自動振替設定入力
 * @author <author-name>
 *
 * 2023/11/10 新規作成
 * @param <IfaMarginNewOrderConfirmA001bRequestDto>
 * @param <IfaForeignMarginTradeRepayOrderInputA001ResponseDto>
 */
@Component(value = "cmpIfaForeignMarginAutoTransferSettingInputService")
public class IfaForeignMarginAutoTransferSettingInputServiceImpL<IfaMarginNewOrderConfirmA001bRequestDto, IfaForeignMarginTradeRepayOrderInputA001ResponseDto>
        implements IfaForeignMarginAutoTransferSettingInputService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaForeignMarginAutoTransferSettingInputServiceImpL.class);
    
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
    private IfaForeignMarginAutoTransferSettingInputDao dao;
    
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
    
    /** 媒介可否(可) */
    private static final String FCT003_MEDIATE_PROPRIETY_VALUE_1 = "1";
    
    /** 区分定義.ドメインID_対象取引 */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 対象取引 区分値：5 */
    private static final String MSG_DISPLAY_TARGET_TRADE_KUBUN = "5";
    
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
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignMarginAutoTransferSettingInputA001DtoRequest
     * Dto レスポンス：IfaForeignMarginAutoTransferSettingInputA001DtoResponse
     * model リクエスト：IfaForeignMarginAutoTransferSettingInputA001RequestModel
     * model レスポンス：IfaForeignMarginAutoTransferSettingInputA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginAutoTransferSettingInputA001ResponseDto> initializeA001(
            IfaForeignMarginAutoTransferSettingInputA001RequestDto dtoReq) throws Exception {
        
        DataList<IfaForeignMarginAutoTransferSettingInputA001ResponseDto> dtoRes = new DataList<IfaForeignMarginAutoTransferSettingInputA001ResponseDto>();
        List<IfaForeignMarginAutoTransferSettingInputA001ResponseDto> resDto = new ArrayList<IfaForeignMarginAutoTransferSettingInputA001ResponseDto>();
        IfaForeignMarginAutoTransferSettingInputA001ResponseDto response = new IfaForeignMarginAutoTransferSettingInputA001ResponseDto();
        IfaForeignMarginAutoTransferSettingInputActionResponseDto res = new IfaForeignMarginAutoTransferSettingInputActionResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaForeignMarginAutoTransferSettingInputServiceImplL.initializeA001");
        
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = new String[2];
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        //action内チェック処理
        if (!checkVaildation(cc, errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        //API001:自動振替設定情報を取得 
        GetMarginAccountAutoTransferSettingResp api001Res = null;
        try {            
            api001Res = callAPI001(cc);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        setApi001Result(res, api001Res);
        /* A001レスポンス */
        //建余力不足.米国株式
        response.setMarginBuyingPowerShortfallSecurities(res.getMarginBuyingPowerShortfallSecurities());
        //建余力不足.米国ドル
        response.setMarginBuyingPowerShortfallCash(res.getMarginBuyingPowerShortfallCash());
        //保証金不足.米国株式
        response.setMarginShortfallSecurities(res.getMarginShortfallSecurities());
        //保証金不足.米ドル
        response.setMarginShortfallCash(res.getMarginShortfallCash());
        //株式自動振替先設定.振替先
        response.setDepositType(res.getDepositType());
        
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, SUCCESS_MESSAGE_CODE, null);
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：設定確認
     * Dto リクエスト：IfaForeignMarginAutoTransferSettingInputA002DtoRequest
     * Dto レスポンス：IfaForeignMarginAutoTransferSettingInputA002DtoResponse
     * model リクエスト：IfaForeignMarginAutoTransferSettingInputSql002RequestModel
     * model レスポンス：IfaForeignMarginAutoTransferSettingInputSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginAutoTransferSettingInputA002ResponseDto> settingConfirmA002(
            IfaForeignMarginAutoTransferSettingInputA002RequestDto dtoReq) throws Exception {
        
        DataList<IfaForeignMarginAutoTransferSettingInputA002ResponseDto> dtoRes = new DataList<IfaForeignMarginAutoTransferSettingInputA002ResponseDto>();
        List<IfaForeignMarginAutoTransferSettingInputA002ResponseDto> resDto = new ArrayList<IfaForeignMarginAutoTransferSettingInputA002ResponseDto>();
        IfaForeignMarginAutoTransferSettingInputA002ResponseDto response = new IfaForeignMarginAutoTransferSettingInputA002ResponseDto();
        IfaForeignMarginAutoTransferSettingInputA002ResponseDto_ApiRes ApiRes = new IfaForeignMarginAutoTransferSettingInputA002ResponseDto_ApiRes();
        IfaForeignMarginAutoTransferSettingInputActionResponseDto res = new IfaForeignMarginAutoTransferSettingInputActionResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaForeignMarginAutoTransferSettingInputServiceImplL.settingConfirmA002");
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = new String[2];
        
        //action内チェック処理
        if (!checkVaildation(cc, errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        //API001:自動振替設定情報を取得 
        GetMarginAccountAutoTransferSettingResp api001Res = null;
        try {
            api001Res = callAPI001(cc);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        setApi001Result(res, api001Res);
        
        /* SQL001:当日店頭取引売却注文のチェック */
        if (!checkSQL001Count(dtoReq, cc, errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        /* SQL002:当日店頭取引買付注文および外債買付代金の入力のチェック */
        if (!checkSQL002Count(dtoReq, cc, errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        /* レスポンス設定 */
        //API応答
        //建余力不足 自動振替設定(米ドル)
        ApiRes.setMarginBuyingPowerShortfallCash(res.getMarginBuyingPowerShortfallCash());
        //建余力不足 自動振替設定(米国株式)
        ApiRes.setMarginBuyingPowerShortfallSecurities(res.getMarginBuyingPowerShortfallSecurities());
        //保証金不足 自動振替設定(米ドル)
        ApiRes.setMarginShortfallCash(res.getMarginShortfallCash());
        //保証金不足 自動振替設定(米国株式)
        ApiRes.setMarginShortfallSecurities(res.getMarginShortfallSecurities());
        //現物買付時 株式自動振替先設定
        ApiRes.setDepositType(res.getDepositType());
        response.setApi(ApiRes);
        
        //　リクエスト内容
        // 建余力不足.米ドル
        response.setMarginBuyingPowerShortfallCash(dtoReq.getMarginBuyingPowerShortfallCash());
        //建余力不足.米国株式
        response.setMarginBuyingPowerShortfallSecurities(dtoReq.getMarginBuyingPowerShortfallSecurities());
        //保証金不足.米ドル
        response.setMarginShortfallCash(dtoReq.getMarginShortfallCash());
        //保証金不足.米国株式
        response.setMarginShortfallSecurities(dtoReq.getMarginShortfallSecurities());
        //株式自動振替先設定.振替先
        response.setDepositType(dtoReq.getDepositType());
        
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, SUCCESS_MESSAGE_CODE, null);
        return dtoRes;
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
        
        boolean result = false;
        
        OutputFct003Dto output = fct003.doCheck(input);
        if (output == null || CollectionUtils.isEmpty(output.getMediateProprietyList())) {
            result = false;
        } else {
            // レスポンス.媒介可否リスト.取引種別が　"自動振替設定"　かつ　レスポンス.媒介可否リスト.媒介可否＝"1"（媒介可） が存在する
            result = output.getMediateProprietyList().stream()
                    .filter(m -> StringUtils.equals(FCT003_MEDIATE_PROPRIETY_VALUE_1, m.getMediatePropriety())
                            && StringUtils.equals(TRADE_CLASS, m.getTradeClass()))
                    .findFirst().isPresent();
        }
        if (!result) {
            errorInfo[0] = ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0], new String[] {
                    codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN) });
        }
        
        return result;
    }
    
    /**
     * action内チェック処理
     * 
     * @param cc 顧客共通情報
     * @param errorInfo エラー情報
     */
    
    private boolean checkVaildation(CustomerCommon cc, String[] errorInfo) {
        
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
    private GetMarginAccountAutoTransferSettingResp callAPI001(CustomerCommon cc) throws Exception {
        
        //API001 Response
        GetMarginAccountAutoTransferSettingResp result = new GetMarginAccountAutoTransferSettingResp();
        //API001 Request
        GetMarginAccountAutoTransferSettingReq req = new GetMarginAccountAutoTransferSettingReq();
        
        req.getHeader().setToken(RequestUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        
        return foreignStockService.getMarginAccountAutoTransferSetting(cc.getButenCode(), cc.getAccountNumber());
    }
    
    /**
     * API001の結果をActionレスポンスDTOに格納する
     * 
     * @param res　A001レスポンスDTO
     * @return api001res API001の出力結果
     */
    private void setApi001Result(IfaForeignMarginAutoTransferSettingInputActionResponseDto res,
            GetMarginAccountAutoTransferSettingResp api001Res) {
        
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
     * @param dtoReq A002リクエストDTO
     * @param selSql001Req
     * @param errorInfo エラー情報
     * @throws Exception 
     */
    private boolean checkSQL001Count(IfaForeignMarginAutoTransferSettingInputA002RequestDto dtoReq, CustomerCommon cc, String[] errorInfo)
            throws Exception {
        
        //リクエスト.建余力不足.米国株式がtrue（チェックON）の場合,当日の店頭取引売却注文件数を取得
        if (StringUtils.equals(dtoReq.getMarginBuyingPowerShortfallSecurities(),
                MarginBuyingPowerShortfallSecuritiesCheck.TRUE.getId())) {
            IfaForeignMarginAutoTransferSettingInputSql001RequestModel selSql001Req = new IfaForeignMarginAutoTransferSettingInputSql001RequestModel();
            selSql001Req.setButenCode(cc.getButenCode());
            selSql001Req.setAccountNumber(cc.getAccountNumber());
            DataList<IfaForeignMarginAutoTransferSettingInputSql001ResponseModel> selSql001Res = dao
                    .selectIfaForeignMarginAutoTransferSettingInputSql001(selSql001Req);
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
     * @param dtoReq A002リクエストDTO
     * @param selSql002Req 
     * @param errorInfo エラー情報
     * @throws Exception 
     */
    private boolean checkSQL002Count(IfaForeignMarginAutoTransferSettingInputA002RequestDto dtoReq, CustomerCommon cc, String[] errorInfo)
            throws Exception {
        
        // リクエスト.建余力不足.米ドルがtrue（チェックON）の場合,当日の店頭取引買付注文と外債買付代金入力の件数を取得
        if (StringUtils.equals(dtoReq.getMarginBuyingPowerShortfallCash(),
                MarginBuyingPowerShortfallCashCheck.TRUE.getId())) {
            // SQL003:遡る時間を取得
            IfaForeignMarginAutoTransferSettingInputSql003RequestModel selSql003Req = new IfaForeignMarginAutoTransferSettingInputSql003RequestModel();
            DataList<IfaForeignMarginAutoTransferSettingInputSql003ResponseModel> selSql003Res = dao
                    .selectIfaForeignMarginAutoTransferSettingInputSql003(selSql003Req);
            
            IfaForeignMarginAutoTransferSettingInputSql002RequestModel selSql002Req = new IfaForeignMarginAutoTransferSettingInputSql002RequestModel();
            selSql002Req.setButenCode(cc.getButenCode());
            selSql002Req.setAccountNumber(cc.getAccountNumber());
            selSql002Req.setTimeInterval(CollectionUtils.isEmpty(selSql003Res.getDataList()) ? 0
                    : Integer.valueOf(selSql003Res.getDataList().get(0).getName()));
            DataList<IfaForeignMarginAutoTransferSettingInputSql002ResponseModel> selSql002Res1 = dao
                    .selectIfaForeignMarginAutoTransferSettingInputSql002_1(selSql002Req);
            DataList<IfaForeignMarginAutoTransferSettingInputSql002ResponseModel> selSql002Res2 = dao
                    .selectIfaForeignMarginAutoTransferSettingInputSql002_2(selSql002Req);
            
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
            return true;
        }
        return true;
    }
    
}
