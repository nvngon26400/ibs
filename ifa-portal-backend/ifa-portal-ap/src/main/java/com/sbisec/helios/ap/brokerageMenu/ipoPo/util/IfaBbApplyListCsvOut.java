package com.sbisec.helios.ap.brokerageMenu.ipoPo.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA004aResponseDtoBbApplyList;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 画面ID：SUB0204_02-01
 * 画面名：BB申込一覧
 *
 * @author BASE李
 *
 2024/03/14 新規作成
 */
public class IfaBbApplyListCsvOut extends CsvOutPutUtil {

    /**
     *
     * @param comp コンプライアンス
     */
    public IfaBbApplyListCsvOut(ComplianceService comp) {
        
        super(comp);
        
    }
    @Override
    public String getCsvHeader(String pattern) {
        return getHeader(pattern);
    }
    
    /**
     *
     * @param pattern 営業員自動判定コード
     * @return CSVヘーダ
     * @see com.sbisec.helios.ap.common.util.CsvOutPutUtil#getCsvHeader(java.lang.String)
     */
    public static String getHeader(String pattern) {
        StringBuilder csvHeader = new StringBuilder();
        csvHeader.append(DOUBLE_QUOTATION + "銘柄コード" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "銘柄名" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        if ("0".equals(pattern)) {
            csvHeader.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
            csvHeader.append(CSV_SEPARATER);
            csvHeader.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
            csvHeader.append(CSV_SEPARATER);
            csvHeader.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
            csvHeader.append(CSV_SEPARATER);
            csvHeader.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
            csvHeader.append(CSV_SEPARATER);
            csvHeader.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
            csvHeader.append(CSV_SEPARATER);
            csvHeader.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
            csvHeader.append(CSV_SEPARATER);
        }
        csvHeader.append(DOUBLE_QUOTATION + "部店" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "口座番号" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "顧客名(漢字)" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "顧客名(カナ)" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "取引コース" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "投資家属性" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "BB申込株数" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "申込価格" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "裁量希望株数" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "当選株数" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "抽選結果" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "注文状況" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "注文株数" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "預り区分" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "勧誘区分" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "ワーニング申請チェック" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "申込日時" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "申込者" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "セクション名" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "電子交付同意" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "目論見書閲覧" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "備考" + DOUBLE_QUOTATION);
        return csvHeader.toString();
    }
    
    /**
     * BB申込一覧データをcvs形式に変換
     *
     * @param modelBase BB申込一覧データ
     * @param pattern 営業員自動判定コード
     * @return String cvs形式BB申込一覧データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase, String pattern) {
        IfaBbApplyListA004aResponseDtoBbApplyList ifaBbApplyListA004aResponseDtoBbApplyList = (IfaBbApplyListA004aResponseDtoBbApplyList) modelBase;
        StringBuilder csvBody = new StringBuilder();
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getBrandCode()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getBrandName())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        if ("0".equals(pattern)) {
            csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getBrokerCode()) + DOUBLE_QUOTATION);
            csvBody.append(CSV_SEPARATER);
            csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getBrokerName())) + DOUBLE_QUOTATION);
            csvBody.append(CSV_SEPARATER);
            csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getBrokerBranchCode()) + DOUBLE_QUOTATION);
            csvBody.append(CSV_SEPARATER);
            csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getBrokerBranchName())) + DOUBLE_QUOTATION);
            csvBody.append(CSV_SEPARATER);
            csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getBrokerChargeCode()) + DOUBLE_QUOTATION);
            csvBody.append(CSV_SEPARATER);
            csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getEmployeeName())) + DOUBLE_QUOTATION);
            csvBody.append(CSV_SEPARATER);
        }
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getButenCode()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getAccountNumber()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getCustomerNameKana())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getCourse())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getInvestorAttributeName())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getBbQuantity()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getBbPrice()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getQuantitySairyou()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getBbQuantityAlloc())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getLotteryResult())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getOrderStatus())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getOrderQuantity()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getDepositType())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getKanyuKbn())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getWarningApplyArranged())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getBbCreateDate()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getBbCreateUserName())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getSectionName())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getEdelivAgreement())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getReadTime()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getBbRemark())) + DOUBLE_QUOTATION);
        return csvBody.toString();
    }
}
