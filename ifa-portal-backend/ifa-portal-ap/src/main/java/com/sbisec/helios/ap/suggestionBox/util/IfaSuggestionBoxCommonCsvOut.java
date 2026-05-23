package com.sbisec.helios.ap.suggestionBox.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA006CsvItem;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;


/** 
 * 皆様からの要望
 *
 * @author 林
    2025/06/18 新規作成
*/
public class IfaSuggestionBoxCommonCsvOut extends CsvOutPutUtil {

    /**
     *
     * @param comp コンプライアンス
     */
    public IfaSuggestionBoxCommonCsvOut(ComplianceService comp) {
        
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
     * 皆様からの要望一覧データをCSV形式に変換
     *
     * @param modelBase 皆様からの要望一覧データ
     * @return String CSV形式 皆様からの要望一覧データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase) {
        
    	IfaSuggestionBoxCommonA006CsvItem csvItems = (IfaSuggestionBoxCommonA006CsvItem) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        // 要望No
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSbcNo()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 更新日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getUpdateDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 登録日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getCreateDate()) + DOUBLE_QUOTATION);
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
    
}
