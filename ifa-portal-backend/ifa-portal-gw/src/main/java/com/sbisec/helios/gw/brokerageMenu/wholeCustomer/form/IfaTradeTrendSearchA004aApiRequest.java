package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.List;

import lombok.Data;

/**
 * 取引動向検索
 * 2025/04/10 新規作成
 *
 * @author 大連 苗
 */
@Data
public class IfaTradeTrendSearchA004aApiRequest {
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者除外 */
    private String chkBrokerCodeExclude;
    
    /** 支店コード */
    private String branchCode;
    
    /** 営業員コード */
    private String empCode;
    
    /** 部店コード */
    private String butenCode;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** 取引コース */
    private List<IfaTradeTrendSearchMultiSelectApiRequest> course;
    
    /** 集計単位 */
    private String countingUnit;
    
    /** 閲覧可能部店 */
    private String visibleButenCode;
    
    /** 証券種別 */
    private List<IfaTradeTrendSearchMultiSelectApiRequest> securityClass;
    
    /** 期間指定 */
    private List<String> periodMonth;
    
}
