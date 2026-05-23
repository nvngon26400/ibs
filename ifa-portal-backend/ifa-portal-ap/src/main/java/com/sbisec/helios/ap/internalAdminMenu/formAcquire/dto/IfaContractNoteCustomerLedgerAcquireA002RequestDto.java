package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto;

import lombok.Data;

/**
 * 取引日記帳・顧客勘定元帳 A002要求
 *
 * @author SCSK
 *
 */
@Data
public class IfaContractNoteCustomerLedgerAcquireA002RequestDto {
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;
    
    /** 作成日From. */
    private String createDateFrom;
    
    /** 作成日To. */
    private String createDateTo;
    
    /** 帳票種別. */
    private String ledgerClass;
    
    /** ファイルディレクトリ（全角半角）. */
    private String fileDirectory;
    
}
