package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeTrendSearchCsvItems;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 画面ID：SUB020302_0401-01
 * 画面名：取引動向検索
 *
 * @author SBI 苗萌
 * 2025/04/10 新規作成
 */
public class IfaTradeTrendSearchCsvOut extends CsvOutPutUtil {
    
    // --------------------------------
    // 表示明細/CSV出力項目パターン
    // --------------------------------   
    /** パターンNo1.集計単位：仲介業者 */
    private static final String PATTERN_NO1_BROKER = "1";
    
    /** パターンNo2.集計単位：営業員 */
    private static final String PATTERN_NO2_BROKER_CHARGE = "2";
    
    /** パターンNo3.集計単位：顧客 */
    private static final String PATTERN_NO3_CUSTOMER = "3";
    
    // 期間指定（From）= 期間指定（To）
    /** パターンNo4.集計単位：仲介業者 */
    private static final String PATTERN_NO4_BROKER = "4";
    
    /** パターンNo5.集計単位：営業員 */
    private static final String PATTERN_NO5_BROKER_CHARGE = "5";
    
    /** パターンNo6.集計単位：顧客 */
    private static final String PATTERN_NO6_CUSTOMER = "6";
    
    /** 数値を ##0.00 形式に */
    private static final DecimalFormat decimalFormat = new DecimalFormat("##0.00");
    
    public IfaTradeTrendSearchCsvOut(ComplianceService comp) {
        super(comp);
    }
    
    @Override
    protected String getCsvHeader(String pattern) {
        return getHeader(pattern);
    }
    
    public static String getHeader(String pattern) {
        
        if (PATTERN_NO1_BROKER.equals(pattern) || PATTERN_NO4_BROKER.equals(pattern))
            return getCsvHeadLine1(pattern);
        else if (PATTERN_NO2_BROKER_CHARGE.equals(pattern) || PATTERN_NO5_BROKER_CHARGE.equals(pattern))
            return getCsvHeadLine2(pattern);
        else if (PATTERN_NO3_CUSTOMER.equals(pattern) || PATTERN_NO6_CUSTOMER.equals(pattern))
            return getCsvHeadLine3(pattern);
        return "";
    }
    
    @Override
    protected String getCsvLine(ModelBase modelBase, String pattern) {
        
        switch (pattern) {
        case PATTERN_NO1_BROKER:
            return getCsvLine1(modelBase, pattern);
        case PATTERN_NO2_BROKER_CHARGE:
            return getCsvLine2(modelBase, pattern);
        case PATTERN_NO3_CUSTOMER:
            return getCsvLine3(modelBase, pattern);
        case PATTERN_NO4_BROKER:
            return getCsvLine1(modelBase, pattern);
        case PATTERN_NO5_BROKER_CHARGE:
            return getCsvLine2(modelBase, pattern);
        case PATTERN_NO6_CUSTOMER:
            return getCsvLine3(modelBase, pattern);
        }
        return "";
    }
    
    /** パターンNo1.集計単位：仲介業者 */
    private static String getCsvHeadLine1(String pattern) {
        
        StringBuilder sb = new StringBuilder(512);
        
        sb.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "取引回数" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "調整後回数" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "買付約定総金額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "預り資産" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "資金回転率（％）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "手数料" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "評価額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "評価損益" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "評価損益率（％）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "前月比評価損益" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "年間実現損益" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        // 期間指定（From）<> 期間指定（To）
        if (PATTERN_NO1_BROKER.equals(pattern)) {
            sb.append(DOUBLE_QUOTATION + "期間指定実現損益" + DOUBLE_QUOTATION);
        } else {
            sb.append(DOUBLE_QUOTATION + "当月実現損益" + DOUBLE_QUOTATION);
        }
        return sb.toString();
    }

    /** パターンNo1.集計単位：仲介業者 */
    private String getCsvLine1(ModelBase modelBase, String pattern) {
        
        IfaTradeTrendSearchCsvItems IfaTradeTrendSearchCsvItems = (IfaTradeTrendSearchCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getTradeCount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getAdjustmentTradeCount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getGrossAmountYen()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getAssets()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        if (IfaTradeTrendSearchCsvItems.getGrossAmountYenAssets() != null ) {
            strCsv.append(DOUBLE_QUOTATION + decimalFormat.format(new BigDecimal(IfaTradeTrendSearchCsvItems.getGrossAmountYenAssets())) + DOUBLE_QUOTATION);
        }else {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getGrossAmountYenAssets()) + DOUBLE_QUOTATION);
        }
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getFee()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getCurrenctPrice()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getProfitAndLoss()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        if (IfaTradeTrendSearchCsvItems.getProfitAndLossCurrenctPrice() != null ) {
            strCsv.append(DOUBLE_QUOTATION + decimalFormat.format(new BigDecimal(IfaTradeTrendSearchCsvItems.getProfitAndLossCurrenctPrice())) + DOUBLE_QUOTATION);
        }else {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getProfitAndLossCurrenctPrice()) + DOUBLE_QUOTATION);
        }
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getCurrenctPriceMonth()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getYearRealProfitAndLoss()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 期間指定（From）<> 期間指定（To）
        if(PATTERN_NO1_BROKER.equals(pattern)) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getPeriodRealProfitAndLoss()) + DOUBLE_QUOTATION);
        }else {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getMonthlyRealProfitAndLoss()) + DOUBLE_QUOTATION);
        }
        
        return strCsv.toString();
    }
    
    /** パターンNo2.集計単位：営業員 */
    private static String getCsvHeadLine2(String pattern) {
        
        StringBuilder sb = new StringBuilder(512);
        
        sb.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "取引回数" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "調整後回数" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "買付約定総金額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "預り資産" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "資金回転率（％）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "手数料" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "評価額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "評価損益" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "評価損益率（％）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "前月比評価損益" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "年間実現損益" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        // 期間指定（From）<> 期間指定（To）
        if (PATTERN_NO2_BROKER_CHARGE.equals(pattern)) {
            sb.append(DOUBLE_QUOTATION + "期間指定実現損益" + DOUBLE_QUOTATION);
        } else {
            sb.append(DOUBLE_QUOTATION + "当月実現損益" + DOUBLE_QUOTATION);
        }
        
        return sb.toString();
    }

    /** パターンNo2.集計単位：営業員 */
    private String getCsvLine2(ModelBase modelBase, String pattern) {
        
        IfaTradeTrendSearchCsvItems IfaTradeTrendSearchCsvItems = (IfaTradeTrendSearchCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getBrokerChargeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getBrokerBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getBranchName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getTradeCount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getAdjustmentTradeCount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getGrossAmountYen()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getAssets()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        if (IfaTradeTrendSearchCsvItems.getGrossAmountYenAssets() != null ) {
            strCsv.append(DOUBLE_QUOTATION + decimalFormat.format(new BigDecimal(IfaTradeTrendSearchCsvItems.getGrossAmountYenAssets())) + DOUBLE_QUOTATION);
        }else {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getGrossAmountYenAssets()) + DOUBLE_QUOTATION);
        }
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getFee()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getCurrenctPrice()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getProfitAndLoss()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        if (IfaTradeTrendSearchCsvItems.getProfitAndLossCurrenctPrice() != null ) {
            strCsv.append(DOUBLE_QUOTATION + decimalFormat.format(new BigDecimal(IfaTradeTrendSearchCsvItems.getProfitAndLossCurrenctPrice())) + DOUBLE_QUOTATION);
        }else {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getProfitAndLossCurrenctPrice()) + DOUBLE_QUOTATION);
        }
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getCurrenctPriceMonth()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getYearRealProfitAndLoss()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 期間指定（From）<> 期間指定（To）
        if(PATTERN_NO2_BROKER_CHARGE.equals(pattern)) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getPeriodRealProfitAndLoss()) + DOUBLE_QUOTATION);
        }else {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getMonthlyRealProfitAndLoss()) + DOUBLE_QUOTATION);
        }
        
        return strCsv.toString();
    }
    
    /** パターンNo3.集計単位：顧客 */
    private static String getCsvHeadLine3(String pattern) {
        
        StringBuilder sb = new StringBuilder(512);
        
        sb.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "部店コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "口座番号" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "年齢" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "Cランク" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "取引コース" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "職業区分" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "投資方針" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "資金性格" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "運用期間" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "収入区分" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "年収" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "金融資産" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "投資経験年数（株式現物）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "投資経験年数（信用）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "投資経験年数（その他投信）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "投資経験年数（外国証券）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "扱者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "閲覧可能部店" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "取引回数" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "調整後回数" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "買付約定総金額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "預り資産" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "資金回転率（％）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "手数料" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        // 集計単位が顧客の場合
        if (PATTERN_NO3_CUSTOMER.equals(pattern) || PATTERN_NO6_CUSTOMER.equals(pattern)) {
            sb.append(DOUBLE_QUOTATION + "営業員手数料合計" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "手数料依存率（％）" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
        }
        sb.append(DOUBLE_QUOTATION + "評価額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "評価損益" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "評価損益率（％）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "前月比評価損益" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "年間実現損益" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        // 期間指定（From）<> 期間指定（To）
        if (PATTERN_NO3_CUSTOMER.equals(pattern)) {
            sb.append(DOUBLE_QUOTATION + "期間指定実現損益" + DOUBLE_QUOTATION);
        } else {
            sb.append(DOUBLE_QUOTATION + "当月実現損益" + DOUBLE_QUOTATION);
        }
        
        return sb.toString();
    }

    /** パターンNo3.集計単位：顧客 */
    private String getCsvLine3(ModelBase modelBase, String pattern) {
        
        IfaTradeTrendSearchCsvItems IfaTradeTrendSearchCsvItems = (IfaTradeTrendSearchCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getBrokerChargeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getButenCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getAccountNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getAge()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getTcCompRank()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getCustomerAttribute())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getOccupation())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getInvestmentPlan())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getFundCharacter())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getEmloymentPeriod())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getIncomeForm())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getAnnualIncome())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getFinancialAssets())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getUaiExpStock())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getUaiExpMargin())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getUaiExpOtherfund())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getUaiExpForeign())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getDealerNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getBrokerBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaTradeTrendSearchCsvItems.getBranchName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getVisibleButenCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getTradeCount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getAdjustmentTradeCount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getGrossAmountYen()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getAssets()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        if (IfaTradeTrendSearchCsvItems.getGrossAmountYenAssets() != null ) {
            strCsv.append(DOUBLE_QUOTATION + decimalFormat.format(new BigDecimal(IfaTradeTrendSearchCsvItems.getGrossAmountYenAssets())) + DOUBLE_QUOTATION);
        }else {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getGrossAmountYenAssets()) + DOUBLE_QUOTATION);
        }
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getFee()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        
        // 集計単位が顧客の場合
        if (PATTERN_NO3_CUSTOMER.equals(pattern) || PATTERN_NO6_CUSTOMER.equals(pattern)) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getRewardPrice()) + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            if (IfaTradeTrendSearchCsvItems.getFeeRewardPrice() != null ) {
                strCsv.append(DOUBLE_QUOTATION + decimalFormat.format(new BigDecimal(IfaTradeTrendSearchCsvItems.getFeeRewardPrice())) + DOUBLE_QUOTATION);
            }else {
                strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getFeeRewardPrice()) + DOUBLE_QUOTATION);
            }
            strCsv.append(CSV_SEPARATER);
        }
        
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getCurrenctPrice()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getProfitAndLoss()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        if (IfaTradeTrendSearchCsvItems.getProfitAndLossCurrenctPrice() != null ) {
            strCsv.append(DOUBLE_QUOTATION + decimalFormat.format(new BigDecimal(IfaTradeTrendSearchCsvItems.getProfitAndLossCurrenctPrice())) + DOUBLE_QUOTATION);
        }else {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getProfitAndLossCurrenctPrice()) + DOUBLE_QUOTATION);
        }
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getCurrenctPriceMonth()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getYearRealProfitAndLoss()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 期間指定（From）<> 期間指定（To）
        if(PATTERN_NO3_CUSTOMER.equals(pattern)) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getPeriodRealProfitAndLoss()) + DOUBLE_QUOTATION);
        }else {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaTradeTrendSearchCsvItems.getMonthlyRealProfitAndLoss()) + DOUBLE_QUOTATION);
        }
        
        return strCsv.toString();
    }

}
