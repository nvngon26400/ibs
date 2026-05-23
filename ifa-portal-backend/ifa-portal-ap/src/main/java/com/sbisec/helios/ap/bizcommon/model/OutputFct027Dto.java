package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT027
 * @author base 熊
 */

@Data
public class OutputFct027Dto extends BaseOutputDto{

	//銘柄コード
	private String brandCode;
	
	//銘柄名
	private String brandName;
	
	//規制銘柄区分
	private String regKbn;
	
	//東証一般信用区分
	private String mktIppanLoanKbnTky;
	
	//名証一般信用区分
	private String mktIppanLoanKbnNgy;
	
	//福証一般信用区分
	private String mktIppanLoanKbnFko;
	
	//札証一般信用区分
	private String mktIppanLoanKbnSpr;
	
	//SOR取扱区分
	private String sorServiceKbn;
	
	//プレミアム空売り区分
	private String premiumShortSaleCcategory;
	
	//売買単位
	private String unit;
	
	//東証貸借区分
	private String mktLoanKbnTky;
	
	//PTS貸借区分
	private String mktLoanKbnPts;
	
	//PTS一般信用区分
	private String mktIppanLoanKbnPts;
	
}
