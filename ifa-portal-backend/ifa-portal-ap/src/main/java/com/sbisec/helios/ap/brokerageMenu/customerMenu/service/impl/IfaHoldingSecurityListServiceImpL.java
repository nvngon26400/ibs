package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.account.ListSecuritiesBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.dto.SecuritiesBalances;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct017;
import com.sbisec.helios.ap.bizcommon.component.Fct020;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct017Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct020Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto.MediatePropriety;
import com.sbisec.helios.ap.bizcommon.model.OutputFct017Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct020Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaHoldingSecurityListDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql011ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA012RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA012ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA013RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA013ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA019RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA019ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListResponseDtoDepositDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListResponseDtoDomesticBonds;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListResponseDtoDomesticBondsDepositDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListResponseDtoDomesticStock;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListResponseDtoDomesticStockDepositDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListResponseDtoForeignBonds;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListResponseDtoForeignCurrencyMmf;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListResponseDtoForeignCurrencyMmfDepositDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListResponseDtoForeignStocks;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListResponseDtoInvestmentTrust;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListResponseDtoOtherSecurity;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListResponseDtoSecurityToken;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaHoldingSecurityListService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignSecurityTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundFundDocReadHistoryBulkRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundFundDocReadHistoryBulkReq;
import com.sbisec.helios.ap.safe.service.SafeCommonService;
import com.sbisec.helios.ap.safe.service.SafeFundTradeService;
import com.sbisec.helios.ap.safe.utils.SafeApiUtil;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulk;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulkApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulkItem;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import jp.co.sbisec.pcenter.dto.yanap.AccountSumWebData;
import jp.co.sbisec.pcenter.dto.yanap.NextKeyInfoIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountPositionSumWebInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountPositionSumWebOutData;
import jp.co.sbisec.pcenter.dto.yanap.QuerySaleLimitIn;
import jp.co.sbisec.pcenter.dto.yanap.QuerySaleLimitInData;
import jp.co.sbisec.pcenter.dto.yanap.QuerySaleLimitOutData;

/**
 * 画面ID：SUB0202_010201-01
 * 画面名：保有商品一覧
 *
 * @author SCSK
 *
 *     2023/09/21 新規作成
 */
@Component(value = "cmpIfaHoldingSecurityListService")
public class IfaHoldingSecurityListServiceImpL implements IfaHoldingSecurityListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaHoldingSecurityListServiceImpL.class);
    
    /** 選択商品：国内株式 */
    private static final String SELECT_SEC_TYPE_DOMESTICSTOCK = "国内株式";
    
    /** 選択商品：国内投信 */
    private static final String SELECT_SEC_TYPE_INVESTMENTTRUST = "投資信託";
    
    /** 選択商品：国内債券 */
    private static final String SELECT_SEC_TYPE_DOMESTICBONDS = "国内債券";
    
    /** 選択商品：外国債券 */
    private static final String SELECT_SEC_TYPE_FOREIGNBONDS = "外国債券";
    
    /** 選択商品：外国株式 */
    private static final String SELECT_SEC_TYPE_FOREIGNSTOCKS = "外国株式";
    
    /** 選択商品：外貨建MMF */
    private static final String SELECT_SEC_TYPE_FOREIGNCURRENCYMMF = "外貨建MMF";
    
    /** 選択商品：全て*/
    private static final String SELECT_SEC_TYPE_ALL = "全て";
    
    /** 正常終了値 */
    public static final String RETURN_CODE_SUCCESS = "";
    public static final String RETURN_CODE_SUCCESSFULL = "0";
    
    /** 正常終了メッセージ */
    public static final String RETURN_MESSEAGE_SUCCESS = "";
    
    /** 権限チェックエラー値 */
    public static final String AUTH_ERROR_VALUE = "0";
    
    /** 権限チェックエラー   */
    public static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 顧客共通情報.特定口座区分   特定口座(代行納付)   */
    public static final String SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT = "1";
    
    /** 顧客共通情報.特定口座区分   特定口座(確定申告)   */
    public static final String SPECIFIC_ACCOUNT_TYPE_TAX_RETURN = "2";
    
    /** 顧客共通情報.ジュニアISA契約区分   ジュニアNISA口座開設済み口座判定   */
    public static final String JR_ISA_CONTRACT_TYPE_OPEN = "1";
    
    /** FCT003.媒介可否リスト.証券金銭種別 国内株式 判定   */
    public static final String FCT003_SECURITY_MONEY_CLASS_DOMESTIC_STOCK = "01";
    
    /** FCT003.媒介可否リスト.証券金銭種別 外国株式 判定   */
    public static final String FCT003_SECURITY_MONEY_CLASS_FOREIGN_STOCK = "15";
    
    /** FCT003.媒介可否リスト.証券金銭種別 国内投信 判定   */
    public static final String FCT003_SECURITY_MONEY_CLASS_INVESTMENT_TRUST = "06";
    
    /** FCT003.媒介可否リスト.取引種別 購入（一般）   */
    public static final String FCT003_TRADE_CLASS_BUY = "0";

    /** FCT003.媒介可否リスト.取引種別 購入（積立）   */
    public static final String FCT003_TRADE_CLASS_BUY_ACCUMULATION = "2";
    
    /** FCT003.媒介可否リスト.取引種別 現物買付   */
    public static final String FCT003_TRADE_CLASS_BUYING = "1";
    
    /** FCT003.媒介可否リスト.取引種別 現物売却   */
    public static final String FCT003_TRADE_CLASS_SELLING = "2";
    
    /** FCT003.媒介可否リスト.取引種別 解約（一般）   */
    public static final String FCT003_TRADE_CLASS_CANCEL_GEN = "7";
    
    /** FCT003.媒介可否リスト.取引種別 解約（累投）   */
    public static final String FCT003_TRADE_CLASS_CANCEL_TOTAL = "8";
    
    /** FCT003.媒介可否リスト.媒介可否 媒介可   */
    public static final String FCT003_MEDIATE_PROPRIETY_AVAILABLE = "1";
    
    /** FCT017.書類コード.受入要否 必要判定   */
    public static final String FCT017_ACCEPTANCE_NECESSITY_AVAILABLE = "1";
    
    /** API001.商品タイプ名 国内株式 判定値 */
    private static final String API001_SEC_TYPE_NAME_DOMESTIC_STOCK = "国内株式";
    
    /** API001.商品タイプ 外国株式 判定値 */
    private static final String API001_SEC_TYPE_NAME_FOREIGN_STOCK = "外国株式";
    
    /** API001.商品タイプ 外国投信 判定値 */
    private static final String API001_SEC_TYPE_NAME_FOREIGN_INVESTMENT_TRUST = "外国投信";
    
    /** API001.商品タイプ名 国内投信 判定値 */
    private static final String API001_SEC_TYPE_NAME_INVESTMENT_TRUST = "国内投信";
    
    /** API001.商品タイプ名 その他商品 判定値 */
    private static final Pattern API001_SEC_TYPE_NAME_OTHER_SECURITY = Pattern.compile("外国投信|外国株式");
    
    /** API001.商品区分 国内株式 判定値 */
    private static final String API001_SEC_ID_DOMESTIC_STOCK = "K";
    
    /** API001.商品区分 投信(国内(一般型)/外国) 判定値 */
    private static final String API001_SEC_ID_INVESTMENT_TRUST = "T";
    
    /** API001.商品区分 国内投信(汎用累投) 判定値 */
    private static final String API001_SEC_ID_INVESTMENT_TRUST_DOMESTIC = "Y";
    
    /** API001.商品区分 外国債券 判定値 */
    private static final String API001_SEC_ID_FOREIGN_BONDS = "S";
    
    /** API001.利払区分 割引債 判定値 */
    private static final String API001_COUPON_PAY_ID_DISCOUNT = "0";
    
    /** API001.利払区分 年１回 判定値 */
    private static final String API001_COUPON_PAY_ID_YEARLY = "1";
    
    /** API001.利払区分 年2回 判定値 */
    private static final String API001_COUPON_PAY_ID_TWICE = "2";
    
    /** API001.利払区分 年4回 判定値 */
    private static final String API001_COUPON_PAY_ID_FOUR = "3";
    
    /** API001.利払区分 年12回 判定値 */
    private static final String API001_COUPON_PAY_ID_TWELVE = "4";
    
    /** API001.利払月日 末日 判定値 */
    private static final String API001_COUPON_PAY_DATE_LAST_DAY = "99";
    
    /** API001.預り区分 国内債券:マル優明細が存在（特優が混在しているも） 判定値 */
    private static final String API001_POSITINO_ID_DMST_BND_ALL_PREFERENTIAL_TREATMENT = "027";
    
    /** API001.預り区分 国内債券:特優明細のみ */
    private static final String API001_POSITINO_ID_DMST_BND_PREFERENTIAL_TREATMENT = "028";
    
    /** API001.商品検索ｺｰﾄﾞ（大分類） 国内金融商品 判定値 */
    private static final String API001_BRAND_SEARCH_CODE1_DOMESTIC_FINANCE = "C";
    
    /** API001.商品検索ｺｰﾄﾞ（大分類） 外国金融商品 判定値 */
    private static final String API001_BRAND_SEARCH_CODE1_FOREIGN_FINANCE = "J";
    
    /** API001.商品検索ｺｰﾄﾞ(大分類) 国内債券 判定値 */
    private static final String API001_BRAND_SEARCH_CODE1_DOMESTIC_BONDS = "B";
    
    /** API001.商品検索ｺｰﾄﾞ(大分類) 外国債券 判定値 */
    private static final String API001_BRAND_SEARCH_CODE1_FOREIGN_BONDS = "I";
    
    /** API001.商品検索ｺｰﾄﾞ(大分類) その他商品 判定値1 */
    private static final Pattern API001_BRAND_SEARCH_CODE1_OTHER_SECURITY_1 = Pattern.compile("[CJ]");
    
    /** API001.商品検索ｺｰﾄﾞ(大分類) その他商品 判定値1 */
    private static final String API001_BRAND_SEARCH_CODE1_OTHER_SECURITY_2 = "B";
    
    /** API001.商品検索ｺｰﾄﾞ(大分類) その他商品 判定値1 */
    private static final String API001_BRAND_SEARCH_CODE1_OTHER_SECURITY_3 = "I";
    
    /** API001.商品検索ｺｰﾄﾞ(大分類) 外国投信 判定値 */
    private static final String API001_BRAND_SEARCH_CODE1_FOREIGN_INVESTMENT_TRUST = "K";
    
    /** API001.商品検索ｺｰﾄﾞ(中分類) 国内債券 判定値 */
    private static final String API001_BRAND_SEARCH_CODE2_DOMESTIC_BONDS = "B04";
    
    /** API001.商品検索ｺｰﾄﾞ(中分類) その他商品 判定値 */
    private static final String API001_BRAND_SEARCH_CODE2_OTHER_SECURITY = "B04";
    
    /** API001.商品検索ｺｰﾄﾞ(小分類) 外国債券 判定値 */
    private static final String API001_BRAND_SEARCH_CODE3_FOREIGN_BONDS = "I0118";
    
    /** API001.商品検索ｺｰﾄﾞ(小分類) その他商品 判定値 */
    private static final String API001_BRAND_SEARCH_CODE3_OTHER_SECURITY = "I0118";
    
    /** API001.商品検索ｺｰﾄﾞ(小分類) 'C0101'(国内金融商品(CP) 判定値 */
    private static final String API001_BRAND_SEARCH_CODE3_DOMESTIC_C0101 = "C0101";
    
    /** API001.商品検索ｺｰﾄﾞ(小分類) 'J0101'(外国金融商品(CP) 判定値 */
    private static final String API001_BRAND_SEARCH_CODE3_FOREIGN_J0101 = "J0101";
    
    /** API001.商品検索ｺｰﾄﾞ(小分類) 'C0102'(国内金融商品(CP) 判定値 */
    private static final String API001_BRAND_SEARCH_CODE3_DOMESTIC_C0102 = "C0102";
    
    /** API001.商品検索ｺｰﾄﾞ(小分類) 'J0102'(外国金融商品(CP) 判定値 */
    private static final String API001_BRAND_SEARCH_CODE3_FOREIGN_J0102 = "J0102";
    
    /** API001.商品検索ｺｰﾄﾞ(小分類) 'C0201'(国内金融商品(抵当証券) 判定値 */
    private static final String API001_BRAND_SEARCH_CODE3_DOMESTIC_C0201 = "C0201";
    
    /** API001.非特定預り区分 特定 判定値 */
    private static final String API001_HITOKUTEI_KBN_SPECIFIC = "0";
    
    /** API001.非特定預り区分 NISA預り 判定値 */
    private static final String API001_HITOKUTEI_KBN_NISA = "4";
    
    /** API001.非特定預り区分 特定(特例) 判定値 */
    private static final String API001_HITOKUTEI_KBN_EX_SPECIFIC = "5";
    
    /** API001.非特定預り区分 一般(特例) 判定値 */
    private static final String API001_HITOKUTEI_KBN_EX_GENERALLY = "6";
    
    /** API001.非特定預り区分 JrNISA 判定値 */
    private static final String API001_HITOKUTEI_KBN_JR_NISA = "7";
    
    /** API001.非特定預り区分 つみたてNISA預り 判定値 */
    private static final String API001_HITOKUTEI_KBN_ACCUMULATION_NISA = "8";
    
    /** API001.非特定預り区分 総合NISA(成長投資枠) 判定値 */
    private static final String API001_HITOKUTEI_KBN_TOTAL_NISA = "H";
    
    /** API001.非特定預り区分 総合NISA(つみたて投資枠) 判定値 */
    private static final String API001_HITOKUTEI_KBN_TOTAL_ACCUMULATION_NISA = "I";
    
    /** API001.非特定預り区分 継続管理勘定 判定値 */
    private static final String API001_HITOKUTEI_KBN_CONTINUATION_MANAGE = "J";
    
    /** API001.再投資停止情報 分配金受取 判定値 */
    private static final String API001_REINVEST_RECEIPT = "1";
    
    /** API001.再投資停止情報 分配金再投資 判定値 */
    private static final String API001_REINVEST_INVESTMENT = "2";
    
    /** API001.通貨コード 円 判定値 */
    private static final String API001_ISSUED_CCY_CODE_JPY = "JPY";
    
    /** API001.商品区分 債券(国内/外国) 判定値 */
    private static final String API001_SEC_ID_BONDS = "S";
    
    /** API004.銘柄情報.商品コード 外国株式 判定値 */
    private static final String API004_PRODUCT_CODE_FOREIGN_STOCK = "FOREIGN_STOCK";
    
    /** API004.銘柄情報.商品コード 外貨建MMF 判定値 */
    private static final String API004_PRODUCT_CODE_FOREIGN_MMF = "FOREIGN_MMF";

    /** API004.銘柄情報.商品コード 外国債券（外貨建） 判定値 */
    private static final String API004_PRODUCT_CODE_FOREIGN_BOND = "FOREIGN_BOND";

    /** API004.銘柄情報.商品コード 外国株式 内部コード */
    private static final String API004_PRODUCT_CODE_FOREIGN_STOCK_KEY = "0";
    
    /** API004.銘柄情報.商品コード 外貨建MMF 内部コード */
    private static final String API004_PRODUCT_CODE_FOREIGN_MMF_KEY = "1";

        /** API004.銘柄情報.商品コード 外国債券（外貨建） 内部コード */
    private static final String API004_PRODUCT_CODE_FOREIGN_BOND_KEY = "2";
    
    /** API004.預り区分 特定 判定値 */
    private static final String API004_SPECIFIC_ACCOUNT_CODE_SPECIFIC = "2";
    
    /** API004.預り区分 一般 判定値 */
    private static final String API004_SPECIFIC_ACCOUNT_CODE_GENERAL = "1";
    
    /** API004.預り区分 成長投資枠 判定値 */
    private static final String API004_SPECIFIC_ACCOUNT_CODE_GROWTH_INVESTMENT = "H";
    
    /** API004.預り区分 NISA 判定値 */
    private static final String API004_SPECIFIC_ACCOUNT_CODE_NISA = "4";
    
    /** API004.預り区分 ジュニアNISA特定 判定値 */
    private static final String API004_SPECIFIC_ACCOUNT_CODE_JR_SPECIFIC = "6";
    
    /** API004.預り区分 ジュニアNISA一般 判定値 */
    private static final String API004_SPECIFIC_ACCOUNT_CODE_JR_GENERAL = "5";
    
    /** API004.預り区分 ジュニアNISA NISA預り（継続管理勘定） 判定値 */
    private static final String API004_SPECIFIC_ACCOUNT_CODE_JR_CONTINUOUS_MANAGEMENT = "J";
    
    /** API004.預り区分 ジュニアNISA NISA     判定値 */
    private static final String API004_SPECIFIC_ACCOUNT_CODE_JR_NISA = "7";

    /** API008.     投信目論見書交付チェックエラー*/
    private static final String API008_MESSAGE_NO_ISSURANCE_RECORD = "errors.fnd.selectedBrand.noIssuanceRecord";

    /** API008. エラー*/
    private static final String ERR_NO_INFO = "errors.inputData.notExist";

    /** API008. 接続エラー発生*/
    private static final String API008_ERR_EXTAPI_CONNECTION_FAILED = "errors.extApi.connection.failed";
    
    /** API001.非特定預り区分 その他商品 判定値 */
    private static final Pattern HITOKUTEI_KBN_OTHER_SECURITY = Pattern.compile("[056]");
    
    /** 顧客共通情報.特定口座区分 その他商品 判定値 */
    private static final Pattern SPECIFIC_ACCOUNT_TYPE_OTHER_SECURITY = Pattern.compile("[12]");
    
    private static final String ERRORS_DMS_SELECTEDBRAND_MARKETNOTFOUND = "errors.dms.selectedBrand.marketNotFound";
    
    @Autowired
    private ApiWrapper apiwrapper;
    
    @Autowired
    private ForeignAccountService foreignAccountService;
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct017 fct017;
    
    @Autowired
    private Fct020 fct020;
    
    @Autowired
    private IfaHoldingSecurityListDao dao;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    @Autowired
    private SafeCommonService safeCommonService;
    
    private enum DisplayType {
        
        // 国内株式
        
        /** ジュニアNISA口座開設済み 総合・国内株式・特定 */
        OPEN_TOTAL_DMSTC_STK_SPECIFIC("総合・国内株式・特定", "1", "総合", "国内株式", null, "特定"),
        /** ジュニアNISA口座開設済み 総合・国内株式・一般 */
        OPEN_TOTAL_DMSTC_STK_GENERAL("総合・国内株式・一般", "1", "総合", "国内株式", null, "一般"),
        /** ジュニアNISA口座開設済み 総合・国内株式・NISA預り（成長投資枠） */
        OPEN_TOTAL_DMSTC_STK_NISA("総合・国内株式・NISA預り（成長投資枠）", "1", "総合", "国内株式", null, "NISA預り（成長投資枠）"),
        /** ジュニアNISA口座開設済み 総合・国内株式・旧NISA */
        OPEN_TOTAL_DMSTC_STK_OLD_NISA("総合・国内株式・旧NISA", "1", "総合", "国内株式", null, "旧NISA"),
        /** ジュニアNISA口座開設済み ジュニアNISA・国内株式・特定 */
        OPEN_JRNISA_DMSTC_STK_SPECIFIC("ジュニアNISA・国内株式・特定", "1", "ジュニアNISA", "国内株式", null, "特定"),
        /** ジュニアNISA口座開設済み ジュニアNISA・国内株式・一般 */
        OPEN_JRNISA_DMSTC_STK_GENERAL("ジュニアNISA・国内株式・一般", "1", "ジュニアNISA", "国内株式", null, "一般"),
        /** ジュニアNISA口座開設済み ジュニアNISA・国内株式・NISA預り（継続管理勘定） */
        OPEN_JRNISA_DMSTC_STK_NISA("ジュニアNISA・国内株式・NISA預り（継続管理勘定）", "1", "ジュニアNISA", "国内株式", null, "NISA預り（継続管理勘定）"),
        /** ジュニアNISA口座開設済み ジュニアNISA・国内株式・旧NISA */
        OPEN_JRNISA_DMSTC_STK_OLD_NISA("ジュニアNISA・国内株式・旧NISA", "1", "ジュニアNISA", "国内株式", null, "旧NISA"),
        /** ジュニアNISA口座未開設 ジュニアNISA・国内株式・特定 */
        CLOSE_TOTAL_DMSTC_STK_SPECIFIC("総合・国内株式・特定", "0", "総合", "国内株式", null, "特定"),
        /** ジュニアNISA口座未開設 総合・国内株式・一般 */
        CLOSE_TOTAL_DMSTC_STK_GENERAL("総合・国内株式・一般", "0", "総合", "国内株式", null, "一般"),
        /** ジュニアNISA口座未開設 総合・国内株式・NISA預り（成長投資枠） */
        CLOSE_TOTAL_DMSTC_STK_NISA("総合・国内株式・NISA預り（成長投資枠）", "0", "総合", "国内株式", null, "NISA預り（成長投資枠）"),
        /** ジュニアNISA口座未開設 総合・国内株式・旧NISA */
        CLOSE_TOTAL_DMSTC_STK_OLD_NISA("総合・国内株式・旧NISA", "0", "総合", "国内株式", null, "旧NISA"),
        
        // 投資信託
        
        /** ジュニアNISA口座開設済み 総合・投資信託・口数・特定 */
        OPEN_TOTAL_TNVSTMT_TRST_UNIT_SPECIFIC("総合・投資信託・口数・特定", "1", "総合", "投資信託", "口数", "特定"),
        /** ジュニアNISA口座開設済み 総合・投資信託・口数・一般 */
        OPEN_TOTAL_TNVSTMT_TRST_UNIT_GENERAL("総合・投資信託・口数・一般", "1", "総合", "投資信託", "口数", "一般"),
        /** ジュニアNISA口座開設済み 総合・投資信託・口数・NISA預り（成長投資枠） */
        OPEN_TOTAL_TNVSTMT_TRST_UNIT_NISA("総合・投資信託・口数・NISA預り（成長投資枠）", "1", "総合", "投資信託", "口数", "NISA預り（成長投資枠）"),
        /** ジュニアNISA口座開設済み 総合・投資信託・口数・NISA預り（つみたて投資枠） */
        OPEN_TOTAL_TNVSTMT_TRST_UNIT_EX_NISA("総合・投資信託・口数・NISA預り（つみたて投資枠）", "1", "総合", "投資信託", "口数", "NISA預り（つみたて投資枠）"),
        /** ジュニアNISA口座開設済み 総合・投資信託・口数・旧NISA */
        OPEN_TOTAL_TNVSTMT_TRST_UNIT_OLD_NISA("総合・投資信託・口数・旧NISA", "1", "総合", "投資信託", "口数", "旧NISA"),
        /** ジュニアNISA口座開設済み 総合・投資信託・口数・旧つみたてNISA */
        OPEN_TOTAL_TNVSTMT_TRST_UNIT_OLD_EX_NISA("総合・投資信託・口数・旧つみたてNISA", "1", "総合", "投資信託", "口数", "旧つみたてNISA"),
        /** ジュニアNISA口座開設済み 総合・投資信託・金額・特定 */
        OPEN_TOTAL_TNVSTMT_TRST_AMNT_SPECIFIC("総合・投資信託・金額・特定", "1", "総合", "投資信託", "金額", "特定"),
        /** ジュニアNISA口座開設済み 総合・投資信託・金額・一般 */
        OPEN_TOTAL_TNVSTMT_TRST_AMNT_GENERAL("総合・投資信託・金額・一般", "1", "総合", "投資信託", "金額", "一般"),
        /** ジュニアNISA口座開設済み 総合・投資信託・金額・NISA預り（成長投資枠） */
        OPEN_TOTAL_TNVSTMT_TRST_AMNT_NISA("総合・投資信託・金額・NISA預り（成長投資枠）", "1", "総合", "投資信託", "金額", "NISA預り（成長投資枠）"),
        /** ジュニアNISA口座開設済み 総合・投資信託・金額・NISA預り（つみたて投資枠） */
        OPEN_TOTAL_TNVSTMT_TRST_AMNT_EX_NISA("総合・投資信託・金額・NISA預り（つみたて投資枠）", "1", "総合", "投資信託", "金額", "NISA預り（つみたて投資枠）"),
        /** ジュニアNISA口座開設済み 総合・投資信託・金額・NISA */
        OPEN_TOTAL_TNVSTMT_TRST_AMNT_OLD_NISA("総合・投資信託・金額・旧NISA", "1", "総合", "投資信託", "金額", "旧NISA"),
        /** ジュニアNISA口座開設済み 総合・投資信託・金額・つみたてNISA */
        OPEN_TOTAL_TNVSTMT_TRST_AMNT_OLD_EX_NISA("総合・投資信託・金額・旧つみたてNISA", "1", "総合", "投資信託", "金額", "旧つみたてNISA"),
        /** ジュニアNISA口座開設済み ジュニアNISA・投資信託・口数・特定 */
        OPEN_JRNISA_TNVSTMT_TRST_UNIT_SPECIFIC("ジュニアNISA・投資信託・口数・特定", "1", "ジュニアNISA", "投資信託", "口数", "特定"),
        /** ジュニアNISA口座開設済み ジュニアNISA・投資信託・口数・一般 */
        OPEN_JRNISA_TNVSTMT_TRST_UNIT_GENERAL("ジュニアNISA・投資信託・口数・一般", "1", "ジュニアNISA", "投資信託", "口数", "一般"),
        /** ジュニアNISA口座開設済み ジュニアNISA・投資信託・口数・NISA預り（継続管理勘定） */
        OPEN_JRNISA_TNVSTMT_TRST_UNIT_NISA("ジュニアNISA・投資信託・口数・NISA預り（継続管理勘定）", "1", "ジュニアNISA", "投資信託", "口数",
                "NISA預り（継続管理勘定）"),
        /** ジュニアNISA口座開設済み ジュニアNISA・投資信託・口数・NISA */
        OPEN_JRNISA_TNVSTMT_TRST_UNIT_OLD_NISA("ジュニアNISA・投資信託・口数・NISA", "1", "ジュニアNISA", "投資信託", "口数", "NISA"),
        /** ジュニアNISA口座開設済み 総合・投資信託・金額・特定 */
        OPEN_JRNISA_TNVSTMT_TRST_AMNT_SPECIFIC("ジュニアNISA・投資信託・金額・特定", "1", "ジュニアNISA", "投資信託", "金額", "特定"),
        /** ジュニアNISA口座開設済み 総合・投資信託・金額・一般 */
        OPEN_JRNISA_TNVSTMT_TRST_AMNT_GENERAL("ジュニアNISA・投資信託・金額・一般", "1", "ジュニアNISA", "投資信託", "金額", "一般"),
        /** ジュニアNISA口座開設済み 総合・投資信託・金額・NISA預り（継続管理勘定） */
        OPEN_JRNISA_TNVSTMT_TRST_AMNT_NISA("ジュニアNISA・投資信託・金額・NISA預り（継続管理勘定）", "1", "ジュニアNISA", "投資信託", "金額",
                "NISA預り（継続管理勘定）"),
        /** ジュニアNISA口座開設済み 総合・投資信託・金額・NISA */
        OPEN_JRNISA_TNVSTMT_TRST_AMNT_OLD_NISA("ジュニアNISA・投資信託・金額・NISA", "1", "ジュニアNISA", "投資信託", "金額", "NISA"),
        /** ジュニアNISA口座未開設 総合・投資信託・口数・特定 */
        CLOSE_TOTAL_TNVSTMT_TRST_UNIT_SPECIFIC("総合・投資信託・口数・特定", "0", "総合", "投資信託", "口数", "特定"),
        /** ジュニアNISA口座未開設 総合・投資信託・口数・一般 */
        CLOSE_TOTAL_TNVSTMT_TRST_UNIT_GENERAL("総合・投資信託・口数・一般", "0", "総合", "投資信託", "口数", "一般"),
        /** ジュニアNISA口座未開設 総合・投資信託・口数・NISA預り（成長投資枠） */
        CLOSE_TOTAL_TNVSTMT_TRST_UNIT_NISA("総合・投資信託・口数・NISA預り（成長投資枠）", "0", "総合", "投資信託", "口数", "NISA預り（成長投資枠）"),
        /** ジュニアNISA口座未開設 総合・投資信託・口数・NISA預り（つみたて投資枠） */
        CLOSE_TOTAL_TNVSTMT_TRST_UNIT_EX_NISA("総合・投資信託・口数・NISA預り（つみたて投資枠）", "0", "総合", "投資信託", "口数", "NISA預り（つみたて投資枠）"),
        /** ジュニアNISA口座未開設 総合・投資信託・口数・旧NISA */
        CLOSE_TOTAL_TNVSTMT_TRST_UNIT_OLD_NISA("総合・投資信託・口数・旧NISA", "0", "総合", "投資信託", "口数", "旧NISA"),
        /** ジュニアNISA口座未開設 総合・投資信託・口数・旧つみたてNISA */
        CLOSE_TOTAL_TNVSTMT_TRST_UNIT_OLD_EX_NISA("総合・投資信託・口数・旧つみたてNISA", "0", "総合", "投資信託", "口数", "旧つみたてNISA"),
        /** ジュニアNISA口座未開設 総合・投資信託・金額・特定 */
        CLOSE_TOTAL_TNVSTMT_TRST_AMNT_SPECIFIC("総合・投資信託・金額・特定", "0", "総合", "投資信託", "金額", "特定"),
        /** ジュニアNISA口座未開設 総合・投資信託・金額・一般 */
        CLOSE_TOTAL_TNVSTMT_TRST_AMNT_GENERAL("総合・投資信託・金額・一般", "0", "総合", "投資信託", "金額", "一般"),
        /** ジュニアNISA口座未開設 総合・投資信託・金額・NISA預り（成長投資枠） */
        CLOSE_TOTAL_TNVSTMT_TRST_AMNT_NISA("総合・投資信託・金額・NISA預り（成長投資枠）", "0", "総合", "投資信託", "金額", "NISA預り（成長投資枠）"),
        /** ジュニアNISA口座未開設 総合・投資信託・口数・NISA預り（つみたて投資枠） */
        CLOSE_TOTAL_TNVSTMT_TRST_AMNT_EX_NISA("総合・投資信託・金額・NISA預り（つみたて投資枠）", "0", "総合", "投資信託", "金額", "NISA預り（つみたて投資枠）"),
        /** ジュニアNISA口座未開設 総合・投資信託・金額・旧NISA */
        CLOSE_TOTAL_TNVSTMT_TRST_AMNT_OLD_NISA("総合・投資信託・金額・旧NISA", "0", "総合", "投資信託", "金額", "旧NISA"),
        /** ジュニアNISA口座未開設 総合・投資信託・口数・旧つみたてNISA */
        CLOSE_TOTAL_TNVSTMT_TRST_AMNT_OLD_EX_NISA("総合・投資信託・金額・旧つみたてNISA", "0", "総合", "投資信託", "金額", "旧つみたてNISA"),
        
        // 国内債券
        
        /** ジュニアNISA口座開設済み 総合・国内債券・特定 */
        OPEN_TOTAL_DMSTC_BND_SPECIFIC("総合・国内債券・特定", "1", "総合", "国内債券", null, "特定"),
        /** ジュニアNISA口座開設済み 総合・国内債券・一般 */
        OPEN_TOTAL_DMSTC_BND_GENERAL("総合・国内債券・一般", "1", "総合", "国内債券", null, "一般"),
        /** ジュニアNISA口座開設済み ジュニアNISA・国内債券・特定 */
        OPEN_JRNISA_DMSTC_BND_SPECIFIC("ジュニアNISA・国内債券・特定", "1", "ジュニアNISA", "国内債券", null, "特定"),
        /** ジュニアNISA口座開設済み ジュニアNISA・国内債券・一般 */
        OPEN_JRNISA_DMSTC_BND_GENERAL("ジュニアNISA・国内債券・一般", "1", "ジュニアNISA", "国内債券", null, "一般"),
        /** ジュニアNISA口座未開設 総合・国内債券・特定 */
        CLOSE_TOTAL_DMSTC_BND_SPECIFIC("総合・国内債券・特定", "0", "総合", "国内債券", null, "特定"),
        /** ジュニアNISA口座未開設 総合・国内債券・一般 */
        CLOSE_TOTAL_DMSTC_BND_GENERAL("総合・国内債券・一般", "0", "総合", "国内債券", null, "一般"),
        
        // 外国債券
        
        /** ジュニアNISA口座開設済み 総合・外国債券・特定 */
        OPEN_TOTAL_FRIGN_BND_SPECIFIC("総合・外国債券・特定", "1", "総合", "外国債券", null, "特定"),
        /** ジュニアNISA口座開設済み 総合・国内債券・一般 */
        OPEN_TOTAL_FRIGN_BND_GENERAL("総合・外国債券・一般", "1", "総合", "外国債券", null, "一般"),
        /** ジュニアNISA口座開設済み ジュニアNISA・外国債券・特定 */
        OPEN_JRNISA_FRIGN_BND_SPECIFIC("ジュニアNISA・外国債券・特定", "1", "ジュニアNISA", "外国債券", null, "特定"),
        /** ジュニアNISA口座開設済み ジュニアNISA・外国債券・一般 */
        OPEN_JRNISA_FRIGN_BND_GENERAL("ジュニアNISA・外国債券・一般", "1", "ジュニアNISA", "外国債券", null, "一般"),
        /** ジュニアNISA口座未開設 総合・外国債券・特定 */
        CLOSE_TOTAL_FRIGN_BND_SPECIFIC("総合・外国債券・特定", "0", "総合", "外国債券", null, "特定"),
        /** ジュニアNISA口座未開設 総合・外国債券・一般 */
        CLOSE_TOTAL_FRIGN_BND_GENERAL("総合・外国債券・一般", "0", "総合", "外国債券", null, "一般"),

        // 外国株式
        
        /** ジュニアNISA口座開設済み 総合・外国株式・特定 */
        OPEN_TOTAL_FRIGN_STK_SPECIFIC("総合・外国株式・特定", "1", "総合", "外国株式", null, "特定"),
        /** ジュニアNISA口座開設済み 総合・外国株式・一般 */
        OPEN_TOTAL_FRIGN_STK_GENERAL("総合・外国株式・一般", "1", "総合", "外国株式", null, "一般"),
        /** ジュニアNISA口座開設済み 総合・外国株式・NISA預り（成長投資枠） */
        OPEN_TOTAL_FRIGN_STK_NISA("総合・外国株式・NISA預り（成長投資枠）", "1", "総合", "外国株式", null, "NISA預り（成長投資枠）"),
        /** ジュニアNISA口座開設済み 総合・外国株式・旧NISA */
        OPEN_TOTAL_FRIGN_STK_OLD_NISA("総合・外国株式・旧NISA", "1", "総合", "外国株式", null, "旧NISA"),
        /** ジュニアNISA口座開設済み ジュニアNISA・外国株式・特定 */
        OPEN_JRNISA_FRIGN_STK_SPECIFIC("ジュニアNISA・外国株式・特定", "1", "ジュニアNISA", "外国株式", null, "特定"),
        /** ジュニアNISA口座開設済み ジュニアNISA・外国株式・一般 */
        OPEN_JRNISA_FRIGN_STK_GENERAL("ジュニアNISA・外国株式・一般", "1", "ジュニアNISA", "外国株式", null, "一般"),
        /** ジュニアNISA口座開設済み ジュニアNISA・外国株式・NNISA預り（継続管理勘定） */
        OPEN_JRNISA_FRIGN_STK_NISA("ジュニアNISA・外国株式・NISA預り（継続管理勘定）", "1", "ジュニアNISA", "外国株式", null, "NISA預り（継続管理勘定）"),
        /** ジュニアNISA口座開設済み ジュニアNISA・外国株式・NISA */
        OPEN_JRNISA_FRIGN_STK_OLD_NISA("ジュニアNISA・外国株式・NISA", "1", "ジュニアNISA", "外国株式", null, "NISA"),
        /** ジュニアNISA口座未開設 総合・外国株式・特定 */
        CLOSE_TOTAL_FRIGN_STK_SPECIFIC("総合・外国株式・特定", "0", "総合", "外国株式", null, "特定"),
        /** ジュニアNISA口座未開設 総合・外国株式・一般 */
        CLOSE_TOTAL_FRIGN_STK_GENERAL("総合・外国株式・一般", "0", "総合", "外国株式", null, "一般"),
        /** ジュニアNISA口座未開設 総合・外国株式・NISA預り（成長投資枠） */
        CLOSE_TOTAL_FRIGN_STK_NISA("総合・外国株式・NISA預り（成長投資枠）", "0", "総合", "外国株式", null, "NISA預り（成長投資枠）"),
        /** ジュニアNISA口座未開設 総合・外国株式・旧NISA */
        CLOSE_TOTAL_FRIGN_STK_OLD_NISA("総合・外国株式・旧NISA", "0", "総合", "外国株式", null, "旧NISA"),
        
        // 外貨建MMF
        
        /** ジュニアNISA口座開設済み 総合・外貨建MMF・特定 */
        OPEN_TOTAL_FRIGN_CRNCY_MMF_SPECIFIC("総合・外貨建MMF・特定", "1", "総合", "外貨建MMF", null, "特定"),
        /** ジュニアNISA口座開設済み 総合・外貨建MMF・一般 */
        OPEN_TOTAL_FRIGN_CRNCY_MMF_GENERAL("総合・外貨建MMF・一般", "1", "総合", "外貨建MMF", null, "一般"),
        /** ジュニアNISA口座開設済み ジュニアNISA・外貨建MMF・特定 */
        OPEN_JRNISA_FRIGN_CRNCY_MMF_SPECIFIC("ジュニアNISA・外貨建MMF・特定", "1", "ジュニアNISA", "外貨建MMF", null, "特定"),
        /** ジュニアNISA口座開設済み ジュニアNISA・外貨建MMF・一般 */
        OPEN_JRNISA_FRIGN_CRNCY_MMF_GENERAL("ジュニアNISA・外貨建MMF・一般", "1", "ジュニアNISA", "外貨建MMF", null, "一般"),
        /** ジュニアNISA口座未開設 総合・外貨建MMF・特定 */
        CLOSE_TOTAL_FRIGN_CRNCY_MMF_SPECIFIC("総合・外貨建MMF・特定", "0", "総合", "外貨建MMF", null, "特定"),
        /** ジュニアNISA口座未開設 総合・外貨建MMF・一般 */
        CLOSE_TOTAL_FRIGN_CRNCY_MMF_GENERAL("総合・外貨建MMF・一般", "0", "総合", "外貨建MMF", null, "一般"),
        
        // ST
        
        /** ジュニアNISA口座開設済み 総合・ST・特定 */
        OPEN_TOTAL_SECURITY_TOKEN_SPECIFIC("総合・ST・特定", "1", "総合", "ST", null, "特定"),
        /** ジュニアNISA口座開設済み 総合・ST・一般 */
        OPEN_TOTAL_SECURITY_TOKEN_GENERAL("総合・ST・一般", "1", "総合", "ST", null, "一般"),
        /** ジュニアNISA口座開設済み ジュニアNISA・ST・特定 */
        OPEN_JRNISA_SECURITY_TOKEN_SPECIFIC("ジュニアNISA・ST・特定", "1", "ジュニアNISA", "ST", null, "特定"),
        /** ジュニアNISA口座開設済み ジュニアNISA・ST・一般 */
        OPEN_JRNISA_SECURITY_TOKEN_GENERAL("ジュニアNISA・ST・一般", "1", "ジュニアNISA", "ST", null, "一般"),
        /** ジュニアNISA口座未開設 総合・ST・特定 */
        CLOSE_TOTAL_SECURITY_TOKEN_SPECIFIC("総合・ST・特定", "0", "総合", "ST", null, "特定"),
        /** ジュニアNISA口座未開設 総合・ST・一般 */
        CLOSE_TOTAL_SECURITY_TOKEN_GENERAL("総合・ST・一般", "0", "総合", "ST", null, "一般"),
        
        // その他商品
        
        /** ジュニアNISA口座開設済み 総合・その他商品・特定 */
        OPEN_TOTAL_OTHR_SEC_SPECIFIC("総合・その他商品・特定", "1", "総合", "その他商品", null, "特定"),
        /** ジュニアNISA口座開設済み 総合・その他商品・一般 */
        OPEN_TOTAL_OTHR_SEC_GENERAL("総合・その他商品・特定", "1", "総合", "その他商品", null, "一般"),
        /** ジュニアNISA口座開設済み ジュニアNISA・その他商品・特定 */
        OPEN_JRNISA_OTHR_SEC_SPECIFIC("ジュニアNISA・外国株式・特定", "1", "ジュニアNISA", "その他商品", null, "特定"),
        /** ジュニアNISA口座開設済み ジュニアNISA・その他商品・一般 */
        OPEN_JRNISA_OTHR_SEC_GENERAL("ジュニアNISA・外国株式・一般", "1", "ジュニアNISA", "その他商品", null, "一般"),
        /** ジュニアNISA口座未開設 総合・その他商品・特定 */
        CLOSE_TOTAL_OTHR_SEC_SPECIFIC("総合・その他商品・特定", "0", "総合", "その他商品", null, "特定"),
        /** ジュニアNISA口座未開設 総合・その他商品・一般 */
        CLOSE_TOTAL_OTHR_SEC_GENERAL("総合・その他商品・特定", "0", "総合", "その他商品", null, "一般"),;
        
        DisplayType(String id, String jrIsaOpen, String accountType, String sec, String buyType, String deposit) {
            
            this.id = id;
            this.jrIsaOpen = jrIsaOpen;
            this.accountType = accountType;
            this.sec = sec;
            this.buyType = buyType;
            this.deposit = deposit;
        }
        
        /** ID */
        private String id;
        
        /** ジュニアNISA口座開設済み */
        private String jrIsaOpen;
        
        /** 口座区分 */
        private String accountType;
        
        /** 商品 */
        private String sec;
        
        /** 買付方法 */
        private String buyType;
        
        /** 預り */
        private String deposit;
        
        /**
         * IDの取得
         *
         * @return ID
         */
        public String getId() {
            
            return id;
        }
        
        /**
         * ジュニアNISA口座開設済みの取得
         *
         * @return ジュニアNISA口座開設済み
         */
        public String getJrIsaOpen() {
            
            return jrIsaOpen;
        }
        
        /**
         * 口座区分の取得
         *
         * @return 口座区分
         */
        public String getAccountType() {
            
            return accountType;
        }
        
        /**
         * 商品の取得
         *
         * @return 商品
         */
        public String getSec() {
            
            return sec;
        }
        
        /**
         * 買付方法の取得
         *
         * @return 買付方法
         */
        public String getBuyType() {
            
            return buyType;
        }
        
        /**
         * 預りの取得
         *
         * @return 預り
         */
        public String getDeposit() {
            
            return deposit;
        }
        
    }
    
    /** ファンドタイプ */
    private enum FundType {

        // 一般口
        GENERAL("1"),
        // 汎用累投
        CUMULATIVE_PITCH("2");

        private final String value;

        FundType(final String value) {

            this.value = value;
        }

        private String getValue() {

            return value;
        }
    }

    /** ファンドタイプ */
    private enum Prospectus {

        // 閲覧済・郵送完了
        READ("0");

        private final String value;

        Prospectus(final String value) {

            this.value = value;
        }

        private String getValue() {

            return value;
        }
    }

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：brokerageMenu.customerMenuA001DtoRequest
     * Dto レスポンス：brokerageMenu.customerMenuA001DtoResponse
     * model リクエスト：IfaHoldingSecurityListSql002RequestModel
     * model レスポンス：IfaHoldingSecurityListSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaHoldingSecurityListA001ResponseDto> initializeA001(IfaHoldingSecurityListA001RequestDto dtoReq)
            throws Exception {
        
        List<IfaHoldingSecurityListA001ResponseDto> resDtoList = new ArrayList<IfaHoldingSecurityListA001ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaHoldingSecurityListServiceImplL.initializeA001");
        }
        
        // 顧客情報の取得
        // 現在は仮項目設定しかしていないのでそれを用いて処理する
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        //  ①   利用者の口座に対する権限チェックを行う。
        OutputFct001Dto fct001Dto = this.callFct001(butenCode, accountNumber);
        if (fct001Dto == null || StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(),
                IfaHoldingSecurityListServiceImpL.AUTH_ERROR_VALUE)) {
            
            DataList<IfaHoldingSecurityListA001ResponseDto> dtoResList = IfaCommonUtil.createDataList(resDtoList,
                    ErrorLevel.FATAL, IfaHoldingSecurityListServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(IfaHoldingSecurityListServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                            new String[] { butenCode, accountNumber }));
            return dtoResList;
        }
        
        //②利用者の口座における取引コース媒介可否リストを取得する。(FCT003)
        OutputFct003Dto fct003Dto = this.callFct003(butenCode, accountNumber);
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // ③   売却制限明細を取得する。
        QuerySaleLimitOutData api007Output = this.callApi007(butenCode, accountNumber, apiErrorUtil);
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        IfaHoldingSecurityListA001ResponseDto resDto = new IfaHoldingSecurityListA001ResponseDto();
        
        // API007.検索件数 > 0、または API007.未送信データ区分='1'(残データあり）
        // 上記条件を満たす場合、Actionパラメータ.売却不可明細表示区分=’1’(表示)
        // 上記条件を満たさない場合、Actionパラメータ.売却不可明細表示区分=’0’(非表示)
        if (Integer.parseInt(api007Output.getTotalNumber()) > 0 || api007Output.getNextUmuFlg().equals("1")) {
            resDto.setSellUnableDetailDisplayClassification("1");
        } else {
            resDto.setSellUnableDetailDisplayClassification("0");
        }
        
        // 国内株式情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoDomesticStock> domesticStockMap = Collections
                .synchronizedMap(new TreeMap<String, IfaHoldingSecurityListResponseDtoDomesticStock>());
        // 投資信託情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoInvestmentTrust> investmentTrustMap = Collections
                .synchronizedMap(new TreeMap<String, IfaHoldingSecurityListResponseDtoInvestmentTrust>());
        // 国内債券情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoDomesticBonds> domesticBondsMap = Collections
                .synchronizedMap(new TreeMap<String, IfaHoldingSecurityListResponseDtoDomesticBonds>());
        // 外国債券情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoForeignBonds> foreignBondsMap = Collections
                .synchronizedMap(new TreeMap<String, IfaHoldingSecurityListResponseDtoForeignBonds>());
        // 外国株式情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoForeignStocks> foreignStocksMap = Collections
                .synchronizedMap(new TreeMap<String, IfaHoldingSecurityListResponseDtoForeignStocks>());
        // 外貨建MMF情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoForeignCurrencyMmf> foreignCurrencyMmfMap = Collections
                .synchronizedMap(new TreeMap<String, IfaHoldingSecurityListResponseDtoForeignCurrencyMmf>());
        // ST（セキュリティ・トークン）情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoSecurityToken> securityTokenMap = Collections
                .synchronizedMap(new TreeMap<String, IfaHoldingSecurityListResponseDtoSecurityToken>());
        // その他商品情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoOtherSecurity> otherSecurityMap = Collections
                .synchronizedMap(new TreeMap<String, IfaHoldingSecurityListResponseDtoOtherSecurity>());
        
        // 国内株式、投資信託、国内債券、外国債券、その他商品情報の生成
        this.createHoldingSecurityInfo1(domesticStockMap, investmentTrustMap, domesticBondsMap, foreignBondsMap,
                otherSecurityMap, fct001Dto, fct003Dto, null, null, apiErrorUtil);
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        // ST
        this.createHoldingSecurityInfo3(securityTokenMap, null);
        // 外国株式、外国MFF情報の生成
        try {
            this.createHoldingSecurityInfo2(foreignStocksMap, foreignCurrencyMmfMap, foreignBondsMap, fct001Dto, fct003Dto, null, null);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // ⑬全体の合計を計算する。
        BigDecimal[] totalAry = this.getValuationAndGetProfitTotal(domesticStockMap, investmentTrustMap,
                domesticBondsMap, foreignBondsMap, foreignStocksMap, foreignCurrencyMmfMap, securityTokenMap, otherSecurityMap);
        
        resDto.setGetTotalAssessedValueAll(totalAry[0].toPlainString());
        resDto.setGetTotalProfitAll(totalAry[1].toPlainString());
        
        List<IfaHoldingSecurityListResponseDtoDomesticStock> domesticStockList = new ArrayList<IfaHoldingSecurityListResponseDtoDomesticStock>();
        for (String key : domesticStockMap.keySet()) {
            IfaHoldingSecurityListResponseDtoDomesticStock domesticStock = domesticStockMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(domesticStock.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoDomesticStockDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoDomesticStockDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoDomesticStockDepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            domesticStockList.add(domesticStock);
        }
        resDto.setDomesticStockList(domesticStockList);
        
        List<IfaHoldingSecurityListResponseDtoInvestmentTrust> investmentTrustList = new ArrayList<IfaHoldingSecurityListResponseDtoInvestmentTrust>();
        for (String key : investmentTrustMap.keySet()) {
            IfaHoldingSecurityListResponseDtoInvestmentTrust investmentTrust = investmentTrustMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(investmentTrust.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            investmentTrustList.add(investmentTrust);
        }
        resDto.setInvestmentTrustList(investmentTrustList);
        
        List<IfaHoldingSecurityListResponseDtoDomesticBonds> domesticBondsList = new ArrayList<IfaHoldingSecurityListResponseDtoDomesticBonds>();
        for (String key : domesticBondsMap.keySet()) {
            IfaHoldingSecurityListResponseDtoDomesticBonds domesticBonds = domesticBondsMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(domesticBonds.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoDomesticBondsDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoDomesticBondsDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoDomesticBondsDepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            domesticBondsList.add(domesticBonds);
        }
        resDto.setDomesticBondsList(domesticBondsList);
        
        List<IfaHoldingSecurityListResponseDtoForeignBonds> foreignBondsList = new ArrayList<IfaHoldingSecurityListResponseDtoForeignBonds>();
        for (String key : foreignBondsMap.keySet()) {
            IfaHoldingSecurityListResponseDtoForeignBonds foreignBonds = foreignBondsMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(foreignBonds.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail p1,
                                IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            foreignBondsList.add(foreignBonds);
        }
        resDto.setForeignBondsList(foreignBondsList);
        
        List<IfaHoldingSecurityListResponseDtoForeignStocks> foreignStockList = new ArrayList<IfaHoldingSecurityListResponseDtoForeignStocks>();
        for (String key : foreignStocksMap.keySet()) {
            IfaHoldingSecurityListResponseDtoForeignStocks foreignStock = foreignStocksMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(foreignStock.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail p1,
                                IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            foreignStockList.add(foreignStock);
        }
        resDto.setForeignStocksList(foreignStockList);
        
        List<IfaHoldingSecurityListResponseDtoForeignCurrencyMmf> foreignCurrencyMmfList = new ArrayList<IfaHoldingSecurityListResponseDtoForeignCurrencyMmf>();
        for (String key : foreignCurrencyMmfMap.keySet()) {
            IfaHoldingSecurityListResponseDtoForeignCurrencyMmf foreignCurrencyMmf = foreignCurrencyMmfMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(foreignCurrencyMmf.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoForeignCurrencyMmfDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoForeignCurrencyMmfDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoForeignCurrencyMmfDepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            foreignCurrencyMmfList.add(foreignCurrencyMmf);
        }
        resDto.setForeignCurrencyMmfList(foreignCurrencyMmfList);
        
        List<IfaHoldingSecurityListResponseDtoSecurityToken> securityTokenList = new ArrayList<IfaHoldingSecurityListResponseDtoSecurityToken>();
        for (String key : securityTokenMap.keySet()) {
            IfaHoldingSecurityListResponseDtoSecurityToken securityToken = securityTokenMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(securityToken.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            securityTokenList.add(securityToken);
        }
        resDto.setSecurityTokenList(securityTokenList);
        
        List<IfaHoldingSecurityListResponseDtoOtherSecurity> otherSecurityList = new ArrayList<IfaHoldingSecurityListResponseDtoOtherSecurity>();
        for (String key : otherSecurityMap.keySet()) {
            IfaHoldingSecurityListResponseDtoOtherSecurity otherSecurity = otherSecurityMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(otherSecurity.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoDepositDetail p2) {
                            
                            return p1.getBrandNameFundName().compareTo(p2.getBrandNameFundName());
                        }
                    });
            otherSecurityList.add(otherSecurity);
        }
        resDto.setOtherSecurityList(otherSecurityList);
        
        resDtoList.add(resDto);
        
        return apiErrorUtil.createDataList(resDtoList, null);
    }
    
    /**
     * アクションID：A003
     * アクション名：国内株式売買
     * Dto リクエスト：IfaHoldingSecurityListA003RequestDto
     * Dto レスポンス：IfaHoldingSecurityListA003ResponseDto
     * model リクエスト：IfaHoldingSecurityListSql007RequestModel
     * model レスポンス：IfaHoldingSecurityListSql007ResponseModel
     *
     * @param dtoReq
     * @return レスポンス
     * @throws Exception
     */
    public DataList<?> domesticStockTradingA003(IfaHoldingSecurityListA003RequestDto dtoReq) throws Exception {
        String brandCode = dtoReq.getBrandCode();
        IfaHoldingSecurityListSql007RequestModel sql007RequestModel = new IfaHoldingSecurityListSql007RequestModel();
        sql007RequestModel.setBrandCode(brandCode);
        IfaHoldingSecurityListSql007ResponseModel sql007ResModel = dao.selectIfaHoldingSecurityListSql007(sql007RequestModel);
        if (sql007ResModel == null
                || (sql007ResModel.getSorServiceKbn() == null || sql007ResModel.getSorServiceKbn().equals("0"))
                && sql007ResModel.getMktKbnTky() == null && sql007ResModel.getMktKbnNgy() == null
                && sql007ResModel.getMktKbnFko() == null && sql007ResModel.getMktKbnSpr() == null && sql007ResModel.getPts() == 0){
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_DMS_SELECTEDBRAND_MARKETNOTFOUND, IfaCommonUtil.getMessage(ERRORS_DMS_SELECTEDBRAND_MARKETNOTFOUND));
        }
        return IfaCommonUtil.createDtaList(Collections.emptyList(), ErrorLevel.SUCCESS, "");
    }
    
    /**
     * アクションID：A012
     * アクション名：口座選択
     * Dto リクエスト：IfaHoldingSecurityListA012DtoRequest
     * Dto レスポンス：IfaHoldingSecurityListA012DtoResponse
     * model リクエスト：IfaHoldingSecurityListSql002RequestModel
     * model レスポンス：IfaHoldingSecurityListSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception 口座選択処理で例外が発生した場合
     */
    public DataList<IfaHoldingSecurityListA012ResponseDto> accountSelectionA012(
            IfaHoldingSecurityListA012RequestDto dtoReq) throws Exception {
        
        List<IfaHoldingSecurityListA012ResponseDto> resDtoList = new ArrayList<IfaHoldingSecurityListA012ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaHoldingSecurityListServiceImplL.accountselectionA012");
        }
        
        // 顧客情報の取得
        // 現在は仮項目設定しかしていないのでそれを用いて処理する
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        //  ①   利用者の口座に対する権限チェックを行う。
        OutputFct001Dto fct001Dto = this.callFct001(butenCode, accountNumber);
        if (fct001Dto == null || StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(),
                IfaHoldingSecurityListServiceImpL.AUTH_ERROR_VALUE)) {
            
            DataList<IfaHoldingSecurityListA012ResponseDto> dtoResList = IfaCommonUtil.createDataList(resDtoList,
                    ErrorLevel.FATAL, IfaHoldingSecurityListServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(IfaHoldingSecurityListServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                            new String[] { butenCode, accountNumber }));
            return dtoResList;
        }
        
        //②利用者の口座における取引コース媒介可否リストを取得する。(FCT003)
        OutputFct003Dto fct003Dto = this.callFct003(butenCode, accountNumber);
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // ③   売却制限明細を取得する。
        QuerySaleLimitOutData api007Output = this.callApi007(butenCode, accountNumber, apiErrorUtil);
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        IfaHoldingSecurityListA012ResponseDto resDto = new IfaHoldingSecurityListA012ResponseDto();
        
        // API007.検索件数
        // 1件以上の場合、Actionパラメータ.売却不可明細表示区分=’1’(表示)
        // 0件の場合、Actionパラメータ.売却不可明細表示区分=’0’(非表示)
        if (Integer.parseInt(api007Output.getTotalNumber()) >= 1) {
            resDto.setSellUnableDetailDisplayClassification("1");
        } else {
            resDto.setSellUnableDetailDisplayClassification("0");
        }
        
        String secType = "";
        String secCode = "";
        if (StringUtils.equals(dtoReq.getSecuritySelect(),
                IfaHoldingSecurityListServiceImpL.SELECT_SEC_TYPE_DOMESTICSTOCK) == true) {
            secType = "K0";
        } else if (StringUtils.equals(dtoReq.getSecuritySelect(),
                IfaHoldingSecurityListServiceImpL.SELECT_SEC_TYPE_INVESTMENTTRUST) == true) {
            secType = "T0";
        } else if (StringUtils.equals(dtoReq.getSecuritySelect(),
                IfaHoldingSecurityListServiceImpL.SELECT_SEC_TYPE_DOMESTICBONDS) == true) {
            secType = "S0";
        } else if (StringUtils.equals(dtoReq.getSecuritySelect(),
                IfaHoldingSecurityListServiceImpL.SELECT_SEC_TYPE_FOREIGNBONDS) == true) {
            secType = "S1";
            secCode = IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_BOND;
        } else if (StringUtils.equals(dtoReq.getSecuritySelect(),
                IfaHoldingSecurityListServiceImpL.SELECT_SEC_TYPE_FOREIGNSTOCKS) == true) {
            secCode = IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_STOCK;
        } else if (StringUtils.equals(dtoReq.getSecuritySelect(),
                IfaHoldingSecurityListServiceImpL.SELECT_SEC_TYPE_FOREIGNCURRENCYMMF) == true) {
            secCode = IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_MMF;
        } else if (StringUtils.equals(dtoReq.getSecuritySelect(),
                IfaHoldingSecurityListServiceImpL.SELECT_SEC_TYPE_ALL) == true) {
            secType = null;
            secCode = null;
        }
        
        // 国内株式情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoDomesticStock> domesticStockMap = new TreeMap<String, IfaHoldingSecurityListResponseDtoDomesticStock>();
        // 投資信託情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoInvestmentTrust> investmentTrustMap = new TreeMap<String, IfaHoldingSecurityListResponseDtoInvestmentTrust>();
        // 国内債券情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoDomesticBonds> domesticBondsMap = new TreeMap<String, IfaHoldingSecurityListResponseDtoDomesticBonds>();
        // 外国債券情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoForeignBonds> foreignBondsMap = new TreeMap<String, IfaHoldingSecurityListResponseDtoForeignBonds>();
        // 外国株式情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoForeignStocks> foreignStocksMap = new TreeMap<String, IfaHoldingSecurityListResponseDtoForeignStocks>();
        // 外貨建MMF情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoForeignCurrencyMmf> foreignCurrencyMmfMap = new TreeMap<String, IfaHoldingSecurityListResponseDtoForeignCurrencyMmf>();
        // ST（セキュリティ・トークン）情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoSecurityToken> securityTokenMap = new TreeMap<String, IfaHoldingSecurityListResponseDtoSecurityToken>();
        // その他商品情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoOtherSecurity> otherSecurityMap = new TreeMap<String, IfaHoldingSecurityListResponseDtoOtherSecurity>();
        
        //・画面.商品選択が"国内株式"、"国内投信"、"国内債券"の場合、A001⑨、A001⑨’およびA001⑩を行なわない。
        //・画面.商品選択が"外国債券"の場合、A001⑨’およびA001⑩を行なわない。
        //・画面.商品選択が"外国株式"の場合、A001⑤’⑥⑦⑨⑩を行なわない。
        //・画面.商品選択が"外貨建MMFの場合、A001⑤’⑥⑦⑨⑨’を行なわない。
        //・画面.商品選択が"ST（セキュリティ・トークン）"の場合、A001⑤’⑥⑦⑨⑨’⑩を行なわない。
        
        if ((secType == null && secCode == null) || secType != null || (secType != "" && secCode != "")) {
            // 国内株式、投資信託、国内債券、外国債券（円貨建）、その他商品情報の生成
            this.createHoldingSecurityInfo1(domesticStockMap, investmentTrustMap, domesticBondsMap, foreignBondsMap,
                    otherSecurityMap, fct001Dto, fct003Dto, dtoReq.getAccountClassification(), secType, apiErrorUtil);
        }
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        if ((secType == null && secCode == null) || (secType == "" && secCode == "")) {
            // ST
            this.createHoldingSecurityInfo3(securityTokenMap, dtoReq.getAccountClassification());
        }
        if ((secType == null && secCode == null) || secCode != null || (secType != "" && secCode != "")) {
            // 外国株式、外国MFF、外国債券（外貨建）情報の生成
            try {
                this.createHoldingSecurityInfo2(foreignStocksMap, foreignCurrencyMmfMap, foreignBondsMap, fct001Dto, fct003Dto, secCode,
                        dtoReq.getAccountClassification());
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
        }
        
        // ⑬全体の合計を計算する。
        BigDecimal[] totalAry = this.getValuationAndGetProfitTotal(domesticStockMap, investmentTrustMap,
                domesticBondsMap, foreignBondsMap, foreignStocksMap, foreignCurrencyMmfMap, securityTokenMap, otherSecurityMap);
        
        resDto.setGetTotalAssessedValueAll(totalAry[0].toPlainString());
        resDto.setGetTotalProfitAll(totalAry[1].toPlainString());
        
        List<IfaHoldingSecurityListResponseDtoDomesticStock> domesticStockList = new ArrayList<IfaHoldingSecurityListResponseDtoDomesticStock>();
        for (String key : domesticStockMap.keySet()) {
            IfaHoldingSecurityListResponseDtoDomesticStock domesticStock = domesticStockMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(domesticStock.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoDomesticStockDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoDomesticStockDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoDomesticStockDepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            domesticStockList.add(domesticStock);
        }
        resDto.setDomesticStockList(domesticStockList);
        
        List<IfaHoldingSecurityListResponseDtoInvestmentTrust> investmentTrustList = new ArrayList<IfaHoldingSecurityListResponseDtoInvestmentTrust>();
        for (String key : investmentTrustMap.keySet()) {
            IfaHoldingSecurityListResponseDtoInvestmentTrust investmentTrust = investmentTrustMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(investmentTrust.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            investmentTrustList.add(investmentTrust);
        }
        resDto.setInvestmentTrustList(investmentTrustList);
        
        List<IfaHoldingSecurityListResponseDtoDomesticBonds> domesticBondsList = new ArrayList<IfaHoldingSecurityListResponseDtoDomesticBonds>();
        for (String key : domesticBondsMap.keySet()) {
            IfaHoldingSecurityListResponseDtoDomesticBonds domesticBonds = domesticBondsMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(domesticBonds.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoDomesticBondsDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoDomesticBondsDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoDomesticBondsDepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            domesticBondsList.add(domesticBonds);
        }
        resDto.setDomesticBondsList(domesticBondsList);
        
        List<IfaHoldingSecurityListResponseDtoForeignBonds> foreignBondsList = new ArrayList<IfaHoldingSecurityListResponseDtoForeignBonds>();
        for (String key : foreignBondsMap.keySet()) {
            IfaHoldingSecurityListResponseDtoForeignBonds foreignBonds = foreignBondsMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(foreignBonds.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail p1,
                                IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            foreignBondsList.add(foreignBonds);
        }
        resDto.setForeignBondsList(foreignBondsList);
        
        List<IfaHoldingSecurityListResponseDtoForeignStocks> foreignStockList = new ArrayList<IfaHoldingSecurityListResponseDtoForeignStocks>();
        for (String key : foreignStocksMap.keySet()) {
            IfaHoldingSecurityListResponseDtoForeignStocks foreignStock = foreignStocksMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(foreignStock.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail p1,
                                IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            foreignStockList.add(foreignStock);
        }
        resDto.setForeignStocksList(foreignStockList);
        
        List<IfaHoldingSecurityListResponseDtoForeignCurrencyMmf> foreignCurrencyMmfList = new ArrayList<IfaHoldingSecurityListResponseDtoForeignCurrencyMmf>();
        for (String key : foreignCurrencyMmfMap.keySet()) {
            IfaHoldingSecurityListResponseDtoForeignCurrencyMmf foreignCurrencyMmf = foreignCurrencyMmfMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(foreignCurrencyMmf.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoForeignCurrencyMmfDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoForeignCurrencyMmfDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoForeignCurrencyMmfDepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            foreignCurrencyMmfList.add(foreignCurrencyMmf);
        }
        resDto.setForeignCurrencyMmfList(foreignCurrencyMmfList);
        
        List<IfaHoldingSecurityListResponseDtoSecurityToken> securityTokenList = new ArrayList<IfaHoldingSecurityListResponseDtoSecurityToken>();
        for (String key : securityTokenMap.keySet()) {
            IfaHoldingSecurityListResponseDtoSecurityToken securityToken = securityTokenMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(securityToken.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            securityTokenList.add(securityToken);
        }
        resDto.setSecurityTokenList(securityTokenList);
        
        List<IfaHoldingSecurityListResponseDtoOtherSecurity> otherSecurityList = new ArrayList<IfaHoldingSecurityListResponseDtoOtherSecurity>();
        for (String key : otherSecurityMap.keySet()) {
            IfaHoldingSecurityListResponseDtoOtherSecurity otherSecurity = otherSecurityMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(otherSecurity.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoDepositDetail p2) {
                            
                            return p1.getBrandNameFundName().compareTo(p2.getBrandNameFundName());
                        }
                    });
            otherSecurityList.add(otherSecurity);
        }
        resDto.setOtherSecurityList(otherSecurityList);
        
        resDtoList.add(resDto);
        
        return apiErrorUtil.createDataList(resDtoList, null);
    }
    
    /**
     * アクションID：A013
     * アクション名：商品選択
     * Dto リクエスト：IfaHoldingSecurityListA013RequestDto
     * Dto レスポンス：IfaHoldingSecurityListA013ResponseDto
     * model リクエスト：IfaHoldingSecurityListSql005RequestModel
     * model レスポンス：IfaHoldingSecurityListSql005ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception 商品選択処理で例外が発生した場合
     */
    public DataList<IfaHoldingSecurityListA013ResponseDto> productSelectionA013(
            IfaHoldingSecurityListA013RequestDto dtoReq) throws Exception {
        
        List<IfaHoldingSecurityListA013ResponseDto> resDtoList = new ArrayList<IfaHoldingSecurityListA013ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaHoldingSecurityListServiceImplL.productselectionA013");
        }
        
        // 顧客情報の取得
        // 現在は仮項目設定しかしていないのでそれを用いて処理する
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        //  ①   利用者の口座に対する権限チェックを行う。
        OutputFct001Dto fct001Dto = this.callFct001(butenCode, accountNumber);
        if (fct001Dto == null || StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(),
                IfaHoldingSecurityListServiceImpL.AUTH_ERROR_VALUE)) {
            
            DataList<IfaHoldingSecurityListA013ResponseDto> dtoResList = IfaCommonUtil.createDataList(resDtoList,
                    ErrorLevel.FATAL, IfaHoldingSecurityListServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(IfaHoldingSecurityListServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                            new String[] { butenCode, accountNumber }));
            return dtoResList;
        }
        
        //②利用者の口座における取引コース媒介可否リストを取得する。(FCT003)
        OutputFct003Dto fct003Dto = this.callFct003(butenCode, accountNumber);
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // ③   売却制限明細を取得する。
        QuerySaleLimitOutData api007Output = this.callApi007(butenCode, accountNumber, apiErrorUtil);
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        IfaHoldingSecurityListA013ResponseDto resDto = new IfaHoldingSecurityListA013ResponseDto();
        
        // API007.検索件数
        // 1件以上の場合、Actionパラメータ.売却不可明細表示区分=’1’(表示)
        // 0件の場合、Actionパラメータ.売却不可明細表示区分=’0’(非表示)
        if (Integer.parseInt(api007Output.getTotalNumber()) >= 1) {
            resDto.setSellUnableDetailDisplayClassification("1");
        } else {
            resDto.setSellUnableDetailDisplayClassification("0");
        }
        
        String secType = "";
        String secCode = "";
        if (StringUtils.equals(dtoReq.getSecuritySelect(),
                IfaHoldingSecurityListServiceImpL.SELECT_SEC_TYPE_DOMESTICSTOCK) == true) {
            secType = "K0";
        } else if (StringUtils.equals(dtoReq.getSecuritySelect(),
                IfaHoldingSecurityListServiceImpL.SELECT_SEC_TYPE_INVESTMENTTRUST) == true) {
            secType = "T0";
        } else if (StringUtils.equals(dtoReq.getSecuritySelect(),
                IfaHoldingSecurityListServiceImpL.SELECT_SEC_TYPE_DOMESTICBONDS) == true) {
            secType = "S0";
        } else if (StringUtils.equals(dtoReq.getSecuritySelect(),
                IfaHoldingSecurityListServiceImpL.SELECT_SEC_TYPE_FOREIGNBONDS) == true) {
            secType = "S1";
            secCode = IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_BOND;
        } else if (StringUtils.equals(dtoReq.getSecuritySelect(),
                IfaHoldingSecurityListServiceImpL.SELECT_SEC_TYPE_FOREIGNSTOCKS) == true) {
            secCode = IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_STOCK;
        } else if (StringUtils.equals(dtoReq.getSecuritySelect(),
                IfaHoldingSecurityListServiceImpL.SELECT_SEC_TYPE_FOREIGNCURRENCYMMF) == true) {
            secCode = IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_MMF;
        } else if (StringUtils.equals(dtoReq.getSecuritySelect(),
                IfaHoldingSecurityListServiceImpL.SELECT_SEC_TYPE_ALL) == true) {
            secType = null;
            secCode = null;
        }
        
        // 国内株式情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoDomesticStock> domesticStockMap = new TreeMap<String, IfaHoldingSecurityListResponseDtoDomesticStock>();
        // 投資信託情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoInvestmentTrust> investmentTrustMap = new TreeMap<String, IfaHoldingSecurityListResponseDtoInvestmentTrust>();
        // 国内債券情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoDomesticBonds> domesticBondsMap = new TreeMap<String, IfaHoldingSecurityListResponseDtoDomesticBonds>();
        // 外国債券情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoForeignBonds> foreignBondsMap = new TreeMap<String, IfaHoldingSecurityListResponseDtoForeignBonds>();
        // 外国株式情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoForeignStocks> foreignStocksMap = new TreeMap<String, IfaHoldingSecurityListResponseDtoForeignStocks>();
        // 外貨建MMF情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoForeignCurrencyMmf> foreignCurrencyMmfMap = new TreeMap<String, IfaHoldingSecurityListResponseDtoForeignCurrencyMmf>();
        // ST（セキュリティ・トークン）情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoSecurityToken> securityTokenMap = new TreeMap<String, IfaHoldingSecurityListResponseDtoSecurityToken>();
        // その他商品情報管理マップ
        Map<String, IfaHoldingSecurityListResponseDtoOtherSecurity> otherSecurityMap = new TreeMap<String, IfaHoldingSecurityListResponseDtoOtherSecurity>();
        
        //・画面.商品選択が"国内株式"、"国内投信"、"国内債券"の場合、A001⑨、A001⑨’およびA001⑩を行なわない。
        //・画面.商品選択が"外国債券"の場合、A001⑨’およびA001⑩を行なわない。
        //・画面.商品選択が"外国株式"の場合、A001⑤’⑥⑦⑨⑩を行なわない。
        //・画面.商品選択が"外貨建MMFの場合、A001⑤’⑥⑦⑨⑨’を行なわない。
        
        if ((secType == null && secCode == null) || secType != null || (secType != "" && secCode != "")) {
            // 国内株式、投資信託、国内債券、外国債券（外貨建）、その他商品情報の生成
            this.createHoldingSecurityInfo1(domesticStockMap, investmentTrustMap, domesticBondsMap, foreignBondsMap,
                    otherSecurityMap, fct001Dto, fct003Dto, dtoReq.getAccountClassification(), secType, apiErrorUtil);
        }
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        if ((secType == null && secCode == null) || (secType == "" && secCode == "")) {
            // ST
            this.createHoldingSecurityInfo3(securityTokenMap, dtoReq.getAccountClassification());
        }
        if ((secType == null && secCode == null) || secCode != null || (secType != "" && secCode != "")) {
            // 外国株式、外国MFF、外国債券（外貨建）情報の生成
            try {
                this.createHoldingSecurityInfo2(foreignStocksMap, foreignCurrencyMmfMap, foreignBondsMap, fct001Dto, fct003Dto, secCode,
                        dtoReq.getAccountClassification());
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
        }
        
        // ⑬全体の合計を計算する。
        BigDecimal[] totalAry = this.getValuationAndGetProfitTotal(domesticStockMap, investmentTrustMap,
                domesticBondsMap, foreignBondsMap, foreignStocksMap, foreignCurrencyMmfMap, securityTokenMap, otherSecurityMap);
        
        resDto.setGetTotalAssessedValueAll(totalAry[0].toPlainString());
        resDto.setGetTotalProfitAll(totalAry[1].toPlainString());
        
        List<IfaHoldingSecurityListResponseDtoDomesticStock> domesticStockList = new ArrayList<IfaHoldingSecurityListResponseDtoDomesticStock>();
        for (String key : domesticStockMap.keySet()) {
            IfaHoldingSecurityListResponseDtoDomesticStock domesticStock = domesticStockMap.get(key);
            domesticStockList.add(domesticStock);
        }
        resDto.setDomesticStockList(domesticStockList);
        
        List<IfaHoldingSecurityListResponseDtoInvestmentTrust> investmentTrustList = new ArrayList<IfaHoldingSecurityListResponseDtoInvestmentTrust>();
        for (String key : investmentTrustMap.keySet()) {
            IfaHoldingSecurityListResponseDtoInvestmentTrust investmentTrust = investmentTrustMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(investmentTrust.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            investmentTrustList.add(investmentTrust);
        }
        resDto.setInvestmentTrustList(investmentTrustList);
        
        List<IfaHoldingSecurityListResponseDtoDomesticBonds> domesticBondsList = new ArrayList<IfaHoldingSecurityListResponseDtoDomesticBonds>();
        for (String key : domesticBondsMap.keySet()) {
            IfaHoldingSecurityListResponseDtoDomesticBonds domesticBonds = domesticBondsMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(domesticBonds.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoDomesticBondsDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoDomesticBondsDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoDomesticBondsDepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            domesticBondsList.add(domesticBonds);
        }
        resDto.setDomesticBondsList(domesticBondsList);
        
        List<IfaHoldingSecurityListResponseDtoForeignBonds> foreignBondsList = new ArrayList<IfaHoldingSecurityListResponseDtoForeignBonds>();
        for (String key : foreignBondsMap.keySet()) {
            IfaHoldingSecurityListResponseDtoForeignBonds foreignBonds = foreignBondsMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(foreignBonds.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail p1,
                                IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            foreignBondsList.add(foreignBonds);
        }
        resDto.setForeignBondsList(foreignBondsList);
        
        List<IfaHoldingSecurityListResponseDtoForeignStocks> foreignStockList = new ArrayList<IfaHoldingSecurityListResponseDtoForeignStocks>();
        for (String key : foreignStocksMap.keySet()) {
            IfaHoldingSecurityListResponseDtoForeignStocks foreignStock = foreignStocksMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(foreignStock.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail p1,
                                IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            foreignStockList.add(foreignStock);
        }
        resDto.setForeignStocksList(foreignStockList);
        
        List<IfaHoldingSecurityListResponseDtoForeignCurrencyMmf> foreignCurrencyMmfList = new ArrayList<IfaHoldingSecurityListResponseDtoForeignCurrencyMmf>();
        for (String key : foreignCurrencyMmfMap.keySet()) {
            IfaHoldingSecurityListResponseDtoForeignCurrencyMmf foreignCurrencyMmf = foreignCurrencyMmfMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(foreignCurrencyMmf.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoForeignCurrencyMmfDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoForeignCurrencyMmfDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoForeignCurrencyMmfDepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            foreignCurrencyMmfList.add(foreignCurrencyMmf);
        }
        resDto.setForeignCurrencyMmfList(foreignCurrencyMmfList);
        
        List<IfaHoldingSecurityListResponseDtoSecurityToken> securityTokenList = new ArrayList<IfaHoldingSecurityListResponseDtoSecurityToken>();
        for (String key : securityTokenMap.keySet()) {
            IfaHoldingSecurityListResponseDtoSecurityToken securityToken = securityTokenMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(securityToken.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail p2) {
                            
                            return p1.getBrandCode().compareTo(p2.getBrandCode());
                        }
                    });
            securityTokenList.add(securityToken);
        }
        resDto.setSecurityTokenList(securityTokenList);
        
        List<IfaHoldingSecurityListResponseDtoOtherSecurity> otherSecurityList = new ArrayList<IfaHoldingSecurityListResponseDtoOtherSecurity>();
        for (String key : otherSecurityMap.keySet()) {
            IfaHoldingSecurityListResponseDtoOtherSecurity otherSecurity = otherSecurityMap.get(key);
            
            // 預り明細リスト内の情報を銘柄コードでソートする
            Collections.sort(otherSecurity.getDepositDetailList(),
                    new Comparator<IfaHoldingSecurityListResponseDtoDepositDetail>() {
                        
                        @Override
                        //順序付けのために2つの引数を比較します。
                        public int compare(IfaHoldingSecurityListResponseDtoDepositDetail p1,
                                IfaHoldingSecurityListResponseDtoDepositDetail p2) {
                            
                            return p1.getBrandNameFundName().compareTo(p2.getBrandNameFundName());
                        }
                    });
            otherSecurityList.add(otherSecurity);
        }
        resDto.setOtherSecurityList(otherSecurityList);
        
        resDtoList.add(resDto);
        
        return apiErrorUtil.createDataList(resDtoList, null);
    }

    /** API呼び出しクラス(Safe) */
    @Autowired
    private SafeFundTradeService safeFundTradeService;
    /**
     * アクションID：A019
     * アクション名：投信積立設定
     * Dto リクエスト：IfaHoldingSecurityListA019DtoRequest
     * Dto レスポンス：IfaHoldingSecurityListA019DtoResponse
     * model リクエスト：IfaHoldingSecurityListA019RequestModel
     * model レスポンス：IfaHoldingSecurityListA019ResponseModel
     *
     * @param dtoReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @throws Exception 選択された投信銘柄の目論見書未閲覧した場合
     */
    public DataList<IfaHoldingSecurityListA019ResponseDto> fundAccumulationA019(
            IfaHoldingSecurityListA019RequestDto dtoReq) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaHoldingSecurityListServiceImplL.fundAccumulationA019");
        }
        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 協会コードの取得
        String fundCode = dtoReq.getFundCode();
        // 投信閲覧履歴一括照会を取得する。
        GetFundFundDocReadHistoryBulkReq req = new GetFundFundDocReadHistoryBulkReq();

        // ファンド一覧
        FundDocReadHistoryBulkApiIn apiIn = new FundDocReadHistoryBulkApiIn();
        List<FundDocReadHistoryBulkItem> funds = new ArrayList<>();
        FundDocReadHistoryBulkItem item = new FundDocReadHistoryBulkItem();
        item.setFundCode(fundCode); // ファンド一覧.ファンドタイプ
        item.setFundType(" "); // ファンド一覧.協会コード
        funds.add(item);

        // FundDocReadHistoryBulkApiIn parameter
        apiIn.setFunds(funds); // ファンド一覧
        apiIn.setDataOutputKbn("1"); // データ出力区分

        req.setParameter(apiIn); // FundDocReadHistoryBulkApiIn parameter
        req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));  // トークン, 顧客共通情報.部店コード+"-"+顧客共通情報.口座番号

        GetFundFundDocReadHistoryBulkRes getFundDocReadHistoryBulkres;
        Boolean continueFlg = false;
        try {
            getFundDocReadHistoryBulkres = safeFundTradeService.getBulkFundDocReadHistory(req);
        } catch (Exception e) {
            // 取得エラーを返却する
        	return safeCommonService.checkSafeBussinessException(List.of(), e);
        }

        if (getFundDocReadHistoryBulkres.getFundDocReadHistoryBulkApiOut().getFunds() != null
                && !CollectionUtils.isEmpty(getFundDocReadHistoryBulkres.getFundDocReadHistoryBulkApiOut().getFunds())) {
            for (FundDocReadHistoryBulk fund : getFundDocReadHistoryBulkres.getFundDocReadHistoryBulkApiOut().getFunds()) {
                // ファンドタイプが汎用累投かつ 目論見書閲覧区分が0(閲覧済・郵送完了)が存在の場合、次の処理へ
                if (StringUtils.equals(fund.getFundType(), FundType.CUMULATIVE_PITCH.getValue())
                        && StringUtils.equals(fund.getProspectus(), Prospectus.READ.getValue())) {
                    continueFlg = true;
                    break;
                }
            }
        }

        // 上記以外：投信目論見書交付チェックエラーを返す。
        if (!continueFlg) {
            // 取得エラーを返却する
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, API008_MESSAGE_NO_ISSURANCE_RECORD,
                    IfaCommonUtil.getMessage(API008_MESSAGE_NO_ISSURANCE_RECORD, new String[]{}));
        }
        
        DataList<IfaHoldingSecurityListA019ResponseDto> dtoRes = IfaCommonUtil.createDataList(List.of(), ErrorLevel.SUCCESS, RETURN_CODE_SUCCESSFULL,
                RETURN_MESSEAGE_SUCCESS);

        return dtoRes;

    }
    
    /**
     * FCT001の呼び出し
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return FCT001レスポンス
     */
    private OutputFct001Dto callFct001(String butenCode, String accountNumber) {
        
        //  ①   利用者の口座に対する権限チェックを行う。
        //  権限あり：次の処理へ。
        //  権限なし：権限なしエラーを返す。
        InputFct001Dto fct001InData = new InputFct001Dto();
        fct001InData.setButenCode(butenCode);
        fct001InData.setAccountNumber(accountNumber);
        OutputFct001Dto fct001Dto = fct001.doCheck(fct001InData);
        
        return fct001Dto;
    }
    
    /**
     * FCT003の呼び出し
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return FCT001レスポンス
     */
    private OutputFct003Dto callFct003(String butenCode, String accountNumber) {
        
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(butenCode);
        inputFct003Dto.setAccountNumber(accountNumber);
        OutputFct003Dto fct003Dto = fct003.doCheck(inputFct003Dto);
        
        return fct003Dto;
    }
    
    /**
     * API007の呼び出し
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return API007レスポンス
     * @throws Exception API007の呼び出しで例外が発生した場合
     */
    private QuerySaleLimitOutData callApi007(String butenCode, String accountNumber, ApiErrorUtil apiErrorUtil)
            throws Exception {
        
        // API NRI_QuerySaleLimit の呼び出し。
        QuerySaleLimitInData vec = new QuerySaleLimitInData();
        vec.setButenCd(butenCode);
        vec.setKozaNo(StringUtil.fillLeft(accountNumber, '0', 7));
        NextKeyInfoIn nextKeyInfoIn = new NextKeyInfoIn();
        vec.setNextKeyInfoIn(nextKeyInfoIn);
        vec.getNextKeyInfoIn().setButenCdN("   ");
        vec.getNextKeyInfoIn().setKozaNoN("       ");
        vec.getNextKeyInfoIn().setBrandCdN("     ");
        vec.getNextKeyInfoIn().setKaisuN("    ");
        vec.getNextKeyInfoIn().setGou1N(" ");
        vec.getNextKeyInfoIn().setGou2N("  ");
        vec.getNextKeyInfoIn().setHitokuteiKbnN(" ");
        vec.getNextKeyInfoIn().setQualifiedClltrlN(" ");
        vec.getNextKeyInfoIn().setSeqNoN("   ");
        vec.setAccountGetKbn(" ");  
        
        QuerySaleLimitIn api007Input = new QuerySaleLimitIn();
        api007Input.setIndata(vec);
        QuerySaleLimitOutData api007Output = apiwrapper.querySaleLimit(api007Input);
        apiErrorUtil.checkApiResponse(api007Output.getShubetu(), api007Output.getCode(), api007Output.getMessage());
        
        return api007Output;
    }
    
    /**
     * 保有商品情報の作成（国内株式、投資信託、国内債券、外国債券（円貨建）、その他商品）
     *
     * @param domesticStockMap      国内株式情報管理マップ
     * @param investmentTrustMap    投資信託情報管理マップ
     * @param domesticBondsMap      国内債券情報管理マップ
     * @param foreignBondsMap       外国債券（円貨建）情報管理マップ
     * @param foreignStocksMap      外国株式情報管理マップ
     * @param otherSecurityMap      その他商品情報管理マップ
     * @param fct001Dto FCT001レスポンス
     * @param fct003Dto FCT003レスポンス
     * @param accountGetKbn 口座区分（初期表示の場合はnull）
     * @param secType 商品タイプ（初期表示の場合はnull）
     * @param apiErrorUtil　エラーハンドリング
     */
    private synchronized void createHoldingSecurityInfo1(
            Map<String, IfaHoldingSecurityListResponseDtoDomesticStock> domesticStockMap,
            Map<String, IfaHoldingSecurityListResponseDtoInvestmentTrust> investmentTrustMap,
            Map<String, IfaHoldingSecurityListResponseDtoDomesticBonds> domesticBondsMap,
            Map<String, IfaHoldingSecurityListResponseDtoForeignBonds> foreignBondsMap,
            Map<String, IfaHoldingSecurityListResponseDtoOtherSecurity> otherSecurityMap, OutputFct001Dto fct001Dto,
            OutputFct003Dto fct003Dto, String accountGetKbn, String secType, ApiErrorUtil apiErrorUtil)
            throws Exception {
        
        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        // SQL006_顧客口座属性テーブルからISA買付可能判定区分（当年、翌年）を取得
        IfaHoldingSecurityListSql006RequestModel sql006Req = new IfaHoldingSecurityListSql006RequestModel();
        sql006Req.setCustomerCode(cc.getCustomerCode());
        IfaHoldingSecurityListSql006ResponseModel sql006Res = new IfaHoldingSecurityListSql006ResponseModel();
        try {
            sql006Res = dao.selectIfaHoldingSecurityListSql006(sql006Req);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("IfaHoldingSecurityListServiceImplL.initializeA001.SQL006");
            }
            throw e;
        }
        
        // API NRI_QuerySaleLimit の呼び出し。
        QueryAccountPositionSumWebInData api001Input = new QueryAccountPositionSumWebInData();
        api001Input.setButenCd(butenCode.substring(0, 3));
        api001Input.setKozaNo(StringUtil.fillLeft(accountNumber, '0', 7));
        api001Input.setRequestType(" ");
        ;
        
        //④預り残高一覧検索FROM：1
        //　預り残高一覧検索TO：100
        // 預り残高一覧リクエストFROM値：検索FROMに5桁目まで0埋めした値を設定
        // 預り残高一覧リクエストTO値：検索TOに5桁目まで0埋めした値を設定
        api001Input.setRefFrom("00001");
        api001Input.setRefTo("00100");
        
        // ⑤   "APIのパラメータを設定する。
        if (secType == null) {
            // ・API入力.商品タイプ='  '(全て)
            api001Input.setSecType("  ");
        } else if (secType.length() == 0) {
            return;
        } else {
            api001Input.setSecType(secType);
        }
        
        // ・顧客共通情報.ジュニアISA契約区分='1'(契約)の場合
        //   API入力.取得口座区分='2'(JrNISA口座(第一、第二口座両方))
        //   顧客共通情報.ジュニアISA契約区分=' '(未契約),'9'(閉鎖済)の場合
        //   API入力.取得口座区分=' '(通常口座およびJrNISA口座の第一口座)"
        if (accountGetKbn == null) {
            String jrIsaContractType = cc.getJrIsaContractType();
            if (StringUtils.equals(jrIsaContractType, "1") == true) {
                
                api001Input.setAccountGetKbn("2");
            } else if (StringUtils.equals(jrIsaContractType, " ") == true
                    || StringUtils.equals(jrIsaContractType, "9")) {
                
                api001Input.setAccountGetKbn(" ");
            }
        } else {
            api001Input.setAccountGetKbn(accountGetKbn);
        }
        
        // ⑤APIから顧客の預り残高リストを取得する。(API001)
        List<QueryAccountPositionSumWebOutData> api001OutList = apiwrapper.queryAccountPositionSumWeb(api001Input);
        Boolean isFatal = false;
        for (QueryAccountPositionSumWebOutData api001Res : api001OutList) {
            if (apiErrorUtil.isError(api001Res.getShubetu(), api001Res.getCode(), api001Res.getMessage())) {
                isFatal = true;
            }
        }
        if (isFatal) {
            return;
        }
        
        //  APIの戻りが該当無しの場合、⑧の外国株式の預り明細取得処理へ進む。
        //  APIの戻りがある場合、次の処理へ進む
        
        //  以下の処理については、ApiWapper側で行うためサーバでの処理は不要
        //  ⑥ 検索結果総数＝100の場合、
        //  預り残高一覧検索FROM＋100をセットする。
        //  預り残高一覧検索TO＋100をセットする。
        //    ⑤へ戻る
        //  検索結果総数＜100の場合、
        //    次の処理へ進む"
        
        if (api001OutList != null && api001OutList.size() > 0) {

            for (QueryAccountPositionSumWebOutData api001Out : api001OutList) {
                
                if (ObjectUtils.isEmpty(api001Out.getAccountSumWebData())) {
                    break;
                }
                
                for (AccountSumWebData api001OutSub : api001Out.getAccountSumWebData()) {
                    
                    // 画面表示種別の取得
                    DisplayType displayType = this.getDisplayType(api001OutSub, cc);
                    
                    if (displayType == null) {
                        continue;
                    }
                    
                    // ⑦   取得した全明細の銘柄について商品ごとに以下を取得する。
                    //      国内株式の明細となる条件は、API001.商品タイプ名＝'国内株式'およびAPI001.商品区分＝'K'(株式（国内/外国）)
                    if (this.isDomesticStock(api001OutSub) == true) {
                        
                        //    国内株式：現在値
                        //      明細ごとにSQLを発行して、銘柄の優先市場を取得する。
                        IfaHoldingSecurityListSql005RequestModel selSql005Req = new IfaHoldingSecurityListSql005RequestModel();
                        
                        //株式/投信ﾌﾗｸﾞに'0'：株式 を設定
                        selSql005Req.setIpmProductFlag("0");
                        selSql005Req.setIpmProductCode(
                                api001OutSub.getCompanyCode().substring(0, 4) + api001OutSub.getNewOldId());
                        selSql005Req.setIpmPriExFlag("1");
                        
                        DataList<IfaHoldingSecurityListSql005ResponseModel> selSql005ResList = dao
                                .selectIfaHoldingSecurityListSql005(selSql005Req);
                        
                        //国内株式 預り明細情報の取得
                        IfaHoldingSecurityListResponseDtoDomesticStockDepositDetail detail = this
                                .createDomesticStockDetail(api001OutSub, displayType);

                        // FCT020.評価用現在値
                        String currentValueForEvaluation = null;
                        if (selSql005ResList.getDataList().size() > 0) {
                            String ipmSeInvestmentsCode = selSql005ResList.get(0).getIpmSeInvestmentsCode();
                            
                            // 明細ごとに共通関数を実行して現在値を取得する。
                            // 共通関数入力.銘柄コード：国内株式リスト.預り明細リスト.銘柄コード
                            // 共通関数入力.市場コード：SQL005で取得した、最上位上場市場コード
                            InputFct020Dto fct020Req = new InputFct020Dto();
                            fct020Req.setBrandCode(detail.getBrandCode().trim());
                            fct020Req.setMarketCode(ipmSeInvestmentsCode.trim());
                            OutputFct020Dto fct020Res = new OutputFct020Dto();
                            fct020Res = fct020.getData(fct020Req);
                            currentValueForEvaluation = fct020Res.getCurrentValueForEvaluation();
                        }

                        // 現在値
                        //  FCT020.評価用現在値
                        //  ※小数点第3位以下切捨て
                        BigDecimal wkCurrentPrice = BigDecimal.ZERO;
                        if (!StringUtil.isNullOrEmpty(currentValueForEvaluation)) {
                            wkCurrentPrice = new BigDecimal(currentValueForEvaluation.trim());
                            detail.setCurrentPrice(wkCurrentPrice.setScale(2, RoundingMode.DOWN).toPlainString());
                        }
                        
                        // 評価額
                        // API001.残高数量 × API002.現在値
                        // ※計算結果の小数点第3位を四捨五入
                        BigDecimal wkPosition = new BigDecimal(api001OutSub.getPosition());
                        if (!StringUtil.isNullOrEmpty(currentValueForEvaluation)) {
                            BigDecimal valuation = wkCurrentPrice.multiply(wkPosition);
                            detail.setValuation(valuation.setScale(2, RoundingMode.HALF_UP).toPlainString());
                        }

                        // 評価損益
                        if (!StringUtil.isNullOrEmpty(currentValueForEvaluation)) {
                            BigDecimal domesticStockListValuation = null;
                            if (displayType == DisplayType.OPEN_TOTAL_DMSTC_STK_SPECIFIC
                                    || displayType == DisplayType.OPEN_TOTAL_DMSTC_STK_NISA
                                    || displayType == DisplayType.OPEN_TOTAL_DMSTC_STK_OLD_NISA
                                    || displayType == DisplayType.OPEN_JRNISA_DMSTC_STK_SPECIFIC
                                    || displayType == DisplayType.OPEN_JRNISA_DMSTC_STK_NISA
                                    || displayType == DisplayType.OPEN_JRNISA_DMSTC_STK_OLD_NISA
                                    || displayType == DisplayType.CLOSE_TOTAL_DMSTC_STK_SPECIFIC
                                    || displayType == DisplayType.CLOSE_TOTAL_DMSTC_STK_NISA
                                    || displayType == DisplayType.CLOSE_TOTAL_DMSTC_STK_OLD_NISA) {
                                
                                // 特定、旧NISA、NISA（成長投資枠）、ジュニアNISA（継続管理勘定）
                                //
                                //  (API002.現在値 - API001.移動平均単価) × API001.残高数量
                                // ※計算結果の小数点第3位を四捨五入
                                
                                domesticStockListValuation = wkCurrentPrice
                                        .subtract(new BigDecimal(api001OutSub.getAveragePrice()));
                                domesticStockListValuation = domesticStockListValuation
                                        .multiply(new BigDecimal(api001OutSub.getPosition()));
                                domesticStockListValuation = domesticStockListValuation.setScale(2, RoundingMode.HALF_UP);
                                
                            } else if (displayType == DisplayType.OPEN_TOTAL_DMSTC_STK_GENERAL
                                    || displayType == DisplayType.OPEN_JRNISA_DMSTC_STK_GENERAL
                                    || displayType == DisplayType.CLOSE_TOTAL_DMSTC_STK_GENERAL) {
                                
                                // 一般
                                //
                                // (API002.現在値 - API001.加重平均単価) × API001.残高数量
                                // ※計算結果の小数点第3位を四捨五入
                                domesticStockListValuation = wkCurrentPrice
                                        .subtract(new BigDecimal(api001OutSub.getWghAveragePrice()));
                                domesticStockListValuation = domesticStockListValuation
                                        .multiply(new BigDecimal(api001OutSub.getPosition()));
                                domesticStockListValuation = domesticStockListValuation.setScale(2, RoundingMode.HALF_UP);
                            }
                            detail.setDomesticStockListValuation(domesticStockListValuation.toPlainString());
                        }

                        // 現買表示区分
                        if (displayType == DisplayType.OPEN_TOTAL_DMSTC_STK_SPECIFIC
                                || displayType == DisplayType.OPEN_JRNISA_DMSTC_STK_SPECIFIC
                                || displayType == DisplayType.CLOSE_TOTAL_DMSTC_STK_SPECIFIC) {
                            
                            //特定
                            if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true && isMediatePropriety(
                                    fct003Dto,
                                    IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_DOMESTIC_STOCK,
                                    IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_BUYING, null, null) == true) {
                                
                                // FCT001.取引停止フラグ = '0':取引停止口座ではない　かつ
                                // FCT003.媒介可否リスト.証券金銭種別 =' 国内株式'　かつ
                                // FCT003.媒介可否リスト.取引種別 ='1'(現物買付)　かつ
                                // FCT003.媒介可否リスト.媒介可否 = '1'(:媒介可)の場合
                                detail.setBuyDisplayClassification("1");
                            } else {
                                detail.setBuyDisplayClassification("0");
                            }
                            
                        } else if (displayType == DisplayType.OPEN_TOTAL_DMSTC_STK_OLD_NISA
                                || displayType == DisplayType.OPEN_JRNISA_DMSTC_STK_OLD_NISA
                                || displayType == DisplayType.CLOSE_TOTAL_DMSTC_STK_OLD_NISA) {
                            
                            //旧NISA
                            if (StringUtils.equals(sql006Res.getIsaBuyAbleThisYear(), "1")
                                    && StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true
                                    && isMediatePropriety(fct003Dto,
                                            IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_DOMESTIC_STOCK,
                                            IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_BUYING, null,
                                            null) == true) {
                                
                                // 顧客共通情報.ISA買付可能判定区分（当年）='1'(買付可能)の場合 かつ
                                // FCT001.取引停止フラグ = '0':取引停止口座ではない　かつ
                                // FCT003.媒介可否リスト.証券金銭種別 =' 国内株式'　かつ
                                // FCT003.媒介可否リスト.取引種別 = '1'(現物買付)　かつ
                                // FCT003.媒介可否リスト.媒介可否 = '1'(:媒介可)の場合
                                detail.setBuyDisplayClassification("1");
                            } else {
                                detail.setBuyDisplayClassification("0");
                            }
                            
                        } else if (displayType == DisplayType.OPEN_TOTAL_DMSTC_STK_NISA
                                || displayType == DisplayType.CLOSE_TOTAL_DMSTC_STK_NISA) {
                            
                            // NISA（成長投資枠）
                            if ((StringUtils.equals(sql006Res.getIsaBuyAbleThisYear(), "2")
                                    || StringUtils.equals(sql006Res.getIsaBuyAbleNextYear(), "2"))
                                    && StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true
                                    && isMediatePropriety(fct003Dto,
                                            IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_DOMESTIC_STOCK,
                                            IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_BUYING, null,
                                            null) == true) {
                                
                                // ①顧客共通情報.ISA買付可能判定区分（当年）が"2" or ISA買付可能判定区分（翌年）が"2"の場合 かつ
                                // FCT001.取引停止フラグ = '0':取引停止口座ではない　かつ
                                // FCT003.媒介可否リスト.証券金銭種別 =' 国内株式'　かつ
                                // FCT003.媒介可否リスト.取引種別 = '1'(現物買付)　かつ
                                // FCT003.媒介可否リスト.媒介可否 = '1'(:媒介可)の場合
                                detail.setBuyDisplayClassification("1");
                            } else {
                                detail.setBuyDisplayClassification("0");
                            }
                            
                        } else if (displayType == DisplayType.OPEN_JRNISA_DMSTC_STK_NISA) {
                            // ジュニアNISA（継続管理勘定）
                            
                            // 　'0'(非表示)を設定
                            detail.setBuyDisplayClassification("0");
                        } else if (displayType == DisplayType.OPEN_TOTAL_DMSTC_STK_GENERAL
                                || displayType == DisplayType.OPEN_JRNISA_DMSTC_STK_GENERAL
                                || displayType == DisplayType.CLOSE_TOTAL_DMSTC_STK_GENERAL) {
                            // 一般
                            
                            if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true && isMediatePropriety(
                                    fct003Dto,
                                    IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_DOMESTIC_STOCK,
                                    IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_BUYING, null, null) == true) {
                                // FCT001.取引停止フラグ = '0':取引停止口座ではない　かつ
                                // FCT003.媒介可否リスト.証券金銭種別 =' 国内株式'　かつ
                                // FCT003.媒介可否リスト.取引種別 = '1'(現物買付)　かつ
                                // FCT003.媒介可否リスト.媒介可否 = '1'(:媒介可)の場合
                                detail.setBuyDisplayClassification("1");
                            } else {
                                detail.setBuyDisplayClassification("0");
                            }
                        }
                        
                        //現売表示区分
                        if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true && isMediatePropriety(
                                fct003Dto, IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_DOMESTIC_STOCK,
                                IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_SELLING, null, null) == true) {
                            
                            // FCT001.取引停止フラグ = '0':取引停止口座ではない　かつ
                            // FCT003.媒介可否リスト.証券金銭種別 =' 国内株式'　かつ
                            // FFCT003.媒介可否リスト.取引種別 = '2'(現物売却)　かつ
                            // FCT003.媒介可否リスト.媒介可否 = '1'(:媒介可)の場合
                            if ((wkPosition.subtract(new BigDecimal(api001OutSub.getOrderedQuantity())))
                                    .compareTo(BigDecimal.ZERO) == -1) {
                                
                                // API001.残高数量-API001.明細部.売却発注済数量 <= 0の場合
                                detail.setSaleDisplayClassification("3");
                            } else {
                                detail.setSaleDisplayClassification("1");
                            }
                        } else {
                            detail.setSaleDisplayClassification("0");
                        }
                        
                        //国内株式情報の取得
                        IfaHoldingSecurityListResponseDtoDomesticStock domesticStock = this
                                .getDomesticStock(domesticStockMap, displayType);
                        
                        // 預り銘柄数の設定
                        int numberOfDepositedIssues = Integer.parseInt(domesticStock.getNumberOfDepositedIssues());
                        numberOfDepositedIssues++;
                        domesticStock.setNumberOfDepositedIssues(Integer.toString(numberOfDepositedIssues));
                        
                        // 評価額合計の設定
                        if (!StringUtil.isNullOrEmpty(detail.getValuation())) { 
                            BigDecimal valuationTotal = new BigDecimal(domesticStock.getValuationTotal());
                            valuationTotal = valuationTotal.add(new BigDecimal(detail.getValuation()));
                            domesticStock.setValuationTotal(valuationTotal.toPlainString());
                        }

                        // 預り銘柄数評価損益合計 の設定
                        if (!StringUtil.isNullOrEmpty(detail.getDomesticStockListValuation())) { 
                            BigDecimal getProfitAll = new BigDecimal(domesticStock.getGetProfitAll());
                            getProfitAll = getProfitAll.add(new BigDecimal(detail.getDomesticStockListValuation()));
                            domesticStock.setGetProfitAll(getProfitAll.toPlainString());
                        }

                        //国内株式情報に預り明細情報を追加
                        List<IfaHoldingSecurityListResponseDtoDomesticStockDepositDetail> detailList = null;
                        if ((detailList = domesticStock.getDepositDetailList()) == null) {
                            detailList = new ArrayList<IfaHoldingSecurityListResponseDtoDomesticStockDepositDetail>();
                        }
                        detailList.add(detail);
                        domesticStock.setDepositDetailList(detailList);
                        
                        //投資信託
                    } else if (this.isInvestmentTrust(api001OutSub) == true) {
                        
                        //    投資信託：基準価額
                        //      明細ごとに共通関数を実行して基準価格を取得する。
                        //        共通関数入力.銘柄リスト.NRIコード=API001.明細部.回数＋' '(半角スペース)＋明細部.号2
                        //        投資信託の明細となる条件は、API001.商品タイプ名＝'国内投信'および
                        //        API001.商品区分＝'T'(投信(国内(一般型)/外国))または'Y'(国内投信(汎用累投))
                        //    投資信託：基準価額単位、協会コード
                        //    投資信託：書類コード.受入状況
                        //      明細ごとに共通関数を実行して書類コード.受入状況を取得する。
                        
                        String serNo = (api001OutSub.getSerNo() != null) ? api001OutSub.getSerNo() : "";
                        String subCode2 = (api001OutSub.getSubCode2() != null) ? api001OutSub.getSubCode2() : "";
                        
                        InputFct017Dto inputFct017Dto = new InputFct017Dto();
                        inputFct017Dto.setButenCode(butenCode);
                        inputFct017Dto.setAccountNumber(accountNumber);
                        
                        List<InputFct017Dto.InquiryMutualFundBrand> inquiryMutualFundBrandList = new ArrayList<InputFct017Dto.InquiryMutualFundBrand>();
                        InputFct017Dto.InquiryMutualFundBrand inquiryMutualFundBrand = new InputFct017Dto.InquiryMutualFundBrand();
                        inquiryMutualFundBrand.setNriCd(serNo + " " + subCode2);
                        inquiryMutualFundBrandList.add(inquiryMutualFundBrand);
                        inputFct017Dto.setInquiryMutualFundBrandList(inquiryMutualFundBrandList);
                        
                        OutputFct017Dto fct017Dto = fct017.getData(inputFct017Dto);
                        OutputFct017Dto.Brand brand = fct017Dto.getBrandList().get(0);
                        
                        //投資信託 預り明細情報の作成
                        IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail detail = this
                                .createInvestmentTrustDepositDetail(api001OutSub, displayType);
                        
                        IfaHoldingSecurityListSql002RequestModel selSql002Req = new IfaHoldingSecurityListSql002RequestModel();
                        selSql002Req.setBrandCode(String.format("%-8s", serNo + " " + subCode2));
                        selSql002Req.setNriCd(api001OutSub.getSerNo() + " " + api001OutSub.getSubCode2());
                        DataList<IfaHoldingSecurityListSql002ResponseModel> selSql002Res = dao
                                .selectIfaHoldingSecurityListSql002(selSql002Req);
                        
                        // 基準価額単位
                        String basePriceUnit = (selSql002Res.size() > 0) ? selSql002Res.get(0).getBasePriceUnit() : "0";
                        // 協会コード
                        String kyoukaiCd = (selSql002Res.size() > 0) ? selSql002Res.get(0).getKyoukaiCd() : null;
                        
                        IfaHoldingSecurityListSql011RequestModel selSql011Req = new IfaHoldingSecurityListSql011RequestModel();
                        selSql011Req.setKyoukaiCd(kyoukaiCd);
                        DataList<IfaHoldingSecurityListSql011ResponseModel> selSql011Res = dao
                                .selectIfaHoldingSecurityListSql011(selSql011Req);

                        //基準価額
                        BigDecimal basePrice = (selSql011Res.size() > 0 && !StringUtil.isNullOrEmpty(selSql011Res.get(0).getBasePrice()) && !selSql011Res.get(0).getBasePrice().trim().isEmpty())
                                ? new BigDecimal(selSql011Res.get(0).getBasePrice())
                                : BigDecimal.ZERO;
                        
                        switch (displayType) {
                            case OPEN_TOTAL_TNVSTMT_TRST_UNIT_SPECIFIC: //投資信託・口数・総合(開設済)・特定
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceSpecific(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(this
                                        .getInvTrustAcquireAmountReferenceAmountSpecific(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationUnitSpecific(
                                        api001OutSub, brand, fct001Dto, fct003Dto));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(
                                        this.getInvTrustSaleDisplayClassificationUnitSpecific(api001OutSub, fct001Dto,
                                                fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(null);
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification("0");
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                
                                break;
                            case OPEN_TOTAL_TNVSTMT_TRST_UNIT_GENERAL: //投資信託・口数・総合(開設済)・一般
                                
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceGeneral(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(this
                                        .getInvTrustAcquireAmountReferenceAmountGeneral(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationUnitSpecific(
                                        api001OutSub, brand, fct001Dto, fct003Dto));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(
                                        this.getInvTrustSaleDisplayClassificationUnitSpecific(api001OutSub, fct001Dto,
                                                fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(null);
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification("0");
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                
                                break;
                            case OPEN_TOTAL_TNVSTMT_TRST_UNIT_NISA: //投資信託・口数・総合(開設済)・NISA(成長投資枠)
                            case OPEN_TOTAL_TNVSTMT_TRST_UNIT_EX_NISA: //投資信託・口数・総合(開設済)・NISA(つみたて投資枠)
                            case OPEN_TOTAL_TNVSTMT_TRST_UNIT_OLD_NISA: //投資信託・口数・総合(開設済)・旧NISA
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceNisa(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(
                                        this.getInvTrustAcquireAmountReferenceAmountNisa(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification("0");
                                // 売却表示区分
                                detail.setSaleDisplayClassification("0");
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(null);
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification("0");
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                break;
                            case OPEN_TOTAL_TNVSTMT_TRST_UNIT_OLD_EX_NISA://投資信託・口数・総合(開設済)・旧つみたてNISA
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceNisa(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(
                                        this.getInvTrustAcquireAmountReferenceAmountNisa(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification("0");
                                // 売却表示区分
                                detail.setSaleDisplayClassification("0");
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(null);
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification("0");
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                break;
                            case OPEN_TOTAL_TNVSTMT_TRST_AMNT_SPECIFIC: //投資信託・金額・総合(開設済)・特定
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceSpecific(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(this
                                        .getInvTrustAcquireAmountReferenceAmountSpecific(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationAmntSpecific(
                                        api001OutSub, brand, fct001Dto, fct003Dto));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(this
                                        .getInvTrustSaleDisplayClassificationAmnt(api001OutSub, fct001Dto, fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(
                                        this.getInvTrustDistributionReceiveMethodAmnt(api001OutSub));
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification(
                                        this.getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(
                                                api001OutSub));
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                break;
                            
                            case OPEN_TOTAL_TNVSTMT_TRST_AMNT_GENERAL: //投資信託・金額・総合(開設済)・一般
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceGeneral(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(this
                                        .getInvTrustAcquireAmountReferenceAmountGeneral(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationAmntSpecific(
                                        api001OutSub, brand, fct001Dto, fct003Dto));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(this
                                        .getInvTrustSaleDisplayClassificationAmnt(api001OutSub, fct001Dto, fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(
                                        this.getInvTrustDistributionReceiveMethodAmnt(api001OutSub));
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification(
                                        this.getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(
                                                api001OutSub));
                                
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                break;
                            case OPEN_TOTAL_TNVSTMT_TRST_AMNT_NISA: //投資信託・金額・総合(開設済)・NISA
                                
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceNisa(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(
                                        this.getInvTrustAcquireAmountReferenceAmountNisa(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationAmntNisa(
                                        api001OutSub, brand, fct001Dto, fct003Dto, sql006Res));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(this
                                        .getInvTrustSaleDisplayClassificationAmnt(api001OutSub, fct001Dto, fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(
                                        this.getInvTrustDistributionReceiveMethodAmnt(api001OutSub));
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification(
                                        this.getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(
                                                api001OutSub));
                                
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                break;
                            case OPEN_TOTAL_TNVSTMT_TRST_AMNT_EX_NISA: //投資信託・金額・総合(開設済)・NISA(つみたて投資枠)
                                
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceNisa(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(
                                        this.getInvTrustAcquireAmountReferenceAmountNisa(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification("0");
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(this
                                        .getInvTrustSaleDisplayClassificationAmnt(api001OutSub, fct001Dto, fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(
                                        this.getInvTrustDistributionReceiveMethodAmnt(api001OutSub));
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification(
                                        this.getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(
                                                api001OutSub));
                                
                                // 積立表示区分
                                if (StringUtils.equals(brand.getAcceptanceNecessity(),
                                        IfaHoldingSecurityListServiceImpL.FCT017_ACCEPTANCE_NECESSITY_AVAILABLE) == false) {
                                    if (api001OutSub.getSerNo() == null && api001OutSub.getSubCode1() == null
                                            && api001OutSub.getSubCode2() == null) {
                                        detail.setAccumulationLink("0");
                                    } else {
                                        if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true
                                                && isMediatePropriety(fct003Dto,
                                                        IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_INVESTMENT_TRUST,
                                                        IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_BUY_ACCUMULATION,
                                                        null, null) == true) {
                                            detail.setAccumulationLink("1");
                                        } else {
                                            detail.setAccumulationLink("0");
                                        }
                                    }
                                } else {
                                    detail.setAccumulationLink("0");
                                }
                                
                                break;
                            case OPEN_TOTAL_TNVSTMT_TRST_AMNT_OLD_NISA: //投資信託・金額・総合(開設済)・旧NISA
                                
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceNisa(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(
                                        this.getInvTrustAcquireAmountReferenceAmountNisa(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationAmntOldNisa(
                                        api001OutSub, brand, fct001Dto, fct003Dto, sql006Res));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(this
                                        .getInvTrustSaleDisplayClassificationAmnt(api001OutSub, fct001Dto, fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(
                                        this.getInvTrustDistributionReceiveMethodAmnt(api001OutSub));
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification(
                                        this.getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(
                                                api001OutSub));
                                
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                break;
                            case OPEN_TOTAL_TNVSTMT_TRST_AMNT_OLD_EX_NISA: //投資信託・金額・総合(開設済)・旧つみたてNISA
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceNisa(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(
                                        this.getInvTrustAcquireAmountReferenceAmountNisa(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification("0");
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(this
                                        .getInvTrustSaleDisplayClassificationAmnt(api001OutSub, fct001Dto, fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(
                                        this.getInvTrustDistributionReceiveMethodAmnt(api001OutSub));
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification(
                                        this.getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(
                                                api001OutSub));
                                
                                // 積立表示区分
                                if (StringUtils.equals(brand.getAcceptanceNecessity(),
                                        IfaHoldingSecurityListServiceImpL.FCT017_ACCEPTANCE_NECESSITY_AVAILABLE) == false) {
                                if (api001OutSub.getSerNo() == null && api001OutSub.getSubCode1() == null
                                        && api001OutSub.getSubCode2() == null) {
                                    detail.setAccumulationLink("0");
                                } else {
                                    if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true
                                            && isMediatePropriety(fct003Dto,
                                            IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_INVESTMENT_TRUST,
                                            IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_BUY_ACCUMULATION,
                                            null, null) == true) {
                                         detail.setAccumulationLink("1");
                                    } else {
                                        detail.setAccumulationLink("0");
                                    }
                                }
                                } else {
                                detail.setAccumulationLink("0");
                                }
                                break;
                            case OPEN_JRNISA_TNVSTMT_TRST_UNIT_SPECIFIC: //投資信託・口数・ｼﾞｭﾆｱNISA・特定
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceSpecific(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(this
                                        .getInvTrustAcquireAmountReferenceAmountSpecific(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationUnitSpecific(
                                        api001OutSub, brand, fct001Dto, fct003Dto));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(
                                        this.getInvTrustSaleDisplayClassificationUnitSpecific(api001OutSub, fct001Dto,
                                                fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(null);
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification("0");
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                break;
                            case OPEN_JRNISA_TNVSTMT_TRST_UNIT_GENERAL: //投資信託・口数・ｼﾞｭﾆｱNISA・一般
                                
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceGeneral(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(this
                                        .getInvTrustAcquireAmountReferenceAmountGeneral(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationUnitSpecific(
                                        api001OutSub, brand, fct001Dto, fct003Dto));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(
                                        this.getInvTrustSaleDisplayClassificationUnitSpecific(api001OutSub, fct001Dto,
                                                fct003Dto));
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(null);
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification("0");
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                break;
                            case OPEN_JRNISA_TNVSTMT_TRST_UNIT_NISA: //投資信託・口数・ｼﾞｭﾆｱNISA・NISA預り（継続管理勘定）
                            case OPEN_JRNISA_TNVSTMT_TRST_UNIT_OLD_NISA://投資信託・口数・ｼﾞｭﾆｱNISA・NISA
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceNisa(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(
                                        this.getInvTrustAcquireAmountReferenceAmountNisa(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification("0");
                                // 売却表示区分
                                detail.setSaleDisplayClassification("0");
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(null);
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification("0");
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                break;
                            case OPEN_JRNISA_TNVSTMT_TRST_AMNT_SPECIFIC: //投資信託・金額・ｼﾞｭﾆｱNISA・特定
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceNisa(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(
                                        this.getInvTrustAcquireAmountReferenceAmountNisa(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationAmntSpecific(
                                        api001OutSub, brand, fct001Dto, fct003Dto));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(this
                                        .getInvTrustSaleDisplayClassificationAmnt(api001OutSub, fct001Dto, fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(
                                        this.getInvTrustDistributionReceiveMethodAmnt(api001OutSub));
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification(
                                        this.getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(
                                                api001OutSub));
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                
                                break;
                            case OPEN_JRNISA_TNVSTMT_TRST_AMNT_GENERAL: //投資信託・金額・ｼﾞｭﾆｱNISA・一般
                                
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceGeneral(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(this
                                        .getInvTrustAcquireAmountReferenceAmountGeneral(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationAmntSpecific(
                                        api001OutSub, brand, fct001Dto, fct003Dto));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(this
                                        .getInvTrustSaleDisplayClassificationAmnt(api001OutSub, fct001Dto, fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(
                                        this.getInvTrustDistributionReceiveMethodAmnt(api001OutSub));
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification(
                                        this.getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(
                                                api001OutSub));
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                
                                break;
                            case OPEN_JRNISA_TNVSTMT_TRST_AMNT_NISA: //投資信託・金額・ｼﾞｭﾆｱNISA・NISA預り（継続管理勘定）
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceNisa(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(
                                        this.getInvTrustAcquireAmountReferenceAmountNisa(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification("0");
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(this
                                        .getInvTrustSaleDisplayClassificationAmnt(api001OutSub, fct001Dto, fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(
                                        this.getInvTrustDistributionReceiveMethodAmnt(api001OutSub));
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification(
                                        this.getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(
                                                api001OutSub));
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                
                                break;
                            case OPEN_JRNISA_TNVSTMT_TRST_AMNT_OLD_NISA: //投資信託・金額・ｼﾞｭﾆｱNISA・NISA
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceNisa(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(
                                        this.getInvTrustAcquireAmountReferenceAmountNisa(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationAmntOldNisa(
                                        api001OutSub, brand, fct001Dto, fct003Dto, sql006Res));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(this
                                        .getInvTrustSaleDisplayClassificationAmnt(api001OutSub, fct001Dto, fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(
                                        this.getInvTrustDistributionReceiveMethodAmnt(api001OutSub));
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification(
                                        this.getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(
                                                api001OutSub));
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                
                                break;
                            case CLOSE_TOTAL_TNVSTMT_TRST_UNIT_SPECIFIC:// 投資信託・口数・総合(未開設)・特定・
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceNisa(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(
                                        this.getInvTrustAcquireAmountReferenceAmountNisa(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationUnitSpecific(
                                        api001OutSub, brand, fct001Dto, fct003Dto));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(
                                        this.getInvTrustSaleDisplayClassificationUnitSpecific(api001OutSub, fct001Dto,
                                                fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(null);
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification("0");
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                break;
                            
                            case CLOSE_TOTAL_TNVSTMT_TRST_UNIT_GENERAL: // 投資信託・口数・総合(未開設)・一般
                                
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceGeneral(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(this
                                        .getInvTrustAcquireAmountReferenceAmountGeneral(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationUnitSpecific(
                                        api001OutSub, brand, fct001Dto, fct003Dto));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(
                                        this.getInvTrustSaleDisplayClassificationUnitSpecific(api001OutSub, fct001Dto,
                                                fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(null);
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification("0");
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                break;
                            case CLOSE_TOTAL_TNVSTMT_TRST_UNIT_NISA: // 投資信託・口数・総合(未開設)・NISA(成長投資枠)
                            case CLOSE_TOTAL_TNVSTMT_TRST_UNIT_EX_NISA: // 投資信託・口数・総合(未開設)・NISA(つみたて投資枠)
                            case CLOSE_TOTAL_TNVSTMT_TRST_UNIT_OLD_NISA:// 投資信託・口数・総合(未開設)・旧NISA
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceNisa(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(
                                        this.getInvTrustAcquireAmountReferenceAmountNisa(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification("0");
                                // 売却表示区分
                                detail.setSaleDisplayClassification("0");
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(null);
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification("0");
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                break;
                            case CLOSE_TOTAL_TNVSTMT_TRST_UNIT_OLD_EX_NISA: // 投資信託・口数・総合(未開設)・旧つみたてNISA    
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceNisa(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(
                                        this.getInvTrustAcquireAmountReferenceAmountNisa(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification("0");
                                // 売却表示区分
                                detail.setSaleDisplayClassification("0");
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(null);
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification("0");
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                break;
                            
                            case CLOSE_TOTAL_TNVSTMT_TRST_AMNT_SPECIFIC:// 投資信託・金額・総合(未開設)・特定
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceNisa(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(
                                        this.getInvTrustAcquireAmountReferenceAmountNisa(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationAmntSpecific(
                                        api001OutSub, brand, fct001Dto, fct003Dto));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(this
                                        .getInvTrustSaleDisplayClassificationAmnt(api001OutSub, fct001Dto, fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(
                                        this.getInvTrustDistributionReceiveMethodAmnt(api001OutSub));
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification(
                                        this.getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(
                                                api001OutSub));
                                
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                break;
                            
                            case CLOSE_TOTAL_TNVSTMT_TRST_AMNT_GENERAL: // 投資信託・金額・総合(未開設)・一般
                                
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceGeneral(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(this
                                        .getInvTrustAcquireAmountReferenceAmountGeneral(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationAmntSpecific(
                                        api001OutSub, brand, fct001Dto, fct003Dto));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(this
                                        .getInvTrustSaleDisplayClassificationAmnt(api001OutSub, fct001Dto, fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(
                                        this.getInvTrustDistributionReceiveMethodAmnt(api001OutSub));
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification(
                                        this.getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(
                                                api001OutSub));
                                
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                break;
                            
                            case CLOSE_TOTAL_TNVSTMT_TRST_AMNT_NISA: // 投資信託・金額・総合(未開設)・NISA(成長投資枠)
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceSpecific(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(this
                                        .getInvTrustAcquireAmountReferenceAmountSpecific(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationAmntNisa(
                                        api001OutSub, brand, fct001Dto, fct003Dto, sql006Res));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(this
                                        .getInvTrustSaleDisplayClassificationAmnt(api001OutSub, fct001Dto, fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(
                                        this.getInvTrustDistributionReceiveMethodAmnt(api001OutSub));
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification(
                                        this.getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(
                                                api001OutSub));
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                
                                break;
                            
                            case CLOSE_TOTAL_TNVSTMT_TRST_AMNT_EX_NISA: // 投資信託・金額・総合(未開設)・NISA(つみたて投資枠)
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceSpecific(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(this
                                        .getInvTrustAcquireAmountReferenceAmountSpecific(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification("0");
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(this
                                        .getInvTrustSaleDisplayClassificationAmnt(api001OutSub, fct001Dto, fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(
                                        this.getInvTrustDistributionReceiveMethodAmnt(api001OutSub));
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification(
                                        this.getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(
                                                api001OutSub));
                                // 積立表示区分
                                if (StringUtils.equals(brand.getAcceptanceNecessity(),
                                        IfaHoldingSecurityListServiceImpL.FCT017_ACCEPTANCE_NECESSITY_AVAILABLE) == false) {
                                    if (api001OutSub.getSerNo() == null && api001OutSub.getSubCode1() == null
                                            && api001OutSub.getSubCode2() == null) {
                                        detail.setAccumulationLink("0");
                                    } else {
                                        if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true
                                                && isMediatePropriety(fct003Dto,
                                                        IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_INVESTMENT_TRUST,
                                                        IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_BUY_ACCUMULATION,
                                                        null, null) == true) {
                                            detail.setAccumulationLink("1");
                                        } else {
                                            detail.setAccumulationLink("0");
                                        }
                                    }
                                } else {
                                    detail.setAccumulationLink("0");
                                }
                                break;
                            
                            case CLOSE_TOTAL_TNVSTMT_TRST_AMNT_OLD_NISA: // 投資信託・金額・総合(未開設)・旧NISA
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceSpecific(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(this
                                        .getInvTrustAcquireAmountReferenceAmountSpecific(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification(this.getInvTrustBuyDisplayClassificationAmntOldNisa(
                                        api001OutSub, brand, fct001Dto, fct003Dto, sql006Res));
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(this
                                        .getInvTrustSaleDisplayClassificationAmnt(api001OutSub, fct001Dto, fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(
                                        this.getInvTrustDistributionReceiveMethodAmnt(api001OutSub));
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification(
                                        this.getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(
                                                api001OutSub));
                                
                                // 積立表示区分
                                detail.setAccumulationLink("0");
                                break;
                            
                            case CLOSE_TOTAL_TNVSTMT_TRST_AMNT_OLD_EX_NISA: // 投資信託・金額・総合(未開設)・旧つみたてNISA
                                // 取得単価/参考単価
                                detail.setAcquirePriceReferencePrice(
                                        this.getInvTrustAcquirePriceReferencePriceSpecific(api001OutSub));
                                
                                // 取得金額/参考金額
                                detail.setAcquireAmountReferenceAmount(this
                                        .getInvTrustAcquireAmountReferenceAmountSpecific(api001OutSub, basePriceUnit));
                                
                                // 評価額
                                detail.setValuation(this.getInvTrustValuation(api001OutSub, basePrice, basePriceUnit));
                                
                                // 評価損益
                                detail.setMutualFundListProfitLossTotal(this.getInvTrustMutualFundListProfitLossTotal(
                                        detail.getValuation(), detail.getAcquireAmountReferenceAmount()));
                                
                                // 買付表示区分
                                detail.setBuyDisplayClassification("0");
                                
                                // 売却表示区分
                                detail.setSaleDisplayClassification(this
                                        .getInvTrustSaleDisplayClassificationAmnt(api001OutSub, fct001Dto, fct003Dto));
                                
                                // 分配金受取方法
                                detail.setDistributionReceiveMethod(
                                        this.getInvTrustDistributionReceiveMethodAmnt(api001OutSub));
                                // 分配金受取方法変更表示区分
                                detail.setDistributionreceiveMethodchangedisplayclassification(
                                        this.getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(
                                                api001OutSub));
                                
                                // 積立表示区分
                                if (StringUtils.equals(brand.getAcceptanceNecessity(),
                                        IfaHoldingSecurityListServiceImpL.FCT017_ACCEPTANCE_NECESSITY_AVAILABLE) == false) {
                                    if (api001OutSub.getSerNo() == null && api001OutSub.getSubCode1() == null
                                            && api001OutSub.getSubCode2() == null) {
                                        detail.setAccumulationLink("0");
                                    } else {
                                        if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true
                                                && isMediatePropriety(fct003Dto,
                                                IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_INVESTMENT_TRUST,
                                                IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_BUY_ACCUMULATION,
                                                null, null) == true) {
                                            detail.setAccumulationLink("1");
                                        } else {
                                            detail.setAccumulationLink("0");
                                        }
                                    }
                                } else {
                                    detail.setAccumulationLink("0");
                                }
                                break;
                            
                            default:
                                break;
                        }
                        
                        // 基準価額
                        detail.setBasePrice8(basePrice.toString());
                        // 協会コード
                        // SQL002  協会コード
                        detail.setKyoukaiCd(kyoukaiCd);
                        
                        IfaHoldingSecurityListResponseDtoInvestmentTrust investmentTrust = this
                                .getInvestmentTrust(investmentTrustMap, displayType);
                        
                        // 預り銘柄数の設定
                        int numberOfDepositedIssues = Integer.parseInt(investmentTrust.getNumberOfDepositedIssues());
                        numberOfDepositedIssues++;
                        investmentTrust.setNumberOfDepositedIssues(Integer.toString(numberOfDepositedIssues));
                        
                        // 評価額合計の設定
                        BigDecimal valuationTotal = new BigDecimal(investmentTrust.getValuationTotal());
                        if (detail.getValuation() != null) {
                            valuationTotal = valuationTotal.add(new BigDecimal(detail.getValuation()));
                        }
                        investmentTrust.setValuationTotal(valuationTotal.toPlainString());
                        
                        // 評価損益合計 の設定
                        BigDecimal getProfitAll = new BigDecimal(investmentTrust.getGetProfitAll());
                        if (detail.getMutualFundListProfitLossTotal() != null) {
                            getProfitAll = getProfitAll.add(new BigDecimal(detail.getMutualFundListProfitLossTotal()));
                        }
                        investmentTrust.setGetProfitAll(getProfitAll.toPlainString());
                        
                        //投資信託情報に預り明細情報を追加
                        List<IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail> detailList = null;
                        if ((detailList = investmentTrust.getDepositDetailList()) == null) {
                            detailList = new ArrayList<IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail>();
                        }
                        detailList.add(detail);
                        investmentTrust.setDepositDetailList(detailList);
                        
                        //国内債券
                    } else if (this.isDomesticBonds(api001OutSub) == true) {
                        // 変数.銘柄コード1
                        String brandCode1 = null;
                        if (api001OutSub.getCompanyCode() == null || api001OutSub.getCompanyCode().trim().isEmpty()) {
                            brandCode1 = "0000";
                        } else {
                            String str = "0000" + api001OutSub.getCompanyCode().trim();
                            brandCode1 = str.substring(str.length() - 4);
                        }

                        // 変数.銘柄コード2
                        String brandCode2 = null;
                        if (api001OutSub.getSerNo() == null || api001OutSub.getSerNo().trim().isEmpty()) {
                            brandCode2 = "000";
                        } else {
                            String str = "000" + api001OutSub.getSerNo().trim();
                            brandCode2 = str.substring(str.length() - 3);
                        }

                        // 変数.銘柄コード3
                        String brandCode3 = null;
                        if (api001OutSub.getSubCode2() == null || api001OutSub.getSubCode2().trim().isEmpty()) {
                            brandCode3 = "";
                        } else {
                            // SQL008
                            IfaHoldingSecurityListSql008RequestModel selSql008Req = new IfaHoldingSecurityListSql008RequestModel();
                            selSql008Req.setIssue2(api001OutSub.getSubCode2().trim());
                            IfaHoldingSecurityListSql008ResponseModel selSql008ResList = dao
                                .selectIfaHoldingSecurityListSql008(selSql008Req);
                            
                            if (selSql008ResList != null) {
                                brandCode3 = "." + selSql008ResList.getTradeCodePare();
                            } else {
                                brandCode3 = "";
                            }
                        }

                        // SQL009
                        IfaHoldingSecurityListSql009RequestModel selSql009Req = new IfaHoldingSecurityListSql009RequestModel();
                        selSql009Req.setBrandCode(brandCode1 + "." + brandCode2 + brandCode3);
                        DataList<IfaHoldingSecurityListSql009ResponseModel> selSql009ResList = dao
                            .selectIfaHoldingSecurityListSql009(selSql009Req);
                        
                        // API001.明細部.商品検索ｺｰﾄﾞ(大分類) = 'B'(国内公社債)の明細、かつ、SQL009が有件の場合、その明細を後続処理から除外する。
                        if (StringUtils.equals(api001OutSub.getBrandSearchCode1(), "B") && (selSql009ResList.getDataList().size() > 0)) {
                            continue;
                        }

                        IfaHoldingSecurityListResponseDtoDomesticBondsDepositDetail detail = this
                                .createDomesticBondsDepositDetail(api001OutSub);
                        
                        switch (displayType) {
                            case OPEN_TOTAL_DMSTC_BND_SPECIFIC:
                            case OPEN_JRNISA_DMSTC_BND_SPECIFIC:
                            case CLOSE_TOTAL_DMSTC_BND_SPECIFIC:
                                //特定
                                
                                //取得単価
                                // API001.移動平均単価
                                // ※小数点第3位以下切捨て
                                BigDecimal averagePrice = new BigDecimal(api001OutSub.getAveragePrice());
                                detail.setAcquirePriceReferencePrice(
                                        averagePrice.setScale(2, RoundingMode.DOWN).toPlainString());
                                
                                // 評価額
                                // API001.残高数量×API001.移動平均単価/100×API001.参考為替
                                // ※計算結果の小数点以下切り捨て
                                // ※API001.移動平均単価がゼロの場合、'-'(半角ハイフン)を設定
                                // ※API001.参考為替(常に「1」で連携)
                                if (averagePrice.compareTo(BigDecimal.ZERO) == 0) {
                                    detail.setValuation(null);
                                } else {
                                    BigDecimal wkPosition = new BigDecimal(api001OutSub.getPosition());
                                    BigDecimal valuation = wkPosition.multiply(averagePrice);
                                    valuation = valuation.divide(new BigDecimal("100"));
                                    valuation = valuation.multiply(new BigDecimal(api001OutSub.getStandardRate()));
                                    detail.setValuation(valuation.setScale(0, RoundingMode.DOWN).toPlainString());
                                }
                                break;
                            case OPEN_TOTAL_DMSTC_BND_GENERAL:
                            case OPEN_JRNISA_DMSTC_BND_GENERAL:
                            case CLOSE_TOTAL_DMSTC_BND_GENERAL:
                                //一般
                                
                                //取得単価
                                // API001.加重平均単価
                                // ※小数点第3位以下切捨て
                                BigDecimal wghAveragePrice = new BigDecimal(api001OutSub.getWghAveragePrice());
                                detail.setAcquirePriceReferencePrice(
                                        wghAveragePrice.setScale(2, RoundingMode.DOWN).toPlainString());
                                
                                // 評価額
                                // API001.残高数量×API001.加重平均単価/100×API001.参考為替
                                // ※計算結果の小数点以下切り捨て
                                // ※API001.加重平均単価がゼロの場合、'-'(半角ハイフン)を設定
                                // ※API001.参考為替(常に「1」で連携)
                                if (wghAveragePrice.compareTo(BigDecimal.ZERO) == 0) {
                                    detail.setValuation(null);
                                } else {
                                    BigDecimal wkPosition = new BigDecimal(api001OutSub.getPosition());
                                    BigDecimal valuation = wkPosition.multiply(wghAveragePrice);
                                    valuation = valuation.divide(new BigDecimal("100"));
                                    valuation = valuation.multiply(new BigDecimal(api001OutSub.getStandardRate()));
                                    detail.setValuation(valuation.setScale(0, RoundingMode.DOWN).toPlainString());
                                }
                                break;
                            default:
                                break;
                        }
                        
                        IfaHoldingSecurityListResponseDtoDomesticBonds domesticBonds = this
                                .getDomesticBonds(domesticBondsMap, displayType);
                        
                        // 預り銘柄数の設定
                        int numberOfDepositedIssues = Integer.parseInt(domesticBonds.getNumberOfDepositedIssues());
                        numberOfDepositedIssues++;
                        domesticBonds.setNumberOfDepositedIssues(Integer.toString(numberOfDepositedIssues));
                        
                        // 評価額合計の設定
                        BigDecimal valuationTotal = new BigDecimal(domesticBonds.getValuationTotal());
                        if (detail.getValuation() != null) {
                            valuationTotal = valuationTotal.add(new BigDecimal(detail.getValuation()));
                        }
                        domesticBonds.setValuationTotal(valuationTotal.toPlainString());
                        
                        //国内債券情報に預り明細情報を追加
                        List<IfaHoldingSecurityListResponseDtoDomesticBondsDepositDetail> detailList = null;
                        if ((detailList = domesticBonds.getDepositDetailList()) == null) {
                            detailList = new ArrayList<IfaHoldingSecurityListResponseDtoDomesticBondsDepositDetail>();
                        }
                        detailList.add(detail);
                        domesticBonds.setDepositDetailList(detailList);
                        
                        //外国債券
                    } else if (this.isForeignBonds(api001OutSub) == true) {
                        IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail detail = this
                                .createForeignBondsdepositDetail(api001OutSub);
                        
                        switch (displayType) {
                            case OPEN_TOTAL_FRIGN_BND_SPECIFIC:
                            case OPEN_JRNISA_FRIGN_BND_SPECIFIC:
                            case CLOSE_TOTAL_FRIGN_BND_SPECIFIC:
                                //特定
                                
                                //買付単価
                                // API001.移動平均単価
                                // ※小数点第3位以下切捨て
                                BigDecimal averagePrice = new BigDecimal(api001OutSub.getAveragePrice());
                                detail.setUnitPrice(averagePrice.setScale(2, RoundingMode.DOWN).toPlainString());
                                
                                // 外貨建評価額
                                // API001.残高数量×API001.移動平均単価/100
                                // ※計算結果の小数点以下切り捨て
                                // ※API001.移動平均単価がゼロの場合、'-'(半角ハイフン)を設定
                                // ※API001.参考為替(常に「1」で連携)
                                if (averagePrice.compareTo(BigDecimal.ZERO) == 0
                                        || StringUtils.equals(detail.getCurrencyCode(),
                                                IfaHoldingSecurityListServiceImpL.API001_ISSUED_CCY_CODE_JPY)) {
                                    detail.setForeignValuation17(null);
                                } else {
                                    BigDecimal wkPosition = new BigDecimal(api001OutSub.getPosition());
                                    BigDecimal foreignValuation = wkPosition.multiply(averagePrice);
                                    foreignValuation = foreignValuation.divide(new BigDecimal("100"));
                                    detail.setForeignValuation17(
                                            foreignValuation.setScale(0, RoundingMode.DOWN).toPlainString());
                                }
                                
                                // 円換算評価額
                                // API001.残高数量×API001.移動平均単価/100×API001.参考為替 
                                // ※計算結果の小数点以下切り捨て
                                // ※API001.移動平均単価がゼロの場合
                                //    '-'(半角ハイフン)を設定
                                // ※API001.参考為替がゼロの場合
                                //  　　'-'(半角ハイフン)を設定
                                if (averagePrice.compareTo(BigDecimal.ZERO) == 0
                                        || (new BigDecimal(api001OutSub.getStandardRate()))
                                                .compareTo(BigDecimal.ZERO) == 0) {
                                    detail.setYenConversionValuation(null);
                                } else {
                                    
                                    BigDecimal wkPosition = new BigDecimal(api001OutSub.getPosition());
                                    BigDecimal yenConversionValuation = wkPosition.multiply(averagePrice);
                                    yenConversionValuation = yenConversionValuation.divide(new BigDecimal("100"));
                                    yenConversionValuation = yenConversionValuation
                                            .multiply(new BigDecimal(api001OutSub.getStandardRate()));
                                    detail.setYenConversionValuation(
                                            yenConversionValuation.setScale(0, RoundingMode.DOWN).toPlainString());
                                    
                                }
                                break;
                            
                            case OPEN_TOTAL_FRIGN_BND_GENERAL:
                            case OPEN_JRNISA_FRIGN_BND_GENERAL:
                            case CLOSE_TOTAL_FRIGN_BND_GENERAL:
                                //一般
                                
                                //買付単価
                                // API001.加重平均単価
                                // ※小数点第3位以下切捨て
                                BigDecimal wghAveragePrice = new BigDecimal(api001OutSub.getWghAveragePrice());
                                detail.setUnitPrice(wghAveragePrice.setScale(2, RoundingMode.DOWN).toPlainString());
                                
                                // 外貨建評価額
                                // API001.残高数量×API001.加重平均単価/100
                                // ※計算結果の小数点以下切り捨て
                                // ※API001.移動平均単価がゼロの場合、'-'(半角ハイフン)を設定
                                // ※API001.参考為替(常に「1」で連携)
                                if (wghAveragePrice.compareTo(BigDecimal.ZERO) == 0) {
                                    detail.setForeignValuation17(null);
                                } else {
                                    BigDecimal wkPosition = new BigDecimal(api001OutSub.getPosition());
                                    BigDecimal foreignValuation = wkPosition.multiply(wghAveragePrice);
                                    foreignValuation = foreignValuation.divide(new BigDecimal("100"));
                                    detail.setForeignValuation17(
                                            foreignValuation.setScale(0, RoundingMode.DOWN).toPlainString());
                                }
                                // 円換算評価額
                                // API001.残高数量×API001.加重平均単価/100×API001.参考為替 
                                // ※計算結果の小数点以下切り捨て
                                // ※API001.加重平均単価がゼロの場合
                                //    '-'(半角ハイフン)を設定
                                // ※API001.参考為替がゼロの場合
                                //  　　'-'(半角ハイフン)を設定
                                if (wghAveragePrice.compareTo(BigDecimal.ZERO) == 0
                                        || (new BigDecimal(api001OutSub.getStandardRate()))
                                                .compareTo(BigDecimal.ZERO) == 0) {
                                    detail.setYenConversionValuation(null);
                                } else {
                                    BigDecimal wkPosition = new BigDecimal(api001OutSub.getPosition());
                                    BigDecimal yenConversionValuation = wkPosition.multiply(wghAveragePrice);
                                    yenConversionValuation = yenConversionValuation.divide(new BigDecimal("100"));
                                    yenConversionValuation = yenConversionValuation
                                            .multiply(new BigDecimal(api001OutSub.getStandardRate()));
                                    detail.setYenConversionValuation(
                                            yenConversionValuation.setScale(0, RoundingMode.DOWN).toPlainString());
                                    
                                }
                                break;
                            default:
                                break;
                        }
                        
                        IfaHoldingSecurityListResponseDtoForeignBonds foreignBonds = this
                                .getForeignBonds(foreignBondsMap, displayType);
                        
                        // 預り銘柄数の設定
                        int numberOfDepositedIssues = Integer.parseInt(foreignBonds.getNumberOfDepositedIssues());
                        numberOfDepositedIssues++;
                        foreignBonds.setNumberOfDepositedIssues(Integer.toString(numberOfDepositedIssues));
                        
                        // 評価額合計の設定
                        BigDecimal valuationTotal = new BigDecimal(foreignBonds.getValuationTotal());
                        if (detail.getYenConversionValuation() != null) {
                            valuationTotal = valuationTotal.add(new BigDecimal(detail.getYenConversionValuation()));
                        }
                        foreignBonds.setValuationTotal(valuationTotal.toPlainString());
                        
                        //外国債券情報に預り明細情報を追加
                        List<IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail> detailList = null;
                        if ((detailList = foreignBonds.getDepositDetailList()) == null) {
                            detailList = new ArrayList<IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail>();
                        }
                        detailList.add(detail);
                        foreignBonds.setDepositDetailList(detailList);

                    } else if (this.isOtherSecurity(api001OutSub, cc) == true) {
                        
                        IfaHoldingSecurityListResponseDtoOtherSecurity otherSecurity = this
                                .getOtherSecurity(otherSecurityMap, displayType);
                        
                        // 預り銘柄数の設定
                        int numberOfDepositedIssues = Integer.parseInt(otherSecurity.getNumberOfDepositedIssues());
                        numberOfDepositedIssues++;
                        otherSecurity.setNumberOfDepositedIssues(Integer.toString(numberOfDepositedIssues));
                        
                        List<IfaHoldingSecurityListResponseDtoDepositDetail> detailList = null;
                        if ((detailList = otherSecurity.getDepositDetailList()) == null) {
                            detailList = new ArrayList<IfaHoldingSecurityListResponseDtoDepositDetail>();
                        }
                        
                        //その他商品情報に預り明細情報を追加
                        IfaHoldingSecurityListResponseDtoDepositDetail detail = this.createDepositDetail(api001OutSub);
                        
                        detailList.add(detail);
                        otherSecurity.setDepositDetailList(detailList);
                    }
                }
            }
        }
    }
    
    /**
     * 保有商品情報の作成（外国株式、外国MMF、 外国債券（外貨建））
     *
     * @param foreignStocksMap      外国株式情報管理マップ
     * @param foreignCurrencyMmfMap 外貨建MMF情報管理マップ
     * @param foreignBondsMap       外国債券情報（外貨建）管理マップ
     * @param fct001Dto FCT001レスポンス
     * @param fct003Dto FCT003レスポンス
     * @param securityClass 商品コード（初期表示の場合はnull）
     * @param accountGetKbn 口座区分（初期表示の場合はnull）
     */
    private synchronized void createHoldingSecurityInfo2(
            Map<String, IfaHoldingSecurityListResponseDtoForeignStocks> foreignStocksMap,
            Map<String, IfaHoldingSecurityListResponseDtoForeignCurrencyMmf> foreignCurrencyMmfMap,
            Map<String, IfaHoldingSecurityListResponseDtoForeignBonds> foreignBondsMap,
            OutputFct001Dto fct001Dto, OutputFct003Dto fct003Dto, String securityClass, String accountGetKbn) throws Exception {
        
        // 顧客情報の取得
        // 現在は仮項目設定しかしていないのでそれを用いて処理する
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        // 「顧客共通情報.外国株式取引口座開設状況=1（開設済）」ではない かつ 「顧客共通情報.外貨建商品取引口座開設状況=1（開設済）」ではない場合、API004の呼び出しはスキップ
        if (!StringUtils.equals(cc.getForeignStockTradeAccountOpenStatus(), ForeignStockTradeAccountOpenStatus.OPEN.getId())
            && !StringUtils.equals(cc.getForeignSecurityTradeAccountOpenStatus(), ForeignSecurityTradeAccountOpenStatus.OPEN.getId())) {
            return;
        }

        // 外国債券情報（外貨建）の習得
        if (securityClass == null || StringUtils.equals(securityClass,
                IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_BOND) == true) {
            ListSecuritiesBalancesResp api004ForeignStockRes = null;
            try {
                api004ForeignStockRes = foreignAccountService.listSecuritiesBalances(butenCode.substring(0, 3),
                        accountNumber, null, IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_BOND, null,
                        null, null, null);
            } catch (Exception e) {
                LOGGER.error("IfaHoldingSecurityListServiceImpL.listSecuritiesBalances Exception[{}]", e.getMessage());
                e.printStackTrace();
                throw e;
            }
            
            if (api004ForeignStockRes != null && api004ForeignStockRes.getSecuritiesBalances() != null
                    && api004ForeignStockRes.getSecuritiesBalances().size() > 0) {
                
                for (SecuritiesBalances securitiesBalances : api004ForeignStockRes.getSecuritiesBalances()) {
                    
                    if (accountGetKbnJudg(securitiesBalances, accountGetKbn) == true) {
                        // 明細読み込みをスキップする
                        continue;
                    }
                    
                    // 画面表示種別の取得
                    DisplayType displayType = this.getDisplayType(securitiesBalances, cc);
                    
                    if (displayType == null) {
                        continue;
                    }
                    
                    //外国債券（外貨建） 預り明細情報の作成
                    IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail detail = this
                            .createForeignBondsdepositDetail(securitiesBalances);
                    
                    IfaHoldingSecurityListResponseDtoForeignBonds foreignBonds = this
                        .getForeignBonds(foreignBondsMap, displayType);
                        
                    // 預り銘柄数の設定
                    int numberOfDepositedIssues = Integer.parseInt(foreignBonds.getNumberOfDepositedIssues());
                    numberOfDepositedIssues++;
                    foreignBonds.setNumberOfDepositedIssues(Integer.toString(numberOfDepositedIssues));
                    
                    // 評価額合計の設定
                    BigDecimal valuationTotal = new BigDecimal(foreignBonds.getValuationTotal());
                    if (detail.getYenConversionValuation() != null) {
                        valuationTotal = valuationTotal.add(new BigDecimal(detail.getYenConversionValuation()));
                    }
                    foreignBonds.setValuationTotal(valuationTotal.toPlainString());
                    
                    //外国債券情報に預り明細情報を追加
                    List<IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail> detailList = null;
                    if ((detailList = foreignBonds.getDepositDetailList()) == null) {
                        detailList = new ArrayList<IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail>();
                    }
                    detailList.add(detail);
                    foreignBonds.setDepositDetailList(detailList);
                }
            }
        }
        
        // 外国株式情報の習得
        if (securityClass == null || StringUtils.equals(securityClass,
                IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_STOCK) == true) {
            ListSecuritiesBalancesResp api004ForeignStockRes = null;
            try {
                api004ForeignStockRes = foreignAccountService.listSecuritiesBalances(butenCode.substring(0, 3),
                        accountNumber, null, IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_STOCK, null,
                        null, null, null);
            } catch (Exception e) {
                LOGGER.error("IfaHoldingSecurityListServiceImpL.listSecuritiesBalances Exception[{}]", e.getMessage());
                e.printStackTrace();
                throw e;
            }
            
            if (api004ForeignStockRes != null && api004ForeignStockRes.getSecuritiesBalances() != null
                    && api004ForeignStockRes.getSecuritiesBalances().size() > 0) {
                
                for (SecuritiesBalances securitiesBalances : api004ForeignStockRes.getSecuritiesBalances()) {
                    
                    if (accountGetKbnJudg(securitiesBalances, accountGetKbn) == true) {
                        // 明細読み込みをスキップする
                        continue;
                    }
                    
                    // 画面表示種別の取得
                    DisplayType displayType = this.getDisplayType(securitiesBalances, cc);
                    
                    if (displayType == null) {
                        continue;
                    }
                    
                    //外国株式 預り明細情報の作成
                    IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail detail = this
                            .createForeignStocksdepositDetail(securitiesBalances);
                    
                    //買付表示区分
                    if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true
                            && isMediatePropriety(fct003Dto,
                                    IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_FOREIGN_STOCK,
                                    IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_BUY,
                                    securitiesBalances.getCountryCode(), null) == true) {
                        detail.setBuyDisplayClassification("1");
                    } else {
                        detail.setBuyDisplayClassification("0");
                    }
                    
                    //売却表示区分
                    if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true
                            && isMediatePropriety(fct003Dto,
                                    IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_FOREIGN_STOCK,
                                    IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_BUYING,
                                    securitiesBalances.getCountryCode(), null) == true) {
                        BigDecimal wkSecuritiesQuantity = new BigDecimal(securitiesBalances.getSecuritiesQuantity());
                        BigDecimal wkSellFixedOrderQuantity = new BigDecimal(
                                securitiesBalances.getSellFixedOrderQuantity());
                        
                        if ((wkSecuritiesQuantity.subtract(wkSellFixedOrderQuantity))
                                .compareTo(BigDecimal.ZERO) == -1) {
                            detail.setSaleDisplayClassification("3");
                        } else {
                            detail.setSaleDisplayClassification("1");
                        }
                    } else {
                        detail.setSaleDisplayClassification("0");
                    }
                    
                    //外国株式情報の取得
                    IfaHoldingSecurityListResponseDtoForeignStocks foreignStocks = this.getForeignStocks(foreignStocksMap,displayType);
                    
                    // 預り銘柄数の設定
                    int numberOfDepositedIssues = Integer.parseInt(foreignStocks.getNumberOfDepositedIssues());
                    numberOfDepositedIssues++;
                    foreignStocks.setNumberOfDepositedIssues(Integer.toString(numberOfDepositedIssues));
                    
                    // 評価額合計の設定
                    BigDecimal valuationTotal = new BigDecimal(foreignStocks.getValuationTotal());
                    if (!StringUtil.isNullOrEmpty(detail.getValuation())) {
                        valuationTotal = valuationTotal.add(new BigDecimal(detail.getValuation()));
                    }
                    foreignStocks.setValuationTotal(valuationTotal.toPlainString());
                    
                    // 評価損益合計 の設定
                    BigDecimal getProfitAll = new BigDecimal(foreignStocks.getGetProfitAll());
                    if (!StringUtil.isNullOrEmpty(detail.getYenProfitLoss())){
                        getProfitAll = getProfitAll.add(new BigDecimal(detail.getYenProfitLoss()));
                    }
                    foreignStocks.setGetProfitAll(getProfitAll.toPlainString());
                    
                    //外国債券情報に預り明細情報を追加
                    List<IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail> detailList = null;
                    if ((detailList = foreignStocks.getDepositDetailList()) == null) {
                        detailList = new ArrayList<IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail>();
                    }
                    detailList.add(detail);
                    foreignStocks.setDepositDetailList(detailList);
                }
            }
        }
        
        if (securityClass == null || StringUtils.equals(securityClass,
                IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_MMF) == true) {
            // 外国MFF情報の習得
            ListSecuritiesBalancesResp api004ForeignMffRes = null;
            
            try {
                api004ForeignMffRes = foreignAccountService.listSecuritiesBalancesMmf(butenCode.substring(0, 3),
                        accountNumber, null, IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_MMF, null,
                        null, null, null);
            } catch (Exception e) {
                LOGGER.error("IfaHoldingSecurityListServiceImpL.listSecuritiesBalancesMmf Exception[{}]",
                        e.getMessage());
                e.printStackTrace();
                throw e;
            }
            
            if (api004ForeignMffRes != null && api004ForeignMffRes.getSecuritiesBalances() != null
                    && api004ForeignMffRes.getSecuritiesBalances().size() > 0) {
                
                for (SecuritiesBalances securitiesBalances : api004ForeignMffRes.getSecuritiesBalances()) {

                    if (accountGetKbnJudg(securitiesBalances, accountGetKbn) == true) {
                        // 明細読み込みをスキップする
                        continue;
                    }
                    
                    // 画面表示種別の取得
                    DisplayType displayType = this.getDisplayType(securitiesBalances, cc);
                    
                    if (displayType == null) {
                        continue;
                    }
                    
                    //外国MFF 預り明細情報の作成
                    IfaHoldingSecurityListResponseDtoForeignCurrencyMmfDepositDetail detail = this
                            .createForeignCurrencyMmfDepositDetail(securitiesBalances);
                    
                    //外国MFF情報の取得
                    IfaHoldingSecurityListResponseDtoForeignCurrencyMmf foreignCurrencyMmf = this
                            .getForeignCurrencyMmf(foreignCurrencyMmfMap, displayType);
                    
                    // 預り銘柄数の設定
                    int numberOfDepositedIssues = Integer.parseInt(foreignCurrencyMmf.getNumberOfDepositedIssues());
                    numberOfDepositedIssues++;
                    foreignCurrencyMmf.setNumberOfDepositedIssues(Integer.toString(numberOfDepositedIssues));
                    
                    // 評価額合計の設定
                    BigDecimal valuationTotal = new BigDecimal(foreignCurrencyMmf.getValuationTotal());
                    if (!StringUtil.isNullOrEmpty(detail.getValuation())) {
                        valuationTotal = valuationTotal.add(new BigDecimal(detail.getValuation()));
                    }
                    foreignCurrencyMmf.setValuationTotal(valuationTotal.toPlainString());
                    
                    // 評価損益合計 の設定
                    BigDecimal getProfitAll = new BigDecimal(foreignCurrencyMmf.getGetProfitAll());
                    if (!StringUtil.isNullOrEmpty(detail.getYenProfitLoss())) {
                        getProfitAll = getProfitAll.add(new BigDecimal(detail.getYenProfitLoss()));
                    }
                    foreignCurrencyMmf.setGetProfitAll(getProfitAll.toPlainString());
                    
                    //外国債券情報に預り明細情報を追加
                    List<IfaHoldingSecurityListResponseDtoForeignCurrencyMmfDepositDetail> detailList = null;
                    if ((detailList = foreignCurrencyMmf.getDepositDetailList()) == null) {
                        detailList = new ArrayList<IfaHoldingSecurityListResponseDtoForeignCurrencyMmfDepositDetail>();
                    }
                    detailList.add(detail);
                    foreignCurrencyMmf.setDepositDetailList(detailList);
                }
            }
        }
    }

    /**
     * 保有商品情報の作成（ST）
     *
     * @param securityTokenMap      ST情報管理マップ
     * @param accountGetKbn 口座区分（初期表示の場合はnull）
     */
    private synchronized void createHoldingSecurityInfo3(
            Map<String, IfaHoldingSecurityListResponseDtoSecurityToken> securityTokenMap,String accountGetKbn)
            throws Exception {
        
        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        // ・顧客共通情報.ジュニアISA契約区分='1'(契約)の場合
        //   API入力.取得口座区分='2'(JrNISA口座(第一、第二口座両方))
        //   顧客共通情報.ジュニアISA契約区分=' '(未契約),'9'(閉鎖済)の場合
        //   API入力.取得口座区分=' '(通常口座およびJrNISA口座の第一口座)"
        String calcAccountGetKbn = "";
        if (accountGetKbn == null) {
            String jrIsaContractType = cc.getJrIsaContractType();
            if (StringUtils.equals(jrIsaContractType, "1") == true) {
                
                calcAccountGetKbn = "2";
            } else if (StringUtils.equals(jrIsaContractType, " ") == true
                    || StringUtils.equals(jrIsaContractType, "9")) {
                
                calcAccountGetKbn = " ";
            }
        } else {
            calcAccountGetKbn = accountGetKbn;
        }

            // ⑥'ST（セキュリティ・トークン）明細リストを取得する。
            IfaHoldingSecurityListSql010RequestModel selSql010Req = new IfaHoldingSecurityListSql010RequestModel();
                        
            selSql010Req.setButenCode(butenCode);
            selSql010Req.setAccountNumber(accountNumber);
            selSql010Req.setAccountGetKbn(calcAccountGetKbn);
                        
            DataList<IfaHoldingSecurityListSql010ResponseModel> selSql010ResList = dao
                    .selectIfaHoldingSecurityListSql010(selSql010Req);
            
            for (int i = 0; i < selSql010ResList.getDataList().size(); i++) {
                // 明細の振り分け
                // 画面表示種別の取得
                DisplayType displayType = this.getDisplayType(selSql010ResList.get(i).getDepositType(), cc);
                    
                if (displayType == null) {
                        continue;
                }

                    //ST 預り明細情報の作成
                    IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail detail = this
                            .createSecurityTokenListDepositDetail(selSql010ResList.get(i));
                    
                    //ST情報の取得
                    IfaHoldingSecurityListResponseDtoSecurityToken securityToken = this
                            .getSecurityTokenList(securityTokenMap, displayType);
                    
                    // 預り銘柄数の設定
                    int numberOfDepositedIssues = Integer.parseInt(securityToken.getNumberOfDepositedIssues());
                    numberOfDepositedIssues++;
                    securityToken.setNumberOfDepositedIssues(Integer.toString(numberOfDepositedIssues));
                    
                    // 評価額合計の設定
                    BigDecimal valuationTotal = new BigDecimal(securityToken.getValuationTotal());
                    if (!StringUtil.isNullOrEmpty(detail.getValuation())) {
                        valuationTotal = valuationTotal.add(new BigDecimal(detail.getValuation()));
                    }
                    securityToken.setValuationTotal(valuationTotal.toPlainString());
                    
                    // 評価損益合計 の設定
                    BigDecimal getProfitAll = new BigDecimal(securityToken.getGetProfitAll());
                    if (!StringUtil.isNullOrEmpty(detail.getYenProfitLoss())) {
                        getProfitAll = getProfitAll.add(new BigDecimal(detail.getYenProfitLoss()));
                    }
                    securityToken.setGetProfitAll(getProfitAll.toPlainString());
                    
                    //預り明細情報を追加
                    List<IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail> detailList = null;
                    if ((detailList = securityToken.getDepositDetailList()) == null) {
                        detailList = new ArrayList<IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail>();
                    }
                    detailList.add(detail);
                    securityToken.setDepositDetailList(detailList);
            }
    }
    
    /**
     * 評価額合計の算出
     *
     * @param domesticStockMap      国内株式情報管理マップ
     * @param investmentTrustMap    投資信託情報管理マップ
     * @param domesticBondsMap      国内債券情報管理マップ
     * @param foreignBondsMap       外国債券情報管理マップ
     * @param foreignStocksMap      外国株式情報管理マップ
     * @param foreignCurrencyMmfMap 外貨建MMF情報管理マップ
     * @param securityTokenMap      STマップ
     * @param otherSecurityMap      その他商品情報管理マップ
     * @return 評価額総合計, 評価損益総合計
     */
    private BigDecimal[] getValuationAndGetProfitTotal(
            Map<String, IfaHoldingSecurityListResponseDtoDomesticStock> domesticStockMap,
            Map<String, IfaHoldingSecurityListResponseDtoInvestmentTrust> investmentTrustMap,
            Map<String, IfaHoldingSecurityListResponseDtoDomesticBonds> domesticBondsMap,
            Map<String, IfaHoldingSecurityListResponseDtoForeignBonds> foreignBondsMap,
            Map<String, IfaHoldingSecurityListResponseDtoForeignStocks> foreignStocksMap,
            Map<String, IfaHoldingSecurityListResponseDtoForeignCurrencyMmf> foreignCurrencyMmfMap,
            Map<String, IfaHoldingSecurityListResponseDtoSecurityToken> securityTokenMap,
            Map<String, IfaHoldingSecurityListResponseDtoOtherSecurity> otherSecurityMap) {
        
        //国内株式全明細の評価額合計の算出
        //国内株式全明細の評価損益合計の算出
        BigDecimal domesticStockValuationTotal = BigDecimal.ZERO;
        BigDecimal domesticStockGetProfitAll = BigDecimal.ZERO;
        for (String key : domesticStockMap.keySet()) {
            IfaHoldingSecurityListResponseDtoDomesticStock domesticStock = domesticStockMap.get(key);
            
            domesticStockValuationTotal = domesticStockValuationTotal
                    .add(new BigDecimal(domesticStock.getValuationTotal()));
            domesticStockGetProfitAll = domesticStockGetProfitAll.add(new BigDecimal(domesticStock.getGetProfitAll()));
        }
        
        //投資信託全明細の評価額合計の算出
        //投資信託全明細の評価損益合計の算出
        BigDecimal investmentTrustValuationTotal = BigDecimal.ZERO;
        BigDecimal investmentTrustGetProfitAll = BigDecimal.ZERO;
        for (String key : investmentTrustMap.keySet()) {
            IfaHoldingSecurityListResponseDtoInvestmentTrust investmentTrust = investmentTrustMap.get(key);
            
            investmentTrustValuationTotal = investmentTrustValuationTotal
                    .add(new BigDecimal(investmentTrust.getValuationTotal()));
            investmentTrustGetProfitAll = investmentTrustGetProfitAll
                    .add(new BigDecimal(investmentTrust.getGetProfitAll()));
        }
        
        //国内債券全明細の評価額合計の算出
        BigDecimal domesticBondsValuationTotal = BigDecimal.ZERO;
        for (String key : domesticBondsMap.keySet()) {
            IfaHoldingSecurityListResponseDtoDomesticBonds domesticBonds = domesticBondsMap.get(key);
            
            domesticBondsValuationTotal = domesticBondsValuationTotal
                    .add(new BigDecimal(domesticBonds.getValuationTotal()));
        }
        
        //外国債券全明細の円換算評価額合計の算出
        BigDecimal foreignBondsValuationTotal = BigDecimal.ZERO;
        for (String key : foreignBondsMap.keySet()) {
            IfaHoldingSecurityListResponseDtoForeignBonds foreignBonds = foreignBondsMap.get(key);
            
            foreignBondsValuationTotal = foreignBondsValuationTotal
                    .add(new BigDecimal(foreignBonds.getValuationTotal()));
        }
        
        //外国株式全明細の評価額（円貨）合計の算出
        //外国株式全明細の評価損益合計の算出
        BigDecimal foreignStocksValuationTotal = BigDecimal.ZERO;
        BigDecimal foreignStocksGetProfitAll = BigDecimal.ZERO;
        for (String key : foreignStocksMap.keySet()) {
            IfaHoldingSecurityListResponseDtoForeignStocks foreignStock = foreignStocksMap.get(key);
            
            foreignStocksValuationTotal = foreignStocksValuationTotal
                    .add(new BigDecimal(foreignStock.getValuationTotal()));
            foreignStocksGetProfitAll = foreignStocksGetProfitAll.add(new BigDecimal(foreignStock.getGetProfitAll()));
        }
        
        //外貨建MMF全明細の評価額（円貨）合計の算出
        //外貨建MMF全明細の評価損益合計の算出
        BigDecimal foreignCurrencyMmfValuationTotal = BigDecimal.ZERO;
        BigDecimal foreignCurrencyMmfGetProfitAll = BigDecimal.ZERO;
        for (String key : foreignCurrencyMmfMap.keySet()) {
            IfaHoldingSecurityListResponseDtoForeignCurrencyMmf foreignCurrencyMmf = foreignCurrencyMmfMap.get(key);
            
            foreignCurrencyMmfValuationTotal = foreignCurrencyMmfValuationTotal
                    .add(new BigDecimal(foreignCurrencyMmf.getValuationTotal()));
            foreignCurrencyMmfGetProfitAll = foreignCurrencyMmfGetProfitAll
                    .add(new BigDecimal(foreignCurrencyMmf.getGetProfitAll()));
        }
        
        //ST全明細の評価額（円貨）合計の算出
        //ST全明細の評価損益合計の算出
        BigDecimal securityTokenValuationTotal = BigDecimal.ZERO;
        BigDecimal securityTokenGetProfitAll = BigDecimal.ZERO;
        for (String key : securityTokenMap.keySet()) {
            IfaHoldingSecurityListResponseDtoSecurityToken securityToken = securityTokenMap.get(key);
            
            securityTokenValuationTotal = securityTokenValuationTotal
                    .add(new BigDecimal(securityToken.getValuationTotal()));
            securityTokenGetProfitAll = securityTokenGetProfitAll
                    .add(new BigDecimal(securityToken.getGetProfitAll()));
        }
        
        // 評価額総合計＝ 国内株式全明細の評価額合計＋投資信託全明細の評価額合計＋国内債券全明細の評価額合計＋
        // 外国債券全明細の円換算評価額合計＋外国株式全明細の評価額（円貨）合計＋
        // 外貨建MMF全明細の評価額（円貨）合計＋ST全明細の評価額（円貨）合計
        BigDecimal getTotalAssessedValueAll = domesticStockValuationTotal.add(investmentTrustValuationTotal);
        getTotalAssessedValueAll = getTotalAssessedValueAll.add(domesticBondsValuationTotal);
        getTotalAssessedValueAll = getTotalAssessedValueAll.add(foreignBondsValuationTotal);
        getTotalAssessedValueAll = getTotalAssessedValueAll.add(foreignStocksValuationTotal);
        getTotalAssessedValueAll = getTotalAssessedValueAll.add(foreignCurrencyMmfValuationTotal);
        getTotalAssessedValueAll = getTotalAssessedValueAll.add(securityTokenValuationTotal);
        
        // 評価損益総合計＝国内株式全明細の評価損益合計＋投資信託全明細の評価損益合計＋
        // 外国株式全明細の評価損益（円貨）合計＋外貨建MMF全明細の評価損益（円貨）合計＋ST全明細の評価損益（円貨）合計
        BigDecimal getTotalProfitAll = domesticStockGetProfitAll.add(investmentTrustGetProfitAll);
        getTotalProfitAll = getTotalProfitAll.add(foreignStocksGetProfitAll);
        getTotalProfitAll = getTotalProfitAll.add(foreignCurrencyMmfGetProfitAll);
        getTotalProfitAll = getTotalProfitAll.add(securityTokenGetProfitAll);
        
        BigDecimal[] resultAry = new BigDecimal[2];
        resultAry[0] = getTotalAssessedValueAll;
        resultAry[1] = getTotalProfitAll;
        return resultAry;
    }
    
    /**
     * 表示区分の取得
     *
     * @param api001ResSub API001レスポンス.明細部
     * @param cc           顧客協情報
     * @return 表示区分
     */
    private DisplayType getDisplayType(AccountSumWebData api001OutSub, CustomerCommon cc) {
        
        DisplayType displayType = null;
        
        //ジュニアISA契約区分
        String jrIsaContractType = cc.getJrIsaContractType();
        //特定口座区分
        String specificAccountType = cc.getSpecificAccountType();
        
        //ジュニアNISA口座開設済み口座判定
        if (StringUtils.equals(jrIsaContractType,
                IfaHoldingSecurityListServiceImpL.JR_ISA_CONTRACT_TYPE_OPEN) == true) {
            
            //国内株式判定
            if (this.isDomesticStock(api001OutSub)) {
                
                if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API001.非特定預り区分
                    //   ''0'(特定預り)
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.OPEN_TOTAL_DMSTC_STK_SPECIFIC;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_NISA) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_SPECIFIC) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_GENERALLY) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_JR_NISA) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_NISA) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_ACCUMULATION_NISA) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_CONTINUATION_MANAGE) == false) {
                    
                    //API001.非特定預り区分
                    //   下記以外
                    // '0'(特定預り)、'4'(NISA預り)、'5'(特定(特例))、'6'(一般(特例))、'7'(JrNISA)、'H'(総合NISA(成長投資枠))、
                    // 'I'(総合NISA(つみたて投資枠))、'J'(継続管理勘定)
                    displayType = DisplayType.OPEN_TOTAL_DMSTC_STK_GENERAL;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_NISA) == true) {
                    
                    //API001.非特定預り区分
                    //   'H'(総合NISA(成長投資枠))
                    displayType = DisplayType.OPEN_TOTAL_DMSTC_STK_NISA;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_NISA) == true) {
                    
                    //API001.非特定預り区分
                    //   ''4'(NISA預り)
                    displayType = DisplayType.OPEN_TOTAL_DMSTC_STK_OLD_NISA;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API001.非特定預り区分
                    //   ''5'(特定(特例))
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.OPEN_JRNISA_DMSTC_STK_SPECIFIC;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_GENERALLY) == true) {
                    
                    //API001.非特定預り区分
                    //   ''6'(一般(特例))
                    displayType = DisplayType.OPEN_JRNISA_DMSTC_STK_GENERAL;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_CONTINUATION_MANAGE) == true) {
                    
                    //API001.非特定預り区分
                    //   ''7'(ジュニアNISA)
                    displayType = DisplayType.OPEN_JRNISA_DMSTC_STK_NISA;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_JR_NISA) == true) {
                    
                    //API001.非特定預り区分
                    //   ''7'(ジュニアNISA)
                    displayType = DisplayType.OPEN_JRNISA_DMSTC_STK_OLD_NISA;
                }
                
                //投資信託 判定
            } else if (this.isInvestmentTrust(api001OutSub)) {
                
                // API001.商品区分 ''T'(投信(国内(一般型)/外国))
                if (StringUtils.equals(api001OutSub.getSecId(),
                        IfaHoldingSecurityListServiceImpL.API001_SEC_ID_INVESTMENT_TRUST) == true) {
                    if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == true
                            && (StringUtils.equals(specificAccountType,
                                    IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                    || StringUtils.equals(specificAccountType,
                                            IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                        
                        //API001.非特定預り区分
                        //   ''0'(特定預り)
                        //顧客共通情報.特定口座区分
                        //   '1'(特定口座(代行納付))／
                        //   '2'(特定口座(確定申告))
                        displayType = DisplayType.OPEN_TOTAL_TNVSTMT_TRST_UNIT_SPECIFIC;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_SPECIFIC) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_GENERALLY) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_JR_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_ACCUMULATION_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_ACCUMULATION_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_CONTINUATION_MANAGE) == false) {
                        
                        //API001.非特定預り区分
                        //   下記以外
                        // '0'(特定預り)、'4'(NISA預り)、'5'(特定(特例))、'6'(一般(特例))、'7'(JrNISA)、'8'(つみたてNISA預り)、
                        // 'H'(総合NISA(成長投資枠))、'I'(総合NISA(つみたて投資枠))、'J'(継続管理勘定)
                        displayType = DisplayType.OPEN_TOTAL_TNVSTMT_TRST_UNIT_GENERAL;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   'H'(総合NISA(成長投資枠))
                        displayType = DisplayType.OPEN_TOTAL_TNVSTMT_TRST_UNIT_NISA;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_ACCUMULATION_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   'I'(総合NISA(つみたて投資枠))
                        displayType = DisplayType.OPEN_TOTAL_TNVSTMT_TRST_UNIT_EX_NISA;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_NISA) == true) {
                        //API001.非特定預り区分
                        //   ''4'(NISA預り)
                        displayType = DisplayType.OPEN_TOTAL_TNVSTMT_TRST_UNIT_OLD_NISA;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_ACCUMULATION_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   ''8'(つみたてNISA預り)
                        displayType = DisplayType.OPEN_TOTAL_TNVSTMT_TRST_UNIT_OLD_EX_NISA;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_SPECIFIC) == true
                            && (StringUtils.equals(specificAccountType,
                                    IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                    || StringUtils.equals(specificAccountType,
                                            IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                        
                        //API001.非特定預り区分
                        //   '5'(特定(特例))
                        //顧客共通情報.特定口座区分
                        //   '1'(特定口座(代行納付))／
                        //   '2'(特定口座(確定申告))
                        displayType = DisplayType.OPEN_JRNISA_TNVSTMT_TRST_UNIT_SPECIFIC;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_GENERALLY) == true) {
                        
                        //API001.非特定預り区分
                        //   ''6'(一般(特例))
                        
                        displayType = DisplayType.OPEN_JRNISA_TNVSTMT_TRST_UNIT_GENERAL;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_CONTINUATION_MANAGE) == true) {
                        
                        //API001.非特定預り区分
                        //   'J'(継続管理勘定)
                        
                        displayType = DisplayType.OPEN_JRNISA_TNVSTMT_TRST_UNIT_NISA;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_JR_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   '7'(ジュニアNISA)
                        
                        displayType = DisplayType.OPEN_JRNISA_TNVSTMT_TRST_UNIT_OLD_NISA;
                    }
                    
                    // API001.商品区分 ''Y'(国内投信(汎用累投))
                } else if (StringUtils.equals(api001OutSub.getSecId(),
                        IfaHoldingSecurityListServiceImpL.API001_SEC_ID_INVESTMENT_TRUST_DOMESTIC) == true) {
                    if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == true
                            && (StringUtils.equals(specificAccountType,
                                    IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                    || StringUtils.equals(specificAccountType,
                                            IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                        
                        //API001.非特定預り区分
                        //   ''0'(特定預り)
                        //顧客共通情報.特定口座区分
                        //   '1'(特定口座(代行納付))／
                        //   '2'(特定口座(確定申告))
                        displayType = DisplayType.OPEN_TOTAL_TNVSTMT_TRST_AMNT_SPECIFIC;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_SPECIFIC) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_GENERALLY) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_JR_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_ACCUMULATION_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_ACCUMULATION_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_CONTINUATION_MANAGE) == false) {
                        
                        //API001.非特定預り区分
                        //   下記以外
                        // '0'(特定預り)、'4'(NISA預り)、'5'(特定(特例))、'6'(一般(特例))、'7'(JrNISA)、'8'(つみたてNISA預り)、
                        // 'H'(総合NISA(成長投資枠))、'I'(総合NISA(つみたて投資枠))、'J'(継続管理勘定)
                        displayType = DisplayType.OPEN_TOTAL_TNVSTMT_TRST_AMNT_GENERAL;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   'H'(総合NISA(成長投資枠))
                        displayType = DisplayType.OPEN_TOTAL_TNVSTMT_TRST_AMNT_NISA;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_ACCUMULATION_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   'I'(総合NISA(つみたて投資枠))
                        displayType = DisplayType.OPEN_TOTAL_TNVSTMT_TRST_AMNT_EX_NISA;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   ''4'(NISA預り)
                        
                        displayType = DisplayType.OPEN_TOTAL_TNVSTMT_TRST_AMNT_OLD_NISA;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_ACCUMULATION_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   ''8'(つみたてNISA預り)
                        
                        displayType = DisplayType.OPEN_TOTAL_TNVSTMT_TRST_AMNT_OLD_EX_NISA;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_SPECIFIC) == true
                            && (StringUtils.equals(specificAccountType,
                                    IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                    || StringUtils.equals(specificAccountType,
                                            IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                        
                        //API001.非特定預り区分
                        //   '5'(特定(特例))
                        //顧客共通情報.特定口座区分
                        //   '1'(特定口座(代行納付))／
                        //   '2'(特定口座(確定申告))
                        displayType = DisplayType.OPEN_JRNISA_TNVSTMT_TRST_AMNT_SPECIFIC;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_GENERALLY) == true) {
                        
                        //API001.非特定預り区分
                        //   ''6'(一般(特例))
                        displayType = DisplayType.OPEN_JRNISA_TNVSTMT_TRST_AMNT_GENERAL;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_CONTINUATION_MANAGE) == true) {
                        
                        //API001.非特定預り区分
                        //   'J'(継続管理勘定)
                        displayType = DisplayType.OPEN_JRNISA_TNVSTMT_TRST_AMNT_NISA;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_JR_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   '7'(ジュニアNISA)
                        displayType = DisplayType.OPEN_JRNISA_TNVSTMT_TRST_AMNT_OLD_NISA;
                    }
                    
                }
                // 国内債券
            } else if (this.isDomesticBonds(api001OutSub)) {
                
                if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API001.非特定預り区分
                    //   ''0'(特定預り)
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    
                    displayType = DisplayType.OPEN_TOTAL_DMSTC_BND_SPECIFIC;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_SPECIFIC) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_GENERALLY) == false) {
                    
                    //API001.非特定預り区分
                    //   下記以外
                    // '0'(特定預り)、'5'(特定(特例))、'6'(一般(特例))
                    displayType = DisplayType.OPEN_TOTAL_DMSTC_BND_GENERAL;
                    
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API001.非特定預り区分
                    //   '5'(特定(特例))
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.OPEN_JRNISA_DMSTC_BND_SPECIFIC;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_GENERALLY) == true) {
                    
                    //API001.非特定預り区分
                    //   '6'(一般(特例))
                    displayType = DisplayType.OPEN_JRNISA_DMSTC_BND_GENERAL;
                }
                // 外国債券
            } else if (this.isForeignBonds(api001OutSub)) {
                
                if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API001.非特定預り区分
                    //   ''0'(特定預り)
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.OPEN_TOTAL_FRIGN_BND_SPECIFIC;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_SPECIFIC) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_GENERALLY) == false) {
                    
                    //API001.非特定預り区分
                    //   下記以外
                    // '0'(特定預り)、'5'(特定(特例))、'6'(一般(特例))
                    displayType = DisplayType.OPEN_TOTAL_FRIGN_BND_GENERAL;
                    
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API001.非特定預り区分
                    //   '5'(特定(特例))
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.OPEN_JRNISA_FRIGN_BND_SPECIFIC;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_GENERALLY) == true) {
                    
                    //API001.非特定預り区分
                    //   '6'(一般(特例))
                    displayType = DisplayType.OPEN_JRNISA_FRIGN_BND_GENERAL;
                }
                
                //その他商品
            } else if ((StringUtils.equals(api001OutSub.getSecTypeName(),
                    IfaHoldingSecurityListServiceImpL.API001_SEC_TYPE_NAME_FOREIGN_STOCK) == true
                    || StringUtils.equals(api001OutSub.getSecTypeName(),
                            IfaHoldingSecurityListServiceImpL.API001_SEC_TYPE_NAME_FOREIGN_INVESTMENT_TRUST) == true)
                    || (StringUtils.equals(api001OutSub.getBrandSearchCode1(),
                            IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE1_DOMESTIC_FINANCE) == true
                            || StringUtils.equals(api001OutSub.getBrandSearchCode1(),
                                    IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE1_FOREIGN_FINANCE) == true)
                    || (StringUtils.equals(api001OutSub.getBrandSearchCode1(),
                            IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE1_DOMESTIC_BONDS) == true
                            && StringUtils.equals(api001OutSub.getBrandSearchCode2(),
                                    IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE2_DOMESTIC_BONDS) == true)
                    || (StringUtils.equals(api001OutSub.getBrandSearchCode1(),
                            IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE1_FOREIGN_BONDS) == true
                            && StringUtils.equals(api001OutSub.getBrandSearchCode3(),
                                    IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE3_FOREIGN_BONDS) == true)) {
                
                //その他商品（以下のいずれか）
                //  ① API001.商品タイプ名 
                //    '外国投信' / '外国株式'
                //  ② API001.商品検索ｺｰﾄﾞ 大分類
                //    'C'(国内金融商品) / ''J'(外国金融商品)　
                //  ③ API001.商品検索ｺｰﾄﾞ 大分類
                //    ''B'(国内公社債)
                //    かつ API001.商品検索ｺｰﾄﾞ 中分類
                //             ''B04'(国内ワラント)
                //  ④ API001.商品検索ｺｰﾄﾞ 大分類
                //    ''I'(外国公社債)
                //    かつ API001.商品検索ｺｰﾄﾞ 小分類
                //             ''I0118'(外国ワラント)
                
                if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API001.非特定預り区分
                    //   ''0'(特定預り)
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.OPEN_TOTAL_OTHR_SEC_SPECIFIC;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API001.非特定預り区分
                    //   '5'(特定(特例))
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.OPEN_JRNISA_OTHR_SEC_SPECIFIC;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_GENERALLY) == true) {
                    
                    //API001.非特定預り区分
                    //   '6'(一般(特例))
                    displayType = DisplayType.OPEN_JRNISA_OTHR_SEC_GENERAL;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_SPECIFIC) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_GENERALLY) == false) {
                    
                    //API001.非特定預り区分
                    //   下記以外
                    // '0'(特定預り)、'5'(特定(特例))、'6'(一般(特例))
                    displayType = DisplayType.OPEN_TOTAL_OTHR_SEC_GENERAL;
                }
            }
        } else {
            
            //国内株式判定
            if (this.isDomesticStock(api001OutSub)) {
                if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API001.非特定預り区分
                    //   ''0'(特定預り)
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.CLOSE_TOTAL_DMSTC_STK_SPECIFIC;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_NISA) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_SPECIFIC) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_GENERALLY) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_JR_NISA) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_NISA) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_ACCUMULATION_NISA) == false
                        && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_CONTINUATION_MANAGE) == false) {
                    
                    //API001.非特定預り区分
                    //   下記以外
                    // '0'(特定預り)、'4'(NISA預り)、'5'(特定(特例))、'6'(一般(特例))、'7'(JrNISA)、
                    // 'H'(総合NISA(成長投資枠))、'I'(総合NISA(つみたて投資枠))、'J'(継続管理勘定)
                    displayType = DisplayType.CLOSE_TOTAL_DMSTC_STK_GENERAL;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_NISA) == true) {
                    
                    //API001.非特定預り区分
                    //   'H'(総合NISA(成長投資枠))
                    displayType = DisplayType.CLOSE_TOTAL_DMSTC_STK_NISA;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_NISA) == true) {
                    
                    //API001.非特定預り区分
                    //   '4'(NISA預り)
                    displayType = DisplayType.CLOSE_TOTAL_DMSTC_STK_OLD_NISA;
                }
                
                //投資信託 判定
            } else if (this.isInvestmentTrust(api001OutSub)) {
                
                // API001.商品区分 ''T'(投信(国内(一般型)/外国))
                if (StringUtils.equals(api001OutSub.getSecId(),
                        IfaHoldingSecurityListServiceImpL.API001_SEC_ID_INVESTMENT_TRUST) == true) {
                    if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == true
                            && (StringUtils.equals(specificAccountType,
                                    IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                    || StringUtils.equals(specificAccountType,
                                            IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                        
                        //API001.非特定預り区分
                        //   ''0'(特定預り)
                        //顧客共通情報.特定口座区分
                        //   '1'(特定口座(代行納付))／
                        //   '2'(特定口座(確定申告))
                        displayType = DisplayType.CLOSE_TOTAL_TNVSTMT_TRST_UNIT_SPECIFIC;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_SPECIFIC) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_GENERALLY) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_JR_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_ACCUMULATION_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_ACCUMULATION_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_CONTINUATION_MANAGE) == false) {
                        
                        //API001.非特定預り区分
                        //   下記以外
                        // '0'(特定預り)、'4'(NISA預り)、'5'(特定(特例))、'6'(一般(特例))、'7'(JrNISA)、
                        // 'H'(総合NISA(成長投資枠))、'I'(総合NISA(つみたて投資枠))、'J'(継続管理勘定)
                        displayType = DisplayType.CLOSE_TOTAL_TNVSTMT_TRST_UNIT_GENERAL;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   'H'(総合NISA(成長投資枠))
                        displayType = DisplayType.CLOSE_TOTAL_TNVSTMT_TRST_UNIT_NISA;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_ACCUMULATION_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   'I'(総合NISA(つみたて投資枠))
                        displayType = DisplayType.CLOSE_TOTAL_TNVSTMT_TRST_UNIT_EX_NISA;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   '4'(NISA預り)
                        displayType = DisplayType.CLOSE_TOTAL_TNVSTMT_TRST_UNIT_OLD_NISA;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_ACCUMULATION_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   '8'(つみたてNISA預り)
                        displayType = DisplayType.CLOSE_TOTAL_TNVSTMT_TRST_UNIT_OLD_EX_NISA;
                    }
                    
                    // API001.商品区分 ''Y'(国内投信(汎用累投))
                } else if (StringUtils.equals(api001OutSub.getSecId(),
                        IfaHoldingSecurityListServiceImpL.API001_SEC_ID_INVESTMENT_TRUST_DOMESTIC) == true) {
                    if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == true
                            && (StringUtils.equals(specificAccountType,
                                    IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                    || StringUtils.equals(specificAccountType,
                                            IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                        
                        //API001.非特定預り区分
                        //   ''0'(特定預り)
                        //顧客共通情報.特定口座区分
                        //   '1'(特定口座(代行納付))／
                        //   '2'(特定口座(確定申告))
                        displayType = DisplayType.CLOSE_TOTAL_TNVSTMT_TRST_AMNT_SPECIFIC;
                        
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_SPECIFIC) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_EX_GENERALLY) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_JR_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_ACCUMULATION_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_ACCUMULATION_NISA) == false
                            && StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                                    IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_CONTINUATION_MANAGE) == false) {
                        
                        //API001.非特定預り区分
                        //   下記以外
                        // '0'(特定預り)、'4'(NISA預り)、'5'(特定(特例))、'6'(一般(特例))、'7'(JrNISA)、
                        // '8'(つみたてNISA預り)、'H'(総合NISA(成長投資枠))、'I'(総合NISA(つみたて投資枠))、'J'(継続管理勘定)
                        displayType = DisplayType.CLOSE_TOTAL_TNVSTMT_TRST_AMNT_GENERAL;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   'H'(総合NISA(成長投資枠))
                        displayType = DisplayType.CLOSE_TOTAL_TNVSTMT_TRST_AMNT_NISA;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_TOTAL_ACCUMULATION_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   'I'(総合NISA(つみたて投資枠))
                        displayType = DisplayType.CLOSE_TOTAL_TNVSTMT_TRST_AMNT_EX_NISA;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   '4'(NISA預り)
                        displayType = DisplayType.CLOSE_TOTAL_TNVSTMT_TRST_AMNT_OLD_NISA;
                    } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                            IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_ACCUMULATION_NISA) == true) {
                        
                        //API001.非特定預り区分
                        //   '8'(つみたてNISA預り)
                        displayType = DisplayType.CLOSE_TOTAL_TNVSTMT_TRST_AMNT_OLD_EX_NISA;
                    }
                }
                
                // 国内債券
            } else if (this.isDomesticBonds(api001OutSub)) {
                if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API001.非特定預り区分
                    //   '0'(特定預り)
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.CLOSE_TOTAL_DMSTC_BND_SPECIFIC;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == false) {
                    
                    //API001.非特定預り区分
                    //   '0'(特定預り)以外
                    displayType = DisplayType.CLOSE_TOTAL_DMSTC_BND_GENERAL;
                }
                
                // 外国債券
            } else if (this.isForeignBonds(api001OutSub)) {
                if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API001.非特定預り区分
                    //   ''0'(特定預り)
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.CLOSE_TOTAL_FRIGN_BND_SPECIFIC;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == false) {
                    
                    //API001.非特定預り区分
                    //   '0'(特定預り)以外
                    displayType = DisplayType.CLOSE_TOTAL_FRIGN_BND_GENERAL;
                }
                
                //その他商品
            } else if ((StringUtils.equals(api001OutSub.getSecTypeName(),
                    IfaHoldingSecurityListServiceImpL.API001_SEC_TYPE_NAME_FOREIGN_STOCK) == true
                    || StringUtils.equals(api001OutSub.getSecTypeName(),
                            IfaHoldingSecurityListServiceImpL.API001_SEC_TYPE_NAME_FOREIGN_INVESTMENT_TRUST) == true)
                    || (StringUtils.equals(api001OutSub.getBrandSearchCode1(),
                            IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE1_DOMESTIC_FINANCE) == true
                            || StringUtils.equals(api001OutSub.getBrandSearchCode1(),
                                    IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE1_FOREIGN_FINANCE) == true)
                    || (StringUtils.equals(api001OutSub.getBrandSearchCode1(),
                            IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE1_DOMESTIC_BONDS) == true
                            && StringUtils.equals(api001OutSub.getBrandSearchCode2(),
                                    IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE2_DOMESTIC_BONDS) == true)
                    || (StringUtils.equals(api001OutSub.getBrandSearchCode1(),
                            IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE1_FOREIGN_BONDS) == true
                            && StringUtils.equals(api001OutSub.getBrandSearchCode3(),
                                    IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE3_FOREIGN_BONDS) == true)) {
                
                //その他商品（以下のいずれか）
                //  ① API001.商品タイプ名 
                //    '外国投信' / '外国株式'
                //  ② API001.商品検索ｺｰﾄﾞ 大分類
                //    'C'(国内金融商品) / ''J'(外国金融商品)　
                //  ③ API001.商品検索ｺｰﾄﾞ 大分類
                //    ''B'(国内公社債)
                //    かつ API001.商品検索ｺｰﾄﾞ 中分類
                //             ''B04'(国内ワラント)
                //  ④ API001.商品検索ｺｰﾄﾞ 大分類
                //    ''I'(外国公社債)
                //    かつ API001.商品検索ｺｰﾄﾞ 小分類
                //             ''I0118'(外国ワラント)
                
                if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API001.非特定預り区分
                    //   ''0'(特定預り)
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.CLOSE_TOTAL_OTHR_SEC_SPECIFIC;
                } else if (StringUtils.equals(api001OutSub.getHitokuteiKbn(),
                        IfaHoldingSecurityListServiceImpL.API001_HITOKUTEI_KBN_SPECIFIC) == false) {
                    
                    //API001.非特定預り区分
                    //   '0'(特定預り)以外
                    displayType = DisplayType.CLOSE_TOTAL_OTHR_SEC_GENERAL;
                }
            }
            
        }
        
        return displayType;
    }
    
    /**
     * 表示区分の取得
     *
     * @param securitiesBalances API004レスポンス.商品保有証券資産情報
     * @param cc           顧客協情報
     * @return 表示区分
     */
    private DisplayType getDisplayType(SecuritiesBalances securitiesBalances, CustomerCommon cc) {
        
        DisplayType displayType = null;
        
        //ジュニアISA契約区分
        String jrIsaContractType = cc.getJrIsaContractType();
        //特定口座区分
        String specificAccountType = cc.getSpecificAccountType();
        
        //ジュニアNISA口座開設済み口座判定
        if (StringUtils.equals(jrIsaContractType,
                IfaHoldingSecurityListServiceImpL.JR_ISA_CONTRACT_TYPE_OPEN) == true) {
            
            //外国株式
            if (StringUtils.equals(securitiesBalances.getSecurities().getProductCode(),
                    IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_STOCK_KEY) == true) {
                
                //API004.銘柄情報.商品コード
                //   'FOREIGN_STOCK'(外国株式)
                if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API004.預り区分
                    //   'SPECIFIC'(特定)
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.OPEN_TOTAL_FRIGN_STK_SPECIFIC;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_GENERAL) == true) {
                    
                    //API004.預り区分
                    //   'GENERAL'(一般)
                    displayType = DisplayType.OPEN_TOTAL_FRIGN_STK_GENERAL;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_GROWTH_INVESTMENT) == true) {
                    
                    //API004.預り区分
                    //   'GROWTH_INVESTMENT'(成長投資枠)
                    displayType = DisplayType.OPEN_TOTAL_FRIGN_STK_NISA;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_NISA) == true) {
                    
                    //API004.預り区分
                    //   'NISA'(NISA)
                    displayType = DisplayType.OPEN_TOTAL_FRIGN_STK_OLD_NISA;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_JR_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API004.預り区分
                    //   'JR_GENERAL'(ジュニアNISA一般)
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.OPEN_JRNISA_FRIGN_STK_SPECIFIC;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_JR_GENERAL) == true) {
                    
                    //API004.預り区分
                    //   'JR_GENERAL'(ジュニアNISA一般)
                    displayType = DisplayType.OPEN_JRNISA_FRIGN_STK_GENERAL;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_JR_CONTINUOUS_MANAGEMENT) == true) {
                    
                    //API004.預り区分
                    //   ’CONTINUOUS_MANAGEMENT'(継続管理勘定)
                    displayType = DisplayType.OPEN_JRNISA_FRIGN_STK_NISA;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_JR_NISA) == true) {
                    
                    //API004.預り区分
                    //   'JR_NISA'(ジュニアNISA NISA)
                    displayType = DisplayType.OPEN_JRNISA_FRIGN_STK_OLD_NISA;
                }
                
                //外貨建MMF
            } else if (StringUtils.equals(securitiesBalances.getSecurities().getProductCode(),
                    IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_MMF_KEY) == true) {
                
                //API004.銘柄情報.商品コード
                //   'FOREIGN_MMF'(外貨建MMF)
                if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API004.預り区分
                    //   'SPECIFIC'(特定)
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.OPEN_TOTAL_FRIGN_CRNCY_MMF_SPECIFIC;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_GENERAL) == true) {
                    
                    //API004.預り区分
                    //   'GENERAL'(一般)
                    displayType = DisplayType.OPEN_TOTAL_FRIGN_CRNCY_MMF_GENERAL;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_JR_SPECIFIC) == true) {
                    
                    //API004.預り区分
                    //   'JR_SPECIFIC'(ジュニアNISA特定)
                    displayType = DisplayType.OPEN_JRNISA_FRIGN_CRNCY_MMF_SPECIFIC;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_JR_GENERAL) == true) {
                    
                    //API004.預り区分
                    //   'JR_GENERAL'(ジュニアNISA一般)
                    displayType = DisplayType.OPEN_JRNISA_FRIGN_CRNCY_MMF_GENERAL;
                }
            } else if (StringUtils.equals(securitiesBalances.getSecurities().getProductCode(),
                    IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_BOND_KEY) == true) {
                //API004.銘柄情報.商品コード
                //   'FOREIGN_BOND'(外国債券(外貨建))
                if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API004.預り区分
                    //   'SPECIFIC'(特定)
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.OPEN_TOTAL_FRIGN_BND_SPECIFIC;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_GENERAL) == true) {
                    
                    //API004.預り区分
                    //   'GENERAL'(一般)
                    displayType = DisplayType.OPEN_TOTAL_FRIGN_BND_GENERAL;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_JR_SPECIFIC) == true) {
                    
                    //API004.預り区分
                    //   'JR_SPECIFIC'(ジュニアNISA特定)
                    displayType = DisplayType.OPEN_JRNISA_FRIGN_BND_SPECIFIC;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_JR_GENERAL) == true) {
                    
                    //API004.預り区分
                    //   'JR_GENERAL'(ジュニアNISA一般)
                    displayType = DisplayType.OPEN_JRNISA_FRIGN_BND_GENERAL;
                }
            }
            
            //ジュニアNISA口座未開設口座判定
        } else {
            
            //外国株式
            if (StringUtils.equals(securitiesBalances.getSecurities().getProductCode(),
                    IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_STOCK_KEY) == true) {
                
                if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API004.預り区分
                    //   'SPECIFIC'(特定)
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.CLOSE_TOTAL_FRIGN_STK_SPECIFIC;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_GENERAL) == true) {
                    
                    //API004.預り区分
                    //   'GENERAL'(一般)
                    displayType = DisplayType.CLOSE_TOTAL_FRIGN_STK_GENERAL;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_GROWTH_INVESTMENT) == true) {
                    
                    //API004.預り区分
                    //   'GROWTH_INVESTMENT'(成長投資枠)
                    displayType = DisplayType.CLOSE_TOTAL_FRIGN_STK_NISA;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_NISA) == true) {
                    
                    //API004.預り区分
                    //   'NISA'(NISA)
                    displayType = DisplayType.CLOSE_TOTAL_FRIGN_STK_OLD_NISA;
                }
                
                //外貨建MMF
            } else if (StringUtils.equals(securitiesBalances.getSecurities().getProductCode(),
                    IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_MMF_KEY) == true) {
                if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API004.預り区分
                    //   'SPECIFIC'(特定)
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.CLOSE_TOTAL_FRIGN_CRNCY_MMF_SPECIFIC;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_GENERAL) == true) {
                    
                    //API004.預り区分
                    //   'GENERAL'(一般)
                    displayType = DisplayType.CLOSE_TOTAL_FRIGN_CRNCY_MMF_GENERAL;
                }
            } else if (StringUtils.equals(securitiesBalances.getSecurities().getProductCode(),
                    IfaHoldingSecurityListServiceImpL.API004_PRODUCT_CODE_FOREIGN_BOND_KEY) == true) {
                if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_SPECIFIC) == true
                        && (StringUtils.equals(specificAccountType,
                                IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_DEPUTIZE_PAYMENT) == true
                                || StringUtils.equals(specificAccountType,
                                        IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_TAX_RETURN) == true)) {
                    
                    //API004.預り区分
                    //   'SPECIFIC'(特定)
                    //顧客共通情報.特定口座区分
                    //   '1'(特定口座(代行納付))／
                    //   '2'(特定口座(確定申告))
                    displayType = DisplayType.CLOSE_TOTAL_FRIGN_BND_SPECIFIC;
                } else if (StringUtils.equals(securitiesBalances.getSpecificAccountCode(),
                        IfaHoldingSecurityListServiceImpL.API004_SPECIFIC_ACCOUNT_CODE_GENERAL) == true) {
                    
                    //API004.預り区分
                    //   'GENERAL'(一般)
                    displayType = DisplayType.CLOSE_TOTAL_FRIGN_BND_GENERAL;
                }
            }
        }
        
        return displayType;
    }
    
    /**
     * 表示区分の取得
     *
     * @param depositType SQL010レスポンス.預り区分
     * @param cc           顧客協情報
     * @return 表示区分
     */
    private DisplayType getDisplayType(String depositType, CustomerCommon cc) {
        
        DisplayType displayType = null;
        
        //ジュニアISA契約区分
        String jrIsaContractType = cc.getJrIsaContractType();
        
        //ジュニアNISA口座開設済み口座判定
        if (StringUtils.equals(jrIsaContractType,
                IfaHoldingSecurityListServiceImpL.JR_ISA_CONTRACT_TYPE_OPEN) == true) {
            
                //SQL010.預り区分
                // ST
                if (StringUtils.equals(depositType, "2") == true) {
                    
                    //SQL010.預り区分
                    //   'SPECIFIC'(特定)
                    displayType = DisplayType.OPEN_TOTAL_SECURITY_TOKEN_SPECIFIC;
                } else if (StringUtils.equals(depositType, "1") == true) {
                    
                    //SQL010.預り区分
                    //   'GENERAL'(一般)
                    displayType = DisplayType.OPEN_TOTAL_SECURITY_TOKEN_GENERAL;
                } else if (StringUtils.equals(depositType, "6") == true) {
                    
                    //SQL010.預り区分
                    //   'JR_SPECIFIC'(ジュニアNISA特定)
                    displayType = DisplayType.OPEN_JRNISA_SECURITY_TOKEN_SPECIFIC;
                } else if (StringUtils.equals(depositType, "5") == true) {
                    
                    //SQL010.預り区分
                    //   'JR_GENERAL'(ジュニアNISA一般)
                    displayType = DisplayType.OPEN_JRNISA_SECURITY_TOKEN_GENERAL;
                }
            
            //ジュニアNISA口座未開設口座判定
        } else {
            
            // ST
                if (StringUtils.equals(depositType, "2") == true) {
                    
                    //SQL010.預り区分
                    //   'SPECIFIC'(特定)
                    displayType = DisplayType.CLOSE_TOTAL_SECURITY_TOKEN_SPECIFIC;
                } else if (StringUtils.equals(depositType, "1") == true) {
                    
                    //SQL010.預り区分
                    //   'GENERAL'(一般)
                    displayType = DisplayType.CLOSE_TOTAL_SECURITY_TOKEN_GENERAL;
                }
        }
        
        return displayType;
    }
    
    /**
     * API001レスポンスに対する国内株式判定
     *
     * @param api001ResSub API001レスポンス.明細部
     * @return 国内株式である場合はtrue、そうでない場合はfalse
     */
    private boolean isDomesticStock(AccountSumWebData api001OutSub) {
        
        boolean isDomesticStock = false;
        
        if (StringUtils.equals(api001OutSub.getSecTypeName(),
                IfaHoldingSecurityListServiceImpL.API001_SEC_TYPE_NAME_DOMESTIC_STOCK) == true
                && StringUtils.equals(api001OutSub.getSecId(),
                        IfaHoldingSecurityListServiceImpL.API001_SEC_ID_DOMESTIC_STOCK) == true
                && (new BigDecimal(api001OutSub.getPosition())).compareTo(BigDecimal.ZERO) == 1) {
            
            //API001.商品タイプ名
            //   '国内株式'
            //API001.商品区分
            //   'K'(株式（国内/外国）)
            isDomesticStock = true;
        }
        
        return isDomesticStock;
    }
    
    /**
     * 国内株式情報の取得
     *
     * @param domesticStockMap 国内株式情報管理マップ
     * @param displayType 画面表示種別
     * @return 国内株式情報
     */
    private IfaHoldingSecurityListResponseDtoDomesticStock getDomesticStock(
            Map<String, IfaHoldingSecurityListResponseDtoDomesticStock> domesticStockMap, DisplayType displayType) {
        
        IfaHoldingSecurityListResponseDtoDomesticStock domesticStock = null;
        // 口座区分
        String depositBalanceAccountTypeName;
        // 預り区分
        String depositType;
        
        switch (displayType) {
            case OPEN_TOTAL_DMSTC_STK_SPECIFIC:
                depositBalanceAccountTypeName = "1";
                depositType = "2";
                break;
            case OPEN_TOTAL_DMSTC_STK_GENERAL:
                depositBalanceAccountTypeName = "1";
                depositType = "3";
                break;
            case OPEN_TOTAL_DMSTC_STK_NISA:
                depositBalanceAccountTypeName = "1";
                depositType = "5";
                break;
            case OPEN_TOTAL_DMSTC_STK_OLD_NISA:
                depositBalanceAccountTypeName = "1";
                depositType = "1";
                break;
            case OPEN_JRNISA_DMSTC_STK_SPECIFIC:
                depositBalanceAccountTypeName = "2";
                depositType = "2";
                break;
            case OPEN_JRNISA_DMSTC_STK_GENERAL:
                depositBalanceAccountTypeName = "2";
                depositType = "3";
                break;
            case OPEN_JRNISA_DMSTC_STK_NISA:
                depositBalanceAccountTypeName = "2";
                depositType = "7";
                break;
            case OPEN_JRNISA_DMSTC_STK_OLD_NISA:
                depositBalanceAccountTypeName = "2";
                depositType = "1";
                break;
            case CLOSE_TOTAL_DMSTC_STK_SPECIFIC:
                depositBalanceAccountTypeName = "3";
                depositType = "2";
                break;
            case CLOSE_TOTAL_DMSTC_STK_GENERAL:
                depositBalanceAccountTypeName = "3";
                depositType = "3";
                break;
            case CLOSE_TOTAL_DMSTC_STK_NISA:
                depositBalanceAccountTypeName = "3";
                depositType = "5";
                break;
            case CLOSE_TOTAL_DMSTC_STK_OLD_NISA:
                depositBalanceAccountTypeName = "3";
                depositType = "1";
                break;
            default:
                depositBalanceAccountTypeName = null;
                depositType = null;
                break;
        }
        
        if (depositBalanceAccountTypeName != null && depositType != null) {
            String key = depositBalanceAccountTypeName + "," + depositType;
            if ((domesticStock = domesticStockMap.get(key)) == null) {
                domesticStock = new IfaHoldingSecurityListResponseDtoDomesticStock();
                // 口座区分
                domesticStock.setDepositBalanceAccountTypeName(depositBalanceAccountTypeName);
                // 預り区分
                domesticStock.setDepositType(depositType);
                // 預り銘柄数
                domesticStock.setNumberOfDepositedIssues("0");
                // 評価額合計
                domesticStock.setValuationTotal("0");
                // 評価損益合計
                domesticStock.setGetProfitAll("0");
                
                domesticStockMap.put(key, domesticStock);
            }
        }
        return domesticStock;
    }
    
    /**
     * API001レスポンスに対する国内株式 預り明細情報の作成
     *
     * @param api001ResSub API001レスポンス.明細部
     * @param displayType 画面表示種別
     * @return 国内株式 預り明細情報
     */
    private IfaHoldingSecurityListResponseDtoDomesticStockDepositDetail createDomesticStockDetail(
            AccountSumWebData api001OutSub, DisplayType displayType) {
        
        IfaHoldingSecurityListResponseDtoDomesticStockDepositDetail detail = new IfaHoldingSecurityListResponseDtoDomesticStockDepositDetail();
        
        //銘柄コード
        //API001.会社コード（フロント）（頭4桁）+API001.新旧区分
        detail.setBrandCode(api001OutSub.getCompanyCode().substring(0, 4) + api001OutSub.getNewOldId());
        // 銘柄名の設定
        detail.setBrandName(api001OutSub.getSecName());
        // 保有株数の設定
        detail.setHoldingStock(api001OutSub.getPosition());
        // 売却注文中
        detail.setSellingVolume(api001OutSub.getOrderedQuantity());
        // 取得単価/参考単価
        String acquirePriceReferencePrice = null;
        if (displayType == DisplayType.OPEN_TOTAL_DMSTC_STK_SPECIFIC
                || displayType == DisplayType.OPEN_TOTAL_DMSTC_STK_NISA
                || displayType == DisplayType.OPEN_TOTAL_DMSTC_STK_OLD_NISA
                || displayType == DisplayType.OPEN_JRNISA_DMSTC_STK_SPECIFIC
                || displayType == DisplayType.OPEN_JRNISA_DMSTC_STK_NISA
                || displayType == DisplayType.OPEN_JRNISA_DMSTC_STK_OLD_NISA
                || displayType == DisplayType.CLOSE_TOTAL_DMSTC_STK_SPECIFIC
                || displayType == DisplayType.CLOSE_TOTAL_DMSTC_STK_NISA
                || displayType == DisplayType.CLOSE_TOTAL_DMSTC_STK_OLD_NISA) {
            
            // 特定、旧NISA、NISA（成長投資枠）、ジュニアNISA（継続管理勘定）
            //
            // API001.移動平均単価
            // ※小数点第3位以下切捨て
            BigDecimal acquirePriceReferencePriceValue = new BigDecimal(api001OutSub.getAveragePrice());
            acquirePriceReferencePrice = acquirePriceReferencePriceValue.setScale(2, RoundingMode.DOWN).toPlainString();
        } else {
            // 一般
            //
            // API001.加重平均単価 
            //  ※小数点第3位以下切捨て
            if (api001OutSub.getWghAveragePrice() != null) {
                BigDecimal acquirePriceReferencePriceValue = new BigDecimal(api001OutSub.getWghAveragePrice());
                acquirePriceReferencePrice = acquirePriceReferencePriceValue.setScale(2, RoundingMode.DOWN)
                        .toPlainString();
            }
        }
        detail.setAcquirePriceReferencePrice(acquirePriceReferencePrice);
        
        // 取得金額/参考金額
        String acquireAmountReferenceAmount = null;
        if (displayType == DisplayType.OPEN_TOTAL_DMSTC_STK_SPECIFIC
                || displayType == DisplayType.OPEN_TOTAL_DMSTC_STK_NISA
                || displayType == DisplayType.OPEN_TOTAL_DMSTC_STK_OLD_NISA
                || displayType == DisplayType.OPEN_JRNISA_DMSTC_STK_SPECIFIC
                || displayType == DisplayType.OPEN_JRNISA_DMSTC_STK_NISA
                || displayType == DisplayType.OPEN_JRNISA_DMSTC_STK_OLD_NISA
                || displayType == DisplayType.CLOSE_TOTAL_DMSTC_STK_SPECIFIC
                || displayType == DisplayType.CLOSE_TOTAL_DMSTC_STK_NISA
                || displayType == DisplayType.CLOSE_TOTAL_DMSTC_STK_OLD_NISA) {
            
            // 特定、旧NISA、NISA（成長投資枠）、ジュニアNISA（継続管理勘定）
            //
            // API001.移動平均単価 × API001.残高数量
            // ※計算結果の小数点第3位を四捨五入
            BigDecimal wkAveragePrice = new BigDecimal(api001OutSub.getAveragePrice());
            BigDecimal acquireAmountReferenceAmountValue = wkAveragePrice
                    .multiply(new BigDecimal(api001OutSub.getPosition()));
            acquireAmountReferenceAmount = acquireAmountReferenceAmountValue.setScale(2, RoundingMode.HALF_UP)
                    .toPlainString();
        } else {
            // API001.加重平均単価 × API001.残高数量
            // ※計算結果の小数点第3位を四捨五入
            if (api001OutSub.getWghAveragePrice() != null) {
                BigDecimal wkWghAveragePrice = new BigDecimal(api001OutSub.getWghAveragePrice());
                BigDecimal acquireAmountReferenceAmountValue = wkWghAveragePrice
                        .multiply(new BigDecimal(api001OutSub.getPosition()));
                acquireAmountReferenceAmount = acquireAmountReferenceAmountValue.setScale(2, RoundingMode.HALF_UP)
                        .toPlainString();
            }
        }
        detail.setAcquireAmountReferenceAmount(acquireAmountReferenceAmount);
        
        // 商品区分
        detail.setSecurityType(api001OutSub.getSecId());
        // 国内外国区分
        detail.setKokunaiGaiKbn(api001OutSub.getDomesticFgnId());
        // 商品種別１
        detail.setSecurityClass1(api001OutSub.getSecType1());
        // 商品種別２
        detail.setSecurityClass2(api001OutSub.getSecType2());
        // 会社ｺｰﾄﾞ
        detail.setCompanyCode(api001OutSub.getCompanyCode());
        // 権利区分
        detail.setRightType(api001OutSub.getStRightId());
        // 新旧区分
        detail.setNewOldType(api001OutSub.getNewOldId());
        // 回数
        detail.setTimes(api001OutSub.getSerNo());
        // 号1
        detail.setIssue1(api001OutSub.getSubCode1());
        // 号2
        detail.setIssue2(api001OutSub.getSubCode2());
        // 上場国ｺｰﾄﾞ
        detail.setListedCountryCode(api001OutSub.getListCntryCd());
        // 非特定預り区分
        detail.setDepositBalanceListSpecificDepositType(api001OutSub.getHitokuteiKbn());
        return detail;
    }
    
    /**
     * API001レスポンスに対する投資信託判定
     *
     * @param api001ResSub API001レスポンス.明細部
     * @return 投資信託である場合はtrue、そうでない場合はfalse
     */
    private boolean isInvestmentTrust(AccountSumWebData api001OutSub) {
        
        boolean isInvestmentTrust = false;
        
        if (StringUtils.equals(api001OutSub.getSecTypeName(),
                IfaHoldingSecurityListServiceImpL.API001_SEC_TYPE_NAME_INVESTMENT_TRUST) == true
                
                && (StringUtils.equals(api001OutSub.getSecId(),
                        IfaHoldingSecurityListServiceImpL.API001_SEC_ID_INVESTMENT_TRUST) == true
                        || StringUtils.equals(api001OutSub.getSecId(),
                                IfaHoldingSecurityListServiceImpL.API001_SEC_ID_INVESTMENT_TRUST_DOMESTIC) == true)
                && (new BigDecimal(api001OutSub.getPosition())).compareTo(BigDecimal.ZERO) == 1) {
            isInvestmentTrust = true;
        }
        
        return isInvestmentTrust;
    }
    
    /**
     * 投資信託情報の取得
     *
     * @param investmentTrustMap    投資信託情報管理マップ
     * @param displayType 画面表示種別
     * @return 投資信託情報
     */
    private IfaHoldingSecurityListResponseDtoInvestmentTrust getInvestmentTrust(
            Map<String, IfaHoldingSecurityListResponseDtoInvestmentTrust> investmentTrustMap, DisplayType displayType) {
        
        IfaHoldingSecurityListResponseDtoInvestmentTrust investmentTrust = null;
        
        // 分配金受取方法区分
        String reinvest;
        // 口座区分
        String depositBalanceAccountTypeName;
        // 預り区分
        String depositType;
        
        switch (displayType) {
            case OPEN_TOTAL_TNVSTMT_TRST_UNIT_SPECIFIC:
                reinvest = "1";
                depositBalanceAccountTypeName = "1";
                depositType = "2";
                break;
            case OPEN_TOTAL_TNVSTMT_TRST_UNIT_GENERAL:
                reinvest = "1";
                depositBalanceAccountTypeName = "1";
                depositType = "3";
                break;
            case OPEN_TOTAL_TNVSTMT_TRST_UNIT_NISA:
                reinvest = "1";
                depositBalanceAccountTypeName = "1";
                depositType = "5";
                break;
            case OPEN_TOTAL_TNVSTMT_TRST_UNIT_EX_NISA:
                reinvest = "1";
                depositBalanceAccountTypeName = "1";
                depositType = "6";
                break;
            case OPEN_TOTAL_TNVSTMT_TRST_UNIT_OLD_NISA:
                reinvest = "1";
                depositBalanceAccountTypeName = "1";
                depositType = "1";
                break;
            case OPEN_TOTAL_TNVSTMT_TRST_UNIT_OLD_EX_NISA:
                reinvest = "1";
                depositBalanceAccountTypeName = "1";
                depositType = "4";
                break;
            case OPEN_TOTAL_TNVSTMT_TRST_AMNT_SPECIFIC:
                reinvest = "2";
                depositBalanceAccountTypeName = "1";
                depositType = "2";
                break;
            case OPEN_TOTAL_TNVSTMT_TRST_AMNT_GENERAL:
                reinvest = "2";
                depositBalanceAccountTypeName = "1";
                depositType = "3";
                break;
            case OPEN_TOTAL_TNVSTMT_TRST_AMNT_NISA:
                reinvest = "2";
                depositBalanceAccountTypeName = "1";
                depositType = "5";
                break;
            case OPEN_TOTAL_TNVSTMT_TRST_AMNT_EX_NISA:
                reinvest = "2";
                depositBalanceAccountTypeName = "1";
                depositType = "6";
                break;
            case OPEN_TOTAL_TNVSTMT_TRST_AMNT_OLD_NISA:
                reinvest = "2";
                depositBalanceAccountTypeName = "1";
                depositType = "1";
                break;
            case OPEN_TOTAL_TNVSTMT_TRST_AMNT_OLD_EX_NISA:
                reinvest = "2";
                depositBalanceAccountTypeName = "1";
                depositType = "4";
                break;
            case OPEN_JRNISA_TNVSTMT_TRST_UNIT_SPECIFIC:
                reinvest = "1";
                depositBalanceAccountTypeName = "2";
                depositType = "2";
                break;
            case OPEN_JRNISA_TNVSTMT_TRST_UNIT_GENERAL:
                reinvest = "1";
                depositBalanceAccountTypeName = "2";
                depositType = "3";
                break;
            case OPEN_JRNISA_TNVSTMT_TRST_UNIT_NISA:
                reinvest = "1";
                depositBalanceAccountTypeName = "2";
                depositType = "7";
                break;
            case OPEN_JRNISA_TNVSTMT_TRST_UNIT_OLD_NISA:
                reinvest = "1";
                depositBalanceAccountTypeName = "2";
                depositType = "1";
                break;
            case OPEN_JRNISA_TNVSTMT_TRST_AMNT_SPECIFIC:
                reinvest = "2";
                depositBalanceAccountTypeName = "2";
                depositType = "2";
                break;
            case OPEN_JRNISA_TNVSTMT_TRST_AMNT_GENERAL:
                reinvest = "2";
                depositBalanceAccountTypeName = "2";
                depositType = "3";
                break;
            case OPEN_JRNISA_TNVSTMT_TRST_AMNT_NISA:
                reinvest = "2";
                depositBalanceAccountTypeName = "2";
                depositType = "7";
                break;
            case OPEN_JRNISA_TNVSTMT_TRST_AMNT_OLD_NISA:
                reinvest = "2";
                depositBalanceAccountTypeName = "2";
                depositType = "1";
                break;
            case CLOSE_TOTAL_TNVSTMT_TRST_UNIT_SPECIFIC:
                reinvest = "1";
                depositBalanceAccountTypeName = "3";
                depositType = "2";
                break;
            case CLOSE_TOTAL_TNVSTMT_TRST_UNIT_GENERAL:
                reinvest = "1";
                depositBalanceAccountTypeName = "3";
                depositType = "3";
                break;
            case CLOSE_TOTAL_TNVSTMT_TRST_UNIT_NISA:
                reinvest = "1";
                depositBalanceAccountTypeName = "3";
                depositType = "5";
                break;
            case CLOSE_TOTAL_TNVSTMT_TRST_UNIT_EX_NISA:
                reinvest = "1";
                depositBalanceAccountTypeName = "3";
                depositType = "6";
                break;
            case CLOSE_TOTAL_TNVSTMT_TRST_UNIT_OLD_NISA:
                reinvest = "1";
                depositBalanceAccountTypeName = "3";
                depositType = "1";
                break;
            case CLOSE_TOTAL_TNVSTMT_TRST_UNIT_OLD_EX_NISA:
                reinvest = "1";
                depositBalanceAccountTypeName = "3";
                depositType = "4";
                break;
            case CLOSE_TOTAL_TNVSTMT_TRST_AMNT_SPECIFIC:
                reinvest = "2";
                depositBalanceAccountTypeName = "3";
                depositType = "2";
                break;
            case CLOSE_TOTAL_TNVSTMT_TRST_AMNT_GENERAL:
                reinvest = "2";
                depositBalanceAccountTypeName = "3";
                depositType = "3";
                break;
            case CLOSE_TOTAL_TNVSTMT_TRST_AMNT_NISA:
                reinvest = "2";
                depositBalanceAccountTypeName = "3";
                depositType = "5";
                break;
            case CLOSE_TOTAL_TNVSTMT_TRST_AMNT_EX_NISA:
                reinvest = "2";
                depositBalanceAccountTypeName = "3";
                depositType = "6";
                break;
            case CLOSE_TOTAL_TNVSTMT_TRST_AMNT_OLD_NISA:
                reinvest = "2";
                depositBalanceAccountTypeName = "3";
                depositType = "1";
                break;
            case CLOSE_TOTAL_TNVSTMT_TRST_AMNT_OLD_EX_NISA:
                reinvest = "2";
                depositBalanceAccountTypeName = "3";
                depositType = "4";
                break;
            default:
                reinvest = null;
                depositBalanceAccountTypeName = null;
                depositType = null;
                break;
        }
        
        if (reinvest != null && depositBalanceAccountTypeName != null && depositType != null) {
            String key = reinvest + "," + depositBalanceAccountTypeName + "," + depositType;
            if ((investmentTrust = investmentTrustMap.get(key)) == null) {
                investmentTrust = new IfaHoldingSecurityListResponseDtoInvestmentTrust();
                // 分配金受取方法区分
                investmentTrust.setReinvest(reinvest);
                // 口座区分
                investmentTrust.setDepositBalanceAccountTypeName(depositBalanceAccountTypeName);
                // 預り区分
                investmentTrust.setDepositType(depositType);
                // 預り銘柄数
                investmentTrust.setNumberOfDepositedIssues("0");
                // 評価額合計
                investmentTrust.setValuationTotal("0");
                // 評価損益合計
                investmentTrust.setGetProfitAll("0");
                
                //コース
                if (StringUtils.equals(displayType.getBuyType(), "口数") == true) {
                    investmentTrust.setCourse("1");
                } else {
                    investmentTrust.setCourse("2");
                }
                
                investmentTrustMap.put(key, investmentTrust);
            }
        }
        return investmentTrust;
    }
    
    /**
     * API001レスポンスに対する投資信託 預り明細情報の作成
     *
     * @param api001ResSub API001レスポンス.明細部
     * @param displayType 画面表示種別
     * @return 投資信託 預り明細情報
     */
    private IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail createInvestmentTrustDepositDetail(
            AccountSumWebData api001OutSub, DisplayType displayType) {
        
        IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail detail = new IfaHoldingSecurityListResponseDtoInvestmentTrustDepositDetail();
        
        // 銘柄コード
        // API001.明細部.回数＋'-'＋API001.明細部.号2
        String serNo = (api001OutSub.getSerNo() != null) ? api001OutSub.getSerNo() : "";
        String subCode2 = (api001OutSub.getSubCode2() != null) ? api001OutSub.getSubCode2() : "";
        detail.setBrandCode(serNo + "." + subCode2);
        // ファンド名の設定
        // API001  明細部.銘柄名
        detail.setFundName(api001OutSub.getSecName());
        // 保有口数の設定
        // API001  明細部.残高数量
        detail.setUnitVolume(api001OutSub.getPosition());
        // 売却注文中
        detail.setSellingVolume(api001OutSub.getOrderedQuantity());
        
        // 個別元本
        // API001  明細部.個別(取得)元本
        detail.setIndividualPrincipal(api001OutSub.getAcqPrincipal());
        
        // 非特定預り区分
        // API001  明細部.非特定預り区分
        detail.setDepositBalanceListSpecificDepositType(api001OutSub.getHitokuteiKbn());
        
        // 回数
        // API001  明細部.回数
        detail.setTimes(api001OutSub.getSerNo());
        // 号1
        // API001  明細部.号1
        detail.setIssue1(api001OutSub.getSubCode1());
        // 号2
        // API001  明細部.号2
        detail.setIssue2(api001OutSub.getSubCode2());
        
        // 商品区分
        // API001  明細部.商品区分
        detail.setSecurityType(api001OutSub.getSecId());
        // 国内外国区分
        // API001  明細部.国内外国区分
        detail.setKokunaiGaiKbn(api001OutSub.getDomesticFgnId());
        
        // 商品種別１
        // API001  明細部.商品種別１
        detail.setSecurityClass1(api001OutSub.getSecType1());
        // 商品種別２
        // API001  明細部.商品種別２
        detail.setSecurityClass2(api001OutSub.getSecType2());
        
        // 会社ｺｰﾄﾞ
        // API001  明細部.会社ｺｰﾄﾞ
        detail.setCompanyCode(api001OutSub.getCompanyCode());
        
        // 権利区分
        // API001  明細部.権利区分ﾞ
        detail.setRightType(api001OutSub.getStRightId());
        // 新旧区分
        // API001  明細部.新旧区分
        detail.setNewOldType(api001OutSub.getNewOldId());
        // 上場国ｺｰﾄﾞ
        // API001  明細部.上場国ｺｰﾄﾞ
        detail.setListedCountryCode(api001OutSub.getListCntryCd());
        
        return detail;
    }
    
    /**
     * 投資信託、預り：特定 における取得単価/参考単価の取得
     *
     * @param api001ResSub API001レスポンス.明細部
     * @return 取得単価/参考単価
     */
    private String getInvTrustAcquirePriceReferencePriceSpecific(AccountSumWebData api001OutSub) {
        
        // 取得単価/参考単価
        // API001.移動平均単価
        // ※小数点第3位以下切捨て
        BigDecimal acquirePriceReferencePrice = new BigDecimal(api001OutSub.getAveragePrice());
        return acquirePriceReferencePrice.setScale(2, RoundingMode.DOWN).toPlainString();
    }
    
    /**
     * 投資信託、預り:特定 における取得金額/参考金額の取得
     *
     * @param api001ResSub API001レスポンス.明細部
     * @param basePriceUnit SQL002.基準価額単位
     * @return 取得金額/参考金額
     */
    private String getInvTrustAcquireAmountReferenceAmountSpecific(AccountSumWebData api001OutSub,
            String basePriceUnit) {
        
        // 取得金額/参考金額
        // API001.移動平均単価 × API001.残高数量 ÷ SQL002.基準価額単位
        // ※計算結果の小数点以下切捨て
        // ※SQL002.基準価額単位がゼロの場合、'-'(半角ハイフン)を設定
        
        BigDecimal wkBasePriceUnit = new BigDecimal(basePriceUnit);
        if (wkBasePriceUnit.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal wkAveragePrice = new BigDecimal(api001OutSub.getAveragePrice());
            BigDecimal acquireAmountReferenceAmount = wkAveragePrice
                    .multiply(new BigDecimal(api001OutSub.getPosition()));
            acquireAmountReferenceAmount = acquireAmountReferenceAmount.divide(wkBasePriceUnit, 0, RoundingMode.DOWN);
            return acquireAmountReferenceAmount.toPlainString();
        } else {
            return null;
        }
        
    }
    
    /**
     * 投資信託における評価額の取得
     *
     * @param api001ResSub API001レスポンス.明細部
     * @param basePrice SQL011.基準価額
     * @param basePriceUnit SQL002.基準価額単位
     * @return 評価額
     */
    private String getInvTrustValuation(AccountSumWebData api001OutSub, BigDecimal basePrice, String basePriceUnit) {
        
        String rtnValuation = null;
        
        // 評価額
        // SQL011.基準価額 × API001.残高数量 ÷ SQL002.基準価額単位
        // ※計算結果の小数点以下切り捨て
        //
        //※計算結果の小数点以下切捨て
        //※SQL002.基準価額単位がゼロ、またはSQL011が0件、またはSQL011.基準価額がスペース、またはSQL011.基準価額がゼロ以下の場合、'-'(半角ハイフン)を設定 (画面側でNULLを'-'(半角ハイフン)置換)        
        //SQL011.基準価額は、SQL011が0件、またはSQL011.基準価額がスペースの場合、0として受け渡される
        if ((new BigDecimal(basePriceUnit)).compareTo(BigDecimal.ZERO) != 0 && basePrice.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal valuation = basePrice.multiply(new BigDecimal(api001OutSub.getPosition()));
            valuation = valuation.divide(new BigDecimal(basePriceUnit), 0, RoundingMode.DOWN);
            rtnValuation = valuation.toPlainString();
        }
        return rtnValuation;
    }
    
    /**
     * 投資信託における評価損益の取得
     *
     * @param valuation 評価額
     * @param acquireAmountReferenceAmount 取得金額
     * @return 評価損益
     */
    private String getInvTrustMutualFundListProfitLossTotal(String valuation, String acquireAmountReferenceAmount) {
        
        String rtnMutualFundListProfitLossTotal = null;
        // 評価損益
        //評価額 － 取得金額
        // ※計算結果の小数点以下切り捨て
        //
        // ※計算結果の小数点以下切捨て
        // ※評価額が'-'(半角ハイフン)またはマイナスの場合、'-'(半角ハイフン)を設定
        if (valuation != null && new BigDecimal(valuation).compareTo(BigDecimal.ZERO) >= 0 ) {
            BigDecimal mutualFundListProfitLossTotal = (new BigDecimal(valuation))
                    .subtract(new BigDecimal(acquireAmountReferenceAmount));
            rtnMutualFundListProfitLossTotal = mutualFundListProfitLossTotal.setScale(0, RoundingMode.DOWN)
                    .toPlainString();
        }
        return rtnMutualFundListProfitLossTotal;
    }
    
    /**
     * 投資信託、預り:特定 買付方法:口座における買付表示区分の取得
     *
     * @param api001ResSub API001レスポンス.明細部
     * @param brand FCT017.銘柄情報
     * @param fct001Dto FCT001レスポンス
     * @param fct003Dto FCT003レスポンス
     * @return 買付表示区分
     */
    private String getInvTrustBuyDisplayClassificationUnitSpecific(AccountSumWebData api001OutSub,
            OutputFct017Dto.Brand brand, OutputFct001Dto fct001Dto, OutputFct003Dto fct003Dto) {
        
        String buyDisplayClassification = null;
        
        // 買付表示区分
        if (StringUtils.equals(brand.getAcceptanceNecessity(),
                IfaHoldingSecurityListServiceImpL.FCT017_ACCEPTANCE_NECESSITY_AVAILABLE) == false) {
            if (api001OutSub.getSerNo() == null && api001OutSub.getSubCode1() == null
                    && api001OutSub.getSubCode1() == null) {
                buyDisplayClassification = "0";
            } else {
                if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true && isMediatePropriety(fct003Dto,
                        IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_INVESTMENT_TRUST,
                        IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_BUY, null, null) == true) {
                    buyDisplayClassification = "1";
                } else {
                    buyDisplayClassification = "0";
                }
            }
        } else {
            buyDisplayClassification = "0";
        }
        
        return buyDisplayClassification;
    }
    
    /**
     * 投資信託、預り:特定 買付方法:口座における売却表示区分の取得
     *
     * @param api001ResSub API001レスポンス.明細部
     * @param fct001Dto FCT001レスポンス
     * @param fct003Dto FCT003レスポンス
     * @return 売却表示区分
     */
    private String getInvTrustSaleDisplayClassificationUnitSpecific(AccountSumWebData api001OutSub,
            OutputFct001Dto fct001Dto, OutputFct003Dto fct003Dto) {
        
        String saleDisplayClassification = null;
        
        // 売却表示区分
        if (api001OutSub.getSerNo() == null && api001OutSub.getSubCode1() == null
                && api001OutSub.getSubCode1() == null) {
            
            saleDisplayClassification = "0";
        } else {
            if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true && isMediatePropriety(fct003Dto,
                    IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_INVESTMENT_TRUST,
                    IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_CANCEL_GEN, null, null) == true) {
                
                BigDecimal wkPosition = new BigDecimal(api001OutSub.getPosition());
                BigDecimal wkOrderedQuantity = new BigDecimal(api001OutSub.getOrderedQuantity());
                
                if ((wkPosition.subtract(wkOrderedQuantity)).compareTo(BigDecimal.ZERO) == -1) {
                    
                    saleDisplayClassification = "3";
                } else {
                    saleDisplayClassification = "1";
                }
            } else {
                saleDisplayClassification = "0";
            }
        }
        
        return saleDisplayClassification;
    }
    
    /**
     * 投資信託、預り:特定 買付方法:金額における買付表示区分の取得
     *
     * @param api001ResSub API001レスポンス.明細部
     * @param brand FCT017.銘柄情報
     * @param fct001Dto FCT001レスポンス
     * @param fct003Dto FCT003レスポンス
     * @return 買付表示区分
     */
    private String getInvTrustBuyDisplayClassificationAmntSpecific(AccountSumWebData api001OutSub,
            OutputFct017Dto.Brand brand, OutputFct001Dto fct001Dto, OutputFct003Dto fct003Dto) {
        
        String buyDisplayClassification = null;
        
        // 買付表示区分
        if (StringUtils.equals(brand.getAcceptanceNecessity(),
                IfaHoldingSecurityListServiceImpL.FCT017_ACCEPTANCE_NECESSITY_AVAILABLE) == false) {
            if (api001OutSub.getSerNo() == null && api001OutSub.getSubCode1() == null
                    && api001OutSub.getSubCode1() == null) {
                buyDisplayClassification = "0";
            } else {
                if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true && isMediatePropriety(fct003Dto,
                        IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_INVESTMENT_TRUST,
                        IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_BUYING, null, null) == true) {
                    buyDisplayClassification = "1";
                } else {
                    buyDisplayClassification = "0";
                }
            }
        } else {
            buyDisplayClassification = "0";
        }
        
        return buyDisplayClassification;
    }
    
    /**
     * 投資信託、買付方法:金額における売却表示区分の取得
     *
     * @param api001ResSub API001レスポンス.明細部
     * @param fct001Dto FCT001レスポンス
     * @param fct003Dto FCT003レスポンス
     * @return 売却表示区分
     */
    private String getInvTrustSaleDisplayClassificationAmnt(AccountSumWebData api001OutSub, OutputFct001Dto fct001Dto,
            OutputFct003Dto fct003Dto) {
        
        String saleDisplayClassification = null;
        
        // 売却表示区分
        if (api001OutSub.getSerNo() == null && api001OutSub.getSubCode1() == null
                && api001OutSub.getSubCode1() == null) {
            saleDisplayClassification = "0";
        } else {
            if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true && isMediatePropriety(fct003Dto,
                    IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_INVESTMENT_TRUST,
                    IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_CANCEL_TOTAL, null, null) == true) {
                
                BigDecimal wkPosition = new BigDecimal(api001OutSub.getPosition());
                BigDecimal wkOrderedQuantity = new BigDecimal(api001OutSub.getOrderedQuantity());
                
                if ((wkPosition.subtract(wkOrderedQuantity)).compareTo(BigDecimal.ZERO) == -1) {
                    saleDisplayClassification = "3";
                } else {
                    saleDisplayClassification = "1";
                }
            } else {
                saleDisplayClassification = "0";
            }
        }
        
        return saleDisplayClassification;
    }
    
    /**
     * 投資信託、預り:Nisa 買付方法:金額における買付表示区分の取得
     *
     * @param api001ResSub API001レスポンス.明細部
     * @param brand FCT017.銘柄情報
     * @param fct001Dto FCT001レスポンス
     * @param fct003Dto FCT003レスポンス
     * @param cc        顧客共通情報
     * @return 買付表示区分
     */
    private String getInvTrustBuyDisplayClassificationAmntNisa(AccountSumWebData api001OutSub,
            OutputFct017Dto.Brand brand, OutputFct001Dto fct001Dto, OutputFct003Dto fct003Dto,
            IfaHoldingSecurityListSql006ResponseModel sql006Res) {
        
        String buyDisplayClassification = null;
        
        // 買付表示区分
        if (StringUtils.equals(brand.getAcceptanceNecessity(),
                IfaHoldingSecurityListServiceImpL.FCT017_ACCEPTANCE_NECESSITY_AVAILABLE) == false) {
            if (StringUtils.equals(sql006Res.getIsaBuyAbleThisYear(), "2") == false
                    && StringUtils.equals(sql006Res.getIsaBuyAbleNextYear(), "2") == false) {
                buyDisplayClassification = "0";
            } else if (api001OutSub.getSerNo() == null && api001OutSub.getSubCode1() == null
                    && api001OutSub.getSubCode1() == null) {
                buyDisplayClassification = "0";
            } else {
                if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true && isMediatePropriety(fct003Dto,
                        IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_INVESTMENT_TRUST,
                        IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_BUYING, null, null) == true) {
                    buyDisplayClassification = "1";
                } else {
                    buyDisplayClassification = "0";
                }
            }
        } else {
            buyDisplayClassification = "0";
        }
        
        return buyDisplayClassification;
    }
    
    /**
     * 投資信託、預り:旧Nisa 買付方法:金額における買付表示区分の取得
     *
     * @param api001ResSub API001レスポンス.明細部
     * @param brand FCT017.銘柄情報
     * @param fct001Dto FCT001レスポンス
     * @param fct003Dto FCT003レスポンス
     * @param cc        顧客共通情報
     * @return 買付表示区分
     */
    private String getInvTrustBuyDisplayClassificationAmntOldNisa(AccountSumWebData api001OutSub,
            OutputFct017Dto.Brand brand, OutputFct001Dto fct001Dto, OutputFct003Dto fct003Dto,
            IfaHoldingSecurityListSql006ResponseModel sql006Res) {
        
        String buyDisplayClassification = null;
        
        // 買付表示区分
        if (StringUtils.equals(brand.getAcceptanceNecessity(),
                IfaHoldingSecurityListServiceImpL.FCT017_ACCEPTANCE_NECESSITY_AVAILABLE) == false) {
            if (StringUtils.equals(sql006Res.getIsaBuyAbleThisYear(), "1") == false) {
                buyDisplayClassification = "0";
            } else if (api001OutSub.getSerNo() == null && api001OutSub.getSubCode1() == null
                    && api001OutSub.getSubCode1() == null) {
                buyDisplayClassification = "0";
            } else {
                if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), "0") == true && isMediatePropriety(fct003Dto,
                        IfaHoldingSecurityListServiceImpL.FCT003_SECURITY_MONEY_CLASS_INVESTMENT_TRUST,
                        IfaHoldingSecurityListServiceImpL.FCT003_TRADE_CLASS_BUYING, null, null) == true) {
                    buyDisplayClassification = "1";
                } else {
                    buyDisplayClassification = "0";
                }
            }
        } else {
            buyDisplayClassification = "0";
        }
        
        return buyDisplayClassification;
    }
    
    /**
     * 投資信託、買付方法:金額における分配金受取方法の取得
     *
     * @param api001ResSub API001レスポンス.明細部
     * @return 分配金受取方法
     */
    private String getInvTrustDistributionReceiveMethodAmnt(AccountSumWebData api001OutSub) {
        
        String distributionReceiveMethod = null;
        
        // 分配金受取方法
        if (StringUtils.equals(api001OutSub.getReinvest(),
                IfaHoldingSecurityListServiceImpL.API001_REINVEST_RECEIPT) == true) {
            
            distributionReceiveMethod = "受取";
        } else if (StringUtils.equals(api001OutSub.getReinvest(),
                IfaHoldingSecurityListServiceImpL.API001_REINVEST_INVESTMENT) == true) {
            
            distributionReceiveMethod = "再投資";
        } else {
            
            distributionReceiveMethod = null;
        }
        
        return distributionReceiveMethod;
    }
    
    /**
     * 投資信託、買付方法:金額における分配金受取方法変更表示区分の取得
     *
     * @param api001ResSub API001レスポンス.明細部
     * @return 分配金受取方法変更表示区分
     */
    private String getInvTrustDistributionreceiveMethodchangedisplayclassificationAmnt(AccountSumWebData api001OutSub) {
        
        String distributionreceiveMethodchangedisplayclassification = null;
        
        // 分配金受取方法変更表示区分
        if (StringUtils.equals(api001OutSub.getReinvest(),
                IfaHoldingSecurityListServiceImpL.API001_REINVEST_RECEIPT) == true) {
            
            distributionreceiveMethodchangedisplayclassification = "1";
        } else if (StringUtils.equals(api001OutSub.getReinvest(),
                IfaHoldingSecurityListServiceImpL.API001_REINVEST_INVESTMENT) == true) {
            
            distributionreceiveMethodchangedisplayclassification = "2";
        } else {
            
            distributionreceiveMethodchangedisplayclassification = "0";
        }
        
        return distributionreceiveMethodchangedisplayclassification;
    }
    
    /**
     * 投資信託、預り：一般 における取得単価/参考単価の取得
     *
     * @param api001ResSub API001レスポンス.明細部
     * @return 取得単価/参考単価
     */
    private String getInvTrustAcquirePriceReferencePriceGeneral(AccountSumWebData api001OutSub) {
        
        // 取得単価/参考単価
        // API001.加重平均単価
        // ※小数点第3位以下切捨て
        BigDecimal acquirePriceReferencePrice = new BigDecimal(api001OutSub.getWghAveragePrice());
        return acquirePriceReferencePrice.setScale(2, RoundingMode.DOWN).toPlainString();
    }
    
    /**
     * 投資信託、預り:一般 における取得金額/参考金額の取得
     *
     * @param api001ResSub API001レスポンス.明細部
     * @param basePriceUnit SQL002.基準価額単位
     * @return 取得金額/参考金額
     */
    private String getInvTrustAcquireAmountReferenceAmountGeneral(AccountSumWebData api001OutSub,
            String basePriceUnit) {
        
        // 取得金額/参考金額
        // API001.加重平均単価 × API001.残高数量 ÷ SQL002.基準価額単位
        // ※計算結果の小数点以下切捨て
        // ※SQL002.基準価額単位がゼロの場合、'-'(半角ハイフン)を設定
        BigDecimal wkBasePriceUnit = new BigDecimal(basePriceUnit);
        if (wkBasePriceUnit.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal acquireAmountReferenceAmount = new BigDecimal(api001OutSub.getWghAveragePrice());
            acquireAmountReferenceAmount = acquireAmountReferenceAmount
                    .multiply(new BigDecimal(api001OutSub.getPosition()));
            acquireAmountReferenceAmount = acquireAmountReferenceAmount.divide(wkBasePriceUnit, 0, RoundingMode.DOWN);
            return acquireAmountReferenceAmount.toPlainString();
        } else {
            return null;
        }
    }
    
    /**
     * 投資信託、預り：NISA における取得単価/参考単価の取得
     *
     * @param api001ResSub API001レスポンス.明細部
     * @return 取得単価/参考単価
     */
    private String getInvTrustAcquirePriceReferencePriceNisa(AccountSumWebData api001OutSub) {
        
        // 取得単価/参考単価
        // API001.移動平均単価
        // ※小数点第3位以下切捨て
        BigDecimal acquirePriceReferencePrice = new BigDecimal(api001OutSub.getAveragePrice());
        return acquirePriceReferencePrice.setScale(2, RoundingMode.DOWN).toPlainString();
    }
    
    /**
     * 投資信託、預り:NISA における取得金額/参考金額の取得
     *
     * @param api001ResSub API001レスポンス.明細部
     * @param basePriceUnit SQL002.基準価額単位
     * @return 取得金額/参考金額
     */
    private String getInvTrustAcquireAmountReferenceAmountNisa(AccountSumWebData api001OutSub, String basePriceUnit) {
        
        // 取得金額/参考金額
        // API001.移動平均単価 × API001.残高数量 ÷ SQL002.基準価額単位
        // ※計算結果の小数点以下切捨て
        // ※SQL002.基準価額単位がゼロの場合、'-'(半角ハイフン)を設定
        
        BigDecimal wkBasePriceUnit = new BigDecimal(basePriceUnit);
        if (wkBasePriceUnit.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal acquireAmountReferenceAmount = new BigDecimal(api001OutSub.getAveragePrice());
            acquireAmountReferenceAmount = acquireAmountReferenceAmount
                    .multiply(new BigDecimal(api001OutSub.getPosition()));
            acquireAmountReferenceAmount = acquireAmountReferenceAmount.divide(wkBasePriceUnit, 0, RoundingMode.DOWN);
            return acquireAmountReferenceAmount.toPlainString();
        } else {
            return null;
        }
    }
    
    /**
     * API001レスポンスに対する国内債券判定
     *
     * @param api001ResSub API001レスポンス.明細部
     * @return 国内債券である場合はtrue、そうでない場合はfalse
     */
    private boolean isDomesticBonds(AccountSumWebData api001OutSub) {
        
        boolean isDomesticBonds = false;
        
        if (StringUtils.equals(api001OutSub.getBrandSearchCode1(),
                IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE1_DOMESTIC_BONDS) == true
                && StringUtils.equals(api001OutSub.getBrandSearchCode2(),
                        IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE2_DOMESTIC_BONDS) == false) {
            isDomesticBonds = true;
        }
        return isDomesticBonds;
    }
    
    /**
     * 国内債券情報の取得
     *
     * @param domesticBondsMap      国内債券情報管理マップ
     * @param displayType 画面表示種別
     * @return 国内債券情報
     */
    private IfaHoldingSecurityListResponseDtoDomesticBonds getDomesticBonds(
            Map<String, IfaHoldingSecurityListResponseDtoDomesticBonds> domesticBondsMap, DisplayType displayType) {
        
        IfaHoldingSecurityListResponseDtoDomesticBonds domesticBonds = null;
        
        // 口座区分
        String depositBalanceAccountTypeName;
        // 預り区分
        String depositType;
        
        switch (displayType) {
            case OPEN_TOTAL_DMSTC_BND_SPECIFIC:
                depositBalanceAccountTypeName = "1";
                depositType = "2";
                break;
            case OPEN_TOTAL_DMSTC_BND_GENERAL:
                depositBalanceAccountTypeName = "1";
                depositType = "3";
                break;
            case OPEN_JRNISA_DMSTC_BND_SPECIFIC:
                depositBalanceAccountTypeName = "2";
                depositType = "2";
                break;
            case OPEN_JRNISA_DMSTC_BND_GENERAL:
                depositBalanceAccountTypeName = "2";
                depositType = "3";
                break;
            case CLOSE_TOTAL_DMSTC_BND_SPECIFIC:
                depositBalanceAccountTypeName = "3";
                depositType = "2";
                break;
            case CLOSE_TOTAL_DMSTC_BND_GENERAL:
                depositBalanceAccountTypeName = "3";
                depositType = "3";
                break;
            default:
                depositBalanceAccountTypeName = null;
                depositType = null;
                break;
        }
        
        if (depositBalanceAccountTypeName != null && depositType != null) {
            String key = depositBalanceAccountTypeName + "," + depositType;
            if ((domesticBonds = domesticBondsMap.get(key)) == null) {
                domesticBonds = new IfaHoldingSecurityListResponseDtoDomesticBonds();
                // 口座区分
                domesticBonds.setDepositBalanceAccountTypeName(depositBalanceAccountTypeName);
                // 預り区分
                domesticBonds.setDepositType(depositType);
                // 預り銘柄数
                domesticBonds.setNumberOfDepositedIssues("0");
                // 評価額合計
                domesticBonds.setValuationTotal("0");
                
                domesticBondsMap.put(key, domesticBonds);
            }
        }
        return domesticBonds;
    }
    
    /**
     * API001レスポンスに対する国内債券 預り明細情報の作成
     *
     * @param api001ResSub API001レスポンス.明細部
     * @param displayType 画面表示種別
     * @return 国内債券 預り明細情報
     */
    private IfaHoldingSecurityListResponseDtoDomesticBondsDepositDetail createDomesticBondsDepositDetail(
            AccountSumWebData api001OutSub) {
        
        IfaHoldingSecurityListResponseDtoDomesticBondsDepositDetail detail = new IfaHoldingSecurityListResponseDtoDomesticBondsDepositDetail();
        
        // 銘柄コード
        // API001.会社コード（フロント）＋'.'＋API001.回数
        String serNo = (api001OutSub.getSerNo() != null) ? api001OutSub.getSerNo() : "";
        detail.setBrandCode(api001OutSub.getCompanyCode() + "." + serNo);
        
        // 銘柄名
        // API001   明細部.銘柄名
        detail.setBrandName(api001OutSub.getSecName());
        
        // 利率(%)
        // API001   明細部.利率
        detail.setCompoundInterestPercent(api001OutSub.getRateInterest());
        
        // 償還日
        // API001   明細部.償還日
        detail.setRedemptionDate(api001OutSub.getRedemptionDate());
        
        // 利払日の設定
        if (StringUtils.equals(api001OutSub.getCouponPayId(),
                IfaHoldingSecurityListServiceImpL.API001_COUPON_PAY_ID_DISCOUNT) == true) {
            // ①API001.利払区分＝0(割引債)の場合,「-」表示。
            detail.setInterestPaymentDate(null);
        } else if (StringUtils.equals(api001OutSub.getCouponPayId(),
                IfaHoldingSecurityListServiceImpL.API001_COUPON_PAY_ID_YEARLY) == true) {
            
            // ②API001.利払区分＝1(年1回)の場合
            //  API001.利払月日1を「MM-DD」で表示。
            //  但し、DD=99の場合は、「MM-末日」で表示。
            
            String couponPayDate1 = api001OutSub.getCouponPayDate1();
            String mm = couponPayDate1.substring(0, 2);
            String dd = (StringUtils.equals(couponPayDate1.substring(2, 4),
                    IfaHoldingSecurityListServiceImpL.API001_COUPON_PAY_DATE_LAST_DAY)) ? "末日"
                            : couponPayDate1.substring(2, 4);
            
            detail.setInterestPaymentDate(mm + "-" + dd);
        } else if (StringUtils.equals(api001OutSub.getCouponPayId(),
                IfaHoldingSecurityListServiceImpL.API001_COUPON_PAY_ID_TWICE) == true) {
            
            // ③API001.利払区分＝2(年2回)の場合
            //   API001.利払月日1,2のDDが同一の場合は、「MM/M'M'-DD」で表示。
            //   但し、DD=99の場合は、「MM/M'M'-末日」で表示。
            //   API001.利払月日1,2のDDが同一でない場合は、「MM-DD,M'M'-D'D'」で表示。
            //   ※MM：API001.利払月日1の月
            //     M'M'：API001.利払月日2の月
            //     DD：API001.利払月日1の日
            //     D'D'：API001.利払月日2の日
            String couponPayDate1 = api001OutSub.getCouponPayDate1();
            String couponPayDate2 = api001OutSub.getCouponPayDate2();
            String mm1 = couponPayDate1.substring(0, 2);
            String dd1 = (StringUtils.equals(couponPayDate1.substring(2, 4),
                    IfaHoldingSecurityListServiceImpL.API001_COUPON_PAY_DATE_LAST_DAY)) ? "末日"
                            : couponPayDate1.substring(2, 4);
            String mm2 = couponPayDate2.substring(0, 2);
            String dd2 = (StringUtils.equals(couponPayDate2.substring(2, 4),
                    IfaHoldingSecurityListServiceImpL.API001_COUPON_PAY_DATE_LAST_DAY)) ? "末日"
                            : couponPayDate2.substring(2, 4);
            
            if (StringUtils.equals(dd1, dd2) == true) {
                detail.setInterestPaymentDate(mm1 + "/" + mm2 + "-" + dd1);
            } else {
                detail.setInterestPaymentDate(mm1 + "-" + dd1 + "," + mm2 + "-" + dd2);
            }
        } else if (StringUtils.equals(api001OutSub.getCouponPayId(),
                IfaHoldingSecurityListServiceImpL.API001_COUPON_PAY_ID_FOUR) == true) {
            
            // ④API001.利払区分＝3(年4回)の場合
            //   API001.利払月日1,2,3,4のDDが全て同一の場合は、「MM/M'M'/M''M''/M'''/M'''-DD」で表示。
            //   但し、DD=99の場合は、「MM/M'M'/M''M''/M'''/M'''-末日」で表示。
            //   API001.利払月日1,2,3,4のDDが同一でない場合は、「MM-DD,M'M'-D'D',M''M''-D''D'',M'''M'''-D'''D'''」で表示
            //   ※M''M''：API001.利払月日3の月
            //   M'''M'''：API001.利払月日4の月
            //   D''D''：API001.利払月日3の日
            //   D'''D'''：API001.利払月日4の日
            String couponPayDate1 = api001OutSub.getCouponPayDate1();
            String couponPayDate2 = api001OutSub.getCouponPayDate2();
            String couponPayDate3 = api001OutSub.getCouponPayDate3();
            String couponPayDate4 = api001OutSub.getCouponPayDate4();
            
            String mm1 = couponPayDate1.substring(0, 2);
            String dd1 = (StringUtils.equals(couponPayDate1.substring(2, 4),
                    IfaHoldingSecurityListServiceImpL.API001_COUPON_PAY_DATE_LAST_DAY)) ? "末日"
                            : couponPayDate1.substring(2, 4);
            String mm2 = couponPayDate2.substring(0, 2);
            String dd2 = (StringUtils.equals(couponPayDate2.substring(2, 4),
                    IfaHoldingSecurityListServiceImpL.API001_COUPON_PAY_DATE_LAST_DAY)) ? "末日"
                            : couponPayDate2.substring(2, 4);
            String mm3 = couponPayDate3.substring(0, 2);
            String dd3 = (StringUtils.equals(couponPayDate3.substring(2, 4),
                    IfaHoldingSecurityListServiceImpL.API001_COUPON_PAY_DATE_LAST_DAY)) ? "末日"
                            : couponPayDate3.substring(2, 4);
            String mm4 = couponPayDate4.substring(0, 2);
            String dd4 = (StringUtils.equals(couponPayDate4.substring(2, 4),
                    IfaHoldingSecurityListServiceImpL.API001_COUPON_PAY_DATE_LAST_DAY)) ? "末日"
                            : couponPayDate4.substring(2, 4);
            
            if (StringUtils.equals(dd1, dd2) == true && StringUtils.equals(dd1, dd3) == true
                    && StringUtils.equals(dd1, dd4) == true) {
                detail.setInterestPaymentDate(mm1 + "/" + mm2 + "/" + mm3 + "/" + mm4 + "-" + dd1);
            } else {
                detail.setInterestPaymentDate(
                        mm1 + "-" + dd1 + "," + mm2 + "-" + dd2 + "," + mm3 + "-" + dd3 + "," + mm4 + "-" + dd4);
            }
            
        } else if (StringUtils.equals(api001OutSub.getCouponPayId(),
                IfaHoldingSecurityListServiceImpL.API001_COUPON_PAY_ID_TWELVE) == true) {
            
            // ⑤API001.・利払区分＝4(年12回(毎月))の場合
            //   API001.利払月日1を「毎月DD日」で表示。
            //   但し、DD=99の場合は、「毎月末日」で表示。
            //   ※ただし、DD一桁の場合は「毎月D日」
            
            String couponPayDate1 = api001OutSub.getCouponPayDate1();
            String dd = (StringUtils.equals(couponPayDate1.substring(2, 4),
                    IfaHoldingSecurityListServiceImpL.API001_COUPON_PAY_DATE_LAST_DAY)) ? "末日"
                            : couponPayDate1.substring(2, 4);
            
            if (StringUtils.equals(dd.substring(0, 1), "0") == true) {
                detail.setInterestPaymentDate("毎月" + dd.substring(1, 2));
            } else {
                detail.setInterestPaymentDate("毎月" + dd);
            }
        }
        //保有額面
        //  ①API001.預り区分='027'(保護預り（特優）)の場合
        //    '特優' +' '(半角スペース)+ API001.残高数量
        //  ②API001.預り区分='028'(保護預り（マル優）)の場合
        //    'マル優' +' '(半角スペース)+ API001.残高数量
        //  ③①②以外の場合、API001.残高数量
        if (StringUtils.equals(api001OutSub.getPositionId(),
                IfaHoldingSecurityListServiceImpL.API001_POSITINO_ID_DMST_BND_ALL_PREFERENTIAL_TREATMENT) == true) {
            detail.setVolumeYen("特優 " + api001OutSub.getPosition());
        } else if (StringUtils.equals(api001OutSub.getPositionId(),
                IfaHoldingSecurityListServiceImpL.API001_POSITINO_ID_DMST_BND_PREFERENTIAL_TREATMENT) == true) {
            detail.setVolumeYen("マル優 " + api001OutSub.getPosition());
        } else {
            detail.setVolumeYen(api001OutSub.getPosition());
        }
        // 約定為替の設定
        // '-'(半角ハイフン)
        detail.setContractExchange(null);
        // 参考為替の設定
        // '-'(半角ハイフン)
        detail.setExchangeRate(null);
        
        // 商品区分
        // API001  明細部.商品区分
        detail.setSecurityType(api001OutSub.getSecId());
        // 国内外国区分
        // API001  明細部.国内外国区分
        detail.setKokunaiGaiKbn(api001OutSub.getDomesticFgnId());
        // 商品種別１
        // API001  明細部.商品種別１
        detail.setSecurityClass1(api001OutSub.getSecType1());
        // 商品種別２
        // API001  明細部.商品種別２
        detail.setSecurityClass2(api001OutSub.getSecType2());
        // 会社ｺｰﾄﾞ
        // API001  明細部.会社ｺｰﾄﾞ
        detail.setCompanyCode(api001OutSub.getCompanyCode());
        // 権利区分
        // API001  明細部.権利区分ﾞ
        detail.setRightType(api001OutSub.getStRightId());
        // 新旧区分
        // API001  明細部.新旧区分
        detail.setNewOldType(api001OutSub.getNewOldId());
        // 回数
        // API001  明細部.回数
        detail.setTimes(api001OutSub.getSerNo());
        // 号1
        // API001  明細部.号1
        detail.setIssue1(api001OutSub.getSubCode1());
        // 号2
        // API001  明細部.号2
        detail.setIssue2(api001OutSub.getSubCode2());
        // 上場国ｺｰﾄﾞ
        // API001  明細部.上場国ｺｰﾄﾞ
        detail.setListedCountryCode(api001OutSub.getListCntryCd());
        
        // 非特定預り区分
        // API001  明細部.非特定預り区分
        detail.setDepositBalanceListSpecificDepositType(api001OutSub.getHitokuteiKbn());
        
        return detail;
    }
    
    /**
     * API001レスポンスに対する外国債券判定
     *
     * @param api001ResSub API001レスポンス.明細部
     * @return 外国債券である場合はtrue、そうでない場合はfalse
     */
    private boolean isForeignBonds(AccountSumWebData api001OutSub) {
        
        boolean isForeignBonds = false;
        
        if (StringUtils.equals(api001OutSub.getSecId(),
                IfaHoldingSecurityListServiceImpL.API001_SEC_ID_FOREIGN_BONDS) == true
                && StringUtils.equals(api001OutSub.getBrandSearchCode1(),
                        IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE1_FOREIGN_BONDS) == true
                && StringUtils.equals(api001OutSub.getBrandSearchCode3(),
                        IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE3_FOREIGN_BONDS) == false) {
            isForeignBonds = true;
        }
        return isForeignBonds;
    }
    
    /**
     * 外国債券情報の取得
     *
     * @param domesticBondsMap 国内債券情報管理マップ
     * @param displayType 画面表示種別
     * @return 外国債券情報
     */
    private IfaHoldingSecurityListResponseDtoForeignBonds getForeignBonds(
            Map<String, IfaHoldingSecurityListResponseDtoForeignBonds> foreignBondsMap, DisplayType displayType) {
        
        IfaHoldingSecurityListResponseDtoForeignBonds foreignBonds = null;
        
        // 口座区分
        String depositBalanceAccountTypeName;
        // 預り区分
        String depositType;
        
        switch (displayType) {
            case OPEN_TOTAL_FRIGN_BND_SPECIFIC:
                depositBalanceAccountTypeName = "1";
                depositType = "2";
                break;
            case OPEN_TOTAL_FRIGN_BND_GENERAL:
                depositBalanceAccountTypeName = "1";
                depositType = "3";
                break;
            case OPEN_JRNISA_FRIGN_BND_SPECIFIC:
                depositBalanceAccountTypeName = "2";
                depositType = "2";
                break;
            case OPEN_JRNISA_FRIGN_BND_GENERAL:
                depositBalanceAccountTypeName = "2";
                depositType = "3";
                break;
            case CLOSE_TOTAL_FRIGN_BND_SPECIFIC:
                depositBalanceAccountTypeName = "3";
                depositType = "2";
                break;
            case CLOSE_TOTAL_FRIGN_BND_GENERAL:
                depositBalanceAccountTypeName = "3";
                depositType = "3";
                break;
            default:
                depositBalanceAccountTypeName = null;
                depositType = null;
                break;
        }
        
        if (depositBalanceAccountTypeName != null && depositType != null) {
            String key = depositBalanceAccountTypeName + "," + depositType;
            if ((foreignBonds = foreignBondsMap.get(key)) == null) {
                foreignBonds = new IfaHoldingSecurityListResponseDtoForeignBonds();
                // 口座区分
                foreignBonds.setDepositBalanceAccountTypeName(depositBalanceAccountTypeName);
                // 預り区分
                foreignBonds.setDepositType(depositType);
                // 預り銘柄数
                foreignBonds.setNumberOfDepositedIssues("0");
                // 評価額合計
                foreignBonds.setValuationTotal("0");
                
                foreignBondsMap.put(key, foreignBonds);
            }
        }
        return foreignBonds;
    }
    
    /**
     * API001レスポンスに対する外国債券（円貨） 預り明細情報の作成
     *
     * @param api001ResSub API001レスポンス.明細部
     * @return 外国債券（円貨） 預り明細情報
     */
    private IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail createForeignBondsdepositDetail(
            AccountSumWebData api001OutSub) {
        
        IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail detail = new IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail();
        
        // 銘柄コード
        // API001.商品種別1+API001.会社コード（フロント）(下4桁)
        detail.setBrandCode(api001OutSub.getSecType1()
                + api001OutSub.getCompanyCode().substring(api001OutSub.getCompanyCode().length() - 4));
        
        //銘柄名
        //API001  明細部.銘柄名
        detail.setBrandName(api001OutSub.getSecName());
        
        //保有額面
        //API001.残高数量
        detail.setVolumeForeign(api001OutSub.getPosition());
        
        // 参考為替の設定
        // API001.参考為替
        // ※API001.通貨コード=JPYの場合
        //   '-'(半角ハイフン)を設定
        if (StringUtils.equals(api001OutSub.getIssuedCcyCode(),
                IfaHoldingSecurityListServiceImpL.API001_ISSUED_CCY_CODE_JPY) == true) {
            detail.setExchangeRate(null);
        } else {
            detail.setExchangeRate(api001OutSub.getStandardRate());
        }
        
        // 通貨コード
        // 明細部.通貨コード
        detail.setCurrencyCode(api001OutSub.getIssuedCcyCode());
        // 商品区分
        // API001  明細部.商品区分
        detail.setSecurityType(api001OutSub.getSecId());
        // 国内外国区分
        // API001  明細部.国内外国区分
        detail.setKokunaiGaiKbn(api001OutSub.getDomesticFgnId());
        // 商品種別１
        // API001  明細部.商品種別１
        detail.setSecurityClass1(api001OutSub.getSecType1());
        // 商品種別２
        // API001  明細部.商品種別２
        detail.setSecurityClass2(api001OutSub.getSecType2());
        // 会社ｺｰﾄﾞ
        // API001  明細部.会社ｺｰﾄﾞ
        detail.setCompanyCode(api001OutSub.getCompanyCode());
        // 権利区分
        // API001  明細部.権利区分ﾞ
        detail.setRightType(api001OutSub.getStRightId());
        // 新旧区分
        // API001  明細部.新旧区分
        detail.setNewOldType(api001OutSub.getNewOldId());
        // 回数
        // API001  明細部.回数
        detail.setTimes(api001OutSub.getSerNo());
        // 号1
        // API001  明細部.号1
        detail.setIssue1(api001OutSub.getSubCode1());
        // 号2
        // API001  明細部.号2
        detail.setIssue2(api001OutSub.getSubCode2());
        // 上場国ｺｰﾄﾞ
        // API001  明細部.上場国ｺｰﾄﾞ
        detail.setListedCountryCode(api001OutSub.getListCntryCd());
        
        // 非特定預り区分
        // API001  明細部.非特定預り区分
        detail.setDepositBalanceListSpecificDepositType(api001OutSub.getHitokuteiKbn());
        
        return detail;
    }
    
    /**
     * API004レスポンスに対する外国債券（外貨） 預り明細情報の作成
     *
     * @param api001ResSub API004レスポンス.明細部
     * @return 外国債券（外貨） 預り明細情報
     */
    private IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail createForeignBondsdepositDetail(
            SecuritiesBalances securitiesBalances) {
        
        IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail detail = new IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail();
        if (!ObjectUtils.isEmpty(securitiesBalances.getSecurities())) {
            // 銘柄名
            // API004.商品保有証券資産情報一覧.銘柄情報.銘柄名
            detail.setBrandName(securitiesBalances.getSecurities().getSecuritiesName());

            // 商品コード
            // API004.商品保有証券資産情報一覧.銘柄情報.商品コード
            detail.setSecurityClass(securitiesBalances.getSecurities().getProductCode());

            // 銘柄コード
            // API004.商品保有証券資産情報一覧.銘柄情報.銘柄コード
            detail.setBrandCode(securitiesBalances.getSecurities().getSecuritiesCode());
        }

        if (!ObjectUtils.isEmpty(securitiesBalances.getEvaluationProfitLoss())) {
            // 外貨建評価額
            // API004.商品保有証券資産情報一覧.評価損益.評価額（外貨）
            detail.setForeignValuation17(securitiesBalances.getEvaluationProfitLoss().getFrnEvaluationAmount());

            // 参考為替
            // API004.商品保有証券資産情報一覧.評価損益.評価為替レート
            detail.setExchangeRate(securitiesBalances.getEvaluationProfitLoss().getEvaluationExchangeRate());

            // 円換算評価額
            // API004.商品保有証券資産情報一覧.評価損益.評価額（円貨）
            detail.setYenConversionValuation(securitiesBalances.getEvaluationProfitLoss().getEvaluationAmount());
        }

        // 通貨コード
        // API004.商品保有証券資産情報一覧.通貨コード
        detail.setCurrencyCode(securitiesBalances.getCurrencyCode());

        // 預り区分
        // API004.商品保有証券資産情報一覧.預り区分
        detail.setDepositType(securitiesBalances.getSpecificAccountCode());

        // 国コード
        // API004.商品保有証券資産情報一覧.国コード
        detail.setCountryCode(securitiesBalances.getCountryCode());

        // 保有額面
        // API004.商品保有証券資産情報一覧.保有株数
        detail.setVolumeForeign(securitiesBalances.getSecuritiesQuantity());

        // 買付単価
        // API004.商品保有証券資産情報一覧.取得（参考）単価（外貨）
        detail.setUnitPrice(securitiesBalances.getFrnAcquisitionPrice());

        return detail;
    }

    /**
     * 外国株式情報の取得
     *
     * @param foreignStocksMap 外国株式情報管理マップ
     * @param displayType 画面表示種別
     * @return 外国株式情報
     */
    private IfaHoldingSecurityListResponseDtoForeignStocks getForeignStocks(
            Map<String, IfaHoldingSecurityListResponseDtoForeignStocks> foreignStocksMap, DisplayType displayType) {
        
        IfaHoldingSecurityListResponseDtoForeignStocks foreignStocks = null;
        
        // 口座区分
        String depositBalanceAccountTypeName;
        // 預り区分
        String depositType;
        
        switch (displayType) {
            case OPEN_TOTAL_FRIGN_STK_SPECIFIC:
                depositBalanceAccountTypeName = "1";
                depositType = "2";
                break;
            case OPEN_TOTAL_FRIGN_STK_GENERAL:
                depositBalanceAccountTypeName = "1";
                depositType = "3";
                break;
            case OPEN_TOTAL_FRIGN_STK_NISA:
                depositBalanceAccountTypeName = "1";
                depositType = "5";
                break;
            case OPEN_TOTAL_FRIGN_STK_OLD_NISA:
                depositBalanceAccountTypeName = "1";
                depositType = "1";
                break;
            case OPEN_JRNISA_FRIGN_STK_SPECIFIC:
                depositBalanceAccountTypeName = "2";
                depositType = "2";
                break;
            case OPEN_JRNISA_FRIGN_STK_GENERAL:
                depositBalanceAccountTypeName = "2";
                depositType = "3";
                break;
            case OPEN_JRNISA_FRIGN_STK_NISA:
                depositBalanceAccountTypeName = "2";
                depositType = "7";
                break;
            case OPEN_JRNISA_FRIGN_STK_OLD_NISA:
                depositBalanceAccountTypeName = "2";
                depositType = "1";
                break;
            case CLOSE_TOTAL_FRIGN_STK_SPECIFIC:
                depositBalanceAccountTypeName = "3";
                depositType = "2";
                break;
            case CLOSE_TOTAL_FRIGN_STK_GENERAL:
                depositBalanceAccountTypeName = "3";
                depositType = "3";
                break;
            case CLOSE_TOTAL_FRIGN_STK_NISA:
                depositBalanceAccountTypeName = "3";
                depositType = "5";
                break;
            case CLOSE_TOTAL_FRIGN_STK_OLD_NISA:
                depositBalanceAccountTypeName = "3";
                depositType = "1";
                break;
            default:
                depositBalanceAccountTypeName = null;
                depositType = null;
                break;
        }
        
        if (depositBalanceAccountTypeName != null && depositType != null) {
            String key = depositBalanceAccountTypeName + "," + depositType;
            if ((foreignStocks = foreignStocksMap.get(key)) == null) {
                foreignStocks = new IfaHoldingSecurityListResponseDtoForeignStocks();
                // 口座区分
                foreignStocks.setDepositBalanceAccountTypeName(depositBalanceAccountTypeName);
                // 預り区分
                foreignStocks.setDepositType(depositType);
                // 預り銘柄数
                foreignStocks.setNumberOfDepositedIssues("0");
                // 評価額合計
                foreignStocks.setValuationTotal("0");
                // 評価損益合計
                foreignStocks.setGetProfitAll("0");
                
                foreignStocksMap.put(key, foreignStocks);
            }
        }
        return foreignStocks;
    }
    
    /**
     * API001レスポンスに対する外国株式 預り明細情報の作成
     *
     * @param securitiesBalances API004レスポンス.商品保有証券資産情報
     * @return 国内債券 預り明細情報
     */
    private IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail createForeignStocksdepositDetail(
            SecuritiesBalances securitiesBalances) {
        
        IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail detail = new IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail();
        
        if (!ObjectUtils.isEmpty(securitiesBalances.getSecurities())) {
            // 銘柄コード
            // 商品保有証券資産情報一覧.銘柄情報.銘柄コード
            detail.setBrandCode(securitiesBalances.getSecurities().getSecuritiesCode());
            // 銘柄名の設定
            // 商品保有証券資産情報一覧.銘柄情報.銘柄名
            detail.setBrandName(securitiesBalances.getSecurities().getSecuritiesName());
        }
        // 保有株数の設定
        // 商品保有証券資産情報一覧.保有株数
        detail.setHoldingQuantity(securitiesBalances.getSecuritiesQuantity());
        // 注文中
        // 商品保有証券資産情報一覧.売却注文中
        detail.setUnactualQuantity(securitiesBalances.getSellFixedOrderQuantity());
        //保護区分
        // 商品保有証券資産情報一覧.保護区分
        detail.setProtectType(securitiesBalances.getDepositType());
        
        // 取得単価
        // API004.取得（参考）単価（外貨）
        detail.setOpenPrice(securitiesBalances.getFrnAcquisitionPrice());
        
        // 通貨コード
        // 商品保有証券資産情報一覧.通貨コード
        detail.setCurrencyCode(securitiesBalances.getCurrencyCode());
        
        if (!ObjectUtils.isEmpty(securitiesBalances.getStockPrice())) {
            // 現在値
            // API004.株価情報.現在値or前日終値
            detail.setCurrentPrice(securitiesBalances.getStockPrice().getLastToPrevClose());
        }
        
        //通貨コード1
        // 商品保有証券資産情報一覧.通貨コード
        detail.setCurrencyCode1(securitiesBalances.getCurrencyCode());
        
        if (!ObjectUtils.isEmpty(securitiesBalances.getEvaluationProfitLoss())) {
            // 外貨建評価損益 
            // API004.評価損益.評価損益（外貨）
            detail.setForeignProfitAndLoss(securitiesBalances.getEvaluationProfitLoss().getFrnEvaluationProfitLoss());
        }
        
        //通貨コード2
        detail.setCurrencyCode2(securitiesBalances.getCurrencyCode());
        
        if (!ObjectUtils.isEmpty(securitiesBalances.getEvaluationProfitLoss())) {
            // 評価為替レート
            // 商品保有証券資産情報一覧.評価損益.評価為替レート
            detail.setFxValuationRate(securitiesBalances.getEvaluationProfitLoss().getEvaluationExchangeRate());
            
            // 評価額（円貨）
            // 商品保有証券資産情報一覧.評価損益.評価額（円貨）
            detail.setValuation(securitiesBalances.getEvaluationProfitLoss().getEvaluationAmount());
            
            // 評価損益（円貨）
            // 商品保有証券資産情報一覧.評価損益.評価損益（円貨）
            detail.setYenProfitLoss(securitiesBalances.getEvaluationProfitLoss().getEvaluationProfitLoss());
        }
        
        if (!ObjectUtils.isEmpty(securitiesBalances.getSecurities().getProductCode())) {
            // 商品コード
            // 商品保有証券資産情報一覧.銘柄情報.商品コード
            detail.setSecurityClass(securitiesBalances.getSecurities().getProductCode());
        }
        
        // 国コード
        // 商品保有証券資産情報一覧.国コード
        detail.setCountryCode(securitiesBalances.getCountryCode());
        
        // 通貨コード
        // 商品保有証券資産情報一覧.通貨コード
        detail.setCurrencyCode3(securitiesBalances.getCurrencyCode());
        
        // 預り区分
        // 商品保有証券資産情報一覧.預り区分
        detail.setDepositType(securitiesBalances.getSpecificAccountCode());
        
        // 銘柄上場ステータス
        // 商品保有証券資産情報一覧.銘柄上場ステータス
        detail.setBrandListedStatus(securitiesBalances.getListedSecuritiesStatus());
        
        return detail;
    }
    
    /**
     * 外貨建MMF情報の取得
     *
     * @param foreignCurrencyMmfMap 外貨建MMF情報管理マップ
     * @param displayType 画面表示種別
     * @return 外貨建MMF情報
     */
    private IfaHoldingSecurityListResponseDtoForeignCurrencyMmf getForeignCurrencyMmf(
            Map<String, IfaHoldingSecurityListResponseDtoForeignCurrencyMmf> foreignCurrencyMmfMap,
            DisplayType displayType) {
        
        IfaHoldingSecurityListResponseDtoForeignCurrencyMmf foreignCurrencyMmf = null;
        
        // 口座区分
        String depositBalanceAccountTypeName;
        // 預り区分
        String depositType;
        switch (displayType) {
            case OPEN_TOTAL_FRIGN_CRNCY_MMF_SPECIFIC:
                depositBalanceAccountTypeName = "1";
                depositType = "2";
                break;
            case OPEN_TOTAL_FRIGN_CRNCY_MMF_GENERAL:
                depositBalanceAccountTypeName = "1";
                depositType = "3";
                break;
            case OPEN_JRNISA_FRIGN_CRNCY_MMF_SPECIFIC:
                depositBalanceAccountTypeName = "2";
                depositType = "2";
                break;
            case OPEN_JRNISA_FRIGN_CRNCY_MMF_GENERAL:
                depositBalanceAccountTypeName = "2";
                depositType = "3";
                break;
            case CLOSE_TOTAL_FRIGN_CRNCY_MMF_SPECIFIC:
                depositBalanceAccountTypeName = "3";
                depositType = "2";
                break;
            case CLOSE_TOTAL_FRIGN_CRNCY_MMF_GENERAL:
                depositBalanceAccountTypeName = "3";
                depositType = "3";
                break;
            default:
                depositBalanceAccountTypeName = null;
                depositType = null;
                break;
        }
        
        if (depositBalanceAccountTypeName != null && depositType != null) {
            String key = depositBalanceAccountTypeName + "," + depositType;
            if ((foreignCurrencyMmf = foreignCurrencyMmfMap.get(key)) == null) {
                foreignCurrencyMmf = new IfaHoldingSecurityListResponseDtoForeignCurrencyMmf();
                // 口座区分
                foreignCurrencyMmf.setDepositBalanceAccountTypeName(depositBalanceAccountTypeName);
                // 預り区分
                foreignCurrencyMmf.setDepositType(depositType);
                // 預り銘柄数
                foreignCurrencyMmf.setNumberOfDepositedIssues("0");
                // 評価額合計
                foreignCurrencyMmf.setValuationTotal("0");
                // 評価損益合計
                foreignCurrencyMmf.setGetProfitAll("0");
                
                foreignCurrencyMmfMap.put(key, foreignCurrencyMmf);
            }
        }
        return foreignCurrencyMmf;
    }
    
    /**
     * API001レスポンスに対する外貨建MMF 預り明細情報の作成
     *
     * @param securitiesBalances API004レスポンス.商品保有証券資産情報
     * @return 外貨建MMF 預り明細情報
     */
    private IfaHoldingSecurityListResponseDtoForeignCurrencyMmfDepositDetail createForeignCurrencyMmfDepositDetail(
            SecuritiesBalances securitiesBalances) {
        
        IfaHoldingSecurityListResponseDtoForeignCurrencyMmfDepositDetail detail = new IfaHoldingSecurityListResponseDtoForeignCurrencyMmfDepositDetail();
        
        if (!ObjectUtils.isEmpty(securitiesBalances.getSecurities())) {
            // ファンド名
            // 商品保有証券資産情報一覧.銘柄情報.銘柄名
            detail.setFundName(securitiesBalances.getSecurities().getSecuritiesName());
        }
        
        // 保有口数
        // 商品保有証券資産情報一覧.保有株数
        detail.setUnitVolume(securitiesBalances.getSecuritiesQuantity());
        
        // 注文中
        // 商品保有証券資産情報一覧.売却注文中
        detail.setUnactualQuantity(securitiesBalances.getSellFixedOrderQuantity());
        
        if (!ObjectUtils.isEmpty(securitiesBalances.getEvaluationProfitLoss())) {
            // 評価額（外貨）
            // API004.評価損益.評価額（外貨）
            detail.setForeignValuation(securitiesBalances.getEvaluationProfitLoss().getFrnEvaluationAmount());
        }
        
        // 通貨コード
        // 商品保有証券資産情報一覧.通貨コード
        detail.setCurrencyCode(securitiesBalances.getCurrencyCode());
        
        if (!ObjectUtils.isEmpty(securitiesBalances.getEvaluationProfitLoss())) {
            // 評価為替レート
            // 商品保有証券資産情報一覧.評価損益.評価為替レート
            detail.setFxValuationRate(securitiesBalances.getEvaluationProfitLoss().getEvaluationExchangeRate());

            // 評価額（円貨）
            // 商品保有証券資産情報一覧.評価損益.評価額（円貨）
            detail.setValuation(securitiesBalances.getEvaluationProfitLoss().getEvaluationAmount());
            
            // 評価損益（円貨）
            // 商品保有証券資産情報一覧.評価損益.評価損益（円貨）
            detail.setYenProfitLoss(securitiesBalances.getEvaluationProfitLoss().getEvaluationProfitLoss());
        }
        
        if (!ObjectUtils.isEmpty(securitiesBalances.getSecurities())) {
            // 銘柄コード
            // 商品保有証券資産情報一覧.銘柄情報.銘柄コード
            detail.setBrandCode(securitiesBalances.getSecurities().getSecuritiesCode());
            
            // 商品コード
            // 商品保有証券資産情報一覧.銘柄情報.商品コード
            detail.setSecurityClass(securitiesBalances.getSecurities().getProductCode());
        }
        
        // 国コード
        // 商品保有証券資産情報一覧.国コード
        detail.setCountryCode(securitiesBalances.getCountryCode());
        
        // 預り区分
        // 商品保有証券資産情報一覧.預り区分
        detail.setDepositType(securitiesBalances.getSpecificAccountCode());
        
        return detail;
    }
    
    /**
     * ST情報の取得
     *
     * @param securityTokenMap ST情報管理マップ
     * @param displayType 画面表示種別
     * @return ST情報
     */
    private IfaHoldingSecurityListResponseDtoSecurityToken getSecurityTokenList(
            Map<String, IfaHoldingSecurityListResponseDtoSecurityToken> securityTokenMap,
            DisplayType displayType) {
        
        IfaHoldingSecurityListResponseDtoSecurityToken securityToken = null;
        
        // 口座区分
        String depositBalanceAccountTypeName;
        // 預り区分
        String depositType;
        switch (displayType) {
            case OPEN_TOTAL_SECURITY_TOKEN_SPECIFIC:
                depositBalanceAccountTypeName = "1";
                depositType = "2";
                break;
            case OPEN_TOTAL_SECURITY_TOKEN_GENERAL:
                depositBalanceAccountTypeName = "1";
                depositType = "3";
                break;
            case OPEN_JRNISA_SECURITY_TOKEN_SPECIFIC:
                depositBalanceAccountTypeName = "2";
                depositType = "2";
                break;
            case OPEN_JRNISA_SECURITY_TOKEN_GENERAL:
                depositBalanceAccountTypeName = "2";
                depositType = "3";
                break;
            case CLOSE_TOTAL_SECURITY_TOKEN_SPECIFIC:
                depositBalanceAccountTypeName = "3";
                depositType = "2";
                break;
            case CLOSE_TOTAL_SECURITY_TOKEN_GENERAL:
                depositBalanceAccountTypeName = "3";
                depositType = "3";
                break;
            default:
                depositBalanceAccountTypeName = null;
                depositType = null;
                break;
        }
        
        if (depositBalanceAccountTypeName != null && depositType != null) {
            String key = depositBalanceAccountTypeName + "," + depositType;
            if ((securityToken = securityTokenMap.get(key)) == null) {
                securityToken = new IfaHoldingSecurityListResponseDtoSecurityToken();
                // 口座区分
                securityToken.setDepositBalanceAccountTypeName(depositBalanceAccountTypeName);
                // 預り区分
                securityToken.setDepositType(depositType);
                // 預り銘柄数
                securityToken.setNumberOfDepositedIssues("0");
                // 評価額合計
                securityToken.setValuationTotal("0");
                // 評価損益合計
                securityToken.setGetProfitAll("0");
                
                securityTokenMap.put(key, securityToken);
            }
        }
        return securityToken;
    }
    
    /**
     * SQL010レスポンスに対するST 預り明細情報の作成
     *
     * @param selSql010Res SQL010レスポンス
     * @return ST 預り明細情報
     */
    private IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail createSecurityTokenListDepositDetail(
            IfaHoldingSecurityListSql010ResponseModel selSql010Res) {
        
        IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail detail = new IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail();
        
        // 銘柄コード
        detail.setBrandCode(selSql010Res.getBrandCode());
        
        // 銘柄名
        detail.setBrandName(selSql010Res.getBrandName());
        
        // 約定基準残高
        detail.setContractStandardDeposit(selSql010Res.getExecBaseBalance());
        
        // 取得単価
        detail.setOpenPrice(selSql010Res.getAcquirePrice());
        
        // 時価
        detail.setPrice(selSql010Res.getAppraiseMarketPrice());
        
        // 評価額（円貨）
        detail.setValuation(selSql010Res.getCurrenctPrice());
        
        // 評価損益
        detail.setYenProfitLoss(selSql010Res.getAppraiseProfitLossPrice());
        
        return detail;
    }
    
    /**
     * API001レスポンスに対するその他商品判定
     *
     * @param api001ResSub API001レスポンス.明細部
     * @return その他商品である場合はtrue、そうでない場合はfalse
     */
    private boolean isOtherSecurity(AccountSumWebData api001OutSub, CustomerCommon cc) {
        
        boolean isOtherSecurity = false;
        
        // API001.非特定預り区分 IN ('0'(特定預り),'5'(特定(特例)), '6'(一般(特例)) かつ 顧客共通情報.特定口座区分 IN ('1'(特定口座(代行納付)), '2'(特定口座(確定申告)))
        // または
        // API001.非特定預り区分 NOT IN ('0'(特定預り),'5'(特定(特例)), '6'(一般(特例))
        if ((IfaHoldingSecurityListServiceImpL.HITOKUTEI_KBN_OTHER_SECURITY.matcher(api001OutSub.getHitokuteiKbn())
                .matches() == true
                && IfaHoldingSecurityListServiceImpL.SPECIFIC_ACCOUNT_TYPE_OTHER_SECURITY
                        .matcher(cc.getSpecificAccountType()).matches() == true)
                || IfaHoldingSecurityListServiceImpL.HITOKUTEI_KBN_OTHER_SECURITY
                        .matcher(api001OutSub.getHitokuteiKbn()).matches() == false) {
            
            if (IfaHoldingSecurityListServiceImpL.API001_SEC_TYPE_NAME_OTHER_SECURITY
                    .matcher(api001OutSub.getSecTypeName()).matches() == true) {
                
                // API001.商品タイプ名='外国投信'|'外国株式'
                // 顧客共通情報.特定口座区分='1'(特定口座(代行納付))／'2'(特定口座(確定申告))                
                isOtherSecurity = true;
            } else if (IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE1_OTHER_SECURITY_1
                    .matcher(api001OutSub.getBrandSearchCode1()).matches() == true) {
                
                // API001.商品検索ｺｰﾄﾞ 大分類 IN ('C'(国内金融商品), 'J'(外国金融商品))
                isOtherSecurity = true;
            } else if (StringUtils.equals(api001OutSub.getBrandSearchCode1(),
                    IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE1_OTHER_SECURITY_2) == true
                    && StringUtils.equals(api001OutSub.getBrandSearchCode2(),
                            IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE2_OTHER_SECURITY) == true) {
                
                // API001.商品検索ｺｰﾄﾞ 大分類 = 'B'(国内公社債)
                // API001.商品検索ｺｰﾄﾞ 中分類 = 'B04'(国内ワラント)
                isOtherSecurity = true;
            } else if (StringUtils.equals(api001OutSub.getBrandSearchCode1(),
                    IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE1_OTHER_SECURITY_3) == true
                    && StringUtils.equals(api001OutSub.getBrandSearchCode3(),
                            IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE3_OTHER_SECURITY) == true) {
                
                // API001.商品検索ｺｰﾄﾞ 大分類 = 'I'(外国公社債)
                // API001.商品検索ｺｰﾄﾞ 小分類 = 'I0118'(外国ワラント)
                isOtherSecurity = true;
            }
        }
        
        return isOtherSecurity;
    }
    
    /**
     * その他商品情報の取得
     *
     * @param otherSecurityMap      その他商品情報管理マップ
     * @param displayType 画面表示種別
     * @return その他商品情報
     */
    private IfaHoldingSecurityListResponseDtoOtherSecurity getOtherSecurity(
            Map<String, IfaHoldingSecurityListResponseDtoOtherSecurity> otherSecurityMap, DisplayType displayType) {
        
        IfaHoldingSecurityListResponseDtoOtherSecurity otherSecurity = null;
        
        // 口座区分
        String depositBalanceAccountTypeName;
        // 預り区分
        String depositType;
        
        switch (displayType) {
            case OPEN_TOTAL_OTHR_SEC_SPECIFIC:
                depositBalanceAccountTypeName = "1";
                depositType = "2";
                break;
            case OPEN_TOTAL_OTHR_SEC_GENERAL:
                depositBalanceAccountTypeName = "1";
                depositType = "3";
                break;
            case OPEN_JRNISA_OTHR_SEC_SPECIFIC:
                depositBalanceAccountTypeName = "2";
                depositType = "2";
                break;
            case OPEN_JRNISA_OTHR_SEC_GENERAL:
                depositBalanceAccountTypeName = "2";
                depositType = "3";
                break;
            case CLOSE_TOTAL_OTHR_SEC_SPECIFIC:
                depositBalanceAccountTypeName = "1";
                depositType = "2";
                break;
            case CLOSE_TOTAL_OTHR_SEC_GENERAL:
                depositBalanceAccountTypeName = "1";
                depositType = "3";
                break;
            default:
                depositBalanceAccountTypeName = null;
                depositType = null;
                break;
        }
        
        if (depositBalanceAccountTypeName != null && depositType != null) {
            String key = depositBalanceAccountTypeName + "," + depositType;
            if ((otherSecurity = otherSecurityMap.get(key)) == null) {
                otherSecurity = new IfaHoldingSecurityListResponseDtoOtherSecurity();
                // 口座区分
                otherSecurity.setDepositBalanceAccountTypeName(depositBalanceAccountTypeName);
                // 預り区分
                otherSecurity.setDepositType(depositType);
                // 預り銘柄数
                otherSecurity.setNumberOfDepositedIssues("0");
                
                otherSecurityMap.put(key, otherSecurity);
            }
        }
        return otherSecurity;
    }
    
    /**
     * API001レスポンスに対するその他商品 預り明細情報の作成
     *
     * @param api001ResSub API001レスポンス.明細部
     * @return その他商品 預り明細情報
     */
    private IfaHoldingSecurityListResponseDtoDepositDetail createDepositDetail(AccountSumWebData api001OutSub) {
        
        IfaHoldingSecurityListResponseDtoDepositDetail detail = new IfaHoldingSecurityListResponseDtoDepositDetail();
        
        // 商品分類
        // ①API001.商品検索コード(小分類)='C0101'(国内金融商品(CP))の場合
        //     '国内CD'を設定
        // ②API001.商品検索コード(小分類)='J0101'(外国金融商品(CP))の場合
        //     '外国CD'を設定
        // ③API001.商品検索コード(小分類)='C0102'(国内金融商品(CP))の場合
        //     '国内CP'を設定
        // ④API001.商品検索コード(小分類)='J0102'(外国金融商品(CP))の場合
        //     '外国CP'を設定
        // ⑤API001.商品検索コード(小分類)='C0201'(国内金融商品(抵当証券))の場
        //     '抵当証券'を設定
        // ⑥API001.商品検索コード(大分類)='K'(外国投信)の場合
        //     '外国投信'を設定
        // ⑦API001.商品検索コード(大分類)='B'(国内公社債) かつ 
        //   API001.商品検索コード(中分類)='B04'(国内ワラント)の場合
        //     '国内ワラント'を設定
        // ⑧API001.商品検索コード(大分類)='I'(外国公社債) かつ 
        //   API001.商品検索コード(小分類) = 'I0118'(外国ワラント)かつ
        //   API001.商品区分='S'(債券(国内/外国))の場合
        //     '外国ワラント'を設定
        // 上記以外の場合、API001.商品タイプ(sec_type_name) をそのまま表示
        if (StringUtils.equals(api001OutSub.getBrandSearchCode3(),
                IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE3_DOMESTIC_C0101) == true) {
            
            detail.setSecurityClass("国内CD");
        } else if (StringUtils.equals(api001OutSub.getBrandSearchCode3(),
                IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE3_FOREIGN_J0101) == true) {
            
            detail.setSecurityClass("外国CD");
        } else if (StringUtils.equals(api001OutSub.getBrandSearchCode3(),
                IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE3_DOMESTIC_C0102) == true) {
            
            detail.setSecurityClass("国内CP");
        } else if (StringUtils.equals(api001OutSub.getBrandSearchCode3(),
                IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE3_FOREIGN_J0102) == true) {
            
            detail.setSecurityClass("外国CP");
        } else if (StringUtils.equals(api001OutSub.getBrandSearchCode3(),
                IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE3_DOMESTIC_C0201) == true) {
            
            detail.setSecurityClass("抵当証券");
        } else if (StringUtils.equals(api001OutSub.getBrandSearchCode1(),
                IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE1_FOREIGN_INVESTMENT_TRUST) == true) {
            
            detail.setSecurityClass("外国投信");
        } else if (StringUtils.equals(api001OutSub.getBrandSearchCode1(),
                IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE1_DOMESTIC_BONDS) == true
                && StringUtils.equals(api001OutSub.getBrandSearchCode2(),
                        IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE2_DOMESTIC_BONDS) == true) {
            
            detail.setSecurityClass("国内ワラント");
        } else if (StringUtils.equals(api001OutSub.getBrandSearchCode1(),
                IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE1_FOREIGN_BONDS) == true
                && StringUtils.equals(api001OutSub.getBrandSearchCode3(),
                        IfaHoldingSecurityListServiceImpL.API001_BRAND_SEARCH_CODE3_FOREIGN_BONDS) == true
                && StringUtils.equals(api001OutSub.getSecId(),
                        IfaHoldingSecurityListServiceImpL.API001_SEC_ID_BONDS) == true) {
            
            detail.setSecurityClass("外国ワラント");
        } else {
            detail.setSecurityClass(api001OutSub.getSecTypeName());
        }
        
        // 銘柄名/ファンド名
        // API001   明細部.銘柄名
        detail.setBrandNameFundName(api001OutSub.getSecName());
        
        // 数量
        // API001   明細部.残高数量
        detail.setQuantity(api001OutSub.getPosition());
        
        return detail;
    }
    
    /**
     * 媒介可否判定
     *
     * @param fct003Dto FCT003レスポンス
     * @param securityMoneyClass 証券金銭種別
     * @param tradeClass 取引種別
     * @param nationalityCode 国籍コード
     * @param currencyCode 通貨コード
     * @return 媒介可であればtrue、そうでなければfalse
     */
    public boolean isMediatePropriety(OutputFct003Dto fct003Dto, String securityMoneyClass, String tradeClass,
            String nationalityCode, String currencyCode) {
        //②利用者の口座における取引コース媒介可否リストを取得する。(FCT003)
        
        boolean isMediatePropriety = false;
        
        for (MediatePropriety mediatePropriety : fct003Dto.getMediateProprietyList()) {
            
            //証券金銭種別 一致判定
            if (securityMoneyClass != null
                    && StringUtils.equals(securityMoneyClass, mediatePropriety.getSecurityMoneyClass()) == false) {
                continue;
            }
            //取引種別 一致判定
            if (tradeClass != null && StringUtils.equals(tradeClass, mediatePropriety.getTradeClass()) == false) {
                continue;
            }
            //国籍コード 一致判定
            if (nationalityCode != null
                    && StringUtils.equals(nationalityCode, mediatePropriety.getNationalityCode()) == false) {
                continue;
            }
            //通貨コード 一致判定
            if (currencyCode != null && StringUtils.equals(currencyCode, mediatePropriety.getCurrencyCode()) == false) {
                continue;
            }
            
            //媒介可否判定
            isMediatePropriety = StringUtils.equals(mediatePropriety.getMediatePropriety(),
                    IfaHoldingSecurityListServiceImpL.FCT003_MEDIATE_PROPRIETY_AVAILABLE);
        }
        
        return isMediatePropriety;
    }

    /**
     * 口座区分選択による選択明細の判定処理
     * 
     * @param accountGetKbn 口座区分（初期表示の場合はnull）
     * @param securitiesBalances API004.商品保有証券資産情報一覧
     * @return 判定結果（true:スキップ対象となる明細, false:選択対象となる明細）
     */
    private boolean accountGetKbnJudg(SecuritiesBalances securitiesBalances, String accountGetKbn) {
        // API004で取得した明細から、口座区分ラジオ別に以下のように明細を選択する。
        if (StringUtils.equals(" ", accountGetKbn)) {
            // 総合口座：
            // API004.商品保有証券資産情報一覧.預り区分＝'2'(特定)、'1'(一般)、'4'(NISA)、'H'(成長投資枠)の明細のみを選択する。
            if (!(StringUtils.equals("2", securitiesBalances.getSpecificAccountCode())
                    || StringUtils.equals("1", securitiesBalances.getSpecificAccountCode())
                    || StringUtils.equals("4", securitiesBalances.getSpecificAccountCode())
                    || StringUtils.equals("H", securitiesBalances.getSpecificAccountCode()))) {
                // スキップ対象として返却
                return true;
            }
        } else if (StringUtils.equals("1", accountGetKbn)) {
            // ジュニアNISA口座：
            // API004.商品保有証券資産情報一覧.預り区分＝'6'(ジュニアNISA特定)、'5'(ジュニアNISA一般)、'7'(ジュニアNISA NISA)、'J'(継続管理勘定)の明細のみを選択する。
            if (!(StringUtils.equals("6", securitiesBalances.getSpecificAccountCode())
                    || StringUtils.equals("5", securitiesBalances.getSpecificAccountCode())
                    || StringUtils.equals("7", securitiesBalances.getSpecificAccountCode())
                    || StringUtils.equals("J", securitiesBalances.getSpecificAccountCode()))) {
                // スキップ対象として返却
                return true;
            }
        }
        // accountGetKbnがnull、または"2"（全て）の場合は選択対象として返却
        return false;
    }
}
