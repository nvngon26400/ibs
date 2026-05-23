package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupSelectedDtoRequest;

import lombok.Data;

@Data
public class IfaJointSubscriptSecurityCashBalanceLookupSql001RequestModel {

    /** 部店コード */
    private String butenCode;
    
    /** 口座番号 */
    private String accountNumber;

    /** 期間指定. */
    private String periodYm;

    /** 銘柄コード. */
    private String brandCode;

    /** 共募支店コード（数字）. */
    private String jointBranchCode;

    /** 権限コード */
    private String privId;
    
    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;

    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 顧客名（全角半角）. */
    private String customerName;

    /** 顧客名検索オプション. */
    private String customerNameSearchType;
    
    /** 集計単位(集計/明細) */
    private String aggregatorDetailsCountingUnit;

    /** 仲介業者コードリスト. */
    private List<String> brokerCodeList;

    /** ユーザ情報・ユーザID */
    private String userId;

    /** 取引コースリスト */
    private List<IfaJointSubscriptSecurityCashBalanceLookupSelectedDtoRequest> course;

    /** 証券　金銭リスト */
    private List<IfaJointSubscriptSecurityCashBalanceLookupSelectedDtoRequest> securityTypeSecuritiesMoney;
    
    /** 証券　金銭リスト選択有無 */
    private boolean securityTypeSecuritiesMoneySelected;

    /** 信用・先OPリスト */
    private List<IfaJointSubscriptSecurityCashBalanceLookupSelectedDtoRequest> securityTypeCreditFuturesOp;
    
    /** 信用・先OPリスト選択有無 */
    private boolean securityTypeCreditFuturesOpSelected;

    /** 閲覧可能部店リスト. */
    private List<String> visibleButenCodeList;

    /** 件数 */
    private String rownum;

}