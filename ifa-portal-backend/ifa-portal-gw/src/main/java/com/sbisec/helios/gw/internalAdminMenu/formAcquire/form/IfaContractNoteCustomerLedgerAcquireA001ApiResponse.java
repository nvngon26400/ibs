package com.sbisec.helios.gw.internalAdminMenu.formAcquire.form;

import java.util.List;

import lombok.Data;

/**
 * 取引日記帳・顧客勘定元帳 A001応答
 *
 * @author SCSK
 *
 */
@Data
public class IfaContractNoteCustomerLedgerAcquireA001ApiResponse {
    
    /** 帳票種別 */
    @Data
    public static class LedgerClass {
        
        /** コードID. */
        private String codeId;
        
        /** コード名称. */
        private String codeName;
        
    }
    
    /** 帳票種別リスト. */
    private List<LedgerClass> ledgerClassList;
    
    /** ファイルディレクトリ（全角半角）. */
    private String fileDirectory;
    
}
