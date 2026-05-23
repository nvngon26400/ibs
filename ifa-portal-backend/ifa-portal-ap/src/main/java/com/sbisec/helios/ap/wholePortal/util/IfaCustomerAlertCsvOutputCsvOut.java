package com.sbisec.helios.ap.wholePortal.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;
import com.sbisec.helios.ap.wholePortal.dto.IfaCustomerAlertCsvOutputCustomerAlertNotification;

/**
 * 画面ID：SUB01-02
 * 画面名：顧客アラートCSV出力
 *
 * @author SCSK丹波
 2024/05/16 新規作成
 */
public class IfaCustomerAlertCsvOutputCsvOut extends CsvOutPutUtil {
    
    public IfaCustomerAlertCsvOutputCsvOut(ComplianceService comp) {
        
        super(comp);
    }
    
    /**
     * Csvタイトル行
     *
     * @return String タイトル行
     */
    @Override
    protected String getCsvHeader() {
        
        return getHeaders();
    }
    
    /**
     * csvファイルのヘッダー
     *
     * @return String csvヘッダー
     */
    public static String getHeaders() {
        
        StringBuilder strCsvHead = new StringBuilder();
        
        strCsvHead.append(DOUBLE_QUOTATION + "アラートカテゴリ" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "アラートタイトル" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "部店コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "口座番号" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取引コース" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "顧客名" + DOUBLE_QUOTATION);
        
        return strCsvHead.toString();
    }
    
    @Override
    protected String getCsvLine(ModelBase modelBase) {
        
        IfaCustomerAlertCsvOutputCustomerAlertNotification alertNotification = (IfaCustomerAlertCsvOutputCustomerAlertNotification) modelBase;
        StringBuilder strCsv = new StringBuilder();
        // アラートカテゴリ
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(alertNotification.getAlertCategory()))
                + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        
        // アラートタイトル
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(alertNotification.getAlertTitle()))
                + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(alertNotification.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        
        // 仲介業者名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(alertNotification.getBrokerName()))
                + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        
        // 支店コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(alertNotification.getBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        
        // 支店名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(alertNotification.getBranchName()))
                + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        
        // 営業員コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(alertNotification.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        
        // 営業員名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(alertNotification.getBrokerChargeName()))
                + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        
        // 部店コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(alertNotification.getButenCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        
        // 口座番号
        strCsv.append(
                DOUBLE_QUOTATION + StringUtil.nullToEmpty(alertNotification.getAccountNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        
        // 取引コース
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(alertNotification.getCourseName()))
                + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        
        // 顧客名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(alertNotification.getCustomerName()))
                + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
}
