package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql002ResponseModel;
import com.sbisec.helios.ap.compliance.service.ComplianceService;
import com.sbisec.helios.ap.common.enums.PrivId;

public class  IfaComplianceReportViewStatusLookupManagerCsvOut extends CsvOutPutUtil {
    
    private static final String COR_BROWSE_FLAG_1 = "1";
    
    private static final String COR_BROWSE_FLAG_TEXT_1 = "閲覧不要";
    
    private static final String COR_BROWSE_FLAG_TEXT_OTHER = "閲覧必要";
    
    public  IfaComplianceReportViewStatusLookupManagerCsvOut(ComplianceService comp) {
        
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
        
        StringBuilder strCsvHead = new StringBuilder(512);
        strCsvHead.append(DOUBLE_QUOTATION + "確認日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "タイトル" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "状態" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "閲覧要否" + DOUBLE_QUOTATION);

        // 本店の場合のみ出力する
        if (PrivId.HEAD_OFFICE.getId().equals(pattern)) {
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "閲覧報告者" + DOUBLE_QUOTATION);
        }
        
        return strCsvHead.toString();
    }
    
    /**
     * コンプライアンス通信閲覧状況照会(管理者用)データをcvs形式に変換
     *
     * @param entrustInvestment コンプライアンス通信閲覧状況照会(管理者用)
     * @return String cvsコンプライアンス通信閲覧状況照会(管理用)データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase, String pattern) {
       
        IfaComplianceReportViewStatusLookupManagerSql002ResponseModel model = (IfaComplianceReportViewStatusLookupManagerSql002ResponseModel) modelBase;
        StringBuilder strCsv = new StringBuilder();
        if (model.getConfirmDateTime() != null) {
            LocalDateTime confirmDateTime = LocalDateTime.parse(model.getConfirmDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            // 確認日
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(DateTimeFormatter.ofPattern("yyyy/MM/dd").format(confirmDateTime)) + DOUBLE_QUOTATION);
        } else {
            strCsv.append(DOUBLE_QUOTATION + "" + DOUBLE_QUOTATION);
        }
        
        strCsv.append(CSV_SEPARATER);
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getBranchNameOfBranch())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getEmployeeId()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getEmployeeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // タイトル
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getTitle())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 状態
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getCodeName()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 閲覧要否
        if (COR_BROWSE_FLAG_1.equals(model.getCorBrowseFlag())) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(COR_BROWSE_FLAG_TEXT_1) + DOUBLE_QUOTATION);
        } else {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(COR_BROWSE_FLAG_TEXT_OTHER) + DOUBLE_QUOTATION);
        }
        
        // 閲覧報告者
        // 本店の場合のみ出力する
        if (PrivId.HEAD_OFFICE.getId().equals(pattern)) {
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getViewReportUser())) + DOUBLE_QUOTATION);
        }
        
        return strCsv.toString();
    }
}

