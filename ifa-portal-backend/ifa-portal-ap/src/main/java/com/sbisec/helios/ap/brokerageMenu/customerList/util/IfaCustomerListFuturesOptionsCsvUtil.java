package com.sbisec.helios.ap.brokerageMenu.customerList.util;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListFuturesOptionsA005CsvModel;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 顧客一覧_先OP　CSV出力ユーティリティ
 */
public class IfaCustomerListFuturesOptionsCsvUtil extends CsvOutPutUtil {
    
    /** CSVヘッダ */
    public static final String HEADER = "仲介業者名,営業員コード,営業員名,部店,口座番号,取引コース,顧客名(漢字),顧客名(カナ),Cランク,必要委託保証金,受入証拠金,前日評価損益,仲介業者コード,支店コード,支店名";
    
    @Override
    protected String getCsvLine(ModelBase model) {
        
        var item = (IfaCustomerListFuturesOptionsA005CsvModel) model;
        var ret = Arrays.asList(
                Cp932.toJIS(item.getBrokerName()), // 仲介業者名
                item.getBrokerChargeCode(), // 営業員コード
                Cp932.toJIS(item.getChargeName()), // 営業員名
                item.getButenCode(), // 部店
                item.getAccountNumber(), // 口座番号
                Cp932.toJIS(item.getCourseName()), // 取引コース
                Cp932.toJIS(item.getCustomerNameKanji()), // 顧客名(漢字)
                Cp932.toJIS(item.getCustomerNameKana()), // 顧客名(カナ)
                item.getTcCompRank(), // Cランク
                item.getNecessaryEntrustDepositFuturesOptions(), // 必要委託保証金
                item.getMarginMoneyFuturesOptions(), // 受入証拠金
                item.getBeforeProfitAndLossFuturesOptions(), // 前日評価損益
                item.getBrokerCode(), // 仲介業者コード
                item.getBranchCode(), // 支店コード
                Cp932.toJIS(item.getBranchName()) // 支店名
        ).stream().map(str -> DOUBLE_QUOTATION + StringUtil.nullToEmpty(str) + DOUBLE_QUOTATION)//
                .collect(Collectors.toList());
        return String.join(",", ret);
    }
    
    @Override
    protected String getCsvHeader() {
        
        var ret = Arrays.stream(HEADER.split(",")).map(str -> DOUBLE_QUOTATION + str + DOUBLE_QUOTATION).collect(Collectors.toList());
        return String.join(",", ret);
    }
    
    public IfaCustomerListFuturesOptionsCsvUtil(ComplianceService comp) {
        
        super(comp);
    }
    
}
