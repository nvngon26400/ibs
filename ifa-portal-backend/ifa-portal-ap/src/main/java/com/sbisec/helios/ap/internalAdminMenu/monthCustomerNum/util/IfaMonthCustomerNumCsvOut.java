package com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA004CsvItem;
/**
 * 画面ID：SUB0407_01
 * 画面名：月末口座数
 *
 * @author SBI大連 チョウ
   2025/05/22 新規作成
 */
public class IfaMonthCustomerNumCsvOut extends CsvOutPutUtil {

    
    /**
     *
     * @param comp コンプライアンス
     */
    public IfaMonthCustomerNumCsvOut(ComplianceService comp) {
        
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
     * CSVファイルのヘッダー
     *
     * @return String csvヘッダー
     */
    public static String getHeaders() {
        
        StringBuilder strCsvHead = new StringBuilder();
        strCsvHead.append(DOUBLE_QUOTATION + "対象年月" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "月末口座数" + DOUBLE_QUOTATION);

        return strCsvHead.toString();
    }
    
    /**
     * 月末口座数データをCSV形式に変換
     *
     * @param modelBase 月末口座数データ
     * @return String CSV形式月末口座数一覧データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase) {
        
        IfaMonthCustomerNumA004CsvItem csvItems = (IfaMonthCustomerNumA004CsvItem) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        // 対象年月
        strCsv.append(DOUBLE_QUOTATION + csvItems.getDateYm() + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION + csvItems.getBrokerCode() + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 月末口座数
        strCsv.append(DOUBLE_QUOTATION + csvItems.getAccountNum() + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
    
}
