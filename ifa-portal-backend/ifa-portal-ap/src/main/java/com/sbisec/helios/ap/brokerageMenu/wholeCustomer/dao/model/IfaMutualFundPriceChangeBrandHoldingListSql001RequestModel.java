package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;

@Data
public class IfaMutualFundPriceChangeBrandHoldingListSql001RequestModel {
    
    /**  仲介業者営業員リスト */
    private List<BrokerCharge> brokerChargeList;
    
    /** 権限コード */
    private String privId;
    
    /** 上限件数 */
    private int maxRow;
    
    /** 仲介業者コード. */
    private List<String> brokerCodeList;
    
    /** 仲介業者除外. */
    private String chkBrokerCodeExclude;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客名（漢字／カナ）. */
    private String customerNameKanjiKana;
    
    /** 顧客名（漢字／カナ）_条件 */
    private String customerNameKanjiKanaTerms;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 支店コード. */
    private String branchCode;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 投信協会コード（半角英数字）. */
    private String investmentTrustAssociationCode;
    
    /** 期間指定（From）. */
    private String periodYmFrom;
    
    /** 期間指定（To）. */
    private String periodYmTo;
    
    /** ステータス区分. */
    private String status;
    
    /** 取引コース. */
    private List<IfaMutualFundPriceChangeBrandHoldingListSql001RequestModelCourseSelected> course;
    
}
