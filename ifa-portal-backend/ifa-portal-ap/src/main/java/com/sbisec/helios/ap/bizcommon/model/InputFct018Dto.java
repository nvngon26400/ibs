package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT018
 * @author base 熊
 */

@Data
public class InputFct018Dto {
	
	//国コード
	private String  countryCode;
	
	//サービス時間チェック対象（外国）
	private String foreignServiceHoursCheckTarget;
}
