
package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util;
import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListCsvItems;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;


/**
 * 画面ID：SUB020302_0104-01
 * 画面名：利金・償還金支払予定一覧
 *
 * @author SCSK濱田
 * 2024/06/06 新規作成
 */
public class IfaCouponRedemptionPaymentScheduleListCsvOut extends CsvOutPutUtil {
    
    public IfaCouponRedemptionPaymentScheduleListCsvOut(ComplianceService comp) {
        
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
        
        StringBuilder strCsvHead = new StringBuilder(512);
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
        strCsvHead.append("銘柄コード");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("銘柄名");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("約定基準残高");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("通貨");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("元利払種別");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("予定日");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("予定利率");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("予定概算金額");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("クーポン判定日");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("KI有無");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("支店コード");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("支店名");
        strCsvHead.append(DOUBLE_QUOTATION);
        
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
        
        IfaCouponRedemptionPaymentScheduleListCsvItems csvItems =
                (IfaCouponRedemptionPaymentScheduleListCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
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
        // 取引コース
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCourceName())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(漢字)
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCustomerNameKanji())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(カナ)
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCustomerNameKana())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 証券種別名
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getSecuriytClassName())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 銘柄コード
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getBrandCode()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 銘柄名
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrandName())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 約定基準残高
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getContractStandardDeposit()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 通貨
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCurrency())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 元利払種別
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getPaymentClass())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 予定日
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getScheduleDate()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 予定利率
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getNextInterest()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 予定概算金額
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getSchedulePrice()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // クーポン判定日
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getCouponDeterminationDate()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // KI有無
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getKiCount())));
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
        
        return strCsv.toString();
    }
    
}
