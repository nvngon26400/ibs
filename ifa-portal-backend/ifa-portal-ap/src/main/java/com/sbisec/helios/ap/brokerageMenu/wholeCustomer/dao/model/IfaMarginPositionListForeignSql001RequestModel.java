package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import java.util.List;

import lombok.Data;

@Data
public class IfaMarginPositionListForeignSql001RequestModel {
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 取引コース（全角半角）. */
    private List<IfaMarginPositionListForeignSql001RequestModelCourseSelected> course;
    
    /** 権限コード */
    private String privId;
    
    /** 顧客名(漢字/カナ)（全角半角）. */
    private String customerNameKanjiKana;
    
    /** 顧客名(漢字/カナ)_条件. */
    private String customerNameKanjiKanaTerms;
    
    /** 仲介業者営業員リスト. */
    List<IfaMarginPositionListForeignSql001RequestModel_BrokerCharge> brokerChargeList;
    
}
