package com.sbisec.helios.ap.brokerageMenu.commFee.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeCsvItems;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

public class IfaTrustFeeCsvOut extends CsvOutPutUtil {
    
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
    
    public IfaTrustFeeCsvOut(ComplianceService comp) {
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
        sb.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "部店" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "口座番号" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "扱者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "取引コース名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名（漢字）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名（カナ）" + DOUBLE_QUOTATION);
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
        sb.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
        
        return sb.toString();
    }
    
    private static String getCsvHeadLine2() {
        
        StringBuilder sb = new StringBuilder(512);
        
        sb.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "部店" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "口座番号" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "扱者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "取引コース名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名（漢字）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名（カナ）" + DOUBLE_QUOTATION);
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
        sb.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
        
        return sb.toString();
    }
    
    private static String getCsvHeadLine3() {
        
        StringBuilder sb = new StringBuilder(512);
        
        sb.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
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
        
        return sb.toString();
    }
    
    private static String getCsvHeadLine4() {
        
        StringBuilder sb = new StringBuilder(512);
        
        sb.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "部店" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "口座番号" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "扱者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "取引コース名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名（漢字）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名（カナ）" + DOUBLE_QUOTATION);
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
        sb.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
        
        return sb.toString();
    }
    
    private static String getCsvHeadLine5() {
        
        StringBuilder sb = new StringBuilder(512);
        
        sb.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "部店" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "口座番号" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "扱者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "取引コース名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名（漢字）" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "顧客名（カナ）" + DOUBLE_QUOTATION);
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
        sb.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
        
        return sb.toString();
    }
    
    private static String getCsvHeadLine6() {
        
        StringBuilder sb = new StringBuilder(512);
        
        sb.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
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
        
        IfaTrustFeeCsvItems ifaTrustFeeCsvItems = (IfaTrustFeeCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaTrustFeeCsvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrokerChargeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getButen()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getAccountNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getDealerNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCourse()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCustomerNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getSecurityClass())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getHoldingcDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrandCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrandName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getQuantity()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getReferencePrice()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getPrice()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getValuation()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + addPourcent(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getTrustFeeRate())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCurrency()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getFxRate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getTrustFeeAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaTrustFeeCsvItems.getBranchName())) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
    
    private String getCsvLine2(ModelBase modelBase) {
        
        IfaTrustFeeCsvItems ifaTrustFeeCsvItems = (IfaTrustFeeCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrokerChargeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getButen()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getAccountNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getDealerNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCourse())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCustomerNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getSecurityClass())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getHoldingcDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getValuation()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCurrency()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getFxRate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getTrustFeeAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaTrustFeeCsvItems.getBranchName())) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
    
    private String getCsvLine3(ModelBase modelBase) {
        
        IfaTrustFeeCsvItems ifaTrustFeeCsvItems = (IfaTrustFeeCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getSecurityClass())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getHoldingcDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getValuation()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCurrency()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getFxRate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getTrustFeeAmount()) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
    
    private String getCsvLine4(ModelBase modelBase) {
        
        IfaTrustFeeCsvItems ifaTrustFeeCsvItems = (IfaTrustFeeCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaTrustFeeCsvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrokerChargeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getButen()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getAccountNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getDealerNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCourse()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCustomerNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getSecurityClass())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBaseMonth()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrandCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrandName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getValuation()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCurrency()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(addZero(ifaTrustFeeCsvItems.getFxRate())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getTrustFeeAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaTrustFeeCsvItems.getBranchName())) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
    
    private String getCsvLine5(ModelBase modelBase) {
        
        IfaTrustFeeCsvItems ifaTrustFeeCsvItems = (IfaTrustFeeCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrokerChargeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getButen()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getAccountNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getDealerNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCourse())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCustomerNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getSecurityClass())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBaseMonth()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getValuation()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCurrency()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(addZero(ifaTrustFeeCsvItems.getFxRate())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getTrustFeeAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaTrustFeeCsvItems.getBranchName())) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
    
    private String getCsvLine6(ModelBase modelBase) {
        
        IfaTrustFeeCsvItems ifaTrustFeeCsvItems = (IfaTrustFeeCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getSecurityClass())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getBaseMonth()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getValuation()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getCurrency()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(addZero(ifaTrustFeeCsvItems.getFxRate())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaTrustFeeCsvItems.getTrustFeeAmount()) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
    
}
