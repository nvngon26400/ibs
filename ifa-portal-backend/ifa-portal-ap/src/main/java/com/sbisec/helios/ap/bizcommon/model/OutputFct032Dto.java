package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT032
 * @author base 熊
 */

@Data
public class OutputFct032Dto extends BaseOutputDto{

	//仲介業者コード自動設定判定
	private String brokerCodeAutoSettingJudge;
	
	//仲介業者支店コード自動設定判定
	private String brokerBranchCodeSettingJudge;
	
	//営業員コード自動設定判定
	private String empCodeCodeSettingJudge;

}
