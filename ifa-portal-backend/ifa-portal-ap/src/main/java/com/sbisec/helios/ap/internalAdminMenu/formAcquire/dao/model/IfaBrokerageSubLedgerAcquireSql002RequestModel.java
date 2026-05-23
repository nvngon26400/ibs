package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model;


import lombok.Data;

@Data
public class IfaBrokerageSubLedgerAcquireSql002RequestModel {

	/** 作成日時. */
	private String createTime;

	/** ファイル名. */
    private String fileName;
    
    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 商品コード. */
    private String productCode;
}
