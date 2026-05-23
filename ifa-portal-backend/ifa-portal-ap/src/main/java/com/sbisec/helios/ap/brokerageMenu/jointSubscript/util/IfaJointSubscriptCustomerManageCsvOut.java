package com.sbisec.helios.ap.brokerageMenu.jointSubscript.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA006CsvOutModel;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * CSV出力
 * 共同募集 顧客管理用
 * 
 * @author 大連 王永宝
 */
public class IfaJointSubscriptCustomerManageCsvOut extends CsvOutPutUtil {

    // --------------------------------
    // 表示明細/CSV出力項目パターン
    // --------------------------------
    /** パターンNo1.普通 */
    private static final String PATTERN_NO1_NORMAL = "1";

    /** パターンNo2.自動解約理由があり */
    private static final String PATTERN_NO2_AUTO_CANCELLATION_REASON = "2";

    public IfaJointSubscriptCustomerManageCsvOut(ComplianceService comp) {
        super(comp);
    }

    /**
     * CSVタイトルを設計する
     *
     * @return String タイトル行
     */
    @Override
    protected String getCsvHeader() {
        return getHeaders();
    }

    /**
     * パターン別CSVタイトルを設計する
     * @param pattern　パターン
     * @return String タイトル行
     */
    @Override
    protected String getCsvHeader(String pattern) { return getHeaders(pattern); }

    /**
     * 共募顧客検索データをCSV形式に変換
     *
     * @param modelBase 共募顧客検索データ
     * @return String CSV形式データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase) {
        return getCsvLine(modelBase,PATTERN_NO1_NORMAL);
    }
    /**
     * 共募顧客検索データをCSV形式に変換
     *
     * @param modelBase 共募顧客検索データ
     * @return String CSV形式データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase,String pattern) {
        IfaJointSubscriptCustomerManageA006CsvOutModel model = (IfaJointSubscriptCustomerManageA006CsvOutModel) modelBase;
        StringBuilder strCsv = new StringBuilder();
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者名(全角半角)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getBrokerName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 部店
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getButenCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 口座番号
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getAccountNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 取引コース(全角半角)
        if (IfaJointSubscriptCustomerManageUtil.EditStats.APPROVE.key.equals(model.getEditStatus())) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getCustomerAttributeName())) + DOUBLE_QUOTATION);
        } else {
            strCsv.append(StringUtil.EMPTY_STRING);
        }
        strCsv.append(CSV_SEPARATER);
        // 顧客名(漢字)(全角半角)
        if (IfaJointSubscriptCustomerManageUtil.EditStats.APPROVE.key.equals(model.getEditStatus())) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getNameKanji())) + DOUBLE_QUOTATION);
        } else {
            strCsv.append(StringUtil.EMPTY_STRING);
        }
        strCsv.append(CSV_SEPARATER);
        // 共募支店コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getJointBranchCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 共募支店名(全角半角)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getJointBranchName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 契約締結日(YYYY/MM/DD)
        if (StringUtil.isNullOrEmpty(model.getContractDate())) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.EMPTY_STRING + DOUBLE_QUOTATION);
        } else {
            strCsv.append(DOUBLE_QUOTATION + DateFormatUtil.convertDateString(model.getContractDate()) + DOUBLE_QUOTATION);
        }
        strCsv.append(CSV_SEPARATER);
        // 同意日(YYYY/MM/DD)
        if (StringUtil.isNullOrEmpty(model.getStartDate())) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.EMPTY_STRING + DOUBLE_QUOTATION);
        } else {
            strCsv.append(DOUBLE_QUOTATION + DateFormatUtil.convertDateString(model.getStartDate()) + DOUBLE_QUOTATION);
        }
        strCsv.append(CSV_SEPARATER);
        // 終了日(YYYY/MM/DD)
        if (StringUtil.isNullOrEmpty(model.getEndDate())) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.EMPTY_STRING + DOUBLE_QUOTATION);
        } else {
            strCsv.append(DOUBLE_QUOTATION + DateFormatUtil.convertDateString(model.getEndDate()) + DOUBLE_QUOTATION);
        }
        strCsv.append(CSV_SEPARATER);
        // 支払率
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getJointRewardRate()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 手続状況(全角半角)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getEditStatusName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(model.getBrokerChargeCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業名(全角半角)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getBrokerChargeName())) + DOUBLE_QUOTATION);

        // 自動解約理由名称(全角半角)
        if (PATTERN_NO2_AUTO_CANCELLATION_REASON.equals(pattern)){
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(model.getJointUserDispKbn())) + DOUBLE_QUOTATION);
        }
        return strCsv.toString();
    }

    /**
     * csvファイルのヘッダー
     *
     * @return String csvヘッダー
     */
    public static String getHeaders() {
        return  getHeaders(PATTERN_NO1_NORMAL);
    }

    /**
     * パターン別csvファイルのヘッダー
     * @param pattern
     * @return String csvヘッダー
     */
    public static String getHeaders(String pattern) {
        StringBuilder strCsvHead = new StringBuilder(512);
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
        strCsvHead.append(DOUBLE_QUOTATION + "共募支店コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "共募支店名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "契約締結日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "同意日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "終了日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "支払率" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "手続状況" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        if (PATTERN_NO2_AUTO_CANCELLATION_REASON.equals(pattern)){
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "自動解約理由" + DOUBLE_QUOTATION);
        }
        return strCsvHead.toString();
    }
}
