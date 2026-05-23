package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.function.Predicate;

import com.sbibits.earth.extapi.ApiConnectionException;
import com.sbibits.earth.extapi.ApiIOException;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct033;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct033Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct033Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDistributionReceiveMethodChangeInputService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.AccountSumWebData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountPositionSumWebInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountPositionSumWebOutData;
import jp.co.sbisec.pcenter.dto.yanap.ReinvestEntryInData;
import jp.co.sbisec.pcenter.dto.yanap.ReinvestEntryOutData;
import jp.co.sbisec.pcenter.dto.yanap.SimReinvestEntryDataI;

/**
 * 画面ID：SUB0202_010201-02_1
 * 画面名：分配金受取方法変更入力
 * 2023/11/28 新規作成
 *
 * @author SCSK 江口
 */
@Component(value = "cmpIfaDistributionReceiveMethodChangeInputService")
public class IfaDistributionReceiveMethodChangeInputServiceImpL
            implements IfaDistributionReceiveMethodChangeInputService {
        
    @Autowired
    private ApiWrapper apiWrapper;

    @Autowired
    private Fct001 fct001;

    @Autowired
    private Fct033 fct033;

    /** 顧客に対する権限なしエラー */
    private static final String ERRORS_BUTEN_ACCOUNT_NOT_EXIST = "errors.butenAccountNotExist";
    
    /** 該当の投資信託銘柄が存在しないエラー */
    private static final String ERRORS_DMT_SECURITIES_NOT_EXIST = "errors.dmt.securities.notExist";

    /** 分配金受取方法変更エラー */
    private static final String ERRORS_DMT_DISTRIBUTIONS_RECEIPT_FAILED = "errors.dmt.distributionsReceipt.failed";

    /** 翌営業日向け申し込み注意 */
    private static final String WARNINGS_DMT_DISTRIBUTIONS_RECEIPT_DELAY = "warnings.dmt.distributionsReceipt.delay";

    /** 対象の顧客に対する権限.権限あり */
    private static final String AUTH_SUCCESS_VALUE = "1";

    /** ジュニアISA契約区分.閉鎖済 */
    private static final String JR_ISA_CONTRACT_TYPE_CLOSED = "9";

    /** ジュニアISA契約区分.未契約  */
    private static final String JR_ISA_CONTRACT_TYPE_NO_CONTRACT = " ";

    /** 預り区分.特定(特例)*/
    private static final String DEPOSIT_CATEGORY_SPECIFIC_SPECIAL_CASE = "5";

    /** 預り区分.一般(特例)*/
    private static final String DEPOSIT_CATEGORY_GENERAL_SPECIAL_CASE = "6";

    /** 預り区分.jrNISA*/
    private static final String DEPOSIT_CATEGORY_JR_NISA = "7";

    /** 預り区分.継続管理勘定*/
    private static final String DEPOSIT_CATEGORY_CONTINUOUS_MANAGEMENT = "J";

    /** 取得口座区分.通常口座およびjrNISA口座の第一口座 */
    private static final String ACQUIRE_ACCOUNT_CLASSIFICATION_GENERIC_JR_NISA_FIREST = " ";

    /** 取得口座区分.jrNISA口座の第一、第二口座両方 */
    private static final String ACQUIRE_ACCOUNT_CLASSIFICATION_JR_NISA_FIRST_JR_NISA_SECOND = "2";

    /** 商品区分.国内投信（汎用累積） */
    private static final String COMMODITY_CLASSIFICATION_DOMESTIC_MUTUAL_FUND = "Y";

    /** 残高.0 */
    private static final int POSITION_ZERO = 0;

    /** 再投資停止情報.分配金受取 */
    private static final String REINVEST_ENTRY_RECEIVE = "1";

    /** 再投資停止情報.分配金再投資 */
    private static final String REINVEST_ENTRY_REINVEST = "2";

    /** 再投資区分.再投資/受取（累積口） */
    private static final String REINVEST_CLASSIFICATION_REINVEST = "2";

    /** 処理区分.登録 */
    private static final String PROCESS_CLASSIFICATINO_REGISTER = "E";

    /** 注文受付経路区分.コールセンター */
    private static final String CALLCENTER_CLASSIFICATION_CALLCENTER = "1";

    /** 商品タイプ.国内投信 */
    private static final String SECURITY_TYPE_DOMESTIC_MUTUAL_FUND = "T0";

    /** リクエスト区分.全ての預り */
    private static final String REQUEST_CLASSIFICATION_ALL = " ";

    /** 営業日チェックフラグ.非営業日 */
    private static final String BUSINESS_DAY_CHECK_FLAG_NON_BUSINESS_DAY = "0";

    /** 分配金受取方法.分配金受取 */
    private static final String DISTRIBUTION_RECEIVE_CLASSIFICATION_RECEIVE = "受取";

    /** 分配金受取方法.分配金再投資 */
    private static final String DISTRIBUTION_RECEIVE_CLASSIFICATION_REINVEST = "再投資";

    /** API002.input.totalNumber */
    private static final String API002_INPUT_TOTAL_NUMBER = "001";

    /** 翌営業日向けメッセージ表示開始時刻(時) */
    private static final int WARNINGS_DMT_DISTRIBUTIONS_RECEIPT_DELAY_BEGIN_HOUR = 19;

    /** ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDistributionReceiveMethodChangeInputServiceImpL.class);


    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDistributionReceiveMethodChangeInputA001RequestDto
     * Dto レスポンス：IfaDistributionReceiveMethodChangeInputA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 受取方法変更入力画面の初期化に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaDistributionReceiveMethodChangeInputA001ResponseDto> initializeA001(
            IfaDistributionReceiveMethodChangeInputA001RequestDto dtoReq
    ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDistributionReceiveMethodChangeInputServiceImplL.initializeA001");
        }

        /* ====================================================================== */
        /* 利用者の口座に対する権限有無を取得(FCT001を呼び出し)                       */
        /* ====================================================================== */
        
        // FCT001リクエストDTOの作成
        String butenCode = IfaCommonUtil.getCustomerCommon().getButenCode();
        String accountNumber = IfaCommonUtil.getCustomerCommon().getAccountNumber();

        InputFct001Dto fct001InputDto = new InputFct001Dto();
        fct001InputDto.setButenCode(butenCode);
        fct001InputDto.setAccountNumber(accountNumber);

        // FCT001リクエストの実行
        OutputFct001Dto fct001OutputDto = fct001.doCheck(fct001InputDto);
        
        // 利用者の口座に対する権限を持っていない場合エラーレスポンスを返す
        if (
                    fct001OutputDto == null
                    || !fct001OutputDto.getTargetCustomerRefAuthFlag().equals(AUTH_SUCCESS_VALUE)
        ) {

            DataList<IfaDistributionReceiveMethodChangeInputA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                    IfaCommonUtil.getMessage(
                        ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                        new String[] { butenCode, accountNumber }
                    )
            );

            return dtoRes;

        }

        /* ====================================================================== */
        /* 預かり残高一覧(サマリー)を取得(API001を呼び出し)                          */
        /* ====================================================================== */
        // API001のリクエストDTOを作成
        QueryAccountPositionSumWebInData api001In = new QueryAccountPositionSumWebInData();
        api001In.setButenCd(IfaCommonUtil.getCustomerCommon().getButenCode());
        api001In.setKozaNo(IfaCommonUtil.getCustomerCommon().getAccountNumber());
        api001In.setSecType(SECURITY_TYPE_DOMESTIC_MUTUAL_FUND);
        api001In.setRequestType(REQUEST_CLASSIFICATION_ALL);

        String acquireAccountClassification = "";
        String jrIsaContractType = IfaCommonUtil.getCustomerCommon().getJrIsaContractType();
        if (
                jrIsaContractType.equals(JR_ISA_CONTRACT_TYPE_NO_CONTRACT) // 未契約
                || jrIsaContractType.equals(JR_ISA_CONTRACT_TYPE_CLOSED) // 閉鎖済
        ) {
            acquireAccountClassification = ACQUIRE_ACCOUNT_CLASSIFICATION_GENERIC_JR_NISA_FIREST;

        } else { // 契約済
            acquireAccountClassification = ACQUIRE_ACCOUNT_CLASSIFICATION_JR_NISA_FIRST_JR_NISA_SECOND;

        }
        api001In.setAccountGetKbn(acquireAccountClassification);

        // 明細の検索条件を指定する
        Predicate<AccountSumWebData> api001Predicate = (data) -> {
            return data.getHitokuteiKbn().equals(dtoReq.getDepositType())                    // 預かり区分
                && data.getSecId().equals(COMMODITY_CLASSIFICATION_DOMESTIC_MUTUAL_FUND)     // 商品区分=国内投信(汎用累投)
                && (data.getSerNo() + data.getSubCode1() + data.getSubCode2())
                    .equals(dtoReq.getTimes() + dtoReq.getIssue1() + dtoReq.getIssue2())     // 回数+号1+号2
                && Long.parseLong(data.getPosition().strip()) != POSITION_ZERO             // 残高数量
                && (
                    data.getReinvest().equals(REINVEST_ENTRY_RECEIVE)                        // 再投資停止情報=1
                    || (
                        data.getReinvest().equals(REINVEST_ENTRY_REINVEST)                   // 再投資停止情報=2
                        && data.getStKbn().equals(REINVEST_CLASSIFICATION_REINVEST)          // 再投資区分=再投資/受取(累投口)
                    )
                );
        };

        // API001を呼び出す
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        QueryAccountPositionSumWebOutData api001OutData = new QueryAccountPositionSumWebOutData();
        AccountSumWebData api001Out = null;
        api001OutData = apiWrapper.queryAccountPositionSumWebWithCondition(api001In, api001Predicate);
        if (api001OutData.getAccountSumWebData().size() > 0) {
            api001Out = api001OutData.getAccountSumWebData().get(0);
        }
        apiErrorUtil.checkApiResponse(api001OutData.getShubetu(), api001OutData.getCode(), api001OutData.getMessage());
        
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        // API001の結果が0件の場合、または、条件を満たす明細が存在しない場合
        // 該当の投資信託銘柄が存在しないエラーを返す。
        if (api001Out == null) {
            DataList<IfaDistributionReceiveMethodChangeInputA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_DMT_SECURITIES_NOT_EXIST,
                    IfaCommonUtil.getMessage(ERRORS_DMT_SECURITIES_NOT_EXIST)
            );

            return dtoRes;
        }

        /* ====================================================================== */
        /* A001 レスポンスDTOを作成                                                */
        /* ====================================================================== */        
        IfaDistributionReceiveMethodChangeInputA001ResponseDto innerData
                    = new IfaDistributionReceiveMethodChangeInputA001ResponseDto();

        innerData.setFundName(api001Out.getSecName().strip()); // ファンド名
        innerData.setUnitVolume(api001Out.getPosition().strip()); // 保有口数
        innerData.setSellingVolume(String.valueOf(Long.parseLong(api001Out.getOrderedQuantity().strip()))); // 売却注文中
        
        innerData.setTimes(dtoReq.getTimes()); // 回数
        innerData.setIssue1(dtoReq.getIssue1()); // 号1
        innerData.setIssue2(dtoReq.getIssue2()); // 号2
        innerData.setDepositType(dtoReq.getDepositType()); // 預り区分

        if (api001Out.getReinvest().equals(REINVEST_ENTRY_RECEIVE)) {
            // 再投資停止情報=受取 の場合
            innerData.setMethod(DISTRIBUTION_RECEIVE_CLASSIFICATION_RECEIVE); // 分配金受取方法
            innerData.setDistributionReceiveClassification(REINVEST_ENTRY_RECEIVE); // 分配金受取区分

        } else if (api001Out.getReinvest().equals(REINVEST_ENTRY_REINVEST)) {
            // 再投資停止情報=再投資 の場合
            innerData.setMethod(DISTRIBUTION_RECEIVE_CLASSIFICATION_REINVEST); // 分配金受取方法
            innerData.setDistributionReceiveClassification(REINVEST_ENTRY_REINVEST); // 分配金受取区分

        }

        /* ====================================================================== */
        /* 営業日の19:00～23:59:59、または非営業日の場合                            */
        /* → 取引実行が翌営業日となるワーニング付きのレスポンスを返す                */
        /*                                                                        */
        /* それ以外                                                                */
        /* ワーニングなし(SUCCESS)のレスポンスを返す                               */
        /* ====================================================================== */
        // システム日時を取得する。(Date型:FCT033用、Calendar型：19:00～23:59判定用)
        Date requestedTime = DateUtil.parse(IfaCommonUtil.getFormattedRequestedTime(), DateUtil.SEPARATED_YYYYMMDD_HHMMSS);
        Calendar requestedTimeCalendar = Calendar.getInstance();
        requestedTimeCalendar.setTime(requestedTime);
        TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
        requestedTimeCalendar.setTimeZone(tz);

        // 営業日か否かを判定する。(FCT033を呼び出し)
        InputFct033Dto fct033InputDto = new InputFct033Dto();
        fct033InputDto.setDate(requestedTime);
        OutputFct033Dto fct033OutputDto = fct033.doCheck(fct033InputDto);

        // 取引実行が翌営業日向けとなる場合のメッセージをレスポンスに設定する
        if (
                fct033OutputDto.getBusinessDayCheckFlag().equals(BUSINESS_DAY_CHECK_FLAG_NON_BUSINESS_DAY) // 非営業日
                || WARNINGS_DMT_DISTRIBUTIONS_RECEIPT_DELAY_BEGIN_HOUR <= requestedTimeCalendar.get(Calendar.HOUR_OF_DAY) // 19:00:00～23:59:59
        ) {
            innerData.setInfoMsg(IfaCommonUtil.getMessage(WARNINGS_DMT_DISTRIBUTIONS_RECEIPT_DELAY));
        }

        // レスポンスを返す       
        DataList<IfaDistributionReceiveMethodChangeInputA001ResponseDto> dtoRes = apiErrorUtil.createDataList(Arrays.asList(innerData), null);
        
        return dtoRes;
    }

    /**
     * アクションID：A002
     * アクション名：登録・変更
     * Dto リクエスト：IfaDistributionReceiveMethodChangeInputA002RequestDto
     * Dto レスポンス：IfaDistributionReceiveMethodChangeInputA002ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 登録・変更の成否
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaDistributionReceiveMethodChangeInputA002ResponseDto> registerChangeA002(
            IfaDistributionReceiveMethodChangeInputA002RequestDto dtoReq
    ) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDistributionReceiveMethodChangeInputServiceImplL.registerChangeA002");
        }

        // API002(NRI_ReinvestEntry)のリクエストを作成する。
        ReinvestEntryInData api002In = new ReinvestEntryInData();
        api002In.setButenCd(IfaCommonUtil.getCustomerCommon().getButenCode());
        api002In.setProcessId(PROCESS_CLASSIFICATINO_REGISTER);
        api002In.setCallcenterKbn(CALLCENTER_CLASSIFICATION_CALLCENTER);
        api002In.setTotalNumber(API002_INPUT_TOTAL_NUMBER);

        String kozaNo = "";
        if (
                dtoReq.getDepositType().equals(DEPOSIT_CATEGORY_SPECIFIC_SPECIAL_CASE)      // 特定(特例)
                || dtoReq.getDepositType().equals(DEPOSIT_CATEGORY_GENERAL_SPECIAL_CASE)    // 一般(特例)
                || dtoReq.getDepositType().equals(DEPOSIT_CATEGORY_JR_NISA)                 // jrNisa
                || dtoReq.getDepositType().equals(DEPOSIT_CATEGORY_CONTINUOUS_MANAGEMENT)   // 継続管理勘定
        ) { 
            // 口座番号
            kozaNo = IfaCommonUtil.getCustomerCommon().getJrNisaAccountNumber();
        } else {
            // ジュニアNISA口座番号
            kozaNo = IfaCommonUtil.getCustomerCommon().getAccountNumber();
        }
        kozaNo = StringUtils.leftPad(kozaNo, 7, '0');
        api002In.setKozaNo(kozaNo);

        SimReinvestEntryDataI simReinvestEntryData = new SimReinvestEntryDataI();
        simReinvestEntryData.setKaisu(dtoReq.getTimes());
        simReinvestEntryData.setGou(dtoReq.getIssue1() + dtoReq.getIssue2());
        simReinvestEntryData.setReinvest(dtoReq.getAfterChangeDistributionReceiveMethodList());
        List<SimReinvestEntryDataI> simReinvestEntryDataList = Arrays.asList(simReinvestEntryData);
        api002In.setSimReinvestEntryDataI(simReinvestEntryDataList);
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        ReinvestEntryOutData api002Out = new ReinvestEntryOutData();
        // API002リクエストの実行
        try {
            api002Out = apiWrapper.reinvestEntry(api002In);

        } catch (ApiIOException | ApiConnectionException e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(
                        "{}, {}, {}",
                        "IfaDistributionReceiveMethodChangeInputServiceImpL",
                        "registerChangeA002",
                        e
                );
            }

            // API呼び出しに失敗した場合、エラーのレスポンスを返す
            DataList<IfaDistributionReceiveMethodChangeInputA002ResponseDto> dtoRes  = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_DMT_DISTRIBUTIONS_RECEIPT_FAILED,
                    IfaCommonUtil.getMessage(ERRORS_DMT_DISTRIBUTIONS_RECEIPT_FAILED)
            );

            return dtoRes;
        }
        apiErrorUtil.checkApiResponse(api002Out.getShubetu(), api002Out.getCode(), api002Out.getMessage());
        if (apiErrorUtil.isFatal()) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_DMT_DISTRIBUTIONS_RECEIPT_FAILED,
                    IfaCommonUtil.getMessage(ERRORS_DMT_DISTRIBUTIONS_RECEIPT_FAILED));
        } else {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }

    }

}
