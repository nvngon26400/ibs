package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model;


import lombok.Data;

@Data
public class IfaBrokerageSubLedgerAcquireSql003ResponseModel {
    /** 総件数. */
    private String totalCount;
    
    /** ユーザID. */
    private String userId;
    
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
    
    /** ダウンロードファイル名. */
    private String fileName;

}
