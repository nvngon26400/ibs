package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT006
 * 
 * @author base 高
 */

@Data
public class OutputFct006Dto extends BaseOutputDto {

	// 判定結果
	private String judgementResult;

	// メッセージID
	private String messageId;

	// 開始基準確認メッセージID
	private String startCriteriaConfirmMsgId;

	// チェックボックス文言
	private String chkBoxLabel;

	// コンプラチェック用資金性格
	private String complaCheckFundCharacter;

}
