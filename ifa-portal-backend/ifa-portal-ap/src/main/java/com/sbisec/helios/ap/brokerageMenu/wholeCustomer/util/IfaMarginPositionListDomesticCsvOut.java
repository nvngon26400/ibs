
package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticDto_MarginPositionListDomestic;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

public class IfaMarginPositionListDomesticCsvOut extends CsvOutPutUtil {
    
    public IfaMarginPositionListDomesticCsvOut(ComplianceService comp) {
        
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
        strCsvHead.append(DOUBLE_QUOTATION + "維持率(％)" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "銘柄名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "銘柄コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "市場" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取引" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "建日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "返済期限" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "預り" + DOUBLE_QUOTATION);
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
        
        IfaMarginPositionListDomesticDto_MarginPositionListDomestic managingContractList = (IfaMarginPositionListDomesticDto_MarginPositionListDomestic) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        // 部店
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(managingContractList.getButenCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 口座
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(managingContractList.getAccountNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 取引コース
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(managingContractList.getCourse()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(漢字)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(managingContractList.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(カナ)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(managingContractList.getCustomerNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 維持率(％)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(managingContractList.getDomesticMarginPositionListActualGrntRate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 銘柄名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(managingContractList.getBrandName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 銘柄コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(managingContractList.getBrandCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 市場
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(managingContractList.getMarket())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 取引
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(managingContractList.getOpenTradeKbn())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 建日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(managingContractList.getOpenTradeDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 返済期限
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(managingContractList.getLastTradeDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 預り
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(managingContractList.getDepositFullHalf13()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 建株数
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(managingContractList.getContPositionTotal())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 注文中
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(managingContractList.getUnactualQuantity())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 建単価
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(managingContractList.getUnitPriceForeign())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 現在値
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(managingContractList.getCurrentPrice())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 建代金
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(managingContractList.getOpenAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 諸経費等
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(managingContractList.getCost()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 評価損益
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(managingContractList.getDomesticPositionValuation()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(managingContractList.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(managingContractList.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(managingContractList.getBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(managingContractList.getBranchName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(managingContractList.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(managingContractList.getBrokerChargeName())) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
    
}