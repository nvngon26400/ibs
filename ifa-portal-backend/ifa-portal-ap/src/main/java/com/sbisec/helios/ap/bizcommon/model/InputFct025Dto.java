package com.sbisec.helios.ap.bizcommon.model;

import java.util.List;

import lombok.Data;

/**
 * 共通関数DTO：FCT025
 * @author base 熊
 */

@Data
public class InputFct025Dto {
	
	//仲介業者コード
	private String brokerCode;
	
	//銘柄リスト
	private List<Brand> brandList;

	@Data
	public static class Brand{
		
		//NRIコード
		private String nriCd;
	}
}
