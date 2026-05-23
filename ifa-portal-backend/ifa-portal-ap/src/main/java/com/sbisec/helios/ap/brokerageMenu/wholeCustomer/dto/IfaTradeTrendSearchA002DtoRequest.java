package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;

import lombok.Data;

/**
 * 取引動向検索
 * 2025/04/10 新規作成
 *
 * @author 大連 苗
 */
@Data
public class IfaTradeTrendSearchA002DtoRequest {
    
    /** 仲介業者コードリスト */
    private List<String> brokerCodeList;
    
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
    private List<String> courseList;
    
    /** 集計単位 */
    private String countingUnit;
    
    /** 閲覧可能部店 */
    private List<String> visibleButenCodeList;
    
    /** 証券種別 */
    private List<String> securityClassList;
    
    /** 期間指定From */
    private String periodFrom;
    
    /** 期間指定To */
    private String periodTo;
    
}
