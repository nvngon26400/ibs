package com.sbisec.helios.ap.brokerageMenu.jointSubscript.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA003CsvItem;
import com.sbisec.helios.ap.compliance.service.ComplianceService;


/** 
 * 共同募集　取引検索
 *
 * @author SBIチョウ
    2024/12/12 新規作成
*/
public class IfaJointSubscriptTradeListCsvOut extends CsvOutPutUtil {

    private static final String ZERO = "0";
    
    /**
     *
     * @param comp コンプライアンス
     */
    public IfaJointSubscriptTradeListCsvOut(ComplianceService comp) {
        
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
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
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
        strCsvHead.append(DOUBLE_QUOTATION + "共募支店コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "共募支店名" + DOUBLE_QUOTATION);
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
        strCsvHead.append(DOUBLE_QUOTATION + "為替レート" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "約定金額（外貨）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "受渡金額（外貨）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "残手数料" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "支払額" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "債券　媒介/非媒介" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "米国株　店頭/委託" + DOUBLE_QUOTATION);

        return strCsvHead.toString();
    }
    
    /**
     * 共募取引一覧データをCSV形式に変換
     *
     * @param modelBase 共募取引一覧データ
     * @return String CSV形式共募取引一覧データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase) {
        
        IfaJointSubscriptTradeListA003CsvItem csvItems = (IfaJointSubscriptTradeListA003CsvItem) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 部店
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getButenCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 口座番号
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getAccountNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 取引コース
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCustomerAttributeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(漢字)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(カナ)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCustomerNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 共募仲支店コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getJointBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 共募支店名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getJointBranchName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 証券種別名
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
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getDepositName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 約定日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getTradeDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 受渡日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getSettlementDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 単価
        strCsv.append(DOUBLE_QUOTATION + numberNullToZero(csvItems.getPrice()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 数量
        strCsv.append(DOUBLE_QUOTATION + numberNullToZero(csvItems.getQuantity()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 約定金額（円貨）
        strCsv.append(DOUBLE_QUOTATION + numberNullToZero(csvItems.getContractAmountJpyAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 手数料（円貨）
        strCsv.append(DOUBLE_QUOTATION + numberNullToZero(csvItems.getYenFee()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 受渡金額（円貨）
        strCsv.append(DOUBLE_QUOTATION + numberNullToZero(csvItems.getYenDeliveryAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 通貨
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCurrency())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 為替レート
        strCsv.append(DOUBLE_QUOTATION + numberNullToZero(csvItems.getFxRate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 約定金額（外貨）
        strCsv.append(DOUBLE_QUOTATION + numberNullToZero(csvItems.getContractAmountForeign()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 受渡金額（外貨）
        strCsv.append(DOUBLE_QUOTATION + numberNullToZero(csvItems.getForeignDeliveryAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 残手数料
        strCsv.append(DOUBLE_QUOTATION + numberNullToZero(csvItems.getFeeBalance()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支払額
        strCsv.append(DOUBLE_QUOTATION + numberNullToZero(csvItems.getJointRewardPrice()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerChargeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 債券　媒介/非媒介
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBondIntermediary())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 米国株　店頭/委託
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getUsStockStoreEntrust())) + DOUBLE_QUOTATION);
        return strCsv.toString();
    }
    
    private String numberNullToZero(String str) {
        return StringUtil.isNullOrEmpty(str) ? ZERO : str;
    }
    
}
