package com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA005CsvItem;

/**
 * 画面ID：SUB0407_01
 * 画面名：月末口座数－仲介業者顧客情報CSV出力
 *
 * @author SBI大連 チョウ
   2025/05/22 新規作成
 */
public class IfaBrokerCustomerCsvOutput extends CsvOutPutUtil {

    /**
     *
     * @param comp コンプライアンス
     */
    public IfaBrokerCustomerCsvOutput(ComplianceService comp) {
        
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
        strCsvHead.append(DOUBLE_QUOTATION + "対象年月" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "扱者コード" + DOUBLE_QUOTATION);
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
        strCsvHead.append(DOUBLE_QUOTATION + "顧客名（漢字）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "顧客名（カナ）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "年齢" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "年代" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "性別" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "個人/法人" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "Cランク" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "住所" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "口座開設日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "電話番号" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "投資方針" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "資金の性格" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "主な収入源" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取引の動機" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "資産運用期間" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "年収" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "金融資産" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "興味ある取引" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "投資経験（株式現物）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "投資経験（債券）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "投資経験（転換社債）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "投資経験（信用）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "投資経験（ワラント）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "投資経験（先物OP）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "投資経験（貯蓄型投信）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "投資経験（その他投信）" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "投資経験（外国証券）" + DOUBLE_QUOTATION);

        return strCsvHead.toString();
    }
    
    /**
     * 仲介業者顧客データをCSV形式に変換
     *
     * @param modelBase 月末口座数データ
     * @return String CSV形式月末口座数一覧データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase) {
        
        IfaMonthCustomerNumA005CsvItem csvItems = (IfaMonthCustomerNumA005CsvItem) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        // 対象年月
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getBaseDateYm()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 扱者コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getDealerNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getIntermediaryEmpCd()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerChargeName())) + DOUBLE_QUOTATION);
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
        // 顧客名（漢字）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名（カナ）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 年齢
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getAge()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 年代
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getAgeGroup())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 性別
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getSexKbn())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 個人/法人
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCorporationKbn())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // Cランク
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getTcCompRank()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getBrokerBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerBranchName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 住所
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getAddressKanji1())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 口座開設日
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getOpenAcctDate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 電話番号
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getPhoneNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 投資方針
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getQaInvestmentPlan())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 資金の性格
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getQaFundCharacter())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 主な収入源
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getQaIncomeForm())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 取引の動機
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getQaTradingMotive())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 資産運用期間
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getQaEmploymentPeriod())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 年収
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getQaAnnualIncome())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 金融資産
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getQaFinancialAssets())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 興味ある取引
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getQaInterestedTrading())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 投資経験（株式現物）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getExpStock()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 投資経験（債券）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getExpDebenture()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 投資経験（転換社債）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getExpCb()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 投資経験（信用）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getExpMargin()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 投資経験（ワラント）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getExpWarrant()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 投資経験（先物OP）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getExpFutureop()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 投資経験（貯蓄型投信）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getExpSavedtypefund()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 投資経験（その他投信）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getExpOtherfund()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 投資経験（外国証券）
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getExpForeign()) + DOUBLE_QUOTATION);

        return strCsv.toString();
    }

}
