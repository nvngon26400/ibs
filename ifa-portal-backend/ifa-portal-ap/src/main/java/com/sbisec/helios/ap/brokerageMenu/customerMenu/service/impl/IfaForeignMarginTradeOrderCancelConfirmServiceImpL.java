package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrder;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.Order;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedMarginOrderInitializationResp;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.athena.utils.RequestUtil;
import com.sbisec.helios.ap.athena.enums.MarginCloseLimitType;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct018;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct018Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct018Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignMarginTradeOrderCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA010aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA010aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA010bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmDto_BrandInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmDto_MarketInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignMarginTradeOrderCancelConfirmService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignMarginAccountType;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeClass;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.enums.PeriodConditions;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0303-03_1
 * 画面名：米株信用取引注文取消確認
 * @author 
 *
 * 2023/09/16 新規作成
 */
@Component(value = "cmpIfaForeignMarginTradeOrderCancelConfirmService")
public class IfaForeignMarginTradeOrderCancelConfirmServiceImpL
        implements IfaForeignMarginTradeOrderCancelConfirmService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaForeignMarginTradeOrderCancelConfirmServiceImpL.class);
    
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
     * FCT018 取引コース媒介可否チェック
     */
    @Autowired
    private Fct018 fct018;
    
    /**
     * 区分値取得クラス
     */
    @Autowired
    private CodeListService codeListService;
    
    /**
     * DAO
     */
    @Autowired
    private IfaForeignMarginTradeOrderCancelConfirmDao dao;
    
    /**
     * AthenaAPI 外国株式信用
     */
    @Autowired
    ForeignStockService foreignStockService;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}] */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 取引停止口座のため処理を進めることができません。 */
    private static final String ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** {0}ができないコースです。*/
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 外国株式口座が未開設です。*/
    private static final String ERRORS_FOREIGNSTOCKACCOUNTCHECK = "errors.foreignStockAccountCheck";
    
    /** 米株信用口座が未開設です。*/
    private static final String ERRORS_FRS_FOREIGNSTOCKACCOUNT_NOTOPEN1 = "errors.frs.foreignStockAccount.notOpen#1";
    
    /** ただいまの時間は取引時間外のため注文できません。*/
    private static final String ERRORS_FRS_SERVICEHOURS_OUTOFRANGE = "errors.frs.serviceHours.outOfRange";
    
    /** 米株信用取引の利用者権限がありません。*/
    private static final String ERRORS_FRS_ORDEREXECUTION_INSUFFICIENTPRIVILEGE_MARGINNTRADING = "errors.frs.orderExecution.insufficientPrivilege.MarginTrading";
    
    /** 注文発注前の注文データが登録できないため、注文しませんでした。*/
    private static final String ERRORS_FRS_PREORDEREXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    /**注文発注後の注文データが更新できませんでした。注文は完了しています。*/
    private static final String WARNING_FRS_POSTORDEREXECUTION_COMPLETED = "warnings.frs.postOrderExecution.completed";
    
    /**注文取消処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1})  {0}:APIエラーコード{1}:APIエラーメッセージ*/
    private static final String ERRORS_COM_ORDEREXECUTIONCANCEL_FAILD = "errors.cmn.orderExecutionCancel.failed";
    
    // --------------------------------
    // 設定値
    // --------------------------------
    
    /** SBI証券本店*/
    private static final String SBI_HEADOFFICE = "1";
    
    /** SBI証券支店*/
    private static final String SBI_BRANCH = "2";
    
    /** 外国株式設定値 */
    private static final String FOREIGN_DOMESTICSTOCK = "15";
    
    /** 区分定義.ドメインID_対象取引 */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 対象取引 区分値：5 */
    private static final String MSG_DISPLAY_TARGET_TRADE_KUBUN = "5";
    
    /**API002　リクエストIDの値*/
    private static final String API002_REQUEST_ID = "";
    
    /**API返却値 売買区分_買付(名称)*/
    private static final String APIRESPONSE_BUY = "3";
    
    /**API返却値 売買区分_売却(名称)*/
    private static final String APIRESPONSE_SELL = "1";
    
    /**API返却値 株取引区分_信用新規(名称)*/
    private static final String APIRESPONSE_OPEN = "MARGIN_OPEN";
    
    /**API返却値 株取引区分_信用返済(名称)*/
    private static final String APIRESPONSE_CLOSE = "MARGIN_CLOSE";
    
    /**FCT018国コード */
    private static final String FCT018_COUNTRYCODE = "US";
    
    /**判定結果：NG*/
    private static final String RESULT_NG = "NG";
    
    /**IFA注文サブ番号のデフォルトの値*/
    private static final String IFA_ORDER_SUB_NUMBER = "1";
    
    /**SQL信用新規の番号*/
    private static final String MARGIN_NEW_NUMBER = "10";
    
    /**SQL信用返済の番号*/
    private static final String MARGIN_REPAY_NUMBER = "11";
    
    /**APIから時刻を取得できなかった場合の注文日の設定値*/
    private static final String DEFAULT_ORDER_DATE = "19000101";
    
    /**APIから時刻を取得できなかった場合の注文時間の設定値*/
    private static final String DEFAULT_ORDER_TIME = "1900/01/01 00:00:00";
    
    /**現行踏襲箇所　SQL001のSELECTで未取得だった場合の値*/
    private static final String ZERO_PARAM = null;
    
    /**現行踏襲箇所 APIのHTTPコード*/
    /** Request Timeout */
    public static final int HTTP_408_CODE = 408;
    
    /** OK */
    public static final int HTTP_200_CODE = 200;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignMarginTradeOrderCancelConfirmA001DtoRequest
     * Dto レスポンス：IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse
     * model リクエスト：IfaForeignMarginTradeOrderCancelConfirmSql001RequestModel
     * model レスポンス：IfaForeignMarginTradeOrderCancelConfirmSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse> initializeA001(
            IfaForeignMarginTradeOrderCancelConfirmA001DtoRequest dtoReq) throws Exception {
        
        DataList<IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse> dtoRes = new DataList<IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse>();
        List<IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse> resDto = new ArrayList<IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse>();
        IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse response = new IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse();
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaForeignMarginTradeOrderCancelConfirmServiceImplL.initializeA001");
        
        // エラーコード初期化
        String errorCode = null;
        String errorMessage =  null;

        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        //利用者の口座に対する権限チェックを行う。
        // FCT001
        errorCode = callFCT001(cc.getButenCode(), cc.getAccountNumber());
        if (ERRORS_BUTENACCOUNTNOTEXIST.equals(errorCode)) {
            errorMessage = IfaCommonUtil.getMessage(errorCode, new String[] { cc.getButenCode(), cc.getAccountNumber() });
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        if (ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE.equals(errorCode)) {
            errorMessage = IfaCommonUtil.getMessage(errorCode);
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        
        //口座情報のチェック
        errorCode = chackAccount(cc);
        if (errorCode != null) {
            errorMessage = IfaCommonUtil.getMessage(errorCode);
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        
        //注文取消初期情報(注文内容)を取得する。
        String stockTradeType = null;
        String buySellCode = null;
        //API001を呼ぶ
        GetForeignStockDeletedMarginOrderInitializationResp api001Res = null;
        try {
            api001Res = getForeignStockDeletedMarginOrderInitialization(dtoReq.getOrderSubNumber());
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        if (api001Res != null) {
            if (api001Res.getMarginOrder() != null) {
                if (api001Res.getMarginOrder().getOrder() != null) {
                    stockTradeType = api001Res.getMarginOrder().getOrder().getStockTradeType();
                    buySellCode = api001Res.getMarginOrder().getOrder().getBuySellCode();
                }
            }
        }
        
        //株取引区分と売買区分から取引種別（区分.取引種別（外国株式））と信用期日（区分.信用期日）を算出する
        //株取引区分="MARGIN_OPEN"（信用新規）かつ 売買区分="BUY"（買付） の場合：　取引種別:2（新規買）, 信用期日:0（無期限）
        if (StringUtils.equals(stockTradeType, APIRESPONSE_OPEN)
                && StringUtils.equals(buySellCode, APIRESPONSE_BUY)) {
            // 取引種別
            response.setTradeCd(ForeignStockTradeClass.MARGIN_NEW_BUY.getId());
            // 信用期日
            response.setMarginDueDate(MarginCloseLimitType.NO_LIMIT.getIfaCd());
        }
        //株取引区分＝"MARGIN_OPEN"（信用新規）かつ 売買区分＝"SELL"（売却） の場合：　取引種別:3（新規売）, 信用期日:1（6ヶ月）
        else if (StringUtils.equals(stockTradeType, APIRESPONSE_OPEN)
                && StringUtils.equals(buySellCode, APIRESPONSE_SELL)) {
            // 取引種別
            response.setTradeCd(ForeignStockTradeClass.MARGIN_NEW_SELL.getId());
            // 信用期日
            response.setMarginDueDate(MarginCloseLimitType.SIX_MONTHS.getIfaCd());
        }
        //株取引区分＝"MARGIN_CLOSE"（信用返済）かつ 売買区分＝"BUY"（買付）の場合： 取引種別:4（返済買）, 信用期日:1（6ヶ月）
        else if (StringUtils.equals(stockTradeType, APIRESPONSE_CLOSE)
                && StringUtils.equals(buySellCode, APIRESPONSE_BUY)) {
            // 取引種別
            response.setTradeCd(ForeignStockTradeClass.MARGIN_REPAY_BUY.getId());
            // 信用期日
            response.setMarginDueDate(MarginCloseLimitType.SIX_MONTHS.getIfaCd());
        }
        //株取引区分＝"MARGIN_CLOSE"（信用返済）かつ 売買区分＝"SELL"（売却）の場合： 取引種別:5（返済売）, 信用期日:0（無期限）
        else if (StringUtils.equals(stockTradeType, APIRESPONSE_CLOSE)
                && StringUtils.equals(buySellCode, APIRESPONSE_SELL)) {
            // 取引種別
            response.setTradeCd(ForeignStockTradeClass.MARGIN_REPAY_SELL.getId());
            // 信用期日
            response.setMarginDueDate(MarginCloseLimitType.NO_LIMIT.getIfaCd());
        } else {
            //必ず上記のいずれかになるため、例外処理
            throw new IfaRuntimeException("想定外エラー");
        }
        
        //口座の契約締結前交付書面コードのチェック　および　共同募集顧客(共募顧客)のチェックを行う
        // FCT003
        errorCode = callFCT003(cc.getButenCode(), cc.getAccountNumber(), response.getTradeCd());
        if (errorCode != null) {
            errorMessage = IfaCommonUtil.getMessage(errorCode, new String[] {codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN) });
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        
        //注文取消初期情報（注文内容）を格納して次の処理へ
        //APIレスポンスの値をA001のレスポンスに格納する処理
        setApi001Response(response, api001Res);
        
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        return dtoRes;
    }
    
    /**
     * アクションID：A010
     * アクション名：注文取消
     * Dto リクエスト：IfaForeignMarginTradeOrderCancelConfirmA010aDtoRequest
     * Dto レスポンス：IfaForeignMarginTradeOrderCancelConfirmA010aDtoResponse
     * model リクエスト：IfaForeignMarginTradeOrderCancelConfirmSql002RequestModel
     * model レスポンス：IfaForeignMarginTradeOrderCancelConfirmSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginTradeOrderCancelConfirmA010aDtoResponse> orderCancellationA010a(
            IfaForeignMarginTradeOrderCancelConfirmA010aDtoRequest dtoReq) throws Exception {
        
        DataList<IfaForeignMarginTradeOrderCancelConfirmA010aDtoResponse> dtoRes = new DataList<IfaForeignMarginTradeOrderCancelConfirmA010aDtoResponse>();
        List<IfaForeignMarginTradeOrderCancelConfirmA010aDtoResponse> resDto = new ArrayList<IfaForeignMarginTradeOrderCancelConfirmA010aDtoResponse>();
        IfaForeignMarginTradeOrderCancelConfirmA010aDtoResponse response = new IfaForeignMarginTradeOrderCancelConfirmA010aDtoResponse();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaForeignMarginTradeOrderCancelConfirmServiceImplL.orderCancellationA010a");
        
        //エラーコードとメッセージの初期化
        String errorCode = null;
        String errorMessage =  null;
        
        // ユーザ共通情報の取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        //委託注文サービス時間のチェックを行う。
        // FCT018
        String countryCode = FCT018_COUNTRYCODE;
        OutputFct018Dto outputFct018 = callFCT018(countryCode);
        if (StringUtils.equals(outputFct018.getProcessResult(), RESULT_NG)) {
            errorCode = ERRORS_FRS_SERVICEHOURS_OUTOFRANGE;
            errorMessage = IfaCommonUtil.getMessage(errorCode);
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        
        //権限コード
        String privId = ua.getPrivId();
        //利用者の権限チェックを行う
        if (StringUtils.equals(privId, SBI_HEADOFFICE) || StringUtils.equals(privId, SBI_BRANCH)) {
            errorCode = ERRORS_FRS_ORDEREXECUTION_INSUFFICIENTPRIVILEGE_MARGINNTRADING;
            errorMessage = IfaCommonUtil.getMessage(errorCode);
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        
        /* 口座情報のチェック */
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        //利用者の口座に対する権限チェックを行う。
        // FCT001
        errorCode = callFCT001(cc.getButenCode(), cc.getAccountNumber());
        if (ERRORS_BUTENACCOUNTNOTEXIST.equals(errorCode)) {
            errorMessage = IfaCommonUtil.getMessage(errorCode, new String[] { cc.getButenCode(), cc.getAccountNumber() });
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        if (ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE.equals(errorCode)) {
            errorMessage = IfaCommonUtil.getMessage(errorCode);
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        
        //口座の契約締結前交付書面コードのチェック　および　共同募集顧客(共募顧客)のチェックを行う
        // FCT003
        errorCode = callFCT003(cc.getButenCode(), cc.getAccountNumber(), dtoReq.getTradeCd());
        if (errorCode != null) {
            errorMessage = IfaCommonUtil.getMessage(errorCode, new String[] {codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN) });
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        //口座情報のチェック
        errorCode = chackAccount(cc);
        if (errorCode != null) {
            errorMessage = IfaCommonUtil.getMessage(errorCode);
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        
        String ifaOrderNo = null;
        String ifaOrderSubNo = null;
        
        //取消発注前にAPI結果ステータスを未処理としてDBに反映する。
        try {
            //現行踏襲箇所
            //検索SQLでorderNoを取得できるか確認(できなければ設定したorderNoを用いる)
            DataList<IfaForeignMarginTradeOrderCancelConfirmSql003ResponseModel> sql003Res = getIfaOrderNumber(
                    dtoReq.getOrderNumber());
            if (sql003Res.getDataList().size() != 0) {
                ifaOrderNo = sql003Res.getDataList().get(0).getIfaOrderNo();
                int nextIfaOrderSubNo = 1 + Integer.parseInt(sql003Res.getDataList().get(0).getIfaOrderSubNo());
                ifaOrderSubNo = String.valueOf(nextIfaOrderSubNo);
            } else {
                //シーケンスオブジェクトから連番を取得
                DataList<IfaForeignMarginTradeOrderCancelConfirmSql004ResponseModel> sql004Res = getOrderNo();
                ifaOrderNo = sql004Res.getDataList().get(0).getIfaOrderNo();
                ifaOrderSubNo = IFA_ORDER_SUB_NUMBER;
            }
            
            //SQL001
            orderRegister(dtoReq, ifaOrderNo, ifaOrderSubNo, sql003Res);
            
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
            LOGGER.debug(e.toString());
            errorCode = ERRORS_FRS_PREORDEREXECUTION_FAILED;
            errorMessage = IfaCommonUtil.getMessage(errorCode);
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        //IFA注文番号・IFA注文番号サブ番号を格納
        response.setIfaOrderNo(ifaOrderNo);
        response.setIfaOrderSubNo(ifaOrderSubNo);
        
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        return dtoRes;
    }
    
    /**
     * アクションID：A010b
     * アクション名：注文取消
     * Dto リクエスト：IfaForeignMarginTradeOrderCancelConfirmA010bDtoRequest
     * Dto レスポンス：IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse
     * model リクエスト：IfaForeignMarginTradeOrderCancelConfirmSql002RequestModel
     * model レスポンス：IfaForeignMarginTradeOrderCancelConfirmSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    
    public DataList<IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse> orderCancellationA010b(
            IfaForeignMarginTradeOrderCancelConfirmA010bDtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaForeignMarginTradeOrderCancelConfirmServiceImplL.orderCancellationA010b");
        
        DataList<IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse> dtoRes = new DataList<IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse>();
        List<IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse> resDto = new ArrayList<IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse>();
        IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse response = new IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse();
        
        //エラーコードとメッセージの初期化
        String errorCode = ErrorLevel.SUCCESS.toString();
        String errorMessage = null;
        
        //リクエスト項目をレスポンスに設定
        setRequestParam(dtoReq, response);
        
        //注文取消初期情報(注文内容)を取得する。
        Integer apiStatusCode = null;
        String apiMessage = null;
        String apiErrorCode = null;
        DeleteForeignStockMarginOrderResp api002Res = new DeleteForeignStockMarginOrderResp();
        DataList<IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse> dtoResApiErr = null;
        try {
            //API002を呼ぶ
            api002Res = deleteForeignStockMarginOrder(dtoReq.getOrderSubNumber());
            apiStatusCode = HTTP_200_CODE;
            //値をレスポンスに格納
            setApi002Response(response, api002Res);
        } catch (Exception e) {
            //業務異常時、APIのエラーコード・メッセージ取得
            apiStatusCode = HTTP_408_CODE;
            if (e instanceof AthenaBusinessException) {
                apiStatusCode = ((AthenaBusinessException) e).getStatusCode();
                apiMessage = ((AthenaBusinessException) e).getMessage();
                apiErrorCode = ((AthenaBusinessException) e).getErrorCode();
            }
            dtoResApiErr = cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, "", null), e);
        }
        
        //取消発注後にAPIの実行結果に応じてAPI結果ステータスをDBに反映する。
        ErrorLevel errorLevel = ErrorLevel.SUCCESS;
        try {
            orderUpdate(dtoReq, api002Res, apiStatusCode, apiMessage, apiErrorCode);
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
            LOGGER.debug(e.toString());
            errorCode = WARNING_FRS_POSTORDEREXECUTION_COMPLETED;
            errorMessage = IfaCommonUtil.getMessage(errorCode);
            errorLevel = ErrorLevel.WARNING;
        }
        
        //注文発注結果がエラーの場合の処理
        if (apiStatusCode != HTTP_200_CODE) {
            return dtoResApiErr;
        }
        
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, errorLevel, errorCode, errorMessage);
        return dtoRes;
    }

    /**
     * 口座の権限などの情報を確認する(A001とA010の処理共通箇所)
     */
    private String chackAccount(CustomerCommon cc) {

        String errorCode = null;
        
        //外国株式取引口座開設状況のチェック
        if (StringUtils.equals(cc.getForeignStockTradeAccountOpenStatus(),
                ForeignStockTradeAccountOpenStatus.CLOSED.getId())) {
            errorCode = ERRORS_FOREIGNSTOCKACCOUNTCHECK;
            return errorCode;
        }
        
        //外国信用口座開設状況のチェックを行う
        if (StringUtils.equals(cc.getForeignMarginAccountType(), ForeignMarginAccountType.SPOT.getId())) {
            errorCode = ERRORS_FRS_FOREIGNSTOCKACCOUNT_NOTOPEN1;
            return errorCode;
        }
        
        return errorCode;
    }
    
    /**
     * レスポンスにリクエストの項目をセット(API002との重複項目はセットしない)
     * @param response A010のレスポンス
     */
    private void setRequestParam(IfaForeignMarginTradeOrderCancelConfirmA010bDtoRequest dtoReq,
            IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse response) {
        
        //預かり区分
        response.setDepositType(dtoReq.getDepositType());
        //決済方法
        response.setCurrencyTypeRadio(dtoReq.getCurrencyTypeRadio());
        //取引種別
        response.setTradeCd(dtoReq.getTradeCd());
        //国内約定日
        response.setDomesticTradeDate(dtoReq.getDomesticTradeDate());
        //現地約定日
        response.setForeignTradeDate(dtoReq.getForeignTradeDate());
        //信用期日
        response.setMarginDueDate(dtoReq.getMarginDueDate());
    }
    
    /**
     * API001の出力結果をA001のレスポンスに格納
     * @param response A001のレスポンス
     * @param api001Res　API001のレスポンス
     */
    private void setApi001Response(IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse response,
            GetForeignStockDeletedMarginOrderInitializationResp api001res) {
        
        //api001内のクラスを宣言
        MarginOrder api001MarginOrder = null;
        Order api001Order = null;
        //nullチェックと格納
        if (api001res != null) {
            if (api001res.getMarginOrder() != null) {
                api001MarginOrder = api001res.getMarginOrder();
                if (api001res.getMarginOrder().getOrder() != null) {
                    api001Order = api001res.getMarginOrder().getOrder();
                }
            }
        }
        
        if (api001Order != null) {
            // 注文番号（数字）. 
            response.setOrderNumber(api001Order.getOrderNo());
            // 注文Sub番号（数字）. 
            response.setOrderSubNumber(api001Order.getOrderSubNo());
            // 銘柄情報. 
            IfaForeignMarginTradeOrderCancelConfirmDto_BrandInfo brandInfo = new IfaForeignMarginTradeOrderCancelConfirmDto_BrandInfo();
            if (api001Order.getSecurities() != null) {
                brandInfo.setBrandCode(api001Order.getSecurities().getSecuritiesCode());
                brandInfo.setBrandName(api001Order.getSecurities().getSecuritiesName());
            }
            response.setBrandInfo(brandInfo);
            // 取引通貨. 
            response.setCurrencyCode(api001Order.getTradeCurrencyCode());
            // 市場情報. 
            IfaForeignMarginTradeOrderCancelConfirmDto_MarketInfo marketInfo = new IfaForeignMarginTradeOrderCancelConfirmDto_MarketInfo();
            if (api001Order.getMarket() != null) {
                marketInfo.setCountryCode(api001Order.getMarket().getCountryCode());
                marketInfo.setMarketCode(api001Order.getMarket().getMarketCode());
                marketInfo.setMarketName(api001Order.getMarket().getMarketShortName());
            }
            response.setMarketInfo(marketInfo);
            // 売買区分（全角半角）. 
            response.setTradeKbn(api001Order.getBuySellCode());
            // 注文数量. 
            response.setForeignQuantity(api001Order.getOrderQuantity());
            // 価格条件. 
            response.setOrderPriceKindList(api001Order.getOrderPriceKindCode());
            // 注文単価（数値(小数)）. 
            response.setHiddenOrderPrice(api001Order.getOrderPrice());
            // 発火条件価格. 
            response.setStopOrderPrice2(api001Order.getStopPrice());
            // 期間条件. 
            response.setPeriodRadio(api001Order.getOrderLimitCode());
            // 期間. 
            response.setPeriod(api001Order.getOrderTerm());
            // 預り区分（全角半角）. 
            response.setDepositType(api001Order.getSpecificAccountCode());
            // 決済方法（全角半角）. 
            response.setCurrencyTypeRadio(api001Order.getSettlementMethodCode());
            // 注文日時. 
            response.setOrderDate(api001Order.getOrderInputDatetime());
            // 国内約定日. 
            response.setDomesticTradeDate(api001Order.getTradeDate());
            // 現地約定日
            response.setForeignTradeDate(api001Order.getFrnTradeDate());
            
        }
        if (api001MarginOrder != null) {
            // 返済建玉指定方法（全角半角）. 
            response.setRepaymentMethodRadio(api001MarginOrder.getClosePositionKind());
            // 返済選択順序（全角半角）. 
            response.setSortOrderRadio(api001MarginOrder.getCloseSelectionSort());
        }
    }
    
    /**
     * API002の出力結果をA010のレスポンスに格納
     * @param response A010のレスポンス
     * @param api002Res　API002のレスポンス
     */
    private void setApi002Response(IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse response,
            DeleteForeignStockMarginOrderResp api002res) {
        
        //api002内のクラスを宣言
        MarginOrder api002MarginOrder = null;
        Order api002Order = null;
        //nullチェックと格納
        if (api002res != null) {
            if (api002res.getMarginOrder() != null) {
                api002MarginOrder = api002res.getMarginOrder();
                if (api002res.getMarginOrder().getOrder() != null) {
                    api002Order = api002res.getMarginOrder().getOrder();
                }
            }
        }
        if (api002Order != null) {
            // 注文番号（数字）. 
            response.setOrderNumber(api002Order.getOrderNo());
            // 注文Sub番号（数字）. 
            response.setOrderSubNumber(api002Order.getOrderSubNo());
            // 銘柄情報. 
            IfaForeignMarginTradeOrderCancelConfirmDto_BrandInfo brandInfo = new IfaForeignMarginTradeOrderCancelConfirmDto_BrandInfo();
            if (api002Order.getSecurities() != null) {
                brandInfo.setBrandCode(api002Order.getSecurities().getSecuritiesCode());
                brandInfo.setBrandName(api002Order.getSecurities().getSecuritiesName());
            }
            response.setBrandInfo(brandInfo);
            // 取引通貨. 
            response.setCurrencyCode(api002Order.getTradeCurrencyCode());
            // 市場情報. 
            IfaForeignMarginTradeOrderCancelConfirmDto_MarketInfo marketInfo = new IfaForeignMarginTradeOrderCancelConfirmDto_MarketInfo();
            if (api002Order.getMarket() != null) {
                marketInfo.setCountryCode(api002Order.getMarket().getCountryCode());
                marketInfo.setMarketCode(api002Order.getMarket().getMarketCode());
                marketInfo.setMarketName(api002Order.getMarket().getMarketShortName());
            }
            response.setMarketInfo(marketInfo);
            // 売買区分（全角半角）. 
            response.setTradeKbn(api002Order.getBuySellCode());
            // 注文数量. 
            response.setForeignQuantity(api002Order.getOrderQuantity());
            // 価格条件. 
            response.setOrderPriceKindList(api002Order.getOrderPriceKindCode());
            // 注文単価（数値(小数)）. 
            response.setHiddenOrderPrice(api002Order.getOrderPrice());
            // 発火条件価格. 
            response.setStopOrderPrice2(api002Order.getStopPrice());
            // 期間条件. 
            response.setPeriodRadio(api002Order.getOrderLimitCode());
            // 期間. 
            response.setPeriod(api002Order.getOrderTerm());
            // 預り区分（全角半角）. 
            response.setDepositType(api002Order.getSpecificAccountCode());
            // 決済方法（全角半角）. 
            response.setCurrencyTypeRadio(api002Order.getSettlementMethodCode());
            // 注文日時. 
            response.setOrderDate(api002Order.getOrderInputDatetime());
            //株取引区分
            response.setStockTradeType(api002Order.getStockTradeType());
        }
        if (api002MarginOrder != null) {
            // 返済建玉指定方法（全角半角）. 
            response.setRepaymentMethodRadio(api002MarginOrder.getClosePositionKind());
            // 返済選択順序（全角半角）. 
            response.setSortOrderRadio(api002MarginOrder.getCloseSelectionSort());
        }
    }
    
    /**
     * FCT001チェック
     * @param butenCode 部店コード
     * @param accountNumber　口座番号
     * @return チェック結果
     */
    private String callFCT001(String butenCode, String accountNumber) {
        
        String errorCode = null;
        InputFct001Dto input = new InputFct001Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        
        OutputFct001Dto output = fct001.doCheck(input);
        
        if (output != null) {
            if (StringUtils.equals(output.getTargetCustomerRefAuthFlag(),
                    TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
                errorCode = ERRORS_BUTENACCOUNTNOTEXIST;
                return errorCode;
            }
            if (StringUtils.equals(output.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
                errorCode = ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE;
                return errorCode;
            }
        }
        return errorCode;
    }
    
    /**
     * FCT003チェック
     * 
     * @param butenCode　部店コード
     * @param accountNumber　口座番号
     * @param tradeCd 取引種別
     * @return チェック結果
     */
    private String callFCT003(String butenCode, String accountNumber, String tradeCd) {

        String errorCode = null;
        
        InputFct003Dto input = new InputFct003Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        input.setProductCd(FOREIGN_DOMESTICSTOCK);
        if (!StringUtil.isNullOrEmpty(tradeCd)) {
            input.setTradeCd(tradeCd);
        }
        
        OutputFct003Dto output = fct003.doCheck(input);
        if (output == null) {
            errorCode = ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE;
            return errorCode;
        }
        if (!StringUtils.equals(output.getMediateAbleTradeFlag(), MediateAbleTradeFlag.ARI.getId())) {
            errorCode = ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE;
            return errorCode;
        }
        
        return errorCode;
    }
    
    /**
     * FCT018呼び出し
     * 
     * @param brandCode 銘柄コード
     * @return fct018出力結果
     */
    private OutputFct018Dto callFCT018(String countryCode) {
        
        InputFct018Dto input = new InputFct018Dto();
        input.setCountryCode(countryCode);
        return fct018.doCheck(input);
        
    }
    
    /**
     * 取消発注前の注文登録(SQL001の発行)
     * 
     * @throws Exception
     */
    private void orderRegister(IfaForeignMarginTradeOrderCancelConfirmA010aDtoRequest dtoReq, String orderNo,
            String orderSubNo, DataList<IfaForeignMarginTradeOrderCancelConfirmSql003ResponseModel> selectRes)
            throws Exception {
        
        IfaForeignMarginTradeOrderCancelConfirmSql001RequestModel sql001Req = new IfaForeignMarginTradeOrderCancelConfirmSql001RequestModel();
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        //ユーザー共通情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        //IFA注文番号
        sql001Req.setIfaOrderNo(orderNo);
        //IFA注文サブ番号
        sql001Req.setIfaOrderSubNo(orderSubNo);
        
        //受付注文番号
        sql001Req.setAcceptOrderNo(dtoReq.getOrderNumber());
        //受付注文サブ番号
        sql001Req.setAcceptOrderSubNo(dtoReq.getOrderSubNumber());
        //部店コード
        sql001Req.setButenCode(cc.getButenCode());
        //口座番号
        sql001Req.setAccountNumber(cc.getAccountNumber());
        //ティッカーコード
        sql001Req.setBrandCode(dtoReq.getBrandCode());
        //銘柄名
        sql001Req.setBrandName(dtoReq.getBrandName());
        //市場コード
        sql001Req.setExchangeCode(dtoReq.getMarketCode());
        //仕法区分
        String methodType = null;
        if (StringUtils.equals(dtoReq.getTradeCd(), ForeignStockTradeClass.MARGIN_NEW_BUY.getId())
                || StringUtils.equals(dtoReq.getTradeCd(), ForeignStockTradeClass.MARGIN_NEW_SELL.getId())) {
            methodType = MARGIN_NEW_NUMBER;
        } else if (StringUtils.equals(dtoReq.getTradeCd(), ForeignStockTradeClass.MARGIN_REPAY_BUY.getId())
                || StringUtils.equals(dtoReq.getTradeCd(), ForeignStockTradeClass.MARGIN_REPAY_SELL.getId())) {
            methodType = MARGIN_REPAY_NUMBER;
        }
        sql001Req.setMethodType(methodType);
        //売買区分
        sql001Req.setTradeType(dtoReq.getTradeKbn());
        //注文数量
        sql001Req.setOrderQuantity(dtoReq.getForeignQuantity());
        //価格条件区分
        sql001Req.setTradeMethodType(dtoReq.getOrderPriceKindList());
        //指値
        sql001Req.setLimitPrice(dtoReq.getHiddenOrderPrice());
        //発火条件価格
        sql001Req.setStopPrice(dtoReq.getStopOrderPrice2());
        //通貨コード
        sql001Req.setCurrencyCode(dtoReq.getCurrencyCode());
        //決済区分
        sql001Req.setSettlementType(dtoReq.getCurrencyTypeRadio());
        //預かり区分
        sql001Req.setDepositType(dtoReq.getDepositType());
        
        //現行踏襲箇所 SELECTSQLで取得出来なかった場合、以下の各値にはサービスにてnullを入れる
        if (selectRes.getDataList().size() > 0) {
            IfaForeignMarginTradeOrderCancelConfirmSql003ResponseModel param = selectRes.getDataList().get(0);
            //勧誘区分
            sql001Req.setInvitationType(param.getInvitationType());
            //受注方法区分
            sql001Req.setOrderMethodType(param.getOrderMethodType());
            //重要事項説明区分
            sql001Req.setExplanationInfoType(param.getExplanationInfoType());
            //英文開示銘柄区分
            sql001Req.setEngPubBrandExpType(param.getEngPubBrandExpType());
        } else {
            //勧誘区分
            sql001Req.setInvitationType(ZERO_PARAM);
            //受注方法区分
            sql001Req.setOrderMethodType(ZERO_PARAM);
            //重要事項説明区分
            sql001Req.setExplanationInfoType(ZERO_PARAM);
            //英文開示銘柄区分
            sql001Req.setEngPubBrandExpType(ZERO_PARAM);
        }
        
        //注文日　YYYYMMDD
        String orderDate = DateFormatUtil.dateFormatToYmd(dtoReq.getOrderDate());
        if (StringUtil.isNullOrEmpty(orderDate)) {
            sql001Req.setOrderDate(DEFAULT_ORDER_DATE);
        } else {
            sql001Req.setOrderDate(orderDate);
        }
        //注文時間 YYYY/MM/DD HH:mm:SS
        String orderTime = DateFormatUtil.dateFormatToSeparatedYmdhms(dtoReq.getOrderDate());
        if (StringUtil.isNullOrEmpty(orderTime)) {
            sql001Req.setOrderTime(DEFAULT_ORDER_TIME);
        } else {
            sql001Req.setOrderTime(orderTime);
        }
        //国内約定日 YYYYMMDD
        sql001Req.setTradeDate(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getDomesticTradeDate()));
        //注文期限日 YYYYMMDD
        if (StringUtils.equals(dtoReq.getPeriodRadio(), PeriodConditions.TODAY.getId())) {
            // 当日中なら現地約定日
            sql001Req.setTradeLimitDate(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getForeignTradeDate()));
        } else {
            // 当日中以外なら期間
            sql001Req.setTradeLimitDate(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getPeriod()));
        }
        
        //仲介業者コード
        sql001Req.setBrokerCode(cc.getBrokerCode());
        //仲介業者営業員コード
        sql001Req.setIntermediaryEmpCd(cc.getBrokerChargeCode());
        
        //作成者
        sql001Req.setCreateUser(ua.getUserId());
        
        //更新者
        sql001Req.setUpdateUser(ua.getUserId());
        
        int insSql001Res = dao.insertIfaForeignMarginTradeOrderCancelConfirmSql001(sql001Req);
        if (insSql001Res != 1) {
            throw new Exception();
        }
    }
    
    /**現行踏襲箇所
     * SQL001の発行のための検索
     * 
     * @throws Exception
     */
    private DataList<IfaForeignMarginTradeOrderCancelConfirmSql003ResponseModel> getIfaOrderNumber(String acceptOrderNo)
            throws Exception {
        
        IfaForeignMarginTradeOrderCancelConfirmSql003RequestModel sql003Req = new IfaForeignMarginTradeOrderCancelConfirmSql003RequestModel();
        
        sql003Req.setAcceptOrderNo(acceptOrderNo);
        return dao.selectIfaForeignMarginTradeOrderCancelConfirmSql003(sql003Req);
    }
    
    /**
     * 取消発注後の注文更新(SQL002の発行)
     * @param dtoReq A010のリクエスト項目
     * @param api002Res API002の出力結果
     * @throws Exception
     */
    private void orderUpdate(IfaForeignMarginTradeOrderCancelConfirmA010bDtoRequest dtoReq,
            DeleteForeignStockMarginOrderResp api002res, Integer apiStatusCode, String apiMessage, String apiErrorCode)
            throws Exception {
        
        IfaForeignMarginTradeOrderCancelConfirmSql002RequestModel sql002Req = new IfaForeignMarginTradeOrderCancelConfirmSql002RequestModel();
        
        //ユーザー共通情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        // IFA注文番号
        sql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
        // IFA注文sub番号
        sql002Req.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
        
        //api002内のクラスを宣言
        Order api002Order = null;
        //nullチェックと格納
        if (api002res != null) {
            if (api002res.getMarginOrder() != null) {
                if (api002res.getMarginOrder().getOrder() != null) {
                    api002Order = api002res.getMarginOrder().getOrder();
                }
            }
        }
        
        if (api002Order != null) {
            // 受付注文番号
            sql002Req.setAcceptOrderNo(api002Order.getOrderNo());
            // 受付注文サブ番号
            sql002Req.setAcceptOrderSubNo(api002Order.getOrderSubNo());
            //注文日　YYYYMMDD
            String orderDate = DateFormatUtil.dateFormatToYmd(api002Order.getOrderInputDatetime());
            if (StringUtil.isNullOrEmpty(orderDate)) {
                sql002Req.setOrderDate(DEFAULT_ORDER_DATE);
            } else {
                sql002Req.setOrderDate(orderDate);
            }
            //注文時間 YYYY/MM/DD HH:mm:SS
            String orderTime = DateFormatUtil.dateFormatToSeparatedYmdhms(api002Order.getOrderInputDatetime());
            if (StringUtil.isNullOrEmpty(orderTime)) {
                sql002Req.setOrderTime(DEFAULT_ORDER_TIME);
            } else {
                sql002Req.setOrderTime(orderTime);
            }
            
            // 注文期限日
            if (StringUtils.equals(dtoReq.getPeriodRadio(), PeriodConditions.TODAY.getId())) {
                sql002Req.setTradeLimitDate(DateFormatUtil.dateFormatToYmdNoSign(api002Order.getFrnTradeDate()));
            } else {
                sql002Req.setTradeLimitDate(DateFormatUtil.dateFormatToYmdNoSign(api002Order.getOrderTerm()));
            }
        }
        // APIエラーコード
        sql002Req.setApiErrorCode(apiErrorCode);
        // APIステータスコード
        sql002Req.setApiStatusCode(apiStatusCode.toString());
        // APIメッセージ
        sql002Req.setApiMsg(apiMessage);
        // 更新者
        sql002Req.setUpdateUser(ua.getUserId());
        
        int insSql002Res = dao.updateIfaForeignMarginTradeOrderCancelConfirmSql002(sql002Req);
        if (insSql002Res != 1) {
            throw new Exception();
        }
    }
    
    /**
     * 連番の取得(シーケンスオブジェクトの呼び出し)
     * 
     * @return　SQL004の出力結果
     * @throws Exception
     */
    private DataList<IfaForeignMarginTradeOrderCancelConfirmSql004ResponseModel> getOrderNo() throws Exception {
        
        IfaForeignMarginTradeOrderCancelConfirmSql004RequestModel req = new IfaForeignMarginTradeOrderCancelConfirmSql004RequestModel();
        return dao.selectIfaForeignMarginTradeOrderCancelConfirmSql004(req);
    }
    
    //以下現行踏襲箇所
    
    /**
     * API001呼び出し処理
     * 
     * @param orderSubNo 注文サブ番号
     * @return API001出力結果
     */
    private GetForeignStockDeletedMarginOrderInitializationResp getForeignStockDeletedMarginOrderInitialization(
            String orderSubNo) throws Exception {
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        //部店コードと口座番号取得
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        /* 外国株式信用注文取消初期情報取得APIの戻り値を返す */
        return foreignStockService.getForeignStockDeletedMarginOrderInitialization(butenCode, accountNumber,
                orderSubNo);
    }
    
    /**
     * API002呼び出し処理
     * 
     * @param orderSubNo 注文サブ番号
     * @return API002出力結果
     */
    private DeleteForeignStockMarginOrderResp deleteForeignStockMarginOrder(String orderSubNo) throws Exception {
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        //部店コードと口座番号取得
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        /* API引数初期化 */
        DeleteForeignStockMarginOrderReq request = new DeleteForeignStockMarginOrderReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        // Header:リクエストID
        request.getHeader().setRequest_id(API002_REQUEST_ID);
        // パラメータ: 注文Sub番号
        request.getParameter()
                .setOrderSubNo(StringUtil.isNullOrEmpty(orderSubNo) ? StringUtil.EMPTY_STRING : orderSubNo);
        
        /* 外国株式信用注文取消登録APIの戻り値を返す */
        return foreignStockService.deleteForeignStockMarginOrder(butenCode, accountNumber, null, API002_REQUEST_ID,
                orderSubNo);
    }
    
}
