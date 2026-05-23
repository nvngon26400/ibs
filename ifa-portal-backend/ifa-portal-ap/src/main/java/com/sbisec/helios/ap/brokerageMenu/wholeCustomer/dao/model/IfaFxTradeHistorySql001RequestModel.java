package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;

/**
 * 為替取引履歴　SQL001　リクエスト

 * @author SCSK川崎
 */
@Data
public class IfaFxTradeHistorySql001RequestModel {
    
    /**  仲介業者営業員リスト（FCT030） */
    private List<BrokerCharge> brokerChargeList;
    
    /** 権限コード */
    private String privId;

    /** 上限件数 */
    private int maxRow;
    
    /** 仲介業者除外. */
    private String chkBrokerCodeExclude;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者コードリスト. */
    private List<String> brokerCodeList;
    
    /** 支店コード（数字）. */
    private String branchCode;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号コード. */
    private String accountNumber;
    
    /** 顧客名（漢字／カナ）（全角半角）. */
    private String customerNameKanjiKana;
    
    /** 顧客名（漢字／カナ）_条件 */
    private String customerNameKanjiKanaTerms;
    
    /** 通貨. */
    private String currency;
    
    /** 期間指定（From). */
    private String periodYmFrom;
    
    /** 期間指定（To）. */
    private String periodYmTo;
    
    /** 取引コース. */
    private List<IfaFxTradeHistorySql001RequestModelCourseSelected> course;
    
}
