package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA004Items;

/**
 * 画面ID：SUB0406-01
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 夏
 * @date   2025/06/05
 */

public class IfaByYearAccountQuantityFeeAmountLookupA004CsvOut extends CsvOutPutUtil {

    
    public IfaByYearAccountQuantityFeeAmountLookupA004CsvOut(ComplianceService comp) {
        
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
        strCsvHead.append(DOUBLE_QUOTATION + "決算" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "決算年月" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "前期末口座数" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "当期末口座数" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "口座数増減" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "うち期中に媒介を行った口座数" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "媒介手数料（円）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "その他受入手数料（円）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "計（円）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "媒介手数料（千円）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "その他受入手数料（千円）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "計（千円）" + DOUBLE_QUOTATION);
        
        return strCsvHead.toString();
    }
    
    /**
     * 年度別口座数・報酬額照会データをcsv形式に変換
     *
     * @param modelBase 年度別口座数・報酬額照会データ
     * @return String csv形式年度別口座数・報酬額照会データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase) {
        IfaByYearAccountQuantityFeeAmountLookupA004Items csvItems = (IfaByYearAccountQuantityFeeAmountLookupA004Items) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        // 決算
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getClosingKbn()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 決算年月
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getClosingYearMonth()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 前期末口座数
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getPreviousNumberOfAccounts()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 当期末口座数
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getNumberOfAccounts()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 口座数増減
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getNumberOfAccountsIncreaseDecrease()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 媒介口座数
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getNumberOfActiveAccounts()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 媒介手数料
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getMediateCom()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // その他受入手数料
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getOtherCom()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 計（円）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getTotal()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 媒介手数料（千円）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getMediateComThousand()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // その他受入手数料（千円）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getOtherComThousand()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 計（千円）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getTotalThousand()) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
}
