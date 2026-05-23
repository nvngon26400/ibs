package com.sbisec.helios.ap.bizcommon.model;

import java.util.List;

import lombok.Data;

/**
 * 共通関数DTO：FCT025
 * @author base 熊
 */

@Data
public class OutputFct025Dto extends BaseOutputDto{

	//仲介業者コード
	private String brokerCode;

	//銘柄リスト
	private List<Brand> brandList;
	
	/**
	 * 銘柄
	 * @author base 熊
	 */
	@Data
	public static class Brand {
		
		//NRIコード 
		private String nriCd;

		//E*TRADE扱い可否
		private String isEtradeService;
		
		//仲介業者扱可否
		private String isBrokerService;

	}
}
