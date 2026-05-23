package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryCsvItems;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 為替取引履歴 CSV出力ユーティリティ
 *
 * @author SCSK川崎
 */
public class IfaFxTradeHistoryCsvOut extends CsvOutPutUtil {
    
    public IfaFxTradeHistoryCsvOut(ComplianceService comp) {
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
        
        StringBuilder strCsvHead = new StringBuilder(512);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "部店" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "口座番号" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "扱者コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取引コース" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "顧客名(漢字)" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "顧客名(カナ)" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "約定日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "受渡日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取引種別" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "通貨" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "為替レート" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "為替スプレッド" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "円額" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "外貨額" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "為替手数料" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
        
        return strCsvHead.toString();
    }
    
    /**
     * 為替取引履歴リストをcsv形式に変換
     *
     * @param modelBase 為替取引履歴リスト
     * @return String csv形式の為替取引履歴リスト
     */
    @Override
    protected String getCsvLine(ModelBase modelBase) {
        
        IfaFxTradeHistoryCsvItems csvItems = (IfaFxTradeHistoryCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerChargeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 部店
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getButen()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 口座番号
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getAccountNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 扱者コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getDealerNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 取引コース
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCourseName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(漢字)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(カナ)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCustomerNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 約定日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getTradeDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 受渡日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSettlementDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 取引種別
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getTradeTypeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 通貨
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getCurrency()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 為替レート
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getFxRate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 為替スプレッド
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getExchangeSpread()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 円額
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getYenAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 外貨額
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getForeignAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 為替手数料
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getExchangeFee()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getBrokerageBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerBranchName())) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
}
