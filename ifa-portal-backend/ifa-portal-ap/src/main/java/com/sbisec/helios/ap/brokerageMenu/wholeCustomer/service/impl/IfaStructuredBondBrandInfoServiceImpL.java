package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaStructuredBondBrandInfoDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaStructuredBondBrandInfoA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaStructuredBondBrandInfoA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaStructuredBondBrandInfoDtoResponseEarlyStageRedemptionJudgmentDateRateJudgmentDate;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaStructuredBondBrandInfoDtoResponseReferenceBrand;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaStructuredBondBrandInfoService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID：SUB020302_0104-02
 * 画面名：仕組債銘柄情報
 *
 * @author SCSK川崎
 2024/06/11 新規作成
 */
@Component(value = "cmpIfaStructuredBondBrandInfoService")
public class IfaStructuredBondBrandInfoServiceImpL implements IfaStructuredBondBrandInfoService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaStructuredBondBrandInfoServiceImpL.class);
    
    /** DAO */
    @Autowired
    private IfaStructuredBondBrandInfoDao dao;

    // --------------------------------
    // メッセージ
    // --------------------------------\
    /** {0}入力した銘柄コードは存在しません。<br>銘柄コード: [{1}] \{0}：※設定無し {1}：A001リクエスト.銘柄コード */
    private static final String ERRORS_BRANDCODENOTEXIST = "errors.brandCodeNotExist";

    // --------------------------------
    // 設定値
    // --------------------------------
    /** BLANK */
    private static final String BLANK = "";
    
    /** HYPHEN */
    private static final String HYPHEN = "-";

    /** SEPARATOR */
    private static final String SEPARATOR = "、";
    
    /** シーケンス番号 80007 */
    private static final int SEQ_NO_80007 = 80007;

    /** シーケンス番号 80008 */
    private static final int SEQ_NO_80008 = 80008;

    /** シーケンス番号 80040 */
    private static final int SEQ_NO_80040 = 80040;
    
    /** 正常終了メッセージコード */
    private static final String SUCCESS_MESSAGE_CODE = "SUCCESS";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaStructuredBondBrandInfoA001DtoRequest
     * Dto レスポンス：IfaStructuredBondBrandInfoA001DtoResponse
     * model リクエスト：IfaStructuredBondBrandInfoSql007RequestModel
     * model レスポンス：IfaStructuredBondBrandInfoSql007ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStructuredBondBrandInfoA001DtoResponse> initializeA001(
            IfaStructuredBondBrandInfoA001DtoRequest dtoReq) throws Exception {
        DataList<IfaStructuredBondBrandInfoA001DtoResponse> dtoRes = new DataList<IfaStructuredBondBrandInfoA001DtoResponse>();
        List<IfaStructuredBondBrandInfoA001DtoResponse> resDto = new ArrayList<IfaStructuredBondBrandInfoA001DtoResponse>();
        IfaStructuredBondBrandInfoA001DtoResponse res = new IfaStructuredBondBrandInfoA001DtoResponse();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaStructuredBondBrandInfoServiceImplL.initializeA001");
        }
        
        // SQL001:仕組債マスタ情報を取得
        IfaStructuredBondBrandInfoSql001RequestModel selSql001Req = new IfaStructuredBondBrandInfoSql001RequestModel();
        selSql001Req.setBrandCode(dtoReq.getBrandCode());
        DataList<IfaStructuredBondBrandInfoSql001ResponseModel> selSql001Res = 
                dao.selectIfaStructuredBondBrandInfoSql001(selSql001Req);
        
        if (selSql001Res.getDataList().size() == 0) {
            // 取得データが0件の場合、エラー
            String errorCode = ERRORS_BRANDCODENOTEXIST;
            String errorMessage = IfaCommonUtil.getMessage(errorCode, new String[] { BLANK, dtoReq.getBrandCode() });
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        // SQL001結果をレスポンスにセット
        convertSql001ResToDto(selSql001Res.getDataList().get(0), res);
        
        // SQL002:参照営業日カレンダーを取得
        IfaStructuredBondBrandInfoSql002RequestModel selSql002Req = new IfaStructuredBondBrandInfoSql002RequestModel();
        selSql002Req.setBrandCode(dtoReq.getBrandCode());
        DataList<IfaStructuredBondBrandInfoSql002ResponseModel> selSql002Res = 
                dao.selectIfaStructuredBondBrandInfoSql002(selSql002Req);
        
        if (selSql002Res.getDataList().size() == 0) {
            // 取得データが0件の場合
            res.setBusinessDayCalendar(HYPHEN);
        } else {
            List<String> businessDayCalendarList = new ArrayList<String>();
            for (IfaStructuredBondBrandInfoSql002ResponseModel item : selSql002Res.getDataList()) {
                businessDayCalendarList.add(item.getFlexField04());
            }
            res.setBusinessDayCalendar(String.join(SEPARATOR, businessDayCalendarList));
        }
        
        // SQL003:参照取引所カレンダーを取得
        IfaStructuredBondBrandInfoSql003RequestModel selSql003Req = new IfaStructuredBondBrandInfoSql003RequestModel();
        selSql003Req.setBrandCode(dtoReq.getBrandCode());
        DataList<IfaStructuredBondBrandInfoSql003ResponseModel> selSql003Res = 
                dao.selectIfaStructuredBondBrandInfoSql003(selSql003Req);
        
        if (selSql003Res.getDataList().size() == 0) {
            // 取得データが0件の場合
            res.setExchangeCalendar(HYPHEN);
        } else {
            List<String> exchangeCalendarList = new ArrayList<String>();
            for (IfaStructuredBondBrandInfoSql003ResponseModel item : selSql003Res.getDataList()) {
                exchangeCalendarList.add(item.getFlexField04());
            }
            res.setExchangeCalendar(String.join(SEPARATOR, exchangeCalendarList));
        }
        
        // SQL004:ハイクーポン端数計算の表示文言を取得
        res.setHighCouponFractionCalculation(
                executeSql004(SEQ_NO_80007, selSql001Res.getDataList().get(0).getSbmHighCouponCalc()));
        // SQL004:ハイクーポン端数処理の表示文言を取得
        res.setHighCouponRounding(
                executeSql004(SEQ_NO_80008, selSql001Res.getDataList().get(0).getSbmHighCouponRound()));
        // SQL004:ミッドクーポン端数計算の表示文言を取得
        res.setMidCouponFractionCalculation(
                executeSql004(SEQ_NO_80007, selSql001Res.getDataList().get(0).getSbmMidCouponCalc()));
        // SQL004:ミッドクーポン端数処理の表示文言を取得
        res.setMidCouponRounding(
                executeSql004(SEQ_NO_80008, selSql001Res.getDataList().get(0).getSbmMidCouponRound()));
        // SQL004:ロークーポン端数計算の表示文言を取得
        res.setLowCouponFractionCalculation(
                executeSql004(SEQ_NO_80007, selSql001Res.getDataList().get(0).getSbmLowCouponCalc()));
        // SQL004:ロークーポン端数処理の表示文言を取得
        res.setLowCouponRounding(
                executeSql004(SEQ_NO_80008, selSql001Res.getDataList().get(0).getSbmLowCouponRound()));
        
        // SQL005:仕組債の早期償還判定日リストを取得
        IfaStructuredBondBrandInfoSql005RequestModel selSql005Req = new IfaStructuredBondBrandInfoSql005RequestModel();
        selSql005Req.setBrandCode(dtoReq.getBrandCode());
        DataList<IfaStructuredBondBrandInfoSql005ResponseModel> selSql005Res = 
                dao.selectIfaStructuredBondBrandInfoSql005(selSql005Req);
        
        List<IfaStructuredBondBrandInfoDtoResponseEarlyStageRedemptionJudgmentDateRateJudgmentDate> judgmentDateList = 
                new ArrayList<IfaStructuredBondBrandInfoDtoResponseEarlyStageRedemptionJudgmentDateRateJudgmentDate>();
        Integer number = 1;
        for (IfaStructuredBondBrandInfoSql005ResponseModel item : selSql005Res.getDataList()) {
            IfaStructuredBondBrandInfoDtoResponseEarlyStageRedemptionJudgmentDateRateJudgmentDate judgmentDate = 
                    new IfaStructuredBondBrandInfoDtoResponseEarlyStageRedemptionJudgmentDateRateJudgmentDate();
            judgmentDate.setJudgmentDate(item.getSbepEarlyPaymentJudgeDate());
            judgmentDate.setNumber(number.toString());
            judgmentDateList.add(judgmentDate);
            number++;
        }
        res.setEarlyStageRedemptionJudgmentDateRateJudgmentDateList(judgmentDateList);
        
        // SQL004:通貨ペアの表示文言を取得
        res.setCurrencyPair(
                executeSql004(SEQ_NO_80040, selSql001Res.getDataList().get(0).getSbmCurrencyPair()));
        
        // SQL006:仕組債の価格・判定水準情報を取得
        IfaStructuredBondBrandInfoSql006RequestModel selSql006Req = new IfaStructuredBondBrandInfoSql006RequestModel();
        selSql006Req.setBrandCode(dtoReq.getBrandCode());
        DataList<IfaStructuredBondBrandInfoSql006ResponseModel> selSql006Res = 
                dao.selectIfaStructuredBondBrandInfoSql006(selSql006Req);
        
        // SQL006結果をレスポンスにセット
        convertSql006ResToDto(selSql006Res.getDataList(), res);
        
        if (selSql006Res.getDataList().size() != 0) {
            // SQL004:ノックイン判定端数計算の表示文言を取得
            res.setKnockInJudgmentFractionCalculation(
                    executeSql004(SEQ_NO_80007, selSql006Res.getDataList().get(0).getSbjmNockinCalc()));
            // SQL004:ノックイン判定端数処理の表示文言を取得
            res.setKnockInJudgmentRounding(
                    executeSql004(SEQ_NO_80008, selSql006Res.getDataList().get(0).getSbjmNockinRound()));
            // SQL004:早期償還判定端数計算の表示文言を取得
            res.setEarlyRedemptionJudgmentFractionCalculation(
                    executeSql004(SEQ_NO_80007, selSql006Res.getDataList().get(0).getSbjmEarlyPaymentCalc()));
            // SQL004:早期償還判定端数処理の表示文言を取得
            res.setEarlyRedemptionJudgmentRounding(
                    executeSql004(SEQ_NO_80008, selSql006Res.getDataList().get(0).getSbjmEarlyPaymentRound()));
            // SQL004:早期償還判定水準ステップダウン下限端数計算の表示文言を取得
            res.setEarlyRedemptionJudgmentStepDownLowerLimitFractionCalculation(
                    executeSql004(SEQ_NO_80007, selSql006Res.getDataList().get(0).getSbjmEarlyPldMinCalc()));
            // SQL004:早期償還判定水準ステップダウン下限端数処理の表示文言を取得
            res.setEarlyRedemptionJudgmentStepDownLowerLimitRounding(
                    executeSql004(SEQ_NO_80008, selSql006Res.getDataList().get(0).getSbjmEarlyPldMinRound()));
            // SQL004:行使価格端数計算の表示文言を取得
            res.setUsePriceFractionCalculation(
                    executeSql004(SEQ_NO_80007, selSql006Res.getDataList().get(0).getSbjmExercisePriceCalc()));
            // SQL004:行使価格端数処理の表示文言を取得
            res.setUsePriceRounding(
                    executeSql004(SEQ_NO_80008, selSql006Res.getDataList().get(0).getSbjmExercisePriceRound()));
            // SQL004:満期償還端数計算の表示文言を取得
            res.setMaturityRedemptionFractionCalculation(
                    executeSql004(SEQ_NO_80007, selSql006Res.getDataList().get(0).getSbjmExpirePaymentCalc()));
            // SQL004:満期償還端数処理の表示文言を取得
            res.setMaturityRedemptionRounding(
                    executeSql004(SEQ_NO_80008, selSql006Res.getDataList().get(0).getSbjmExpirePaymentRound()));
            // SQL004:ノックイン免除判定端数計算の表示文言を取得
            res.setKnockInExemptFractionCalculation(
                    executeSql004(SEQ_NO_80007, selSql006Res.getDataList().get(0).getSbjmNockinExemptionCalc()));
            // SQL004:ノックイン免除判定端数処理の表示文言を取得
            res.setKnockInExemptRounding(
                    executeSql004(SEQ_NO_80008, selSql006Res.getDataList().get(0).getSbjmNockinExemptionRound()));
        } else {
            // SQL006の結果が0件の時は全てハイフンをセット
            res.setKnockInJudgmentFractionCalculation(HYPHEN);
            res.setKnockInJudgmentRounding(HYPHEN);
            res.setEarlyRedemptionJudgmentFractionCalculation(HYPHEN);
            res.setEarlyRedemptionJudgmentRounding(HYPHEN);
            res.setEarlyRedemptionJudgmentStepDownLowerLimitFractionCalculation(HYPHEN);
            res.setEarlyRedemptionJudgmentStepDownLowerLimitRounding(HYPHEN);
            res.setUsePriceFractionCalculation(HYPHEN);
            res.setUsePriceRounding(HYPHEN);
            res.setMaturityRedemptionFractionCalculation(HYPHEN);
            res.setMaturityRedemptionRounding(HYPHEN);
            res.setKnockInExemptFractionCalculation(HYPHEN);
            res.setKnockInExemptRounding(HYPHEN);
        }

        // SQL007:参照銘柄リストを取得
        IfaStructuredBondBrandInfoSql007RequestModel selSql007Req = new IfaStructuredBondBrandInfoSql007RequestModel();
        selSql007Req.setBrandCode(dtoReq.getBrandCode());
        DataList<IfaStructuredBondBrandInfoSql007ResponseModel> selSql007Res = 
                dao.selectIfaStructuredBondBrandInfoSql007(selSql007Req);
        
        List<IfaStructuredBondBrandInfoDtoResponseReferenceBrand> referenceBrandList = 
                new ArrayList<IfaStructuredBondBrandInfoDtoResponseReferenceBrand>();
        for (IfaStructuredBondBrandInfoSql007ResponseModel item : selSql007Res.getDataList()) {
            IfaStructuredBondBrandInfoDtoResponseReferenceBrand referenceBrand = 
                    new IfaStructuredBondBrandInfoDtoResponseReferenceBrand();
            convertSql007ResToDto(item, referenceBrand);
            referenceBrandList.add(referenceBrand);
        }
        res.setReferenceBrandList(referenceBrandList);
        
        resDto.add(res);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, SUCCESS_MESSAGE_CODE, null);
        return dtoRes;
        
    }
    
    /**
     * SQL001の取得データをレスポンスDTOに詰め替える
     *
     * @param item SQL001取得データ
     * @param res A001レスポンスDTO
     */
    private void convertSql001ResToDto(
            IfaStructuredBondBrandInfoSql001ResponseModel item, IfaStructuredBondBrandInfoA001DtoResponse res) {
        
        // 債券コード
        res.setBondCode(item.getSbmBondCode());
        // ISINコード
        res.setIsinCode(item.getSbmIsinCode());
        // 債券名
        res.setBondName(item.getSbmBondName());
        // 発行日
        res.setIssueDate(item.getSbmIssueDate());
        // 満期償還日
        res.setMaturityRedemptionDate(item.getSbmPaymentDate());
        // 公募私募区分
        res.setPublicPrivateOfferingName(item.getSbmSubscriptionWay());
        // ノックインイベント区分
        res.setKnockInEventType(item.getSbmNockinEventKbn());
        // 早期償還イベント区分
        res.setEarlyRedemptionEventType(item.getSbmEarlyPaymentKbn());
        // 取引所判断日数
        res.setExchangeJudgmentDays(item.getSbmBeforeTradeDays());
        // ノンコール期間
        res.setNonCallPeriod(item.getSbmNocallPeriod());
        // 適用利率
        res.setAppInterest(item.getSbmCurrentRate());
        // クーポン種類
        res.setCouponKind(item.getSbmCouponType());
        // 固定クーポン
        res.setCoupon(item.getSbmFixCoupon());
        // 固定クーポン回数
        res.setCouponCount(item.getSbmFixCouponTimes());
        // ハイ判定水準
        res.setHighJudgmentLevel(item.getSbmHighCouponLevel());
        // ハイクーポン
        res.setHighCoupon(item.getSbmHighCoupon());
        // ミッド判定水準
        res.setMidJudgmentLevel(item.getSbmMidCouponLevel());
        // ミッドクーポン
        res.setMidCoupon(item.getSbmMidCoupon());
        // ロー判定水準
        res.setLowJudgmentLevel(item.getSbmLowCouponLevel());
        // ロークーポン
        res.setLowCoupon(item.getSbmLowCoupon());
        // 満期償還判定日
        res.setMaturityRedemptionJudgmentDate(item.getSbmPaymentJudgementDate());
        // 当初価格設定判定日
        res.setInitiallyPriceSettingJudgmentDate(item.getSbmFirstJudgementDate());
        // 観察期間from
        res.setObservationPeriodFrom(item.getSbmObservationPeriodFrom());
        // 観察期間to
        res.setObservationPeriodTo(item.getSbmObservationPeriodTo());
        // 参照値
        res.setReferenceValue(item.getSbmValueType());
        // 使用レート
        res.setUseRate(item.getSbmUseRate());
        // レバレッジ
        res.setLeverage(item.getSbmLeverage());
        // ノックイン免除有無
        res.setKnockInExemptExistence(item.getSbmNockinExemptionKbn());
        // ノックイン免除観察期間From
        res.setKnockInExemptobservationPeriodFrom(item.getSbmNockinExemptionFrom());
        // ノックイン免除観察期間To
        res.setKnockInExemptobservationPeriodTo(item.getSbmNockinExemptionTo());
        // 早期償還メモリ有無
        res.setEarlyRedemptionMemoryExistence(item.getSbmKoMemoryKbn());

    }
    
    /**
     * SQL004を実行し汎用フィールド4（表示用文言）を取得する
     *
     * @param seqNo シーケンス番号（大）
     * @param displayItemCode 表示項目コード
     * @return 表示用文言
     * @throws Exception 例外
     */
    private String executeSql004(int seqNo, String displayItemCode) throws Exception {
        
        if (Objects.isNull(displayItemCode)) {
            // 表示項目コードがnullの場合はハイフン
            return HYPHEN;
        }
        
        IfaStructuredBondBrandInfoSql004RequestModel selSql004Req = new IfaStructuredBondBrandInfoSql004RequestModel();
        selSql004Req.setSeqNo(seqNo);
        selSql004Req.setDisplayItemCode(displayItemCode);
        DataList<IfaStructuredBondBrandInfoSql004ResponseModel> selSql004Res = 
                dao.selectIfaStructuredBondBrandInfoSql004(selSql004Req);
        if (selSql004Res.getDataList().size() == 0) {
            // データ0件の場合はハイフン
            return HYPHEN;
        } else {
            return selSql004Res.getDataList().get(0).getFlexField04();
        }
        
    }

    /**
     * SQL006の取得データをレスポンスDTOに詰め替える
     *
     * @param itemList SQL006取得データ
     * @param res A001レスポンスDTO
     */
    private void convertSql006ResToDto(List<IfaStructuredBondBrandInfoSql006ResponseModel> itemList,
            IfaStructuredBondBrandInfoA001DtoResponse res) {
    
        if (itemList.size() == 0) {
            // データが0件の場合
            // ノックイン判定水準
            res.setKnockInJudgmentLevel(HYPHEN);
            // 早期償還判定水準
            res.setEarlyRedemptionJudgmentLevel(HYPHEN);
            // 早期償還判定水準ステップダウン有無
            res.setEarlyRedemptionJudgmentStepDown(HYPHEN);
            // 早期償還判定水準ステップダウン開始回目
            res.setStepDownStartCount(HYPHEN);
            // 早期償還判定水準ステップダウン設定値
            res.setEarlyRedemptionJudgmentStepDownSettingValue(HYPHEN);
            // 早期償還判定水準ステップダウン下限
            res.setEarlyRedemptionJudgmentStepDownLowerLimit(HYPHEN);
            // 早期償還価格比率
            res.setEarlyRedemptionPriceRatio(HYPHEN);
            // 行使価格比率
            res.setUsePriceRatio(HYPHEN);
            // 満期償還判定水準
            res.setMaturityRedemptionJudgmentLevel(HYPHEN);
            // 満期償還価格比率
            res.setMaturityRedemptionPriceRatio(HYPHEN);
            // ノックイン免除判定水準
            res.setKnockInExemptJudgmentLevel(HYPHEN);
            
        } else {
            IfaStructuredBondBrandInfoSql006ResponseModel item = itemList.get(0);
            // ノックイン判定水準
            res.setKnockInJudgmentLevel(item.getSbjmNockinLevel());
            // 早期償還判定水準
            res.setEarlyRedemptionJudgmentLevel(item.getSbjmEarlyPaymentLevel());
            // 早期償還判定水準ステップダウン有無
            res.setEarlyRedemptionJudgmentStepDown(item.getSbjmEarlyPldFlg());
            // 早期償還判定水準ステップダウン開始回目
            res.setStepDownStartCount(item.getSbjmEarlyPldFrom());
            // 早期償還判定水準ステップダウン設定値
            res.setEarlyRedemptionJudgmentStepDownSettingValue(item.getSbjmEarlyPldValue());
            // 早期償還判定水準ステップダウン下限
            res.setEarlyRedemptionJudgmentStepDownLowerLimit(item.getSbjmEarlyPldMin());
            // 早期償還価格比率
            res.setEarlyRedemptionPriceRatio(item.getSbjmEarlyPaymentPercent());
            // 行使価格比率
            res.setUsePriceRatio(item.getSbjmExercisePricePercent());
            // 満期償還判定水準
            res.setMaturityRedemptionJudgmentLevel(item.getSbjmPaymentCheckLevel());
            // 満期償還価格比率
            res.setMaturityRedemptionPriceRatio(item.getSbjmExpirePaymentPercent());
            // ノックイン免除判定水準
            res.setKnockInExemptJudgmentLevel(item.getSbjmNockinExemptionLevel());

        }
    }
    
    /**
     * SQL007の取得データを参照銘柄クラスに詰め替える
     *
     * @param item SQL007取得データ
     * @param referenceBrand 参照銘柄
     */
    private void convertSql007ResToDto(IfaStructuredBondBrandInfoSql007ResponseModel item, 
            IfaStructuredBondBrandInfoDtoResponseReferenceBrand referenceBrand) {
        
        // 表示順
        referenceBrand.setDisporder(item.getSbjmDisplayOrder());
        // 判定銘柄種別
        referenceBrand.setJudgmentBrandClass(item.getSbjmGroupCode());
        // 判定市場コード
        referenceBrand.setJudgmentMarketCode(item.getSbjmExchangeCode());
        // 判定銘柄コード
        referenceBrand.setJudgmentBrandCode(item.getSbjmTickerCode());
        // 判定銘柄名
        referenceBrand.setJudgmentBrandName(item.getSbjmBrandName());
        // 単位
        referenceBrand.setUnit(item.getFlexField04());
        // 当初価格
        referenceBrand.setInitiallyPrice(item.getSbjmFirstPrice());
        // ノックイン水準価格
        referenceBrand.setKnockInLevelPrice(item.getSbjmNockinPrice());
        // 早期償還水準価格
        referenceBrand.setEarlyRedemptionLevelPrice(item.getSbjmEarlyPaymentPrice());
        // ノックイン発生日
        referenceBrand.setKnockInAccuralDate(item.getSbjmNockinOnsetDate());
        // ノックイン発生時価格
        referenceBrand.setKnockInAccuralTimePrice(item.getSbjmNockinOnsetPrice());
        
    }
    
}
