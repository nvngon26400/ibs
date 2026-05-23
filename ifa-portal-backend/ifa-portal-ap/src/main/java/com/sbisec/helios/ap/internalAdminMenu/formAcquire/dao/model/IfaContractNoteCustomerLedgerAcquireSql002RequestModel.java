package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取引日記帳・顧客勘定元帳 SQL002要求
 *
 * @author SCSK
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaContractNoteCustomerLedgerAcquireSql002RequestModel {
    
    /** ユーザID. */
    private String userId;
    
    /** 最大取得件数 */
    private int maxCount;
    
}
