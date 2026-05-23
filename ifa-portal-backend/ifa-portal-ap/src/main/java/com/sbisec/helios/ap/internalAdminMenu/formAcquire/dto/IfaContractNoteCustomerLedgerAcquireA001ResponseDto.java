package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取引日記帳・顧客勘定元帳 A001応答
 *
 * @author SCSK
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaContractNoteCustomerLedgerAcquireA001ResponseDto {
    
    /** 帳票種別 */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
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
