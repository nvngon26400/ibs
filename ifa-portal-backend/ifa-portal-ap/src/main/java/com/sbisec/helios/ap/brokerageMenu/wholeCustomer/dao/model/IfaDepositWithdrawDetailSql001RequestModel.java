package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import java.util.List;

import lombok.Data;

/**
 * 入出金明細SQL001リクエストモデル
 *
 * @author SCSK
 */
@Data
public class IfaDepositWithdrawDetailSql001RequestModel {
    
    /** 仲介業者コード（数字）. */
    private List<String> brokerCodeList;
    
    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;
    
    /** 支店コード（数字）. */
    private String branchCode;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客名(漢字/カナ)（全角半角）. */
    private String customerNameKanjiKana;
    
    /** 顧客名(漢字/カナ)_条件. */
    private String customerNameKanjiKanaTerms;
    
    /** 取引コース（全角半角）. */
    private List<String> course;
    
    /** 区分. */
    private String code;
    
    /** 期間指定（From). */
    private String periodYmFrom;
    
    /** 期間指定（To). */
    private String periodYmTo;
    
    /** 権限コード. */
    private String privId;
    
    /** 件数. */
    private String rownum;
    
    /** FCT030.仲介業者営業員リスト. */
    private List<WithdrawBrokerCharge> brokerChargeList;
    
    /**
     * 仲介業者営業員
     */
    @Data
    public static class WithdrawBrokerCharge {
        
        //仲介業者コード
        private String brokerCode;
        
        //営業員コード
        private String empCode;
        
    }
    
}
