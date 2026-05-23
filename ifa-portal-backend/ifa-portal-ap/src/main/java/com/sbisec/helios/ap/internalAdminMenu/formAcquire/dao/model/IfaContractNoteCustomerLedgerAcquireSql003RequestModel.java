package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取引日記帳・顧客勘定元帳 SQL003要求
 *
 * @author SCSK
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaContractNoteCustomerLedgerAcquireSql003RequestModel {
    
    /** ユーザID. */
    private String userId;
    
}
