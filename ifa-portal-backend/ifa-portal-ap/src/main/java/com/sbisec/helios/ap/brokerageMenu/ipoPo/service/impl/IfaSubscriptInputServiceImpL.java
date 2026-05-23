package com.sbisec.helios.ap.brokerageMenu.ipoPo.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ibm.icu.math.BigDecimal;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.component.Fct031;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct031Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto.MediatePropriety;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct031Dto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IfaBbApplyInputDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IfaSubscriptInputDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA002ComplianceRankCheckResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.service.IfaSubscriptInputService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.enums.RtnCdEnum;
import com.sbisec.helios.ap.common.enums.SpecificAccount;
import com.sbisec.helios.ap.common.enums.ipopo.OrderStatus;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceOutData;

/**
 * 画面ID：SUB0204_02-04_1
 * 画面名：募集入力
 * 2024/02/02 新規作成
 *
 * @author SCSK 江口
 */
@Component(value = "cmpIfaSubscriptInputService")
public class IfaSubscriptInputServiceImpL implements IfaSubscriptInputService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSubscriptInputServiceImpL.class);
    
    @Autowired
    private ApiWrapper apiWrapper;
    
    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct006 fct006;
    
    @Autowired
    private Fct021 fct021;
    
    @Autowired
    private Fct031 fct031;
    
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private IfaSubscriptInputDao dao;
    
    @Autowired
    private IfaBbApplyInputDao bbApplyInputDao;
    
    /** 顧客に対する権限なしエラー */
    private static final String ERRORS_BUTEN_ACCOUNT_NOT_EXIST = "errors.butenAccountNotExist";
    
    /** 取引停止口座エラー */
    private static final String ERRORS_CMN_SELECTED_ACCOUNT_OUT_OF_SERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** 取引不可エラー */
    private static final String ERRORS_CMN_SELECTED_ACCOUNT_COURCE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 買い付け余力情報取得失敗エラー */
    private static final String ERRORS_NRI_QUERY_ACCOUNT_BALANCE = "errors.nriQueryAccountBalance";
    
    /** JrNISA買付不可エラー */
    private static final String ERRORS_ORDER_JR_NISA = "errors.orderJrNISA";
    
    /** つみたてNISA買付不可エラー */
    private static final String ERRORS_ORDER_TSUMITATE_NISA = "errors.orderTsumitateNISA";
    
    /** 預り区分アラート.NISA買付可能 かつ 一般預りを選択 */
    private static final String INFO_GENERAL_NOT_NISA = "info.General.NotNISA";
    
    /** 預り区分アラート.NISA買付可能 かつ 特定預りを選択 */
    private static final String INFO_SPECIFIC_NOT_NISA = "info.Specific.NotNISA";
    
    /** 預り区分アラート.特定預り可能 かつ　NISA預りを選択 */
    private static final String INFO_NISA_NOT_SPECIFIC = "info.NISA.NotSpecific";
    
    /** 預り区分アラート.特定預り可能 かつ 一般預りを選択 */
    private static final String INFO_GENERAL_NOT_SPECIFIC = "info.General.NotSpecific";
    
    /** 募集期間外エラー */
    private static final String ERRORS_PERIOD_CHECK = "errors.periodCheck";
    
    /** 募集終了時間エラー */
    private static final String ERRORS_ORDER_LAST_TIME = "errors.orderLastTime";
    
    /** 当選株数未入力エラー */
    private static final String ERRORS_BB_QUANTITY_ALLOC_CHECK_INPUT = "errors.bbQuantityAllocCheckInput";
    
    /** 当選株数0エラー */
    private static final String ERRORS_NUMBER_INSERT_CHECK = "errors.numberInsertCheck";
    
    /** 当選株数売買単位外エラー */
    private static final String ERRORS_UNIT_CHECK = "errors.unitCheck";
    
    /** 当選株数未入力エラー */
    private static final String ERRORS_REQUIRED = "errors.required";
    
    /** 当選株数入力エラー(入力されていないことが期待されるケース) */
    private static final String ERRORS_BB_QUANTITY_ALLOC_CHECK = "errors.bbQuantityAllocCheck";
    
    /** 数量 > 当選株数エラー */
    private static final String ERRORS_COUNT_UNIT_CHECK = "errors.countUnitCheck";
    
    /** 数量 > 配分上限株数エラー */
    private static final String ERRORS_IPO_TRADE_RANGE = "errors.ipoTradeRange";
    
    /** 口座の取引制限.注意情報エラー */
    private static final String ERRORS_CMN_NOTICE_ERROR_CHECK = "errors.cmn.noticeErrorCheck";
    
    /** 口座の取引制限.お知らせエラー */
    private static final String ERRORS_INFORMATION_CHECK = "errors.informationCheck";
    
    /** 口座の取引制限.注意情報アラートあり */
    private static final String WARNINGS_CMN_NOTICE_WARNING_CHECK = "warnings.cmn.noticeWarningCheck";
    
    /** 口座の取引制限.お知らせアラートあり */
    private static final String WARNINGS_CMN_INFORMATION_CHECK = "warnings.cmm.informationCheck";
    
    /** 特定口座未開設エラー */
    private static final String ERRORS_SPECIFIC_ACCOUNT_CHECK = "errors.specificAccountCheck";
    
    /** NISA口座未開設エラー */
    private static final String ERRORS_NISA_ACCOUNT_CHECK = "errors.nisaAccountCheck";
    
    /** NISA口座買付不可エラー */
    private static final String ERRORS_NISA_ACCOUNT_BUY_CHECK = "errors.nisaAccountBuyCheck";
    
    /** 重要事項未説明エラー */
    private static final String ERRORS_EXPLAIN_IMPORTANT_INFORMATION = "errors.explainImportantInformation";
    
    /** 約定金額 > 買付余力エラー */
    private static final String ERRORS_BUY_LIMIT_CHECK = "errors.buyLimitCheck";
    
    /** 約定金額 > NISA買付可能額エラー */
    private static final String ERRORS_EXCEEDED_MAXIMUM = "errors.exceededMaximum";
    
    /** セクション関連情報なしエラー */
    private static final String ERRORS_SECTION_ID = "errors.sectionId";
    
    /** 目論見書閲覧日時未来エラー */
    private static final String ERRORS_FUTURE_TIME_CHECK = "errors.futureTimeCheck";
    
    /** レスポンス.メッセージ.空 */
    private static final String RESPONSE_MESSAGE_EMPTY = "";
    
    /** 対象の顧客に対する参照権限.権限あり */
    private static final String TARGET_CUSTOMER_REF_AUTH_FAIL = "0";
    
    /** 取引停止フラグ.取引停止口座 */
    private static final String TRADE_SUSPEND_FLAG_SUSPEND = "1";
    
    /** 証券金銭種別.国内株式 */
    private static final String SECURITY_MONEY_CLASS_DOMESTIC_STOCK = "01";
    
    /** 取引種別.区分.取引種別（国内株式）.募集 */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_SUBSCRIPT = "A";
    
    /** 媒介可否リスト.媒介可否.媒介可 */
    private static final String INTERMEDIARYVALUE_POSSIBLE = "1";
    
    /** 区分ID:対象取引（メッセージ表示用） */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 区分.対象取引（メッセージ表示用）.IPO/PO取引 */
    private static final String MSG_DISPLAY_TARGET_TRADE_IPO_PO = "8";

    /** 区分.重要事項の説明.説明不要を確認（属性登録済） */
    private static final String IMPORTANT_MATTERS_EXPLAIN_NO_EXPLAIN_CONFIRM = "1";

    /** エラーメッセージ：当選株数 */
    private static final String MSG_BB_QUANTITY_ALLOC = "当選株数";
    
    /** エラーメッセージ：数量 */
    private static final String MSG_UNIT = "数量";
    
    /** エラーメッセージ：配分上限株数 */
    private static final String MSG_MAX_ALLOCATION = "配分上限株数";
    
    /** エラーメッセージ：NISあ買付可能額 */
    private static final String MSG_NISA_BUY_LIMIT = "NISA買付可能額";
    
    /** エラーメッセージ：セクションID */
    private static final String MSG_SECTION_ID = "セクションID";
    
    /** ジュニアNISAフラグ.ジュニアNISA口座 */
    private static final String JUNIOR_NISA_FLAG_JUNIOR_NISA_ACCOUNT = "1";
    
    /** ジュニアNISAフラグ.非ジュニアNISA口座 */
    private static final String JUNIOR_NISA_FLAG_NOT_JUNIOR_NISA_ACCOUNT = "0";
    
    /** 預り区分.一般預り */
    private static final String DEPOSIT_TYPE_GENERAL = "1";
    
    /** 預り区分.特定預り */
    private static final String DEPOSIT_TYPE_SPECIFIC = "2";
    
    /** 預り区分.旧NISA */
    private static final String DEPOSIT_TYPE_NISA_OLD = "4";
    
    /** 預り区分.NISA（成長投資枠） */
    private static final String DEPOSIT_TYPE_NISA_GROWTH_INVESTMENT = "H";
    
    /** 特定口座区分.特定口座(代行納付) */
    private static final String SPECIFIC_KBN_SPECIFIC_DEDUCTION_AT_SOURCE = "1";
    
    /** 特定口座区分.特定口座(確定申告) */
    private static final String SPECIFIC_KBN_SPECIFIC_TAX_RETURN = "2";
    
    /** つみたてNISAフラグ.つみたてNISA口座 */
    private static final String ACCUMULATE_NISA_FLAG_ACCUMULATE_NISA_ACCOUNT = "1";
    
    /** つみたてNISAフラグ.非つみたてNISA口座 */
    private static final String ACCUMULATE_NISA_FLAG_NOT_ACCUMULATE_NISA_ACCOUNT = "0";
    
    /** 抽選結果.当選 */
    private static final String LOTTERY_RESULT_WIN = "1";
    
    /** 抽選結果.当選（繰上） */
    private static final String LOTTERY_RESULT_WIN_KURIAGE = "3";
    
    /** 抽選結果.裁量 */
    private static final String LOTTERY_RESULT_DISCRETION = "4";
    
    /** 抽選結果.申込取消 */
    private static final String LOTTERY_RESULT_APPLY_CANCEL = "5";
    
    /** 注意情報エラー有無.あり */
    private static final String NOTICE_ERROR_EXISTS = "1";
    
    /** 注意情報アラート有無.あり */
    private static final String NOTICE_WARNING_EXISTS = "1";
    
    /** お知らせエラー有無.あり */
    private static final String INFORMATION_ERROR_EXISTS = "1";
    
    /** お知らせアラート有無.あり */
    private static final String INFORMATION_WARNING_EXISTS = "1";
    
    /** 国籍コード.BB申込一覧 */
    private static final String COUNTRY_CODE_BB_APPLY_LIST = "99";
    
    /** 通貨コード.BB申込一覧 */
    private static final String CURRENCY_CODE_BB_APPLY_LIST = "999";
    
    /** ISA契約区分.契約 */
    private static final String ISA_CONTRACT_TYPE_CONTRACT = "1";
    
    /** ISA買付可能判定区分(当年).可能(NISA)※ジュニアNISAも含む */
    private static final String ISA_BUY_ABLE_JUDGE_CLASSIFICATION_YEAR_POSSIBLE_NISA = "1";
    
    /** ISA買付可能判定区分(当年).可能(つみたてNISA) */
    private static final String ISA_BUY_ABLE_JUDGE_CLASSIFICATION_YEAR_POSSIBLE_TSUMITATE_NISA = "2";
    
    /** 国内外国区分.国内 */
    private static final String DOMESTIC_FOREIGN_TYPE_DOMESTIC = "0";
    
    /** 商品区分.株式 */
    private static final String SECURITY_TYPE_STOCK = "1 ";
    
    /** コンプラチェック種類.買付注文 */
    private static final String COMPLA_CHECK_KIND_BUY_ORDER = "1";
    
    /** コンプラチェック判定結果.ノーマル */
    private static final String COMPLA_CHECK_JUDGMENT_RESULT_NORMAL = "0";
    
    /** コンプラチェック判定結果.アラート */
    private static final String COMPLA_CHECK_JUDGMENT_RESULT_ALERT = "1";
    
    /** コンプラチェック判定結果.エラー */
    private static final String COMPLA_CHECK_JUDGMENT_RESULT_ERROR = "2";
    
    /** 送信・訂正用ロジック処理判定フラグ.管理者新規注文 */
    protected static final String SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIINSERT = "SBIINSERT";
    
    /** 送信・訂正用ロジック処理判定フラグ.管理者更新注文 */
    protected static final String SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIUPDATEONE = "SBIUPDATEONE";
    
    /** 送信・訂正用ロジック処理判定フラグ.管理者訂正注文(注文状況のみ更新可能) */
    protected static final String SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIUPDATETWO = "SBIUPDATETWO";
    
    /** 送信・訂正用ロジック処理判定フラグ.仲介業者新規注文 */
    protected static final String SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERINSERT = "BROKERINSERT";
    
    /** 送信・訂正用ロジック処理判定フラグ.仲介業者更新注文 */
    protected static final String SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERUPDATE = "BROKERUPDATE";
    
    /** 送信・訂正用ロジック処理判定フラグ.仲介業者訂正注文 */
    protected static final String SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERMODIFY = "BROKERMODIFY";
    
    /** 送信・訂正用ロジック処理判定フラグ.その他 */
    protected static final String SEND_CORRECT_LOGIC_JUDGE_FLAG_OTHER = "OTHER";
    
    /** 注意情報レベル.注意情報なし */
    private static final String NOTICE_INFO_LEVEL_NO_INFO = "1";
    
    /** 注意情報レベル.注意情報あり（エラーなし） */
    private static final String NOTICE_INFO_LEVEL_HAS_INFO = "2";
    
    /** 注意情報レベル.注意情報あり（エラーあり） */
    private static final String NOTICE_INFO_LEVEL_HAS_INFO_WITH_ERROR = "3";
    
    /** 注文フラグ.新規注文 */
    private static final String ORDER_FLAG_INSERT = "1";
    
    /** 注文フラグ.更新注文 */
    private static final String ORDER_FLAG_UPDATE = "2";
    
    /** 注文フラグ.訂正注文 */
    private static final String ORDER_FLAG_MODIFY = "3";
    
    /** 注文フラグ.取消注文 */
    private static final String ORDER_FLAG_CANCEL_APPLY = "4";
    
    /** ISA買付余力(当年)(JrNISA).非ジュニアNISA口座 */
    private static final String ISA_BUY_LIMIT_JR_NISA_NOT_JR_NISA_ACCOUNT = " ";
    
    /** ISA買付余力(当年).非NISA口座 */
    private static final String ISA_BUY_LIMIT_NOT_NISA_ACCOUNT = " ";
    
    /** 移管前部店（値なし） */
    private static final String OLD_BUTEN_CODE = "0";
    
    /** 移管前口座番号（値なし） */
    private static final String OLD_ACCOUNT_NUMBER = "0";
    
    /** 当選株数0 */
    private static final String BB_QUANTITY_ALLOC_ZERO = "0";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaSubscriptInputA001RequestDto
     * Dto レスポンス：IfaSubscriptInputA001ResponseDto
     * model リクエスト：IfaSubscriptInputSql004RequestModel
     * model レスポンス：IfaSubscriptInputSql004ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptInputA001ResponseDto> initializeA001(IfaSubscriptInputA001RequestDto dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSubscriptInputServiceImplL.initializeA001");
        }
        
        // レスポンスにセットする値
        IfaSubscriptInputA001ResponseDto innerData = new IfaSubscriptInputA001ResponseDto();
        
        /* ====================================================================== */
        /* 利用者の口座に対する権限チェック、取引コース媒介可否チェック         　　   */
        /* ====================================================================== */
        
        DataList<IfaSubscriptInputA001ResponseDto> checkAuthResult = checkAuth(dtoReq.getButenCode(),
                dtoReq.getAccountNumber());
        if (checkAuthResult != null) {
            return checkAuthResult;
        }
        
        /* ====================================================================== */
        /* 特定の条件を満たした場合、送信・訂正用ロジック処理判定までスキップ           */
        /* ====================================================================== */
        String orderStatus = OrderStatus.NOT_ORDER.getId();
        String depositType = "";
        
        DataList<IfaSubscriptInputSql001ResponseModel> selSql001Res = new DataList<>();
        do {
            /* ====================================================================== */
            /* 募集の初期情報を取得する(SQL001)                                         */
            /* ====================================================================== */
            
            // SQL001のリクエストを作成
            IfaSubscriptInputSql001RequestModel selSql001Req = new IfaSubscriptInputSql001RequestModel();
            BeanUtils.copyProperties(selSql001Req, dtoReq);
            
            // SQL001を実行
            selSql001Res = dao.selectIfaSubscriptInputSql001(selSql001Req);
            
            // SQL001.結果件数=0件の場合、送信・訂正用ロジック処理判定までスキップ
            if (selSql001Res.getDataList().size() == 0) {
                break;
            }
            
            // SQL001の結果をinnerDataに格納する
            BeanUtils.copyProperties(innerData, selSql001Res.getDataList().get(0));
            orderStatus = selSql001Res.getDataList().get(0).getOrderStatus();
            depositType = selSql001Res.getDataList().get(0).getDepositType();
            
            /* ====================================================================== */
            /* 預り資産額を取得する                                                     */
            /* ====================================================================== */
            IfaBbApplyInputSql007RequestModel bbApplyInputSql007Req = new IfaBbApplyInputSql007RequestModel();
            bbApplyInputSql007Req.setButenCode(dtoReq.getButenCode());
            bbApplyInputSql007Req.setAccountNumber(dtoReq.getAccountNumber());
            
            DataList<IfaBbApplyInputSql007ResponseModel> bbApplyInputSql007Res = bbApplyInputDao
                    .selectIfaBbApplyInputSql007(bbApplyInputSql007Req);
            innerData.setDepositAssetAmount(bbApplyInputSql007Res.get(0).getDepositAssetAmount());
            
            /* ====================================================================== */
            /* 年間裁量配分割り当て回数ベース情報を取得する(SQL002)                       */
            /* ====================================================================== */
            
            // SQL002のリクエストを作成
            IfaSubscriptInputSql002RequestModel selSql002Req = new IfaSubscriptInputSql002RequestModel();
            BeanUtils.copyProperties(selSql002Req, dtoReq);
            
            // SQL002を実行
            DataList<IfaSubscriptInputSql002ResponseModel> selSql002Res = dao
                    .selectIfaSubscriptInputSql002(selSql002Req);
            
            // データが0件の場合、本年の年間裁量配分割当回数と本年の年間裁量配分可能回数をセットして
            // 送信・訂正用ロジック処理判定までスキップ
            if (selSql002Res.getDataList().size() == 0) {
                innerData.setDiscretionAlloCount(selSql001Res.getDataList().get(0).getDiscretionAlloCount());
                innerData.setDiscretionAllocateAbleTimes(
                        selSql001Res.getDataList().get(0).getDiscretionAllocateAbleTimes());
                
                break;
            }
            
            /* ====================================================================== */
            /* 裁量配分割り当て回数(未抽選)を取得する(SQL003)                            */
            /* ====================================================================== */
            
            // SQL003のリクエストを作成
            IfaSubscriptInputSql003RequestModel selSql003CurrentReq = new IfaSubscriptInputSql003RequestModel();
            BeanUtils.copyProperties(selSql003CurrentReq, dtoReq);
            selSql003CurrentReq.setAccountNumber(dtoReq.getAccountNumber());
            selSql003CurrentReq.setButenCode(dtoReq.getButenCode());
            
            // SQL003を実行
            DataList<IfaSubscriptInputSql003ResponseModel> selSql003CurrentRes = dao //SUPPRESS CHECKSTYLE
                    .selectIfaSubscriptInputSql003(selSql003CurrentReq);
            
            /* ====================================================================== */
            /* 移管前の裁量配分割り当て回数(未抽選)を取得する(SQL003)                     */
            /* ====================================================================== */
            
            // 移管前の口座番号と部店コードを取得(SQL002で結果が取得できない場合、固定値"0"を指定)
            String oldAccountNumber = OLD_ACCOUNT_NUMBER;
            String oldButenCode = OLD_BUTEN_CODE;
            
            String oldAccountNumberSelSql002 = selSql002Res.getDataList().get(0).getOldAccountNumber();
            if (oldAccountNumberSelSql002 != null) {
                oldAccountNumber = oldAccountNumberSelSql002;
            }
            
            String oldButenCodeSelSql002 = selSql002Res.getDataList().get(0).getOldButenNumber();
            if (oldButenCodeSelSql002 != null) {
                oldButenCode = oldButenCodeSelSql002;
            }
            
            // SQL003のリクエストを作成
            IfaSubscriptInputSql003RequestModel selSql003OldReq = new IfaSubscriptInputSql003RequestModel();
            BeanUtils.copyProperties(selSql003OldReq, dtoReq);
            selSql003OldReq.setAccountNumber(oldAccountNumber);
            selSql003OldReq.setButenCode(oldButenCode);
            
            // SQL003を実行
            DataList<IfaSubscriptInputSql003ResponseModel> selSql003OldRes = dao
                    .selectIfaSubscriptInputSql003(selSql003OldReq);
            
            /* ====================================================================== */
            /* 年間裁量配分割り当て回数情報を設定する                                    */
            /* ====================================================================== */
            
            innerData.setDiscretionAlloCount(String.valueOf(Integer.parseInt(selSql003CurrentRes.getDataList().get(0).getRowCount()) // 裁量配分割当回数(未抽選)
                    + Integer.parseInt(selSql003OldRes.getDataList().get(0).getRowCount()) // 移管前の裁量配分割当回数(未抽選)
                    + Integer.parseInt(selSql002Res.getDataList().get(0).getSairyouAlloCount()) // SQL002.本年の年間裁量配分割当回数
                    + Integer.parseInt(selSql002Res.getDataList().get(0).getOldSairyouAlloCount()))); // SQL002.移管前本年の年間裁量配分割当回数
            innerData.setDiscretionAllocateAbleTimes(selSql002Res.getDataList().get(0).getMaxSairyouAllo());
            
        } while (false);
        
        /* ====================================================================== */
        /* 送信・訂正用ロジック処理判定フラグを設定する                               */
        /* ====================================================================== */
        innerData.setSendCorrectLogicJudgeFlag(
                getSendCorrectLogicJudgeFlag(dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getBrandCode(),
                        dtoReq.getBookBuildingPresentationFrom(), orderStatus));
        
        /* ====================================================================== */
        /* 買付余力とNISA買付可能額を設定する                                        */
        /* ====================================================================== */
        
        // API001のリクエストを作成
        QueryAccountBalanceInData api001InData = new QueryAccountBalanceInData();
        api001InData.setKozaNo(dtoReq.getAccountNumber());
        api001InData.setButenCd(dtoReq.getButenCode());
        
        QueryAccountBalanceIn api001Input = new QueryAccountBalanceIn();
        api001Input.setIndata(api001InData);
        
        // API001を呼び出し
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        QueryAccountBalanceOutData api001OutData = new QueryAccountBalanceOutData();
        api001OutData = apiWrapper.queryAccountBalance(api001Input);
        if (apiErrorUtil.isError(api001OutData.getShubetu(), api001OutData.getCode(), api001OutData.getMessage())) {
            innerData.setDepositType(selSql001Res.getDataList().isEmpty() ? null : selSql001Res.getDataList().get(0).getDepositType());
            
            return IfaCommonUtil.createDataList(Arrays.asList(innerData), ErrorLevel.FATAL,
                    ERRORS_NRI_QUERY_ACCOUNT_BALANCE, IfaCommonUtil.getMessage(ERRORS_NRI_QUERY_ACCOUNT_BALANCE));
        }
        innerData.setYenBuyingPowerGeneralAccount(api001OutData.getT0().getBuyingPowerTotal());
        // 預り区分の初期値として、SQL001.預り区分を設定(後続処理で上書きの可能性あり)
        innerData.setDepositType(depositType);
        
        // ジュニアNISA口座の判定
        if (!ObjectUtils.isEmpty(api001OutData.getIsaBuyLimitJrnisa())) {
            // ジュニアNISA
            innerData.setJuniorNisaFlag(JUNIOR_NISA_FLAG_JUNIOR_NISA_ACCOUNT);
            
            // NISA買付可能額の設定
            innerData.setNisaBuyPotentialAmount(api001OutData.getIsaBuyLimitJrnisa());
        } else {
            // 非ジュニアNISA
            innerData.setJuniorNisaFlag(JUNIOR_NISA_FLAG_NOT_JUNIOR_NISA_ACCOUNT);
            
            // NISA買付可能額と預り区分の設定
            // 非ジュニアNISA口座の場合かつAPI001.ISA買付可能枠(当年)が空文字以外の場合
            if (!ObjectUtils.isEmpty(api001OutData.getIsaBuyLimit())) {
                innerData.setNisaBuyPotentialAmount(api001OutData.getIsaBuyLimit());
            } else {
                innerData.setNisaBuyPotentialAmount(api001OutData.getIsaSeityoBuyLimit());
                
            }
            
        }
        
        return apiErrorUtil.createDataList(Arrays.asList(innerData), null);
    }
    
    /**
     * アクションID：A002
     * アクション名：確認（仲介業者登録・管理者訂正）
     * Dto リクエスト：IfaSubscriptInputA002RequestDto
     * Dto レスポンス：IfaSubscriptInputA002ResponseDto
     * model リクエスト：IfaSubscriptInputSql008RequestModel
     * model レスポンス：IfaSubscriptInputSql008ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptInputA002ResponseDto> confirmIntermediaryRegistrationAdminCorrectionA002(
            IfaSubscriptInputA002RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSubscriptInputServiceImplL.confirmIntermediaryRegistrationAdminCorrectionA002");
        }
        
        // レスポンス用のinnerDataを作成し、リクエストをコピーする
        IfaSubscriptInputA002ResponseDto innerData = new IfaSubscriptInputA002ResponseDto();
        BeanUtils.copyProperties(innerData, dtoReq);
        
        /* ====================================================================== */
        /* 利用者の口座に対する権限チェック、取引コース媒介可否チェック                */
        /* ====================================================================== */
        
        DataList<IfaSubscriptInputA002ResponseDto> checkAuthResult = checkAuth(dtoReq.getButenCode(),
                dtoReq.getAccountNumber());
        if (checkAuthResult != null) {
            return checkAuthResult;
        }
        
        /* ====================================================================== */
        /* チェックマトリクスに従い、各種チェックを実行                               */
        /* ====================================================================== */
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        switch (dtoReq.getSendCorrectLogicJudgeFlag()) {
            case SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIINSERT: // fall-through
            case SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIUPDATEONE: // fall-through
            case SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIUPDATETWO:
                // 上場日のチェック
                DataList<IfaSubscriptInputA002ResponseDto> checkPresentationDateResultCase1 = checkPresentationDate(
                        dtoReq.getPresentationDate());
                if (checkPresentationDateResultCase1 != null) {
                    return checkPresentationDateResultCase1;
                }
                
                // 抽選結果に対する当選株数の紐づけチェック
                DataList<IfaSubscriptInputA002ResponseDto> checkLotteryResultAndInputResultCase1 = checkLotteryResultAndInput(
                        dtoReq.getLotteryResult(), dtoReq.getBbQuantityAlloc(), dtoReq.getUnit(),
                        dtoReq.getSellBuyUnitType());
                if (checkLotteryResultAndInputResultCase1 != null) {
                    return checkLotteryResultAndInputResultCase1;
                }
                
                break;
            
            case SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERINSERT: // fall-through
            case SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERUPDATE:
                // J-NISA口座のチェック
                DataList<IfaSubscriptInputA002ResponseDto> checkJrNisaResultCase2 = checkJrNisa(
                        dtoReq.getJuniorNisaFlag(), dtoReq.getDepositType());
                if (checkJrNisaResultCase2 != null) {
                    return checkJrNisaResultCase2;
                }
                
                // つみたてNISA口座のチェック
                DataList<IfaSubscriptInputA002ResponseDto> checkAccumulateNisaResultCase2 = checkAccumulateNisa(
                        dtoReq.getAccumulateNisaFlag(), dtoReq.getDepositType());
                if (checkAccumulateNisaResultCase2 != null) {
                    return checkAccumulateNisaResultCase2;
                }
                
                // 預り区分のアラートメッセージの表示判定
                List<String> depositTypeAlertList = checkDepositTypeAlert(dtoReq.getIsaBuyAbleJudgeClassificationYear(),
                        dtoReq.getIsaContractType(), dtoReq.getNisaBuyPotentialAmount(), dtoReq.getJuniorNisaFlag(),
                        dtoReq.getAccumulateNisaFlag(), dtoReq.getDepositType(), dtoReq.getTokuteiKouzaKbn());
                innerData.setDepositTypeAlert(depositTypeAlertList);
                
                // 上場日のチェック
                DataList<IfaSubscriptInputA002ResponseDto> checkPresentationDateResultCase2DataList = checkPresentationDate(
                        dtoReq.getPresentationDate());
                if (checkPresentationDateResultCase2DataList != null) {
                    return checkPresentationDateResultCase2DataList;
                }
                
                // 募集終了時間のチェック(各々エラーチェック前)
                DataList<IfaSubscriptInputA002ResponseDto> checkOrderLastTimeBeforeCase2 = checkOrderLastTime(
                        dtoReq.getBbPeriodTo());
                if (checkOrderLastTimeBeforeCase2 != null) {
                    return checkOrderLastTimeBeforeCase2;
                }
                
                // 数量のチェック
                DataList<IfaSubscriptInputA002ResponseDto> checkQuantityResultCase2 = checkQuantity(
                        dtoReq.getQuantity(), dtoReq.getBbQuantityAlloc(), dtoReq.getMaxAllocation(), dtoReq.getUnit(),
                        dtoReq.getSellBuyUnitType());
                if (checkQuantityResultCase2 != null) {
                    return checkQuantityResultCase2;
                }
                
                // 口座の取引制限チェック
                DataList<IfaSubscriptInputA002ResponseDto> checkAccountTradeRestrictionResultCase2 = checkAccountTradeRestriction(
                        dtoReq.getButenCode(), dtoReq.getAccountNumber());
                if (checkAccountTradeRestrictionResultCase2 != null) {
                    if (checkAccountTradeRestrictionResultCase2.getErrorLevel() == ErrorLevel.FATAL.getId()) { // エラーの場合
                        return checkAccountTradeRestrictionResultCase2;
                        
                    } else { // アラートがセットされている場合innerDataにコピー
                        innerData.setNoticeInfoAlert(
                                checkAccountTradeRestrictionResultCase2.getDataList().get(0).getNoticeInfoAlert());
                        innerData.setNoticeAlert(
                                checkAccountTradeRestrictionResultCase2.getDataList().get(0).getNoticeAlert());
                    }
                }
                
                // 預り区分のチェック
                DataList<IfaSubscriptInputA002ResponseDto> checkDepositTypeResultCase2 = checkDepositType(
                        dtoReq.getDepositType(), dtoReq.getCustomerCode());
                if (checkDepositTypeResultCase2 != null) {
                    return checkDepositTypeResultCase2;
                }
                
                // 最重要事項の説明のチェック
                DataList<IfaSubscriptInputA002ResponseDto> checkExplainImportantImformationResultCase2 = checkExplainImportantImformation(
                        dtoReq.getCustomerCode(), dtoReq.getImportantMatterType());
                if (checkExplainImportantImformationResultCase2 != null) {
                    return checkExplainImportantImformationResultCase2;
                }
                
                // コンプラランクのチェック
                DataList<IfaSubscriptInputA002ResponseDto> checkComplianceRankResultCase2 = checkComplianceRank(
                        dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getBrandCode(), dtoReq.getKanyuKbn(),
                        dtoReq.getJutyuKbn());
                if (checkComplianceRankResultCase2 != null) {
                    // エラーの場合エラーを返す
                    if (checkComplianceRankResultCase2.getErrorLevel() == ErrorLevel.FATAL.getId()) {
                        return checkComplianceRankResultCase2;
                    }
                    
                    // アラートの場合、innerDataにコンプライアンスチェックをコピー
                    innerData.setComplianceRankCheck(
                            checkComplianceRankResultCase2.getDataList().get(0).getComplianceRankCheck());
                }
                
                // 約定金額のチェック
                DataList<IfaSubscriptInputA002ResponseDto> checkContractAmountResultCase2 = checkContractAmount(
                        dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getDepositType(),
                        dtoReq.getContractAmount(), apiErrorUtil);
                if (checkContractAmountResultCase2 != null) {
                    return checkContractAmountResultCase2;
                }
                
                // 備考のチェック
                DataList<IfaSubscriptInputA002ResponseDto> checkBbRemarkCase2 = checkBbRemark(dtoReq.getButenCode(),
                        dtoReq.getAccountNumber(), dtoReq.getBbRemark(), dtoReq.getBbRemark2());
                if (checkBbRemarkCase2 != null) {
                    return checkBbRemarkCase2;
                }
                
                // 募集終了時間のチェック(各々エラーチェック後)
                DataList<IfaSubscriptInputA002ResponseDto> checkOrderLastTimeAfterCase2 = checkOrderLastTime(
                        dtoReq.getBbPeriodTo());
                if (checkOrderLastTimeAfterCase2 != null) {
                    return checkOrderLastTimeAfterCase2;
                }
                
                // 目論見書閲覧日時のチェック
                DataList<IfaSubscriptInputA002ResponseDto> checkReadTimeResultCase2 = checkReadTime(
                        dtoReq.getReadTime());
                if (checkReadTimeResultCase2 != null) {
                    return checkReadTimeResultCase2;
                }
                break;
            
            case SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERMODIFY:
                // 最重要事項の説明のチェック
                DataList<IfaSubscriptInputA002ResponseDto> checkExplainImportantImformationResultCase3 = checkExplainImportantImformation(
                        dtoReq.getCustomerCode(), dtoReq.getImportantMatterType());
                if (checkExplainImportantImformationResultCase3 != null) {
                    return checkExplainImportantImformationResultCase3;
                }
                
                // コンプラランクのチェック
                DataList<IfaSubscriptInputA002ResponseDto> checkComplianceRankResultCase3 = checkComplianceRank(
                        dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getBrandCode(), dtoReq.getKanyuKbn(),
                        dtoReq.getJutyuKbn());
                if (checkComplianceRankResultCase3 != null) {
                    // エラーの場合エラーを返す
                    if (checkComplianceRankResultCase3.getErrorLevel() == ErrorLevel.FATAL.getId()) {
                        return checkComplianceRankResultCase3;
                    }
                    
                    // アラートの場合、innerDataにコンプライアンスチェックをコピー
                    innerData.setComplianceRankCheck(
                            checkComplianceRankResultCase3.getDataList().get(0).getComplianceRankCheck());
                }
                
                // 備考のチェック
                DataList<IfaSubscriptInputA002ResponseDto> checkBbRemarkCase3 = checkBbRemark(dtoReq.getButenCode(),
                        dtoReq.getAccountNumber(), dtoReq.getBbRemark(), dtoReq.getBbRemark2());
                if (checkBbRemarkCase3 != null) {
                    return checkBbRemarkCase3;
                }
                
                // 募集終了時間のチェック(各々エラーチェック後)
                DataList<IfaSubscriptInputA002ResponseDto> checkOrderLastTimeCase3 = checkOrderLastTime(
                        dtoReq.getBbPeriodTo());
                if (checkOrderLastTimeCase3 != null) {
                    return checkOrderLastTimeCase3;
                }
                
                break;
            default:
                break;
        }
        
        /* ====================================================================== */
        /* 顧客情報を取得し、注意レベルを判定する(FCT031)                             */
        /* ====================================================================== */
        
        // FCT031のリクエストを作成
        InputFct031Dto fct031InputDto = new InputFct031Dto();
        fct031InputDto.setAccountNumber(dtoReq.getAccountNumber());
        fct031InputDto.setButenCode(dtoReq.getButenCode());
        
        // FCT031を実行
        OutputFct031Dto fct031OutputDto = fct031.getData(fct031InputDto);
        
        // 顧客名をinnerDataに格納
        innerData.setCustomerNameKana(fct031OutputDto.getCustomerNameKana());
        
        // FCT031.注意情報エラー件数＞０　または　FCT031.未読の重要なお知らせによる取引制限エラー件数＞0　の場合
        if (0 < fct031OutputDto.getNoteInfoErrorCount()
                || 0 < fct031OutputDto.getUnreadImportantNoticeTransactionLimitErrorNumber()) {
            // 注意情報レベル = 注意情報あり(エラーあり)をセット
            innerData.setNoticeInfoLevel(NOTICE_INFO_LEVEL_HAS_INFO_WITH_ERROR);
            
            // FCT031.注意情報件数＞０　または　FCT031.未読の重要なお知らせによる取引制限件数＞0　の場合
        } else if (0 < fct031OutputDto.getNoteInfoCount()
                || 0 < fct031OutputDto.getUnreadImportantNoticeTransactionLimitNumber()) {
            // 注意情報レベル = 注意情報あり(エラーなし)をセット
            innerData.setNoticeInfoLevel(NOTICE_INFO_LEVEL_HAS_INFO);
            
        } else {
            // 注意情報レベル = 注意情報なしをセット
            innerData.setNoticeInfoLevel(NOTICE_INFO_LEVEL_NO_INFO);
        }
        
        /* ====================================================================== */
        /* 送信・訂正用ロジック処理フラグに応じて、注文フラグを設定する                 */
        /* ====================================================================== */
        
        switch (dtoReq.getSendCorrectLogicJudgeFlag()) {
            case SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERINSERT:
            case SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERUPDATE: // fall-through
                // 注文フラグ = 新規注文
                innerData.setOrderFlag(ORDER_FLAG_INSERT);
                break;
            
            case SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIINSERT: // fall-through
            case SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIUPDATEONE: // fall-through
            case SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIUPDATETWO:
                // 注文フラグ = 更新注文
                innerData.setOrderFlag(ORDER_FLAG_UPDATE);
                break;
            
            case SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERMODIFY:
                // 注文フラグ = 訂正注文
                innerData.setOrderFlag(ORDER_FLAG_MODIFY);
                break;
            default:
                break;
        }
        
        // レスポンスを返す
        DataList<IfaSubscriptInputA002ResponseDto> dtoRes = apiErrorUtil.createDataList(Arrays.asList(innerData),null);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A003
     * アクション名：確認（仲介業者訂正）
     * Dto リクエスト：IfaSubscriptInputA003RequestDto
     * Dto レスポンス：IfaSubscriptInputA003ResponseDto
     * model リクエスト：IfaSubscriptInputSql005RequestModel
     * model レスポンス：IfaSubscriptInputSql005ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptInputA003ResponseDto> confirmIntermediaryCorrectionA003(
            IfaSubscriptInputA003RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSubscriptInputServiceImplL.confirmIntermediaryCorrectionA003");
        }
        
        // レスポンス用のinnerDataを作成し、リクエストをコピーする
        IfaSubscriptInputA003ResponseDto innerData = new IfaSubscriptInputA003ResponseDto();
        BeanUtils.copyProperties(innerData, dtoReq);
        
        /* ====================================================================== */
        /* 利用者の口座に対する権限チェック、取引コース媒介可否チェック                */
        /* ====================================================================== */
        
        DataList<IfaSubscriptInputA003ResponseDto> checkAuthResult = checkAuth(dtoReq.getButenCode(),
                dtoReq.getAccountNumber());
        if (checkAuthResult != null) {
            return checkAuthResult;
        }
        
        /* ====================================================================== */
        /* 募集終了時間のチェック                                                   */
        /* ====================================================================== */
        
        DataList<IfaSubscriptInputA003ResponseDto> checkOrderLastTime = checkOrderLastTime(dtoReq.getBbPeriodTo());
        if (checkOrderLastTime != null) {
            return checkOrderLastTime;
        }
        
        /* ====================================================================== */
        /* 顧客情報を取得し、注意レベルを判定する(FCT031)                             */
        /* ====================================================================== */
        
        // FCT031のリクエストを作成
        InputFct031Dto fct031InputDto = new InputFct031Dto();
        fct031InputDto.setAccountNumber(dtoReq.getAccountNumber());
        fct031InputDto.setButenCode(dtoReq.getButenCode());
        
        // FCT031を実行
        OutputFct031Dto fct031OutputDto = fct031.getData(fct031InputDto);
        
        // 顧客名をinnerDataに格納
        innerData.setCustomerNameKana(fct031OutputDto.getCustomerNameKana());
        
        // FCT031.注意情報エラー件数＞０　または　FCT031.未読の重要なお知らせによる取引制限エラー件数＞0　の場合
        if (0 < fct031OutputDto.getNoteInfoErrorCount()
                || 0 < fct031OutputDto.getUnreadImportantNoticeTransactionLimitErrorNumber()) {
            // 注意情報レベル = 注意情報あり(エラーあり)をセット
            innerData.setNoticeInfoLevel(NOTICE_INFO_LEVEL_HAS_INFO_WITH_ERROR);
            
            // FCT031.注意情報エラー件数＞０　または　FCT031.未読の重要なお知らせによる取引制限エラー件数＞0　の場合
        } else if (0 < fct031OutputDto.getNoteInfoCount()
                || 0 < fct031OutputDto.getUnreadImportantNoticeTransactionLimitNumber()) {
            // 注意情報レベル = 注意情報あり(エラーあり)をセット
            innerData.setNoticeInfoLevel(NOTICE_INFO_LEVEL_HAS_INFO);
            
        } else {
            // 注意情報レベル = 注意情報なしをセット
            innerData.setNoticeInfoLevel(NOTICE_INFO_LEVEL_NO_INFO);
        }
        
        // 注文フラグ = 取消注文
        innerData.setOrderFlag(ORDER_FLAG_CANCEL_APPLY);
        
        // レスポンスを返す
        DataList<IfaSubscriptInputA003ResponseDto> dtoRes = IfaCommonUtil.createDataList(Arrays.asList(innerData),
                ErrorLevel.SUCCESS, RtnCdEnum.SUCCESS.getText(), "");
        
        return dtoRes;
    }
    
    /**
     * A001～A003
     * 利用者の口座に対する権限チェックと取引コース媒介可否チェック
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     */
    private <T> DataList<T> checkAuth(String butenCode, String accountNumber) throws Exception {
        /* ====================================================================== */
        
        /* 利用者の口座に対する権限チェック(FCT001)                                  */
        /* ====================================================================== */
        
        // FCT001リクエストDTOの作成
        InputFct001Dto fct001InputDto = new InputFct001Dto();
        fct001InputDto.setAccountNumber(accountNumber);
        fct001InputDto.setButenCode(butenCode);
        
        // FCT001の実行
        OutputFct001Dto fct001OutputDto = fct001.doCheck(fct001InputDto);
        
        // 利用者の口座に対する権限を持っていない場合、権限なしエラーを返す
        if (fct001OutputDto == null
                || TARGET_CUSTOMER_REF_AUTH_FAIL.equals(fct001OutputDto.getTargetCustomerRefAuthFlag())) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_BUTEN_ACCOUNT_NOT_EXIST, IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                            new String[] { butenCode, accountNumber }));
            
            return dtoRes;
        }
        
        // 取引停止口座の場合、取引停止口座エラーを返す
        if (TRADE_SUSPEND_FLAG_SUSPEND.equals(fct001OutputDto.getTradeSuspendFlag())) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTED_ACCOUNT_OUT_OF_SERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_OUT_OF_SERVICE, new String[] {}));
            
            return dtoRes;
        }
        
        /* ====================================================================== */
        /* 取引コース媒介可否チェック(FCT003)                                       */
        /* ====================================================================== */
        
        // FCT003リクエストDTOの作成
        InputFct003Dto fct003InputDto = new InputFct003Dto();
        fct003InputDto.setAccountNumber(accountNumber);
        fct003InputDto.setButenCode(butenCode);
        fct003InputDto.setProductCd(SECURITY_MONEY_CLASS_DOMESTIC_STOCK);
        fct003InputDto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_SUBSCRIPT);
        
        // FCT003の実行
        OutputFct003Dto fct003OutputDto = fct003.doCheck(fct003InputDto);
        if (fct003OutputDto.getMediateProprietyList() == null) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTED_ACCOUNT_COURCE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_COURCE_UNAVAILABLE, new String[] {
                            codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_IPO_PO) }));
        }
        // 取引媒介可否リスト.媒介可否が"1"(媒介可)以外の場合、取引不可エラーを返す
        for (MediatePropriety mediatePropriety : fct003OutputDto.getMediateProprietyList()) {
            if (!INTERMEDIARYVALUE_POSSIBLE.equals(mediatePropriety.getMediatePropriety())) {
                return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                        ERRORS_CMN_SELECTED_ACCOUNT_COURCE_UNAVAILABLE,
                        IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_COURCE_UNAVAILABLE, new String[] {
                                codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_IPO_PO) }));
            }
        }
        
        return null;
    }
    
    /**
     * A002 J-NISA口座のチェック<br/>
     * ジュニアNISAフラグが"1"かつ、預り区分が"4"または"H"（NISA）の場合、エラーメッセージを返す
     *
     * @param juniorNisaFlag ジュニアNISAフラグ
     * @param depositType 預り区分
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     */
    private DataList<IfaSubscriptInputA002ResponseDto> checkJrNisa(String juniorNisaFlag, String depositType) {
        
        if (JUNIOR_NISA_FLAG_JUNIOR_NISA_ACCOUNT.equals(juniorNisaFlag) && (DEPOSIT_TYPE_NISA_OLD.equals(depositType)
                || DEPOSIT_TYPE_NISA_GROWTH_INVESTMENT.equals(depositType))) {
            DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ERRORS_ORDER_JR_NISA, IfaCommonUtil.getMessage(ERRORS_ORDER_JR_NISA));
            return dtoRes;
        }
        
        return null;
    }
    
    /**
     * A002 つみたてNISA口座のチェック<br/>
     * つみたてNISAフラグが"1"かつ、預り区分が"4"または"H"（NISA）の場合、エラーメッセージを返す
     *
     * @param accumulateNisaFlag つみたてNISAフラグ
     * @param depositType 預り区分
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     */
    private DataList<IfaSubscriptInputA002ResponseDto> checkAccumulateNisa(String accumulateNisaFlag,
            String depositType) {
        
        if (ACCUMULATE_NISA_FLAG_ACCUMULATE_NISA_ACCOUNT.equals(accumulateNisaFlag)
                && (DEPOSIT_TYPE_NISA_OLD.equals(depositType)
                        || DEPOSIT_TYPE_NISA_GROWTH_INVESTMENT.equals(depositType))) {
            DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ERRORS_ORDER_TSUMITATE_NISA,
                    IfaCommonUtil.getMessage(ERRORS_ORDER_TSUMITATE_NISA));
            
            return dtoRes;
        }
        
        return null;
    }
    
    /**
     * A002 預り区分のアラートメッセージの表示判定<br/>
     * 預り区分アラートにセットすべき文字列を取得する。
     *
     * @param isaBuyAbleJudgeClassificationYear ISA買付可能判定区分（当年）
     * @param isaContractType ISA契約区分
     * @param nisaBuyPotentialAmount NISA買付可能額
     * @param juniorNisaFlag ジュニアNISAフラグ
     * @param accumulateNisaFlag つみたてNISAフラグ
     * @param depositType 預り区分
     * @param tokuteiKouzaKbn 特定口座区分
     * @return 預り区分アラート(アラートなしの場合、null)
     */
    private List<String> checkDepositTypeAlert(String isaBuyAbleJudgeClassificationYear, String isaContractType,
            String nisaBuyPotentialAmount, String juniorNisaFlag, String accumulateNisaFlag, String depositType,
            String tokuteiKouzaKbn) {

        // NISA買付可能額をint型に変換
        Long nisaBuyPotentialAmountInt = 0L;
        if (NumberUtils.isCreatable(nisaBuyPotentialAmount)) {
            nisaBuyPotentialAmountInt = Long.parseLong(nisaBuyPotentialAmount);
        }
        
        // NISA買付可能か否かの判定
        boolean nisaBuyable = ((ISA_BUY_ABLE_JUDGE_CLASSIFICATION_YEAR_POSSIBLE_NISA
                .equals(isaBuyAbleJudgeClassificationYear)
                || ISA_BUY_ABLE_JUDGE_CLASSIFICATION_YEAR_POSSIBLE_TSUMITATE_NISA
                        .equals(isaBuyAbleJudgeClassificationYear))
                && ISA_CONTRACT_TYPE_CONTRACT.equals(isaContractType) && nisaBuyPotentialAmountInt > 0
                && JUNIOR_NISA_FLAG_NOT_JUNIOR_NISA_ACCOUNT.equals(juniorNisaFlag)
                && ACCUMULATE_NISA_FLAG_NOT_ACCUMULATE_NISA_ACCOUNT.equals(accumulateNisaFlag));
        
        // アラートの判定
        ArrayList<String> depositAlert = new ArrayList<>();
        
        // NISA買付可能
        if (nisaBuyable) {
            // 預り区分 = 一般預り
            if (DEPOSIT_TYPE_GENERAL.equals(depositType)) {
                depositAlert.add(IfaCommonUtil.getMessage(INFO_GENERAL_NOT_NISA));
            }
            
            // 預り区分 = 特定預り
            if (DEPOSIT_TYPE_SPECIFIC.equals(depositType)) {
                depositAlert.add(IfaCommonUtil.getMessage(INFO_SPECIFIC_NOT_NISA));
            }
        }
        
        // 特定口座
        if (SPECIFIC_KBN_SPECIFIC_DEDUCTION_AT_SOURCE.equals(tokuteiKouzaKbn)
                || SPECIFIC_KBN_SPECIFIC_TAX_RETURN.equals(tokuteiKouzaKbn)) {
            // NISA預り
            if (depositType.equals(DEPOSIT_TYPE_NISA_OLD) || depositType.equals(DEPOSIT_TYPE_NISA_GROWTH_INVESTMENT)) {
                depositAlert.add(IfaCommonUtil.getMessage(INFO_NISA_NOT_SPECIFIC));
            }
            
            // 一般預り
            if (depositType.equals(DEPOSIT_TYPE_GENERAL)) {
                depositAlert.add(IfaCommonUtil.getMessage(INFO_GENERAL_NOT_SPECIFIC));
            }
        }
        
        if (depositAlert.size() == 0) {
            return null;
        }
        
        return depositAlert;
    }
    
    /**
     * A002 上場日のチェック<br/>
     * 現在日付が上場日以後の場合、エラーメッセージを返す
     *
     * @param presentationDate 上場日
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     * @throws Exception 
     */
    private DataList<IfaSubscriptInputA002ResponseDto> checkPresentationDate(String presentationDate)
            throws Exception {
        
        // 現在日付を取得
        String systemDate = ifaDateUtil.format(IfaDateUtil.YYYYMMDD);
        
        // 現在日時が上場日以後の場合、エラーメッセージを返す
        if (0 <= systemDate.compareTo(presentationDate)) {
            DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ERRORS_PERIOD_CHECK, IfaCommonUtil.getMessage(ERRORS_PERIOD_CHECK));
            
            return dtoRes;
        }
        
        return null;
    }
    
    /**
     * A002, A003 募集終了時間のチェック
     * 募集最終日かつ、募集入力終了時間 <= 現在日付の場合エラーを返す
     *
     * @param bbPeriodTo 募集期間（To）
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     * @exception SQLException SQL005の処理に失敗した場合に発生
     */
    private <T> DataList<T> checkOrderLastTime(String bbPeriodTo) throws Exception {
        
        // 募集入力終了時刻の取得(SQL005を実行)
        DataList<IfaSubscriptInputSql005ResponseModel> selSql005Res = dao.selectIfaSubscriptInputSql005();
        String subscriptInputEndTime = selSql005Res.getDataList().get(0).getName();
        
        // 現在日付の取得
        String systemDate = ifaDateUtil.format(IfaDateUtil.SEPARATED_YYYYMMDD);
        String systemTime = ifaDateUtil.format(IfaDateUtil.SEPARATED_YYYYMMDD_HHMMSS).substring(11, 16);
        
        // 募集最終日かつ、募集入力最終時刻を超過している場合エラー
        if (bbPeriodTo.compareTo(systemDate) == 0
                && subscriptInputEndTime.compareTo(systemTime) <= 0) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_ORDER_LAST_TIME,
                    IfaCommonUtil.getMessage(ERRORS_ORDER_LAST_TIME, new String[] { subscriptInputEndTime }));
            
            return dtoRes;
        }
        
        return null;
    }
    
    /**
     * A002 抽選結果に対する当選株数の紐付けチェック<br/>
     * 抽選結果、当選株数、売買単位が適切な関係にない場合エラーを返す
     *
     * @param lotteryResult 抽選結果
     * @param bbQuantityAlloc 当選株数
     * @param unit 売買単位
     * @param sellBuyUnitType 売買単位区分(エラーメッセージに使用する)
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     */
    private DataList<IfaSubscriptInputA002ResponseDto> checkLotteryResultAndInput(String lotteryResult,
            String bbQuantityAlloc, String unit, String sellBuyUnitType) {
        
        // 抽選結果が当選、当選(繰上)、裁量のいずれかの場合
        if (lotteryResult.equals(LOTTERY_RESULT_WIN) || lotteryResult.equals(LOTTERY_RESULT_WIN_KURIAGE)
                || lotteryResult.equals(LOTTERY_RESULT_DISCRETION)) {
            // 当選株数が未入力の場合エラーを返す
            if (StringUtil.isNullOrEmpty(bbQuantityAlloc)) {
                DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ERRORS_BB_QUANTITY_ALLOC_CHECK_INPUT,
                        IfaCommonUtil.getMessage(ERRORS_BB_QUANTITY_ALLOC_CHECK_INPUT));
                
                return dtoRes;
            }
            
            // 当選株数が0の場合エラーを返す
            if (BB_QUANTITY_ALLOC_ZERO.equals(bbQuantityAlloc)) {
                DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ERRORS_NUMBER_INSERT_CHECK,
                        IfaCommonUtil.getMessage(ERRORS_NUMBER_INSERT_CHECK, new String[] { MSG_BB_QUANTITY_ALLOC }));
                
                return dtoRes;
            }
            
            // 当選株数が売買単位外の場合エラーを返す
            if (new BigDecimal(bbQuantityAlloc).remainder(new BigDecimal(unit)) != BigDecimal.ZERO) {
                DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ERRORS_UNIT_CHECK, IfaCommonUtil.getMessage(ERRORS_UNIT_CHECK,
                                new String[] { MSG_BB_QUANTITY_ALLOC, unit, sellBuyUnitType }));
                
                return dtoRes;
            }
            
        } else if (lotteryResult.equals(LOTTERY_RESULT_APPLY_CANCEL)) {
            // 抽選結果が申込取消かつ、当選結果が未入力の場合エラーメッセージを返す
            if (StringUtil.isNullOrEmpty(bbQuantityAlloc)) {
                DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ERRORS_REQUIRED,
                        IfaCommonUtil.getMessage(ERRORS_REQUIRED, new String[] { MSG_BB_QUANTITY_ALLOC }));
                
                return dtoRes;
            }
            
        } else {
            // 抽選結果が、当選、当選(繰上)、裁量、申込取消のいずれでもない
            // かつ、当選株数が0より大きい場合、エラーメッセージを返す
            if (StringUtil.parseBigDecimal(bbQuantityAlloc).compareTo(StringUtil.parseBigDecimal("0")) > 0) {
                DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ERRORS_BB_QUANTITY_ALLOC_CHECK,
                        IfaCommonUtil.getMessage(ERRORS_BB_QUANTITY_ALLOC_CHECK));
                
                return dtoRes;
            }
        }
        
        return null;
    }
    
    /**
     * A002 数量のチェック<br/>
     * 数量が売買単位外の場合エラーを返す<br/>
     * 数量が当選株数を超える場合エラーを返す<br/>
     * 数量が配分上限株数を超える場合エラーを返す<br/>
     *
     * @param quantity 数量
     * @param bbQuantityAlloc 当選株数
     * @param maxAllocation 配分上限株数
     * @param unit 売買単位
     * @param sellBuyUnitType 売買単位区分(エラーメッセージに使用する)
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     */
    private DataList<IfaSubscriptInputA002ResponseDto> checkQuantity(String quantity, String bbQuantityAlloc,
            String maxAllocation, String unit, String sellBuyUnitType) {
        
        BigDecimal quantityNum = new BigDecimal(quantity);
        
        // 数量が売買単位外の場合エラー
        if (quantityNum.remainder(new BigDecimal(unit)) != BigDecimal.ZERO) {
            DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ERRORS_UNIT_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_UNIT_CHECK, new String[] { MSG_UNIT, unit, sellBuyUnitType }));
            
            return dtoRes;
        }
        
        // 数量が当選株数を超える場合エラー
        if (new BigDecimal(bbQuantityAlloc).compareTo(quantityNum) < 0) {
            DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ERRORS_COUNT_UNIT_CHECK, IfaCommonUtil.getMessage(ERRORS_COUNT_UNIT_CHECK));
            
            return dtoRes;
        }
        
        // 数量が配分上限株数を超える場合エラー
        if (!ObjectUtils.isEmpty(maxAllocation) && new BigDecimal(maxAllocation).compareTo(quantityNum) < 0) {
            DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ERRORS_IPO_TRADE_RANGE, IfaCommonUtil.getMessage(ERRORS_IPO_TRADE_RANGE,
                            new String[] { MSG_UNIT, MSG_MAX_ALLOCATION, MSG_MAX_ALLOCATION, maxAllocation }));
            
            return dtoRes;
        }
        
        return null;
    }
    
    /**
     * A002 口座の取引制限チェック
     * エラー、またはアラートがある場合、DataListを返却する。
     * エラー → ErrorLevel: FATAL
     * アラート → ErrorLevel: SUCCESS
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     * @exception Exception FCT021の実行で発生するException
     */
    private DataList<IfaSubscriptInputA002ResponseDto> checkAccountTradeRestriction(String butenCode,
            String accountNumber) throws Exception {
        
        // FCT021のリクエストDTOを作成
        InputFct021Dto fct021InputDto = new InputFct021Dto();
        fct021InputDto.setButenCode(butenCode);
        fct021InputDto.setAccountNumber(accountNumber);
        fct021InputDto.setProductCd(Fct021.DOMESTIC_STOCK);
        fct021InputDto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_SUBSCRIPT);
        fct021InputDto.setCountryCd(COUNTRY_CODE_BB_APPLY_LIST);
        fct021InputDto.setCurrencyCode(CURRENCY_CODE_BB_APPLY_LIST);
        
        // FCT021の実行
        OutputFct021Dto fct021OutputDto = fct021.doCheck(fct021InputDto);
        
        // 注意情報エラー有無 = ありの場合エラーを返す
        if (NOTICE_ERROR_EXISTS.equals(fct021OutputDto.getNoteInfoErrFlag())) {
            DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ERRORS_CMN_NOTICE_ERROR_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_CMN_NOTICE_ERROR_CHECK, new String[] {
                            codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_IPO_PO) }));
            
            return dtoRes;
        }
        
        // お知らせエラー有無 = ありの場合エラーを返す
        if (INFORMATION_ERROR_EXISTS.equals(fct021OutputDto.getNoteLimitErrFlag())) {
            DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ERRORS_INFORMATION_CHECK, IfaCommonUtil.getMessage(ERRORS_INFORMATION_CHECK));
            
            return dtoRes;
        }
        
        IfaSubscriptInputA002ResponseDto innerData = new IfaSubscriptInputA002ResponseDto();
        boolean hasWarning = false;
        
        // 注意情報アラート有無 = ありの場合、注意情報アラートをセットする
        if (NOTICE_WARNING_EXISTS.equals(fct021OutputDto.getNoteInfoAlertFlag())) {
            hasWarning = true;
            
            innerData.setNoticeInfoAlert(IfaCommonUtil.getMessage(WARNINGS_CMN_NOTICE_WARNING_CHECK, new String[] {
                    codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_IPO_PO) }));
        }
        
        // お知らせアラート有無 = ありの場合、お知らせアラートをセットする
        if (INFORMATION_WARNING_EXISTS.equals(fct021OutputDto.getNoteLimitAlertFlag())) {
            hasWarning = true;
            
            innerData.setNoticeAlert(IfaCommonUtil.getMessage(WARNINGS_CMN_INFORMATION_CHECK));
        }
        
        // アラートが存在する場合、DataListを返す
        if (hasWarning) {
            DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(Arrays.asList(innerData),
                    ErrorLevel.SUCCESS, RtnCdEnum.SUCCESS.getText(), "");
            
            return dtoRes;
        }
        
        return null;
    }
    
    /**
     * A002 預り区分のチェック
     *
     * @param depositType 預り区分
     * @param customerCode 顧客コード
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     * @exception Exception SQL006エラーの場合
     */
    private DataList<IfaSubscriptInputA002ResponseDto> checkDepositType(String depositType, String customerCode)
            throws Exception {
        
        // SQL006のリクエストを作成
        IfaSubscriptInputSql006RequestModel selSql006Req = new IfaSubscriptInputSql006RequestModel();
        selSql006Req.setCustomerCode(customerCode);
        
        // SQL006を実行
        DataList<IfaSubscriptInputSql006ResponseModel> selSql006Res = dao.selectIfaSubscriptInputSql006(selSql006Req);
        String tokuteiKouzaKbn = selSql006Res.getDataList().get(0).getTokuteiKouzaKbn();
        String isaContractType = selSql006Res.getDataList().get(0).getIsaContractType();
        String isaBuyable = selSql006Res.getDataList().get(0).getIsaBuyAbleJudgeClassificationYear();
        
        // 特定口座未開設で、預り区分 = 特定の場合エラーを返す
        if (!SpecificAccount.SPECIFIC_PAYMENT_PROXY.getId().equals(tokuteiKouzaKbn)
                && !SpecificAccount.SPECIFIC_FINAL_TAX.getId().equals(tokuteiKouzaKbn)
                && DEPOSIT_TYPE_SPECIFIC.equals(depositType)) {
            DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ERRORS_SPECIFIC_ACCOUNT_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_SPECIFIC_ACCOUNT_CHECK));
            
            return dtoRes;
        }
        
        // NISA未開設で預り区分 = NISAの場合エラーを返す
        if (!ISA_CONTRACT_TYPE_CONTRACT.equals(isaContractType) && (DEPOSIT_TYPE_NISA_OLD.equals(depositType)
                || DEPOSIT_TYPE_NISA_GROWTH_INVESTMENT.equals(depositType))) {
            DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ERRORS_NISA_ACCOUNT_CHECK, IfaCommonUtil.getMessage(ERRORS_NISA_ACCOUNT_CHECK));
            
            return dtoRes;
        }
        
        // NISA口座が買付不可で預り区分 = NISAの場合エラー
        if (!ISA_BUY_ABLE_JUDGE_CLASSIFICATION_YEAR_POSSIBLE_NISA.equals(isaBuyable)
                && !ISA_BUY_ABLE_JUDGE_CLASSIFICATION_YEAR_POSSIBLE_TSUMITATE_NISA.equals(isaBuyable)
                && (DEPOSIT_TYPE_NISA_OLD.equals(depositType)
                        || DEPOSIT_TYPE_NISA_GROWTH_INVESTMENT.equals(depositType))) {
            DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ERRORS_NISA_ACCOUNT_BUY_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_NISA_ACCOUNT_BUY_CHECK));
            
            return dtoRes;
        }
        return null;
    }
    
    /**
     * A002 最重要事項の説明チェック
     *
     * @param customerCode 顧客コード
     * @param importantMatterType 重要事項の説明
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     * @exception Exception SQL007エラーの場合
     */
    private DataList<IfaSubscriptInputA002ResponseDto> checkExplainImportantImformation(String customerCode, String importantMatterType)
            throws Exception {
        
        // SQL007のリクエストを作成
        IfaSubscriptInputSql007RequestModel selSql007Req = new IfaSubscriptInputSql007RequestModel();
        selSql007Req.setCustomerCode(customerCode);
        
        // SQL007を実行
        DataList<IfaSubscriptInputSql007ResponseModel> selSql007Res = dao.selectIfaSubscriptInputSql007(selSql007Req);
        
        // リクエスト．重要事項の説明＝1（説明不要を確認（属性登録済））かつ、リスク説明未1のデータがある場合、エラーを返す
        if (IMPORTANT_MATTERS_EXPLAIN_NO_EXPLAIN_CONFIRM.equals(importantMatterType) && Integer.parseInt(selSql007Res.getDataList().get(0).getRowCount()) > 0) {
            DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ERRORS_EXPLAIN_IMPORTANT_INFORMATION,
                    IfaCommonUtil.getMessage(ERRORS_EXPLAIN_IMPORTANT_INFORMATION));
            
            return dtoRes;
        }
        
        return null;
    }
    
    /**
     * A002 コンプラランクのチェック
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param brandCode 銘柄コード
     * @param kanyuKbn 勧誘区分
     * @param jutyuKbn 受注方法
     * @return エラーなし：null, アラートあり：アラートを格納したDataList, エラーあり: レスポンス用DataList
     * @exception Exception システムエラー
     */
    private DataList<IfaSubscriptInputA002ResponseDto> checkComplianceRank(String butenCode, String accountNumber,
            String brandCode, String kanyuKbn, String jutyuKbn) throws Exception {
        
        // FCT006のリクエストDTOを作成
        InputFct006Dto fct006InputDto = new InputFct006Dto();
        fct006InputDto.setButenCode(butenCode);
        fct006InputDto.setAccountNumber(accountNumber);
        fct006InputDto.setBrDomesticFgnInd(DOMESTIC_FOREIGN_TYPE_DOMESTIC);
        fct006InputDto.setBrBrandInd(SECURITY_TYPE_STOCK);
        fct006InputDto.setBrandCode1(brandCode);
        fct006InputDto.setInvitationType(kanyuKbn);
        fct006InputDto.setOrderMethod(jutyuKbn);
        fct006InputDto.setComplaCheckKind(COMPLA_CHECK_KIND_BUY_ORDER);
        
        // FCT006を実行
        OutputFct006Dto fct006OutputDto = fct006.doCheck(fct006InputDto);
        
        switch (fct006OutputDto.getJudgementResult()) {
            case COMPLA_CHECK_JUDGMENT_RESULT_NORMAL:
                return null;
            
            case COMPLA_CHECK_JUDGMENT_RESULT_ALERT:
                IfaSubscriptInputA002ComplianceRankCheckResponseDto complianceRankCheck = new IfaSubscriptInputA002ComplianceRankCheckResponseDto();
                complianceRankCheck.setMessage(fct006OutputDto.getMessageId());
                complianceRankCheck.setChkBoxLabel(fct006OutputDto.getChkBoxLabel());
                
                IfaSubscriptInputA002ResponseDto innerData = new IfaSubscriptInputA002ResponseDto();
                innerData.setComplianceRankCheck(complianceRankCheck);
                
                DataList<IfaSubscriptInputA002ResponseDto> dtoResAlert = IfaCommonUtil
                        .createDataList(Arrays.asList(innerData), ErrorLevel.SUCCESS, RtnCdEnum.SUCCESS.getText(), "");
                
                return dtoResAlert;
            
            case COMPLA_CHECK_JUDGMENT_RESULT_ERROR: // fall-through
            default:
                DataList<IfaSubscriptInputA002ResponseDto> dtoResError = IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, fct006OutputDto.getMessageId(),
                        IfaCommonUtil.getMessage(fct006OutputDto.getMessageId()));
                
                return dtoResError;
        }
    }
    
    /**
     * A002 約定金額のチェック
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param depositType 預り区分
     * @param contractAmount 約定金額
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     * @exception Exception システムエラー
     */
    private DataList<IfaSubscriptInputA002ResponseDto> checkContractAmount(String butenCode, String accountNumber,
            String depositType, String contractAmount, ApiErrorUtil apiErrorUtil) throws Exception {
        
        // API001のリクエストを作成
        QueryAccountBalanceInData api001InData = new QueryAccountBalanceInData();
        api001InData.setButenCd(butenCode);
        api001InData.setKozaNo(accountNumber);
        
        QueryAccountBalanceIn api001Input = new QueryAccountBalanceIn();
        api001Input.setIndata(api001InData);
        
        // API001を呼び出し
        QueryAccountBalanceOutData api001OutData = new QueryAccountBalanceOutData();
        api001OutData = apiWrapper.queryAccountBalance(api001Input);
        if (apiErrorUtil.isError(api001OutData.getShubetu(), api001OutData.getCode(), api001OutData.getMessage())) {
            DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ERRORS_NRI_QUERY_ACCOUNT_BALANCE,
                    IfaCommonUtil.getMessage(ERRORS_NRI_QUERY_ACCOUNT_BALANCE));
            
            return dtoRes;
        }
        
        // 約定金額 > API001.買付余力の場合エラーを返す
        if (new BigDecimal(contractAmount).compareTo(new BigDecimal(api001OutData.getT0().getBuyingPowerTotal())) > 0) {
            DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ERRORS_BUY_LIMIT_CHECK, IfaCommonUtil.getMessage(ERRORS_BUY_LIMIT_CHECK));
            
            return dtoRes;
        }
        
        // 預り区分 = NISAの場合
        if (DEPOSIT_TYPE_NISA_OLD.equals(depositType) || DEPOSIT_TYPE_NISA_GROWTH_INVESTMENT.equals(depositType)) {
            // NISA買付可能額の取得
            BigDecimal nisaBuyLimit = BigDecimal.ZERO;
            
            if (!ObjectUtils.isEmpty(api001OutData.getIsaBuyLimitJrnisa())) {
                // ジュニアNISA口座の場合、NISA買付可能額 = ISA買付可能枠(当年)(JrNISA)
                nisaBuyLimit = new BigDecimal(api001OutData.getIsaBuyLimitJrnisa());
            } else {
                // 非ジュニアNISA口座の場合
                if (ObjectUtils.isEmpty(api001OutData.getIsaBuyLimit())) {
                    // NISA口座(成長投資枠)
                    nisaBuyLimit = new BigDecimal(api001OutData.getIsaSeityoBuyLimit());
                    
                } else {
                    // 旧NISA口座
                    nisaBuyLimit = new BigDecimal(api001OutData.getIsaBuyLimit());
                }
                
            }
            
            // 約定金額 > NISA買付可能額の場合エラーを返す
            if (new BigDecimal(contractAmount).compareTo(nisaBuyLimit) > 0) {
                DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ERRORS_EXCEEDED_MAXIMUM,
                        IfaCommonUtil.getMessage(ERRORS_EXCEEDED_MAXIMUM, new String[] { MSG_NISA_BUY_LIMIT }));
                
                return dtoRes;
            }
        }
        
        return null;
    }
    
    /**
     * A002 備考のチェック
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param bbRemark 備考
     * @param bbRemark2 訂正前_備考
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     * @exception Exception システムエラー
     */
    private DataList<IfaSubscriptInputA002ResponseDto> checkBbRemark(String butenCode, String accountNumber,
            String bbRemark, String bbRemark2) throws Exception {
        
        // 備考を変更する場合
        if (!StringUtil.isNullOrEmpty(bbRemark) && !bbRemark.equals(bbRemark2)) {
            // SQL008のリクエストを作成
            IfaSubscriptInputSql008RequestModel selSql008Req = new IfaSubscriptInputSql008RequestModel();
            selSql008Req.setButenCode(butenCode);
            selSql008Req.setAccountNumber(accountNumber);
            
            // SQL008を実行
            DataList<IfaSubscriptInputSql008ResponseModel> selSql008Res = dao
                    .selectIfaSubscriptInputSql008(selSql008Req);
            
            // セクション関連情報が存在しない場合エラーを返す
            if (selSql008Res.getDataList().isEmpty() || selSql008Res.getDataList().get(0) == null){
                DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ERRORS_SECTION_ID,
                        IfaCommonUtil.getMessage(ERRORS_SECTION_ID, new String[] { MSG_SECTION_ID }));
                
                return dtoRes;
            }
        }
        return null;
    }
    
    /**
     * A002 目論見書閲覧日時のチェック
     *
     * @param readTime 目論見書閲覧日時
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     * @exception Exception システムエラー
     */
    private DataList<IfaSubscriptInputA002ResponseDto> checkReadTime(String readTime) throws Exception {
        
        //現在日時の取得
        String systemDateTime = ifaDateUtil.format(IfaDateUtil.SEPARATED_YYYYMMDD_HHMMSS).substring(0, 16);
        
        // システム日時(確認ボタンを押した日時) < 目論見書閲覧日時の場合エラーを返す
        if (systemDateTime.compareTo(readTime) < 0) {
            DataList<IfaSubscriptInputA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ERRORS_FUTURE_TIME_CHECK, IfaCommonUtil.getMessage(ERRORS_FUTURE_TIME_CHECK));
            
            return dtoRes;
        }
        
        return null;
    }
    
    /**
     * 送信・訂正用ロジック処理判定処理フラグを算出する
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param brandCode 銘柄コード
     * @param bookBuildingPresentationFrom ブックビルディング申込期間（開始）
     * @param orderStatus 注文状況
     * @return 送信・訂正用ロジック処理判定フラグ
     * @throws Exception 例外
     */
    protected String getSendCorrectLogicJudgeFlag(String butenCode, String accountNumber, String brandCode,
            String bookBuildingPresentationFrom, String orderStatus) throws Exception {
        
        String judgeFlag = SEND_CORRECT_LOGIC_JUDGE_FLAG_OTHER;
        
        // 対面募集注文情報を取得する
        IfaSubscriptInputSql004RequestModel selSql004Req = new IfaSubscriptInputSql004RequestModel();
        selSql004Req.setButenCode(butenCode);
        selSql004Req.setAccountNumber(accountNumber);
        selSql004Req.setBrandCode(brandCode);
        selSql004Req.setBookBuildingPresentationFrom(bookBuildingPresentationFrom);
        DataList<IfaSubscriptInputSql004ResponseModel> selSql004Res = dao.selectIfaSubscriptInputSql004(selSql004Req);
        List<IfaSubscriptInputSql004ResponseModel> selSql004ResDataList = selSql004Res.getDataList();
        
        // ユーザ共通情報から権限コードを取得する
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        
        // 判定条件② 対面募集注文明細情報が存在するか
        boolean isOrderExist = selSql004ResDataList != null && !selSql004ResDataList.isEmpty()
                && selSql004ResDataList.size() != 0;
        
        // 判定条件③ 対面募集注文明細情報.数量のフィールド値が存在するか
        boolean isOrderCountPresent = isOrderExist && selSql004ResDataList.get(0) != null
                && !StringUtil.isNullOrEmpty(selSql004ResDataList.get(0).getQuantity());
        
        // 判定条件① (ログインユーザ) 
        if (PrivId.HEAD_OFFICE.getId().equals(privId)) { // 本店権限（ユーザ共通情報.権限コード＝1）
            // 判定条件② (レコード 有無) 
            if (!isOrderExist) { // 対面募集注文明細情報　無し
                judgeFlag = SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIINSERT;
            } else { // 対面募集注文明細情報　有り
                // 判定条件③ (フィールド値　有無） 
                if (!isOrderCountPresent) { // 対面募集注文明細情報.数量　無し
                    judgeFlag = SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIUPDATEONE;
                } else { // 対面募集注文明細情報.数量　有り
                    judgeFlag = SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIUPDATETWO;
                }
            }
        } else { // 仲介業者権限（ユーザ共通情報.権限コード＝1以外）
            // 判定条件② (レコード 有無) 
            if (!isOrderExist) { // 対面募集注文明細情報　無し
                judgeFlag = SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERINSERT;
            } else { // 対面募集注文明細情報　有り
                // 判定条件③ (フィールド値　有無）
                if (!isOrderCountPresent) { // 対面募集注文明細情報.数量　無し
                    judgeFlag = SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERUPDATE;
                } else { // 対面募集注文明細情報.数量　有り
                    if (OrderStatus.OFFERING_INPUT.getId().equals(orderStatus)) {
                        // 判定条件④ (SQL001.注文状況） 1:募集入力済み
                        judgeFlag = SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERMODIFY;
                    } else {
                        // 判定条件④ (SQL001.注文状況） 1:募集入力済み 以外
                        judgeFlag = SEND_CORRECT_LOGIC_JUDGE_FLAG_OTHER;
                    }
                }
            }
            
            if (OrderStatus.MANUAL_INPUT.getId().equals(orderStatus)) {
                // SQL001.注文状況が5:手入力の場合
                judgeFlag = SEND_CORRECT_LOGIC_JUDGE_FLAG_OTHER;
            }
        }

        // SQL001.注文状況が'6'(募集取消)の場合、OTHERを設定する
            if (orderStatus.equals("6")) {
                judgeFlag = SEND_CORRECT_LOGIC_JUDGE_FLAG_OTHER;
            }

        return judgeFlag;
    }
}
