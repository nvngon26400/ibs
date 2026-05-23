package com.sbisec.helios.ap.bizcommon.model;

import java.util.Date;

import lombok.Data;

/**
 * 共通関数DTO：FCT007
 * @author base 熊
 */

@Data
public class InputFct007Dto {
	
	//基準日
	private Date standardDate;

	//カレンダー区分
	private String calendarType;

	//日数
	private Integer day;

}
