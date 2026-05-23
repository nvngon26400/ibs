package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.List;
import java.util.Optional;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.util.OrderStatusUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMarginNewOrderCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCancelConfirmSql001SubResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA002aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA002aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA002bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA002bResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMarginNewOrderCancelConfirmService;
import com.sbisec.helios.ap.common.enums.AccountType;
import com.sbisec.helios.ap.common.enums.DomesticMarginAccountType;
import com.sbisec.helios.ap.common.enums.DomesticStockTradeClass;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ExecuteMethod;
import com.sbisec.helios.ap.common.enums.MarginTradeKbn;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.enums.OrderClass;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.exception.ApiError;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.nri.ifa.NriApiService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOrd;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOutData;
import jp.co.sbisec.pcenter.dto.yanap.StockCancelOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.StockCancelOrderOutData;

/**
 * 画面ID：SUB0202_0212-03_1
 * 画面名：信用新規注文取消確認
 * 2024/04/17 新規作成
 *
 * @author 宇田川達弥
 */
@Component(value = "cmpIfaMarginNewOrderCancelConfirmService")
public class IfaMarginNewOrderCancelConfirmServiceImpL implements IfaMarginNewOrderCancelConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginNewOrderCancelConfirmServiceImpL.class);
    
    /** メッセージID:権限チェックエラー */
    private static final String MESSAGE_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** メッセージID:取引停止口座エラー */
    private static final String MESSAGE_OUT_OF_SERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** メッセージID:媒介不可エラー */
    private static final String MESSAGE_MEDIATE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** メッセージID:信用口座未開設エラー */
    private static final String MESSAGE_DMARGIN_ACCOUNT_NOT_OPENED = "errors.dms.domesticMarginAccount.notOpen";

    /** CCSIDが未登録のためご利用できません。 */
    private static final String MESSAGE_CCSID_UNREGISTERED = "errors.cmn.ccsid.unregistered";
    
    /** メッセージID:指定注文存在チェックエラー */
    private static final String MESSAGE_ORDER_NOT_FOUND = "errors.cmn.order.notFound";
    
    /** メッセージID:注文取消可否チェックエラー */
    private static final String MESSAGE_ORDER_CANCEL_UNABLE = "errors.cmn.orderCancel.outOfService";
    
    /** メッセージID:DB登録エラー */
    private static final String MESSAGE_PRE_ORDER_EXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    /** メッセージID:注文取消API処理結果エラー */
    private static final String MESSAGE_ORDER_EXECUTION_FAILED = "errors.cmn.orderExecutionCancel.failed";
    
    /** メッセージID:注文取消時の注文DB更新ワーニング */
    private static final String MESSAGE_POST_ORDER_EXECUTION_WARNING = "warnings.cmn.postOrderExecutionCancel.completed";
    
    /** 区分ID:対象取引（メッセージ表示用） */
    private static final String CODE_ID_MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 区分値:対象取引（メッセージ表示用）.国内信用取引 */
    private static final String CODE_VAL_MSG_DISPLAY_TARGET_TRADE_DMARGIN = "3";
    
    /** 外部コード.取引種別（国内株式）.買い */
    private static final String CODE_EXT_DOMESTIC_STOCK_TRADE_CLASS_BUY = "K";
    
    /** 外部コード.取引種別（国内株式）.売り */
    private static final String CODE_EXT_DOMESTIC_STOCK_TRADE_CLASS_SELL = "U";
    
    /** 区分値：証券種別.国内株式 */
    private static final String CODE_VAL_SECURITY_CLASS_DSTOCK = "01";
    
    /** 区分値:API.リクエスト区分.受注番号指定 */
    private static final String CODE_VAL_EXEC_ORDER_ORDER_NO = "2";
    
    /** 区分値:API.取引区分.現物信用注文明細（上場CW含まない） */
    private static final String CODE_VAL_TORIHIKI_KBN_STOCK_MARGIN_WO_CW = "1";
    
    /** 区分値:API.約定取得区分.約定明細取得 */
    private static final String CODE_VAL_TRADE_GET_KBN_GET_CONTRACT = "1";
    
    /** 区分値:API.弁済期限.一般信用売り-短期銘柄 */
    private static final String CODE_VAL_PAYMENT_LIMIT_SHORT_TERM = "A";
    
    /** 区分値:API.弁済期限.一般信用売り-長期在庫制限有り銘柄 */
    private static final String CODE_VAL_PAYMENT_LIMIT_LONG_TERM_INVENTORY_LIMITED = "B";
    
    /** パディング用Char'0'. */
    private static final char PADDING_CHAR_ZERO = '0';
    
    /** パディング長:口座番号(7桁) */
    private static final int PADDING_LENGTH_ACCOUNT_NUMBER = 7;
    
    /** パディング長:検索番号指定(5桁) */
    private static final int PADDING_LENGTH_REF_NUMBER = 5;
    
    /** パディング長:銘柄コード(5桁) */
    private static final int PADDING_LENGTH_BRAND_CD = 5;
    
    /** パディング長:トランザクションID(32桁) */
    private static final int PADDING_LENGTH_TRANSACTION_ID = 32;
    
    /** パディング長:通番(7桁) */
    private static final int PADDING_LENGTH_COMMAND_NUM = 7;
    
    /** パディング長:API002.アカウントID(11桁) */
    private static final int PADDING_LENGTH_API002_ACCOUNT_ID = 11;
    
    /** パディング長:アカウント毎の連番(7桁) */
    private static final int PADDING_LENGTH_NUMBER_PER_ACCOUNT = 7;
    
    /** SQLレコード数.1件 */
    private static final int SQL_COUNT_ONE = 1;
    
    /** IFA注文サブ番号.初期値 */
    private static final String IFA_ORDER_SUB_NO_INITIAL = "1";
    
    /** API002 商品区分 : "S"  */
    private static final String API002_ORDER_TYPE = "S";
    
    /** API002 オリジン : "0"  */
    private static final String API002_ORIGIN = "0";
    
    /** API002 取消受付経路区分 : "0"：支店  */
    private static final String API002_CXL_CALLCENTER_KBN = "0";
    
    /** API002 IPアドレス : "ifap"  */
    private static final String API002_IP_ADDRESS = "ifap";
    
    /** SQL001 注文種別（一覧） : 0:通常注文 */
    private static final String CODE_VAL_LIST_ORDER_CLASS_NORMAL = "0";
    
    /** SQL001 注文種別（一覧） : 1:逆指値注文 */
    private static final String CODE_VAL_LIST_ORDER_CLASS_STOP = "1";
    
    /** SQL001 注文種別（一覧） : 2:OCO注文 */
    private static final String CODE_VAL_LIST_ORDER_CLASS_OCO = "2";
    
    /** SQL001 注文種別（一覧） : 3:IFD1注文（通常） */
    private static final String CODE_VAL_LIST_ORDER_CLASS_IFD1_NORMAL = "3";
    
    /** SQL001 注文種別（一覧） : 4:IFD1注文（逆指値） */
    private static final String CODE_VAL_LIST_ORDER_CLASS_IFD1_STOP = "4";
    
    /** API002 RBE注文種別."△△△":通常注文 */
    private static final String CODE_VAL_RBE_ORDER_KIND_NORMAL = StringUtils.repeat(StringUtils.SPACE, 3);
    
    /** API002 RBE注文種別."SLO":逆指値注文 */
    private static final String CODE_VAL_RBE_ORDER_KIND_SLO = "SLO";
    
    /** API002 RBE注文種別."OCO":OCO注文 */
    private static final String CODE_VAL_RBE_ORDER_KIND_OCO = "OCO";
    
    /** DONE RBE注文種別.OCO注文 */
    private static final String CODE_VAL_DONE_RBE_ORDER_KIND_OCO = "OCO";
    
    /** API002 RBE注文ステータス."1":発火済み */
    private static final String CODE_VAL_RBE_ORDER_STATUS_TRIGGER = "1";
    
    /** API002 自動注文種別."IF△△":IFD親注文 */
    private static final String CODE_VAL_AUTO_ORDER_KIND_IFD_PARENT = "IF  ";
    
    /** API002 自動注文種別."DONE":IFD子注文 */
    private static final String CODE_VAL_AUTO_ORDER_KIND_IFD_CHILD = "DONE";
    
    /** API002 自動注文種別."△△△△":通常注文/逆指注文/OCO注文 */
    private static final String CODE_VAL_AUTO_ORDER_KIND_OTHERS = StringUtils.repeat(StringUtils.SPACE, 4);
    
    /** API002 指成区分.指値 */
    private static final String CODE_VAL_SASINARI_ID_LIMIT = StringUtils.SPACE;
    
    /** API002 指成区分.寄付指値 */
    private static final String CODE_VAL_SASINARI_ID_LIMIT_OPEN = "Z";
    
    /** API002 指成区分.引け指値 */
    private static final String CODE_VAL_SASINARI_ID_LIMIT_CLOSE = "I";
    
    /** API002 指成区分.成行 */
    private static final String CODE_VAL_SASINARI_ID_MARKET = "N";
    
    /** API002 指成区分.寄付成行 */
    private static final String CODE_VAL_SASINARI_ID_MARKET_OPEN = "Y";
    
    /** API002 指成区分.引け成行 */
    private static final String CODE_VAL_SASINARI_ID_MARKET_CLOSE = "H";
    
    /** API002 指成区分.不成 */
    private static final String CODE_VAL_SASINARI_ID_LIMIT_TO_MARKET = "F";
    
    /** API002 指成区分.IOC指 */
    private static final String CODE_VAL_SASINARI_ID_LIMIT_IOC = "P";
    
    /** API002 指成区分.IOC成 */
    private static final String CODE_VAL_SASINARI_ID_MARKET_IOC = "O";
    
    /** API002 直近OCO指成区分.指値 */
    private static final String CODE_VAL_LATEST_OCO_SASINARI_ID_LIMIT = StringUtils.SPACE;
    
    /** API002 直近OCO指成区分.成行 */
    private static final String CODE_VAL_LATEST_OCO_SASINARI_ID_MARKET = "N";
    
    /** API002 直近OCO指成区分.不成 */
    private static final String CODE_VAL_LATEST_OCO_SASINARI_ID_LIMIT_TO_MARKET = "F";
    
    /** API002 DONE 指成区分.指値 */
    private static final String CODE_VAL_DONE_SASINARI_ID_LIMIT = StringUtils.SPACE;
    
    /** API002 DONE 指成区分.寄付指値 */
    private static final String CODE_VAL_DONE_SASINARI_ID_LIMIT_OPEN = "Z";
    
    /** API002 DONE 指成区分.引け指値 */
    private static final String CODE_VAL_DONE_SASINARI_ID_LIMIT_CLOSE = "I";
    
    /** API002 DONE 指成区分.成行 */
    private static final String CODE_VAL_DONE_SASINARI_ID_MARKET = "N";
    
    /** API002 DONE 指成区分.寄付成行 */
    private static final String CODE_VAL_DONE_SASINARI_ID_MARKET_OPEN = "Y";
    
    /** API002 DONE 指成区分.引け成行 */
    private static final String CODE_VAL_DONE_SASINARI_ID_MARKET_CLOSE = "H";
    
    /** API002 DONE 指成区分.不成 */
    private static final String CODE_VAL_DONE_SASINARI_ID_LIMIT_TO_MARKET = "F";
    
    /** API002 DONE 指成区分.IOC指 */
    private static final String CODE_VAL_DONE_SASINARI_ID_LIMIT_IOC = "P";
    
    /** API002 DONE 指成区分.IOC成 */
    private static final String CODE_VAL_DONE_SASINARI_ID_MARKET_IOC = "O";
    
    /** 国内株式:信用取引区分 年 */
    private static final String DOMESTIC_MARGIN_TRADE_TERM_YEAR = "年";
    
    /** 国内株式:信用取引区分 ヵ月 */
    private static final String DOMESTIC_MARGIN_TRADE_TERM_MONTH = "ヶ月";
    
    /** 国内株式:信用取引区分 日 */
    private static final String DOMESTIC_MARGIN_TRADE_TERM_DAY = "日";
    
    /** API002 一般信用売弁済期限年月日区分の出力値 "△"：無期限 */
    private static final String CODE_VAL_IPPAN_MG_PAYMENTKBN_UNLIMITED = StringUtils.SPACE;
    
    /** 弁済期限 日計り(D) */
    private static final String PAYMENT_DDEADLINE_D = "D";

    /** 弁済期限 日計りH(E) */
    private static final String PAYMENT_DDEADLINE_E = "E";

    /** 一日信用区分 日計り */
    private static final String DAILY_CREDIT_KBN_1 = "1";

    /** 一日信用区分 日計りH */
    private static final String DAILY_CREDIT_KBN_2 = "2";

    /** 一日信用区分 日計り,日計りH以外 */
    private static final String DAILY_CREDIT_KBN_OTHER = " ";

    /** 信用新規注文取消確認Dao. */
    @Autowired
    private IfaMarginNewOrderCancelConfirmDao dao;
    
    /**  FCT001 利用者顧客参照権限チェック. */
    @Autowired
    private Fct001 fct001;
    
    /** FCT003 取引コース媒介可否チェック. */
    @Autowired
    private Fct003 fct003;
    
    /** 区分定義公開機能Service. */
    @Autowired
    private CodeListService codeListService;
    
    /** NRI-APIサービス */
    @Autowired
    private NriApiService nriApiService;
    
    /** 注文ステータスユーティリティ */
    @Autowired
    private OrderStatusUtil orderStatusUtil;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMarginNewOrderCancelConfirmA001RequestDto
     * Dto レスポンス：IfaMarginNewOrderCancelConfirmA001ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaMarginNewOrderCancelConfirmA001ResponseDto> initializeA001(
            IfaMarginNewOrderCancelConfirmA001RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginNewOrderCancelConfirmServiceImplL.initializeA001");
        }
        
        // 顧客共通情報を取得する
        final CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // 口座に対する権限チェック
        final InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(customerCommon.getButenCode());
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber());
        
        final OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        if (StringUtils.equals(outputFct001Dto.getTargetCustomerRefAuthFlag(),
                TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
            // 対象顧客参照権限有無が権限なしの場合、権限なしエラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(MESSAGE_ACCOUNT_NOT_EXISTS,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
        } else if (StringUtils.equals(outputFct001Dto.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
            // 取引停止口座の場合、取引停止エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_OUT_OF_SERVICE,
                    IfaCommonUtil.getMessage(MESSAGE_OUT_OF_SERVICE));
        }
        
        // 株式統合注文一覧照会リクエストデータを作成する
        QueryStockUniteOrderPointInData api001InData = new QueryStockUniteOrderPointInData();
        
        api001InData.setButenCd(customerCommon.getButenCode());
        api001InData.setKozaNo(StringUtil.fillLeft(customerCommon.getAccountNumber(), PADDING_CHAR_ZERO,
                PADDING_LENGTH_ACCOUNT_NUMBER));
        api001InData.setExecOrder(CODE_VAL_EXEC_ORDER_ORDER_NO);
        api001InData.setRefFrom(StringUtils.repeat(StringUtils.SPACE, PADDING_LENGTH_REF_NUMBER));
        api001InData.setRefTo(StringUtils.repeat(StringUtils.SPACE, PADDING_LENGTH_REF_NUMBER));
        api001InData.setOrderNo(dtoReq.getEcOrderNo());
        api001InData.setTorihikiKbn(CODE_VAL_TORIHIKI_KBN_STOCK_MARGIN_WO_CW);
        api001InData.setTradeGetKbn(CODE_VAL_TRADE_GET_KBN_GET_CONTRACT);
        api001InData.setBrandCd(StringUtils.repeat(StringUtils.SPACE, PADDING_LENGTH_BRAND_CD));
        api001InData.setAccountGetKbn(AccountType.WHOLE.getId());
        
        QueryStockUniteOrderPointOutData api001OutData = null;
        try {
            // 株式統合注文一覧照会APIを呼び出す
            api001OutData = nriApiService.queryStockUniteOrderPointForOrderStatusList(api001InData, false);
        } catch (ApiError e) {
            // 株式統合注文一覧照会APIでエラーが発生した場合、指定注文存在チェックエラーを返す
            LOGGER.warn("IfaMarginNewOrderCancelConfirmServiceImplL.initializeA001", e);
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_ORDER_NOT_FOUND,
                    IfaCommonUtil.getMessage(MESSAGE_ORDER_NOT_FOUND));
        }
        
        // 株式統合注文一覧照会APIのレスポンスから指定したEC受注番号の注文情報を取り出す
        final Optional<QueryStockUniteOrderPointOrd> optionalReqOrder = api001OutData.getReqOrderDataExe().stream()
                .filter(reqOrderData -> StringUtils.equals(reqOrderData.getOrderNo(), dtoReq.getEcOrderNo()))
                .findFirst();
        
        if (!optionalReqOrder.isPresent()) {
            // 注文情報が存在しない場合、指定注文存在チェックエラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_ORDER_NOT_FOUND,
                    IfaCommonUtil.getMessage(MESSAGE_ORDER_NOT_FOUND));
        }
        
        // 注文状況一覧画面で取消ボタンが表示される条件に該当するか判定する
        if (!optionalReqOrder.filter(orderStatusUtil::canCancelOrder).isPresent()) {
            // 取消ボタンが表示される条件に該当しない場合(取消不可)、注文取消可否チェックエラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_ORDER_CANCEL_UNABLE,
                    IfaCommonUtil.getMessage(MESSAGE_ORDER_CANCEL_UNABLE));
        }
        final QueryStockUniteOrderPointOrd api001ReqOrderData = optionalReqOrder.get();
        
        // 媒介可否チェック
        final InputFct003Dto inputFct003Dto = new InputFct003Dto();
        
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(CODE_VAL_SECURITY_CLASS_DSTOCK);
        if (StringUtils.equals(api001ReqOrderData.getBuySell(), CODE_EXT_DOMESTIC_STOCK_TRADE_CLASS_BUY)) {
            inputFct003Dto.setTradeCd(DomesticStockTradeClass.SHINYOSHINKI_BUY.getId());
        } else if (StringUtils.equals(api001ReqOrderData.getBuySell(), CODE_EXT_DOMESTIC_STOCK_TRADE_CLASS_SELL)) {
            inputFct003Dto.setTradeCd(DomesticStockTradeClass.SHINYOSHINKI_SELL.getId());
        }
        
        final OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        
        // 媒介可否リスト.媒介可否に媒介可が存在するか判定する
        if (outputFct003Dto.getMediateProprietyList().stream()
                .map(mediateProprieties -> mediateProprieties.getMediatePropriety()).noneMatch(
                        mediatePropriety -> StringUtils.equals(MediateAbleTradeFlag.ARI.getId(), mediatePropriety))) {
            // 存在しない場合、媒介不可エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_MEDIATE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(MESSAGE_MEDIATE_UNAVAILABLE, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_DMARGIN) }));
        }
        
        if (!StringUtils.equals(customerCommon.getDomesticMarginAccountType(),
                DomesticMarginAccountType.MARGIN_ACCOUNT.getId())) {
            // 国内信用口座開設状況が開設済ではない場合、信用口座未開設エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_DMARGIN_ACCOUNT_NOT_OPENED,
                    IfaCommonUtil.getMessage(MESSAGE_DMARGIN_ACCOUNT_NOT_OPENED));
        }
        
        // レスポンスデータを作成する
        final IfaMarginNewOrderCancelConfirmA001ResponseDto responseData = new IfaMarginNewOrderCancelConfirmA001ResponseDto();
        responseData.setBrandCode(api001ReqOrderData.getStockSecCode());
        responseData.setBrandName(api001ReqOrderData.getSecName());
        responseData.setMarket(api001ReqOrderData.getLatestMarketId());
        
        // 取引種別を売買区分から設定する
        if (StringUtils.equals(api001ReqOrderData.getBuySell(), CODE_EXT_DOMESTIC_STOCK_TRADE_CLASS_BUY)) {
            responseData.setTradeCd(DomesticStockTradeClass.SHINYOSHINKI_BUY.getId());
        } else if (StringUtils.equals(api001ReqOrderData.getBuySell(), CODE_EXT_DOMESTIC_STOCK_TRADE_CLASS_SELL)) {
            responseData.setTradeCd(DomesticStockTradeClass.SHINYOSHINKI_SELL.getId());
        }
        
        responseData.setOrderQuantity(api001ReqOrderData.getQuantity());
        responseData.setUnTradeQuantity(api001ReqOrderData.getExecLeftQuantity());
        responseData.setPeriod(api001ReqOrderData.getLimit());
        responseData.setDepositType(api001ReqOrderData.getHitokuteiTradeKbn());
        
        // 注文種別を自動注文種別、RBE注文種別、DONERBE注文種別から設定する
        String orderKind = null;
        if (StringUtils.equalsAny(api001ReqOrderData.getAutoOrderKind(),
                new String[] { CODE_VAL_AUTO_ORDER_KIND_OTHERS, CODE_VAL_AUTO_ORDER_KIND_IFD_CHILD })) {
            // 自動注文種別=通常注文/逆指注文/OCO注文 または IFD子注文
            if (StringUtils.equalsAny(api001ReqOrderData.getRbeOrderKind(),
                    new String[] { CODE_VAL_RBE_ORDER_KIND_NORMAL, CODE_VAL_RBE_ORDER_KIND_SLO })) {
                // RBE注文種別=通常注文 または 逆指値注文
                orderKind = OrderClass.NORMAL.getId();
            } else if (StringUtils.equals(api001ReqOrderData.getRbeOrderKind(), CODE_VAL_RBE_ORDER_KIND_OCO)) {
                // RBE注文種別=OCO注文
                orderKind = OrderClass.OCO.getId();
            }
        } else if (StringUtils.equals(api001ReqOrderData.getAutoOrderKind(), CODE_VAL_AUTO_ORDER_KIND_IFD_PARENT)) {
            // 自動注文種別=IFD親注文
            if (StringUtils.equals(api001ReqOrderData.getDoneRbeOrderKind(), CODE_VAL_DONE_RBE_ORDER_KIND_OCO)) {
                // DONERBE注文種別=OCO注文
                orderKind = OrderClass.IFDOCO.getId();
            } else {
                // DONERBE注文種別がOCO注文以外
                orderKind = OrderClass.IFD.getId();
            }
        }
        responseData.setOrderKind(orderKind);
        
        responseData.setOrderDayTime(api001ReqOrderData.getInputDate());
        
        // 注文種別が通常/逆指値の場合の値を設定する
        if (StringUtils.equals(orderKind, OrderClass.NORMAL.getId())) {
            // 執行方法をRBE注文種別と指成区分から設定する
            if (StringUtils.equals(api001ReqOrderData.getRbeOrderKind(), CODE_VAL_RBE_ORDER_KIND_NORMAL)) {
                // RBE注文種別が通常注文の場合
                if (StringUtils.equalsAny(api001ReqOrderData.getSasinariId(),
                        new String[] { CODE_VAL_SASINARI_ID_LIMIT, CODE_VAL_SASINARI_ID_LIMIT_OPEN,
                                CODE_VAL_SASINARI_ID_LIMIT_CLOSE, CODE_VAL_SASINARI_ID_LIMIT_TO_MARKET,
                                CODE_VAL_SASINARI_ID_LIMIT_IOC })) {
                    // 指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
                    responseData.setSasinariHouhou(ExecuteMethod.LIMIT.getId());
                } else if (StringUtils.equalsAny(api001ReqOrderData.getSasinariId(),
                        new String[] { CODE_VAL_SASINARI_ID_MARKET, CODE_VAL_SASINARI_ID_MARKET_OPEN,
                                CODE_VAL_SASINARI_ID_MARKET_CLOSE, CODE_VAL_SASINARI_ID_MARKET_IOC })) {
                    // 指成区分=指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
                    responseData.setSasinariHouhou(ExecuteMethod.MARKET.getId());
                }
            } else if (StringUtils.equals(api001ReqOrderData.getRbeOrderKind(), CODE_VAL_RBE_ORDER_KIND_SLO)) {
                // RBE注文種別が逆指値の場合
                responseData.setSasinariHouhou(ExecuteMethod.STOP.getId());
                if (StringUtils.equalsAny(api001ReqOrderData.getSasinariId(),
                        new String[] { CODE_VAL_SASINARI_ID_LIMIT, CODE_VAL_SASINARI_ID_LIMIT_OPEN,
                                CODE_VAL_SASINARI_ID_LIMIT_CLOSE, CODE_VAL_SASINARI_ID_LIMIT_TO_MARKET,
                                CODE_VAL_SASINARI_ID_LIMIT_IOC })) {
                    // 指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
                    responseData.setGyakusasiHouhou(ExecuteMethod.LIMIT.getId());
                } else if (StringUtils.equalsAny(api001ReqOrderData.getSasinariId(),
                        new String[] { CODE_VAL_SASINARI_ID_MARKET, CODE_VAL_SASINARI_ID_MARKET_OPEN,
                                CODE_VAL_SASINARI_ID_MARKET_CLOSE, CODE_VAL_SASINARI_ID_MARKET_IOC })) {
                    // 指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
                    responseData.setGyakusasiHouhou(ExecuteMethod.MARKET.getId());
                }
            }
            responseData.setSasinariJyouken(api001ReqOrderData.getSasinariId());
            responseData.setTriggerPrice(api001ReqOrderData.getLatestTriggerPrice());
            responseData.setTriggerPriceZone(api001ReqOrderData.getLatestTriggerZone());
            responseData.setPrice(api001ReqOrderData.getLimitPrice());
        }
        
        // 注文種別がOCOの場合の値を設定する
        if (StringUtils.equals(orderKind, OrderClass.OCO.getId())) {
            responseData.setOco1SasinariHouhou(ExecuteMethod.LIMIT.getId());
            responseData.setOco1SasinariJyouken(api001ReqOrderData.getSasinariId());
            responseData.setOco1Price(api001ReqOrderData.getLimitPrice());
            responseData.setOco2TriggerPrice(api001ReqOrderData.getLatestTriggerPrice());
            responseData.setOco2TriggerPriceZone(api001ReqOrderData.getLatestTriggerZone());
            
            if (StringUtils.equalsAny(api001ReqOrderData.getLatestOcoSasinariId(), new String[] {
                    CODE_VAL_LATEST_OCO_SASINARI_ID_LIMIT, CODE_VAL_LATEST_OCO_SASINARI_ID_LIMIT_TO_MARKET })) {
                // 直近OCO指成区分=指値、不成(F)　の場合
                responseData.setOco2GyakusasiHouhou(ExecuteMethod.LIMIT.getId());
            } else if (StringUtils.equals(api001ReqOrderData.getLatestOcoSasinariId(),
                    CODE_VAL_LATEST_OCO_SASINARI_ID_MARKET)) {
                // 直近OCO指成区分=成行　の場合
                responseData.setOco2GyakusasiHouhou(ExecuteMethod.MARKET.getId());
            }
            responseData.setOco2GyakusasiJyouken(api001ReqOrderData.getLatestOcoSasinariId());
            responseData.setOco2Price(api001ReqOrderData.getLatestOcoPrice());
        }
        
        // 注文種別がIFDOCOの場合の値を設定する
        if (StringUtils.equals(orderKind, OrderClass.IFDOCO.getId())) {
            responseData.setOco1SasinariHouhou(ExecuteMethod.LIMIT.getId());
            responseData.setOco1SasinariJyouken(api001ReqOrderData.getDoneSasinariId());
            responseData.setOco1Price(api001ReqOrderData.getDonePrice());
            responseData.setOco2TriggerPrice(api001ReqOrderData.getDoneTriggerPrice());
            responseData.setOco2TriggerPriceZone(api001ReqOrderData.getDoneTriggerZone());
            
            if (StringUtils.equalsAny(api001ReqOrderData.getDoneOcoSasinariId(),
                    new String[] { CODE_VAL_DONE_SASINARI_ID_LIMIT, CODE_VAL_DONE_SASINARI_ID_LIMIT_TO_MARKET })) {
                // DONEOCO指成区分=指値、不成(F)　の場合
                responseData.setOco2GyakusasiHouhou(ExecuteMethod.LIMIT.getId());
            } else if (StringUtils.equals(api001ReqOrderData.getDoneOcoSasinariId(),
                    CODE_VAL_DONE_SASINARI_ID_MARKET)) {
                // 直近OCO指成区分=成行　の場合
                responseData.setOco2GyakusasiHouhou(ExecuteMethod.MARKET.getId());
            }
            responseData.setOco2GyakusasiJyouken(api001ReqOrderData.getDoneOcoSasinariId());
            responseData.setOco2Price(api001ReqOrderData.getDoneOcoPrice());
            
            // IFD1.執行方法をRBE注文種別と指成区分から設定する
            if (StringUtils.equals(api001ReqOrderData.getRbeOrderKind(), CODE_VAL_RBE_ORDER_KIND_NORMAL)) {
                // RBE注文種別が通常注文の場合
                if (StringUtils.equalsAny(api001ReqOrderData.getSasinariId(),
                        new String[] { CODE_VAL_SASINARI_ID_LIMIT, CODE_VAL_SASINARI_ID_LIMIT_OPEN,
                                CODE_VAL_SASINARI_ID_LIMIT_CLOSE, CODE_VAL_SASINARI_ID_LIMIT_TO_MARKET,
                                CODE_VAL_SASINARI_ID_LIMIT_IOC })) {
                    // 指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
                    responseData.setIfd1SasinariHouhou(ExecuteMethod.LIMIT.getId());
                } else if (StringUtils.equalsAny(api001ReqOrderData.getSasinariId(),
                        new String[] { CODE_VAL_SASINARI_ID_MARKET, CODE_VAL_SASINARI_ID_MARKET_OPEN,
                                CODE_VAL_SASINARI_ID_MARKET_CLOSE, CODE_VAL_SASINARI_ID_MARKET_IOC })) {
                    // 指成区分=指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
                    responseData.setIfd1SasinariHouhou(ExecuteMethod.MARKET.getId());
                }
            } else if (StringUtils.equals(api001ReqOrderData.getRbeOrderKind(), CODE_VAL_RBE_ORDER_KIND_SLO)) {
                // RBE注文種別が逆指値の場合
                responseData.setIfd1SasinariHouhou(ExecuteMethod.STOP.getId());
                if (StringUtils.equalsAny(api001ReqOrderData.getSasinariId(),
                        new String[] { CODE_VAL_SASINARI_ID_LIMIT, CODE_VAL_SASINARI_ID_LIMIT_OPEN,
                                CODE_VAL_SASINARI_ID_LIMIT_CLOSE, CODE_VAL_SASINARI_ID_LIMIT_TO_MARKET,
                                CODE_VAL_SASINARI_ID_LIMIT_IOC })) {
                    // 指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
                    responseData.setIfd1GyakusasiHouhou(ExecuteMethod.LIMIT.getId());
                } else if (StringUtils.equalsAny(api001ReqOrderData.getSasinariId(),
                        new String[] { CODE_VAL_SASINARI_ID_MARKET, CODE_VAL_SASINARI_ID_MARKET_OPEN,
                                CODE_VAL_SASINARI_ID_MARKET_CLOSE, CODE_VAL_SASINARI_ID_MARKET_IOC })) {
                    // 指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
                    responseData.setIfd1GyakusasiHouhou(ExecuteMethod.MARKET.getId());
                }
            }
            responseData.setIfd1SasinariJyouken(api001ReqOrderData.getSasinariId());
            responseData.setIfd1TriggerPrice(api001ReqOrderData.getLatestTriggerPrice());
            responseData.setIfd1TriggerPriceZone(api001ReqOrderData.getLatestTriggerZone());
            responseData.setIfd1Price(api001ReqOrderData.getLimitPrice());
            responseData.setIfd2Limit(api001ReqOrderData.getDoneLimit());
        }
        
        // 注文種別がIFDの場合の値を設定する
        if (StringUtils.equals(orderKind, OrderClass.IFD.getId())) {
            // IFD1.執行方法をRBE注文種別と指成区分から設定する
            if (StringUtils.equals(api001ReqOrderData.getRbeOrderKind(), CODE_VAL_RBE_ORDER_KIND_NORMAL)) {
                // RBE注文種別が通常注文の場合
                if (StringUtils.equalsAny(api001ReqOrderData.getSasinariId(),
                        new String[] { CODE_VAL_SASINARI_ID_LIMIT, CODE_VAL_SASINARI_ID_LIMIT_OPEN,
                                CODE_VAL_SASINARI_ID_LIMIT_CLOSE, CODE_VAL_SASINARI_ID_LIMIT_TO_MARKET,
                                CODE_VAL_SASINARI_ID_LIMIT_IOC })) {
                    // 指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
                    responseData.setIfd1SasinariHouhou(ExecuteMethod.LIMIT.getId());
                } else if (StringUtils.equalsAny(api001ReqOrderData.getSasinariId(),
                        new String[] { CODE_VAL_SASINARI_ID_MARKET, CODE_VAL_SASINARI_ID_MARKET_OPEN,
                                CODE_VAL_SASINARI_ID_MARKET_CLOSE, CODE_VAL_SASINARI_ID_MARKET_IOC })) {
                    // 指成区分=指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
                    responseData.setIfd1SasinariHouhou(ExecuteMethod.MARKET.getId());
                }
            } else if (StringUtils.equals(api001ReqOrderData.getRbeOrderKind(), CODE_VAL_RBE_ORDER_KIND_SLO)) {
                // RBE注文種別が逆指値の場合
                responseData.setIfd1SasinariHouhou(ExecuteMethod.STOP.getId());
                if (StringUtils.equalsAny(api001ReqOrderData.getSasinariId(),
                        new String[] { CODE_VAL_SASINARI_ID_LIMIT, CODE_VAL_SASINARI_ID_LIMIT_OPEN,
                                CODE_VAL_SASINARI_ID_LIMIT_CLOSE, CODE_VAL_SASINARI_ID_LIMIT_TO_MARKET,
                                CODE_VAL_SASINARI_ID_LIMIT_IOC })) {
                    // 指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
                    responseData.setIfd1GyakusasiHouhou(ExecuteMethod.LIMIT.getId());
                } else if (StringUtils.equalsAny(api001ReqOrderData.getSasinariId(),
                        new String[] { CODE_VAL_SASINARI_ID_MARKET, CODE_VAL_SASINARI_ID_MARKET_OPEN,
                                CODE_VAL_SASINARI_ID_MARKET_CLOSE, CODE_VAL_SASINARI_ID_MARKET_IOC })) {
                    // 指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
                    responseData.setIfd1GyakusasiHouhou(ExecuteMethod.MARKET.getId());
                }
            }
            responseData.setIfd1SasinariJyouken(api001ReqOrderData.getSasinariId());
            responseData.setIfd1TriggerPrice(api001ReqOrderData.getLatestTriggerPrice());
            responseData.setIfd1TriggerPriceZone(api001ReqOrderData.getLatestTriggerZone());
            responseData.setIfd1Price(api001ReqOrderData.getLimitPrice());
            responseData.setIfd2Limit(api001ReqOrderData.getDoneLimit());
            // IFD2.執行方法をDONE RBE注文種別とDONE指成区分から設定する
            if (StringUtils.equals(api001ReqOrderData.getDoneRbeOrderKind(), CODE_VAL_RBE_ORDER_KIND_NORMAL)) {
                // DONE RBE注文種別が通常注文の場合
                if (StringUtils.equalsAny(api001ReqOrderData.getDoneSasinariId(),
                        new String[] { CODE_VAL_DONE_SASINARI_ID_LIMIT, CODE_VAL_DONE_SASINARI_ID_LIMIT_OPEN,
                                CODE_VAL_DONE_SASINARI_ID_LIMIT_CLOSE, CODE_VAL_DONE_SASINARI_ID_LIMIT_TO_MARKET,
                                CODE_VAL_DONE_SASINARI_ID_LIMIT_IOC })) {
                    // DONE指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
                    responseData.setIfd2SasinariHouhou(ExecuteMethod.LIMIT.getId());
                } else if (StringUtils.equalsAny(api001ReqOrderData.getDoneSasinariId(),
                        new String[] { CODE_VAL_DONE_SASINARI_ID_MARKET, CODE_VAL_DONE_SASINARI_ID_MARKET_OPEN,
                                CODE_VAL_DONE_SASINARI_ID_MARKET_CLOSE, CODE_VAL_DONE_SASINARI_ID_MARKET_IOC })) {
                    // DONE指成区分=指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
                    responseData.setIfd2SasinariHouhou(ExecuteMethod.MARKET.getId());
                }
            } else if (StringUtils.equals(api001ReqOrderData.getDoneRbeOrderKind(), CODE_VAL_RBE_ORDER_KIND_SLO)) {
                // DONE RBE注文種別が逆指値の場合
                responseData.setIfd2SasinariHouhou(ExecuteMethod.STOP.getId());
                if (StringUtils.equalsAny(api001ReqOrderData.getDoneSasinariId(),
                        new String[] { CODE_VAL_DONE_SASINARI_ID_LIMIT, CODE_VAL_DONE_SASINARI_ID_LIMIT_OPEN,
                                CODE_VAL_DONE_SASINARI_ID_LIMIT_CLOSE, CODE_VAL_DONE_SASINARI_ID_LIMIT_TO_MARKET,
                                CODE_VAL_DONE_SASINARI_ID_LIMIT_IOC })) {
                    // DONE指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
                    responseData.setIfd2GyakusasiHouhou(ExecuteMethod.LIMIT.getId());
                } else if (StringUtils.equalsAny(api001ReqOrderData.getDoneSasinariId(),
                        new String[] { CODE_VAL_DONE_SASINARI_ID_MARKET, CODE_VAL_DONE_SASINARI_ID_MARKET_OPEN,
                                CODE_VAL_DONE_SASINARI_ID_MARKET_CLOSE, CODE_VAL_DONE_SASINARI_ID_MARKET_IOC })) {
                    // DONE指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
                    responseData.setIfd2GyakusasiHouhou(ExecuteMethod.MARKET.getId());
                }
            }
            responseData.setIfd2SasinariJyouken(api001ReqOrderData.getDoneSasinariId());
            responseData.setIfd2TriggerPrice(api001ReqOrderData.getDoneTriggerPrice());
            responseData.setIfd2TriggerPriceZone(api001ReqOrderData.getDoneTriggerZone());
            responseData.setIfd2Price(api001ReqOrderData.getDonePrice());
        }
        
        // 発火状態をRBE注文ステータスから設定する
        if (StringUtils.equals(api001ReqOrderData.getRbeOrderStatus(), CODE_VAL_RBE_ORDER_STATUS_TRIGGER)) {
            responseData.setWorkingStatus(Boolean.TRUE.toString());
        } else {
            responseData.setWorkingStatus(Boolean.FALSE.toString());
        }
        
        responseData.setPaymentDeadline(api001ReqOrderData.getPaymentLimit());
        
        // 信用取引区分を算出する
        Optional<MarginTradeKbn> marginTradeKbnOptional = Optional.ofNullable(MarginTradeKbn
                .valueOfKbn(api001ReqOrderData.getPaymentLimit(), api001ReqOrderData.getIppanMgPaymentKbn()));
        marginTradeKbnOptional.ifPresent(marginTradeKbnEnum -> {
            if (StringUtils.equalsAny(api001ReqOrderData.getPaymentLimit(), new String[] {
                    CODE_VAL_PAYMENT_LIMIT_SHORT_TERM, CODE_VAL_PAYMENT_LIMIT_LONG_TERM_INVENTORY_LIMITED })) {
                // 弁済期限が"A"(一般信用売り-短期銘柄)、"B"(一般信用売り-長期在庫制限有り銘柄)の場合
                if (StringUtils.equals(api001ReqOrderData.getIppanMgPaymentKbn(),
                        CODE_VAL_IPPAN_MG_PAYMENTKBN_UNLIMITED)) {
                    // 一般信用売弁済期限年月日区分が無期限の場合はそのまま出力する
                    responseData.setMarginTradeTypeTextCalculation(marginTradeKbnEnum.getMarginTradeKbn());
                } else {
                    // 一般信用売弁済期限年月日区分が無期限以外の場合は、一般信用売弁済期限年月日数を編集して設定する
                    if (marginTradeKbnEnum == MarginTradeKbn.LONG_TERM_YEAR
                            || marginTradeKbnEnum == MarginTradeKbn.SHORT_TERM_YEAR) {
                        responseData.setMarginTradeTypeTextCalculation(Integer.valueOf(api001ReqOrderData.getIppanMgPaymentLimit()).toString()
                                .concat(DOMESTIC_MARGIN_TRADE_TERM_YEAR));
                    } else if (marginTradeKbnEnum == MarginTradeKbn.LONG_TERM_MONTH
                            || marginTradeKbnEnum == MarginTradeKbn.SHORT_TERM_MONTH) {
                        responseData.setMarginTradeTypeTextCalculation(Integer.valueOf(api001ReqOrderData.getIppanMgPaymentLimit()).toString()
                                .concat(DOMESTIC_MARGIN_TRADE_TERM_MONTH));
                    } else if (marginTradeKbnEnum == MarginTradeKbn.LONG_TERM_DAY
                            || marginTradeKbnEnum == MarginTradeKbn.SHORT_TERM_DAY) {
                        responseData.setMarginTradeTypeTextCalculation(Integer.valueOf(api001ReqOrderData.getIppanMgPaymentLimit()).toString()
                                .concat(DOMESTIC_MARGIN_TRADE_TERM_DAY));
                    }
                }
            } else {
                // 弁済期限が"A"、"B"以外の場合
                responseData.setMarginTradeTypeTextCalculation(marginTradeKbnEnum.getMarginTradeKbn());
            }
        });
        
        responseData.setAutoOrderClass(api001ReqOrderData.getAutoOrderKind());
        responseData.setRbeChumonShubetsu(api001ReqOrderData.getRbeOrderKind());
        responseData.setSashinariKbn(api001ReqOrderData.getSasinariId());
        responseData.setSashine(api001ReqOrderData.getLimitPrice());
        responseData.setTriggerZone(api001ReqOrderData.getLatestTriggerZone());
        responseData.setTriggerNedan(api001ReqOrderData.getLatestTriggerPrice());
        responseData.setOcoSasinariKbn(api001ReqOrderData.getLatestOcoSasinariId());
        responseData.setOcoSashine(api001ReqOrderData.getLatestOcoPrice());
        responseData.setDoneSasinariKbn(api001ReqOrderData.getDoneSasinariId());
        responseData.setDoneSashine(api001ReqOrderData.getDonePrice());
        responseData.setDoneRbeOrderKind(api001ReqOrderData.getDoneRbeOrderKind());
        responseData.setDoneTriggerZone(api001ReqOrderData.getDoneTriggerZone());
        responseData.setDoneTriggerNedan(api001ReqOrderData.getDoneTriggerPrice());
        responseData.setDoneOcoSasinariKbn(api001ReqOrderData.getDoneOcoSasinariId());
        responseData.setDoneOcoSashine(api001ReqOrderData.getDoneOcoPrice());
        responseData.setDoneYuukouKigen(api001ReqOrderData.getDoneLimit());
        responseData.setRbeOrderStatus(api001ReqOrderData.getRbeOrderStatus());
        responseData.setPaymentDeadlineDate(api001ReqOrderData.getIppanMgPaymentLimit());
        responseData.setDateKbn(api001ReqOrderData.getIppanMgPaymentKbn());
        responseData.setComIdR(api001ReqOrderData.getComIdR());

        // レスポンスを返却する 
        return IfaCommonUtil.createDataList(List.of(responseData), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
    
    /**
     * アクションID：A002a
     * アクション名：注文発注前の国内株式注文登録
     * Dto リクエスト：IfaMarginNewOrderCancelConfirmA002aRequestDto
     * Dto レスポンス：IfaMarginNewOrderCancelConfirmA002aResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaMarginNewOrderCancelConfirmA002aResponseDto> orderPlacementA002a(
            IfaMarginNewOrderCancelConfirmA002aRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginNewOrderCancelConfirmServiceImplL.orderPlacementA002a");
        }
        
        // 顧客共通情報を取得する
        final CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // 口座に対する権限チェック
        final InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(customerCommon.getButenCode());
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber());
        
        final OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        if (StringUtils.equals(outputFct001Dto.getTargetCustomerRefAuthFlag(),
                TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
            // 対象顧客参照権限有無が権限なしの場合、権限なしエラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(MESSAGE_ACCOUNT_NOT_EXISTS,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
        } else if (StringUtils.equals(outputFct001Dto.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
            // 取引停止口座の場合、取引停止エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_OUT_OF_SERVICE,
                    IfaCommonUtil.getMessage(MESSAGE_OUT_OF_SERVICE));
        }
        
        // 媒介可否チェック
        final InputFct003Dto inputFct003Dto = new InputFct003Dto();
        
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(CODE_VAL_SECURITY_CLASS_DSTOCK);
        inputFct003Dto.setTradeCd(dtoReq.getTradeCd());
        
        final OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        
        // 媒介可否リスト.媒介可否に媒介可が存在するか判定する
        if (outputFct003Dto.getMediateProprietyList().stream()
                .map(mediateProprieties -> mediateProprieties.getMediatePropriety()).noneMatch(
                        mediatePropriety -> StringUtils.equals(MediateAbleTradeFlag.ARI.getId(), mediatePropriety))) {
            // 存在しない場合、媒介不可エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_MEDIATE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(MESSAGE_MEDIATE_UNAVAILABLE, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_DMARGIN) }));
        }

        // ユーザー共通情報を取得する
        final UserAccount userAccount = IfaCommonUtil.getUserAccount();

        // ユーザ共通情報 .CCSログイン用IDが未設定(Null または空文字）の場合：取引不可エラーを返す
        if (StringUtils.isEmpty(userAccount.getCcsUserId())) {
            DataList<IfaMarginNewOrderCancelConfirmA002aResponseDto> response = IfaCommonUtil.createDataList(
                    List.of(),
                    ErrorLevel.FATAL,
                    MESSAGE_CCSID_UNREGISTERED,
                    IfaCommonUtil.getMessage(MESSAGE_CCSID_UNREGISTERED)
            );

            return response;
        }
        
        // 取消対象の国内株式注文情報を取得する
        final IfaMarginNewOrderCancelConfirmSql001SubResponseModel selSql001SubRes = dao
                .selectIfaMarginNewOrderCancelConfirmSql001Sub(dtoReq.getEcOrderNo(), customerCommon.getButenCode(),
                        dtoReq.getOrderDayTime());
        
        // 発注前の国内株式注文登録データを作成する
        final IfaMarginNewOrderCancelConfirmSql001RequestModel insSql001Req = new IfaMarginNewOrderCancelConfirmSql001RequestModel();
        
        if (selSql001SubRes != null) {
            // 取消対象の国内株式注文情報が存在する場合
            insSql001Req.setIfaOrderNo(selSql001SubRes.getIfaOrderNo());
            insSql001Req.setIfaOrderSubNo(
                    String.valueOf(Integer.parseInt(selSql001SubRes.getIfaOrderSubNo()) + SQL_COUNT_ONE));
            insSql001Req.setButenCode(selSql001SubRes.getButenCode());
            insSql001Req.setAccountNumber(selSql001SubRes.getAccountNumber());
            insSql001Req.setCustomerId(selSql001SubRes.getCustomerId());
            insSql001Req.setSpecificKbn(selSql001SubRes.getSpecificKbn());
            insSql001Req.setBrandCd(dtoReq.getBrandCode());
            insSql001Req.setMarket(dtoReq.getMarket());
            insSql001Req.setTradeKbn(dtoReq.getTradeCd());
            insSql001Req.setQuantity(dtoReq.getQuantity());
            insSql001Req.setLimit(dtoReq.getPeriod());
            insSql001Req.setPaymentLimit(dtoReq.getPaymentDeadline());
            insSql001Req.setOrderSyubetsu(selSql001SubRes.getOrderSyubetsu());
            insSql001Req.setOrderSyubetsuList(selSql001SubRes.getOrderSyubetsuList());
            insSql001Req.setKanyuKbn(selSql001SubRes.getKanyuKbn());
            insSql001Req.setJutyuKbn(selSql001SubRes.getJutyuKbn());
            insSql001Req.setCheckInsider(selSql001SubRes.getCheckInsider());
            insSql001Req.setCheckSor(selSql001SubRes.getCheckSor());
            insSql001Req.setCheckCompWrnAlert(selSql001SubRes.getCheckCompWrnAlert());
            insSql001Req.setShikinSeikakuKbn(selSql001SubRes.getShikinSeikakuKbn());
            insSql001Req.setUserId(selSql001SubRes.getUserId());
            insSql001Req.setCancelUserId(userAccount.getCcsUserId());
            insSql001Req.setTesuuryouKbn(selSql001SubRes.getTesuuryouKbn());
            insSql001Req.setAlterFlg(selSql001SubRes.getAlterFlg());
            insSql001Req.setAutoOrderKind(dtoReq.getAutoOrderClass());
            insSql001Req.setRbeOrderKind(dtoReq.getRbeChumonShubetsu());
            insSql001Req.setSasinariKbn(dtoReq.getSashinariKbn());
            insSql001Req.setPrice(dtoReq.getSashine());
            insSql001Req.setTriggerZone(dtoReq.getTriggerZone());
            insSql001Req.setTriggerPrice(dtoReq.getTriggerNedan());
            insSql001Req.setOcoSasinariKbn(dtoReq.getOcoSasinariKbn());
            insSql001Req.setOcoSashine(dtoReq.getOcoSashine());
            insSql001Req.setDoneSasinariKbn(dtoReq.getDoneSasinariKbn());
            insSql001Req.setDoneSashine(dtoReq.getDoneSashine());
            insSql001Req.setDoneRbeOrderKind(dtoReq.getDoneRbeOrderKind());
            insSql001Req.setDoneTriggerZone(dtoReq.getDoneTriggerZone());
            insSql001Req.setDoneTriggerNedan(dtoReq.getDoneTriggerNedan());
            insSql001Req.setDoneOcoSasinariKbn(dtoReq.getDoneOcoSasinariKbn());
            insSql001Req.setDoneOcoSashine(dtoReq.getDoneOcoSashine());
            insSql001Req.setDoneLimit(dtoReq.getDoneYuukouKigen());
            insSql001Req.setJoZeiKbn(selSql001SubRes.getJoZeiKbn());
            insSql001Req.setAlterOrderNum(selSql001SubRes.getAlterOrderNum());
            insSql001Req.setRbeOrderStatus(selSql001SubRes.getRbeOrderStatus());
            insSql001Req.setOrderNum(dtoReq.getEcOrderNo());
            insSql001Req.setComIdR(dtoReq.getComIdR());
        } else {
            /* 
             * 取消対象の国内株式注文情報が存在しない場合
             * IFA注文番号はINSERT時にこのbeanに設定されるため、この時点では空のまま.
             */
            insSql001Req.setIfaOrderSubNo(IFA_ORDER_SUB_NO_INITIAL);
            insSql001Req.setButenCode(customerCommon.getButenCode());
            insSql001Req.setAccountNumber(customerCommon.getAccountNumber());
            insSql001Req.setCustomerId(customerCommon.getCustomerCode());
            insSql001Req.setSpecificKbn(customerCommon.getSpecificAccountType());
            insSql001Req.setBrandCd(dtoReq.getBrandCode());
            insSql001Req.setMarket(dtoReq.getMarket());
            insSql001Req.setTradeKbn(dtoReq.getTradeCd());
            insSql001Req.setQuantity(dtoReq.getQuantity());
            insSql001Req.setLimit(dtoReq.getPeriod());
            insSql001Req.setPaymentLimit(dtoReq.getPaymentDeadline());
            insSql001Req.setOrderSyubetsu(dtoReq.getOrderKind());
            
            // 注文種別 （一覧）を設定する
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())) {
                // 注文種別が通常/逆指値の場合
                if (StringUtils.equals(dtoReq.getSasinariHouhou(), ExecuteMethod.STOP.getId())) {
                    // 通常/逆指値.執行方法が逆指値の場合
                    insSql001Req.setOrderSyubetsuList(CODE_VAL_LIST_ORDER_CLASS_STOP);
                } else {
                    // 通常/逆指値.執行方法が逆指値以外の場
                    insSql001Req.setOrderSyubetsuList(CODE_VAL_LIST_ORDER_CLASS_NORMAL);
                }
            } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
                //　注文種別=OCO の場合
                insSql001Req.setOrderSyubetsuList(CODE_VAL_LIST_ORDER_CLASS_OCO);
            } else if (StringUtils.equalsAny(dtoReq.getOrderKind(),
                    new String[] { OrderClass.IFD.getId(), OrderClass.IFDOCO.getId() })) {
                //　注文種別がIFD、またはIFDOCOの場合
                if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), ExecuteMethod.STOP.getId())) {
                    // IFD1.執行方法が逆指値の場合
                    insSql001Req.setOrderSyubetsuList(CODE_VAL_LIST_ORDER_CLASS_IFD1_STOP);
                } else {
                    // IFD1.執行方法が逆指値以外の場合
                    insSql001Req.setOrderSyubetsuList(CODE_VAL_LIST_ORDER_CLASS_IFD1_NORMAL);
                }
            }
            
            insSql001Req.setUserId(userAccount.getCcsUserId());
            insSql001Req.setCancelUserId(userAccount.getCcsUserId());
            insSql001Req.setAutoOrderKind(dtoReq.getAutoOrderClass());
            insSql001Req.setRbeOrderKind(dtoReq.getRbeChumonShubetsu());
            insSql001Req.setSasinariKbn(dtoReq.getSashinariKbn());
            insSql001Req.setPrice(dtoReq.getSashine());
            insSql001Req.setTriggerZone(dtoReq.getTriggerZone());
            insSql001Req.setTriggerPrice(dtoReq.getTriggerNedan());
            insSql001Req.setOcoSasinariKbn(dtoReq.getOcoSasinariKbn());
            insSql001Req.setOcoSashine(dtoReq.getOcoSashine());
            insSql001Req.setDoneSasinariKbn(dtoReq.getDoneSasinariKbn());
            insSql001Req.setDoneSashine(dtoReq.getDoneSashine());
            insSql001Req.setDoneRbeOrderKind(dtoReq.getDoneRbeOrderKind());
            insSql001Req.setDoneTriggerZone(dtoReq.getDoneTriggerZone());
            insSql001Req.setDoneTriggerNedan(dtoReq.getDoneTriggerNedan());
            insSql001Req.setDoneOcoSasinariKbn(dtoReq.getDoneOcoSasinariKbn());
            insSql001Req.setDoneOcoSashine(dtoReq.getDoneOcoSashine());
            insSql001Req.setDoneLimit(dtoReq.getDoneYuukouKigen());
            insSql001Req.setRbeOrderStatus(dtoReq.getRbeOrderStatus());
            insSql001Req.setOrderNum(dtoReq.getEcOrderNo());
            insSql001Req.setComIdR(dtoReq.getComIdR());
        }
        
        // 共通の項目を設定する
        insSql001Req.setBrokerCode(customerCommon.getBrokerCode());
        insSql001Req.setIntermediaryEmpCd(customerCommon.getBrokerChargeCode());
        insSql001Req.setCreateUser(userAccount.getUserId());
        insSql001Req.setUpdateUser(userAccount.getUserId());

        String dailyCreditKbn = "";
        // リクエスト.弁済期限が日計り(D)の場合は、'1'
        if (PAYMENT_DDEADLINE_D.equals(dtoReq.getPaymentDeadline())){
            dailyCreditKbn = DAILY_CREDIT_KBN_1;
        // リクエスト.弁済期限が日計りH(E)の場合は、'2'
        } else if (PAYMENT_DDEADLINE_E.equals(dtoReq.getPaymentDeadline())) {
            dailyCreditKbn = DAILY_CREDIT_KBN_2;
        // リクエスト.弁済期限が上記以外の場合は、'△'
        } else {
            dailyCreditKbn = DAILY_CREDIT_KBN_OTHER;
        }
        insSql001Req.setDailyCreditKbn(dailyCreditKbn); // 一日信用区分
        insSql001Req.setPaymentDeadlineDate(dtoReq.getPaymentDeadlineDate()); // 弁済期限年月日数
        insSql001Req.setDateKbn(dtoReq.getDateKbn()); // 年月日区分

        try {
            // 発注前の国内株式注文を登録する
            final int insSql001Res = dao.insertIfaMarginNewOrderCancelConfirmSql001(insSql001Req);
            if (insSql001Res != SQL_COUNT_ONE) {
                // 登録件数が1件ではない場合、DB登録エラーを返す
                return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_PRE_ORDER_EXECUTION_FAILED,
                        IfaCommonUtil.getMessage(MESSAGE_PRE_ORDER_EXECUTION_FAILED));
            }
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("IfaMarginNewOrderCancelConfirmServiceImplL.orderPlacementA002a", e);
            }
            // 発注前の国内株式注文を登録でエラーが発生した場合、DB登録エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_PRE_ORDER_EXECUTION_FAILED,
                    IfaCommonUtil.getMessage(MESSAGE_PRE_ORDER_EXECUTION_FAILED));
        }
        
        // 国内株式注文登録時のIFA注文番号、IFA注文サブ番号をレスポンスデータに設定する
        final IfaMarginNewOrderCancelConfirmA002aResponseDto responseData = new IfaMarginNewOrderCancelConfirmA002aResponseDto();
        responseData.setIfaOrderNo(insSql001Req.getIfaOrderNo());
        responseData.setIfaOrderSubNo(insSql001Req.getIfaOrderSubNo());
        
        // レスポンスを返却する 
        return IfaCommonUtil.createDataList(List.of(responseData), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
    
    /**
     * アクションID：A002b
     * アクション名：注文発注
     * Dto リクエスト：IfaMarginNewOrderCancelConfirmA002bRequestDto
     * Dto レスポンス：IfaMarginNewOrderCancelConfirmA002bResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaMarginNewOrderCancelConfirmA002bResponseDto> orderPlacementA002b(
            IfaMarginNewOrderCancelConfirmA002bRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginNewOrderCancelConfirmServiceImplL.orderPlacementA002b");
        }
        
        // 顧客共通情報を取得する
        final CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        // ユーザー共通情報を取得する
        final UserAccount userAccount = IfaCommonUtil.getUserAccount();
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 国内株式・信用注文取消APIのリクエストデータを作成する
        StockCancelOrderInData api002InData = new StockCancelOrderInData();
        
        api002InData.setTransactionId(StringUtils.repeat(PADDING_CHAR_ZERO, PADDING_LENGTH_TRANSACTION_ID));
        api002InData.setCommandNum(StringUtils.repeat(PADDING_CHAR_ZERO, PADDING_LENGTH_COMMAND_NUM));
        api002InData.setCommandDate(StringUtils.repeat(PADDING_CHAR_ZERO, DateUtil.YYYYMMDD.length()));
        api002InData.setButenCd(customerCommon.getButenCode());
        api002InData.setKozaNo(StringUtil.fillLeft(customerCommon.getAccountNumber(), PADDING_CHAR_ZERO,
                PADDING_LENGTH_ACCOUNT_NUMBER));
        api002InData.setAccountId(StringUtils.repeat(PADDING_CHAR_ZERO, PADDING_LENGTH_API002_ACCOUNT_ID));
        api002InData.setNumber(StringUtils.repeat(PADDING_CHAR_ZERO, PADDING_LENGTH_NUMBER_PER_ACCOUNT));
        api002InData.setOrigin(API002_ORIGIN);
        api002InData.setOrderType(API002_ORDER_TYPE);
        api002InData.setOrderNum(dtoReq.getRequest().getEcOrderNo());
        api002InData.setCxlCallcenterKbn(API002_CXL_CALLCENTER_KBN);
        api002InData.setCxlUserId(userAccount.getCcsUserId());
        api002InData.setIpAddress(API002_IP_ADDRESS);
        
        StockCancelOrderOutData api002Res = null;
        final IfaMarginNewOrderCancelConfirmSql002RequestModel updSql002Req = new IfaMarginNewOrderCancelConfirmSql002RequestModel();

        // 国内株式・信用注文取消APIを呼び出す
        try {
            api002Res = nriApiService.stockCancelOrder(api002InData);
            apiErrorUtil.checkApiResponse(api002Res.getShubetu(), api002Res.getCode(), api002Res.getMessage());

        } catch(Exception e) {
            // システムエラー発生時、DBを更新してエラーレスポンスを返す
            updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
            updSql002Req.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
            updSql002Req.setUpdateUser(userAccount.getUserId());
            dao.updateIfaMarginNewOrderCancelConfirmSql002b(updSql002Req);

            return IfaCommonUtil.createDataList(
                    List.of(),
                    ErrorLevel.FATAL,
                    MESSAGE_ORDER_EXECUTION_FAILED,
                    IfaCommonUtil.getMessage(MESSAGE_ORDER_EXECUTION_FAILED)
            );
        }

        // 注文発注後の国内株式注文の更新データを作成する
        updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
        updSql002Req.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
        updSql002Req.setShubetu(api002Res.getShubetu());
        updSql002Req.setCode(api002Res.getCode());
        updSql002Req.setErrMessage(api002Res.getMessage());
        updSql002Req.setUpdateUser(userAccount.getUserId());
        updSql002Req.setAcceptDate(api002Res.getAcceptDate());
        updSql002Req.setError(apiErrorUtil.isFatal());
        
        if (!apiErrorUtil.isFatal()) {
            // APIの応答が正常またはワーニングの場合の値を設定する
            updSql002Req.setAcceptTime(api002Res.getAcceptTime());
        } 
        
        int updSql002Res = 0;
        try {
            // 発注後に国内株式注文を更新する
            updSql002Res = dao.updateIfaMarginNewOrderCancelConfirmSql002(updSql002Req);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("IfaMarginNewOrderCancelConfirmServiceImplL.orderPlacementA002b", e);
            }
        }
        
        if (apiErrorUtil.isFatal()) {
            // エラー判定結果が「致命的」の場合、APIのエラー情報を返却する
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_ORDER_EXECUTION_FAILED,
                    IfaCommonUtil.getMessage(MESSAGE_ORDER_EXECUTION_FAILED,
                            new String[] { api002Res.getCode(), api002Res.getMessage() }));
        }
        
        // レスポンスデータを作成する
        final IfaMarginNewOrderCancelConfirmA002bResponseDto responseData = new IfaMarginNewOrderCancelConfirmA002bResponseDto();
        responseData.setOrderDayTime(
                String.join(StringUtils.SPACE, new String[] { api002Res.getAcceptDate(), api002Res.getAcceptTime() }));
        responseData.setRequest(dtoReq.getRequest());
        
        if (updSql002Res != SQL_COUNT_ONE) {
            // 更新件数が1件ではない場合、レスポンスデータにDB更新のワーニング情報を設定して返す
            apiErrorUtil.addDbError(MESSAGE_POST_ORDER_EXECUTION_WARNING, null);
        }
        
        return apiErrorUtil.createDataList(List.of(responseData), "");
    }
}
