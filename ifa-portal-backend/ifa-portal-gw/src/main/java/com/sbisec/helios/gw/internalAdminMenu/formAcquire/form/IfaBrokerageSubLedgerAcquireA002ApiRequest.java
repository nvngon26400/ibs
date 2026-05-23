package com.sbisec.helios.gw.internalAdminMenu.formAcquire.form;




import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



import lombok.Data;

@Data
public class IfaBrokerageSubLedgerAcquireA002ApiRequest {

	/** 仲介業者コード（数字）. */
	@NotEmpty(message = "仲介業者コード")
	@Pattern(regexp="0-9", message = "仲介業者コード")
	@Size(max = 4, message = "仲介業者コード")
	private String brokerCode;

	/** 仲介業者除外（半角英数字）. */
	@NotEmpty(message = "仲介業者除外")
	@Size(min = 1, max = 1, message = "仲介業者除外")
	private String chkBrokerCodeExclude;

	/** 作成日From. */
	@NotEmpty(message = "作成日From")
	private String createDateFrom;

	/** 作成日To. */
	@NotEmpty(message = "作成日To")
	private String createDateTo;

	/** ファイルディレクトリ（全角半角）. */
	 private String fileDirectory;

}
