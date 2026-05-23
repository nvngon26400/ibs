package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT035
 * @author base 熊
 */

@Data
public class InputFct035Dto extends BaseOutputDto{

	//部店コード
	private String butenCode;
	
	//口座番号
	private String accountNumber;
	

}
