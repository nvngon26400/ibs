package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryDtoRequestSelected;

import lombok.Data;

/**
 * 共同募集　取引検索 SQL001リクエスト
 *
 * @author SBIチョウ
 *
 */
@Data
public class IfaJointSubscriptTradeListSql001RequestModel {

    /** 仲介業者コード（数字）. */
    private List<String> brokerCodeList;

    /** 仲介業者除外 */
    private String chkBrokerCodeExclude;
    
    /** 共募支店コード. */
    private String jointBranchCode;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 期間指定From. */
    private String periodDateFrom;
    
    /** 期間指定To. */
    private String periodDateTo;
    
    /** 証券種別. */
    private List<IfaTradeHistoryDtoRequestSelected> securityClass;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode12;
    
    /** 取得最大件数（閾値）. */
    private int rownum;
    
    /** 権限コード（全角半角）. */
    private String privId;
    
    /** ユーザID. */
    private String userId;
    
    /** 顧客名（全角半角）. */
    private String customerName;
    
    /** 顧客名検索オプション. */
    private String customerNameSearchType;
    
    /** 取引コース（全角半角）. */
    private List<IfaTradeHistoryDtoRequestSelected> course;
    
    /** 証券種別が選択されているか否か（true：選択あり、false：選択無し） */
    private Boolean isSecurityClassSelected;

}
