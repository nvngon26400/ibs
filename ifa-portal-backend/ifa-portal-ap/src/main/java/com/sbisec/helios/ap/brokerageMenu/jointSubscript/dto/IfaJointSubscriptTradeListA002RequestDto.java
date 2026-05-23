package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto;

import java.util.List;

import lombok.Data;

/**
 * 共同募集　取引検索　
 *
 * @author SBIチョウ
 *
 */
@Data
public class IfaJointSubscriptTradeListA002RequestDto {

    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;
    
    /** 共募支店コード（数字）. */
    private String jointBranchCode;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;

    /** 期間指定From. */
    private String periodDateFrom;
    
    /** 期間指定To. */
    private String periodDateTo;

    /** 証券種別. */
    private List<IfaJointSubscriptTradeListDtoRequestSelected> securityClass;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode12;
    
    /** 顧客名（全角半角）. */
    private String customerName;
    
    /** 顧客名検索オプション. */
    private String customerNameSearchType;
    
    /** 取引コース（全角半角）. */
    private List<IfaJointSubscriptTradeListDtoRequestSelected> course;
    
    /** 営業員コード*/
    private String empCode;

}
