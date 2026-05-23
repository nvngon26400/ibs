package com.sbisec.helios.ap.brokerageMenu.customerList.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 顧客一覧_先OP
 *
 * @author SCSK
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IfaCustomerListFuturesOptionsA002ResponseDto {
    
    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String brokerChargeCode;
    
    /** 担当者名（全角半角）. */
    private String chargeName;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** コース名. */
    private String courseName;
    
    /** 顧客名（漢字）（全角半角）. */
    private String customerNameKanji;
    
    /** 顧客名（カナ）（全角半角）. */
    private String customerNameKana;
    
    /** Cランク. */
    private String tcCompRank;
    
    /** 必要委託証拠金（先OP）. */
    private String necessaryEntrustDepositFuturesOptions;
    
    /** 受入証拠金（先OP）. */
    private String marginMoneyFuturesOptions;
    
    /** 前日評価損益（先OP）. */
    private String beforeProfitAndLossFuturesOptions;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 支店コード（数字）. */
    private String branchCode;
    
    /** 支店名（全角半角）. */
    private String branchName;
    
}
