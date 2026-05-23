package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT035
 * @author base 熊
 */

@Data
public class OutputFct035Dto extends BaseOutputDto{

	//建余力不足_自動振替設定（米ドル）フラグ
	private String capacityShortageAutoTransferSettingUSDFlag;
	
	//建余力不足_自動振替設定（米国株式）フラグ
	private String capacityShortageAutoTransferSettingUSStocksFlag;
	

}
