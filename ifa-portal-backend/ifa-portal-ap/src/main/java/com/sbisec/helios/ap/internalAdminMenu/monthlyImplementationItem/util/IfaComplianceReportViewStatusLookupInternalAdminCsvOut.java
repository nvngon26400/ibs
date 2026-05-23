package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel;

public class IfaComplianceReportViewStatusLookupInternalAdminCsvOut extends CsvOutPutUtil {
    
    private static final String COR_BROWSE_FLAG_1 = "1";
    
    private static final String COR_BROWSE_FLAG_TEXT_1 = "閲覧任意";
    
    private static final String COR_BROWSE_FLAG_TEXT_OTHER = "閲覧必須";
    
    public IfaComplianceReportViewStatusLookupInternalAdminCsvOut(ComplianceService comp) {
        
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
    
    public static String getHeaders() {
        
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
        
        return strCsvHead.toString();
    }
    
    /**
     * コンプライアンス通信閲覧状況照会(内部管理責任者用)データをcvs形式に変換
     *
     * @param entrustInvestment コンプライアンス通信閲覧状況照会(内部管理責任者用)
     * @return String cvsコンプライアンス通信閲覧状況照会(内部管理責任者用)データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase) {
        
        IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel model = (IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        // 確認日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getConfirmDateTime()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getBrokerBranchName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getEmployeeId()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getUserId())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // タイトル
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getTitle())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 状態
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getCodeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 閲覧要否
        if (COR_BROWSE_FLAG_1.equals(model.getCorBrowseFlag())) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(COR_BROWSE_FLAG_TEXT_1)) + DOUBLE_QUOTATION);
        } else {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(COR_BROWSE_FLAG_TEXT_OTHER)) + DOUBLE_QUOTATION);
        }
        
        return strCsv.toString();
    }
}
