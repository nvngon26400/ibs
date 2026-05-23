package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaDomesticMarginPositionDueDateAlertSql001RequestModel {
    
    /** 権限Id */
    private String privId;
    
    /** FCT030.仲介業者営業員リスト */
    private List<BrokerCharge> brokerChargeList;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;
    
    /** 支店コード（数字）. */
    private String branchCode;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客名（漢字／カナ）（全角半角）. */
    private String customerNameKanjiKana;
    
    /** 顧客名（漢字／カナ）_条件. */
    private String customerNameKanjiKanaTerms;
    
    /** 取引コース（全角半角）. */
    private String course;
    
    private String designatedDate;
    
    /** 年月日 */
    private String dateYmd;
}
