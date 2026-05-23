package com.sbisec.helios.ap.common.brokerMaintenance.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaDomesticPositionDetailX001DtoRequest;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaDomesticPositionDetailX001DtoResponse;
import com.sbisec.helios.ap.common.brokerMaintenance.service.IfaDomesticPositionDetailService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.SpecificPositionType;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.nri.ifa.NriApiService;

import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1OoutVec;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1OutData;

/**
 * 画面ID：SUB07-05
 * 画面名：建玉詳細(国内)
 * アクションID：X001
 * アクション名：初期化
 *
 * @author 松田
 *     2023/08/14 新規作成
 */
@Component(value = "cmpIfaDomesticPositionDetailService")
public class IfaDomesticPositionDetailServiceImpL implements IfaDomesticPositionDetailService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticPositionDetailServiceImpL.class);
    
    /** 一括個別表示フラグ：一括表示 */
    private static final String BATCH_DISPLAY_FLAG_BULK = "0";
    
    /** 一括個別表示フラグ：個別表示 */
    private static final String BATCH_DISPLAY_FLAG_INDIVIDUAL = "1";
    
    /** 日付未入力設定値 */
    private static final String NO_DATE = "----/--/--";

    /** 期限無し */
    private static final String UNLIMITED = "無期限";

    /** 期限無し（日付） */
    private static final String LAST_TRADE_DATE_UNLIMITED = "99991231";
    
    /** 決済期日短縮あり */
    private static final String LAST_TRADE_DATE_SHORTENING_DISP = "1";
    
    /** API001.期日短縮区分:'1'(決済期日短縮あり) */
    private static final String CHANGE_LAST_TRADE_KBN_SHORTENING = "1";
    
    /** API001.担保区分 */
    private static final List<String> COLLATERAL_DIV_LIST = Arrays.asList(new String[] { "1", "2", "3" });
    
    /** 区分定義.担保規制内容 */
    private static final String COLLATERAL_REGULATIONS = "COLLATERAL_REGULATIONS";
    
    /** W002メッセージ時の引数 */
    private static final String W0002_PARAM = "信用建玉";
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 入力した部店口座は存在しません。 */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 該当する情報は存在しません。 */
    private static final String ERRORS_CMN_INFORMATION_NOTFOUND = "errors.cmn.information.notfound";
    
    /** {0}情報の取得ができませんでした。<br>部店コード:[{1}] 口座番号:[{2}] */
    private static final String W0002 = "W0002";
    
    // --------------------------------
    // API固定値
    // --------------------------------    
    /** API001.リクエストタイプ */
    private static final String API001_REQUEST_TYPE = "61";
    
    /**
     * NRI API呼び出しクラス
     */
    @Autowired
    NriApiService nriApiService;
    
    /**
     * 区分マスタ取得クラス
     */
    @Autowired
    CodeListService codeListService;
    
    /**
     * 共通関数Function001クラス
     */
    @Autowired
    Fct001 fct001;
    
    //    @Autowired
    //    private IfaDomesticPositionDetailDao dao;
    
    /**
     * Dto リクエスト：IfaDomesticPositionDetailX001DtoRequest
     * Dto レスポンス：IfaDomesticPositionDetailX001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaDomesticPositionDetailX001DtoResponse> initializeX001(
            IfaDomesticPositionDetailX001DtoRequest dtoReq) throws Exception {
        
        List<IfaDomesticPositionDetailX001DtoResponse> resDto = new ArrayList<IfaDomesticPositionDetailX001DtoResponse>();
        IfaDomesticPositionDetailX001DtoResponse response = new IfaDomesticPositionDetailX001DtoResponse();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticPositionDetailServiceImplL.initializeX001");
        }
        
        // 初期化
        String errorMessage = StringUtil.EMPTY_STRING;
        String errorMessageId = StringUtil.EMPTY_STRING;
        
        // FCT001：利用者顧客参照権限チェック
        if (!callFct001(dtoReq.getButenCode(), dtoReq.getAccountNumber())) {
            errorMessage = IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST,
                    new String[] { dtoReq.getButenCode(), dtoReq.getAccountNumber() });
            errorMessageId = ERRORS_BUTENACCOUNTNOTEXIST;
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorMessageId, errorMessage);
        }
        
        // API001:建玉残高明細
        QueryMarginContract1OutData api001Res = callQueryMarginContract1(dtoReq);
        if (api001Res != null) {
            String str = setResponse(api001Res, dtoReq, response);
            
            if (StringUtils.equals(str, "1")) {
                // 該当の残高明細が存在しない場合
                errorMessage = IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_NOTFOUND);
                errorMessageId = ERRORS_CMN_INFORMATION_NOTFOUND;
                return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorMessageId, errorMessage);
                
            } else if (StringUtils.equals(str, "2")) {
                // 画面表示対象データ件数が0件の場合
                errorMessage = IfaCommonUtil.getMessage(W0002,
                        new String[] { W0002_PARAM, dtoReq.getButenCode(), dtoReq.getAccountNumber() });
                errorMessageId = W0002;
                return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorMessageId, errorMessage);
            }
        }
    
        resDto.add(response);
        
        DataList<IfaDomesticPositionDetailX001DtoResponse> dtoRes = new DataList<IfaDomesticPositionDetailX001DtoResponse>();
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        
        return dtoRes;
    }

    /**
     * レスポンス編集
     *
     * @param api001Res API取得結果
     * @param dtoReq リクエスト情報
     * @param response レスポンス情報
     * @return エラー判定フラグ
     * @throws IllegalAccessException レスポンス編集処理で例外が発生した場合
     * @throws InvocationTargetException レスポンス編集処理で例外が発生した場合
     */
    private String setResponse(QueryMarginContract1OutData api001Res, IfaDomesticPositionDetailX001DtoRequest dtoReq,
            IfaDomesticPositionDetailX001DtoResponse response)
            throws IllegalAccessException, InvocationTargetException {
        
        // 画面表示対象データ件数
        int displayCount = 0;
        
        // 設定値判定参照リスト
        List<QueryMarginContract1OoutVec> contract1List = new ArrayList<QueryMarginContract1OoutVec>();
        
        // 一括表示フラグ
        boolean isBulkDisp = false;
        if (StringUtils.equals(dtoReq.getBatchIndividualDisplayFlag(), BATCH_DISPLAY_FLAG_BULK)) {
            isBulkDisp = true;
        } else if (StringUtils.equals(dtoReq.getBatchIndividualDisplayFlag(), BATCH_DISPLAY_FLAG_INDIVIDUAL)) {
            isBulkDisp = false;
        }
        
        Optional<QueryMarginContract1OoutVec> opt = api001Res.getQueryMarginContract1Data().stream()
                .filter(s -> StringUtils.equals(dtoReq.getNewOpenInterestNumber(), s.getOpenContractNo())
                        && StringUtils.equals(dtoReq.getParentStockTradeDate(), s.getOrgNewTradeDate()) 
                        && StringUtils.equals(dtoReq.getNewTradeDate(), s.getOpenTradeDate()) 
                        && new BigDecimal(dtoReq.getOpenPrice()).compareTo(new BigDecimal(s.getOpenPrice()).divide(new BigDecimal(100))) == 0
                        ).findFirst();
        boolean isGetTargetInfo = opt.isPresent();
        
        // エラーメッセージフラグ
        String errMsgFlg = "0";
        
        // API取得結果と一括表示フラグの設定値から検索結果数を判定する
        int hitNumber = 0;
        if (!StringUtil.isNullOrEmpty(api001Res.getHitNumber())) {
            hitNumber = Integer.valueOf(api001Res.getHitNumber());
        }
        switch (hitNumber) {
            case 0:
                errMsgFlg = "1";
                return errMsgFlg;
            case 1:
                // 取得件数1件の場合は新規建玉指定番号で指定したものが取れているかチェック
                displayCount = isGetTargetInfo ? 1 : 0;
                break;
            default:
                // 取得件数2件以上の場合
                if (isBulkDisp) {
                    // 一括表示なら全件対象
                    displayCount = hitNumber;
                } else if (isGetTargetInfo) {
                    // 個別表示且つ新規建玉指定番号で指定したものが取れていたら1件
                    displayCount = 1;
                }
        }
        // 1件のみ表示判定
        boolean isDisplaySingle = displayCount == 1;
        
        if (displayCount == 0) {
            // 検索結果：0件ならエラー
            errMsgFlg = "2";
            return errMsgFlg;
        } else if (isDisplaySingle) {
            // 検索結果：1件なら新規建玉指定番号で指定したもの
            contract1List.add(opt.get());
        } else {
            // 検索結果：2件以上なら全件
            contract1List = api001Res.getQueryMarginContract1Data();
        }
        // 表示項目を編集
        setDisplayColumns(response, contract1List, isDisplaySingle);
        
        // リクエスト項目の設定値をコピー
        response.setBrandCode(dtoReq.getBrandCode());
        response.setBrand(dtoReq.getBrandName());
        response.setMarket(dtoReq.getNewOpenMarket());
        response.setNewMarket(dtoReq.getOpenTradeKbn());
        response.setLimit(dtoReq.getPaymentDeadline());
        response.setBatchIndividualDisplayFlag(isBulkDisp ? BATCH_DISPLAY_FLAG_BULK : BATCH_DISPLAY_FLAG_INDIVIDUAL);
        
        // エラーの場合の判定フラグを返却
        return errMsgFlg;
    }
    
    /**
     * 画面項目編集
     *
     * @param response レスポンス情報
     * @param contract1List API取得結果（表示対象のみ）
     * @param isDisplaySingle 1件表示判定
     */
    private void setDisplayColumns(IfaDomesticPositionDetailX001DtoResponse response,
            List<QueryMarginContract1OoutVec> contract1List, boolean isDisplaySingle) {
        
        // 判定用リスト
        List<String> tempList = new ArrayList<String>();
        
        // 親株新規約定日
        tempList = contract1List.stream().filter(c -> !isEmptyColumn(c.getOrgNewTradeDate(), false))
                .map(m -> m.getOrgNewTradeDate()).collect(Collectors.toList());
        response.setPositionDetailDeadLine(getValue(tempList, NO_DATE));
        
        // 建日
        tempList = contract1List.stream().filter(c -> !isEmptyColumn(c.getOpenTradeDate(), false))
                .map(m -> m.getOpenTradeDate()).collect(Collectors.toList());
        response.setOpenTradeDate(getValue(tempList, NO_DATE));
        
        // 返済期限
        // 画面表示対象データ件数が1件の場合
        if (isDisplaySingle) {
            // API001.決済期日が"99991231"の場合
            if (StringUtils.equals(contract1List.get(0).getLastTradeDate(), LAST_TRADE_DATE_UNLIMITED)) {
                response.setLastTradeDate(UNLIMITED); // 無期限
            // 上記以外場合
            } else {
                response.setLastTradeDate(contract1List.get(0).getLastTradeDate()); // API001.決済期日
            }
        // 画面表示対象データ件数が複数件の場合
        } else {
            // 全てのAPI001.期日短縮区分が同じ
            Boolean isChangeLastTradeKbnSame = contract1List.stream()
            .map(QueryMarginContract1OoutVec::getChangeLastTradeKbn)
            .distinct()
            .count() <= 1;

            // 全てのAPI001.決済期日が"99991231"
            Boolean isLastTradeDateAllUnlimited = contract1List.stream()
            .allMatch(data -> LAST_TRADE_DATE_UNLIMITED.equals(data.getLastTradeDate()));
            
            // 全てのAPI001.決済期日が同じ
            Boolean isLastTradeDateSame = contract1List.stream()
            .map(QueryMarginContract1OoutVec::getLastTradeDate)
            .distinct()
            .count() <= 1;
            
            // 全てのAPI001.期日短縮区分が同じ、かつ全てのAPI001.決済期日が"99991231"の場合
            if (isChangeLastTradeKbnSame && isLastTradeDateAllUnlimited) {
                response.setLastTradeDate(UNLIMITED); // 無期限
            // 全てのAPI001.期日短縮区分が同じ、かつ全てのAPI001.決済期日が同じ場合
            } else if (isChangeLastTradeKbnSame && isLastTradeDateSame) {
                response.setLastTradeDate(contract1List.get(0).getLastTradeDate()); // API001.決済期日
            // 上記以外
            } else {
                response.setLastTradeDate(NO_DATE); // 未設定固定
            }
        }
        
        // 期日短縮区分フラグ
        // 画面表示対象データ件数が1件の場合
        if (isDisplaySingle) {
            // API001.決済期日が"99991231"ではなく、かつAPI001.期日短縮区分が'1'(決済期日短縮あり)の場合
            if (!StringUtils.equals(contract1List.get(0).getLastTradeDate(), LAST_TRADE_DATE_UNLIMITED)
                && StringUtils.equals(contract1List.get(0).getChangeLastTradeKbn(), CHANGE_LAST_TRADE_KBN_SHORTENING)) {
                response.setRepayPeriodShorterFlag(LAST_TRADE_DATE_SHORTENING_DISP); // "1"
            } else {
                response.setRepayPeriodShorterFlag(null); // 未設定
            }
        // 画面表示対象データ件数が複数件の場合
        } else {
            // 全てのAPI001.決済期日が同じ
            Boolean isLastTradeDateSame = contract1List.stream()
            .map(QueryMarginContract1OoutVec::getLastTradeDate)
            .distinct()
            .count() <= 1;

            // 全てのAPI001.期日短縮区分が'1'(決済期日短縮あり)
            Boolean isChangeLastTradeKbnShortening = contract1List.stream()
            .allMatch(data -> LAST_TRADE_DATE_SHORTENING_DISP.equals(data.getChangeLastTradeKbn()));

            // API001.決済期日が"99991231"ではなく、かつ全てのAPI001.決済期日が同じ、かつ全てのAPI001.期日短縮区分が'1'(決済期日短縮あり)の場合
            if (!StringUtils.equals(contract1List.get(0).getLastTradeDate(), LAST_TRADE_DATE_UNLIMITED) 
                && isLastTradeDateSame 
                && isChangeLastTradeKbnShortening) {
                response.setRepayPeriodShorterFlag(LAST_TRADE_DATE_SHORTENING_DISP); // '1'
            } else {
                response.setRepayPeriodShorterFlag(null); // 未設定
            }
        }
        
        // 預り区分
        tempList = contract1List.stream().filter(c -> !isEmptyColumn(c.getTokuteiContractId(), false))
                .map(m -> m.getTokuteiContractId()).collect(Collectors.toList());
        String depositTypeValue = getValue(tempList, null);
        if (depositTypeValue != null) {
            SpecificPositionType depositType = SpecificPositionType.valueOfId(depositTypeValue);
            
            response.setDepositType(
                    depositType == null ? null : SpecificPositionType.valueOfId(depositTypeValue).getId());
        } else {
            response.setDepositType(null);
        }
        
        // 建株数合計
        tempList = contract1List.stream().filter(c -> !isEmptyColumn(c.getContPosition(), false))
                .map(m -> m.getContPosition()).collect(Collectors.toList());
        BigDecimal constPosition = getSumValue(tempList);
        response.setStockNumTotal(constPosition.toString());
        
        // 建代金合計
        tempList = contract1List.stream().filter(c -> !isEmptyColumn(c.getOpenAmount(), false))
                .map(m -> m.getOpenAmount()).collect(Collectors.toList());
        BigDecimal openAmount = getSumValue(tempList);
        response.setConstructionPriceTotal(openAmount.toString());
        
        // 平均建単価
        response.setPositionPriceLabel(openAmount.divide(constPosition, 2, RoundingMode.DOWN).toString());
        
        // 新規建手数料（税込）
        tempList = contract1List.stream().filter(c -> !isEmptyColumn(c.getOpenCommission(), true))
                .map(m -> String.valueOf(Double.valueOf(m.getOpenCommission()) + Double.valueOf(m.getCommissionTax())))
                .collect(Collectors.toList());
        BigDecimal domesticNewComm = getSumValue(tempList);
        response.setDomesticNewComm(domesticNewComm == BigDecimal.ZERO ? null : domesticNewComm.setScale(0).toString());
        
        // 管理料
        tempList = contract1List.stream().filter(c -> !isEmptyColumn(c.getMaintenanceFee(), false))
                .map(m -> m.getMaintenanceFee()).collect(Collectors.toList());
        BigDecimal managePrice = getSumValue(tempList);
        response.setManagePrice(managePrice == BigDecimal.ZERO ? null : managePrice.toString());
        
        // 権利処理等手数料
        tempList = contract1List.stream().filter(c -> !isEmptyColumn(c.getTranscerFee(), false))
                .map(m -> m.getTranscerFee()).collect(Collectors.toList());
        BigDecimal rightProcessingCharge = getSumValue(tempList);
        response.setRightProcessingCharge(
                rightProcessingCharge == BigDecimal.ZERO ? null : rightProcessingCharge.toString());
        
        // 日歩
        tempList = contract1List.stream().filter(c -> !isEmptyColumn(c.getInterest(), false)).map(m -> m.getInterest())
                .collect(Collectors.toList());
        BigDecimal dailyInterest = getSumValue(tempList);
        response.setDailyInterest(dailyInterest == BigDecimal.ZERO ? null
                : dailyInterest.divide(BigDecimal.valueOf(100), 0, RoundingMode.DOWN).toString());
        
        // 逆日歩および貸株料
        tempList = contract1List.stream().filter(c -> !isEmptyColumn(c.getNegativeIntrst(), false))
                .map(m -> m.getNegativeIntrst()).collect(Collectors.toList());
        
        BigDecimal reverseDailyInterest = getSumValue(tempList);
        response.setReverseDailyInterest(reverseDailyInterest == BigDecimal.ZERO ? null
                : reverseDailyInterest.divide(BigDecimal.valueOf(100), 0, RoundingMode.DOWN).toString());
        
        // 合計
        tempList = contract1List.stream().filter(c -> !isEmptyColumn(c.getCost(), false)).map(m -> m.getCost())
                .collect(Collectors.toList());
        BigDecimal total = getSumValue(tempList);
        response.setTotal(total == BigDecimal.ZERO ? null : total.toString());
        
        // 新規建保証金率
        tempList = contract1List.stream().filter(c -> !isEmptyColumn(c.getBargainNeedSecRate(), true)).map(m -> {
            double value = Double.parseDouble(m.getBargainNeedSecRate());
            value /= 100; // 1/100の処理
            return String.valueOf(value);
        }).collect(Collectors.toList());
        response.setNewDepositRate(getValue(tempList, null));
        
        // 現金保証金率
        tempList = contract1List.stream().filter(c -> !isEmptyColumn(c.getCashSecurityRate(), true)).map(m -> {
            double value = Double.parseDouble(m.getCashSecurityRate());
            value /= 100; // 1/100の処理
            return String.valueOf(value);
        }).collect(Collectors.toList());
        response.setCashDepositRate(getValue(tempList, null));
        
        // 現物買付保証金率
        tempList = contract1List.stream().filter(c -> !isEmptyColumn(c.getBuySecurityRate(), true)).map(m -> {
            double value = Double.parseDouble(m.getBuySecurityRate());
            value /= 100; // 1/100の処理
            return String.valueOf(value);
        }).collect(Collectors.toList());
        response.setCashBuyDepositRate(getValue(tempList, null));
        
        // 出金・振替保証金率
        tempList = contract1List.stream().filter(c -> !isEmptyColumn(c.getSellSecurityRate(), true)).map(m -> {
            double value = Double.parseDouble(m.getSellSecurityRate());
            value /= 100; // 1/100の処理
            return String.valueOf(value);
        }).collect(Collectors.toList());
        response.setWithdrawTransferDepositRate(getValue(tempList, null));
        
        // 増担保規制
        /* 重複無しで担保区分をList化 */
        tempList = contract1List.stream().map(m -> m.getRegulateKbn()).distinct().collect(Collectors.toList());
        /* 同一値のみ、且つ COLLATERAL_DIV_LISTに該当する値であれば設定　*/
        if (!CollectionUtils.isEmpty(tempList) && tempList.size() == 1
                && COLLATERAL_DIV_LIST.contains(tempList.get(0))) {
            response.setAdditionalCollateralRegulations("適用（ " + codeListService.getValue(COLLATERAL_REGULATIONS,
                    contract1List.stream().findFirst().get().getRegulateKbn()) + "）");
        } else {
            response.setAdditionalCollateralRegulations(null);
        }
    }
    
    /**
     * リストの値を合計して返却する
     *
     * @param filterList 合計値計算対象リスト
     * @return 合計値
     */
    private BigDecimal getSumValue(List<String> filterList) {
        
        BigDecimal resultSumValue = BigDecimal.ZERO;
        
        if (!CollectionUtils.isEmpty(filterList)) {
            List<BigDecimal> convertList = filterList.stream().map(m -> StringUtil.parseBigDecimal(m))
                    .collect(Collectors.toList());
            resultSumValue = convertList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        
        return resultSumValue;
        
    }
    
    /**
     * 返却値判定
     *
     * @param filterList チェック対象リスト(未設定値が除かれている前提)
     * @param deputyValue 他に値が存在した/値が存在しない時の設定値
     * @return 全て同じだった時の値
     */
    private String getValue(List<String> filterList, String deputyValue) {
        
        if (CollectionUtils.isEmpty(filterList)) {
            return deputyValue;
        }
        Stream<String> list = filterList.stream();
        // List内の設定値を取得し、他の設定値が存在するかチェックする
        String tempValue = filterList.get(0);
        if (list.filter(f -> !StringUtils.equals(f, tempValue)).findFirst().isPresent()) {
            return deputyValue;
        }
        return tempValue;
    }
    
    /**
     * 項目値未設定判定
     *
     * @param columnValue 確認対象項目値
     * @param iseEmptyZero :設定値「0」を未入力判定とするか
     * @return 未設定：true 設定有り：false
     */
    private boolean isEmptyColumn(String columnValue, boolean iseEmptyZero) {
        
        if (columnValue == null) {
            return true;
        }
        // スペース削除
        String judgeValue = StringUtil.trim(columnValue);
        
        if (StringUtil.isNullOrEmpty(judgeValue)) {
            return true;
        }
        
        if (iseEmptyZero && StringUtils.equals(judgeValue, "0")) {
            return true;
        }
        
        return false;
    }
    
    /**
     * API001
     * NRI_QueryMarginContract1
     * 建玉残高明細
     *
     * @param dtoReq リクエスト情報
     * @return API結果
     */
    private QueryMarginContract1OutData callQueryMarginContract1(IfaDomesticPositionDetailX001DtoRequest dtoReq) {
        
        List<QueryMarginContract1OutData> apiRes = new ArrayList<>();
        try {
            apiRes = nriApiService.queryMarginContract1List(dtoReq.getButenCode(), dtoReq.getAccountNumber(),
                    dtoReq.getBrandCode(), dtoReq.getOpenTradeKbn(), dtoReq.getNewOpenMarket(), dtoReq.getPaymentDeadline(),
                    API001_REQUEST_TYPE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 実装済みのコードの形式に合わせるため、繰り返し部を1つのQueryMarginContract1OutDataに詰める。
        QueryMarginContract1OutData result = apiRes.get(0);
        for (int i = 1; i < apiRes.size(); i++) {
            result.getQueryMarginContract1Data().addAll(apiRes.get(i).getQueryMarginContract1Data());
        }
        
        return result;
    }
    
    /**
     * FCT001チェック
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return API結果
     */
    private boolean callFct001(String butenCode, String accountNumber) {
        
        InputFct001Dto input = new InputFct001Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        
        OutputFct001Dto output = fct001.doCheck(input);
        if (output != null) {
            if (StringUtils.equals(output.getTargetCustomerRefAuthFlag(), Fct001.TARGET_CUSTOMER_REF_AUTH_FLAG_0)) {
                return false;
            }
        }
        return true;
    }
    
}
