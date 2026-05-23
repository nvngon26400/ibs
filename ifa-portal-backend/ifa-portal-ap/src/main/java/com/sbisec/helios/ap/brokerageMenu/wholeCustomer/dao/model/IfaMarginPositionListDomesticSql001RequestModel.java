package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import java.util.List;

import lombok.Data;

@Data
public class IfaMarginPositionListDomesticSql001RequestModel {
    
    /** 画面入力．仲介業者コード. */
    private String brokerCode;
    
    /** 画面入力．営業員コード. */
    private String empCode;
    
    /** 画面入力．部店コード. */
    private String butenCode;
    
    /** 画面入力．口座番号. */
    private String accountNumber;
    
    /** 画面入力．取引コース. */
    private List<String> course;
    
    /** 権限コード */
    private String privId;
    
    /** 顧客名（漢字／カナ）（全角半角）. */
    private String customerNameKanjiKana;
    
    /** 顧客名（漢字／カナ）_条件. */
    private String customerNameKanjiKanaTerms;
    
    /** 仲介業者営業員リスト */
    List<IfaMarginPositionListDomesticSql001RequestModel_BrokerCharge> brokerChargeList;
}
