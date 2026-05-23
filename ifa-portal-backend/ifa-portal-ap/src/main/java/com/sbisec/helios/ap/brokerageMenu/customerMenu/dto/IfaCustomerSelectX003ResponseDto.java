package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 検索 レスポンス
 *
 * @author SCSK
 */
@Data
public class IfaCustomerSelectX003ResponseDto {
    
    /** 顧客一覧. */
    private List<Customer> customerList;
    
    public IfaCustomerSelectX003ResponseDto() {
        
    }
    
    /**
     *  顧客
     */
    @Data
    public static class Customer {
        
        /** 部店コード（半角英数字）. */
        private String butenCode;
        
        /** 口座番号（数字）. */
        private String accountNumber;
        
        /** 取引制限有無. */
        private String tradeRestrictionSelect;
        
        /** 顧客名（漢字）（全角半角）. */
        private String customerNameKanji;
        
        /** 顧客名（カナ）（全角半角）. */
        private String customerNameKana;
        
        /** 口座開設年月日. */
        private String openAcctDate;
        
        /** お気に入り登録状況（数字）. */
        private String favoRegStatus;
        
        /** 顧客コード（数字）. */
        private String customerCode;
        
    }
}
