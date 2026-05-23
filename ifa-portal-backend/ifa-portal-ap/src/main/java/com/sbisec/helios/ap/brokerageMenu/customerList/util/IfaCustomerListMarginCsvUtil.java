package com.sbisec.helios.ap.brokerageMenu.customerList.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListMarginSql001ResponseModel;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

public class IfaCustomerListMarginCsvUtil extends CsvOutPutUtil{
    
    private static final String MARGIN_STATUS_0 = "0";
    
    private static final String CHARGE_FLAG_1 = "1";
    
    public IfaCustomerListMarginCsvUtil(ComplianceService comp) {
        
        super(comp);
        
    }
    
    /**
     * Csvタイトル行
     *
     * @return String タイトル行
     */
    @Override
    protected String getCsvHeader(String pattern) {
        
        return getHeaders(pattern);
    }
    
    public static String getHeaders(String pattern) {
        
        String[] patterns = pattern.split(",");
        
        StringBuilder strCsvHead = new StringBuilder(512);
        if ("0".equals(patterns[0])) {
            strCsvHead.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        strCsvHead.append(DOUBLE_QUOTATION + "部店" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "口座番号" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取引コース" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "顧客名(漢字)" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "顧客名(カナ)" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "Cランク" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "前日評価損益" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "委託保証金率" + DOUBLE_QUOTATION);
        if ("true".equals(patterns[1])) {
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "信用追証" + DOUBLE_QUOTATION);
        }
        if ("true".equals(patterns[2])) {
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "引出金不足" + DOUBLE_QUOTATION);
        }
        if ("0".equals(patterns[0])) {
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
        }
        
        return strCsvHead.toString();
    }

    /**
     * 顧客一覧・信用データをcvs形式に変換
     *
     * @param entrustInvestment 顧客一覧・信用
     * @return String cvs顧客一覧・信用データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase, String pattern) {
        
        IfaCustomerListMarginSql001ResponseModel model = (IfaCustomerListMarginSql001ResponseModel) modelBase;
        
        String[] patterns = pattern.split(",");
        
        StringBuilder strCsv = new StringBuilder();
        
        if ("0".equals(patterns[0])) {
            // 仲介業者名
            strCsv.append(
                    DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getBrokerName())) + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            // 営業員コード
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getBrokerChargeCode()) + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            // 営業員名
            strCsv.append(
                    DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getChargeName())) + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }

        // 部店
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getButenCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 口座番号
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getAccountNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 取引コース
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getCustomerAttributeName()))
                + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(漢字)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getCustomerNameKanji()))
                + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(カナ)
        strCsv.append(
                DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getCustomerNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // Cランク
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getTcCompRank()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 前日評価損益
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getCustomerListProfitAndLossOfMarginPosition())
                + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 委託保証金率
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getMaintenanceRate()) + DOUBLE_QUOTATION);
        
        if ("true".equals(patterns[1])) {
            // 信用追証 追証ステータスが「0以外:追証発生」の場合、”○”を設定
            if (MARGIN_STATUS_0.equals(model.getMarginStatus())) {
                strCsv.append(CSV_SEPARATER);
                strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getMarginStatus()))
                        + DOUBLE_QUOTATION);
            } else {
                strCsv.append(CSV_SEPARATER);
                strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS("○") + DOUBLE_QUOTATION);
            }
        }
        
        if ("true".equals(patterns[2])) {
            // 引出金不足
            if (CHARGE_FLAG_1.equals(model.getChargeFlag())) {
                strCsv.append(CSV_SEPARATER);
                strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS("○") + DOUBLE_QUOTATION);
            } else {
                strCsv.append(CSV_SEPARATER);
                strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getChargeFlag()) + DOUBLE_QUOTATION);
            }
        }
        
        if ("0".equals(patterns[0])) {
            // 仲介業者コード
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getBrokerCode()) + DOUBLE_QUOTATION);
            // 支店コード
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getBranchCode()) + DOUBLE_QUOTATION);
            // 支店名
            strCsv.append(CSV_SEPARATER);
            strCsv.append(
                    DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getBranchName())) + DOUBLE_QUOTATION);
        }
        
        return strCsv.toString();
    }

}
