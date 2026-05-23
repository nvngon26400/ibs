package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA005CsvItem;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;


/** 
 * 取引履歴
 *
 * @author SCSK
    2024/06/14 新規作成
*/
public class IfaTradeHistoryCsvOut extends CsvOutPutUtil {
    
    /**
     *
     * @param comp コンプライアンス
     */
    public IfaTradeHistoryCsvOut(ComplianceService comp) {
        
        super(comp);
        
    }
    
    /**
     * Csvタイトル行
     *
     * @return String タイトル行
     */
    @Override
    protected String getCsvHeader(String pattern) {
        
        return getHeaders(pattern);
    }
    
    /**
     * CSVファイルのヘッダー
     *
     * @return String csvヘッダー
     */
    public static String getHeaders(String pattern) {
        
        StringBuilder strCsvHead = new StringBuilder();
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
        strCsvHead.append(DOUBLE_QUOTATION + "Cランク" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "扱者コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取引コース" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "顧客名(漢字)" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "顧客名(カナ)" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "証券種別" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "銘柄コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "銘柄名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "商品ランク" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取引種別" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
		strCsvHead.append(DOUBLE_QUOTATION + "預り区分" + DOUBLE_QUOTATION);
		strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "約定日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "受渡日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "単価" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "数量" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "約定金額（円貨）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "手数料（円貨）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "受渡金額（円貨）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "通貨" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "約定金額（外貨）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "受渡金額（外貨）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "手数料（外貨）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "為替レート" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "債券　媒介/非媒介" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "米国株　店頭/委託" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "閲覧可能部店" + DOUBLE_QUOTATION);
        if ("1".equals(pattern)) {
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "債券種別" + DOUBLE_QUOTATION);
        }
        
        return strCsvHead.toString();
    }
    
    /**
     * 取引履歴データをCSV形式に変換
     *
     * @param modelBase 取引履歴データ
     * @return String CSV形式取引履歴データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase, String pattern) {
        
        IfaTradeHistoryA005CsvItem csvItems = (IfaTradeHistoryA005CsvItem) modelBase;
        
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
        // Cランク
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getTcCompRank())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 扱者コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getDealerNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // コース名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCourseName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(漢字)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(カナ)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCustomerNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 証券種別
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getSecurityClass())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 銘柄コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getBrandCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 銘柄名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrandName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 商品ランク
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getProductRank())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 取引種別
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getTradeTypeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
		// 預り区分
		strCsv.append(
				DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getDepositName())) + DOUBLE_QUOTATION);
		strCsv.append(CSV_SEPARATER);
        // 約定日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getTradeDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 受渡日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSettlementDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 単価
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getPrice()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 数量
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getQuantity()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 約定金額（円貨）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getContractAmountJpyAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 手数料（円貨）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getYenFee()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 受渡金額（円貨）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getYenDeliveryAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 通貨
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCurrency())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 約定金額（外貨）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getContractAmountForeign()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 受渡金額（外貨）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getForeignDeliveryAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 手数料（外貨）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getForeignFee()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 為替レート
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getFxRate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBranchName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 債券　媒介/非媒介
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBondIntermediary())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 米国株　店頭/委託
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getUsStockStoreEntrust())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 閲覧可能部店
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getVisibleButenCode()) + DOUBLE_QUOTATION);
        
        if ("1".equals(pattern)) {
            strCsv.append(CSV_SEPARATER);
            // 仕組債区分
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getStructuredBondClassification())) + DOUBLE_QUOTATION);
        }
        
        return strCsv.toString();
    }
    
}
