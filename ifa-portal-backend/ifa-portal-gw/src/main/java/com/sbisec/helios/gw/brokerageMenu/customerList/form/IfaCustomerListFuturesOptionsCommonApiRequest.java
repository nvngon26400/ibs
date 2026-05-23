package com.sbisec.helios.gw.brokerageMenu.customerList.form;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 顧客一覧_先OP
 *
 * @author SCSK
 *
 */
@Data
public class IfaCustomerListFuturesOptionsCommonApiRequest {
    
    /**
     * コース
     *
     * @author SCSK
     *
     */
    @Data
    public static class TradingCourse {
        
        /** id */
        @NotEmpty(message = "取引コース(id)")
        private String id;
        
        /** isSelected */
        @NotEmpty(message = "取引コース(isSelected)")
        private String isSelected;
        
    }
    
    /** 仲介業者コード. */
    @Size(max = 49, message = "仲介業者コード")
    @Pattern(regexp = "[a-zA-Z0-9\\,]*", message = "仲介業者コード")
    private String brokerCode;
    
    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;
    
    /** 支店コード（数字）. */
    @Pattern(regexp = "[0-9]*", message = "支店コード")
    @Size(max = 3, message = "支店コード")
    private String branchCode;
    
    /** 営業員コード（半角英数字）. */
    @Size(max = 4, message = "営業員コード")
    private String empCode;
    
    /** 部店コード（半角英数字）. */
    @Size(max = 3, message = "部店コード")
    private String butenCode;
    
    /** 口座番号（数字）. */
    @Pattern(regexp = "[0-9]*", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;
    
    /** 顧客名(漢字/カナ)（全角半角）. */
    @Size(max = 72, message = "顧客名(漢字/カナ)")
    private String customerNameKanjiKana;
    
    /** 顧客名(漢字/カナ)（条件リスト）. */
    private String customerNameKanjiKanaTerms;
    
    /** 取引コース（全角半角）. */
    @NotEmpty(message = "取引コース")
    @Size(max = 40, message = "取引コース")
    private List<TradingCourse> course;
    
    /** 必要委託保証金（From）（数値(整数)）. */
    @Digits(integer = 19, fraction = 0, message = "必要委託保証金（From）")
    @Size(max = 25, message = "必要委託保証金（From）")
    private String necessaryEntrustDepositFrom;
    
    /** 必要委託保証金（To）（数値(整数)）. */
    @Digits(integer = 19, fraction = 0, message = "必要委託保証金（To）")
    @Size(max = 25, message = "必要委託保証金（To）")
    private String necessaryEntrustDepositTo;
    
    /** 受入証拠金（From）（数値(整数)）. */
    @Digits(integer = 19, fraction = 0, message = "受入証拠金（From）")
    @Size(max = 25, message = "受入証拠金（From）")
    private String marginMoneyFrom;
    
    /** 受入証拠金（To）（数値(整数)）. */
    @Digits(integer = 19, fraction = 0, message = "受入証拠金（To）")
    @Size(max = 25, message = "受入証拠金（To）")
    private String marginMoneyTo;
    
    /** 前日評価損益（From）（数値(整数)）. */
    @Digits(integer = 19, fraction = 0, message = "前日評価損益（From）")
    @Size(max = 25, message = "前日評価損益（From）")
    private String beforeProfitAndLossFrom;
    
    /** 前日評価損益（To）（数値(整数)）. */
    @Digits(integer = 19, fraction = 0, message = "前日評価損益（To）")
    @Size(max = 25, message = "前日評価損益（To）")
    private String beforeProfitAndLossTo;
    
}
