package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model;


import lombok.Data;

@Data
public class IfaBrokerageSubLedgerAcquireSql001RequestModel {

	/** 機能ID（全角半角）. */
	private String functionId;
	
	/** カテゴリID（全角半角）. */
    private String t9nInfoCat;

}
