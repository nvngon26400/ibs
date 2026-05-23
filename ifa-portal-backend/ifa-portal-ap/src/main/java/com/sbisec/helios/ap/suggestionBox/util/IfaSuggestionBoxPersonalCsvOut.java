package com.sbisec.helios.ap.suggestionBox.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalA006CsvItem;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;


/**
 * あなたの要望
 * @author SCSK神木
 * 2025/06/12 新規作成
 */
public class IfaSuggestionBoxPersonalCsvOut extends CsvOutPutUtil {

    private static final String ZERO = "0";
    
    /**
     *
     * @param comp コンプライアンス
     */
    public IfaSuggestionBoxPersonalCsvOut(ComplianceService comp) {
        
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
        strCsvHead.append(DOUBLE_QUOTATION + "要望No" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "更新日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "登録日" + DOUBLE_QUOTATION);
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
        strCsvHead.append(DOUBLE_QUOTATION + "カテゴリ" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "ステータス" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "要望内容" + DOUBLE_QUOTATION);

        return strCsvHead.toString();
    }
    
    /**
     * 要望一覧データをCSV形式に変換
     *
     * @param modelBase 要望一覧データ
     * @return String CSV形式要望一覧データ
     */
   @Override
    protected String getCsvLine(ModelBase modelBase) {
        
    	IfaSuggestionBoxPersonalA006CsvItem csvItems = (IfaSuggestionBoxPersonalA006CsvItem) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        // 要望No
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbpNo()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 更新日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getAnsUpdateTime()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 登録日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getRegisterDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getBrokerName()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getEmpName()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // タイトル
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getTitle())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // カテゴリ
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCategory())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // ステータス
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getStatus())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 要望内容
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getSuggestion())) + DOUBLE_QUOTATION);

        return strCsv.toString();
    }
    
    private String numberNullToZero(String str) {
        return StringUtil.isNullOrEmpty(str) ? ZERO : str;
    }

}
