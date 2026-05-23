package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDeliverInOutDetailCsvItems;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

public class IfaDeliverInOutDetailCsvOut extends CsvOutPutUtil {
    
    public IfaDeliverInOutDetailCsvOut(ComplianceService comp) {
        
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
        strCsvHead.append(DOUBLE_QUOTATION + "取引コース" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "顧客名(漢字)" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "顧客名(カナ)" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "銘柄名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "商品区分" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "入出庫区分" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "数量" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "入出庫日時価" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "合計金額" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "入出庫日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
        
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
        
        IfaDeliverInOutDetailCsvItems csvItems = (IfaDeliverInOutDetailCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
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
        // 取引コース
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCourse())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(漢字)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(カナ)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCustomerNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 銘柄名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrandName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 商品区分
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getSecurityType())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 入出庫区分
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getDeliverInOutClassification())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 数量
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getQuantity()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 入出庫日時価
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getDeliverInOutDateMarketValue()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 合計金額
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getTotalAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 入出庫日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getCheckInOutDay()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBranchName())) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
}
