package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT021
 * @author base 熊
 */

@Data
public class OutputFct021Dto extends BaseOutputDto{
	
	//注意情報エラー有無
	private String noteInfoErrFlag;
	
	//注意情報アラート有無
	private String noteInfoAlertFlag;
	
	//お知らせエラー有無
	private String noteLimitErrFlag;
	
	//お知らせアラート有無
	private String noteLimitAlertFlag;
	
}
