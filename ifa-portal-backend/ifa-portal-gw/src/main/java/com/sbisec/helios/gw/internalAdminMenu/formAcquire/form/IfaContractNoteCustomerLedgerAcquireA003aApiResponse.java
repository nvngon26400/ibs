package com.sbisec.helios.gw.internalAdminMenu.formAcquire.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取引日記帳・顧客勘定元帳 A003a応答
 *
 * @author SCSK
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaContractNoteCustomerLedgerAcquireA003aApiResponse {
    
    /** pdfファイルへのフルパス. */
    private String pdfFileName;
    
}
