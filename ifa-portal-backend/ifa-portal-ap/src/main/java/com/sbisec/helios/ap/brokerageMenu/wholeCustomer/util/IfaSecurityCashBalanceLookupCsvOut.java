
package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupCsvItems;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;


/** 
 * 画面ID：SUB020302_0301-01
 * 画面名：証券・金銭・残高照会

 * @author 濱田
    2024/05/16 新規作成
*/
public class IfaSecurityCashBalanceLookupCsvOut extends CsvOutPutUtil {
    
    public IfaSecurityCashBalanceLookupCsvOut(ComplianceService comp) {
        
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
        
        StringBuilder strCsvHead = new StringBuilder(512);

        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("年月日");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("仲介業者コード");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("仲介業者名");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("営業員コード");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("営業員名");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("部店");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("口座番号");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("扱者コード");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("取引コース");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("顧客名(漢字)");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("顧客名(カナ)");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("証券種別名");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("取引種別名");
        strCsvHead.append(DOUBLE_QUOTATION);
        if (!"1".equals(pattern) && !"2".equals(pattern)){
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION);
            strCsvHead.append("返済期限");
            strCsvHead.append(DOUBLE_QUOTATION);
        }
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("銘柄コード/通貨");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("銘柄名");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("預り区分");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("約定基準残高");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("現在値");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("評価額（円貨）");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("通貨");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("評価額（外貨）");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("為替レート");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("支店コード");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("支店名");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("閲覧可能部店");
        strCsvHead.append(DOUBLE_QUOTATION);
        if (!"1".equals(pattern) && !"3".equals(pattern)){
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION);
            strCsvHead.append("外債評価基準日");
            strCsvHead.append(DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION);
            strCsvHead.append("債券種別");
            strCsvHead.append(DOUBLE_QUOTATION);
        }
        return strCsvHead.toString();
    }
    
    /**
     * 顧客一覧データをcvs形式に変換
     *
     * @param entrustInvestment 顧客一覧データ
     * @return String cvs形式顧客一覧データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase, String pattern) {
        
        IfaSecurityCashBalanceLookupCsvItems csvItems =
                (IfaSecurityCashBalanceLookupCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        // pattern = "0"(全項目表示)
        // pattern = "1"(返還期限,外債評価基準日,債権種別 非表示)
        // pattern = "2"(返還期限 非表示)
        // pattern = "3"(外債評価基準日,債権種別 非表示)
        
        // 年月日
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getDateYmd()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getBrokerCode()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者名
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerName())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員コード
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getEmpCode()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員名
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerChargeName())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 部店
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getButen()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 口座番号
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getAccountNumber()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 扱者コード
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getDealerNumber()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // コース名
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCourceName())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名_姓名(漢字)
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCustomerNameKanji())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名_姓名(カナ)
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCustomerNameKana())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 証券種別名
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getSecuriytClass())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 取引種別名
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getTradeTypeName())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);

        if (!"1".equals(pattern) && !"2".equals(pattern)) {
            // 返済期限
            strCsv.append(DOUBLE_QUOTATION);
            strCsv.append(StringUtil.nullToEmpty(csvItems.getLastTradeDate()));
            strCsv.append(DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }

        // 銘柄コード/通貨
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrandCodeCurrency())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 銘柄名
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrandName())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 預り区分
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getDepositType())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 約定基準残高
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getContractStandardDeposit()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 現在値
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getCurrentPrice()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 評価額（円貨）
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getValuation()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 通貨
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getCurrency()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 評価額（外貨）
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getForeignValuation()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 為替レート
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getFxRate()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店コード
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getBranchCode()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店名
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBranchName())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 閲覧可能部店
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getButenCodeViewAble()));
        strCsv.append(DOUBLE_QUOTATION);
        if (!"1".equals(pattern) && !"3".equals(pattern)) {
            strCsv.append(CSV_SEPARATER);
            // 外債評価基準日
            strCsv.append(DOUBLE_QUOTATION);
            strCsv.append(StringUtil.nullToEmpty(csvItems.getForeignStandardDate()));
            strCsv.append(DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            // 仕組債区分
            strCsv.append(DOUBLE_QUOTATION);
            strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getStructuredBondClassification())));
            strCsv.append(DOUBLE_QUOTATION);
        }

        return strCsv.toString();
    }
    
}
