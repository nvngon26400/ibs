
package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignDto_MarginPositionListForeign;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

public class IfaMarginPositionListForeignCsvOut extends CsvOutPutUtil {
    
    public IfaMarginPositionListForeignCsvOut(ComplianceService comp) {
        
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
        strCsvHead.append(DOUBLE_QUOTATION + "ティッカー" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "市場" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取引" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "建日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "返済期限" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "預り区分" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "担保" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "建株数" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "注文中" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "建単価" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "現在値" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "建代金" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "諸経費等" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "評価損益" + DOUBLE_QUOTATION);
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
        
        return strCsvHead.toString();
    }
    
    /**
     * 顧客一覧データをcvs形式に変換
     *
     * @param entrustInvestment 顧客一覧データ
     * @return String cvs形式顧客一覧データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase) {
        
        IfaMarginPositionListForeignDto_MarginPositionListForeign marginPositionListForeignList =
                (IfaMarginPositionListForeignDto_MarginPositionListForeign) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        // 部店
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(marginPositionListForeignList.getButen()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 口座
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(marginPositionListForeignList.getAccount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 取引コース
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(marginPositionListForeignList.getCourse()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(漢字)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(marginPositionListForeignList.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(カナ)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(marginPositionListForeignList.getCustomerNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 銘柄名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(marginPositionListForeignList.getBrandName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // ティッカー
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(marginPositionListForeignList.getTicker()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 市場
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(marginPositionListForeignList.getMarket())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 取引
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(marginPositionListForeignList.getOpenTradeKbn())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 建日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(marginPositionListForeignList.getOpenTradeDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 返済期限
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(marginPositionListForeignList.getLastTradeDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 預り区分
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(marginPositionListForeignList.getDepositType()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 担保
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(marginPositionListForeignList.getSecurity()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 建株数
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(marginPositionListForeignList.getContPositionTotal())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 注文中
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(marginPositionListForeignList.getUnactualQuantity())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 建単価
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(marginPositionListForeignList.getPositionPrice())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 現在値
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(marginPositionListForeignList.getCurrentPrice())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 建代金
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(marginPositionListForeignList.getOpenAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 諸経費等
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(marginPositionListForeignList.getTotalExpensesEtc()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 評価損益
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(marginPositionListForeignList.getCustomerListProfitAndLoss()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(marginPositionListForeignList.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(marginPositionListForeignList.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(marginPositionListForeignList.getBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(marginPositionListForeignList.getBranchName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(marginPositionListForeignList.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(marginPositionListForeignList.getBrokerChargeName())) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
    
}
