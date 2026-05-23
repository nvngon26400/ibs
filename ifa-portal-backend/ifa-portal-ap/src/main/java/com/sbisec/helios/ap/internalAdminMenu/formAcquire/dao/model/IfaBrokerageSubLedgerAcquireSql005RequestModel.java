package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model;


import lombok.Data;

@Data
public class IfaBrokerageSubLedgerAcquireSql005RequestModel {

	/** ユーザID. */
	private String userId;
	
	/** 作成日. */
    private String createDate;
    
    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 仲介業者名. */
    private String brokerName;
    
    /** 商品名. */
    private String codeName;
    
    /** DL. */
    private String dl;
    
    /** ダウンロードファイル名. */
    private String downloadFileName;

}
