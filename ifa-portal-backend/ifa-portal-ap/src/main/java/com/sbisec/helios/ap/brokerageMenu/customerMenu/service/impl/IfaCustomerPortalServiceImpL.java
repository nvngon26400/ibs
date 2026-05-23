package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Objects;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.enums.MarginAccountType;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.account.CashDeposit;
import com.sbisec.helios.ap.athena.model.AthenaErrorMessageModel;
import com.sbisec.helios.ap.athena.protocol.account.CheckMarginAccountOpenResp;
import com.sbisec.helios.ap.athena.protocol.account.GetAccountProfileResp;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPowerDetailResp;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct031;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct031Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct031Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaCustomerPortalDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003InsertRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003SelectExclusiveResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003SelectExclusiveResquestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003SelectRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003SelectResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003UpdateRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSqlStbRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSqlStbResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerPortalA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerPortalA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerPortalA001ResponseDto_CustomerInfoAcquireErrorList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerPortalA011RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerPortalA011ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaCustomerPortalService;
import com.sbisec.helios.ap.common.enums.DividendReceiptType;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignBuyPowerFlag;
import com.sbisec.helios.ap.common.enums.ForeignMarginAccountType;
import com.sbisec.helios.ap.common.enums.ForeignSecurityTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.NoticeInfoLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.enums.SpecificAccount;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityOutData;

/**
 * 画面ID：SUB0202_00-02
 * 画面名：顧客ポータル_顧客情報
 * @author 松田
 *
 * 2023/11/30 新規作成
 */
@Component(value = "cmpIfaCustomerPortalService")
public class IfaCustomerPortalServiceImpL implements IfaCustomerPortalService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCustomerPortalServiceImpL.class);
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** メモ(IFA)の更新権限がありません。 */
    private static final String ERRORS_CSM_IFA_EXCLUSIVE_INSUFFICIENT_PRIVILEGE = "errors.csm.ifaExclusive.insufficientPrivilege";
    
    /** 入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}] */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** {0}が失敗しました。 */
    private static final String ERRORS_PROCESSING_FAILED = "errors.processingFailed";
    
    /** 参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    // --------------------------------
    // 変数定義
    // --------------------------------
    
    /** FCT031エラー時のメッセージ引数 */
    private static final String FCT031_ERR_MSG_ATTR = "顧客情報の取得";
    
    /** チェック対象権限コード */
    private static final String CHECK_AUTH_CODE = "9";
    
    /** API001取得時のエラーメッセージ */
    private static final String API001_ERROR_MSG_ATTR = "ISA買付可能枠の取得";
    
    /** API003取得時のエラーメッセージ */
    private static final String API003_ERROR_MSG_ATTR = "外貨建取引口座開設状況の取得";
    
    /** API007取得時のエラーメッセージ */
    private static final String API007_ERROR_MSG_ATTR = "買付余力情報（外貨）の取得";
    
    /** API005取得時のエラーメッセージ */
    private static final String API005_ERROR_MSG_ATTR = "米株信用口座開設状況の取得";
    
    /** 更新時のエラーメッセージ */
    private static final String UPDATE_ERROR_MSG_ATTR = "メモ(IFA専用)の更新";
    
    /** API002取得時のエラーメッセージ */
    private static final String API002_FAILED_MSG_ATTR = "買付余力情報（円貨）、信用余力情報（円貨）の取得";
    
    /** API002_余力項目セット区分 */
    private static final String API002_REQUEST_KBN1 = "1";
    
    /** API002_追証項目セット区分 */
    private static final String API002_REQUEST_KBN2 = "N";
    
    /** API003プロファイル名：外貨建口座取引開設状況 */
    private static final String ACCOUNT_PROFILE_TRADE_FSTOCK_US_PERMISSION = "TRADE_FSTOCK_US_PERMISSION";
    
    /** API003プロファイル名：外国債券取引可否 */
    private static final String ACCOUNT_PROFILE_TRADE_FXBOND_PERMISSION = "TRADE_FXBOND_PERMISSION";
    
    /** API003エラーコード：該当データなしエラー */
    private static final String API003_ERROR_NOT_FOUND = "ECOMN0002";
    
    /** API003プロファイル名：外貨建口座取引開設状況時のコードリストID */
    private static final String FSTOCK_DOMAIN_FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STATUS = "FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STATUS";
    
    /** API003プロファイル名：外貨建口座取引開設状況時のAPIタイプ */
    private static final String FXBOND_DOMAIN_FOREIGN_SECURITY_TRADE_ACCOUNT_OPEN_STATUS = "FOREIGN_SECURITY_TRADE_ACCOUNT_OPEN_STATUS";
    
    /** API003外部コード */
    private static final String API003_API_TYPE = "Athena";
    
    /** API003処理結果：該当データ無し時のプロファイル設定値 */
    private static final String API003_ERROR_NOT_FOUND_PROFILE_VALUE = "No";
    
    /** API007リクエスト：口座分類（総合） */
    private static final String API007_ACCOUNT_KIND_GENERAL = "GENERAL";
    
    /** API007リクエスト：口座分類（ジュニアNISA） */
    private static final String API007_ACCOUNT_KIND_JR_NISA = "JR_NISA";
    
    /** API007リクエスト：日数 */
    private static final int API007_DAYS = 6;
    
    /** API007リクエスト：通貨コードリスト */
    public static final List<String> API007_CURRENCY_CODES = Arrays.asList("USD", "HKD", "EUR", "AUD", "NZD", "CAD",
            "ZAR", "MXN", "TRY", "SGD", "KRW", "RUB", "VND", "IDR", "THB", "MYR", "CNY");
    
    /** API006リクエスト：国コード */
    private static final String API006_US = "US";
    
    /** 比較基準値　: 0 */
    private static final int LIMIT_ZERO = 0;
    
    /** データサイズ　: 1 */
    private static final int SIZE_ONE = 1;
    
    /** 電話番号区分 : "-" */
    private static final String PHONE_NUMBER_HYPHEN = "-";
    
    /** 郵便番号区分 : "-" */
    private static final String POST_NUMBER_HYPHEN = "-";
    
    /** A001: ユーザ共通情報.権限コード="1" */
    private static final String UA_PRIVID_ONE = "1";

    /** FCT031.特定口座配当受入開始日/特定口座配当受入終了日がnullである場合の値 */
    private static final String DEFAULT_SPECIFIC_ACCOUNT_DIVIDEND_DATE = "00000000";

    /** コードテーブル.種別.項目説明文言 */
    private static final String M_CODE_CODE_TYPE_DESCRIPTION_MESSAGE = "58";

    /** コードテーブル.CODE_1.顧客ポータル_顧客情報 */
    private static final String M_CODE_CODE_1_CUSTOMER_PORTAL = "SUB0202_00-02";

    /** コードテーブル.CODE_2.維持率(円貨) */
    private static final String M_CODE_CODE_2_MAINTENANCE_RATE_JPY = "maintenanceRateJPY";
    
    /**
     * FCT001
     */
    @Autowired
    private Fct001 fct001;
    
    /**
     * FCT030
     */
    @Autowired
    private Fct030 fct030;
    
    /**
     * FCT031
     */
    @Autowired
    private Fct031 fct031;

    /**
     * コードマスタ
     */
    @Autowired
    MCodeService mcodeService;

    
    /**
     * NRI API
     */
    @Autowired
    private ApiWrapper apiWrapper;
    
    /**
     * Athena API
     */
    @Autowired
    private ForeignAccountService foreignAccountService;
    
    /**
     * 区分値取得クラス
     */
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private IfaCustomerPortalDao dao;

    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaCustomerPortalA001DtoRequest
     * Dto レスポンス：IfaCustomerPortalA001DtoResponse
     * model リクエスト：IfaCustomerPortalSql005RequestModel
     * model レスポンス：IfaCustomerPortalSql005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerPortalA001ResponseDto> initializeA001Stb(IfaCustomerPortalA001RequestDto dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaCustomerPortalServiceImplL.initializeA001Stb");
        
        List<IfaCustomerPortalA001ResponseDto> resDto = new ArrayList<IfaCustomerPortalA001ResponseDto>();
        
        // スタブ：レスポンス情報取得SQL発行
        IfaCustomerPortalSqlStbRequestModel sqlStbReq = new IfaCustomerPortalSqlStbRequestModel();
        sqlStbReq.setButenCode(dtoReq.getButenCode());
        sqlStbReq.setAccountNumber(dtoReq.getAccountNumber());
        DataList<IfaCustomerPortalSqlStbResponseModel> result = dao.selectIfaCustomerPortalSqlStb(sqlStbReq);
        
        IfaCustomerPortalA001ResponseDto response = new IfaCustomerPortalA001ResponseDto();
        List<IfaCustomerPortalA001ResponseDto_CustomerInfoAcquireErrorList> errList = new ArrayList<IfaCustomerPortalA001ResponseDto_CustomerInfoAcquireErrorList>();
        if (result == null || CollectionUtils.isEmpty(result.getDataList())) {
            LOGGER.debug("no data.");
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), "顧客情報がありません");
        } else {
            BeanUtils.copyProperties(response, result.getDataList().get(0));
            result.getDataList().stream().forEach(d -> {
                IfaCustomerPortalA001ResponseDto_CustomerInfoAcquireErrorList err = new IfaCustomerPortalA001ResponseDto_CustomerInfoAcquireErrorList();
                try {
                    BeanUtils.copyProperties(err, d);
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.debug("copy error.");
                }
                errList.add(err);
            });
            response.setCustomerInfoAcquireErrorList(errList);
        }
        
        resDto.add(response);
        
        return IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaCustomerPortalA001DtoRequest
     * Dto レスポンス：IfaCustomerPortalA001DtoResponse
     * model リクエスト：IfaCustomerPortalSql005RequestModel
     * model レスポンス：IfaCustomerPortalSql005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerPortalA001ResponseDto> initializeA001(IfaCustomerPortalA001RequestDto dtoReq)
            throws Exception {
        
        IfaCustomerPortalA001ResponseDto response = new IfaCustomerPortalA001ResponseDto();
        List<IfaCustomerPortalA001ResponseDto_CustomerInfoAcquireErrorList> errorResponseList = new ArrayList<IfaCustomerPortalA001ResponseDto_CustomerInfoAcquireErrorList>();
        errorResponseList.add(new IfaCustomerPortalA001ResponseDto_CustomerInfoAcquireErrorList());
        response.setCustomerInfoAcquireErrorList(errorResponseList);
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaCustomerPortalServiceImplL.initializeA001");
        
        // 初期化
        UserAccount ua = IfaCommonUtil.getUserAccount();
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = dtoReq.getButenCode();
        String accountNumber = dtoReq.getAccountNumber();
        String errorMessage = StringUtil.EMPTY_STRING;
        
        // FCT001
        if (!checkFct001(butenCode, accountNumber)) {
            errorMessage = IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST,
                    new String[] { butenCode, accountNumber });
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_BUTENACCOUNTNOTEXIST, errorMessage);
        }
        
        // FCT031の各項目算出
        boolean fct031Result = getCustomerInfo(response, butenCode, accountNumber);
        if (!fct031Result) {
            errorMessage = IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED, new String[] { FCT031_ERR_MSG_ATTR });
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_PROCESSING_FAILED, errorMessage);
        }
        
        // メモ(IFA専用)を取得(SQL002)
        DataList<IfaCustomerPortalSql002ResponseModel> sql002Res = getMemoIfaInfo(response.getCustomerCode());
        if (sql002Res != null && CollectionUtils.isNotEmpty(sql002Res.getDataList())) {
            response.setIfaMemoContent(sql002Res.getDataList().get(0).getIfaMemoContent());
            response.setIfaMemoUpdateDateTime(sql002Res.getDataList().get(0).getIfaMemoUpdateDateTime());
        }
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // API001取得処理
        getQueryAccountBalanceInfo(response, butenCode, accountNumber, apiErrorUtil);
        
        // API002取得処理
        getQueryMgEstimateCapabilityInfo(response, butenCode, accountNumber, apiErrorUtil);
        
        // API003取得処理
        getAccountOpenStatusinfo(response, butenCode, accountNumber);
        
        // API007取得処理
        // ⑩外貨建取引口座開設状況または外国株式取引口座開設状況が開設済の場合、買付余力情報（外貨）を取得する
        if (
            StringUtils.equals(response.getForeignStockTradeAccountOpenStatus(), ForeignSecurityTradeAccountOpenStatus.OPEN.getId())
            || StringUtils.equals(response.getForeignSecurityTradeAccountOpenStatus(), ForeignStockTradeAccountOpenStatus.OPEN.getId())
        ) {
            getMultiGetCashDepositsReqInfo(response, butenCode, accountNumber);
        }
        
        // API005,API006取得処理
        getForeignAccountInfo(response, butenCode, accountNumber);
        
        // メモ(CCS)を取得(SQL004)
        String ccsMemo = getMemoCcsInfo(response.getCustomerCode());
        response.setCcsMemo(StringUtil.isNullOrEmpty(ccsMemo) ? StringUtil.EMPTY_STRING : ccsMemo);
        
        // コース名を取得(SQL005)
        String courseName = getCourseName(response.getDealerNumber());
        response.setCustomerInfoCourse(StringUtil.isNullOrEmpty(courseName) ? StringUtil.EMPTY_STRING : courseName);
        
        // ユーザ共通情報.権限コードが1以外の場合, 仲介業者コード営業員リストを取得
        if (!StringUtils.equals(ua.getPrivId(), UA_PRIVID_ONE)) {
            boolean fct030Result = getBrokerChargeList(response);
            if (!fct030Result) {
                errorMessage = IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST);
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST, errorMessage);
            }
        }

        // 維持率(円貨)説明文言を取得する(SQL006の代わりにMCodeServiceを使用)
        List<MCode> selSql006Res = mcodeService.getMCodeList(
                M_CODE_CODE_TYPE_DESCRIPTION_MESSAGE,
                M_CODE_CODE_1_CUSTOMER_PORTAL,
                M_CODE_CODE_2_MAINTENANCE_RATE_JPY
        );

        // 仲介業者顧客口座属性情報取得(SQL007)
        DataList<IfaCustomerPortalSql007ResponseModel> sql007Res = getBrokerCustomerAccount(response.getCustomerCode());
        if(sql007Res != null && CollectionUtils.isNotEmpty(sql007Res.getDataList()) && sql007Res.getDataList().get(0) !=null) {
            IfaCustomerPortalSql007ResponseModel res = sql007Res.getDataList().get(0);
            // 顧客名_姓名(カナ)
            response.setNameKana(res.getNameKana());
            // 郵便番号
            if(StringUtils.isNotEmpty(res.getZipCode()) && res.getZipCode().length() == 7) {
                response.setZipCode("〒" + res.getZipCode().substring(0, 3) + POST_NUMBER_HYPHEN + res.getZipCode().substring(3, 7));
            } else {
                response.setZipCode(res.getZipCode());
            }
            // 住所(漢字)
            response.setAddressKanji1(res.getAddressKanji1());
            // 住所(カナ)
            response.setAddressKana1(res.getAddressKana1());
            // 性別
            response.setSexKbn(res.getSexKbn());
        }
        

        // 顧客口座属性情報取得(SQL008)
        DataList<IfaCustomerPortalSql008ResponseModel> sql008Res = getCustomerAccount(response.getCustomerCode());
        if(sql008Res != null && CollectionUtils.isNotEmpty(sql008Res.getDataList()) && sql008Res.getDataList().get(0) != null) {
            IfaCustomerPortalSql008ResponseModel res = sql008Res.getDataList().get(0);
            // 職業
            response.setUaiOccupation(res.getUaiOccupation());
            // 勤務先
            response.setUaiOfficeName(res.getUaiOfficeName());
            // 勤務先TEL
            //勤務先電話番号(市外局番)、勤務先電話番号(市内局番)、勤務先電話番号(番号)のいずれか一つでも値なしの場合、""（ブランク）を設定
            if (StringUtils.isEmpty(res.getUaiOfficePhoneLongDist())
                    || StringUtils.isEmpty(res.getUaiOfficePhoneCityCode())
                    || StringUtils.isEmpty(res.getUaiOfficePhoneNumber())) {
                response.setUaiOfficePhoneNumber("");
            } else {
                // 上記以外の場合、勤務先電話番号(市外局番) ＋ "-" ＋ 勤務先電話番号(市内局番) ＋ "-" ＋ 勤務先電話番号(番号)
                response.setUaiOfficePhoneNumber(StringUtil.trim(res.getUaiOfficePhoneLongDist()) + PHONE_NUMBER_HYPHEN
                        + StringUtil.trim(res.getUaiOfficePhoneCityCode()) + PHONE_NUMBER_HYPHEN
                        + StringUtil.trim(res.getUaiOfficePhoneNumber()));
            }
            // 代表者肩書区分
            response.setTitleOfDaihyo(res.getUaiOfficeDaihyoKbn());
            // 決算期1
            response.setUaiCorpSettlementTerm1(convertToMMDD(res.getUaiCorpSettlementTerm1()));
            // 決算期2
            response.setUaiCorpSettlementTerm2(convertToMMDD(res.getUaiCorpSettlementTerm2()));
            // 口座開設日
            response.setUaiOpenAcctDate(res.getUaiOpenAcctDate());
            // 払出制限解除日
            response.setUaiJnisaSeigenShuryoYmd(res.getUaiJnisaSeigenShuryoYmd());
        }
        

        
       
        // 営業員属性設定マスタ情報取得(SQL009)
        DataList<IfaCustomerPortalSql009ResponseModel> sql009Res = getEmp(response.getButenCode(), response.getAccountNumber());
        if(sql009Res != null && CollectionUtils.isNotEmpty(sql009Res.getDataList()) && sql009Res.getDataList().get(0) != null) {
            IfaCustomerPortalSql009ResponseModel res = sql009Res.getDataList().get(0);
            // 閲覧可能部店コード
            response.setVisibleButenCode(res.getVisibleButenCode());
            // ID/PW交付状況
            response.setIdPwFlg(res.getIdPwFlg());
            // 社員営業員（ＣＲ）コード
            response.setEmpCrCd(res.getEmpCrCd());
            // 社員営業員（ＣS）コード
            response.setEmpCsCd(res.getEmpCsCd());
            // CR営業員名
            response.setEmpCrName(res.getEmpCrName());
            // CS営業員名
            response.setEmpCsName(res.getEmpCsName());
        }
        

        // EC顧客マスタビュー情報取得(SQL010)
        DataList<IfaCustomerPortalSql010ResponseModel> sql010Res = getEcCustomer(response.getCustomerCode());
        if(sql010Res != null && CollectionUtils.isNotEmpty(sql010Res.getDataList()) && sql010Res.getDataList().get(0) != null) {
            IfaCustomerPortalSql010ResponseModel res = sql010Res.getDataList().get(0);
            // ユーザーネーム
            response.setUserName(res.getUserName());
            // 手数料プラン
            // システム日付
            String systemDate = ifaDateUtil.format(IfaDateUtil.YYYYMMDD);
            // 判定区分
            String judgmentDistnction = null;
            if(StringUtils.isEmpty(res.getNewCommissionDate())) {
                judgmentDistnction = res.getCommissionId();
            } else {
                if(systemDate.compareTo(res.getNewCommissionDate()) >= 0) {
                    judgmentDistnction = res.getNewCommissionId();
                } else {
                    judgmentDistnction = res.getCommissionId();
                }
            }
            if(StringUtils.isEmpty(judgmentDistnction) || StringUtils.equals(judgmentDistnction, StringUtils.SPACE)) {
                response.setCommission("Standard");
            } else if(StringUtils.equals(judgmentDistnction, "1")) {
                response.setCommission("Active");
            }
            response.setNewCommissionDate(res.getNewCommissionDate());
        } else {
            response.setCommission("Standard");
        }
        
        // SQL011.店群情報取得
        String tenGunId = dao.selectIfaCustomerPortalSql011(butenCode);
        String ccsOpId = ua.getUserId();
        if (StringUtils.equals(ua.getPrivId(), PrivId.HEAD_OFFICE.getId()) || StringUtils.equals(ua.getPrivId(), PrivId.BRANCH.getId())) {
            ccsOpId = ccsOpId.length() >= 7 ? ccsOpId.substring(ccsOpId.length()-7) + "@" : ccsOpId + "@";
        } else {
            ccsOpId = ccsOpId.length() >= 8 ? ccsOpId = ccsOpId.substring(ccsOpId.length()-8) : ccsOpId;
        }
        response.setTenGunId(tenGunId);
        response.setCcsOpId(ccsOpId);

        
        if (selSql006Res != null && 0 < selSql006Res.size()) {
            response.setMaintenanceRateJpyAmountDescriptionMessage(selSql006Res.get(0).getName());
        }

        
        List<IfaCustomerPortalA001ResponseDto> resDto = new ArrayList<IfaCustomerPortalA001ResponseDto>();
        resDto.add(response);
        
        if (apiErrorUtil.isWarn()) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.WARNING, apiErrorUtil.getWarningMessageId(),
                    apiErrorUtil.getWarningMessages());
        }
        return IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    

    /**
     * API001処理
     * 
     * @param response
     * @param apiErrorUtil　エラーハンドリング
     * @throws Exception
     */
    private void getQueryAccountBalanceInfo(IfaCustomerPortalA001ResponseDto response, String butenCode,
            String accountNumber, ApiErrorUtil apiErrorUtil) throws Exception {
        
        QueryAccountBalanceIn input = new QueryAccountBalanceIn();
        QueryAccountBalanceInData inData = new QueryAccountBalanceInData();
        QueryAccountBalanceOutData result = new QueryAccountBalanceOutData();
        
        inData.setButenCd(butenCode);
        inData.setKozaNo(createApiRequestAccountNo(accountNumber));
        
        input.setIndata(inData);
        
        try {
            result = apiWrapper.queryAccountBalance(input);
        } catch (Exception e) {
            e.printStackTrace();
            // エラーの場合はエラーリストに格納
            response.getCustomerInfoAcquireErrorList().get(0)
                    .setIsaBuyLimitAcquireFailureErrorLevel(ErrorLevel.WARNING.toString());
            response.getCustomerInfoAcquireErrorList().get(0).setIsaBuyLimitAcquireFailureMsg(
                    IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED, new String[] { API001_ERROR_MSG_ATTR }));
            
            return;
        }
        
        if (apiErrorUtil.isError(result.getShubetu(), result.getCode(), result.getMessage())) {
            // 業務エラーの場合はAPIのエラーメッセージを格納
            response.getCustomerInfoAcquireErrorList().get(0)
                    .setIsaBuyLimitAcquireFailureErrorLevel(ErrorLevel.WARNING.toString());
            response.getCustomerInfoAcquireErrorList().get(0).setIsaBuyLimitAcquireFailureMsg(result.getMessage());
            
            return;
        }
        
        // ISA買付可能枠(当年)
        response.setIsaBuyLimitYear(result.getIsaBuyLimit());
        // ISA買付可能枠(翌年)
        response.setIsaBuyLimitYearNext(result.getIsaBuyLimitNext());
        // ISA買付可能当年
        response.setIsaBuyAbleYear(result.getIsaBuyYear());
        // ISA買付可能翌年
        response.setIsaBuyAbleYearNext(result.getIsaBuyYearNext());
        // NISA区分（当年）
        response.setNisaType(result.getIsaThisYearKbn());
        // NISA区分（翌年）
        response.setNisaTypeYearNext(result.getIsaNextYearKbn());
        // ISA買付可能枠(当年)(JrNISA)
        response.setIsaBuyLimitYearJuniorNisa(result.getIsaBuyLimitJrnisa());
        // ISA買付可能枠(翌年)(JrNISA)
        response.setIsaBuyLimitYearNextJuniorNisa(result.getIsaBuyLimitNextJrnisa());
        // ISA買付可能当年(JrNISA)
        response.setIsaBuyAbleYearJuniorNisa(result.getIsaBuyYearJrnisa());
        // ISA買付可能翌年(JrNISA)
        response.setIsaBuyAbleYearNextJuniorNisa(result.getIsaBuyYearNextJrnisa());
        // 払出制限解除フラグ
        response.setWithdrawalRestrictionCancelFlag(result.getJnisaSeigenFlg());
        // 成長投資枠(当年)
        response.setGrowthInvestmentLimitYear(result.getIsaSeityoBuyLimit());
        // つみたて投資枠(当年)
        response.setAccumulateInvestmentLimitYear(result.getIsaTsumitateBuyLimit());
    }
    
    /**
     * API002処理
     * 
     * @param response
     * @param apiErrorUtil　エラーハンドリング
     * @throws Exception
     */
    private void getQueryMgEstimateCapabilityInfo(IfaCustomerPortalA001ResponseDto response, String butenCode,
            String accountNumber, ApiErrorUtil apiErrorUtil) throws Exception {
        
        QueryMgEstimateCapabilityIn input = new QueryMgEstimateCapabilityIn();
        QueryMgEstimateCapabilityInData inData = new QueryMgEstimateCapabilityInData();
        QueryMgEstimateCapabilityOutData result = new QueryMgEstimateCapabilityOutData();
        
        inData.setButenCd(butenCode);
        inData.setKozaNo(createApiRequestAccountNo(accountNumber));
        inData.setRequestKbn1(API002_REQUEST_KBN1);
        inData.setRequestKbn2(API002_REQUEST_KBN2);
        
        input.setIndata(inData);
        
        String errorMessageApi002 = StringUtil.EMPTY_STRING;
        boolean isWarningFlag = false;
        try {
            result = apiWrapper.queryMgEstimateCapability(input);
            // 買付余力(円貨)
            response.setYenBuyingPower(result.getSettlementDateT().get(0).getBuyingPowerByDay());
            // 信用口座区分(国内)
            response.setDomesticMarginAccountType(result.getCreditAccountKbn());
            // 新規建余力(円貨)
            response.setNewBuildingCapacity(result.getSettlementDateT().get(0).getMarginCapacity());
            // 維持率(円貨)
            String adjustedRateString = null;
            if (result.getSettlementDateT().get(0).getActualGrntRate() != null && !result.getSettlementDateT().get(0).getActualGrntRate().isEmpty()) {
                // getActualGrntRate()の値を取得
                BigDecimal actualGrntRate = new BigDecimal(result.getSettlementDateT().get(0).getActualGrntRate());
                
                // 1/100をする
                BigDecimal adjustedRate = actualGrntRate.divide(new BigDecimal("100"));
                
                // 調整されたBigDecimal値をStringに変換
                adjustedRateString = adjustedRate.toPlainString();
            }
            response.setYenCustomerInfoDetentionRate(adjustedRateString);
        } catch (Exception e) {
            e.printStackTrace();
            isWarningFlag = true;
        }
        
        if (result == null || isWarningFlag) {
            errorMessageApi002 = IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED,
                    new String[] { API002_FAILED_MSG_ATTR });
        } else if (apiErrorUtil.isError(result.getShubetu(), result.getCode(), result.getMessage())) {
            errorMessageApi002 = result.getMessage();
            isWarningFlag = true;
        }
        
        if (isWarningFlag) {
            response.getCustomerInfoAcquireErrorList().get(0)
                    .setMarginPowerInfoYenAcquireFailureMsg(errorMessageApi002);
            response.getCustomerInfoAcquireErrorList().get(0)
                    .setMarginPowerInfoYenAcquireFailureErrorLevel(ErrorLevel.WARNING.toString());
        }
        
    }
    
    /**
     * API003取得処理
     * 
     * @param response
     * @throws Exception 
     */
    private void getAccountOpenStatusinfo(IfaCustomerPortalA001ResponseDto response, String butenCode,
            String accountNumber) throws Exception {
        
        GetAccountProfileResp api003ResFstockUsPermission = new GetAccountProfileResp();
        GetAccountProfileResp api003ResFxbondPermission = new GetAccountProfileResp();
        // 外国株式取引口座開設状況
        String fxStockErrMsg = null;
        try {
            // API003取得処理(外貨建口座取引開設状況)
            api003ResFstockUsPermission = foreignAccountService.getAccountProfile(butenCode, accountNumber,
                    ACCOUNT_PROFILE_TRADE_FSTOCK_US_PERMISSION);
            response.setForeignStockTradeAccountOpenStatus(api003ResFstockUsPermission.getProfileValue());
        } catch (Exception e) {
            if (e instanceof AthenaBusinessException) {
                if (StringUtils.equals(((AthenaBusinessException) e).getErrorCode(), API003_ERROR_NOT_FOUND)) {
                    // ECOMN0002の場合はAPI003.アカウントプロファイル設定値を"No"とみなす
                    response.setForeignStockTradeAccountOpenStatus(
                            codeListService.convertExtKeyToKey(FSTOCK_DOMAIN_FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STATUS,
                                    API003_API_TYPE, API003_ERROR_NOT_FOUND_PROFILE_VALUE));
                } else {
                    AthenaErrorMessageModel athenaErrorMessageModel = cometCommonService
                            .getAthenaErrorCodeAndMessage(((AthenaBusinessException) e).getErrorCode());
                    fxStockErrMsg = athenaErrorMessageModel.getErrorMessage();
                }
            } else {
                fxStockErrMsg = IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED,
                        new String[] { API003_ERROR_MSG_ATTR });
            }
            
        }
        // 外貨建商品取引口座開設状況
        String fxBondErrMsg = null;
        try {
            // API003取得処理(外国債券取引可否)
            api003ResFxbondPermission = foreignAccountService.getAccountProfile(butenCode, accountNumber,
                    ACCOUNT_PROFILE_TRADE_FXBOND_PERMISSION);
            response.setForeignSecurityTradeAccountOpenStatus(api003ResFxbondPermission.getProfileValue());
        } catch (Exception e) {
            if (e instanceof AthenaBusinessException) {
                if (StringUtils.equals(((AthenaBusinessException) e).getErrorCode(), API003_ERROR_NOT_FOUND)) {
                    // ECOMN0002の場合はAPI003.アカウントプロファイル設定値を"No"とみなす
                    response.setForeignSecurityTradeAccountOpenStatus(
                            codeListService.convertExtKeyToKey(FXBOND_DOMAIN_FOREIGN_SECURITY_TRADE_ACCOUNT_OPEN_STATUS,
                                    API003_API_TYPE, API003_ERROR_NOT_FOUND_PROFILE_VALUE));
                } else {
                    AthenaErrorMessageModel athenaErrorMessageModel = cometCommonService
                            .getAthenaErrorCodeAndMessage(((AthenaBusinessException) e).getErrorCode());
                    fxBondErrMsg = athenaErrorMessageModel.getErrorMessage();
                }
            } else {
                fxBondErrMsg = IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED,
                        new String[] { API003_ERROR_MSG_ATTR });
            }
            
        }
        // エラーが発生した場合はエラーリストに格納
        if (!StringUtil.isNullOrEmpty(fxStockErrMsg) || !StringUtil.isNullOrEmpty(fxBondErrMsg)) {
            response.getCustomerInfoAcquireErrorList().get(0).setForeignTradeAccountOpenStatusAcquireFailureMsg(
                    StringUtil.isNullOrEmpty(fxStockErrMsg) ? fxBondErrMsg : fxStockErrMsg);
            response.getCustomerInfoAcquireErrorList().get(0)
                    .setForeignTradeAccountOpenStatusAcquireFailureErrorLevel(ErrorLevel.WARNING.toString());
        }
    }
    
    /**
     * API007呼び出し処理
     * 
     * @param response
     * @throws Exception
     */
    private void getMultiGetCashDepositsReqInfo(IfaCustomerPortalA001ResponseDto response, String butenCode,
            String accountNumber) throws Exception {
        
        List<CashDeposit> cashDeposits = new ArrayList<>(); // API007の預り金リストを収集するリスト
        boolean allSuccessful = true; // すべての呼び出しが成功したかどうかを追跡するフラグ
        
        for (int i = 1; i <= API007_DAYS; i++) {
            try {
                cashDeposits.addAll(foreignAccountService.multiGetCashDeposits(butenCode, accountNumber,
                        API007_ACCOUNT_KIND_GENERAL, i, API007_CURRENCY_CODES).getCashDeposits());
            } catch (Exception e) {
                allSuccessful = false;
                String errorMsg = StringUtil.EMPTY_STRING;
                if (e instanceof AthenaBusinessException) {
                    AthenaErrorMessageModel athenaErrorMessageModel = cometCommonService
                            .getAthenaErrorCodeAndMessage(((AthenaBusinessException) e).getErrorCode());
                    errorMsg = athenaErrorMessageModel.getErrorMessage();
                } else {
                    errorMsg = IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED,
                            new String[] { API007_ERROR_MSG_ATTR });
                }
                response.getCustomerInfoAcquireErrorList().get(0)
                        .setBuyReservePowerInfoForeignAcquireFailureErrorLevel(ErrorLevel.WARNING.toString());
                response.getCustomerInfoAcquireErrorList().get(0)
                        .setBuyReservePowerInfoForeignAcquireFailureMsg(errorMsg);
                break; // エラーが発生したらループを終了
            }
        }
        
        // 口座区分='GENERAL'(総合)のループ処理が正常終了
        if (allSuccessful) {
            // FCT031.ジュニアISA契約区分＝1かつAPI001.払出制限解除フラグ＝1（払出制限中）の場合
            if ("1".equals(response.getJrIsaContractType())
                    && "1".equals(response.getWithdrawalRestrictionCancelFlag())) {
                for (int i = 1; i <= API007_DAYS; i++) {
                    try {
                        cashDeposits.addAll(foreignAccountService.multiGetCashDeposits(butenCode, accountNumber,
                                API007_ACCOUNT_KIND_JR_NISA, i, API007_CURRENCY_CODES).getCashDeposits());
                    } catch (Exception e) {
                        allSuccessful = false;
                        String errorMsg = StringUtil.EMPTY_STRING;
                        if (e instanceof AthenaBusinessException) {
                            AthenaErrorMessageModel athenaErrorMessageModel = cometCommonService
                                    .getAthenaErrorCodeAndMessage(((AthenaBusinessException) e).getErrorCode());
                            errorMsg = athenaErrorMessageModel.getErrorMessage();
                        } else {
                            errorMsg = IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED, new String[] { API007_ERROR_MSG_ATTR });
                        }
                        response.getCustomerInfoAcquireErrorList().get(0)
                                .setBuyReservePowerInfoForeignAcquireFailureErrorLevel(ErrorLevel.WARNING.toString());
                        response.getCustomerInfoAcquireErrorList().get(0).setBuyReservePowerInfoForeignAcquireFailureMsg(errorMsg);
                        break; // エラーが発生したらループを終了
                    }
                }
            }
        }
        
        // 11，12の処理
        calcForeignBuyPowerFlag(cashDeposits, response, allSuccessful);
    }
    
    /**
     * API005呼び出し処理
     * 
     * @param response
     * @throws Exception
     */
    private void getForeignAccountInfo(IfaCustomerPortalA001ResponseDto response, String butenCode,
            String accountNumber) throws Exception {
        
        CheckMarginAccountOpenResp api005Res = new CheckMarginAccountOpenResp();
        try {
            api005Res = foreignAccountService.checkMarginAccountOpen(butenCode, accountNumber,
                    MarginAccountType.FOREIGN.getId());
            
        } catch (Exception e) {
            String errorMsg = StringUtil.EMPTY_STRING;
            if (e instanceof AthenaBusinessException) {
                AthenaErrorMessageModel athenaErrorMessageModel = cometCommonService
                        .getAthenaErrorCodeAndMessage(((AthenaBusinessException) e).getErrorCode());
                errorMsg = athenaErrorMessageModel.getErrorMessage();
            } else {
                errorMsg = IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED, new String[] { API005_ERROR_MSG_ATTR });
            }
            // エラーの場合はエラーリストに格納
            response.getCustomerInfoAcquireErrorList().get(0)
                    .setUsStockMarginAccountOpenStatusAcquireFailureErrorLevel(ErrorLevel.WARNING.toString());
            response.getCustomerInfoAcquireErrorList().get(0)
                    .setUsStockMarginAccountOpenStatusAcquireFailureMsg(errorMsg);
        }
        if (api005Res != null && api005Res.getOpened() != null) {
            response.setForeignMarginAccountType(api005Res.getConvOpened());
            if (StringUtils.equals(ForeignMarginAccountType.MARGIN.getId(), api005Res.getConvOpened())) {
                // API006
                getMarginPowerDetailInfo(response, butenCode, accountNumber);
            }
        }
    }
    
    /**
     * API006呼び出し処理
     * 
     * @param response
     */
    private void getMarginPowerDetailInfo(IfaCustomerPortalA001ResponseDto response, String butenCode,
            String accountNumber) throws Exception {
        
        GetMarginPowerDetailResp api006Res = new GetMarginPowerDetailResp();
        try {
            // API006
            api006Res = foreignAccountService.getMarginPowerDetail(butenCode, accountNumber, API006_US);
            // 新規建余力(外貨)
            response.setForeignNewBuildingCapacity(api006Res.getMarginBuyingPower());
            // 維持率(外貨)
            response.setForeignCustomerInfoDetentionRate(api006Res.getDepositRateWithSameDayStandard());
        } catch (Exception e) {
            String errorMsg = StringUtil.EMPTY_STRING;
            if (e instanceof AthenaBusinessException) {
                AthenaErrorMessageModel athenaErrorMessageModel = cometCommonService
                        .getAthenaErrorCodeAndMessage(((AthenaBusinessException) e).getErrorCode());
                errorMsg = athenaErrorMessageModel.getErrorMessage();
            } else {
                errorMsg = IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED, new String[] { API005_ERROR_MSG_ATTR });
            }
            // エラーの場合はエラーリストに格納
            response.getCustomerInfoAcquireErrorList().get(0)
                    .setMarginPowerInfoForeignAcquireFailureErrorLevel(ErrorLevel.WARNING.toString());
            response.getCustomerInfoAcquireErrorList().get(0).setMarginPowerInfoForeignAcquireFailureMsg(errorMsg);
        }
    }
    
    /**
     * FCT030呼び出し処理
     * 
     * @param response
     * @return Fct030処理結果
     */
    private boolean getBrokerChargeList(IfaCustomerPortalA001ResponseDto response) {
        
        // INPUT無し
        InputFct030Dto input = new InputFct030Dto();
        OutputFct030Dto result = fct030.getData(input);
        if (result == null || CollectionUtils.isEmpty(result.getBrokerChargeList())) {
            // 0件の場合はエラー
            return false;
        }
        response.setBrokerChargeListCount(String.valueOf(result.getBrokerChargeList().size()));
        
        return true;
    }
    
    /**
     * 買付余力(外貨)有無算出処理
     * 
     * @param api007Res
     * @param response
     */
    private void calcForeignBuyPowerFlag(List<CashDeposit> cashDeposits, IfaCustomerPortalA001ResponseDto response,
            Boolean allSuccessful) {
        
        // API007の呼出結果が全て正常か
        if (allSuccessful == false || cashDeposits == null || cashDeposits.isEmpty()) {
            return;
        }
        
        // API007.預り金リスト.預り金≠0のデータがあるか
        boolean isExistsBuyingPower = cashDeposits.stream().anyMatch(cashDeposit -> !StringUtil
                .parseBigDecimal(cashDeposit.getDepositAmount()).equals(StringUtil.parseBigDecimal("0")));
        
        Long currencyCount = cashDeposits.stream()
                .filter(cashDeposit -> !StringUtil.parseBigDecimal(cashDeposit.getDepositAmount())
                        .equals(StringUtil.parseBigDecimal("0")))
                .map(cashDeposit -> cashDeposit.getCurrencyCode()).distinct().count();
        
        // 買付余力(外貨)有無
        response.setForeignBuyPowerFlag(
                isExistsBuyingPower ? ForeignBuyPowerFlag.EXISTS.getId() : ForeignBuyPowerFlag.NOTHING.getId());
        // 買付余力(外貨)あり国数
        response.setForeignBuyingPowerCountryCount(currencyCount.toString());
    }
    
    /**
     * 注意情報レベル算出
     * 
     * @param fct031Res
     * @return 注意情報レベル値
     */
    private String calcNoticeInfoLevel(OutputFct031Dto fct031Res) {
        
        String result = NoticeInfoLevel.NOTHING.getId();
        if (fct031Res.getNoteInfoErrorCount() > LIMIT_ZERO
                || fct031Res.getUnreadImportantNoticeTransactionLimitErrorNumber() > LIMIT_ZERO) {
            result = NoticeInfoLevel.EXIST_ERROR_EXIST.getId();
        } else if (fct031Res.getNoteInfoCount() > LIMIT_ZERO
                || fct031Res.getUnreadImportantNoticeTransactionLimitNumber() > LIMIT_ZERO) {
            result = NoticeInfoLevel.EXIST_ERROR_NOTHING.getId();
        }
        
        return result;
    }
    
    /**
     * 配受無区分算出処理
     * 
     * @param fct031Res
     * @return 配受無区分値
     */
    private String calcDividendReceiptType(OutputFct031Dto fct031Res) throws Exception {

        String result = DividendReceiptType.NOT_APPLICABLE.getId(); // 「該当しない」
        // 現在日時
        String currentDate = ifaDateUtil.format(IfaDateUtil.YYYYMMDD);
        // 特定口座配当受入開始日
        String startDate = DEFAULT_SPECIFIC_ACCOUNT_DIVIDEND_DATE;
        // 特定口座配当受入終了日
        String endDate = DEFAULT_SPECIFIC_ACCOUNT_DIVIDEND_DATE;
        if (!StringUtil.isNullOrEmpty(fct031Res.getSpecificAccountDividendStartDate())) {
            startDate = fct031Res.getSpecificAccountDividendStartDate();
        }
        if (!StringUtil.isNullOrEmpty(fct031Res.getSpecificAccountDividendEndDate())) {
            endDate = fct031Res.getSpecificAccountDividendEndDate();
        }

        // FCT031.特定口座区分＝1:特定口座(代行納付) の場合
        if (StringUtils.equals(SpecificAccount.SPECIFIC_PAYMENT_PROXY.getId(), fct031Res.getSpecificAccount())){
            // FCT031.特定口座配当受入開始日≦システム共通情報.システム日時（YYYYMMDD）≦FCT031.特定口座配当受入終了日
            if (!(startDate.compareTo(currentDate) <= 0  && currentDate.compareTo(endDate) <= 0)) {
                // 「該当する」
                result = DividendReceiptType.APPLICABLE.getId();
            }
        }

        return result;
    }
    
    /**
     * アクションID：A011
     * アクション名：メモ(IFA専用)更新
     * Dto リクエスト：IfaCustomerPortalA011DtoRequest
     * Dto レスポンス：IfaCustomerPortalA011DtoResponse
     * model リクエスト：IfaCustomerPortalSql005RequestModel
     * model レスポンス：IfaCustomerPortalSql005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerPortalA011ResponseDto> memoIFAUpdateA011(IfaCustomerPortalA011RequestDto dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaCustomerPortalServiceImplL.memoIFAUpdateA011");
        
        String errorMessage = StringUtil.EMPTY_STRING;
        
        // 初期化
        UserAccount ua = IfaCommonUtil.getUserAccount();
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // ユーザ共通情報.権限コードのチェック
        if (!StringUtils.equals(ua.getPrivId(), CHECK_AUTH_CODE)) {
            errorMessage = IfaCommonUtil.getMessage(ERRORS_CSM_IFA_EXCLUSIVE_INSUFFICIENT_PRIVILEGE);
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CSM_IFA_EXCLUSIVE_INSUFFICIENT_PRIVILEGE,
                    errorMessage);
        }
        // FCT001チェック
        if (!checkFct001(cc.getButenCode(), cc.getAccountNumber())) {
            errorMessage = IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST,
                    new String[] { cc.getButenCode(), cc.getAccountNumber() });
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_BUTENACCOUNTNOTEXIST, errorMessage);
        }
        
        // IFA専用顧客メモテーブルを更新
        try {
            updateIfaCustomerMemo(dtoReq.getIfaMemoContent(),dtoReq.getIfaMemoUpdateDateTime(), cc.getCustomerCode());
        } catch (Exception e) {
            LOGGER.debug("memoIFAUpdateA011 update error" + e.getMessage());
            errorMessage = IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED, new String[] { UPDATE_ERROR_MSG_ATTR });
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_PROCESSING_FAILED, errorMessage);
        }
        // メモ(IFA専用)を取得
        DataList<IfaCustomerPortalSql002ResponseModel> sql002Res = getMemoIfaInfo(cc.getCustomerCode());
        
        IfaCustomerPortalA011ResponseDto response = new IfaCustomerPortalA011ResponseDto();
        response.setIfaMemoContent(sql002Res.get(0).getIfaMemoContent());
        response.setIfaMemoUpdateDateTime(sql002Res.get(0).getIfaMemoUpdateDateTime());
        List<IfaCustomerPortalA011ResponseDto> resDto = new ArrayList<IfaCustomerPortalA011ResponseDto>();
        resDto.add(response);
        
        return IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
    /**
     * メモ(IFA専用)取得処理(SQL002)
     * @return　メモ(IFA専用)
     * @throws Exception
     */
    private DataList<IfaCustomerPortalSql002ResponseModel> getMemoIfaInfo(String customerCode) throws Exception {
        
        IfaCustomerPortalSql002RequestModel selSql002Req = new IfaCustomerPortalSql002RequestModel();
        selSql002Req.setCustomerCode(customerCode);
        DataList<IfaCustomerPortalSql002ResponseModel> selSql002Res = dao.selectIfaCustomerPortalSql002(selSql002Req);
        
        return selSql002Res;
    }
    
    /**
     * IFA専用顧客メモテーブルを更新(SQL003)
     * @param ifaMemoContent
     * @throws Exception 
     */
    private void updateIfaCustomerMemo(String ifaMemoContent,String ifaMemoUpdateDateTime, String customerCode) throws Exception {
        
        IfaCustomerPortalSql003SelectRequestModel selSql003Req = new IfaCustomerPortalSql003SelectRequestModel();
        selSql003Req.setCustomerCode(customerCode);
        DataList<IfaCustomerPortalSql003SelectResponseModel> selSql003Res = dao.selectIfaCustomerPortalSql003(selSql003Req);
        
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        int result = 0;
        if(selSql003Res != null && selSql003Res.getDataList() != null && selSql003Res.getDataList().size() > 0) {
            // 排他チェック
            IfaCustomerPortalSql003SelectExclusiveResquestModel selSql003ReqExclusive = new IfaCustomerPortalSql003SelectExclusiveResquestModel();
            selSql003ReqExclusive.setCustomerCode(customerCode);
            selSql003ReqExclusive.setIfaMemoUpdateDateTime(ifaMemoUpdateDateTime);
            DataList<IfaCustomerPortalSql003SelectExclusiveResponseModel> selSql003ResExclusive = dao.selectExclusiveIfaCustomerPortalSql003(selSql003ReqExclusive);
            
            if(selSql003ResExclusive != null && selSql003ResExclusive.getDataList() != null && selSql003ResExclusive.getDataList().size() > 0) {
                // UPDATE処理
                IfaCustomerPortalSql003UpdateRequestModel updSql003Req = new IfaCustomerPortalSql003UpdateRequestModel();
                updSql003Req.setCustomerCode(customerCode);
                updSql003Req.setUserId(ua.getUserId());
                updSql003Req.setIfaMemoContent(ifaMemoContent);
                updSql003Req.setIfaMemoUpdateDateTime(ifaMemoUpdateDateTime);
                result = dao.updateIfaCustomerPortalSql003(updSql003Req);
            }            
        } else {
            // INSERT処理
            IfaCustomerPortalSql003InsertRequestModel insSql003Req = new IfaCustomerPortalSql003InsertRequestModel();
            insSql003Req.setCustomerCode(customerCode);
            insSql003Req.setUserId(ua.getUserId());
            insSql003Req.setIfaMemoContent(ifaMemoContent);
            result = dao.insertIfaCustomerPortalSql003(insSql003Req);
            
        }
        
        if (result != 1) {
            throw new Exception("SQL003 update count Error.");
        }
        
    }
    
    /**
     * メモ(CCS専用)取得処理(SQL004)
     * @return　メモ(CCS専用)
     * @throws Exception 
     */
    private String getMemoCcsInfo(String customerCode) throws Exception {
        
        IfaCustomerPortalSql004RequestModel selSql004Req = new IfaCustomerPortalSql004RequestModel();
        selSql004Req.setCustomerCode(customerCode);
        
        DataList<IfaCustomerPortalSql004ResponseModel> sqlResult = dao.selectIfaCustomerPortalSql004(selSql004Req);
        if (sqlResult == null || CollectionUtils.isEmpty(sqlResult.getDataList())
                || sqlResult.getDataList().size() != SIZE_ONE || sqlResult.getDataList().get(0) == null) {
            return StringUtil.EMPTY_STRING;
        }
        
        return CollectionUtils.isEmpty(sqlResult.getDataList()) ? null : sqlResult.getDataList().get(0).getCcsMemo();
    }
    
    /**
     * コース名取得処理(SQL005)
     * @return　コース名
     * @throws Exception 
     */
    private String getCourseName(String dealerNumber) throws Exception {
        
        IfaCustomerPortalSql005RequestModel selSql005Req = new IfaCustomerPortalSql005RequestModel();
        selSql005Req.setDealerNumber(dealerNumber);
        
        DataList<IfaCustomerPortalSql005ResponseModel> sqlResult = dao.selectIfaCustomerPortalSql005(selSql005Req);
        
        if (sqlResult == null || CollectionUtils.isEmpty(sqlResult.getDataList())
                || sqlResult.getDataList().size() != SIZE_ONE || sqlResult.getDataList().get(0) == null) {
            return StringUtil.EMPTY_STRING;
        }
        
        return CollectionUtils.isEmpty(sqlResult.getDataList()) ? null
                : sqlResult.getDataList().get(0).getCustomerInfoCourse();
    }
    
    /**
     * 仲介業者顧客口座属性取得処理(SQL007)
     * @return　仲介業者顧客口座属性
     * @throws Exception 
     */
    private DataList<IfaCustomerPortalSql007ResponseModel> getBrokerCustomerAccount(String customerCode) throws Exception {
        IfaCustomerPortalSql007RequestModel sql007Req = new IfaCustomerPortalSql007RequestModel();
        sql007Req.setCustomerCode(customerCode);
        DataList<IfaCustomerPortalSql007ResponseModel> sql007Res = dao.selectIfaCustomerPortalSql007(sql007Req);
        
        return sql007Res;
    }
    
    /**
     * 顧客口座属性取得処理(SQL008)
     * @return　顧客口座属性
     * @throws Exception 
     */
    private DataList<IfaCustomerPortalSql008ResponseModel> getCustomerAccount(String customerCode) throws Exception {
        IfaCustomerPortalSql008RequestModel sql008Req = new IfaCustomerPortalSql008RequestModel();
        sql008Req.setCustomerCode(customerCode);
        DataList<IfaCustomerPortalSql008ResponseModel> sql008Res = dao.selectIfaCustomerPortalSql008(sql008Req);
        
        return sql008Res;
    }
    
    /**
     * 営業員属性設定マスタ取得処理(SQL009)
     * @return　営業員属性設定マスタ
     * @throws Exception 
     */
    private DataList<IfaCustomerPortalSql009ResponseModel> getEmp(String butenCode, String accountNumber) throws Exception {
        IfaCustomerPortalSql009RequestModel sql009Req = new IfaCustomerPortalSql009RequestModel();
        sql009Req.setButenCode(butenCode);
        sql009Req.setAccountNumber(accountNumber);
        DataList<IfaCustomerPortalSql009ResponseModel> sql009Res = dao.selectIfaCustomerPortalSql009(sql009Req);
        
        return sql009Res;
    }
    
    
    /**
     * EC顧客マスタビュー取得処理(SQL010)
     * @return　EC顧客マスタビュー
     * @throws Exception 
     */
    private DataList<IfaCustomerPortalSql010ResponseModel> getEcCustomer(String customerCode) throws Exception {
        IfaCustomerPortalSql010RequestModel sql010Req = new IfaCustomerPortalSql010RequestModel();
        sql010Req.setCustomerCode(customerCode);
        DataList<IfaCustomerPortalSql010ResponseModel> sql010Res = dao.selectIfaCustomerPortalSql010(sql010Req);
        
        return sql010Res;
    }
    
    /**
     * FCT001チェック
     * @param butenCode　部店コード
     * @param accountNumber 口座番号
     * @return FCT001チェック結果
     */
    private boolean checkFct001(String butenCode, String accountNumber) {
        
        InputFct001Dto input = new InputFct001Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        
        OutputFct001Dto output = fct001.doCheck(input);
        boolean isResultNg = true;
        
        if (output != null) {
            isResultNg = !StringUtils.equals(output.getTargetCustomerRefAuthFlag(),
                    Fct001.TARGET_CUSTOMER_REF_AUTH_FLAG_1);
        }
        if (isResultNg) {
            return false;
        }
        
        return true;
    }
    
    /**
     * FCT031呼び出し処理
     * @param response　レスポンス
     * @param butenCode　部店コード
     * @param accountNumber 口座番号
     * @return　FCT031処理結果
     * @throws Exception 
     */
    private boolean getCustomerInfo(IfaCustomerPortalA001ResponseDto response, String butenCode, String accountNumber)
            throws Exception {
        
        InputFct031Dto input = new InputFct031Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        OutputFct031Dto fct031Res = fct031.getData(input);
        // 顧客情報取得チェック
        if (fct031Res == null || Objects.equal(new OutputFct031Dto(), fct031Res)) {
            return false;
        }
        
        BeanUtils.copyProperties(response, fct031Res);
        // 配受無区分設定
        response.setDividendReceiptType(calcDividendReceiptType(fct031Res));
        // 注意情報レベル算出
        response.setNoticeInfoLevel(calcNoticeInfoLevel(fct031Res));
        
        /* ----------以下同一名・型ではない項目の設定----------  */
        // 顧客コード
        response.setCustomerCode(fct031Res.getCustomerCode() == null ? null : fct031Res.getCustomerCode().toString());
        // コンプラランク
        response.setComplianceLank(fct031Res.getCompRank());
        // 生年月日
        DateFormat sdf = new SimpleDateFormat(DateUtil.SEPARATED_YYYYMMDD);
        if (fct031Res.getCorBirthFlg() != null) {
            response.setCorBirthFlg(sdf.format(fct031Res.getCorBirthFlg()));
        }
        // 年齢
        response.setAge(fct031Res.getAge() == null ? null : fct031Res.getAge().toString());
        // 電話番号
        // 自宅電話番号(市外局番)、自宅電話番号(市内局番)、自宅電話番号(番号)のいずれか一つでも値なしの場合、""（ブランク）を設定
        if (StringUtils.isEmpty(fct031Res.getHomePhoneNumberAreaCode())
                || StringUtils.isEmpty(fct031Res.getHomePhoneNumberLocalAreaCode())
                || StringUtils.isEmpty(fct031Res.getHomePhoneNumber())) {
            response.setHomePhoneNumbar("");
        } else {
            // 上記以外の場合、自宅電話番号(市外局番) ＋ "-" ＋ 自宅電話番号(市内局番) ＋ "-" ＋ 自宅電話番号(番号)
            response.setHomePhoneNumbar(StringUtil.trim(fct031Res.getHomePhoneNumberAreaCode()) + PHONE_NUMBER_HYPHEN
                    + StringUtil.trim(fct031Res.getHomePhoneNumberLocalAreaCode()) + PHONE_NUMBER_HYPHEN
                    + StringUtil.trim(fct031Res.getHomePhoneNumber()));
        }
        // 携帯電話番号
        // 携帯電話番号(上5桁)、携帯電話番号(中5桁)、携帯電話番号(下5桁)のいずれか一つでも値なしの場合、""（ブランク）を設定
        if (StringUtils.isEmpty(fct031Res.getPhoneNumber1())
                || StringUtils.isEmpty(fct031Res.getPhoneNumber2())
                || StringUtils.isEmpty(fct031Res.getPhoneNumber3())) {
            response.setPhoneNumber2("");
        } else {
            // 上記以外の場合、携帯電話番号(上5桁) ＋ "-" ＋ 携帯電話番号(中5桁) ＋ "-" ＋ 携帯電話番号(下5桁)
            response.setPhoneNumber2(StringUtil.trim(fct031Res.getPhoneNumber1()) + PHONE_NUMBER_HYPHEN
                    + StringUtil.trim(fct031Res.getPhoneNumber2()) + PHONE_NUMBER_HYPHEN
                    + StringUtil.trim(fct031Res.getPhoneNumber3()));
        }
        // 代表者名
        response.setRepresentativeName(fct031Res.getRepresentativeNameKanji());
        // 代理人名
        response.setAgentName(fct031Res.getAgentNameKanji());
        // 代理人肩書
        response.setTitleOfAgent(fct031Res.getTitleOfAgeNtType());
        // 特定口座区分
        response.setSpecificAccountType(fct031Res.getSpecificAccount() == null ? null : fct031Res.getSpecificAccount());
        // マル優適格者区分
        response.setTaxExemptQualifiedPersonType(fct031Res.getTaxExemptQualifiedPersonType() == null ? null
                : fct031Res.getTaxExemptQualifiedPersonType());
        // ISA契約区分
        response.setIsaContractType(fct031Res.getIsaContractType() == null ? null : fct031Res.getIsaContractType());
        // ジュニアISA契約区分
        response.setJrIsaContractType(
                fct031Res.getJrIsaContractType() == null ? null : fct031Res.getJrIsaContractType());
        // ジュニアNISA口座番号
        response.setJrNisaAccountNumber(
                fct031Res.getJrNisaAccountNumber() == null ? null : fct031Res.getJrNisaAccountNumber());
        // ジュニア特定口座区分
        response.setJrTokuteiKouzaKbn(
                fct031Res.getJrSpecificAccount() == null ? null : fct031Res.getJrSpecificAccount());
        // 仲介業者名
        response.setBrokerName(fct031Res.getBrokerName() == null ? null : fct031Res.getBrokerName());
        // 仲介業者支店名
        response.setBrokerBranchName(fct031Res.getBranchName() == null ? null : fct031Res.getBranchName());
        // 仲介業者営業員名
        response.setBrokerChargeName(fct031Res.getBrokerChargeName() == null ? null : fct031Res.getBrokerChargeName());
        
        return true;
        
    }
    
    /**
     * APIリクエスト項目：口座番号設定値作成
     * 
     * @param accountNo
     * @return
     */
    private static String createApiRequestAccountNo(String accountNo) {
        
        return String.format("%7s", accountNo).replace(" ", "0");
    }
    
    /** 
     * 決算日を文字列(MM/DD)に変換
     * */
    private static String convertToMMDD(String date) {
        
        String newDate = null;
        if (StringUtils.isNotEmpty(date) && date.length() == 4) {
            newDate = date.substring(0,2) + "/" + date.substring(2,4);
        }
        return newDate;
    }
}
