package com.sbisec.helios.gw.internalAdminMenu.formAcquire.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 取引日記帳・顧客勘定元帳 A003要求
 *
 * @author SCSK
 *
 */
@Data
public class IfaContractNoteCustomerLedgerAcquireA003aApiRequest {
    
    /** ファイルディレクトリ（全角半角）. */
    @NotEmpty(message = "ファイルディレクトリ")
    @Size(max = 255, message = "ファイルディレクトリ")
    private String fileDirectory;
    
    /** 帳票名. */
    @NotEmpty(message = "帳票名")
    private String ledgerName;
    
    /** 帳票種別. */
    @NotEmpty(message = "帳票種別")
    private String ledgerClass;
    
    /** 作成日. */
    @NotEmpty(message = "作成日")
    @Size(min = 8, max = 8, message = "作成日")
    private String createDate;
    
    /** ダウンロードファイル名. */
    @NotEmpty(message = "ダウンロードファイル名")
    private String downloadFileName;
    
}
