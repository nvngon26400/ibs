package com.sbisec.helios.ap.brokerageMenu.ipoPo.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.ibm.icu.math.BigDecimal;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto.MediatePropriety;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IfaSubscriptInputConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql002_1RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql002_2RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql003_1RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql003_2RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql006_1RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql006_2RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql010_1RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql010_2RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql012_1RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql012_2RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql013RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA006ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA007ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.service.IfaSubscriptInputConfirmService;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.util.IfaSubscriptInputException;
import com.sbisec.helios.ap.common.dao.SystemDateDao;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.SpecificAccount;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.ServiceStatusService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceOutData;

/**
 * 画面ID：SUB0204_02-04_2
 * 画面名：募集入力確認
 * 2024/04/15 新規作成
 *
 * @author SCSK濱田
 */
@Component(value = "cmpIfaSubscriptInputConfirmService")
public class IfaSubscriptInputConfirmServiceImpL implements IfaSubscriptInputConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSubscriptInputConfirmServiceImpL.class);
    
    @Autowired
    private IfaSubscriptInputConfirmDao dao;
    
    @Autowired
    private ApiWrapper apiWrapper;
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct006 fct006;
    
    @Autowired
    private Fct021 fct021;
    
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private SystemDateDao systemDateDao;
    
    @Autowired
    private ServiceStatusService serviceStatusService;

    @Autowired
    private IfaSubscriptInputServiceImpL subscriptInputServiceImpL;
    
    @Autowired
    private IfaSubscriptInputConfirmEtintraServiceImpL etintraServiceImpL;
    
    
    // ****************************************
    // エラーメッセージID
    // ****************************************
    
    /** エラーメッセージID：権限なしエラー */
    private static final String ERRORS_BUTEN_ACCOUNT_NOT_EXIST = "errors.butenAccountNotExist";
    
    /** エラーメッセージID：取引停止口座エラー */
    private static final String ERRORS_CMN_SELECTED_ACCOUNT_OUT_OF_SERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** エラーメッセージID：取引不可エラー */
    private static final String ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** ワーニングメッセージID：サービス閉塞ワーニング */
    private static final String WARNING_W0006 = "W0006";
    
    /** エラーメッセージID：上場日のチェックエラー */
    private static final String ERRORS_PERIOD_CHECK = "errors.periodCheck";
    
    /** エラーメッセージID：募集終了時間チェックエラー */
    private static final String ERRORS_ORDER_LAST_TIME = "errors.orderLastTime";
    
    /** エラーメッセージID：数量チェックエラー（数量が売買単位外） */
    private static final String ERRORS_UNIT_CHECK = "errors.unitCheck";
    
    /** エラーメッセージID：数量チェックエラー（数量が当選株数を超える） */
    private static final String ERRORS_COUNT_UNIT_CHECK = "errors.countUnitCheck";
    
    /** エラーメッセージID：数量チェックエラー（数量が配分上限株数を超える） */
    private static final String ERRORS_IPO_TRADE_RANGE = "errors.ipoTradeRange";
    
    /** エラーメッセージID：注意情報エラー */
    private static final String ERRORS_CMN_NOTICE_ERROR_CHECK = "errors.cmn.noticeErrorCheck";
    
    /** ワーニングメッセージID：お知らせエラー */
    private static final String WARNING_INFORMATION_CHECK = "warnings.informationCheck";
    
    /** エラーメッセージID：注意情報アラート.チェックボックスOFFエラー */
    private static final String ERRORS_INFORMATION_CHECK = "errors.informationCheck";
    
    /** エラーメッセージID：お知らせアラート.チェックボックスOFFエラー */
    private static final String ERRORS_CMN_INFORMATION_OCCURS = "errors.cmn.information.occurs";
    
    /** エラーメッセージID：特定口座未開設エラー */
    private static final String ERRORS_SPECIFIC_ACCOUNT_CHECK = "errors.specificAccountCheck";
    
    /** エラーメッセージID：NISA口座未開設エラー */
    private static final String ERRORS_NISA_ACCOUNT_CHECK = "errors.nisaAccountCheck";
    
    /** エラーメッセージID：NISA口座買付不可エラー */
    private static final String ERRORS_NISA_ACCOUNT_BUY_CHECK = "errors.nisaAccountBuyCheck";
    
    /** エラーメッセージID：重要事項未説明エラー */
    private static final String ERRORS_EXPLAIN_IMPORTANT_INFORMATION = "errors.explainImportantInformation";
    
    /** エラーメッセージID：買付余力情報の取得エラー */
    private static final String ERRORS_NRI_QUERY_ACCOUNT_BALANCE = "errors.nriQueryAccountBalance";
    
    /** エラーメッセージID：買付余力不足エラー */
    private static final String ERRORS_BUY_LIMIT_CHECK = "errors.buyLimitCheck";
    
    /**　エラーメッセージID：NISA買付可能額超過エラー */
    private static final String ERRORS_EXCEEDED_MAXIMUM = "errors.exceededMaximum";
    
    /** エラーメッセージID：セクション関連情報なしエラー */
    private static final String ERRORS_SECTION_ID = "errors.sectionId";
    
    /** エラーメッセージID：目論見書閲覧日時未来エラー */
    private static final String ERRORS_FUTURE_TIME_CHECK = "errors.futureTimeCheck";
    
    /** エラーメッセージID：注文登録済みエラー */
    private static final String ERRORS_INSERT_DATE_EXIST = "errors.insertDataExist";
    
    /** エラーメッセージID：当選株数が未入力エラー */
    private static final String ERRORS_BB_QUANTITY_ALLOC_CHECK_INPUT = "errors.bbQuantityAllocCheckInput";
    
    /** エラーメッセージID：登録不可エラー */
    private static final String ERRORS_NUMBER_INSERT_CHECK = "errors.numberInsertCheck";
    
    /** エラーメッセージID：必須項目エラー */
    private static final String ERRORS_REQUIRED = "errors.required";
    
    /** エラーメッセージID：当選株数入力エラー */
    private static final String ERRORS_BB_QUANTITY_ALLOC_CHECK = "errors.bbQuantityAllocCheck";
    
    /** エラーメッセージID：更新エラー */
    private static final String ERRORS_EXCLUSIVE = "errors.exclusive";
    
    // ****************************************
    // エラーメッセージパラメータ文言
    // ****************************************
    
    /** エラーメッセージ：数量 */
    private static final String MSG_UNIT = "数量";
    
    /** エラーメッセージ：配分上限株数 */
    private static final String MSG_MAX_ALLOCATION = "配分上限株数";
    
    /** エラーメッセージ：NISA買付可能額 */
    private static final String MSG_NISA_BUY_LIMIT = "NISA買付可能額";
    
    /** エラーメッセージ：セクションID */
    private static final String MSG_SECTION_ID = "セクションID";
    
    /** エラーメッセージ：注文 */
    private static final String MSG_ORDER = "注文";
    
    /** エラーメッセージ：当選株数 */
    private static final String MSG_BB_QUANTITY_ALLOC = "当選株数";
    
    // ****************************************
    // 区分値・コード
    // ****************************************
    
    /** FCT001 対象顧客参照権限有無＝"0"（権限なし） */
    private static final String FCT001_NO_AUTHORIZED = "0";
    
    /** FCT001 取引停止フラグ＝"1"（取引停止口座） */
    private static final String FCT001_TRADING_SUSPENSION = "1";
    
    /** 証券金銭種別："01"(国内株式) */
    private static final String SECURITY_MONEY_CLASS_01 = "01";
    
    /** 取引種別(国内株式)："A"(募集) */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_A = "A";
    
    /** 国籍コード */
    private static final String COUNTRY_CODE_BB_APPLY_LIST = "99";
    
    /** 通貨コード */
    private static final String CURRENCY_CODE_BB_APPLY_LIST = "999";
    
    /** 預り区分.特定預り */
    private static final String DEPOSIT_TYPE_SPECIFIC = "2";
    
    /** 預り区分.旧NISA */
    private static final String DEPOSIT_TYPE_NISA_OLD = "4";
    
    /** 預り区分.NISA（成長投資枠） */
    private static final String DEPOSIT_TYPE_NISA_GROWTH_INVESTMENT = "H";
    
    /** ISA買付可能判定区分(当年).可能(NISA)※ジュニアNISAも含む */
    private static final String ISA_BUY_ABLE_JUDGE_CLASSIFICATION_YEAR_POSSIBLE_NISA = "1";
    
    /** ISA買付可能判定区分(当年).可能(つみたてNISA) */
    private static final String ISA_BUY_ABLE_JUDGE_CLASSIFICATION_YEAR_POSSIBLE_TSUMITATE_NISA = "2";
    
    /** ISA契約区分.契約 */
    private static final String ISA_CONTRACT_TYPE_CONTRACT = "1";
    
    /** FCT003 媒介可否リスト.媒介可否="1"（媒介可） */
    private static final String MEDIATE_PROPRIETY = "1";
    
    /** 区分.対象取引（メッセージ表示用） 変換用：ドメインID */
    private static final String CODE_LIST_MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 区分.対象取引（メッセージ表示用） 変換用：表示区分 */
    private static final String CODE_LIST_KEY = "8";
    
    /** 区分.対象取引（メッセージ表示用） 変換用：表示パターン */
    private static final String CODE_LIST_DISP_PATTERN = "1";
    
    /** 区分.重要事項の説明.説明不要を確認（属性登録済） */
    private static final String IMPORTANT_MATTERS_EXPLAIN_NO_EXPLAIN_CONFIRM = "1";
    
    /** 閉塞チェックのパラメータ  */
    private static final String IPOPO_NRI_API = "NRI_API";
    
    /** 注意情報エラー有無.あり */
    private static final String NOTICE_ERROR_EXISTS = "1";
    
    /** 注意情報アラート有無.あり */
    private static final String NOTICE_WARNING_EXISTS = "1";
    
    /** お知らせエラー有無.あり */
    private static final String INFORMATION_ERROR_EXISTS = "1";
    
    /** お知らせアラート有無.あり */
    private static final String INFORMATION_WARNING_EXISTS = "1";
    
    /** 確認チェックボックスフラグ "0"(OFF) */
    private static final String CHECK_BOX_FLG_OFF = "0";
    
    /** コンプラチェック判定結果.ノーマル */
    private static final String COMPLA_CHECK_JUDGMENT_RESULT_NORMAL = "0";
    
    /** コンプラチェック判定結果.アラート */
    private static final String COMPLA_CHECK_JUDGMENT_RESULT_ALERT = "1";
    
    /** コンプラチェック判定結果.エラー */
    private static final String COMPLA_CHECK_JUDGMENT_RESULT_ERROR = "2";
    
    /** 国内外国区分.国内 */
    private static final String DOMESTIC_FOREIGN_TYPE_DOMESTIC = "0";
    
    /** 商品区分.株式 */
    private static final String SECURITY_TYPE_STOCK = "1 ";
    
    /** コンプラチェック種類.買付注文 */
    private static final String COMPLA_CHECK_KIND_BUY_ORDER = "1";
    
    /** 抽選結果　当選："1" */
    private static final String LOTTERY_RESULT_WINNING = "1";
    
    /** 抽選結果　当選(繰上)："3" */
    private static final String LOTTERY_RESULT_ALTERNATE = "3";
    
    /** 抽選結果　裁量："4" */
    private static final String LOTTERY_RESULT_DISCRETIONARY = "4";
    
    /** 抽選結果　申込取消："5" */
    private static final String LOTTERY_RESULT_CANCEL = "5";
    
    /** 余力拘束処理タイプ："0" 余力拘束解除 */
    private static final String ACTION_TYPE_ADDITIONAL_PLACE_ORDER_CANCEL = "0";
    
    // ****************************************
    // 送信・訂正用ロジック処理判定フラグ
    // ****************************************
    
    /** 送信・訂正用ロジック処理判定フラグ.管理者新規注文 */
    protected static final String SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIINSERT = "SBIINSERT";
    
    /** 送信・訂正用ロジック処理判定フラグ.管理者更新注文 */
    protected static final String SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIUPDATEONE = "SBIUPDATEONE";
    
    /** 送信・訂正用ロジック処理判定フラグ.管理者訂正注文 */
    protected static final String SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIUPDATETWO = "SBIUPDATETWO";
    
    /** 送信・訂正用ロジック処理判定フラグ.仲介業者新規注文 */
    protected static final String SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERINSERT = "BROKERINSERT";
    
    /** 送信・訂正用ロジック処理判定フラグ.仲介業者更新注文 */
    protected static final String SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERUPDATE = "BROKERUPDATE";
    
    /** 送信・訂正用ロジック処理判定フラグ.仲介業者訂正注文(募集入力済み) */
    protected static final String SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERMODIFY = "BROKERMODIFY";
    
    /** 送信・訂正用ロジック処理判定フラグ.その他 */
    protected static final String SEND_CORRECT_LOGIC_JUDGE_FLAG_OTHER = "OTHER";
    
    

    /**
     * アクションID：A001
     * アクション名：注文（仲介業者新規）
     * Dto リクエスト：IfaSubscriptInputConfirmA001DtoRequest
     * Dto レスポンス：IfaSubscriptInputConfirmA001DtoResponse
     *
     * @param dtoReq リクエストボディ
     * @return 注文に必要な情報
     * @exception Exception システムエラー
     */
    
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DataList<IfaSubscriptInputConfirmA001ResponseDto> orderPlacementBrokerA001(
            IfaSubscriptInputConfirmA001RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSubscriptPeriodMasterRegisterInputServiceImplL.orderPlacementBrokerA001");
        }
        // 利用者の口座に対する権限チェックを行う。
        DataList<IfaSubscriptInputConfirmA001ResponseDto> checkFct001Ret = checkFct001(dtoReq.getButenCode(),
                dtoReq.getAccountNumber());
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        if (checkFct001Ret != null) {
            return checkFct001Ret;
        }
        
        // 取引コース媒介可否チェックを行う。
        DataList<IfaSubscriptInputConfirmA001ResponseDto> checkMediationAllowedRet = checkMediationAllowed(
                dtoReq.getButenCode(), dtoReq.getAccountNumber());
        
        if (checkMediationAllowedRet != null) {
            return checkMediationAllowedRet;
        }
        
        // サービス閉塞チェックで、サービス閉塞状態を判定する。
        DataList<IfaSubscriptInputConfirmA001ResponseDto> checkServiceStatusRet = checkServiceStatus();
        if (checkServiceStatusRet != null) {
            return checkServiceStatusRet;
        }
        
        // 上場日のチェック
        DataList<IfaSubscriptInputConfirmA001ResponseDto> checkPresentationDateRet = checkPresentationDate(
                dtoReq.getPresentationDate());
        if (checkPresentationDateRet != null) {
            return checkPresentationDateRet;
        }
        
        // 募集終了時間チェック
        DataList<IfaSubscriptInputConfirmA001ResponseDto> checkBbPeriodToBeforeRet = checkBbPeriodTo(
                dtoReq.getBbPeriodTo());
        if (checkBbPeriodToBeforeRet != null) {
            return checkBbPeriodToBeforeRet;
        }
        
        // 数量のチェック
        DataList<IfaSubscriptInputConfirmA001ResponseDto> checkQuantityRet = checkQuantity(dtoReq.getQuantity(),
                dtoReq.getBbQuantityAlloc(), dtoReq.getMaxAllocation(), dtoReq.getUnit(), dtoReq.getSellBuyUnitType());
        if (checkQuantityRet != null) {
            return checkQuantityRet;
        }
        
        // 口座の取引制限チェック
        DataList<IfaSubscriptInputConfirmA001ResponseDto> checkAccountTradeRestrictionRet = checkAccountTradeRestriction(
                dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getNoteInfoCheckbox(),
                dtoReq.getNoteLimitCheck());
        
        if (checkAccountTradeRestrictionRet != null) {
            return checkAccountTradeRestrictionRet;
        }
        
        // 預り区分のチェック
        DataList<IfaSubscriptInputConfirmA001ResponseDto> checkDepositTypeRet = checkDepositType(
                dtoReq.getDepositType(), dtoReq.getCustomerCode());
        if (checkDepositTypeRet != null) {
            return checkDepositTypeRet;
        }
        
        // 最重要事項の説明のチェック
        DataList<IfaSubscriptInputConfirmA001ResponseDto> checkExplainImportantImformationRet = checkExplainImportantImformation(
                dtoReq.getCustomerCode(), dtoReq.getImportantMatterType());
        if (checkExplainImportantImformationRet != null) {
            return checkExplainImportantImformationRet;
        }
        
        // コンプラランクのチェック        
        DataList<IfaSubscriptInputConfirmA001ResponseDto> checkComplianceRankRet = checkComplianceRank(
                dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getBrandCode(), dtoReq.getKanyuKbn(),
                dtoReq.getJutyuKbn(), dtoReq.getComplianceCheckMsg(), dtoReq.getInvitationCheck());
        
        if (checkComplianceRankRet != null) {
            return checkComplianceRankRet;
        }
        
        // 約定金額のチェック

        DataList<IfaSubscriptInputConfirmA001ResponseDto> checkContractAmountRet = checkContractAmount(
                dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getDepositType(), dtoReq.getContractAmount(), apiErrorUtil);
        if (checkContractAmountRet != null) {
            return checkContractAmountRet;
        }
        
        // 備考のチェック
        DataList<IfaSubscriptInputConfirmA001ResponseDto> checkBbRemarkRet = checkBbRemark(dtoReq.getButenCode(),
                dtoReq.getAccountNumber(), dtoReq.getBbRemark(), dtoReq.getBbRemark2());
        if (checkBbRemarkRet != null) {
            return checkBbRemarkRet;
        }
        
        // 募集終了時間チェック
        DataList<IfaSubscriptInputConfirmA001ResponseDto> checkBbPeriodToAfterRet = checkBbPeriodTo(
                dtoReq.getBbPeriodTo());
        if (checkBbPeriodToAfterRet != null) {
            return checkBbPeriodToAfterRet;
        }
        
        // システム日時を取得
        String systemDateTime = DateUtil.format(systemDateDao.getSystemDate(), "YYYY/MM/dd HH:mm");
        
        // 目論見書閲覧日時のチェック(各々エラーチェック後)
        DataList<IfaSubscriptInputConfirmA001ResponseDto> checkReadTimeRet = checkReadTime(systemDateTime,
                dtoReq.getReadTime());
        if (checkReadTimeRet != null) {
            return checkReadTimeRet;
        }
        
        // 注文登録済みのチェック
        // 「画面定義書_SUB0204_02-04_1_募集入力.xlsx」の【別紙】送信・訂正用ロジック処理判定を行い、戻り値を取得する      
        // IfaSubscriptInputServiceImpL　のメソッドを呼ぶ
        String sendCorrectLogicJudgeFlag = subscriptInputServiceImpL.getSendCorrectLogicJudgeFlag(
                dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getBrandCode(),
                dtoReq.getBookBuildingPresentationFrom(), dtoReq.getOrderStatus());
        
        // 戻り値が"仲介業者新規注文"以外の場合：エラーメッセージを返し、注文発注ボタンを非活性にする
        if (!SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERINSERT.equals(sendCorrectLogicJudgeFlag)){
            DataList<IfaSubscriptInputConfirmA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ERRORS_INSERT_DATE_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_INSERT_DATE_EXIST, new String[] { MSG_ORDER }));
        
            return dtoRes;
        }
        
        // 注文情報を対面募集注文テーブル、対面募集注文明細テーブルに登録する。 
        // SQL010_1, SQL010_2を実行
        
        // SQL010_1 リクエストセット
        IfaSubscriptInputConfirmSql010_1RequestModel insSql01001Req = new IfaSubscriptInputConfirmSql010_1RequestModel();
        // リクエストをSQL010_1リクエストにコピー
        BeanUtils.copyProperties(insSql01001Req, dtoReq);
        
        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        // ユーザID（作成者,更新者）
        insSql01001Req.setUserId(ua.getUserId());
        
        // SQL010_1を実行
        try {
            int insSql01001Res = dao.insertIfaSubscriptInputConfirmSql010_1(insSql01001Req);
            if (insSql01001Res == 0) {
                throw new IfaSubscriptInputException(ERRORS_INSERT_DATE_EXIST, new String[] { MSG_ORDER });
            }
        } catch (IfaSubscriptInputException | DataIntegrityViolationException e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            throw new IfaSubscriptInputException(ERRORS_INSERT_DATE_EXIST, new String[] { MSG_ORDER });
        }
        
        // SQL010_2 リクエストセット
        IfaSubscriptInputConfirmSql010_2RequestModel insSql01002Req = new IfaSubscriptInputConfirmSql010_2RequestModel();
        // リクエストをSQL010_2リクエストにコピー
        BeanUtils.copyProperties(insSql01002Req, dtoReq);
        
        // 募集入力者
        insSql01002Req.setOrderUser(ua.getUserId());
        // ユーザID（作成者,更新者）
        insSql01002Req.setUserId(ua.getUserId());
        
        try {
            // SQL010_2を実行
            int insSql01002Res = dao.insertIfaSubscriptInputConfirmSql010_2(insSql01002Req);
            if (insSql01002Res == 0) {
                throw new IfaSubscriptInputException(ERRORS_INSERT_DATE_EXIST, new String[] { MSG_ORDER });
            }
        } catch (IfaSubscriptInputException | DataIntegrityViolationException e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            throw new IfaSubscriptInputException(ERRORS_INSERT_DATE_EXIST, new String[] { MSG_ORDER });
        }
        
        // 備考を変更する場合、関連テーブル（ブックビルディング受付テーブル）を更新する。
        // 余力拘束を行う
        // ETINTRAは別クラスでトランザクション制御
        etintraServiceImpL.orderPlacementBrokerA001Etintra(dtoReq, apiErrorUtil);
        // レスポンス返却
        // リクエストをそのままレスポンスにコピー
        IfaSubscriptInputConfirmA001ResponseDto dtoResData = new IfaSubscriptInputConfirmA001ResponseDto();
        BeanUtils.copyProperties(dtoResData, dtoReq);
        return apiErrorUtil.createDataList(Arrays.asList(dtoResData), null);
    }
    
    /**
     * アクションID：A002
     * アクション名：更新（管理者新規）
     * Dto リクエスト：IfaSubscriptInputConfirmA002RequestDto
     * Dto レスポンス：IfaSubscriptInputConfirmA002ResponseDto
     * model リクエスト：IfaSubscriptInputConfirmA002RequestModel
     * model レスポンス：IfaSubscriptInputConfirmA002ResponseModel
     *
     * @param dtoReq リクエストボディ
     * @return 更新（管理者新規）に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DataList<IfaSubscriptInputConfirmA002ResponseDto> orderPlacementManagerA002(
            IfaSubscriptInputConfirmA002RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSubscriptPeriodMasterRegisterInputServiceImplL.orderPlacementManagerA002");
        }
        
        // 利用者の口座に対する権限チェックを行う。
        DataList<IfaSubscriptInputConfirmA002ResponseDto> checkFct001Ret = checkFct001(dtoReq.getButenCode(),
                dtoReq.getAccountNumber());
        
        if (checkFct001Ret != null) {
            return checkFct001Ret;
        }
        
        // 取引コース媒介可否チェックを行う。
        DataList<IfaSubscriptInputConfirmA002ResponseDto> checkMediationAllowedRet = checkMediationAllowed(
                dtoReq.getButenCode(), dtoReq.getAccountNumber());
        
        if (checkMediationAllowedRet != null) {
            return checkMediationAllowedRet;
        }
        
        // サービス閉塞チェックで、サービス閉塞状態を判定する。
        DataList<IfaSubscriptInputConfirmA002ResponseDto> checkServiceStatusRet = checkServiceStatus();
        if (checkServiceStatusRet != null) {
            return checkServiceStatusRet;
        }
        
        // 上場日のチェック
        DataList<IfaSubscriptInputConfirmA002ResponseDto> checkPresentationDateRet = checkPresentationDate(
                dtoReq.getPresentationDate());
        if (checkPresentationDateRet != null) {
            return checkPresentationDateRet;
        }
        
        // 抽選結果に対する当選株数の紐付けチェック
        DataList<IfaSubscriptInputConfirmA002ResponseDto> checkLotteryResultRet = checkLotteryResult(
                dtoReq.getLotteryResult(), dtoReq.getBbQuantityAlloc(), dtoReq.getUnit(), dtoReq.getSellBuyUnitType());
        
        if (checkLotteryResultRet != null) {
            return checkLotteryResultRet;
        }
        
        // 注文登録済みのチェック
        // 「画面定義書_SUB0204_02-04_1_募集入力.xlsx」の【別紙】送信・訂正用ロジック処理判定を行い、戻り値を取得する
        // IfaSubscriptInputServiceImpL　のメソッドを呼ぶ
        String sendCorrectLogicJudgeFlag = subscriptInputServiceImpL.getSendCorrectLogicJudgeFlag(
                dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getBrandCode(),
                dtoReq.getBookBuildingPresentationFrom(), dtoReq.getOrderStatus());
        
        // 戻り値が"SBIINSERT:管理者新規注文"以外の場合：エラーメッセージを返し、注文発注ボタンを非活性にする
        if (!SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIINSERT.equals(sendCorrectLogicJudgeFlag)) {
            DataList<IfaSubscriptInputConfirmA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ERRORS_INSERT_DATE_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_INSERT_DATE_EXIST, new String[] { MSG_ORDER }));
        
            return dtoRes;
        }
        
        // 注文情報を対面募集注文テーブル、対面募集注文明細テーブルに登録する。
        // SQL002_1, SQL002_2 の実行
        
        // SQL002_1 リクエストセット
        IfaSubscriptInputConfirmSql002_1RequestModel insSql00201Req = new IfaSubscriptInputConfirmSql002_1RequestModel();
        // リクエストをSQL002_1リクエストにコピー
        BeanUtils.copyProperties(insSql00201Req, dtoReq);
        
        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        // ユーザID（作成者,更新者）
        insSql00201Req.setUserId(ua.getUserId());
        
        // SQL002_1を実行
        // 正常：次の処理へ
        // 異常：ロールバックして、エラーメッセージを返す
        try {
            int insSql00201Res = dao.insertIfaSubscriptInputConfirmSql002_1(insSql00201Req);
            if (insSql00201Res == 0) {
                throw new IfaSubscriptInputException(ERRORS_INSERT_DATE_EXIST, new String[] { MSG_ORDER });
            }
        } catch (IfaSubscriptInputException | DataIntegrityViolationException e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            throw new IfaSubscriptInputException(ERRORS_INSERT_DATE_EXIST, new String[] { MSG_ORDER });
        }
        
        // SQL002_2 リクエストセット
        IfaSubscriptInputConfirmSql002_2RequestModel insSql00202Req = new IfaSubscriptInputConfirmSql002_2RequestModel();
        // リクエストをSQL002_2リクエストにコピー
        BeanUtils.copyProperties(insSql00202Req, dtoReq);
        // ユーザID（作成者,更新者）
        insSql00202Req.setUserId(ua.getUserId());
        
        // SQL002_2を実行
        // 正常：次の処理へ
        // 異常：ロールバックして、エラーメッセージを返す
        try {
            int insSql00202Res = dao.insertIfaSubscriptInputConfirmSql002_2(insSql00202Req);
            if (insSql00202Res == 0) {
                throw new IfaSubscriptInputException(ERRORS_INSERT_DATE_EXIST, new String[] { MSG_ORDER });
            }
        } catch (IfaSubscriptInputException | DataIntegrityViolationException e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            throw new IfaSubscriptInputException(ERRORS_INSERT_DATE_EXIST, new String[] { MSG_ORDER });
        }
        
        // レスポンス返却
        // リクエストをそのままレスポンスにコピー
        IfaSubscriptInputConfirmA002ResponseDto dtoResData = new IfaSubscriptInputConfirmA002ResponseDto();
        BeanUtils.copyProperties(dtoResData, dtoReq);
        
        DataList<IfaSubscriptInputConfirmA002ResponseDto> dtoRes = IfaCommonUtil
                .createDataList(Arrays.asList(dtoResData), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        return dtoRes;
    }
    
    /**
     * アクションID：A003
     * アクション名：更新（管理者更新）
     * Dto リクエスト：IfaSubscriptInputConfirmA003RequestDto
     * Dto レスポンス：IfaSubscriptInputConfirmA003ResponseDto
     *
     * @param dtoReq リクエストボディ
     * @return 更新（管理者更新）に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DataList<IfaSubscriptInputConfirmA003ResponseDto> orderUpdateMnagerA003(
            IfaSubscriptInputConfirmA003RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSubscriptPeriodMasterRegisterInputServiceImplL.orderUpdateMnagerA003");
        }
        
        // 利用者の口座に対する権限チェックを行う。
        DataList<IfaSubscriptInputConfirmA003ResponseDto> checkFct001Ret = checkFct001(dtoReq.getButenCode(),
                dtoReq.getAccountNumber());
        
        if (checkFct001Ret != null) {
            return checkFct001Ret;
        }
        
        // 取引コース媒介可否チェックを行う。
        DataList<IfaSubscriptInputConfirmA003ResponseDto> checkMediationAllowedRet = checkMediationAllowed(
                dtoReq.getButenCode(), dtoReq.getAccountNumber());
        
        if (checkMediationAllowedRet != null) {
            return checkMediationAllowedRet;
        }
        
        // サービス閉塞チェックで、サービス閉塞状態を判定する。
        DataList<IfaSubscriptInputConfirmA003ResponseDto> checkServiceStatusRet = checkServiceStatus();
        if (checkServiceStatusRet != null) {
            return checkServiceStatusRet;
        }
        
        // 上場日のチェック
        DataList<IfaSubscriptInputConfirmA003ResponseDto> checkPresentationDateRet = checkPresentationDate(
                dtoReq.getPresentationDate());
        if (checkPresentationDateRet != null) {
            return checkPresentationDateRet;
        }
        
        // 抽選結果に対する当選株数の紐付けチェック
        DataList<IfaSubscriptInputConfirmA003ResponseDto> checkLotteryResultRet = checkLotteryResult(
                dtoReq.getLotteryResult(), dtoReq.getBbQuantityAlloc(), dtoReq.getUnit(), dtoReq.getSellBuyUnitType());
        
        if (checkLotteryResultRet != null) {
            return checkLotteryResultRet;
        }
        
        // 注文登録済みのチェック
        // 「画面定義書_SUB0204_02-04_1_募集入力.xlsx」の【別紙】送信・訂正用ロジック処理判定を行い、戻り値を取得する
        // IfaSubscriptInputServiceImpL　のメソッドを呼ぶ
        String sendCorrectLogicJudgeFlag = subscriptInputServiceImpL.getSendCorrectLogicJudgeFlag(
                dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getBrandCode(),
                dtoReq.getBookBuildingPresentationFrom(), dtoReq.getOrderStatus());
        
        // 戻り値が"SBIUPDATEONE:管理者更新注文"以外の場合：エラーメッセージを返し、更新ボタンを非活性にする
        if (!SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIUPDATEONE.equals(sendCorrectLogicJudgeFlag)) {
            DataList<IfaSubscriptInputConfirmA003ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ERRORS_INSERT_DATE_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_INSERT_DATE_EXIST, new String[] { MSG_ORDER }));
        
            return dtoRes;
        }
        
        // 対面募集注文テーブル、対面募集注文明細テーブルを更新する。
        // SQL003_1, SQL003_2 の実行
        
        // SQL003_1 リクエストセット
        IfaSubscriptInputConfirmSql003_1RequestModel updSql00301Req = new IfaSubscriptInputConfirmSql003_1RequestModel();
        // リクエストをSQL003_1リクエストにコピー
        BeanUtils.copyProperties(updSql00301Req, dtoReq);
        
        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        // ユーザID（更新者）
        updSql00301Req.setUserId(ua.getUserId());
        
        // SQL003_1を実行
        // 正常：次の処理へ
        // 異常：ロールバックして、エラーメッセージを返す
        try {
            int updSql00301Res = dao.updateIfaSubscriptInputConfirmSql003_1(updSql00301Req);
            if (updSql00301Res == 0) {
                throw new IfaSubscriptInputException(ERRORS_EXCLUSIVE, new String[] { MSG_ORDER });
            }
        } catch (IfaSubscriptInputException e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            throw new IfaSubscriptInputException(ERRORS_EXCLUSIVE, new String[] { MSG_ORDER });
        }
        
        // SQL003_2 リクエストセット
        IfaSubscriptInputConfirmSql003_2RequestModel updSql00302Req = new IfaSubscriptInputConfirmSql003_2RequestModel();
        // リクエストをSQL003_2リクエストにコピー
        BeanUtils.copyProperties(updSql00302Req, dtoReq);
        
        // ユーザID（更新者）
        updSql00302Req.setUserId(ua.getUserId());
        
        // SQL003_2を実行
        // 正常：次の処理へ
        // 異常：ロールバックして、エラーメッセージを返す
        try {
            int updSql00302Res = dao.updateIfaSubscriptInputConfirmSql003_2(updSql00302Req);
            if (updSql00302Res == 0) {
                throw new IfaSubscriptInputException(ERRORS_EXCLUSIVE, new String[] { MSG_ORDER });
            }
        } catch (Exception e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            throw new IfaSubscriptInputException(ERRORS_EXCLUSIVE, new String[] { MSG_ORDER });
        }
        
        // レスポンス返却
        // リクエストをそのままレスポンスにコピー
        IfaSubscriptInputConfirmA003ResponseDto dtoResData = new IfaSubscriptInputConfirmA003ResponseDto();
        BeanUtils.copyProperties(dtoResData, dtoReq);
        
        DataList<IfaSubscriptInputConfirmA003ResponseDto> dtoRes = IfaCommonUtil
                .createDataList(Arrays.asList(dtoResData), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        return dtoRes;
        
    }
    
    /**
     * アクションID：A004
     * アクション名：更新（管理者訂正）
     * Dto リクエスト：IfaSubscriptInputConfirmA004RequestDto
     * Dto レスポンス：IfaSubscriptInputConfirmA004ResponseDto
     * model リクエスト：IfaSubscriptInputConfirmA004RequestModel
     * model レスポンス：IfaSubscriptInputConfirmA004ResponseModel
     *
     * @param dtoReq リクエストボディ
     * @return 更新（管理者訂正）に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DataList<IfaSubscriptInputConfirmA004ResponseDto> orderCorrectionManagerA004(
            IfaSubscriptInputConfirmA004RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSubscriptPeriodMasterRegisterInputServiceImplL.orderCorrectionManagerA004");
        }
        
        // 利用者の口座に対する権限チェックを行う。
        DataList<IfaSubscriptInputConfirmA004ResponseDto> checkFct001Ret = checkFct001(dtoReq.getButenCode(),
                dtoReq.getAccountNumber());
        
        if (checkFct001Ret != null) {
            return checkFct001Ret;
        }
        
        // 取引コース媒介可否チェックを行う。
        DataList<IfaSubscriptInputConfirmA004ResponseDto> checkMediationAllowedRet = checkMediationAllowed(
                dtoReq.getButenCode(), dtoReq.getAccountNumber());
        
        if (checkMediationAllowedRet != null) {
            return checkMediationAllowedRet;
        }
        
        // サービス閉塞チェックで、サービス閉塞状態を判定する。
        DataList<IfaSubscriptInputConfirmA004ResponseDto> checkServiceStatusRet = checkServiceStatus();
        if (checkServiceStatusRet != null) {
            return checkServiceStatusRet;
        }
        
        // 上場日のチェック
        DataList<IfaSubscriptInputConfirmA004ResponseDto> checkPresentationDateRet = checkPresentationDate(
                dtoReq.getPresentationDate());
        if (checkPresentationDateRet != null) {
            return checkPresentationDateRet;
        }
        
        // 抽選結果に対する当選株数の紐付けチェック
        DataList<IfaSubscriptInputConfirmA004ResponseDto> checkLotteryResultRet = checkLotteryResult(
                dtoReq.getLotteryResult(), dtoReq.getBbQuantityAlloc(), dtoReq.getUnit(), dtoReq.getSellBuyUnitType());
        
        if (checkLotteryResultRet != null) {
            return checkLotteryResultRet;
        }
        
        // 注文登録済みのチェック
        // 「画面定義書_SUB0204_02-04_1_募集入力.xlsx」の【別紙】送信・訂正用ロジック処理判定を行い、戻り値を取得する
        // IfaSubscriptInputServiceImpL　のメソッドを呼ぶ
        String sendCorrectLogicJudgeFlag = subscriptInputServiceImpL.getSendCorrectLogicJudgeFlag(
                dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getBrandCode(),
                dtoReq.getBookBuildingPresentationFrom(), dtoReq.getOrderStatus());
        
        // 戻り値が"SBIUPDATETWO:管理者訂正注文(注文状況のみ更新可能)"以外の場合：エラーメッセージを返し、注文訂正ボタンを非活性にする
        if (!SEND_CORRECT_LOGIC_JUDGE_FLAG_SBIUPDATETWO.equals(sendCorrectLogicJudgeFlag)) {
            DataList<IfaSubscriptInputConfirmA004ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ERRORS_INSERT_DATE_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_INSERT_DATE_EXIST, new String[] { MSG_ORDER }));
        
            return dtoRes;
        }
        
        // 対面募集注文明細テーブルを更新する。
        // SQL004 の実行
        // SQL004 リクエストセット
        IfaSubscriptInputConfirmSql004RequestModel updSql004Req = new IfaSubscriptInputConfirmSql004RequestModel();
        // リクエストをSQL004リクエストにコピー
        BeanUtils.copyProperties(updSql004Req, dtoReq);
        
        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        // ユーザID（更新者）
        updSql004Req.setUserId(ua.getUserId());
        
        // SQL004を実行
        // 正常：次の処理へ
        // 異常：ロールバックして、エラーメッセージを返す
        try {
            int updSql004Res = dao.updateIfaSubscriptInputConfirmSql004(updSql004Req);
            if (updSql004Res == 0) {
                throw new IfaSubscriptInputException(ERRORS_EXCLUSIVE, new String[] { MSG_ORDER });
            }
        } catch (Exception e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            throw new IfaSubscriptInputException(ERRORS_EXCLUSIVE, new String[] { MSG_ORDER });
        }
        
        // レスポンス返却
        // リクエストをそのままレスポンスにコピー
        IfaSubscriptInputConfirmA004ResponseDto dtoResData = new IfaSubscriptInputConfirmA004ResponseDto();
        BeanUtils.copyProperties(dtoResData, dtoReq);
        
        DataList<IfaSubscriptInputConfirmA004ResponseDto> dtoRes = IfaCommonUtil
                .createDataList(Arrays.asList(dtoResData), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        return dtoRes;
    }
    
    /**
     * アクションID：A005
     * アクション名：訂正（仲介業者更新）
     * Dto リクエスト：IfaSubscriptInputConfirmA005RequestDto
     * Dto レスポンス：IfaSubscriptInputConfirmA005ResponseDto
     * model リクエスト：IfaSubscriptInputConfirmA005RequestModel
     * model レスポンス：IfaSubscriptInputConfirmA005ResponseModel
     *
     * @param dtoReq リクエストボディ
     * @return 訂正（仲介業者更新）に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DataList<IfaSubscriptInputConfirmA005ResponseDto> orderUpdateBrokerA005(
            IfaSubscriptInputConfirmA005RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSubscriptPeriodMasterRegisterInputServiceImplL.orderUpdateBrokerA005");
        }
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 利用者の口座に対する権限チェックを行う。
        DataList<IfaSubscriptInputConfirmA005ResponseDto> checkFct001Ret = checkFct001(dtoReq.getButenCode(),
                dtoReq.getAccountNumber());
        
        if (checkFct001Ret != null) {
            return checkFct001Ret;
        }
        
        // 取引コース媒介可否チェックを行う。
        DataList<IfaSubscriptInputConfirmA005ResponseDto> checkMediationAllowedRet = checkMediationAllowed(
                dtoReq.getButenCode(), dtoReq.getAccountNumber());
        
        if (checkMediationAllowedRet != null) {
            return checkMediationAllowedRet;
        }
        
        // サービス閉塞チェックで、サービス閉塞状態を判定する。
        DataList<IfaSubscriptInputConfirmA005ResponseDto> checkServiceStatusRet = checkServiceStatus();
        if (checkServiceStatusRet != null) {
            return checkServiceStatusRet;
        }
        
        // 上場日のチェック
        DataList<IfaSubscriptInputConfirmA005ResponseDto> checkPresentationDateRet = checkPresentationDate(
                dtoReq.getPresentationDate());
        if (checkPresentationDateRet != null) {
            return checkPresentationDateRet;
        }
        
        // 募集終了時間チェック（各々エラーチェック前）
        DataList<IfaSubscriptInputConfirmA005ResponseDto> checkBbPeriodToBeforeRet = checkBbPeriodTo(
                dtoReq.getBbPeriodTo());
        if (checkBbPeriodToBeforeRet != null) {
            return checkBbPeriodToBeforeRet;
        }
        
        // 数量のチェック
        DataList<IfaSubscriptInputConfirmA005ResponseDto> checkQuantityRet = checkQuantity(dtoReq.getQuantity(),
                dtoReq.getBbQuantityAlloc(), dtoReq.getMaxAllocation(), dtoReq.getUnit(), dtoReq.getSellBuyUnitType());
        if (checkQuantityRet != null) {
            return checkQuantityRet;
        }
        
        // 口座の取引制限チェック
        DataList<IfaSubscriptInputConfirmA005ResponseDto> checkAccountTradeRestrictionRet = checkAccountTradeRestriction(
                dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getNoteInfoCheckbox(),
                dtoReq.getNoteLimitCheck());
        if (checkAccountTradeRestrictionRet != null) {
            return checkAccountTradeRestrictionRet;
        }
        
        // 預り区分のチェック
        DataList<IfaSubscriptInputConfirmA005ResponseDto> checkDepositTypeRet = checkDepositType(
                dtoReq.getDepositType(), dtoReq.getCustomerCode());
        if (checkDepositTypeRet != null) {
            return checkDepositTypeRet;
        }
        
        // 最重要事項の説明のチェック
        DataList<IfaSubscriptInputConfirmA005ResponseDto> checkExplainImportantImformationRet = checkExplainImportantImformation(
                dtoReq.getCustomerCode(), dtoReq.getImportantMatterType());
        if (checkExplainImportantImformationRet != null) {
            return checkExplainImportantImformationRet;
        }
        
        // コンプラランクのチェック        
        DataList<IfaSubscriptInputConfirmA005ResponseDto> checkComplianceRankRet = checkComplianceRank(
                dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getBrandCode(), dtoReq.getKanyuKbn(),
                dtoReq.getJutyuKbn(), dtoReq.getComplianceCheckMsg(), dtoReq.getInvitationCheck());
        
        if (checkComplianceRankRet != null) {
            return checkComplianceRankRet;
        }
        
        // 約定金額のチェック

        DataList<IfaSubscriptInputConfirmA005ResponseDto> checkContractAmountRet = checkContractAmount(
                dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getDepositType(), dtoReq.getContractAmount(), apiErrorUtil);
        if (checkContractAmountRet != null) {
            return checkContractAmountRet;
        }
        
        // 備考のチェック
        DataList<IfaSubscriptInputConfirmA005ResponseDto> checkBbRemarkRet = checkBbRemark(dtoReq.getButenCode(),
                dtoReq.getAccountNumber(), dtoReq.getBbRemark(), dtoReq.getBbRemark2());
        if (checkBbRemarkRet != null) {
            return checkBbRemarkRet;
        }
        
        // 募集終了時間チェック(各々エラーチェック後)
        DataList<IfaSubscriptInputConfirmA005ResponseDto> checkBbPeriodToAfterRet = checkBbPeriodTo(
                dtoReq.getBbPeriodTo());
        if (checkBbPeriodToAfterRet != null) {
            return checkBbPeriodToAfterRet;
        }
        
        // システム日付を取得
        String systemDateTime = DateUtil.format(systemDateDao.getSystemDate(), "yyyy/MM/dd HH:mm");
        
        // 目論見書閲覧日時のチェック
        DataList<IfaSubscriptInputConfirmA005ResponseDto> checkReadTimeRet = checkReadTime(systemDateTime,
                dtoReq.getReadTime());
        if (checkReadTimeRet != null) {
            return checkReadTimeRet;
        }
        
        // 注文登録済みのチェック
        // 「画面定義書_SUB0204_02-04_1_募集入力.xlsx」の【別紙】送信・訂正用ロジック処理判定を行い、戻り値を取得する
        // IfaSubscriptInputServiceImpL　のメソッドを呼ぶ
        String sendCorrectLogicJudgeFlag = subscriptInputServiceImpL.getSendCorrectLogicJudgeFlag(
                dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getBrandCode(),
                dtoReq.getBookBuildingPresentationFrom(), dtoReq.getOrderStatus());
        
        // // 戻り値が"BROKERUPDATE:仲介業者更新注文"以外の場合：エラーメッセージを返し、更新ボタンを非活性にする
        if (!SEND_CORRECT_LOGIC_JUDGE_FLAG_BROKERUPDATE.equals(sendCorrectLogicJudgeFlag)) {
            DataList<IfaSubscriptInputConfirmA005ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ERRORS_INSERT_DATE_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_INSERT_DATE_EXIST, new String[] { MSG_ORDER }));
        
            return dtoRes;
        }
        
        // 対面募集注文テーブル、対面募集注文明細テーブルを更新する。
        // SQL012_1, SQL012_2 を実行
        
        // SQL012_1 リクエストセット
        IfaSubscriptInputConfirmSql012_1RequestModel insSql01201Req = new IfaSubscriptInputConfirmSql012_1RequestModel();
        // リクエストをSQL012_1リクエストにコピー
        BeanUtils.copyProperties(insSql01201Req, dtoReq);
        
        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        // ユーザID（更新者）
        insSql01201Req.setUserId(ua.getUserId());
        
        // SQL012_1を実行
        // 正常：次の処理へ
        // 異常または更新0件：ロールバックして、エラーメッセージを返す
        try {
            int insSql01201Res = dao.updateIfaSubscriptInputConfirmSql012_1(insSql01201Req);
            if (insSql01201Res == 0) {
                throw new IfaSubscriptInputException(ERRORS_EXCLUSIVE, new String[] { MSG_ORDER });
            }
        } catch (IfaSubscriptInputException e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            throw new IfaSubscriptInputException(ERRORS_EXCLUSIVE, new String[] { MSG_ORDER });
        }
        
        // SQL012_2 リクエストセット
        IfaSubscriptInputConfirmSql012_2RequestModel insSql01202Req = new IfaSubscriptInputConfirmSql012_2RequestModel();
        // リクエストをSQL012_2リクエストにコピー
        BeanUtils.copyProperties(insSql01202Req, dtoReq);
        
        // ユーザID（更新者）
        insSql01202Req.setUserId(ua.getUserId());
        
        // SQL012_2を実行
        // 正常：次の処理へ
        // 異常または更新0件：ロールバックして、エラーメッセージを返す
        try {
            int insSql01202Res = dao.updateIfaSubscriptInputConfirmSql012_2(insSql01202Req);
            if (insSql01202Res == 0) {
                throw new IfaSubscriptInputException(ERRORS_EXCLUSIVE, new String[] { MSG_ORDER });
            }
        } catch (Exception e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            throw new IfaSubscriptInputException(ERRORS_EXCLUSIVE, new String[] { MSG_ORDER });
        }
        
        // 備考を変更する場合、関連テーブル（ブックビルディング受付テーブル）を更新する。
        // 余力拘束を行う
        // ETINTRAは別クラスでトランザクション制御
        etintraServiceImpL.orderUpdateBrokerA005Etintra(dtoReq, apiErrorUtil);

        // レスポンス返却
        // リクエストをそのままレスポンスにコピー
        IfaSubscriptInputConfirmA005ResponseDto dtoResData = new IfaSubscriptInputConfirmA005ResponseDto();
        BeanUtils.copyProperties(dtoResData, dtoReq);
        
        return apiErrorUtil.createDataList(Arrays.asList(dtoResData), null);
    }

    /**
     * アクションID：A006
     * アクション名：訂正（仲介業者訂正）
     * Dto リクエスト：IfaSubscriptInputConfirmA006RequestDto
     * Dto レスポンス：IfaSubscriptInputConfirmA006ResponseDto
     * model リクエスト：IfaSubscriptInputConfirmA006RequestModel
     * model レスポンス：IfaSubscriptInputConfirmA006ResponseModel
     *
     * @param dtoReq リクエストボディ
     * @return 訂正（仲介業者訂正）に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DataList<IfaSubscriptInputConfirmA006ResponseDto> orderCorrectionBrokerA006(
            IfaSubscriptInputConfirmA006RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSubscriptPeriodMasterRegisterInputServiceImplL.orderCorrectionBrokerA006");
        }
        // 利用者の口座に対する権限チェックを行う。
        DataList<IfaSubscriptInputConfirmA006ResponseDto> checkFct001Ret = checkFct001(dtoReq.getButenCode(),
                dtoReq.getAccountNumber());
        
        if (checkFct001Ret != null) {
            return checkFct001Ret;
        }
        
        // 取引コース媒介可否チェックを行う。
        DataList<IfaSubscriptInputConfirmA006ResponseDto> checkMediationAllowedRet = checkMediationAllowed(
                dtoReq.getButenCode(), dtoReq.getAccountNumber());
        
        if (checkMediationAllowedRet != null) {
            return checkMediationAllowedRet;
        }
        
        // サービス閉塞チェックで、サービス閉塞状態を判定する。
        DataList<IfaSubscriptInputConfirmA006ResponseDto> checkServiceStatusRet = checkServiceStatus();
        if (checkServiceStatusRet != null) {
            return checkServiceStatusRet;
        }
        
        // コンプラランクのチェック        
        DataList<IfaSubscriptInputConfirmA006ResponseDto> checkComplianceRankRet = checkComplianceRank(
                dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getBrandCode(), dtoReq.getKanyuKbn(),
                dtoReq.getJutyuKbn(), dtoReq.getComplianceCheckMsg(), dtoReq.getInvitationCheck());
        
        if (checkComplianceRankRet != null) {
            return checkComplianceRankRet;
        }
        
        // 備考のチェック
        DataList<IfaSubscriptInputConfirmA006ResponseDto> checkBbRemarkRet = checkBbRemark(dtoReq.getButenCode(),
                dtoReq.getAccountNumber(), dtoReq.getBbRemark(), dtoReq.getBbRemark2());
        if (checkBbRemarkRet != null) {
            return checkBbRemarkRet;
        }
        
        // 募集終了時間チェック(各々エラーチェック後)
        DataList<IfaSubscriptInputConfirmA006ResponseDto> checkBbPeriodToBeforeRet = checkBbPeriodTo(
                dtoReq.getBbPeriodTo());
        if (checkBbPeriodToBeforeRet != null) {
            return checkBbPeriodToBeforeRet;
        }
        
        // 最重要事項の説明のチェック
        DataList<IfaSubscriptInputConfirmA006ResponseDto> checkExplainImportantImformationRet = checkExplainImportantImformation(
                dtoReq.getCustomerCode(), dtoReq.getImportantMatterType());
        if (checkExplainImportantImformationRet != null) {
            return checkExplainImportantImformationRet;
        }
        
        // 対面募集注文明細テーブルを更新する。
        // SQL013の実行
        
        // SQL013 リクエストセット
        IfaSubscriptInputConfirmSql013RequestModel updSql013Req = new IfaSubscriptInputConfirmSql013RequestModel();
        // リクエストをSQL013リクエストにコピー
        BeanUtils.copyProperties(updSql013Req, dtoReq);
        
        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        // ユーザID（更新者）
        updSql013Req.setUserId(ua.getUserId());
        
        // SQL013を実行
        // 正常：次の処理へ
        // 異常または更新0件：ロールバックして、エラーメッセージを返す
        try {
            int updSql013Res = dao.updateIfaSubscriptInputConfirmSql013(updSql013Req);
            if (updSql013Res == 0) {
                throw new IfaSubscriptInputException(ERRORS_EXCLUSIVE, new String[] { MSG_ORDER });
            }
        } catch (Exception e) {
            throw new IfaSubscriptInputException(ERRORS_EXCLUSIVE, new String[] { MSG_ORDER });
        }
        
        // 備考を変更する場合、関連テーブル（ブックビルディング受付テーブル）を更新する。
        // ETINTRAは別クラスでトランザクション制御
        etintraServiceImpL.orderCorrectionBrokerA006Etintra(dtoReq);
        
        // レスポンス返却
        // リクエストをそのままレスポンスにコピー
        IfaSubscriptInputConfirmA006ResponseDto dtoResData = new IfaSubscriptInputConfirmA006ResponseDto();
        BeanUtils.copyProperties(dtoResData, dtoReq);
        
        return IfaCommonUtil.createDataList(Arrays.asList(dtoResData), ErrorLevel.SUCCESS,
                    ErrorLevel.SUCCESS.toString(), "");
    }
    
    /**
     * アクションID：A007
     * アクション名：取消
     * Dto リクエスト：IfaSubscriptInputConfirmA007RequestDto
     * Dto レスポンス：IfaSubscriptInputConfirmA007ResponseDto
     * model リクエスト：IfaSubscriptInputConfirmA007RequestModel
     * model レスポンス：IfaSubscriptInputConfirmA007ResponseModel
     *
     * @param dtoReq リクエストボディ
     * @return 取消に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DataList<IfaSubscriptInputConfirmA007ResponseDto> orderCancellationA007(
            IfaSubscriptInputConfirmA007RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSubscriptPeriodMasterRegisterInputServiceImplL.orderCancellationA007");
        }
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 利用者の口座に対する権限チェックを行う。
        DataList<IfaSubscriptInputConfirmA007ResponseDto> checkFct001Ret = checkFct001(dtoReq.getButenCode(),
                dtoReq.getAccountNumber());
        
        if (checkFct001Ret != null) {
            return checkFct001Ret;
        }
        
        // 取引コース媒介可否チェックを行う。
        DataList<IfaSubscriptInputConfirmA007ResponseDto> checkMediationAllowedRet = checkMediationAllowed(
                dtoReq.getButenCode(), dtoReq.getAccountNumber());
        
        if (checkMediationAllowedRet != null) {
            return checkMediationAllowedRet;
        }
        
        // サービス閉塞チェックで、サービス閉塞状態を判定する。
        DataList<IfaSubscriptInputConfirmA007ResponseDto> checkServiceStatusRet = checkServiceStatus();
        if (checkServiceStatusRet != null) {
            return checkServiceStatusRet;
        }
        
        // 募集終了時間チェック
        DataList<IfaSubscriptInputConfirmA007ResponseDto> checkBbPeriodToBeforeRet = checkBbPeriodTo(
                dtoReq.getBbPeriodTo());
        if (checkBbPeriodToBeforeRet != null) {
            return checkBbPeriodToBeforeRet;
        }
        
        // 対面募集注文明細テーブルの更新と登録を行う。
        // SQL006_1, SQl006_2 の実行
        
        // SQL006_1 リクエストセット
        IfaSubscriptInputConfirmSql006_1RequestModel updSql00601Req = new IfaSubscriptInputConfirmSql006_1RequestModel();
        // リクエストをSQL006_1リクエストにコピー
        BeanUtils.copyProperties(updSql00601Req, dtoReq);
        
        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        // ユーザID（作成者,更新者）
        updSql00601Req.setUserId(ua.getUserId());
        
        // SQL006_1を実行
        // 正常：次の処理へ
        // 異常または更新0件：ロールバックして、エラーメッセージを返す
        try {
            int updSql00601Res = dao.updateIfaSubscriptInputConfirmSql006_1(updSql00601Req);
            if (updSql00601Res == 0) {
                throw new IfaSubscriptInputException(ERRORS_EXCLUSIVE, new String[] { MSG_ORDER });
            }
        } catch (Exception e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            throw new IfaSubscriptInputException(ERRORS_EXCLUSIVE, new String[] { MSG_ORDER });
        }
        
        // SQL006_2 リクエストセット
        IfaSubscriptInputConfirmSql006_2RequestModel insSql00602Req = new IfaSubscriptInputConfirmSql006_2RequestModel();
        // リクエストをSQL006_2リクエストにコピー
        BeanUtils.copyProperties(insSql00602Req, dtoReq);
        
        // ユーザID（作成者,更新者）
        insSql00602Req.setUserId(ua.getUserId());
        
        // SQL006_2を実行
        // 正常：次の処理へ
        // 異常または登録0件：ロールバックして、エラーメッセージを返す
        try {
            int insSql00602Res = dao.insertIfaSubscriptInputConfirmSql006_2(insSql00602Req);
            if (insSql00602Res == 0) {
                throw new IfaSubscriptInputException(ERRORS_EXCLUSIVE, new String[] { MSG_ORDER });
            }
        } catch (IfaSubscriptInputException | DataIntegrityViolationException e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            throw new IfaSubscriptInputException(ERRORS_EXCLUSIVE, new String[] { MSG_ORDER });
        }
        
        if (!checkRecruitmentOrderDate(dtoReq.getRecruitmentOrderDate())) {
            // 日跨ぎ状態ではない（FALSE）の場合：余力拘束解除を行う
            etintraServiceImpL.callAdditionalPlaceOrder(ACTION_TYPE_ADDITIONAL_PLACE_ORDER_CANCEL,
                    dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getContractAmount(), ua.getUserId(),
                    dtoReq.getDepositType(), dtoReq.getRecruitmentOrderDate(), apiErrorUtil);

        }
        
        // レスポンス返却
        // リクエストをそのままレスポンスにコピー
        IfaSubscriptInputConfirmA007ResponseDto dtoResData = new IfaSubscriptInputConfirmA007ResponseDto();
        BeanUtils.copyProperties(dtoResData, dtoReq);
        
        return apiErrorUtil.createDataList(Arrays.asList(dtoResData), null);
    }
    
    // --------------------------------------------------------------
    // メソッド
    // --------------------------------------------------------------
    
    /**
     * 利用者の口座に対する権限チェックを行う<br/>
     * FCT001のチェック（A001,A002,A005,A007）
     *
     * @param <T> レスポンスタイプ
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     */
    private <T> DataList<T> checkFct001(String butenCode, String accountNumber) {
        
        // FCT001：権限チェックを行う
        InputFct001Dto fct001In = new InputFct001Dto();
        
        // 部店コード,
        fct001In.setButenCode(butenCode);
        // 口座番号
        fct001In.setAccountNumber(accountNumber);
        
        // FCT001を呼び出す(利用者顧客参照権限チェック)
        OutputFct001Dto fct001Out = fct001.doCheck(fct001In);
        
        // FCT001.対象顧客参照権限有無＝"0"（権限なし）の場合
        if (FCT001_NO_AUTHORIZED.equals(fct001Out.getTargetCustomerRefAuthFlag())) {
            // 権限なしエラー(errors.butenAccountNotExis)を返す
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_BUTEN_ACCOUNT_NOT_EXIST, IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                            new String[] { butenCode, accountNumber }));
            return dtoRes;
        }
        
        // FCT001.取引停止フラグ＝"1"（取引停止口座）の場合
        if (FCT001_TRADING_SUSPENSION.equals(fct001Out.getTradeSuspendFlag())) {
            // 取引停止口座エラー(errors.cmn.selectedAccount.outOfService)を返す
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTED_ACCOUNT_OUT_OF_SERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_OUT_OF_SERVICE, new String[] {}));
            return dtoRes;
        }
        
        return null;
    }
    
    /**
     * 取引コース媒介可否チェック（A001,A002,A005,A007）<br/>
     * FCT003を呼び出す。
     *
     * @param <T> レスポンスタイプ
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     */
    private <T> DataList<T> checkMediationAllowed(String butenCode, String accountNumber) {
        
        // 利用者の口座における媒介可否リストを取得する。
        InputFct003Dto fct003In = new InputFct003Dto();
        // "リクエストパラメーターセット
        // 部店コード
        fct003In.setButenCode(butenCode);
        // 口座番号
        fct003In.setAccountNumber(accountNumber);
        // 証券金銭種別（"01"　国内株式）※固定値
        fct003In.setProductCd(SECURITY_MONEY_CLASS_01);
        // 取引種別（国内株式）（"A" 募集）※固定値
        fct003In.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_A);
        
        // FCT003(取引コース媒介可否取得)を呼び出す
        OutputFct003Dto fct003Out = fct003.doCheck(fct003In);
        
        if (fct003Out.getMediateProprietyList() == null) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE, new String[] {
                            codeListService.getValue(CODE_LIST_MSG_DISPLAY_TARGET_TRADE, CODE_LIST_KEY, CODE_LIST_DISP_PATTERN) }));
        }
        // 取引媒介可否リスト.媒介可否が"1"(媒介可)以外の場合、取引不可エラーを返す
        for (MediatePropriety mediatePropriety : fct003Out.getMediateProprietyList()) {
            if (!MEDIATE_PROPRIETY.equals(mediatePropriety.getMediatePropriety())) {
                return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                        ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                        IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE, new String[] {
                                codeListService.getValue(CODE_LIST_MSG_DISPLAY_TARGET_TRADE, CODE_LIST_KEY, CODE_LIST_DISP_PATTERN) }));
            }
        }
        return null;
    }
    
    private <T> DataList<T> checkServiceStatus() throws Exception {
        
        Boolean serviceStatus = serviceStatusService.getServiceStatus(IPOPO_NRI_API);
        
        // サービス閉塞状態がFALSEのとき処理を抜ける
        if (!serviceStatus) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, WARNING_W0006,
                    IfaCommonUtil.getMessage(WARNING_W0006, new String[] {}));
            return dtoRes;
        }
        return null;
    }
    
    /**
     * 上場日のチェック（A001,A002,A003,A004,A005）
     *
     * @param <T> レスポンスタイプ
     * @param presentationDate 上場日
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     * @throws Exception システム日時取得時の例外
     */
    private <T> DataList<T> checkPresentationDate(String presentationDate) throws Exception {
        
        // システム日付を取得
        String systemDate = DateUtil.format(systemDateDao.getSystemDate(), DateUtil.YYYYMMDD);
        
        // 上場日>システム日付の場合：次の処理へ
        if (StringUtils.equals(systemDate, presentationDate)
                || !DateUtil.isValidFromTo(systemDate, presentationDate, DateUtil.YYYYMMDD)) {
            // 上場日<=システム日付の場合：エラーメッセージを返す
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_PERIOD_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_PERIOD_CHECK, new String[] {}));
            return dtoRes;
        }
        return null;
    }
    
    /**
     * 募集終了時間チェック<br/>
     * 募集期間（To）＝システム日付 かつ　SQL005.NAME<=システム時刻の場合<br/>
     * エラーメッセージを返す
     *
     * @param <T> レスポンスタイプ
     * @param bbPeriodTo 募集期間（To）
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     * @throws Exception SQL005実行時の例外
     */
    private <T> DataList<T> checkBbPeriodTo(String bbPeriodTo) throws Exception {
        
        // システム日時を取得
        String systemDate = DateUtil.format(systemDateDao.getSystemDate(), DateUtil.SEPARATED_YYYYMMDD);
        String systemTime = DateUtil.format(systemDateDao.getSystemDate(), "HH:mm");
        
        // 募集入力終了時刻の取得(SQL005を実行)
        DataList<IfaSubscriptInputConfirmSql005ResponseModel> selSql005Res = dao.selectIfaSubscriptInputConfirmSql005();
        String subscriptInputEndTime = selSql005Res.getDataList().get(0).getName();
        
        // 募集期間（To）＝システム日付 かつ　SQL005.NAME<=システム時刻の場合：エラーメッセージを返す        
        if (StringUtils.equals(systemDate, bbPeriodTo) && StringUtils.compare(systemTime, subscriptInputEndTime) >= 0) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_ORDER_LAST_TIME,
                    IfaCommonUtil.getMessage(ERRORS_ORDER_LAST_TIME, new String[] { subscriptInputEndTime }));
            return dtoRes;
        }
        return null;
    }
    
    /**
     * 数量のチェック<br/>
     * 数量が売買単位外の場合エラーを返す<br/>
     * 数量が当選株数を超える場合エラーを返す<br/>
     * 数量が配分上限株数を超える場合エラーを返す<br/>
     *
     * @param <T> レスポンスタイプ
     * @param quantity 数量
     * @param bbQuantityAlloc 当選株数
     * @param maxAllocation 配分上限株数
     * @param unit 売買単位
     * @param sellBuyUnitType 売買単位区分(エラーメッセージに使用する)
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     */
    private <T> DataList<T> checkQuantity(String quantity, String bbQuantityAlloc, String maxAllocation, String unit,
            String sellBuyUnitType) {
        
        BigDecimal quantityNum = new BigDecimal(quantity);
        
        // 数量が売買単位外の場合エラー
        if (quantityNum.remainder(new BigDecimal(unit)) != BigDecimal.ZERO) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_UNIT_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_UNIT_CHECK, new String[] { MSG_UNIT, unit, sellBuyUnitType }));
            
            return dtoRes;
        }
        
        // 数量が当選株数を超える場合エラー
        if (new BigDecimal(bbQuantityAlloc).compareTo(quantityNum) < 0) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_COUNT_UNIT_CHECK, IfaCommonUtil.getMessage(ERRORS_COUNT_UNIT_CHECK));
            
            return dtoRes;
        }
        
        // 数量が配分上限株数を超える場合エラー
        if (!ObjectUtils.isEmpty(maxAllocation) && new BigDecimal(maxAllocation).compareTo(quantityNum) < 0) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_IPO_TRADE_RANGE, IfaCommonUtil.getMessage(ERRORS_IPO_TRADE_RANGE,
                            new String[] { MSG_UNIT, MSG_MAX_ALLOCATION, MSG_MAX_ALLOCATION, maxAllocation }));
            
            return dtoRes;
        }
        
        return null;
    }
    
    /**
     * 口座の取引制限チェック
     *
     * @param <T> レスポンスタイプ
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param noteInfoCheckbox 注意情報アラート確認
     * @param noteLimitCheck お知らせアラート確認
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     * @exception Exception FCT021の実行で発生するException
     */
    private <T> DataList<T> checkAccountTradeRestriction(String butenCode, String accountNumber,
            String noteInfoCheckbox, String noteLimitCheck) throws Exception {
        
        // FCT021のリクエストDTOを作成
        InputFct021Dto fct021InputDto = new InputFct021Dto();
        fct021InputDto.setButenCode(butenCode);
        fct021InputDto.setAccountNumber(accountNumber);
        fct021InputDto.setProductCd(SECURITY_MONEY_CLASS_01);
        fct021InputDto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_A);
        fct021InputDto.setCountryCd(COUNTRY_CODE_BB_APPLY_LIST);
        fct021InputDto.setCurrencyCode(CURRENCY_CODE_BB_APPLY_LIST);
        
        // FCT021の実行
        OutputFct021Dto fct021OutputDto = fct021.doCheck(fct021InputDto);
        
        // 注意情報エラー有無="1"（あり）：エラーメッセージを返す
        if (NOTICE_ERROR_EXISTS.equals(fct021OutputDto.getNoteInfoErrFlag())) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CMN_NOTICE_ERROR_CHECK,
                    IfaCommonUtil.getMessage(ERRORS_CMN_NOTICE_ERROR_CHECK, new String[] { codeListService
                            .getValue(CODE_LIST_MSG_DISPLAY_TARGET_TRADE, CODE_LIST_KEY, CODE_LIST_DISP_PATTERN) }));
            
            return dtoRes;
        }
        
        // お知らせエラー有無="1"（あり）：エラーメッセージを返す
        if (INFORMATION_ERROR_EXISTS.equals(fct021OutputDto.getNoteLimitErrFlag())) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    WARNING_INFORMATION_CHECK, IfaCommonUtil.getMessage(WARNING_INFORMATION_CHECK, new String[] {}));
            
            return dtoRes;
        }
        
        // 注意情報アラート有無="1"（あり）：
        // 注意情報アラート確認チェックボックス=OFF　または　非表示：エラーを返す。
        if (NOTICE_WARNING_EXISTS.equals(fct021OutputDto.getNoteInfoAlertFlag())) {
            if (CHECK_BOX_FLG_OFF.equals(noteInfoCheckbox) || noteInfoCheckbox == null || noteInfoCheckbox == "") {
                DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                        ERRORS_INFORMATION_CHECK, IfaCommonUtil.getMessage(ERRORS_INFORMATION_CHECK, new String[] {}));
                
                return dtoRes;
            }
        }
        
        // お知らせアラート有無="1"（あり）：
        // お知らせアラート確認チェックボックス=OFF　または　非表示：エラーを返す。
        if (INFORMATION_WARNING_EXISTS.equals(fct021OutputDto.getNoteLimitAlertFlag())) {
            if (CHECK_BOX_FLG_OFF.equals(noteLimitCheck) || noteLimitCheck == null || noteLimitCheck == "") {
                DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                        ERRORS_CMN_INFORMATION_OCCURS,
                        IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS, new String[] {}));
                
                return dtoRes;
            }
        }
        return null;
    }
    
    /**
     * 預り区分のチェック
     *
     * @param <T> レスポンスタイプ
     * @param depositType 預り区分
     * @param customerCode 顧客コード
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     * @exception Exception SQL006エラーの場合
     */
    private <T> DataList<T> checkDepositType(String depositType, String customerCode) throws Exception {
        
        // SQL007のリクエストを作成
        IfaSubscriptInputConfirmSql007RequestModel selSql007Req = new IfaSubscriptInputConfirmSql007RequestModel();
        selSql007Req.setCustomerCode(customerCode);
        
        // SQL007を実行
        DataList<IfaSubscriptInputConfirmSql007ResponseModel> selSql007Res = dao
                .selectIfaSubscriptInputConfirmSql007(selSql007Req);
        
        // sql007.特定口座区分
        String tokuteiKouzaKbn = selSql007Res.getDataList().get(0).getTokuteiKouzaKbn();
        // sql007.契約区分
        String isaContractType = selSql007Res.getDataList().get(0).getIsaContractType();
        // sql007.買付可能判定区分
        String isaBuyable = selSql007Res.getDataList().get(0).getIsaBuyAbleJudgeClassificationYear();
        
        // 特定口座未開設（SQL007.特定口座区分="1","2"以外）で預り区分="2"（特定）の場合
        // ：エラーメッセージを返す
        if (!SpecificAccount.SPECIFIC_PAYMENT_PROXY.getId().equals(tokuteiKouzaKbn)
                && !SpecificAccount.SPECIFIC_FINAL_TAX.getId().equals(tokuteiKouzaKbn)
                && DEPOSIT_TYPE_SPECIFIC.equals(depositType)) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_SPECIFIC_ACCOUNT_CHECK, IfaCommonUtil.getMessage(ERRORS_SPECIFIC_ACCOUNT_CHECK));
            
            return dtoRes;
        }
        
        // NISA未開設（SQL007.ISA契約区分="1"以外）で預り区分="4"または"H"（NISA）の場合
        // ：エラーメッセージを返す
        if (!ISA_CONTRACT_TYPE_CONTRACT.equals(isaContractType) && (DEPOSIT_TYPE_NISA_OLD.equals(depositType)
                || DEPOSIT_TYPE_NISA_GROWTH_INVESTMENT.equals(depositType))) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_NISA_ACCOUNT_CHECK, IfaCommonUtil.getMessage(ERRORS_NISA_ACCOUNT_CHECK));
            
            return dtoRes;
        }
        
        // NISA口座が買付不可（SQL007.ISA買付可能判定区分（当年）="1","2"以外）で預り区分="4"または"H"（NISA）の場合
        // ：エラーメッセージを返す
        if (!ISA_BUY_ABLE_JUDGE_CLASSIFICATION_YEAR_POSSIBLE_NISA.equals(isaBuyable)
                && !ISA_BUY_ABLE_JUDGE_CLASSIFICATION_YEAR_POSSIBLE_TSUMITATE_NISA.equals(isaBuyable)
                && (DEPOSIT_TYPE_NISA_OLD.equals(depositType)
                        || DEPOSIT_TYPE_NISA_GROWTH_INVESTMENT.equals(depositType))) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_NISA_ACCOUNT_BUY_CHECK, IfaCommonUtil.getMessage(ERRORS_NISA_ACCOUNT_BUY_CHECK));
            
            return dtoRes;
        }
        return null;
    }
    
    /**
     * 最重要事項の説明チェック
     *
     * @param <T> レスポンスタイプ
     * @param customerCode 顧客コード
     * @param importantMatterType 重要事項の説明
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     * @throws Exception SQL008実行時の例外
     */
    private <T> DataList<T> checkExplainImportantImformation(String customerCode, String importantMatterType) throws Exception {
        
        // SQL008のリクエストを作成
        IfaSubscriptInputConfirmSql008RequestModel selSql008Req = new IfaSubscriptInputConfirmSql008RequestModel();
        selSql008Req.setCustomerCode(customerCode);
        
        // SQL008を実行
        DataList<IfaSubscriptInputConfirmSql008ResponseModel> selSql008Res = dao
                .selectIfaSubscriptInputConfirmSql008(selSql008Req);
        
        // リクエスト．重要事項の説明＝1（説明不要を確認（属性登録済））かつ、リスク説明未1のデータがある場合、エラーを返す
        if (IMPORTANT_MATTERS_EXPLAIN_NO_EXPLAIN_CONFIRM.equals(importantMatterType) && Integer.parseInt(selSql008Res.getDataList().get(0).getRowCount()) > 0) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_EXPLAIN_IMPORTANT_INFORMATION,
                    IfaCommonUtil.getMessage(ERRORS_EXPLAIN_IMPORTANT_INFORMATION));
            
            return dtoRes;
        }
        
        return null;
    }
    
    /**
     * コンプラランクのチェック
     *
     * @param <T> レスポンスタイプ
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param brandCode 銘柄コード
     * @param kanyuKbn 勧誘区分
     * @param jutyuKbn 受注方法
     * @param complianceCheckMsg コンプラランクチェック.チェックボックス文言
     * @param invitationCheck コンプラランクチェック確認
     * @return エラーなし：null, アラートあり：アラートを格納したDataList, エラーあり: レスポンス用DataList
     * @exception Exception システムエラー
     */
    private <T> DataList<T> checkComplianceRank(String butenCode, String accountNumber, String brandCode,
            String kanyuKbn, String jutyuKbn, String complianceCheckMsg, String invitationCheck) throws Exception {
        
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
        DataList<T> dtoRes;
        switch (fct006OutputDto.getJudgementResult()) {
        
            // FCT006.判定結果=0：ノーマル：次の処理へ
            case COMPLA_CHECK_JUDGMENT_RESULT_NORMAL:
                return null;
        
            // FCT006.判定結果=1：アラート
            case COMPLA_CHECK_JUDGMENT_RESULT_ALERT:
                
                // FTC006.チェックボックス文言 = コンプラランクチェック.チェックボックス文言 かつ コンプラランクチェック確認 = "0"(OFF)
                // または　FTC006.チェックボックス文言 ≠ コンプラランクチェック.チェックボックス文言
                // の時エラー
                if ((StringUtils.equals(fct006OutputDto.getChkBoxLabel(), complianceCheckMsg)
                        && CHECK_BOX_FLG_OFF.equals(invitationCheck))
                        || !StringUtils.equals(fct006OutputDto.getChkBoxLabel(), complianceCheckMsg)) {
                    
                    dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                            ERRORS_CMN_INFORMATION_OCCURS, IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                    
                    return dtoRes;
                }
            
                return null;
        
            // FCT006.判定結果=2：エラー：エラーメッセージを返す
            case COMPLA_CHECK_JUDGMENT_RESULT_ERROR:
                
                dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, fct006OutputDto.getMessageId(),
                        IfaCommonUtil.getMessage(fct006OutputDto.getMessageId()));
                
                return dtoRes;
            
            // FCT006.判定結果=上記以外：エラーメッセージを返す
            default:
                
                dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, fct006OutputDto.getMessageId(),
                        IfaCommonUtil.getMessage(fct006OutputDto.getMessageId()));
                
                return dtoRes;
        
        }
    }
    
    /**
     * 約定金額のチェック<br/>
     * API001 NRI_QueryAccountBalance の呼び出し
     *
     * @param <T> レスポンスタイプ
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param depositType 預り区分
     * @param contractAmount 約定金額
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     * @exception Exception API001実行時の例外
     */
    private <T> DataList<T> checkContractAmount(String butenCode, String accountNumber, String depositType,
            String contractAmount, ApiErrorUtil apiErrorUtil) throws Exception {
        // API001のリクエストを作成
        QueryAccountBalanceInData api001InData = new QueryAccountBalanceInData();
        api001InData.setButenCd(butenCode);
        api001InData.setKozaNo(String.format("%7s", accountNumber).replace(' ', '0'));
        
        QueryAccountBalanceIn api001Input = new QueryAccountBalanceIn();
        api001Input.setIndata(api001InData);
        
        // API001を呼び出し
        QueryAccountBalanceOutData api001OutData = new QueryAccountBalanceOutData();

        api001OutData = apiWrapper.queryAccountBalance(api001Input);
        apiErrorUtil.checkApiResponse(api001OutData.getShubetu(), api001OutData.getCode(), api001OutData.getMessage());
        if (apiErrorUtil.isFatal()) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_NRI_QUERY_ACCOUNT_BALANCE, IfaCommonUtil.getMessage(ERRORS_NRI_QUERY_ACCOUNT_BALANCE));
            
            return dtoRes;
        }

        
        // 約定金額 > API001.買付余力の場合エラーを返す
        if (new BigDecimal(contractAmount)
                .compareTo(new BigDecimal(api001OutData.getT0().getBuyingPowerTotal().trim())) > 0) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_BUY_LIMIT_CHECK, IfaCommonUtil.getMessage(ERRORS_BUY_LIMIT_CHECK));
            
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
                if (!ObjectUtils.isEmpty(api001OutData.getIsaBuyLimit())) {
                    // 旧NISA口座
                    nisaBuyLimit = new BigDecimal(api001OutData.getIsaBuyLimit());
                    
                } else {
                    // NISA口座(成長投資枠)総合NISA(成長投資枠）買付可能枠(当年)
                    nisaBuyLimit = new BigDecimal(api001OutData.getIsaSeityoBuyLimit());
                }
                
            }
            
            // 約定金額 > NISA買付可能額の場合エラーを返す
            if (new BigDecimal(contractAmount).compareTo(nisaBuyLimit) > 0) {
                DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                        ERRORS_EXCEEDED_MAXIMUM,
                        IfaCommonUtil.getMessage(ERRORS_EXCEEDED_MAXIMUM, new String[] { MSG_NISA_BUY_LIMIT }));
                
                return dtoRes;
            }
        }
        
        return null;
    }
    
    /**
     * 備考のチェック
     *
     * @param <T> レスポンスタイプ
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param bbRemark 備考
     * @param bbRemark2 訂正前_備考
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     * @throws Exception SQL009実行時の例外
     */
    private <T> DataList<T> checkBbRemark(String butenCode, String accountNumber, String bbRemark, String bbRemark2)
            throws Exception {
        
        // 備考を変更する場合
        if (!StringUtils.equals(bbRemark, bbRemark2)) {
            // SQL009のリクエストを作成
            IfaSubscriptInputConfirmSql009RequestModel selSql009Req = new IfaSubscriptInputConfirmSql009RequestModel();
            selSql009Req.setButenCode(butenCode);
            selSql009Req.setAccountNumber(accountNumber);
            
            // SQL009を実行
            DataList<IfaSubscriptInputConfirmSql009ResponseModel> selSql009Res = dao
                    .selectIfaSubscriptInputConfirmSql009(selSql009Req);
            
            // セクション関連情報が存在しない場合エラーを返す
            if (selSql009Res.getDataList().size() == 0 || selSql009Res.get(0) == null) {
                DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                        ERRORS_SECTION_ID,
                        IfaCommonUtil.getMessage(ERRORS_SECTION_ID, new String[] { MSG_SECTION_ID }));
                
                return dtoRes;
            }
        }
        return null;
    }
    
    /**
     * 目論見書閲覧日時のチェック
     *
     * @param <T> レスポンスタイプ
     * @param readTime 目論見書閲覧日時
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     * @exception Exception システムエラー
     */
    private <T> DataList<T> checkReadTime(String systemDateTime, String readTime) throws Exception {
        
        // システム日時(確認ボタンを押した日時) < 目論見書閲覧日時の場合エラーを返す
        if (ObjectUtils.isEmpty(readTime)) {
            return null;
        }
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        if (LocalDateTime.parse(readTime, dateFormat).isAfter(LocalDateTime.parse(systemDateTime, dateFormat))) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_FUTURE_TIME_CHECK, IfaCommonUtil.getMessage(ERRORS_FUTURE_TIME_CHECK));
            
            return dtoRes;
        }
        
        return null;
    }
    
    /**
     * 抽選結果に対する当選株数の紐付けチェック
     *
     * @param <T> レスポンスタイプ
     * @param lotteryResult 抽選結果
     * @param bbQuantityAlloc 当選株数
     * @param unit 売買単位
     * @param sellBuyUnitType 売買単位区分
     * @return エラーなし：null, エラーあり: レスポンス用DataList
     */
    private <T> DataList<T> checkLotteryResult(String lotteryResult, String bbQuantityAlloc, String unit,
            String sellBuyUnitType) {
        
        // ・抽選結果が「当選："1"／当選(繰上)："3"／裁量："4"」　のとき
        if (LOTTERY_RESULT_WINNING.equals(lotteryResult) || LOTTERY_RESULT_ALTERNATE.equals(lotteryResult)
                || LOTTERY_RESULT_DISCRETIONARY.equals(lotteryResult)) {
            
            // 当選株数が未入力の場合：エラーメッセージを返す
            if (bbQuantityAlloc == null || bbQuantityAlloc == "") {
                
                DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                        ERRORS_BB_QUANTITY_ALLOC_CHECK_INPUT,
                        IfaCommonUtil.getMessage(ERRORS_BB_QUANTITY_ALLOC_CHECK_INPUT));
                
                return dtoRes;
                
                // 当選株数が0の場合：エラーメッセージを返す
            }
            if (bbQuantityAlloc.equals("0")) {
                
                DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                        ERRORS_NUMBER_INSERT_CHECK,
                        IfaCommonUtil.getMessage(ERRORS_NUMBER_INSERT_CHECK, new String[] { MSG_BB_QUANTITY_ALLOC }));
                
                return dtoRes;
                
                // 当選株数が売買単位外の場合：エラーメッセージを返す
            }
            if (new BigDecimal(bbQuantityAlloc).remainder(new BigDecimal(unit)) != BigDecimal.ZERO) {
                
                DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                        ERRORS_UNIT_CHECK, IfaCommonUtil.getMessage(ERRORS_UNIT_CHECK,
                                new String[] { MSG_BB_QUANTITY_ALLOC, unit, sellBuyUnitType }));
                
                return dtoRes;
            }
            
            // ・抽選結果が「申込取消："5"」　のとき
        } else if (LOTTERY_RESULT_CANCEL.equals(lotteryResult)) {
            
            // 当選株数が未入力の場合：エラーメッセージを返す
            if (bbQuantityAlloc == null || bbQuantityAlloc == "") {
                
                DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_REQUIRED,
                        IfaCommonUtil.getMessage(ERRORS_REQUIRED, new String[] { MSG_BB_QUANTITY_ALLOC }));
                
                return dtoRes;
            }
            
            // 抽選結果が上記以外のとき
        } else {
            // 当選株数が0より大きい場合：エラーメッセージを返す
            if (StringUtil.parseBigDecimal(bbQuantityAlloc).compareTo(StringUtil.parseBigDecimal("0")) > 0) {
                
                DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                        ERRORS_BB_QUANTITY_ALLOC_CHECK, IfaCommonUtil.getMessage(ERRORS_BB_QUANTITY_ALLOC_CHECK));
                
                return dtoRes;
            }
        }
        
        return null;
        
    }
    
    /**
     * 募集取消レコードの日跨ぎ状態を判定する
     *
     * @param recruitmentOrderDate 募集受注日時
     * @return TRUE:日跨ぎ状態、FALSE:日跨ぎ状態ではない
     * @throws Exception FCT007,FCT033実行時の例外
     */
    private Boolean checkRecruitmentOrderDate(String recruitmentOrderDate) throws Exception {
        
        // 募集受注日時について、直近の営業日を取得
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dateTypeRecruitmentOrderDate = sdf.parse(recruitmentOrderDate);
        String lastBusinessDayFromRecruitmentOrderDate = etintraServiceImpL
                .getLastBusinessDay(dateTypeRecruitmentOrderDate);
        
        // システム日付について、直近の営業日を取得
        Date systemDate = systemDateDao.getSystemDate();
        String lastBusinessDayFromSystemDate = etintraServiceImpL.getLastBusinessDay(systemDate);
        
        // 募集受注日時以降の直近営業日＜システム日付以降の直近営業日の場合：日跨ぎ(過日)と判断
        if (Integer.parseInt(lastBusinessDayFromRecruitmentOrderDate) < Integer.parseInt(lastBusinessDayFromSystemDate)) {
            return true;
        }
        
        return false;
    }
    
}
