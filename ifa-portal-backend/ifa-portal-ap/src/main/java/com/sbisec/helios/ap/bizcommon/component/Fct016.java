package com.sbisec.helios.ap.bizcommon.component;

import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.bizcommon.model.InputFct016Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct016Dto;

/**
 * 共通関数：FCT016
 * 顧客ポータル内画面への遷移指示
 * @author　SCSK
 */

@Component
public class Fct016 {

	public OutputFct016Dto doTransition(InputFct016Dto input) {
		return new OutputFct016Dto();
	}

}
