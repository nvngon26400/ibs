package com.sbisec.helios.ap.brokerageMenu.customerList.dto;

import java.util.List;

import lombok.Data;

/**
 * 顧客一覧_先OP
 *
 * @author SCSK
 *
 */
@Data
public class IfaCustomerListFuturesOptionsCommonRequestDto {
    
    /**
     * 顧客一覧_先OPコース4
     *
     * @author SCSK
     *
     */
    @Data
    public static class TradingCourse {
        
        /** id */
        private String id;
        
        /** isSelected */
        private String isSelected;
    }
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 支店コード（数字）. */
    private String branchCode;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客名(漢字/カナ)（全角半角）. */
    private String customerNameKanjiKana;
    
    /** 顧客名(漢字/カナ)（条件リスト）. */
    private String customerNameKanjiKanaTerms;
    
    /** 取引コース（全角半角）. */
    private List<TradingCourse> course;
    
    /** 必要委託保証金（From）（数値(整数)）. */
    private String necessaryEntrustDepositFrom;
    
    /** 必要委託保証金（To）（数値(整数)）. */
    private String necessaryEntrustDepositTo;
    
    /** 受入証拠金（From）（数値(整数)）. */
    private String marginMoneyFrom;
    
    /** 受入証拠金（To）（数値(整数)）. */
    private String marginMoneyTo;
    
    /** 前日評価損益（From）（数値(整数)）. */
    private String beforeProfitAndLossFrom;
    
    /** 前日評価損益（To）（数値(整数)）. */
    private String beforeProfitAndLossTo;
    
}
