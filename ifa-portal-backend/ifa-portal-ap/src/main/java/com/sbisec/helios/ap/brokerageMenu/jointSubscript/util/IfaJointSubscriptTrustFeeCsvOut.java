package com.sbisec.helios.ap.brokerageMenu.jointSubscript.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeCsvItems;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 画面ID：SUB0206_03-01
 * 画面名：共同募集　信託報酬
 *
 * @author SBI 苗萌
 * 2024/12/18 新規作成
 */
public class IfaJointSubscriptTrustFeeCsvOut extends CsvOutPutUtil {
    
    // --------------------------------
    // 表示明細/CSV出力項目パターン
    // --------------------------------   
    /** パターンNo1.日次-明細 */
    private static final String PATTERN_NO1_DAILY_DETAIL = "1";
    
    /** パターンNo2.日次-顧客・商品分類・通貨毎 */
    private static final String PATTERN_NO2_DAILY_CUSTOMER = "2";
    
    /** パターンNo3.日次-通貨毎 */
    private static final String PATTERN_NO3_DAILY_CURRENCY = "3";
    
    /** パターンNo4.月次累計-明細 */
    private static final String PATTERN_NO4_MONTHLY_DETAIL = "4";
    
    /** パターンNo5.月次累計-顧客・商品分類・通貨毎 */
    private static final String PATTERN_NO5_MONTHLY_CUSTOMER = "5";
    
    /** パターンNo6.月次累計-通貨毎 */
    private static final String PATTERN_NO6_MONTHLY_CURRENCY = "6";
    
    public IfaJointSubscriptTrustFeeCsvOut(ComplianceService comp) {
        super(comp);
    }
    
    @Override
    protected String getCsvHeader(String pattern) {
        return getHeader(pattern);
    }
    
    public static String getHeader(String pattern) {
        
        if (PATTERN_NO1_DAILY_DETAIL.equals(pattern))
            return getCsvHeadLine1();
        else if (PATTERN_NO2_DAILY_CUSTOMER.equals(pattern))
            return getCsvHeadLine2();
        else if (PATTERN_NO3_DAILY_CURRENCY.equals(pattern))
            return getCsvHeadLine3();
        else if (PATTERN_NO4_MONTHLY_DETAIL.equals(pattern))
            return getCsvHeadLine4();
        else if (PATTERN_NO5_MONTHLY_CUSTOMER.equals(pattern))
            return getCsvHeadLine5();
        else if (PATTERN_NO6_MONTHLY_CURRENCY.equals(pattern))
            return getCsvHeadLine6();
        return "";
    }
    
    private static String getCsvHeadLine1() {
        
        StringBuilder sb = new StringBuilder(512);
        
        sb.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "部店" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "口座番号" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "取引コース" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名（漢字）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名（カナ）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "共募支店コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "共募支店名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "証券種別" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "保有日" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "銘柄コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "銘柄名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "数量" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "参考価額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "基準価額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "評価額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "信託報酬率" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "通貨" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "為替レート" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "信託報酬額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "残手数料" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "支払額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        
        return sb.toString();
    }
    
    private static String getCsvHeadLine2() {
        
        StringBuilder sb = new StringBuilder(512);
        
        sb.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "部店" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "口座番号" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "取引コース" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名（漢字）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名（カナ）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "共募支店コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "共募支店名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "証券種別" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "保有日" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "評価額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "通貨" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "為替レート" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "信託報酬額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "残手数料" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "支払額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        
        return sb.toString();
    }
    
    private static String getCsvHeadLine3() {
        
        StringBuilder sb = new StringBuilder(512);
        
        sb.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "共募支店コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "共募支店名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "証券種別" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "保有日" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "評価額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "通貨" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "為替レート" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "信託報酬額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "残手数料" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "支払額" + DOUBLE_QUOTATION);
        
        return sb.toString();
    }
    
    private static String getCsvHeadLine4() {
        
        StringBuilder sb = new StringBuilder(512);
        
        sb.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "部店" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "口座番号" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "取引コース" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名（漢字）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名（カナ）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "共募支店コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "共募支店名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "証券種別" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "保有月" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "銘柄コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "銘柄名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "評価額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "通貨" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "為替レート" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "信託報酬額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "残手数料" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "支払額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        
        return sb.toString();
    }
    
    private static String getCsvHeadLine5() {
        
        StringBuilder sb = new StringBuilder(512);
        
        sb.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "部店" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "口座番号" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "取引コース" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名（漢字）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名（カナ）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "共募支店コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "共募支店名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "証券種別" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "保有月" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "評価額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "通貨" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "為替レート" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "信託報酬額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "残手数料" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "支払額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        
        return sb.toString();
    }
    
    private static String getCsvHeadLine6() {
        
        StringBuilder sb = new StringBuilder(512);
        
        sb.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "共募支店コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "共募支店名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "証券種別" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "保有月" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "評価額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "通貨" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "為替レート" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "信託報酬額" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "残手数料" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "支払額" + DOUBLE_QUOTATION);
        
        return sb.toString();
    }
    
    @Override
    protected String getCsvLine(ModelBase modelBase, String pattern) {
        
        switch (pattern) {
        case PATTERN_NO1_DAILY_DETAIL:
            return getCsvLine1(modelBase);
        case PATTERN_NO2_DAILY_CUSTOMER:
            return getCsvLine2(modelBase);
        case PATTERN_NO3_DAILY_CURRENCY:
            return getCsvLine3(modelBase);
        case PATTERN_NO4_MONTHLY_DETAIL:
            return getCsvLine4(modelBase);
        case PATTERN_NO5_MONTHLY_CUSTOMER:
            return getCsvLine5(modelBase);
        case PATTERN_NO6_MONTHLY_CURRENCY:
            return getCsvLine6(modelBase);
        }
        return "";
    }
    
    private String getCsvLine1(ModelBase modelBase) {
        
        IfaJointSubscriptTrustFeeCsvItems IfaJointSubscriptTrustFeeCsvItems = (IfaJointSubscriptTrustFeeCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getButenCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getAccountNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getCourse())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getCustomerNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getJointBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getJointBranchName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getSecurityClass())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getBaseDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getBrandCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getBrandName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getQuantity()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getReferencePrice()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getPrice()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getValuation()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + addPourcent(StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getTrustFeeRate())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getCurrency()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getFxRate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getTrustFeeAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getFeeBalance()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getJointRewardPrice()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getBrokerChargeName())) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
    
    private String getCsvLine2(ModelBase modelBase) {
        
        IfaJointSubscriptTrustFeeCsvItems IfaJointSubscriptTrustFeeCsvItems = (IfaJointSubscriptTrustFeeCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getButenCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getAccountNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getCourse())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getCustomerNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getJointBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getJointBranchName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getSecurityClass())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getBaseDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getValuation()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getCurrency()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getFxRate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getTrustFeeAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getFeeBalance()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getJointRewardPrice()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getBrokerChargeName())) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
    
    private String getCsvLine3(ModelBase modelBase) {
        
        IfaJointSubscriptTrustFeeCsvItems IfaJointSubscriptTrustFeeCsvItems = (IfaJointSubscriptTrustFeeCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getJointBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getJointBranchName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getSecurityClass())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getBaseDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getValuation()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getCurrency()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getFxRate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getTrustFeeAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getFeeBalance()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getJointRewardPrice()) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
    
    private String getCsvLine4(ModelBase modelBase) {
        
        IfaJointSubscriptTrustFeeCsvItems IfaJointSubscriptTrustFeeCsvItems = (IfaJointSubscriptTrustFeeCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getButenCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getAccountNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getCourse())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getCustomerNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getJointBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getJointBranchName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getSecurityClass())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getBaseMonth()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getBrandCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getBrandName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getValuation()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getCurrency()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getFxRate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getTrustFeeAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getFeeBalance()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getJointRewardPrice()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getBrokerChargeName())) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
    
    private String getCsvLine5(ModelBase modelBase) {
        
        IfaJointSubscriptTrustFeeCsvItems IfaJointSubscriptTrustFeeCsvItems = (IfaJointSubscriptTrustFeeCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getButenCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getAccountNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getCourse())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getCustomerNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getJointBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getJointBranchName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getSecurityClass())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getBaseMonth()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getValuation()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getCurrency()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getFxRate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getTrustFeeAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getFeeBalance()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getJointRewardPrice()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getBrokerChargeName())) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
    
    private String getCsvLine6(ModelBase modelBase) {
        
        IfaJointSubscriptTrustFeeCsvItems IfaJointSubscriptTrustFeeCsvItems = (IfaJointSubscriptTrustFeeCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getJointBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getJointBranchName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaJointSubscriptTrustFeeCsvItems.getSecurityClass())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getBaseMonth()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getValuation()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getCurrency()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getFxRate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getTrustFeeAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getFeeBalance()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaJointSubscriptTrustFeeCsvItems.getJointRewardPrice()) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
    
}
