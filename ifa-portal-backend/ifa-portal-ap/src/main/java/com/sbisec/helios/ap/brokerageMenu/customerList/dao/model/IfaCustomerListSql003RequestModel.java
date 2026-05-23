package com.sbisec.helios.ap.brokerageMenu.customerList.dao.model;

import java.util.List;

import lombok.Data;

/**
 * 顧客一覧_基本 SQL003 リクエストモデル
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaCustomerListSql003RequestModel {
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 権限コード （全角半角）. */
    private String privId;
    
    /** 仲介業者コード*/
    private String brokerCode;
    
    /** 営業員コード*/
    private String empCode;
    
    /** 仲介業者営業員リスト 追加必要 */
    private List<BrokerCharge> brokerChargeList;
    
    /**
     * 仲介業者営業員
     */
    @Data
    public static class BrokerCharge {
        
        //仲介業者コード
        private String brokerCode;
        
        //営業員コード
        private String empCode;
    }
    
}
