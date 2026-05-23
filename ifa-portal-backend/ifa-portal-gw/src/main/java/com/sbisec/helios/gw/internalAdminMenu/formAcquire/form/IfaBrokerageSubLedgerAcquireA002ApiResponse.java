package com.sbisec.helios.gw.internalAdminMenu.formAcquire.form;


import lombok.Data;

@Data
public class IfaBrokerageSubLedgerAcquireA002ApiResponse {

    /** 作成日. */
    private String createDate;
    
    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 仲介業者名. */
    private String brokerName;
    
    /** 対象商品. */
    private String targetSecurity;
    
    /** DL. */
    private String dl;
    
    /** ファイル名. */
    private String fileName;

}
