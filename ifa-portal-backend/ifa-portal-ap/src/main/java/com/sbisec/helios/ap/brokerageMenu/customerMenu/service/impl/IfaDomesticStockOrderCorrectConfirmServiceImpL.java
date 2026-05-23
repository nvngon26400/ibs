package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.component.Fct027;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct027Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct027Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDomesticStockOrderCorrectConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCorrectConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCorrectConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCorrectConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCorrectConfirmSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectConfirmA001aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectConfirmA001aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectConfirmA001bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectConfirmA001bDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDomesticStockOrderCorrectConfirmService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.exception.ApiError;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.StockModifyOrderAutoIn;
import jp.co.sbisec.pcenter.dto.yanap.StockModifyOrderAutoInData;
import jp.co.sbisec.pcenter.dto.yanap.StockModifyOrderAutoOutData;
import jp.co.sbisec.pcenter.dto.yanap.StockModifyOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.StockModifyOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.StockModifyOrderOutData;

/**
 * 画面ID：SUB0202_0208-03_2
 * 画面名：国内株式注文訂正確認
 *
 * @author SCSK 矢口
 *
 *         2024/04/16 新規作成
 */
@Component(value = "cmpIfaDomesticStockOrderCorrectConfirmService")
public class IfaDomesticStockOrderCorrectConfirmServiceImpL implements IfaDomesticStockOrderCorrectConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticStockOrderCorrectConfirmServiceImpL.class);
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct006 fct006;
    
    @Autowired
    private Fct021 fct021;
    
    @Autowired
    private Fct027 fct027;
    
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private IfaDomesticStockOrderCorrectConfirmDao dao;
    
    @Autowired
    private ApiWrapper apiWrapper;
    
    /** 権限なしエラー */
    private static final String ERRORS_BUTEN_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 取引停止口座エラー */
    private static final String ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** 取引不可エラー */
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 注意情報エラー */
    private static final String ERRORS_CMN_NOTICE_ERROR_CHECK = "errors.cmn.noticeErrorCheck";
    
    /** お知らせエラー */
    private static final String ERRORS_INFORMATION_CHECK = "errors.informationCheck";
    
    /** アラート発生エラー */
    private static final String ERRORS_CMN_INFORMATION_OCCURS = "errors.cmn.information.occurs";
    
    /** DB登録エラー */
    private static final String ERRORS_FRS_PRE_ORDER_EXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    /** DB更新エラー */
    private static final String WARNINGS_FRS_POST_ORDER_EXECUTION_COMPLETED = "warnings.frs.postOrderExecution.completed";
    
    /** APIエラー */
    private static final String ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED = "errors.cmn.orderExecutionModify.failed";

    /** SOR対象外ワーニング */
    private static final String WARNINGS_FRS_OUT_OF_SOR_ORDER_COMPLETED = "warnings.frs.outOfSOROrder.completed";

    /** 対象顧客参照権限有無：権限なし */
    private static final String AUTH_ERROR_VALUE = "0";
    
    /** 取引停止フラグ：取引停止口座 */
    private static final String TRADE_SUSPEND_VALUE = "1";
    
    /** 証券金銭種別：国内株式 */
    private static final String SECURITY_MONEY_CLASS_DOMESTIC = "01";
    
    /** 対象取引（メッセージ表示用） */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 媒介可取引有無：あり */
    private static final String MEDIATE_ABLE_VALUE = "1";
    
    /** FCT021 国籍コード 設定値 */
    private static final String NATIONALITY_CODE_VALUE = "99";
    
    /** FCT021 通貨コード 設定値 */
    private static final String CURRENCY_CODE_VALUE = "999";
    
    /** 注意情報エラー有無：あり */
    private static final String NOTE_INFO_ERR_FLAG_EXIST = "1";
    
    /** お知らせエラー有無：あり */
    private static final String NOTE_LIMIT_ERR_FLAG_EXIST = "1";
    
    /** 注意情報アラート有無：あり */
    private static final String NOTE_INFO_ALERT_FLAG_EXIST = "1";
    
    /** お知らせアラート有無：あり */
    private static final String NOTE_LIMIT_ALERT_FLAG_EXIST = "1";
    
    /** 注意情報アラート確認チェックボックス：OFF */
    private static final String NOTICE_INFO_ALERT_CHECK_BOX_OFF = "0";
    
    /** お知らせアラート確認チェックボックス：OFF */
    private static final String NOTICE_ALERT_CHECK_BOX_OFF = "0";
    
    /** 取引種別（国内株式）.現物買付 */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_SPOT_BUY = "1";
    
    /** 取引種別（国内株式）.現物売却 */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_SPOT_SELL = "2";
    
    /** 国内外国区分.国内 */
    private static final String DOMESTIC_FOREIGN_TYPE_DOMESTIC = "0";
    
    /** 商品区分.株式 */
    private static final String SECURITY_TYPE_STOCK = "1 ";
    
    /** コンプラチェック種類.買付注文 */
    private static final String COMPLA_CHECK_KIND_BUY_ORDER = "1";
    
    /** コンプラランクチェック判定結果.ノーマル */
    private static final String COMPLA_RANK_JUDGEMENT_RESULT_NORMAL = "0";
    
    /** コンプラランクチェック判定結果.アラート */
    private static final String COMPLA_RANK_JUDGEMENT_RESULT_ALERT = "1";
    
    /** コンプラランクチェック確認チェックボックス：ON */
    private static final String INVITATION_CHECK_BOX_ON = "1";
    
    /** 規制銘柄区分.規制銘柄 */
    private static final String REG_KBN_REG_BRAND = "1";
    
    /** 取引注意情報（銘柄）確認チェックボックス：ON */
    private static final String TRADING_CAUTION_INFORMATION_CHECK_BOX_ON = "1";
    
    /** SQL001 IFA注文サブ番号 設定値：新規注文 */
    private static final String IFA_ORDER_SUB_NO_VALUE = "1";
    
    /** 預り区分（国内）.特定 */
    private static final String DOMESTIC_DEPOSIT_TYPE_SPECIFIC = "0";
    
    /** 預り区分（国内）.一般 */
    private static final String DOMESTIC_DEPOSIT_TYPE_GENERAL = "1";
    
    /** 預り区分（国内）.旧NISA */
    private static final String DOMESTIC_DEPOSIT_TYPE_CLASSIC_NISA = "4";
    
    /** 預り区分（国内）.Jr特定 */
    private static final String DOMESTIC_DEPOSIT_TYPE_JR_SPECIFIC = "5";
    
    /** 預り区分（国内）.Jr一般 */
    private static final String DOMESTIC_DEPOSIT_TYPE_JR_GENERAL = "6";
    
    /** 預り区分（国内）.JrNISA */
    private static final String DOMESTIC_DEPOSIT_TYPE_JR_NISA = "7";
    
    /** 預り区分（国内）.JrNISA（継続管理勘定） */
    private static final String DOMESTIC_DEPOSIT_TYPE_JR_NISA_J= "J";

    /** SQL001 注文状況 設定値：1.訂正 */
    private static final String ORDER_STATUS_VALUE = "1";
    
    /** DONE状態：DONE */
    private static final String DONE_STATE_DONE = "DONE";
    
    /** 注文種別.通常/逆指値 */
    private static final String ORDER_CLASS_NORMAL = "1";
    
    /** 注文種別.OCO */
    private static final String ORDER_CLASS_OCO = "2";
    
    /** 注文種別.IFD */
    private static final String ORDER_CLASS_IFD = "3";
    
    /** 注文種別.IFDOCO */
    private static final String ORDER_CLASS_IFDOCO = "4";
    
    /** 執行方法.指値 */
    private static final String EXECUTE_METHOD_PRICE_LIMIT = "1";
    
    /** 執行方法.成行 */
    private static final String EXECUTE_METHOD_MARKET_ORDER = "2";
    
    /** 執行方法.逆指値 */
    private static final String EXECUTE_METHOD_REVERSE_PRICE_LIMIT = "3";
    
    /** 訂正SOR注文区分：訂正SOR */
    private static final String CORRECT_SOR_ORDER_CLASSFICATION_SOR = "1";

    /** 選択市場：当社優先市場／SOR */
    private static final String SELECT_MARKET_SOR = "A";

    /** SQL001 注文種別（一覧）設定値：通常注文 */
    private static final String ORDER_SYUBETSU_LIST_NORMAL = "0";
    
    /** SQL001 注文種別（一覧）設定値：逆指値注文 */
    private static final String ORDER_SYUBETSU_LIST_REVERSE_PRICE_LIMIT = "1";
    
    /** SQL001 注文種別（一覧）設定値：OCO注文 */
    private static final String ORDER_SYUBETSU_LIST_OCO = "2";
    
    /** SQL001 注文種別（一覧）設定値：IFD1注文（通常） */
    private static final String ORDER_SYUBETSU_LIST_IFD1_NORMAL = "3";
    
    /** SQL001 注文種別（一覧）設定値：IFD1注文（逆指値） */
    private static final String ORDER_SYUBETSU_LIST_IFD1_REVERSE_PRICE_LIMIT = "4";
    
    /** SQL001 注文種別（一覧）設定値：IFD2注文（通常） */
    private static final String ORDER_SYUBETSU_LIST_IFD2_NORMAL = "5";
    
    /** SQL001 注文種別（一覧）設定値：IFD2注文（逆指値） */
    private static final String ORDER_SYUBETSU_LIST_IFD2_REVERSE_PRICE_LIMIT = "6";
    
    /** SQL001 注文種別（一覧）設定値：IFD2注文（OCO） */
    private static final String ORDER_SYUBETSU_LIST_IFD2_OCO = "7";
    
    /** コンプラチェックボックス文言.ワーニング申請・承認済 */
    private static final String COMPLA_CHECK_BOX_WORDING_APPROVED = "1";
    
    /** コンプラチェックボックス文言.勧誘なし */
    private static final String COMPLA_CHECK_BOX_WORDING_NONE = "2";
    
    /** SQL001 アラート内容確認.コンプラチェックワーニング確認 設定値：コンプラランクチェック.チェックボックス文言=勧誘なし の場合 */
    private static final String CHECK_COMP_WRN_ALERT_NONE = "2";
    
    /**
     * SQL001 アラート内容確認.コンプラチェックワーニング確認 設定値：コンプラランクチェック.チェックボックス文言≠ワーニング申請・承認済、勧誘なし
     * の場合
     */
    private static final String CHECK_COMP_WRN_ALERT_OTHER = "0";
    
    /** SQL001 訂正区分 設定値：1 */
    private static final String ALTER_FLG_VALUE = "1";

    /** API001 リクエスト（訂正SOR注文区分）：訂正SOR"1" */
    private static final String API001_SOR_MODIFY_CODE_CORRECT_SOR = "1";
    
    /** API001 リクエスト（訂正SOR注文区分）：通常訂正" " */
    private static final String API001_SOR_MODIFY_CODE_NORMAL = " ";
    
    /** API002 自動注文種別 設定値：IFD親注文 */
    private static final String AUTO_ORDER_KIND_VALUE = "IF  ";
    
    /** API002 DONE指成区分 注文種別≠IFDの場合の設定値 */
    private static final String DONE_SASINARI_KBN_NONE = " ";
    
    /** API002 DONE指値（訂正値段） 注文種別≠IFDの場合の設定値 */
    private static final String DONE_SASHINE_NONE = "0000000000";
    
    /** API002 DONERBE注文種別 設定値：通常注文 */
    private static final String DONE_RBE_ORDER_KIND_NORMAL = "   ";
    
    /** API002 DONERBE注文種別 設定値：逆指値注文 */
    private static final String DONE_RBE_ORDER_KIND_SLO = "SLO";
    
    /** API002 DONERBE注文種別 設定値：DONE注文がOCO注文 */
    private static final String DONE_RBE_ORDER_KIND_OCO = "OCO";
    
    /** API002 DONERBE注文種別 注文種別≠IFDの場合の設定値 */
    private static final String DONE_RBE_ORDER_KIND_NONE = "   ";
    
    /** API002 DONEトリガ発動ゾーン 設定値：以下 */
    private static final String DONE_TRIGGER_ZONE_UNDER = "1";
    
    /** API002 DONEトリガ発動ゾーン 注文種別≠IFDの場合の設定値 */
    private static final String DONE_TRIGGER_ZONE_NONE = " ";
    
    /** API002 DONEトリガ値段（訂正値段） 注文種別≠IFDの場合の設定値 */
    private static final String DONE_TRIGGER_PRICE_NONE = "0000000000";
    
    /** API002 DONEOCO指成区分 注文種別≠IFDOCOの場合の設定値 */
    private static final String DONE_OCO_SASINARI_KBN_NONE = " ";
    
    /** API002 DONEOCO指成区分 注文種別≠IFDOCOの場合の設定値 */
    private static final String DONE_OCO_SASHINE_NONE = "0000000000";
    
    /** API RBE注文種別 設定値：通常注文 */
    private static final String RBE_ORDER_KIND_NORMAL = "   ";
    
    /** API RBE注文種別 設定値：逆指値注文 */
    private static final String RBE_ORDER_KIND_SLO = "SLO";
    
    /** API RBE注文種別 設定値：OCO注文 */
    private static final String RBE_ORDER_KIND_OCO = "OCO";
    
    /** API 指値（訂正値段） 注文種別≠通常/逆指値,OCOの場合の設定値 */
    private static final String PRICE_NONE = "0000000000";
    
    /** 発火状態：発火前 */
    private static final String WORKING_STATUS_IGNITION_BEFORE = "false";
    
    /** 発火状態：発火後 */
    private static final String WORKING_STATUS_IGNITION_AFTER = "true";
    
    /** API トリガ発動ゾーン 設定値：以上 */
    private static final String TRIGGER_ZONE_OVER = "0";
    
    /** API トリガ発動ゾーン 設定値：以下 */
    private static final String TRIGGER_ZONE_UNDER = "1";
    
    /** API トリガ発動ゾーン 設定値：指定なし */
    private static final String TRIGGER_ZONE_NONE = " ";
    
    /** API トリガ値段 設定値：指定なし */
    private static final String TRIGGER_PRICE_NONE = "0000000000";
    
    /** API OCO指成区分 設定値：指定なし */
    private static final String OCO_SASINARI_KBN_NONE = " ";
    
    /** API OCO指値 設定値：指定なし */
    private static final String OCO_SASHINE_NONE = StringUtils.repeat(" ", 10);
    
    /** SQL001 商品区分 設定値 */
    private static final String ORDER_TYPE_VALUE = "S";
    
    /** API 商品区分 設定値：株 */
    private static final String ORDER_TYPE_STOCK = "S";
    
    /** API 訂正区分 設定値 */
    private static final String MODIFY_TYPE_VALUE = "PRICE   ";
    
    /** API 受付経路区分 設定値：支店 */
    private static final String CALLCENTER_KBN_BRANCH = "0";
    
    /** API チケット出力区分 設定値 */
    private static final String TICKET_ID_VALUE = " ";
    
    /** API 余力チェック区分 設定値 */
    private static final String CHECK_ID_VALUE = " ";
    
    /** API 媒介 設定値：媒介注文 */
    private static final String INTERMEDIARY_VALUE = "1";
    
    /** API IPアドレス 設定値 */
    private static final String IP_ADDRESS_VALUE = String.format("%-39s", "ifap");
    
    /** 半角スペース */
    private static final String SPACE = " ";
    
    /**
     * アクションID：A001a
     * アクション名：注文発注
     * Dto リクエスト：IfaDomesticStockOrderCorrectConfirmA001aDtoRequest
     * Dto レスポンス：IfaDomesticStockOrderCorrectConfirmA001aDtoResponse
     * model リクエスト：IfaDomesticStockOrderCorrectConfirmA001aRequestModel
     * model レスポンス：IfaDomesticStockOrderCorrectConfirmA001aResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスDTO
     * @exception Exception 例外
     */
    public DataList<IfaDomesticStockOrderCorrectConfirmA001aDtoResponse> orderPlacementA001a(
            IfaDomesticStockOrderCorrectConfirmA001aDtoRequest dtoReq) throws Exception {
        
        DataList<IfaDomesticStockOrderCorrectConfirmA001aDtoResponse> dtoRes = new DataList<IfaDomesticStockOrderCorrectConfirmA001aDtoResponse>();
        List<IfaDomesticStockOrderCorrectConfirmA001aDtoResponse> resDtoList = new ArrayList<IfaDomesticStockOrderCorrectConfirmA001aDtoResponse>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticStockOrderCorrectConfirmServiceImpL.orderPlacementA001a");
        }
        
        /* ===================================== */
        /* ①利用者の口座に対する権限チェックを行う */
        /* ===================================== */
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(customerCommon.getButenCode()); // 部店コード
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber()); // 口座番号
        
        // FCT001の実行
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        /*
         * 対象顧客参照権限有無＝"0"（権限なし）：権限なしエラーを返す。
         * 取引停止フラグ＝"1"（取引停止口座）：取引停止口座エラーを返す
         */
        if (StringUtils.equals(outputFct001Dto.getTargetCustomerRefAuthFlag(), AUTH_ERROR_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXISTS,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
            return dtoRes;
        } else if (StringUtils.equals(outputFct001Dto.getTradeSuspendFlag(), TRADE_SUSPEND_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE));
            return dtoRes;
        }
        
        /* ===================================== */
        /* ②取引コース媒介可否チェックを行う。 */
        /* ===================================== */
        
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(customerCommon.getButenCode()); // 部店コード
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber()); // 口座番号
        inputFct003Dto.setProductCd(SECURITY_MONEY_CLASS_DOMESTIC); // 証券金銭種別
        inputFct003Dto.setTradeCd(dtoReq.getTradeCd()); // 取引種別
        
        // FCT003の実行
        OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        
        /*
         * 取引可：次の処理へ。
         * 上記以外：取引不可エラーを返す。
         */
        if (!StringUtils.equals(outputFct003Dto.getMediateAbleTradeFlag(), MEDIATE_ABLE_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                            new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, "2", "1") }));
            return dtoRes;
        }
        
        /* ===================================== */
        /* ③口座の取引制限チェックを行う。 */
        /* ===================================== */
        
        InputFct021Dto inputFct021Dto = new InputFct021Dto();
        inputFct021Dto.setButenCode(customerCommon.getButenCode()); // 部店コード
        inputFct021Dto.setAccountNumber(customerCommon.getAccountNumber()); // 口座番号
        inputFct021Dto.setProductCd(SECURITY_MONEY_CLASS_DOMESTIC); // 証券金銭種別
        inputFct021Dto.setTradeCd(dtoReq.getTradeCd()); // 取引種別
        inputFct021Dto.setCountryCd(NATIONALITY_CODE_VALUE); // 国籍コード
        inputFct021Dto.setCurrencyCode(CURRENCY_CODE_VALUE); // 通貨コード
        inputFct021Dto.setTradeRestrictChkMarket(dtoReq.getAfterCorrectMarket()); // 選択市場
        
        // FCT021の実行
        OutputFct021Dto outputFct021Dto = fct021.doCheck(inputFct021Dto);
        
        /* 注意情報エラー有無="1"（あり）：エラーを返す。 */
        if (StringUtils.equals(outputFct021Dto.getNoteInfoErrFlag(), NOTE_INFO_ERR_FLAG_EXIST)) {
            String codeName = codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, "2", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_NOTICE_ERROR_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_CMN_NOTICE_ERROR_CHECK, new String[] { codeName }));
            return dtoRes;
        }
        
        /* お知らせエラー有無="1"（あり）：エラーを返す。 */
        if (StringUtils.equals(outputFct021Dto.getNoteLimitErrFlag(), NOTE_LIMIT_ERR_FLAG_EXIST)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_INFORMATION_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_INFORMATION_CHECK));
            return dtoRes;
        }
        
        /*
         * 注意情報アラート有無="1"（あり）：
         * 注意情報アラート確認チェックボックス=ON：次の処理へ
         * 注意情報アラート確認チェックボックス=OFF または 非表示：エラーを返す。
         */
        if (StringUtils.equals(outputFct021Dto.getNoteInfoAlertFlag(), NOTE_INFO_ALERT_FLAG_EXIST)) {
            if (StringUtils.equals(dtoReq.getNoticeInfoAlertCheck(), NOTICE_INFO_ALERT_CHECK_BOX_OFF)
                    || dtoReq.getNoticeInfoAlertCheck().isEmpty()) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_INFORMATION_OCCURS,
                        IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                return dtoRes;
            }
        }
        
        /*
         * お知らせアラート有無="1"（あり）：アラート情報を格納する。
         * お知らせアラート確認チェックボックス=ON：次の処理へ
         * お知らせアラート確認チェックボックス=OFF または 非表示：エラーを返す。
         */
        if (StringUtils.equals(outputFct021Dto.getNoteLimitAlertFlag(), NOTE_LIMIT_ALERT_FLAG_EXIST)) {
            if (StringUtils.equals(dtoReq.getNoticeAlertCheck(), NOTICE_ALERT_CHECK_BOX_OFF)
                    || dtoReq.getNoticeAlertCheck().isEmpty()) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_INFORMATION_OCCURS,
                        IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                return dtoRes;
            }
        }
        
        /* ============================================= */
        /* ④現物買付の場合、コンプラランクチェックを行う。 */
        /* ============================================= */
        
        if (StringUtils.equals(dtoReq.getTradeCd(), DOMESTIC_STOCK_TRADE_CLASS_SPOT_BUY)) {
            
            InputFct006Dto inputFct006Dto = new InputFct006Dto();
            inputFct006Dto.setButenCode(customerCommon.getButenCode()); // 部店コード
            inputFct006Dto.setAccountNumber(customerCommon.getAccountNumber()); // 口座番号
            inputFct006Dto.setBrDomesticFgnInd(DOMESTIC_FOREIGN_TYPE_DOMESTIC); // 国内外国区分
            inputFct006Dto.setBrBrandInd(SECURITY_TYPE_STOCK); // 商品区分
            inputFct006Dto.setBrandCode1(dtoReq.getBrandCode()); // 銘柄コード１
            inputFct006Dto.setInvitationType(dtoReq.getKanyuKbn()); // 勧誘区分
            inputFct006Dto.setOrderMethod(dtoReq.getReceiveOrderType()); // 受注方法
            inputFct006Dto.setComplaCheckKind(COMPLA_CHECK_KIND_BUY_ORDER); // コンプラチェック種類
            
            // FCT006の実行
            OutputFct006Dto outputFct006Dto = fct006.doCheck(inputFct006Dto);
            
            /*
             * FCT006.判定結果=0：ノーマル：次の処理へ
             * FCT006.判定結果=1：アラート：
             */
            if (StringUtils.equals(outputFct006Dto.getJudgementResult(), COMPLA_RANK_JUDGEMENT_RESULT_ALERT)) {
                /*
                 * FTC006.コンプラランクチェック.チェックボックス文言と同じチェックボックスがON：次の処理へ
                 * FTC006.コンプラランクチェック.チェックボックス文言と同じチェックボックスがOFF または 非表示：エラーを返す。
                 */
                if (StringUtils.equals(dtoReq.getChkBoxLabel(), outputFct006Dto.getChkBoxLabel()) // リクエスト.コンプラランクチェック.チェックボックス文言とFTC006.チェックボックス文言が同じ
                        && !StringUtils.equals(dtoReq.getInvitationCheck(), INVITATION_CHECK_BOX_ON)) { // かつ リクエスト.アラート内容確認.コンプラランクチェック確認="1"ではない
                    
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_INFORMATION_OCCURS,
                            IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                    return dtoRes;
                }
            } else if (!StringUtils.equals(outputFct006Dto.getJudgementResult(), COMPLA_RANK_JUDGEMENT_RESULT_NORMAL)) {
                // FCT006.判定結果=2：エラー：エラーを返す。
                // FCT006.判定結果=上記以外：エラーを返す。
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, outputFct006Dto.getMessageId(),
                        IfaCommonUtil.getMessage(outputFct006Dto.getMessageId()));
                return dtoRes;
            }
        }
        
        /* ===================================== */
        /* ⑤銘柄の取引注意情報を取得する。 */
        /* ===================================== */
        
        InputFct027Dto inputFct027Dto = new InputFct027Dto();
        inputFct027Dto.setBrandCode(dtoReq.getBrandCode()); // 銘柄コード
        
        // FCT027の実行
        OutputFct027Dto outputFct027Dto = fct027.getData(inputFct027Dto);
        
        /*
         * 規制銘柄区分=1:規制銘柄 の場合
         * 取引注意情報（銘柄）確認チェックボックス=ON：次の処理へ
         * 取引注意情報（銘柄）確認チェックボックス=OFF または 非表示：エラーを返す。
         */
        if (StringUtils.equals(outputFct027Dto.getRegKbn(), REG_KBN_REG_BRAND)) {
            if (!StringUtils.equals(dtoReq.getTradingCautionInformation(), TRADING_CAUTION_INFORMATION_CHECK_BOX_ON)) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_INFORMATION_OCCURS,
                        IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                return dtoRes;
            }
        }
        
        /* ===================================================== */
        /* ⑥発注前に注文訂正内容を国内株式注文テーブルへ記録する。 */
        /* ===================================================== */
        
        // SQL003で同じEC受注番号のデータを取得（データがなければ新規データを作成）
        IfaDomesticStockOrderCorrectConfirmSql003RequestModel selSql003Req = new IfaDomesticStockOrderCorrectConfirmSql003RequestModel();
        selSql003Req.setOrderNum(dtoReq.getEcOrderNo());
        selSql003Req.setButenCode(IfaCommonUtil.getCustomerCommon().getButenCode());
        selSql003Req.setAccountNumber(IfaCommonUtil.getCustomerCommon().getAccountNumber());
        selSql003Req.setAcceptDate(dtoReq.getOrderDate());
        selSql003Req.setCustomerId(IfaCommonUtil.getCustomerCommon().getCustomerCode());
        selSql003Req.setSpecificKbn(IfaCommonUtil.getCustomerCommon().getSpecificAccountType());
        selSql003Req.setCheckSor(dtoReq.getCheckSor());
        
        // SQL003の実行
        DataList<IfaDomesticStockOrderCorrectConfirmSql003ResponseModel> selSql003ResList = dao
                .selectIfaDomesticStockOrderCorrectConfirmSql003(selSql003Req);
        IfaDomesticStockOrderCorrectConfirmSql003ResponseModel selSql003Res = selSql003ResList.getDataList().get(0);
        
        IfaDomesticStockOrderCorrectConfirmSql001RequestModel insSql001Req = new IfaDomesticStockOrderCorrectConfirmSql001RequestModel();
        insSql001Req = setSql001Req(dtoReq, selSql003Res);
        
        // SQL001の実行
        int insSql001Res = dao.insertIfaDomesticStockOrderCorrectConfirmSql001(insSql001Req);
        
        /* DB登録NG：DB登録エラーを返す。 */
        if (insSql001Res != 1) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_FRS_PRE_ORDER_EXECUTION_FAILED,
                    IfaCommonUtil.getMessage(ERRORS_FRS_PRE_ORDER_EXECUTION_FAILED));
            return dtoRes;
        }
        
        // レスポンスに値を格納
        IfaDomesticStockOrderCorrectConfirmA001aDtoResponse resDto = new IfaDomesticStockOrderCorrectConfirmA001aDtoResponse();
        resDto.setIfaOrderNo(selSql003Res.getIfaOrderNo());
        resDto.setIfaOrderSubNo(selSql003Res.getIfaOrderSubNo());
        resDtoList.add(resDto);
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }
    
    /**
     * アクションID：A001b
     * アクション名：注文発注
     * Dto リクエスト：IfaDomesticStockOrderCorrectConfirmA001bDtoRequest
     * Dto レスポンス：IfaDomesticStockOrderCorrectConfirmA001bDtoResponse
     * model リクエスト：IfaDomesticStockOrderCorrectConfirmA001bRequestModel
     * model レスポンス：IfaDomesticStockOrderCorrectConfirmA001bResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスDTO
     * @exception Exception 例外
     */
    public DataList<IfaDomesticStockOrderCorrectConfirmA001bDtoResponse> orderPlacementA001b(
            IfaDomesticStockOrderCorrectConfirmA001bDtoRequest dtoReq) throws Exception {
        
        DataList<IfaDomesticStockOrderCorrectConfirmA001bDtoResponse> dtoRes = new DataList<IfaDomesticStockOrderCorrectConfirmA001bDtoResponse>();
        List<IfaDomesticStockOrderCorrectConfirmA001bDtoResponse> resDtoList = new ArrayList<IfaDomesticStockOrderCorrectConfirmA001bDtoResponse>();
        IfaDomesticStockOrderCorrectConfirmA001bDtoResponse resDto = new IfaDomesticStockOrderCorrectConfirmA001bDtoResponse();
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticStockOrderCorrectConfirmServiceImpL.orderPlacementA001b");
        }
        
        /* ===================================================== */
        /* ⑦注文種別に応じて、注文訂正を行う。 */
        /* ⑧発注後に注文訂正内容を国内株式注文テーブルへ記録する。 */
        /* ===================================================== */
        
        IfaDomesticStockOrderCorrectConfirmSql002RequestModel updSql002Req = new IfaDomesticStockOrderCorrectConfirmSql002RequestModel();
        
        StockModifyOrderOutData api001Result = new StockModifyOrderOutData();
        StockModifyOrderAutoOutData api002Result = new StockModifyOrderAutoOutData();
        
        // APIエラーの有無
        Boolean isApiError = false;
        // エラーレベル
        ErrorLevel errorLevel = ErrorLevel.SUCCESS;
        // エラーメッセージ
        String errorMessage = StringUtil.EMPTY_STRING;
        // エラーコード
        String errorCode = StringUtil.EMPTY_STRING;
        
        /* 通常/逆指値、OCO注文：国内株式・信用訂正注文API(API001)の呼出し。 */
        /* 正常、エラーいずれも次の処理へ。 */
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_NORMAL)
                || StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_OCO)) {
            
            StockModifyOrderInData api001InData = setApi001Req(dtoReq);
            StockModifyOrderIn api001In = new StockModifyOrderIn();
            api001In.setIndata(api001InData);
            
            // API001の実行
            try {
                api001Result = apiWrapper.stockModifyOrder(api001In);
                apiErrorUtil.checkApiResponse(api001Result.getShubetu(), api001Result.getCode(),
                        api001Result.getMessage());
                if (apiErrorUtil.isFatal()) {
                    // エラー有の場合、DB更新後にエラーを返す
                    isApiError = true;
                } else if (apiErrorUtil.isWarn()) {
                    errorMessage = apiErrorUtil.getWarningMessages();
                    errorLevel = ErrorLevel.WARNING;
                    errorCode = apiErrorUtil.getWarningMessageId();
                }
            } catch (Exception ae) {
                // Exception発生の場合、DBを更新してエラーレスポンスを返却する。
                // IFA注文番号
                updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
                // IFA注文サブ番号
                updSql002Req.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
                // 更新者
                updSql002Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());

                try {
                    dao.updateIfaDomesticStockOrderCorrectConfirmSql002b(updSql002Req);

                } finally {
                    dtoRes = IfaCommonUtil.createDataList(
                            List.of(),
                            ErrorLevel.FATAL,
                            ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED,
                            IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED)
                    );
                    return dtoRes;
                }
            }
            
            // APIエラー判定
            updSql002Req.setApiError(isApiError);
            // IFA注文番号
            updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
            // IFA注文サブ番号
            updSql002Req.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
            // 種別
            updSql002Req.setShubetu(api001Result.getShubetu());
            // エラーコード
            updSql002Req.setCode(api001Result.getCode());
            // エラーメッセージ
            updSql002Req.setMessage(api001Result.getMessage());
            // 更新者
            updSql002Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
            // 受注日
            updSql002Req.setAcceptDate(api001Result.getAcceptDate());
            if (!isApiError) {
                // 受注時刻
                updSql002Req.setAcceptTime(api001Result.getAcceptTime());
                // 与信チェック用時価
                updSql002Req.setEstimatePrice(api001Result.getEstimatePrice());
                // 訂正前後の約定金額(概算)差分
                updSql002Req.setAmount(api001Result.getAmount());
                // 手数料（概算）
                updSql002Req.setCommission(api001Result.getCommission());
                // 消費税（概算）
                updSql002Req.setConsumptionTax(api001Result.getConsumptionTax());
                // 譲渡益税（概算）
                updSql002Req.setCapitalGainTax(api001Result.getCapitalGainTax());
                // 精算金額（概算）
                updSql002Req.setNetAmount(api001Result.getNetAmount());
                // 諸経費
                updSql002Req.setCost(api001Result.getCost());
                // 約定予定日
                updSql002Req.setTradeDate(api001Result.getTradeDate());
                // 受渡予定日
                updSql002Req.setSettlementDate(api001Result.getSettlementDate());
                // 受付有効期限
                updSql002Req.setAcceptLimit(api001Result.getModLimit());
                // 手数料区分（採用）
                updSql002Req.setComIdR(api001Result.getComIdR());
                // 売却可能数量
                updSql002Req.setAcPosition(api001Result.getAcPosition());
                // 注文後の売却可能数量
                updSql002Req.setAcPositionAfter(api001Result.getAcPositionAfter());
                // 買付可能金額
                updSql002Req.setAcBalance(api001Result.getAcBalance());
                // 注文後の買付可能金額
                updSql002Req.setAcBalanceAfter(api001Result.getAcBalanceAfter());
                // 取引不足額
                updSql002Req.setTradeDeficitAmount(api001Result.getTradeDeficitAmount());
                // ISA買付可能枠
                updSql002Req.setIsaBuyLimit(api001Result.getIsaBuyLimit());
                // ジュニアNISA振替金額
                updSql002Req.setJrnisaTransferAmount(api001Result.getJrnisaTransferAmount());
                // 決済可能数量
                updSql002Req.setUnclosedQuantity(api001Result.getUnclosedQuantity());
                // 注文後の決済可能数量
                updSql002Req.setUnclosedQuantityAfter(api001Result.getUnclosedQuantityAfter());
                // 建玉余力
                updSql002Req.setMarginCapability(api001Result.getMarginCapability());
                // 注文後の建玉余力
                updSql002Req.setMarginCapabilityAfter(api001Result.getMarginCapabilityAfter());
                // 維持率
                updSql002Req.setActualGrntRate(api001Result.getActualGrntRate());
                // 注文後維持率
                updSql002Req.setActualGrntRateAfter(api001Result.getActualGrntRateAfter());
            }
            try {
                int updSql002Res = dao.updateIfaDomesticStockOrderCorrectConfirmSql002(updSql002Req);
                if (updSql002Res != 1) {
                    throw new Exception();
                }
            } catch (Exception e) {
                /* DB登録NG：DB登録エラーを格納 */
                LOGGER.debug("IfaDomesticStockOrderCorrectConfirmServiceImpL.orderPlacementA001b update Exception[{}]",
                        e.getMessage());
                errorLevel = ErrorLevel.WARNING;
                errorCode = WARNINGS_FRS_POST_ORDER_EXECUTION_COMPLETED;
                errorMessage = IfaCommonUtil.getMessage(WARNINGS_FRS_POST_ORDER_EXECUTION_COMPLETED);
            }
            
            if (isApiError) {
                errorLevel = ErrorLevel.FATAL;
                errorCode = ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED;
                errorMessage = IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED,
                        new String[] { api001Result.getCode(), api001Result.getMessage() });
                dtoRes = IfaCommonUtil.createDataList(resDtoList, errorLevel, errorCode, errorMessage);
                return dtoRes;
            } else {
                // ジュニアNISA振替金額
                resDto.setJrnisaTransferAmount(api001Result.getJrnisaTransferAmount());
                // 見積単価
                resDto.setQuoteUnitPrice(api001Result.getEstimatePrice());
                // 投資可能枠
                if (StringUtils.equals(dtoReq.getTradeCd(), DOMESTIC_STOCK_TRADE_CLASS_SPOT_BUY)) {
                    resDto.setNisaInvestableQuote(api001Result.getIsaBuyLimit());
                }
                // 約定予定日
                resDto.setContractDate(api001Result.getTradeDate());
                // 受渡予定日
                resDto.setDeliveryDate(api001Result.getSettlementDate());
                // 受注日時
                resDto.setOrderDayTime(api001Result.getAcceptDate() + SPACE + api001Result.getAcceptTime());
                // 訂正後買付余力
                resDto.setAfterCorrecBuyPower(api001Result.getAcBalanceAfter());
                // 有効期限変更フラグ
                resDto.setYukoKigenChange(api001Result.getModLimitFlg());
                // 有効期限
                resDto.setYukoKigen(api001Result.getModLimit());
                // 訂正SOR注文結果区分
                resDto.setCorrectSorOrderResultClassification(api001Result.getSorModifyStatus());
            }
            
        }
        
        /* IFD、IFDOCO注文：自動注文(国内株式)訂正注文受付API(API002)の呼出し。 */
        /* 正常、エラーいずれも次の処理へ。 */
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFD)
                || StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFDOCO)) {
            
            StockModifyOrderAutoInData api002InData = setApi002Req(dtoReq);
            StockModifyOrderAutoIn api002In = new StockModifyOrderAutoIn();
            api002In.setIndata(api002InData);
            
            // API002の実行
            try {
                api002Result = apiWrapper.stockModifyOrderAuto(api002In);
                apiErrorUtil.checkApiResponse(api002Result.getShubetu(), api002Result.getCode(),
                        api002Result.getMessage());
                if (apiErrorUtil.isFatal()) {
                    // エラー有の場合、DB更新後にエラーを返す
                    isApiError = true;
                } else if (apiErrorUtil.isWarn()) {
                    errorMessage = apiErrorUtil.getWarningMessages();
                    errorLevel = ErrorLevel.WARNING;
                    errorCode = apiErrorUtil.getWarningMessageId();
                }
            } catch (Exception ae) {
                // Exception発生の場合、DBを更新してエラーレスポンスを返却する。
                // IFA注文番号
                updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
                // IFA注文サブ番号
                updSql002Req.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
                // 更新者
                updSql002Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());

                try {
                    dao.updateIfaDomesticStockOrderCorrectConfirmSql002b(updSql002Req);

                } finally {
                    dtoRes = IfaCommonUtil.createDataList(
                            List.of(),
                            ErrorLevel.FATAL,
                            ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED,
                            IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED)
                    );
                    return dtoRes;
                }
            }
            
            // APIエラー判定
            updSql002Req.setApiError(isApiError);
            // IFA注文番号
            updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
            // IFA注文サブ番号
            updSql002Req.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
            // 種別
            updSql002Req.setShubetu(api002Result.getShubetu());
            // エラーコード
            updSql002Req.setCode(api002Result.getCode());
            // エラーメッセージ
            updSql002Req.setMessage(api002Result.getMessage());
            // 更新者
            updSql002Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
            if (!isApiError) {
                // 受注日
                updSql002Req.setAcceptDate(api002Result.getAcceptDate());
                // 受注時刻
                updSql002Req.setAcceptTime(api002Result.getAcceptTime());
                // 与信チェック用時価
                updSql002Req.setEstimatePrice(api002Result.getEstimatePrice());
                // 訂正前後の約定金額(概算)差分
                updSql002Req.setAmount(api002Result.getAmount());
                // 手数料（概算）
                updSql002Req.setCommission(api002Result.getCommission());
                // 消費税（概算）
                updSql002Req.setConsumptionTax(api002Result.getConsumptionTax());
                // 譲渡益税（概算）
                updSql002Req.setCapitalGainTax(api002Result.getCapitalGainTax());
                // 精算金額（概算）
                updSql002Req.setNetAmount(api002Result.getNetAmount());
                // 諸経費
                updSql002Req.setCost(api002Result.getCost());
                // 約定予定日
                updSql002Req.setTradeDate(api002Result.getTradeDate());
                // 受渡予定日
                updSql002Req.setSettlementDate(api002Result.getSettlementDate());
                // 受付有効期限
                updSql002Req.setAcceptLimit(api002Result.getModLimit());
                // 手数料区分（採用）
                updSql002Req.setComIdR(api002Result.getComIdR());
                // 売却可能数量
                updSql002Req.setAcPosition(api002Result.getAcPosition());
                // 注文後の売却可能数量
                updSql002Req.setAcPositionAfter(api002Result.getAcPositionAfter());
                // 買付可能金額
                updSql002Req.setAcBalance(api002Result.getAcBalance());
                // 注文後の買付可能金額
                updSql002Req.setAcBalanceAfter(api002Result.getAcBalanceAfter());
                // 取引不足額
                updSql002Req.setTradeDeficitAmount(api002Result.getTradeDeficitAmount());
                // ISA買付可能枠
                updSql002Req.setIsaBuyLimit(api002Result.getIsaBuyLimit());
                // ジュニアNISA振替金額
                updSql002Req.setJrnisaTransferAmount(api002Result.getJrnisaTransferAmount());
                // 決済可能数量
                updSql002Req.setUnclosedQuantity(api002Result.getUnclosedQuantity());
                // 注文後の決済可能数量
                updSql002Req.setUnclosedQuantityAfter(api002Result.getUnclosedQuantityAfter());
                // 建玉余力
                updSql002Req.setMarginCapability(api002Result.getMarginCapability());
                // 注文後の建玉余力
                updSql002Req.setMarginCapabilityAfter(api002Result.getMarginCapabilityAfter());
                // 維持率
                updSql002Req.setActualGrntRate(api002Result.getActualGrntRate());
                // 注文後維持率
                updSql002Req.setActualGrntRateAfter(api002Result.getActualGrntRateAfter());
            }
            try {
                int updSql002Res = dao.updateIfaDomesticStockOrderCorrectConfirmSql002(updSql002Req);
                if (updSql002Res != 1) {
                    throw new Exception();
                }
            } catch (Exception e) {
                // DB登録NG：DB登録エラーを返す。 */
                LOGGER.debug("IfaDomesticStockOrderCorrectConfirmServiceImpL.orderPlacementA001b update Exception[{}]",
                        e.getMessage());
                errorLevel = ErrorLevel.WARNING;
                errorCode = WARNINGS_FRS_POST_ORDER_EXECUTION_COMPLETED;
                errorMessage = IfaCommonUtil.getMessage(WARNINGS_FRS_POST_ORDER_EXECUTION_COMPLETED);
            }
            
            if (isApiError) {
                errorLevel = ErrorLevel.FATAL;
                errorCode = ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED;
                errorMessage = IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_EXECUTION_MODIFY_FAILED,
                        new String[] { api002Result.getCode(), api002Result.getMessage() });
                dtoRes = IfaCommonUtil.createDataList(resDtoList, errorLevel, errorCode, errorMessage);
                return dtoRes;
            } else {
                // ジュニアNISA振替金額
                resDto.setJrnisaTransferAmount(api002Result.getJrnisaTransferAmount());
                // 見積単価
                resDto.setQuoteUnitPrice(api002Result.getEstimatePrice());
                // 投資可能枠
                if (StringUtils.equals(dtoReq.getTradeCd(), DOMESTIC_STOCK_TRADE_CLASS_SPOT_BUY)) {
                    resDto.setNisaInvestableQuote(api002Result.getIsaBuyLimit());
                }
                // 約定予定日
                resDto.setContractDate(api002Result.getTradeDate());
                // 受渡予定日
                resDto.setDeliveryDate(api002Result.getSettlementDate());
                // 受注日時
                resDto.setOrderDayTime(api002Result.getAcceptDate() + SPACE + api002Result.getAcceptTime());
                // 訂正後買付余力
                resDto.setAfterCorrecBuyPower(api002Result.getAcBalanceAfter());
                // 有効期限変更フラグ
                resDto.setYukoKigenChange(api002Result.getModLimitFlg());
                // 有効期限
                resDto.setYukoKigen(api002Result.getModLimit());
            }
        }
        
        // 訂正SOR注文区分="1"：訂正SOR　かつ　訂正SOR注文結果区分="△"：SOR対象外の場合、SOR対象外アラートを表示する
        if (CORRECT_SOR_ORDER_CLASSFICATION_SOR.equals(dtoReq.getCorrectSorOrderClassification()) && SPACE.equals(api001Result.getSorModifyStatus())) {
            // 既にワーニングメッセージがセットされている場合、ワーニングメッセージを区切り文字（<sep>）で区切って複数セットする
            // エラーコードは画面側で見ていないので、セット不要
            if (ErrorLevel.WARNING.equals(errorLevel)) {
                errorMessage = errorMessage + "<sep>" + IfaCommonUtil.getMessage(WARNINGS_FRS_OUT_OF_SOR_ORDER_COMPLETED);
            } else {
                errorLevel = ErrorLevel.WARNING;
                errorCode = WARNINGS_FRS_OUT_OF_SOR_ORDER_COMPLETED;
                errorMessage = IfaCommonUtil.getMessage(WARNINGS_FRS_OUT_OF_SOR_ORDER_COMPLETED);
            }
        }
        
        // リクエスト項目をセット
        BeanUtils.copyProperties(resDto, dtoReq);
        
        resDtoList.add(resDto);
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, errorLevel, errorCode, errorMessage);
        return dtoRes;
    }
    
    /**
     * API001リクエストをセット
     *
     * @param dtoReq リクエストDTO
     * @return API001リクエスト
     */
    private StockModifyOrderInData setApi001Req(IfaDomesticStockOrderCorrectConfirmA001bDtoRequest dtoReq) {
        
        StockModifyOrderInData inData = new StockModifyOrderInData();
        
        // トランザクションID
        inData.setTransactionId(String.format("%32s", " ").replace(" ", "0"));
        // 通番
        inData.setCommandNum(String.format("%7s", " ").replace(" ", "0"));
        // 年月日
        inData.setCommandDate(String.format("%8s", " ").replace(" ", "0"));
        // 部店コード3桁
        inData.setButenCd(IfaCommonUtil.getCustomerCommon().getButenCode());
        // 口座番号7桁
        inData.setKozaNo(String.format("%7s", IfaCommonUtil.getCustomerCommon().getAccountNumber()).replace(" ", "0"));
        // アカウントID
        inData.setAccountId(String.format("%11s", " ").replace(" ", "0"));
        // アカウント毎の連番
        inData.setNumber(String.format("%7s", " ").replace(" ", "0"));
        // オリジン
        inData.setOrigin("0");
        // 商品区分
        inData.setOrderType(ORDER_TYPE_STOCK);
        // 訂正区分
        inData.setModifyType(MODIFY_TYPE_VALUE);
        // EC受注番号
        inData.setOrderNo(String.format("%6s", dtoReq.getEcOrderNo()).replace(" ", "0"));
        // 注文株数（訂正後数量）
        inData.setQuantity(String.format("%8s", dtoReq.getQuantity()).replace(" ", "0"));
        // 指成区分
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_NORMAL)) {
            inData.setSasinariKbn(dtoReq.getSasinariJyouken());
        } else if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_OCO)) {
            inData.setSasinariKbn(dtoReq.getOco1SasinariJyouken());
        }
        // 指値（訂正値段）
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_NORMAL)) {
            inData.setPrice(String.format("%10s", dtoReq.getPrice()).replace(" ", "0"));
        } else if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_OCO)) {
            inData.setPrice(String.format("%10s", dtoReq.getOco1Price()).replace(" ", "0"));
        } else {
            inData.setPrice(PRICE_NONE);
        }
        // 受付経路区分
        inData.setCallcenterKbn(CALLCENTER_KBN_BRANCH);
        // ユーザーＩＤ
        inData.setUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
        // チケット出力区分
        inData.setTicketId(TICKET_ID_VALUE);
        // 手数料区分
        inData.setComId(dtoReq.getTesuuryouKbn());
        // 余力チェック区分
        inData.setCheckId(CHECK_ID_VALUE);
        // RBE注文種別
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_NORMAL)) {
            if (StringUtils.equals(dtoReq.getSasinariHouhou(), EXECUTE_METHOD_PRICE_LIMIT)
                    || StringUtils.equals(dtoReq.getSasinariHouhou(), EXECUTE_METHOD_MARKET_ORDER)) {
                inData.setRbeOrderKind(RBE_ORDER_KIND_NORMAL);
            } else if (StringUtils.equals(dtoReq.getSasinariHouhou(), EXECUTE_METHOD_REVERSE_PRICE_LIMIT)) {
                inData.setRbeOrderKind(RBE_ORDER_KIND_SLO);
            }
        } else if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_OCO)) {
            inData.setRbeOrderKind(RBE_ORDER_KIND_OCO);
        }
        
        // トリガ発動ゾーン
        if (StringUtils.equals(dtoReq.getWorkingStatus(), WORKING_STATUS_IGNITION_BEFORE)) {
            inData.setTriggerZone(TRIGGER_ZONE_NONE); // 設定値がないの場合の値で初期化
            if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_NORMAL)) {
                if (StringUtils.equals(dtoReq.getSasinariHouhou(), EXECUTE_METHOD_REVERSE_PRICE_LIMIT)) {
                    if (StringUtils.equals(dtoReq.getTradeCd(), DOMESTIC_STOCK_TRADE_CLASS_SPOT_BUY)) {
                        inData.setTriggerZone(TRIGGER_ZONE_OVER);
                    }
                    if (StringUtils.equals(dtoReq.getTradeCd(), DOMESTIC_STOCK_TRADE_CLASS_SPOT_SELL)) {
                        inData.setTriggerZone(TRIGGER_ZONE_UNDER);
                    }
                }
            }
            if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_OCO)) {
                if (StringUtils.equals(dtoReq.getTradeCd(), DOMESTIC_STOCK_TRADE_CLASS_SPOT_BUY)) {
                    inData.setTriggerZone(TRIGGER_ZONE_OVER);
                }
                if (StringUtils.equals(dtoReq.getTradeCd(), DOMESTIC_STOCK_TRADE_CLASS_SPOT_SELL)) {
                    inData.setTriggerZone(TRIGGER_ZONE_UNDER);
                }
            }
        } else if (StringUtils.equals(dtoReq.getWorkingStatus(), WORKING_STATUS_IGNITION_AFTER)) {
            inData.setTriggerZone(TRIGGER_ZONE_NONE);
        }
        
        // トリガ値段
        if (StringUtils.equals(dtoReq.getWorkingStatus(), WORKING_STATUS_IGNITION_BEFORE)) {
            inData.setTriggerPrice(TRIGGER_PRICE_NONE); // 設定値がないの場合の値で初期化
            if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_NORMAL)) {
                if (StringUtils.equals(dtoReq.getSasinariHouhou(), EXECUTE_METHOD_REVERSE_PRICE_LIMIT)) {
                    inData.setTriggerPrice(String.format("%10s", dtoReq.getTriggerPrice()).replace(" ", "0"));
                }
            }
            if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_OCO)) {
                inData.setTriggerPrice(String.format("%10s", dtoReq.getOco2TriggerPrice()).replace(" ", "0"));
            }
        } else if (StringUtils.equals(dtoReq.getWorkingStatus(), WORKING_STATUS_IGNITION_AFTER)) {
            inData.setTriggerPrice(TRIGGER_PRICE_NONE);
        }
        
        // OCO指成区分
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_NORMAL)) {
            inData.setOcoSasinariKbn(OCO_SASINARI_KBN_NONE);
        } else if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_OCO)) {
            inData.setOcoSasinariKbn(dtoReq.getOco2GyakusasiJyouken());
        }
        
        // OCO指値
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_NORMAL)) {
            inData.setOcoPrice(OCO_SASHINE_NONE);
        } else if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_OCO)) {
            inData.setOcoPrice(String.format("%10s", dtoReq.getOco2Price()).replace(" ", "0"));
        }
        
        // 媒介
        inData.setIntermediary(INTERMEDIARY_VALUE);
        // IPアドレス
        inData.setIpAddress(IP_ADDRESS_VALUE);
        // 訂正SOR注文区分
        // 市場が「当社優先市場／SOR」に変更された場合
        if (SELECT_MARKET_SOR.equals(dtoReq.getAfterCorrectMarket())) {
            // "1"：訂正SOR
            inData.setSorModifyCode(API001_SOR_MODIFY_CODE_CORRECT_SOR);
        // 上記以外
        } else {
            // "△"：通常訂正
            inData.setSorModifyCode(API001_SOR_MODIFY_CODE_NORMAL);
        }
        
        return inData;
    }
    
    /**
     * API002リクエストをセット
     *
     * @param dtoReq リクエストDTO
     * @return API002リクエスト
     */
    private StockModifyOrderAutoInData setApi002Req(IfaDomesticStockOrderCorrectConfirmA001bDtoRequest dtoReq) {
        
        StockModifyOrderAutoInData inData = new StockModifyOrderAutoInData();
        
        // トランザクションID
        inData.setTransactionId(String.format("%32s", " ").replace(" ", "0"));
        // 通番
        inData.setCommandNum(String.format("%7s", " ").replace(" ", "0"));
        // 年月日
        inData.setCommandDate(String.format("%8s", " ").replace(" ", "0"));
        // 部店コード3桁
        inData.setButenCd(IfaCommonUtil.getCustomerCommon().getButenCode());
        // 口座番号7桁
        inData.setKozaNo(String.format("%7s", IfaCommonUtil.getCustomerCommon().getAccountNumber()).replace(" ", "0"));
        // アカウントID
        inData.setAccountId(String.format("%11s", " ").replace(" ", "0"));
        // アカウント毎の連番
        inData.setNumber(String.format("%7s", " ").replace(" ", "0"));
        // オリジン
        inData.setOrigin("0");
        // 商品区分
        inData.setOrderType(ORDER_TYPE_STOCK);
        // 訂正区分
        inData.setModifyType(MODIFY_TYPE_VALUE);
        // EC受注番号
        inData.setOrderNo(String.format("%6s", dtoReq.getEcOrderNo()).replace(" ", "0"));
        // 注文株数（訂正後数量）
        inData.setQuantity(String.format("%8s", dtoReq.getQuantity()).replace(" ", "0"));
        // 指成区分
        inData.setSasinariKbn(dtoReq.getIfd1SasinariJyouken());
        // 指値（訂正値段）
        inData.setPrice(PRICE_NONE); // 設定値がないの場合の値で初期化
        if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), EXECUTE_METHOD_PRICE_LIMIT)) {
            inData.setPrice(String.format("%10s", dtoReq.getIfd1Price()).replace(" ", "0"));
        }
        if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), EXECUTE_METHOD_REVERSE_PRICE_LIMIT)) {
            if (StringUtils.equals(dtoReq.getIfd1GyakusasiHouhou(), EXECUTE_METHOD_PRICE_LIMIT)) {
                inData.setPrice(String.format("%10s", dtoReq.getIfd1Price()).replace(" ", "0"));
            }
        }
        // 受付経路区分
        inData.setCallcenterKbn(CALLCENTER_KBN_BRANCH);
        // ユーザーＩＤ
        inData.setUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
        // チケット出力区分
        inData.setTicketId(TICKET_ID_VALUE);
        // 手数料区分
        inData.setComId(dtoReq.getTesuuryouKbn());
        // 余力チェック区分
        inData.setCheckId(CHECK_ID_VALUE);
        // RBE注文種別
        if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), EXECUTE_METHOD_PRICE_LIMIT)
                || StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), EXECUTE_METHOD_MARKET_ORDER)) {
            inData.setRbeOrderKind(RBE_ORDER_KIND_NORMAL);
        } else if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), EXECUTE_METHOD_REVERSE_PRICE_LIMIT)) {
            inData.setRbeOrderKind(RBE_ORDER_KIND_SLO);
        }
        
        // トリガ発動ゾーン
        if (StringUtils.equals(dtoReq.getWorkingStatus(), WORKING_STATUS_IGNITION_BEFORE)) {
            if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), EXECUTE_METHOD_REVERSE_PRICE_LIMIT)) {
                inData.setTriggerZone(TRIGGER_ZONE_OVER);
            } else {
                inData.setTriggerZone(TRIGGER_ZONE_NONE);
            }
        }
        if (StringUtils.equals(dtoReq.getWorkingStatus(), WORKING_STATUS_IGNITION_AFTER)) {
            inData.setTriggerZone(TRIGGER_ZONE_NONE);
        }
        
        // トリガ値段
        if (StringUtils.equals(dtoReq.getWorkingStatus(), WORKING_STATUS_IGNITION_BEFORE)) {
            if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), EXECUTE_METHOD_REVERSE_PRICE_LIMIT)) {
                inData.setTriggerPrice(String.format("%10s", dtoReq.getIfd1TriggerPrice()).replace(" ", "0"));
            } else {
                inData.setTriggerPrice(TRIGGER_PRICE_NONE);
            }
        }
        if (StringUtils.equals(dtoReq.getWorkingStatus(), WORKING_STATUS_IGNITION_AFTER)) {
            inData.setTriggerPrice(TRIGGER_PRICE_NONE);
        }
        
        // OCO指成区分
        inData.setOcoSasinariKbn(OCO_SASINARI_KBN_NONE);
        
        // OCO指値
        inData.setOcoPrice(OCO_SASHINE_NONE);
        
        // 自動注文種別
        inData.setAutoOrderKind(AUTO_ORDER_KIND_VALUE);
        
        // DONE指成区分
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFD)) {
            inData.setDoneSasinariKbn(dtoReq.getIfd2SasinariJyouken());
        } else if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFDOCO)) {
            inData.setDoneSasinariKbn(dtoReq.getOco1SasinariJyouken());
        }
        // DONE指値（訂正値段）
        inData.setDonePrice(DONE_SASHINE_NONE); // 設定値がないの場合の値で初期化
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFD)) {
            if (StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), EXECUTE_METHOD_PRICE_LIMIT)) {
                inData.setDonePrice(String.format("%10s", dtoReq.getIfd2Price()).replace(" ", "0"));
            }
            if (StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), EXECUTE_METHOD_REVERSE_PRICE_LIMIT)) {
                if (StringUtils.equals(dtoReq.getIfd2GyakusasiHouhou(), EXECUTE_METHOD_PRICE_LIMIT)) {
                    inData.setDonePrice(String.format("%10s", dtoReq.getIfd2Price()).replace(" ", "0"));
                }
            }
        } else if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFDOCO)) {
            if (StringUtils.equals(dtoReq.getOco1SasinariHouhou(), EXECUTE_METHOD_PRICE_LIMIT)) {
                inData.setDonePrice(String.format("%10s", dtoReq.getOco1Price()).replace(" ", "0"));
            }
        }
        
        // DONERBE注文種別
        inData.setDoneRbeOrderKind(DONE_RBE_ORDER_KIND_NONE); // 設定値がないの場合の値で初期化
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFD)) {
            if (StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), EXECUTE_METHOD_PRICE_LIMIT)
                    || StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), EXECUTE_METHOD_MARKET_ORDER)) {
                inData.setDoneRbeOrderKind(DONE_RBE_ORDER_KIND_NORMAL);
            }
            if (StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), EXECUTE_METHOD_REVERSE_PRICE_LIMIT)) {
                inData.setDoneRbeOrderKind(DONE_RBE_ORDER_KIND_SLO);
            }
        }
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFDOCO)) {
            inData.setDoneRbeOrderKind(DONE_RBE_ORDER_KIND_OCO);
        }
        
        // DONEトリガ発動ゾーン
        inData.setDoneTriggerZone(DONE_TRIGGER_ZONE_NONE); // 設定値がないの場合の値で初期化
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFD)) {
            if (StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), EXECUTE_METHOD_REVERSE_PRICE_LIMIT)) {
                inData.setDoneTriggerZone(DONE_TRIGGER_ZONE_UNDER);
            }
        }
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFDOCO)) {
            inData.setDoneTriggerZone(DONE_TRIGGER_ZONE_UNDER);
        }
        
        // DONEトリガ値段（訂正値段）
        inData.setDoneTriggerPrice(DONE_TRIGGER_PRICE_NONE); // 設定値がないの場合の値で初期化
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFD)) {
            if (StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), EXECUTE_METHOD_REVERSE_PRICE_LIMIT)) {
                inData.setDoneTriggerPrice(String.format("%10s", dtoReq.getIfd2TriggerPrice()).replace(" ", "0"));
            }
        }
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFDOCO)) {
            inData.setDoneTriggerPrice(String.format("%10s", dtoReq.getOco2TriggerPrice()).replace(" ", "0"));
        }
        
        // DONEOCO指成区分
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFDOCO)) {
            inData.setDoneOcoSasinariKbn(dtoReq.getOco2GyakusasiJyouken());
        } else {
            inData.setDoneOcoSasinariKbn(DONE_OCO_SASINARI_KBN_NONE);
        }
        
        // DONEOCO指値（訂正値段）
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFDOCO)) {
            inData.setDoneOcoPrice(String.format("%10s", dtoReq.getOco2Price()).replace(" ", "0"));
        } else {
            inData.setDoneOcoPrice(DONE_OCO_SASHINE_NONE);
        }
        
        // 媒介
        inData.setIntermediary(INTERMEDIARY_VALUE);
        // IPアドレス
        inData.setIpAddress(IP_ADDRESS_VALUE);
        
        return inData;
    }
    
    /**
     * SQL001のリクエスト項目をセット
     *
     * @param dtoReq リクエストDTO
     * @param insSql003Req SQL003レスポンス
     * @return SQL001リクエスト
     * @throws Exception 例外
     */
    private IfaDomesticStockOrderCorrectConfirmSql001RequestModel setSql001Req(
            IfaDomesticStockOrderCorrectConfirmA001aDtoRequest dtoReq,
            IfaDomesticStockOrderCorrectConfirmSql003ResponseModel selSql003Res) throws Exception {
        
        IfaDomesticStockOrderCorrectConfirmSql001RequestModel insSql001Req = new IfaDomesticStockOrderCorrectConfirmSql001RequestModel();
        
        IfaDomesticStockOrderCorrectConfirmA001bDtoRequest dtoReqB = new IfaDomesticStockOrderCorrectConfirmA001bDtoRequest();
        BeanUtils.copyProperties(dtoReqB, dtoReq);
        StockModifyOrderAutoInData api002InData = setApi002Req(dtoReqB);
        
        // SQL003で取得する項目
        BeanUtils.copyProperties(insSql001Req, selSql003Res);
        
        // 同じEC受注番号のデータがない場合
        if (IFA_ORDER_SUB_NO_VALUE.equals(selSql003Res.getIfaOrderSubNo())) {
            // 部店コード
            insSql001Req.setButenCode(IfaCommonUtil.getCustomerCommon().getButenCode());
            // 口座番号
            insSql001Req.setAccountNumber(IfaCommonUtil.getCustomerCommon().getAccountNumber());
            // 顧客ID
            insSql001Req.setCustomerId(IfaCommonUtil.getCustomerCommon().getCustomerCode());
            
            // 特定口座区分
            // リクエスト.預り区分='5'、'6'、'7'、'J'　ではない
            if (!StringUtils.equals(dtoReq.getDepositType(), DOMESTIC_DEPOSIT_TYPE_JR_SPECIFIC)
                    && !StringUtils.equals(dtoReq.getDepositType(), DOMESTIC_DEPOSIT_TYPE_JR_GENERAL)
                    && !StringUtils.equals(dtoReq.getDepositType(), DOMESTIC_DEPOSIT_TYPE_JR_NISA)
                    && !StringUtils.equals(dtoReq.getDepositType(), DOMESTIC_DEPOSIT_TYPE_JR_NISA_J)) {
                // 顧客共通情報.特定口座区分
                insSql001Req.setSpecificKbn(IfaCommonUtil.getCustomerCommon().getSpecificAccountType());
            }
            // リクエスト.預り区分='5'、'6'、'7'、'J'
            if (StringUtils.equals(dtoReq.getDepositType(), DOMESTIC_DEPOSIT_TYPE_JR_SPECIFIC)
                    || StringUtils.equals(dtoReq.getDepositType(), DOMESTIC_DEPOSIT_TYPE_JR_GENERAL)
                    || StringUtils.equals(dtoReq.getDepositType(), DOMESTIC_DEPOSIT_TYPE_JR_NISA)
                    || StringUtils.equals(dtoReq.getDepositType(), DOMESTIC_DEPOSIT_TYPE_JR_NISA_J)) {
                // 顧客共通情報.ジュニア特定口座区分
                insSql001Req.setSpecificKbn(IfaCommonUtil.getCustomerCommon().getJrTokuteiKouzaKbn());
            }
        }
        
        // 銘柄コード
        insSql001Req.setBrandCd(dtoReq.getBrandCode());
        // 市場
        insSql001Req.setMarket(dtoReq.getAfterCorrectMarket());
        // 注文状況
        insSql001Req.setOrderStatus(ORDER_STATUS_VALUE);
        // 取引種別
        insSql001Req.setTradeKbn(dtoReq.getTradeCd());
        // 数量
        insSql001Req.setQuantity(dtoReq.getQuantity());
        // 有効期限
        insSql001Req.setLimit(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getPeriod())); // 期間.日付をYYYYMMDD形式に変換
        // 預り区分
        insSql001Req.setAzukariKbn(dtoReq.getDepositType());
        // 注文種別
        insSql001Req.setOrderSyubetsu(dtoReq.getOrderKind());
        
        // 注文種別（一覧）
        if (StringUtils.equals(dtoReq.getDoneState(), DONE_STATE_DONE)) {
            if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_NORMAL)) {
                if (StringUtils.equals(dtoReq.getSasinariHouhou(), EXECUTE_METHOD_REVERSE_PRICE_LIMIT)) {
                    insSql001Req.setOrderSyubetsuList(ORDER_SYUBETSU_LIST_IFD2_REVERSE_PRICE_LIMIT);
                } else {
                    insSql001Req.setOrderSyubetsuList(ORDER_SYUBETSU_LIST_IFD2_NORMAL);
                }
            }
            if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_OCO)) {
                insSql001Req.setOrderSyubetsuList(ORDER_SYUBETSU_LIST_IFD2_OCO);
            }
        } else {
            if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_NORMAL)) {
                if (StringUtils.equals(dtoReq.getSasinariHouhou(), EXECUTE_METHOD_REVERSE_PRICE_LIMIT)) {
                    insSql001Req.setOrderSyubetsuList(ORDER_SYUBETSU_LIST_REVERSE_PRICE_LIMIT);
                } else {
                    insSql001Req.setOrderSyubetsuList(ORDER_SYUBETSU_LIST_NORMAL);
                }
            }
            if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_OCO)) {
                insSql001Req.setOrderSyubetsuList(ORDER_SYUBETSU_LIST_OCO);
            }
            if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFD)
                    || StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFDOCO)) {
                if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), EXECUTE_METHOD_REVERSE_PRICE_LIMIT)) {
                    insSql001Req.setOrderSyubetsuList(ORDER_SYUBETSU_LIST_IFD1_REVERSE_PRICE_LIMIT);
                } else {
                    insSql001Req.setOrderSyubetsuList(ORDER_SYUBETSU_LIST_IFD1_NORMAL);
                }
            }
        }
        
        // 勧誘区分
        insSql001Req.setKanyuKbn(dtoReq.getKanyuKbn());
        // 受注方法
        insSql001Req.setJutyuKbn(dtoReq.getReceiveOrderType());
        // 確認項目.インサイダー確認
        insSql001Req.setCheckInsider(dtoReq.getCheckInsider());
        
        // アラート内容確認.コンプラチェックワーニング確認
        if (StringUtils.equals(dtoReq.getTradeCd(), DOMESTIC_STOCK_TRADE_CLASS_SPOT_BUY)) {
            if (StringUtils.equals(dtoReq.getChkBoxLabel(), COMPLA_CHECK_BOX_WORDING_APPROVED)) {
                insSql001Req.setCheckCompWrnAlert(dtoReq.getInvitationCheck());
            } else if (StringUtils.equals(dtoReq.getChkBoxLabel(), COMPLA_CHECK_BOX_WORDING_NONE)) {
                insSql001Req.setCheckCompWrnAlert(CHECK_COMP_WRN_ALERT_NONE);
            } else {
                insSql001Req.setCheckCompWrnAlert(CHECK_COMP_WRN_ALERT_OTHER);
            }
        } else {
            insSql001Req.setCheckCompWrnAlert(null);
        }
        
        StockModifyOrderInData api001InData = setApi001Req(dtoReqB);
        // 資金性格区分
        insSql001Req.setShikinSeikakuKbn(dtoReq.getFundComplianceCheck());
        // ユーザーＩＤ
        insSql001Req.setUserId(api001InData.getUserId());
        // 内部コード⇒外部コードの変換処理
        // 手数料区分
        insSql001Req.setTesuuryouKbn(codeListService.convertKeyToExtKey("COMM_TYPE", "EC-GW", api001InData.getComId()));
        // 訂正区分
        insSql001Req.setAlterFlg(ALTER_FLG_VALUE);
        // トリガ発動ゾーン初期値
        String triggerZone = TRIGGER_ZONE_NONE;
        // トリガ値段初期値
        String triggerPrice = TRIGGER_PRICE_NONE;
        // API001呼び出し時（注文種別が通常/逆指値、OCO注文）
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_NORMAL)
                || StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_OCO)) {
            
            // RBE注文種別
            insSql001Req.setRbeOrderKind(api001InData.getRbeOrderKind());
            // 指成区分
            insSql001Req.setSasinariKbn(api001InData.getSasinariKbn());
            // 指値
            insSql001Req.setPrice(api001InData.getPrice());
            // トリガ発動ゾーン編集
            // トリガ値段編集
            if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_NORMAL)) {
                // 注文種別=通常/逆指値
                if (EXECUTE_METHOD_REVERSE_PRICE_LIMIT.equals(dtoReq.getSasinariHouhou())) {
                    // 通常/逆指値.執行方法=逆指値
                    if (DOMESTIC_STOCK_TRADE_CLASS_SPOT_BUY.equals(dtoReq.getTradeCd())) {
                        // 取引種別=現物買付
                        triggerZone = TRIGGER_ZONE_OVER;
                    } else if (DOMESTIC_STOCK_TRADE_CLASS_SPOT_SELL.equals(dtoReq.getTradeCd())) {
                        // 取引種別=現物売却
                        triggerZone = TRIGGER_ZONE_UNDER;
                    }
                    triggerPrice = dtoReq.getTriggerPrice();
                }
            } else {
                // 注文種別=OCOの場合
                if (DOMESTIC_STOCK_TRADE_CLASS_SPOT_BUY.equals(dtoReq.getTradeCd())) {
                    // 取引種別=現物買付
                    triggerZone = TRIGGER_ZONE_OVER;
                } else if (DOMESTIC_STOCK_TRADE_CLASS_SPOT_SELL.equals(dtoReq.getTradeCd())) {
                    // 取引種別=現物売却
                    triggerZone = TRIGGER_ZONE_UNDER;
                }
                triggerPrice = dtoReq.getOco2TriggerPrice();
            }
            // OCO指成区分
            insSql001Req.setOcoSasinariKbn(api001InData.getOcoSasinariKbn());
            // OCO指値
            if (StringUtils.equals(api001InData.getOcoPrice(), OCO_SASHINE_NONE)) {
                insSql001Req.setOcoSashine(null);
            } else {
                insSql001Req.setOcoSashine(api001InData.getOcoPrice());
            }
        }
        
        // API002呼び出し時（注文種別がIFD、IFDOCO注文）
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFD)
                || StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_IFDOCO)) {
            
            // RBE注文種別
            insSql001Req.setRbeOrderKind(api002InData.getRbeOrderKind());
            // 指成区分
            insSql001Req.setSasinariKbn(api002InData.getSasinariKbn());
            // 指値
            insSql001Req.setPrice(api002InData.getPrice());
            // トリガ発動ゾーン編集 トリガ値段編集
            if (EXECUTE_METHOD_REVERSE_PRICE_LIMIT.equals(dtoReq.getIfd1SasinariHouhou())) {
                // ■IFD1.執行方法=逆指値
                triggerZone = TRIGGER_ZONE_OVER;
                triggerPrice = dtoReq.getIfd1TriggerPrice();
            }
            
            // OCO指成区分
            insSql001Req.setOcoSasinariKbn(api002InData.getOcoSasinariKbn());
            // OCO指値
            if (StringUtils.equals(api002InData.getOcoPrice(), OCO_SASHINE_NONE)) {
                insSql001Req.setOcoSashine(null);
            } else {
                insSql001Req.setOcoSashine(api002InData.getOcoPrice());
            }
            
            // API002呼び出し時のみ設定する項目
            // 自動注文種別
            insSql001Req.setAutoOrderKind(api002InData.getAutoOrderKind());
            // DONE指成区分
            insSql001Req.setDoneSasinariKbn(api002InData.getDoneSasinariKbn());
            // DONE指値
            insSql001Req.setDoneSashine(api002InData.getDonePrice());
            // DONERBE注文種別
            insSql001Req.setDoneRbeOrderKind(api002InData.getDoneRbeOrderKind());
            // DONEトリガ発動ゾーン
            insSql001Req.setDoneTriggerZone(api002InData.getDoneTriggerZone());
            // DONEトリガ値段
            insSql001Req.setDoneTriggerNedan(api002InData.getDoneTriggerPrice());
            // DONEOCO指成区分
            insSql001Req.setDoneOcoSasinariKbn(api002InData.getDoneOcoSasinariKbn());
            // DONEOCO指値
            insSql001Req.setDoneOcoSashine(api002InData.getDoneOcoPrice());
        }
        // トリガ発動ゾーン
        insSql001Req.setTriggerZone(triggerZone);
        // トリガ値段
        insSql001Req.setTriggerPrice(triggerPrice);
        // DONE有効期限
        insSql001Req.setDoneLimit(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getIfd2Limit())); // IFD2.期間.日付をYYYYMMDD形式に変換
        // EC受注番号訂正時
        insSql001Req.setAlterOrderNum(dtoReq.getEcOrderNo());
        // RBE注文ステータス
        insSql001Req.setRbeOrderStatus(dtoReq.getRbeOrderStatus());
        // 商品区分
        insSql001Req.setOrderType(ORDER_TYPE_VALUE);
        // EC受注番号
        insSql001Req.setOrderNum(dtoReq.getEcOrderNo());
        // 仲介業者コード
        insSql001Req.setBrokerCode(IfaCommonUtil.getCustomerCommon().getBrokerCode());
        // 仲介業者営業員コード
        insSql001Req.setIntermediaryEmpCd(IfaCommonUtil.getCustomerCommon().getBrokerChargeCode());
        // 作成者
        insSql001Req.setCreateUser(IfaCommonUtil.getUserAccount().getUserId());
        // 更新者
        insSql001Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
        
        return insSql001Req;
    }
}
