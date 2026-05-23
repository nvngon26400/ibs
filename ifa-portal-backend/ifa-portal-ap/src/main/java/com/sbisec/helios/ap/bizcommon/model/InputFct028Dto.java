package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT028
 * @author base 熊
 */

@Data
public class InputFct028Dto {

	//部店コード
	private String butenCode;
	
	//口座番号
	private String accountNumber;
	
	//銘柄コード
	private String brandCode;
	
	//預り区分
	private String depositType;
	
	//店頭管理番号
	private String otcManagementNo;
	
}
