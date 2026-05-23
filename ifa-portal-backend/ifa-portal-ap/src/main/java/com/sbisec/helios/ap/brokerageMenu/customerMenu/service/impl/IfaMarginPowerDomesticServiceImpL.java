package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerDomesticA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerDomesticA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginPowerDomesticA001DtoResponseMarginCallinfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMarginPowerDomesticService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import jp.co.sbisec.pcenter.dto.yanap.DateT;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityOutData;
import jp.co.sbisec.pcenter.dto.yanap.SettlementDateT;

/**
 * 画面ID：SUB0202_010302-01 画面名：信用余力（国内） アクションID：A001 アクション名：初期化
 *
 * @author 島崎 聡士 2023/08/14 新規作成
 */
@Component(value = "cmpIfaMarginPowerDomesticService")
public class IfaMarginPowerDomesticServiceImpL implements IfaMarginPowerDomesticService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginPowerDomesticServiceImpL.class);

    @Autowired
    private Fct001 fct001;

    @Autowired
    private ApiWrapper apiWrapper;

    /** 権限チェックエラー   */
    public static final String ERRORS_BUTEN_ACCOUNT_NOTEXISTS = "errors.butenAccountNotExist";

    /** 権限チェックエラー値 「権限なし」 */
    public static final String TARGET_CUSTOMER_REF_AUTH_FLAG = "0";

    /** 信用口座開設状況チェック値 「未開設」 */
    public static final String DOMESTIC_MARGIN_ACCOUNT_TYPE_KIND_ACCOUNT = " ";

    /** 信用口座未開設エラー   */
    public static final String ERRORS_DMS_DOMESTIC_MARGIN_ACCOUNT_NOTOPEN = "errors.dms.domesticMarginAccount.notOpen";

    /** 余力項目セット区分='Ａ'：Ｔ～Ｔ＋５まで全てセット */
    public static final String REQUEST_KBN1 = "A";

    /** 追証項目セット区分='Ａ'：Ｔ～Ｔ－４まで全てセット */
    public static final String REQUEST_KBN2 = "A";

    /** 概算 */
    public static final String APPROXIMATION = "概算";

    /** 確定 */
    public static final String DECISION = "確定";

    /** 計上の場合 */
    public static final String APPROPRIATION = "計上の場合";

    /** 半角スペース（設計上"△"と表記されている項目） */
    public static final String HALF_WIDTH_SPACE = " ";

    /** ハイフン */
    public static final String HYPHEN = "-";
    
    /** 信用口座区分:信用口座 */
    public static final String DOMESTIC_MARGIN_ACCOUNT_TYPE = "1";

    /**
     * 追証ステータス
     * 
     *
     * @author 島崎 聡士 2023/08/14 新規作成
     */
    public enum MarginCallStatus {
        BEFORE_MARGIN_CALL_CANCELLATION_DEADLINE("7", "追証解消期限前"),
        AFTER_THE_UNRESOLVED_MARGIN_CALL_CANCELLATION_DEADLINE("3", "追証解消期限後（未解消）"), 
        MARGIN_FORCED_SALE_CONFIRMED("9", "追証強制売却確定"),
        AFTER_EXECUTION_OF_FORCED_MARGIN_SALE("5", "追証強制売却実行後"),
        AFTER_THE_CANCELED_MARGIN_CALL_CANCELLATION_DEADLINE("4", "追証解消期限後（解消済）"),
        CANCELLATION_OF_COMPULSORY_MARGIN_CALL("8", "追証解消（強制）"),
        NOT_A_MARGIN_CALL(" ", "-");
        
        MarginCallStatus(String id, String name) {
            
            this.id = id;
            this.name = name;
        }
        
        private String id;
        
        private String name;
        
        public String getId() {
            
            return id;
        }
        
        private String getName() {
            
            return name;
        }
        
        /**
         * idを取得する。
         *
         * @param id {@code String }
         * @return id Returns {@code null}
         */
        public static MarginCallStatus getById(String id) {
            
            if (ObjectUtils.isEmpty(id)) {
                return null;
            }
            
            MarginCallStatus[] enums = values();
            
            for (int i = 0; i < enums.length; i++) {
                if (enums[i].getId().equals(id)) {
                    return enums[i];
                }
            }
            
            return null;
        }
    }

    /**
     * Dto リクエスト：IfaMarginPowerDomesticA001DtoRequest 
     * Dto レスポンス：IfaMarginPowerDomesticA001DtoResponse model 
     * リクエスト：RequestModel model
     * レスポンス：ResponseModel
     *
     * @param dtoReq Dto リクエスト
     * @return Dto レスポンス
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaMarginPowerDomesticA001DtoResponse> initializeA001(IfaMarginPowerDomesticA001DtoRequest dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginPowerDomesticServiceImplL.initializeA001");
        }
        // ①利用者の口座に対する権限チェックを行う。
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        // 顧客共通情報の取得（項目仮実装中）
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();
        inputFct001Dto.setButenCode(butenCode);
        inputFct001Dto.setAccountNumber(accountNumber);
        // FCT001の実行
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        if (TARGET_CUSTOMER_REF_AUTH_FLAG.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            // 業務エラーメッセージの取得
            String message = IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                    new String[] { butenCode, accountNumber });
            // エラーレベルとメッセージの設定
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ErrorLevel.FATAL.name(), message);
        }
        
        // ②顧客共通情報の「信用口座区分(国内)」より、信用口座開設状況チェックを行う。
        // 顧客共通情報の取得（項目仮実装中）
        String domesticMarginAccountType = customerCommon.getDomesticMarginAccountType();
        if (DOMESTIC_MARGIN_ACCOUNT_TYPE_KIND_ACCOUNT.equals(domesticMarginAccountType)) {
            // 業務エラーメッセージの取得
            String message = IfaCommonUtil.getMessage(ERRORS_DMS_DOMESTIC_MARGIN_ACCOUNT_NOTOPEN);
            // エラーレベルとメッセージの設定
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ErrorLevel.FATAL.name(), message);
        }
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // ③信用建玉余力情報を取得する
        List<IfaMarginPowerDomesticA001DtoResponse> responseDto = callApi001(dtoReq.getButenCode(),
                dtoReq.getAccountNumber(), apiErrorUtil);
        return apiErrorUtil.createDataList(responseDto, null);
    }

    /**
     * 信用建玉余力リクエスト:NRI_QueryMgEstimateCapabilityの実行メソッド
     * 信用建玉余力リクエストの実施結果を編集して返却する
     *
     * @param butenCd 部店コード
     * @param kozaNo 口座番号
     * @return Dto レスポンス
     */
    private List<IfaMarginPowerDomesticA001DtoResponse> callApi001(String butenCd, String kozaNo,
            ApiErrorUtil apiErrorUtil) throws Exception {
        
        // 信用建玉余力リクエスト:入力値
        QueryMgEstimateCapabilityIn input = new QueryMgEstimateCapabilityIn();
        QueryMgEstimateCapabilityInData inData = new QueryMgEstimateCapabilityInData();
        QueryMgEstimateCapabilityOutData apiResponse = new QueryMgEstimateCapabilityOutData();

        inData.setButenCd(butenCd);
        inData.setKozaNo(createApiRequestAccountNo(kozaNo));
        inData.setRequestKbn1(REQUEST_KBN1);
        inData.setRequestKbn2(REQUEST_KBN2);

        input.setIndata(inData);
        // 信用建玉余力リクエスト:NRI_QueryMgEstimateCapability
        apiResponse = apiWrapper.queryMgEstimateCapability(input);
        apiErrorUtil.checkApiResponse(apiResponse.getShubetu(), apiResponse.getCode(), apiResponse.getMessage());
        if (apiErrorUtil.isFatal()) {
            return new ArrayList<>();
        }
        
        // サービスへのレスポンス用データ
        IfaMarginPowerDomesticA001DtoResponse responseDto = new IfaMarginPowerDomesticA001DtoResponse();
        
        // 設計の表記とリストを制御する際のindexとのマッピングはQA433の回答により追証情報（T-４）～(T+0)と追証情報リストの場合は以下となります。
        // 追証情報(T-4) ＝ 追証情報リスト(0)
        // 追証情報(T-3) ＝ 追証情報リスト(1)
        // 追証情報(T-2) ＝ 追証情報リスト(2)
        // 追証情報(T-1) ＝ 追証情報リスト(3)
        // 追証情報(T+0) ＝ 追証情報リスト(4)
        List<DateT> datetList = apiResponse.getDateT();
        List<IfaMarginPowerDomesticA001DtoResponseMarginCallinfo> marginCallinfoList = initMarginCallinfoList();
        
        // 共通 API001.の預り金自動スイープ申込区分
        String autoSweepKbn = apiResponse.getAutoSweepKbn();
        
        // ▼自動スイープ対象フラグの設定
        editAutoSweepTargetFlag(responseDto, autoSweepKbn);
        
        // 共通 API001.追証情報（T+0）.追証入金予定日
        String deficitChargeDate0 = datetList.get(4).getDeficitChargeDate();
        
        // ▼解消期限/入金・入庫計上日(確定・概算)
        editCancellationDeadlineRecordingDateDefiniteApproximate(apiResponse, datetList, marginCallinfoList,
                deficitChargeDate0);
        
        // ▼共通 最も古い基準日の取得
        String oldestDateTm = getOldestDateTm(datetList);
        
        // ▼当初追証額（T-４）～(T+0)
        editInitialMarginAmount(apiResponse, datetList, marginCallinfoList, oldestDateTm);
        
        // ▼追証発生日（T-４）～(T+0)
        editMarginCallinfoMarginCallDate(apiResponse, datetList, marginCallinfoList, oldestDateTm);
        
        // 受渡日（T+0）～受渡日（T+5） と 受渡日（T+0）～受渡日（T+5）リストのindexとのマッピングは以下となります。 
        // 受渡日(T+0) ＝ 受渡日（T+0）～受渡日（T+5）リスト(0)
        // 受渡日(T+1) ＝ 受渡日（T+0）～受渡日（T+5）リスト(1)
        // 受渡日(T+2) ＝ 受渡日（T+0）～受渡日（T+5）リスト(2)
        // 受渡日(T+3) ＝ 受渡日（T+0）～受渡日（T+5）リスト(3)
        // 受渡日(T+4) ＝ 受渡日（T+0）～受渡日（T+5）リスト(4)
        // 受渡日(T+5) ＝ 受渡日（T+0）～受渡日（T+5）リスト(5)
        List<SettlementDateT> settlementdatetList = apiResponse.getSettlementDateT();
        
        // ▼入金額（T-４）～(T+0)
        editDepositAmount(datetList, marginCallinfoList, settlementdatetList, autoSweepKbn);
        
        // 共通 追証ステータスの取得
        String deficitStatus = apiResponse.getDeficitStatus();
        
        // ▼追証ステータス
        editMarginStatus(responseDto, deficitStatus);
        
        // ▼追加保証金未解消に伴う建玉強制返済予定日時
        editPlannedDateForcedRedemptionOpenInterest(responseDto, datetList);
        
        // ▼追証入金予定日
        editMarginDepositScheduleDate(responseDto, deficitChargeDate0);
        
        // ▼追加保証金を全て解消するための必要額
        editAmountrequiredtocancelallAdditionalsecuritydeposits(responseDto, datetList, settlementdatetList,
                autoSweepKbn, deficitStatus);
        
        // 共通 最も古い追証発生日の取得
        String oldestDeficitDate = getOldestDeficitDate(datetList);
        
        // ▼追証基準維持率
        editMarginstandardmaintenanceRate(responseDto, datetList, oldestDeficitDate);
        
        // ▼追証金額
        editMarginAmount(responseDto, datetList, oldestDeficitDate);
        
        // ▼追証発生日
        editMarginCallDate(responseDto, datetList, oldestDeficitDate);
        
        // ▼追証解消期限
        editMarginCancellationDeadline(responseDto, datetList);
        
        // ▼建玉強制返済執行期限
        editDeadlineforforcedrepaymentOfopeninterest(responseDto, datetList);
        
        // ▼建玉総限度額
        editOpenInterestLimit(apiResponse, responseDto);
        
        // ▼前日委託保証金率(T+0)
        editConsignmentDeposit(responseDto, datetList);

        // ▼新規建保証金率（信用建余力の計算に適用）
        editNewOpenInterest(apiResponse, responseDto);
        
        // ▼現金保証金率（新規建に必要な保証金現金の計算に適用）
        editChache(apiResponse, responseDto);

        // ▼現物買付保証金率（現物の買付余力の計算に適用）
        editInKindPurchase(apiResponse, responseDto);

        // ▼出金・振替保証金率（出金・振替可能額の計算に適用）
        editWithdrawTransfer(apiResponse, responseDto);
        
        // 受渡日（T+0）～受渡日（T+5）リスト
        List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList =
                initDeliveryDateToFiveDaysLaterList();
        
        // ▼委託保証金率(受渡日（T+0）～受渡日（T+5）)
        editMarginDepositRate9(apiResponse, settlementdatetList, deliveryDateToFiveDaysLaterList, autoSweepKbn);
        
        // ▼参考委託保証金率(受渡日（T+0）～受渡日（T+5）)
        editDomesticReferenceMarginDeposit(apiResponse, settlementdatetList, deliveryDateToFiveDaysLaterList, autoSweepKbn);
        
        // ▼余力詳細_委託保証金率(受渡日（T+0）～受渡日（T+5）)
        editReservePowerDetailDeliveryDateToFiveDaysLaterMarginDepositRate9(settlementdatetList,
                deliveryDateToFiveDaysLaterList);
        
        // ▼信用建余力(受渡日（T+0）～受渡日（T+5）)
        editCreditCapacity(settlementdatetList, deliveryDateToFiveDaysLaterList);
        
        // ▼保証金現金（余力WK）（下段）(受渡日（T+0）～受渡日（T+5）)
        editRemainingCapacityWkUnder(settlementdatetList, deliveryDateToFiveDaysLaterList, autoSweepKbn);
        
        // ▼SSNB精算前 保証金現金 (余力WK)（上段）(受渡日（T+0）～受渡日（T+5）)
        editMarginDepositBeforeSsnbCalculateTop(settlementdatetList, deliveryDateToFiveDaysLaterList, autoSweepKbn);
        
        // ▼SSNB精算前 保証金現金 (余力WK)（下段）(受渡日（T+0）～受渡日（T+5）)
        editMarginDepositBeforeSsnbCalculateUnder(settlementdatetList, deliveryDateToFiveDaysLaterList, autoSweepKbn);
        
        // ▼保証金合計(受渡日（T+0）～受渡日（T+5）)
        editTotalDeposit(settlementdatetList, deliveryDateToFiveDaysLaterList, autoSweepKbn);
        
        // ▼評価損(受渡日（T+0）～受渡日（T+5）)
        editValuationLoss(settlementdatetList, deliveryDateToFiveDaysLaterList);
        
        // ▼決済損益(受渡日（T+0）～受渡日（T+5）)
        editSettlement(settlementdatetList, deliveryDateToFiveDaysLaterList);
        
        // ▼実質保証金(受渡日（T+0）～受渡日（T+5）)
        editYenActualDeposit(settlementdatetList, deliveryDateToFiveDaysLaterList, autoSweepKbn);
        
        // ▼実保+SBIハイブリッド預金(受渡日（T+0）～受渡日（T+5）)
        editRealInsuranceAndHybridDepositBalance(settlementdatetList, deliveryDateToFiveDaysLaterList, autoSweepKbn);
        
        // ▼当社保証金+SBIハイブリッド預金(現金部分のみ)(受渡日（T+0）～受渡日（T+5）
        editDepositAndHybridDepositBalance(settlementdatetList, deliveryDateToFiveDaysLaterList, autoSweepKbn);
        
        // ▼注文中建玉金額(受渡日（T+0）～受渡日（T+5）)
        editOrderOpenInterest(settlementdatetList, deliveryDateToFiveDaysLaterList);
        
        // ▼未決済建玉金額(受渡日（T+0）～受渡日（T+5）)
        editUnsetteledOpenInterest(settlementdatetList, deliveryDateToFiveDaysLaterList);
        
        // ▼決済済建玉金額(受渡日（T+0）～受渡日（T+5）)
        editCurrentDeliverlyOpenInterest(settlementdatetList, deliveryDateToFiveDaysLaterList);
        
        // ▼現引現渡建玉金額(受渡日（T+0）～受渡日（T+5）)
        editSetteledOpenInterest(settlementdatetList, deliveryDateToFiveDaysLaterList);
        
        // ▼追証情報リスト（算出値以外の設定）
        editMarginCallinfoList(datetList, marginCallinfoList, settlementdatetList);
        
        // ▼受渡日（T+0）～受渡日（T+5）リスト（算出値以外の設定）
        editDeliveryDateToFiveDaysLaterList(settlementdatetList, deliveryDateToFiveDaysLaterList);
        
        // ▼その他
        editResponseDto(apiResponse, responseDto, settlementdatetList);
        
        // レスポンスをセット
        responseDto.setMarginCallinfoList(marginCallinfoList);
        responseDto.setDeliveryDateToFiveDaysLaterList(deliveryDateToFiveDaysLaterList);
        List<IfaMarginPowerDomesticA001DtoResponse> responseDtoList = new ArrayList<>();
        responseDtoList.add(responseDto);
        
        return responseDtoList;
    }

    /**
     * 配列の先頭から順に文字列を数値に変換し加算する。<br>
     * 計算結果を文字列にして返却する。
     *
     * @param strArray 数文字列群
     * @return 計算結果
     */
    private String stringAddition(String[] strArray) {
        long addVal = Stream.of(strArray).mapToLong(this::minusNumericalConversion).sum();
        return String.valueOf(addVal);
    }

    /**
     * 配列の先頭から順に文字列を数値に変換し、減算する。<br>
     * 計算結果を文字列にして返却する。
     *
     * @param strArray 数文字列群
     * @return 計算結果
     */
    private String stringSubtraction(String[] strArray) {
        long addVal = Stream.of(strArray).mapToLong(this::minusNumericalConversion)
                .reduce((acum, val) -> {
                    return acum - val;
                }).orElse(0);
        return String.valueOf(addVal);
    }

    /**
     * API.追証情報リストの中で最も古い基準日を取得する。
     *
     * @param datetList API.追証情報リスト
     * @return 最も古い基準日
     */
    private String getOldestDateTm(List<DateT> datetList) {
        return datetList.stream()
                .filter(d -> !isNullorEmpty(d.getDateTM()))
                .min(Comparator.comparing(DateT::getDateTM)).map(DateT::getDateTM).orElse(null);
    }

    /**
     * API.追証情報リストの中で最も古い追証発生日を取得する。
     *
     * @param datetList  API.追証情報リスト
     * @return 最も古い追証発生日
     */
    private String getOldestDeficitDate(List<DateT> datetList) {
        return datetList.stream()
                .filter(d -> !isNullorEmpty(d.getDeficitDate()))
                .min(Comparator.comparing(DateT::getDeficitDate)).map(d-> d.getDeficitDate()).orElse(null);
    }

    /**
     * 決済済建玉金額(受渡日（T+0）～受渡日（T+5）)の設定
     *
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     */
    private void editCurrentDeliverlyOpenInterest(List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList) {
        
        for (int i = 0; i <= 5; i++) {
            // 日計り決済建玉金額(買決済)
            String mgDayTradeAmntBuy = settlementdatetList.get(i).getMgDayTradeAmntBuy();
            // 日計り決済建玉金額(売決済)
            String mgDayTradeAmntSell = settlementdatetList.get(i).getMgDayTradeAmntSell();
            // 現引現渡建玉金額(受渡日（T+0）～受渡日（T+5）)
            String[] strArray = { mgDayTradeAmntBuy, mgDayTradeAmntSell };
            String currentDeliverlyOpenInterest = stringAddition(strArray);
            deliveryDateToFiveDaysLaterList.get(i).setCurrentDeliverlyOpenInterest(currentDeliverlyOpenInterest);
        }
    }

    /**
     * 現引現渡建玉金額(受渡日（T+0）～受渡日（T+5）)の設定
     *
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     */
    private void editSetteledOpenInterest(List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList) {
        
        for (int i = 0; i <= 5; i++) {
            // 約定済未決済弁済買建玉合計額
            String buyExUnstPayBgSum = settlementdatetList.get(i).getBuyExUnstPayBgSum();
            // 約定済未決済弁済売建玉合計額
            String sellExUnstPayBgSum = settlementdatetList.get(i).getSellExUnstPayBgSum();
            // 決済済建玉金額(受渡日（T+0）～受渡日（T+5）)
            String[] strArray = { buyExUnstPayBgSum, sellExUnstPayBgSum };
            String setteledOpenInterest = stringAddition(strArray);
            deliveryDateToFiveDaysLaterList.get(i).setSetteledOpenInterest(setteledOpenInterest);
        }
    }

    /**
     * 未決済建玉金額(受渡日（T+0）～受渡日（T+5）)の設定
     *
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     */
    private void editUnsetteledOpenInterest(List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList) {
        
        for (int i = 0; i <= 5; i++) {
            // 買建玉総額
            String buyUnclosedAmount = settlementdatetList.get(i).getBuyUnclosedAmount();
            // 売建玉総額
            String sellUnclosedAmount = settlementdatetList.get(i).getSellUnclosedAmount();
            // 未決済建玉金額(受渡日（T+0）～受渡日（T+5）)
            String[] strArray = { buyUnclosedAmount, sellUnclosedAmount };
            String unsetteledOpenInterest = stringAddition(strArray);
            deliveryDateToFiveDaysLaterList.get(i).setUnsetteledOpenInterest(unsetteledOpenInterest);
        }
    }

    /**
     * 注文中建玉金額(受渡日（T+0）～受渡日（T+5）)の設定
     *
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     */
    private void editOrderOpenInterest(List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList) {
        
        for (int i = 0; i <= 5; i++) {
            // 未出来信用新規注文買建玉概算合計
            String unexecCrBuyBgSum = settlementdatetList.get(i).getUnexecCrBuyBgSum();
            // 未出来信用新規注文売建玉概算合計
            String unexecCrSellBgSum = settlementdatetList.get(i).getUnexecCrSellBgSum();
            // 注文中建玉金額(受渡日（T+0）～受渡日（T+5）)
            String[] strArray = { unexecCrBuyBgSum, unexecCrSellBgSum };
            String orderOpenInterest = stringAddition(strArray);
            deliveryDateToFiveDaysLaterList.get(i).setOrderOpenInterest(orderOpenInterest);
        }
    }

    /**
     * 当社保証金+SBIハイブリッド預金(現金部分のみ)(受渡日（T+0）～受渡日（T+5）の設定
     *
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     * @param autoSweepKbn 預り金自動スイープ申込区分
     */
    private void editDepositAndHybridDepositBalance(List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList,
            String autoSweepKbn) {
        
        if (! HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
            for (int i = 0; i <= 5; i++) {
                // 残高(ｲｰ･ﾄﾚｰﾄﾞ証券)
                String settleEtBalance = settlementdatetList.get(i).getSettleEtBalance();
                // 残高(決済専用銀行口座)
                String settleBkBalance = settlementdatetList.get(i).getSettleBkBalance();
                // 当社保証金+SBIハイブリッド預金(現金部分のみ)(受渡日（T+0）～受渡日（T+5）
                String[] strArray = { settleEtBalance, settleBkBalance };
                String depositAndHybridDepositBalance = stringAddition(strArray);
                deliveryDateToFiveDaysLaterList.get(i)
                        .setDepositAndHybridDepositBalance(depositAndHybridDepositBalance);
            }
        }
    }

    /**
     * 実保+SBIハイブリッド預金(受渡日（T+0）～受渡日（T+5）)の設定
     *
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     * @param autoSweepKbn 預り金自動スイープ申込区分
     */
    private void editRealInsuranceAndHybridDepositBalance(List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList,
            String autoSweepKbn) {
        
        if (! HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
            for (int i = 0; i <= 5; i++) {
                // 残高(ｲｰ･ﾄﾚｰﾄﾞ証券)
                String settleEtBalance = settlementdatetList.get(i).getSettleEtBalance();
                // 保証金代用? 保証金・代用有価証券
                String securitySubstitute = settlementdatetList.get(i).getSecuritySubstitute();
                // 評価損(受渡日（T+0）～受渡日（T+5）)※算出値
                String valuationLoss = deliveryDateToFiveDaysLaterList.get(i)
                        .getValuationLoss();
                // 決済損益(受渡日（T+0）～受渡日（T+5）)※算出値
                String settlement = deliveryDateToFiveDaysLaterList.get(i).getSettlement();
                // 余力計算用諸経費合計
                String calcEngyPaymentCost = settlementdatetList.get(i).getCalcEngyPaymentCost();
                // 残高(決済専用銀行口座)
                String settleBkBalance = settlementdatetList.get(i).getSettleBkBalance();
                // 実保+SBIハイブリッド預金(受渡日（T+0）～受渡日（T+5）)
                String[] strArray = { settleEtBalance, securitySubstitute, valuationLoss, settlement,
                        calcEngyPaymentCost, settleBkBalance };
                String realInsuranceAndHybridDepositBalance = stringAddition(strArray);
                deliveryDateToFiveDaysLaterList.get(i)
                        .setRealInsuranceAndHybridDepositBalance(realInsuranceAndHybridDepositBalance);
            }
        }
    }

    /**
     * 実質保証金(受渡日（T+0）～受渡日（T+5）)の設定
     *
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     * @param autoSweepKbn 預り金自動スイープ申込区分
     */
    private void editYenActualDeposit(List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList,
            String autoSweepKbn) {
        
        for (int i = 0; i <= 5; i++) {
            String yenActualDeposit = null;
            if (! HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
                // 残高(ｲｰ･ﾄﾚｰﾄﾞ証券)
                String settleEtBalance = settlementdatetList.get(i).getSettleEtBalance();
                // 保証金・代用有価証券
                String securitySubstitute = settlementdatetList.get(i).getSecuritySubstitute();
                // 評価損(受渡日（T+0）～受渡日（T+5）)※算出値
                String valuationLoss = deliveryDateToFiveDaysLaterList.get(i)
                        .getValuationLoss();
                // 決済損益(受渡日（T+0）～受渡日（T+5）)※算出値
                String settlement = deliveryDateToFiveDaysLaterList.get(i).getSettlement();
                // 余力計算用諸経費合計
                String calcEngyPaymentCost = settlementdatetList.get(i).getCalcEngyPaymentCost();
                // 実質保証金(受渡日（T+0）～受渡日（T+5）)
                String[] strArray = { settleEtBalance, securitySubstitute, valuationLoss, settlement,
                        calcEngyPaymentCost };
                yenActualDeposit = stringAddition(strArray);
            }
            if (HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
                // 保証金・現金
                String securityCash = settlementdatetList.get(i).getSecurityCash();
                // 保証金・代用有価証券
                String securitySubstitute = settlementdatetList.get(i).getSecuritySubstitute();
                // 評価損(受渡日（T+0）～受渡日（T+5）)※算出値
                String valuationLoss = deliveryDateToFiveDaysLaterList.get(i)
                        .getValuationLoss();
                // 決済損益(受渡日（T+0）～受渡日（T+5）)※算出値
                String settlement = deliveryDateToFiveDaysLaterList.get(i).getSettlement();
                // 余力計算用諸経費合計
                String calcEngyPaymentCost = settlementdatetList.get(i).getCalcEngyPaymentCost();
                // 実質保証金(受渡日（T+0）～受渡日（T+5）)
                String[] strArray = { securityCash, securitySubstitute, valuationLoss, settlement,
                        calcEngyPaymentCost };
                yenActualDeposit = stringAddition(strArray);
            }
            deliveryDateToFiveDaysLaterList.get(i).setYenActualDeposit(yenActualDeposit);
        }
    }

    /**
     * 決済損益(受渡日（T+0）～受渡日（T+5）)の設定
     *
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     */
    private void editSettlement(List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList) {
        
        for (int i = 0; i <= 5; i++) {
            // 約定済返済建玉決済損益合計額
            String execPayBgLossSum = settlementdatetList.get(i).getExecPayBgLossSum();
            // 未出来返済建玉決済損益合計額
            String unexecPayBgLossSum = settlementdatetList.get(i).getUnexecPayBgLossSum();
            // 決済損益(受渡日（T+0）～受渡日（T+5）)
            String[] strArray = { execPayBgLossSum, unexecPayBgLossSum };
            String settlement = stringAddition(strArray);
            deliveryDateToFiveDaysLaterList.get(i).setSettlement(settlement);
        }
    }

    /**
     * 評価損(受渡日（T+0）～受渡日（T+5）)の設定
     *
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     */
    private void editValuationLoss(List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList) {
        
        for (int i = 0; i <= 5; i++) {
            // 余力計算用時価損益合計
            String calcEngyProfitLoss = settlementdatetList.get(i).getCalcEngyProfitLoss();
            long longCalcEngyProfitLoss = minusNumericalConversion(calcEngyProfitLoss);
            String valuationLoss = null;
            if (longCalcEngyProfitLoss < 0) {
                valuationLoss = calcEngyProfitLoss;
            } else {
                valuationLoss = "0";
            }
            deliveryDateToFiveDaysLaterList.get(i).setValuationLoss(valuationLoss);
        }
    }

    /**
     * 保証金合計(受渡日（T+0）～受渡日（T+5）)の設定
     *
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     * @param autoSweepKbn 預り金自動スイープ申込区分
     */
    private void editTotalDeposit(List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList,
            String autoSweepKbn) {
        
        for (int i = 0; i <= 5; i++) {
            String totalDeposit = null;
            if (! HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
                // 保証金・代用有価証券
                String securitySubstitute = settlementdatetList.get(i).getSecuritySubstitute();
                // 残高(ｲｰ･ﾄﾚｰﾄﾞ証券)
                String settleEtBalance = settlementdatetList.get(i).getSettleEtBalance();
                // 保証金合計(受渡日（T+0）～受渡日（T+5）)
                String[] strArray = { securitySubstitute, settleEtBalance };
                totalDeposit = stringAddition(strArray);
            }
            if (HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
                // 保証金・代用有価証券
                String securitySubstitute = settlementdatetList.get(i).getSecuritySubstitute();
                // 保証金・現金
                String securityCash = settlementdatetList.get(i).getSecurityCash();
                // 保証金合計(受渡日（T+0）～受渡日（T+5）)
                String[] strArray = { securitySubstitute, securityCash };
                totalDeposit = stringAddition(strArray);
            }
            deliveryDateToFiveDaysLaterList.get(i).setTotalDeposit(totalDeposit);
        }
    }

    /**
     * SSNB精算前 保証金現金 (余力WK)（下段）(受渡日（T+0）～受渡日（T+5）)の設定
     *
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     * @param autoSweepKbn 預り金自動スイープ申込区分
     */
    private void editMarginDepositBeforeSsnbCalculateUnder(List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList,
            String autoSweepKbn) {
        
        if (!HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
            for (int i = 0; i <= 5; i++) {
                // 残高(ｲｰ･ﾄﾚｰﾄﾞ証券)
                String settleEtBalance = settlementdatetList.get(i).getSettleEtBalance();
                // SSNB精算額
                String adjAmntNewbk = settlementdatetList.get(i).getAdjAmntNewbk();
                // 差金合計額
                String dayTradingSumAmnt = settlementdatetList.get(i).getDayTradingSumAmnt();
                // SSNB精算前 保証金現金 (余力WK)（下段）(受渡日（T+0）～受渡日（T+5）)
                String[] strArray = { settleEtBalance, adjAmntNewbk, dayTradingSumAmnt };
                String marginDepositBeforeSsnbCalculateUnder = stringSubtraction(strArray);
                deliveryDateToFiveDaysLaterList.get(i)
                        .setMarginDepositBeforeSsnbCalculateUnder(marginDepositBeforeSsnbCalculateUnder);
            }
        }
    }

    /**
     * SSNB精算前 保証金現金 (余力WK)（上段）(受渡日（T+0）～受渡日（T+5）)の設定
     *
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     * @param autoSweepKbn 預り金自動スイープ申込区分
     */
    private void editMarginDepositBeforeSsnbCalculateTop(List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList,
            String autoSweepKbn) {
        
        if (!HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
            for (int i = 0; i <= 5; i++) {
                // 残高(ｲｰ･ﾄﾚｰﾄﾞ証券)
                String settleEtBalance = settlementdatetList.get(i).getSettleEtBalance();
                // SSNB精算額
                String adjAmntNewbk = settlementdatetList.get(i).getAdjAmntNewbk();
                // SSNB精算前 保証金現金 (余力WK)（上段）(受渡日（T+0）～受渡日（T+5）)
                String[] strArray = { settleEtBalance, adjAmntNewbk };
                String marginDepositBeforeSsnbCalculateTop = stringSubtraction(strArray);
                deliveryDateToFiveDaysLaterList.get(i)
                        .setMarginDepositBeforeSsnbCalculateTop(marginDepositBeforeSsnbCalculateTop);
            }
        }
    }
    
    /**
     * 保証金現金（余力WK）（下段）(受渡日（T+0）～受渡日（T+5）)の設定
     *
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     * @param autoSweepKbn 預り金自動スイープ申込区分
     */
    private void editRemainingCapacityWkUnder(List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList,
            String autoSweepKbn) {
        
        if (HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
            for (int i = 0; i <= 5; i++) {
                // 保証金・現金
                String securityCash = settlementdatetList.get(i).getSecurityCash();
                // 差金合計額
                String dayTradingSumAmnt = settlementdatetList.get(i).getDayTradingSumAmnt();
                // 保証金現金（余力WK）（下段）
                String[] strArray = { securityCash, dayTradingSumAmnt };
                String remainingCapacityWkUnder = stringSubtraction(strArray);
                deliveryDateToFiveDaysLaterList.get(i).setRemainingCapacityWkUnder(remainingCapacityWkUnder);
            }
        }
    }
    
    /**
     * 信用建余力(受渡日（T+0）～受渡日（T+5）)の設定
     *
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     */
    private void editCreditCapacity(List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList) {
        
        for (int i = 0; i <= 5; i++) {
            String creditCapacity = null;
            String marginCapacity = settlementdatetList.get(i).getMarginCapacity();
            long longMarginCapacity = minusNumericalConversion(marginCapacity);
            if (longMarginCapacity < 0) {
                creditCapacity = "0";
            } else {
                creditCapacity = String.valueOf(longMarginCapacity);
            }
            deliveryDateToFiveDaysLaterList.get(i).setCreditCapacity(creditCapacity);
        }
    }

    /**
     * 余力詳細_委託保証金率(受渡日（T+0）～受渡日（T+5）)
     *
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     */
    private void editReservePowerDetailDeliveryDateToFiveDaysLaterMarginDepositRate9(
            List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList) {
        
        for (int i = 0; i <= 5; i++) {
            // 受渡日（T+0）～受渡日（T+5）.維持率
            String actualGrntRate = settlementdatetList.get(i).getActualGrntRate();
            String reservePowerDetailDeliveryDateToFiveDaysLaterMarginDepositRate9 = null;
            if (isNullorEmpty(actualGrntRate)) {
                reservePowerDetailDeliveryDateToFiveDaysLaterMarginDepositRate9 = HYPHEN;
            } else {
                reservePowerDetailDeliveryDateToFiveDaysLaterMarginDepositRate9 = divideAndStringConversion(
                        actualGrntRate, "100");
            }
            deliveryDateToFiveDaysLaterList.get(i).setReservePowerDetailDeliveryDateToFiveDaysLaterMarginDepositRate9(
                    reservePowerDetailDeliveryDateToFiveDaysLaterMarginDepositRate9);
        }
    }

    /**
     * 参考委託保証金率(受渡日（T+0）～受渡日（T+5）)の設定
     *
     * @param apiResponse APIレスポンス
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     * @param autoSweepKbn 預り金自動スイープ申込区分
     */
    private void editDomesticReferenceMarginDeposit(QueryMgEstimateCapabilityOutData apiResponse,
            List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList,
            String autoSweepKbn) {
        
        // API001.信用口座区分
        String creditAccountKbn = apiResponse.getCreditAccountKbn();
        for (int i = 0; i <= 5; i++) {
            // 参考委託保証金率(受渡日（T+0）～受渡日（T+5）)
            String domesticReferenceMarginDeposit = null;
            if (DOMESTIC_MARGIN_ACCOUNT_TYPE.equals(creditAccountKbn) && !HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
                // API001の受渡日（T+0）～受渡日（T+5）.維持率/100して設定する。なお、値が△の場合は、-とする。
                String actualGrntRate = settlementdatetList.get(i).getActualGrntRate();
                if (isNullorEmpty(actualGrntRate)) {
                    domesticReferenceMarginDeposit = HYPHEN;
                } else {
                    // ※参考委託保証金率が0%の場合、参考委託保証金率を「0.00」と設定
                    domesticReferenceMarginDeposit = divideAndStringConversion(actualGrntRate, "100");
                }
            }
            if (DOMESTIC_MARGIN_ACCOUNT_TYPE.equals(creditAccountKbn) && HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
                // 信用口座且つAPI001.の預り金自動スイープ申込区分=△の場合
                domesticReferenceMarginDeposit = HYPHEN;
            }
            // 参考委託保証金率(受渡日（T+0）～受渡日（T+5）)
            deliveryDateToFiveDaysLaterList.get(i)
            .setDomesticReferenceMarginDeposit(domesticReferenceMarginDeposit);
        }
    }
    
    /**
     * 委託保証金率(受渡日（T+0）～受渡日（T+5）)
     *
     * @param apiResponse APIレスポンス
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     * @param autoSweepKbn 預り金自動スイープ申込区分
     */
    private void editMarginDepositRate9(QueryMgEstimateCapabilityOutData apiResponse,
            List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList,
            String autoSweepKbn) {
        
        for (int i = 0; i <= 5; i++) {
            // API001.信用口座区分
            String creditAccountKbn = apiResponse.getCreditAccountKbn();
            // 委託保証金率(受渡日（T+0）～受渡日（T+5）)
            String marginDepositRate9 = null;
            if (DOMESTIC_MARGIN_ACCOUNT_TYPE.equals(creditAccountKbn) && HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
                // 受渡日（T+0）～受渡日（T+5）.維持率
                String actualGrntRate = settlementdatetList.get(i).getActualGrntRate();
                if (isNullorEmpty(actualGrntRate)) {
                    marginDepositRate9 = HYPHEN;
                } else {
                    marginDepositRate9 = divideAndStringConversion(actualGrntRate, "100");
                }
            }
            if (DOMESTIC_MARGIN_ACCOUNT_TYPE.equals(creditAccountKbn) && !HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
                // 受渡日（T+0）～受渡日（T+5）.委託保証金率(ET)
                String etKeepRate = settlementdatetList.get(i).getEtKeepRate();
                if (isNullorEmpty(etKeepRate)) {
                    marginDepositRate9 = HYPHEN;
                } else {
                    marginDepositRate9 = divideAndStringConversion(etKeepRate, "100");
                }
            }
            deliveryDateToFiveDaysLaterList.get(i).setMarginDepositRate9(marginDepositRate9);
        }
    }

    /**
     * 出金・振替保証金率（出金・振替可能額の計算に適用）の設定
     *
     * @param apiResponse APIレスポンス
     * @param responseDto レスポンスモデル
     */
    private void editWithdrawTransfer(QueryMgEstimateCapabilityOutData apiResponse,
            IfaMarginPowerDomesticA001DtoResponse responseDto) {
        
        String repayNeedSecRate = apiResponse.getRepayNeedSecRate();
        if (isNullorEmpty(repayNeedSecRate)) {
            responseDto.setWithdrawTransfer(HYPHEN);
        } else {
            BigDecimal withdrawTransfer = new BigDecimal(repayNeedSecRate).divide(new BigDecimal("100"));
            responseDto.setWithdrawTransfer(withdrawTransfer.toString());
        }
    }

    /**
     * 現物買付保証金率（現物の買付余力の計算に適用）の設定
     *
     * @param apiResponse APIレスポンス
     * @param responseDto レスポンスモデル
     */
    private void editInKindPurchase(QueryMgEstimateCapabilityOutData apiResponse,
            IfaMarginPowerDomesticA001DtoResponse responseDto) {
        
        String buyNeedSecRate = apiResponse.getBuyNeedSecRate();
        if (isNullorEmpty(buyNeedSecRate)) {
            responseDto.setInKindPurchase(HYPHEN);
        } else {
            BigDecimal inKindPurchase = new BigDecimal(buyNeedSecRate).divide(new BigDecimal("100"));
            responseDto.setInKindPurchase(inKindPurchase.toString());
        }
    }

    /**
     * 現金保証金率（新規建に必要な保証金現金の計算に適用）の設定
     *
     * @param apiResponse APIレスポンス
     * @param responseDto レスポンスモデル
     */
    private void editChache(QueryMgEstimateCapabilityOutData apiResponse,
            IfaMarginPowerDomesticA001DtoResponse responseDto) {
        
        String cashNeedSecRate = apiResponse.getCashNeedSecRate();
        if (isNullorEmpty(cashNeedSecRate)) {
            responseDto.setChache(HYPHEN);
        } else {
            BigDecimal chache = new BigDecimal(cashNeedSecRate).divide(new BigDecimal("100"));
            responseDto.setChache(chache.toString());
        }
    }

    /**
     * 新規建保証金率（信用建余力の計算に適用）の設定
     *
     * @param apiResponse APIレスポンス
     * @param responseDto レスポンスモデル
     */
    private void editNewOpenInterest(QueryMgEstimateCapabilityOutData apiResponse,
            IfaMarginPowerDomesticA001DtoResponse responseDto) {
        
        String bgnNeedSecRate = apiResponse.getBgnNeedSecRate();
        if (isNullorEmpty(bgnNeedSecRate)) {
            responseDto.setNewOpenInterest(HYPHEN);
        } else {
            BigDecimal newOpenInterest = new BigDecimal(bgnNeedSecRate).divide(new BigDecimal("100"));
            responseDto.setNewOpenInterest(newOpenInterest.toString());
        }
    }

    /**
     * 前日委託保証金率(T+0)の設定
     *
     * @param responseDto レスポンスモデル
     * @param datetList API.追証情報リスト
     */
    private void editConsignmentDeposit(IfaMarginPowerDomesticA001DtoResponse responseDto, List<DateT> datetList) {
        
        String guarannteeRate = datetList.get(4).getGuarannteeRate();
        if (isNullorEmpty(guarannteeRate)) {
            responseDto.setConsignmentDeposit(HYPHEN);
        } else {
            String consignmentDeposit = divideAndStringConversion(guarannteeRate, "100");
            responseDto.setConsignmentDeposit(consignmentDeposit);
        }
    }

    /**
     * 建玉総限度額の設定
     *
     * @param apiResponse APIレスポンス
     * @param responseDto レスポンスモデル
     */
    private void editOpenInterestLimit(QueryMgEstimateCapabilityOutData apiResponse,
            IfaMarginPowerDomesticA001DtoResponse responseDto) {
        
        String bargainMaxStr = apiResponse.getBargainMax();
        if (isNullorEmpty(bargainMaxStr)) {
            responseDto.setOpenInterestLimit(HYPHEN);
        } else {
            long bargainMaxLong = minusNumericalConversion(bargainMaxStr);
            long openInterestLimitLong = bargainMaxLong / 10000;
            String openInterestLimitStr = String.valueOf(openInterestLimitLong);
            responseDto.setOpenInterestLimit(openInterestLimitStr);
        }
    }

    /**
     * 建玉強制返済執行期限の設定
     *
     * @param responseDto レスポンスモデル
     * @param datetList API.追証情報リスト
     */
    private void editDeadlineforforcedrepaymentOfopeninterest(IfaMarginPowerDomesticA001DtoResponse responseDto,
            List<DateT> datetList) {
        
        if (isAllHarfWidthSpaceDeficitDate(datetList)) {
            responseDto.setDeadlineforforcedrepaymentOfopeninterest(HYPHEN);
        } else {
            String deadlineforforcedrepaymentOfopeninterest = datetList.stream()
                    .filter(d -> !isNullorEmpty(d.getDeficitDate()))
                    .filter(d -> Objects.nonNull(d.getCloseooutDate()))
                    .min(Comparator.comparing(DateT::getCloseooutDate)).get().getCloseooutDate();
            responseDto.setDeadlineforforcedrepaymentOfopeninterest(deadlineforforcedrepaymentOfopeninterest);
        }
    }

    /**
     * 追証解消期限の設定
     *
     * @param responseDto レスポンスモデル
     * @param datetList API.追証情報リスト
     */
    private void editMarginCancellationDeadline(IfaMarginPowerDomesticA001DtoResponse responseDto,
            List<DateT> datetList) {
        
        if (isAllHarfWidthSpaceDeficitDate(datetList)) {
            responseDto.setMarginCancellationDeadline(HYPHEN);
        } else {
            String marginCancellationDeadline = datetList.stream()
                    .filter(d -> !isNullorEmpty(d.getDeficitDate()))
                    .min(Comparator.comparing(DateT::getDeficitChargeDate)).get().getDeficitChargeDate();
            responseDto.setMarginCancellationDeadline(marginCancellationDeadline);
        }
    }

    /**
     * 追証発生日の設定
     *
     * @param responseDto レスポンスモデル
     * @param datetList API.追証情報リスト
     * @param oldestDeficitDate 最も古い追証発生日
     */
    private void editMarginCallDate(IfaMarginPowerDomesticA001DtoResponse responseDto, List<DateT> datetList,
            String oldestDeficitDate) {
        
        if (isAllHarfWidthSpaceDeficitDate(datetList)) {
            responseDto.setMarginCallDate(HYPHEN);
        } else {
            responseDto.setMarginCallDate(oldestDeficitDate);
        }
    }

    /**
     * 追証金額の設定
     *
     * @param responseDto レスポンスモデル
     * @param datetList API.追証情報リスト
     * @param oldestDeficitDate 最も古い追証発生日
     */
    private void editMarginAmount(IfaMarginPowerDomesticA001DtoResponse responseDto, List<DateT> datetList,
            String oldestDeficitDate) {
        
        if (isAllHarfWidthSpaceDeficitDate(datetList)) {
            responseDto.setMarginAmount(HYPHEN);
        } else {
            String marginAmount = datetList.stream()
                    .filter((d -> d.getDeficitDate().equals(oldestDeficitDate)))
                    .map(DateT::getDeficitChargeRef).findFirst().orElse(null);
            responseDto.setMarginAmount(marginAmount);
        }
    }

    /**
     * 追証基準維持率の設定
     *
     * @param responseDto レスポンスモデル
     * @param datetList API.追証情報リスト
     * @param oldestDeficitDate 最も古い追証発生日
     */
    private void editMarginstandardmaintenanceRate(IfaMarginPowerDomesticA001DtoResponse responseDto,
            List<DateT> datetList, String oldestDeficitDate) {
        
        String guarannteeRate = null;
        if (isAllHarfWidthSpaceDeficitDate(datetList)) {
            // T日～T-4日の追証発生日全て"△"の場合、T日の値を使用。
            guarannteeRate = datetList.get(4).getGuarannteeRate();
        } else {
            // 最も古い日付の維持率
            guarannteeRate = datetList.stream().filter((d -> d.getDeficitDate().equals(oldestDeficitDate)))
                    .map(DateT::getGuarannteeRate).findFirst().orElse(null);
        }
        if (isNullorEmpty(guarannteeRate)) {
            // 追証基準維持率が△の場合は、-とする。
            responseDto.setMarginstandardmaintenanceRate(HYPHEN);
        } else {
            String marginstandardmaintenanceRateStr = divideAndStringConversion(guarannteeRate, "100");
            responseDto.setMarginstandardmaintenanceRate(marginstandardmaintenanceRateStr);
        }
    }

    /**
     * 追加保証金を全て解消するための必要額の設定
     *
     * @param responseDto レスポンスモデル
     * @param datetList API.追証情報リスト
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param autoSweepKbn 預り金自動スイープ申込区分
     * @param deficitStatus 追証ステータス
     */
    private void editAmountrequiredtocancelallAdditionalsecuritydeposits(
            IfaMarginPowerDomesticA001DtoResponse responseDto, List<DateT> datetList,
            List<SettlementDateT> settlementdatetList, String autoSweepKbn, String deficitStatus) {
        
        // 受渡日（T+0）
        SettlementDateT apiResSettlementDate0 = settlementdatetList.get(0);
        // 受渡日（T+0）.翌日精算額確定フラグ
        String fixedSettlementFlg0 = apiResSettlementDate0.getFixedSettlementFlg();
        // API001.追証情報(T-0)
        DateT dataT0 = datetList.get(4);
        // 追証請求額(参照用）
        String totalChargeRefDate0 = dataT0.getDeficitChargeRef();
        // その他請求額(参照用）
        String deficitChargeRef0 = dataT0.getTotalChargeRef();
        // 代用証券入庫額
        String collateralDepositAmt0 = dataT0.getCollateralDepositAmt();
        // 現金入金額
        String cashDepositAmount0 = dataT0.getCashDepositAmount();
        // SSNB精算額(T+0)
        String adjAmntNewbk = apiResSettlementDate0.getAdjAmntNewbk();
        long longAdjAmntNewbk0 = minusNumericalConversion(adjAmntNewbk);
        
        if (deficitStatus.equals("3") || deficitStatus.equals("7")) {
            if (!HALF_WIDTH_SPACE.equals(autoSweepKbn) && "1".equals(fixedSettlementFlg0)) {
                if (longAdjAmntNewbk0 < 0) {
                    String[] strArray = { totalChargeRefDate0, deficitChargeRef0, collateralDepositAmt0,
                            cashDepositAmount0 };
                    String amountrequiredtocancelallAdditionalsecuritydeposits = stringSubtraction(strArray);
                    responseDto.setAmountrequiredtocancelallAdditionalsecuritydeposits(
                            amountrequiredtocancelallAdditionalsecuritydeposits);
                } else {
                    String[] strArray = { totalChargeRefDate0, deficitChargeRef0, collateralDepositAmt0,
                            cashDepositAmount0, adjAmntNewbk};
                    String amountrequiredtocancelallAdditionalsecuritydeposits = stringSubtraction(strArray);
                    responseDto.setAmountrequiredtocancelallAdditionalsecuritydeposits(
                            amountrequiredtocancelallAdditionalsecuritydeposits);
                }
            }
            if (!HALF_WIDTH_SPACE.equals(autoSweepKbn)
                    && HALF_WIDTH_SPACE.equals(fixedSettlementFlg0)) {
                String[] strArray = { totalChargeRefDate0, deficitChargeRef0, collateralDepositAmt0,
                        cashDepositAmount0 };
                String amountrequiredtocancelallAdditionalsecuritydeposits = stringSubtraction(strArray);
                responseDto.setAmountrequiredtocancelallAdditionalsecuritydeposits(
                        amountrequiredtocancelallAdditionalsecuritydeposits);
            }
            if (HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
                String[] strArray = { totalChargeRefDate0, deficitChargeRef0, collateralDepositAmt0,
                        cashDepositAmount0 };
                String amountrequiredtocancelallAdditionalsecuritydeposits = stringSubtraction(strArray);
                responseDto.setAmountrequiredtocancelallAdditionalsecuritydeposits(
                        amountrequiredtocancelallAdditionalsecuritydeposits);
            }
        } else {
            // API001.追証ステータス＝3or7 以外の場合
            responseDto.setAmountrequiredtocancelallAdditionalsecuritydeposits(HYPHEN);
        }
    }

    /**
     * 追加保証金未解消に伴う建玉強制返済予定日時の設定
     *
     * @param responseDto レスポンスモデル
     * @param datetList API.追証情報リスト
     */
    private void editPlannedDateForcedRedemptionOpenInterest(IfaMarginPowerDomesticA001DtoResponse responseDto,
            List<DateT> datetList) {
        
        if (isAllHarfWidthSpaceDeficitDate(datetList)) {
            responseDto.setPlannedDateForcedRedemptionOpenInterest(HYPHEN);
        } else {
            String oldestCloseooutDate = datetList.stream().filter(d -> !isNullorEmpty(d.getDeficitDate()))
                    .min(Comparator.comparing(DateT::getCloseooutDate)).get().getCloseooutDate();
            
            responseDto.setPlannedDateForcedRedemptionOpenInterest(oldestCloseooutDate);
        }
    }

    /**
     * 追証ステータスの設定
     *
     * @param responseDto レスポンスモデル
     * @param deficitStatus 追証ステータス
     */
    private void editMarginStatus(IfaMarginPowerDomesticA001DtoResponse responseDto, String deficitStatus) {
        
        switch (deficitStatus) {
            case "7":
                responseDto.setMarginStatus(MarginCallStatus.BEFORE_MARGIN_CALL_CANCELLATION_DEADLINE.getName());
                break;
            case "3":
                responseDto.setMarginStatus(
                        MarginCallStatus.AFTER_THE_UNRESOLVED_MARGIN_CALL_CANCELLATION_DEADLINE.getName());
                break;
            case "9":
                responseDto.setMarginStatus(MarginCallStatus.MARGIN_FORCED_SALE_CONFIRMED.getName());
                break;
            case "5":
                responseDto.setMarginStatus(MarginCallStatus.AFTER_EXECUTION_OF_FORCED_MARGIN_SALE.getName());
                break;
            case "4":
                responseDto.setMarginStatus(
                        MarginCallStatus.AFTER_THE_CANCELED_MARGIN_CALL_CANCELLATION_DEADLINE.getName());
                break;
            case "8":
                responseDto.setMarginStatus(MarginCallStatus.CANCELLATION_OF_COMPULSORY_MARGIN_CALL.getName());
                break;
            case HALF_WIDTH_SPACE:
                responseDto.setMarginStatus(MarginCallStatus.NOT_A_MARGIN_CALL.getName());
                break;
            default:
                responseDto.setMarginStatus(MarginCallStatus.NOT_A_MARGIN_CALL.getName());
        }
        ;
    }

    /**
     * 入金額（T-４）～(T+0)の設定
     *
     * @param datetList API.追証情報リスト
     * @param marginCallinfoList 追証情報リスト
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param autoSweepKbn 預り金自動スイープ申込区分
     */
    private void editDepositAmount(List<DateT> datetList,
            List<IfaMarginPowerDomesticA001DtoResponseMarginCallinfo> marginCallinfoList,
            List<SettlementDateT> settlementdatetList, String autoSweepKbn) {
        
        if (HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
            for (int i = 0; i <= 4; i++) {
                marginCallinfoList.get(i).setDepositAmount(datetList.get(i).getCashDepositAmount());
            }
        }
        if (!HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
            for (int i = 0; i <= 4; i++) {
                // 受渡日(T+0) ＝ 受渡日（T+0）～受渡日（T+5）リスト(0)
                SettlementDateT apiResSettlementDate0 = settlementdatetList.get(0);
                // API001.受渡日（T+0）.翌日精算額確定フラグ
                if ("1".equals(apiResSettlementDate0.getFixedSettlementFlg())) {
                    // 追証情報(T-0) ＝ 追証情報リスト(4)
                    if (i == 4) {
                        // SSNB精算額がマイナスだった場合は加算しない
                        long adjAmntNewbk = minusNumericalConversion(apiResSettlementDate0.getAdjAmntNewbk());
                        if (adjAmntNewbk < 0) {
                            marginCallinfoList.get(i).setDepositAmount(datetList.get(i).getCashDepositAmount());
                        } else {
                            long cashDepositAmount = numericalConversion(datetList.get(i).getCashDepositAmount());
                            long longDepositAmount = cashDepositAmount + adjAmntNewbk;
                            String depositAmount = String.valueOf(longDepositAmount);
                            marginCallinfoList.get(i).setDepositAmount(depositAmount);
                        }
                    } else {
                        // 入金額（T-４）～(T-１)にAPI001の追証情報（T-４）～(T-１).現金入金額を設定。
                        marginCallinfoList.get(i).setDepositAmount(datetList.get(i).getCashDepositAmount());
                    }
                }
                if (HALF_WIDTH_SPACE.equals(apiResSettlementDate0.getFixedSettlementFlg())) {
                    marginCallinfoList.get(i).setDepositAmount(datetList.get(i).getCashDepositAmount());
                }
            }
        }
    }

    /**
     * 追証発生日（T-４）～(T+0)の設定
     *
     * @param apiResponse {@code }
     * @param datetList {@code }
     * @param marginCallinfoList {@code }
     * @param oldestDateTm 最も古い基準日の取得
     */
    private void editMarginCallinfoMarginCallDate(QueryMgEstimateCapabilityOutData apiResponse, List<DateT> datetList,
            List<IfaMarginPowerDomesticA001DtoResponseMarginCallinfo> marginCallinfoList, String oldestDateTm) {
        if ("5".equals(apiResponse.getDeficitStatus()) || "9".equals(apiResponse.getDeficitStatus())) {
            for (int i = 0; i <= 4; i++) {
                DateT dateT = datetList.get(i);
                IfaMarginPowerDomesticA001DtoResponseMarginCallinfo marginCallinfo = marginCallinfoList.get(i);
                if (!isAllHarfWidthSpaceDeficitDate(datetList)) {
                    if (dateT.getDateTM().equals(oldestDateTm)) {
                        marginCallinfo.setMarginCallDate(dateT.getDeficitDate());
                    } else {
                        marginCallinfo.setMarginCallDate(HYPHEN);
                    }
                }
            }
            return;
        } else {
            for (int i = 0; i <= 4; i++) {
                DateT dateT = datetList.get(i);
                IfaMarginPowerDomesticA001DtoResponseMarginCallinfo marginCallinfo = marginCallinfoList.get(i);
                if (isNullorEmpty(dateT.getDeficitDate())) {
                    marginCallinfo.setMarginCallDate(HYPHEN);
                } else {
                    marginCallinfo.setMarginCallDate(dateT.getDeficitDate());
                }
            }
        }
    }

    /**
     * 当初追証額（T-４）～(T+0)の設定
     *
     * @param apiResponse APIレスポンス
     * @param datetList API.追証情報リスト
     * @param marginCallinfoList 追証情報リスト
     * @param oldestDateTm 最も古い基準日の取得
     */
    private void editInitialMarginAmount(QueryMgEstimateCapabilityOutData apiResponse, List<DateT> datetList,
            List<IfaMarginPowerDomesticA001DtoResponseMarginCallinfo> marginCallinfoList, String oldestDateTm) {
        
        if ("5".equals(apiResponse.getDeficitStatus()) || "9".equals(apiResponse.getDeficitStatus())) {
            for (int i = 0; i <= 4; i++) {
                DateT dateT = datetList.get(i);
                IfaMarginPowerDomesticA001DtoResponseMarginCallinfo marginCallinfo = marginCallinfoList.get(i);
                if (!isAllHarfWidthSpaceDeficitDate(datetList)) {
                    if (dateT.getDateTM().equals(oldestDateTm)) {
                        marginCallinfo.setInitialMarginAmount(dateT.getDeficitChargeRef());
                    } else {
                        marginCallinfo.setInitialMarginAmount(HYPHEN);
                    }
                }
            }
        } else {
            for (int i = 0; i <= 4; i++) {
                DateT apiRes = datetList.get(i);
                IfaMarginPowerDomesticA001DtoResponseMarginCallinfo marginCallinfo = marginCallinfoList.get(i);
                if ("0".equals(apiRes.getDeficitChargeRef())) {
                    marginCallinfo.setInitialMarginAmount(HYPHEN);
                } else {
                    marginCallinfo.setInitialMarginAmount(apiRes.getDeficitChargeRef());
                }
            }
        }
    }

    /**
     * 解消期限/入金・入庫計上日(確定・概算)の設定
     *
     * @param apiResponse APIレスポンス
     * @param datetList API.追証情報リスト
     * @param marginCallinfoList 追証情報リスト
     * @param deficitChargeDate0 API001.追証情報（T+0）.追証入金予定日
     */
    private void editCancellationDeadlineRecordingDateDefiniteApproximate(QueryMgEstimateCapabilityOutData apiResponse,
            List<DateT> datetList, List<IfaMarginPowerDomesticA001DtoResponseMarginCallinfo> marginCallinfoList,
            String deficitChargeDate0) {
        
        for (int i = 0; i <= 4; i++) {
            if (deficitChargeDate0.equals(datetList.get(i).getDeficitChargeDate())) {
                if ("1".equals(apiResponse.getPriceWashKbn())) {
                    marginCallinfoList.get(i).setCancellationDeadlineRecordingDateDefiniteApproximate(APPROXIMATION);
                }
                if ("2".equals(apiResponse.getPriceWashKbn())) {
                    marginCallinfoList.get(i).setCancellationDeadlineRecordingDateDefiniteApproximate(DECISION);
                }
            } else {
                marginCallinfoList.get(i).setCancellationDeadlineRecordingDateDefiniteApproximate(DECISION);
            }
        }
    }

    /**
     * 自動スイープ対象フラグの設定
     *
     * @param responseDto レスポンスモデル
     * @param autoSweepKbn 預り金自動スイープ申込区分
     */
    private void editAutoSweepTargetFlag(IfaMarginPowerDomesticA001DtoResponse responseDto, String autoSweepKbn) {
        // ・自動スイープ対象フラグの設定
        if (!HALF_WIDTH_SPACE.equals(autoSweepKbn)) {
            responseDto.setAutoSweepTargetFlag("1");
        } else {
            responseDto.setAutoSweepTargetFlag("0");
        }
    }

    /**
     * 追証入金予定日の設定
     *
     * @param responseDto レスポンスモデル
     * @param deficitChargeDate0 追証情報(T+0).追証入金予定日
     */
    private void editMarginDepositScheduleDate(
            IfaMarginPowerDomesticA001DtoResponse responseDto, String deficitChargeDate0) {
        // （YYYY/MM/DD　＋　計上の場合）
        StringBuilder sb = new StringBuilder();
        sb.append("（");
        sb.append(deficitChargeDate0, 0, 4);
        sb.append("/");
        sb.append(deficitChargeDate0, 4, 6);
        sb.append("/");
        sb.append(deficitChargeDate0, 6, 8);
        sb.append(APPROPRIATION);
        sb.append("）");
        responseDto.setMarginDepositScheduleDate(sb.toString());
    }
    
    /**
     * レスポンスモデルの設定
     *
     * @param apiResponse APIレスポンス
     * @param responseDto レスポンスモデル
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     */
    private void editResponseDto(QueryMgEstimateCapabilityOutData apiResponse,
            IfaMarginPowerDomesticA001DtoResponse responseDto, List<SettlementDateT> settlementdatetList) {
        
        // レスポンス.受渡日別入金予定合計額 = 受渡日（T+0）～受渡日（T+5）リスト(1).受渡日別入金予定合計額
        responseDto.setDeliveryDateDepositScheduleTotalAmount(settlementdatetList.get(1).getEachDepositSumAmnt());
        // レスポンス.信用建余力 = 受渡日（T+0）～受渡日（T+5）リスト(0).信用建玉余力
        responseDto.setCreditCapacity(settlementdatetList.get(0).getMarginCapacity());
        // レスポンス.現引可能額 = 受渡日（T+0）～受渡日（T+5）リスト(0).現引可能額
        responseDto.setCashOnDelivery(settlementdatetList.get(0).getActualReceiptPower());
        // レスポンス.現物買付余力 = 受渡日（T+0）～受渡日（T+5）リスト(0).現物買付余力(最終）
        responseDto.setBuyingRemainingPower(settlementdatetList.get(0).getBuyingPowerByDay());
        // レスポンス.出金・振替可能額 = 受渡日（T+0）～受渡日（T+5）リスト(0).出金余力
        responseDto.setWithdrawalCapacity(settlementdatetList.get(0).getCashPaymentPower());
        
        // ▼その他
        // レスポンス.前日基準.未決済建玉金額 = 未決済建玉金額(T-1)
        responseDto.setPreviousDayBaseOpenInterestAmount(apiResponse.getUnclosedAmount());
    }

    /**
     * 受渡日（T+0）～受渡日（T+5）リストの設定（算出値以外の設定）
     *
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     * @param deliveryDateToFiveDaysLaterList 受渡日（T+0）～受渡日（T+5）リスト
     */
    private void editDeliveryDateToFiveDaysLaterList(List<SettlementDateT> settlementdatetList,
            List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList) {
        
        for (int i = 0; i <= 5; i++) {
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).受渡日 = 受渡日（T+0）～受渡日（T+5）リスト(n).受渡日
            deliveryDateToFiveDaysLaterList.get(i)
                    .setSettlementDateT0toT5(settlementdatetList.get(i).getSettlementDate());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).建玉必要額 = 受渡日（T+0）～受渡日（T+5）リスト(n).追証必要額
            deliveryDateToFiveDaysLaterList.get(i)
                    .setOpenInterestCostYen(settlementdatetList.get(i).getHoldedGrntDeficit());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).追証予定額 = 受渡日（T+0）～受渡日（T+5）リスト(n).追証過不足額
            deliveryDateToFiveDaysLaterList.get(i)
                    .setAdditionalMarginPlannedAmount(settlementdatetList.get(i).getDeficitSurplus());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).預り金不足予定額 = 受渡日（T+0）～受渡日（T+5）リスト(n).預り金残高赤残分
            deliveryDateToFiveDaysLaterList.get(i)
                    .setShortageDepositDue(settlementdatetList.get(i).getDepositBalanceRed());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).31％超分 = 受渡日（T+0）～受渡日（T+5）リスト(n).新規建充当可能額
            deliveryDateToFiveDaysLaterList.get(i).setOver31per(settlementdatetList.get(i).getSurplusForBargain());
            // レスポンス."受渡日（T+0）～受渡日（T+5）リスト(n).31%超分(当社のみ)" =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).新規建充当可能額(ET)
            deliveryDateToFiveDaysLaterList.get(i)
                    .setOver31perOurCompany(settlementdatetList.get(i).getEtSurplusForBargain());
            // レスポンス."受渡日（T+0）～受渡日（T+5）リスト(n).31%超分(当社+SSNB)" =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).新規建充当可能額
            deliveryDateToFiveDaysLaterList.get(i)
                    .setOver31perOurCompanySsnb(settlementdatetList.get(i).getSurplusForBargain());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).30％超分 = 受渡日（T+0）～受渡日（T+5）リスト(n).現物買余力用保証金余力
            deliveryDateToFiveDaysLaterList.get(i).setOver30per(settlementdatetList.get(i).getSurplusForCashTrade());
            // レスポンス."受渡日（T+0）～受渡日（T+5）リスト(n).30%超分(当社のみ)" =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).現物買余力用保証金余力(ET)
            deliveryDateToFiveDaysLaterList.get(i)
                    .setOver30perOurCompany(settlementdatetList.get(i).getEtSurplusForCashTrade());
            // レスポンス."受渡日（T+0）～受渡日（T+5）リスト(n).30%超分(当社+SSNB)" =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).現物買余力用保証金余力
            deliveryDateToFiveDaysLaterList.get(i)
                    .setOver30perOurCompanySsnb(settlementdatetList.get(i).getSurplusForCashTrade());
            // レスポンス."受渡日（T+0）～受渡日（T+5）リスト(n).30.2%超分(当社のみ)" =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).現金拘束用保証金余力(ET)
            deliveryDateToFiveDaysLaterList.get(i)
                    .setOver302perOurCompany(settlementdatetList.get(i).getEtSurplusForCashRest());
            // レスポンス."受渡日（T+0）～受渡日（T+5）リスト(n).30.2%超分(当社+SSNB)" =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).現金拘束用保証金余力(ET+SSNB)
            deliveryDateToFiveDaysLaterList.get(i)
                    .setOver302perOurCompanySsnb(settlementdatetList.get(i).getSurplusForCashRest());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).現物買付余力 = 受渡日（T+0）～受渡日（T+5）リスト(n).買付余力
            deliveryDateToFiveDaysLaterList.get(i)
                    .setBuyingRemainingPower(settlementdatetList.get(i).getBuyingEnergy());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).出金振替可能額 = 受渡日（T+0）～受渡日（T+5）リスト(n).出金余力
            deliveryDateToFiveDaysLaterList.get(i)
                    .setWithdrawalCapacity(settlementdatetList.get(i).getCashPaymentPower());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).委託保証金受渡日 = 受渡日（T+0）～受渡日（T+5）リスト(n).受渡日
            deliveryDateToFiveDaysLaterList.get(i)
                    .setConsignmentSecurityDepositDeliveryDate(settlementdatetList.get(i).getSettlementDate());
            // レスポンス."受渡日（T+0）～受渡日（T+5）リスト(n).保証金現金（余力WK）（上段）"
            // 受渡日（T+0）～受渡日（T+5）リスト(n).保証金・現金
            deliveryDateToFiveDaysLaterList.get(i)
                    .setRemainingCapacityWkTop(settlementdatetList.get(i).getSecurityCash());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).SSNB精算額 = 受渡日（T+0）～受渡日（T+5）リスト(n).SSNB精算額
            deliveryDateToFiveDaysLaterList.get(i).setSsnbCalculateAmount(settlementdatetList.get(i).getAdjAmntNewbk());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).SSNB精算後保証金 =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).残高(ｲｰ･ﾄﾚｰﾄﾞ証券)
            deliveryDateToFiveDaysLaterList.get(i)
                    .setMarginDepositAfterSsnbCalculate(settlementdatetList.get(i).getSettleEtBalance());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).代用入庫額 = 受渡日（T+0）～受渡日（T+5）リスト(n).代用証券入庫評価額
            deliveryDateToFiveDaysLaterList.get(i)
                    .setAlternativeReceipt(settlementdatetList.get(i).getCollateralDeposit());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).代用出庫額 = 受渡日（T+0）～受渡日（T+5）リスト(n).代用証券出庫評価額
            deliveryDateToFiveDaysLaterList.get(i)
                    .setSubstituteDeliveryAmount(settlementdatetList.get(i).getCollateralWithdraw());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).保証金代用 = 受渡日（T+0）～受渡日（T+5）リスト(n).保証金・代用有価証券
            deliveryDateToFiveDaysLaterList.get(i)
                    .setAlternativeDeposit(settlementdatetList.get(i).getSecuritySubstitute());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).評価損益 = 受渡日（T+0）～受渡日（T+5）リスト(n).余力計算用時価損益合計
            deliveryDateToFiveDaysLaterList.get(i)
                    .setDomesticPositionValuation(settlementdatetList.get(i).getCalcEngyProfitLoss());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).支払諸経費 = 受渡日（T+0）～受渡日（T+5）リスト(n).余力計算用諸経費合計
            deliveryDateToFiveDaysLaterList.get(i)
                    .setPaymentExpence(settlementdatetList.get(i).getCalcEngyPaymentCost());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).SBIハイブリッド預金残高 =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).残高(決済専用銀行口座)
            deliveryDateToFiveDaysLaterList.get(i)
                    .setHybridDepositBalance(settlementdatetList.get(i).getSettleBkBalance());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).建玉必要保証金 = 受渡日（T+0）～受渡日（T+5）リスト(n).建玉余力用必要保証金額
            deliveryDateToFiveDaysLaterList.get(i)
                    .setOpenInterstDeposit(settlementdatetList.get(i).getHoldedGrntMargin());
            // レスポンス."受渡日（T+0）～受渡日（T+5）リスト(n).建玉必要保証金(うち現金)" =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).現金必要保証金（建玉用）
            deliveryDateToFiveDaysLaterList.get(i)
                    .setOpenInterstDepositCash(settlementdatetList.get(i).getCashHoldBgnNeedSec());
            // レスポンス."受渡日（T+0）～受渡日（T+5）リスト(n).買付必要保証金（30％）" =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).現物買余力用必要保証金額
            deliveryDateToFiveDaysLaterList.get(i)
                    .setPurchaseDeposit30(settlementdatetList.get(i).getHoldedGrntCashTrade());
            // レスポンス."受渡日（T+0）～受渡日（T+5）リスト(n).買付必要保証金（30.％）(うち現金)" =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).現金拘束用必要保証金額
            deliveryDateToFiveDaysLaterList.get(i)
                    .setPurchaseDeposit30Cash(settlementdatetList.get(i).getHoldedGrntCash());
            // レスポンス."受渡日（T+0）～受渡日（T+5）リスト(n).買付必要保証金（30.2％）" =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).現金拘束用必要保証金額
            deliveryDateToFiveDaysLaterList.get(i).setPurchaseDeposit32(settlementdatetList.get(i).getHoldedGrntCash());
            // レスポンス."受渡日（T+0）～受渡日（T+5）リスト(n).買付必要保証金（30.2％）(増担保)" =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).現金拘束金（買付）(増担保)
            deliveryDateToFiveDaysLaterList.get(i)
                    .setPurchaseDeposit32AdditionalCollateral(settlementdatetList.get(i).getCashSecBuyInc());
            // レスポンス."受渡日（T+0）～受渡日（T+5）リスト(n).買付必要保証金（30.2％）[配当金]" =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).現金拘束金（買付）(配当金)
            deliveryDateToFiveDaysLaterList.get(i)
                    .setPurchaseDeposit32Divined(settlementdatetList.get(i).getCashSecBuyDiv());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).返却必要保証金 = 受渡日（T+0）～受渡日（T+5）リスト(n).返却余力用必要保証金額
            deliveryDateToFiveDaysLaterList.get(i)
                    .setReturnDeposit(settlementdatetList.get(i).getHoldedGrntPayment());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).返却必要保証金(うち現金) =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).現金必要保証金（返却用）
            deliveryDateToFiveDaysLaterList.get(i)
                    .setReturnDepositCash(settlementdatetList.get(i).getCashHoldPayNeedSec());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).返却必要保証金[増担保] =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).現金拘束金（返却）(増担保)
            deliveryDateToFiveDaysLaterList.get(i)
                    .setReturnDepositAdditionalCollateral(settlementdatetList.get(i).getCashSecRepInc());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).返却必要保証金[配当金] =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).現金拘束金（返却）(配当金)
            deliveryDateToFiveDaysLaterList.get(i)
                    .setReturnDepositDivined(settlementdatetList.get(i).getCashSecRepDiv());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).現引・現渡必要保証金 =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).日計り決済用必要保証金
            deliveryDateToFiveDaysLaterList.get(i)
                    .setCurrentDeliverlyDeposit(settlementdatetList.get(i).getDayTradePayNeedSec());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).信用取引必要保証金（※） =
            // 受渡日（T+0）～受渡日（T+5）リスト(n).信用取引必要保証金
            deliveryDateToFiveDaysLaterList.get(i)
                    .setCreditOrderDeposit(settlementdatetList.get(i).getMarginNeedCash());
            // レスポンス.受渡日（T+0）～受渡日（T+5）リスト(n).当社必要保証金（※） = 受渡日（T+0）～受渡日（T+5）リスト(n).ET必要保証金
            deliveryDateToFiveDaysLaterList.get(i).setInnerDeposit(settlementdatetList.get(i).getEtNeedCash());
        }
    }

    /**
     * 追証情報リストの設定（算出値以外の設定）
     *
     * @param datetList API.追証情報リスト
     * @param marginCallinfoList 追証情報リスト
     * @param settlementdatetList API.受渡日（T+0）～受渡日（T+5）リスト
     */
    private void editMarginCallinfoList(List<DateT> datetList,
            List<IfaMarginPowerDomesticA001DtoResponseMarginCallinfo> marginCallinfoList,
            List<SettlementDateT> settlementdatetList) {
        
        for (int i = 0; i <= 4; i++) {
            // レスポンス.追証情報リスト(n).解消期限/入金・入庫計上日（T-4～T+0） = 追証情報リスト(n)追証.入金予定日
            String deficitChargeDate = datetList.get(i).getDeficitChargeDate();
            marginCallinfoList.get(i).setCancellationDeadlineRecordingDate(deficitChargeDate);
            // レスポンス.追証情報リスト(n).預り金不足額（T-4～T+0）） = 追証情報リスト(n).その他請求額(参照用）
            String totalChargeRef = datetList.get(i).getTotalChargeRef();
            marginCallinfoList.get(i).setDepositShortage(totalChargeRef);
            // レスポンス.追証情報リスト(n).入庫額 及び 決済建玉充当額（決済建玉金額×20%）（T-4～T+0） = 追証情報リスト(n).代用証券入庫額
            String collateralDepositAmt = datetList.get(i).getCollateralDepositAmt();
            marginCallinfoList.get(i).setSettlementOpenInterestYen(collateralDepositAmt);
            // レスポンス.追証情報リスト(n).解消期限/入金・入庫計上日受渡日（T+1） = 受渡日（T+0）～受渡日（T+5）リスト(1).受渡日
            String settlementDate1 = settlementdatetList.get(1).getSettlementDate();
            marginCallinfoList.get(i).setCancellationDeadlineRecordingDateSettlementDate(settlementDate1);
        }
    }
    
    /**
     * 追証情報リストの初期化処理
     *
     * @return 追証情報リスト
     */
    private List<IfaMarginPowerDomesticA001DtoResponseMarginCallinfo> initMarginCallinfoList() {
        
        List<IfaMarginPowerDomesticA001DtoResponseMarginCallinfo> marginCallinfoList = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            IfaMarginPowerDomesticA001DtoResponseMarginCallinfo marginCallinfo = 
                    new IfaMarginPowerDomesticA001DtoResponseMarginCallinfo();
            marginCallinfoList.add(marginCallinfo);
        }
        return marginCallinfoList;
    }
    
    /**
     * 受渡日（T+0）～受渡日（T+5）リストの初期化処理
     *
     * @return 受渡日（T+0）～受渡日（T+5）リスト
     * 
     */
    private List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater>
            initDeliveryDateToFiveDaysLaterList() {
        
        List<IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater> deliveryDateToFiveDaysLaterList = 
                new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater dateToFiveDaysLater = 
                    new IfaMarginPowerDomesticA001DtoResponseDeliveryDateToFiveDaysLater();
            deliveryDateToFiveDaysLaterList.add(dateToFiveDaysLater);
        }
        return deliveryDateToFiveDaysLaterList;
    }
    
    /**
     * 文字列から数値に変換する<br>
     * 数値に該当する文字のみを変換する
     *
     * @param str 変換対象
     * @return 変換された数値
     */
    private long numericalConversion(String str) {
        if (!isNullorEmpty(str)) {
            String replaceTarget = str.replaceAll("[^0-9]", "");
            return Long.parseLong(replaceTarget);
        }
        return 0;
    }
    
    /**
     * 文字列から数値に変換する<br>
     * 数字の先頭にマイナス演算子が入っている場合も許容する<br>
     *
     * @param str 変換対象
     * @return 変換された数値
     */
    private long minusNumericalConversion(String str) {
        if (!isNullorEmpty(str)) {
            String replaceTarget = str.replaceAll("[^-0-9]", "");
            return Long.parseLong(replaceTarget);
        }
        return 0;
    }

    /**
     * 文字列が.
     * 「null,空文字もしくは"△"：空白スペースのみ」だった場合:true
     * 「null,空文字ではなく"△"：空白スペースのみ」でない場合:false
     *
     * @param str 変換対象
     * @return チェック結果
     */
    private boolean isNullorEmpty(String str) {
        if (ObjectUtils.isEmpty(str)) {
           return true;
        }
        return str.matches("^\\s+$");
    }
    
    /**
     * T日～T-4日の追証発生日全て"△"の場合trueを返却する
     *
     * @param datetList 追証情報リスト
     * @return チェック結果
     */
    private boolean isAllHarfWidthSpaceDeficitDate(List<DateT> datetList) {
        return datetList.stream().allMatch(d -> isNullorEmpty(d.getDeficitDate()));
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
     * 対象を任意の数で割り、小数点第2位までの文字列で返却する
     *
     * @param targetVal 対象
     * @param divideVal 割る数
     * @return 文字列変換結果
     */
    private String divideAndStringConversion(String targetVal, String divideVal) {
        BigDecimal targetValue = new BigDecimal(targetVal);
        BigDecimal divideValue = new BigDecimal(divideVal);
        BigDecimal resultValue = targetValue.divide(divideValue);
        String result = resultValue.setScale(2, RoundingMode.DOWN).toPlainString();
        return result;
    }
}
