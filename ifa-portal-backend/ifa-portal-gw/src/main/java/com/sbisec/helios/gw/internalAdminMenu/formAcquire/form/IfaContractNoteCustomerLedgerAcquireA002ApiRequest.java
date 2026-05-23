package com.sbisec.helios.gw.internalAdminMenu.formAcquire.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaContractNoteCustomerLedgerAcquireA002ApiRequest {
    
    /** 仲介業者コード. */
    @Size(max = 49, message = "仲介業者コード")
    @Pattern(regexp = "[a-zA-Z0-9\\,]*", message = "仲介業者コード")
    private String brokerCode;
    
    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;
    
    /** 作成日 From. */
    @NotEmpty(message = "作成日 From")
    @Size(min = 8, max = 8, message = "作成日 From")
    private String createDateFrom;
    
    /** 作成日 To. */
    @NotEmpty(message = "作成日 To")
    @Size(min = 8, max = 8, message = "作成日 To")
    private String createDateTo;
    
    /** 帳票種別. */
    private String ledgerClass;
    
    /** ファイルディレクトリ（全角半角）. */
    @NotEmpty(message = "ファイルディレクトリ")
    @Size(max = 255, message = "ファイルディレクトリ")
    private String fileDirectory;
    
}
