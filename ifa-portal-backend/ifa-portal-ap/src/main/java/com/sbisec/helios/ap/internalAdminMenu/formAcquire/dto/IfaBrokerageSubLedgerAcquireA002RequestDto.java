package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto;




import lombok.Data;

@Data
public class IfaBrokerageSubLedgerAcquireA002RequestDto {

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 仲介業者除外（半角英数字）. */
	private String chkBrokerCodeExclude;

	/** 作成日From. */
	private String createDateFrom;

	/** 作成日To. */
    private String createDateTo;
    
    /** ファイルディレクトリ. */
    private String fileDirectory;
}
