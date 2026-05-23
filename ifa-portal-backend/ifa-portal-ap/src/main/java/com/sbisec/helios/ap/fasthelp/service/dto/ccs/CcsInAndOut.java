package com.sbisec.helios.ap.fasthelp.service.dto.ccs;

import lombok.Data;

/**
 * CCS連携API設定パラメータ設定取得InDtoとOutDto
 */
@Data
public class CcsInAndOut {

	/**
	 * CCS連携API設定パラメータ設定取得InDto
	 */
	private CcsFastHelpInfoInsertDoIn ccsIn;

	/**
	 * CCS連携API設定パラメータ設定取得OutDto
	 */
	private CcsFastHelpInfoInsertDoOut ccsOut;
}
