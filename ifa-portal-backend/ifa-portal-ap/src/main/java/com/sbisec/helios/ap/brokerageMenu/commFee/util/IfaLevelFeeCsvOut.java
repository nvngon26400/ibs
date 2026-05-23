package com.sbisec.helios.ap.brokerageMenu.commFee.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeCsvItems;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;
import com.sbisec.helios.ap.common.util.DateFormatUtil;

/**
 * 画面ID：SUB020505-01
 * 画面名：残高連動手数料・報酬
 * 2025/06/02 新規作成
 *
 * @author SCSK
 */
public class IfaLevelFeeCsvOut extends CsvOutPutUtil {
    
    /** 集計単位（日次/月次）.日次 */
    private static final String DAILY_MONTHLY_COUNTING_UNIT_DAILY = "0";

    /** 集計単位（仲介業者/営業員/顧客毎）.仲介業者 */
    private static final String BROKER_CHARGE_CUSTOMER_COUNTING_UNIT_BROKER = "0";

    /** 集計単位（仲介業者/営業員/顧客毎）.顧客毎 */
    private static final String BROKER_CHARGE_CUSTOMER_COUNTING_UNIT_CUSTOMER = "2";


    public IfaLevelFeeCsvOut(ComplianceService comp) {
        super(comp);
    }
    
    @Override
    protected String getCsvHeader(String pattern) {
        return getHeader(pattern);
    }
    
    public static String getHeader(String pattern) {
        
        StringBuilder sb = new StringBuilder(512);
        
        // 集計単位(日次/月次)
        String dailyMonthlyCountingUnit = pattern.substring(0, 1);
        // 集計単位(仲介業者/営業員/顧客)
        String brokerChargeBranchCountingUnit = pattern.substring(1);
        
        // 集計単位(日次/月次) = 日次
        if (DAILY_MONTHLY_COUNTING_UNIT_DAILY.equals(dailyMonthlyCountingUnit)) {
            sb.append(DOUBLE_QUOTATION + "年月日" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
        } else { // 集計単位(日次/月次) = 月次
            sb.append(DOUBLE_QUOTATION + "年月" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
        }
        
        sb.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        
        // 集計単位(仲介業者/営業員/顧客) != 仲介業者
        if (!BROKER_CHARGE_CUSTOMER_COUNTING_UNIT_BROKER.equals(brokerChargeBranchCountingUnit)) {
            sb.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
        }

        // 集計単位(仲介業者/営業員/顧客) = 顧客
        if (BROKER_CHARGE_CUSTOMER_COUNTING_UNIT_CUSTOMER.equals(brokerChargeBranchCountingUnit)) {
            sb.append(DOUBLE_QUOTATION + "部店" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "口座番号" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "扱者コード" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "顧客名（漢字）" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "顧客名（カナ）" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
        }

        sb.append(DOUBLE_QUOTATION + "証券種別" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "計算対象残高" + DOUBLE_QUOTATION);
        sb.append(CSV_SEPARATER);
        sb.append(DOUBLE_QUOTATION + "徴収額(税抜)" + DOUBLE_QUOTATION);

        // 集計単位(仲介業者/営業員/顧客) != 仲介業者
        if (!BROKER_CHARGE_CUSTOMER_COUNTING_UNIT_BROKER.equals(brokerChargeBranchCountingUnit)) {
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
            sb.append(CSV_SEPARATER);
            sb.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
        }

        return sb.toString();
    }
    
    @Override
    protected String getCsvLine(ModelBase modelBase, String pattern) {
        IfaLevelFeeCsvItems IfaLevelFeeCsvItems = (IfaLevelFeeCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        // 集計単位(日次/月次)
        String dailyMonthlyCountingUnit = pattern.substring(0, 1);
        // 集計単位(仲介業者/営業員/顧客)
        String brokerChargeBranchCountingUnit = pattern.substring(1);
        
        if (DAILY_MONTHLY_COUNTING_UNIT_DAILY.equals(dailyMonthlyCountingUnit)) { // 集計単位(日次/月次) = 日次
            // 年月日
            if (StringUtil.isNullOrEmpty(IfaLevelFeeCsvItems.getDateYmd())) {
                strCsv.append(DOUBLE_QUOTATION + StringUtil.EMPTY_STRING + DOUBLE_QUOTATION);
            } else {
                // YYYYMMDD → YYYY/MM/DD に変換をする
                strCsv.append(DOUBLE_QUOTATION + DateFormatUtil.convertDateString(IfaLevelFeeCsvItems.getDateYmd()) + DOUBLE_QUOTATION);
            }
            strCsv.append(CSV_SEPARATER);
        } else { // 集計単位(日次/月次) = 月次
            // 年月
            if (StringUtil.isNullOrEmpty(IfaLevelFeeCsvItems.getDateYm())) {
                strCsv.append(DOUBLE_QUOTATION + StringUtil.EMPTY_STRING + DOUBLE_QUOTATION);
            } else {
                // YYYYMM → YYYY/MM に変換をする
                strCsv.append(DOUBLE_QUOTATION + DateFormatUtil.convertDateYmString(IfaLevelFeeCsvItems.getDateYm()) + DOUBLE_QUOTATION);
            }
            strCsv.append(CSV_SEPARATER);
        }

        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaLevelFeeCsvItems.getBrokerCode()) + DOUBLE_QUOTATION); // 仲介業者コード
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaLevelFeeCsvItems.getBrokerName())) + DOUBLE_QUOTATION); // 仲介業者名
        strCsv.append(CSV_SEPARATER);

        // 集計単位(仲介業者/営業員/顧客) != 仲介業者
        if (!BROKER_CHARGE_CUSTOMER_COUNTING_UNIT_BROKER.equals(brokerChargeBranchCountingUnit)) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaLevelFeeCsvItems.getEmpCode()) + DOUBLE_QUOTATION); // 営業員コード
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaLevelFeeCsvItems.getBrokerChargeName())) + DOUBLE_QUOTATION); // 営業員名
            strCsv.append(CSV_SEPARATER);
        }

        // 集計単位(仲介業者/営業員/顧客) = 顧客
        if (BROKER_CHARGE_CUSTOMER_COUNTING_UNIT_CUSTOMER.equals(brokerChargeBranchCountingUnit)) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaLevelFeeCsvItems.getButenCode()) + DOUBLE_QUOTATION); // 部店
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(StringUtil.fillLeft(IfaLevelFeeCsvItems.getAccountNumber(), '0', 7)) + DOUBLE_QUOTATION); // 口座番号
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaLevelFeeCsvItems.getDealerNumber()) + DOUBLE_QUOTATION); // 扱者コード
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaLevelFeeCsvItems.getCustomerNameKanji())) + DOUBLE_QUOTATION); // 顧客名（漢字）
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaLevelFeeCsvItems.getCustomerNameKana())) + DOUBLE_QUOTATION); // 顧客名（カナ）
            strCsv.append(CSV_SEPARATER);
        }

        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS("国内投信") + DOUBLE_QUOTATION); // 証券種別
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaLevelFeeCsvItems.getValuationTotalJpyAmount()) + DOUBLE_QUOTATION); // 計算対象残高
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaLevelFeeCsvItems.getFee()) + DOUBLE_QUOTATION); // 徴収額(税抜)

        // 集計単位(仲介業者/営業員/顧客) != 仲介業者
        if (!BROKER_CHARGE_CUSTOMER_COUNTING_UNIT_BROKER.equals(brokerChargeBranchCountingUnit)) {
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(IfaLevelFeeCsvItems.getBranchCode()) + DOUBLE_QUOTATION); // 支店コード
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(IfaLevelFeeCsvItems.getBranchName())) + DOUBLE_QUOTATION); // 支店名
        }

        return strCsv.toString();
    }
}
