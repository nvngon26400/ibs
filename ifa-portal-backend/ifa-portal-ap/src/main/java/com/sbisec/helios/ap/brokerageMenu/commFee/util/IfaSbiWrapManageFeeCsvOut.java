package com.sbisec.helios.ap.brokerageMenu.commFee.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaSbiWrapManageFeeCsvItems;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

public class IfaSbiWrapManageFeeCsvOut extends CsvOutPutUtil {
    
    public IfaSbiWrapManageFeeCsvOut(ComplianceService comp) {
        
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
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("仲介業者コード");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("仲介業者名");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("営業員コード");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("営業員名");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("部店");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("口座番号");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("扱者コード");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("顧客名(漢字)");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("手数料徴収日");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("手数料番号");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("運用サービスID");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("徴収額(税抜)");
        strCsvHead.append(DOUBLE_QUOTATION);
        
        return strCsvHead.toString();
    }
    
    /**
     * 投信基準価額変動情報リストをcsv形式に変換
     *
     * @param entrustInvestment 投信基準価額変動情報リスト
     * @return String csv形式の投信基準価額変動情報リスト
     */
    @Override
    protected String getCsvLine(ModelBase modelBase) {
        
        IfaSbiWrapManageFeeCsvItems csvItems = (IfaSbiWrapManageFeeCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getBrokerCode()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者名
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerName())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員コード
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getEmpCode()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員名
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerChargeName())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 部店
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getButen()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 口座番号
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getAccountNumber()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 扱者コード
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getDealerNumber()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(漢字)
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCustomerNameKanji())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 手数料徴収日
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getFeeCollectionDate()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 手数料番号
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getFeeNumber()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 運用サービスID
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getOperationServiceId()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 徴収額(税抜)
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getCollectionAmount()));
        strCsv.append(DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
}
