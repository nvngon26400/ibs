package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 入出金明細A004リクエストDTO
 *
 * @author SCSK
 */
@Data
public class IfaDepositWithdrawDetailA004RequestDto {
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
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
    private List<IfaDepositWithdrawDetailTradingCourse> course = new ArrayList<IfaDepositWithdrawDetailTradingCourse>();
    
    /** 区分. */
    private String depositWithdrawDetailType;
    
    /** 期間指定（From). */
    private String periodYmFrom;
    
    /** 期間指定（To). */
    private String periodYmTo;
    
}
