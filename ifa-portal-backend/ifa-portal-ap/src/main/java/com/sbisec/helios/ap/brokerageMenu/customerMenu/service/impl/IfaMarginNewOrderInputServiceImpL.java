package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct008;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.component.Fct027;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct008Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct027Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct008Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct027Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA005DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA005DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA016DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA016DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputErrorModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMarginNewOrderInputService;
import com.sbisec.helios.ap.common.enums.DomesticMarginAccountType;
import com.sbisec.helios.ap.common.enums.DomesticStockTradeClass;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ExecuteMethod;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.enums.OrderClass;
import com.sbisec.helios.ap.common.enums.SelectMarket;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.exception.ApiError;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockOrderAutoIn;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockOrderAutoInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockOrderAutoOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityOutData;

/**
 * 画面ID：SUB0202_0212-01_1
 * 画面名：信用新規注文入力
 * @author 周, 卞
 *
 * 2023/08/17 新規作成
 * 2023/10/05 変更
 */
@Component(value = "cmpIfaMarginNewOrderInputService")
public class IfaMarginNewOrderInputServiceImpL implements IfaMarginNewOrderInputService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginNewOrderInputServiceImpL.class);
    
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
     * FCT008 営業日リスト
     */
    @Autowired
    private Fct008 fct008;
    
    /**
     * FCT021 取引制限チェック
     */
    @Autowired
    private Fct021 fct021;
    
    /**
     * FCT027 国内株式情報取得
     */
    @Autowired
    private Fct027 fct027;
    
    /**
     * 区分値取得クラス
     */
    @Autowired
    private CodeListService codeListService;

    /**
     * コードマスタ
     */
    @Autowired
    MCodeService mcodeService;
    
    /**
     * APIラッパークラス
     */
    @Autowired
    private ApiWrapper apiWrapper;
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}] */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 取引停止口座のため処理を進めることができません。 */
    private static final String ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** {0}ができないコースです。 \{0}：区分.対象取引（メッセージ表示用）（区分値：X　＠表示パターン：1　）*/
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 信用口座が未開設です。 */
    private static final String ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN = "errors.dms.domesticMarginAccount.notOpen";

    /** CCSIDが未登録のためご利用できません。 */
    private static final String ERRORS_CMN_CCSID_UNREGISTERED = "errors.cmn.ccsid.unregistered";

    /** 当該顧客の{0}に関わる重要な注意情報があるため処理を進めることができません。注意情報欄をご確認ください。 \{0}：区分.対象取引（メッセージ表示用）（区分値：X　＠表示パターン：1　）*/
    private static final String ERRORS_CMN_NOTICEERRORCHECK = "errors.cmn.noticeErrorCheck";
    
    /** 未確認の「重要なお知らせ」があります。 */
    private static final String ERRORS_INFORMATIONCHECK = "errors.informationCheck";
    
    /** 当該顧客の{0}に関わる注意情報があります。注意情報欄を確認してください。 */
    private static final String WARNINGS_CMN_NOTICEWARNINGCHECK = "warnings.cmn.noticeWarningCheck";
    
    /** 未確認の重要なお知らせがあります。注意情報欄を確認してください。*/
    private static final String WARNINGS_CMM_INFORMATIONCHECK = "warnings.cmm.informationCheck";
    
    /** 注文処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1}) */
    private static final String ERRORS_CMN_ORDEREXECUTION_FAILED = "errors.cmn.orderExecution.failed";
    
    /** 注文銘柄に対する取引注意情報があります。取引注意情報の内容を顧客へ説明後に注文を執行してください。 */
    private static final String WARNINGS_DMS_INFORMATION_CHECK = "warnings.dms.informationCheck";
    
    // --------------------------------
    // 設定値
    // --------------------------------
    /** FCT021：通貨コード　設定値 */
    private static final String CURRENCY_CODE_VALUE = "999";
    
    /** FCT021：国籍コード　設定値 */
    private static final String NATIONALITY_CODE_VALUE = "99";
    
    /** FCT003：証券金銭種別　設定値 国内株式*/
    private static final String DOMESTICSTOCK = "01";
    
    /** 区分定義.ドメインID_対象取引 */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 対象取引 区分値：3 */
    private static final String MSG_DISPLAY_TARGET_TRADE_KUBUN = "3";
    
    /** お知らせアラート条件 */
    private static final String NOTE_INFO_ALERT_FLAG = "1";
    
    /** お知らせエラー条件 */
    private static final String NOTE_INFO_ERR_FLAG = "1";
    
    /**　注意情報アラート条件　*/
    private static final String NOTE_LIMIT_ALERT_FLAG = "1";
    
    /**　注意情報エラー条件　*/
    private static final String NOTE_LIMIT_ERR_FLAG = "1";
    
    /**　期間条件:当日中　*/
    private static final String TODAY = "0";
    
    /** FCT027.規制銘柄区分:規制銘柄 */
    private static final String FCT027_REG_KBN_ON = "1";
    
    /** 内部者確認メッセージ */
    private static final String INSIDER_ERROR_MSG = "内部者登録があります。インサイダー情報に基づく売買ではないことを確認してください。";
    
    /** 内部者確認メッセージ対象区分値 */
    private static final List<String> INSIDER_ERROR_MSG_TARGET_KBN = Arrays.asList(new String[] { "1", "5" });
    
    /** 空売り規制抵触メッセージ */
    private static final String SHORT_SELLING_REGULATION_CONFLICT_MSG = "「空売り価格規制」に抵触する注文は失効されます。";
    
    /** 空売り規制抵触メッセージ */
    private static final List<String> SHORT_SELLING_REGULATION_CONFLICT_MSG_TARGET_KBN = Arrays
            .asList(new String[] { "3", "5" });
    
    //API設定値(固定値)
    
    /** API001 リクエスト（アカウントID）：ALL"0" */
    private static final String API001_REQUEST_ORDER_ID_ACCOUNT_ID = String
            .format("%10s", StringUtil.EMPTY_STRING).replace(" ", "0");
    
    /** API001　リクエスト（アカウント毎の連番）:ALL"0"*/
    private static final String API001_REQUEST_ORDER_ID_NUMBER = String.format("%7s", StringUtil.EMPTY_STRING)
            .replace(" ", "0");
    
    /** API001　リクエスト（オリジン）:"0"*/
    private static final String API001_REQUEST_ORDER_ID_ORIGIN = "0";
    
    /** API001　リクエスト（受渡方法-信用新規）:"6"*/
    private static final String API001_REQUEST_UKEW_HOHO = "6";
    
    /** API001　リクエスト（譲渡益税区分）:"△"*/
    private static final String API001_REQUEST_JO_ZEI_KBN = " ";
    
    /** API001　リクエスト（非特定預り売買区分）:"△"(：初期値)*/
    private static final String API001_REQUEST_HITOKUTEI_TRADE_KBN = " ";
    
    /** API001　リクエスト（摘要）:ALL"△"*/
    private static final String API001_REQUEST_SUMMARY = String.format("%30s", StringUtil.EMPTY_STRING);
    
    /** API001　リクエスト（決済方法区分）:"△"*/
    private static final String API001_REQUEST_PAYMENT_KBN = " ";
    
    /** API001　リクエスト（決済方法）:ALL"△"*/
    private static final String API001_REQUEST_PAYMENT_METHOD = String.format("%10s", StringUtil.EMPTY_STRING);
    
    /** API001　リクエスト（振込先銀行区分）:"△"*/
    private static final String API001_REQUEST_BANK_KBN = " ";
    
    /** API001　リクエスト（振込先銀行名）:'ALL"△"*/
    private static final String API001_REQUEST_BANK_NAME = String.format("%20s", StringUtil.EMPTY_STRING);
    
    /** API001　リクエスト（受付経路区分）:"0"：支店*/
    private static final String API001_REQUEST_CALLCENTER_KBN = "0";
    
    /** API001　リクエスト（ベティング区分）:"△"*/
    private static final String API001_REQUEST_VETTING_KBN = " ";
    
    /** API001　リクエスト（与信チェック用時価）:ALL"0"*/
    private static final String API001_REQUEST_CHECK_PRICE = String.format("%10s", " ").replace(" ", "0");
    
    /** API001　リクエスト（余力チェック区分）:'"△"：建玉余力または決済可能数量のチェック要*/
    private static final String API001_REQUEST_CHECK_ID = " ";
    
    /** API001　リクエスト（IPアドレス）:"ifap"*/
    private static final String API001_REQUEST_IP_ADDRESS = "ifap";
    
    /** API001　リクエスト（新規約定日）:ALL"△"*/
    private static final String API001_REQUEST_OPEN_TRADE_DATE = String.format("%8s", StringUtil.EMPTY_STRING);
    
    /** API001　リクエスト（新規単価）:ALL"△"*/
    private static final String API001_REQUEST_OPEN_PRICE = String.format("%12s", StringUtil.EMPTY_STRING);
    
    /** API001　リクエスト（一般信用売弁済期限年月日区分）:"△"*/
    private static final String API001_REQUEST_IPPAN_MG_PAYMENT_KBN = " ";
    
    /** API001　リクエスト（一般信用売弁済期限年月日数）:"△△"*/
    private static final String API001_REQUEST_IPPAN_MG_PAYMENT_LIMIT = "  ";
    
    /** API001 リクエスト 市場A:当社優先市場／SOR時の設定値 */
    private static final String API001_REQUEST_MARKET_SOR_VALUE = "tse";
    //-----------------------------------------
    
    /** API002 リクエスト（アカウントID）："" */
    private static final String API002_REQUEST_ORDER_ID_ACCOUNT_ID = "";
    
    /** API002　リクエスト（アカウント毎の連番）:""*/
    private static final String API002_REQUEST_ORDER_ID_NUMBER = "";
    
    /** API002　リクエスト（オリジン）:""*/
    private static final String API002_REQUEST_ORDER_ID_ORIGIN = "";
    
    /** API002　リクエスト（受渡方法-信用新規）:"6"(信用新規)*/
    private static final String API002_REQUEST_UKEW_HOHO = "6";
    
    /** API002　リクエスト（譲渡益税区分）:"△"(現物売以外)*/
    private static final String API002_REQUEST_JO_ZEI_KBN = " ";
    
    /** API002 リクエスト（非特定預り売買区分）:"△"（信用新規)*/
    private static final String API002_REQUEST_HITOKUTEI_TRADE_KBN = " ";
    
    /** API002　リクエスト（摘要）:ALL"△"*/
    private static final String API002_REQUEST_SUMMARY = String.format("%30s", StringUtil.EMPTY_STRING);
    
    /** API002　リクエスト（決済方法区分）:"△"*/
    private static final String API002_REQUEST_PAYMENT_KBN = " ";
    
    /** API002　リクエスト（決済方法）:ALL"△"*/
    private static final String API002_REQUEST_PAYMENT_METHOD = String.format("%10s", StringUtil.EMPTY_STRING);
    
    /** API002　リクエスト（振込先銀行区分）:"△"*/
    private static final String API002_REQUEST_BANK_KBN = " ";
    
    /** API002　リクエスト（振込先銀行名）:'ALL"△"*/
    private static final String API002_REQUEST_BANK_NAME = String.format("%20s", StringUtil.EMPTY_STRING);
    
    /** API002　リクエスト（受付経路区分）:"0"（支店）*/
    private static final String API002_REQUEST_CALLCENTER_KBN = "0";
    
    /** API002　リクエスト（ベティング区分）:"△"*/
    private static final String API002_REQUEST_VETTING_KBN = " ";
    
    /** API002　リクエスト（与信チェック用時価）:ALL"0"*/
    private static final String API002_REQUEST_CHECK_PRICE = String.format("%10s", " ").replace(" ", "0");
    
    /** API002　リクエスト（余力チェック区分）:'"△"：建玉余力または決済可能数量のチェック要*/
    private static final String API002_REQUEST_CHECK_ID = " ";
    
    /** API002　リクエスト（OCO指成区分）:"△"(指定なし)*/
    private static final String API002_REQUEST_OCO_SASINARI_KBN = " ";
    
    /** API002　リクエスト（OCO指値）:ALL"△"*/
    private static final String API002_REQUEST_OCO_PRICE = String.format("%10s", StringUtil.EMPTY_STRING);
    
    /** API002　リクエスト（自動注文種別）:'"IF△△":IFD親注文*/
    private static final String API002_REQUEST_AUTO_ORDER_KIND = "IF  ";
    
    /** API002　リクエスト（IPアドレス）:"ifap"*/
    private static final String API002_REQUEST_IP_ADDRESS = "ifap";
    
    /** API002　リクエスト（引継ぎID）:"△"*/
    private static final String API002_REQUEST_TRANS_ID = " ";
    
    /** API002　リクエスト（媒介）:"△"*/
    private static final String API002_REQUEST_INTERMEDIARY = " ";
    
    /** API002　リクエスト（新規約定日）:ALL"△"*/
    private static final String API002_REQUEST_OPEN_TRADE_DATE = String.format("%8s", StringUtil.EMPTY_STRING);
    
    /** API002　リクエスト（新規単価）:ALL"△"*/
    private static final String API002_REQUEST_OPEN_PRICE = String.format("%12s", StringUtil.EMPTY_STRING);
    
    /** API002　リクエスト（一般信用売弁済期限年月日区分）:"△"*/
    private static final String API002_REQUEST_IPPAN_MG_PAYMENT_KBN = " ";
    
    /** API002　リクエスト（一般信用売弁済期限年月日数）:"△△"*/
    private static final String API002_REQUEST_IPPAN_MG_PAYMENT_LIMIT = "  ";
    
    //---------------------------------------------------
    
    /** API003 リクエスト（余力項目セット区分）:"A"(Ｔ～Ｔ＋５まで全てセット)*/
    private static final String API003_REQUEST_KBN1 = "A";
    
    /** API003 リクエスト（追証項目セット区分）:"N"(セット不要)*/
    private static final String API003_REQUEST_KBN2 = "N";
    
    //------------------------------------------------------
    
    /**　APIリクエスト固定値"SLO"　*/
    private static final String API_REQUEST_SLO = "SLO";
    
    /**　APIリクエスト固定値"OCO"　*/
    private static final String API_REQUEST_OCO = "OCO";

    //------------------------------------------------------

    /** コードテーブル.種別.項目説明文言 */
    private static final String M_CODE_CODE_TYPE_DESCRIPTION_MESSAGE = "58";

    /** コードテーブル.CODE_1.信用新規注文入力 */
    private static final String M_CODE_CODE_1_MARGIN_NEW_ORDER_INPUT = "SUB0202_0212-01_1";

    /** コードテーブル.CODE_2.維持率(円貨) */
    private static final String M_CODE_CODE_2_MAINTENANCE_RATE_JPY = "maintenanceRateJPY";
    
    /**
     * アクションID：A001
     * アクション名：初期化 
     * Dto リクエスト：IfaMarginNewOrderInputA001DtoRequest
     * Dto レスポンス：IfaMarginNewOrderInputA001DtoResponse
     * model リクエスト：IfaMarginNewOrderInputSql001RequestModel
     * model レスポンス：IfaMarginNewOrderInputSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginNewOrderInputA001DtoResponse> initializeA001(IfaMarginNewOrderInputA001DtoRequest dtoReq)
            throws Exception {
    	
    	ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        DataList<IfaMarginNewOrderInputA001DtoResponse> dtoRes = new DataList<IfaMarginNewOrderInputA001DtoResponse>();
        List<IfaMarginNewOrderInputA001DtoResponse> resDto = new ArrayList<IfaMarginNewOrderInputA001DtoResponse>();
        QueryMgEstimateCapabilityOutData api003Res = new QueryMgEstimateCapabilityOutData();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaMarginNewOrderInputServiceImplL.initializeA001");
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // 共通関数のエラーメッセージを格納
        IfaMarginNewOrderInputErrorModel error = new IfaMarginNewOrderInputErrorModel();
        // FCT001
        if (!callFCT001(cc.getButenCode(), cc.getAccountNumber(), error)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        }
        
        // FCT003
        OutputFct003Dto output = callFCT003(cc.getButenCode(), cc.getAccountNumber(), null);
        // 媒介可否リスト.取引種別が　"信用新規買",または媒介可否リスト.取引種別が　"信用新規売"が存在したらOK
        if (output == null || CollectionUtils.isEmpty(output.getMediateProprietyList()) || !(output
                .getMediateProprietyList().stream()
                .filter(m -> StringUtils.equals(m.getMediatePropriety(), MediateAbleTradeFlag.ARI.getId())
                        && (StringUtils.equals(m.getTradeClass(), DomesticStockTradeClass.SHINYOSHINKI_BUY.getId())
                                || StringUtils.equals(m.getTradeClass(),
                                        DomesticStockTradeClass.SHINYOSHINKI_SELL.getId())))
                .findFirst().isPresent())) {
            
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE, new String[] {
                            codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN) }));
        }
        
        if (StringUtils.equals(cc.getDomesticMarginAccountType(), DomesticMarginAccountType.CASH_ACCOUNT.getId())) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN, IfaCommonUtil.getMessage(ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN));
        } else if (!(StringUtils.equals(cc.getDomesticMarginAccountType(),
                DomesticMarginAccountType.MARGIN_ACCOUNT.getId()))) {
            return dtoRes;
        }
        
        try {
        	api003Res = callAPI003(cc.getButenCode(), cc.getAccountNumber());
        	if (api003Res != null) {
                // APIレスポンスチェック
                apiErrorUtil.checkApiResponse(api003Res.getShubetu(),api003Res.getCode(),api003Res.getMessage());
                if(apiErrorUtil.isFatal()) {
                	dtoRes = apiErrorUtil.createDataList(resDto, "");
                	return dtoRes;
                }
        	}
        }catch(ApiError ae) {
        	dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ae.getMessage(), "");
        	return dtoRes;
        }

        // SQL001 維持率(円貨)説明文言を取得する(SQL001の代わりにMCodeServiceを使用)
        List<MCode> selSql001Res = mcodeService.getMCodeList(
                M_CODE_CODE_TYPE_DESCRIPTION_MESSAGE,
                M_CODE_CODE_1_MARGIN_NEW_ORDER_INPUT,
                M_CODE_CODE_2_MAINTENANCE_RATE_JPY
        );

        String maintenanceRateJpyAmountDescriptionMessage = null;
        if (selSql001Res != null && 0 < selSql001Res.size()) {
            maintenanceRateJpyAmountDescriptionMessage = selSql001Res.get(0).getName();
        }

        // レスポンスの設定
        IfaMarginNewOrderInputA001DtoResponse response = new IfaMarginNewOrderInputA001DtoResponse();
        if (api003Res != null && !CollectionUtils.isEmpty(api003Res.getSettlementDateT())) {
            //受渡日(T+0)
            response.setSettlementDateT0(api003Res.getSettlementDateT().get(0).getSettlementDate());
            //新規建余力（T+0）
            response.setNewBuildingCapacityT0(api003Res.getSettlementDateT().get(0).getMarginCapacity());
            //維持率（T+0）
            response.setRateT0(api003Res.getSettlementDateT().get(0).getActualGrntRate());
            if (api003Res.getSettlementDateT().size() > 1) {
                //受渡日(T+1)
                response.setSettlementDateT1(api003Res.getSettlementDateT().get(1).getSettlementDate());
                //新規建余力（T+1）
                response.setNewBuildingCapacityT1(api003Res.getSettlementDateT().get(1).getMarginCapacity());
                //維持率（T+1）
                response.setRateT1(api003Res.getSettlementDateT().get(1).getActualGrntRate());
            }
            // 維持率(円貨)説明文言
            response.setMaintenanceRateJpyAmountDescriptionMessage(maintenanceRateJpyAmountDescriptionMessage);
        }
        
        resDto.add(response);
        dtoRes = apiErrorUtil.createDataList(resDto, "");
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：銘柄選択、市場選択
     * Dto リクエスト：IfaMarginNewOrderInputA002DtoRequest
     * Dto レスポンス：IfaMarginNewOrderInputA002DtoResponse
     * model リクエスト：IfaMarginNewOrderInputSql001RequestModel
     * model レスポンス：IfaMarginNewOrderInputSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginNewOrderInputA002DtoResponse> brandSelectionMarketSelectionA002(
            IfaMarginNewOrderInputA002DtoRequest dtoReq) throws Exception {
        
        DataList<IfaMarginNewOrderInputA002DtoResponse> dtoRes = new DataList<IfaMarginNewOrderInputA002DtoResponse>();
        List<IfaMarginNewOrderInputA002DtoResponse> resDto = new ArrayList<IfaMarginNewOrderInputA002DtoResponse>();
        IfaMarginNewOrderInputA002DtoResponse response = new IfaMarginNewOrderInputA002DtoResponse();
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaMarginNewOrderInputServiceImplL.selectionA002");

        // FCT027
        OutputFct027Dto fct027Result = callFCT027(dtoReq.getBrandCode());
        
        //FCT008
        OutputFct008Dto fct008Result = callFCT008(dtoReq.getBrandCode(), dtoReq.getMarket());
        
        if (fct027Result != null) {
            //銘柄コード.
            response.setBrandCode(fct027Result.getBrandCode());
            //規制銘柄区分.
            response.setRegKbn(fct027Result.getRegKbn());
            //福証一般信用区分.
            response.setMktIppanLoanKbnFko(fct027Result.getMktIppanLoanKbnFko());
            //名証一般信用区分.
            response.setMktIppanLoanKbnNgy(fct027Result.getMktIppanLoanKbnNgy());
            //札証一般信用区分.
            response.setMktIppanLoanKbnSpr(fct027Result.getMktIppanLoanKbnSpr());
            //東証一般信用区分.
            response.setMktIppanLoanKbnTky(fct027Result.getMktIppanLoanKbnTky());
            //東証貸借区分.
            response.setMktLoanKbnTky(fct027Result.getMktLoanKbnTky());
            //PTS貸借区分.
            response.setMktLoanKbnPts(fct027Result.getMktLoanKbnPts());
            //PTS一般信用区分.
            response.setMktIppanLoanKbnPts(fct027Result.getMktIppanLoanKbnPts());
        }
        
        if (fct008Result != null) {
            ///営業日リスト
            response.setBusinessDayList(String.valueOf(fct008Result.getBussiessDaylist()));
        }
        
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, "", "");
        return dtoRes;
    }
    
    /**
     * アクションID：A005
     * アクション名：更新
     * Dto リクエスト：IfaMarginNewOrderInputA005DtoRequest
     * Dto レスポンス：IfaMarginNewOrderInputA005DtoResponse
     * model リクエスト：IfaMarginNewOrderConfirmSql001RequestModel
     * model レスポンス：IfaMarginNewOrderConfirmSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginNewOrderInputA005DtoResponse> updateA005(IfaMarginNewOrderInputA005DtoRequest dtoReq)
            throws Exception {
        
    	ApiErrorUtil apiErrorUtil = new ApiErrorUtil();   	
        DataList<IfaMarginNewOrderInputA005DtoResponse> dtoRes = new DataList<IfaMarginNewOrderInputA005DtoResponse>();
        List<IfaMarginNewOrderInputA005DtoResponse> resDto = new ArrayList<IfaMarginNewOrderInputA005DtoResponse>();
        QueryMgEstimateCapabilityOutData api003Res = new QueryMgEstimateCapabilityOutData();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaMarginNewOrderInputServiceImplL.UpdateA005");
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        try {
            api003Res = callAPI003(cc.getButenCode(), cc.getAccountNumber());
        	if (api003Res != null) {
                // APIレスポンスチェック
        		apiErrorUtil.checkApiResponse(api003Res.getShubetu(),api003Res.getCode(),api003Res.getMessage());
                if(apiErrorUtil.isFatal()) {
                	dtoRes = apiErrorUtil.createDataList(resDto, "");
                	return dtoRes;
                }
        	}
        }catch(ApiError ae) {
        	//異常終了
        	dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ae.getMessage(), "");
        	return dtoRes;
        }

        //FCT008
        OutputFct008Dto fct008Result = callFCT008(dtoReq.getBrandCode(), dtoReq.getMarket());
        
        // レスポンスの設定
        IfaMarginNewOrderInputA005DtoResponse response = new IfaMarginNewOrderInputA005DtoResponse();
        //取引種別
        response.setTradeCd(dtoReq.getTradeCd());
        //銘柄コード
        response.setBrandCode(dtoReq.getBrandCode());
        if (api003Res != null && !CollectionUtils.isEmpty(api003Res.getSettlementDateT())) {
            //受渡日(T+0)
            response.setSettlementDateT0(api003Res.getSettlementDateT().get(0).getSettlementDate());
            //新規建余力（T+0）
            response.setNewBuildingCapacityT0(api003Res.getSettlementDateT().get(0).getMarginCapacity());
            //維持率（T+0）
            response.setRateT0(api003Res.getSettlementDateT().get(0).getActualGrntRate());
            if (api003Res.getSettlementDateT().size() > 1) {
                //受渡日(T+1)
                response.setSettlementDateT1(api003Res.getSettlementDateT().get(1).getSettlementDate());
                //新規建余力（T+1）
                response.setNewBuildingCapacityT1(api003Res.getSettlementDateT().get(1).getMarginCapacity());
                //維持率（T+1）
                response.setRateT1(api003Res.getSettlementDateT().get(1).getActualGrntRate());
            }
        }
        
        if (fct008Result != null) {
            ///営業日リスト
            response.setBusinessDayList(String.valueOf(fct008Result.getBussiessDaylist()));
        }
        
        resDto.add(response);
        dtoRes = apiErrorUtil.createDataList(resDto, "");
        return dtoRes;
    }
    
    /**
     * アクションID：A016
     * アクション名：注文確認
     * Dto リクエスト：IfaMarginNewOrderInputA016DtoRequest
     * Dto レスポンス：IfaMarginNewOrderInputA016DtoResponse
     * model リクエスト：IfaMarginNewOrderConfirmSql001RequestModel
     * model レスポンス：IfaMarginNewOrderConfirmSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginNewOrderInputA016DtoResponse> orderConfirmA016(IfaMarginNewOrderInputA016DtoRequest dtoReq)
            throws Exception {
    	
    	ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        DataList<IfaMarginNewOrderInputA016DtoResponse> dtoRes = new DataList<IfaMarginNewOrderInputA016DtoResponse>();
        List<IfaMarginNewOrderInputA016DtoResponse> resDto = new ArrayList<IfaMarginNewOrderInputA016DtoResponse>();
        IfaMarginNewOrderInputA016DtoResponse response = new IfaMarginNewOrderInputA016DtoResponse();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaMarginNewOrderInputServiceImplL.orderConfirmationA016");
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // 共通関数のエラーメッセージを格納
        IfaMarginNewOrderInputErrorModel error = new IfaMarginNewOrderInputErrorModel();
        // FCT001
        if (!callFCT001(cc.getButenCode(), cc.getAccountNumber(), error)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        }
        
        // FCT003
        OutputFct003Dto output = callFCT003(cc.getButenCode(), cc.getAccountNumber(), dtoReq.getTradeCd());
        // レスポンス.媒介可否リスト.媒介可否＝"1"（媒介可)が存在したらOK
        if (output == null || CollectionUtils.isEmpty(output.getMediateProprietyList())
                || !output.getMediateProprietyList().stream()
                        .filter(m -> StringUtils.equals(m.getMediatePropriety(), MediateAbleTradeFlag.ARI.getId()))
                        .findFirst().isPresent()) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE, new String[] {
                            codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN) }));
        }
        
        if (StringUtils.equals(cc.getDomesticMarginAccountType(), DomesticMarginAccountType.CASH_ACCOUNT.getId())) {
            // 区分.対象取引（メッセージ表示用）（区分値：3　＠表示パターン：1　）
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN, IfaCommonUtil.getMessage(ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN, new String[] {
                    codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN) }));
        } else if (!(StringUtils.equals(cc.getDomesticMarginAccountType(),
                DomesticMarginAccountType.MARGIN_ACCOUNT.getId()))) {
            return dtoRes;
        }

        // ユーザ共通情報 .CCSログイン用IDが未設定(Null または空文字）の場合：取引不可エラーを返す
        UserAccount ua = IfaCommonUtil.getUserAccount();
        if (StringUtils.isEmpty(ua.getCcsUserId())) {
            DataList<IfaMarginNewOrderInputA016DtoResponse> res = IfaCommonUtil.createDataList(
                    List.of(),
                    ErrorLevel.FATAL,
                    ERRORS_CMN_CCSID_UNREGISTERED,
                    IfaCommonUtil.getMessage(ERRORS_CMN_CCSID_UNREGISTERED)
            );

            return res;
        }
        
        // FCT021
        OutputFct021Dto fct021Result = callFCT021(cc.getButenCode(), cc.getAccountNumber(), dtoReq.getTradeCd(),
                dtoReq.getMarket(), dtoReq.getMarginTradeTypeText(), error);
        List<String> noticeInfoAlertList = new ArrayList<String>();
        List<String> noticeAlertList = new ArrayList<String>();
        if (!StringUtil.isNullOrEmpty(error.getErrorMessage())) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        }
        if (StringUtils.equals(fct021Result.getNoteInfoAlertFlag(), NOTE_INFO_ALERT_FLAG)) {
            // 区分.対象取引（メッセージ表示用）（区分値：3　＠表示パターン：1　）
            response.setNoticeInfoAlert(
                    List.of((IfaCommonUtil.getMessage(WARNINGS_CMN_NOTICEWARNINGCHECK,
                            new String[] {
                                    codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN),
                                    MSG_DISPLAY_TARGET_TRADE_KUBUN }))));
            noticeInfoAlertList.add(IfaCommonUtil.getMessage(WARNINGS_CMN_NOTICEWARNINGCHECK,
                    new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN),
                            MSG_DISPLAY_TARGET_TRADE_KUBUN }));
        }
        if (StringUtils.equals(fct021Result.getNoteLimitAlertFlag(), NOTE_LIMIT_ALERT_FLAG)) {
            response.setNoticeAlert(List.of(IfaCommonUtil.getMessage(WARNINGS_CMM_INFORMATIONCHECK)));
            noticeAlertList.add(IfaCommonUtil.getMessage(WARNINGS_CMM_INFORMATIONCHECK));
        }
        response.setNoticeInfoAlert(noticeInfoAlertList);
        response.setNoticeAlert(noticeAlertList);
        
        // FCT027
        OutputFct027Dto fct027Result = callFCT027(dtoReq.getBrandCode());
        List<String> TradeNoticeInfoBrandMsgList = new ArrayList<String>();
        if (fct027Result != null) {
            // 銘柄名
            response.setBrandName(fct027Result.getBrandName());
            //銘柄コード.
            response.setBrandCode(fct027Result.getBrandCode());
            //プレミアム空売り区分.
            response.setPremiumShortSaleCcategory(fct027Result.getPremiumShortSaleCcategory());
            // 取引注意情報（銘柄）メッセージ
            if (StringUtils.equals(fct027Result.getRegKbn(), FCT027_REG_KBN_ON)) {
                TradeNoticeInfoBrandMsgList.add(IfaCommonUtil.getMessage(WARNINGS_DMS_INFORMATION_CHECK));
            }
        }
        response.setTradeNoticeInfoBrandMsg(TradeNoticeInfoBrandMsgList);
        // API処理結果
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            // 国内株式注文確認（API001）
            EstimateMarginOrderOutData apiRes = new EstimateMarginOrderOutData();
            try {
            	apiRes = callAPI001(dtoReq, cc);
            } catch (ApiError ae) {
            	//異常終了
            	return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_ORDEREXECUTION_FAILED,
                        IfaCommonUtil.getMessage(ERRORS_CMN_ORDEREXECUTION_FAILED,
                                new String[] { ae.getCode(), ae.getMessage() }));
            }
            //APIWarnigメッセージハンドリング・処理続行
            apiErrorUtil.checkApiResponse(apiRes.getShubetu(),apiRes.getCode(),apiRes.getMessage());
            if (apiErrorUtil.isFatal()) {
                return apiErrorUtil.createDataList(new ArrayList<>(), ERRORS_CMN_ORDEREXECUTION_FAILED);
            }
            setApi001Result(response, apiRes, dtoReq.getTradeCd());

        }
        
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            EstimateStockOrderAutoOutData apiRes = new EstimateStockOrderAutoOutData();
            try {
            	apiRes = callAPI002(dtoReq, cc);
            } catch (ApiError ae) {
            	//異常終了
            	return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_ORDEREXECUTION_FAILED,
            			IfaCommonUtil.getMessage(ERRORS_CMN_ORDEREXECUTION_FAILED,
                                new String[] { ae.getCode(), ae.getMessage() }));
            }
            //APIWarnigメッセージハンドリング・処理続行
            apiErrorUtil.checkApiResponse(apiRes.getShubetu(),apiRes.getCode(),apiRes.getMessage());
            if (apiErrorUtil.isFatal()) {
                return apiErrorUtil.createDataList(new ArrayList<>(), ERRORS_CMN_ORDEREXECUTION_FAILED);
            }
            setApi002Result(response, apiRes, dtoReq.getTradeCd());

        }
        //入力項目をレスポンスにコピー
        BeanUtils.copyProperties(response, dtoReq);
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }
    
    /**
     * FCT001チェック
     * @param butenCode 部店コード
     * @param accountNumber　口座番号
     * @return チェック結果
     */
    private boolean callFCT001(String butenCode, String accountNumber, IfaMarginNewOrderInputErrorModel error) {
        
        InputFct001Dto input = new InputFct001Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        
        OutputFct001Dto output = fct001.doCheck(input);
        if (output != null) {
            if (StringUtils.equals(output.getTargetCustomerRefAuthFlag(),
                    TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
                error.setErrorCode(ERRORS_BUTENACCOUNTNOTEXIST);
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST, new String[] { butenCode, accountNumber }));
                return false;
            }
            if (StringUtils.equals(output.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
                error.setErrorCode(ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE);
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE));
                return false;
            }
        }
        return true;
    }
    
    /**
     * FCT003チェック
     * 
     * @param butenCode　部店コード
     * @param accountNumber　口座番号
     * @param tradeCd 取引種別
     * @return チェック結果
     */
    private OutputFct003Dto callFCT003(String butenCode, String accountNumber, String tradeCd) {
        
        InputFct003Dto input = new InputFct003Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        input.setProductCd(DOMESTICSTOCK);
        if (!StringUtil.isNullOrEmpty(tradeCd)) {
            input.setTradeCd(tradeCd);
        }
        
        return fct003.doCheck(input);
    }
    
    /**
     * FCT008呼び出し
     * 
     * @param brandCode　銘柄コード
     * @param market　市場
     * @return FCT008出力結果
     */
    private OutputFct008Dto callFCT008(String brandCode, String market) {
        
        InputFct008Dto input = new InputFct008Dto();
        input.setBrandCode(brandCode);
        input.setPeriodTargetMarket(market);
        
        return fct008.getData(input);
        
    }
    
    /**
     * FCT021
     * 
     * @param butenCode　部店コード
     * @param accountNumber　口座番号
     * @param tradeCd 取引種別
     * @return fct021出力結果
     */
    private OutputFct021Dto callFCT021(String butenCode, String accountNumber, String tradeCd,
            String tradeRestrictChkMarket, String paymentLimit, IfaMarginNewOrderInputErrorModel error
        ) throws Exception {
        
        InputFct021Dto input = new InputFct021Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        input.setTradeCd(tradeCd);
        input.setProductCd(Fct021.DOMESTIC_STOCK);
        input.setCountryCd(NATIONALITY_CODE_VALUE);
        input.setCurrencyCode(CURRENCY_CODE_VALUE);
        if (StringUtils.equals(input.getProductCd(), Fct021.DOMESTIC_STOCK)) {
            input.setTradeRestrictChkMarket(tradeRestrictChkMarket);
            input.setPaymentLimit(paymentLimit);
        }
        
        OutputFct021Dto output = fct021.doCheck(input);
        if (output != null) {
            if (StringUtils.equals(output.getNoteInfoErrFlag(), NOTE_INFO_ERR_FLAG)) {
                // 区分.対象取引（メッセージ表示用）（区分値：3　＠表示パターン：1　）を取得
                error.setErrorCode(ERRORS_CMN_NOTICEERRORCHECK);
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_CMN_NOTICEERRORCHECK, new String[] {
                        codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN) }));
                return null;
            }
            if (StringUtils.equals(output.getNoteLimitErrFlag(), NOTE_LIMIT_ERR_FLAG)) {
                error.setErrorCode(ERRORS_INFORMATIONCHECK);
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_INFORMATIONCHECK));
                return null;
            }
        }
        
        return output;
    }
    
    /**
     * FCT027呼び出し
     * 
     * @param brandCode 銘柄コード
     * @return fct027出力結果
     */
    private OutputFct027Dto callFCT027(String brandCode) {
        
        InputFct027Dto input = new InputFct027Dto();
        input.setBrandCode(brandCode);
        
        return fct027.getData(input);
        
    }
    
    /**
     * API001呼び出し処理
     * 
     * @param dtoReq A016リクエストDTO
     * @param cc 顧客共通情報
     * @return API出力結果
     */
    private EstimateMarginOrderOutData callAPI001(IfaMarginNewOrderInputA016DtoRequest dtoReq, CustomerCommon cc)
        throws Exception {
        //API001 Response
        EstimateMarginOrderOutData result = new EstimateMarginOrderOutData();
        //API001 Input
        EstimateMarginOrderIn input = new EstimateMarginOrderIn();
        //API001 Request
        EstimateMarginOrderInData inData = new EstimateMarginOrderInData();
        
        //ユーザー共通情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        //部店コード3桁 
        inData.setButenCd(cc.getButenCode());
        //口座番号7桁 
        inData.setKozaNo(createApiRequestAccountNo(cc.getAccountNumber()));
        //アカウントID　(固定値:ALL"0" xxx-xxxxxxx) 
        inData.setAccountId(API001_REQUEST_ORDER_ID_ACCOUNT_ID);
        //アカウント毎の連番　(固定値:ALL"0" 長さ7) 
        inData.setNumber(API001_REQUEST_ORDER_ID_NUMBER);
        //オリジン　(固定値:"0") 
        inData.setOrigin(API001_REQUEST_ORDER_ID_ORIGIN);
        //銘柄コード 
        inData.setBrandCd(String.format("%-5s", dtoReq.getBrandCode()));
        //売買区分 
        inData.setTradeKbn(dtoReq.getTradeCd());
        //注文株数 0埋め8桁
        inData.setQuantity(String.format("%8s", dtoReq.getOrderQuantity()).replace(" ", "0"));
        //指成区分
        inData.setSasinariKbn(getSasinarikbnApi001(dtoReq));
        //指値 0埋め10桁
        inData.setPrice(getPriceApi001(dtoReq));
        //受渡方法　(固定値:"6"信用新規)
        inData.setUkewHoho(API001_REQUEST_UKEW_HOHO);
        //発注市場
        inData.setMarket(dtoReq.getMarket());
        //譲渡益税区分　(固定値:"△")
        inData.setJoZeiKbn(API001_REQUEST_JO_ZEI_KBN);
        //非特定預かり売買区分　(固定値:"△"初期値)
        inData.setHitokuteiTradeKbn(API001_REQUEST_HITOKUTEI_TRADE_KBN);
        // 弁済期限
        inData.setPaymentLimit(dtoReq.getMarginTradeTypeText());
        //有効期限
        inData.setLimit(getLimit(dtoReq));
        //摘要　(固定値:ALL"△"　長さ30)
        inData.setSummary(API001_REQUEST_SUMMARY);
        //決済方法区分　(固定値:"△")
        inData.setPaymentKbn(API001_REQUEST_PAYMENT_KBN);
        //決済方法　(固定値ALL:"△"　長さ10)
        inData.setPaymentMethod(API001_REQUEST_PAYMENT_METHOD);
        //振込先近郊区分 (固定値"△")
        inData.setBankKbn(API001_REQUEST_BANK_KBN);
        //振込先銀行名 (固定値:ALL"△" 長さ20)
        inData.setBankName(API001_REQUEST_BANK_NAME);
        //受付経路区分(固定値:"0":支店)
        inData.setCallcenterKbn(API001_REQUEST_CALLCENTER_KBN);
        //ユーザーID
        inData.setUserId(ua.getCcsUserId());
        //ぺディング区分 (固定値"△")
        inData.setVettingKbn(API001_REQUEST_VETTING_KBN);
        //与信チェック用時価 (固定値:ALL"0" 長さ10)
        inData.setCheckPrice(API001_REQUEST_CHECK_PRICE);
        //手数料区分
        inData.setComId(cc.getCustomerAttribute());
        //与信チェック区分 (固定値:"△"建玉余力または決済可能数量のチェック要)
        inData.setCheckId(API001_REQUEST_CHECK_ID);
        //RBE注文種別
        inData.setRbeOrderKind(getRbeOrderKindApi001(dtoReq));
        //トリガ発動ゾーン
        inData.setTriggerZone(getTriggerZoneApi001(dtoReq));
        //トリガ値段 0埋め10桁
        inData.setTriggerPrice(getTriggerPriceApi001(dtoReq));
        //OCO指成区分
        inData.setOcoSasinariKbn(getOcoSasinariKbnApi001(dtoReq));
        //OCO指値
        inData.setOcoPrice(getOcoPrice(dtoReq));
        //IPアドレス (固定値:"ifap")
        inData.setIpAddress(String.format("%-39s", API001_REQUEST_IP_ADDRESS));
        //新規約定日 (固定値:ALL"△" 長さ8)
        inData.setOpenTradeDate(API001_REQUEST_OPEN_TRADE_DATE);
        //新規単価 (固定値:ALL"△" 長さ12)
        inData.setOpenPrice(API001_REQUEST_OPEN_PRICE);
        //一般信用売弁済期限年月日区分 (固定値:"△")
        inData.setIppanMgPaymentKbn(API001_REQUEST_IPPAN_MG_PAYMENT_KBN);
        //一般信用売弁済期限年月日数 (固定値:"△△")
        inData.setIppanMgPaymentLimit(API001_REQUEST_IPPAN_MG_PAYMENT_LIMIT);
        // SOR受注時板乗せ市場区分
        inData.setSorLastMarket(getSorLastMarket(dtoReq.getMarket()));
        
        input.setIndata(inData);
        result = apiWrapper.estimateMarginOrder(input);
        
        return result;
    }
    
    /**
     * API002呼び出し処理
     * 
     * @param dtoReq　A016リクエストDTO
     * @param cc　顧客共通情報
     * @return API出力結果
     * @throws Exception 
     */
    private EstimateStockOrderAutoOutData callAPI002(IfaMarginNewOrderInputA016DtoRequest dtoReq, CustomerCommon cc)
            throws Exception {
        
        EstimateStockOrderAutoOutData result = new EstimateStockOrderAutoOutData();
        EstimateStockOrderAutoIn input = new EstimateStockOrderAutoIn();
        EstimateStockOrderAutoInData inData = new EstimateStockOrderAutoInData();
        
        //ユーザー共通情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        //部店コード3桁
        inData.setButenCd(cc.getButenCode());
        //口座番号7桁
        inData.setKozaNo(createApiRequestAccountNo(cc.getAccountNumber()));
        //アカウントID　(固定値:"")
        inData.setAccountId(API002_REQUEST_ORDER_ID_ACCOUNT_ID);
        //アカウント毎の連番　(固定値:"")
        inData.setNumber(API002_REQUEST_ORDER_ID_NUMBER);
        //オリジン　(固定値:"")
        inData.setOrigin(API002_REQUEST_ORDER_ID_ORIGIN);
        //銘柄コード
        inData.setBrandCd(String.format("%-5s", dtoReq.getBrandCode()));
        //売買区分
        inData.setTradeKbn(dtoReq.getTradeCd());
        //注文株数 0埋め8桁
        inData.setQuantity(String.format("%8s", dtoReq.getOrderQuantity()).replace(" ", "0"));
        //指成区分
        inData.setSasinariKbn(dtoReq.getIfd1LimitExecutionConditionList());
        //指値 0埋め10桁
        inData.setPrice(getPriceApi002(dtoReq));
        //受渡方法　(固定値:"6"信用新規)
        inData.setUkewHoho(API002_REQUEST_UKEW_HOHO);
        //発注市場
        inData.setMarket(dtoReq.getMarket());
        //譲渡益税区分　(固定値:"△")
        inData.setJoZeiKbn(API002_REQUEST_JO_ZEI_KBN);
        //非特定預かり売買区分　(固定値:"△"初期値)
        inData.setHitokuteiTradeKbn(API002_REQUEST_HITOKUTEI_TRADE_KBN);
        // 弁済期限
        inData.setPaymentLimit(dtoReq.getMarginTradeTypeText());
        //有効期限
        inData.setLimit(getLimit(dtoReq));
        //摘要　(固定値:ALL"△"　長さ30)
        inData.setSummary(API002_REQUEST_SUMMARY);
        //決済方法区分　(固定値:"△")
        inData.setPaymentKbn(API002_REQUEST_PAYMENT_KBN);
        //決済方法　(固定値ALL:"△"　長さ10)
        inData.setPaymentMethod(API002_REQUEST_PAYMENT_METHOD);
        //振込先近郊区分 (固定値"△")
        inData.setBankKbn(API002_REQUEST_BANK_KBN);
        //振込先銀行名 (固定値:ALL"△" 長さ20)
        inData.setBankName(API002_REQUEST_BANK_NAME);
        //受付経路区分(固定値:"0":支店)
        inData.setCallcenterKbn(API002_REQUEST_CALLCENTER_KBN);
        //ユーザーID
        inData.setUserId(ua.getCcsUserId());
        //ぺディング区分 (固定値"△")
        inData.setVettingKbn(API002_REQUEST_VETTING_KBN);
        //与信チェック用時価 (固定値:ALL"0" 長さ10)
        inData.setCheckPrice(API002_REQUEST_CHECK_PRICE);
        //手数料区分
        inData.setComId(cc.getCustomerAttribute());
        //与信チェック区分 (固定値:"△"建玉余力または決済可能数量のチェック要)
        inData.setCheckId(API002_REQUEST_CHECK_ID);
        //RBE注文種別
        inData.setRbeOrderKind(getRbeOrderKindApi002(dtoReq));
        //トリガ発動ゾーン
        inData.setTriggerZone(getTriggerZoneApi002(dtoReq));
        //トリガ値段 0埋め10桁
        inData.setTriggerPrice(getTriggerPriceApi002(dtoReq));
        //OCO指成区分 (固定値:"△"(指定無し))
        inData.setOcoSasinariKbn(API002_REQUEST_OCO_SASINARI_KBN);
        //OCO指値 (固定値:ALL"△" 長さ10)
        inData.setOcoPrice(API002_REQUEST_OCO_PRICE);
        //自動注文種別 (固定値:"IF△△"(IFD親注文))
        inData.setAutoOrderKind(API002_REQUEST_AUTO_ORDER_KIND);
        //DONE指成区分
        inData.setDoneSasinariKbn(getDoneSasinariKbnApi002(dtoReq));
        //DONE指値 0埋め10桁
        inData.setDonePrice(getDonePriceApi002(dtoReq));
        //DONE有効期限
        inData.setDoneLimit(getDoneLimitApi002(dtoReq));
        //DONERBE注文種別
        inData.setDoneRbeOrderKind(getDoneRbeOrderKindApi002(dtoReq));
        //DONEトリガ発動ゾーン
        inData.setDoneTriggerZone(getDoneTriggerZoneApi002(dtoReq));
        //DONEトリガ値段 0埋め10桁
        inData.setDoneTriggerPrice(getDoneTriggerPriceApi002(dtoReq));
        //DONEOCO指成区分
        inData.setDoneOcoSasinariKbn(getDoneOcoSasinariKbnApi002(dtoReq));
        //DONEOCO指値 0埋め10桁
        inData.setDoneOcoPrice(getDoneOcoPriceApi002(dtoReq));
        //IPアドレス (固定値:"ifap")
        inData.setIpAddress(String.format("%-39s", API002_REQUEST_IP_ADDRESS));
        //引継ぎID (固定値:"△")
        inData.setTransId(API002_REQUEST_TRANS_ID);
        //媒介 (固定値:"△")
        inData.setIntermediary(API002_REQUEST_INTERMEDIARY);
        //新規約定日 (固定値:ALL"△" 長さ8)
        inData.setOpenTradeDate(API002_REQUEST_OPEN_TRADE_DATE);
        //新規単価 (固定値:ALL"△" 長さ12)
        inData.setOpenPrice(API002_REQUEST_OPEN_PRICE);
        //一般信用売弁済期限年月日区分 (固定値:"△")
        inData.setIppanMgPaymentKbn(API002_REQUEST_IPPAN_MG_PAYMENT_KBN);
        //一般信用売弁済期限年月日数 (固定値:"△△")
        inData.setIppanMgPaymentLimit(API002_REQUEST_IPPAN_MG_PAYMENT_LIMIT);
        
        input.setIndata(inData);
        
        result = apiWrapper.estimateStockOrderAuto(input);
        
        return result;
    }
    
    /**
     * API003呼び出し処理
     * 
     * @param butenCode 部店コード
     * @param accountNumber　口座番号
     * @return API結果
     */
    private QueryMgEstimateCapabilityOutData callAPI003(String butenCode, String accountNumber) 
    		throws  Exception {
        
        QueryMgEstimateCapabilityOutData result = new QueryMgEstimateCapabilityOutData();
        QueryMgEstimateCapabilityIn input = new QueryMgEstimateCapabilityIn();
        QueryMgEstimateCapabilityInData inData = new QueryMgEstimateCapabilityInData();
        
        inData.setButenCd(butenCode);
        inData.setKozaNo(createApiRequestAccountNo(accountNumber));
        inData.setRequestKbn1(API003_REQUEST_KBN1);
        inData.setRequestKbn2(API003_REQUEST_KBN2);
        
        input.setIndata(inData);
        // NRI_APIから買付余力情報を取得する。
        result = apiWrapper.queryMgEstimateCapability(input);

        return result;
    }
    
    /**
     * API001の結果をDTOに格納する
     * 
     * @param res　A016レスポンスDTO
     * @return api001res API001の出力結果
     */
    private void setApi001Result(IfaMarginNewOrderInputA016DtoResponse res, EstimateMarginOrderOutData api001res,
            String tradeCd) {
        
        //種別
        res.setShubetu(api001res.getShubetu());
        //エラーコード
        res.setCode(api001res.getCode());
        //エラーメッセージ
        res.setErrMessage(api001res.getMessage());
        //内部者確認メッセージ
        List<String> insiderErrorMsgList = new ArrayList<String>();
        if (INSIDER_ERROR_MSG_TARGET_KBN.contains(api001res.getInsiderErrKbn())) {
            insiderErrorMsgList.add(INSIDER_ERROR_MSG);
        }
        res.setInsiderErrorMsg(insiderErrorMsgList);
        //空売り規制抵触メッセージ
        List<String> shortSellingRegulationConflictMessageList = new ArrayList<String>();
        if (SHORT_SELLING_REGULATION_CONFLICT_MSG_TARGET_KBN.contains(api001res.getInsiderErrKbn())) {
            shortSellingRegulationConflictMessageList.add(SHORT_SELLING_REGULATION_CONFLICT_MSG);
        }
        res.setShortSellingRegulationConflictMessage(shortSellingRegulationConflictMessageList);
        //見積単価
        res.setQuoteUnitPrice(api001res.getEstimatePrice());
        //約定金額
        res.setContractAmount(api001res.getAmount());
        //手数料/諸費用
        res.setCharge(api001res.getCommission());
        //消費税
        res.setConsumptionTax(api001res.getConsumptionTax());
        //適用金利
        if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_BUY.getId())) {
            res.setApplicableInterestRate(api001res.getAppInterestRates());
        }
        //適用貸株料
        if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_SELL.getId())) {
            res.setApplicableStockLendingFees(api001res.getAppLendingStock());
        }
        //精算金額
        res.setSettlementAmount(api001res.getNetAmount());
        //約定予定日
        res.setContractDate(api001res.getTradeDate());
        //受渡予定日
        res.setDeliveryDate(api001res.getSettlementDate());
        //受注日時
        String dateTime = api001res.getAcceptDate() + " " + api001res.getAcceptTime();
        res.setOrderDayTime(dateTime);
        //注文入力市場
        res.setOrderedMarket(api001res.getOrderedMarket());
    }
    
    /**
     * API002の結果をDTOに格納する
     * 
     * @param res　A016レスポンスDTO
     * @return api002res　API002の出力結果
     */
    private void setApi002Result(IfaMarginNewOrderInputA016DtoResponse res, EstimateStockOrderAutoOutData api002res,
            String tradeCd) {
        
        //種別
        res.setShubetu(api002res.getShubetu());
        //エラーコード
        res.setCode(api002res.getCode());
        //エラーメッセージ
        res.setErrMessage(api002res.getMessage());
        //内部者確認メッセージ
        List<String> insiderErrorMsgList = new ArrayList<String>();
        if (INSIDER_ERROR_MSG_TARGET_KBN.contains(api002res.getInsiderErrKbn())) {
            insiderErrorMsgList.add(INSIDER_ERROR_MSG);
        }
        res.setInsiderErrorMsg(insiderErrorMsgList);
        //空売り規制抵触メッセージ
        List<String> shortSellingRegulationConflictMessageList = new ArrayList<String>();
        if (SHORT_SELLING_REGULATION_CONFLICT_MSG_TARGET_KBN.contains(api002res.getInsiderErrKbn())) {
            shortSellingRegulationConflictMessageList.add(SHORT_SELLING_REGULATION_CONFLICT_MSG);
        }
        res.setShortSellingRegulationConflictMessage(shortSellingRegulationConflictMessageList);
        //見積単価
        res.setQuoteUnitPrice(api002res.getEstimatePrice());
        //約定金額
        res.setContractAmount(api002res.getAmount());
        //手数料/諸費用
        res.setCharge(api002res.getCommission());
        //消費税
        res.setConsumptionTax(api002res.getConsumptionTax());
        //適用金利
        if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_BUY.getId())) {
            res.setApplicableInterestRate(api002res.getAppInterestRates());
        }
        //適用貸株料
        if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_SELL.getId())) {
            res.setApplicableStockLendingFees(api002res.getAppLendingStock());
        }
        //精算金額
        res.setSettlementAmount(api002res.getNetAmount());
        //約定予定日
        res.setContractDate(api002res.getTradeDate());
        //受渡予定日
        res.setDeliveryDate(api002res.getSettlementDate());
        //受注日時
        String dateTime = api002res.getAcceptDate() + " " + api002res.getAcceptTime();
        res.setOrderDayTime(dateTime);
        //注文入力市場
        res.setOrderedMarket(api002res.getOrderedMarket());
    }
    
    /**
     *　API001のリクエスト項目：指成区分の値を取得
     *
     * @param dtoReq　A016リクエストDTO
     * @return sasinariKbn　指成区分の設定値
     */
    private String getSasinarikbnApi001(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String sasinariKbn = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(dtoReq.getOrderKind());
        if (Objects.isNull(orderKindClass)) {
            return null;
        }
        switch (orderKindClass) {
        case NORMAL:
            sasinariKbn = dtoReq.getSasinariJyouken();
            break;
        case OCO:
            sasinariKbn = dtoReq.getOco1LimitExecutionConditionList();
            break;
        default:
            break;
        }
        return sasinariKbn;
    }
    
    /**
     *　API001のリクエスト項目：指値の値を取得
     *
     * @param dtoReq　A016リクエストDTO
     * @return price　指値の設定値
     */
    private String getPriceApi001(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String price = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(dtoReq.getOrderKind());
        if (!Objects.isNull(orderKindClass)) {
            switch (orderKindClass) {
            case NORMAL:
                //通常/逆指値.執行方法
                String sasinari = dtoReq.getSasinariHouhou();
                if (StringUtils.equals(sasinari, ExecuteMethod.LIMIT.getId())) {
                    price = dtoReq.getPrice();
                } else if (StringUtils.equals(sasinari, ExecuteMethod.STOP.getId())) {
                    if (StringUtils.equals(dtoReq.getGyakusasiHouhou(), ExecuteMethod.LIMIT.getId())) {
                        price = dtoReq.getPrice();
                    }
                }
                break;
            case OCO:
                price = dtoReq.getOco1DomesticLimitPrice();
                break;
            default:
                break;
            }
        }
        //上記のいずれでもない 
        if (price == null) {
            price = StringUtil.EMPTY_STRING;
        }
        return String.format("%10s", price).replace(" ", "0");
    }
    
    /**
     *　API001のリクエスト項目：有効期限の値を取得
     *
     * @param dtoReq　A016リクエストDTO
     * @return limit　有効期限の設定値
     */
    private String getLimit(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String limit = null;
        if (StringUtils.equals(dtoReq.getPeriodRadio(), TODAY)) {
            //固定値"△△△△△△△△" 
            limit = String.format("%8s", StringUtil.EMPTY_STRING);
        } else {
            limit = DateFormatUtil.dateStringYMD(dtoReq.getLimit());
        }
        return limit;
    }
    
    /**
     *　API001のリクエスト項目：rbe注文種別の値を取得
     * @param dtoReq　A016リクエストDTO
     * @return rbeOrderKind　rbe注文種別の設定値
     */
    private String getRbeOrderKindApi001(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String rbeOrderKind = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(dtoReq.getOrderKind());
        if (Objects.isNull(orderKindClass)) {
            return rbeOrderKind;
        }
        switch (orderKindClass) {
        case NORMAL:
            //通常/逆指値.執行方法
            String sasinari = dtoReq.getSasinariHouhou();
            if (StringUtils.equals(sasinari, ExecuteMethod.LIMIT.getId())
                    || StringUtils.equals(sasinari, ExecuteMethod.MARKET.getId())) {
                //固定値:"△△△" 
                rbeOrderKind = String.format("%3s", StringUtil.EMPTY_STRING);
            } else if (StringUtils.equals(sasinari, ExecuteMethod.STOP.getId())) {
                //固定値:SLO
                rbeOrderKind = API_REQUEST_SLO;
            }
            break;
        case OCO:
            //固定値:OCO 
            rbeOrderKind = API_REQUEST_OCO;
            break;
        default:
            break;
        }
        return rbeOrderKind;
    }
    
    /**
     *　API001のリクエスト項目：トリガ発動ゾーンの値を取得
     * @param dtoReq　A016リクエストDTO
     * @return triggerZone　トリガ発動ゾーンの設定値
     */
    private String getTriggerZoneApi001(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String triggerZone = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(dtoReq.getOrderKind());
        if (Objects.isNull(orderKindClass)) {
            //固定値:"△"(指定なし)
            return " ";
        }
        //取引種別(売買区分)
        String tradeCd = dtoReq.getTradeCd();
        switch (orderKindClass) {
        case NORMAL:
            //執行方法
            if (StringUtils.equals(dtoReq.getSasinariHouhou(), ExecuteMethod.STOP.getId())) {
                if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_BUY.getId())) {
                    //固定値:"0"(以上)
                    triggerZone = "0";
                } else if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_SELL.getId())) {
                    //固定値:"１"(以下)
                    triggerZone = "1";
                }
            }
            break;
        case OCO:
            if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_BUY.getId())) {
                //固定値:"0"(以上)
                triggerZone = "0";
            } else if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_SELL.getId())) {
                //固定値:"１"(以下)
                triggerZone = "1";
            }
            break;
        default:
            break;
        }
        //上記のいずれでもない
        if (triggerZone == null) {
            //固定値:"△"(指定なし) 
            triggerZone = " ";
        }
        return triggerZone;
    }
    
    /**
     *　API001のリクエスト項目：トリガ値段の値を取得
     * @param dtoReq　A016リクエストDTO
     * @return triggerPrice　トリガ値段の設定値
     */
    private String getTriggerPriceApi001(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String triggerPrice = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(dtoReq.getOrderKind());
        if (!Objects.isNull(orderKindClass)) {
            switch (orderKindClass) {
            case NORMAL:
                if (StringUtils.equals(dtoReq.getSasinariHouhou(), ExecuteMethod.STOP.getId())) {
                    triggerPrice = dtoReq.getTriggerPrice();
                    break;
                }
                break;
            case OCO:
                triggerPrice = dtoReq.getOco2DomesticStopOrderPrice();
                break;
            default:
                break;
            }
        }
        
        //上記のいずれでもない
        if (triggerPrice == null) {
            //固定値:"0000000000"
            triggerPrice = StringUtil.EMPTY_STRING;
        }
        return String.format("%10s", triggerPrice).replace(" ", "0");
    }
    
    /**
     *　APIのリクエスト項目：OCO指値の値を取得
     *
     * @param dtoReq　A016リクエストDTO
     * @return ocoPrice　OCO指値の設定値
     */
    private String getOcoPrice(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String ocoPrice = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(dtoReq.getOrderKind());
        if (Objects.isNull(orderKindClass)) {
            return ocoPrice;
        }
        switch (orderKindClass) {
        case NORMAL:
            //固定値:"△"
            ocoPrice = String.format("%10s", StringUtil.EMPTY_STRING);
            break;
        case OCO:
            ocoPrice = String.format("%10s", dtoReq.getOco2DomesticLimitPrice()).replace(" ", "0");
            break;
        default:
            break;
        }
        return ocoPrice;
    }
    
    /**
     *　API001のリクエスト項目：OCO指成区分の値を取得
     *
     * @param dtoReq　A016リクエストDTO
     * @return ocoSasine　OCO指成区分の設定値
     */
    private String getOcoSasinariKbnApi001(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String ocoSasinariKbn = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(dtoReq.getOrderKind());
        if (Objects.isNull(orderKindClass)) {
            return ocoSasinariKbn;
        }
        switch (orderKindClass) {
        case NORMAL:
            //固定値:"△"
            ocoSasinariKbn = " ";
            break;
        case OCO:
            ocoSasinariKbn = dtoReq.getOco2StopOrderMarketExecutionConditionList();
            break;
        default:
            break;
        }
        return ocoSasinariKbn;
    }
    
    /**
     *　API002のリクエスト項目：指値の値を取得
     *
     * @param dtoReq　A016リクエストDTO
     * @return price　指値の設定値
     */
    private String getPriceApi002(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String price = null;
        //執行方法
        ExecuteMethod executeMethod = ExecuteMethod.valueOfId(dtoReq.getIfd1OrderExecuteMethodList());
        if (Objects.isNull(executeMethod)) {
            //固定値"0000000000"
            return String.format("%10s", StringUtil.EMPTY_STRING).replace(" ", "0");
        }
        switch (executeMethod) {
        case LIMIT:
            price = dtoReq.getIfd1DomesticLimitPrice();
            break;
        case STOP:
            if (StringUtils.equals(dtoReq.getIfd1StopOrderExecuteMethodList(), ExecuteMethod.LIMIT.getId()))
                price = dtoReq.getIfd1DomesticLimitPrice();
            break;
        default:
            break;
        }
        //上記のいずれでもない
        if (price == null) {
            //固定値"0000000000"
            price = StringUtil.EMPTY_STRING;
        }
        return String.format("%10s", price).replace(" ", "0");
    }
    
    /**
     *　API002のリクエスト項目：rbe注文種別の値を取得
     * @param dtoReq　A016リクエストDTO
     * @return rbeOrderKind　rbe注文種別の設定値
     */
    private String getRbeOrderKindApi002(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String rbeOrderKind = null;
        //執行方法
        ExecuteMethod executeMethod = ExecuteMethod.valueOfId(dtoReq.getIfd1OrderExecuteMethodList());
        if (Objects.isNull(executeMethod)) {
            return rbeOrderKind;
        }
        switch (executeMethod) {
        case LIMIT:
        case MARKET:
            //固定値:"△△△"：通常注文
            rbeOrderKind = String.format("%3s", StringUtil.EMPTY_STRING);
            break;
        case STOP:
            //固定値:"SLO":逆指値注文
            rbeOrderKind = API_REQUEST_SLO;
            break;
        default:
            break;
        }
        return rbeOrderKind;
    }
    
    /**
     *　API002のリクエスト項目：トリガ発動ゾーンの値を取得
     *
     * @param dtoReq　A016リクエストDTO
     * @return triggerZone　トリガ発動ゾーンの設定値
     */
    private String getTriggerZoneApi002(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String triggerZone = null;
        //IFD1.執行方法 = 逆指値
        if (StringUtils.equals(dtoReq.getIfd1OrderExecuteMethodList(), ExecuteMethod.STOP.getId())) {
            String tradeCd = dtoReq.getTradeCd();
            // 取引種別 = 信用新規買
            if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_BUY.getId())) {
                //固定値:"0"(以上)
                triggerZone = "0";
            // 取引種別 = 信用新規売
            } else if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_SELL.getId())) {
                //固定値:"１"(以下)
                triggerZone = "1";
            }
        }
        //上記のいずれでもない
        if (triggerZone == null) {
            //固定値:"△"(指定なし)
            triggerZone = " ";
        }
        return triggerZone;
    }
    
    /**
     *　API002のリクエスト項目：トリガ値段の値を取得
     *
     * @param dtoReq　A016リクエストDTO
     * @return　triggerPrice　トリガ値段の設定値
     */
    private String getTriggerPriceApi002(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String triggerPrice = null;
        //執行方法
        if (StringUtils.equals(dtoReq.getIfd1OrderExecuteMethodList(), ExecuteMethod.STOP.getId())) {
            triggerPrice = dtoReq.getIfd1DomesticStopOrderPrice();
        }
        //上記のいずれでもない
        if (triggerPrice == null) {
            //固定値:"0000000000"
            triggerPrice = StringUtil.EMPTY_STRING;
        }
        return String.format("%10s", triggerPrice).replace(" ", "0");
    }
    
    /**
     *　API002のリクエスト項目：DONE指成区分の値を取得
     *
     * @param dtoReq　A016リクエストDTO
     * @return doneSasinariKbn　DONE指成区分の設定値
     */
    private String getDoneSasinariKbnApi002(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String doneSasinariKbn = null;
        String sasinariKey = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(dtoReq.getOrderKind());
        if (Objects.isNull(orderKindClass)) {
            return doneSasinariKbn;
        }
        switch (orderKindClass) {
        case IFD:
            sasinariKey = dtoReq.getIfd2LimitExecutionConditionList();
            break;
        case IFDOCO:
            sasinariKey = dtoReq.getOco1LimitExecutionConditionList();
            break;
        default:
            break;
        }
        if (sasinariKey != null) {
            doneSasinariKbn = sasinariKey;
        }
        return doneSasinariKbn;
    }
    
    /**
     *　API002のリクエスト項目：Done指値の値を取得
     *
     * @param dtoReq　A016リクエストDTO
     * @return donePrice　Done指値の設定値
     */
    private String getDonePriceApi002(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String donePrice = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(dtoReq.getOrderKind());
        if (Objects.isNull(orderKindClass)) {
            //固定値:"0000000000"
            return String.format("%10s", StringUtil.EMPTY_STRING).replace(" ", "0");
        }
        switch (orderKindClass) {
        case IFD:
            //IFD2.執行方法
            String exeMethod = dtoReq.getIfd2OrderExecuteMethodList();
            if (StringUtils.equals(exeMethod, ExecuteMethod.LIMIT.getId())) {
                donePrice = dtoReq.getIfd2DomesticLimitPrice();
            } else if (StringUtils.equals(exeMethod, ExecuteMethod.STOP.getId())) {
                if (StringUtils.equals(dtoReq.getIfd2StopOrderExecuteMethodList(), ExecuteMethod.LIMIT.getId())) {
                    donePrice = dtoReq.getIfd2DomesticLimitPrice();
                }
            }
            break;
        case IFDOCO:
            donePrice = dtoReq.getOco1DomesticLimitPrice();
            break;
        default:
            break;
        }
        //上記のいずれでもない
        if (donePrice == null) {
            //固定値:"0000000000"
            donePrice = StringUtil.EMPTY_STRING;
        }
        return String.format("%10s", donePrice).replace(" ", "0");
    }
    
    /**
     *　API002のリクエスト項目：DONE有効期限の値を取得
     *
     * @param dtoReq　A016リクエストDTO
     * @return doneLimit　DONE有効期限の設定値
     */
    private String getDoneLimitApi002(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String doneLimit = null;
        // 期間.期間条件
        if (StringUtils.equals(dtoReq.getIfd2PeriodRadio(), TODAY)) {
            //固定値"△△△△△△△△"
            doneLimit = String.format("%8s", StringUtil.EMPTY_STRING);
        } else {
            doneLimit = DateFormatUtil.dateStringYMD(dtoReq.getIfd2Limit());
        }
        return doneLimit;
    }
    
    /**
     *　API002のリクエスト項目：DONERBE注文種別の値を取得
     *
     * @param dtoReq　A016リクエストDTO
     * @return doneRbeOrderKind　DONERBE注文種別の設定値
     */
    private String getDoneRbeOrderKindApi002(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String doneRbeOrderKind = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(dtoReq.getOrderKind());
        if (Objects.isNull(orderKindClass)) {
            //固定値:"△△△"(通常注文)
            return String.format("%3s", StringUtil.EMPTY_STRING);
        }
        switch (orderKindClass) {
        case IFD:
            //IFD2.執行方法
            String exeMethod = dtoReq.getIfd2OrderExecuteMethodList();
            if (StringUtils.equals(exeMethod, ExecuteMethod.LIMIT.getId())
                    || StringUtils.equals(exeMethod, ExecuteMethod.MARKET.getId())) {
                //固定値:"△△△"(通常注文)
                doneRbeOrderKind = String.format("%3s", StringUtil.EMPTY_STRING);
            } else if (StringUtils.equals(exeMethod, ExecuteMethod.STOP.getId())) {
                //固定値:SLO(逆指値注文)
                doneRbeOrderKind = API_REQUEST_SLO;
            }
            break;
        case IFDOCO:
            //固定値:OCO(DONE注文がOCO)
            doneRbeOrderKind = API_REQUEST_OCO;
            break;
        default:
            break;
        }
        //上記以外
        if (doneRbeOrderKind == null) {
            //固定値:"△△△"(通常注文)
            doneRbeOrderKind = String.format("%3s", StringUtil.EMPTY_STRING);
        }
        return doneRbeOrderKind;
    }
    
    /**
     *　API002のリクエスト項目：DONEトリガ発動ゾーンの値を取得
     *
     * @param dtoReq　A016リクエストDTO
     * @return doneTriggerZone　DONEトリガ発動ゾーンの設定値
     */
    private String getDoneTriggerZoneApi002(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String doneTriggerZone = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(dtoReq.getOrderKind());
        if (Objects.isNull(orderKindClass)) {
            //固定値:"△"(指定なし)
            return " ";
        }
        switch (orderKindClass) {
        case IFD:
            //執行方法 = 逆指値
            if (StringUtils.equals(dtoReq.getIfd2OrderExecuteMethodList(), ExecuteMethod.STOP.getId())) {
                String tradeCd = dtoReq.getTradeCd();
                // 取引種別 = 信用新規買
                if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_BUY.getId())) {
                    //固定値:"１"(以下)
                    doneTriggerZone = "1";
                // 取引種別 = 信用新規売
                } else if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_SELL.getId())) {
                    //固定値:"0"(以上)
                    doneTriggerZone = "0";
                }
                break;
            }
            break;
        case IFDOCO:
            String tradeCd = dtoReq.getTradeCd();
            // 取引種別 = 信用新規買
            if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_BUY.getId())) {
                //固定値:"１"(以下)
                doneTriggerZone = "1";
            // 取引種別 = 信用新規売
            } else if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_SELL.getId())) {
                //固定値:"0"(以上)
                doneTriggerZone = "0";
            }
            break;
        default:
            break;
        }
        //上記のいずれでもない
        if (doneTriggerZone == null) {
            //固定値:"△"(指定なし)
            doneTriggerZone = " ";
        }
        return doneTriggerZone;
    }
    
    /**
     *　API002のリクエスト項目：DONEトリガ値段の値を取得
     *
     * @param dtoReq　A016リクエストDTO
     * @return doneTriggerPrice　DONEトリガ値段の設定値
     */
    private String getDoneTriggerPriceApi002(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String doneTriggerPrice = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(dtoReq.getOrderKind());
        if (!Objects.isNull(orderKindClass)) {
            switch (orderKindClass) {
            case IFD:
                if (StringUtils.equals(dtoReq.getIfd2OrderExecuteMethodList(), ExecuteMethod.STOP.getId())) {
                    doneTriggerPrice = dtoReq.getIfd2DomesticStopOrderPrice();
                    break;
                }
                break;
            case IFDOCO:
                doneTriggerPrice = dtoReq.getOco2DomesticStopOrderPrice();
                break;
            default:
                break;
            }
        }
        //上記のいずれでもない
        if (doneTriggerPrice == null) {
            // 0埋め
            doneTriggerPrice = StringUtil.EMPTY_STRING;
        }
        // 0埋め10桁で返却する
        return String.format("%10s", doneTriggerPrice).replace(" ", "0");
    }
    
    /**
     *　API002のリクエスト項目：DONEOCO指値の値を取得
     *
     * @param dtoReq　A016リクエストDTO
     * @return doneOcoPrice　DONEOCO指値の設定値
     */
    private String getDoneOcoPriceApi002(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String doneOcoPrice = StringUtil.EMPTY_STRING;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            doneOcoPrice = dtoReq.getOco2DomesticLimitPrice();
        } else {
            //固定値:"△"
            doneOcoPrice = " ";
        }
        return String.format("%10s", doneOcoPrice).replace(" ", "0");
    }
    
    /**
     *　API002のリクエスト項目：DONEOCO指成区分の値を取得
     * @param dtoReq　A016リクエストDTO
     * @return doneOcoSasinariKbn　DONEOCO指成区分の設定値
     */
    private String getDoneOcoSasinariKbnApi002(IfaMarginNewOrderInputA016DtoRequest dtoReq) {
        
        String doneOcoSasinariKbn = null;
        //注文種別
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            doneOcoSasinariKbn = dtoReq.getOco2StopOrderMarketExecutionConditionList();
        } else {
            //固定値:"△"
            doneOcoSasinariKbn = " ";
        }
        return doneOcoSasinariKbn;
    }
    
    /**
     * SOR受注時板乗せ市場区分
     * @param market
     * @return
     */
    private String getSorLastMarket(String market) {
        
        return StringUtils.equals(SelectMarket.SOR.getId(), market) ? API001_REQUEST_MARKET_SOR_VALUE
                : String.format("%3s", StringUtil.EMPTY_STRING);
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
}
