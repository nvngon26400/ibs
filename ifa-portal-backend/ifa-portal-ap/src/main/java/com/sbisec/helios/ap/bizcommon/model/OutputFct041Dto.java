package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT041
 * 2024/12/12 新規作成
 *
 * @author 大連 王永宝
 */

@Data
public class OutputFct041Dto extends BaseOutputDto{

	// 共募支店コード自動設定判定
	private String jointBranchCodeSettingJudge;
	// 営業員コード自動設定判定
	private String empCodeCodeSettingJudge;

}
