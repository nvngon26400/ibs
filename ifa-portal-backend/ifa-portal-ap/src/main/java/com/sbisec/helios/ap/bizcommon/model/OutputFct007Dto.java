package com.sbisec.helios.ap.bizcommon.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 共通関数DTO：FCT007
 * @author base 熊
 */

@Data
public class OutputFct007Dto extends BaseOutputDto{

	//指定日
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+9")
	private Date designatedDate;
	
}
