package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto;


import lombok.Data;

@Data
public class IfaBrokerageSubLedgerAcquireA002ResponseDto {

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
