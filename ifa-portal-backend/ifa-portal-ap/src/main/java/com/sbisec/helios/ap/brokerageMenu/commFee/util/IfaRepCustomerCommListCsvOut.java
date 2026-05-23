package com.sbisec.helios.ap.brokerageMenu.commFee.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListModel;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 画面ID：SUB020501-01
 * 画面名：担当顧客別手数料一覧
 * 2024/6/11 新規作成
 *
 * @author 宇田川達弥
 */

public class IfaRepCustomerCommListCsvOut extends CsvOutPutUtil {

    /** 集計単位:顧客毎 */
    private static final String CHARGE_CUSTOMER_COUNTING_UNIT_CUSTOMER = "1";
    
    /**
     * コンストラクタ
     *
     * @param comp コンプライアンスService
     */
    public IfaRepCustomerCommListCsvOut(ComplianceService comp) {
        
        super(comp);
    }
    
    /**
     * Csvタイトル行
     *
     * @param chargeCustomerCountingUnit 集計単位
     * @return String タイトル行
     */
    @Override
    protected String getCsvHeader(String chargeCustomerCountingUnit) {
        
        return getHeaders(chargeCustomerCountingUnit);
    }
    
    /**
     * csvファイルのヘッダー
     *
     * @param chargeCustomerCountingUnit 集計単位
     * @return String csvヘッダー
     */
    public static String getHeaders(String chargeCustomerCountingUnit) {
        
        final StringBuilder strCsvHead = new StringBuilder(512);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        if (CHARGE_CUSTOMER_COUNTING_UNIT_CUSTOMER.equals(chargeCustomerCountingUnit)) {
            // 集計単位が '1':顧客毎 の場合
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
        }
        strCsvHead.append(DOUBLE_QUOTATION + "手数料/合計(税抜き)" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
        
        return strCsvHead.toString();
    }
    
    /**
     * 担当顧客別手数料一覧データをcsv形式に変換
     *
     * @param modelBase 担当顧客別手数料一覧データ
     * @param chargeCustomerCountingUnit 集計単位
     * @return String csv形式担当顧客別手数料一覧データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase, String chargeCustomerCountingUnit) {
        
        final IfaRepCustomerCommListModel repCustomerComm = (IfaRepCustomerCommListModel) modelBase;
        final StringBuilder strCsv = new StringBuilder();
        // 仲介業者コード（半角英数字）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(repCustomerComm.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者名（全角半角）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(repCustomerComm.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員コード（半角英数字）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(repCustomerComm.getEmpCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員名（全角半角）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(repCustomerComm.getBrokerChargeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        if (CHARGE_CUSTOMER_COUNTING_UNIT_CUSTOMER.equals(chargeCustomerCountingUnit)) {
            // 集計単位が '1':顧客毎 の場合
            // 部店（半角英数字）
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(repCustomerComm.getButenCode()) + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            // 口座番号（半角英数字）
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(repCustomerComm.getAccountNumber()) + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            // 扱者コード（半角英数字）
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(repCustomerComm.getDealerNumber()) + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            // 取引コース(全角半角)
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(repCustomerComm.getCourseName())) + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            // 顧客名(漢字)(全角半角)
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(repCustomerComm.getCustomerNameKanji())) + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            // 顧客名(カナ)(全角半角)
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(repCustomerComm.getCustomerNameKana())) + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 手数料/合計(税抜き)(半角英数字)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(repCustomerComm.getCommTotal()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店コード(半角英数字)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(repCustomerComm.getBrokerageBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店名(全角半角)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(repCustomerComm.getBrokerBranchName())) + DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
}
