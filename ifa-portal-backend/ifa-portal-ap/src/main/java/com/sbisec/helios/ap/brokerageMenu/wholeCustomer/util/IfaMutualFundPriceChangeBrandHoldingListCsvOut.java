
package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingListCsvItems;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

public class IfaMutualFundPriceChangeBrandHoldingListCsvOut extends CsvOutPutUtil {
    
    public IfaMutualFundPriceChangeBrandHoldingListCsvOut(ComplianceService comp) {
        
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
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("下落率区分");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("ステータス");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("対応方法");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("「その他」の内容");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("「その他」の詳細（自由記入）");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("顧客対応日");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("対応完了確認日");
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
        strCsvHead.append("扱者コード");
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
        strCsvHead.append("支店コード");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("支店名");
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
        strCsvHead.append("投信協会コード");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("銘柄名");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("基準日（F）");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("基準価額（F）");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("基準日（T）");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("基準価額（T）");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("前日比");
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION);
        strCsvHead.append("下落率");
        strCsvHead.append(DOUBLE_QUOTATION);
        
        return strCsvHead.toString();
    }
    
    /**
     * 投信基準価額変動情報リストをcsv形式に変換
     *
     * @param entrustInvestment 投信基準価額変動情報リスト
     * @return String csv形式の投信基準価額変動情報リスト
     */
    @Override
    protected String getCsvLine(ModelBase modelBase) {
        
        IfaMutualFundPriceChangeBrandHoldingListCsvItems csvItems = (IfaMutualFundPriceChangeBrandHoldingListCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        
        // 下落率区分
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getDeclineRateKbn()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // ステータス区分
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getStatusKbn()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 対応方法区分
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getMethodsKbn()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // その他内容区分
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getOtherContentsKbn()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // その他詳細
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getOtherDetails()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客対応日
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getCustomerSupportDate()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 対応完了確認日
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getCompletionConfirmationDate()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 部店コード
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getButenCode()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 口座番号
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getAccountNumber()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 取引コース
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getCustomerAttribute()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名_姓名(漢字)
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getNameKanji())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名_姓名(カナ)
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getNameKana())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 扱者コード
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getDealerNumber()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getBrokerCode()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者名
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBranchName())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業支店コード
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getBrokerBranchCode()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者支店名
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerBranchName())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者営業員コード
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getBrokerEmployeeCode()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 仲介業者担当者名
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrokerChargeName())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 投信協会コード
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getToushinKyoukaiCode()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 投信銘柄名
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getToushinBrandName())));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 基準日（From）
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getBaseDateFrom()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 基準価額（From）
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getBasePriceFrom()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 基準日（T）
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getBaseDateTo()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 基準価額（T）
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getBasePriceTo()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 前日比
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getZenjitsuHi()));
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 下落率
        strCsv.append(DOUBLE_QUOTATION);
        strCsv.append(StringUtil.nullToEmpty(csvItems.getDeclineRate()));
        strCsv.append(DOUBLE_QUOTATION);
        
        return strCsv.toString();
    }
    
}
