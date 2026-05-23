package com.sbisec.helios.ap.bizcommon.model;

import java.util.List;

import lombok.Data;

/**
 * 共通関数DTO：FCT003
 * @author base 熊
 */

@Data
public class OutputFct003Dto extends BaseOutputDto{
	
	//媒介可取引有無
	private String mediateAbleTradeFlag;
	
	//媒介可否リスト
	private List<MediatePropriety> mediateProprietyList;
	
	@Data
	public static class MediatePropriety{
		
		//証券金銭種別
		private String securityMoneyClass;
		
		//取引種別
		private String tradeClass;
		
		//国籍コード
		private String nationalityCode;
		
		//通貨コード
		private String currencyCode; 
		
		//媒介可否
		private String mediatePropriety;
	}

}
